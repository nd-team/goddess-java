package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.RecommendTypeBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendType;
import com.bjike.goddess.interiorrecommend.to.RecommendTypeTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 推荐类型设定业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class RecommendTypeSerImpl extends ServiceImpl<RecommendType, RecommendTypeDTO> implements RecommendTypeSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public RecommendTypeBO insertModel(RecommendTypeTO to) throws SerException {
        RecommendType model = BeanTransform.copyProperties(to, RecommendType.class, true);
        model.setCreateUser(userAPI.currentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, RecommendTypeBO.class);
    }

    @Override
    public RecommendTypeBO updateModel(RecommendTypeTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            RecommendType model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setUpdateUser(userAPI.currentUser().getUsername());
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, RecommendTypeBO.class);
    }

    @Override
    public List<RecommendTypeBO> pageList(RecommendTypeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<RecommendType> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, RecommendTypeBO.class);
    }
}