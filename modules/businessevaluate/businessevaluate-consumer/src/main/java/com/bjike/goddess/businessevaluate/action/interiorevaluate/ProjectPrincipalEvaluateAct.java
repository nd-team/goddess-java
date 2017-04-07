package com.bjike.goddess.businessevaluate.action.interiorevaluate;

import com.bjike.goddess.businessevaluate.api.interiorevaluate.ProjectPrincipalEvaluateAPI;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.ProjectPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.ProjectPrincipalEvaluateTO;
import com.bjike.goddess.businessevaluate.vo.interiorevaluate.ProjectPrincipalEvaluateVO;
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
 * 项目负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 项目负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/projectprincipalevaluate")
public class ProjectPrincipalEvaluateAct {

    @Autowired
    private ProjectPrincipalEvaluateAPI projectPrincipalEvaluateAPI;

    /**
     * 新增项目负责人评价
     *
     * @param to 项目负责人评价
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ProjectPrincipalEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectPrincipalEvaluateVO vo = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.addModel(to), ProjectPrincipalEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目负责人评价
     *
     * @param to 项目负责人评价
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ProjectPrincipalEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectPrincipalEvaluateVO vo = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.editModel(to), ProjectPrincipalEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目负责人评价
     *
     * @param id 项目负责人评价ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectPrincipalEvaluateAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目负责人评价
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ProjectPrincipalEvaluateDTO dto) throws ActException {
        try {
            List<ProjectPrincipalEvaluateVO> voList = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.pageList(dto), ProjectPrincipalEvaluateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}