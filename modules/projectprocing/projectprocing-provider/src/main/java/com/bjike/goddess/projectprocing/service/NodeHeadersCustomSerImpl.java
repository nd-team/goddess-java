package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.entity.NodeHeadersCustom;
import com.bjike.goddess.projectprocing.entity.SettleWorkProgreManage;
import com.bjike.goddess.projectprocing.enums.GuideAddrStatus;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SettleWorkProgreManageSer settleWorkProgreManageSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countNode(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws SerException {
        nodeHeadersCustomDTO.getConditions().add(Restrict.isNull("prossManageId"));
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
        checkPermission();
        nodeHeadersCustomDTO.getConditions().add(Restrict.isNull("prossManageId"));
        List<NodeHeadersCustom> nodeHeadersCustomList = super.findByCis(nodeHeadersCustomDTO, true);
        return BeanTransform.copyProperties(nodeHeadersCustomList, NodeHeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public NodeHeadersCustomBO addNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        checkPermission();
        NodeHeadersCustom nodeHeadersCustom = BeanTransform.copyProperties(nodeHeadersCustomTO, NodeHeadersCustom.class);
        nodeHeadersCustom.setCreateTime(LocalDateTime.now());
        super.save(nodeHeadersCustom);
        return BeanTransform.copyProperties(nodeHeadersCustom, NodeHeadersCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public NodeHeadersCustomBO editNode(NodeHeadersCustomTO nodeHeadersCustomTO) throws SerException {
        checkPermission();
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
        checkPermission();
        //查询到本条数据对应的内容数据
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.eq("fatherId",id));
        List<NodeHeadersCustom> nodeHeadersCustomList = super.findByCis(nodeHeadersCustomDTO);
        //删除时也要将内容数据分配节点出去的删除掉
        if(nodeHeadersCustomList!=null && nodeHeadersCustomList.size()>0){
            for (NodeHeadersCustom nodeHeadersCustom: nodeHeadersCustomList){
                SettleWorkProgreManageDTO settleWorkProgreManageDTO = new SettleWorkProgreManageDTO();
                settleWorkProgreManageDTO.getConditions().add(Restrict.eq("nodeId",nodeHeadersCustom.getId()));
                List<SettleWorkProgreManage> settleWorkProgreManages = settleWorkProgreManageSer.findByCis(settleWorkProgreManageDTO);
                settleWorkProgreManageSer.remove(settleWorkProgreManages);
            }
        }

        super.remove(nodeHeadersCustomList);
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
        if (nodeHeadersCustoms != null && nodeHeadersCustoms.size() > 0) {
            for (NodeHeadersCustom nodeHeadersCustom : nodeHeadersCustoms) {
                NodeHeadersCustom nodeHeadersCustom1 = super.findById(nodeHeadersCustom.getFatherId());
                nodeHeadersCustom.setNodeOneName(nodeHeadersCustom1.getNodeOneName());
                nodeHeadersCustom.setNodeOneHeader(nodeHeadersCustom1.getNodeOneHeader());
                nodeHeadersCustom.setNodeTwoHeader(nodeHeadersCustom1.getNodeTwoHeader());
                nodeHeadersCustom.setNodeTwoInterDate(nodeHeadersCustom1.getNodeTwoInterDate());
                nodeHeadersCustom.setNodeThreeHeader(nodeHeadersCustom1.getNodeThreeHeader());
                nodeHeadersCustom.setNodeThreeInterDate(nodeHeadersCustom1.getNodeThreeInterDate());
                nodeHeadersCustom.setNodeFourInterDate(nodeHeadersCustom1.getNodeFourInterDate());
                nodeHeadersCustom.setNodeFourHeader(nodeHeadersCustom1.getNodeFourHeader());
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
            for (NodeHeadersCustom nodeHeadersCustom : nodeHeadersCustomList) {
                if (nodeHeadersCustom.getNodeTwoContent() == LocalDate.now()) {
                    nodeHeadersCustom.setNodeOneNameContent(2);
                }
                if (nodeHeadersCustom.getNodeThreeContent() == LocalDate.now()) {
                    nodeHeadersCustom.setNodeOneNameContent(3);
                }
                if (nodeHeadersCustom.getNodeFourContent() == LocalDate.now()) {
                    nodeHeadersCustom.setNodeOneNameContent(4);
                }
            }
        }
    }

    @Override
    public NodeHeadersCustomBO getByFatherId(String fatherId) throws SerException {
        NodeHeadersCustomDTO nodeHeadersCustomDTO = new NodeHeadersCustomDTO();
        nodeHeadersCustomDTO.getConditions().add(Restrict.eq("id", fatherId));
        NodeHeadersCustom nodeHeadersCustom = super.findOne(nodeHeadersCustomDTO);
        return BeanTransform.copyProperties(nodeHeadersCustom, NodeHeadersCustomBO.class);
    }
}