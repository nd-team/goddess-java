package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.ProjectContractAPI;
import com.bjike.goddess.contractware.bo.ProjectContractBO;
import com.bjike.goddess.contractware.dto.ProjectContractDTO;
import com.bjike.goddess.contractware.to.ProjectContractTO;
import com.bjike.goddess.contractware.vo.ProjectContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目合同
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectcontract")
public class ProjectContractAction {
    @Autowired
    private ProjectContractAPI projectContractAPI;

    /**
     * 项目合同列表总条数
     *
     * @param projectContractDTO 项目合同dto
     * @des 获取所有项目合同总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectContractDTO projectContractDTO) throws ActException {
        try {
            Long count = projectContractAPI.countProjectContract(projectContractDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个项目合同
     *
     * @param id
     * @return class ProjectContractVO
     * @des 获取一个项目合同
     * @version v1
     */
    @GetMapping("v1/project/{id}")
    public Result project(@PathVariable String id) throws ActException {
        try {
            ProjectContractBO projectContractBO = projectContractAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(projectContractBO, ProjectContractVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 项目合同列表
     *
     * @param projectContractDTO 项目合同dto
     * @return class ProjectContractVO
     * @des 获取所有项目合同
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectContractDTO projectContractDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProjectContractVO> projectContractVOS = BeanTransform.copyProperties
                    (projectContractAPI.findListProjectContract(projectContractDTO), ProjectContractVO.class, request);
            return ActResult.initialize(projectContractVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目合同
     *
     * @param projectContractTO 项目合同数据to
     * @return class ProjectContractVO
     * @des 添加项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ProjectContractTO projectContractTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectContractBO projectContractBO = projectContractAPI.insertProjectContract(projectContractTO);
            return ActResult.initialize(projectContractBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目合同
     *
     * @param projectContractTO 项目合同数据to
     * @return class ProjectContractVO
     * @des 编辑项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProjectContractTO projectContractTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectContractBO projectContractBO = projectContractAPI.editProjectContract(projectContractTO);
            return ActResult.initialize(projectContractBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目合同
     *
     * @param id 用户id
     * @des 根据用户id删除项目合同记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectContractAPI.removeProjectContract(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @version v1
     */


}