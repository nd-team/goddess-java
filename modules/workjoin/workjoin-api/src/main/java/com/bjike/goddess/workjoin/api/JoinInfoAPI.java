package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.JoinInfoTO;

import java.util.List;

/**
 * 交接资料业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface JoinInfoAPI {
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
     * 交接资料列表总条数
     */
    default Long countJoinInfo(JoinInfoDTO joinInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个交接资料
     *
     * @return class JoinInfoBO
     */
    default JoinInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 交接资料
     *
     * @param joinInfoDTO 交接资料dto
     * @return class JoinInfoBO
     * @throws SerException
     */
    default List<JoinInfoBO> findListJoinInfo(JoinInfoDTO joinInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加交接资料
     *
     * @param joinInfoTO 交接资料数据to
     * @return class JoinInfoBO
     * @throws SerException
     */
    default JoinInfoBO insertJoinInfo(JoinInfoTO joinInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑交接资料
     *
     * @param joinInfoTO 交接资料数据to
     * @return class JoinInfoBO
     * @throws SerException
     */
    default JoinInfoBO editJoinInfo(JoinInfoTO joinInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除交接资料
     *
     * @throws SerException
     */
    default void removeJoinInfo(String id) throws SerException {

    }

    /**
     * 获取制度文件夹编号和经验总结编号
     * jiangzaixuan
     */
    List<NumSpecificationBO> findNumSepecification() throws SerException;

}