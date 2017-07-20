package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.HouseAssistAPI;
import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.assistance.dto.HouseAssistDTO;
import com.bjike.goddess.assistance.to.HouseAssistTO;
import com.bjike.goddess.assistance.vo.HouseAssistVO;
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
 * 住宿补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 11:27 ]
 * @Description: [ 住宿补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("houseassist")
public class HouseAssistAction {

    @Autowired
    private HouseAssistAPI houseAssistAPI;

    /**
     *  住宿补助列表总条数
     *
     * @param houseAssistDTO  住宿补助信息dto
     * @des 获取所有住宿补助信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HouseAssistDTO houseAssistDTO) throws ActException {
        try {
            Long count = houseAssistAPI.countHouseAssist(houseAssistDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     *  一个住宿补助
     *
     * @param id  住宿补助id
     * @des 一个住宿补助
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id ) throws ActException {
        try {
            HouseAssistVO ageAssistVO = BeanTransform.copyProperties(
                    houseAssistAPI.getOneById(id), HouseAssistVO.class);
            return ActResult.initialize(ageAssistVO );
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 住宿补助列表
     *
     * @param houseAssistDTO 住宿补助信息dto
     * @des 获取所有住宿补助信息
     * @return  class HouseAssistVO
     * @version v1
     */
    @GetMapping("v1/listHouseAssist")
    public Result findListHouseAssist(HouseAssistDTO houseAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HouseAssistVO> houseAssistVOList = BeanTransform.copyProperties(
                    houseAssistAPI.listHouseAssist(houseAssistDTO), HouseAssistVO.class, true);
            return ActResult.initialize(houseAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加住宿补助
     *
     * @param houseAssistTO 住宿补助基本信息数据to
     * @des 添加住宿补助
     * @return  class HouseAssistVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addHouseAssist(@Validated HouseAssistTO houseAssistTO, BindingResult bindingResult) throws ActException {
        try {
            HouseAssistBO houseAssistBO1 = houseAssistAPI.addHouseAssist(houseAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(houseAssistBO1,HouseAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑住宿补助
     *
     * @param houseAssistTO 住宿补助基本信息数据bo
     * @des 添加住宿补助
     * @return  class HouseAssistVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editHouseAssist(@Validated HouseAssistTO houseAssistTO) throws ActException {
        try {
            HouseAssistBO houseAssistBO1 = houseAssistAPI.editHouseAssist(houseAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(houseAssistBO1,HouseAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除住宿补助信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteHouseAssist(@PathVariable String id) throws ActException {
        try {
            houseAssistAPI.deleteHouseAssist(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    
}