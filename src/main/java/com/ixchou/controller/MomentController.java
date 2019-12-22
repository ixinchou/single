package com.ixchou.controller;

import com.github.pagehelper.PageInfo;
import com.ixchou.annotation.MasterService;
import com.ixchou.model.entity.TMoment;
import com.ixchou.model.entity.TRelation;
import com.ixchou.model.vo.MomentVo;
import com.ixchou.services.impl.MomentServiceImpl;
import com.ixchou.services.impl.RelationServiceImpl;
import com.ixchou.util.http.response.HttpCode;
import com.ixchou.util.http.response.HttpResponse;
import com.ixchou.util.http.response.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/12/21 10:29<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 个人风采相关接口<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/moment")
@Api(tags = "Moment Controller 个人风采相关接口")
public class MomentController extends AbstractHttpController<TMoment> {

    @MasterService
    @Resource
    private MomentServiceImpl momentService;

    @Resource
    private RelationServiceImpl relationService;

    @ApiOperation(value = "添加")
    @PostMapping("/add")
    public HttpResponse insert(@RequestBody MomentVo moment) {
        return handleMoment(moment);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete/{id}")
    public HttpResponse delete(@PathVariable("id") Integer id) {
        return __delete(id);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/query/{id}")
    public HttpResponse query(@PathVariable("id") Integer id) {
        return __select(id);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public HttpResponse update(@RequestBody MomentVo moment) {
        return handleMoment(moment);
    }

    private HttpResponse handleMoment(MomentVo vo) {
        TMoment moment = new TMoment();
        if (vo.getId() > 0) {
            moment.setId(vo.getId());
        }
        moment.setTitle(vo.getTitle());
        moment.setType(vo.getType());
        moment.setContentId(vo.getContent());
        if (vo.getId() > 0) {
            saveRelations(vo.getId(), vo.getAttachments());
            return __update(moment);
        } else {
            HttpResponse response = __insert(moment);
            // 获取插入之后的新的 id
            saveRelations(moment.getId(), vo.getAttachments());
            return response;
        }
    }

    private void saveRelations(int host, List<Integer> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        // 添加附件列表
        for (Integer id : list) {
            TRelation relation = new TRelation();
            relation.setHost(host);
            relation.setAttachment(id);
            relationService.insert(relation);
        }
    }

    @ApiOperation("分页列取课程列表")
    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageIndex", defaultValue = "1", dataType = "Integer", value = "页码"),
            @ApiImplicitParam(name = "pageSize", defaultValue = "10", dataType = "Integer", value = "每页记录数"),
            @ApiImplicitParam(name = "type", defaultValue = "1", dataType = "Integer", value = "个人风采类型：1=教师，2=学生")})
    public HttpResponse list(@RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "type", defaultValue = "1") Byte type) {
        PageInfo<TMoment> info = momentService.list(type, pageIndex, pageSize);
        return HttpResponse.success(new Pagination<TMoment>(info));
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
    protected void setFuzzyProperties(TMoment entity, String queryValue) {

    }
}
