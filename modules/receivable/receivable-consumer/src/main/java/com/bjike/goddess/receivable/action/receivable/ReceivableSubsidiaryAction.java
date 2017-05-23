package com.bjike.goddess.receivable.action.receivable;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.api.ReceivableSubsidiaryAPI;
import com.bjike.goddess.receivable.bo.CollectContractorBO;
import com.bjike.goddess.receivable.bo.CollectProjectNameBO;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.bo.ReceivableSubsidiaryBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.to.ContractorTO;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import com.bjike.goddess.receivable.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("receivablesubsidiary")
public class ReceivableSubsidiaryAction {
    @Autowired
    private ReceivableSubsidiaryAPI receivableSubsidiaryAPI;
    /**
     * 回款明细列表总条数
     *
     * @param receivableSubsidiaryDTO 回款明细dto
     * @des 获取所有回款明细总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws ActException {
        try {
            Long count = receivableSubsidiaryAPI.countReceivableSubsidiary(receivableSubsidiaryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个回款明细
     *
     * @param id
     * @return class ReceivableSubsidiaryVO
     * @des 获取一个回款明细
     * @version v1
     */
    @GetMapping("v1/receivable/{id}")
    public Result receivable(@PathVariable String id) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(receivableSubsidiaryBO, ReceivableSubsidiaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取回款明细
     *
     * @param receivableSubsidiaryDTO 回款明细dto
     * @return class ReceivableSubsidiaryVO
     * @des 获取所有回款明细
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ReceivableSubsidiaryDTO receivableSubsidiaryDTO, HttpServletRequest request) throws ActException {
        try {
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties
                    (receivableSubsidiaryAPI.findListReceivableSubsidiary(receivableSubsidiaryDTO),ReceivableSubsidiaryVO.class,request);
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
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ReceivableSubsidiaryTO receivableSubsidiaryTO, BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ReceivableSubsidiaryTO receivableSubsidiaryTO,BindingResult bindingResult) throws ActException {
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
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
    @GetMapping("v1/editTime")
    public Result editTime(ReceivableSubsidiary receivableSubsidiary, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws ActException {
        try {
            receivableSubsidiaryAPI.editTime(receivableSubsidiary, auditStatusStr, countStatusStr, billStatusStr, planStatusStr);
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
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            List<String> areaList = receivableSubsidiaryAPI.getArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取项目名称
     *
     * @des 获取项目名称集合
     * @version v1
     */
    @GetMapping("v1/name")
    public Result name() throws ActException {
        try {
            List<String> innerNameList = receivableSubsidiaryAPI.getInnerName();
            return ActResult.initialize(innerNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取总包单位
     *
     * @des 获取总包单位集合
     * @version v1
     */
    @GetMapping("v1/contractor")
    public Result contractor() throws ActException {
        try {
            List<String> contractorList = receivableSubsidiaryAPI.getContractor();
            return ActResult.initialize(contractorList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总地区
     *
     * @param areas 地区
     * @des 汇总地区
     * @return  class CollectAreaVO
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea ( @RequestParam String[] areas ) throws ActException {
        try {
            List<CollectAreaVO> collectAreaVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectArea(areas),CollectAreaVO.class);
            return ActResult.initialize(collectAreaVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总项目名称
     *
     * @param innerNames 项目名称
     * @des 汇总项目名称
     * @return  class CollectProjectNameVO
     * @version v1
     */
    @GetMapping("v1/collectName")
    public Result collectName ( @RequestParam String[] innerNames ) throws ActException {
        try {
            List<CollectProjectNameVO> collectProjectNameVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectInnerName(innerNames),CollectProjectNameVO.class);
            return ActResult.initialize(collectProjectNameVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总总包单位
     *
     * @param contractors 总包单位
     * @des 汇总总包单位
     * @return  class CollectContractorVO
     * @version v1
     */
    @GetMapping("v1/collectContractor")
    public Result collectContractor ( @RequestParam String[] contractors ) throws ActException {
        try {
            List<CollectContractorVO> collectContractorVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectContractor(contractors),CollectContractorVO.class);
            return ActResult.initialize(collectContractorVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总地区详情
     *
     * @param areas 地区
     * @des 汇总地区详情
     * @return  class CollectAreaDetailVO
     * @version v1
     */
    @GetMapping("v1/collectAreaDetail")
    public Result collectAreaDetail ( @RequestParam String[] areas ) throws ActException {
        try {
            List<CollectAreaDetailVO> collectAreaDetailVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectAreaDetail(areas),CollectAreaDetailVO.class);
            return ActResult.initialize(collectAreaDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总项目名称详情
     *
     * @param innerNames 项目名称
     * @des 汇总项目名称详情
     * @return  class CollectProjectNameDetailVO
     * @version v1
     */
    @GetMapping("v1/collectNameDetail")
    public Result collectNameDetail ( @RequestParam String[] innerNames ) throws ActException {
        try {
            List<CollectProjectNameDetailVO> collectProjectNameDetailVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectInnerNameDetail(innerNames),CollectProjectNameDetailVO.class);
            return ActResult.initialize(collectProjectNameDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总总包单位详情
     *
     * @param contractors 总包单位
     * @des 汇总总包单位详情
     * @return  class CollectContractorDetailVO
     * @version v1
     */
    @GetMapping("v1/collectContractorDetail")
    public Result collectContractorDetail ( @RequestParam String[] contractors ) throws ActException {
        try {
            List<CollectContractorDetailVO> collectContractorDetailVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectContractorDetail(contractors),CollectContractorDetailVO.class);
            return ActResult.initialize(collectContractorDetailVOS);
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