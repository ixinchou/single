package com.ixchou.controller;

import com.ixchou.model.vo.ChildVo;
import com.ixchou.services.IChildService;
import com.ixchou.util.StringUtil;
import com.ixchou.util.response.ChildCode;
import com.ixchou.util.response.HttpResponse;
import com.ixchou.util.response.MemberCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 17:48<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 孩子的信息<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/child")
public class ChildController {

    @Resource
    private IChildService childService;

    @ApiOperation(value = "给当前用户添加一个小孩信息")
    @PostMapping("add")
    public HttpResponse addChild(@RequestBody ChildVo vo) {
        if (StringUtil.isEmpty(vo.getSessionId())) {
            return HttpResponse.failure(MemberCode.SessionIdEmpty, "添加孩子信息的 sessionId 不能为空");
        }
        if (StringUtil.isEmpty(vo.getName())) {
            return HttpResponse.failure(ChildCode.ChildNameCannotBeEmpty, "添加孩子信息时名字不能为空");
        }
        boolean b = childService.addChild(vo);
        if (b) {
            return HttpResponse.success("添加成功");
        } else {
            return HttpResponse.failure("添加失败");
        }
    }

    @ApiOperation(value = "查找用户绑定的孩子的信息")
    @GetMapping("/list/{sessionId}")
    public HttpResponse findChildren(@PathVariable("sessionId") String sessionId) {
        return HttpResponse.success(childService.find(sessionId));
    }
}
