package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.entity.BasicInfo;
import com.bjike.goddess.buyticket.enums.GuideAddrStatus;
import com.bjike.goddess.buyticket.to.BasicInfoTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 基本信息设置业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BasicInfoSerImpl extends ServiceImpl<BasicInfo, BasicInfoDTO> implements BasicInfoSer {

    @Autowired
    private BuyCusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 设置权限表中岗位权限
     *
     * @throws SerException
     */
    private Boolean positCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission(idFlag);
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;

    }

    /**
     * 检查权限(模块)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查申请查看权限(模块)
     *
     * @throws SerException
     */
    private Boolean checkAppSeePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);

        return flag;
    }

    /**
     * 检查权限(岗位)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对模块权限（模块级别）
     */
    private Boolean guideMondIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuideMod = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuideMod || flagGuidePosi) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(BuyGuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case APPLIST:
                flag = true;
                break;
            case APPADD:
                flag = true;
                break;
            case APPEDIT:
                flag = true;
                break;
            case LIST:
                flag = guideMondIdentity();
                break;
            case ADD:
                flag = guideMondIdentity();
                break;
            case EDIT:
                flag = guideMondIdentity();
                break;
            case DELETE:
                flag = guideMondIdentity();
                break;
            case PLANAUDIT:
                flag = guideMondIdentity();
                break;
            case WELFAUDIT:
                flag = guideMondIdentity();
                break;
            case CONGEL:
                flag = guideMondIdentity();
                break;
            case THAW:
                flag = guideMondIdentity();
                break;
            case RECORDLIST:
                flag = true;
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        basicInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(basicInfoDTO);
        return count;
    }

    @Override
    public BasicInfoBO getOne(String id) throws SerException {
        BasicInfo basicInfo = super.findById(id);
        return BeanTransform.copyProperties(basicInfo, BasicInfoBO.class, true);
    }

    @Override
    public List<BasicInfoBO> findListBasicInfo(BasicInfoDTO basicInfoDTO) throws SerException {
        checkModPermission();
        basicInfoDTO.getSorts().add("createTime=desc");
        List<BasicInfo> basicInfos = super.findByCis(basicInfoDTO, true);
        List<BasicInfoBO> basicInfoBOS = BeanTransform.copyProperties(basicInfos, BasicInfoBO.class, true);
        return basicInfoBOS;
    }

    @Override
    public BasicInfoBO insertBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        checkModPermission();
        BasicInfo basicInfo = BeanTransform.copyProperties(basicInfoTO, BasicInfo.class, true);
        basicInfo.setCreateTime(LocalDateTime.now());
        super.save(basicInfo);
        return BeanTransform.copyProperties(basicInfo, BasicInfoBO.class);
    }

    @Override
    public BasicInfoBO editBasicInfo(BasicInfoTO basicInfoTO) throws SerException {
        checkModPermission();
        BasicInfo basicInfo = super.findById(basicInfoTO.getId());
        BeanTransform.copyProperties(basicInfoTO, basicInfo, true);
        basicInfo.setModifyTime(LocalDateTime.now());
        super.update(basicInfo);
        return BeanTransform.copyProperties(basicInfoTO, BasicInfoBO.class);
    }

    @Override
    public void removeBasicInfo(String id) throws SerException {
        checkModPermission();
        super.remove(id);

    }

    @Override
    public List<String> findAllTicketCause() throws SerException {
        List<BasicInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BasicInfo model : list) {
            String ticketCause = model.getTicketCause();
            if (StringUtils.isNotBlank(model.getTicketCause())) {
                set.add(ticketCause);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAllTicketType() throws SerException {
        List<BasicInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BasicInfo model : list) {
            String ticketType = model.getTicketType();
            if (StringUtils.isNotBlank(model.getTicketType())) {
                set.add(ticketType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAllBuyPattern() throws SerException {
        List<BasicInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BasicInfo model : list) {
            String buyPattern = model.getBuyPattern();
            if (StringUtils.isNotBlank(model.getBuyPattern())) {
                set.add(buyPattern);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAllSummaryType() throws SerException {
        List<BasicInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BasicInfo model : list) {
            String summaryType = model.getSummaryType();
            if (StringUtils.isNotBlank(model.getSummaryType())) {
                set.add(summaryType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAllDataAggregationType() throws SerException {
        List<BasicInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BasicInfo model : list) {
            String dataAggregationType = model.getDataAggregationType();
            if (StringUtils.isNotBlank(model.getDataAggregationType())) {
                set.add(dataAggregationType);
            }
        }
        return new ArrayList<>(set);
    }
}