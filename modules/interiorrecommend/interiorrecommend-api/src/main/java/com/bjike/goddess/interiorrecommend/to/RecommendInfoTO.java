package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 推荐信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendInfoTO extends BaseTO {

    /**
     * 推荐要求设定id
     */
    @NotBlank(message = "推荐要求设定id不能为空", groups = {ADD.class, EDIT.class})
    private String requireId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 推荐内容
     */
    @NotNull(message = "推荐内容不能为空", groups = {ADD.class, EDIT.class})
    private List<RecommendContentTO> contentList;

    public String getRequireId() {
        return requireId;
    }

    public void setRequireId(String requireId) {
        this.requireId = requireId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RecommendContentTO> getContentList() {
        return contentList;
    }

    public void setContentList(List<RecommendContentTO> contentList) {
        this.contentList = contentList;
    }

}