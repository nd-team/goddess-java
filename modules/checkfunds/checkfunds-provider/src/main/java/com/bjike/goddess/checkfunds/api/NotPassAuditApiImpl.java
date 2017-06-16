package com.bjike.goddess.checkfunds.api;


import com.bjike.goddess.checkfunds.bo.NotPassAuditBO;
import com.bjike.goddess.checkfunds.dto.NotPassAuditDTO;
import com.bjike.goddess.checkfunds.service.NotPassAuditSer;
import com.bjike.goddess.checkfunds.to.NotPassAuditTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 审批不通过记录业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:23 ]
 * @Description: [ 审批不通过记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("notPassAuditApiImpl")
public class NotPassAuditApiImpl implements NotPassAuditAPI {
    @Autowired
    private NotPassAuditSer notPassAuditSer;

    @Override
    public NotPassAuditBO save(NotPassAuditTO to) throws SerException {
        return notPassAuditSer.save(to);
    }

    @Override
    public List<NotPassAuditBO> list(NotPassAuditDTO dto) throws SerException {
        return notPassAuditSer.list(dto);
    }

    @Override
    public Long countNum(NotPassAuditDTO dto) throws SerException {
        return notPassAuditSer.countNum(dto);
    }
}

