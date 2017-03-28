package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.bo.GroupInfoBO;
import com.bjike.goddess.headcount.dao.GroupInfoRep;
import com.bjike.goddess.headcount.dto.GroupInfoDTO;
import com.bjike.goddess.headcount.entity.plancost.GroupInfo;
import com.bjike.goddess.headcount.to.GroupInfoTO;
import com.bjike.goddess.headcount.to.PlanCostsTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-09 11:26 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]

 */
@Service
public class GroupInfoSerImpl extends ServiceImpl<GroupInfo,GroupInfoDTO> implements GroupInfoSer{
    @Autowired
    private GroupInfoRep groupInfoRep;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public GroupInfoBO saveByTO(TransactionContext txContext, GroupInfoTO groupInfoTO) throws SerException{
        GroupInfo groupInfo = BeanTransform.copyProperties(groupInfoTO,GroupInfo.class,true);
        super.save(groupInfo);
        groupInfoTO.setId(groupInfo.getId());
        return BeanTransform.copyProperties(groupInfoTO,GroupInfoBO.class);
    }
    @Override
    public List<GroupInfoBO> list()throws SerException{
        List<GroupInfo> groupInfos = super.findAll();
        List<GroupInfoBO> groupInfoBOs = BeanTransform.copyProperties(groupInfos,GroupInfoBO.class);
        return groupInfoBOs;
    }
    @Override
    public void update(GroupInfoTO groupInfoTO)throws SerException{
        GroupInfo groupInfo = super.findById(groupInfoTO.getId());
        BeanTransform.copyProperties(groupInfoTO,groupInfo,true);
        groupInfo.setModifyTime(LocalDateTime.now());
        super.update(groupInfo);
    }

    @Override
    public GroupInfoBO findByName(String name)throws SerException{
        try{
            GroupInfo groupinfo = groupInfoRep.findByName(name);
            return BeanTransform.copyProperties(groupinfo,GroupInfoBO.class);
        }catch(RepException e ){
            throw new SerException(e.getMessage());
        }
   }
}
