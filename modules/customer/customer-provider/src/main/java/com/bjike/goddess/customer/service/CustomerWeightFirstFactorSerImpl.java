package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerWeightFirstFactorBO;
import com.bjike.goddess.customer.dto.CustomerWeightFirstFactorDTO;
import com.bjike.goddess.customer.entity.CustomerWeightFirstFactor;
import com.bjike.goddess.customer.entity.FirstFactorWeight;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.CustomerWeightFirstFactorTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;


    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity("3");
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity("1");
                break;
            case ADD:
                flag = guideAddIdentity("3");
                break;
            case EDIT:
                flag = guideAddIdentity("3");
                break;
            case DELETE:
                flag = guideAddIdentity("3");
                break;
            case UPLOAD:
                flag = guideAddIdentity("3");
                break;
            case SEEFILE:
                flag = guideAddIdentity("3");
                break;
            case DOWNLOAD:
                flag = guideAddIdentity("3");
                break;
            case CONGEL:
                flag = guideAddIdentity("3");
                break;
            case THAW:
                flag = guideAddIdentity("3");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
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
        checkSeeIdentity("1");
        List<CustomerWeightFirstFactor> customerWeightFirstFactors = super.findByCis(customerWeightFirstFactorDTO, true);
        return BeanTransform.copyProperties(customerWeightFirstFactors, CustomerWeightFirstFactorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CustomerWeightFirstFactorBO addFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
       checkAddIdentity("3");
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
        checkAddIdentity("3");
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
        checkAddIdentity("3");
        super.remove(id);

        List<FirstFactorWeight> firstFactorWeights = firstFactorWeightSer.findAll();
        firstFactorWeightSer.remove(firstFactorWeights);
    }

}