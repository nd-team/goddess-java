package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.QualificationsInfoAPI;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoStatusTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoTO;
import com.bjike.goddess.qualifications.vo.QualificationsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 资质信息管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/qualificationsinfo")
public class QualificationsInfoAction {

    @Autowired
    private QualificationsInfoAPI qualificationsInfoAPI;

    /**
     * 保存
     *
     * @param to 资质信息管理传输对象
     * @return class QualificationsInfoVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) QualificationsInfoTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsInfoAPI.save(to), QualificationsInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 资质信息管理传输对象
     * @return class QualificationsInfoVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) QualificationsInfoTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsInfoAPI.update(to), QualificationsInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资质信息管理id
     * @return class QualificationsInfoVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsInfoAPI.delete(id), QualificationsInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改资质信息状态
     *
     * @param to 资质信息管理资质状态传输对象
     * @return class QualificationsInfoVO
     * @version v1
     */
    @PutMapping("v1/updateStatus/{id}")
    public Result updateStatus(@Validated(EDIT.class) QualificationsInfoStatusTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsInfoAPI.updateStatus(to), QualificationsInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据纸质类型查询资质信息
     *
     * @param type 资质类型
     * @return class QualificationsInfoVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsInfoAPI.findByType(type), QualificationsInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 资质信息管理数据传输对象
     * @return class QualificationsInfoVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(QualificationsInfoDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsInfoAPI.maps(dto), QualificationsInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}