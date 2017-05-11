package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.bankrecords.bo.BankAccountInfoBO;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.entity.BankAccountInfo;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 账号信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bankrecordsSerCache")
@Service
public class BankAccountInfoSerImpl extends ServiceImpl<BankAccountInfo, BankAccountInfoDTO> implements BankAccountInfoSer {

    @Override
    public BankAccountInfoBO insertModel(BankAccountInfoTO to) throws SerException {
        BankAccountInfo model = BeanTransform.copyProperties(to, BankAccountInfo.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, BankAccountInfoBO.class);
    }

    @Override
    public BankAccountInfoBO updateModel(BankAccountInfoTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            BankAccountInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("无法更新记账凭证查询的数据!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, BankAccountInfoBO.class);
    }

    @Override
    public List<BankAccountInfoBO> pageList(BankAccountInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), BankAccountInfoBO.class);
    }
}