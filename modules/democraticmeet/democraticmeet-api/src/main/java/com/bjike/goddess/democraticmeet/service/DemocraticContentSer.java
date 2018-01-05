package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.democraticmeet.bo.DemocraticContentBO;
import com.bjike.goddess.democraticmeet.bo.MeetTitleOpinionBO;
import com.bjike.goddess.democraticmeet.entity.DemocraticContent;
import com.bjike.goddess.democraticmeet.dto.DemocraticContentDTO;
import com.bjike.goddess.democraticmeet.to.DemocraticContentTO;

import java.util.List;

/**
 * 民主生活会议组织内容业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemocraticContentSer extends Ser<DemocraticContent, DemocraticContentDTO> {

    /**
     * 民主生活会议组织内容列表总条数
     *
     */
    default Long countDemocraticContent(DemocraticContentDTO democraticContentDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取民主生活会议组织内容列表
     * @return class DemocraticContentBO
     */
    default DemocraticContentBO getOneById(String id) throws SerException {return null;}

    /**
     * 民主生活会议组织内容列表
     * @return class DemocraticContentBO
     */
    default List<DemocraticContentBO> listDemocraticContent(DemocraticContentDTO democraticContentDTO) throws SerException {return null;}
    /**
     *  添加
     * @param democraticContentTO 民主生活会议组织内容信息
     * @return class DemocraticContentBO
     */
    default DemocraticContentBO addDemocraticContent(DemocraticContentTO democraticContentTO) throws SerException { return null;}

    /**
     *  编辑
     * @param democraticContentTO 民主生活会议组织内容信息
     * @return class DemocraticContentBO
     */
    default DemocraticContentBO editDemocraticContent(DemocraticContentTO democraticContentTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteDemocraticContent(String id ) throws SerException {return;};


    /**
     * 所有会议议题
     */
    default List<String> listTitle(    ) throws SerException {return null;};


    /**
     * 所有没有会议纪要的会议议题
     */
    default List<MeetTitleOpinionBO> listOpinionTitle(    ) throws SerException {return null;};



}