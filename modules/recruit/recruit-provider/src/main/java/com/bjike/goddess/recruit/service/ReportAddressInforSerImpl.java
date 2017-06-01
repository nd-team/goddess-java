package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.ReportAddressInforBO;
import com.bjike.goddess.recruit.dto.ReportAddressInforDTO;
import com.bjike.goddess.recruit.entity.ReportAddressInfor;
import com.bjike.goddess.recruit.to.ReportAddressInforTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
@Service
public class ReportAddressInforSerImpl extends ServiceImpl<ReportAddressInfor, ReportAddressInforDTO> implements ReportAddressInforSer {

    /**
     * 分页查询报道地址信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<ReportAddressInforBO> list(ReportAddressInforDTO dto) throws SerException {
        List<ReportAddressInfor> list = super.findByPage(dto);
        List<ReportAddressInforBO> listBO = BeanTransform.copyProperties(list, ReportAddressInforBO.class);
        return listBO;
    }

    /**
     * 保存报道地址信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ReportAddressInforBO save(ReportAddressInforTO to) throws SerException {
        ReportAddressInfor reportAddressInfor = BeanTransform.copyProperties(to, ReportAddressInfor.class, true);
        reportAddressInfor = super.save(reportAddressInfor);
        ReportAddressInforBO bo = BeanTransform.copyProperties(reportAddressInfor, ReportAddressInforBO.class);
        return bo;
    }

    /**
     * 更新报道地址信息
     *
     * @param to 报道地址信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ReportAddressInforTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ReportAddressInfor model = super.findById(to.getId());
            if (model != null) {
                updateReportAddressInfor(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新报道地址信息
     *
     * @param to    报道地址信息to
     * @param model 报道地址信息实体
     */
    private void updateReportAddressInfor(ReportAddressInforTO to, ReportAddressInfor model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除报道地址信息
     * 
     * @param id 报道地址信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
