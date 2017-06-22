package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.ProgressNodeAPI;
import com.bjike.goddess.progressmanage.api.ProjectInfoAPI;
import com.bjike.goddess.progressmanage.dto.ProgressNodeDTO;
import com.bjike.goddess.progressmanage.to.ProgressNodeTO;
import com.bjike.goddess.progressmanage.vo.ProgressNodeVO;
import com.bjike.goddess.progressmanage.vo.ProjectListForNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 进度节点
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:21 ]
 * @Description: [ 进度节点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("progressnode")
public class ProgressNodeAct {

    @Autowired
    private ProgressNodeAPI progressNodeAPI;
    @Autowired
    private ProjectInfoAPI projectInfoAPI;

    /**
     * 项目下拉列表
     *
     * @return class ProjectListForNodeVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {

        try {
            List<ProjectListForNodeVO> voList = BeanTransform.copyProperties(projectInfoAPI.projects(), ProjectListForNodeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 新增
     *
     * @param to 节点
     * @return class ProgressNodeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProgressNodeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProgressNodeVO vo = BeanTransform.copyProperties(progressNodeAPI.add(to), ProgressNodeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 节点
     * @return class ProgressNodeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProgressNodeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProgressNodeVO vo = BeanTransform.copyProperties(progressNodeAPI.edit(to), ProgressNodeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 节点id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            progressNodeAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @return class ProgressNodeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({ProgressNodeDTO.NodeValidate.class}) ProgressNodeDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProgressNodeVO> voList = BeanTransform.copyProperties(progressNodeAPI.pageList(dto), ProgressNodeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}