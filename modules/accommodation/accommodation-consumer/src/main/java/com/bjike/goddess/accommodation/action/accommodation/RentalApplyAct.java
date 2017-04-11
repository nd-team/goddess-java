
package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalApplyAPI;
import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.accommodation.vo.RentalApplyVO;
import com.bjike.goddess.common.api.entity.ADD;
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
 * 租房申请业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [租房申请业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */


@RestController
@RequestMapping("rentalApplyAct")
public class RentalApplyAct {
    @Autowired
    private RentalApplyAPI rentalApplyAPI;

    /**
     * 获取租房申请信息
     *
     * @param rentalApplyDTO 租房申请dto
     * @version v1
     */
    @GetMapping("v1/listRentalApply")
    public Result findListRentalApply(RentalApplyDTO rentalApplyDTO) throws ActException {
        try {
            List<RentalApplyVO> rentalApplyVOS = BeanTransform.copyProperties(
                    rentalApplyAPI.findListRentalApply(rentalApplyDTO), RentalApplyVO.class, true);
            return ActResult.initialize(rentalApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加租房申请
     *
     * @param rentalApplyTO 租房申请to
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addApply(@Validated({ADD.class}) RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO applyBO = rentalApplyAPI.insertApply(rentalApplyTO);
            return ActResult.initialize(applyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租房申请
     *
     * @param rentalApplyTO 租房申请数据bo
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editApply(@Validated RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO rentalApplyBO = rentalApplyAPI.editApply(rentalApplyTO);
            return ActResult.initialize(rentalApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id删除租房申请记录
     *
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteApply(@PathVariable String id) throws ActException {
        try {
            rentalApplyAPI.removeApply(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(@Validated RentalApplyTO applyTO) throws ActException {
        try {
            rentalApplyAPI.audit(applyTO);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 租房申请导出明细
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(@Validated String startTime, String endTime) throws ActException {
        try {
            String excel = null;
            excel = rentalApplyAPI.exportExcel(startTime, endTime);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 自动生成记账凭证
     *
     * @version v1
     */
    @PostMapping("v1/generateCredentials")
    public Result generateCredentials() throws ActException {
        try {
            String credentials = null;
            credentials = rentalApplyAPI.generateCredentials();
            return new ActResult(credentials);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 租房申请汇总到租房信息中
     *
     * @param rentalApplyTO rentalApplyTO
     * @version v1
     */
    @PostMapping("v1/summary")
    public Result summary(@Validated RentalApplyTO rentalApplyTO) throws ActException {
        try {
            RentalApplyBO rentalApplyBO = rentalApplyAPI.editApply(rentalApplyTO);
            return ActResult.initialize(rentalApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}

