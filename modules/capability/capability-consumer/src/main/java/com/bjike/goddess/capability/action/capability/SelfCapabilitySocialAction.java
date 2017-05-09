package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.SelfCapabilitySocialAPI;
import com.bjike.goddess.capability.bo.SelfCapabilitySocialBO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.to.SelfCapabilitySocialTO;
import com.bjike.goddess.capability.vo.SelfCapabilitySocialVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 个人社交展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人社交展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("selfsocial")
public class SelfCapabilitySocialAction {
    @Autowired
    private SelfCapabilitySocialAPI selfCapabilitySocialAPI;

    /**
     * 个人社交总条数
     *
     * @param selfCapabilitySocialDTO
     * @des 获取所有个人社交总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws ActException {
        try {
            Long count = selfCapabilitySocialAPI.counts(selfCapabilitySocialDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个个人社交
     *
     * @param id 列表id
     * @param request  前端过滤参数
     * @des 获取一个个人社交
     * @return class SelfCapabilitySocialVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id,HttpServletRequest request) throws ActException {
        try {
            SelfCapabilitySocialBO selfCapabilitySocialBO = selfCapabilitySocialAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilitySocialBO ,SelfCapabilitySocialVO.class , request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人社交列表
     *
     * @param selfCapabilitySocialDTO 个人社交信息dto
     * @param request  前端过滤参数
     * @return class SelfCapabilitySocialVO
     * @des 获取所有个人社交信息
     * @version v1
     */
    @GetMapping("v1/listSelf")
    public Result findList(SelfCapabilitySocialDTO selfCapabilitySocialDTO,HttpServletRequest request) throws ActException {
        try {
            List<SelfCapabilitySocialVO> selfCapabilitySocialVOList = BeanTransform.copyProperties(
                    selfCapabilitySocialAPI.listSelfCapabilitySocial(selfCapabilitySocialDTO), SelfCapabilitySocialVO.class, request);
            return ActResult.initialize(selfCapabilitySocialVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加个人社交
     *
     * @param selfCapabilitySocialTO 个人社交基本信息数据to
     * @return class SelfCapabilitySocialVO
     * @des 添加个人社交, 公司名称不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSelfCapabilitySocial(@Validated(SelfCapabilitySocialTO.TestAdd.class) SelfCapabilitySocialTO selfCapabilitySocialTO) throws ActException {
        try {
            SelfCapabilitySocialBO selfCapabilitySocialBO1 = selfCapabilitySocialAPI.addSelfCapabilitySocial(selfCapabilitySocialTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilitySocialBO1, SelfCapabilitySocialVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 编辑个人社交
     *
     * @param selfCapabilitySocialTO 个人社交基本信息数据bo
     * @param id 个人社交基本信息数据id
     * @return class SelfCapabilitySocialVO
     * @des 编辑个人社交
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit/{id}")
    public Result editSelfCapabilitySocial( @PathVariable String id , SelfCapabilitySocialTO selfCapabilitySocialTO) throws ActException {
        try {
            SelfCapabilitySocialBO selfCapabilitySocialBO1 = selfCapabilitySocialAPI.editSelfCapabilitySocial(id,selfCapabilitySocialTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilitySocialBO1, SelfCapabilitySocialVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除个人社交信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSelfCapabilitySocial(@PathVariable String id) throws ActException {
        try {
            selfCapabilitySocialAPI.deleteSelfCapabilitySocial(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}