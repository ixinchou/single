package com.ixchou.controller;

import com.ixchou.annotation.MasterService;
import com.ixchou.model.entity.TContent;
import com.ixchou.services.impl.ContentServiceImpl;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/12/19 14:26<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 富文本内容相关接口<br/>
 * <b>Description</b>:
 */
@RestController
@Api(tags = "Content Controller 富文本内容相关接口")
@RequestMapping("/api/content")
public class ContentController extends AbstractHttpController<TContent> {

    @MasterService
    @Resource
    private ContentServiceImpl contentService;

    @ApiOperation(value = "添加课程")
    @PostMapping("/add")
    public HttpResponse insert(@RequestBody TContent content) {
        return __insert(content);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public HttpResponse delete(@PathVariable("id") Integer id) {
        return __delete(id);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public HttpResponse update(@RequestBody TContent content) {
        return __update(content);
    }

    @ApiOperation(value = "单个查找")
    @GetMapping("/get/{id}")
    public HttpResponse list(@PathVariable("id") Integer id) {
        return __select(id);
    }

//    @ApiOperation("分页列取列表")
//    @GetMapping("/list")
//    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex", defaultValue = "1", dataType = "Integer", value = "页码"),
//            @ApiImplicitParam(name = "pageSize", defaultValue = "10", dataType = "Integer", value = "每页记录数")})
//    public HttpResponse list(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
//                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        return __list(pageIndex, pageSize, null);
//    }

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
    protected void setFuzzyProperties(TContent entity, String queryValue) {

    }
}
