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
        int ret = childService.addChild(vo);
        switch (ret) {
            case -2:
                return HttpResponse.failure(MemberCode.MemberNotExists, "请先绑定您的微信");
            case -1:
                return HttpResponse.failure(ChildCode.ChildNameExist, "已有同名孩子的信息");
            case 1:
                return HttpResponse.success("添加成功");
            default:
                return HttpResponse.failure("添加失败");
        }
    }

    @ApiOperation(value = "查找用户绑定的孩子的信息")
    @GetMapping("/list/{sessionId}")
    public HttpResponse findChildren(@PathVariable("sessionId") String sessionId) {
        return HttpResponse.success(childService.find(sessionId));
    }

    @ApiOperation(value = "删除已添加的孩子信息")
    @GetMapping("/delete/{childId}")
    public HttpResponse deleteChild(@PathVariable("childId") Integer childId) {
        return HttpResponse.failure("暂时不支持删除");
    }
}
