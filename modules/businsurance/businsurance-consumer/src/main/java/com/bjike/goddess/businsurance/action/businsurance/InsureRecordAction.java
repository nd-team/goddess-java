package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.InsureRecordAPI;
import com.bjike.goddess.businsurance.bo.InsureRecordBO;
import com.bjike.goddess.businsurance.dto.InsureRecordDTO;
import com.bjike.goddess.businsurance.to.InsureRecordTO;
import com.bjike.goddess.businsurance.vo.InsureRecordVO;
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
 * 意外险记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("insurerecord")
public class InsureRecordAction {

    @Autowired
    private InsureRecordAPI insureRecordAPI;

    /**
     *  总条数
     *
     * @param insureRecordDTO  意外险记录信息dto
     * @des 获取所有意外险记录信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InsureRecordDTO insureRecordDTO) throws ActException {
        try {
            Long count = insureRecordAPI.countInsureRecord(insureRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 意外险记录列表
     *
     * @param insureRecordDTO 意外险记录信息dto
     * @des 获取所有意外险记录信息
     * @return  class InsureRecordVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(InsureRecordDTO insureRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<InsureRecordVO> insureRecordVOList = BeanTransform.copyProperties(
                    insureRecordAPI.listInsureRecord(insureRecordDTO), InsureRecordVO.class, true);
            return ActResult.initialize(insureRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param insureRecordTO 意外险记录基本信息数据to
     * @des 添加意外险记录
     * @return  class InsureRecordVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated InsureRecordTO insureRecordTO, BindingResult bindingResult) throws ActException {
        try {
            InsureRecordBO insureRecordBO1 = insureRecordAPI.addInsureRecord(insureRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(insureRecordBO1,InsureRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param insureRecordTO 意外险记录基本信息数据bo
     * @des 编辑意外险记录
     * @return  class InsureRecordVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated InsureRecordTO insureRecordTO) throws ActException {
        try {
            InsureRecordBO insureRecordBO1 = insureRecordAPI.editInsureRecord(insureRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(insureRecordBO1,InsureRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除意外险记录信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            insureRecordAPI.deleteInsureRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }



    /**
     * 一个方案查看详细
     *
     * @param id id
     * @des 根据id查看详细
     * @return  class InsureRecordVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id ) throws ActException {
        try {
            InsureRecordBO insureRecordBO1 = insureRecordAPI.getInsureRecord(id);
            return ActResult.initialize(BeanTransform.copyProperties(insureRecordBO1,InsureRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导出
     *
     * @param insureRecordDTO insureRecordDTO
     * @des 导出
     * @return  class InsureRecordVO
     * @version v1
     */
    @GetMapping("v1/export")
    public Result export(InsureRecordDTO insureRecordDTO ) throws ActException {
//        try {
//            InsureRecordBO insureRecordBO1 = insureRecordAPI.getInsureRecord(id);
//            return ActResult.initialize(BeanTransform.copyProperties(insureRecordBO1,InsureRecordVO.class,true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return ActResult.initialize(null);
    }

}