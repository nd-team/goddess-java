package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfaremanage.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfaremanage.entity.ThankStatement;
import com.bjike.goddess.staffwelfaremanage.to.ThankStatementTO;
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
 * 感谢语业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class ThankStatementSerImpl extends ServiceImpl<ThankStatement, ThankStatementDTO> implements ThankStatementSer {

    @Autowired
    private UserAPI userAPI;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public ThankStatementBO insertModel(ThankStatementTO to) throws SerException {
        ThankStatement model = BeanTransform.copyProperties(to, ThankStatement.class, true);
        model.setCreateUser(getCurrentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ThankStatementBO updateModel(ThankStatementTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            ThankStatement model = super.findById(to.getId());
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
        return BeanTransform.copyProperties(to, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ThankStatementBO> pageList(ThankStatementDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        //查询当前用户及共享的感谢语
        dto.getConditions().add(Restrict.or("createUser",getCurrentUser().getUsername()));
        dto.getConditions().add(Restrict.or("share",1));
        List<ThankStatement> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ThankStatementBO> findAllStatement() throws SerException {
        ThankStatementDTO dto = new ThankStatementDTO();
        dto.getSorts().add("createTime=desc");
        //查询当前用户及共享的非冻结感谢语
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
        dto.getConditions().add(Restrict.or("createUser",getCurrentUser().getUsername()));
        dto.getConditions().add(Restrict.or("share",1));
        List<ThankStatement> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ThankStatementBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {

        if (!StringUtils.isEmpty(id)) {
            ThankStatement model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        if (!StringUtils.isEmpty(id)) {
            ThankStatement model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    //获取当前用户
    public UserBO getCurrentUser() throws SerException{
        UserBO userBO = userAPI.currentUser();
        if (userBO != null){
            return userBO;
        }else{
            throw new SerException("用户未登录或已超时!");
        }
    }
}