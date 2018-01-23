package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.FinanceAttendanceAPI;
import com.bjike.goddess.attendance.bo.FinanceAttendanceBO;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.to.FinanceAttendanceTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.vo.FinanceAttendanceVO;
import com.bjike.goddess.attendance.vo.FinanceCountFieldVO;
import com.bjike.goddess.attendance.vo.FinanceCountVO;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.entity.GET;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * 财务出勤表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("financeattendance")
public class FinanceAttendanceAction extends BaseFileAction {
    @Autowired
    private FinanceAttendanceAPI financeAttendanceAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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

            Boolean isHasPermission = financeAttendanceAPI.guidePermission(guidePermissionTO);
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
     * 财务出勤表
     *
     * @return class FinanceAttendanceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FinanceAttendanceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FinanceAttendanceBO> list = financeAttendanceAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FinanceAttendanceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请反馈审批
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/apply")
    public Result apply(@Validated(EDIT.class) FinanceAttendanceTO to, BindingResult result) throws ActException {
        try {
            financeAttendanceAPI.apply(to);
            return new ActResult("申请成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核列表
     *
     * @param dto dto
     * @return class FinanceAttendanceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/aduit/list")
    public Result aduitList(FinanceAttendanceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FinanceAttendanceBO> list = financeAttendanceAPI.aduitList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FinanceAttendanceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class FinanceAttendanceVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result one(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FinanceAttendanceBO list = financeAttendanceAPI.one(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, FinanceAttendanceVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核列表总条数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/aduit/list/num")
    public Result aduitListNum(FinanceAttendanceDTO dto) throws ActException {
        try {
            return ActResult.initialize(financeAttendanceAPI.aduitListNum(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/audit")
    public Result audit(@Validated(FinanceAttendanceTO.ADUIT.class) FinanceAttendanceTO to, BindingResult result) throws ActException {
        try {
            financeAttendanceAPI.audit(to);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总详细字段信息
     *
     * @param dto dto
     * @return class FinanceCountFieldVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/fields")
    public Result fields(FinanceAttendanceDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(financeAttendanceAPI.fields(dto), FinanceCountFieldVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 财务出勤表汇总
     *
     * @param dto dto
     * @return class FinanceCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/finance/count")
    public Result financeCount(FinanceAttendanceDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(financeAttendanceAPI.financeCount(dto), FinanceCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找负责人
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/charge")
    public Result findCharge() throws ActException {
        try {
            return ActResult.initialize(positionUserDetailAPI.findCharge());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找项目经理
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/project/manager")
    public Result projectManager() throws ActException {
        try {
            return ActResult.initialize(positionUserDetailAPI.projectManager());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.findUserListInOrgan(), UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("v1/vacateDay")
    public Result vacateDay(String name,String date) throws ActException {
        try {
            return ActResult.initialize(financeAttendanceAPI.vacateDay(name, date));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel导出
     *
     * @param dto
     * @return {name:'data',type:'byte',defaultValue:'',description:'文件流.'}
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/excel/export")
    public Result excelExport(FinanceAttendanceDTO dto, HttpServletResponse response) throws ActException {
        try {
            try {
                byte[] bytes = financeAttendanceAPI.excelExport(dto);
                writeOutFile(response, bytes, "财务出勤表" + DateUtil.dateToString(LocalDate.now()) + ".xlsx");
            } catch (Exception e) {
            }
            return ActResult.initialize(financeAttendanceAPI.excelExport(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}