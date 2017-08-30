package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.dto.ModuleTypeDTO;
import com.bjike.goddess.organize.to.ModuleTypeTO;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 模块类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moduletype")
public class ModuleTypeAct {

    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private ModuleAPI moduleAPI;

    /**
     * 保存
     *
     * @param to 模块类型传输对象
     * @return class ModuleTypeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ModuleTypeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.save(to), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 模块类型传输对象
     * @return class ModuleTypeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ModuleTypeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.update(to), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 模块类型数据id
     * @return class ModuleTypeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.delete(id), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 模块类型数据id
     * @return class ModuleTypeVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.congeal(id), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 模块类型数据id
     * @return class ModuleTypeVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.thaw(id), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询模块类型
     *
     * @param status 状态
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Status status, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(status), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的模块类型数据
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(Status.THAW), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 模块类型数据传输对象
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ModuleTypeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.maps(dto), ModuleTypeVO.class, request));
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
            return ActResult.initialize(moduleTypeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询模块类型
     *
     * @param id 模块类型数据id
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findById(id), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询未冻结模块选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findThawOpinion")
    public Result findThawOpinion(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findThawOpinion(), OpinionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 查询所有的模块
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getModule")
//    public Result getModule(HttpServletRequest request) throws ActException {
//        try {
//            if(moduleAPI.isCheck("")){}
//
//            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findThawOpinion(), OpinionVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


}