package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.businessproject.bo.BusinessContractsBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.HeadersCustomAPI;
import com.bjike.goddess.projectprocing.api.NodeHeadersCustomAPI;
import com.bjike.goddess.projectprocing.api.SettleProgressManageAPI;
import com.bjike.goddess.projectprocing.api.SettleWorkProgreManageAPI;
import com.bjike.goddess.projectprocing.bo.OptionBO;
import com.bjike.goddess.projectprocing.bo.SettleProgressManageBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.HeadersCustom;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.service.HeadersCustomSer;
import com.bjike.goddess.projectprocing.service.NodeHeadersCustomSer;
import com.bjike.goddess.projectprocing.to.*;
import com.bjike.goddess.projectprocing.vo.*;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 结算进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("settleprogressmanage")
public class SettleProgressManageAction extends BaseFileAction {
    @Autowired
    private SettleProgressManageAPI settleProgressManageAPI;
    @Autowired
    private BusinessContractAPI businessContractAPI;
    @Autowired
    private SettleWorkProgreManageAPI settleWorkProgreManageAPI;
    @Autowired
    private HeadersCustomAPI headersCustomAPI;
    @Autowired
    private NodeHeadersCustomAPI nodeHeadersCustomAPI;
    @Autowired
    private FileAPI fileAPI;

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

