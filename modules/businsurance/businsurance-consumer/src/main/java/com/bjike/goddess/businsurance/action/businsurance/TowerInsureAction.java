package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.TowerInsureAPI;
import com.bjike.goddess.businsurance.bo.TowerInsureBO;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.to.TowerInsureTO;
import com.bjike.goddess.businsurance.vo.TowerInsureVO;
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
 * 塔工意外险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("towerinsure")
public class TowerInsureAction {


    @Autowired
    private TowerInsureAPI towerInsureAPI;

    /**
     * 塔工意外险列表总条数
     *
     * @param towerInsureDTO 塔工意外险信息dto
     * @des 获取所有塔工意外险信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TowerInsureDTO towerInsureDTO) throws ActException {
        try {
            Long count = towerInsureAPI.countTowerInsure(towerInsureDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 塔工意外险列表
     *
     * @param towerInsureDTO 塔工意外险信息dto
     * @return class TowerInsureVO
     * @des 获取所有塔工意外险信息
     * @version v1
     */
    @GetMapping("v1/listTowerInsure")
    public Result findList(TowerInsureDTO towerInsureDTO, BindingResult bindingResult) throws ActException {
        try {
            List<TowerInsureVO> towerInsureVOList = BeanTransform.copyProperties(
                    towerInsureAPI.listTowerInsure(towerInsureDTO), TowerInsureVO.class, true);
            return ActResult.initialize(towerInsureVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加塔工意外险
     *
     * @param towerInsureTO 塔工意外险基本信息数据to
     * @return class TowerInsureVO
     * @des 添加塔工意外险
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(TowerInsureTO.TestAddAndEdit.class) TowerInsureTO towerInsureTO, BindingResult bindingResult) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.addTowerInsure(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑塔工意外险
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑塔工意外险
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(TowerInsureTO.TestAddAndEdit.class) TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editTowerInsure(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除塔工意外险信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            towerInsureAPI.deleteTowerInsure(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 编辑转账授权信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑转账授权信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editAccount")
    public Result editAccount(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editAccount(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑要约内容
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑要约内容
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editContext")
    public Result editContext(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editContext(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑投保人基本信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑投保人基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/applicant")
    public Result applicant(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.applicant(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑被投保人基本信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑被投保人基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editInsurePerson")
    public Result editInsurePerson(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editInsurePerson(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑受益人基本信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑受益人基本信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editBenefit")
    public Result editBenefit(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editBenefit(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑销售机构信息
     *
     * @param towerInsureTO 塔工意外险基本信息数据bo
     * @return class TowerInsureVO
     * @des 编辑销售机构信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSaleOrgan")
    public Result editSaleOrgan(@Validated TowerInsureTO towerInsureTO) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.editSaleOrgan(towerInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个方案查看详细
     *
     * @param id id
     * @return class TowerInsureVO
     * @des 根据id查看详细
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            TowerInsureBO towerInsureBO1 = towerInsureAPI.getTowerInsure(id);
            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param towerInsureDTO 塔工意外险基本信息数据towerInsureDTO
     * @des 上传附件
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(TowerInsureDTO towerInsureDTO, BindingResult bindingResult) throws ActException {
//        try {
//            TowerInsureBO towerInsureBO1 = towerInsureAPI.uploadFile(towerInsureDTO);
//            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return new ActResult(null);
    }


    /**
     * 查看附件
     *
     * @param id 塔工意外险基本信息数据id
     * @des 查看附件
     * @version v1
     */
    @GetMapping("v1/getFile/{id}")
    public Result getFile(@PathVariable String id , BindingResult bindingResult) throws ActException {
//        try {
//            TowerInsureBO towerInsureBO1 = towerInsureAPI.addTowerInsure(towerInsureTO);
//            return ActResult.initialize(BeanTransform.copyProperties(towerInsureBO1, TowerInsureVO.class, true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
        return new ActResult(null);
    }

}