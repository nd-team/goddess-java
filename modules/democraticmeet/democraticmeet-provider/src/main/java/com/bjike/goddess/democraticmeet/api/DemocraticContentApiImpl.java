package com.bjike.goddess.democraticmeet.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.democraticmeet.bo.DemocraticContentBO;
import com.bjike.goddess.democraticmeet.bo.MeetTitleOpinionBO;
import com.bjike.goddess.democraticmeet.dto.DemocraticContentDTO;
import com.bjike.goddess.democraticmeet.service.DemocraticContentSer;
import com.bjike.goddess.democraticmeet.to.DemocraticContentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 民主生活会议组织内容业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:20 ]
 * @Description: [ 民主生活会议组织内容业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("democraticContentApiImpl")
public class DemocraticContentApiImpl implements DemocraticContentAPI {

    @Autowired
    private DemocraticContentSer democraticContentSer;


    @Override
    public Long countDemocraticContent(DemocraticContentDTO democraticContentDTO) throws SerException {
        return democraticContentSer.countDemocraticContent( democraticContentDTO);
    }

    @Override
    public DemocraticContentBO getOneById(String id) throws SerException {
        return democraticContentSer.getOneById(id);
    }

    @Override
    public List<DemocraticContentBO> listDemocraticContent(DemocraticContentDTO democraticContentDTO) throws SerException {
        return democraticContentSer.listDemocraticContent( democraticContentDTO);
    }

    @Override
    public DemocraticContentBO addDemocraticContent(DemocraticContentTO democraticContentTO) throws SerException {
        return democraticContentSer.addDemocraticContent( democraticContentTO);
    }

    @Override
    public DemocraticContentBO editDemocraticContent(DemocraticContentTO democraticContentTO) throws SerException {
        return democraticContentSer.editDemocraticContent( democraticContentTO);
    }

    @Override
    public void deleteDemocraticContent(String id) throws SerException {
        democraticContentSer.deleteDemocraticContent(id);
    }

    @Override
    public List<String> listTitle() throws SerException {
        return democraticContentSer.listTitle();
    }


    @Override
    public List<MeetTitleOpinionBO> listOpinionTitle() throws SerException {
        return democraticContentSer.listOpinionTitle();
    }
}