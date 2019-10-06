package com.ixchou.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/10/06 16:00<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: 文件上传相关 api<br/>
 * <b>Description</b>:
 */
@RestController
@RequestMapping("/api/upload")
@Api(tags = "Attachment Controller 文件上传 api")
public class AttachmentController {

    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Resource
    private FastFileStorageClient client;

    @Resource
    @Qualifier("asyncTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    @ApiOperation(value = "文件上传")
    @PostMapping("single")
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
}
