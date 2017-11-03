package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.DifficultyFoactorSetBO;
import com.bjike.goddess.customer.dto.DifficultyFoactorSetDTO;
import com.bjike.goddess.customer.entity.DifficultyFoactorSet;
import com.bjike.goddess.customer.entity.DifficultyFoactorWeight;
import com.bjike.goddess.customer.to.DifficultyFoactorSetTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 难易度因素层设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:18 ]
 * @Description: [ 难易度因素层设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class DifficultyFoactorSetSerImpl extends ServiceImpl<DifficultyFoactorSet, DifficultyFoactorSetDTO> implements DifficultyFoactorSetSer {
    @Autowired
    private DifficultyFoactorWeightSer difficultyFoactorWeightSer;

    @Override
    public Long countDifficulty(DifficultyFoactorSetDTO difficultyFoactorSetDTO) throws SerException {
        Long count = super.count(difficultyFoactorSetDTO);
        return count;
    }

    @Override
    public DifficultyFoactorSetBO getOneDifficulty(String id) throws SerException {
        DifficultyFoactorSet difficultyFoactorSet = super.findById(id);
        return BeanTransform.copyProperties(difficultyFoactorSet, DifficultyFoactorSetBO.class);
    }

    @Override
    public List<DifficultyFoactorSetBO> listDifficulty(DifficultyFoactorSetDTO difficultyFoactorSetDTO) throws SerException {
        List<DifficultyFoactorSet> difficultyFoactorSets = super.findByCis(difficultyFoactorSetDTO);
        return BeanTransform.copyProperties(difficultyFoactorSets, DifficultyFoactorSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public DifficultyFoactorSetBO addDifficulty(DifficultyFoactorSetTO difficultyFoactorSetTO) throws SerException {
        DifficultyFoactorSet difficultyFoactorSet = BeanTransform.copyProperties(difficultyFoactorSetTO, DifficultyFoactorSet.class, true);
        difficultyFoactorSet.setCreateTime(LocalDateTime.now());
        super.save(difficultyFoactorSet);

        //计算权重
        double[][] a = {{difficultyFoactorSetTO.getDifficDiffic(), difficultyFoactorSetTO.getDifficGeneral(), difficultyFoactorSetTO.getDifficEasy()},
                {difficultyFoactorSetTO.getGeneralDiffic(), difficultyFoactorSetTO.getGeneralGeneral(), difficultyFoactorSetTO.getGeneralEasy()},
                {difficultyFoactorSetTO.getEasyDiffic(), difficultyFoactorSetTO.getEasyGeneral(), difficultyFoactorSetTO.getEasyEasy()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<DifficultyFoactorWeight> difficultyFoactorWeights = new ArrayList<>();
        String[] name_str = new String[]{"困难", "一般", "容易"};
        for (int i = 0; i < 3; i++) {
            DifficultyFoactorWeight difficultyFoactorWeight = new DifficultyFoactorWeight();
            difficultyFoactorWeight.setDifficName(name_str[i]);
            difficultyFoactorWeight.setDifficWeight(weight[i]);
            difficultyFoactorWeight.setCreateTime(LocalDateTime.now());
            difficultyFoactorWeights.add(difficultyFoactorWeight);
        }
        difficultyFoactorWeightSer.save(difficultyFoactorWeights);

        return BeanTransform.copyProperties(difficultyFoactorSet, DifficultyFoactorSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DifficultyFoactorSetBO editDifficulty(DifficultyFoactorSetTO difficultyFoactorSetTO) throws SerException {
        DifficultyFoactorSet difficultyFoactorSet = super.findById(difficultyFoactorSetTO.getId());
        LocalDateTime dateTime = difficultyFoactorSet.getCreateTime();
        difficultyFoactorSet = BeanTransform.copyProperties(difficultyFoactorSetTO, DifficultyFoactorSet.class);
        difficultyFoactorSet.setCreateTime(dateTime);
        difficultyFoactorSet.setModifyTime(LocalDateTime.now());
        super.update(difficultyFoactorSet);

        //删除权重
        List<DifficultyFoactorWeight> difficultyFoactorWeightList = difficultyFoactorWeightSer.findAll();
        difficultyFoactorWeightSer.remove(difficultyFoactorWeightList);

        //重新计算权重
        double[][] a = {{difficultyFoactorSetTO.getDifficDiffic(), difficultyFoactorSetTO.getDifficGeneral(), difficultyFoactorSetTO.getDifficEasy()},
                {difficultyFoactorSetTO.getGeneralDiffic(), difficultyFoactorSetTO.getGeneralGeneral(), difficultyFoactorSetTO.getGeneralEasy()},
                {difficultyFoactorSetTO.getEasyDiffic(), difficultyFoactorSetTO.getEasyGeneral(), difficultyFoactorSetTO.getEasyEasy()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<DifficultyFoactorWeight> difficultyFoactorWeights = new ArrayList<>();
        String[] name_str = new String[]{"困难", "一般", "容易"};
        for (int i = 0; i < 3; i++) {
            DifficultyFoactorWeight difficultyFoactorWeight = new DifficultyFoactorWeight();
            difficultyFoactorWeight.setDifficName(name_str[i]);
            difficultyFoactorWeight.setDifficWeight(weight[i]);
            difficultyFoactorWeight.setCreateTime(LocalDateTime.now());
            difficultyFoactorWeights.add(difficultyFoactorWeight);
        }
        difficultyFoactorWeightSer.save(difficultyFoactorWeights);

        return BeanTransform.copyProperties(difficultyFoactorSet, DifficultyFoactorSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteDifficulty(String id) throws SerException {
        super.remove(id);

        //删除权重
        List<DifficultyFoactorWeight> difficultyFoactorWeightList = difficultyFoactorWeightSer.findAll();
        difficultyFoactorWeightSer.remove(difficultyFoactorWeightList);
    }
}