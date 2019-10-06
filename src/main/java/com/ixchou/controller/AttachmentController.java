package com.ixchou.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ixchou.util.http.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Resource
    private FastFileStorageClient client;

    @ApiOperation(value = "文件上传")
    @PostMapping("single")
    public HttpResponse upload(@RequestBody MultipartFile file) {
        try {
            StorePath path = client.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            return HttpResponse.success(path);
        } catch (Exception e) {
            return HttpResponse.failure("文件上传失败：" + e.getMessage());
        }
    }
}
