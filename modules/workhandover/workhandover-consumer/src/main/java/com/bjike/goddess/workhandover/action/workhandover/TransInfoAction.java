package com.bjike.goddess.workhandover.action.workhandover;


import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.ModuleTypeBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.workhandover.api.TransInfoAPI;
import com.bjike.goddess.workhandover.bo.TransInfoBO;
import com.bjike.goddess.workhandover.dto.TransInfoDTO;
import com.bjike.goddess.workhandover.entity.TransInfo;
import com.bjike.goddess.workhandover.excel.TransInfoExcel;
import com.bjike.goddess.workhandover.to.TransInfoTO;
import com.bjike.goddess.workhandover.vo.TransInfoVO;
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
 * 工作交接
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-10 05:08 ]
 * @Description: [ 工作交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transinfo")
public class TransInfoAction extends BaseFileAction{

    @Autowired
    TransInfoAPI transInfoAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;

    /**
     * 工作交接列表
     *
     * @param dto
     * @param request
     * @return
     * @throws ActException
     */
    @GetMapping("v1/list")
    public Result list(TransInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TransInfoVO> vo = BeanTransform.copyProperties ( transInfoAPI.findWorkHandOver ( dto ), TransInfoVO.class, request );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 获取一个工作交接
     *
     * @param id 工作交接id
     * @return class TransInfoVO
     * @des 获取一个工作交接
     * @version v1
     */
    @GetMapping("v1/oneWorkHandOver/{id}")
    public Result work(@PathVariable String id) throws ActException {
        try {
            TransInfoBO bo = transInfoAPI.getOneWorkhandOver ( id );
            return ActResult.initialize(BeanTransform.copyProperties(bo, TransInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工作交接总数
     *
     * @param dto
     * @param request
     * @return
     * @throws ActException
     */
    @GetMapping("v1/count")
    public Result count(TransInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            Long count = transInfoAPI.countWorkHandOver ( dto );
            return ActResult.initialize ( count );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 添加工作交接
     *
     * @param to
     * @param request
     * @return
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TransInfoTO to, BindingResult request) throws ActException {
        try {
            TransInfoBO bo = transInfoAPI.insertWorkHandOver ( to );
            return ActResult.initialize ( bo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 编辑工作交接
     *
     * @param to
     * @param result
     * @return
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TransInfoTO to, BindingResult result) throws ActException {
        try {
            TransInfoBO bo = transInfoAPI.editWorkHandOver ( to );
            return ActResult.initialize ( bo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 删除工作交接
     *
     * @param id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            transInfoAPI.removeWorkHandOver ( id );
            return new ActResult ( "删除成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 搜索工作交接
     *
     * @param to
     * @param request
     * @return
     * @throws ActException
     */
    @GetMapping("v1/search")
    public Result search(TransInfoTO to, HttpServletRequest request) throws ActException {
        try {
            List<TransInfoVO> vo = BeanTransform.copyProperties ( transInfoAPI.searchWorkHandOver ( to ), TransInfoVO.class, request );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 上传附件
     *
     * @des 招标信息
     * @version v1
     */
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 招标信息id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /bidding/id/....
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
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
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

    /**
     * 查询正常状态的岗位详细
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/position")
    public Result position(HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> boList = new ArrayList<> ();
            if(moduleAPI.isCheck("organize")){
                boList = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, PositionDetailVO.class, request));
//            if (moduleAPI.isCheck("organize")) {
//                return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findStatus(), PositionDetailVO.class, request));
//            } else {
//                return ActResult.initialize(null);
//            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的模块类型数据
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/module")
    public Result module(HttpServletRequest request) throws ActException {
        try {
            List<ModuleTypeBO> boList = new ArrayList<>();
            if(moduleAPI.isCheck("organize")){
                boList = moduleTypeAPI.findByStatus( Status.THAW);
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, ModuleTypeVO.class, request));
//            if (moduleAPI.isCheck("organize")) {
//                return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(Status.THAW), ModuleTypeVO.class, request));
//            } else {
//                return ActResult.initialize(null);
//            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<TransInfoExcel> tos = ExcelUtil.excelToClazz(is, TransInfoExcel.class, excel);
            List<TransInfoTO> tocs = new ArrayList<>();
            List<TransInfoTO> toList = BeanTransform.copyProperties(tos, TransInfoTO.class,"cause");
            transInfoAPI.importExcel ( toList );
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//
    /**
     * 导出Excel
     *
     * @param dto 导出条件
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(TransInfoDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "工作交接.xlsx";
            super.writeOutFile(response, transInfoAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}