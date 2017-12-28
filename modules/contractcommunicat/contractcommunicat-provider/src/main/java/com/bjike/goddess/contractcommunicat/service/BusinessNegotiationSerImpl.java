package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationExport;
import com.bjike.goddess.contractcommunicat.excel.BusinessNegotiationTemplateExcel;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NestedTransactionNotSupportedException;
import org.springframework.transaction.annotation.Transactional;

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
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<BusinessNegotiationBO> list(BusinessNegotiationDTO dto) throws SerException {
        search(dto);
        List<BusinessNegotiation> businessNegotiations = super.findByCis(dto);
        List<BusinessNegotiationBO> businessNegotiationBOS = BeanTransform.copyProperties(businessNegotiations,BusinessNegotiationBO.class);
        return businessNegotiationBOS;
    }
    public List<BusinessNegotiationBO> search(BusinessNegotiationDTO dto)throws SerException{
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
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessNegotiationBO insert(BusinessNegotiationTO to) throws SerException {
        BusinessNegotiation businessNegotiation = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
        businessNegotiation.setCreateTime(LocalDateTime.now());
        super.save(businessNegotiation);
        BusinessNegotiationBO bo = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
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
}