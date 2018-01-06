package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.TalkDetailAPI;
import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;
import com.bjike.goddess.businessinteraction.excel.TalkDetailExcel;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.SonPermissionObject;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.businessinteraction.vo.TalkDetailVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 资料信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("talkdetail")
public class TalkDetailAction extends BaseFileAction{
    @Autowired
    private TalkDetailAPI talkDetailAPI;


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

            List<SonPermissionObject> hasPermissionList = talkDetailAPI.sonPermission();
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
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = talkDetailAPI.guidePermission(guidePermissionTO);
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
     * @param talkDetailDTO 资料信息
     * @des 获取所有资料信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TalkDetailDTO talkDetailDTO) throws ActException {
        try {
            Long count = talkDetailAPI.countInter(talkDetailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资料信息
     *
     * @param id 资料信息id
     * @return class TalkDetailVO
     * @des 根据id获取资料信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            TalkDetailVO talkDetailVO = BeanTransform.copyProperties(
                    talkDetailAPI.getOneById(id), TalkDetailVO.class);
            return ActResult.initialize(talkDetailVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资料信息列表
     *
     * @param talkDetailDTO 资料信息dto
     * @return class TalkDetailVO
     * @des 获取所有资料信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListMoneyPerpare(TalkDetailDTO talkDetailDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<TalkDetailVO> talkDetailVOS = BeanTransform.copyProperties(
                    talkDetailAPI.listIntera(talkDetailDTO), TalkDetailVO.class, request);
            return ActResult.initialize(talkDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加资料信息
     *
     * @param talkDetailTO 资料信息to
     * @return class TalkDetailVO
     * @des 添加资料信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBaseInfoManage(@Validated({ADD.class}) TalkDetailTO talkDetailTO, BindingResult result) throws ActException {
        try {
            TalkDetailBO talkDetailBO = talkDetailAPI.addIntera(talkDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(talkDetailBO, TalkDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑资料信息
     *
     * @param talkDetailTO 资料信息数据bo
     * @return class TalkDetailVO
     * @des 编辑资料信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBaseInfoManage(@Validated({EDIT.class}) TalkDetailTO talkDetailTO, BindingResult result) throws ActException {
        try {
            TalkDetailBO talkDetailBO = talkDetailAPI.editIntera(talkDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(talkDetailBO, TalkDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除资料信息
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            talkDetailAPI.deleteIntera(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<TalkDetailExcel> tos = ExcelUtil.excelToClazz(is, TalkDetailExcel.class, excel);
            List<TalkDetailTO> tocs = new ArrayList<>();
            for (TalkDetailExcel str : tos) {
                TalkDetailTO talkDetailTO = BeanTransform.copyProperties(str, TalkDetailTO.class,"cooperDate");
                talkDetailTO.setCooperDate(DateUtil.dateToString(str.getCooperDate()));
                tocs.add(talkDetailTO);
            }
            //注意序列化
            talkDetailAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出资料信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "资料信息.xlsx";
            super.writeOutFile(response, talkDetailAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * excel模板下载
     *
     * @des 下载模板资料信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "资料信息模板.xlsx";
            super.writeOutFile(response, talkDetailAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取所有的业务类型
     *
     * @des 获取所有的业务类型
     * @version v1
     */
    @GetMapping("v1/findBussType")
    public Result findBussType(HttpServletRequest request) throws ActException {
        try {
            List<String> bussType = talkDetailAPI.findBussType();
            return ActResult.initialize(bussType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}