            Boolean isHasPermission = settleWorkProgreManageAPI.guidePermission(guidePermissionTO);
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
     * 结算进度管理总条数
     *
     * @param settleProgressManageDTO 结算流程存储记录dto
     * @des 获取所有结算进度管理总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(SettleProgressManageDTO settleProgressManageDTO) throws ActException {
        try {
            Long count = settleProgressManageAPI.countManage(settleProgressManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个结算进度管理
     *
     * @param id 结算进度管理id
     * @return class SettleProgressManageVO
     * @des 根据id获取结算进度管理
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SettleProgressManageVO settleProgressManageVO = BeanTransform.copyProperties(
                    settleProgressManageAPI.getOneById(id), SettleProgressManageVO.class, true);
            return ActResult.initialize(settleProgressManageVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 结算进度管理列表
     *
     * @param settleProgressManageDTO 结算进度管理dto
     * @return class SettleProgressManageVO
     * @des 获取所有结算进度管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(SettleProgressManageDTO settleProgressManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SettleProgressManageVO> projectCarryVOList = BeanTransform.copyProperties(
                    settleProgressManageAPI.listManage(settleProgressManageDTO), SettleProgressManageVO.class, request);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加结算进度管理
     *
     * @param settleProgressManageTO 结算进度管理数据to
     * @return class SettleProgressManageVO
     * @des 添加结算进度管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) SettleProgressManageTO settleProgressManageTO, BindingResult bindingResult) throws ActException {
        try {
            SettleProgressManageBO settlementProcessBO = settleProgressManageAPI.addManage(settleProgressManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(settlementProcessBO, SettleProgressManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑结算进度管理
     *
     * @param settleProgressManageTO 结算进度管理数据bo
     * @return class SettleProgressManageVO
     * @des 添加结算进度管理
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) SettleProgressManageTO settleProgressManageTO) throws ActException {
        try {
            SettleProgressManageBO settleProgressManageBO = settleProgressManageAPI.editManage(settleProgressManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(settleProgressManageBO, SettleProgressManageVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除结算进度管理
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            settleProgressManageAPI.deleteManage(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 根据外包单位筛选信息
     *
     * @param outUnit 外包单位
     * @return class ScreeningSettleProgressManageVO
     * @des 获取所有结算进度管理
     * @version v1
     */
    @GetMapping("v1/listByOutUnit")
    public Result findListByOutUnit(@RequestParam String outUnit, HttpServletRequest request) throws ActException {
        try {
            List<ScreeningSettleProgressManageVO> screeningSettleProgressManageVOS = BeanTransform.copyProperties(
                    settleProgressManageAPI.listByOutUnit(outUnit), ScreeningSettleProgressManageVO.class, request);
            return ActResult.initialize(screeningSettleProgressManageVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的运营商名称
     *
     * @version v1
     */
    @GetMapping("v1/operatorName")
    public Result findOperatorName(HttpServletRequest request) throws ActException {
        try {
            List<String> operatorName = settleProgressManageAPI.findOperatorName();
            return ActResult.initialize(operatorName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的地区
     *
     * @version v1
     */
    @GetMapping("v1/area")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            List<String> area = settleProgressManageAPI.findArea();
            return ActResult.initialize(area);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的外包单位
     *
     * @version v1
     */
    @GetMapping("v1/outUnit")
    public Result findOutUnit(HttpServletRequest request) throws ActException {
        try {
            List<String> outUnit = settleProgressManageAPI.findOutUnit();
            return ActResult.initialize(outUnit);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的销售合同
     *
     * @version v1
     */
    @GetMapping("v1/saleContractNo")
    public Result findSaleContractNo(HttpServletRequest request) throws ActException {
        try {
            List<String> saleContractNo = settleProgressManageAPI.findSaleContractNo();
            return ActResult.initialize(saleContractNo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的外包合同号
     *
     * @version v1
     */
    @GetMapping("v1/contractNo")
    public Result findContractNo(HttpServletRequest request) throws ActException {
        try {
            List<String> saleContractNo = settleProgressManageAPI.findContractNo();
            return ActResult.initialize(saleContractNo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的进度
     *
     * @version v1
     */
    @GetMapping("v1/progress")
    public Result findProgress(HttpServletRequest request) throws ActException {
        try {
            List<String> saleContractNo = settleProgressManageAPI.findProgress();
            return ActResult.initialize(saleContractNo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的派工名称
     *
     * @version v1
     */
    @GetMapping("v1/dispatName")
    public Result findDispatName(HttpServletRequest request) throws ActException {
        try {
            List<String> dispatName = settleProgressManageAPI.findDispatName();
            return ActResult.initialize(dispatName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据派工名称获取外包合同号
     *
     * @version v1
     */
    @GetMapping("v1/contractNo/name")
    public Result findContractNoByName(@RequestParam String dispatName, HttpServletRequest request) throws ActException {
        try {
            List<String> contractNo = businessContractAPI.findSingleNumByName(dispatName);
            return ActResult.initialize(contractNo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据外包合同号获取对应字段信息
     *
     * @return class BusinessContractsBO
     * @version v1
     */
    @GetMapping("v1/progress/contractNo")
    public Result findProgress(@RequestParam String contractNo, HttpServletRequest request) throws ActException {
        try {
            BusinessContractsBO businessContractsBO = businessContractAPI.findBySingleNum(contractNo);
            return ActResult.initialize(businessContractsBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据对应的进度管理id获取对应自定义表头字段信息
     *
     * @return class HeadersCustomVO
     * @version v1
     */
    @GetMapping("v1/headers/manageId")
    public Result findHeaderByManage(@RequestParam String manageId, HttpServletRequest request) throws ActException {
        try {
            List<HeadersCustomVO> headersCustomVOList = BeanTransform.copyProperties(headersCustomAPI.getByManageId(manageId),HeadersCustomVO.class);
            return ActResult.initialize(headersCustomVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据对应的进度管理id获取对应自定义节点表头字段信息
     *
     * @return class NodeHeadersCustomVO
     * @version v1
     */
    @GetMapping("v1/nodeHeaders/manageId")
    public Result findNodeHeaderByManage(@RequestParam String manageId, HttpServletRequest request) throws ActException {
        try {
            List<NodeHeadersCustomVO> nodeHeadersCustomVOList = BeanTransform.copyProperties(nodeHeadersCustomAPI.getByManageId(manageId),NodeHeadersCustomVO.class);
            return ActResult.initialize(nodeHeadersCustomVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分配责任人
     *
     * @param settleWorkProgreManageTO 结算工作进度管理
     * @version v1
     */
    @PostMapping("v1/redistribution")
    public Result redistribution(@Validated({ADD.class}) SettleWorkProgreManageTO settleWorkProgreManageTO, HttpServletRequest request) throws ActException {
        try {
            settleWorkProgreManageAPI.addSettleWork(settleWorkProgreManageTO);
            return new ActResult("redistribution success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导出Excel
     *
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(@RequestParam String outUnit, HttpServletResponse response) throws ActException {
        try {
            String fileName = "结算进度管理.xlsx";
            super.writeOutFile(response, settleProgressManageAPI.exportExcel(outUnit), fileName);
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
     * @param outUnit 外包单位
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel(String outUnit, HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = getInputStreams(request);
            settleProgressManageAPI.excelImport(inputStreams, outUnit);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分配节点和进度延后的节点下拉值
     *
     * @return class AllotmentNodeDataVO
     * @version v1
     */
    @GetMapping("v1/allotmentNodeData/{id}")
    public Result findAllotmentNodeData(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<AllotmentNodeDataVO> allotmentNodeDataVO = BeanTransform.copyProperties(settleProgressManageAPI.findAllNodeById(id), AllotmentNodeDataVO.class);
            return ActResult.initialize(allotmentNodeDataVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 进度延后
     *
     * @param scheduleDelayDataTO 进度延后to
     * @des 进度延后
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/scheduleDelay")
    public Result scheduleDelay(@Validated({ADD.class}) ScheduleDelayDataTO scheduleDelayDataTO) throws ActException {
        try {
            settleProgressManageAPI.scheduleDelay(scheduleDelayDataTO);
            return new ActResult("scheduleDelay success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结算进度汇总模板
     *
     * @return class SettleProgressSummVO
     * @version v1
     */
    @GetMapping("v1/settleProgressSumm")
    public Result settleProgressSumm(String area, String outUnit, HttpServletRequest request) throws ActException {
        try {
            List<SettleProgressSummVO> settleProgressSummVOS = BeanTransform.copyProperties(settleProgressManageAPI.settleProgress(area, outUnit), SettleProgressSummVO.class);
            return ActResult.initialize(settleProgressSummVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结算进度管理汇总
     *
     * @return class SettleProgressManageSummVO
     * @version v1
     */
    @GetMapping("v1/settleProgressManageSumm")
    public Result settleProgressManageSumm(HttpServletRequest request) throws ActException {
        try {
            List<SettleProgressManageSummVO> settleProgressManageSummVOS = BeanTransform.copyProperties(settleProgressManageAPI.settleProgressManageSumm(), SettleProgressManageSummVO.class);
            return ActResult.initialize(settleProgressManageSummVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的内部项目名称
     *
     * @version v1
     */
    @GetMapping("v1/findInternalProName")
    public Result findInternalProName(HttpServletRequest request) throws ActException {
        try {
            List<String> internalName = settleProgressManageAPI.findInternalProName();
            return ActResult.initialize(internalName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结算进度管理数量柱状图
     *
     * @return class OptionVO
     * @version v1
     */
    @GetMapping("v1/settleProgressNum")
    public Result settleProgressNum(HttpServletRequest request) throws ActException {
        try {
            OptionVO OptionVO = BeanTransform.copyProperties(settleProgressManageAPI.settleProgressNum(), OptionVO.class);
            return ActResult.initialize(OptionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结算进度管理金额柱状图
     *
     * @return class OptionAmountVO
     * @version v1
     */
    @GetMapping("v1/settleProgressAmount")
    public Result settleProgressAmount(HttpServletRequest request) throws ActException {
        try {
            OptionAmountVO optionAmountVO = BeanTransform.copyProperties(settleProgressManageAPI.settleProgressAmount(), OptionAmountVO.class);
            return ActResult.initialize(optionAmountVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}