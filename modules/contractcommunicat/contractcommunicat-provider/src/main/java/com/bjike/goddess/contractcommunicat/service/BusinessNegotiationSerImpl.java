package com.bjike.goddess.contractcommunicat.service;

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
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<BusinessNegotiationBO> list(BusinessNegotiationDTO dto) throws SerException {
        List<BusinessNegotiation> businessNegotiations = super.findByCis(dto);
        List<BusinessNegotiationBO> businessNegotiationBOS = BeanTransform.copyProperties(businessNegotiations,BusinessNegotiationBO.class);
        return businessNegotiationBOS;
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
        list.stream().forEach(str->{
            BusinessNegotiationExport export = BeanTransform.copyProperties(str,BusinessNegotiationExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
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