package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.QualificationsHandleAPI;
import com.bjike.goddess.qualifications.dto.QualificationsHandleDTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleForeignTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import com.bjike.goddess.qualifications.vo.QualificationsHandleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 资质办理管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/qualificationshandle")
public class QualificationsHandleAction {

    @Autowired
    private QualificationsHandleAPI qualificationsHandleAPI;

    /**
     * 保存
     *
     * @param to 资质办理管理传输对象
     * @return class QualificationsHandleVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) QualificationsHandleTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.save(to), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 资质办理管理传输对象
     * @return class QualificationsHandleVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) QualificationsHandleTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.update(to), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资质办理id
     * @return class QualificationsHandleVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.delete(id), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未获取资质
     *
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.findStatus(), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 资质办理管理数据传输对象
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(QualificationsHandleDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.maps(dto), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id根据资质类型查询资质办理
     *
     * @param type 资质类型
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.findByType(type), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取人员信息资料
     *
     * @param id 资质办理id
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/getPersonnel/{id}")
    public Result getPersonnel(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.getPersonnel(id), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取财务资料业务
     *
     * @param id 资质办理id
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/getFinance/{id}")
    public Result getFinance(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.getFinance(id), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取设备信息
     *
     * @param id 资质办理id
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/getFacility/{id}")
    public Result getFacility(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.getFacility(id), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取公司基本信息
     *
     * @param id 资质办理id
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/getCompany/{id}")
    public Result getCompany(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.getCompany(id), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取审核资料
     *
     * @param id 资质办理id
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/getAudit/{id}")
    public Result getAudit(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.getAudit(id), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存资料管理
     *
     * @param to 资质办理管理外键id传输对象
     * @return class QualificationsHandleVO
     * @version v1
     */
    @GetMapping("v1/saveForeign")
    public Result saveForeign(@Validated(EDIT.class) QualificationsHandleForeignTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandleAPI.saveForeign(to), QualificationsHandleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}