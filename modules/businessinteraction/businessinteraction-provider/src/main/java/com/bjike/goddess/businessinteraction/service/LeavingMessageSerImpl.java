package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.LeavingMessageBO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.to.LeavingMessageTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessinteraction.dto.LeavingMessageDTO;
import com.bjike.goddess.businessinteraction.entity.LeavingMessage;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 留言业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class LeavingMessageSerImpl extends ServiceImpl<LeavingMessage, LeavingMessageDTO> implements LeavingMessageSer {

    @Autowired
    private InteractionRelationSer interactionRelationSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

//    @Override
//    public Long countInter(LeavingMessageDTO leavingMessageDTO) throws SerException {
//        LeavingMessageDTO dto = new LeavingMessageDTO();
//        dto.getConditions().add(Restrict.eq("interactionRelation.id",interactionId ));
//
//        Long count = super.count( dto );
//        return count;
//    }

    @Override
    public List<LeavingMessageBO> listLeavingMessage(String interactionId ) throws SerException {



        LeavingMessageDTO dto = new LeavingMessageDTO();
        dto.getConditions().add(Restrict.eq("interactionRelation.id",interactionId ));
        dto.getSorts().add("createTime=desc");
        List<LeavingMessage> list = super.findByCis(dto);

        return BeanTransform.copyProperties(list, LeavingMessageBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public LeavingMessageBO addLeavingMessage(LeavingMessageTO leavingMessageTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加操作");
        }


        if( StringUtils.isBlank(leavingMessageTO.getInteractionId() )){
            throw  new SerException("互动联系信息id为空");
        }
        InteractionRelation interactionRelation = interactionRelationSer.findById( leavingMessageTO.getInteractionId());
        LeavingMessage leavingMessage = null;
        try {
            leavingMessage = BeanTransform.copyProperties(leavingMessageTO,LeavingMessage.class,true);
            leavingMessage.setCreateTime(LocalDateTime.now());
            leavingMessage.setInteractionRelation( interactionRelation );
            super.save( leavingMessage );
        } catch (SerException e) {
            throw new SerException("留言添加失败");
        }
        return BeanTransform.copyProperties(leavingMessage, LeavingMessageBO.class);
    }



}