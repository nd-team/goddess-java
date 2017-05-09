package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.api.InvolvedProcessingTaskAPI;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
import com.bjike.goddess.projectissuehandle.vo.InvolvedProcessingTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 参与处理人员的任务分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("involvedprocessingtask")
public class InvolvedProcessingTaskAction  {
    @Autowired
    private InvolvedProcessingTaskAPI involvedProcessingTaskAPI;

    /**
     * 参与处理人员的任务分配列表总条数
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @des 获取所有参与处理人员的任务分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws ActException {
        try {
            Long count = involvedProcessingTaskAPI.countInvolvedProcessingTask(involvedProcessingTaskDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个参与处理人员的任务分配
     *
     * @param id
     * @return class InvolvedProcessingTaskVO
     * @des 获取一个参与处理人员的任务分配
     * @version v1
     */
    @GetMapping("v1/task/{id}")
    public Result task(@PathVariable String id) throws ActException {
        try {
            InvolvedProcessingTaskBO involvedProcessingTaskBO = involvedProcessingTaskAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(involvedProcessingTaskBO, InvolvedProcessingTaskVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 参与处理人员的任务分配列表
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @return class InvolvedProcessingTaskVO
     * @des 获取所有参与处理人员的任务分配
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvolvedProcessingTaskDTO involvedProcessingTaskDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvolvedProcessingTaskVO> involvedProcessingTaskVOS = BeanTransform.copyProperties
                    (involvedProcessingTaskAPI.findListInvolvedProcessingTask(involvedProcessingTaskDTO), InvolvedProcessingTaskVO.class,request);
            return ActResult.initialize(involvedProcessingTaskVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加参与处理人员的任务分配
     *
     * @param involvedProcessingTaskTO 参与处理人员的任务分配数据to
     * @return class InvolvedProcessingTaskVO
     * @des 添加参与处理人员的任务分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvolvedProcessingTaskTO involvedProcessingTaskTO, BindingResult bindingResult) throws ActException {
        try {
            InvolvedProcessingTaskBO involvedProcessingTaskBO = involvedProcessingTaskAPI.insertInvolvedProcessingTask(involvedProcessingTaskTO);
            return ActResult.initialize(involvedProcessingTaskBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑参与处理人员的任务分配
     *
     * @param involvedProcessingTaskTO 参与处理人员的任务分配数据to
     * @return class InvolvedProcessingTaskVO
     * @des 编辑参与处理人员的任务分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editInvolvedProcessingTask(@Validated(EDIT.class) InvolvedProcessingTaskTO involvedProcessingTaskTO,BindingResult bindingResult) throws ActException {
        try {
            InvolvedProcessingTaskBO involvedProcessingTaskBO = involvedProcessingTaskAPI.editInvolvedProcessingTask(involvedProcessingTaskTO);
            return ActResult.initialize(involvedProcessingTaskBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除参与处理人员的任务分配
     *
     * @param id 用户id
     * @des 根据用户id删除参与处理人员的任务分配记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            involvedProcessingTaskAPI.removeInvolvedProcessingTask(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @return class InvolvedProcessingTaskVO
     * @des 搜索获取所有参与处理人员的任务分配
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(InvolvedProcessingTaskDTO involvedProcessingTaskDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvolvedProcessingTaskVO> involvedProcessingTaskVOS = BeanTransform.copyProperties
                    (involvedProcessingTaskAPI.searchInvolvedProcessingTask(involvedProcessingTaskDTO), InvolvedProcessingTaskVO.class,request);
            return ActResult.initialize(involvedProcessingTaskVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出参与处理人员的任务分配
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel(String internalProjectName, String handler) throws ActException {
        String excel = null;
        try {
            excel = involvedProcessingTaskAPI.exportExcel(internalProjectName, handler);
            return new ActResult(excel);
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
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            involvedProcessingTaskAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}