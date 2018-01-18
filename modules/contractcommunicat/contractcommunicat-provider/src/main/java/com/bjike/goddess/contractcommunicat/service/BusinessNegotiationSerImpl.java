package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.dao.BusinessNegotiationRep;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.enums.GuideAddrStatus;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationExport;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationTemplateExcel;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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

    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    public void getCusPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
//        Boolean permission = cusPermissionSer.getCusPermission("1");
//
//        if (!permission) {
//            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
//        }
//    }
    }

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }



    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("businessNegotiation");
        obj.setDescribesion("商务洽谈");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        obj = new SonPermissionObject();
        obj.setName("businessNegotiationCollect");
        obj.setDescribesion("商务洽谈汇总");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("skillLibrary");
        obj.setDescribesion("谈判技巧库");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);
        return list;
    }

    @Override
    public BusinessNegotiationBO getOne(String id) throws SerException {
        BusinessNegotiation businessNegotiation = super.findById(id);
        BusinessNegotiationBO businessNegotiationBO = BeanTransform.copyProperties(businessNegotiation, BusinessNegotiationBO.class);
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
        if (StringUtils.isBlank(dto.getSearch())) { //当search为空时，Specification查询方式无法获取数据；暂时采用这种方式实现无条件查询
            List<BusinessNegotiationBO> bo = BeanTransform.copyProperties(super.findByPage(dto), BusinessNegotiationBO.class);
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
    /*public List<BusinessNegotiationBO> search(BusinessNegotiationDTO dto)throws SerException{
        //市场编号
        if(StringUtils.isNotBlank(dto.getMarketNum())){
            dto.getConditions().add(Restrict.like("marketNum",dto.getMarketNum()));
        }
        //洽谈公司
        if(StringUtils.isNotBlank(dto.getDiscussCompany())){
            dto.getConditions().add(Restrict.like("discussCompany",dto.getDiscussCompany()));
        }
        //洽谈对象
        if(StringUtils.isNotBlank(dto.getDiscussObject())){
            dto.getConditions().add(Restrict.like("discussObject",dto.getDiscussObject()));
        }
        //洽谈人
        if(StringUtils.isNotBlank(dto.getDiscussPeople())){
            dto.getConditions().add(Restrict.like("discussPeople",dto.getDiscussPeople()));
        }
        //地区
        if(StringUtils.isNotBlank(dto.getArea())){
            dto.getConditions().add(Restrict.like("area",dto.getArea()));
        }
        //内部项目编号
        if(StringUtils.isNotBlank(dto.getProjectNum())){
            dto.getConditions().add(Restrict.like("projectNum",dto.getProjectNum()));
        }
        List<BusinessNegotiation> negotiations = super.findByCis(dto);
        List<BusinessNegotiationBO> boList = BeanTransform.copyProperties(negotiations,BusinessNegotiationBO.class);
        return boList;
    }*/

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
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessNegotiation businessNegotiation = super.findById(to.getId());
            LocalDateTime createTime = businessNegotiation.getCreateTime();
            businessNegotiation = BeanTransform.copyProperties(to, BusinessNegotiation.class, true);
            businessNegotiation.setCreateTime(createTime);
            businessNegotiation.setModifyTime(LocalDateTime.now());
            BusinessNegotiationBO bo = BeanTransform.copyProperties(businessNegotiation, BusinessNegotiationBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public BusinessNegotiationBO importExcel(List<BusinessNegotiationTO> businessNegotiationTOS) throws SerException {
        List<BusinessNegotiation> businessNegotiations = new ArrayList<>(businessNegotiationTOS.size());
        for (BusinessNegotiationTO to : businessNegotiationTOS) {
            BusinessNegotiation entity = BeanTransform.copyProperties(to, BusinessNegotiation.class, true);
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

//        List<BusinessNegotiation> list = super.findByCis(dto);
//        List<BusinessNegotiationExport> exports = new ArrayList<>();
//        list.stream().forEach(str -> {
//            BusinessNegotiationExport export = BeanTransform.copyProperties(str, BusinessNegotiationExport.class,
//                    "discussPrepare", "discuss", "attainDiscussIdea", "discussProblem",
//                    "soundRecord", "hasProject", "marketCost", "marketFor",
//                    "continueFollowUp", "closedLoop", "needAssist", "assistLetter", "produceTrip");
//            //是否有洽谈准备
//            if (null != str.getDiscussPrepare()) {
//                if (str.getDiscussPrepare().equals("是")) {
//                    export.setDiscussPrepare(true);
//                } else {
//                    export.setDiscussPrepare(false);
//                }
//            }
//            //是否洽谈
//            if (null != str.getDiscuss()) {
//                if (str.getDiscuss().equals("是")) {
//                    export.setDiscuss(true);
//                } else {
//                    export.setDiscuss(false);
//                }
//            }
//            //是否达到洽谈目的
//            if (null != str.getAttainDiscussIdea()) {
//                if (str.getAttainDiscussIdea().equals("是")) {
//                    export.setAttainDiscussIdea(true);
//                } else {
//                    export.setAttainDiscussIdea(false);
//                }
//            }
//            //是否有洽谈到其他问题
//            if (null != str.getDiscussProblem()) {
//                if (str.getDiscussProblem().equals("是")) {
//                    export.setDiscussProblem(true);
//                } else {
//                    export.setDiscussProblem(false);
//                }
//            }
//
//            //是否有录音
//            if (null != str.getSoundRecord()) {
//                if (str.getSoundRecord().equals("是")) {
//                    export.setSoundRecord(true);
//                } else {
//                    export.setSoundRecord(false);
//                }
//            }
//            //是否转入合同管理-已立项
//            if (null != str.getHasProject()) {
//                if (str.getHasProject().equals("是")) {
//                    export.setHasProject(true);
//                } else {
//                    export.setHasProject(false);
//                }
//            }
//            //是否转入合同管理-市场费用
//            if (null != str.getMarketCost()) {
//                if (str.getMarketCost().equals("是")) {
//                    export.setMarketCost(true);
//                } else {
//                    export.setMarketCost(false);
//                }
//            }
//            //是否转换市场招待
//            if (null != str.getMarketFor()) {
//                if (str.getMarketFor().equals("是")) {
//                    export.setMarketFor(true);
//                } else {
//                    export.setMarketFor(false);
//                }
//            }
//            //是否持续跟进
//            if (null != str.getContinueFollowUp()) {
//                if (str.getContinueFollowUp().equals("是")) {
//                    export.setContinueFollowUp(true);
//                } else {
//                    export.setContinueFollowUp(false);
//                }
//            }
//            //是否闭环
//            if (null != str.getClosedLoop()) {
//                if (str.getClosedLoop().equals("是")) {
//                    export.setClosedLoop(true);
//                } else {
//                    export.setClosedLoop(false);
//                }
//            }
//            //是否需要协助
//            if (null != str.getNeedAssist()) {
//                if (str.getNeedAssist().equals("是")) {
//                    export.setNeedAssist(true);
//                } else {
//                    export.setNeedAssist(false);
//                }
//            }
//            //是否已发协助函
//            if (null != str.getAssistLetter()) {
//                if (str.getAssistLetter().equals("是")) {
//                    export.setAssistLetter(true);
//                } else {
//                    export.setAssistLetter(false);
//                }
//            }
//            //是否产生路费
//            if (null != str.getProduceTrip()) {
//                if (str.getProduceTrip().equals("是")) {
//                    export.setProduceTrip(true);
//                } else {
//                    export.setProduceTrip(false);
//                }
//            }
//
//        });
//        Excel excel = new Excel(0, 2);
//        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
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
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, excel);
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