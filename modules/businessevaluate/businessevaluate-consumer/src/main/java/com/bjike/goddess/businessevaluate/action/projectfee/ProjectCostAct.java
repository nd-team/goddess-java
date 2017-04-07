package com.bjike.goddess.businessevaluate.action.projectfee;

import com.bjike.goddess.businessevaluate.api.ProjectCostAPI;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.to.ProjectCostTO;
import com.bjike.goddess.businessevaluate.vo.ProjectCostVO;
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
 * 项目费用
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/projectcost")
public class ProjectCostAct {

    @Autowired
    private ProjectCostAPI projectCostAPI;

    /**
     * 新增需求成本
     *
     * @param to 需求成本
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ProjectCostTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectCostVO vo = BeanTransform.copyProperties(projectCostAPI.addModel(to), ProjectCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑需求成本
     *
     * @param to 需求成本
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ProjectCostTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectCostVO vo = BeanTransform.copyProperties(projectCostAPI.editModel(to), ProjectCostVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除需求成本
     *
     * @param id 需求成本id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectCostAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result delete(ProjectCostDTO dto) throws ActException {
        try {
            List<ProjectCostVO> voList = BeanTransform.copyProperties(projectCostAPI.pageList(dto), ProjectCostVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}