package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalPreceptAPI;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.accommodation.vo.RentalPreceptVO;
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
 * 租房方案业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [租房方案业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
@RestController
@RequestMapping("rentalPrecept")
public class RentalPreceptAct {
    @Autowired
    private RentalPreceptAPI rentalPreceptAPI;

    /**
     * 获取租房方案信息
     * @param rentalPreceptDTO 租房方案dto
     * @version v1
     */
    @GetMapping("v1/listRentalPrecept")
    public Result findListRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws ActException {
        try {
            List<RentalPreceptVO> rentalPreceptVOList = BeanTransform.copyProperties(
                    rentalPreceptAPI.listRentalPrecept(rentalPreceptDTO),RentalPreceptVO.class,true);
            return ActResult.initialize( rentalPreceptVOList );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 添加租房方案
     * @param rentalPreceptTO 租房方案to
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addPecept (@Validated({ADD.class}) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO preceptBO = rentalPreceptAPI.insertPecept(rentalPreceptTO);
            return ActResult.initialize( preceptBO);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }
    /**
     * 编辑租房方案
     * @param rentalPreceptTO 租房方案数据bo
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editPecept (@Validated RentalPreceptTO rentalPreceptTO ) throws ActException {
        try {
            RentalPreceptBO rentalPreceptBO = rentalPreceptAPI.editPecept(rentalPreceptTO);
            return ActResult.initialize( rentalPreceptBO);
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


    /**
     * 根据用户id删除租房方案记录
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deletePecept(@PathVariable String id) throws ActException {
        try {
            rentalPreceptAPI.removePecept(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }
    /**
     * 审核
     * @param rentalPreceptTO yh
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit (@Validated RentalPreceptTO rentalPreceptTO ) throws ActException {
        try {
            rentalPreceptAPI.audit(rentalPreceptTO);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }


}
