package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.entity.ManagementScore;
import com.bjike.goddess.regularization.to.ManagementScoreTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理层评分业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class ManagementScoreSerImpl extends ServiceImpl<ManagementScore, ManagementScoreDTO> implements ManagementScoreSer {

    /**
     * 分页查询管理层评分
     *
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> list(ManagementScoreDTO dto) throws SerException {
        List<ManagementScore> list = super.findByPage(dto);
        List<ManagementScoreBO> listBO = BeanTransform.copyProperties(list, ManagementScoreBO.class);
        return listBO;
    }

    /**
     * 保存管理层评分
     *
     * @param to 管理层评分to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public ManagementScoreBO save(ManagementScoreTO to) throws SerException {
        ManagementScore entity = BeanTransform.copyProperties(to, ManagementScore.class, true);
        entity = super.save(entity);
        ManagementScoreBO bo = BeanTransform.copyProperties(entity, ManagementScoreBO.class);
        return bo;
    }

    /**
     * 更新管理层评分
     *
     * @param to 管理层评分to
     * @throws SerException
     */
    @Override
    public void update(ManagementScoreTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ManagementScore model = super.findById(to.getId());
            if (model != null) {
                updateManagementScore(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新管理层评分
     *
     * @param to 管理层评分to
     * @param model 管理层评分
     * @throws SerException
     */
    private void updateManagementScore(ManagementScoreTO to, ManagementScore model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除管理层评分
     *
     * @param id 管理层评分唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

}