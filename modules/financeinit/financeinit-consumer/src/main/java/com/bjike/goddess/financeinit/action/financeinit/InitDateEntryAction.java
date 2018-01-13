package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.InitDateEntryAPI;
import com.bjike.goddess.financeinit.bo.InitDateEntryBO;
import com.bjike.goddess.financeinit.dto.InitDateEntryDTO;
import com.bjike.goddess.financeinit.entity.InitDateEntry;
import com.bjike.goddess.financeinit.excel.InitDateEntryImport;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.InitDateEntryTO;
import com.bjike.goddess.financeinit.vo.InitDateEntryVO;
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
 * 初始化数据录入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("initdateentry")
public class InitDateEntryAction extends BaseFileAction{
    @Autowired
    private InitDateEntryAPI initDateEntryAPI;
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

            Boolean isHasPermission = initDateEntryAPI.guidePermission(guidePermissionTO);
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
     * @param initDateEntryDTO 初始化数据dto
     * @des 获取所有初始化数据总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InitDateEntryDTO initDateEntryDTO) throws ActException {
        try {
            Long count = initDateEntryAPI.countInit(initDateEntryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个初始化数据
     *
     * @param id 初始化数据id
     * @return class InitDateEntryVO
     * @des 根据id获取初始化数据
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            InitDateEntryVO initDateEntryVO = BeanTransform.copyProperties(
                    initDateEntryAPI.getOneById(id), InitDateEntryVO.class);
            return ActResult.initialize(initDateEntryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 初始化数据列表
     *
     * @param initDateEntryDTO 初始化数据dto
     * @return class InitDateEntryVO
     * @des 获取所有初始化数据
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(InitDateEntryDTO initDateEntryDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<InitDateEntryVO> initDateEntryVOS = BeanTransform.copyProperties(
                    initDateEntryAPI.listInit(initDateEntryDTO), InitDateEntryVO.class, request);
            return ActResult.initialize(initDateEntryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑初始化数据
     *
     * @param initDateEntryTO 初始化数据数据bo
     * @return class InitDateEntryVO
     * @des 编辑初始化数据
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) InitDateEntryTO initDateEntryTO, BindingResult bindingResult) throws ActException {
        try {
            InitDateEntryBO initDateEntryBO = initDateEntryAPI.editInit(initDateEntryTO);
            return ActResult.initialize(BeanTransform.copyProperties(initDateEntryBO, InitDateEntryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 试算平衡
     *
     * @des 试算平衡
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/trialBalance")
    public Result trialBalance() throws ActException {
        try {
            String balances = initDateEntryAPI.trialBalance();
            return ActResult.initialize(balances);
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
            List<InitDateEntryImport> tos = ExcelUtil.excelToClazz(is, InitDateEntryImport.class, excel);
            List<InitDateEntryTO> tocs = new ArrayList<>();
            for (InitDateEntryImport str : tos) {
                InitDateEntryTO accountanCourseTO = BeanTransform.copyProperties(str, InitDateEntryTO.class);
                tocs.add(accountanCourseTO);
            }
            //注意序列化
            initDateEntryAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出财务初始化
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "财务初始化.xlsx";
            super.writeOutFile(response, initDateEntryAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}