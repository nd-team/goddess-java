package com.bjike.goddess.business.action.business;

import com.bjike.goddess.business.api.BusinessTaxChangeAPI;
import com.bjike.goddess.business.bo.BusinessTaxChangeBO;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.to.BusinessTaxChangeTO;
import com.bjike.goddess.business.vo.BusinessTaxChangeVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工商税务变更
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesstaxchange")
public class BusinessTaxChangeAction {
    @Autowired
    private BusinessTaxChangeAPI businessTaxChangeAPI;

    /**
     * 工商税务变更列表总条数
     *
     * @param businessTaxChangeDTO 工商税务变更dto
     * @des 获取所有工商税务变更总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessTaxChangeDTO businessTaxChangeDTO) throws ActException {
        try {
            Long count = businessTaxChangeAPI.countBusinessTaxChange(businessTaxChangeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个工商税务变更
     *
     * @param id
     * @return class BusinessTaxChangeVO
     * @des 获取一个工商税务变更
     * @version v1
     */
    @GetMapping("v1/change/{id}")
    public Result change(@PathVariable String id) throws ActException {
        try {
            BusinessTaxChangeBO businessTaxChangeBO = businessTaxChangeAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(businessTaxChangeBO, BusinessTaxChangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工商税务变更列表
     *
     * @param businessTaxChangeDTO 工商税务变更dto
     * @return class BusinessTaxChangeVO
     * @des 获取所有工商税务变更
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BusinessTaxChangeDTO businessTaxChangeDTO, HttpServletRequest request) throws ActException {
        try {
            List<BusinessTaxChangeVO> businessTaxChangeVOS = BeanTransform.copyProperties
                    (businessTaxChangeAPI.findListBusinessTaxChange(businessTaxChangeDTO), BusinessTaxChangeVO.class, request);
            return ActResult.initialize(businessTaxChangeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工商税务变更
     *
     * @param businessTaxChangeTO 工商税务变更数据to
     * @return class BusinessTaxChangeVO
     * @des 添加工商税务变更
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BusinessTaxChangeTO businessTaxChangeTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessTaxChangeBO businessTaxChangeBO = businessTaxChangeAPI.insertBusinessTaxChange(businessTaxChangeTO);
            return ActResult.initialize(businessTaxChangeBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工商税务变更
     *
     * @param businessTaxChangeTO 工商税务变更数据to
     * @return class BusinessTaxChangeVO
     * @des 编辑工商税务变更
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BusinessTaxChangeTO businessTaxChangeTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessTaxChangeBO businessTaxChangeBO = businessTaxChangeAPI.editBusinessTaxChange(businessTaxChangeTO);
            return ActResult.initialize(businessTaxChangeBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工商税务变更
     *
     * @param id 用户id
     * @des 根据用户id删除工商税务变更记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            businessTaxChangeAPI.removeBusinessTaxChange(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            businessTaxChangeAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 下载
     *
     * @version v1
     */
    @PostMapping("v1/download")
    public Result download() throws ActException {
        try {
            businessTaxChangeAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}