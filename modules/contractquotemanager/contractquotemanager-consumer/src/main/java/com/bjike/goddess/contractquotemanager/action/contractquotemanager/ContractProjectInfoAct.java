package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.businessproject.api.DispatchSheetAPI;
import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
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
import com.bjike.goddess.contractquotemanager.api.ContractProjectInfoAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;
import com.bjike.goddess.contractquotemanager.excel.ContractProjectInfoExcel;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;
import com.bjike.goddess.contractquotemanager.vo.ContractProjectInfoVO;
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
 * 合同项目基本信息(临时表存放数据商务模块获取数据)
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractprojectinfo")
public class ContractProjectInfoAct extends BaseFileAction{

    @Autowired
    private ContractProjectInfoAPI contractProjectInfoAPI;

    @Autowired
    private DispatchSheetAPI dispatchSheetAPI;

    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = contractProjectInfoAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据id查询合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contractprojectinfo/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ContractProjectInfoBO bo = contractProjectInfoAPI.findById(id);
            ContractProjectInfoVO vo = BeanTransform.copyProperties(bo, ContractProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 合同项目基本信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ContractProjectInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = contractProjectInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 合同项目基本信息dto
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ContractProjectInfoDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ContractProjectInfoBO> boList = contractProjectInfoAPI.list(dto);
            List<ContractProjectInfoVO> voList = BeanTransform.copyProperties(boList, ContractProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合同项目基本信息
     *
     * @param to 合同项目基本信息to信息
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractProjectInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ContractProjectInfoBO bo = contractProjectInfoAPI.save(to);
            ContractProjectInfoVO vo = BeanTransform.copyProperties(bo, ContractProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractProjectInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑合同项目基本信息
     *
     * @param to 合同项目基本信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ContractProjectInfoTO to, BindingResult result) throws ActException {
        try {
            contractProjectInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询所有派工单编号
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allDispatchNum")
    public Result findAllNum() throws ActException {
        try {
            List<String> allDispatchNum = new ArrayList<>();
            allDispatchNum = dispatchSheetAPI.allDispatchNum();
            return ActResult.initialize(allDispatchNum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据派工单编号查询一个派工单信息
     *
     * @param dispatchNum 合同项目基本信息唯一标识
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/dispatchprojectinfo")
    public Result dispatchprojectinfo(@RequestParam String dispatchNum,HttpServletRequest request) throws ActException {
        try {
            List<DispatchSheetBO> bos = dispatchSheetAPI.getInfoByDispatchNum(dispatchNum);
            List<ContractProjectInfoVO> vos = BeanTransform.copyProperties(bos, ContractProjectInfoVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所基本信息地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findApplyAreas")
    public Result findApplyAreas() throws ActException {
        try {
            List<String> areas = new ArrayList<>();
            areas = contractProjectInfoAPI.findAllAreas();
            return ActResult.initialize(areas);
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
            List<ContractProjectInfoExcel> tos = ExcelUtil.excelToClazz(is, ContractProjectInfoExcel.class, excel);
            List<ContractProjectInfoTO> tocs = new ArrayList<>();
            for (ContractProjectInfoExcel str : tos) {
                ContractProjectInfoTO contractProjectInfoTO = BeanTransform.copyProperties(str, ContractProjectInfoTO.class);

                tocs.add(contractProjectInfoTO);
            }
            //注意序列化
            contractProjectInfoAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 合同单价项目基本信息
     * @des 导出 合同单价
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(ContractProjectInfoDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "合同单价.xlsx";
            super.writeOutFile(response, contractProjectInfoAPI.exportExcel(dto), fileName);
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
     * @des 下载模板 合同单价项目基本信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "合同单价.xlsx";
            super.writeOutFile(response, contractProjectInfoAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}