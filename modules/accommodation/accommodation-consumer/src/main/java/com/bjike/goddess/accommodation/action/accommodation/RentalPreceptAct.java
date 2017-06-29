package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalPreceptAPI;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.accommodation.vo.RentalPreceptVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 租房方案列表总条数
     *
     * @param rentalPreceptDTO 租房方案记录dto
     * @des 获取所有租房方案
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RentalPreceptDTO rentalPreceptDTO) throws ActException {
        try {
            Long count = rentalPreceptAPI.countRentalPrecept(rentalPreceptDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个租房方案
     *
     * @param id
     * @return class RentalPreceptVO
     * @des 获取一个租房方案
     * @version v1
     */
    @GetMapping("v1/precept/{id}")
    public Result precept(@PathVariable String id) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取租房方案信息
     * @param rentalPreceptDTO 租房方案dto
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RentalPreceptDTO rentalPreceptDTO, HttpServletRequest request) throws ActException {
        try {
            List<RentalPreceptVO> rentalPreceptVOList = BeanTransform.copyProperties(
                    rentalPreceptAPI.findListRentalPrecept(rentalPreceptDTO),RentalPreceptVO.class,request);
            return ActResult.initialize( rentalPreceptVOList );
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }

    /**
     * 添加租房方案
     * @param rentalPreceptTO 租房方案to
     * @return class RentalPreceptVO
     * @des 添加租房方案
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add (@Validated({ADD.class}) RentalPreceptTO rentalPreceptTO,BindingResult bindingResult) throws ActException {
        try {
            RentalPreceptBO preceptBO = rentalPreceptAPI.insertPecept(rentalPreceptTO);
            return ActResult.initialize( BeanTransform.copyProperties(preceptBO,RentalPreceptVO.class));
        } catch (SerException e) {
            throw  new ActException( e.getMessage() );
        }
    }
    /**
     * 编辑租房方案
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 编辑租房方案
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit (@Validated(EDIT.class) RentalPreceptTO rentalPreceptTO, BindingResult bindingResult) throws ActException {
        try {
            RentalPreceptBO rentalPreceptBO = rentalPreceptAPI.editPecept(rentalPreceptTO);
            return ActResult.initialize( BeanTransform.copyProperties(rentalPreceptBO,RentalPreceptVO.class));
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
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rentalPreceptAPI.removePecept(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw  new ActException( e.getMessage());
        }
    }
    /**
     * 项目经理审核
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 项目经理审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/manageAudit")
    public Result manageAudit(@Validated(RentalPreceptTO.TestManager.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.manageAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 总经办审核
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/generalAudit")
    public Result generalAudit(@Validated(RentalPreceptTO.TestGeneral.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.generalAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
