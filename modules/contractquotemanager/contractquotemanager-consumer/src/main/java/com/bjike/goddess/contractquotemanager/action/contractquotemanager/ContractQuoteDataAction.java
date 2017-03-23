package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractQuoteDataAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import com.bjike.goddess.contractquotemanager.vo.ContractNodeStandardVO;
import com.bjike.goddess.contractquotemanager.vo.ContractQuoteDataVO;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 合同单价资料信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:01:53.318 ]
 * @Description: [ 合同单价资料信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractquotemanager/contractquotedata")
public class ContractQuoteDataAction {
    @Autowired
    private ContractQuoteDataAPI contractQuoteDataAPI;

    /**
     * 添加合同单价资料信息
     *
     * @param contractQuoteDataTO 合同单价资料信息
     * @throws ActException
     * @Des 返回合同单价资料信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result save(@Validated({ADD.class}) ContractQuoteDataTO contractQuoteDataTO) throws ActException {
        try {
            return ActResult.initialize(contractQuoteDataAPI.save(contractQuoteDataTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取合同单价资料信息
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ContractQuoteDataDTO contractQuoteDataDTO) throws ActException {
        try {
            List<ContractQuoteDataVO> contractQuoteDataVOs = BeanTransform.copyProperties(
                    contractQuoteDataAPI.list(contractQuoteDataDTO), ContractQuoteDataVO.class, true);
            return ActResult.initialize(contractQuoteDataVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑合同单价资料信息
     *
     * @param contractQuoteDataTO 合同单价资料信息
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result update(@Validated({EDIT.class}) ContractQuoteDataTO contractQuoteDataTO) throws ActException {
        try {
            contractQuoteDataAPI.update(contractQuoteDataTO);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除合同单价资料信息
     *
     * @param id 合同单价资料信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractQuoteDataAPI.remove(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 过期
     *
     * @param id id
     * @des 根据id标识为过期状态
     * @version v1
     */
    @PostMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            contractQuoteDataAPI.congealStatus(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @des 根据id标识为正常状态
     * @version v1
     */
    @PostMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            contractQuoteDataAPI.thawStatus(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合同单价资料信息
     *
     * @param bo 合同单价资料信息
     * @throws ActException
     * @des class ContractQuoteDataVO 根据地区(area)、用户名称(customerName)和适用年度汇总
     * @version v1
     */
    @GetMapping("v1/collects")
    public Result collect(ContractQuoteDataBO bo) throws ActException {
        try {

            List<ContractQuoteDataVO> contractQuoteDataVOs = BeanTransform.copyProperties(
                    contractQuoteDataAPI.collect(bo),
                    ContractQuoteDataVO.class, true);
            return ActResult.initialize(contractQuoteDataVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索合同单价资料信息
     *
     * @param bo 合同单价资料信息
     * @throws ActException
     * @des class ContractQuoteDataVO 根据地区(area)、项目(project)搜索
     * @version v1
     */
    @GetMapping("v1/searchs")
    public Result searchs(ContractQuoteDataBO bo) throws ActException {
        try {

            List<ContractQuoteDataVO> contractQuoteDataVOs = BeanTransform.copyProperties(
                    contractQuoteDataAPI.searchs(bo),
                    ContractQuoteDataVO.class, true);
            return ActResult.initialize(contractQuoteDataVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }


    }

    //TODO: yewenbo 2017-03-21 上传附件
    //TODO: yewenbo 2017-03-21 查看附件
    //TODO: yewenbo 2017-03-21 下载附件


}