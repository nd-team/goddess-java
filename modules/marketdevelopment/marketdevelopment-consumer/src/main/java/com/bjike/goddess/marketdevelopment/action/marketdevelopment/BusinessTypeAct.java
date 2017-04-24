package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.BusinessTypeAPI;
import com.bjike.goddess.marketdevelopment.dto.BusinessTypeDTO;
import com.bjike.goddess.marketdevelopment.to.BusinessTypeTO;
import com.bjike.goddess.marketdevelopment.vo.BusinessTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 业务类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:20 ]
 * @Description: [ 业务类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesstype")
public class BusinessTypeAct {

    @Autowired
    private BusinessTypeAPI businessTypeAPI;


    /**
     * 列表
     *
     * @param dto 业务类型数据传输对象
     * @return class BusinessTypeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(BusinessTypeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.maps(dto), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存业务类型数据
     *
     * @param to 业务类型传输对象
     * @return class BusinessTypeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) BusinessTypeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.save(to), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改业务类型数据
     *
     * @param to 业务类型传输对象
     * @return class BusinessTypeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) BusinessTypeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.update(to), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结业务类型数据
     *
     * @param to 业务类型传输对象
     * @return class BusinessTypeVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(BusinessTypeTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.save(to), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻业务类型数据
     *
     * @param to 业务类型传输对象
     * @return class BusinessTypeVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(BusinessTypeTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.thaw(to), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除业务类型数据
     *
     * @param to 业务类型传输对象
     * @return class BusinessTypeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(BusinessTypeTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.delete(to), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常数据的业务类型数据
     *
     * @return class BusinessTypeVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.findThaw(), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取业务类型数据
     *
     * @param id 业务类型数据id
     * @return class BusinessTypeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessTypeAPI.getById(id), BusinessTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(businessTypeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}