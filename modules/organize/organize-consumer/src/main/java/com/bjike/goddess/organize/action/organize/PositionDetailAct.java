package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.ReHierarchyBO;
import com.bjike.goddess.organize.to.PositionDetailTO;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.organize.vo.ReHierarchyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 岗位详细
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
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findStatus(), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据岗位ID集合查询
     *
     * @param ids 岗位详细数据id集合
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/findByPostIds")
    public Result findByPostIds(String[] ids, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findByPostIds(ids), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询下级层级职位详细
     *
     * @param postId 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/findChildByArrangement")
    public Result findChildByArrangement(String postId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findChildByArrangement(postId), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询上级层级职位详细
     *
     * @param postId 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/findParentByArrangement")
    public Result findParentByArrangement(String postId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findParentByArrangement(postId), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询职位详细信息
     *
     * @param id 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/findBOById")
    public Result findBOById(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findBOById(id), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 增加岗位详细信息
     *
     * @param to 岗位详细传输对象
     * @return class PositionDetailVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PositionDetailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.save(to), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 修改职位详细信息
     *
     * @param to 岗位详细传输对象
     * @return class PositionDetailVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PositionDetailTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.update(to), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.delete(id), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @return class ReHierarchyVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(HttpServletRequest request) throws ActException {
        try {
            List<ReHierarchyBO> list = positionDetailAPI.list();
            return ActResult.initialize(BeanTransform.copyProperties(list, ReHierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(positionDetailAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询岗位详细
     *
     * @param id 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findBOById(id), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.congeal(id), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 岗位详细数据id
     * @return class PositionDetailVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.thaw(id), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询未冻结职位选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findThawOpinion(), OpinionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取真实编号
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/number")
    public Result number(PositionDetailTO to) throws ActException {
        try {
            return ActResult.initialize(positionDetailAPI.number(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


//    /**
//     * 获取所有的岗位
//     *
//     * @version v1
//     */
//    @GetMapping("v1/id")
//    public Result findAllOpinion(String id) throws ActException {
//        try {
//            List<String> getOpinion = positionDetailAPI.getPositions(id);
//            return ActResult.initialize(getOpinion);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}
