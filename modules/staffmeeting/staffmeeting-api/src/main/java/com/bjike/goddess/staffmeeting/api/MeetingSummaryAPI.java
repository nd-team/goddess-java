package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;

import java.util.List;

/**
 * 员工代表大会纪要业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingSummaryAPI {

    /**
     * 根据Id查询会议纪要
     *
     * @param id 会议纪要Id
     * @return 会议纪要
     */
    MeetingSummaryBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     * @throws SerException
     */
    Long count(MeetingSummaryDTO dto) throws SerException;

    /**
     * 编辑会议纪要
     *
     * @param to 会议纪要
     * @return 会议纪要
     */
    MeetingSummaryBO edit(MeetingSummaryTO to) throws SerException;

    /**
     * 冻结会议纪要
     *
     * @param id 会议纪要Id
     */
    void freeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingSummaryBO> pageList(MeetingSummaryDTO dto) throws SerException;
}