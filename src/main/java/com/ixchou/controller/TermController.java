package com.ixchou.controller;

import com.ixchou.annotation.MasterService;
import com.ixchou.model.entity.TTerm;
import com.ixchou.services.impl.TermServiceImpl;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/20 22:00<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 学期类型相关 api<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/term")
@Api(tags = "Term Controller 学期类型相关接口")
public class TermController extends AbstractHttpController<TTerm> {

    @MasterService
    @Resource
    private TermServiceImpl termService;

    @ApiOperation("分页列取列表")
    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex", defaultValue = "1", dataType = "Integer", value = "页码"),
            @ApiImplicitParam(name = "pageSize", defaultValue = "10", dataType = "Integer", value = "每页记录数")})
    public HttpResponse list(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return __list(pageIndex, pageSize, null);
    }

    @Override
    protected String checkSameRecordPropertyName() {
        return null;
    }

    @Override
    protected String insertSuccessMessage() {
        return null;
    }

    @Override
    protected String deleteSuccessMessage() {
        return null;
    }

    @Override
    protected String updateSuccess() {
        return null;
    }

    @Override
    protected HttpCode insertExistCode() {
        return null;
    }

    @Override
    protected void setFuzzyProperties(TTerm entity, String queryValue) {

    }
}
