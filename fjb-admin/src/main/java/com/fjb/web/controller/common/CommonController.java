package com.fjb.web.controller.common;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fjb.common.config.FjbConfig;
import com.fjb.common.constant.Constants;
import com.fjb.common.core.domain.AjaxResult;
import com.fjb.common.utils.StringUtils;
import com.fjb.common.utils.file.FileUploadUtils;
import com.fjb.common.utils.file.FileUtils;
import com.fjb.framework.config.ServerConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 通用请求处理
 *
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = FjbConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * IE7下载浏览器离线安装包
     *
     * @param fileName 文件名称
     */
    @GetMapping("common/download/browser")
    public void fileDownloadBrowser(String fileName, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String filePath = FjbConfig.getDownloadPath() + fileName;
            System.out.println("文件==="+filePath);
            File file=new File(filePath);
            if(!file.exists()){
                System.out.println("文件不存在");
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            response.setHeader("content-disposition","attachment; filename="+URLEncoder.encode(fileName, "utf-8")); //设置下载文件名称
            response.setHeader("Content-Length", String.valueOf(file.length())); //设置下载文件大小
            ServletOutputStream sos = null;
            FileInputStream fis = null;
            try {
            sos = response.getOutputStream();
            fis = new FileInputStream(file);
            int len = 0;
            byte [] buf = new byte[1024];
            while((len=fis.read(buf))!=-1){
                sos.write(buf,0,len);
            }
            } catch (IOException e1) {
                //e1.printStackTrace();
            } finally {
                try {
                    if (fis!=null) {
                        fis.close();
                    }
                    if (sos!=null) {
                        sos.close();
                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = FjbConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 本地资源路径
        String localPath = FjbConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(name, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }
}
