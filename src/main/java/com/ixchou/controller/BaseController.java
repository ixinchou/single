package com.ixchou.controller;

import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 23:45<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@RestController
@Api(tags = "基本接口")
public class BaseController {

    @GetMapping("/404.do")
    public Object error404() {
        return new HttpResponse<>(HttpCode.Err404);
    }
}
