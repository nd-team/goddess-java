package com.bjike.goddess.commerce.api;

import com.bjike.goddess.commerce.bo.CommerceConferenceBO;
import com.bjike.goddess.commerce.dto.CommerceConferenceDTO;
import com.bjike.goddess.commerce.to.CollectTO;
import com.bjike.goddess.commerce.to.CommerceConferenceExcelTO;
import com.bjike.goddess.commerce.to.CommerceConferenceTO;
import com.bjike.goddess.commerce.to.GuidePermissionTO;
import com.bjike.goddess.commerce.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 商务会议业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommerceConferenceAPI {


    /**
     * 保存
     *
     * @param to 商务会议传输对象
     * @return
     * @throws SerException
     */
    default CommerceConferenceBO save(CommerceConferenceTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 商务会议传输对象
     * @return
     * @throws SerException
     */
    default CommerceConferenceBO update(CommerceConferenceTO to) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 商务会议数据id
     * @return
     * @throws SerException
     */
    default CommerceConferenceBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询商务会议数据
     *
     * @param id 商务会议数据id
     * @return
     * @throws SerException
     */
    default CommerceConferenceBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 商务会议数据传输对象
     * @return
     * @throws SerException
     */
    default List<CommerceConferenceBO> maps(CommerceConferenceDTO dto) throws SerException {
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

    /**
     * 上传
     *
     * @param list 商务会议业务上传数据传输对象集合
     * @throws SerException
     */
    void upload(List<CommerceConferenceExcelTO> list) throws SerException;

    /**
     * 导出
     *
     * @param to 导出查询条件传输对象
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 根据时间查询商务会议数据
     *
     * @param to 导出查询条件传输对象
     * @return
     * @throws SerException
     */
    default List<CommerceConferenceBO> findByTime(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 根据编号查询商务会议数据
     *
     * @param serialNumber 编号
     * @return
     * @throws SerException
     */
    default CommerceConferenceBO findByNumber(String serialNumber) throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param tocs 商务会议
     * @return class CommerceConferenceBO
     */
    default CommerceConferenceBO importExcel(List<CommerceConferenceTO> tocs) throws SerException {
        return null;
    }

    /**
     * 导出Excel导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;
}