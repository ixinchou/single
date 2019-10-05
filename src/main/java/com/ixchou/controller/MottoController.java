package com.ixchou.controller;

import com.ixchou.model.entity.TMotto;
import com.ixchou.services.IMottoService;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/05 09:19<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/motto")
@Api(tags = "Motto Controller 校训相关接口")
public class MottoController {

    @Resource
    private IMottoService service;

    @PostMapping("insert")
    @ApiOperation("添加新的校训内容")
    public HttpResponse insert(@RequestBody TMotto motto) {
        if (motto.getId() <= 0) {
            if (service.insert(motto) > 0) {
                return HttpResponse.success("校训添加完毕");
            } else {
                return HttpResponse.failure(HttpCode.MottoInsertFailure);
            }
        } else {
            return HttpResponse.failure(HttpCode.MottoInsertUseUpdate);
        }
    }

    @PostMapping("update")
    @ApiOperation("修改校训内容")
    public HttpResponse update(@RequestBody TMotto motto) {
        if (motto.getId() > 0) {
            if (service.update(motto) > 0) {
                return HttpResponse.success("校训内容已修改");
            } else {
                return HttpResponse.failure(HttpCode.MottoUpdateFailure);
            }
        } else {
            return HttpResponse.failure(HttpCode.MottoUpdateUseInsert);
        }
    }

    @GetMapping("get")
    @ApiOperation("获取校训内容")
    public HttpResponse get() {
        TMotto motto = service.get();
        if (null == motto) {
            return HttpResponse.failure(HttpCode.MottoNotExist);
        } else {
            return HttpResponse.success(motto);
        }
    }
}
