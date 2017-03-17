package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.to.PositionDetailTO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位详细操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("positionDetail")
public class PositionDetailAct {

    @Autowired
    private PositionDetailAPI positionDetailAPI;


    /**
     * 查询正常状态的岗位详细
     *
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findStatus(), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据职位ID集合查询
     *
     * @param ids 职位ID集合
     * @version v1
     */
    @GetMapping("v1/findByPostIds")
    public Result findByPostIds(String[] ids) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findByPostIds(ids), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据职位ID查询
     *
     * @param id 职位ID
     * @version v1
     */
    @GetMapping("v1/findByPostId")
    public Result findByPostId(String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findByPostId(id), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据上级职位ID查询
     *
     * @param parentId 上级职位ID
     * @version v1
     */
    @GetMapping("v1/findChild")
    public Result findChild(String parentId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findChild(parentId), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据职位ID查询直接上级职位详细
     *
     * @param postId 职位ID
     * @version v1
     */
    @GetMapping("v1/findParent")
    public Result findParent(String postId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findParent(postId), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询下级层级职位详细
     *
     * @param postId 职位ID
     * @version v1
     */
    @GetMapping("v1/findChildByArrangement")
    public Result findChildByArrangement(String postId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findChildByArrangement(postId), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询上级层级职位详细
     *
     * @param postId 职位ID
     * @version v1
     */
    @GetMapping("v1/findParentByArrangement")
    public Result findParentByArrangement(String postId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findParentByArrangement(postId), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询职位详细信息
     *
     * @param id 职位详细ID
     * @version v1
     */
    @GetMapping("v1/findBOById")
    public Result findBOById(String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findBOById(id), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 增加岗位详细信息
     *
     * @param to 岗位详细传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(PositionDetailTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.save(to), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 修改职位详细信息
     *
     * @param to 岗位详细传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(PositionDetailTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.update(to), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
