package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.PublicizeProgramInfoAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.PublicizeProgramInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.to.PublicizeProgramInfoTO;
import com.bjike.goddess.enterpriseculturemanage.vo.EnterpriseCultureInfoVO;
import com.bjike.goddess.enterpriseculturemanage.vo.PublicizeProgramInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 宣传方案信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 05:28 ]
 * @Description: [ 宣传方案信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("enterpriseculturemanage/publicizeprograminfo")
public class PublicizeProgramInfoAct {

    @Autowired
    private PublicizeProgramInfoAPI publicizeProgramInfoAPI;

    /**
     * 查询企业文化信息
     *
     * @version v1
     */
    @GetMapping("v1/findInfo")
    public Result findInfo() throws ActException {
        try {
            List<EnterpriseCultureInfoVO> voList = BeanTransform.copyProperties(publicizeProgramInfoAPI.findInfo(), EnterpriseCultureInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增宣传方案信息
     *
     * @param to 宣传方案信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(PublicizeProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PublicizeProgramInfoVO vo = BeanTransform.copyProperties(publicizeProgramInfoAPI.addModel(to), PublicizeProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑宣传方案信息
     *
     * @param to 宣传方案信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(PublicizeProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            PublicizeProgramInfoVO vo = BeanTransform.copyProperties(publicizeProgramInfoAPI.editModel(to), PublicizeProgramInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核宣传方案信息
     *
     * @param to 审核内容
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(PublicizeProgramInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            publicizeProgramInfoAPI.audit(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除宣传方案信息
     *
     * @param id 宣传方案信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            publicizeProgramInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 宣传方案信息分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(PublicizeProgramInfoDTO dto) throws ActException {
        try {
            List<PublicizeProgramInfoVO> voList = BeanTransform.copyProperties(publicizeProgramInfoAPI.pageList(dto), PublicizeProgramInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}