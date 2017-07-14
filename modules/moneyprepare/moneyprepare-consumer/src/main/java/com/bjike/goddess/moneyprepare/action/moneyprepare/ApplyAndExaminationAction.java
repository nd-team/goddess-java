package com.bjike.goddess.moneyprepare.action.moneyprepare;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyprepare.api.ApplyAndExaminationAPI;
import com.bjike.goddess.moneyprepare.api.FundPrepareAPI;
import com.bjike.goddess.moneyprepare.bo.ApplyAndExaminationBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareApplyBO;
import com.bjike.goddess.moneyprepare.bo.ProportionBO;
import com.bjike.goddess.moneyprepare.dto.ApplyAndExaminationDTO;
import com.bjike.goddess.moneyprepare.excel.SonPermissionObject;
import com.bjike.goddess.moneyprepare.to.ApplyAndExaminationTO;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import com.bjike.goddess.moneyprepare.vo.ApplyAndExaminationVO;
import com.bjike.goddess.moneyprepare.vo.FundPrepareApplyVO;
import com.bjike.goddess.moneyprepare.vo.ProportionVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请和审批
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("applyandexamination")
public class ApplyAndExaminationAction {
    @Autowired
    private ApplyAndExaminationAPI applyAndExaminationAPI;
    @Autowired
    private FundPrepareAPI fundPrepareAPI;
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

            List<SonPermissionObject> hasPermissionList = applyAndExaminationAPI.sonPermission();
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

            Boolean isHasPermission = applyAndExaminationAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param applyAndExaminationDTO 申请和审批dto
     * @des 获取所有申请和审批总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ApplyAndExaminationDTO applyAndExaminationDTO) throws ActException {
        try {
            Long count = applyAndExaminationAPI.countApplyAndExamination(applyAndExaminationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个申请和审批
     *
     * @param id 项目申请和审批id
     * @return class ApplyAndExaminationVO
     * @des 根据id获取项目申请和审批
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ApplyAndExaminationVO projectCarryVO = BeanTransform.copyProperties(
                    applyAndExaminationAPI.getOneById(id), ApplyAndExaminationVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目申请和审批列表
     *
     * @param applyAndExaminationDTO 项目申请和审批信息dto
     * @return class ApplyAndExaminationVO
     * @des 获取所有项目申请和审批信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ApplyAndExaminationBO> list = applyAndExaminationAPI.listApplyAndExamination(applyAndExaminationDTO);
            List<ApplyAndExaminationVO> applyAndExaminationVOList = new ArrayList<>();
            list.stream().forEach(str -> {
                ApplyAndExaminationVO vo = BeanTransform.copyProperties(str, ApplyAndExaminationVO.class);
                applyAndExaminationVOList.add(vo);
            });

            return ActResult.initialize(applyAndExaminationVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目申请和审批
     *
     * @param applyAndExaminationTO 项目申请和审批基本信息数据to
     * @return class ApplyAndExaminationVO
     * @des 添加项目申请和审批
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addApplyAndExamination(@Validated(ApplyAndExaminationTO.TestAdd.class) ApplyAndExaminationTO applyAndExaminationTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyAndExaminationBO applyAndExaminationBO1 = applyAndExaminationAPI.addApplyAndExamination(applyAndExaminationTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyAndExaminationBO1, ApplyAndExaminationVO.class,false));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目申请和审批
     *
     * @param applyAndExaminationTO 项目申请和审批基本信息数据bo
     * @return class ApplyAndExaminationVO
     * @des 添加项目申请和审批
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editApplyAndExamination(@Validated(ApplyAndExaminationTO.TestAdd.class) ApplyAndExaminationTO applyAndExaminationTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyAndExaminationBO applyAndExaminationBO1 = applyAndExaminationAPI.editApplyAndExamination(applyAndExaminationTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyAndExaminationBO1, ApplyAndExaminationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目申请和审批信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteApplyAndExamination(@PathVariable String id) throws ActException {
        try {
            applyAndExaminationAPI.deleteApplyAndExamination(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param applyAndExaminationTO 资金准备申请基本信息数据bo
     * @return class ApplyAndExaminationVO
     * @des 审核资金准备申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/audit")
    public Result auditApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO, BindingResult bindingResult) throws ActException {
        try {
            ApplyAndExaminationBO applyAndExaminationBO1 = applyAndExaminationAPI.auditApplyAndExamination(applyAndExaminationTO);
            return ActResult.initialize(BeanTransform.copyProperties(applyAndExaminationBO1, ApplyAndExaminationVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看记录
     *
     * @param id 项目申请和审批id
     * @return class FundPrepareApplyVO
     * @des 根据id获取项目申请和审批表的记录
     * @version v1
     */
    @GetMapping("v1/record/{id}")
    public Result record(@PathVariable String id) throws ActException {
        try {
            List<FundPrepareApplyVO> list = BeanTransform.copyProperties(
                    applyAndExaminationAPI.record(id), FundPrepareApplyVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据资金准备ID获得项目分配详情
     *
     * @param id 查看记录返回中的FundPrepareApplyVO中的id
     * @return class ProportionVO
     * @des 根据id获取项目资金准备
     * @version v1
     */
    @GetMapping("v1/listDetail/{id}")
    public Result listDetail(@PathVariable String id) throws ActException {
        try {
            List<ProportionBO> list = BeanTransform.copyProperties(
                    fundPrepareAPI.listDetail(id), ProportionVO.class,"time");
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}