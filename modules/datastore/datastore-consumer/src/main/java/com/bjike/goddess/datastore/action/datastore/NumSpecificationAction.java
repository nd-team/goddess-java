package com.bjike.goddess.datastore.action.datastore;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.api.NumSpecificationAPI;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.to.NumSpecificationTO;
import com.bjike.goddess.datastore.vo.NumSpecificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 数据存储编号规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储编号规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("numspecification")
public class NumSpecificationAction {
    @Autowired
    private NumSpecificationAPI numSpecificationAPI;

    /**
     * 数据存储编号规范列表总条数
     *
     * @param numSpecificationDTO 数据存储编号规范dto
     * @des 获取所有数据存储编号规范总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(NumSpecificationDTO numSpecificationDTO) throws ActException {
        try {
            Long count = numSpecificationAPI.countNumSpecification(numSpecificationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个数据存储编号规范
     *
     * @param id
     * @return class NumSpecificationVO
     * @des 获取一个数据存储编号规范
     * @version v1
     */
    @GetMapping("v1/num/{id}")
    public Result num(@PathVariable String id) throws ActException {
        try {
            NumSpecificationBO numSpecificationBO = numSpecificationAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(numSpecificationBO, NumSpecificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据存储编号规范列表
     *
     * @param numSpecificationDTO 数据存储编号规范dto
     * @return class NumSpecificationVO
     * @des 获取所有数据存储编号规范
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(NumSpecificationDTO numSpecificationDTO, HttpServletRequest request) throws ActException {
        try {
            List<NumSpecificationVO> numSpecificationVOS = BeanTransform.copyProperties
                    (numSpecificationAPI.findListNumSpecification(numSpecificationDTO), NumSpecificationVO.class, request);
            return ActResult.initialize(numSpecificationVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加数据存储编号规范
     *
     * @param numSpecificationTO 数据存储编号规范数据to
     * @return class NumSpecificationVO
     * @des 添加数据存储编号规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(NumSpecificationTO numSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            NumSpecificationBO numSpecificationBO = numSpecificationAPI.insertNumSpecification(numSpecificationTO);
            return ActResult.initialize(numSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑数据存储编号规范
     *
     * @param numSpecificationTO 数据存储编号规范数据to
     * @return class NumSpecificationVO
     * @des 编辑数据存储编号规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(NumSpecificationTO numSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            NumSpecificationBO numSpecificationBO = numSpecificationAPI.editNumSpecification(numSpecificationTO);
            return ActResult.initialize(numSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除数据存储编号规范
     *
     * @param id 用户id
     * @des 根据用户id删除数据存储编号规范记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            numSpecificationAPI.removeNumSpecification(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}