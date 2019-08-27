package com.ixchou.controller;

import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.MemberVo;
import com.ixchou.model.vo.WxPhoneEncryptedVo;
import com.ixchou.model.vo.WxRegistryVo;
import com.ixchou.services.IMemberService;
import com.ixchou.util.StringUtil;
import com.ixchou.util.response.HttpResponse;
import com.ixchou.util.response.WxCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 00:52<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/wx")
public class WxController {

    private final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Resource
    private IMemberService memberService;

    @ApiOperation("解密微信绑定的手机号码")
    @PostMapping("phone")
    public WebAsyncTask<HttpResponse> fetchWxPhone(@RequestBody WxPhoneEncryptedVo info) {
        return new WebAsyncTask<>(() -> {
            if (StringUtil.isEmpty(info.getSessionId())) {
                return HttpResponse.failure(WxCode.NullOfLoginCode, "解密手机号码时sessionId不能为空");
            }
            return HttpResponse.success(new MemberVo(memberService.fetchingWxPhone(info)));
        });
    }

    @ApiOperation(value = "通过微信绑定的信息查找用户", notes = "通过微信端获取到的用户数据去获取用户的基本信息，如果用户不存在则直接注册")
    @PostMapping("registry")
    public WebAsyncTask<HttpResponse> findWxUserInfo(@RequestBody WxRegistryVo info) {
        return new WebAsyncTask<>(() -> {
            if (StringUtil.isEmpty(info.getLoginCode())) {
                logger.info("查询请求中的 login code 为空");
                return HttpResponse.failure(WxCode.NullOfLoginCode, "login code 为空");
            }
            TMember member = memberService.findByWxInfo(info);
            MemberVo vo = new MemberVo(member);
            return HttpResponse.success(vo);
        });
        // 通过微信用户信息查找用户手机号码等信息之后保留注册信息
//        if (StringUtil.isEmpty(info.getLoginCode())) {
//            logger.info("登录码为空：" + info);
//            return HttpResponse.failure(WxCode.NullOfLoginCode, "登录码不能为空");
//        } else {
//            // 通过用户的登录码去查询用户的基本信息，比如手机号码，openid等
//            RestTemplate template = new RestTemplate();
//            String response = template.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid={1}&secret={2}&js_code={3}&grant_type=authorization_code",
//                    String.class, "wxd3d08f6f3ef69525", "463ec9bdd2d9670b9379a78e8ce12255", info.getLoginCode());
//            System.out.println("template: " + template + ", " + "response: " + response);
//            WxSessionOpenIdVo vo = GsonUtil.fromString(response, WxSessionOpenIdVo.class);
//            //System.out.println(vo);
//            TMember member = memberService.register(info, vo);
//            if (null == member) {
//                return HttpResponse.failure("用户信息获取失败");
//            } else {
//                return HttpResponse.success(member);
//            }
//            if (null != vo) {

//            } else {
//                return HttpResponse.failure(WxCode.GetSessionFailure, "获取微信登录信息失败");
//            }
//        }
    }
}
