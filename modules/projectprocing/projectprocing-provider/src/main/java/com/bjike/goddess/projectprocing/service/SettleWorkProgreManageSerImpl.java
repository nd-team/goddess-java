package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.bo.SettleWorkProgreManageBO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.entity.SettleWorkProgreManage;
import com.bjike.goddess.projectprocing.to.SettleWorkProgreManageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 结算工作进度管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettleWorkProgreManageSerImpl extends ServiceImpl<SettleWorkProgreManage, SettleWorkProgreManageDTO> implements SettleWorkProgreManageSer {
    @Autowired
    private NodeHeadersCustomSer nodeHeadersCustomSer;
    @Override
    public Long countSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        Long count = super.count(settleWorkProgreManageDTO);
        return count;
    }

    @Override
    public SettleWorkProgreManageBO getOneById(String id) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(id);
        return BeanTransform.copyProperties(settleWorkProgreManage,SettleWorkProgreManageBO.class);
    }

    @Override
    public List<SettleWorkProgreManageBO> listSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        List<SettleWorkProgreManage> settleWorkProgreManageList = super.findByCis(settleWorkProgreManageDTO,true);
        return BeanTransform.copyProperties(settleWorkProgreManageList,SettleWorkProgreManageBO.class);
    }

    @Override
    public SettleWorkProgreManageBO addSettleWork(SettleWorkProgreManageTO settleWorkProgreManageTO) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = BeanTransform.copyProperties( settleWorkProgreManageTO,SettleWorkProgreManage.class,true);
        settleWorkProgreManage.setCreateTime(LocalDateTime.now());
        super.save(settleWorkProgreManage);
        return BeanTransform.copyProperties(settleWorkProgreManage,SettleWorkProgreManageBO.class);
    }

    @Override
    public void redistribution(String id,String responsible) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(id);
        settleWorkProgreManage.setResponsible(responsible);
        super.update(settleWorkProgreManage);
    }

    @Override
    public void deleteSettleWork(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<SettleWorkProgreManageBO> listWorkByOutUnit(String outUnit) throws SerException {
        List<NodeHeadersCustomBO> nodeHeadersCustomBOList =nodeHeadersCustomSer.getNodeByOutUnit(outUnit);
        List<SettleWorkProgreManage> settleWorkProgreManageList = new ArrayList<>();
        if(nodeHeadersCustomBOList!=null&&nodeHeadersCustomBOList.size()>0){
            for (NodeHeadersCustomBO nodeHeadersCustomBO : nodeHeadersCustomBOList){
                String[] str = new String[]{nodeHeadersCustomBO.getNodeOneHeader(),nodeHeadersCustomBO.getNodeTwoHeader(),nodeHeadersCustomBO.getNodeThreeHeader(),nodeHeadersCustomBO.getNodeFourHeader()};
                SettleWorkProgreManageDTO settleWorkProgreManageDTO = new SettleWorkProgreManageDTO();
                settleWorkProgreManageDTO.getConditions().add(Restrict.in("node",str));
                List<SettleWorkProgreManage> settleWorkProgreManageList1 = super.findByCis(settleWorkProgreManageDTO);
                if(settleWorkProgreManageList1!=null && settleWorkProgreManageList1.size()>0){
                    settleWorkProgreManageList.addAll(settleWorkProgreManageList1);
                }
            }
        }
        return BeanTransform.copyProperties(settleWorkProgreManageList,SettleWorkProgreManageBO.class);
    }
}