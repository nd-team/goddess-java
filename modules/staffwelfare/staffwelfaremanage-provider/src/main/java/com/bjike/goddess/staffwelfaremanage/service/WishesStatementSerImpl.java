package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfaremanage.dto.WishesStatementDTO;
import com.bjike.goddess.staffwelfaremanage.entity.WishesStatement;
import com.bjike.goddess.staffwelfaremanage.to.WishesStatementTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 祝福语业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 03:21 ]
 * @Description: [ 祝福语业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class WishesStatementSerImpl extends ServiceImpl<WishesStatement, WishesStatementDTO> implements WishesStatementSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public WishesStatementBO insertModel(WishesStatementTO to) throws SerException {
        WishesStatement model = BeanTransform.copyProperties(to, WishesStatement.class, true);
        model.setCreateUser(getCurrentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, WishesStatementBO.class);
    }

    @Override
    public WishesStatementBO updateModel(WishesStatementTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            WishesStatement model = super.findById(to.getId());
            if (model != null) {
                model.setUpdateUser(getCurrentUser().getUsername());
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, WishesStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WishesStatementBO> pageList(WishesStatementDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        //查询当前用户的祝福语
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<WishesStatement> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, WishesStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<WishesStatementBO> findAllStatement() throws SerException {
        WishesStatementDTO dto = new WishesStatementDTO();
        dto.getSorts().add("createTime=desc");
        //查询当前用户的非冻结感谢语
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<WishesStatement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, WishesStatementBO.class);
    }

    //获取当前用户
    public UserBO getCurrentUser() throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (userBO != null) {
            return userBO;
        } else {
            throw new SerException("用户未登录或已超时!");
        }
    }
}