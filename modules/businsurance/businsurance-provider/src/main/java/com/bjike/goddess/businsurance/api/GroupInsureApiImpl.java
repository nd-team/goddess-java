package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.GroupInsureBO;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.service.GroupInsureSer;
import com.bjike.goddess.businsurance.to.GroupInsureTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团体意外险信息管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("groupInsureApiImpl")
public class GroupInsureApiImpl implements GroupInsureAPI {

    @Autowired
    private GroupInsureSer groupInsureSer;

    @Override
    public Long countGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        return groupInsureSer.count(groupInsureDTO);
    }

    @Override
    public List<GroupInsureBO> listGroupInsure(GroupInsureDTO groupInsureDTO) throws SerException {
        return groupInsureSer.listGroupInsure(groupInsureDTO);
    }

    @Override
    public GroupInsureBO addGroupInsure(GroupInsureTO groupInsureTO) throws SerException {
        return groupInsureSer.addGroupInsure(groupInsureTO);
    }

    @Override
    public GroupInsureBO editGroupInsure(GroupInsureTO groupInsureTO) throws SerException {
        return groupInsureSer.editGroupInsure(groupInsureTO);
    }

    @Override
    public void deleteGroupInsure(String id) throws SerException {
        groupInsureSer.deleteGroupInsure(id);
    }

    @Override
    public GroupInsureBO getGroupInsure(String id) throws SerException {
        return groupInsureSer.getGroupInsure( id );
    }
}