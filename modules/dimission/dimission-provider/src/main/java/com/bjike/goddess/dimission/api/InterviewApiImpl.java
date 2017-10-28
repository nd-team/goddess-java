package com.bjike.goddess.dimission.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dimission.bo.InterviewBO;
import com.bjike.goddess.dimission.dto.InterviewDTO;
import com.bjike.goddess.dimission.service.InterviewSer;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.InterviewTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 离职管理面谈业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-28 02:39 ]
* @Description:	[ 离职管理面谈业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("interviewApiImpl")
public class InterviewApiImpl implements InterviewAPI  {
    @Autowired
    private InterviewSer interviewSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return interviewSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return interviewSer.guidePermission(guidePermissionTO);
    }

    @Override
    public InterviewBO save(InterviewTO to) throws SerException {
        return interviewSer.add(to);
    }

    @Override
    public InterviewBO update(InterviewTO to) throws SerException {
        return interviewSer.edit(to);
    }

    @Override
    public InterviewBO delete(String id) throws SerException {
        return interviewSer.delete(id);
    }

    @Override
    public List<InterviewBO> list(InterviewDTO dto) throws SerException {
        return interviewSer.list(dto);
    }

    @Override
    public InterviewBO findById(String id) throws SerException {
        return interviewSer.getById(id);
    }

    @Override
    public Boolean judgeManager(String name) throws SerException {
        return interviewSer.judgeManager(name);
    }

    @Override
    public Boolean judgePrincipal(String name) throws SerException {
        return interviewSer.judgePrincipal(name);
    }

    @Override
    public Boolean judgeWelfare(String name) throws SerException {
        return interviewSer.judgeWelfare(name);
    }

    @Override
    public Boolean judgeOffice(String name) throws SerException {
        return interviewSer.judgeOffice(name);
    }

    @Override
    public Boolean detainment(String name) throws SerException {
        return interviewSer.detainment(name);
    }

    @Override
    public Long getTotal() throws SerException {
        return interviewSer.getTotal();
    }
}