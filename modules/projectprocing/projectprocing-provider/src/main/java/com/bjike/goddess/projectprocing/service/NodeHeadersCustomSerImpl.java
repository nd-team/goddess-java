package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 节点表头定制业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:47 ]
 * @Description: [ 节点表头定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class NodeHeadersCustomSerImpl extends ServiceImpl<NodeHeadersCustom, NodeHeadersCustomDTO> implements NodeHeadersCustomSer {
    @Override
    public Long countNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        Long count = super.count(nodeHeadersCustomDTO);
        return count;
    }

    @Override
    public NodeHeadersCustomBO getOneById(String id) throws SerException {
        NodeHeadersCustom nodeHeadersCustom = super.findById(id);
        return BeanTransform.copyProperties(nodeHeadersCustom, NodeHeadersCustomBO.class);
    }

    @Override
    public List<NodeHeadersCustomBO> listNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        List<NodeHeadersCustom> nodeHeadersCustomList = super.findByCis(nodeHeadersCustomDTO);
        return BeanTransform.copyProperties(nodeHeadersCustomList, NodeHeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public NodeHeadersCustomBO addNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        NodeHeadersCustom nodeHeadersCustom = BeanTransform.copyProperties(nodeHeadersCustomTO, NodeHeadersCustom.class);
        nodeHeadersCustom.setCreateTime(LocalDateTime.now());
        super.save(nodeHeadersCustom);
        return BeanTransform.copyProperties(nodeHeadersCustom, NodeHeadersCustom.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public NodeHeadersCustomBO editNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        NodeHeadersCustom nodeHeadersCustom = super.findById(nodeHeadersCustomTO.getId());
        LocalDateTime dateTime = nodeHeadersCustom.getCreateTime();
        nodeHeadersCustom = BeanTransform.copyProperties(nodeHeadersCustomTO, NodeHeadersCustom.class);
        nodeHeadersCustom.setCreateTime(dateTime);
        nodeHeadersCustom.setModifyTime(LocalDateTime.now());
        super.update(nodeHeadersCustom);
        return BeanTransform.copyProperties(nodeHeadersCustom, NodeHeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteNode(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<NodeHeadersCustomBO> getNodeByOutUnit(String outUnit) throws SerException {
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.eq("outUnit", outUnit));
        nodeHeadersCustomDTO.getConditions().add(Restrict.isNull("prossManageId"));
        List<NodeHeadersCustom> headersCustomList = super.findByCis(nodeHeadersCustomDTO);
        return BeanTransform.copyProperties(headersCustomList, NodeHeadersCustomBO.class);
    }

    @Override
    public List<NodeHeadersCustomBO> getByManageId(String passManageId) throws SerException {
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.eq("prossManageId", passManageId));
        List<NodeHeadersCustom> nodeHeadersCustoms = super.findByCis(nodeHeadersCustomDTO);
        if(nodeHeadersCustoms!=null && nodeHeadersCustoms.size()>0){
            for (NodeHeadersCustom nodeHeadersCustom : nodeHeadersCustoms){
                NodeHeadersCustomDTO nodeHeadersCustomDTO1 = new NodeHeadersCustomDTO();
                nodeHeadersCustomDTO1.getConditions().add(Restrict.eq("fatherId",nodeHeadersCustom.getFatherId()));
                NodeHeadersCustom nodeHeadersCustom1 = super.findOne(nodeHeadersCustomDTO1);
                nodeHeadersCustom.setNodeOneName(nodeHeadersCustom1.getNodeOneName());
                nodeHeadersCustom.setNodeOneHeader(nodeHeadersCustom1.getNodeOneHeader());
                nodeHeadersCustom.setNodeTwoHeader(nodeHeadersCustom1.getNodeTwoHeader());
                nodeHeadersCustom.setNodeTwoInterDate(nodeHeadersCustom1.getNodeTwoInterDate());
                nodeHeadersCustom.setNodeThreeHeader(nodeHeadersCustom1.getNodeThreeHeader());
                nodeHeadersCustom.setNodeThreeInterDate(nodeHeadersCustom1.getNodeThreeInterDate());
                nodeHeadersCustom.setNodeFourInterDate(nodeHeadersCustom1.getNodeFourInterDate());
            }
        }
        return BeanTransform.copyProperties(nodeHeadersCustoms, NodeHeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeByManageId(String passManageId) throws SerException {
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.eq("prossManageId", passManageId));
        List<NodeHeadersCustom> nodeHeadersCustoms = super.findByCis(nodeHeadersCustomDTO);
        super.remove(nodeHeadersCustoms);
    }

    @Override
    public void checkNodeCutoff() throws SerException {
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.isNotNull("prossManageId"));
        List<NodeHeadersCustom> nodeHeadersCustomList = super.findByCis(nodeHeadersCustomDTO);
        if (nodeHeadersCustomList != null && nodeHeadersCustomList.size() > 0) {
            for (NodeHeadersCustom nodeHeadersCustom : nodeHeadersCustomList){
                if(nodeHeadersCustom.getNodeTwoContent()== LocalDate.now()){
                    nodeHeadersCustom.setNodeOneNameContent(2);
                }
                if(nodeHeadersCustom.getNodeThreeContent()== LocalDate.now()){
                    nodeHeadersCustom.setNodeOneNameContent(3);
                }
                if(nodeHeadersCustom.getNodeFourContent()== LocalDate.now()){
                    nodeHeadersCustom.setNodeOneNameContent(4);
                }
            }
        }
    }

    @Override
    public NodeHeadersCustomBO getByFatherId(String fatherId) throws SerException {
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.eq("fatherId",fatherId));
        NodeHeadersCustom nodeHeadersCustom = super.findOne(nodeHeadersCustomDTO);
        return BeanTransform.copyProperties(nodeHeadersCustom,NodeHeadersCustomBO.class);
    }
}