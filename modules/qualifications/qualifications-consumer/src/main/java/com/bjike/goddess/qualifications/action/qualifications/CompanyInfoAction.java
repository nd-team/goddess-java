package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.CompanyInfoAPI;
import com.bjike.goddess.qualifications.to.CompanyInfoTO;
import com.bjike.goddess.qualifications.vo.CompanyInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 公司基本信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:38 ]
 * @Description: [ 公司基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/companyinfo")
public class CompanyInfoAction {

    @Autowired
    private CompanyInfoAPI companyInfoAPI;

    /**
     * 保存
     *
     * @param to 公司基本信息传输对象
     * @return class CompanyInfoVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CompanyInfoTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(companyInfoAPI.save(to), CompanyInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 公司基本信息传输对象
     * @return class CompanyInfoVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CompanyInfoTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(companyInfoAPI.update(to), CompanyInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 公司基本信息ID
     * @return class CompanyInfoVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(companyInfoAPI.delete(id), CompanyInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部
     *
     * @return class CompanyInfoVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(companyInfoAPI.all(), CompanyInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}