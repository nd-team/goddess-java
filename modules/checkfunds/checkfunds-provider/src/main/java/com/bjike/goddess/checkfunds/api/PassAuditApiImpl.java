package com.bjike.goddess.checkfunds.api;


import com.bjike.goddess.checkfunds.bo.PassAuditBO;
import com.bjike.goddess.checkfunds.dto.PassAuditDTO;
import com.bjike.goddess.checkfunds.service.PassAuditSer;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.to.PassAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 已完成核对记录业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:18 ]
 * @Description: [ 已完成核对记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("passAuditApiImpl")
public class PassAuditApiImpl implements PassAuditAPI {
    @Autowired
    private PassAuditSer passAuditSer;

    @Override
    public PassAuditBO save(PassAuditTO to) throws SerException {
        return passAuditSer.save(to);
    }

    @Override
    public List<PassAuditBO> list(PassAuditDTO dto) throws SerException {
        return passAuditSer.list(dto);
    }

    @Override
    public Long countNum(PassAuditDTO dto) throws SerException {
        return passAuditSer.countNum(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return passAuditSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return passAuditSer.guidePermission(guidePermissionTO);
    }
}
