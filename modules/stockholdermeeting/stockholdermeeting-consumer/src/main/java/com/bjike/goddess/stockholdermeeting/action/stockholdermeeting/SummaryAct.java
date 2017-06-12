package com.bjike.goddess.stockholdermeeting.action.stockholdermeeting;

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
import com.bjike.goddess.stockholdermeeting.api.SummaryAPI;
import com.bjike.goddess.stockholdermeeting.api.SummarySonAPI;
import com.bjike.goddess.stockholdermeeting.bo.SummaryBO;
import com.bjike.goddess.stockholdermeeting.bo.SummarySonBO;
import com.bjike.goddess.stockholdermeeting.dto.ExcelDTO;
import com.bjike.goddess.stockholdermeeting.dto.SummaryDTO;
import com.bjike.goddess.stockholdermeeting.excel.SummaryExcel;
import com.bjike.goddess.stockholdermeeting.to.ExportExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummarySonTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryTO;
import com.bjike.goddess.stockholdermeeting.vo.SummarySonVO;
import com.bjike.goddess.stockholdermeeting.vo.SummaryVO;
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
 * 股东大会纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 06:13 ]
 * @Description: [ 股东大会纪要 ]
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
     * 添加会议纪要
     *
     * @param to 股东大会纪要信息数据to
     * @return class SummarySonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) SummaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SummaryBO bo = summaryAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SummaryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股东大会纪要列表总条数
     *
     * @param dto 股东大会纪要dto
     * @des 获取所有股东大会纪要总条数
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
     * 一个股东大会纪要
     *
     * @param id id
     * @return class SummaryVO
     * @des 获取一个股东大会纪要
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
     * 股东大会纪要列表
     *
     * @param dto 股东大会纪要信息dto
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
     * 编辑股东大会纪要信息
     *
     * @param to 股东大会纪要信息数据to
     * @des 编辑股东大会纪要信息
     * @version v1
     */
    @LoginAuth
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
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SummaryExcel> toList = ExcelUtil.excelToClazz(is, SummaryExcel.class, excel);
            List<SummaryExcelTO> tos=new ArrayList<SummaryExcelTO>();
            for (SummaryExcel to:toList){
                SummaryExcelTO excelTO=BeanTransform.copyProperties(to,SummaryExcelTO.class);
                excelTO.setActualTime(String.valueOf(to.getActualTime()));
                tos.add(excelTO);
            }
            summaryAPI.leadExcel(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param dto 导出excel条件
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(@Validated({ADD.class}) ExcelDTO dto, BindingResult result, HttpServletResponse response) throws ActException {
        try {
            String fileName = "股东大会纪要.xlsx";
            super.writeOutFile(response, summaryAPI.exportExcel(dto), fileName);
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
     * @param to 股东大会纪要子表信息数据to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editSpeak")
    public Result editSpeak(@Validated({EDIT.class}) SummarySonTO to, BindingResult bindingResult) throws ActException {
        try {
            summarySonAPI.editSpeak(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加发言内容
     *
     * @param to 股东大会纪要子表信息数据to
     * @return class SummarySonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/saveSon")
    public Result saveSon(@Validated({ADD.class}) SummarySonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SummarySonBO bo = summarySonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SummarySonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 组织内容id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            summaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 组织内容id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            summaryAPI.thaw(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}