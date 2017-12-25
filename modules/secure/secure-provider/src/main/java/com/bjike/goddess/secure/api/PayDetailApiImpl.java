package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.PayDetailBO;
import com.bjike.goddess.secure.dto.PayDetailDTO;
import com.bjike.goddess.secure.service.PayDetailSer;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.PayDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 缴费比例明细业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 12:55 ]
 * @Description: [ 缴费比例明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("payDetailApiImpl")
public class PayDetailApiImpl implements PayDetailAPI {
    @Autowired
    private PayDetailSer payDetailSer;

    @Override
    public PayDetailBO save(PayDetailTO to) throws SerException {
        return payDetailSer.save(to);
    }

    @Override
    public void edit(PayDetailTO to) throws SerException {
        payDetailSer.edit(to);
    }

    @Override
    public List<PayDetailBO> list(PayDetailDTO dto) throws SerException {
        return payDetailSer.list(dto);
    }

    @Override
    public PayDetailBO findByID(String id) throws SerException {
        return payDetailSer.findByID(id);
    }

    @Override
    public void delete(String id) throws SerException {
        payDetailSer.delete(id);
    }

    @Override
    public Long count(PayDetailDTO dto) throws SerException {
        return payDetailSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return payDetailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return payDetailSer.guidePermission(guidePermissionTO);
    }
}