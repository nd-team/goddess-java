package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.SelfCapabilityAPI;
import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.capability.vo.SelfCapabilityVO;
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
 * 个人能力展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("selfcapability")
public class SelfCapabilityAction {
    @Autowired
    private SelfCapabilityAPI selfCapabilityAPI;

    /**
     * 个人能力总条数
     *
     * @param selfCapabilityDTO
     * @des 获取所有个人能力总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SelfCapabilityDTO selfCapabilityDTO) throws ActException {
        try {
            Long count = selfCapabilityAPI.counts(selfCapabilityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个个人能力
     *
     * @param id
     * @des 获取一个个人能力
     * @return class SelfCapabilityVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO = selfCapabilityAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO ,SelfCapabilityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人能力列表
     *
     * @param selfCapabilityDTO 个人能力信息dto
     * @return class SelfCapabilityVO
     * @des 获取所有个人能力信息
     * @version v1
     */
    @GetMapping("v1/listSelf")
    public Result findList(SelfCapabilityDTO selfCapabilityDTO) throws ActException {
        try {
            List<SelfCapabilityVO> selfCapabilityVOList = BeanTransform.copyProperties(
                    selfCapabilityAPI.listSelfCapability(selfCapabilityDTO), SelfCapabilityVO.class, true);
            return ActResult.initialize(selfCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加个人能力
     *
     * @param selfCapabilityTO 个人能力基本信息数据to
     * @return class SelfCapabilityVO
     * @des 添加个人能力, 公司名称不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSelfCapability(@Validated(SelfCapabilityTO.TestAdd.class) SelfCapabilityTO selfCapabilityTO) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO1 = selfCapabilityAPI.addSelfCapability(selfCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO1, SelfCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 编辑个人能力
     *
     * @param selfCapabilityTO 个人能力基本信息数据bo
     * @return class SelfCapabilityVO
     * @des 添加个人能力, 公司名称不能为空
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSelfCapability(@Validated(SelfCapabilityTO.TestAdd.class)  SelfCapabilityTO selfCapabilityTO) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO1 = selfCapabilityAPI.editSelfCapability(selfCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO1, SelfCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑个人社交能力
     *
     * @param selfCapabilityTO 个人能力基本信息数据bo
     * @return class SelfCapabilityVO
     * @des 编辑个人社交能力
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSocial")
    public Result editSocial(   SelfCapabilityTO selfCapabilityTO) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO1 = selfCapabilityAPI.editSocial(selfCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO1, SelfCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除个人能力信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSelfCapability(@PathVariable String id) throws ActException {
        try {
            selfCapabilityAPI.deleteSelfCapability(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索模糊搜索
     *
     * @param selfCapabilityDTO 个人能力信息dto里面的公司名称
     * @return class SelfCapabilityVO
     * @des 获取搜索到的所有个人能力信息
     * @version v1
     */
    @GetMapping("v1/listCapabilityByCompanyName")
    public Result listCompanyCbilityByCompanyName(SelfCapabilityDTO selfCapabilityDTO) throws ActException {
        try {
            List<SelfCapabilityVO> selfCapabilityVOList = BeanTransform.copyProperties(
                    selfCapabilityAPI.listSelfCapabilityByName(selfCapabilityDTO), SelfCapabilityVO.class, true);
            return ActResult.initialize(selfCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @param selfCapabilityTO 个人能力基本信息数据to
     * @return class SelfCapabilityVO
     * @des 导入个人能力, 名称不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcelCCapability(@Validated SelfCapabilityTO selfCapabilityTO) throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param name 名称
     * @return class SelfCapabilityVO
     * @des 导出个人能力, 名称可以为空
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcelCCapability(String name) throws ActException {
        return null;
    }

}