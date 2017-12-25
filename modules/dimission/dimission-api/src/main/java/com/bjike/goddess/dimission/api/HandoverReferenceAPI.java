package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.HandoverReferenceBO;
import com.bjike.goddess.dimission.dto.HandoverReferenceDTO;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.HandoverReferenceTO;

import java.util.List;

/**
 * 交接信息参考业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:36 ]
 * @Description: [ 交接信息参考业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HandoverReferenceAPI {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 保存
     *
     * @param to 交接信息传输对象
     * @return
     * @throws SerException
     */
    default HandoverReferenceBO save(HandoverReferenceTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 交接信息传输对象
     * @return
     * @throws SerException
     */
    default HandoverReferenceBO update(HandoverReferenceTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 交接信息数据id
     * @return
     * @throws SerException
     */
    default HandoverReferenceBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 交接信息参考数据传输对象
     * @return
     * @throws SerException
     */
    default List<HandoverReferenceBO> maps(HandoverReferenceDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取交接信息参考数据
     *
     * @param id 交接信息参考数据id
     * @return
     * @throws SerException
     */
    default HandoverReferenceBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

}