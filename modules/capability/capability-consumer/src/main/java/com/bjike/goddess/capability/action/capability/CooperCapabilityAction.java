package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.CooperCapabilityAPI;
import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.capability.dto.CooperCapabilityDTO;
import com.bjike.goddess.capability.to.CooperCapabilityTO;
import com.bjike.goddess.capability.vo.CooperCapabilityVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 合作对象商务展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:31 ]
 * @Description: [ 合作对象商务展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("capability/coopercapability")
public class CooperCapabilityAction {
    @Autowired
    private CooperCapabilityAPI cooperCapabilityAPI;

    /**
     * 合作对象能力列表
     *
     * @param cooperCapabilityDTO 合作对象能力信息dto
     * @return class CooperCapabilityVO
     * @des 获取所有合作对象能力信息
     * @version v1
     */
    @GetMapping("v1/listCooperCapability")
    public Result findListCooperCapability(CooperCapabilityDTO cooperCapabilityDTO) throws ActException {
        try {
            List<CooperCapabilityVO> cooperCapabilityVOList = BeanTransform.copyProperties(
                    cooperCapabilityAPI.listCooperCapability(cooperCapabilityDTO), CooperCapabilityVO.class, true);
            return ActResult.initialize(cooperCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合作对象能力
     *
     * @param cooperCapabilityTO 合作对象能力基本信息数据to
     * @return class CooperCapabilityVO
     * @des 添加合作对象能力, 公司名称不能为空
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCooperCapability(@Validated CooperCapabilityTO cooperCapabilityTO) throws ActException {
        try {
            CooperCapabilityBO cooperCapabilityBO1 = cooperCapabilityAPI.addCooperCapability(cooperCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(cooperCapabilityBO1, CooperCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑合作对象能力
     *
     * @param cooperCapabilityTO 合作对象能力基本信息数据bo
     * @return class CooperCapabilityVO
     * @des 添加合作对象能力, 公司名称不能为空
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCooperCapability(@Validated CooperCapabilityTO cooperCapabilityTO) throws ActException {
        try {
            CooperCapabilityBO cooperCapabilityBO1 = cooperCapabilityAPI.editCooperCapability(cooperCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(cooperCapabilityBO1, CooperCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除合作对象能力信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCooperCapability(@PathVariable String id) throws ActException {
        try {
            cooperCapabilityAPI.deleteCooperCapability(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索模糊搜索
     *
     * @param cooperCapabilityDTO 合作对象能力信息dto里面的公司名称
     * @return class CooperCapabilityVO
     * @des 获取搜索到的所有合作对象能力信息
     * @version v1
     */
    @GetMapping("v1/listCapabilityByCompanyName")
    public Result listCompanyCbilityByCompanyName(CooperCapabilityDTO cooperCapabilityDTO) throws ActException {
        try {
            List<CooperCapabilityVO> cooperCapabilityVOList = BeanTransform.copyProperties(
                    cooperCapabilityAPI.listCooperCapabilityByName(cooperCapabilityDTO), CooperCapabilityVO.class, true);
            return ActResult.initialize(cooperCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @param cooperCapabilityTO 公司能力基本信息数据to
     * @des 导入公司能力,公司名称不能为空
     * @return  class CooperCapabilityVO
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcelCCapability(@Validated CooperCapabilityTO cooperCapabilityTO) throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param companyName 公司名称
     * @des 导出公司能力,公司名称可以为空
     * @return  class CooperCapabilityVO
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcelCCapability(String companyName ) throws ActException {
        return null;
    }


}