package com.bjike.goddess.workhandover.action.workhandover;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.workhandover.api.workTimeSpecificationAPI;
import com.bjike.goddess.workhandover.bo.workTimeSpecificationBO;
import com.bjike.goddess.workhandover.dto.workTimeSpecificationDTO;
import com.bjike.goddess.workhandover.excel.workTimeSpecificationExcel;
import com.bjike.goddess.workhandover.to.workTimeSpecificationTO;
import com.bjike.goddess.workhandover.vo.workTimeSpecificationVO;
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
 * 工作交接时间规范
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 工作交接时间规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("worktimespecification")
public class workTimeSpecificationAction extends BaseFileAction {

    @Autowired
    workTimeSpecificationAPI workTimeSpecificationAPI;

    /**
     * 工作交接时间规范列表
     *
     * @param dto
     * @param request
     * @return
     * @throws ActException
     */
    @GetMapping("v1/list")
    public Result list(workTimeSpecificationDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<workTimeSpecificationVO> vo = BeanTransform.copyProperties ( workTimeSpecificationAPI.findWorkTimeSpecification ( dto ), workTimeSpecificationVO.class, request );
            return ActResult.initialize ( vo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 添加工作交接时间规范
     *
     * @param to
     * @param request
     * @return
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) workTimeSpecificationTO to, BindingResult request) throws ActException {
        try {
            workTimeSpecificationBO bo = workTimeSpecificationAPI.insertWorkTimeSpecification ( to );
            return ActResult.initialize ( bo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }

    }

    /**
     * 编辑工作交接时间规范
     *
     * @param to
     * @param result
     * @return
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) workTimeSpecificationTO to, BindingResult result) throws ActException {
        try {
            workTimeSpecificationBO bo = workTimeSpecificationAPI.editWorkTimeSpecification ( to );
            return ActResult.initialize ( bo );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 删除工作交接时间规范
     *
     * @param id
     * @return
     * @throws ActException
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            workTimeSpecificationAPI.removeWorkTimeSpecification ( id );
            return new ActResult ( "删除成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        }
    }

    /**
     * 获取工作交接原因
     *
     * @param request
     * @return
     * @throws ActException
     */
    @GetMapping("v1/findWorkHandoverReason")
    public Result findWorkHandoverReason(HttpServletRequest request) throws ActException {
        try {
            List<String> list = workTimeSpecificationAPI.findWorkHandoverReason ();
            return ActResult.initialize ( list );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
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
            List<InputStream> inputStreams = super.getInputStreams ( request );
            InputStream is = inputStreams.get ( 1 );
            Excel excel = new Excel ( 0, 1 );
            List<workTimeSpecificationExcel> tos = ExcelUtil.excelToClazz ( is, workTimeSpecificationExcel.class, excel );
            List<workTimeSpecificationTO> tocs = new ArrayList<> ();
            List<workTimeSpecificationTO> toList = BeanTransform.copyProperties ( tos, workTimeSpecificationTO.class );
            workTimeSpecificationAPI.importExcel ( toList );
            return new ActResult ( "导入成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
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
    public Result exportExcel(workTimeSpecificationDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "工作交接.xlsx";
            super.writeOutFile ( response, workTimeSpecificationAPI.exportExcel ( dto ), fileName );
            return new ActResult ( "导出成功" );
        } catch (SerException e) {
            throw new ActException ( e.getMessage () );
        } catch (IOException e1) {
            throw new ActException ( e1.getMessage () );
        }
    }

}