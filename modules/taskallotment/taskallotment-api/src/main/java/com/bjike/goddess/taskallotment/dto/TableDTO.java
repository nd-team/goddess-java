package com.bjike.goddess.taskallotment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * 项目表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableDTO extends BaseDTO {
    public interface TABLE {
    }

    public interface EXPORT {
    }
    public interface LIST{}

    /**
     * 项目id数组
     */
    @NotNull(groups = TableDTO.TABLE.class, message = "项目id数组不能为空")
    private String[] projectIds;

    /**
     * 项目表数组
     */
    @NotNull(groups = TableDTO.EXPORT.class, message = "项目表数组不能为空")
    private String[] tables;

    /**
     * 项目名称id
     */
    @NotBlank(groups = TableDTO.LIST.class, message = "项目名称id不能为空")
    private String projectId;
    /**
     * 地区
     */
    private String area;
    /**
     * 所属项目组
     */
    private String depart;
    /**
     * 立项情况
     */
    private String makeProject;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getMakeProject() {
        return makeProject;
    }

    public void setMakeProject(String makeProject) {
        this.makeProject = makeProject;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public String[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String[] projectIds) {
        this.projectIds = projectIds;
    }

    @Override
    public String toString() {
        return "TableDTO{" +
                "projectIds=" + Arrays.toString(projectIds) +
                ", tables=" + Arrays.toString(tables) +
                ", projectId='" + projectId + '\'' +
                ", area='" + area + '\'' +
                ", depart='" + depart + '\'' +
                ", makeProject='" + makeProject + '\'' +
                '}';
    }
}