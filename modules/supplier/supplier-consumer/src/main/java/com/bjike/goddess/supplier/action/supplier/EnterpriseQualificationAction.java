package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.EnterpriseQualificationAPI;
import com.bjike.goddess.supplier.to.EnterpriseQualificationTO;
import com.bjike.goddess.supplier.vo.EnterpriseQualificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 企业资质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.261 ]
 * @Description: [ 企业资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplier/enterprisequalification")
public class EnterpriseQualificationAction {

    @Autowired
    private EnterpriseQualificationAPI enterpriseQualificationAPI;

    /**
     * 根据供应商基本信息ID查询企业资质
     *
     * @param info_id 供应商基本信息ID
     * @return class EnterpriseQualificationVO
     * @version v1
     */
    @GetMapping("v1/findByInformation/{info_id}")
    public Result findByInformation(@PathVariable String info_id) throws ActException {
        try {
            return ActResult.initialize(
                    BeanTransform.copyProperties(
                            enterpriseQualificationAPI.findByInformation(info_id)
                            , EnterpriseQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存供应商企业资质数据
     *
     * @param to 供应商企业资质传输对象
     * @return class EnterpriseQualificationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated EnterpriseQualificationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enterpriseQualificationAPI.save(to), EnterpriseQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商企业资质数据
     *
     * @param to 供应商企业资质传输对象
     * @return class EnterpriseQualificationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated EnterpriseQualificationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enterpriseQualificationAPI.update(to), EnterpriseQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除供应商企业资质数据
     *
     * @param id 供应商企业资质id
     * @return class EnterpriseQualificationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enterpriseQualificationAPI.delete(id), EnterpriseQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}