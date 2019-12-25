package com.ixchou.services.impl;

import com.ixchou.controller.WxController;
import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.*;
import com.ixchou.services.IMemberService;
import com.ixchou.util.GsonUtil;
import com.ixchou.util.StringUtil;
import com.ixchou.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 08:11<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 会员相关业务逻辑<br/>
 * <b>Description</b>:
 */
@Service
@DependsOn("springContextAware")
public class MemberServiceImpl extends BaseServiceImpl<TMember> implements IMemberService {

    private final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Override
    public TMember findOrRegisterByWxInfo(WxRegistryVo info) {
        // 通过用户的登录码去查询用户的基本信息，比如手机号码，openid等
        RestTemplate template = new RestTemplate();
        String response = template.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid={1}&secret={2}&js_code={3}&grant_type=authorization_code",
                String.class, "wxd3d08f6f3ef69525", "463ec9bdd2d9670b9379a78e8ce12255", info.getLoginCode());
        System.out.println("template: " + template + ", " + "response: " + response);
        WxSessionOpenIdVo openIdVo = GsonUtil.fromString(response, WxSessionOpenIdVo.class);
        TMember member = query("wxId", openIdVo.getOpenid());
        if (null != member) {
            // 更新最后登录时间
            member.setLastLoginTime(new Date());
            // 同时更新微信昵称
            member.setWxNickName(info.getNickName());
            // 同时更新微信 sessionid以便后续解码手机号码等信息
            member.setWxSession(openIdVo.getSession_key());
            // 同时更新微信的头像
            member.setWxAvatar(info.getAvatar());
            update(member);
            return member;
        }
        // 注册一个新的账号
        member = new TMember();
        member.setIsUploadAble(False);
        member.setLastLoginTime(new Date());
        member.setUserName("");
        member.setPhone("");
        member.setWxId(openIdVo.getOpenid());
        member.setWxNickName(info.getNickName());
        member.setWxSession(openIdVo.getSession_key());
        member.setWxSex(info.getGender());
        member.setWxAvatar(info.getAvatar());
        member.setSessionId(UUIDGenerator.getUUID());
        insert(member);
        return member;
    }

    @Override
    public TMember fetchingWxPhone(WxPhoneEncryptedVo info) {
        TMember member = query("sessionId", info.getSessionId());
        if (null == member) {
            return null;
        }
        byte[] data = null;
        byte[] encryptedData = Base64Utils.decodeFromString(info.getEncryptedData());
        byte[] ivData = Base64Utils.decodeFromString(info.getIv());
        byte[] sessionData = Base64Utils.decodeFromString(member.getWxSession());
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(sessionData, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            data = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != data) {
            String resultString = new String(data, StandardCharsets.UTF_8);
            System.out.println(resultString);
            WxPhoneDecryptedVo decryptedVo = GsonUtil.fromString(resultString, WxPhoneDecryptedVo.class);
            member.setPhone(decryptedVo.getPhoneNumber());
            update(member);
        }
        return member;
    }

    @Override
    public boolean updateNameBySessionId(String mySessionId, String myNewName) {
        TMember member = query("sessionId", mySessionId);
        if (null == member) {
            return false;
        }
        if (StringUtil.isEmpty(myNewName)) {
            return false;
        }
        member.setUserName(myNewName);
        int updated = update(member);
        return updated > 0;
    }

    public boolean updateUploadPermission(Integer id, Boolean uploadAble) {
        TMember member = select(id);
        if (null == member) {
            return false;
        }
        member.setIsUploadAble(uploadAble ? MemberServiceImpl.True : MemberServiceImpl.False);
        int updated = update(member);
        return updated > 0;
    }
}
