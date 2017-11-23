package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.SettleWorkProgreManageBO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.service.SettleProgressRecordSer;
import com.bjike.goddess.projectprocing.service.SettleWorkProgreManageSer;
import com.bjike.goddess.projectprocing.to.SettleWorkProgreManageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 结算工作进度管理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("settleWorkProgreManageApiImpl")
public class SettleWorkProgreManageApiImpl implements SettleWorkProgreManageAPI {
    @Autowired
    private SettleWorkProgreManageSer settleWorkProgreManageSer;
    @Override
    public Long countSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        return settleWorkProgreManageSer.countSettleWork(settleWorkProgreManageDTO);
    }

    @Override
    public SettleWorkProgreManageBO getOneById(String id) throws SerException {
        return settleWorkProgreManageSer.getOneById(id);
    }

    @Override
    public List<SettleWorkProgreManageBO> listSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        return settleWorkProgreManageSer.listSettleWork(settleWorkProgreManageDTO);
    }

    @Override
    public SettleWorkProgreManageBO addSettleWork(SettleWorkProgreManageTO settleWorkProgreManageTO) throws SerException {
        return settleWorkProgreManageSer.addSettleWork(settleWorkProgreManageTO);
    }

    @Override
    public void redistribution(String id, String responsible) throws SerException {
        settleWorkProgreManageSer.redistribution(id,responsible);
    }

    @Override
    public void deleteSettleWork(String id) throws SerException {
        settleWorkProgreManageSer.deleteSettleWork(id);
    }
}