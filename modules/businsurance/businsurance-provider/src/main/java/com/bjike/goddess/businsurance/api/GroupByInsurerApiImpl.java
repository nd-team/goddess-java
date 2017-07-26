package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.GroupByInsurerBO;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.service.GroupByInsurerSer;
import com.bjike.goddess.businsurance.to.GroupByInsurerTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团体意外险被保险人信息管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("groupByInsurerApiImpl")
public class GroupByInsurerApiImpl implements GroupByInsurerAPI {

    @Autowired
    private GroupByInsurerSer groupByInsurerSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return groupByInsurerSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return groupByInsurerSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countGroupByInsurer(GroupByInsurerDTO groupByInsurerDTO) throws SerException {
        return groupByInsurerSer.count(groupByInsurerDTO);
    }

    @Override
    public List<GroupByInsurerBO> listGroupByInsurer(GroupByInsurerDTO groupByInsurerDTO) throws SerException {
        return groupByInsurerSer.listGroupByInsurer(groupByInsurerDTO);
    }

    @Override
    public GroupByInsurerBO addGroupByInsurer(GroupByInsurerTO groupByInsurerTO) throws SerException {
        return groupByInsurerSer.addGroupByInsurer(groupByInsurerTO);
    }

    @Override
    public GroupByInsurerBO editGroupByInsurer(GroupByInsurerTO groupByInsurerTO) throws SerException {
        return groupByInsurerSer.editGroupByInsurer(groupByInsurerTO);
    }

    @Override
    public void deleteGroupByInsurer(String id) throws SerException {
        groupByInsurerSer.deleteGroupByInsurer(id);
    }

    @Override
    public GroupByInsurerBO getGroupByInsurer(String id) throws SerException {
        return groupByInsurerSer.getGroupByInsurer(id);
    }
}