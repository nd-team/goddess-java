package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.PersonnelInformationAPI;
import com.bjike.goddess.qualifications.to.PersonnelInformationTO;
import com.bjike.goddess.qualifications.vo.PersonnelInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 人员信息资料
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/personnelinformation")
public class PersonnelInformationAction {

    @Autowired
    private PersonnelInformationAPI personnelInformationAPI;

    /**
     * 保存
     *
     * @param to 人员资料信息传输对象
     * @return class PersonnelInformationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PersonnelInformationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelInformationAPI.save(to), PersonnelInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 人员资料信息传输对象
     * @return class PersonnelInformationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PersonnelInformationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelInformationAPI.update(to), PersonnelInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 人员资料信息id
     * @return class PersonnelInformationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelInformationAPI.delete(id), PersonnelInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部
     *
     * @return class PersonnelInformationVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelInformationAPI.all(), PersonnelInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}