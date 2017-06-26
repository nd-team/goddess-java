package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.democraticmeet.bo.CusEmailBO;
import com.bjike.goddess.democraticmeet.bo.SummaryEmail;
import com.bjike.goddess.democraticmeet.dto.*;
import com.bjike.goddess.democraticmeet.entity.*;
import com.bjike.goddess.democraticmeet.excel.SummaryExportExcel;
import com.bjike.goddess.democraticmeet.to.CusEmailTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 客户邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "democraticmeetSerCache")
@Service
public class CusEmailSerImpl extends ServiceImpl<CusEmail, CusEmailDTO> implements CusEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DemocraticContentSer democraticContentSer;
    @Autowired
    private MeetDesignSer meetDesignSer;
    @Autowired
    private AdviceTableSer adviceTableSer;
    @Autowired
    private AttenderSer attenderSer;
    @Autowired
    private SummarySer summarySer;


    @Override
    public Long countCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        if(StringUtils.isNotBlank(cusEmailDTO.getMeetNumber())){
            cusEmailDTO.getConditions().add(Restrict.like("meetNumber",cusEmailDTO.getMeetNumber()));
        }
        if(StringUtils.isNotBlank(cusEmailDTO.getMeetLevel())){
            cusEmailDTO.getConditions().add(Restrict.like("meetLevel",cusEmailDTO.getMeetLevel()));
        }
        return super.count(cusEmailDTO);
    }

    @Cacheable
    @Override
    public List<CusEmailBO> listCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        if(StringUtils.isNotBlank(cusEmailDTO.getMeetNumber())){
            cusEmailDTO.getConditions().add(Restrict.like("meetNumber",cusEmailDTO.getMeetNumber()));
        }
        if(StringUtils.isNotBlank(cusEmailDTO.getMeetLevel())){
            cusEmailDTO.getConditions().add(Restrict.like("meetLevel",cusEmailDTO.getMeetLevel()));
        }
        cusEmailDTO.getSorts().add("createTime=desc");
        List<CusEmail> list = super.findByCis(cusEmailDTO, true);
        return BeanTransform.copyProperties(list, CusEmailBO.class);
    }

    @Override
    public CusEmailBO getCusEmailById(String id) throws SerException {
        CusEmail cusEmail = super.findById(id);
        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO addCusEmail(CusEmailTO cusEmailTO) throws SerException {

        if ( StringUtils.isBlank(cusEmailTO.getMeetNumber()) ) {
            throw new SerException("会议编号必须填");
        } if ( StringUtils.isBlank(cusEmailTO.getMeetLevel()) ) {
            throw new SerException("层面必须填");
        }if ( null == cusEmailTO.getSendObject() || cusEmailTO.getSendObject().length<= 0  ) {
            throw new SerException("发送对象必须填");
        }

        String[] sendObjectList = cusEmailTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.length > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CusEmail cusEmail = BeanTransform.copyProperties(cusEmailTO, CusEmail.class, true);


        //设置发送对象
        cusEmail.setSendObject(String.valueOf(emails));
        //设置会议编号
        cusEmail.setMeetNumber( cusEmailTO.getMeetNumber());
        //设置层面
        cusEmail.setMeetLevel(cusEmailTO.getMeetLevel());
        cusEmail.setCreateTime(LocalDateTime.now());
        cusEmail.setModifyTime(LocalDateTime.now());
        cusEmail.setCreatePersion(userAPI.currentUser().getUsername());

        super.save(cusEmail);

        //TODO 发邮件

        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO editCusEmail(CusEmailTO cusEmailTO) throws SerException {
        if ( StringUtils.isBlank(cusEmailTO.getId()) ) {
            throw new SerException("id必须填");
        } if ( StringUtils.isBlank(cusEmailTO.getMeetNumber()) ) {
            throw new SerException("会议编号必须填");
        } if ( StringUtils.isBlank(cusEmailTO.getMeetLevel()) ) {
            throw new SerException("层面必须填");
        }if ( null == cusEmailTO.getSendObject() || cusEmailTO.getSendObject().length<= 0  ) {
            throw new SerException("发送对象必须填");
        }

        String[] sendObjectList = cusEmailTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.length > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CusEmail cusEmail = super.findById( cusEmailTO.getId());


        //设置发送对象
        cusEmail.setSendObject(String.valueOf(emails));
        //设置会议编号
        cusEmail.setMeetNumber( cusEmailTO.getMeetNumber());
        //设置层面
        cusEmail.setMeetLevel(cusEmailTO.getMeetLevel());
        cusEmail.setModifyTime(LocalDateTime.now());
        cusEmail.setCreatePersion(userAPI.currentUser().getUsername());


        //TODO 发邮件
//        SummaryDTO dto = new SummaryDTO();
//        dto.getConditions().add(Restrict.eq("meetNumber", cusEmailTO.getMeetNumber() ));
//        List<Summary> list = summarySer.findByCis(dto);
//        List<SummaryEmail> listSummaryEmail = new ArrayList<>();
//        for (Summary model : list) {
//            SummaryEmail summaryEmail = new SummaryEmail();
//            summaryEmail = sendEmailModel(model , summaryEmail  );
//        }

        super.update(cusEmail);
        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
    }

    private SummaryEmail sendEmailModel (Summary summary , SummaryEmail summaryExportExcel ) throws SerException{
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
        AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
        adviceTableDTO.getConditions().add(Restrict.eq("summary.id", summary.getId()));
        List<AdviceTable> adviceList = adviceTableSer.findByCis(adviceTableDTO);
        StringBuffer advice = new StringBuffer("");
        StringBuffer advicer = new StringBuffer("");
        if( adviceList != null && adviceList.size()>0 ){
            for( AdviceTable ad : adviceList){
                advice.append(ad.getAdvicer()+" 评论意见："+ ad.getAdvice()+" &nbsp;</br>");
                advicer.append( ad.getAdvicer() +";");
            }
        }

        summaryExportExcel.setTitle( null != democraticContent ? democraticContent.getMeetTitle() : "" );
        summaryExportExcel.setTitleLevel(  (meetDesignList!= null && meetDesignList.size()>0) ? meetDesignList.get(0).getMeetLevel() : "" );
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
        summaryExportExcel.setAdvice( advice.toString() );  //advice
        summaryExportExcel.setAdvicer( advicer.toString() );   //advice
        summaryExportExcel.setSelfsummery( summary.getSelfsummery() );
        summaryExportExcel.setPerson( summary.getPeople() );
        summaryExportExcel.setRecorder( summary.getRecorder() );   //sumer
        summaryExportExcel.setMeetHost( (meetDesignList!= null && meetDesignList.size()>0) ? meetDesignList.get(0).getHostMan():""  );  //meetDesign
        summaryExportExcel.setMeetOrganor( null != democraticContent ? democraticContent.getOrganizationMan():"" );   //democra
        return summaryExportExcel;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCusEmail(String id) throws SerException {
        if ( StringUtils.isBlank(id ) ) {
            throw new SerException("id不能为空");
        }
        CusEmail cusEmail = super.findById( id );
        if( cusEmail == null ){
            throw new SerException("不存在该对象，删除失败");
        }
        super.remove(id);
    }


}