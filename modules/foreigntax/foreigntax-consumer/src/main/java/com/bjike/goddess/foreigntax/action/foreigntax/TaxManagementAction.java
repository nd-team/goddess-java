package com.bjike.goddess.foreigntax.action.foreigntax;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.TaxManagementAPI;
import com.bjike.goddess.foreigntax.bo.TaxManagementBO;
import com.bjike.goddess.foreigntax.dto.TaxManagementDTO;
import com.bjike.goddess.foreigntax.to.CollectTo;
import com.bjike.goddess.foreigntax.to.TaxManagementTO;
import com.bjike.goddess.foreigntax.vo.TaxManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 税金管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:40 ]
 * @Description: [ 税金管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("taxmanagement")
public class TaxManagementAction {
    @Autowired
    private TaxManagementAPI taxManagementAPI;

    /**
     * 税金管理列表总条数
     *
     * @param taxManagementDTO 税金管理dto
     * @des 获取所有税金管理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TaxManagementDTO taxManagementDTO) throws ActException {
        try {
            Long count = taxManagementAPI.countTaxManagement(taxManagementDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 税金管理列表
     *
     * @param taxManagementDTO 税金管理dto
     * @return class TaxManagementVO
     * @des 获取所有税金管理
     * @version v1
     */
    @GetMapping("v1/listTaxManagement")
    public Result findListTaxManagement(TaxManagementDTO taxManagementDTO, BindingResult bindingResult) throws ActException {
        try {
            List<TaxManagementVO> taxManagementVOS = BeanTransform.copyProperties
                    (taxManagementAPI.findListTaxManagement(taxManagementDTO), TaxManagementVO.class);
            return ActResult.initialize(taxManagementVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加税金管理
     *
     * @param taxManagementTO 税金管理数据to
     * @return class TaxManagementVO
     * @des 添加税金管理
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addTaxManagement(TaxManagementTO taxManagementTO, BindingResult bindingResult) throws ActException {
        try {
            TaxManagementBO taxManagementBO = taxManagementAPI.insertTaxManagement(taxManagementTO);
            return ActResult.initialize(taxManagementBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑税金管理
     *
     * @param taxManagementTO 税金管理数据to
     * @return class TaxManagementVO
     * @des 编辑税金管理
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editTaxManagement(TaxManagementTO taxManagementTO) throws ActException {
        try {
            TaxManagementBO taxManagementBO = taxManagementAPI.editTaxManagement(taxManagementTO);
            return ActResult.initialize(taxManagementBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除税金管理
     *
     * @param id 用户id
     * @des 根据用户id删除税金管理记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeTaxManagement(@PathVariable String id) throws ActException {
        try {
            taxManagementAPI.removeTaxManagement(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            taxManagementAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 查看功能
     *
     * @return class TaxManagementVO
     * @des 根据公司(company)、税种(taxType)、月份(month) 查看
     * @version v1
     */
    @GetMapping("v1/view")
    public Result viewTaxManagement(String company, String taxType, String month) throws ActException {
        try {
            TaxManagementBO taxManagementBO = taxManagementAPI.viewTaxManagement(company, taxType, month);
            TaxManagementVO taxManagementVO = BeanTransform.copyProperties(taxManagementBO, TaxManagementBO.class);
            return ActResult.initialize(taxManagementVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @return class TaxManagementVO
     * @des 汇总税金管理
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collectTaxManagement(CollectTo to) throws ActException {
        try {
            List<TaxManagementVO> taxManagementVOS = BeanTransform.copyProperties(
                    taxManagementAPI.collectTaxManagement(to), TaxManagementVO.class, true);
            return ActResult.initialize(taxManagementVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}