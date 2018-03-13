package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.PunchSonAPI;
import com.bjike.goddess.attendance.bo.CaseCountBO;
import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchPhoneBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.excel.PunchImportExcel;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.attendance.vo.CaseCountVO;
import com.bjike.goddess.attendance.vo.PunchPhoneVO;
import com.bjike.goddess.attendance.vo.PunchSonVO;
import com.bjike.goddess.attendance.vo.PunchVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.common.utils.token.IpUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.CheckMobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * 打卡
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:21 ]
 * @Description: [ 打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("punch")
public class PunchAction extends BaseFileAction {
    @Autowired
    private PunchSonAPI punchSonAPI;
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

            Boolean isHasPermission = punchSonAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto dto
     * @return class PunchPhoneVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PunchDTO dto, HttpServletRequest request) throws ActException {
        try {
            if (CheckMobile.check(request)) {   //移动端列表
                dto.setStartTime(DateUtil.dateToString(LocalDate.now()));
                dto.setEndTime(DateUtil.dateToString(LocalDate.now()));
                List<PunchPhoneBO> list = punchSonAPI.phoneList(dto);
                return ActResult.initialize(BeanTransform.copyProperties(list, PunchPhoneVO.class, request));
            } else {
                List<PunchBO> list = punchSonAPI.list(dto);
                return ActResult.initialize(BeanTransform.copyProperties(list, PunchVO.class, request));
            }
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看打卡范围
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/range")
    public Result range(@Validated(PunchDTO.RANGE.class) PunchDTO dto, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(punchSonAPI.string(dto.getLongitude(), dto.getLatitude(), dto.getArea()));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端打卡
     *
     * @param to to
     * @return class PunchSonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/phone")
    public Result phone(@Validated(PunchSonTO.PHONE.class) PunchSonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            to.setPunchSource(PunchSource.MOBILE);
            PunchSonBO bo = punchSonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PunchSonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * pc端打卡
     *
     * @param to to
     * @return class PunchSonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/pc")
    public Result pc(@Validated(PunchSonTO.PC.class) PunchSonTO to, BindingResult result1, HttpServletRequest request) throws ActException {
        try {

            String ip = IpUtil.getIp(request);
            String address = IpUtil.getAddress(ip);
            to.setArea(address);
            to.setPunchSource(PunchSource.PC);
            PunchSonBO bo = punchSonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, PunchSonVO.class, request));
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PunchDTO dto) throws ActException {
        try {
            return ActResult.initialize(punchSonAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 考勤情况汇总
     *
     * @param dto dto
     * @return class CaseCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/case/count")
    public Result caseCount(@Validated(PunchDTO.COUNT.class) PunchDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<CaseCountBO> userBOS = punchSonAPI.caseCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, CaseCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(HttpServletResponse response, PunchDTO dto) throws ActException {
        try {
            String fileName = "考勤情况.xlsx";
            super.writeOutFile(response, punchSonAPI.exportExcel(dto), fileName);
            return ActResult.initialize("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导出导入的excel模板
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/templateExcel")
    public Result templateExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "考勤情况excel模板下载.xlsx";
            super.writeOutFile(response, punchSonAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导入
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<PunchImportExcel> tos = ExcelUtil.excelToClazz(is, PunchImportExcel.class, excel);
            punchSonAPI.upload(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 当前用户迟到总条数
     *
     * @version v1
     */
    @GetMapping("v1/lateCount")
    public Result currentUserLateCount() throws ActException {
        try {
            return ActResult.initialize(punchSonAPI.currentUserLateCount());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 当前用户当天是否有打卡
     *
     * @version v1
     */
    @GetMapping("v1/isPunch")
    public Result isPunch(@Validated(PunchSonTO.ISPUNCH.class) PunchSonTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(punchSonAPI.isPunch(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据时间获取当天全部数据
     *
     * @param date date
     * @return class PunchSonVO
     * @version v1
     */
    @GetMapping("v1/getPunchSon")
    public Result getPunchSon(String date) throws ActException {
        try {
            List<PunchSonBO> punchSonBOS = punchSonAPI.getPunchSon(date);
            List<PunchSonVO> punchSonVOS = BeanTransform.copyProperties(punchSonBOS, PunchSonVO.class);
            return ActResult.initialize(punchSonVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}