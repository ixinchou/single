package com.ixchou.controller;

import com.ixchou.annotation.MasterService;
import com.ixchou.model.entity.TEnrolled;
import com.ixchou.model.vo.EnrolledVo;
import com.ixchou.services.impl.EnrolledServiceImpl;
import com.ixchou.util.StringUtil;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2020/01/02 15:08<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 报名相关的 controller <br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/enrolled")
@Api(tags = "学员报名相关接口")
public class EnrolledController extends AbstractHttpController<TEnrolled> {

    @MasterService
    @Resource
    private EnrolledServiceImpl enrolledService;

    @ApiOperation(value = "报名")
    @PostMapping("/add")
    public HttpResponse insert(@RequestBody EnrolledVo vo) {
        if (StringUtil.isEmpty(vo.getSessionId())) {
            return HttpResponse.failure(HttpCode.MemberSessionIdNull);
        }
        int result = enrolledService.enroll(vo);
        if (result > 0) {
            return HttpResponse.success("报名成功");
        }
        switch (result) {
            case -1:
                return HttpResponse.failure(HttpCode.MemberSessionIdNull);
            case -2:
                return HttpResponse.failure(HttpCode.CourseIdNull);
            case -3:
                return HttpResponse.failure(HttpCode.CourseEnrollTypeNull);
            case -4:
                return HttpResponse.failure(HttpCode.MemberNotBind);
            case -5:
                return HttpResponse.failure(HttpCode.CourseChildNotExist);
        }
        return HttpResponse.failure("报名失败(" + result + ")");
    }

    @ApiOperation(value = "取消报名")
    @PostMapping("/delete/{id}")
    public HttpResponse delete(@PathVariable("id") Integer id) {
        return __delete(id);
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
    protected void setFuzzyProperties(TEnrolled entity, String queryValue) {

    }
}
