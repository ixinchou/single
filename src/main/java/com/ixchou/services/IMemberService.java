package com.ixchou.services;

import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.MemberVo;
import com.ixchou.model.vo.WxPhoneEncryptedVo;
import com.ixchou.model.vo.WxRegistryVo;

import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 18:32<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 用户服务类<br/>
 * <b>Description</b>:
 */
public interface IMemberService {

    /**
     * 通过微信的绑定信息查找或注册用户
     *
     * @param info 微信平台绑定的基本信息
     * @return 返回找到或新注册的用户信息
     */
    TMember findOrRegisterByWxInfo(WxRegistryVo info);

    /***
     * 解密微信绑定的手机号码
     * @param vo 微信中绑定的信息
     * @return 返回带手机号码的用户信息
     */
    TMember fetchingWxPhone(WxPhoneEncryptedVo vo);

    /**
     * 通过 sessionId 查找并更新成员的名字
     */
    boolean updateNameBySessionId(String mySessionId, String myNewName);
}
