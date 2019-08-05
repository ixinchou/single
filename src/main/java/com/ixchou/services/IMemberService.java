package com.ixchou.services;

import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.MemberVo;
import com.ixchou.model.vo.WxPhoneEncryptedVo;
import com.ixchou.model.vo.WxRegistryVo;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 18:32<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 用户服务类<br/>
 * <b>Description</b>:
 */
public interface IMemberService {

    /**
     * 通过 sessionId 查找用户信息
     *
     * @param sessionId 新筹平台的sessionid
     * @return 返回找到的用户信息
     */
    TMember findBySessionId(String sessionId);

    /**
     * 通过微信的绑定信息查找用户
     *
     * @param info 微信平台绑定的基本信息
     * @return 返回找到或新注册的用户信息
     */
    TMember findByWxInfo(WxRegistryVo info);

    /***
     * 解密微信绑定的手机号码
     * @param vo 微信中绑定的信息
     * @return 返回带手机号码的用户信息
     */
    TMember fetchingWxPhone(WxPhoneEncryptedVo vo);

    /**
     * 更新我的名字
     * @param vo
     */
    boolean updateMyName(MemberVo vo);
}
