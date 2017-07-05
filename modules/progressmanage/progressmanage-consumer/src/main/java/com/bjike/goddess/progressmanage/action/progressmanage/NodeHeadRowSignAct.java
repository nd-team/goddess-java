package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.NodeHeadRowSignAPI;
import com.bjike.goddess.progressmanage.api.ProgressNodeAPI;
import com.bjike.goddess.progressmanage.api.ProjectInfoAPI;
import com.bjike.goddess.progressmanage.bo.NodeHeadRowSignBO;
import com.bjike.goddess.progressmanage.dto.NodeHeadRowSignDTO;
import com.bjike.goddess.progressmanage.to.NodeHeadRowSignTO;
import com.bjike.goddess.progressmanage.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 节点表头对应值的行标记
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("nodeheadrowsign")
public class NodeHeadRowSignAct {

    @Autowired
    private ProjectInfoAPI projectInfoAPI;
    @Autowired
    private ProgressNodeAPI progressNodeAPI;
    @Autowired
    private NodeHeadRowSignAPI nodeHeadRowSignAPI;

    /**
     * 项目下拉列表
     *
     * @return class ProjectListForNodeVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {
        try {
            List<ProjectListForNodeVO> voList = BeanTransform.copyProperties(projectInfoAPI.projects(), ProjectListForNodeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 节点下拉列表
     *
     * @return class NodeListForHeadVO
     * @version v1
     */
    @GetMapping("v1/nodes/{projectId}")
    public Result nodes(@PathVariable String projectId, HttpServletRequest request) throws ActException {
        try {
            List<NodeListForHeadVO> voList = BeanTransform.copyProperties(progressNodeAPI.nodes(projectId), NodeListForHeadVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据节点Id查询所有表头
     *
     * @return class NodeHeadForValueVO
     * @version v1
     */
    @GetMapping("v1/heads/{nodeId}")
    public Result heads(@PathVariable String nodeId, HttpServletRequest request) throws ActException {
        try {
            List<NodeHeadForValueVO> voList = BeanTransform.copyProperties(nodeHeadRowSignAPI.heads(nodeId), NodeHeadForValueVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目Id查询所有节点及节点表头对应值
     *
     * @return class NodeHeadRowSignVO
     * @version v1
     */
 /*   @GetMapping("v1/list")
    public Result pageList(@Validated({NodeHeadRowSignDTO.NodeValidate.class}) NodeHeadRowSignDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<NodeHeadRowSignVO> voList = BeanTransform.copyProperties(nodeHeadRowSignAPI.pageList(dto), NodeHeadRowSignVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }   */

    /**
     * 根据节点Id查询所有表头及对应值
     *
     * @return class NodeHeadRowSignVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated({NodeHeadRowSignDTO.NodeValidate.class}) NodeHeadRowSignDTO dto, HttpServletRequest request) throws ActException {
        try {

            List<NodeHeadRowSignVO> voList = new ArrayList<NodeHeadRowSignVO>();
            List<NodeHeadRowSignBO> boList = nodeHeadRowSignAPI.pageList(dto);
            for (NodeHeadRowSignBO bo : boList) {
                NodeHeadRowSignVO vo = new NodeHeadRowSignVO();
                vo.setId(bo.getId());
                vo.setVoList(BeanTransform.copyProperties(bo.getBoList(),NodeHeadValueVO.class));
                voList.add(vo);
            }
//            List<NodeHeadRowSignVO> voList = BeanTransform.copyProperties(nodeHeadRowSignAPI.pageList(dto), NodeHeadRowSignVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 表头信息List
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) NodeHeadRowSignTO to) throws ActException {
        try {
            nodeHeadRowSignAPI.add(to);
            return new ActResult("新增成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 表头信息List
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class})NodeHeadRowSignTO to) throws ActException {
        try {
            nodeHeadRowSignAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 行Id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            nodeHeadRowSignAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}