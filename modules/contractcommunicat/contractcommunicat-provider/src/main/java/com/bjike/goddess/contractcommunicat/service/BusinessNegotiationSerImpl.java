package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.dao.BusinessNegotiationRep;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationExport;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationTemplateExcel;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务洽谈业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class BusinessNegotiationSerImpl extends ServiceImpl<BusinessNegotiation, BusinessNegotiationDTO> implements BusinessNegotiationSer {
    @Autowired
    BusinessNegotiationRep businessNegotiationRep;
    @Autowired
    MessageAPI messageAPI;
    @Autowired
    PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    InternalContactsAPI internalContactsAPI;
//    @Autowired
//    CommerceMemberAPI memberAPI;

    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public BusinessNegotiationBO getOne(String id) throws SerException {
        BusinessNegotiation businessNegotiation = super.findById(id);
        BusinessNegotiationBO businessNegotiationBO = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
        return businessNegotiationBO;
    }

    @Override
    public Long count(BusinessNegotiationDTO dto) throws SerException {
        if (StringUtils.isBlank(dto.getSearch())) {
            return super.count(dto);
        }
//        Long count = super.count(dto);
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotBlank(dto.getSearch())) {
                    predicates.add(criteriaBuilder.like(root.get("marketNum"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("discussCompany"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("discussObject"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("discussPeople"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("projectNum"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("area"), dto.getSearch()));
                }
                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return rep.count(specification);
    }

    @Override
    public List<BusinessNegotiationBO> list(BusinessNegotiationDTO dto) throws SerException {
        /*String sql = "select * from contractcommunicat_businessnegotiation";
        if (StringUtils.isNotBlank(dto.getSearch())) {
            sql += " where (marketNum like '%"+ dto.getSearch() +"%') or (discussCompany like '%"+ dto.getSearch()
                    +"%') or (discussObject like '%"+ dto.getSearch() +"%') or " +
                    "(discussPeople like '%"+ dto.getSearch() +"%') or (area like '%"+ dto.getSearch() +"%') or" +
                    "(projectNum like '%"+ dto.getSearch() +"%')";
        }
        String[] fields = {"amountInvolved", "area", "assistContent", "assistDept", "assistLetter", "assistLetterNum", "attainDiscussIdea",
                "businessType", "closedLoop", "continueFollowUp", "customerNum", "department", "discuss", "discussCompany",
                "discussIdea", "discussObject", "discussPeople", "discussPlace", "discussPrepare", "discussProblem", "discussResult",
                "discussWay", "hasProject", "id", "innerProject", "marketCost", "marketFor", "marketNum","needAssist", "planFollowUp",
                "planNegotiationTime", "practiceNegotiationTime", "problemBelong", "problemCategorize", "produceTrip", "projectNum", "recorder",
                "rounds", "soundRecord", "subjectRecord", "trip"};*/

        if (StringUtils.isBlank(dto.getSearch())) { //当search为空时，Specification查询方式无法获取数据；暂时采用这种方式实现无条件查询
            List<BusinessNegotiationBO> bo = BeanTransform.copyProperties(super.findByCis(dto), BusinessNegotiationBO.class);
            return bo;
        }
        //原有的查询无法实现OR多个条件，故采用Specification方式实现
        Specification specification = new Specification<BusinessNegotiation>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotBlank(dto.getSearch())) {
                    predicates.add(criteriaBuilder.like(root.get("marketNum"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("discussCompany"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("discussObject"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("discussPeople"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("projectNum"), dto.getSearch()));
                    predicates.add(criteriaBuilder.like(root.get("area"), dto.getSearch()));
                }

                return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        PageRequest pr = new PageRequest(dto.getPage(),dto.getLimit());
        Page<BusinessNegotiation> businessNegotiations =  rep.findAll(specification, pr);
        List<BusinessNegotiationBO> businessNegotiationBOS = BeanTransform.copyProperties(businessNegotiations.getContent(), BusinessNegotiationBO.class);
        return businessNegotiationBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessNegotiationBO insert(BusinessNegotiationTO to) throws SerException {


        BusinessNegotiation businessNegotiation = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
        businessNegotiation.setCreateTime(LocalDateTime.now());
        super.save(businessNegotiation);
        BusinessNegotiationBO bo = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
        //发送邮件
        if (to.getNeedAssist()) {   //发送协助函
            String department = to.getAssistDept();
            if (StringUtils.isBlank(department)){
                throw new SerException("需协助部门不能为空");
            }
            //            List<DepartmentDetailBO> bos = departmentDetailAPI.view(new DepartmentDetailDTO());
            //            commonalityAPI.findByDepartment()
            String manager = positionDetailUserAPI.findManageByDepart(to.getAssistDept());
//                manager = "zhangsan";
            if (null != manager) {
                List<String> emails = internalContactsAPI.getEmails(new String[]{manager});
//                    emails.add("wenxianm@163.com");
                sendAssist("商务洽谈协助函", to.getAssistContent(), (String[])emails.toArray(new String[emails.size()]), to.getId(), null);
            }
        }

        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessNegotiationBO edit(BusinessNegotiationTO to) throws SerException {
        if(StringUtils.isNotBlank(to.getId())){
            BusinessNegotiation businessNegotiation = super.findById(to.getId());
            LocalDateTime createTime =businessNegotiation.getCreateTime();
            businessNegotiation = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
            businessNegotiation.setCreateTime(createTime);
            businessNegotiation.setModifyTime(LocalDateTime.now());
            BusinessNegotiationBO bo = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if(StringUtils.isNotBlank(id)){
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public BusinessNegotiationBO importExcel(List<BusinessNegotiationTO> businessNegotiationTOS) throws SerException {
        List<BusinessNegotiation> businessNegotiations = new ArrayList<>(businessNegotiationTOS.size());
        for(BusinessNegotiationTO to:businessNegotiationTOS){
            BusinessNegotiation entity = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
            businessNegotiations.add(entity);
        }
        super.save(businessNegotiations);
        //发送协助函
        for(BusinessNegotiation to:businessNegotiations){
            if (to.getNeedAssist()) {   //发送协助函
                String department = to.getAssistDept();
                if (StringUtils.isBlank(department)){
                    throw new SerException("需协助部门不能为空");
                }
                //            List<DepartmentDetailBO> bos = departmentDetailAPI.view(new DepartmentDetailDTO());
                //            commonalityAPI.findByDepartment()
                String manager = positionDetailUserAPI.findManageByDepart(to.getAssistDept());
//                manager = "zhangsan";
                if (null != manager) {
                    List<String> emails = internalContactsAPI.getEmails(new String[]{manager});
//                    emails.add("wenxianm@163.com");
                    sendAssist("商务洽谈协助函", to.getAssistContent(), (String[])emails.toArray(new String[emails.size()]), to.getId(), null);
                }



            }
        }

        BusinessNegotiationBO bo = BeanTransform.copyProperties(new BusinessNegotiation(),BusinessNegotiationBO.class);
        return bo;
    }

    @Override
    public byte[] exportExcel(BusinessNegotiationDTO dto) throws SerException {
        List<BusinessNegotiationExport> exports = new ArrayList<>();
        List<BusinessNegotiation> list = super.findByCis(dto);
        if(list != null && list.size()>0){
            for(BusinessNegotiation negotiation:list){
                BusinessNegotiationExport export = BeanTransform.copyProperties(negotiation,BusinessNegotiationExport.class,
                        "discussPrepare","discuss","attainDiscussIdea","discussProblem","soundRecord",
                        "hasProject","marketCost","marketFor","continueFollowUp","closedLoop",
                        "needAssist","assistLetter","produceTrip");
                //是否有洽谈准备
                export.setDiscussPrepare(boolToString(negotiation.getDiscussPrepare()));
                //是否洽谈
                export.setDiscuss(boolToString(negotiation.getDiscuss()));
                //是否达到洽谈目的
                export.setAttainDiscussIdea(boolToString(negotiation.getAttainDiscussIdea()));
                //是否有录音
                export.setDiscussProblem(boolToString(negotiation.getDiscussProblem()));
                //是否有洽谈到其他问题
                export.setSoundRecord(boolToString(negotiation.getSoundRecord()));
                //是否转入合同管理-已立项
                export.setHasProject(boolToString(negotiation.getHasProject()));
                //是否转入合同管理-市场费用
                export.setMarketCost(boolToString(negotiation.getMarketCost()));
                //是否转换市场招待
                export.setMarketFor(boolToString(negotiation.getMarketFor()));
                //是否持续跟进
                export.setContinueFollowUp(boolToString(negotiation.getContinueFollowUp()));
                //是否闭环
                export.setClosedLoop(boolToString(negotiation.getClosedLoop()));
                //是否需要协助
                export.setNeedAssist(boolToString(negotiation.getNeedAssist()));
                //是否已发协助函
                export.setAssistLetter(boolToString(negotiation.getAssistLetter()));
                //是否产生路费
                export.setProduceTrip(boolToString(negotiation.getProduceTrip()));
                exports.add(export);
            }
        }

        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
    }

    private String boolToString(Boolean bool)throws SerException{
        String str= "";
        if(bool != null){
            if(bool){
                str = "是";
            }else {
                str = "否";
            }
        }
        return str;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        List<BusinessNegotiationTemplateExcel> templateExcels = new ArrayList<>();
        BusinessNegotiationTemplateExcel templateExcel = new BusinessNegotiationTemplateExcel();
        templateExcel.setMarketNum("test");
        templateExcel.setProjectNum("test");
        templateExcel.setInnerProject("test");
        templateExcel.setBusinessType("test");
        templateExcel.setArea("广州");
        templateExcel.setDepartment("项目组");
        templateExcel.setProblemBelong("市场信息管理");
        templateExcel.setRounds(1);
        templateExcel.setPlanNegotiationTime(LocalDate.now());
        templateExcel.setPracticeNegotiationTime(LocalDate.now());
        templateExcel.setDiscussPeople("test");
        templateExcel.setRecorder("test");
        templateExcel.setCustomerNum("test");
        templateExcel.setDiscussCompany("test");
        templateExcel.setDiscussObject("test");
        templateExcel.setDiscussWay("微信");
        templateExcel.setDiscussPlace("test");
        templateExcel.setProblemCategorize("市场信息初步分析");
        templateExcel.setDiscussIdea("test");
        templateExcel.setDiscussPrepare("是");
        templateExcel.setAmountInvolved("12");
        templateExcel.setDiscuss("是");
        templateExcel.setDiscussResult("test");
        templateExcel.setAttainDiscussIdea("是");
        templateExcel.setDiscussProblem("是");
        templateExcel.setSubjectRecord("test");
        templateExcel.setSoundRecord("是");
        templateExcel.setHasProject("是");
        templateExcel.setMarketCost("是");
        templateExcel.setMarketFor("是");
        templateExcel.setContinueFollowUp("是");
        templateExcel.setClosedLoop("是");
        templateExcel.setNeedAssist("是");
        templateExcel.setAssistDept("test");
        templateExcel.setAssistContent("test");
        templateExcel.setAssistLetter("是");
        templateExcel.setAssistLetterNum("test");
        templateExcel.setPlanFollowUp(LocalDate.now());
        templateExcel.setProduceTrip("是");
        templateExcel.setTrip(0d);

        templateExcels.add(templateExcel);
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels,excel);
        return bytes;
    }

    @Override
    public void addPrepareNegotiation(String id, String skillLibraryId) throws SerException {
        BusinessNegotiation entity = super.findById(id);
        if (entity == null) {
            throw new SerException("查询实体不存在");
        }
        entity.setSkillLibraryId(skillLibraryId);
        entity.setRounds(entity.getRounds() + 1);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void sendAssist(String title, String content, String[] receiver, String id, String type) throws SerException {
//        MessageTO messageTO = new MessageTO();
//        messageTO.setContent( content );
//        messageTO.setTitle("定时发送项目实施进度管理汇总");
//        messageTO.setMsgType(MsgType.SYS);
//        messageTO.setSendType(SendType.EMAIL);
//        messageTO.setRangeType(RangeType.SPECIFIED);
//
//        messageTO.setReceivers(receiver);
//        messageAPI.send(messageTO);
        if (receiver.length == 0) {
            return;
        }
        Email email = new Email(title, content, receiver, id, type);
        new Thread(email).start();
    }

    @Transactional(rollbackFor = SerException.class)
    public void updateNegotiationAssist(String id) throws SerException{
        BusinessNegotiation entity = super.findById(id);
        entity.setAssistLetter(true);
        super.update(entity);
    }

    //定义内部线程类，用于异步发送邮件
    public class Email implements Runnable {
//        @Autowired
//        MessageAPI messageAPI;

        private String title;

        private String content;

        private String[] receivers;

        private String id;

        private String type;

        Email(String title, String content, String[] receivers, String id, String type) {
            this.title = title;
            this.content = content;
            this.receivers = receivers;
            this.id = id;
            this.type = type;

        }
        @Override
        public void run() {
            try {
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(this.content );
                messageTO.setTitle(this.title);
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                messageTO.setReceivers(this.receivers);
//                Thread.sleep(5000);
                System.out.println("开始发送邮件");
                messageAPI.send(messageTO);

                updateNegotiationAssist(this.id);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}