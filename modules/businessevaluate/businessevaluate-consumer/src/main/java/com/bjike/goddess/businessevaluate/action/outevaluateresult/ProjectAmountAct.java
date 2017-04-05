package com.bjike.goddess.businessevaluate.action.outevaluateresult;

import com.bjike.goddess.businessevaluate.api.ProjectAmountAPI;
import com.bjike.goddess.businessevaluate.dto.ProjectAmountDTO;
import com.bjike.goddess.businessevaluate.to.ProjectAmountTO;
import com.bjike.goddess.businessevaluate.vo.ProjectAmountInfoVO;
import com.bjike.goddess.businessevaluate.vo.ProjectAmountVO;
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
 * 项目金额
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/projectamount")
public class ProjectAmountAct {

    @Autowired
    private ProjectAmountAPI projectAmountAPI;

    /**
     * 查询项目金额信息
     *
     * @param id 项目金额信息id
     * @version v1
     */
    @GetMapping("v1/findInfoById/{id}")
    public Result findInfoById(@PathVariable String id) throws ActException {
        try {
            ProjectAmountInfoVO vo = BeanTransform.copyProperties(projectAmountAPI.findInfoById(id), ProjectAmountInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目金额
     *
     * @param to 项目金额
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ProjectAmountTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectAmountVO vo = BeanTransform.copyProperties(projectAmountAPI.addModel(to), ProjectAmountVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目金额
     *
     * @param to 项目金额
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ProjectAmountTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectAmountVO vo = BeanTransform.copyProperties(projectAmountAPI.editModel(to), ProjectAmountVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目金额
     *
     * @param id 项目金额ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectAmountAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目金额
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ProjectAmountDTO dto) throws ActException {
        try {
            List<ProjectAmountVO> voList = BeanTransform.copyProperties(projectAmountAPI.pageList(dto), ProjectAmountVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}