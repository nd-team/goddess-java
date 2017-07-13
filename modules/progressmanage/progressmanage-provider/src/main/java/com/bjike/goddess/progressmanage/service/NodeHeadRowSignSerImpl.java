package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.NodeHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.NodeHeadRowSignBO;
import com.bjike.goddess.progressmanage.bo.NodeHeadValueBO;
import com.bjike.goddess.progressmanage.bo.TableHeadForValueBO;
import com.bjike.goddess.progressmanage.dto.NodeHeadDTO;
import com.bjike.goddess.progressmanage.dto.NodeHeadRowSignDTO;
import com.bjike.goddess.progressmanage.dto.NodeHeadValueDTO;
import com.bjike.goddess.progressmanage.entity.NodeHead;
import com.bjike.goddess.progressmanage.entity.NodeHeadRowSign;
import com.bjike.goddess.progressmanage.entity.NodeHeadValue;
import com.bjike.goddess.progressmanage.entity.ProgressNode;
import com.bjike.goddess.progressmanage.to.NodeHeadRowSignTO;
import com.bjike.goddess.progressmanage.to.NodeHeadValueTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 节点表头对应值的行标记业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class NodeHeadRowSignSerImpl extends ServiceImpl<NodeHeadRowSign, NodeHeadRowSignDTO> implements NodeHeadRowSignSer {

    @Autowired
    private NodeHeadSer nodeHeadSer;
    @Autowired
    private ProgressNodeSer progressNodeSer;
    @Autowired
    private NodeHeadValueSer nodeHeadValueSer;

    @Override
    public List<NodeHeadForValueBO> heads(String nodeId) throws SerException {
        ProgressNode table = progressNodeSer.findById(nodeId);
        if (table != null) {

            NodeHeadDTO nodeHeadDTO = new NodeHeadDTO();
            nodeHeadDTO.getConditions().add(Restrict.eq("progressNode.id", nodeId));
            nodeHeadDTO.getSorts().add("sortIndex=asc");
            List<NodeHead> nodeHeadList = nodeHeadSer.findByCis(nodeHeadDTO, true);

            return BeanTransform.copyProperties(nodeHeadList, TableHeadForValueBO.class);
        } else {
            throw new SerException("非法nodeId,节点对象不能为空!");
        }
    }

    @Override
    public List<NodeHeadRowSignBO> pageList(NodeHeadRowSignDTO dto) throws SerException {
        ProgressNode projectInfo = progressNodeSer.findById(dto.getNodeId());
        if (projectInfo != null) {

            dto.getConditions().add(Restrict.eq("progressNode.id", dto.getNodeId()));
            dto.getSorts().add("createTime=asc");
            List<NodeHeadRowSign> list = super.findByPage(dto);

            if (!CollectionUtils.isEmpty(list)) {
                //排序
                sortByHead(list);

                List<NodeHeadRowSignBO> boList = new ArrayList<NodeHeadRowSignBO>();

                for (NodeHeadRowSign model : list) {
                    NodeHeadRowSignBO bo = new NodeHeadRowSignBO();
                    bo.setId(model.getId());
                    bo.setBoList(BeanTransform.copyProperties(model.getNodeHeadValueList(), NodeHeadValueBO.class));
                    boList.add(bo);
                }
                return boList;
            } else {
                return null;
            }
        } else {
            throw new SerException("非法nodeId,节点对象不能为空!");
        }
    }

    //按表头排序
    public List<NodeHeadRowSign> sortByHead(List<NodeHeadRowSign> list) {
        for (NodeHeadRowSign model : list) {
            model.setNodeHeadValueList(new ArrayList<>());
            model.getNodeHeadValueList().addAll(model.getNodeHeadValueSet());
        }
        for (NodeHeadRowSign model : list) {
            Collections.sort(model.getNodeHeadValueList(), new Comparator<NodeHeadValue>() {

                @Override
                public int compare(NodeHeadValue o1, NodeHeadValue o2) {
                    return o1.getNodeHead().getSortIndex().compareTo(o2.getNodeHead().getSortIndex());
                }
            });
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void insertModel(NodeHeadRowSignTO to) throws SerException {
        if (!CollectionUtils.isEmpty(to.getToList())) {

            ProgressNode node = progressNodeSer.findById(to.getNodeId());
            if (node != null) {
                NodeHeadRowSign nodeHeadRowSign = new NodeHeadRowSign();
                nodeHeadRowSign.setProgressNode(node);

                Set<NodeHeadValue> tableHeadValueSet = new HashSet<NodeHeadValue>();

                if (!CollectionUtils.isEmpty(to.getToList())) {
                    for (NodeHeadValueTO valueTO : to.getToList()) {

                        NodeHead nodeHead = nodeHeadSer.findById(valueTO.getNodeHeadId());
                        if (nodeHead != null) {
                            //表头与节点不对应
                            if (nodeHead.getProgressNode().getId().equals(node.getId())) {
                                //判断是否必填,且是否赋值
                                if (nodeHead.getRequired() && StringUtils.isEmpty(valueTO.getValue())) {
                                    throw new SerException("[" + nodeHead.getHeadName() + "],不能为空!");
                                }

                                NodeHeadValue model = BeanTransform.copyProperties(valueTO, NodeHeadValue.class);
                                model.setNodeHead(nodeHead);
                                model.setNodeHeadRowSign(nodeHeadRowSign);
                                tableHeadValueSet.add(model);
                            } else {
                                throw new SerException("[" + node.getNodeName() + "]节点并无[" + nodeHead.getHeadName() + "]表头字段!");
                            }

                        } else {
                            throw new SerException("非法表头Id, " + valueTO.getValue() + " 值对应的表头对象不存在!");
                        }
                    }
                }

                if (!tableHeadValueSet.isEmpty()) {
                    if (tableHeadValueSet.size() == node.getNodeHeadSet().size()) {
                        nodeHeadRowSign.setNodeHeadValueSet(tableHeadValueSet);
                        super.save(nodeHeadRowSign);
                    } else {
                        throw new SerException("提交的表头数据与表头不对应,请检查提交的参数!");
                    }

                } else {
                    throw new SerException("保存失败,请联系管理员!");
                }
            } else {
                throw new SerException("非法节点Id, 节点对象不能为空!");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void updateModel(NodeHeadRowSignTO to) throws SerException {
        NodeHeadRowSign model = super.findById(to.getId());
        if (model != null) {
            if (!CollectionUtils.isEmpty(to.getToList())) {

                List<NodeHeadValue> valueList = new ArrayList<NodeHeadValue>();
                for (NodeHeadValueTO valueTO : to.getToList()) {

                    NodeHead nodeHead = nodeHeadSer.findById(valueTO.getNodeHeadId());
                    if (nodeHead != null) {
                        //判断是否必填,且是否赋值
                        if (nodeHead.getRequired() && StringUtils.isEmpty(valueTO.getValue())) {
                            throw new SerException("[" + nodeHead.getHeadName() + "],不能为空!");
                        }

                        NodeHeadValueDTO valueDTO = new NodeHeadValueDTO();
                        valueDTO.getConditions().add(Restrict.eq("nodeHead.id", valueTO.getNodeHeadId()));
                        valueDTO.getConditions().add(Restrict.eq("nodeHeadRowSign.id", to.getId()));
                        NodeHeadValue value = nodeHeadValueSer.findOne(valueDTO);
                        BeanTransform.copyProperties(valueTO, value);
                        valueList.add(value);
                    } else {
                        throw new SerException("非法表头Id, " + valueTO.getValue() + " 值对应的表头对象不存在!");
                    }
                }

                if (!valueList.isEmpty()) {
                    nodeHeadValueSer.update(valueList);
                }
            } else {
                throw new SerException("非法表头Id, 进度表对象不能为空!");
            }
        } else {
            throw new SerException("非法Id,行对象不能为空!");
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        NodeHeadRowSign model = super.findById(id);
        if (model != null) {
            super.remove(model);
        } else {
            throw new SerException("非法行Id,行对象不能为空!");
        }
    }
}