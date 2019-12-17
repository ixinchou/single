package com.ixchou.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ixchou.annotation.MasterService;
import com.ixchou.model.entity.TAttachment;
import com.ixchou.services.impl.AttachmentServiceImpl;
import com.ixchou.util.DigestUtil;
import com.ixchou.util.ObjectUtil;
import com.ixchou.util.StringUtil;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/10/06 16:00<br/>
 * <b>Version</b>: 1.0<br>
 * <b>Subject</b>: 文件上传相关 api<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/attachment")
@Api(tags = "Attachment Controller 文件上传 api")
public class AttachmentController extends AbstractBaseController<TAttachment> {

    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Resource
    private FastFileStorageClient client;

    @Resource
    @Qualifier("asyncTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @MasterService
    @Resource
    AttachmentServiceImpl service;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public WebAsyncTask<HttpResponse> upload(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) {
        logger.info("thread '" + Thread.currentThread().getName() + "' executing....");
        WebAsyncTask<HttpResponse> task = new WebAsyncTask<>(10000L, executor, () -> {
            logger.info("thread '" + Thread.currentThread().getName() + "' executing....");
            request.setCharacterEncoding("UTF-8");
            // 反射获取所有属性
            TAttachment attachment = new TAttachment();
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                String value = request.getParameter(name);
                ObjectUtil.setPropertyValue(attachment, name, value);
            }
            TAttachment exist = _query("signature", attachment.getSignature());
            if (null != exist && exist.getSize().equals(attachment.getSize())) {
                // 如果文件签名相同且文件长度相同则认为有相同的文件存在，直接返回
                return HttpResponse.success(exist, "已有相同文件存在");
            }
            HttpResponse response;
            if (null == file || file.isEmpty()) {
                response = HttpResponse.failure("没有文件内容");
            } else {
                try {
                    String orgName = FilenameUtils.getName(file.getOriginalFilename());
                    attachment.setOriginName(orgName);
                    long size = file.getSize();
                    // 判断文件长度
                    if (null == attachment.getSize() || 0 == attachment.getSize()) {
                        attachment.setSize((int) size);
                    }
                    // 判断文件特征码
                    if (StringUtil.isEmpty(attachment.getSignature())) {
                        attachment.setSignature(DigestUtil.sha1(file.getInputStream()));
                    }
                    exist = _query("signature", attachment.getSignature());
                    if (null != exist && exist.getSize().equals(attachment.getSize())) {
                        response = HttpResponse.success(exist, "已有相同文件存在无需重复上传");
                    } else {
                        StorePath path = client.uploadFile(file.getInputStream(), size, FilenameUtils.getExtension(file.getOriginalFilename()), null);
                        attachment.setUrl(path.getFullPath());
                        _insert(attachment);
                        response = HttpResponse.success(attachment, "上传成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response = HttpResponse.failure("文件上传失败：" + e.getMessage());
                }
            }
            logger.info("thread '" + Thread.currentThread().getName() + "' executed....");
            return response;
        });
        logger.info("thread '" + Thread.currentThread().getName() + "' executed....");
        return task;
    }

    @ApiOperation(value = "通过文件特征码检索是否已有相同的文件存在")
    @GetMapping("query/{signature}")
    public HttpResponse query(@PathVariable("signature") String signature) {
        // f2dcdee25062e554925069e67ecd4f18368834ca
        TAttachment attachment = _query("signature", signature);
        // 无论找到相同文件与否，都返回成功
        return HttpResponse.success(attachment);
    }
}
