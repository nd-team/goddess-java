package com.bjike.goddess.individualvision.action.individualvision;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.api.IndividualVisionPlanAPI;
import com.bjike.goddess.individualvision.bo.IndividualVisionPlanBO;
import com.bjike.goddess.individualvision.dto.IndividualVisionPlanDTO;
import com.bjike.goddess.individualvision.to.IndividualVisionPlanTO;
import com.bjike.goddess.individualvision.vo.IndividualVisionPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 个人愿景计划
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:26 ]
 * @Description: [ 个人愿景计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("individualvision/individualvisionplan")
public class IndividualVisionPlanAction {
    @Autowired
    private IndividualVisionPlanAPI individualVisionPlanAPI;
    /**
     * 获取个人愿景计划
     *
     * @param individualVisionPlanDTO 个人愿景计划dto
     * @return class IndividualVisionPlanVO
     * @des 获取所有个人愿景计划
     * @version v1
     */
    @GetMapping("v1/listIndividualVisionPlan")
    public Result findListIndividualVisionPlan(IndividualVisionPlanDTO individualVisionPlanDTO) throws ActException {
        try {
            List<IndividualVisionPlanVO> individualVisionPlanVOS = BeanTransform.copyProperties
                    (individualVisionPlanAPI.findListIndividualVisionPlan(individualVisionPlanDTO),IndividualVisionPlanVO.class);
            return ActResult.initialize(individualVisionPlanVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加个人愿景计划
     *
     * @param individualVisionPlanTO 个人愿景计划数据to
     * @return class IndividualVisionPlanVO
     * @des 添加个人愿景计划
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.insertIndividualVisionPlan(individualVisionPlanTO);
            return ActResult.initialize(individualVisionPlanBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑个人愿景计划
     *
     * @param individualVisionPlanTO 个人愿景计划数据to
     * @return class IndividualVisionPlanVO
     * @des 编辑个人愿景计划
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editIndividualVisionPlan(IndividualVisionPlanTO individualVisionPlanTO) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.editIndividualVisionPlan(individualVisionPlanTO);
            return ActResult.initialize(individualVisionPlanBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除个人愿景计划
     *
     * @param id 用户id
     * @des 根据用户id删除个人愿景计划记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeIndividualVisionPlan(String id) throws ActException {
        try {
            individualVisionPlanAPI.removeIndividualVisionPlan(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 审核
     *
     * @param individualVisionPlanTO 个人愿景计划数据bo
     * @return class IndividualVisionPlanVO
     * @des 审核个人愿景计划
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result auditIndividualVisionPlan(@Validated IndividualVisionPlanTO individualVisionPlanTO) throws ActException {
        try {
            IndividualVisionPlanBO individualVisionPlanBO = individualVisionPlanAPI.auditIndividualVisionPlan(individualVisionPlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(individualVisionPlanBO, IndividualVisionPlanVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}