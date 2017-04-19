package com.bjike.goddess.business.action.business;

import com.bjike.goddess.business.api.BusinessAnnualInfoAPI;
import com.bjike.goddess.business.bo.BusinessAnnualInfoBO;
import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.to.BusinessAnnualInfoTO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.business.vo.BusinessAnnualInfoVO;
import com.bjike.goddess.business.vo.BusinessRegisterVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工商年检信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessannualinfo")
public class BusinessAnnualInfoAction {
    @Autowired
    private BusinessAnnualInfoAPI businessAnnualInfoAPI;
    /**
     * 工商年检信息列表总条数
     *
     * @param businessAnnualInfoDTO 工商年检信息dto
     * @des 获取所有工商年检信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws ActException {
        try {
            Long count = businessAnnualInfoAPI.countBusinessAnnualInfo(businessAnnualInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工商年检信息列表
     *
     * @param businessAnnualInfoDTO 工商年检信息dto
     * @return class BusinessAnnualInfoVO
     * @des 获取所有工商年检信息
     * @version v1
     */
    @GetMapping("v1/listBusinessAnnualInfo")
    public Result findListBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO, BindingResult bindingResult) throws ActException {
        try {
            List<BusinessAnnualInfoVO> businessAnnualInfoVOS = BeanTransform.copyProperties
                    (businessAnnualInfoAPI.findListBusinessAnnualInfo(businessAnnualInfoDTO),BusinessAnnualInfoVO.class);
            return ActResult.initialize(businessAnnualInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工商年检信息
     *
     * @param businessAnnualInfoTO 工商年检信息数据to
     * @return class BusinessAnnualInfoVO
     * @des 添加工商年检信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessAnnualInfoBO businessAnnualInfoBO = businessAnnualInfoAPI.insertBusinessAnnualInfo(businessAnnualInfoTO);
            return ActResult.initialize(businessAnnualInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工商年检信息
     *
     * @param businessAnnualInfoTO 工商年检信息数据to
     * @return class BusinessAnnualInfoVO
     * @des 编辑工商年检信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws ActException {
        try {
            BusinessAnnualInfoBO businessAnnualInfoBO = businessAnnualInfoAPI.editBusinessAnnualInfo(businessAnnualInfoTO);
            return ActResult.initialize(businessAnnualInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工商年检信息
     *
     * @param id 用户id
     * @des 根据用户id删除工商年检信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeBusinessAnnualInfo(@PathVariable String id) throws ActException {
        try {
            businessAnnualInfoAPI.removeBusinessAnnualInfo(id);
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
            businessAnnualInfoAPI.upload();
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
            businessAnnualInfoAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}