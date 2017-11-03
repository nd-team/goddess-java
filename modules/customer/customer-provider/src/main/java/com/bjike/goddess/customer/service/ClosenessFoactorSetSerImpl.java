package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.ClosenessFoactorSetBO;
import com.bjike.goddess.customer.dto.ClosenessFoactorSetDTO;
import com.bjike.goddess.customer.entity.ClosenessFoactorSet;
import com.bjike.goddess.customer.entity.ClosenessFoactorWeight;
import com.bjike.goddess.customer.entity.TimelinessFactorWeight;
import com.bjike.goddess.customer.to.ClosenessFoactorSetTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 亲密度因素层设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:01 ]
 * @Description: [ 亲密度因素层设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class ClosenessFoactorSetSerImpl extends ServiceImpl<ClosenessFoactorSet, ClosenessFoactorSetDTO> implements ClosenessFoactorSetSer {
    @Autowired
    private ClosenessFoactorWeightSer closenessFoactorWeightSer;
    @Override
    public Long countCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
        Long count = super.count(closenessFoactorSetDTO);
        return count;
    }

    @Override
    public ClosenessFoactorSetBO getOneCloseness(String id) throws SerException {
        ClosenessFoactorSet closenessFoactorSet = super.findById(id);
        return BeanTransform.copyProperties(closenessFoactorSet,ClosenessFoactorSet.class);
    }

    @Override
    public List<ClosenessFoactorSetBO> listCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
       List<ClosenessFoactorSet> closenessFoactorSetList = super.findByCis(closenessFoactorSetDTO);
        return BeanTransform.copyProperties(closenessFoactorSetList,ClosenessFoactorSetBO.class);
    }

    @Override
    public ClosenessFoactorSetBO addCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        ClosenessFoactorSet closenessFoactorSet = BeanTransform.copyProperties(closenessFoactorSetTO,ClosenessFoactorSet.class,true);
        closenessFoactorSet.setCreateTime(LocalDateTime.now());
        super.save(closenessFoactorSet);

        //计算权重
        double[][] a = {{closenessFoactorSetTO.getGeneralGeneral(), closenessFoactorSetTO.getGeneralClose(), closenessFoactorSetTO.getGeneralVeryClose()},
                {closenessFoactorSetTO.getCloseGeneral(), closenessFoactorSetTO.getCloseClose(), closenessFoactorSetTO.getCloseVeryClose()},
                {closenessFoactorSetTO.getVeryCloseGeneral(), closenessFoactorSetTO.getVeryCloseClose(), closenessFoactorSetTO.getVeryCloseVeryClose()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<ClosenessFoactorWeight> closenessFoactorWeights = new ArrayList<>();
        String[] name_str = new String[]{"一般", "亲密", "非常亲密"};
        for (int i = 0; i < 4; i++) {
            ClosenessFoactorWeight closenessFoactorWeight = new ClosenessFoactorWeight();
            closenessFoactorWeight.setClosenessName(name_str[i]);
            closenessFoactorWeight.setClosenessWeight(weight[i]);
            closenessFoactorWeight.setCreateTime(LocalDateTime.now());
            closenessFoactorWeights.add(closenessFoactorWeight);
        }
        closenessFoactorWeightSer.save(closenessFoactorWeights);

        return BeanTransform.copyProperties(closenessFoactorSet,ClosenessFoactorSetBO.class);
    }

    @Override
    public ClosenessFoactorSetBO editCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        ClosenessFoactorSet closenessFoactorSet = super.findById(closenessFoactorSetTO.getId());
        LocalDateTime dateTime = closenessFoactorSet.getCreateTime();
        closenessFoactorSet = BeanTransform.copyProperties(closenessFoactorSetTO,ClosenessFoactorSet.class,true);
        closenessFoactorSet.setCreateTime(dateTime);
        closenessFoactorSet.setModifyTime(LocalDateTime.now());
        super.update(closenessFoactorSet);

        //删除所有的权重
        List<ClosenessFoactorWeight> closenessFoactorWeights = closenessFoactorWeightSer.findAll();
        closenessFoactorWeightSer.remove(closenessFoactorWeights);


        //计算权重
        double[][] a = {{closenessFoactorSetTO.getGeneralGeneral(), closenessFoactorSetTO.getGeneralClose(), closenessFoactorSetTO.getGeneralVeryClose()},
                {closenessFoactorSetTO.getCloseGeneral(), closenessFoactorSetTO.getCloseClose(), closenessFoactorSetTO.getCloseVeryClose()},
                {closenessFoactorSetTO.getVeryCloseGeneral(), closenessFoactorSetTO.getVeryCloseClose(), closenessFoactorSetTO.getVeryCloseVeryClose()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<ClosenessFoactorWeight> closenessFoactorWeightList = new ArrayList<>();
        String[] name_str = new String[]{"一般", "亲密", "非常亲密"};
        for (int i = 0; i < 4; i++) {
            ClosenessFoactorWeight closenessFoactorWeight = new ClosenessFoactorWeight();
            closenessFoactorWeight.setClosenessName(name_str[i]);
            closenessFoactorWeight.setClosenessWeight(weight[i]);
            closenessFoactorWeight.setCreateTime(LocalDateTime.now());
            closenessFoactorWeightList.add(closenessFoactorWeight);
        }
        closenessFoactorWeightSer.save(closenessFoactorWeightList);

        return BeanTransform.copyProperties(closenessFoactorSet,ClosenessFoactorSetBO.class);
    }

    @Override
    public void deleteCloseness(String id) throws SerException {
        super.remove(id);

        List<ClosenessFoactorWeight> closenessFoactorWeights = closenessFoactorWeightSer.findAll();
        closenessFoactorWeightSer.remove(closenessFoactorWeights);
    }
}