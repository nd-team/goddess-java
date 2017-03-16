package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报道地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("reportAddressInforApiImpl")
public class ReportAddressInforApiImpl implements ReportAddressInforAPI {
    @Override
    public List<ReportAddressInforBO> list(ReportAddressInforDTO dto) throws SerException {
        return null;
    }

    @Override
    public ReportAddressInforBO save(ReportAddressInforTO reportAddressInforTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(ReportAddressInforTO reportAddressInforTO) throws SerException {

    }
}
