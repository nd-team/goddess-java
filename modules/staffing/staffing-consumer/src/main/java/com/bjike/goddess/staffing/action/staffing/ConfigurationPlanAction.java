package com.bjike.goddess.staffing.action.staffing;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.HierarchyVO;
import com.bjike.goddess.staffing.api.ConfigurationPlanAPI;
import com.bjike.goddess.staffing.api.CountAPI;
import com.bjike.goddess.staffing.bo.ConfigurationPlanBO;
import com.bjike.goddess.staffing.dto.ConfigurationPlanDTO;
import com.bjike.goddess.staffing.to.ConfigurationPlanTO;
import com.bjike.goddess.staffing.to.CountTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.vo.ConfigurationPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 人数配置-计划
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:33 ]
 * @Description: [ 人数配置-计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("configurationplan")
public class ConfigurationPlanAction {
    @Autowired
    private ConfigurationPlanAPI configurationPlanAPI;
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private CountAPI countAPI;

    private static final String navigation="人数配置计划";
    private CountTO countTO=new CountTO(navigation);

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

            Boolean isHasPermission = configurationPlanAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ConfigurationPlanTO to,HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            configurationPlanAPI.save(to);
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
            countTO.setFunction("添加");
            countAPI.add(countTO);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto dto
     * @return class ConfigurationPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ConfigurationPlanDTO dto,HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<ConfigurationPlanVO> list = BeanTransform.copyProperties(
                    configurationPlanAPI.list(dto), ConfigurationPlanVO.class,request);
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
            countTO.setFunction("列表");
            countAPI.add(countTO);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 工资区间id
     * @return class ConfigurationPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/configurationplan/{id}")
    public Result configurationplan(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ConfigurationPlanBO bo = configurationPlanAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ConfigurationPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 工资区间传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ConfigurationPlanTO to, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            configurationPlanAPI.edit(to);
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
            countTO.setFunction("编辑");
            countAPI.add(countTO);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 工资区间id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id,HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            configurationPlanAPI.delete(id);
            RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
            countTO.setFunction("删除");
            countAPI.add(countTO);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 工资区间数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ConfigurationPlanDTO dto) throws ActException {
        try {
            return ActResult.initialize(configurationPlanAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有分类
     *
     * @return class HierarchyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/classify")
    public Result classify(HttpServletRequest request) throws ActException {
        try {
            List<HierarchyBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = hierarchyAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找分类对应的部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/depart/{classify}")
    public Result depart(@PathVariable String classify, HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findByHierarchy(classify);
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}