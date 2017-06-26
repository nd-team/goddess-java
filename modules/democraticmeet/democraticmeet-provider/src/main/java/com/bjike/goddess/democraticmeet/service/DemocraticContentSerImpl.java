package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.democraticmeet.api.MeetDesignAPI;
import com.bjike.goddess.democraticmeet.bo.AttenderBO;
import com.bjike.goddess.democraticmeet.bo.DemocraticContentBO;
import com.bjike.goddess.democraticmeet.bo.MeetDesignBO;
import com.bjike.goddess.democraticmeet.bo.MeetTitleOpinionBO;
import com.bjike.goddess.democraticmeet.dto.*;
import com.bjike.goddess.democraticmeet.entity.*;
import com.bjike.goddess.democraticmeet.to.DemocraticContentTO;
import com.bjike.goddess.democraticmeet.to.MeetDesignTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 民主生活会议组织内容业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "democraticmeetSerCache")
@Service
public class DemocraticContentSerImpl extends ServiceImpl<DemocraticContent, DemocraticContentDTO> implements DemocraticContentSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MeetDesignSer meetDesignSer;
    @Autowired
    private AttenderSer attenderSer;
    @Autowired
    private SummarySer summarySer;
    @Autowired
    private AdviceTableSer adviceTableSer;

    @Override
    public Long countDemocraticContent(DemocraticContentDTO democraticContentDTO) throws SerException {
        if(StringUtils.isNotBlank( democraticContentDTO.getMeetTitle())){
            democraticContentDTO.getConditions().add(Restrict.like("meetTitle",democraticContentDTO.getMeetTitle()));
        }
        if(StringUtils.isNotBlank( democraticContentDTO.getOrganizationMan())){
            democraticContentDTO.getConditions().add(Restrict.like("organizationMan",democraticContentDTO.getOrganizationMan()));
        }
        Long count = super.count( democraticContentDTO );
        return count;
    }

    @Override
    public DemocraticContentBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        DemocraticContent democraticContent = super.findById(id);
        DemocraticContentBO bo = BeanTransform.copyProperties(democraticContent, DemocraticContentBO.class );

        MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
        meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id",democraticContent.getId()));
        List<MeetDesign> meetDesignList = meetDesignSer.findByCis( meetDesignDTO );
        List<MeetDesignBO> meBO = BeanTransform.copyProperties( meetDesignList , MeetDesignBO.class);
        bo.setMeetDesignBO( meBO==null ? new MeetDesignBO():meBO.get(0) );

        if( bo.getMeetDesignBO() != null && null!= bo.getMeetDesignBO().getId()){
            AttenderDTO attenderDTO = new AttenderDTO();
            attenderDTO.getConditions().add(Restrict.eq("meetDesign.id",bo.getMeetDesignBO().getId()));
            List<Attender> attenders = attenderSer.findByCis( attenderDTO );
            List<AttenderBO> attenderBOList = BeanTransform.copyProperties( attenders , AttenderBO.class);

            bo.getMeetDesignBO().setAttenderBOs( attenderBOList );
        }


        return bo;
    }
    @Override
    public List<DemocraticContentBO> listDemocraticContent(DemocraticContentDTO democraticContentDTO) throws SerException {
        if(StringUtils.isNotBlank( democraticContentDTO.getMeetTitle())){
            democraticContentDTO.getConditions().add(Restrict.like("meetTitle",democraticContentDTO.getMeetTitle()));
        }
        if(StringUtils.isNotBlank( democraticContentDTO.getOrganizationMan())){
            democraticContentDTO.getConditions().add(Restrict.like("organizationMan",democraticContentDTO.getOrganizationMan()));
        }
        List<DemocraticContent> list = super.findByCis(democraticContentDTO, true);

        List<DemocraticContentBO> listBO = new ArrayList<>();
        for(DemocraticContent str : list) {
            DemocraticContentBO bo = BeanTransform.copyProperties(str, DemocraticContentBO.class);

            MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
            meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id",str.getId()));
            List<MeetDesign> meetDesignList = meetDesignSer.findByCis( meetDesignDTO );
            List<MeetDesignBO> meBO = BeanTransform.copyProperties( meetDesignList , MeetDesignBO.class);
            bo.setMeetDesignBO( meBO==null ? new MeetDesignBO():meBO.get(0) );

            if( bo.getMeetDesignBO() != null && null!= bo.getMeetDesignBO().getId()){
                AttenderDTO attenderDTO = new AttenderDTO();
                attenderDTO.getConditions().add(Restrict.eq("meetDesign.id",bo.getMeetDesignBO().getId()));
                List<Attender> attenders = attenderSer.findByCis( attenderDTO );
                List<AttenderBO> attenderBOList = BeanTransform.copyProperties( attenders , AttenderBO.class);

                bo.getMeetDesignBO().setAttenderBOs( attenderBOList );
            }

            listBO.add( bo );
        }

        return listBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemocraticContentBO addDemocraticContent(DemocraticContentTO democraticContentTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        DemocraticContent democraticContent = BeanTransform.copyProperties(democraticContentTO,DemocraticContent.class,true);
        democraticContent.setMeetType("民主生活会");
        democraticContent.setOrganizationMan(userBO.getUsername());
        democraticContent.setCreateTime(LocalDateTime.now());
        democraticContent.setModifyTime(LocalDateTime.now());
        super.save( democraticContent );

        //存多个MeetDesign
        List<MeetDesignTO> listMeet = democraticContentTO.getMeetDesignTOList();
        if( listMeet != null && listMeet.size()>0 ){
            meetCheck(listMeet);

            for(MeetDesignTO p : listMeet){
                MeetDesign meetDesignList = BeanTransform.copyProperties(p,MeetDesign.class,true);
                meetDesignList.setCreateTime(LocalDateTime.now());
                meetDesignList.setModifyTime(LocalDateTime.now());
                meetDesignList.setDemocraticContent( democraticContent );
                meetDesignSer.save( meetDesignList );

                //存多个参会人员
                String [] persons = p.getAttenders();
                List<Attender> attenderList = new ArrayList<>();
                for(String person:persons){
                    Attender attender = new Attender();
                    attender.setAttenderName( person);
                    attender.setCreateTime( LocalDateTime.now());
                    attender.setModifyTime( LocalDateTime.now());
                    attender.setMeetDesign( meetDesignList );
                    attenderList.add( attender );
                }
                if( attenderList != null && attenderList.size()>0 ){
                    attenderSer.save( attenderList );
                }
            }

        }
        //TODO:发邮箱未做  tanghaixiang 2017-06-03
        return BeanTransform.copyProperties(democraticContent, DemocraticContentBO.class);
    }

    public void meetCheck(List<MeetDesignTO> meetDesignTOS) throws SerException{
        for(MeetDesignTO me : meetDesignTOS){
            if(StringUtils.isBlank( me.getMeetLevel())){
                throw new SerException("会议层面不能为空");
            }
            if(null == me.getMeetTitles() ){
                throw new SerException("计划参会岗位不能为空");
            }
            if (null == me.getAttenders()) {
                throw new SerException("计划参会人员不能为空");
            }
            if (StringUtils.isBlank( me.getMeetPlanTime())) {
                throw new SerException("计划会议时间不能为空");
            }
            if (StringUtils.isBlank( me.getHostMan())) {
                throw new SerException("会议主持人不能为空");
            }
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DemocraticContentBO editDemocraticContent(DemocraticContentTO democraticContentTO) throws SerException {
        if(StringUtils.isBlank(democraticContentTO.getId())){
            throw new SerException("id不能为空");
        }
        DemocraticContent dbDemocraticContent = super.findById( democraticContentTO.getId() );

        MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
        meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id",dbDemocraticContent.getId()));
        List<MeetDesign> dbMeetDesignList = meetDesignSer.findByCis( meetDesignDTO );

        List<Attender> deleteAttentList = new ArrayList<>();
        for(MeetDesign md : dbMeetDesignList){
            AttenderDTO attenderDTO = new AttenderDTO();
            attenderDTO.getConditions().add(Restrict.eq("meetDesign.id",md.getId()));
            List<Attender> attendList = attenderSer.findByCis( attenderDTO );
            deleteAttentList.addAll( attendList );
        }
        //删除attend
        if(deleteAttentList!= null && deleteAttentList.size()>0 ){
            attenderSer.remove( deleteAttentList );
        }
        //删除design
        if(dbMeetDesignList!= null && dbMeetDesignList.size()>0 ){
            meetDesignSer.remove( dbMeetDesignList );
        }

        //修改
        dbDemocraticContent.setMeetType( democraticContentTO.getMeetType() );
        dbDemocraticContent.setMeetTitle( democraticContentTO.getMeetTitle() );
        dbDemocraticContent.setMeetContent( democraticContentTO.getMeetContent());
        dbDemocraticContent.setRemark( democraticContentTO.getRemark() );
        dbDemocraticContent.setModifyTime(LocalDateTime.now());
        super.update( dbDemocraticContent );


        //重新添加
        reAdd(democraticContentTO , dbDemocraticContent );

        //TODO:发邮箱未做 tanghaixiang 2017-06-03

        return BeanTransform.copyProperties(dbDemocraticContent, DemocraticContentBO.class);
    }

    private void reAdd(DemocraticContentTO democraticContentTO , DemocraticContent dbDemocraticContent ) throws SerException{
        //存多个MeetDesign
        List<MeetDesignTO> listMeet = democraticContentTO.getMeetDesignTOList();
        if( listMeet != null && listMeet.size()>0 ){
            meetCheck(listMeet);

            for(MeetDesignTO p : listMeet){
                MeetDesign meetDesignList = BeanTransform.copyProperties(p,MeetDesign.class,true);
                meetDesignList.setCreateTime(LocalDateTime.now());
                meetDesignList.setModifyTime(LocalDateTime.now());
                meetDesignList.setDemocraticContent( dbDemocraticContent );
                meetDesignSer.save( meetDesignList );

                //存多个参会人员
                String [] persons = p.getAttenders();
                List<Attender> attenderList = new ArrayList<>();
                for(String person:persons){
                    Attender attender = new Attender();
                    attender.setAttenderName( person);
                    attender.setCreateTime( LocalDateTime.now());
                    attender.setModifyTime( LocalDateTime.now());
                    attender.setMeetDesign( meetDesignList );
                    attenderList.add( attender );
                }
                if( attenderList != null && attenderList.size()>0 ){
                    attenderSer.save( attenderList );
                }
            }

        }

    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDemocraticContent(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }

        DemocraticContent dbDemocraticContent = super.findById( id );
        if( dbDemocraticContent != null ){

            MeetDesignDTO meetDesignDTO = new MeetDesignDTO();
            meetDesignDTO.getConditions().add(Restrict.eq("democraticContent.id",dbDemocraticContent.getId()));
            List<MeetDesign> dbMeetDesignList = meetDesignSer.findByCis( meetDesignDTO );

            List<Attender> deleteAttentList = new ArrayList<>();
            for(MeetDesign md : dbMeetDesignList){
                AttenderDTO attenderDTO = new AttenderDTO();
                attenderDTO.getConditions().add(Restrict.eq("meetDesign.id",md.getId()));
                List<Attender> attendList = attenderSer.findByCis( attenderDTO );
                deleteAttentList.addAll( attendList );
            }
            //删除attend
            if(deleteAttentList!= null && deleteAttentList.size()>0 ){
                attenderSer.remove( deleteAttentList );
            }
            //删除design
            if(dbMeetDesignList!= null && dbMeetDesignList.size()>0 ){
                meetDesignSer.remove( dbMeetDesignList );
            }

            SummaryDTO summaryDTO = new SummaryDTO();
            summaryDTO.getConditions().add(Restrict.eq("democraticContent.id", dbDemocraticContent.getId()));
            List<Summary> summaryList = summarySer.findByCis( summaryDTO );
            if( summaryList != null && summaryList.size()>0 ){
                for(Summary s:summaryList){

                    //先删除建议表
                    AdviceTableDTO adviceTableDTO = new AdviceTableDTO();
                    adviceTableDTO.getConditions().add(Restrict.eq("summary.id",s.getId()));
                    List<AdviceTable> deleteAdviceList = adviceTableSer.findByCis( adviceTableDTO );
                    if( deleteAdviceList != null && deleteAdviceList.size()>0 ){
                        adviceTableSer.remove( deleteAdviceList );
                    }
                }

                summarySer.remove( summaryList );

            }



            super.remove( id );
        }
    }

    @Override
    public List<String> listTitle() throws SerException {
        List<String> titles = new ArrayList<>();
        String [] fields = new String[]{"meetTitle"};
        String sql = " select meetTitle from democraticmeet_democraticcontent group by meetTitle";
        List<DemocraticContent> list = super.findBySql(sql , DemocraticContent.class , fields);
        titles = list.stream().map(DemocraticContent::getMeetTitle).collect(Collectors.toList());
        return titles;
    }

    @Override
    public List<MeetTitleOpinionBO> listOpinionTitle() throws SerException {

        List<MeetTitleOpinionBO> titles = new ArrayList<>();
        String [] fields = new String[]{"id","meetTitle"};
        String sql = " select id ,meetTitle from democraticmeet_democraticcontent " +
                " where id not in ( " +
                " select summary.democraticContent_id from democraticmeet_summary as summary " +
                " )";
        List<DemocraticContent> list = super.findBySql(sql , DemocraticContent.class , fields);
        list.stream().forEach(str->{
            MeetTitleOpinionBO bo = new MeetTitleOpinionBO();
            bo.setTitle( str.getMeetTitle());
            bo.setId( str.getId() );
            titles.add( bo );
        });
        return titles;
    }
}