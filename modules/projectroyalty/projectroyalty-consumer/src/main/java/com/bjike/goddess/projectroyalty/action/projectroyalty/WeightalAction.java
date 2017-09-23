package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.projectroyalty.api.WeightalAPI;
import com.bjike.goddess.projectroyalty.dto.WeightalDTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalTO;
import com.bjike.goddess.projectroyalty.vo.WeightalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("weightal")
public class WeightalAction {
//    @Autowired
//    private WeightalAPI weightalAPI;
//    @Autowired
//    private DepartmentDetailAPI departmentDetailAPI;
//    @Autowired
//    private ModuleAPI moduleAPI;
//    @Autowired
//    private SiginManageAPI siginManageAPI;
//
//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//
//            Boolean isHasPermission = weightalAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 保存项目提成权重分配表
//     *
//     * @param to 项目提成权重分配传输对象
//     * @version v1
//     */
//    @PostMapping("v1/save")
//    public Result save(@Validated(ADD.class) WeightalTO to, BindingResult result) throws ActException {
//        try {
//            weightalAPI.save(to);
//            return ActResult.initialize("SAVE SUCCESS");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 修改
//     *
//     * @param to 项目提成权重分配表传输对象
//     * @version v1
//     */
//    @PutMapping("v1/update/{id}")
//    public Result update(@Validated(EDIT.class) WeightalTO to, BindingResult result) throws ActException {
//        try {
//            weightalAPI.update(to);
//            return ActResult.initialize("UPDATE SUCCESS");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除
//     *
//     * @param id 项目提成权重分配数据id
//     * @version v1
//     */
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            weightalAPI.delete(id);
//            return ActResult.initialize("DELETE SUCCESS");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据id获取业务提成权重分配数据
//     *
//     * @param id 项目提成权重分配数据id
//     * @return class WeightalVO
//     * @version v1
//     */
//    @GetMapping("v1/findById/{id}")
//    public Result getById(@PathVariable String id) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(weightalAPI.getById(id), WeightalVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 项目提成权重分配表列表
//     *
//     * @param dto 项目提成权重分配数据传输对象
//     * @return class WeightalVO
//     * @version v1
//     */
//    @GetMapping("v1/maps")
//    public Result maps(WeightalDTO dto, HttpServletRequest request) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(weightalAPI.maps(dto), WeightalVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 项目提成权重分配合并列表
//     *
//     * @param dto 项目提成权重分配数据传输对象
//     * @return class WeightalListBO
//     * @version v1
//     */
//    @GetMapping("v1/list")
//    public Result list(WeightalDTO dto) throws ActException {
//        try {
//            return ActResult.initialize(weightalAPI.list(dto));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 项目提成权重分配表总条数
//     *
//     * @version v1
//     */
//    @GetMapping("v1/getTotal")
//    public Result getTotal(WeightalDTO dto) throws ActException {
//        try {
//            return ActResult.initialize(weightalAPI.getTotal(dto));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 比例调整
//     *
//     * @version v1
//     */
//    @PutMapping("v1/adjust/{id}")
//    public Result adjust(@Validated(EDIT.class) WeightalAdjustTO to, BindingResult result) throws ActException {
//        try {
//            weightalAPI.adjust(to);
//            return ActResult.initialize("ADJUST SUCCESS");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
////    /**
////     * 地区
////     *
////     * @version v1
////     */
////    @GetMapping("v1/area/list")
////    public Result listArea() throws ActException {
////        try {
////            if (moduleAPI.isCheck("organize")) {
////                List<AreaBO> list = departmentDetailAPI.findArea();
////                return ActResult.initialize(list);
////            }
////            return ActResult.initialize(null);
////        } catch (SerException e) {
////            throw new ActException(e.getMessage());
////        }
////    }
////
////    /**
////     * 项目组
////     *
////     * @version v1
////     */
////    @GetMapping("v1/getDepartment")
////    public Result getDepartment() throws ActException {
////        try {
////            if (moduleAPI.isCheck("organize")) {
////                List<OpinionBO> list = departmentDetailAPI.findThawOpinion();
////                return ActResult.initialize(list);
////            }
////            return ActResult.initialize(null);
////        } catch (SerException e) {
////            throw new ActException(e.getMessage());
////        }
////    }
//
//    /**
//     * 内部项目名称
//     *
//     * @version v1
//     */
//    @GetMapping("v1/find/projectName")
//    public Result findProjectName() throws ActException {
//        try {
//            if (moduleAPI.isCheck("businessproject")) {
//                List<String> projectNames = siginManageAPI.listInnerProject();
//                return ActResult.initialize(projectNames);
//            }
//            return ActResult.initialize(null);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据内部项目名称获取地区部门立项时间是否立项
//     *
//     * @return class WeightalVO
//     * @version v1
//     */
//    @GetMapping("v1/findByProject")
//    public Result findByProject(String projectName) throws ActException {
//        try {
//            SiginManageBO siginManageBO = siginManageAPI.findByProject(projectName);
//            if (null != siginManageBO) {
//                WeightalVO weightalVO = new WeightalVO();
//                weightalVO.setArea(siginManageBO.getArea());
//                weightalVO.setDepartment(siginManageBO.getProjectGroup());
//                weightalVO.setBuildTime(siginManageBO.getCreateTime());
//                if ("已立项".equals(siginManageBO.getMakeProject())) {
//                    weightalVO.setBuild(true);
//                } else {
//                    weightalVO.setBuild(false);
//                }
//                return ActResult.initialize(weightalVO);
//            }
//            return ActResult.initialize(null);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 根据项目名称获取是否已完工
//     *
//     * @version v1
//     */
//    @GetMapping("v1/findCompleteStatus")
//    public Result findCompleteStatus(String projectName) throws ActException {
//        try {
//            return ActResult.initialize(siginManageAPI.findCompleteStatus(projectName));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获取方案
//     */
//    @GetMapping("v1/findProgram")
//    public Result findProgram() throws ActException {
//        try {
//            return ActResult.initialize(weightalAPI.findProgram());
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


}