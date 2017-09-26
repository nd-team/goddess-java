package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.enums.NoticeType;
import com.bjike.goddess.task.enums.SummaryType;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class CustomizeSerImpl extends ServiceImpl<Customize, CustomizeDTO> implements CustomizeSer {
    @Autowired
    private  UserAPI userAPI;
    @Override
    public List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), CustomizeBO.class);
    }

    @Override
    public Long count(CustomizeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void add(CustomizeTO to) throws SerException {
        String nickname = userAPI.currentUser().getNickname();
        validated(to);
        Customize customize = BeanTransform.copyProperties(to, Customize.class, "tables", "fields");
        customize.setTables(StringUtils.join(to.getTables(), ","));
        customize.setFields(StringUtils.join(to.getFields(), ","));
        customize.setUser(nickname);
        super.save(customize);
    }

    @Override
    public void enable(String id, boolean enable) throws SerException {
        Customize customize = super.findById(id);
        if (null != customize) {
            customize.setEnable(enable);
            super.update(customize);
        } else {
            throw new SerException("找不到该记录");
        }

    }

    private void validated(CustomizeTO to) throws SerException {
        if (!to.getSummaryType().equals(SummaryType.ALL)) {
            if (StringUtils.isBlank(to.getSummaryTarget())) {
                throw new SerException("请指定汇总部门或者人员");
            }
        }
        if (!to.getNoticeType().equals(NoticeType.ALL.ALL)) {
            if (StringUtils.isBlank(to.getNoticeTarget())) {
                throw new SerException("请指定提醒部门或者人员");
            }
        }
    }
}
