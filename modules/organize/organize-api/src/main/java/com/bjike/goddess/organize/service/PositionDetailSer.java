package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.to.PositionDetailTO;

import java.util.Collection;
import java.util.List;

/**
 * 岗位详细业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionDetailSer extends Ser<PositionDetail, PositionDetailDTO> {

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
     * 根据职位ID查询该职位下级层级职位详细
     *
     * @param postId 职位ID
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findChildByArrangement(String postId) throws SerException {
        return null;
    }

    /**
     * 根据职位ID查询该职位上级层级职位详细
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
     * @param to 岗位详细传输对象
     * @return
     * @throws SerException
     */
    default PositionDetailBO save(PositionDetailTO to) throws SerException {
        return null;
    }

    /**
     * 修改职位详细
     *
     * @param to 岗位详细传输对象
     * @return
     * @throws SerException
     */
    default PositionDetailBO update(PositionDetailTO to) throws SerException {
        return null;
    }

    /**
     * 转换岗位详细传输对象
     *
     * @param list 岗位详细数据对象集合
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> transformationToBOList(Collection<PositionDetail> list) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 岗位详细数据id
     * @return
     * @throws SerException
     */
    default PositionDetailBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 岗位详细数据id
     * @return
     * @throws SerException
     */
    default PositionDetailBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 岗位详细数据id
     * @return
     * @throws SerException
     */
    default PositionDetailBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 岗位详细数据传输
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> maps(PositionDetailDTO dto) throws SerException {
        return null;
    }

    /**
     * 查询未冻结职位选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException {
        return null;
    }
}
