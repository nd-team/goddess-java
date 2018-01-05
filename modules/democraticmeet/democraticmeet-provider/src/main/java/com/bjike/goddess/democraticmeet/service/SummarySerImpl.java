package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.democraticmeet.bo.AdviceTableBO;
import com.bjike.goddess.democraticmeet.bo.DemocraticContentBO;
import com.bjike.goddess.democraticmeet.bo.MeetDesignBO;
import com.bjike.goddess.democraticmeet.bo.SummaryBO;
import com.bjike.goddess.democraticmeet.dto.*;
import com.bjike.goddess.democraticmeet.entity.*;
import com.bjike.goddess.democraticmeet.excel.SummaryExportExcel;
import com.bjike.goddess.democraticmeet.to.ExportExcelCondition;
import com.bjike.goddess.democraticmeet.excel.SummaryExcelConvert;
import com.bjike.goddess.democraticmeet.to.AdviceTableTO;
import com.bjike.goddess.democraticmeet.to.SummaryTO;
import com.bjike.goddess.democraticmeet.utils.ChineseConvert;
import com.bjike.goddess.democraticmeet.utils.DownloadExeclUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 会议纪要业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "democraticmeetSerCache")
@Service
public class SummarySerImpl extends ServiceImpl<Summary, SummaryDTO> implements SummarySer {

    private static final Logger LOGGER = Logger.getLogger(ExcelUtil.class.getName());
    public static final String[] EXCELHEAD = {"会议类型","会议编号","实际会议时间","会议形式","会议地点",
            "计划参会人员","实际参会人员","新增参会人员","未参会人员","参会人数","自我批评的内容","他人给予的建议",
            "建议人","自我总结","会议记录人","会议主持人","会议组织人"};


    @Autowired
    private DemocraticContentSer democraticContentSer;
    @Autowired
    private MeetDesignSer meetDesignSer;
    @Autowired
    private AdviceTableSer adviceTableSer;
    @Autowired
    private AttenderSer attenderSer;

    @Override
    public Long countSummary(SummaryDTO summaryDTO) throws SerException {
        if (StringUtils.isNotBlank(summaryDTO.getRecorder())) {
            summaryDTO.getConditions().add(Restrict.like("recorder", summaryDTO.getRecorder()));
        }
        Long count = super.count(summaryDTO);
        return count;
    }

    @Override
    public SummaryBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Summary summary = super.findById(id);

        SummaryBO bo = BeanTransform.copyProperties(summary, SummaryBO.class);

        AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
        adviceTableDTO.getConditions().add(Restrict.eq("summary.id", summary.getId()));
        List<AdviceTable> deleteAdviceList = adviceTableSer.findByCis(adviceTableDTO);
        List<AdviceTableBO> adviceBOList = BeanTransform.copyProperties(deleteAdviceList, AdviceTableBO.class);
        bo.setAdviceTableBOList(adviceBOList);

        List<MeetDesignBO> meetDesignBOList = new ArrayList<>();
        DemocraticContentBO democraticContentBO = new DemocraticContentBO();
        if (null != summary.getDemocraticContent()) {

            MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
            meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id", summary.getDemocraticContent().getId()));
            List<MeetDesign> meetDesignList = meetDesignSer.findByCis(meetDesignDTO);
            meetDesignBOList = BeanTransform.copyProperties(meetDesignList, MeetDesignBO.class);
            democraticContentBO = BeanTransform.copyProperties(summary.getDemocraticContent(), DemocraticContentBO.class);
        }

        democraticContentBO.setMeetDesignBO(meetDesignBOList == null || meetDesignBOList.size()==0 ? null : meetDesignBOList.get(0));
        bo.setDemocraticContentBO(democraticContentBO);


