package com.changgou.file.controller;

import com.changgou.file.util.FastDFSFile;
import com.changgou.file.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author caoqian
 * @ClassName FileUpLoadController
 * @Date 2020/3/24 11:12
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/upload")
@CrossOrigin
public class FileUpLoadController {

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public Result upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),//文件名字
                file.getBytes(),//文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename()));//文件扩展名
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        //拼接url地址
//      String url = "http://192.168.200.128:8080/" + uploads[0] + "/" + uploads[1];
        String url = FastDFSUtil.getTrackerInfo() + "/" + uploads[0] + "/" + uploads[1];
        return new Result(true, StatusCode.OK, "上传成功", url);
    }
}
