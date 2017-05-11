package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.BusInsuranceAPI;
import com.bjike.goddess.businsurance.bo.BusInsuranceBO;
import com.bjike.goddess.businsurance.dto.BusInsuranceDTO;
import com.bjike.goddess.businsurance.to.BusInsuranceTO;
import com.bjike.goddess.businsurance.vo.BusInsuranceVO;
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

import javax.validation.Valid;
import java.util.List;

/**
 * 商业保险方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businsurance")
public class BusInsuranceAction {

    @Autowired
    private BusInsuranceAPI busInsuranceAPI;

    /**
     *  商业保险方案列表总条数
     *
     * @param busInsuranceDTO  商业保险方案信息dto
     * @des 获取所有商业保险方案信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusInsuranceDTO busInsuranceDTO) throws ActException {
        try {
            Long count = busInsuranceAPI.countBusInsurance(busInsuranceDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业保险方案列表
     *
     * @param busInsuranceDTO 商业保险方案信息dto
     * @des 获取所有商业保险方案信息
     * @return  class BusInsuranceVO
     * @version v1
     */
    @GetMapping("v1/listBusInsurance")
    public Result findList(BusInsuranceDTO busInsuranceDTO, BindingResult bindingResult) throws ActException {
        try {
            List<BusInsuranceVO> busInsuranceVOList = BeanTransform.copyProperties(
                    busInsuranceAPI.listBusInsurance(busInsuranceDTO), BusInsuranceVO.class, true);
            return ActResult.initialize(busInsuranceVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加商业保险方案
     *
     * @param busInsuranceTO 商业保险方案基本信息数据to
     * @des 添加商业保险方案
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated BusInsuranceTO busInsuranceTO, BindingResult bindingResult) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.addBusInsurance(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑商业保险方案
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑商业保险方案
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editBusInsurance(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除商业保险方案信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            busInsuranceAPI.deleteBusInsurance(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 编辑购买条件
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑购买条件
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editCondition")
    public Result editCondition(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editBuyCondition(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商业保险方案审核
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 编辑商业保险方案审核
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAdvice")
    public Result editAdvice(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.editAdvice(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 总经办审核
     *
     * @param busInsuranceTO 商业保险方案基本信息数据bo
     * @des 总经办审核
     * @return  class BusInsuranceVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/audit")
    public Result audit(@Validated BusInsuranceTO busInsuranceTO) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.audit(busInsuranceTO);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个方案查看详细
     *
     * @param id id
     * @des 根据id查看详细
     * @return  class BusInsuranceVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id ) throws ActException {
        try {
            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.getBusInsurance(id);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导出
     *
     * @param busInsuranceDTO busInsuranceDTO
     * @des 导出
     * @return  class BusInsuranceVO
     * @version v1
     */
    @GetMapping("v1/export")
    public Result export(BusInsuranceDTO busInsuranceDTO ) throws ActException {
//        try {
//            BusInsuranceBO busInsuranceBO1 = busInsuranceAPI.getBusInsurance(id);
//            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,BusInsuranceVO.class,true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return ActResult.initialize(null);
    }



    
}