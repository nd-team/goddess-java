package com.bjike.goddess.staffing.action.staffing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.staffing.api.ExpendPlanAPI;
import com.bjike.goddess.staffing.bo.ExpendPlanBO;
import com.bjike.goddess.staffing.dto.ExpendPlanDTO;
import com.bjike.goddess.staffing.to.ExpendPlanTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.vo.ExpendPlanVO;
import com.bjike.goddess.staffing.vo.FieldVO;
import com.bjike.goddess.staffing.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 人工成本计划
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("expendplan")
public class ExpendPlanAction {
    @Autowired
    private ExpendPlanAPI expendPlanAPI;
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = expendPlanAPI.sonPermission();
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

            Boolean isHasPermission = expendPlanAPI.guidePermission(guidePermissionTO);
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
     * 列表详细字段
     *
     * @return class FieldVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listField")
    public Result listField() throws ActException {
        try {
            List<FieldVO> fieldVOs = new ArrayList<>();
            List<HierarchyBO> hierarchyBOs = hierarchyAPI.findStatus();
            int a = 0;
            FieldVO fieldVO1 = new FieldVO();
            List<FieldVO> fieldVOs1 = fieldVO1.getFieldVOs();
            fieldVO1.setTitle("总经办");
            FieldVO vo11 = new FieldVO();
            vo11.setTitle("计划人数");
            vo11.setTitleIndex(a);
            fieldVOs1.add(vo11);
            a++;
            FieldVO vo22 = new FieldVO();
            vo22.setTitle("计划成本");
            vo22.setTitleIndex(a);
            fieldVOs1.add(vo22);
            a++;
            FieldVO vo33 = new FieldVO();
            vo33.setTitle("现有总人数");
            vo33.setTitleIndex(a);
            fieldVOs1.add(vo33);
            a++;
            FieldVO vo44 = new FieldVO();
            vo44.setTitle("现有总人工成本");
            vo44.setTitleIndex(a);
            fieldVOs1.add(vo44);
            a++;
            fieldVOs.add(fieldVO1);
            for (HierarchyBO h : hierarchyBOs) {
                List<DepartmentDetailBO> detailBOs = departmentDetailAPI.findByHierarchy(h.getId());
                FieldVO fieldVO = new FieldVO();
                fieldVO.setTitle(h.getHierarchy());
                List<FieldVO> vos = fieldVO.getFieldVOs();
                for (DepartmentDetailBO detailBO : detailBOs) {
                    FieldVO vo = new FieldVO();
                    vo.setTitle(detailBO.getDepartment());
                    vos.add(vo);
                    List<FieldVO> vos1 = vo.getFieldVOs();
                    FieldVO vo1 = new FieldVO();
                    vo1.setTitle("计划人数");
                    vo1.setTitleIndex(a);
                    vos1.add(vo1);
                    a++;
                    FieldVO vo2 = new FieldVO();
                    vo2.setTitle("计划成本");
                    vo2.setTitleIndex(a);
                    vos1.add(vo2);
                    a++;
                    FieldVO vo3 = new FieldVO();
                    vo3.setTitle("现有总人数");
                    vo3.setTitleIndex(a);
                    vos1.add(vo3);
                    a++;
                    FieldVO vo4 = new FieldVO();
                    vo4.setTitle("现有总人工成本");
                    vo4.setTitleIndex(a);
                    vos1.add(vo4);
                    a++;
                }
                fieldVOs.add(fieldVO);
            }
            int i = 0;
            for (HierarchyBO h : hierarchyBOs) {
                FieldVO fieldVO = new FieldVO();
                fieldVO.setTitle(h.getHierarchy() + "成本占比");
                fieldVO.setListTitleIndex(i);
                fieldVOs.add(fieldVO);
                i++;
            }
            return ActResult.initialize(fieldVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 增添详细字段
     *
     * @return class FieldVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/addField")
    public Result addField() throws ActException {
        try {
            List<FieldVO> fieldVOs = new ArrayList<>();
            List<HierarchyBO> hierarchyBOs = hierarchyAPI.findStatus();
            int a = 0;
            FieldVO fieldVO2 = new FieldVO();
            fieldVO2.setTitle("总经办");
            List<FieldVO> voss=fieldVO2.getFieldVOs();
            FieldVO fieldVO1 = new FieldVO();
            List<FieldVO> fieldVOs1 = fieldVO1.getFieldVOs();
            fieldVO1.setTitle("总经办");
            FieldVO vo11 = new FieldVO();
            vo11.setTitle("计划人数");
            vo11.setTitleIndex(a);
            fieldVOs1.add(vo11);
            a++;
            FieldVO vo22 = new FieldVO();
            vo22.setTitle("计划成本");
            vo22.setTitleIndex(a);
            fieldVOs1.add(vo22);
            a++;
            FieldVO vo33 = new FieldVO();
            vo33.setTitle("现有总人数");
            vo33.setTitleIndex(a);
            fieldVOs1.add(vo33);
            a++;
            FieldVO vo44 = new FieldVO();
            vo44.setTitle("现有总人工成本");
            vo44.setTitleIndex(a);
            fieldVOs1.add(vo44);
            a++;
            voss.add(fieldVO1);
            fieldVOs.add(fieldVO2);
            for (HierarchyBO h : hierarchyBOs) {
                List<DepartmentDetailBO> detailBOs = departmentDetailAPI.findByHierarchy(h.getId());
                FieldVO fieldVO = new FieldVO();
                fieldVO.setTitle(h.getHierarchy());
                List<FieldVO> vos = fieldVO.getFieldVOs();
                for (DepartmentDetailBO detailBO : detailBOs) {
                    FieldVO vo = new FieldVO();
                    vo.setTitle(detailBO.getDepartment());
                    vos.add(vo);
                    List<FieldVO> vos1 = vo.getFieldVOs();
                    FieldVO vo1 = new FieldVO();
                    vo1.setTitle("计划人数");
                    vo1.setTitleIndex(a);
                    vos1.add(vo1);
                    a++;
                    FieldVO vo2 = new FieldVO();
                    vo2.setTitle("计划成本");
                    vo2.setTitleIndex(a);
                    vos1.add(vo2);
                    a++;
                    FieldVO vo3 = new FieldVO();
                    vo3.setTitle("现有总人数");
                    vo3.setTitleIndex(a);
                    vos1.add(vo3);
                    a++;
                    FieldVO vo4 = new FieldVO();
                    vo4.setTitle("现有总人工成本");
                    vo4.setTitleIndex(a);
                    vos1.add(vo4);
                    a++;
                }
                fieldVOs.add(fieldVO);
            }
            return ActResult.initialize(fieldVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 人工成本计划数据传输
     * @return class ExpendPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ExpendPlanDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ExpendPlanBO> list = expendPlanAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ExpendPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 人工成本计划传输对象
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ExpendPlanTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ExpendPlanBO bo = expendPlanAPI.save(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 人工成本计划id
     * @return class ExpendPlanVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/expendplan/{id}")
    public Result expendplan(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ExpendPlanBO bo = expendPlanAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ExpendPlanVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 人工成本计划传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ExpendPlanTO to, BindingResult result) throws ActException {
        try {
            expendPlanAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 人工成本计划id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            expendPlanAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 人工成本计划数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ExpendPlanDTO dto) throws ActException {
        try {
            return ActResult.initialize(expendPlanAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}