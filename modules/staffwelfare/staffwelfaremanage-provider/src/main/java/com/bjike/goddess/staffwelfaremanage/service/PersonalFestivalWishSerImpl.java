package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalBO;
import com.bjike.goddess.staffwelfaremanage.bo.PersonalFestivalWishBO;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalWishDTO;
import com.bjike.goddess.staffwelfaremanage.entity.PersonalFestival;
import com.bjike.goddess.staffwelfaremanage.entity.PersonalFestivalWish;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 节日祝福语业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 03:08 ]
 * @Description: [ 节日祝福语业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class PersonalFestivalWishSerImpl extends ServiceImpl<PersonalFestivalWish, PersonalFestivalWishDTO> implements PersonalFestivalWishSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PersonalFestivalSer personalFestivalSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<PersonalFestivalWishBO> pageList(PersonalFestivalWishDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        UserBO userBO = userAPI.currentUser();
        if(userBO == null){
            throw new SerException("用户未登录或登录已失效!");
        }
        //查询当前用户收到的祝福语
        dto.getConditions().add(Restrict.eq("receiveUserId",userBO.getId()));
        List<PersonalFestivalWish> list = super.findByPage(dto);
        List<PersonalFestivalWishBO> boList = new ArrayList<PersonalFestivalWishBO>();
        if (list != null && !list.isEmpty()) {
            boList = BeanTransform.copyProperties(list, PersonalFestivalWishBO.class);
            for (PersonalFestivalWishBO bo : boList) {
                PersonalFestival personalFestival = personalFestivalSer.findById(bo.getFestivalId());
                if(personalFestival!=null){
                    PersonalFestivalBO personalFestivalBO = BeanTransform.copyProperties(personalFestival,PersonalFestivalBO.class);
                    bo.setFestivalName(personalFestivalBO.getFestivalName());
                    bo.setFestivalTime(personalFestivalBO.getFestivalDate());
                    bo.setThankStatement(personalFestivalBO.getThankStatement());
                }
            }

        }
        return boList;
    }
}