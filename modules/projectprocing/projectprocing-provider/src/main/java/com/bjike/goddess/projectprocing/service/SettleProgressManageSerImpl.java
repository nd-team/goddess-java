package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.businessproject.api.BusinessContractAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.projectprocing.bo.AllotmentNodeDataBO;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.bo.ScreeningSettleProgressManageBO;
import com.bjike.goddess.projectprocing.bo.SettleProgressManageBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.HeadersCustom;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.entity.SettleProgressManage;
import com.bjike.goddess.projectprocing.to.HeadersCustomTO;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;
import com.bjike.goddess.projectprocing.to.SettleProgressManageTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结算进度管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettleProgressManageSerImpl extends ServiceImpl<SettleProgressManage, SettleProgressManageDTO> implements SettleProgressManageSer {
    @Autowired
    private HeadersCustomSer headersCustomSer;
    @Autowired
    private NodeHeadersCustomSer nodeHeadersCustomSer;
    @Autowired
    private BusinessContractAPI businessContractAPI;

    @Override
    public Long countManage(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        searchCondi(settleProgressManageDTO);
        Long count = super.count(settleProgressManageDTO);
        return count;
    }

    @Override
    public SettleProgressManageBO getOneById(String id) throws SerException {
        SettleProgressManage settleProgressManage = super.findById(id);
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Override
    public List<SettleProgressManageBO> listManage(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        searchCondi(settleProgressManageDTO);
        List<SettleProgressManage> settleProgressManages = super.findByCis(settleProgressManageDTO, true);
        return BeanTransform.copyProperties(settleProgressManages, SettleProgressManageBO.class);
    }

    private void searchCondi(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        if (StringUtils.isNotBlank(settleProgressManageDTO.getOperatorName())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("operatorName", settleProgressManageDTO.getOperatorName()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getArea())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("area", settleProgressManageDTO.getArea()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getOutUnit())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("outUnit", settleProgressManageDTO.getOutUnit()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getSaleContractNo())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("saleContractNo", settleProgressManageDTO.getSaleContractNo()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getContractNo())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("contractNo", settleProgressManageDTO.getContractNo()));
        }
        if (settleProgressManageDTO.getDispatCondition() != null) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("dispatCondition", settleProgressManageDTO.getDispatCondition()));
        }
        if (settleProgressManageDTO.getActualCompletedState() != null) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("actualCompletedState", settleProgressManageDTO.getActualCompletedState()));
        }
        if (settleProgressManageDTO.getKpi() != null) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("kpi", settleProgressManageDTO.getKpi()));
        }
        if (StringUtils.isNotBlank(settleProgressManageDTO.getProgress())) {
            settleProgressManageDTO.getConditions().add(Restrict.eq("progress", settleProgressManageDTO.getProgress()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettleProgressManageBO addManage(SettleProgressManageTO settleProgressManageTO) throws SerException {
        SettleProgressManage settleProgressManage = BeanTransform.copyProperties(settleProgressManageTO, SettleProgressManage.class, true);
        settleProgressManage.setCreateTime(LocalDateTime.now());
        super.save(settleProgressManage);
        List<HeadersCustomTO> headersCustomTOList = settleProgressManageTO.getHeadersCustomTOS();
        List<NodeHeadersCustomTO> nodeHeadersCustomTOList = settleProgressManageTO.getNodeHeadersCustomTOS();
        if (headersCustomTOList != null && headersCustomTOList.size() > 0) {
            List<HeadersCustom> headersCustomList = new ArrayList<>();
            for (HeadersCustomTO headersCustomTO : headersCustomTOList) {
                HeadersCustom headersCustom = new HeadersCustom();
                headersCustom.setCreateTime(LocalDateTime.now());
                headersCustom.setContent(headersCustomTO.getContent());
                headersCustom.setId(headersCustomTO.getId());
                headersCustom.setProssManageId(settleProgressManage.getId());
                headersCustomList.add(headersCustom);
            }
            headersCustomSer.save(headersCustomList);
        }
        if (nodeHeadersCustomTOList != null && nodeHeadersCustomTOList.size() > 0) {
            List<NodeHeadersCustom> nodeHeadersCustomList = new ArrayList<>();
            for (NodeHeadersCustomTO nodeHeadersCustomTO : nodeHeadersCustomTOList) {
                NodeHeadersCustom nodeHeadersCustom = new NodeHeadersCustom();
                nodeHeadersCustom.setCreateTime(LocalDateTime.now());
                nodeHeadersCustom.setProssManageId(settleProgressManage.getId());
                nodeHeadersCustom.setFatherId(nodeHeadersCustomTO.getId());
                nodeHeadersCustom.setNodeTwoContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeOneContent()).plusDays(nodeHeadersCustomTO.getNodeTwoInterDate()));
                nodeHeadersCustom.setNodeThreeContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeTwoContent()).plusDays(nodeHeadersCustomTO.getNodeThreeInterDate()));
                nodeHeadersCustom.setNodeFourContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeThreeContent()).plusDays(nodeHeadersCustomTO.getNodeFourInterDate()));
                nodeHeadersCustomList.add(nodeHeadersCustom);
            }
            nodeHeadersCustomSer.save(nodeHeadersCustomList);
        }
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettleProgressManageBO editManage(SettleProgressManageTO settleProgressManageTO) throws SerException {
        SettleProgressManage settleProgressManage = super.findById(settleProgressManageTO.getId());
        LocalDateTime dateTime = settleProgressManage.getCreateTime();
        settleProgressManage = BeanTransform.copyProperties(settleProgressManageTO, SettleProgressManage.class, true);
        settleProgressManage.setCreateTime(dateTime);
        settleProgressManage.setModifyTime(LocalDateTime.now());
        super.update(settleProgressManage);
        List<HeadersCustomTO> headersCustomTOList = settleProgressManageTO.getHeadersCustomTOS();
        List<NodeHeadersCustomTO> nodeHeadersCustomTOList = settleProgressManageTO.getNodeHeadersCustomTOS();
        headersCustomSer.removeByManageId(settleProgressManageTO.getId());
        nodeHeadersCustomSer.removeByManageId(settleProgressManageTO.getId());
        if (headersCustomTOList != null && headersCustomTOList.size() > 0) {
            List<HeadersCustom> headersCustomList = new ArrayList<>();
            for (HeadersCustomTO headersCustomTO : headersCustomTOList) {
                HeadersCustom headersCustom = new HeadersCustom();
                headersCustom.setCreateTime(LocalDateTime.now());
                headersCustom.setContent(headersCustomTO.getContent());
                headersCustom.setId(headersCustomTO.getId());
                headersCustom.setProssManageId(settleProgressManage.getId());
                headersCustomList.add(headersCustom);
            }
            headersCustomSer.save(headersCustomList);
        }
        if (nodeHeadersCustomTOList != null && nodeHeadersCustomTOList.size() > 0) {
            List<NodeHeadersCustom> nodeHeadersCustomList = new ArrayList<>();
            for (NodeHeadersCustomTO nodeHeadersCustomTO : nodeHeadersCustomTOList) {
                NodeHeadersCustom nodeHeadersCustom = new NodeHeadersCustom();
                nodeHeadersCustom.setCreateTime(LocalDateTime.now());
                nodeHeadersCustom.setProssManageId(settleProgressManage.getId());
                nodeHeadersCustom.setFatherId(nodeHeadersCustomTO.getId());
                nodeHeadersCustom.setNodeTwoContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeOneContent()).plusDays(nodeHeadersCustomTO.getNodeTwoInterDate()));
                nodeHeadersCustom.setNodeThreeContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeTwoContent()).plusDays(nodeHeadersCustomTO.getNodeThreeInterDate()));
                nodeHeadersCustom.setNodeFourContent(DateUtil.parseDate(nodeHeadersCustomTO.getNodeThreeContent()).plusDays(nodeHeadersCustomTO.getNodeFourInterDate()));
                nodeHeadersCustomList.add(nodeHeadersCustom);
            }
            nodeHeadersCustomSer.save(nodeHeadersCustomList);
        }
        return BeanTransform.copyProperties(settleProgressManage, SettleProgressManageBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteManage(String id) throws SerException {
        headersCustomSer.removeByManageId(id);
        nodeHeadersCustomSer.removeByManageId(id);
        super.remove(id);
    }

    @Override
    public List<String> findOperatorName() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getOperatorName).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findArea() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getArea).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findOutUnit() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getOutUnit).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findSaleContractNo() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getSaleContractNo).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findContractNo() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getContractNo).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findProgress() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        if (CollectionUtils.isEmpty(settleProgressManageList)) {
            Collections.emptyList();
        }
        return settleProgressManageList.stream().map(SettleProgressManage::getProgress).distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> findDispatName() throws SerException {
        List<SettleProgressManage> settleProgressManageList = super.findAll();
        List<String> dispatNames = businessContractAPI.findSingleContractName();
        List<String> returnDates = new ArrayList<>();
        if (settleProgressManageList == null || settleProgressManageList.size() == 0) {
            returnDates = dispatNames;
        } else {
            if (dispatNames != null && dispatNames.size() > 0) {
                for (String dispatName : dispatNames) {
                    for (SettleProgressManage settleProgressManage : settleProgressManageList) {
                        if (!dispatName.equals(settleProgressManage.getDispatName())) {
                            returnDates.add(dispatName);
                        }
                    }
                }
            }
        }
        return returnDates;
    }

    @Override
    public List<ScreeningSettleProgressManageBO> listByOutUnit(String outUnit) throws SerException {
        SettleProgressManageDTO settleProgressManageDTO = new SettleProgressManageDTO();
        settleProgressManageDTO.getConditions().add(Restrict.eq("outUnit", outUnit));
        List<SettleProgressManage> settleProgressManageList = super.findByCis(settleProgressManageDTO, true);
        List<ScreeningSettleProgressManageBO> screeningSettleProgressManageBOList = BeanTransform.copyProperties(settleProgressManageList, ScreeningSettleProgressManageBO.class);
        if (screeningSettleProgressManageBOList != null && screeningSettleProgressManageBOList.size() > 0) {
            for (ScreeningSettleProgressManageBO screeningSettleProgressManageBO : screeningSettleProgressManageBOList) {
                screeningSettleProgressManageBO.setHeadersCustomBOList(headersCustomSer.getByManageId(screeningSettleProgressManageBO.getId()));
                screeningSettleProgressManageBO.setNodeHeadersCustomBOList(nodeHeadersCustomSer.getByManageId(screeningSettleProgressManageBO.getId()));
            }
        }
        return screeningSettleProgressManageBOList;
    }

    @Override
    public List<AllotmentNodeDataBO> findAllNodeById(String id) throws SerException {
        SettleProgressManage settleProgressManage = super.findById(id);
        List<NodeHeadersCustomBO> nodeHeadersCustomList = nodeHeadersCustomSer.getByManageId(id);
        List<AllotmentNodeDataBO> allotmentNodeDataBOList = new ArrayList<>();
        if(nodeHeadersCustomList!=null && nodeHeadersCustomList.size()>0){
            for (NodeHeadersCustomBO nodeHeadersCustomBO : nodeHeadersCustomList){
                AllotmentNodeDataBO allotmentNodeDataBO = new AllotmentNodeDataBO();
                allotmentNodeDataBO.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO.setNodeHeader(nodeHeadersCustomBO.getNodeOneHeader());
                allotmentNodeDataBO.setNodeContent(nodeHeadersCustomBO.getNodeOneContent());
                allotmentNodeDataBO.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO.setPayableAmount(settleProgressManage.getPartialSettle());
                allotmentNodeDataBO.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO);

                AllotmentNodeDataBO allotmentNodeDataBO1 = new AllotmentNodeDataBO();
                allotmentNodeDataBO1.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO1.setNodeHeader(nodeHeadersCustomBO.getNodeTwoHeader());
                allotmentNodeDataBO1.setNodeContent(nodeHeadersCustomBO.getNodeTwoContent());
                allotmentNodeDataBO1.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO1.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO1.setPayableAmount(settleProgressManage.getPartialSettle());
                allotmentNodeDataBO1.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO1);

                AllotmentNodeDataBO allotmentNodeDataBO2 = new AllotmentNodeDataBO();
                allotmentNodeDataBO2.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO2.setNodeHeader(nodeHeadersCustomBO.getNodeThreeHeader());
                allotmentNodeDataBO2.setNodeContent(nodeHeadersCustomBO.getNodeThreeContent());
                allotmentNodeDataBO2.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO2.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO2.setPayableAmount(settleProgressManage.getPartialSettle());
                allotmentNodeDataBO2.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO2);

                AllotmentNodeDataBO allotmentNodeDataBO3 = new AllotmentNodeDataBO();
                allotmentNodeDataBO3.setNodeId(nodeHeadersCustomBO.getId());
                allotmentNodeDataBO3.setNodeHeader(nodeHeadersCustomBO.getNodeFourHeader());
                allotmentNodeDataBO3.setNodeContent(nodeHeadersCustomBO.getNodeFourContent());
                allotmentNodeDataBO3.setContractNo(settleProgressManage.getContractNo());
                allotmentNodeDataBO3.setDispatName(settleProgressManage.getDispatName());
                allotmentNodeDataBO3.setPayableAmount(settleProgressManage.getPartialSettle());
                allotmentNodeDataBO3.setSaleContractNo(settleProgressManage.getSaleContractNo());
                allotmentNodeDataBOList.add(allotmentNodeDataBO3);
            }
        }
        return allotmentNodeDataBOList;
    }

    @Override
    public byte[] exportExcel(String outUnit) throws SerException {

        return new byte[0];
    }

    @Override
    public void importExcel(List<SettleProgressManageTO> settleProgressManageTOS) throws SerException {

    }
}