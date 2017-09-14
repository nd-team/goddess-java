package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.dto.PositionUserDetailDTO;
import com.bjike.goddess.organize.entity.PositionUserDetail;

import java.util.List;

/**
 * Created by ike on 17-9-6.
 */
public interface PositionUserDetailSer extends Ser<PositionUserDetail, PositionUserDetailDTO> {

    /**
     * 查询主职状态人员
     * zhuangkaiqin
     */
    default List<String> findMainUser() throws SerException {
        return null;
    }

    /**
     * 查询代理岗位人员
     * zhuangkaiqin
     */
    default List<String> findAgentUser() throws SerException {
        return null;
    }

    /**
     * 获取岗位
     * zhuangkaiqin
     *
     * @return
     * @throws SerException
     */
    default List<String> getPosition() throws SerException {
        return null;
    }

    /**
     * 根据时间获取地区个数
     */
    default Long getAreaNum(String startTime,String endTime) throws SerException {
        return null;
    }
}
