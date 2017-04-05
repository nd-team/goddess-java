package com.bjike.goddess.businessevaluate.action.interiorevaluate;

import com.bjike.goddess.businessevaluate.api.interiorevaluate.BusinessPrincipalEvaluateAPI;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.BusinessPrincipalEvaluateTO;
import com.bjike.goddess.businessevaluate.vo.interiorevaluate.BusinessPrincipalEvaluateVO;
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
 * 商务负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/businessprincipalevaluate")
public class BusinessPrincipalEvaluateAct {

    @Autowired
    private BusinessPrincipalEvaluateAPI principalEvaluateAPI;

    /**
     * 新增一线体系评价
     *
     * @param to 一线体系评价
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(BusinessPrincipalEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessPrincipalEvaluateVO vo = BeanTransform.copyProperties(principalEvaluateAPI.addModel(to), BusinessPrincipalEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑一线体系评价
     *
     * @param to 一线体系评价
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(BusinessPrincipalEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessPrincipalEvaluateVO vo = BeanTransform.copyProperties(principalEvaluateAPI.editModel(to), BusinessPrincipalEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除一线体系评价
     *
     * @param id 一线体系评价ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            principalEvaluateAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询一线体系评价
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(BusinessPrincipalEvaluateDTO dto) throws ActException {
        try {
            List<BusinessPrincipalEvaluateVO> voList = BeanTransform.copyProperties(principalEvaluateAPI.pageList(dto), BusinessPrincipalEvaluateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}