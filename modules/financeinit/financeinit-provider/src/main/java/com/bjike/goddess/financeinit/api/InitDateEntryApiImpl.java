package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.InitDateEntryBO;
import com.bjike.goddess.financeinit.dto.InitDateEntryDTO;
import com.bjike.goddess.financeinit.service.InitDateEntrySer;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.InitDateEntryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 初始化数据录入业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:21 ]
 * @Description: [ 初始化数据录入业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("initDateEntryApiImpl")
public class InitDateEntryApiImpl implements InitDateEntryAPI {
    @Autowired
    private InitDateEntrySer initDateEntrySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return initDateEntrySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return initDateEntrySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countInit(InitDateEntryDTO initDateEntryDTO) throws SerException {
        return initDateEntrySer.countInit(initDateEntryDTO);
    }

    @Override
    public InitDateEntryBO getOneById(String id) throws SerException {
        return initDateEntrySer.getOneById(id);
    }

    @Override
    public List<InitDateEntryBO> listInit(InitDateEntryDTO initDateEntryDTO) throws SerException {
        return initDateEntrySer.listInit(initDateEntryDTO);
    }

    @Override
    public InitDateEntryBO editInit(InitDateEntryTO initDateEntryTO) throws SerException {
        return initDateEntrySer.editInit(initDateEntryTO);
    }

    @Override
    public String trialBalance() throws SerException {
        return initDateEntrySer.trialBalance();
    }
}