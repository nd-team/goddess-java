package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.AuditMaterialAPI;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.to.AuditMaterialTO;
import com.bjike.goddess.qualifications.vo.AuditMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 审核资料
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("auditmaterial")
public class AuditMaterialAct {

    @Autowired
    private AuditMaterialAPI auditMaterialAPI;

    /**
     * 保存
     *
     * @param to 审核资料传输对象
     * @return class AuditMaterialVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AuditMaterialTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(auditMaterialAPI.save(to), AuditMaterialVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 审核资料传输对象
     * @return class AuditMaterialVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) AuditMaterialTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(auditMaterialAPI.update(to), AuditMaterialVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 审核资料ID
     * @return class AuditMaterialVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(auditMaterialAPI.delete(id), AuditMaterialVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部
     *
     * @return class AuditMaterialVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(auditMaterialAPI.all(), AuditMaterialVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 审核资料数据传输对象
     * @return class AuditMaterialVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AuditMaterialDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(auditMaterialAPI.maps(dto), AuditMaterialVO.class, request));
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
            return ActResult.initialize(auditMaterialAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return class AuditMaterialVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(auditMaterialAPI.getById(id), AuditMaterialVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}