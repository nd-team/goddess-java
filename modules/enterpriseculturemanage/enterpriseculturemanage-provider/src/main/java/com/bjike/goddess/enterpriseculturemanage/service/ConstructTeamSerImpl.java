package com.bjike.goddess.enterpriseculturemanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.ConstructTeamBO;
import com.bjike.goddess.enterpriseculturemanage.dto.ConstructTeamDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.ConstructTeam;
import com.bjike.goddess.enterpriseculturemanage.to.ConstructTeamTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ConstructTeamBO insertModel(ConstructTeamTO to) throws SerException {
        if (StringUtils.isEmpty(to.getUserName())) {
            throw new SerException("用户名不能为空!");
        }
        ConstructTeam model = BeanTransform.copyProperties(to, ConstructTeam.class);
        findUserDetail(model);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ConstructTeamBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ConstructTeamBO updateModel(ConstructTeamTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            ConstructTeam model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                findUserDetail(model);
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

    //查询员工编号，查询所属项目组，如果存在则填充对应的信息
    public void findUserDetail(ConstructTeam model) throws SerException{

        UserBO userBO = userAPI.findByUsername(model.getUserName());
        if (userBO != null) {
            model.setUserNumber(userBO.getEmployeeNumber());
//            model.setProjectGroup(userb);
        }
    }
}