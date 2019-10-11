package com.ixchou.controller;

import com.ixchou.model.entity.TCourse;
import com.ixchou.services.impl.TCourseServiceImpl;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/09 22:22<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 课程特长相关 api 接口<br/>
 * <b>Description</b>:
 */
@RestController
@Api(tags = "Course Controller 课程、特长相关接口")
@RequestMapping("/api/course")
public class CourseController extends AbstractHttpController<TCourse> {

    @Resource
    private TCourseServiceImpl courseService;

    @ApiOperation(value = "添加课程")
    @PostMapping("/add")
    public HttpResponse insert(@RequestBody TCourse course) {
        return __insert(course);
    }

    @ApiOperation(value = "删除课程")
    @PostMapping("/delete/{id}")
    public HttpResponse delete(@PathVariable("id") Integer id) {
        return __delete(id);
    }


    @ApiOperation(value = "修改课程")
    @PostMapping("/update")
    public HttpResponse update(@RequestBody TCourse course) {
        return __update(course);
    }

    @ApiOperation("分页列取课程列表")
    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex", defaultValue = "1", dataType = "Integer", value = "页码"),
            @ApiImplicitParam(name = "pageSize", defaultValue = "10", dataType = "Integer", value = "每页记录数"),
            @ApiImplicitParam(name = "query", defaultValue = "", dataType = "String", value = "通过课程名称模糊查询")})
    public HttpResponse list(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "query", defaultValue = "", required = false) String query) {
        return __list(pageIndex, pageSize, query);
    }

    @Override
    protected String checkSameRecordPropertyName() {
        return "name";
    }

    @Override
    protected String insertSuccessMessage() {
        return "课程添加成功";
    }

    @Override
    protected String deleteSuccessMessage() {
        return "课程已删除";
    }

    @Override
    protected String updateSuccess() {
        return "课程修改成功";
    }

    @Override
    protected HttpCode insertExistCode() {
        return HttpCode.CourseNameExist;
    }

    @Override
    protected void setFuzzyProperties(TCourse entity, String queryValue) {
        entity.setName(queryValue);
    }

}
