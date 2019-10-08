package com.ixchou.controller;

import com.github.pagehelper.PageInfo;
import com.ixchou.model.entity.TMember;
import com.ixchou.model.entity.TMotto;
import com.ixchou.model.vo.DefaultQueryVo;
import com.ixchou.services.impl.MemberServiceImpl;
import com.ixchou.services.impl.MottoServiceImpl;
import com.ixchou.util.StringUtil;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/05 09:19<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 校训<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/motto")
@Api(tags = "Motto Controller 校训相关接口")
public class MottoController extends AbstractBaseController<TMotto> {

    @Resource
    private MottoServiceImpl mottoService;

    @Resource
    private MemberServiceImpl memberService;

    @PostMapping("insert")
    @ApiOperation("添加新的校训内容")
    public HttpResponse insert(@RequestBody DefaultQueryVo queryVo) {
        if (queryVo.getId() <= 0) {
            return handleMotto(queryVo);
        } else {
            return HttpResponse.failure(HttpCode.MottoInsertUseUpdate);
        }
    }

    @PostMapping("update")
    @ApiOperation("修改校训内容")
    public HttpResponse update(@RequestBody DefaultQueryVo queryVo) {
        if (queryVo.getId() > 0) {
            return handleMotto(queryVo);
        } else {
            return HttpResponse.failure(HttpCode.MottoUpdateUseInsert);
        }
    }

    private HttpResponse handleMotto(DefaultQueryVo queryVo) {
        String session = queryVo.getSessionId();
        if (StringUtil.isEmpty(session)) {
            return HttpResponse.failure(HttpCode.MemberSessionIdNull, "添加、修改校训内容时 session 内容不能为空");
        }
        TMember member = memberService.query("sessionId", session);
        if (null == member) {
            return HttpResponse.failure(HttpCode.MemberNotBind);
        }
        TMotto motto = new TMotto();
        motto.setContent(queryVo.getContent());
        motto.setPostMember(member.getId());
        if (queryVo.getId() > 0) {
            motto.setId(queryVo.getId());
            motto.setLastUpdate(new Date());
            motto.setUpdateTimes(1);
            if (mottoService.update(motto) > 0) {
                return HttpResponse.success(motto, "校训内容已修改");
            } else {
                return HttpResponse.failure(HttpCode.MottoUpdateFailure);
            }
        } else {
            if (mottoService.insert(motto) > 0) {
                return HttpResponse.success(motto, "校训添加完毕");
            } else {
                return HttpResponse.failure(HttpCode.MottoUpdateFailure);
            }
        }
    }

    @GetMapping("get")
    @ApiOperation("获取校训内容")
    public HttpResponse get() {
        PageInfo<TMotto> info = _list(1, 10);
        TMotto motto = (null == info.getList() || info.getList().size() < 1) ? null : info.getList().get(0);
        return HttpResponse.success(motto);
    }
}
