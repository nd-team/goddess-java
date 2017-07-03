package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.ConstructTeamBO;
import com.bjike.goddess.enterpriseculturemanage.dto.ConstructTeamDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.ConstructTeam;
import com.bjike.goddess.enterpriseculturemanage.to.ConstructTeamTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 建设小组业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "enterpriseculturemanageSerCache")
@Service
public class ConstructTeamSerImpl extends ServiceImpl<ConstructTeam, ConstructTeamDTO> implements ConstructTeamSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ConstructTeamBO insertModel(ConstructTeamTO to) throws SerException {
        if (isUserNameExist(to)) {
            throw new SerException("该用户已存在!");
        }
        ConstructTeam model = BeanTransform.copyProperties(to, ConstructTeam.class);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ConstructTeamBO.class);
    }

    public Boolean isUserNameExist(ConstructTeamTO to) throws SerException {
        ConstructTeamDTO dto = new ConstructTeamDTO();
        dto.getConditions().add(Restrict.eq("userId", to.getUserId()));
        List<ConstructTeam> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        } else {
            if (!StringUtils.isEmpty(to.getId()) && to.getId().equals(list.get(0).getId())) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ConstructTeamBO updateModel(ConstructTeamTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            ConstructTeam model = super.findById(to.getId());
            if (model != null) {
                if (isUserNameExist(to)) {
                    throw new SerException("该用户已存在!");
                }
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, ConstructTeamBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ConstructTeamBO> pageList(ConstructTeamDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ConstructTeam> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, ConstructTeamBO.class);
    }

}