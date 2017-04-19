package com.bjike.goddess.checkhost.action.checkhost;

import com.bjike.goddess.checkhost.api.StayApplyAPI;
import com.bjike.goddess.checkhost.bo.StayApplyBO;
import com.bjike.goddess.checkhost.dto.StayApplyDTO;
import com.bjike.goddess.checkhost.to.StayApplyTO;
import com.bjike.goddess.checkhost.vo.StayApplyVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 住宿申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("stayapply")
public class StayApplyAction {
    @Autowired
    private StayApplyAPI stayApplyAPI;
    /**
     * 获取住宿申请
     *
     * @param stayApplyDTO 住宿申请dto
     * @return class StayApplyVO
     * @des 获取所有住宿申请
     * @version v1
     */
    @GetMapping("v1/listStayApply")
    public Result findListStayApply(StayApplyDTO stayApplyDTO) throws ActException {
        try {
            List<StayApplyVO> stayApplyVOS = BeanTransform.copyProperties
                    (stayApplyAPI.findListStayApply(stayApplyDTO),StayApplyVO.class);
            return ActResult.initialize(stayApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加住宿申请
     *
     * @param stayApplyTO 住宿申请数据to
     * @return class StayApplyVO
     * @des 添加住宿申请
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addStayApply(@Validated StayApplyTO stayApplyTO) throws ActException {
        try {
            StayApplyBO stayApplyBO = stayApplyAPI.insertStayApply(stayApplyTO);
            return ActResult.initialize(stayApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑住宿申请
     *
     * @param stayApplyTO 住宿申请数据to
     * @return class StayApplyVO
     * @des 编辑住宿申请
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editStayApply(@Validated StayApplyTO stayApplyTO) throws ActException {
        try {
            StayApplyBO stayApplyBO = stayApplyAPI.editStayApply(stayApplyTO);
            return ActResult.initialize(stayApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除住宿申请
     *
     * @param id 用户id
     * @des 根据用户id删除住宿申请记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeStayApply(@PathVariable String id) throws ActException {
        try {
            stayApplyAPI.removeStayApply(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 审核
     *
     * @param stayApplyTO 住宿申请数据bo
     * @return class StayApplyVO
     * @des 审核住宿申请
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result auditStayApply(@Validated StayApplyTO stayApplyTO) throws ActException {
        try {
            StayApplyBO stayApplyBO = stayApplyAPI.auditStayApply(stayApplyTO);
            return ActResult.initialize(BeanTransform.copyProperties(stayApplyBO, StayApplyVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}