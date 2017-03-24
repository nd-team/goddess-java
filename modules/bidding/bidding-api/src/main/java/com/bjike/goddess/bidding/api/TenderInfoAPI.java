package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.entity.TenderInfo;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 标书资料业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.312 ]
 * @Description: [ 标书资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TenderInfoAPI {
    /**
     * 获取所有标书资料
     *
     * @param tenderInfoDTO 标书资料dto
     * @return class tenderInfoBO
     * @throws SerException
     */
    default List<TenderInfoBO> findListTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加标书资料
     *
     * @param tenderInfoTO 标书资料数据to
     * @throws SerException
     */
    default TenderInfoBO insertTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑标书资料
     *
     * @param tenderInfoTO 标书资料数据to
     * @return class tenderInfoBO
     * @throws SerException
     */
    default TenderInfoBO editTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除标书资料
     *
     * @param id
     * @throws SerException
     */
    default void removeTenderInfo(String id) throws SerException {

    }

    /**
     * 导出
     *
     * @param projectName
     * @throws SerException
     */
    default String exportExcel(String projectName) throws SerException {
        return null;
    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

    /**
     * 上传附件
     */
    default void uploadAttachments() throws SerException {
        return;
    }

}