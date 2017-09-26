package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.interiorrecommend.bo.OldAwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.OldAwardStandardDTO;
import com.bjike.goddess.interiorrecommend.entity.OldAwardStandard;
import com.bjike.goddess.interiorrecommend.entity.OldRecommendRequire;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.to.OldAwardStandardTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐奖励要求标准业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 11:39 ]
 * @Description: [ 推荐奖励要求标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class OldAwardStandardSerImpl extends ServiceImpl<OldAwardStandard, OldAwardStandardDTO> implements OldAwardStandardSer {

    @Autowired
    private OldRecommendRequireSer recommendRequireSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
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
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public OldAwardStandardBO insertModel(OldAwardStandardTO to) throws SerException {
        checkAddIdentity();
        OldRecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
        if (recommendRequire != null) {
            OldAwardStandard model = BeanTransform.copyProperties(to, OldAwardStandard.class, true);
            model.setRecommendRequire(recommendRequire);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, OldAwardStandardBO.class);
        } else {
            throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
        }
    }

    @Override
    public OldAwardStandardBO updateModel(OldAwardStandardTO to) throws SerException {
        checkAddIdentity();
        OldAwardStandard model = super.findById(to.getId());
        if (model != null) {
            OldRecommendRequire recommendRequire = recommendRequireSer.findById(to.getRequireId());
            if (recommendRequire != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setRecommendRequire(recommendRequire);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, OldAwardStandardBO.class);
            } else {
                throw new SerException("非法推荐要求Id,推荐要求对象不能为空!");
            }
        } else {
            throw new SerException("非法Id,奖励标准设定对象不能为空!");
        }
    }

    @Override
    public List<OldAwardStandardBO> pageList(OldAwardStandardDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
//        List<AwardStandard> list = super.findByPage(dto);
        List<OldAwardStandard> list = super.findByCis(dto);
        if (!CollectionUtils.isEmpty(list)) {
            List<OldAwardStandardBO> boList = new ArrayList<OldAwardStandardBO>();
            for (OldAwardStandard model : list) {
                OldAwardStandardBO bo = BeanTransform.copyProperties(model, OldAwardStandardBO.class);
                bo.setOpenTime(DateUtil.dateToString(model.getRecommendRequire().getRecommendScheme().getOpenTime()));
                bo.setCloseTime(DateUtil.dateToString(model.getRecommendRequire().getRecommendScheme().getCloseTime()));
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    public OldAwardStandardBO findOne(String id) throws SerException {
        OldAwardStandard awardStandard = super.findById(id);
        OldAwardStandardBO bo = BeanTransform.copyProperties(awardStandard,OldAwardStandardBO.class);
        return bo;
    }

    @Override
    public Long count(OldAwardStandardDTO dto) throws SerException {
        return super.count(dto);
    }
}