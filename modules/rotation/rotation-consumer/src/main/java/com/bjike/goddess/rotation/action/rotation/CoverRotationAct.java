package com.bjike.goddess.rotation.action.rotation;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.api.CoverRotationAPI;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.to.CoverRotationOpinionTO;
import com.bjike.goddess.rotation.to.CoverRotationTO;
import com.bjike.goddess.rotation.vo.CoverRotationOpinionVO;
import com.bjike.goddess.rotation.vo.CoverRotationVO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位轮换自荐
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("coverrotation")
public class CoverRotationAct {

    @Autowired
    private CoverRotationAPI coverRotationAPI;

    /**
     * 保存
     *
     * @param to 岗位轮换自荐传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CoverRotationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.save(to), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 岗位轮换自荐传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(ADD.class) CoverRotationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.update(to), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位轮换自荐数据id
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.delete(id), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询岗位轮换自荐数据
     *
     * @param id 岗位轮换自荐数据id
     * @return class CoverRotationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.getById(id), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 意见
     *
     * @param to 岗位轮换自荐意见传输对象
     * @return class CoverRotationOpinionVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/opinion/{id}")
    public Result opinion(@Validated(ADD.class) CoverRotationOpinionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            to.setCoverId(to.getId());
            to.setId(null);
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.opinion(to), CoverRotationOpinionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办意见
     *
     * @param to 岗位轮换自荐传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/general/opinion/{id}")
    public Result generalOpinion(@Validated(EDIT.class) CoverRotationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.generalOpinion(to), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 岗位轮换自荐数据传输对象
     * @return class CoverRotationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CoverRotationDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(coverRotationAPI.maps(dto), CoverRotationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(coverRotationAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}