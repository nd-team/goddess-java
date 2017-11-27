package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.bo.SettleProgressManageBO;
import com.bjike.goddess.projectprocing.bo.SettleWorkProgreManageBO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.entity.SettleProgressManage;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.entity.SettleWorkProgreManage;
import com.bjike.goddess.projectprocing.to.CompletionStatusTO;
import com.bjike.goddess.projectprocing.to.SettleWorkProgreManageTO;
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
    @Autowired
    private SettleProgressManageSer settleProgressManageSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SettleProgressRecordSer settleProgressRecordSer;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Override
    public Long countSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        seachCondi(settleWorkProgreManageDTO);
        Long count = super.count(settleWorkProgreManageDTO);
        return count;
    }

    @Override
    public SettleWorkProgreManageBO getOneById(String id) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(id);
        return BeanTransform.copyProperties(settleWorkProgreManage, SettleWorkProgreManageBO.class);
    }

    @Override
    public List<SettleWorkProgreManageBO> listSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
       seachCondi(settleWorkProgreManageDTO);
        List<SettleWorkProgreManage> settleWorkProgreManageList = super.findByCis(settleWorkProgreManageDTO, true);
        return BeanTransform.copyProperties(settleWorkProgreManageList, SettleWorkProgreManageBO.class);
    }

    private void seachCondi(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException{
        if(StringUtils.isNotBlank(settleWorkProgreManageDTO.getContractNo())){
            settleWorkProgreManageDTO.getConditions().add(Restrict.eq("contractNo",settleWorkProgreManageDTO.getContractNo()));
        }
        if(StringUtils.isNotBlank(settleWorkProgreManageDTO.getDispatchingName())){
            settleWorkProgreManageDTO.getConditions().add(Restrict.eq("dispatchingName",settleWorkProgreManageDTO.getDispatchingName()));
        }
        if(StringUtils.isNotBlank(settleWorkProgreManageDTO.getResponsible())){
            settleWorkProgreManageDTO.getConditions().add(Restrict.eq("responsible",settleWorkProgreManageDTO.getResponsible()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettleWorkProgreManageBO addSettleWork(SettleWorkProgreManageTO settleWorkProgreManageTO) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = BeanTransform.copyProperties(settleWorkProgreManageTO, SettleWorkProgreManage.class, true);
        settleWorkProgreManage.setCreateTime(LocalDateTime.now());
        super.save(settleWorkProgreManage);
        return BeanTransform.copyProperties(settleWorkProgreManage, SettleWorkProgreManageBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void redistribution(String id, String responsible) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(id);
        settleWorkProgreManage.setResponsible(responsible);
        super.update(settleWorkProgreManage);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSettleWork(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<SettleWorkProgreManageBO> listWorkByOutUnit(String outUnit) throws SerException {
        List<NodeHeadersCustomBO> nodeHeadersCustomBOList = nodeHeadersCustomSer.getNodeByOutUnit(outUnit);
        List<SettleWorkProgreManage> settleWorkProgreManageList = new ArrayList<>();
        if (nodeHeadersCustomBOList != null && nodeHeadersCustomBOList.size() > 0) {
            for (NodeHeadersCustomBO nodeHeadersCustomBO : nodeHeadersCustomBOList) {
                String[] str = new String[]{nodeHeadersCustomBO.getNodeOneHeader(), nodeHeadersCustomBO.getNodeTwoHeader(), nodeHeadersCustomBO.getNodeThreeHeader(), nodeHeadersCustomBO.getNodeFourHeader()};
                SettleWorkProgreManageDTO settleWorkProgreManageDTO = new SettleWorkProgreManageDTO();
                settleWorkProgreManageDTO.getConditions().add(Restrict.in("node", str));
                List<SettleWorkProgreManage> settleWorkProgreManageList1 = super.findByCis(settleWorkProgreManageDTO);
                if (settleWorkProgreManageList1 != null && settleWorkProgreManageList1.size() > 0) {
                    settleWorkProgreManageList.addAll(settleWorkProgreManageList1);
                }
            }
        }
        List<SettleWorkProgreManageBO> settleWorkProgreManageBOList = BeanTransform.copyProperties(settleWorkProgreManageList, SettleWorkProgreManageBO.class);
        SettleWorkProgreManageDTO settleWorkProgreManageDTO = new SettleWorkProgreManageDTO();
        int limit = settleWorkProgreManageDTO.getLimit();
        int start = limit * settleWorkProgreManageDTO.getPage();
        return settleWorkProgreManageBOList.stream().skip(start).skip(limit).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void fullFinishStatus(CompletionStatusTO completionStatusTO) throws SerException {
        SettleWorkProgreManage settleWorkProgreManage = super.findById(completionStatusTO.getId());
        SettleProgressManageBO settleProgressManageBO = settleProgressManageSer.findByContractNo(settleWorkProgreManage.getContractNo());
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        if (!completionStatusTO.getComplete()) {
            checkUpdateZD(settleWorkProgreManage, completionStatusTO.getDateTime());//修改节点数据
            //添加至结算进度调整记录&结算问题汇总表中
            SettleProgressRecord settleProgressRecord = new SettleProgressRecord();
            String moneyModuleName = positionDetailUserAPI.moneyModulePerson();
            List<PositionDetailUserBO> positionDetailUserBOS = positionUserDetailAPI.findManager();
            settleProgressRecord.setOutUnit(settleProgressManageBO.getOutUnit());
            settleProgressRecord.setDispatchingName(settleProgressManageBO.getDispatchingItems());
            settleProgressRecord.setInternalName(settleProgressManageBO.getInternalProName());
            settleProgressRecord.setModifier(userBO.getUsername());
            settleProgressRecord.setUpdateDate(LocalDateTime.now());
            settleProgressRecord.setUpdateContent("修改了" + settleWorkProgreManage.getNode() + "节点时间改为" + completionStatusTO.getDateTime());
            settleProgressRecord.setProblemDescription(completionStatusTO.getProblemDescription());
            settleProgressRecord.setProblemType(completionStatusTO.getProblemType());
            settleProgressRecord.setAssistPeoper(completionStatusTO.getAssistPeoper());
            settleProgressRecord.setAssistContent(completionStatusTO.getAssistContent());
            settleProgressRecord.setMoneyModule(moneyModuleName);
            if(positionDetailUserBOS!=null && positionDetailUserBOS.size()>0){
                settleProgressRecord.setMoneyModule(positionDetailUserBOS.get(0).getName());
            }
            settleProgressRecordSer.save(settleProgressRecord);

            settleWorkProgreManage.setDelayDate(completionStatusTO.getDateTime());//修改本表数据

            //更新结算进度表中的更新时间
            SettleProgressManage settleProgressManage = settleProgressManageSer.findById(settleProgressManageBO.getId());
            settleProgressManage.setChangeDate(true);
            settleProgressManage.setUpdateDate(settleProgressRecord.getUpdateDate());
            settleProgressManageSer.update(settleProgressManage);
        }
        settleWorkProgreManage.setComplete(completionStatusTO.getComplete());
        settleWorkProgreManage.setModifyTime(LocalDateTime.now());
        super.update(settleWorkProgreManage);
    }

    /**
     * 检测修改的是哪个节点然后进行后面节点数据的推算
     *
     * @param settleWorkProgreManage
     * @param dateTime
     * @throws SerException
     */
    private void checkUpdateZD(SettleWorkProgreManage settleWorkProgreManage, String dateTime) throws SerException {
        NodeHeadersCustom nodeHeadersCustom = nodeHeadersCustomSer.findById(settleWorkProgreManage.getNodeId());//本条节点内容
        NodeHeadersCustomBO nodeHeadersCustomBO = nodeHeadersCustomSer.getByFatherId(nodeHeadersCustom.getFatherId());//本条数据的对应父数据(节点表头名)
        if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeOneHeader())) {
            nodeHeadersCustom.setNodeOneContent(DateUtil.parseDate(dateTime));
            nodeHeadersCustom.setNodeTwoContent(nodeHeadersCustom.getNodeOneContent().plusDays(nodeHeadersCustomBO.getNodeTwoInterDate()));
            nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomBO.getNodeThreeInterDate()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeTwoHeader())) {
            nodeHeadersCustom.setNodeTwoContent(DateUtil.parseDate(dateTime));
            nodeHeadersCustom.setNodeThreeContent(nodeHeadersCustom.getNodeTwoContent().plusDays(nodeHeadersCustomBO.getNodeThreeInterDate()));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeThreeHeader())) {
            nodeHeadersCustom.setNodeThreeContent(DateUtil.parseDate(dateTime));
            nodeHeadersCustom.setNodeFourContent(nodeHeadersCustom.getNodeThreeContent().plusDays(nodeHeadersCustomBO.getNodeFourInterDate()));
        } else if (settleWorkProgreManage.getNode().equals(nodeHeadersCustomBO.getNodeFourHeader())) {
            nodeHeadersCustom.setNodeFourContent(DateUtil.parseDate(dateTime));
        }
        nodeHeadersCustom.setModifyTime(LocalDateTime.now());
        nodeHeadersCustomSer.update(nodeHeadersCustom);
    }
}