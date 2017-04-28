package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.ContactObjectBO;
import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.capability.api.CooperCapabilityAPI;
import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.entity.TalkDetail;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 洽谈详情业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class TalkDetailSerImpl extends ServiceImpl<TalkDetail, TalkDetailDTO> implements TalkDetailSer {

    @Autowired
    private CooperCapabilityAPI cooperCapabilityAPI;

    @Override
    public Long countInter(TalkDetailDTO talkDetailDTO) throws SerException {
        Long count =  super.count(talkDetailDTO);
        return count;
    }

    @Override
    public TalkDetailBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        TalkDetail talkDetail = super.findById(id);

        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }
    
    @Override
    public List<TalkDetailBO> listTalkDetail(TalkDetailDTO talkDetailDTO) throws SerException {
        List<TalkDetail> list = super.findByCis(talkDetailDTO, true);

        return BeanTransform.copyProperties(list, TalkDetailBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TalkDetailBO addTalkDetail(TalkDetailTO talkDetailTO) throws SerException {
        TalkDetail talkDetail = null;
        try {
            talkDetail = BeanTransform.copyProperties(talkDetailTO,TalkDetail.class,true);
            talkDetail.setCreateTime(LocalDateTime.now());
            super.save( talkDetail );
        } catch (SerException e) {
            throw new SerException("洽谈详情添加失败");
        }
        return BeanTransform.copyProperties(talkDetail, TalkDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TalkDetailBO editTalkDetail(TalkDetailTO talkDetailTO) throws SerException {
        TalkDetail talkDetailTarget = null;
        try {
            TalkDetail talkDetail = BeanTransform.copyProperties(talkDetailTO,TalkDetail.class,true);

            talkDetailTarget = super.findById( talkDetailTO.getId() );
            BeanUtils.copyProperties(talkDetail,talkDetailTarget,"id","createTime");
            talkDetailTarget.setModifyTime(LocalDateTime.now());
            super.save(talkDetailTarget );
        } catch (SerException e) {
            throw new SerException("洽谈详情更新失败");
        }
        return BeanTransform.copyProperties(talkDetailTarget,TalkDetailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTalkDetail(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            super.remove(id);
        }else{
            throw new SerException("洽谈详情id不能为空");
        }
    }

    @Override
    public List<ContactObjectBO> getContactWays(TalkDetailDTO talkDetailDTO) throws SerException {
        if( StringUtils.isBlank( talkDetailDTO.getCooperCompany() )){
            throw new SerException("合作对象公司名称为空，不能获取");
        }
        String companyName = talkDetailDTO.getCooperCompany();
        List<ContactObjectBO> list = new ArrayList<>();

        //TODO : tanghaixiang 2017-03-29 从供应商信息管理里面根据合作公司名称获取联系人，联系电话，移动电话

        //TODO : tanghaixiang 2017-03-29 从客户处获取
        //TODO : tanghaixiang 2017-03-29 从市场信息管理处获取
        //TODO : tanghaixiang 2017-03-29  从竞争对手管理处获取
        //从商业能力处获取
        List<CooperCapabilityBO> cooperCapabilityBOS = cooperCapabilityAPI.listCompanyContact( companyName );
        cooperCapabilityBOS.stream().forEach(str->{
            ContactObjectBO contactObjectBO = new ContactObjectBO();
            contactObjectBO.setCompanyName(str.getCompanyName());
            contactObjectBO.setContactName(str.getContactName());
            contactObjectBO.setContactTel(str.getContactWay());
            contactObjectBO.setMobilePhone(str.getContactWay());
            list.add( contactObjectBO );
        });
        return list;
    }
}