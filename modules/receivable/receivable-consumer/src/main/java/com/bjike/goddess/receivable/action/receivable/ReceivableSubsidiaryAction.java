package com.bjike.goddess.receivable.action.receivable;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
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
import com.bjike.goddess.receivable.api.ReceivableSubsidiaryAPI;
import com.bjike.goddess.receivable.bo.ReceivableSubsidiaryBO;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.enums.CompareStatus;
import com.bjike.goddess.receivable.excel.ReceivableSubsidiaryExcel;
import com.bjike.goddess.receivable.to.CollectCompareTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.ProgressTO;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import com.bjike.goddess.receivable.vo.*;
import org.springframework.beans.BeanUtils;
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
 * 回款明细
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("receivablesubsidiary")
public class ReceivableSubsidiaryAction extends BaseFileAction{
    @Autowired
    private ReceivableSubsidiaryAPI receivableSubsidiaryAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = receivableSubsidiaryAPI.guidePermission(guidePermissionTO);
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
     * 回款明细列表总条数
     *
     * @param receivableSubsidiaryDTO 回款明细dto
     * @des 获取所有回款明细总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws ActException {
        try {
            Long count = receivableSubsidiaryAPI.countReceivableSubsidiary(receivableSubsidiaryDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个回款明细
     *
     * @param id
     * @return class ReceivableSubsidiaryVO
     * @des 获取一个回款明细
     * @version v1
     */
    @GetMapping("v1/receivable/{id}")
    public Result receivable(@PathVariable String id) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(receivableSubsidiaryBO, ReceivableSubsidiaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取回款明细
     *
     * @param receivableSubsidiaryDTO 回款明细dto
     * @return class ReceivableSubsidiaryVO
     * @des 获取所有回款明细
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ReceivableSubsidiaryDTO receivableSubsidiaryDTO, HttpServletRequest request) throws ActException {
        try {
            List<ReceivableSubsidiaryBO> bo = receivableSubsidiaryAPI.findListReceivableSubsidiary(receivableSubsidiaryDTO);
            List<ReceivableSubsidiaryVO> receivableSubsidiaryVOS = BeanTransform.copyProperties
                    (bo, ReceivableSubsidiaryVO.class, request);

            for (int i = 0; i < bo.size(); i++) {
                ReceivableSubsidiaryBO temp = bo.get(i);
                ContractorVO cvo = BeanTransform.copyProperties(temp.getContractorBO(), ContractorVO.class);
                receivableSubsidiaryVOS.get(i).setContractorVO(cvo);
            }

            return ActResult.initialize(receivableSubsidiaryVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据录入回款明细
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @return class ReceivableSubsidiaryVO
     * @des 添加回款明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ReceivableSubsidiaryTO.TestAdd.class) ReceivableSubsidiaryTO receivableSubsidiaryTO, BindingResult bindingResult) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.insertReceivableSubsidiary(receivableSubsidiaryTO);
            return ActResult.initialize(BeanTransform.copyProperties(receivableSubsidiaryBO,ReceivableSubsidiaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑回款明细
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @return class ReceivableSubsidiaryVO
     * @des 编辑回款明细
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(ReceivableSubsidiaryTO.TestEdit.class) ReceivableSubsidiaryTO receivableSubsidiaryTO, BindingResult bindingResult) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.editReceivableSubsidiary(receivableSubsidiaryTO);
            return ActResult.initialize(BeanTransform.copyProperties(receivableSubsidiaryBO,ReceivableSubsidiaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除回款明细
     *
     * @param id 用户id
     * @des 根据用户id删除回款明细记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            receivableSubsidiaryAPI.removeReceivableSubsidiary(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 签字审批时间
     *
     * @param auditTime
     * @version v1
     */
    @GetMapping("v1/auditTime")
    public Result auditTime(String auditTime) throws ActException {
        try {
            List<String> audit = receivableSubsidiaryAPI.auditTime(auditTime);
            return ActResult.initialize(audit);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * ERP结算审批时间
     *
     * @param countTime
     * @version v1
     */
    @GetMapping("v1/countTime")
    public Result countTime(String countTime) throws ActException {
        try {
            List<String> count = receivableSubsidiaryAPI.countTime(countTime);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发票审核时间
     *
     * @param billTime
     * @version v1
     */
    @GetMapping("v1/billTime")
    public Result billTime(String billTime) throws ActException {
        try {
            List<String> bill = receivableSubsidiaryAPI.billTime(billTime);
            return ActResult.initialize(bill);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预计支付时间
     *
     * @param planTime
     * @version v1
     */
    @GetMapping("v1/planTime")
    public Result planTime(String planTime) throws ActException {
        try {
            String plan = receivableSubsidiaryAPI.planTime(planTime);
            return ActResult.initialize(plan);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 时间
     *
     * @param receivableSubsidiaryTO 回款明细数据to
     * @return class ReceivableSubsidiaryVO
     * @des 时间
     * @version v1
     */
    @PostMapping("v1/editTime")
    public Result editTime(ReceivableSubsidiaryTO receivableSubsidiaryTO, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.editTime(receivableSubsidiaryTO,auditStatusStr,countStatusStr,billStatusStr,planStatusStr);
            return ActResult.initialize(BeanTransform.copyProperties(receivableSubsidiaryBO,ReceivableSubsidiaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 结算进度
     *
     * @param to 结算进度数据to
     * @return class ReceivableSubsidiaryVO
     * @des 结算进度
     * @version v1
     */
    @PostMapping("v1/progress")
    public Result progress(@Validated ProgressTO to, BindingResult bindingResult) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.progress(to);
            return ActResult.initialize(receivableSubsidiaryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            List<String> areaList = receivableSubsidiaryAPI.getArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目名称
     *
     * @des 获取项目名称集合
     * @version v1
     */
    @GetMapping("v1/name")
    public Result name() throws ActException {
        try {
            List<String> innerNameList = receivableSubsidiaryAPI.getInnerName();
            return ActResult.initialize(innerNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总包单位
     *
     * @des 获取总包单位集合
     * @version v1
     */
    @GetMapping("v1/contractor")
    public Result contractor() throws ActException {
        try {
            List<String> contractorList = receivableSubsidiaryAPI.getContractor();
            return ActResult.initialize(contractorList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总地区
     *
     * @param areas 地区
     * @return class CollectAreaVO
     * @des 汇总地区
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@RequestParam String[] areas) throws ActException {
        try {
            List<CollectAreaVO> collectAreaVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectArea(areas), CollectAreaVO.class);
            return ActResult.initialize(collectAreaVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总项目名称
     *
     * @param innerNames 项目名称
     * @return class CollectProjectNameVO
     * @des 汇总项目名称
     * @version v1
     */
    @GetMapping("v1/collectName")
    public Result collectName(@RequestParam String[] innerNames) throws ActException {
        try {
            List<CollectProjectNameVO> collectProjectNameVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectInnerName(innerNames), CollectProjectNameVO.class);
            return ActResult.initialize(collectProjectNameVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总总包单位
     *
     * @param contractors 总包单位
     * @return class CollectContractorVO
     * @des 汇总总包单位
     * @version v1
     */
    @GetMapping("v1/collectContractor")
    public Result collectContractor(@RequestParam String[] contractors) throws ActException {
        try {
            List<CollectContractorVO> collectContractorVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectContractor(contractors), CollectContractorVO.class);
            return ActResult.initialize(collectContractorVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总地区详情
     *
     * @param areas 地区
     * @return class CollectAreaDetailVO
     * @des 汇总地区详情
     * @version v1
     */
    @GetMapping("v1/collectAreaDetail")
    public Result collectAreaDetail(@RequestParam String[] areas) throws ActException {
        try {
            List<CollectAreaDetailVO> collectAreaDetailVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectAreaDetail(areas), CollectAreaDetailVO.class);
            return ActResult.initialize(collectAreaDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总项目名称详情
     *
     * @param innerNames 项目名称
     * @return class CollectProjectNameDetailVO
     * @des 汇总项目名称详情
     * @version v1
     */
    @GetMapping("v1/collectNameDetail")
    public Result collectNameDetail(@RequestParam String[] innerNames) throws ActException {
        try {
            List<CollectProjectNameDetailVO> collectProjectNameDetailVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectInnerNameDetail(innerNames), CollectProjectNameDetailVO.class);
            return ActResult.initialize(collectProjectNameDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总总包单位详情
     *
     * @param contractors 总包单位
     * @return class CollectContractorDetailVO
     * @des 汇总总包单位详情
     * @version v1
     */
    @GetMapping("v1/collectContractorDetail")
    public Result collectContractorDetail(@RequestParam String[] contractors) throws ActException {
        try {
            List<CollectContractorDetailVO> collectContractorDetailVOS = BeanTransform.copyProperties(
                    receivableSubsidiaryAPI.collectContractorDetail(contractors), CollectContractorDetailVO.class);
            return ActResult.initialize(collectContractorDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总id
     *
     * @param id
     * @return class ReceivableSubsidiaryVO
     * @des 获取一个汇总id
     * @version v1
     */
    @GetMapping("v1/collect/{id}")
    public Result collect(@PathVariable String id) throws ActException {
        try {
            ReceivableSubsidiaryBO receivableSubsidiaryBO = receivableSubsidiaryAPI.collectId(id);
            ReceivableSubsidiaryVO vo = BeanTransform.copyProperties(receivableSubsidiaryBO, ReceivableSubsidiaryVO.class);
            ContractorVO cvo = new ContractorVO();
            if (null != receivableSubsidiaryBO.getContractorBO()) {
                cvo = BeanTransform.copyProperties(receivableSubsidiaryBO.getContractorBO(), ContractorVO.class);
            }

            vo.setContractorVO(cvo);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 对比汇总
     *
     * @param to 对比汇总数据to
     * @return class CollectCompareVO
     * @version v1
     */
    @GetMapping("v1/collectCompare")
    public Result collectCompare(CollectCompareTO to) throws ActException {
        try {
            List<CollectCompareVO> collectCompareVOS = BeanTransform.copyProperties(receivableSubsidiaryAPI.collectCompare(to), CollectCompareVO.class);
            return ActResult.initialize(collectCompareVOS);
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
            List<ReceivableSubsidiaryExcel> tos = ExcelUtil.excelToClazz(is, ReceivableSubsidiaryExcel.class, excel);
            List<ReceivableSubsidiaryTO> tocs = new ArrayList<>();
            for (ReceivableSubsidiaryExcel str : tos) {
                ReceivableSubsidiaryTO receivableSubsidiaryTO = BeanTransform.copyProperties(str, ReceivableSubsidiaryTO.class,"contractor","pay","frame","pact","flow");
//                if(null != str.getContractor()) {
//                  receivableSubsidiaryTO.setContractorName(str.getContractor());
//                }
                if(str.getPay().equals("是")){
                    receivableSubsidiaryTO.setPay(true);
                }else{
                    receivableSubsidiaryTO.setPay(false);
                }
                if(str.getFrame().equals("是")){
                    receivableSubsidiaryTO.setFrame(true);
                }else{
                    receivableSubsidiaryTO.setFrame(false);
                }
                if(str.getPact().equals("是")){
                    receivableSubsidiaryTO.setPact(true);
                }else{
                    receivableSubsidiaryTO.setPact(false);
                }
                if(str.getFlow().equals("是")){
                    receivableSubsidiaryTO.setFlow(true);
                }else{
                    receivableSubsidiaryTO.setFlow(false);
                }
                tocs.add(receivableSubsidiaryTO);
            }
            //注意序列化
            receivableSubsidiaryAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出excel
     *
     * @param dto 回款管理
     * @des 导出回款管理
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(ReceivableSubsidiaryDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "回款管理.xlsx";
            super.writeOutFile(response, receivableSubsidiaryAPI.exportExcel(dto), fileName);
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
     * @des 下载模板回款管理
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "回款管理导入模板.xlsx";
            super.writeOutFile(response, receivableSubsidiaryAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

//    @GetMapping("v1/r")
//    public Result r(@RequestParam String startTime,String endTime) throws ActException {
//        try {
//            List<ReceivableSubsidiaryVO> collectAreaVOS = BeanTransform.copyProperties(
//                    receivableSubsidiaryAPI.receivable(startTime,endTime), ReceivableSubsidiaryVO.class);
//            return ActResult.initialize(collectAreaVOS);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}