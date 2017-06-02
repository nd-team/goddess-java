package com.bjike.goddess.salaryconfirm.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.salaryconfirm.bo.InvoiceSubmitBO;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.entity.InvoiceSubmit;
import com.bjike.goddess.salaryconfirm.to.InvoiceSubmitTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 上交发票业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 上交发票业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "salaryconfirmSerCache")
@Service
public class InvoiceSubmitSerImpl extends ServiceImpl<InvoiceSubmit, InvoiceSubmitDTO> implements InvoiceSubmitSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public InvoiceSubmitBO insertModel(InvoiceSubmitTO to) throws SerException {
        String userName = userAPI.currentUser().getUsername();
        InvoiceSubmit model = BeanTransform.copyProperties(to, InvoiceSubmit.class, true);
        model.setCreateUser(userName);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, InvoiceSubmitBO.class);
    }

    @Override
    public InvoiceSubmitBO updateModel(InvoiceSubmitTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            InvoiceSubmit model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                model.setModifyUser(userAPI.currentUser().getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, InvoiceSubmitBO.class);
    }

    @Override
    public List<InvoiceSubmitBO> pageList(InvoiceSubmitDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), InvoiceSubmitBO.class);
    }

    @Override
    public List<InvoiceSubmitBO> findByCondition(String employeeNumber, Integer year, Integer month) throws SerException {
        InvoiceSubmitDTO dto = new InvoiceSubmitDTO();
        dto.getConditions().add(Restrict.eq("employeeNumber",employeeNumber));
        //查询指定月份前的发票数据
        dto.getConditions().add(Restrict.lt("submitDate",LocalDate.of(year,month, 1)));
        return BeanTransform.copyProperties(super.findByCis(dto),InvoiceSubmitBO.class);
    }
}