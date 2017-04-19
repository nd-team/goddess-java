package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.CompanyCapabilityAPI;
import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.capability.vo.CompanyCapabilityVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 公司能力展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("companycapability")
public class CompanyCapabilityAction {

    @Autowired
    private CompanyCapabilityAPI companyCapabilityAPI;

    /**
     * 公司能力总条数
     *
     * @param companyCapabilityDTO
     * @des 获取所有公司能力总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CompanyCapabilityDTO companyCapabilityDTO) throws ActException {
        try {
            Long count = companyCapabilityAPI.counts(companyCapabilityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个公司能力
     *
     * @param id
     * @des 获取一个公司能力
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            CompanyCapabilityBO companyCapabilityBO = companyCapabilityAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(companyCapabilityBO,CompanyCapabilityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司能力列表
     *
     * @param companyCapabilityDTO 公司能力信息dto
     * @des 获取所有公司能力信息
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/listCompanyCapability")
    public Result findListCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO) throws ActException {
        try {
            List<CompanyCapabilityVO> companyCapabilityVOList = BeanTransform.copyProperties(
                    companyCapabilityAPI.listCompanyCapability(companyCapabilityDTO), CompanyCapabilityVO.class, true);
            return ActResult.initialize(companyCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司能力
     *
     * @param companyCapabilityTO 公司能力基本信息数据to
     * @des 添加公司能力,公司名称不能为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCompanyCapability(@Validated CompanyCapabilityTO companyCapabilityTO) throws ActException {
        try {
            CompanyCapabilityBO companyCapabilityBO1 = companyCapabilityAPI.addCompanyCapability(companyCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyCapabilityBO1,CompanyCapabilityVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公司能力
     *
     * @param companyCapabilityTO 公司能力基本信息数据bo
     * @des 添加公司能力,公司名称不能为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCompanyCapability(@Validated  CompanyCapabilityTO companyCapabilityTO) throws ActException {
        try {
            CompanyCapabilityBO companyCapabilityBO1 = companyCapabilityAPI.editCompanyCapability(companyCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(companyCapabilityBO1,CompanyCapabilityVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司能力信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCompanyCapability(@PathVariable String id) throws ActException {
        try {
            companyCapabilityAPI.deleteCompanyCapability(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索模糊搜索
     *
     * @param companyCapabilityDTO 公司能力信息dto里面的公司名称
     * @des 获取搜索到的所有公司能力信息
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/listCapabilityByCompanyName")
    public Result listCompanyCbilityByCompanyName(CompanyCapabilityDTO companyCapabilityDTO) throws ActException {
        try {
            List<CompanyCapabilityVO> companyCapabilityVOList = BeanTransform.copyProperties(
                    companyCapabilityAPI.listCompanyCapabilityByName(companyCapabilityDTO), CompanyCapabilityVO.class, true);
            return ActResult.initialize(companyCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @param companyCapabilityTO 公司能力基本信息数据to
     * @des 导入公司能力,公司名称不能为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcelCCapability(@Validated CompanyCapabilityTO companyCapabilityTO) throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param companyName 公司名称
     * @des 导出公司能力,公司名称可以为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcelCCapability(String companyName ) throws ActException {
        return null;
    }


    /**
     * 查看公司组织结构规划
     *
     * @param companyCapabilityTO 公司能力to
     * @des 导出公司能力,公司名称可以为空
     * @return  class CompanyCapabilityVO
     * @version v1
     */
    @GetMapping("v1/lookLayout")
    public Result lookLayout(CompanyCapabilityTO companyCapabilityTO ) throws ActException {
        return null;
    }

}