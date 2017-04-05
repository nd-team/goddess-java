package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.EnterpriseCultureInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.to.EnterpriseCultureInfoTO;
import com.bjike.goddess.enterpriseculturemanage.vo.EnterpriseCultureInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业文化信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:05 ]
 * @Description: [ 企业文化信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("enterpriseculturemanage/enterprisecultureinfo")
public class EnterpriseCultureInfoAct {

    @Autowired
    private EnterpriseCultureInfoAPI enterpriseCultureInfoAPI;

    /**
     * 新增企业文化信息
     *
     * @param to 企业文化信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(EnterpriseCultureInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EnterpriseCultureInfoVO vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.addModel(to), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑企业文化信息
     *
     * @param to 企业文化信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(EnterpriseCultureInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EnterpriseCultureInfoVO vo = BeanTransform.copyProperties(enterpriseCultureInfoAPI.editModel(to), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除企业文化信息
     *
     * @param id 企业文化信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            enterpriseCultureInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 企业文化信息分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(EnterpriseCultureInfoDTO dto) throws ActException {
        try {
            List<EnterpriseCultureInfoVO> voList = BeanTransform.copyProperties(enterpriseCultureInfoAPI.pageList(dto), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据企业文化信息id查询宣传方案
     *
     * @param id 企业文化信息id
     * @version v1
     */

    @GetMapping("v1/findPublicize")
    public Result findPublicize(String id) throws ActException {
        try {
            List<EnterpriseCultureInfoVO> voList = BeanTransform.copyProperties(enterpriseCultureInfoAPI.findPublicize(id), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据企业文化信息id查询刊物方案
     *
     * @param id 企业文化信息id
     * @version v1
     */
    @GetMapping("v1/findPeriodical")
    public Result findPeriodical(String id) throws ActException {
        try {
            List<EnterpriseCultureInfoVO> voList = BeanTransform.copyProperties(enterpriseCultureInfoAPI.findPeriodical(id), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}