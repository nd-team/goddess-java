package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerWeightFirstFactorBO;
import com.bjike.goddess.customer.dto.CustomerWeightFirstFactorDTO;
import com.bjike.goddess.customer.entity.CustomerWeightFirstFactor;
import com.bjike.goddess.customer.entity.FirstFactorWeight;
import com.bjike.goddess.customer.to.CustomerWeightFirstFactorTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 客户权重一层因素层设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:16 ]
 * @Description: [ 客户权重一层因素层设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CustomerWeightFirstFactorSerImpl extends ServiceImpl<CustomerWeightFirstFactor, CustomerWeightFirstFactorDTO> implements CustomerWeightFirstFactorSer {
    @Autowired
    private FirstFactorWeightSer firstFactorWeightSer;

    @Override
    public Long countFirstFactor(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws SerException {
        Long count = super.count(customerWeightFirstFactorDTO);
        return count;
    }

    @Override
    public CustomerWeightFirstFactorBO getOneFirstFactor(String id) throws SerException {
        CustomerWeightFirstFactor customerWeightFirstFactor = super.findById(id);
        return BeanTransform.copyProperties(customerWeightFirstFactor, CustomerWeightFirstFactorBO.class);
    }

    @Override
    public List<CustomerWeightFirstFactorBO> listFirstFactor(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws SerException {
        List<CustomerWeightFirstFactor> customerWeightFirstFactors = super.findByCis(customerWeightFirstFactorDTO, true);
        return BeanTransform.copyProperties(customerWeightFirstFactors, CustomerWeightFirstFactorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerWeightFirstFactorBO addFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
        CustomerWeightFirstFactor customerWeightFirstFactor = BeanTransform.copyProperties(customerWeightFirstFactorTO, CustomerWeightFirstFactor.class, true);
        customerWeightFirstFactor.setCreateTime(LocalDateTime.now());
        super.save(customerWeightFirstFactor);

        //计算权重
        double[][] a = {{customerWeightFirstFactorTO.getFunPowersFunPowers(), customerWeightFirstFactorTO.getFunPowersTimeliness(), customerWeightFirstFactorTO.getFunPowersCloseness(), customerWeightFirstFactorTO.getFunPowersDiffLevel()},
                {customerWeightFirstFactorTO.getTimelinessFunPowers(), customerWeightFirstFactorTO.getTimelinessTimeliness(), customerWeightFirstFactorTO.getTimelinessCloseness(), customerWeightFirstFactorTO.getTimelinessDiffLevel()},
                {customerWeightFirstFactorTO.getClosenessFunPowers(), customerWeightFirstFactorTO.getClosenessTimeliness(), customerWeightFirstFactorTO.getClosenessCloseness(), customerWeightFirstFactorTO.getClosenessDiffLevel()},
                {customerWeightFirstFactorTO.getDiffLevelFunPowers(), customerWeightFirstFactorTO.getDiffLevelTimeliness(), customerWeightFirstFactorTO.getDiffLevelCloseness(), customerWeightFirstFactorTO.getDiffLevelDiffLevel()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<FirstFactorWeight> firstFactorWeights = new ArrayList<>();
        String[] name_str = new String[]{"职权", "时效性", "亲密度", "难易度"};
        for (int i = 0; i < 4; i++) {
            FirstFactorWeight firstFactorWeight = new FirstFactorWeight();
            firstFactorWeight.setFirstFactorName(name_str[i]);
            firstFactorWeight.setFirstFactorWeight(weight[i]);
            firstFactorWeight.setCreateTime(LocalDateTime.now());
            firstFactorWeights.add(firstFactorWeight);
        }
        firstFactorWeightSer.save(firstFactorWeights);
//        System.out.println("权重:" + Arrays.toString(weight));

        return BeanTransform.copyProperties(customerWeightFirstFactor, CustomerWeightFirstFactorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerWeightFirstFactorBO editFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
        CustomerWeightFirstFactor customerWeightFirstFactor = super.findById(customerWeightFirstFactorTO.getId());
        LocalDateTime dateTime =customerWeightFirstFactor.getCreateTime();
        customerWeightFirstFactor = BeanTransform.copyProperties(customerWeightFirstFactorTO,CustomerWeightFirstFactor.class,true);
        customerWeightFirstFactor.setCreateTime(dateTime);
        customerWeightFirstFactor.setModifyTime(LocalDateTime.now());
        super.update(customerWeightFirstFactor);

        //权重数据全部删除
        List<FirstFactorWeight> firstFactorWeights = firstFactorWeightSer.findAll();
        firstFactorWeightSer.remove(firstFactorWeights);
        //重新设置权重
        double[][] a = {{customerWeightFirstFactorTO.getFunPowersFunPowers(), customerWeightFirstFactorTO.getFunPowersTimeliness(), customerWeightFirstFactorTO.getFunPowersCloseness(), customerWeightFirstFactorTO.getFunPowersDiffLevel()},
                {customerWeightFirstFactorTO.getTimelinessFunPowers(), customerWeightFirstFactorTO.getTimelinessTimeliness(), customerWeightFirstFactorTO.getTimelinessCloseness(), customerWeightFirstFactorTO.getTimelinessDiffLevel()},
                {customerWeightFirstFactorTO.getClosenessFunPowers(), customerWeightFirstFactorTO.getClosenessTimeliness(), customerWeightFirstFactorTO.getClosenessCloseness(), customerWeightFirstFactorTO.getClosenessDiffLevel()},
                {customerWeightFirstFactorTO.getDiffLevelFunPowers(), customerWeightFirstFactorTO.getDiffLevelTimeliness(), customerWeightFirstFactorTO.getDiffLevelCloseness(), customerWeightFirstFactorTO.getDiffLevelDiffLevel()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<FirstFactorWeight> firstFactorWeightList = new ArrayList<>();
        String[] name_str = new String[]{"职权", "时效性", "亲密度", "难易度"};
        for (int i = 0; i < 4; i++) {
            FirstFactorWeight firstFactorWeight = new FirstFactorWeight();
            firstFactorWeight.setFirstFactorName(name_str[i]);
            firstFactorWeight.setFirstFactorWeight(weight[i]);
            firstFactorWeight.setCreateTime(LocalDateTime.now());
            firstFactorWeights.add(firstFactorWeight);
        }
        firstFactorWeightSer.save(firstFactorWeights);


        return BeanTransform.copyProperties(customerWeightFirstFactor,CustomerWeightFirstFactorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteFirstFactor(String id) throws SerException {
        super.remove(id);

        List<FirstFactorWeight> firstFactorWeights = firstFactorWeightSer.findAll();
        firstFactorWeightSer.remove(firstFactorWeights);
    }

}