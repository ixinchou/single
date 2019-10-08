package com.ixchou.controller;

import com.ixchou.model.entity.TChild;
import com.ixchou.model.vo.ChildVo;
import com.ixchou.services.IBaseService;
import com.ixchou.services.impl.ChildServiceImpl;
import com.ixchou.util.StringUtil;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
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
@Api(tags = "Child Controller 孩子相关接口")
public class ChildController extends AbstractBaseController<TChild> {

    @Resource
    private ChildServiceImpl childService;

    @ApiOperation(value = "给当前用户添加一个小孩信息")
    @PostMapping("add")
    public HttpResponse addChild(@RequestBody ChildVo vo) {
        if (StringUtil.isEmpty(vo.getSessionId())) {
            return HttpResponse.failure(HttpCode.MemberSessionIdNull, "添加孩子信息的 sessionId 不能为空");
        }
        if (StringUtil.isEmpty(vo.getName())) {
            return HttpResponse.failure(HttpCode.MemberNameNull, "添加孩子信息时名字不能为空");
        }
        int ret = childService.addChild(vo);
        switch (ret) {
            case -2:
                return HttpResponse.failure(HttpCode.MemberNotBind);
            case -1:
                return HttpResponse.failure(HttpCode.MemberChildExist);
            case 1:
                return HttpResponse.success("添加孩子信息成功");
            default:
                return HttpResponse.failure("添加孩子信息失败");
        }
    }

    @ApiOperation(value = "查找用户绑定的孩子的信息")
    @GetMapping("/list/{sessionId}")
    public HttpResponse findChildren(@PathVariable("sessionId") String sessionId) {
        return HttpResponse.success(childService.find(sessionId));
    }

    @ApiOperation(value = "删除已添加的孩子信息")
    @PostMapping("/delete/{childId}")
    public HttpResponse deleteChild(@PathVariable("childId") Integer childId) {
        TChild child = new TChild();
        child.setId(childId);
        child.setIsDeleted(IBaseService.True);
        if (_update(child) > 0) {
            return HttpResponse.success("已删除孩子的信息");
        }
        return HttpResponse.failure("删除孩子的信息失败");
    }
}
