package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.ClosenessFoactorSetBO;
import com.bjike.goddess.customer.dto.ClosenessFoactorSetDTO;
import com.bjike.goddess.customer.entity.ClosenessFoactorSet;
import com.bjike.goddess.customer.entity.ClosenessFoactorWeight;
import com.bjike.goddess.customer.entity.FirstFactorWeight;
import com.bjike.goddess.customer.entity.TimelinessFactorWeight;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.ClosenessFoactorSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.utils.AHPComputeWeight;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
    public Long countCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
        Long count = super.count(closenessFoactorSetDTO);
        return count;
    }

    @Override
    public ClosenessFoactorSetBO getOneCloseness(String id) throws SerException {
        ClosenessFoactorSet closenessFoactorSet = super.findById(id);
        return BeanTransform.copyProperties(closenessFoactorSet,ClosenessFoactorSetBO.class);
    }

    @Override
    public List<ClosenessFoactorSetBO> listCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
       List<ClosenessFoactorSet> closenessFoactorSetList = super.findByCis(closenessFoactorSetDTO);
        return BeanTransform.copyProperties(closenessFoactorSetList,ClosenessFoactorSetBO.class);
    }

    @Override
    public ClosenessFoactorSetBO addCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        List<FirstFactorWeight> firstFactorWeightList = firstFactorWeightSer.findAll();
        if(firstFactorWeightList==null){
            throw new SerException("请先设置权重一层因素层");
        }
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
        for (int i = 0; i < 3; i++) {
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