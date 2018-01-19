package com.bjike.goddess.projectcalculation.action.projectcalculation;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.market.api.MarketInfoPreAnalysisAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.projectcalculation.api.CalculationDecisionsAPI;
import com.bjike.goddess.projectcalculation.api.CalculationDetailAPI;
import com.bjike.goddess.projectcalculation.bo.CalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.excel.CalculationDetailExcel;
import com.bjike.goddess.projectcalculation.excel.SonPermissionObject;
import com.bjike.goddess.projectcalculation.to.CalculationDecisionsTO;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.vo.CalculationDetailVO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 项目测算明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-05 02:48 ]
 * @Description: [ 项目测算明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("calculationdetail")
public class CalculationDetailAction extends BaseFileAction {
    @Autowired
    private CalculationDetailAPI calculationDetailAPI;
    @Autowired
    private MarketInfoPreAnalysisAPI marketInfoPreAnalysisAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private CalculationDecisionsAPI calculationDecisionsAPI;

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
            obj.setDescribesion(" 设置");
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

            List<SonPermissionObject> hasPermissionList = calculationDetailAPI.sonPermission();
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

            Boolean isHasPermission = calculationDetailAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, " 没有权限", false);
            } else {
                return new ActResult(0, " 有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    //取得列表
    @GetMapping("v1/getlist")
    public Result getList() throws SerException {
        List<CalculationDetailBO> bo = calculationDetailAPI.getList();
        List<CalculationDetailVO> voList = BeanTransform.copyProperties(bo, CalculationDetailVO.class);
        return ActResult.initialize(voList);
    }

    //添加
    @PostMapping("v1/add")
    public Result add(CalculationDetailTO calculationDetailTO) throws SerException {
        calculationDetailAPI.add(calculationDetailTO);
        return new ActResult("添加成功");
    }

    //编辑
    @GetMapping("v1/editor/{id}")
    public Result editor(@PathVariable String id) throws SerException {
        return new ActResult("", BeanTransform.copyProperties(calculationDetailAPI.findById(id), CalculationDetailVO.class));
    }

    //更新
    @PutMapping("v1/update")
    public Result upDate(CalculationDetailTO calculationDetailTO) throws SerException {
        calculationDetailAPI.upDate(calculationDetailTO);
        return new ActResult("success");
    }

    //导出
    @GetMapping("v1/export")
    public Result exportExcel(CalculationDetailDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目测算明细.xlsx";
            super.writeOutFile(response, calculationDetailAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    //导入
    @PostMapping("v1/import")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CalculationDetailExcel> tos = ExcelUtil.excelToClazz(is, CalculationDetailExcel.class, excel);
            List<CalculationDetailTO> tocs = new ArrayList<>();
            for (CalculationDetailExcel str : tos) {
                CalculationDetailTO siginManageTO = BeanTransform.copyProperties(str, CalculationDetailTO.class);
               /* siginManageTO.setStartProjectTime(String.valueOf(str.getStartProjectTime()));
                siginManageTO.setEndProjectTime(String.valueOf(str.getEndProjectTime()));
                siginManageTO.setSiginStatus(convertSiginStatus(str.getSiginStatus()));
                siginManageTO.setManager("");
                siginManageTO.setAuditAdvice("");*/
                tocs.add(siginManageTO);
            }
            //注意序列化
            calculationDetailAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (ActException e) {
            e.printStackTrace();
        }
        return new ActResult("");
    }

    //文件上传
    @PostMapping("v1/fileUpload/{id}")
    public Result fileUpload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/projectcalculation/CalculationDetail/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //文件列表
    @GetMapping("v1/fileList/{id}")
    public Result fileList(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/projectcalculation/CalculationDetail/" + id;
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

    //文件下载
    @GetMapping("v1/fileDownload")
    public Result fileDownload(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult(" success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    //根据市场信息搜索
    @GetMapping("v1/getsearchlist/{marketInfoNum}")
    public Result getSearchList(@PathVariable String marketInfoNum) throws SerException {
        List<CalculationDetailBO> bos = calculationDetailAPI.getSearchList(marketInfoNum);
        List<CalculationDetailVO> vos = BeanTransform.copyProperties(bos, CalculationDetailVO.class);
        return new ActResult("success", vos);
    }

    /*@GetMapping("v1/getMarket")
    public Result getMarket() throws SerException {
        calculationDetailAPI.saveMarket();
        return new ActResult("success");
    }*/

    //冻结状态
    @PostMapping("v1/status")
    public void setStatus(CalculationDetailTO calculationDetailTO) throws SerException {
        CalculationDetailBO bo = calculationDetailAPI.findById(calculationDetailTO.getId());
        CalculationDetailTO to = BeanTransform.copyProperties(bo, CalculationDetailTO.class);
        calculationDetailAPI.upDate(to);
        bo.setStatus(calculationDetailTO.getStatus());
    }

    /*@GetMapping("v1/listFile/{id}")
    public Result filelist(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/CalculationDetail/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }*/

    //提交
    @GetMapping("v1/submit")
    public Result submit(CalculationDetailTO to) throws SerException {
        CalculationDecisionsTO calculationDecisionsTO = BeanTransform.copyProperties(to,CalculationDecisionsTO.class,"calculationTime","demand","timeForProject","initiateDate","endDate"+
        "cooperationWay","contractAmount","majors","peopelNum","configRequirements","vehicleAllocate","vehicleCost","estimateArtificialCost","estimateEquipmentCost","estimateVehicleCost","calculationType","calculationPassTime");
        calculationDecisionsAPI.save(calculationDecisionsTO);
        return new ActResult("success");
    }
}