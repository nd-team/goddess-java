package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.CollectSendBO;
import com.bjike.goddess.supplier.dto.CollectSendDTO;
import com.bjike.goddess.supplier.entity.CollectSend;
import com.bjike.goddess.supplier.to.CollectSendTO;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;

import java.util.List;

/**
 * 供应商汇总业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-14 11:48 ]
 * @Description: [ 供应商汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CollectSendSer extends Ser<CollectSend, CollectSendDTO> {

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
     * @param to 供应商汇总传输对象
     * @return
     * @throws SerException
     */
    default CollectSendBO save(CollectSendTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 供应商汇总传输对象
     * @return
     * @throws SerException
     */
    default CollectSendBO update(CollectSendTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 供应商汇总数据id
     * @return
     * @throws SerException
     */
    default CollectSendBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 供应商汇总数据id
     * @return
     * @throws SerException
     */
    default CollectSendBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 供应商汇总数据id
     * @return
     * @throws SerException
     */
    default CollectSendBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商汇总数据
     *
     * @param id 供应商汇总数据id
     * @return
     * @throws SerException
     */
    default CollectSendBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 供应商汇总数据传输对象
     * @return
     * @throws SerException
     */
    default List<CollectSendBO> maps(CollectSendDTO dto) throws SerException {
        return null;
    }

    /**
     * 查询未冻结数据
     *
     * @return
     * @throws SerException
     */
    default List<CollectSendBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto 供应商汇总数据传输对象
     * @return
     * @throws SerException
     */
    default Long getTotal(CollectSendDTO dto) throws SerException {
        return null;
    }

    /**
     * 返回汇总表格
     *
     * @param to 供应商汇总传输对象
     * @return
     * @throws SerException
     */
    default String collect(CollectTo to) throws SerException {
        return null;
    }

    /**
     * 发送邮件
     *
     * @throws SerException
     */
    void sendEmail() throws SerException;

}