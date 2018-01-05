package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 多项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectSingleUIBTO extends BaseTO {


    /**
     * 界面选择
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "界面选择不能为空")
    private InterfaceSelect interfaceSelect;

    /**
     * 多项目单界面
     */
    private List<MultipleProjectSingleUITO> multipleProjectSingleUITOS;




    public InterfaceSelect getInterfaceSelect() {
        return interfaceSelect;
    }

    public void setInterfaceSelect(InterfaceSelect interfaceSelect) {
        this.interfaceSelect = interfaceSelect;
    }

    public List<MultipleProjectSingleUITO> getMultipleProjectSingleUITOS() {
        return multipleProjectSingleUITOS;
    }

    public void setMultipleProjectSingleUITOS(List<MultipleProjectSingleUITO> multipleProjectSingleUITOS) {
        this.multipleProjectSingleUITOS = multipleProjectSingleUITOS;
    }
}