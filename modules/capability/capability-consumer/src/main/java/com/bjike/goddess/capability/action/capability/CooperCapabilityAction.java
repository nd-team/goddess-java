package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.CooperCapabilityAPI;
import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.capability.dto.CooperCapabilityDTO;
import com.bjike.goddess.capability.to.CooperCapabilityTO;
import com.bjike.goddess.capability.vo.CooperCapabilityVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("coopercapability")
public class CooperCapabilityAction {
    @Autowired
    private CooperCapabilityAPI cooperCapabilityAPI;

    /**
     * 合作对象能力总条数
     *
     * @param cooperCapabilityDTO
     * @des 获取所有合作对象能力总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CooperCapabilityDTO cooperCapabilityDTO) throws ActException {
        try {
            Long count = cooperCapabilityAPI.counts(cooperCapabilityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个合作对象能力
     *
     * @param id
     * @param request 前端过滤参数
     * @return class CooperCapabilityVO
     * @des 获取一个合作对象能力
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id,HttpServletRequest request) throws ActException {
        try {
            CooperCapabilityBO cooperCapabilityBO = cooperCapabilityAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(cooperCapabilityBO, CooperCapabilityVO.class ,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合作对象能力列表
     *
     * @param cooperCapabilityDTO 合作对象能力信息dto
     * @param request 前端过滤参数
     * @return class CooperCapabilityVO
     * @des 获取所有合作对象能力信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findLists(CooperCapabilityDTO cooperCapabilityDTO,HttpServletRequest request) throws ActException {
        try {
            List<CooperCapabilityVO> cooperCapabilityVOList = BeanTransform.copyProperties(
                    cooperCapabilityAPI.listCooperCapability(cooperCapabilityDTO), CooperCapabilityVO.class, request);
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
    @LoginAuth
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
    @LoginAuth
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
     * 编辑联系人
     *
     * @param cooperCapabilityTO 合作对象能力基本信息数据bo
     * @return class CooperCapabilityVO
     * @des 编辑联系人
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editRelation")
    public Result editCompanyConnector(@Validated CooperCapabilityTO cooperCapabilityTO) throws ActException {
        try {
            CooperCapabilityBO cooperCapabilityBO1 = cooperCapabilityAPI.editCompanyConnector(cooperCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(cooperCapabilityBO1, CooperCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看联系人
     *
     * @param id 合作对象能力基本信息数据id
     * @return class CooperCapabilityVO
     * @des 根据id编查看联系人
     * @version v1
     */
    @GetMapping("v1/getRelation/{id}")
    public Result getCompanyConnector(@PathVariable String id) throws ActException {
        try {
            CooperCapabilityBO cooperCapabilityBO1 = cooperCapabilityAPI.getCompanyConnector(id);
            return ActResult.initialize(BeanTransform.copyProperties(cooperCapabilityBO1, CooperCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有公司名
     *
     * @return class CooperCapabilityVO
     * @des 查找所有公司名
     * @version v1
     */
    @GetMapping("v1/listCompany")
    public Result listAllCompanyName() throws ActException {
        try {
            List<String> list = cooperCapabilityAPI.listAllCompanyName();
            return ActResult.initialize(list);
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
    @LoginAuth
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
     * @return class CooperCapabilityVO
     * @des 导入公司能力, 公司名称不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcelCCapability(@Validated CooperCapabilityTO cooperCapabilityTO) throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param companyName 公司名称
     * @return class CooperCapabilityVO
     * @des 导出公司能力, 公司名称可以为空
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcelCCapability(String companyName) throws ActException {
        return null;
    }


}