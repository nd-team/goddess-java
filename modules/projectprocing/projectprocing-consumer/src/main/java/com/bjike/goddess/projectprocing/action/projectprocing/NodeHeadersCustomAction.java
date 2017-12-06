package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.HeadersCustomAPI;
import com.bjike.goddess.projectprocing.api.NodeHeadersCustomAPI;
import com.bjike.goddess.projectprocing.bo.NodeHeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.NodeHeadersCustomDTO;
import com.bjike.goddess.projectprocing.to.NodeHeadersCustomTO;
import com.bjike.goddess.projectprocing.vo.NodeHeadersCustomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 节点表头定制
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:47 ]
 * @Description: [ 节点表头定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("nodeheaderscustom")
public class NodeHeadersCustomAction {
    @Autowired
    private NodeHeadersCustomAPI nodeHeadersCustomAPI;

    /**
     * 节点表头定制总条数
     *
     * @param nodeHeadersCustomDTO 节点表头定制dto
     * @des 获取所有节点表头定制总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(NodeHeadersCustomDTO nodeHeadersCustomDTO) throws ActException {
        try {
            Long count = nodeHeadersCustomAPI.countNode(nodeHeadersCustomDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个节点表头定制
     *
     * @param id 节点表头定制id
     * @return class NodeHeadersCustomVO
     * @des 根据id获取节点表头定制
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            NodeHeadersCustomVO nodeHeadersCustomVO = BeanTransform.copyProperties(
                    nodeHeadersCustomAPI.getOneById(id), NodeHeadersCustomVO.class, true);
            return ActResult.initialize(nodeHeadersCustomVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 节点表头定制列表
     *
     * @param nodeHeadersCustomDTO 节点表头定制dto
     * @return class NodeHeadersCustomVO
     * @des 获取所有表头定制
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(NodeHeadersCustomDTO nodeHeadersCustomDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<NodeHeadersCustomVO> projectCarryVOList = BeanTransform.copyProperties(
                    nodeHeadersCustomAPI.listNode(nodeHeadersCustomDTO), NodeHeadersCustomVO.class, request);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加节点表头定制
     *
     * @param nodeHeadersCustomTO 节点表头定制数据to
     * @return class NodeHeadersCustomVO
     * @des 添加节点表头定制
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) NodeHeadersCustomTO nodeHeadersCustomTO, BindingResult bindingResult) throws ActException {
        try {
            NodeHeadersCustomBO nodeHeadersCustomBO = nodeHeadersCustomAPI.addNode(nodeHeadersCustomTO);
            return ActResult.initialize(BeanTransform.copyProperties(nodeHeadersCustomBO, NodeHeadersCustomVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑节点表头定制
     *
     * @param nodeHeadersCustomTO 节点表头定制bo
     * @return class NodeHeadersCustomVO
     * @des 添加节点表头定制
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) NodeHeadersCustomTO nodeHeadersCustomTO) throws ActException {
        try {
            NodeHeadersCustomBO nodeHeadersCustomBO = nodeHeadersCustomAPI.editNode(nodeHeadersCustomTO);
            return ActResult.initialize(BeanTransform.copyProperties(nodeHeadersCustomBO, NodeHeadersCustomVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除节点表头定制
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            nodeHeadersCustomAPI.deleteNode(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 根据外包单位获取所有节点表头字段
     *
     * @param outUnit 外包单位
     * @des 获取所有的外包单位
     * @version v1
     */
    @GetMapping("v1/nodeByOutUnit")
    public Result findNodeByOutUnit(@RequestParam String outUnit) throws ActException {
        try {
            List<NodeHeadersCustomVO> headersCustomVO =
                    BeanTransform.copyProperties(nodeHeadersCustomAPI.getNodeByOutUnit(outUnit), NodeHeadersCustomVO.class);
            return ActResult.initialize(headersCustomVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}