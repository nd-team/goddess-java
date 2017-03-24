package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.to.PositionDetailTO;

import java.util.List;

/**
 * 对外岗位详细业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionDetailAPI {

    /**
     * 查询正常状态的岗位详细
     *
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 根据职位ID集合查询
     *
     * @param ids 职位ID集合
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findByPostIds(String[] ids) throws SerException {
        return null;
    }

    /**
     * 根据职位ID查询
     *
     * @param id 职位ID
     * @return
     * @throws SerException
     */
    default PositionDetailBO findByPostId(String id) throws SerException {
        return null;
    }

    /**
     * 根据上级职位ID查询
     *
     * @param parentId 上级职位ID
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findChild(String parentId) throws SerException {
        return null;
    }

    /**
     * 根据职位ID查询直接上级职位详细
     *
     * @param postId 职位ID
     * @return
     * @throws SerException
     */
    default PositionDetailBO findParent(String postId) throws SerException {
        return null;
    }

    /**
     * 查询下级层级职位详细
     *
     * @param postId 职位ID
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findChildByArrangement(String postId) throws SerException {
        return null;
    }

    /**
     * 查询上级层级职位详细
     *
     * @param postId 职位ID
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findParentByArrangement(String postId) throws SerException {
        return null;
    }

    /**
     * 根据ID查询职位详细信息
     *
     * @param id 职位详细ID
     * @return
     * @throws SerException
     */
    default PositionDetailBO findBOById(String id) throws SerException {
        return null;
    }

    /**
     * 增加岗位详细
     *
     * @param to
     * @return
     * @throws SerException
     */
    default PositionDetailBO save(PositionDetailTO to) throws SerException {
        return null;
    }

    /**
     * 修改职位详细
     *
     * @param to
     * @return
     * @throws SerException
     */
    default PositionDetailBO update(PositionDetailTO to) throws SerException {
        return null;
    }
}
