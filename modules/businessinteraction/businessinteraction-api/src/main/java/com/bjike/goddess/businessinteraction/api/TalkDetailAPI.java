package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.TalkDetailBO;
import com.bjike.goddess.businessinteraction.dto.TalkDetailDTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.SonPermissionObject;
import com.bjike.goddess.businessinteraction.to.TalkDetailTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 资料信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:48 ]
 * @Description: [ 资料信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TalkDetailAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 资料信息列表总条数
     */
    default Long countInter(TalkDetailDTO talkDetailDTO) throws SerException {
        return null;
    }

    /**
     * 一个资料信息
     *
     * @return class TalkDetailBO
     */
    default TalkDetailBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 资料信息列表
     *
     * @return class TalkDetailBO
     */
    default List<TalkDetailBO> listIntera(TalkDetailDTO talkDetailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param talkDetailTO 资料信息
     * @return class TalkDetailBO
     */
    default TalkDetailBO addIntera(TalkDetailTO talkDetailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param talkDetailTO 资料信息
     * @return class TalkDetailBO
     */
    default TalkDetailBO editIntera(TalkDetailTO talkDetailTO) throws SerException {
        return null;
    }

    /**
     * 删除资料信息
     *
     * @param id id
     */
    default void deleteIntera(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param talkDetailTOS 资料信息
     */
    void importExcel(List<TalkDetailTO> talkDetailTOS) throws SerException;
    /**
     * 获取所有的业务类型
     *
     * @throws SerException
     */
    default List<String> findBussType() throws SerException {
        return null;
    }

}