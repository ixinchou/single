package com.ixchou.controller;

import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.MemberVo;
import com.ixchou.services.IMemberService;
import com.ixchou.util.StringUtil;
import com.ixchou.util.response.HttpResponse;
import com.ixchou.util.response.MemberCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 12:49<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 用户信息<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Resource
    private IMemberService memberService;

    @ApiOperation(value = "通过平台自身的sessionId查询用户信息")
    @GetMapping("/find/{sessionId}")
    public HttpResponse findBySessionId(@PathVariable("sessionId") String sessionId) {
        if (StringUtil.isEmpty(sessionId)) {
            return HttpResponse.failure("查询的 sessionId 不能为空");
        }
        TMember member = memberService.findBySessionId(sessionId);
        return HttpResponse.success(new MemberVo(member));
    }

    @ApiOperation(value = "通过平台自身的sessionId更新我的名字")
    @PostMapping("update/name")
    public HttpResponse updateMyName(@RequestBody MemberVo vo) {
        if (StringUtil.isEmpty(vo.getSessionId())) {
            return HttpResponse.failure(MemberCode.SessionIdEmpty, "SessionId 不能为空");
        }
        if (StringUtil.isEmpty(vo.getUserName())) {
            return HttpResponse.failure(MemberCode.NameCannotUpdateToEmpty, "用户名字不能改为空");
        }
        boolean updated = memberService.updateMyName(vo);
        if (updated) {
            return HttpResponse.success("名字更改成功");
        } else {
            return HttpResponse.failure("名字更改失败");
        }
    }
}
