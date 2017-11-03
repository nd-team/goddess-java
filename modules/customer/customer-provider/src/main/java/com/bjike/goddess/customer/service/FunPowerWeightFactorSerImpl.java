package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.FunPowerWeightFactorBO;
import com.bjike.goddess.customer.dto.FunPowerWeightFactorDTO;
import com.bjike.goddess.customer.entity.FirstFactorWeight;
import com.bjike.goddess.customer.entity.FunPowerWeight;
import com.bjike.goddess.customer.entity.FunPowerWeightFactor;
import com.bjike.goddess.customer.to.FunPowerWeightFactorTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 职权因素层设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:37 ]
 * @Description: [ 职权因素层设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class FunPowerWeightFactorSerImpl extends ServiceImpl<FunPowerWeightFactor, FunPowerWeightFactorDTO> implements FunPowerWeightFactorSer {
    @Autowired
    private FunPowerWeightSer funPowerWeightSer;
    @Override
    public Long countFunPower(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws SerException {
        Long count = super.count(funPowerWeightFactorDTO);
        return count;
    }

    @Override
    public FunPowerWeightFactorBO getOneFunPower(String id) throws SerException {
        FunPowerWeightFactor funPowerWeightFactor = super.findById(id);
        return BeanTransform.copyProperties(funPowerWeightFactor,FunPowerWeightFactorBO.class);
    }

    @Override
    public List<FunPowerWeightFactorBO> listFunPower(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws SerException {
        List<FunPowerWeightFactor> funPowerWeightFactorList = super.findByCis(funPowerWeightFactorDTO);
        return BeanTransform.copyProperties(funPowerWeightFactorList,FunPowerWeightFactorBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public FunPowerWeightFactorBO addFunPower(FunPowerWeightFactorTO funPowerWeightFactorTO) throws SerException {
        FunPowerWeightFactor funPowerWeightFactor = BeanTransform.copyProperties(funPowerWeightFactorTO,FunPowerWeightFactor.class,true);
        funPowerWeightFactor.setCreateTime(LocalDateTime.now());
        super.save(funPowerWeightFactor);
        //计算权重
        double[][] a = {{funPowerWeightFactorTO.getLowLow(), funPowerWeightFactorTO.getLowMedium(), funPowerWeightFactorTO.getLowHeight()},
                        {funPowerWeightFactorTO.getMediumLow(), funPowerWeightFactorTO.getMediumMedium(), funPowerWeightFactorTO.getMediumHeight()},
                        {funPowerWeightFactorTO.getHeightLow(), funPowerWeightFactorTO.getHeightMedium(), funPowerWeightFactorTO.getHeightHeight()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<FunPowerWeight> funPowerWeights = new ArrayList<>();
        String[] name_str = new String[]{"低", "中", "高"};
        for (int i = 0; i < 3; i++) {
            FunPowerWeight funPowerWeight = new FunPowerWeight();
            funPowerWeight.setFunPowerTypeName(name_str[i]);
            funPowerWeight.setFunPowerTypeWeight(weight[i]);
            funPowerWeight.setCreateTime(LocalDateTime.now());
            funPowerWeights.add(funPowerWeight);
        }
        funPowerWeightSer.save(funPowerWeights);
        return BeanTransform.copyProperties(funPowerWeightFactor,FunPowerWeightFactorBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public FunPowerWeightFactorBO editFunPower(FunPowerWeightFactorTO funPowerWeightFactorTO) throws SerException {
        FunPowerWeightFactor funPowerWeightFactor =  super.findById(funPowerWeightFactorTO.getId());
        LocalDateTime dateTime = funPowerWeightFactor.getCreateTime();
        funPowerWeightFactor = BeanTransform.copyProperties(funPowerWeightFactorTO,FunPowerWeightFactor.class,true);
        funPowerWeightFactor.setCreateTime(dateTime);
        funPowerWeightFactor.setModifyTime(LocalDateTime.now());
        super.update(funPowerWeightFactor);
        //权重数据全部删除
        List<FunPowerWeight> funPowerWeights = funPowerWeightSer.findAll();
        funPowerWeightSer.remove(funPowerWeights);
        //重新设置权重
        double[][] a = {{funPowerWeightFactorTO.getLowLow(), funPowerWeightFactorTO.getLowMedium(), funPowerWeightFactorTO.getLowHeight()},
                {funPowerWeightFactorTO.getMediumLow(), funPowerWeightFactorTO.getMediumMedium(), funPowerWeightFactorTO.getMediumHeight()},
                {funPowerWeightFactorTO.getHeightLow(), funPowerWeightFactorTO.getHeightMedium(), funPowerWeightFactorTO.getHeightHeight()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<FunPowerWeight> funPowerWeightList = new ArrayList<>();
        String[] name_str = new String[]{"低", "中", "高"};
        for (int i = 0; i < 3; i++) {
            FunPowerWeight funPowerWeight = new FunPowerWeight();
            funPowerWeight.setFunPowerTypeName(name_str[i]);
            funPowerWeight.setFunPowerTypeWeight(weight[i]);
            funPowerWeight.setCreateTime(LocalDateTime.now());
            funPowerWeightList.add(funPowerWeight);
        }
        funPowerWeightSer.save(funPowerWeightList);


        return BeanTransform.copyProperties(funPowerWeightFactor,FunPowerWeightFactor.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteFunPower(String id) throws SerException {
        super.remove(id);

        List<FunPowerWeight> funPowerWeights = funPowerWeightSer.findAll();
        funPowerWeightSer.remove(funPowerWeights);
    }
}