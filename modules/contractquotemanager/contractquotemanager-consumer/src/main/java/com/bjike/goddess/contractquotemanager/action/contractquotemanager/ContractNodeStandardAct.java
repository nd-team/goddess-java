package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

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
import com.bjike.goddess.contractquotemanager.api.ContractNodeStandardAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.excel.ContractNodeStandardImpor;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;
import com.bjike.goddess.contractquotemanager.vo.ColationVO;
import com.bjike.goddess.contractquotemanager.vo.ContractNodeStandardVO;
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
 * 合同节点标准信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.722 ]
 * @Description: [ 合同节点标准信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractnodestandard")
public class ContractNodeStandardAct extends BaseFileAction{

    @Autowired
    private ContractNodeStandardAPI contractNodeStandardAPI;

    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = contractNodeStandardAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @return class ContractNodeStandardVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contractnodestandard/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ContractNodeStandardBO bo = contractNodeStandardAPI.findById(id);
            ContractNodeStandardVO vo = BeanTransform.copyProperties(bo, ContractNodeStandardVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 合同节点标准信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ContractNodeStandardDTO dto, BindingResult result) throws ActException {
        try {
            Long count = contractNodeStandardAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 合同节点标准信息dto
     * @return class ContractNodeStandardVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ContractNodeStandardDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ContractNodeStandardBO> boList = contractNodeStandardAPI.list(dto);
            List<ContractNodeStandardVO> voList = BeanTransform.copyProperties(boList, ContractNodeStandardVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合同节点标准信息
     *
     * @param to 合同节点标准信息to信息
     * @return class ContractNodeStandardVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractNodeStandardTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ContractNodeStandardBO bo = contractNodeStandardAPI.save(to);
            ContractNodeStandardVO vo = BeanTransform.copyProperties(bo, ContractNodeStandardVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractNodeStandardAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑合同节点标准信息
     *
     * @param to 合同节点标准信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ContractNodeStandardTO to, BindingResult result) throws ActException {
        try {
            contractNodeStandardAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合同节点标准信息
     *
     * @param to 合同节点标准信息bo
     * @throws ActException
     * @des class ContractNodeStandardVO 根据日期(date)、地区(area)、项目组(project)和节点(node)汇总
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/collect")
    public Result collect(ContractNodeStandardTO to, HttpServletRequest request) throws ActException {
        try {

            List<ContractNodeStandardVO> contractNodeStandardVOs = BeanTransform.copyProperties(
                    contractNodeStandardAPI.collect(to),
                    ContractNodeStandardVO.class, request);
            return ActResult.initialize(contractNodeStandardVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @throws ActException
     * @des 根据地区(area)、项目组(project)搜索
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/search")
    public Result search(ContractNodeStandardTO to, HttpServletRequest request) throws ActException {
        try {
            List<ContractNodeStandardVO> contractNodeStandardVOs = BeanTransform.copyProperties(
                    contractNodeStandardAPI.searchContractNodeStandard(to),
                    ContractNodeStandardVO.class, request);
            return ActResult.initialize(contractNodeStandardVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取类别
     *
     * @return class ColationVO
     * @version v1
     */
    @GetMapping("v1/findType")
    public Result findType() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNodeStandardAPI.findType(), ColationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取节点
     *
     * @return class ColationVO
     * @version v1
     */
    @GetMapping("v1/findNode")
    public Result findNode() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contractNodeStandardAPI.findNode(), ColationVO.class));
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
            List<ContractNodeStandardImpor> tos = ExcelUtil.excelToClazz(is, ContractNodeStandardImpor.class, excel);
            List<ContractNodeStandardTO> tocs = new ArrayList<>();
            for (ContractNodeStandardImpor str : tos) {
                ContractNodeStandardTO contractNodeStandardTO = BeanTransform.copyProperties(str, ContractNodeStandardTO.class);
                contractNodeStandardTO.setDate(str.getDate().toString());
                tocs.add(contractNodeStandardTO);
            }
            //注意序列化
            contractNodeStandardAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出 合同单价
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "合同节点标准信息.xlsx";
            super.writeOutFile(response, contractNodeStandardAPI.exportExcel(), fileName);
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
     * @des 下载模板 合同节点标准信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "合同节点标准信息模板.xlsx";
            super.writeOutFile(response, contractNodeStandardAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}