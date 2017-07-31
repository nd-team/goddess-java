package com.bjike.goddess.bonus.service;

import com.bjike.goddess.bonus.bo.PerformanceIndicatorBO;
import com.bjike.goddess.bonus.dto.PerformanceIndicatorDTO;
import com.bjike.goddess.bonus.entity.PerformanceIndicator;
import com.bjike.goddess.bonus.enums.GuideAddrStatus;
import com.bjike.goddess.bonus.excel.SonPermissionObject;
import com.bjike.goddess.bonus.to.GuidePermissionTO;
import com.bjike.goddess.bonus.to.PerformanceIndicatorTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 绩效指标业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bonusSerCache")
@Service
public class PerformanceIndicatorSerImpl extends ServiceImpl<PerformanceIndicator, PerformanceIndicatorDTO> implements PerformanceIndicatorSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DisciplineRecordSer disciplineRecordSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 调整(总经办)
     *
     * @throws SerException
     */
    private Boolean checkPosin() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.positCusPermission("2");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 调整(决策层)
     *
     * @throws SerException
     */
    private Boolean checkLeve() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.leveCusPermission("3");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 调整(规划模块)
     *
     * @throws SerException
     */
    private Boolean checkModule() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.getCusPermission("4");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 调整权限(总经办,决策层,规划模块)
     *
     * @throws SerException
     */
    private void checkAdjustPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean posinFlag = checkPosin();
        Boolean leveFlag = checkLeve();
        Boolean moduleFlag = checkModule();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (posinFlag || leveFlag || moduleFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 发起奖励处罚(综合资源部)
     *
     * @throws SerException
     */
    private Boolean checkDetail() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.positCusPermission("5");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 发起奖励处罚(福利模块或规划模块)
     *
     * @throws SerException
     */
    private Boolean checkModulePlan() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.leveCusPermission("6");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 发起奖励处罚(总经办,决策层,规划模块)
     *
     * @throws SerException
     */
    private void checkInitiPerm() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean detailFlag = checkDetail();
        Boolean planFlag = checkModulePlan();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (detailFlag || planFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 调整权限(总经办,决策层,规划模块)
     */
    private Boolean guideAdjustIdentity() throws SerException {
        Boolean flag = false;
        Boolean posinFlag = checkPosin();
        Boolean leveFlag = checkLeve();
        Boolean moduleFlag = checkModule();
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (posinFlag || leveFlag || moduleFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 发起奖励处罚(总经办,决策层,规划模块)
     */
    private Boolean guideInitiPerm() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean detailFlag = checkDetail();
        Boolean planFlag = checkModulePlan();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (detailFlag || planFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 权限
     */
    private Boolean guideAllTrue() throws SerException {
        return true;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        {
            List<SonPermissionObject> list = new ArrayList<>();
            String userToken = RpcTransmit.getUserToken();
            Boolean flagSee = guideIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagAdjust = guideAdjustIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagIniti = guideInitiPerm();
            RpcTransmit.transmitUserToken(userToken);

            SonPermissionObject obj = new SonPermissionObject();

            obj = new SonPermissionObject();
            obj.setName("performanceindicator");
            obj.setDescribesion("流程绩效指标管理");
            if (flagSee || flagAdjust || flagIniti) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagReward = disciplineRecordSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("disciplireward");
            obj.setDescribesion("奖励明细");
            if (flagReward) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagPunish = disciplineRecordSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("discipliPunish");
            obj.setDescribesion("处罚明细");
            if (flagPunish) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            return list;
        }
    }


    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case ADD:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case EDIT:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case DELETE:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case OPERS:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case CLOSES:
                flag = guideIdentity() || guideAdjustIdentity();
                break;
            case SUMMARY:
                flag = guideInitiPerm();
                break;
            case PROJECTRANK:
                flag = guideInitiPerm();
                break;
            case PERSONRANK:
                flag = guideAllTrue();
                break;
            case JCLIST:
                flag = guideInitiPerm();
                break;
            case JCADD:
                flag = guideInitiPerm();
                break;
            case JCEDIT:
                flag = guideInitiPerm();
                break;
            case JCDELETE:
                flag = guideInitiPerm();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public PerformanceIndicatorBO save(PerformanceIndicatorTO to) throws SerException {
        checkAdjustPermission();
        PerformanceIndicator entity = BeanTransform.copyProperties(to, PerformanceIndicator.class);
        entity.setStatus(Boolean.TRUE);
        super.save(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO update(PerformanceIndicatorTO to) throws SerException {
        checkAdjustPermission();
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                PerformanceIndicator entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }

        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public PerformanceIndicatorBO delete(String id) throws SerException {
        checkAdjustPermission();
        PerformanceIndicator entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO start(String id) throws SerException {
        checkAdjustPermission();
        PerformanceIndicator entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Boolean.TRUE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO close(String id) throws SerException {
        checkAdjustPermission();
        PerformanceIndicator entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Boolean.FALSE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public List<PerformanceIndicatorBO> findByStatus(Boolean status) throws SerException {
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO();
        dto.getConditions().add(Restrict.eq("status", !status));
        List<PerformanceIndicator> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, PerformanceIndicatorBO.class);
    }

    @Override
    public List<PerformanceIndicatorBO> maps(PerformanceIndicatorDTO dto) throws SerException {
        checkAdjustPermission();
        dto.getSorts().add("project=asc");
        dto.getSorts().add("type=asc");
        dto.getSorts().add("name=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO getById(String id) throws SerException {
        PerformanceIndicator entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO();
        return super.count(dto);
    }
}