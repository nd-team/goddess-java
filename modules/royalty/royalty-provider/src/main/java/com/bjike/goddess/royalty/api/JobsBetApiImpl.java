package com.bjike.goddess.royalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.royalty.bo.JobsBetABO;
import com.bjike.goddess.royalty.bo.JobsBetBO;
import com.bjike.goddess.royalty.bo.ManageCommissionBO;
import com.bjike.goddess.royalty.dto.JobsBetDTO;
import com.bjike.goddess.royalty.dto.JobsBetEDTO;
import com.bjike.goddess.royalty.service.JobsBetSer;
import com.bjike.goddess.royalty.to.CollectTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.JobsBetATO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位间对赌表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("jobsBetApiImpl")
public class JobsBetApiImpl implements JobsBetAPI {
    @Autowired
    private JobsBetSer jobsBetSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return jobsBetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return jobsBetSer.guidePermission( guidePermissionTO );
    }

    @Override
    public Long count(JobsBetEDTO dto) throws SerException {
        return jobsBetSer.count(dto);
    }

    @Override
    public JobsBetABO getOne(String id) throws SerException {
        return jobsBetSer.getOne(id);
    }

    @Override
    public List<JobsBetBO> list(JobsBetDTO dto) throws SerException {
        return jobsBetSer.list(dto);
    }

    @Override
    public void insert(JobsBetATO jobsBetATO) throws SerException {
        jobsBetSer.insert(jobsBetATO);
    }

    @Override
    public void edit(JobsBetATO jobsBetATO) throws SerException {
        jobsBetSer.edit(jobsBetATO);
    }

    @Override
    public void delete(String id) throws SerException {
        jobsBetSer.delete(id);
    }
    @Override
    public List<ManageCommissionBO> collect(CollectTO to) throws SerException {
        return jobsBetSer.collect(to);
    }
}