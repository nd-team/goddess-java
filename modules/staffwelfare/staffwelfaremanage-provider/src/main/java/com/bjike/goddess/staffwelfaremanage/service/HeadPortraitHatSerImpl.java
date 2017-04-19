package com.bjike.goddess.staffwelfaremanage.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfaremanage.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfaremanage.entity.HeadPortraitHat;
import com.bjike.goddess.staffwelfaremanage.to.HeadPortraitHatTO;
import com.bjike.goddess.storage.api.FileAPI;
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
 * 头像帽业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class HeadPortraitHatSerImpl extends ServiceImpl<HeadPortraitHat, HeadPortraitHatDTO> implements HeadPortraitHatSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FileAPI fileAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)

    public HeadPortraitHatBO insertModel(HeadPortraitHatTO to) throws SerException {
        Object o = RpcContext.getContext().getAttachment("storageToken");

        HeadPortraitHat model = BeanTransform.copyProperties(to, HeadPortraitHat.class, true);
        model.setCreateUser(getCurrentUser().getUsername());
        String path = "/headPortraitHat";

        if(null!=o){
            RpcContext.getContext().setAttachment("storageToken",o.toString());
        }
        fileAPI.upload(to.getMap(), path);
        model.setUrl(path);
      //  super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, HeadPortraitHatBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public HeadPortraitHatBO updateModel(HeadPortraitHatTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            HeadPortraitHat model = super.findById(to.getId());
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
        return BeanTransform.copyProperties(to, HeadPortraitHatBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<HeadPortraitHatBO> pageList(HeadPortraitHatDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        //
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<HeadPortraitHat> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, HeadPortraitHatBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<HeadPortraitHatBO> findHeadPortraitHats() throws SerException {
        HeadPortraitHatDTO dto = new HeadPortraitHatDTO();
        dto.getSorts().add("createTime=desc");
        //
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<HeadPortraitHat> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HeadPortraitHatBO.class);
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