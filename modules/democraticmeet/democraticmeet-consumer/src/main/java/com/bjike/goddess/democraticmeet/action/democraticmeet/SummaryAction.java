package com.bjike.goddess.democraticmeet.action.democraticmeet;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.democraticmeet.api.SummaryAPI;
import com.bjike.goddess.democraticmeet.bo.AdviceTableBO;
import com.bjike.goddess.democraticmeet.bo.MeetDesignBO;
import com.bjike.goddess.democraticmeet.bo.SummaryBO;
import com.bjike.goddess.democraticmeet.dto.SummaryDTO;
import com.bjike.goddess.democraticmeet.to.ExportExcelCondition;
import com.bjike.goddess.democraticmeet.excel.SummaryExcel;
import com.bjike.goddess.democraticmeet.excel.SummaryExcelConvert;
import com.bjike.goddess.democraticmeet.to.SummaryTO;
import com.bjike.goddess.democraticmeet.vo.AdviceTableVO;
import com.bjike.goddess.democraticmeet.vo.DemocraticContentVO;
import com.bjike.goddess.democraticmeet.vo.MeetDesignVO;
import com.bjike.goddess.democraticmeet.vo.SummaryVO;
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
 * 会议纪要
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("summary")
public class SummaryAction extends BaseFileAction {

    @Autowired
    private SummaryAPI summaryAPI;

    /**
     * 列表总条数
     *
     * @param customerBaseInfoDTO 会议纪要信息dto
     * @des 获取所有会议纪要信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SummaryDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = summaryAPI.countSummary(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个会议纪要
     *
     * @param id 会议纪要信息id
     * @des 根据id获取会议纪要信息
     * @return  class SummaryVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SummaryBO bo = summaryAPI.getOneById(id);
            SummaryVO vo = BeanTransform.copyProperties(bo, SummaryVO.class);

            List<AdviceTableBO> deleteAdviceList = bo.getAdviceTableBOList();
            if( deleteAdviceList != null && deleteAdviceList.size()>0 ){

                List<AdviceTableVO> adviceVOList = BeanTransform.copyProperties(deleteAdviceList, AdviceTableVO.class);
                vo.setAdviceTableVOList( adviceVOList );
            }

            DemocraticContentVO democraticContentVO = new DemocraticContentVO();

            democraticContentVO = (null == bo.getDemocraticContentBO()? democraticContentVO
                    :BeanTransform.copyProperties( bo.getDemocraticContentBO(), DemocraticContentVO.class));
            MeetDesignBO meetDesignBO = (null == democraticContentVO ? new MeetDesignBO() : bo.getDemocraticContentBO().getMeetDesignBO());
            MeetDesignVO meetDesignVO = BeanTransform.copyProperties( meetDesignBO , MeetDesignVO.class);

            democraticContentVO.setMeetDesignVO( meetDesignVO );
            vo.setDemocraticContentVO( democraticContentVO );


            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 会议纪要列表
     *
     * @param summaryDTO 会议纪要信息dto
     * @return class SummaryVO
     * @des 获取所有会议纪要信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/listSummary")
    public Result findListSummary(SummaryDTO summaryDTO, BindingResult bindingResult , HttpServletRequest request) throws ActException {
        try {

            List<SummaryBO> list = summaryAPI.listSummary(summaryDTO);
            List<SummaryVO> listVO = new ArrayList<>();
            for(SummaryBO str : list){
                SummaryVO vo = BeanTransform.copyProperties(str, SummaryVO.class);

                List<AdviceTableBO> deleteAdviceList = str.getAdviceTableBOList();
                List<AdviceTableVO> adviceVOList = BeanTransform.copyProperties(deleteAdviceList, AdviceTableVO.class);
                vo.setAdviceTableVOList( adviceVOList );

                DemocraticContentVO democraticContentVO = new DemocraticContentVO();

                democraticContentVO = (null == str.getDemocraticContentBO()? democraticContentVO
                        :BeanTransform.copyProperties( str.getDemocraticContentBO(), DemocraticContentVO.class));
                MeetDesignBO meetDesignBO = (null == democraticContentVO ? new MeetDesignBO() : str.getDemocraticContentBO().getMeetDesignBO());
                MeetDesignVO meetDesignVO = BeanTransform.copyProperties( meetDesignBO , MeetDesignVO.class);

                democraticContentVO.setMeetDesignVO( meetDesignVO );
                vo.setDemocraticContentVO( democraticContentVO );

                listVO.add( vo );
            }


            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加会议纪要
     *
     * @param summaryTO 会议纪要基本信息数据to
     * @return class SummaryVO
     * @des 添加会议纪要
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSummary(@Validated SummaryTO summaryTO ) throws ActException {
        try {
            SummaryBO summaryBO1 = summaryAPI.addSummary(summaryTO);
            return ActResult.initialize(BeanTransform.copyProperties(summaryBO1, SummaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑会议纪要
     *
     * @param summaryTO 会议纪要基本信息数据bo
     * @return class SummaryVO
     * @des 添加会议纪要
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSummary(@Validated SummaryTO summaryTO) throws ActException {
        try {
            SummaryBO summaryBO1 = summaryAPI.editSummary(summaryTO);
            return ActResult.initialize(BeanTransform.copyProperties(summaryBO1, SummaryVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除会议纪要信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSummary(@PathVariable String id) throws ActException {
        try {
            summaryAPI.deleteSummary(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 导入会议纪要Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SummaryExcel> tos = ExcelUtil.mergeExcelToClazz(is, SummaryExcel.class, excel);
            List<SummaryExcelConvert> tocs = new ArrayList<>();
            tos.stream().forEach(str->{
                SummaryExcelConvert summaryExcelConvert = BeanTransform.copyProperties( str , SummaryExcelConvert.class);
                summaryExcelConvert.setDealTime( String.valueOf( str.getActualTime() ) );
                tocs.add( summaryExcelConvert );
            });
            //注意序列化
            summaryAPI.leadExcel(tocs);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导出会议纪要
     *
     * @param to 会议纪要信息
     * @return class DepartYearIndexSetVO
     * @des 导出会议纪要
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/export")
    public Result departYearReport(ExportExcelCondition to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "民主生活会议纪要.xlsx";
            super.writeOutFile(response, summaryAPI.exportReport(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    
}