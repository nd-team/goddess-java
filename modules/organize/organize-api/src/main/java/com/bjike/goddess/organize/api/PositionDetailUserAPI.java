package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;

import java.util.List;

/**
 * 用户职位业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionDetailUserAPI {

    /**
     * 保存
     *
     * @param to 用户职位传输对象
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO save(PositionDetailUserTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 用户职位传输对象
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO update(PositionDetailUserTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 用户职位数据id
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据用户id查询职位详细数据
     *
     * @param user_id 用户id
     * @return
     * @throws SerException
     */
    default List<PositionDetailBO> findPositionByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 获取用户职位数据
     *
     * @param user_id 用户id
     * @return
     * @throws SerException
     */
    default PositionDetailUserBO findOneByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定职位中
     *
     * @param user_id      用户id
     * @param position_ids 职位id数组
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserPosition(String user_id, String[] position_ids) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定职位中
     *
     * @param user_id      用户id
     * @param position_ids 职位详细id数组
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserPositionDetail(String user_id, String[] position_ids) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定层级中
     *
     * @param user_id        用户id
     * @param arrangement_id 层级id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserArrangement(String user_id, String arrangement_id) throws SerException {
        return null;
    }

    /**
     * 检测用户是否在指定模块中
     *
     * @param user_id   用户id
     * @param module_id 模块id
     * @return
     * @throws SerException
     */
    default Boolean checkAsUserModule(String user_id, String module_id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 用户职位数据传输对象
     * @return
     * @throws SerException
     */
    default List<PositionDetailUserBO> maps(PositionDetailUserDTO dto) throws SerException {
        return null;
    }

}