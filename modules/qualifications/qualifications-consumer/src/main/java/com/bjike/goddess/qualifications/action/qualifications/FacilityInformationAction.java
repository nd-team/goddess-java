package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.FacilityInformationAPI;
import com.bjike.goddess.qualifications.to.FacilityInformationTO;
import com.bjike.goddess.qualifications.vo.FacilityInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 设备信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/facilityinformation")
public class FacilityInformationAction {

    @Autowired
    private FacilityInformationAPI facilityInformationAPI;

    /**
     * 保存
     *
     * @param to 设备信息传输对象
     * @return class FacilityInformationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) FacilityInformationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityInformationAPI.save(to), FacilityInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 设备信息传输对象
     * @return class FacilityInformationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) FacilityInformationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityInformationAPI.update(to), FacilityInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 设备信息ID
     * @return class FacilityInformationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityInformationAPI.delete(id), FacilityInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部
     *
     * @return class FacilityInformationVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityInformationAPI.all(), FacilityInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}