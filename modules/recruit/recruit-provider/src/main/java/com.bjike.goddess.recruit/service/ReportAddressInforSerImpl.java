package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.entity.ReportAddressInfor;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ReportAddressInforSerImpl extends ServiceImpl<ReportAddressInfor, ReportAddressInforDTO> implements ReportAddressInforSer {
    @Override
    public List<ReportAddressInforBO> list(ReportAddressInforDTO dto) throws SerException {
        return null;
    }

    @Override
    public ReportAddressInforBO save(ReportAddressInforTO reportAddressInforTO) throws SerException {
        return null;
    }

    @Override
    public void update(ReportAddressInforTO reportAddressInforTO) throws SerException {

    }
}
