package com.bjike.goddess.progressmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 进度表数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProgressTableDTO extends BaseDTO {

    public interface validateProject {
    }

    /**
     * 项目Id
     */
    @NotBlank(message = "项目Id不能为空", groups = {ProgressTableDTO.validateProject.class})
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}