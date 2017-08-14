package com.bjike.goddess.system.action.system;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.system.api.AuswerAPI;
import com.bjike.goddess.system.bo.AuswerBO;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.to.AuswerTO;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.vo.AuswerVO;
import com.bjike.goddess.system.vo.FieldDockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 答案
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("auswer")
public class AuswerAction {
    @Autowired
    private AuswerAPI auswerAPI;
    /**
     * 答案列表总条数
     *
     * @param dto 答案记录dto
     * @des 获取所有答案
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AuswerDTO dto) throws ActException {
        try {
            Long count = auswerAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个答案
     *
     * @param id
     * @return class AuswerVO
     * @des 获取一个答案
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            AuswerBO auswerBO = auswerAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(auswerBO, AuswerVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 答案列表
     *
     * @param dto 答案记录dto
     * @return class FieldDockVO
     * @des 获取所有答案
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AuswerDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AuswerVO> auswerVOS = BeanTransform.copyProperties(
                    auswerAPI.list(dto), AuswerVO.class, request);
            return ActResult.initialize(auswerVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加答案
     *
     * @param to 答案to
     * @return class AuswerVO
     * @des 添加答案
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(AuswerTO to, BindingResult bindingResult) throws ActException {
        try {
            AuswerBO auswerBO = auswerAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(auswerBO,AuswerVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑答案
     *
     * @param to 答案数据to
     * @return class AuswerVO
     * @des 编辑答案
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(AuswerTO to, BindingResult bindingResult) throws ActException {
        try {
            AuswerBO auswerBO = auswerAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(auswerBO,AuswerVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除答案
     *
     * @param id 用户id
     * @des 根据用户id删除答案
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            auswerAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}