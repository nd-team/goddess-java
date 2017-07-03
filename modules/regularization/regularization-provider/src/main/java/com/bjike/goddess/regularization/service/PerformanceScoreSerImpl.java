package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.PerformanceScoreBO;
import com.bjike.goddess.regularization.dto.PerformanceScoreDTO;
import com.bjike.goddess.regularization.entity.ManagementScore;
import com.bjike.goddess.regularization.entity.PerformanceScore;
import com.bjike.goddess.regularization.to.PerformanceScoreTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作表现评分业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class PerformanceScoreSerImpl extends ServiceImpl<PerformanceScore, PerformanceScoreDTO> implements PerformanceScoreSer {

    /**
     * 分页查询工作表现评分
     *
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    @Override
    public List<PerformanceScoreBO> list(PerformanceScoreDTO dto) throws SerException {
        List<PerformanceScore> list = super.findByPage(dto);
        List<PerformanceScoreBO> listBO = BeanTransform.copyProperties(list, PerformanceScoreBO.class);
        return listBO;
    }

    /**
     * 保存工作表现评分
     *
     * @param to 工作表现评分to
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public PerformanceScoreBO save(PerformanceScoreTO to) throws SerException {
        checkPostHierarchyUnique(to);
        PerformanceScore entity = BeanTransform.copyProperties(to, PerformanceScore.class, true);
        entity = super.save(entity);
        PerformanceScoreBO bo = BeanTransform.copyProperties(entity, PerformanceScoreBO.class);
        return bo;
    }

    /**
     * 校验数据库中是否存在相同的岗位层级
     *
     * @param to
     * @throws SerException
     */
    private void checkPostHierarchyUnique(PerformanceScoreTO to) throws SerException {
        String postHierarchy = to.getPostHierarchy();
        PerformanceScoreDTO dto = new PerformanceScoreDTO();
        dto.getConditions().add(Restrict.eq("postHierarchy", postHierarchy));
        List<PerformanceScore> list = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(list)) {
            throw new SerException("岗位层级必须唯一,无法进行该操作,请直接在原有基础上编辑.");
        }
    }

    /**
     * 更新工作表现评分
     *
     * @param to 工作表现评分to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(PerformanceScoreTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            PerformanceScore model = super.findById(to.getId());
            if (model != null) {
                updatePerformanceScore(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新工作表现评分
     *
     * @param to 工作表现评分to
     * @param model 工作表现评分
     * @throws SerException
     */
    private void updatePerformanceScore(PerformanceScoreTO to, PerformanceScore model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}