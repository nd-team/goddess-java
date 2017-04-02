package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.QualificationsGatherAPI;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.to.QualificationsGatherTO;
import com.bjike.goddess.qualifications.vo.QualificationsGatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 资质办理信息采集
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/qualificationsgather")
public class QualificationsGatherAction {

    @Autowired
    private QualificationsGatherAPI qualificationsGatherAPI;

    /**
     * 保存
     *
     * @param to 资质办理传输对象
     * @return class QualificationsGatherVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) QualificationsGatherTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsGatherAPI.save(to), QualificationsGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 资质办理传输对象
     * @return class QualificationsGatherVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) QualificationsGatherTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsGatherAPI.update(to), QualificationsGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资质办理传输对象
     * @return class QualificationsGatherVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsGatherAPI.delete(id), QualificationsGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据类型查询资质办理数据
     *
     * @param type 资质类型
     * @return class QualificationsGatherVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsGatherAPI.findByType(type), QualificationsGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 资质办理信息采集数据传输对象
     * @return class QualificationsGatherVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(QualificationsGatherDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsGatherAPI.maps(dto), QualificationsGatherVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}