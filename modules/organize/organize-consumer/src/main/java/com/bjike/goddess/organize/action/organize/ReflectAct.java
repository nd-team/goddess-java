package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ReflectAPI;
import com.bjike.goddess.organize.dto.ReflectDTO;
import com.bjike.goddess.organize.to.ReflectTO;
import com.bjike.goddess.organize.vo.ReflectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 体现类型操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("reflect")
public class ReflectAct {

    @Autowired
    private ReflectAPI reflectAPI;

    /**
     * 保存体现类型信息
     *
     * @param to 体现类型传输对象
     * @return class ReflectVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ReflectTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.save(to), ReflectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改体现类型信息
     *
     * @param to 体现类型传输对象
     * @return class ReflectVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ReflectTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.update(to), ReflectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的体现类型信息
     *
     * @return class ReflectVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.findStatus(), ReflectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启
     *
     * @param id 体现类型数据id
     * @return class ReflectVO
     * @version v1
     */
    @PatchMapping("v1/open/{id}")
    public Result open(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.open(id), ReflectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 体现类型数据id
     * @return class ReflectVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.delete(id), ReflectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 体现类型数据传输
     * @return class ReflectVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ReflectDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.maps(dto), ReflectVO.class, request));
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
            return ActResult.initialize(reflectAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询体现类型
     *
     * @param id
     * @return class ReflectVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.findById(id), ReflectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
