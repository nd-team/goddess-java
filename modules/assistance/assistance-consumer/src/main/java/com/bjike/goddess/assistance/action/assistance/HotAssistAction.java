package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.HotAssistAPI;
import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.dto.HotAssistDTO;
import com.bjike.goddess.assistance.to.HotAssistTO;
import com.bjike.goddess.assistance.vo.HotAssistVO;
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
 * 高温补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("hotassist")
public class HotAssistAction {

    @Autowired
    private HotAssistAPI hotAssistAPI;

    /**
     *  高温补助列表总条数
     *
     * @param hotAssistDTO  高温补助信息dto
     * @des 获取所有高温补助信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HotAssistDTO hotAssistDTO) throws ActException {
        try {
            Long count = hotAssistAPI.countHotAssist(hotAssistDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 高温补助列表
     *
     * @param hotAssistDTO 高温补助信息dto
     * @des 获取所有高温补助信息
     * @return  class HotAssistVO
     * @version v1
     */
    @GetMapping("v1/listHotAssist")
    public Result findListHotAssist(HotAssistDTO hotAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HotAssistVO> hotAssistVOList = BeanTransform.copyProperties(
                    hotAssistAPI.listHotAssist(hotAssistDTO), HotAssistVO.class, true);
            return ActResult.initialize(hotAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加高温补助
     *
     * @param hotAssistTO 高温补助基本信息数据to
     * @des 添加高温补助
     * @return  class HotAssistVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addHotAssist(@Validated HotAssistTO hotAssistTO, BindingResult bindingResult) throws ActException {
        try {
            HotAssistBO hotAssistBO1 = hotAssistAPI.addHotAssist(hotAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(hotAssistBO1,HotAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑高温补助
     *
     * @param hotAssistTO 高温补助基本信息数据bo
     * @des 添加高温补助
     * @return  class HotAssistVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editHotAssist(@Validated HotAssistTO hotAssistTO) throws ActException {
        try {
            HotAssistBO hotAssistBO1 = hotAssistAPI.editHotAssist(hotAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(hotAssistBO1,HotAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除高温补助信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteHotAssist(@PathVariable String id) throws ActException {
        try {
            hotAssistAPI.deleteHotAssist(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 地区汇总
     *
     * @param hotAssistDTO 高温补助信息dto
     * @des 根据地区汇总
     * @return  class HotAssistVO
     * @version v1
     */
    @GetMapping("v1/collectByArea")
    public Result collectByArea(HotAssistDTO hotAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HotAssistVO> hotAssistVOList = BeanTransform.copyProperties(
                    hotAssistAPI.collectByArea(hotAssistDTO), HotAssistVO.class, true);
            return ActResult.initialize(hotAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param hotAssistDTO 高温补助信息dto
     * @des 获取所有高温补助信息
     * @return  class HotAssistVO
     * @version v1
     */
    @GetMapping("v1/collectByProGroup")
    public Result collectByProGroup(HotAssistDTO hotAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HotAssistVO> hotAssistVOList = BeanTransform.copyProperties(
                    hotAssistAPI.collectByProGroup(hotAssistDTO), HotAssistVO.class, true);
            return ActResult.initialize(hotAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    
}