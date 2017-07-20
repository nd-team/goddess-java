package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.ComputerAssistAPI;
import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.dto.ComputerAssistDTO;
import com.bjike.goddess.assistance.to.ComputerAssistTO;
import com.bjike.goddess.assistance.vo.ComputerAssistVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 电脑补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:20 ]
 * @Description: [ 电脑补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("computerassist")
public class ComputerAssistAction {

    @Autowired
    private ComputerAssistAPI computerAssistAPI;

    /**
     * 电脑补助列表总条数
     *
     * @param computerAssistDTO 电脑补助信息dto
     * @des 获取所有电脑补助信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ComputerAssistDTO computerAssistDTO) throws ActException {
        try {
            Long count = computerAssistAPI.countComputerAssist(computerAssistDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     *  一个电脑补助
     *
     * @param id  电脑补助id
     * @des 一个电脑补助
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id ) throws ActException {
        try {
            ComputerAssistVO computerAssistVO = BeanTransform.copyProperties(
                    computerAssistAPI.getOneById(id), ComputerAssistVO.class);
            return ActResult.initialize(computerAssistVO );
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 电脑补助列表
     *
     * @param computerAssistDTO 电脑补助信息dto
     * @return class ComputerAssistVO
     * @des 获取所有电脑补助信息
     * @version v1
     */
    @GetMapping("v1/listComputerAssist")
    public Result findListComputerAssist(ComputerAssistDTO computerAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ComputerAssistVO> computerAssistVOList = BeanTransform.copyProperties(
                    computerAssistAPI.listComputerAssist(computerAssistDTO), ComputerAssistVO.class, true);
            return ActResult.initialize(computerAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加电脑补助
     *
     * @param computerAssistTO 电脑补助基本信息数据to
     * @return class ComputerAssistVO
     * @des 添加电脑补助
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addComputerAssist(@Validated ComputerAssistTO computerAssistTO, BindingResult bindingResult) throws ActException {
        try {
            ComputerAssistBO computerAssistBO1 = computerAssistAPI.addComputerAssist(computerAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(computerAssistBO1, ComputerAssistVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑电脑补助
     *
     * @param computerAssistTO 电脑补助基本信息数据bo
     * @return class ComputerAssistVO
     * @des 添加电脑补助
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editComputerAssist(@Validated ComputerAssistTO computerAssistTO) throws ActException {
        try {
            ComputerAssistBO computerAssistBO1 = computerAssistAPI.editComputerAssist(computerAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(computerAssistBO1, ComputerAssistVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除电脑补助信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteComputerAssist(@PathVariable String id) throws ActException {
        try {
            computerAssistAPI.deleteComputerAssist(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 地区汇总
     *
     * @param computerAssistDTO 电脑补助信息dto
     * @return class ComputerAssistVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/collectByArea")
    public Result collectByArea(ComputerAssistDTO computerAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ComputerAssistVO> computerAssistVOList = BeanTransform.copyProperties(
                    computerAssistAPI.collectByArea(computerAssistDTO), ComputerAssistVO.class, true);
            return ActResult.initialize(computerAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param computerAssistDTO 电脑补助信息dto
     * @return class ComputerAssistVO
     * @des 获取所有电脑补助信息
     * @version v1
     */
    @GetMapping("v1/collectByProGroup")
    public Result collectByProGroup(ComputerAssistDTO computerAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ComputerAssistVO> computerAssistVOList = BeanTransform.copyProperties(
                    computerAssistAPI.collectByProGroup(computerAssistDTO), ComputerAssistVO.class, true);
            return ActResult.initialize(computerAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有员工
     *
     * @des 获取所有所有员工
     * @version v1
     */
    @GetMapping("v1/listAllUser")
    public Result listAllUser() throws ActException {
        try {
            List<String> users = computerAssistAPI.listAllUser();
            return ActResult.initialize(users);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据员工名获取员工信息
     *
     * @des 根据员工名获取员工信息
     * @version v1
     */
    @GetMapping("v1/getUserByName")
    public Result getUserByName( @Validated(ComputerAssistDTO.TestQueryByName.class) ComputerAssistDTO computerAssistDTO) throws ActException {
        try {
            EntryBasicInfoVO users = BeanTransform.copyProperties(
                    computerAssistAPI.getUserByName(computerAssistDTO) , EntryBasicInfoVO.class);
            return ActResult.initialize(users);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}