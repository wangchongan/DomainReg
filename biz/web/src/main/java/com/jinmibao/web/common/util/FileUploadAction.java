package com.jinmibao.web.common.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传公用ACTION TODO-待完善
 * 
 * @author chongan.wangca 2012-3-25 下午3:44:02
 */
public class FileUploadAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = -558099030509822282L;

    private File[]            file;

    private String[]          fileName;

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }

    public String execute() throws Exception {
        String realPath = ServletActionContext.getServletContext().getRealPath("/image");
        if (this.file != null && file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                File saveFile = new File(new File(realPath), fileName[i]);
                if (!saveFile.getParentFile().exists()) saveFile.getParentFile().mkdirs();
                FileUtils.copyFile(file[i], saveFile);
            }
            ActionContext.getContext().put("message", "上传成功！");
            return SUCCESS;
        } else {
            ActionContext.getContext().put("message", "没有找到可上传的文件");
            return SUCCESS;
        }
    }
}
