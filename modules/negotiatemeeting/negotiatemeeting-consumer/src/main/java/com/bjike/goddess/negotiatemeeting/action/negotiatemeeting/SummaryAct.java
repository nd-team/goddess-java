package com.bjike.goddess.negotiatemeeting.action.negotiatemeeting;

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
import com.bjike.goddess.negotiatemeeting.api.SummaryAPI;
import com.bjike.goddess.negotiatemeeting.api.SummarySonAPI;
import com.bjike.goddess.negotiatemeeting.bo.SummaryBO;
import com.bjike.goddess.negotiatemeeting.bo.SummarySonBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryDTO;
import com.bjike.goddess.negotiatemeeting.entity.SummarySon;
import com.bjike.goddess.negotiatemeeting.excel.SummaryExcel;
import com.bjike.goddess.negotiatemeeting.to.SummarySonTO;
import com.bjike.goddess.negotiatemeeting.to.SummaryTO;
import com.bjike.goddess.negotiatemeeting.vo.SummarySonVO;
import com.bjike.goddess.negotiatemeeting.vo.SummaryVO;
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
 * 协商会议纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:49 ]
 * @Description: [ 协商会议纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("summary")
public class SummaryAct extends BaseFileAction {
    @Autowired
    private SummaryAPI summaryAPI;
    @Autowired
    private SummarySonAPI summarySonAPI;

    /**
     * 协商会议纪要列表总条数
     *
     * @param dto 协商会议纪要dto
     * @des 获取所有协商会议纪要总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(SummaryDTO dto) throws ActException {
        try {
            Long count = summaryAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个协商会议纪要
     *
     * @param id id
     * @return class SummaryVO
     * @des 获取一个协商会议纪要
     * @version v1
     */
    @GetMapping("v1/summary/{id}")
    public Result summary(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SummaryBO bo = summaryAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SummaryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 协商会议纪要列表
     *
     * @param dto 协商会议纪要信息dto
     * @return class SummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SummaryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SummaryVO> VOS = BeanTransform.copyProperties
                    (summaryAPI.list(dto), SummaryVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑协商会议纪要信息
     *
     * @param to 协商会议纪要信息数据to
     * @des 编辑协商会议纪要信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SummaryTO to, BindingResult bindingResult) throws ActException {
        try {
            summaryAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SummaryExcel> toList = ExcelUtil.excelToClazz(is, SummaryExcel.class, excel);
            summaryAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param id 导出的会议纪要id
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/exportExcel/{id}/{summaryId}")
    public Result exportExcel(@PathVariable String id, @PathVariable String summaryId, HttpServletResponse response) throws ActException {
        try {
            String fileName = "协商会议纪要.xlsx";
            super.writeOutFile(response, summaryAPI.exportExcel(id, summaryId), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 修改个人发言意见
     *
     * @param to 协商会议纪要子表信息数据to
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/editPerson")
    public Result editPerson(@Validated({EDIT.class}) SummarySonTO to, BindingResult bindingResult) throws ActException {
        try {
            summarySonAPI.editPerson(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加参会人和协商意见
     * @param to 协商会议纪要子表信息数据to
     * @return class SummarySonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/saveSon")
    public Result saveSon(@Validated({ADD.class}) SummarySonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SummarySonBO bo = summarySonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SummarySonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}