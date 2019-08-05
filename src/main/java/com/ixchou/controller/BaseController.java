package com.ixchou.controller;

import com.ixchou.util.response.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 23:45<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@RestController
public class BaseController {

    @GetMapping("/404.do")
    public Object error404() {
        return new HttpResponse<>("404", "您的请求不存在！");
    }
}
