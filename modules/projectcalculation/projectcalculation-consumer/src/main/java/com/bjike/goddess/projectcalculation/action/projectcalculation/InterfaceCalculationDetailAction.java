package com.bjike.goddess.projectcalculation.action.projectcalculation;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.projectcalculation.api.InterfaceCalculationDetailAPI;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDetailDTO;
import com.bjike.goddess.projectcalculation.excel.InterfaceCalculationDetailExcel;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDetailTO;
import com.bjike.goddess.projectcalculation.vo.InterfaceCalculationDetailVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目界面测算明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-08 03:04 ]
 * @Description: [ 项目界面测算明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("interfacecalculationdetail")
public class InterfaceCalculationDetailAction extends BaseFileAction {
    @Autowired
    private InterfaceCalculationDetailAPI interfaceCalculationDetailAPI;

    @Autowired
    private FileAPI fileAPI;

    @GetMapping("v1/getlist")
    public Result getList() throws SerException {
        return new ActResult("success", BeanTransform.copyProperties(interfaceCalculationDetailAPI.getList(), InterfaceCalculationDetailVO.class));
    }

    @PostMapping("v1/add")
    public Result add(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        interfaceCalculationDetailAPI.add(interfaceCalculationDetailTO);
        return new ActResult("添加成功");
    }

    @PutMapping("v1/editor")
    public Result editor(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        InterfaceCalculationDetailBO interfaceCalculationDetailBO = interfaceCalculationDetailAPI.editor(interfaceCalculationDetailTO);
        InterfaceCalculationDetailVO interfaceCalculationDetailVO = BeanTransform.copyProperties(interfaceCalculationDetailBO, InterfaceCalculationDetailVO.class);
        return ActResult.initialize(interfaceCalculationDetailVO);
    }

    @PostMapping("v1/update")
    public void upDate(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        interfaceCalculationDetailAPI.upDate(interfaceCalculationDetailTO);
    }

    @GetMapping("v1/export")
    public Result exportExcel(InterfaceCalculationDetailDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目界面测算明细.xlsx";
            super.writeOutFile(response, interfaceCalculationDetailAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    @PostMapping("v1/import")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<InterfaceCalculationDetailExcel> tos = ExcelUtil.excelToClazz(is, InterfaceCalculationDetailExcel.class, excel);
            List<InterfaceCalculationDetailTO> tocs = new ArrayList<>();
            for (InterfaceCalculationDetailExcel str : tos) {
                InterfaceCalculationDetailTO siginManageTO = BeanTransform.copyProperties(str, InterfaceCalculationDetailTO.class);
               /* siginManageTO.setStartProjectTime(String.valueOf(str.getStartProjectTime()));
                siginManageTO.setEndProjectTime(String.valueOf(str.getEndProjectTime()));
                siginManageTO.setSiginStatus(convertSiginStatus(str.getSiginStatus()));
                siginManageTO.setManager("");
                siginManageTO.setAuditAdvice("");*/
                tocs.add(siginManageTO);
            }
            //注意序列化
            interfaceCalculationDetailAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (ActException e) {
            e.printStackTrace();
        }
        return new ActResult("");
    }

    @PostMapping("v1/fileUpload/{id}")
    public Result fileUpload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/projectcalculation/InterfaceCalculationDetail/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("v1/listFile/{id}")
    public Result filelist(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/InterfaceCalculationDetail/" + id;
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

    @GetMapping("v1/searcharea/{area}")
    public Result searchByArea(@PathVariable String area) throws SerException {
        return new ActResult("", interfaceCalculationDetailAPI.searchByArea(area));
    }

    @GetMapping("v1/searchinternalProjectNum/{internalProjectNum}")
    public Result searchByInternalProjectNum(@PathVariable String internalProjectNum) throws SerException {
        return new ActResult("", BeanTransform.copyProperties(interfaceCalculationDetailAPI.searchByInternalProjectNum(internalProjectNum), InterfaceCalculationDetailVO.class));
    }

    @PostMapping("v1/submit")
    public Result submit(InterfaceCalculationDetailTO to) throws SerException {
        interfaceCalculationDetailAPI.submit(to);
        return new ActResult("success");
    }
}