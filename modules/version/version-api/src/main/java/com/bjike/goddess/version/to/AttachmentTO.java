package com.bjike.goddess.version.to;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 附件to
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-07 09:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AttachmentTO {
    public interface TestDEL {
    }

    public interface UPLOAD {
    }

    /**
     * id
     */
    @NotBlank(groups = {AttachmentTO.UPLOAD.class},message = "id不能为空")
    private String id;
    /**
     * 模块名
     */
    @NotBlank(groups = {AttachmentTO.UPLOAD.class},message = "模块名不能为空")
    private String module;
    /**
     * 附件文件夹
     */
    @NotBlank(groups = {AttachmentTO.UPLOAD.class},message = "附件文件夹不能为空")
    private String folder;

    /**
     * 删除路径
     */
    @NotNull(groups = {AttachmentTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
