package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.entity.ReportAddressInfor;
import com.bjike.goddess.recruit.service.ReportAddressInforSer;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报道地址信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("reportAddressInforApiImpl")
public class ReportAddressInforApiImpl implements ReportAddressInforAPI {

    @Autowired
    private ReportAddressInforSer reportAddressInforSer;

    /**
     * 根据id查询报道地址信息
     *
     * @param id 报道地址信息唯一标识
     * @return class ReportAddressInforBO
     * @throws SerException
     */
    @Override
    public ReportAddressInforBO findById(String id) throws SerException {
        ReportAddressInfor model = reportAddressInforSer.findById(id);
        return BeanTransform.copyProperties(model, ReportAddressInforBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 报道地址信息dto
     * @throws SerException
     */
    @Override
    public Long count(ReportAddressInforDTO dto) throws SerException {
        return reportAddressInforSer.count(dto);
    }

    /**
     * 分页查询报道地址信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<ReportAddressInforBO> list(ReportAddressInforDTO dto) throws SerException {
        return reportAddressInforSer.list(dto);
    }

    /**
     * 保存报道地址信息
     *
     * @param reportAddressInforTO
     * @return
     * @throws SerException
     */
    @Override
    public ReportAddressInforBO save(ReportAddressInforTO reportAddressInforTO) throws SerException {
        return reportAddressInforSer.save(reportAddressInforTO);
    }

    /**
     * 根据id删除报道地址信息
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        reportAddressInforSer.remove(id);
    }

    /**
     * 更新报道地址信息
     *
     * @param reportAddressInforTO
     * @throws SerException
     */
    @Override
    public void update(ReportAddressInforTO reportAddressInforTO) throws SerException {
        reportAddressInforSer.update(reportAddressInforTO);
    }
}
