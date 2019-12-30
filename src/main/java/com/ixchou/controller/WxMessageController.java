package com.ixchou.controller;

import com.ixchou.util.DigestUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/25 22:47<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/wxmsg")
@Api(tags = "WX Message 微信消息相关接口")
public class WxMessageController {

    /**
     * url: https://edu.ixchou.com/message/pull
     * token: 92c8b2d368204645a7420d9e5889ddd8
     * key: NU9Zhmm72snqUjJZ5dKuYX0909KvtoVVm4K95lbahRE
     */

    @Value("${setting.wx-msg-token}")
    private String token;

    @Value("${setting.wx-msg-security-key}")
    private String securityKey;

    @RequestMapping(value = "/service", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String message() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        String method = attributes.getRequest().getMethod().toLowerCase();
        // get，微信发来的验证内容，需要返回验证结果
        if (Objects.equals(method, "get")) {
            return checkWeChatTest(attributes.getRequest());
        }
        return "success";
    }

    private String checkWeChatTest(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        List<String> list = new ArrayList<>();
        list.add(request.getParameter("timestamp"));
        list.add(request.getParameter("nonce"));
        list.add(token);
        Collections.sort(list);
        String check = DigestUtil.sha1(String.join("", list).getBytes());
        System.out.println("signature-origin: " + check);
        System.out.println("signature: " + signature);
        if (check.equals(signature)) {
            return echostr;
        }
        return "error";
    }
}
