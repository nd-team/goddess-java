package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.SettleProgressRecordBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.service.SettleProgressRecordSer;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettleProgressRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 结算进度调整记录&结算问题汇总业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度调整记录&结算问题汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("settleProgressRecordApiImpl")
public class SettleProgressRecordApiImpl implements SettleProgressRecordAPI {
    @Autowired
    private SettleProgressRecordSer settleProgressRecordSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return settleProgressRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return settleProgressRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        return settleProgressRecordSer.countManage(settleProgressRecordDTO);
    }

    @Override
    public SettleProgressRecordBO getOneById(String id) throws SerException {
        return settleProgressRecordSer.getOneById(id);
    }

    @Override
    public List<SettleProgressRecordBO> listManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        return settleProgressRecordSer.listManage(settleProgressRecordDTO);
    }

    @Override
    public SettleProgressRecordBO addManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        return settleProgressRecordSer.addManage(settleProgressRecordTO);
    }

    @Override
    public SettleProgressRecordBO editManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        return settleProgressRecordSer.editManage(settleProgressRecordTO);
    }

    @Override
    public void deleteManage(String id) throws SerException {
        settleProgressRecordSer.deleteManage(id);
    }

    @Override
    public void checkAnalysis(String id, String moneyModule, String moneyModuleOpinion) throws SerException {
        settleProgressRecordSer.checkAnalysis(id,moneyModule,moneyModuleOpinion);
    }

    @Override
    public void confirm(String id, String generalManager, String approvalExam, Boolean confirm) throws SerException {
        settleProgressRecordSer.confirm(id,generalManager,approvalExam,confirm);
    }
}