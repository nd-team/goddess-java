package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.*;
import com.bjike.goddess.organize.bo.ModuleTypeBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.WorkRangeBO;
import com.bjike.goddess.organize.entity.ModuleType;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.entity.WorkRange;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.organize.vo.WorkRangeVO;
import com.bjike.goddess.workjoin.api.WorkJoinAPI;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.entity.WorkJoin;
import com.bjike.goddess.workjoin.excel.SonPermissionObject;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import com.bjike.goddess.workjoin.vo.WorkJoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("workjoin")
public class WorkJoinAction {
    @Autowired
    private WorkJoinAPI workJoinAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private WorkRangeAPI workRangeAPI;
    @Autowired
    private PositionInstructionAPI positionInstructionAPI;
    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = workJoinAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = workJoinAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 工作交接列表总条数
     *
     * @param workJoinDTO 工作交接dto
     * @des 获取所有工作交接总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WorkJoinDTO workJoinDTO) throws ActException {
        try {
            Long count = workJoinAPI.countWorkJoin(workJoinDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个工作交接
     *
     * @param id
     * @return class WorkJoinVO
     * @des 获取一个工作交接
     * @version v1
     */
    @GetMapping("v1/work/{id}")
    public Result work(@PathVariable String id) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(workJoinBO, WorkJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 工作交接列表
     *
     * @param workJoinDTO 工作交接dto
     * @return class WorkJoinVO
     * @des 获取所有工作交接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WorkJoinDTO workJoinDTO, HttpServletRequest request) throws ActException {
        try {
            List<WorkJoinVO> workJoinVOS = BeanTransform.copyProperties
                    (workJoinAPI.findListWorkJoin(workJoinDTO), WorkJoinVO.class, request);
            return ActResult.initialize(workJoinVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作交接
     *
     * @param workJoinTO 工作交接数据to
     * @return class WorkJoinVO
     * @des 添加工作交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) WorkJoinTO workJoinTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.insertWorkJoin(workJoinTO);
            return ActResult.initialize(workJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作交接
     *
     * @param workJoinTO 工作交接数据to
     * @return class WorkJoinVO
     * @des 编辑工作交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) WorkJoinTO workJoinTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.editWorkJoin(workJoinTO);
            return ActResult.initialize(workJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工作交接
     *
     * @param id 用户id
     * @des 根据用户id删除工作交接记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            workJoinAPI.removeWorkJoin(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取工作编号
     *
     * @des 获取工作编号
     * @version v1
     */
    @GetMapping("v1/getNum")
    public Result getNum() throws ActException {
        try {
            List<String> list = workJoinAPI.getNum();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 负责人审核
     *
     * @param to 工作交接数据to
     * @return class WorkJoinVO
     * @des 负责人审核
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/audit")
    public Result audit(@Validated(WorkJoinTO.audit.class) WorkJoinTO to, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.audit(to);
            return ActResult.initialize(BeanTransform.copyProperties(workJoinBO, WorkJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的岗位详细
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/position")
    public Result position(HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的模块类型数据
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/module")
    public Result module(HttpServletRequest request) throws ActException {
        try {
            List<ModuleTypeBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = moduleTypeAPI.findByStatus(Status.THAW);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的工作范围类型数据
     *
     * @return class WorkRangeVO
     * @version v1
     */
    @GetMapping("v1/workRange")
    public Result workRange(HttpServletRequest request) throws ActException {
        try {
            List<WorkRangeBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = workRangeAPI.findByStatus(Status.THAW);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, WorkRangeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的工作目的类型数据
     *
     * @return class String
     * @version v1
     */
    @GetMapping("v1/workPurpose")
    public Result workPurpose() throws ActException {
        try {
            List<String> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = positionInstructionAPI.getOutCome();
            }
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}