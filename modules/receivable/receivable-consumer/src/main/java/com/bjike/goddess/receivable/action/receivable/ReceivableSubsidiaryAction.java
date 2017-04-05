package com.bjike.goddess.receivable.action.receivable;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.api.ReceivableSubsidiaryAPI;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.bo.ReceivableSubsidiaryBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.to.ContractorTO;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import com.bjike.goddess.receivable.vo.ContractorVO;
import com.bjike.goddess.receivable.vo.ReceivableSubsidiaryVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 回款明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("receivable/receivablesubsidiary")
public class ReceivableSubsidiaryAction {
    @Autowired
    private ReceivableSubsidiaryAPI receivableSubsidiaryAPI;
    /**
     * 获取回款明细
     *
     * @param receivableSubsidiaryDTO 回款明细dto
     * @return class ReceivableSubsidiaryVO
     * @des 获取所有回款明细
     * @version v1
     */
    @GetMapping("v1/listReceivableSubsidiary")
    public Result findListReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties
                    (receivableSubsidiaryAPI.findListReceivableSubsidiary(receivableSubsidiaryDTO),ReceivableSubsidiaryVO.class);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据录入回款明细
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @return class ReceivableSubsidiaryVO
     * @des 添加回款明细
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.insertReceivableSubsidiary(receivableSubsidiaryTO);
            return ActResult.initialize(receivableSubsidiaryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑回款明细
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @return class ReceivableSubsidiaryVO
     * @des 编辑回款明细
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.editReceivableSubsidiary(receivableSubsidiaryTO);
            return ActResult.initialize(receivableSubsidiaryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除回款明细
     *
     * @param id 用户id
     * @des 根据用户id删除回款明细记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeReceivableSubsidiary(String id) throws ActException {
        try {
            receivableSubsidiaryAPI.removeReceivableSubsidiary(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 时间
     *
     * @version v1
     */
    @PostMapping("v1/editDate")
    public Result editDate(ReceivableSubsidiary receivableSubsidiary, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws ActException {
        try {
            receivableSubsidiaryAPI.editDate(receivableSubsidiary, auditStatusStr, countStatusStr, billStatusStr, planStatusStr);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出回款明细
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String area,String start,String end) throws ActException {
        String excel = null;
        try {
            excel = receivableSubsidiaryAPI.exportExcel(area, start, end);
            return new ActResult(excel);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 导入
     *
     * @version v1
     */
    @PostMapping("v1/input")
    public Result input() throws ActException {
        try {
            receivableSubsidiaryAPI.input();
            return new ActResult("input success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 汇总地区
     *
     * @param area 地区
     * @des 根据地区汇总
     * @return  class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea ( @NotBlank String[] area ) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectArea(area),ReceivableSubsidiaryVO.class,true);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总地区详情
     *
     * @param area 地区
     * @des 根据地区汇总
     * @return  class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectAreaDetail")
    public Result collectAreaDetail ( @NotBlank String[] area ) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectAreaDetail(area),ReceivableSubsidiaryVO.class,true);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总项目名称
     *
     * @param innerName 项目名称
     * @des 根据项目名称汇总
     * @return  class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectInnerName")
    public Result collectInnerName ( @NotBlank String[] innerName ) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectInnerName(innerName),ReceivableSubsidiaryVO.class,true);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总项目名称详情
     *
     * @param innerName 项目名称
     * @des 根据项目名称汇总
     * @return  class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectInnerNameDetail")
    public Result collectInnerNameDetail ( @NotBlank String[] innerName ) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectInnerNameDetail(innerName),ReceivableSubsidiaryVO.class,true);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总总包单位
     *
     * @param contractor 总包单位
     * @des 根据总包单位汇总
     * @return  class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectContractor")
    public Result collectContractor ( @NotBlank String[] contractor ) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectContractor(contractor),ReceivableSubsidiaryVO.class,true);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总总包单位详情
     *
     * @param contractor 总包单位
     * @des 根据总包单位汇总
     * @return  class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectContractorDetail")
    public Result collectContractorDetail ( @NotBlank String[] contractor ) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectContractorDetail(contractor),ReceivableSubsidiaryVO.class,true);
            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 对比汇总
     *
     * @param receivableSubsidiaryTO 对比汇总数据to
     * @return class ReceivableSubsidiaryVO
     * @version v1
     */
    @GetMapping("v1/collectCompare")
    public Result collectCompare(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws ActException {
        try {
            List<ReceivableSubsidiaryBO> receivableSubsidiaryBO = receivableSubsidiaryAPI.collectCompare(receivableSubsidiaryTO);
            return ActResult.initialize(receivableSubsidiaryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 发送邮件
     *
     * @version v1
     */
    @PostMapping("v1/send")
    public Result sendReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.sendReceivableSubsidiary(receivableSubsidiaryTO);
            return ActResult.initialize(receivableSubsidiaryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }



}