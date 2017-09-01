package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.NewEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.NewEquity;
import com.bjike.goddess.shareholdersmanage.to.NewEquityTO;

import java.util.List;

/**
 * 新增股权业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NewEquitySer extends Ser<NewEquity, NewEquityDTO> {
    /**
     * 新增股权列表总条数
     */
    default Long countNewEquity(NewEquityDTO newEquityDTO) throws SerException {
        return null;
    }

    /**
     * 一个新增股权
     *
     * @return class NewEquityBO
     */
    default NewEquityBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 新增股权列表
     *
     * @param newEquityDTO 新增股权dto
     * @return class NewEquityBO
     * @throws SerException
     */
    default List<NewEquityBO> findList(NewEquityDTO newEquityDTO) throws SerException {
        return null;
    }

    /**
     * 新增股权添加
     *
     * @param newEquityTO 新增股权数据to
     * @throws SerException
     */
    default NewEquityBO save(NewEquityTO newEquityTO) throws SerException {
        return null;
    }

    /**
     * 新增股权编辑
     *
     * @param newEquityTO 新增股权数据to
     * @throws SerException
     */
    default NewEquityBO edit(NewEquityTO newEquityTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除新增股权
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
    /**
     * 根据股东姓名链接数据
     *
     * @param shareholderName 股东姓名
     * @throws SerException
     */
    default NewEquityLinkDateBO newEqLinkDate(Integer newHoldNum,String shareholderName) throws SerException {
        return null;
    }
}