        return bo;
    }

    @Override
    public List<SummaryBO> listSummary(SummaryDTO summaryDTO) throws SerException {
        if (StringUtils.isNotBlank(summaryDTO.getRecorder())) {
            summaryDTO.getConditions().add(Restrict.like("recorder", summaryDTO.getRecorder()));
        }

        List<Summary> list = super.findByCis(summaryDTO, true);
        List<SummaryBO> listBO = new ArrayList<>();
        for (Summary str : list) {
            SummaryBO bo = BeanTransform.copyProperties(str, SummaryBO.class);

            AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
            adviceTableDTO.getConditions().add(Restrict.eq("summary.id", str.getId()));
            List<AdviceTable> deleteAdviceList = adviceTableSer.findByCis(adviceTableDTO);
            List<AdviceTableBO> adviceBOList = BeanTransform.copyProperties(deleteAdviceList, AdviceTableBO.class);
            bo.setAdviceTableBOList(adviceBOList);

            List<MeetDesignBO> meetDesignBOList = new ArrayList<>();
            DemocraticContentBO democraticContentBO = new DemocraticContentBO();
            if (null != str.getDemocraticContent()) {
                MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
                meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id", str.getDemocraticContent().getId()));
                List<MeetDesign> meetDesignList = meetDesignSer.findByCis(meetDesignDTO);
                meetDesignBOList = BeanTransform.copyProperties(meetDesignList, MeetDesignBO.class);

                democraticContentBO = BeanTransform.copyProperties(str.getDemocraticContent(), DemocraticContentBO.class);
            }
            democraticContentBO.setMeetDesignBO(meetDesignBOList == null || meetDesignBOList.size()==0 ? null : meetDesignBOList.get(0));
            bo.setDemocraticContentBO(democraticContentBO);

            listBO.add(bo);
        }

        return listBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SummaryBO addSummary(SummaryTO summaryTO) throws SerException {
        if (StringUtils.isBlank(summaryTO.getMeetTitleId())) {
            throw new SerException("会议议题id不能为空");
        }

        DemocraticContentDTO deDTO = new DemocraticContentDTO();
        deDTO.getConditions().add(Restrict.eq("id", summaryTO.getMeetTitleId()));
        List<DemocraticContent> deList = democraticContentSer.findByCis(deDTO);
        if (deList == null) {
            throw new SerException("还没有会议组织内容，不能添加会议纪要");
        }
        DemocraticContent democraticContent = deList.get(0);
        MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
        meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id", democraticContent.getId()));
        List<MeetDesign> meetDesignList = meetDesignSer.findByCis(meetDesignDTO);

        //设置会议编号拼音
        String chinese = "无";
        if (meetDesignList != null && meetDesignList.size() > 0) {
            chinese = meetDesignList.get(0).getMeetLevel();
        }
        chinese = ChineseConvert.getTargetNumber(chinese, chinese.length());
        if (StringUtils.isNotBlank(chinese) && chinese.length() > 2) {
            chinese = chinese.substring(0, 2);
        }
        //设置会议编号时间
        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        String time = year + month + day;

        //设置会议编号序号
        Long num = 1l;
        SummaryDTO sDTO = new SummaryDTO();
        sDTO.getSorts().add("meetNumber=desc");
        sDTO.getConditions().add(Restrict.like("meetNumber", "MZSH-" + chinese + "-" + time));
        List<Summary> tempList = super.findByCis(sDTO);
        if (tempList != null && tempList.size() > 0) {
            String tempNum = StringUtils.substringAfterLast(tempList.get(0).getMeetNumber(), "-");
            num = Long.parseLong(tempNum) + 1;
        }
        Summary summary = new Summary();
        summary.setTypeName("民主生活会");
        //MZSH-GS/MK-时间(年月日)-(序号)
        summary.setMeetNumber("MZSH-" + chinese + "-" + time + "-" + num);
        summary.setActualTime(meetDesignList.get(0).getMeetPlanTime());
        //实际参会人
        String[] acPersons = summaryTO.getActualPersons();
        StringBuffer acsb = new StringBuffer("");
        for( String p : acPersons ){
            acsb.append(p+";");
        }
        summary.setActualPerson( acsb.toString() );
        //新参会人
        String[] newPersons = summaryTO.getNewPersons();
        StringBuffer nsb = new StringBuffer("");
        for( String p : newPersons ){
            nsb.append(p+";");
        }
        summary.setNewPerson( nsb.toString() );
        //未参会人
        String[] nonePersons = summaryTO.getNonePersons();
        StringBuffer npsb = new StringBuffer("");
        for( String p : nonePersons ){
            npsb.append(p+";");
        }
        summary.setNonePerson( npsb.toString() );
        //参会人
        String[] peoples = summaryTO.getPeoples();
        StringBuffer psb = new StringBuffer("");
        for( String p : peoples){
            psb.append(p+";");
        }
        summary.setRecorder(summaryTO.getRecorder());
        summary.setPersonNum(summaryTO.getPersonNum());
        summary.setSelfCritic(summaryTO.getSelfCritic());
        summary.setSelfsummery(summaryTO.getSelfsummery());
        summary.setDemocraticContent(democraticContent);
        summary.setCreateTime(LocalDateTime.now());
        summary.setModifyTime(LocalDateTime.now());
        super.save(summary);

        //添加建议表
        List<AdviceTableTO> adviceTableTOS = summaryTO.getAdviceTableTOList();
        if (adviceTableTOS != null && adviceTableTOS.size() > 0) {
            List<AdviceTable> adviceTable = BeanTransform.copyProperties(adviceTableTOS, AdviceTable.class, true);
            adviceTable.stream().forEach(ad -> {
                ad.setSummary(summary);
            });
            adviceTableSer.save(adviceTable);
        }

        return BeanTransform.copyProperties(summary, SummaryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SummaryBO editSummary(SummaryTO summaryTO) throws SerException {
        if (StringUtils.isBlank(summaryTO.getId())) {
            throw new SerException("id不能为空");
        }
        if (StringUtils.isBlank(summaryTO.getMeetTitleId())) {
            throw new SerException("会议议题id不能为空");
        }

        Summary summary = super.findById(summaryTO.getId());
        DemocraticContent dbDeContent = summary.getDemocraticContent();
        if (!dbDeContent.getId().equals(summaryTO.getMeetTitleId())) {
            DemocraticContentDTO deDTO = new DemocraticContentDTO();
            deDTO.getConditions().add(Restrict.eq("id", summaryTO.getMeetTitleId()));
            List<DemocraticContent> deList = democraticContentSer.findByCis(deDTO);
            if (deList == null) {
                throw new SerException("还没有会议组织内容，不能编辑会议纪要");
            }
            DemocraticContent democraticContent = deList.get(0);
            MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
            meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id", democraticContent.getId()));
            List<MeetDesign> meetDesignList = meetDesignSer.findByCis(meetDesignDTO);

            //设置会议编号拼音
            String chinese = "";
            if (meetDesignList != null && meetDesignList.size() > 0) {
                chinese = meetDesignList.get(0).getMeetLevel();
            }
            chinese = ChineseConvert.getTargetNumber(chinese, chinese.length());
            if (StringUtils.isNotBlank(chinese) && chinese.length() > 2) {
                chinese = chinese.substring(0, 2);
            }
            //设置会议编号时间
            String year = String.valueOf(LocalDate.now().getYear());
            String month = String.valueOf(LocalDate.now().getMonthValue());
            String day = String.valueOf(LocalDate.now().getDayOfMonth());
            String time = year + month + day;

            //设置会议编号序号
            Long num = 1l;
            SummaryDTO sDTO = new SummaryDTO();
            sDTO.getSorts().add("meetNumber=desc");
            sDTO.getConditions().add(Restrict.like("meetNumber", "MZSH-" + chinese + "-" + time));
            List<Summary> tempList = super.findByCis(sDTO);
            if (tempList != null && tempList.size() > 0) {
                String tempNum = StringUtils.substringAfterLast(tempList.get(0).getMeetNumber(), "-");
                num = Long.parseLong(tempNum) + 1;
            }
            summary.setMeetNumber("MZSH-" + chinese + "-" + time + "-" + num);
            summary.setDemocraticContent(democraticContent);
        }

        summary.setTypeName(summaryTO.getTypeName());
        //MZSH-GS/MK-时间(年月日)-(序号)
        summary.setActualTime(LocalDateTime.parse(summaryTO.getActualTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //实际参会人
        String[] acPersons = summaryTO.getActualPersons();
        StringBuffer acsb = new StringBuffer("");
        for( String p : acPersons ){
            acsb.append(p+";");
        }
        summary.setActualPerson( acsb.toString() );
        //新参会人
        String[] newPersons = summaryTO.getNewPersons();
        StringBuffer nsb = new StringBuffer("");
        for( String p : newPersons ){
            nsb.append(p+";");
        }
        summary.setNewPerson( nsb.toString() );
        //未参会人
        String[] nonePersons = summaryTO.getNonePersons();
        StringBuffer npsb = new StringBuffer("");
        for( String p : nonePersons ){
            npsb.append(p+";");
        }
        summary.setNonePerson( npsb.toString() );
        //参会人
        String[] peoples = summaryTO.getPeoples();
        StringBuffer psb = new StringBuffer("");
        for( String p : peoples){
            psb.append(p+";");
        }
        summary.setPeople( psb.toString() );
        summary.setRecorder(summaryTO.getRecorder());
        summary.setPersonNum(summaryTO.getPersonNum());
        summary.setSelfCritic(summaryTO.getSelfCritic());
        summary.setSelfsummery(summaryTO.getSelfsummery());
        summary.setModifyTime(LocalDateTime.now());
        super.update(summary);

        //先删除建议表
        AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
        adviceTableDTO.getConditions().add(Restrict.eq("summary.id", summary.getId()));
        List<AdviceTable> deleteAdviceList = adviceTableSer.findByCis(adviceTableDTO);
        if (deleteAdviceList != null && deleteAdviceList.size() > 0) {
            adviceTableSer.remove(deleteAdviceList);
        }
        //再添加建议表
        List<AdviceTableTO> adviceTableTOS = summaryTO.getAdviceTableTOList();
        if (adviceTableTOS != null && adviceTableTOS.size() > 0) {
            List<AdviceTable> adviceTable = BeanTransform.copyProperties(adviceTableTOS, AdviceTable.class, true);
            adviceTable.stream().forEach(ad -> {
                ad.setSummary(summary);
            });
            adviceTableSer.save(adviceTable);
        }

        return BeanTransform.copyProperties(summary, SummaryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSummary(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Summary summary = super.findById(id);
        if (summary == null) {
            throw new SerException("id不存在");
        }
        //先删除建议表
        AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
        adviceTableDTO.getConditions().add(Restrict.eq("summary.id", summary.getId()));
        List<AdviceTable> deleteAdviceList = adviceTableSer.findByCis(adviceTableDTO);
        if (deleteAdviceList != null && deleteAdviceList.size() > 0) {
            adviceTableSer.remove(deleteAdviceList);
        }

        super.remove(id);
    }

    @Override
    public byte[] exportReport(ExportExcelCondition to) throws SerException {

        SummaryDTO dto = new SummaryDTO();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        if (!StringUtils.isNotBlank(to.getStartTime())) {
            start = LocalDateTime.parse( to.getStartTime());
        }
        if (!StringUtils.isNotBlank(to.getEndTime())) {
            end = LocalDateTime.parse( to.getEndTime());

        }
        dto.getConditions().add(Restrict.between("actualTime", new String[]{String.valueOf(start) , String.valueOf(end) }));

        List<Summary> list = super.findByCis(dto);
        List<SummaryExportExcel> toList = new ArrayList<SummaryExportExcel>();
        for (Summary model : list) {
            //TODO : 导出合并未解决 2017-06-06 tanghaixiang
            AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
            adviceTableDTO.getConditions().add(Restrict.eq("summary.id", model.getId()));
            List<AdviceTable> adviceList = adviceTableSer.findByCis(adviceTableDTO);

            if( adviceList != null && adviceList.size()>0 ){
                for( AdviceTable atb : adviceList){

                    SummaryExportExcel excel = new SummaryExportExcel();
                    modelConvertExcelValue( model , excel , atb);
                    toList.add(excel);
                }
            }else{
                AdviceTable atb = new AdviceTable();
                SummaryExportExcel excel = new SummaryExportExcel();
                modelConvertExcelValue( model , excel , atb);
                toList.add(excel);
            }
        }


        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
//        return null;
    }




    private SummaryExportExcel modelConvertExcelValue(Summary summary ,SummaryExportExcel summaryExportExcel , AdviceTable atb) throws SerException{
        //democr
        DemocraticContent democraticContent = (null == summary.getDemocraticContent()? new DemocraticContent():summary.getDemocraticContent());
        List<MeetDesign> meetDesignList = new ArrayList<>();
        StringBuffer  attentList = new StringBuffer("");
        if( null != democraticContent && null != democraticContent.getId()){
            MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
            meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id", democraticContent.getId()));
            meetDesignList = meetDesignSer.findByCis(meetDesignDTO);

            if( null != meetDesignList && meetDesignList.size()>0 ){
                for(MeetDesign md : meetDesignList){
                    AttenderDTO attenderDTO = new AttenderDTO();
                    attenderDTO.getConditions().add(Restrict.eq("meetDesign.id",md.getId()));
                    List<Attender> attendList = attenderSer.findByCis( attenderDTO );
                    attentList.append( attendList+";" );
                }
            }
        }


        summaryExportExcel.setTypeName( summary.getTypeName() );
        summaryExportExcel.setMeetNumber( summary.getMeetNumber()  );
        summaryExportExcel.setActualTime( String.valueOf( summary.getActualTime()) );
        summaryExportExcel.setMeetForm( (meetDesignList!= null && meetDesignList.size()>0) ? meetDesignList.get(0).getMeetForm():"" ); //meetDesign
        summaryExportExcel.setArea( (meetDesignList!= null && meetDesignList.size()>0) ? meetDesignList.get(0).getMeetArea():""  );  //meetDesign
        summaryExportExcel.setPlanEmp(attentList.toString() ); //attender
        summaryExportExcel.setActualPerson( summary.getActualPerson() );
        summaryExportExcel.setNewPerson( summary.getNewPerson() );
        summaryExportExcel.setNonePerson( summary.getNonePerson() );
        summaryExportExcel.setPersonNum( summary.getPersonNum() );
        summaryExportExcel.setSelfCritic( summary.getSelfCritic()  );
        summaryExportExcel.setAdvice( atb.getAdvice() );  //advice
        summaryExportExcel.setAdvicer( atb.getAdvicer() );   //advice
        summaryExportExcel.setSelfsummery( summary.getSelfsummery() );
        summaryExportExcel.setRecorder( summary.getRecorder() );   //sumer
        summaryExportExcel.setMeetHost( (meetDesignList!= null && meetDesignList.size()>0) ? meetDesignList.get(0).getHostMan():""  );  //meetDesign
        summaryExportExcel.setMeetOrganor( null != democraticContent ? democraticContent.getOrganizationMan():"" );   //democra
        return summaryExportExcel;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void leadExcel(List<SummaryExcelConvert> toList) throws SerException {
        List<AdviceTable> adviceTables = new ArrayList<>();
        String serNumber = (toList == null ? "" : toList.get(0).getSeriNumber());
        for (int i = 0 ;i<toList.size() ; i++ ) {
            SummaryExcelConvert se = toList.get(i);
            if (serNumber.equals(se.getSeriNumber()) ) {
                AdviceTable adviceTable = new AdviceTable();
                adviceTable.setAdvice(se.getAdvice());
                adviceTable.setAdvicer(se.getAdvicer());
                adviceTables.add(adviceTable);
            } else if( !serNumber.equals(se.getSeriNumber()) && i != toList.size()-1 ){
                addByImportExcel(se, adviceTables);

                adviceTables = new ArrayList<>();
                AdviceTable adviceTable = new AdviceTable();
                adviceTable.setAdvice(se.getAdvice());
                adviceTable.setAdvicer(se.getAdvicer());
                adviceTables.add(adviceTable);

            }

            if(  i == toList.size()-1 ){
                addByImportExcel(se, adviceTables);

            }

        }
    }


    /**
     * 导入处理
     *
     * @param se
     * @param adviceTables
     * @throws SerException
     */
    private void addByImportExcel(SummaryExcelConvert se, List<AdviceTable> adviceTables) throws SerException {
        //设置会议编号拼音
        String chinese = "无";
        chinese = ChineseConvert.getTargetNumber(chinese, chinese.length());
        if (StringUtils.isNotBlank(chinese) && chinese.length() > 2) {
            chinese = chinese.substring(0, 2);
        }
        //设置会议编号时间
        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        String time = year + month + day;

        //设置会议编号序号
        Long num = 1l;
        SummaryDTO sDTO = new SummaryDTO();
        sDTO.getSorts().add("meetNumber=desc");
        sDTO.getConditions().add(Restrict.like("meetNumber", "MZSH-" + chinese + "-" + time));
        List<Summary> tempList = super.findByCis(sDTO);
        if (tempList != null && tempList.size() > 0) {
            String tempNum = StringUtils.substringAfterLast(tempList.get(0).getMeetNumber(), "-");
            num = Long.parseLong(tempNum) + 1;
        }
        Summary summary = new Summary();
        summary.setTypeName("民主生活会");
        //MZSH-GS/MK-时间(年月日)-(序号)
        summary.setMeetNumber("MZSH-" + chinese + "-" + time + "-" + num);
        summary.setActualTime(LocalDateTime.parse(se.getDealTime()));
        summary.setActualPerson(se.getActualPerson());
        summary.setNewPerson(se.getNewPerson());
        summary.setNonePerson(se.getNonePerson());
        summary.setRecorder(se.getRecorder());
        summary.setPersonNum(se.getPersonNum());
        summary.setSelfCritic(se.getSelfCritic());
        summary.setSelfsummery(se.getSelfsummery());
        summary.setDemocraticContent(null);
        summary.setCreateTime(LocalDateTime.now());
        summary.setModifyTime(LocalDateTime.now());
        super.save(summary);

        //添加建议表
        if (adviceTables != null && adviceTables.size() > 0) {
            adviceTables.stream().forEach(ad -> {
                ad.setSummary(summary);
                ad.setCreateTime(LocalDateTime.now());
                ad.setModifyTime(LocalDateTime.now());
            });
            adviceTableSer.save(adviceTables);
        }


    }

}