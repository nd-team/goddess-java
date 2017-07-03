package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfaremanage.entity.PersonalFestival;
import com.bjike.goddess.staffwelfaremanage.entity.PersonalFestivalWish;
import com.bjike.goddess.staffwelfaremanage.to.PersonalFestivalTO;
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
 * 个人节日业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class PersonalFestivalSerImpl extends ServiceImpl<PersonalFestival, PersonalFestivalDTO> implements PersonalFestivalSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PersonalFestivalWishSer personalFestivalWishSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PersonalFestivalBO insertModel(PersonalFestivalTO to) throws SerException {
        PersonalFestival model = BeanTransform.copyProperties(to, PersonalFestival.class, true);
        UserBO userBO = userAPI.currentUser();
        if (userBO != null) {
            model.setUserName(userBO.getUsername());
            model.setUserId(userBO.getId());
        }
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, PersonalFestivalBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public PersonalFestivalBO updateModel(PersonalFestivalTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            PersonalFestival model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, PersonalFestivalBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<PersonalFestivalBO> pageList(PersonalFestivalDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<PersonalFestival> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, PersonalFestivalBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void wish(String id, String wishStatement) throws SerException {
        if (!StringUtils.isEmpty(id)) {
            PersonalFestival model = super.findById(id);
            if (model != null) {
                UserBO userBO = userAPI.currentUser();
                if (userBO != null) {
                    if (userBO.getUsername().equals(model.getUserName())) {
                        throw new SerException("亲，本人的祝福不如藏心里吧!");
                    } else {
                        PersonalFestivalWish personalFestivalWish = new PersonalFestivalWish();
                        personalFestivalWish.setFestivalId(id);
                        personalFestivalWish.setWishStatement(wishStatement);
                        personalFestivalWish.setSendUser(userBO.getUsername());
                        personalFestivalWish.setSendUserId(userBO.getId());
                        personalFestivalWish.setReceiveUser(model.getUserName());
                        personalFestivalWish.setReceiveUserId(model.getUserId());
                        personalFestivalWishSer.save(personalFestivalWish);
                    }
                } else {
                    throw new SerException("用户未登录或登录已失效!");
                }
            } else {
                throw new SerException("节日对象不能为空!");
            }
        } else {
            throw new SerException("节日Id不能为空!");
        }

    }
}