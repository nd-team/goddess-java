package com.bjike.goddess.communicatemeeting.action.communicatemeeting;

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
import com.bjike.goddess.communicatemeeting.api.MeetingSummaryAPI;
import com.bjike.goddess.communicatemeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.communicatemeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.communicatemeeting.excel.MeetingSummaryExcel;
import com.bjike.goddess.communicatemeeting.to.MeetingSummaryTO;
import com.bjike.goddess.communicatemeeting.vo.MeetingSummaryVO;
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
 * 交流会纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("meetingsummary")
public class MeetingSummaryAct extends BaseFileAction {
    @Autowired
    private MeetingSummaryAPI meetingSummaryAPI;

    /**
     * 交流会纪要列表总条数
     *
     * @param dto 交流会纪要dto
     * @des 获取所有交流会纪要总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(MeetingSummaryDTO dto) throws ActException {
        try {
            Long count = meetingSummaryAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个交流会纪要
     *
     * @param id id
     * @return class MeetingSummaryVO
     * @des 获取一个交流会纪要
     * @version v1
     */
    @GetMapping("v1/meetingsummary/{id}")
    public Result meetingsummary(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingSummaryBO bo = meetingSummaryAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MeetingSummaryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流会纪要列表
     *
     * @param dto 交流会纪要信息dto
     * @return class MeetingSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MeetingSummaryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MeetingSummaryVO> VOS = BeanTransform.copyProperties
                    (meetingSummaryAPI.list(dto), MeetingSummaryVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑交流会纪要信息
     *
     * @param to 交流会纪要信息数据to
     * @des 编辑交流会纪要信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MeetingSummaryTO to, BindingResult bindingResult) throws ActException {
        try {
            meetingSummaryAPI.edit(to);
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
            List<MeetingSummaryExcel> toList = ExcelUtil.excelToClazz(is, MeetingSummaryExcel.class, excel);
            meetingSummaryAPI.leadExcel(toList);
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
    @GetMapping("v1/exportExcel/{id}")
    public Result exportExcel(@PathVariable String id, HttpServletResponse response) throws ActException {
        try {
            String fileName = "交流会纪要.xlsx";
            super.writeOutFile(response, meetingSummaryAPI.exportExcel(id), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}