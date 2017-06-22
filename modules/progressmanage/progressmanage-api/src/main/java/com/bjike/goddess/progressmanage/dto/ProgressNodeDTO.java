package com.bjike.goddess.progressmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 进度节点数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:21 ]
 * @Description: [ 进度节点数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProgressNodeDTO extends BaseDTO {

    public interface NodeValidate {
    }

    /**
     * 项目Id
     */
    @NotBlank(message = "项目Id不能为空", groups = {ProgressNodeDTO.NodeValidate.class})
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}