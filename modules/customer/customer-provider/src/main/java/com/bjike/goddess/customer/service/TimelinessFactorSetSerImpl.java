package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.TimelinessFactorSetBO;
import com.bjike.goddess.customer.dto.TimelinessFactorSetDTO;
import com.bjike.goddess.customer.entity.FirstFactorWeight;
import com.bjike.goddess.customer.entity.TimelinessFactorSet;
import com.bjike.goddess.customer.entity.TimelinessFactorWeight;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.TimelinessFactorSetTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 时效性因素层设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:49 ]
 * @Description: [ 时效性因素层设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class TimelinessFactorSetSerImpl extends ServiceImpl<TimelinessFactorSet, TimelinessFactorSetDTO> implements TimelinessFactorSetSer {
    @Autowired
    private TimelinessFactorWeightSer timelinessFactorWeightSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FirstFactorWeightSer firstFactorWeightSer;


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
    public Long countTimeliness(TimelinessFactorSetDTO timelinessFactorSetDTO) throws SerException {
        Long count = super.count(timelinessFactorSetDTO);
        return count;
    }

    @Override
    public TimelinessFactorSetBO getOneTimeliness(String id) throws SerException {
        TimelinessFactorSet timelinessFactorSet = super.findById(id);
        return BeanTransform.copyProperties(timelinessFactorSet, TimelinessFactorSetBO.class);
    }

    @Override
    public List<TimelinessFactorSetBO> listTimeliness(TimelinessFactorSetDTO timelinessFactorSetDTO) throws SerException {
       checkSeeIdentity("1");
        List<TimelinessFactorSet> timelinessFactorSets = super.findByCis(timelinessFactorSetDTO);
        return BeanTransform.copyProperties(timelinessFactorSets, TimelinessFactorSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TimelinessFactorSetBO addTimeliness(TimelinessFactorSetTO timelinessFactorSetTO) throws SerException {

        checkAddIdentity("3");
        List<FirstFactorWeight> firstFactorWeightList = firstFactorWeightSer.findAll();
        if(firstFactorWeightList==null){
            throw new SerException("请先设置权重一层因素层");
        }
        TimelinessFactorSet timelinessFactorSet = BeanTransform.copyProperties(timelinessFactorSetTO, TimelinessFactorSet.class, true);
        timelinessFactorSet.setCreateTime(LocalDateTime.now());
        super.save(timelinessFactorSet);

        //计算权重
        double[][] a = {{timelinessFactorSetTO.getUrgentUrgent(), timelinessFactorSetTO.getUrgentUrgency(), timelinessFactorSetTO.getUrgentGeneral(), timelinessFactorSetTO.getUrgentNoUrgency()},
                {timelinessFactorSetTO.getUrgencyUrgent(), timelinessFactorSetTO.getUrgencyUrgency(), timelinessFactorSetTO.getUrgencyGeneral(), timelinessFactorSetTO.getUrgencyNoUrgency()},
                {timelinessFactorSetTO.getGeneralUrgent(), timelinessFactorSetTO.getGeneralUrgency(), timelinessFactorSetTO.getGeneralGeneral(), timelinessFactorSetTO.getGeneralNoUrgency()},
                {timelinessFactorSetTO.getNoUrgencyUrgent(), timelinessFactorSetTO.getNoUrgencyUrgency(), timelinessFactorSetTO.getNoUrgencyGeneral(), timelinessFactorSetTO.getNoUrgencyNoUrgency()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<TimelinessFactorWeight> timelinessFactorWeights = new ArrayList<>();
        String[] name_str = new String[]{"十分紧急", "紧急", "一般", "不紧急"};
        for (int i = 0; i < 4; i++) {
            TimelinessFactorWeight timelinessFactorWeight = new TimelinessFactorWeight();
            timelinessFactorWeight.setTimelinessName(name_str[i]);
            timelinessFactorWeight.setTimelinessWeight(weight[i]);
            timelinessFactorWeight.setCreateTime(LocalDateTime.now());
            timelinessFactorWeights.add(timelinessFactorWeight);
        }
        timelinessFactorWeightSer.save(timelinessFactorWeights);

        return BeanTransform.copyProperties(timelinessFactorSet, TimelinessFactorSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TimelinessFactorSetBO editTimeliness(TimelinessFactorSetTO timelinessFactorSetTO) throws SerException {
        checkAddIdentity("3");
        TimelinessFactorSet timelinessFactorSet = super.findById(timelinessFactorSetTO.getId());
        LocalDateTime dateTime = timelinessFactorSet.getCreateTime();
        timelinessFactorSet = BeanTransform.copyProperties(timelinessFactorSetTO, TimelinessFactorSet.class, true);
        timelinessFactorSet.setCreateTime(dateTime);
        timelinessFactorSet.setModifyTime(LocalDateTime.now());
        super.update(timelinessFactorSet);

        //删除所有的权重
        List<TimelinessFactorWeight> timelinessFactorWeights = timelinessFactorWeightSer.findAll();
        timelinessFactorWeightSer.remove(timelinessFactorWeights);

        //重新计算权重
        double[][] a = {{timelinessFactorSetTO.getUrgentUrgent(), timelinessFactorSetTO.getUrgentUrgency(), timelinessFactorSetTO.getUrgentGeneral(), timelinessFactorSetTO.getUrgentNoUrgency()},
                {timelinessFactorSetTO.getUrgencyUrgent(), timelinessFactorSetTO.getUrgencyUrgency(), timelinessFactorSetTO.getUrgencyGeneral(), timelinessFactorSetTO.getUrgencyNoUrgency()},
                {timelinessFactorSetTO.getGeneralUrgent(), timelinessFactorSetTO.getGeneralUrgency(), timelinessFactorSetTO.getGeneralGeneral(), timelinessFactorSetTO.getGeneralNoUrgency()},
                {timelinessFactorSetTO.getNoUrgencyUrgent(), timelinessFactorSetTO.getNoUrgencyUrgency(), timelinessFactorSetTO.getNoUrgencyGeneral(), timelinessFactorSetTO.getNoUrgencyNoUrgency()}};
        int N = a[0].length;
        double[] weight = new double[N];
        AHPComputeWeight instance = AHPComputeWeight.getInstance();
        instance.weight(a, weight, N);
        //将权重插入权重表中
        List<TimelinessFactorWeight> timelinessFactorWeightList = new ArrayList<>();
        String[] name_str = new String[]{"十分紧急", "紧急", "一般", "不紧急"};
        for (int i = 0; i < 4; i++) {
            TimelinessFactorWeight timelinessFactorWeight = new TimelinessFactorWeight();
            timelinessFactorWeight.setTimelinessName(name_str[i]);
            timelinessFactorWeight.setTimelinessWeight(weight[i]);
            timelinessFactorWeight.setCreateTime(LocalDateTime.now());
            timelinessFactorWeightList.add(timelinessFactorWeight);
        }
        timelinessFactorWeightSer.save(timelinessFactorWeightList);

        return BeanTransform.copyProperties(timelinessFactorSet, TimelinessFactorSetBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTimeliness(String id) throws SerException {
        checkAddIdentity("3");
        super.remove(id);

        List<TimelinessFactorWeight> timelinessFactorWeights = timelinessFactorWeightSer.findAll();
        timelinessFactorWeightSer.remove(timelinessFactorWeights);
    }
}