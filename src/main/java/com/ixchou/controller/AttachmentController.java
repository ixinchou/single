package com.ixchou.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ixchou.model.entity.TAttachment;
import com.ixchou.services.IAttachmentService;
import com.ixchou.util.http.response.HttpCode;
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
public class AttachmentController {

    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Resource
    private FastFileStorageClient client;

    @Resource
    @Qualifier("asyncTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @Resource
    IAttachmentService service;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public WebAsyncTask<HttpResponse> upload(@RequestBody MultipartFile file) {
        logger.info("thread '" + Thread.currentThread().getName() + "' executing....");
        WebAsyncTask<HttpResponse> task = new WebAsyncTask<>(10000L, executor, () -> {
            logger.info("thread '" + Thread.currentThread().getName() + "' executing....");
            HttpResponse response;
            try {
                String orgName = FilenameUtils.getName(file.getOriginalFilename());
                long size = file.getSize();
                String contentType = file.getContentType();
                logger.info("name: " + orgName + ", size: " + size + ", type: " + contentType);
                StorePath path = client.uploadFile(file.getInputStream(), size, FilenameUtils.getExtension(file.getOriginalFilename()), null);
                response = HttpResponse.success(path);
            } catch (Exception e) {
                response = HttpResponse.failure("文件上传失败：" + e.getMessage());
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
        TAttachment attachment = service.query(signature);
        // 无论找到相同文件与否，都返回成功
        return HttpResponse.success(attachment);
    }
}
