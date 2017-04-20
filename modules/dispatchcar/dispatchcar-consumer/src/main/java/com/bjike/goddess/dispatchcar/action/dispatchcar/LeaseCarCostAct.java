package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.LeaseCarCostAPI;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.to.LeaseCarCostTO;
import com.bjike.goddess.dispatchcar.vo.LeaseCarCostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租车费用基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("leasecarcost")
public class LeaseCarCostAct {
    
    @Autowired
    private LeaseCarCostAPI leaseCarCostAPI;

    /**
     * 新增租车费用基本信息
     *
     * @param to 租车费用基本信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) LeaseCarCostTO to, BindingResult bindingResult) throws ActException {
        try {
            LeaseCarCostVO vo = BeanTransform.copyProperties(leaseCarCostAPI.addModel(to), LeaseCarCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租车费用基本信息
     *
     * @param to 租车费用基本信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) LeaseCarCostTO to, BindingResult bindingResult) throws ActException {
        try {
            LeaseCarCostVO vo = BeanTransform.copyProperties(leaseCarCostAPI.editModel(to), LeaseCarCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除租车费用基本信息
     *
     * @param id 租车费用基本信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            leaseCarCostAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 租车费用基本信息分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(LeaseCarCostDTO dto) throws ActException {
        try {
            List<LeaseCarCostVO> voList = BeanTransform.copyProperties(leaseCarCostAPI.pageList(dto), LeaseCarCostVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}