package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualApplyAPI;
import com.bjike.goddess.annual.dto.AnnualApplyDTO;
import com.bjike.goddess.annual.to.AnnualApplyAuditTo;
import com.bjike.goddess.annual.to.AnnualApplyTO;
import com.bjike.goddess.annual.vo.AnnualApplyVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 年假申请
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annualapply")
public class AnnualApplyAction {

    @Autowired
    private AnnualApplyAPI annualApplyAPI;

    /**
     * 保存年假申请实体数据
     *
     * @param to 年假申请传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AnnualApplyTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.save(to), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除年假申请实体数据
     *
     * @param to 年假申请传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(AnnualApplyTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.delete(to), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核年假申请
     *
     * @param to 年假申请审核传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @PutMapping("v1/audit/{id}")
    public Result audit(@Validated(EDIT.class) AnnualApplyAuditTo to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.audit(to), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户名查询年假申请记录
     *
     * @param username 用户名
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/findByUsername")
    public Result findByUsername(String username) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.findByUsername(username), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年假信息查询年假申请记录
     *
     * @param info_id 年假信息ID
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/findByInfo/{info_id}")
    public Result findByInfo(@PathVariable String info_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.findByInfo(info_id), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表
     *
     * @param dto 年假申请数据传输对象
     * @return class AnnualApplyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualApplyDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualApplyAPI.maps(dto), AnnualApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}