package com.bjike.goddess.system.to;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 附件to
 *
 * @Author: [xiazhili]
 * @Date: [2017-08-07 09:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SystemTO {
    public interface TestDEL {
    }

    public interface UPLOAD {
    }

    /**
     * id
     */
    @NotBlank(groups = {SystemTO.UPLOAD.class},message = "id不能为空")
    private String id;
    /**
     * 附件文件夹
     */
    @NotBlank(groups = {SystemTO.UPLOAD.class},message = "附件文件夹不能为空")
    private String folder;

    /**
     * 删除路径
     */
    @NotNull(groups = {SystemTO.TestDEL.class}, message = "删除路径不能为空")
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


    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
