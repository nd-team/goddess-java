package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CusFamilyMemberAPI;
import com.bjike.goddess.customer.bo.CusFamilyMemberBO;
import com.bjike.goddess.customer.dto.CusFamilyMemberDTO;
import com.bjike.goddess.customer.to.CusFamilyMemberTO;
import com.bjike.goddess.customer.vo.CusFamilyMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客户家庭成员
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:48:29.110 ]
 * @Description: [ 客户家庭成员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customer/cusfamilymember")
public class CusFamilyMemberAction {

    @Autowired
    private CusFamilyMemberAPI cusFamilyMemberAPI;

    /**
     * 客户家庭成员列表
     *
     * @param cusFamilyMemberDTO 客户家庭成员信息dto
     * @return class CusFamilyMemberVO
     * @des 获取所有客户家庭成员信息
     * @version v1
     */
    @GetMapping("v1/listCusFamilyMember")
    public Result findListCusFamilyMember(CusFamilyMemberDTO cusFamilyMemberDTO) throws ActException {
        try {
            List<CusFamilyMemberVO> cusFamilyMemberVOList = BeanTransform.copyProperties(
                    cusFamilyMemberAPI.listCusFamilyMember(cusFamilyMemberDTO), CusFamilyMemberVO.class, true);
            return ActResult.initialize(cusFamilyMemberVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户家庭成员
     *
     * @param cusFamilyMemberTO 客户家庭成员基本信息数据to
     * @return class CusFamilyMemberVO
     * @des 添加客户家庭成员
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCusFamilyMember(@Validated CusFamilyMemberTO cusFamilyMemberTO) throws ActException {
        try {
            CusFamilyMemberBO cusFamilyMemberBO1 = cusFamilyMemberAPI.addCusFamilyMember(cusFamilyMemberTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusFamilyMemberBO1, CusFamilyMemberVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户家庭成员
     *
     * @param cusFamilyMemberTO 客户家庭成员基本信息数据bo
     * @return class CusFamilyMemberVO
     * @des 添加客户家庭成员
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editCusFamilyMember(@Validated CusFamilyMemberTO cusFamilyMemberTO) throws ActException {
        try {
            CusFamilyMemberBO cusFamilyMemberBO1 = cusFamilyMemberAPI.editCusFamilyMember(cusFamilyMemberTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusFamilyMemberBO1, CusFamilyMemberVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户家庭成员信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteEntryBasicInfo(@PathVariable String id) throws ActException {
        try {
            cusFamilyMemberAPI.deleteCusFamilyMember(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户基本信息记录
     * @version v1
     */
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            cusFamilyMemberAPI.congealCusFamilyMember(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户基本信息记录
     * @version v1
     */
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            cusFamilyMemberAPI.thawCusFamilyMember(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}