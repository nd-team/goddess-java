package com.bjike.goddess.interiorrecommend.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.bo.AwardInfoBO;
import com.bjike.goddess.interiorrecommend.bo.AwardStandardBO;
import com.bjike.goddess.interiorrecommend.dto.AwardInfoDTO;
import com.bjike.goddess.interiorrecommend.entity.AwardInfo;
import com.bjike.goddess.interiorrecommend.entity.RecommendInfo;
import com.bjike.goddess.interiorrecommend.enums.GuideAddrStatus;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.to.AwardInfoTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐奖励信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 04:07 ]
 * @Description: [ 推荐奖励信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "interiorrecommendSerCache")
@Service
public class AwardInfoSerImpl extends ServiceImpl<AwardInfo, AwardInfoDTO> implements AwardInfoSer {

    @Autowired
    private RecommendInfoSer recommendInfoSer;
    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionOperateSer cusPermissionOperateSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private AwardStandardSer awardStandardSer;

    @Autowired
    private RecommendRequireSer recommendRequireSer;

    @Autowired
    private RecommendTypeSer recommendTypeSer;

    @Autowired
    private RecommendSchemeSer recommendSchemeSer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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
     * 导航栏核对查看权限（部门级别）
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
     * 导航栏核对添加修改删除权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagAwardInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("awardinfo");
        obj.setDescribesion("推荐奖励信息");
        if (flagAwardInfo || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagStandard = awardStandardSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("awardstandard");
        obj.setDescribesion("推荐奖励要求标准");
        if (flagStandard) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagInfo = recommendInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recommendinfo");
        obj.setDescribesion("推荐信息");
        if (flagInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagRequire = recommendRequireSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recommendrequire");
        obj.setDescribesion("推荐要求");
        if (flagRequire) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagType = recommendTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recommendscheme");
        obj.setDescribesion("推荐方案");
        if (flagType) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagScheme = recommendSchemeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recommendtype");
        obj.setDescribesion("推荐类型");
        if (flagScheme) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    public AwardInfoBO updateModel(AwardInfoTO to) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(to.getId())) {
            AwardInfo model = super.findById(to.getId());
            if (model != null) {

                //判断领取奖励人是否为推荐人
                if (to.getGetAward()) {
                    RecommendInfo recommendInfo = model.getRecommendInfo();
                    if (recommendInfo != null) {
                        if (!userAPI.currentUser().getUsername().equals(recommendInfo.getRecommendUser())) {
                            throw new SerException("无法代领他人的推荐奖励!");
                        }
                    } else {
                        throw new SerException("推荐信息不存在!");
                    }
                }
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, AwardInfoBO.class);
    }

    @Override
    public List<AwardInfoBO> pageList(AwardInfoDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("creatTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), AwardInfoBO.class);
    }

}