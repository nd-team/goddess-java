package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.RentPayAPI;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
import com.bjike.goddess.rentutilitiespay.vo.CollectAreaVO;
import com.bjike.goddess.rentutilitiespay.vo.RentPayVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.ISCII91;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 房租缴费
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("rentpay")
public class RentPayAction {
    @Autowired
    private RentPayAPI rentPayAPI;
    /**
     * 房租缴费列表总条数
     *
     * @param rentPayDTO 房租缴费记录dto
     * @des 获取所有房租缴费
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RentPayDTO rentPayDTO) throws ActException {
        try {
            Long count = rentPayAPI.countRentPay(rentPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个房租缴费
     *
     * @param id
     * @return class RentPayVO
     * @des 获取一个房租缴费
     * @version v1
     */
    @GetMapping("v1/rent/{id}")
    public Result rent(@PathVariable String id) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(rentPayBO, RentPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取房租缴费
     *
     * @param rentPayDTO 房租缴费dto
     * @return class RentPayVO
     * @des 获取所有房租缴费
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RentPayDTO rentPayDTO, HttpServletRequest request) throws ActException {
        try {
            List<RentPayVO> rentPayVOS = BeanTransform.copyProperties
                    (rentPayAPI.findListRentPay(rentPayDTO),RentPayVO.class,request);
            return ActResult.initialize(rentPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加房租缴费
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayVO
     * @des 添加房租缴费
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RentPayTO rentPayTO, BindingResult bindingResult) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.insertRentPay(rentPayTO);
            return ActResult.initialize(rentPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑房租缴费
     *
     * @param rentPayTO 房租缴费数据to
     * @return class RentPayVO
     * @des 编辑房租缴费
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RentPayTO rentPayTO, BindingResult bindingResult) throws ActException {
        try {
            RentPayBO rentPayBO = rentPayAPI.editRentPay(rentPayTO);
            return ActResult.initialize(rentPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除房租缴费
     *
     * @param id 用户id
     * @des 根据用户id删除房租缴费记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rentPayAPI.removeRentPay(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @version v1
     */
    @PostMapping("v1/uploadAttachments")
    public Result uploadAttachments() throws ActException {
        try {
            rentPayAPI.uploadAttachments();
            return new ActResult("uploadAttachments success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param areas 地区
     * @des 根据地区汇总
     * @return  class CollectAreaVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect ( @RequestParam String[] areas ) throws ActException {
        try {
            List<CollectAreaVO> collectAreaVOS = BeanTransform.copyProperties(
                    rentPayAPI.collectArea(areas),CollectAreaVO.class,true);
            return ActResult.initialize(collectAreaVOS);
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
            List<String> areaList = rentPayAPI.getArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}