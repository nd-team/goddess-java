package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecordsExcel;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsUploadTO;
import com.bjike.goddess.archive.vo.StaffRecordsVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 员工档案
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffrecords")
public class StaffRecordsAct extends BaseFileAction {

    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 上传数据
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<StaffRecordsExcel> tos = ExcelUtil.excelToClazz(is, StaffRecordsExcel.class, excel);
            List<StaffRecordsExcelTO> toList = BeanTransform.copyProperties(tos, StaffRecordsExcelTO.class);
            staffRecordsAPI.upload(toList);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 上传请求
     * @param to      员工档案附件上传
     * @return class Result
     * @version v1
     */
    @PostMapping("v1/uploadEnclosure")
    public Result uploadEnclosure(HttpServletRequest request, @Validated(ADD.class) StaffRecordsUploadTO to, BindingResult result) throws ActException {
        try {

            String path = "/" + to.getUsername() + "/" + to.getEnclosure();
            fileAPI.upload(this.getInputStreams(request, path));
            return new ActResult("上传成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 员工档案数据传输对象
     * @return class StaffRecordsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(StaffRecordsDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsAPI.maps(dto), StaffRecordsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取档案调阅数据
     *
     * @param id 档案调阅数据id
     * @return class StaffRecordsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsAPI.getById(id), StaffRecordsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(staffRecordsAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}