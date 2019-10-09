package com.ixchou.controller;

import com.github.pagehelper.PageInfo;
import com.ixchou.model.entity.TMember;
import com.ixchou.model.vo.MemberVo;
import com.ixchou.services.impl.MemberServiceImpl;
import com.ixchou.util.StringUtil;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import com.ixchou.util.http.response.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 12:49<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 用户信息<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/member")
@Api(tags = "Member Controller 会员相关接口")
public class MemberController extends AbstractBaseController<TMember> {

    @Resource
    private MemberServiceImpl memberService;

    @ApiOperation(value = "通过平台自身的sessionId查询用户信息")
    @GetMapping("find/{sessionId}")
    public HttpResponse findBySessionId(@PathVariable("sessionId") String sessionId) {
        if (StringUtil.isEmpty(sessionId)) {
            return HttpResponse.failure("查询的 sessionId 不能为空");
        }
        TMember member = _query("sessionId", sessionId);
        if (null == member) {
            return HttpResponse.failure(HttpCode.MemberNotBind);
        }
        return HttpResponse.success(new MemberVo(member));
    }

    @ApiOperation(value = "通过平台自身的sessionId更新我的名字")
    @PostMapping("update/name")
    public HttpResponse updateMyName(@RequestBody MemberVo vo) {
        if (StringUtil.isEmpty(vo.getSessionId())) {
            return HttpResponse.failure(HttpCode.MemberSessionIdNull);
        }
        if (StringUtil.isEmpty(vo.getUserName())) {
            return HttpResponse.failure(HttpCode.MemberNameNull);
        }
        boolean updated = memberService.updateNameBySessionId(vo.getSessionId(), vo.getUserName());
        if (updated) {
            return HttpResponse.success("名字更改成功");
        } else {
            return HttpResponse.failure("名字更改失败");
        }
    }

    @ApiOperation(value = "列举所有用户")
    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex", defaultValue = "1", dataType = "Integer", value = "页码"),
            @ApiImplicitParam(name = "pageSize", defaultValue = "10", dataType = "Integer", value = "每页记录数"),
            @ApiImplicitParam(name = "query", defaultValue = "", dataType = "String", value = "查询条件(可同时支持用户名字/手机号码/微信昵称的模糊查询)")})
    public HttpResponse list(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "query", defaultValue = "", required = false) String query) {
        PageInfo<TMember> info;
        if (StringUtil.isEmpty(query)) {
            info = _list(pageIndex, pageSize);
        } else {
            TMember search = new TMember();
            search.setUserName(query);
            search.setPhone(query);
            search.setWxNickName(query);
            info = _fuzzySearch(search, pageIndex, pageSize);
        }
        Pagination<TMember> page = new Pagination<>(info);
        List<MemberVo> list = new ArrayList<>();
        if (null != info.getList()) {
            for (TMember member : info.getList()) {
                list.add(new MemberVo(member));
            }
        }
        Pagination<MemberVo> pagination = new Pagination<>();
        pagination.setList(list);
        pagination.setPageIndex(page.getPageIndex());
        pagination.setPageRecords(page.getPageRecords());
        pagination.setPageSize(page.getPageSize());
        pagination.setTotalPages(page.getTotalPages());
        pagination.setTotalRecords(page.getTotalRecords());
        return HttpResponse.success(pagination);
    }
}
