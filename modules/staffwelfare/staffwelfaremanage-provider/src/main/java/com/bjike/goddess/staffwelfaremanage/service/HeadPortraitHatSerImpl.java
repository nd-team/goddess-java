package com.bjike.goddess.staffwelfaremanage.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfaremanage.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfaremanage.entity.HeadPortraitHat;
import com.bjike.goddess.staffwelfaremanage.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfaremanage.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.HeadPortraitHatTO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 头像帽业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class HeadPortraitHatSerImpl extends ServiceImpl<HeadPortraitHat, HeadPortraitHatDTO> implements HeadPortraitHatSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private PersonalFestivalSer personalFestivalSer;

    @Autowired
    private PersonalFestivalWishSer personalFestivalWishSer;
    @Autowired
    private StaffBirthdaySchemeSer staffBirthdaySchemeSer;
    @Autowired
    private StaffBirthDayWelfareSer staffBirthDayWelfareSer;

    @Autowired
    private ThankStatementSer thankStatementSer;

    @Autowired
    private WishesStatementSer wishesStatementSer;



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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("headPortraiHat");
        obj.setDescribesion("头像帽表");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = personalFestivalSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("personalFestival");
        obj.setDescribesion("个人节日表");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = personalFestivalWishSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("personalFestivalWish");
        obj.setDescribesion("个人生日祝福语表");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = staffBirthdaySchemeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("staffBirthdayScheme");
        obj.setDescribesion("员工生日福利方案表");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = staffBirthDayWelfareSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("staffBirthDayWelfare");
        obj.setDescribesion("员工生日福利记录表");
        if (flagSeeBase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagThank = thankStatementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("thankStatement");
        obj.setDescribesion("感谢语表");
        if (flagThank) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagWishes = wishesStatementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("staffBirthDayWelfare");
        obj.setDescribesion("祝福语表");
        if (flagWishes) {
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
            case AUDIT:
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
    @Transactional(rollbackFor = SerException.class)
    public HeadPortraitHatBO insertModel(HeadPortraitHatTO to) throws SerException {
        checkSeeIdentity();
        Object o = RpcContext.getContext().getAttachment("storageToken");

        HeadPortraitHat model = BeanTransform.copyProperties(to, HeadPortraitHat.class, true);
        model.setCreateUser(getCurrentUser().getUsername());
        String path = "/headPortraitHat";

        if(null!=o){
            RpcContext.getContext().setAttachment("storageToken",o.toString());
        }
//        fileAPI.upload(to.getMap(), path);
        model.setUrl(path);
      //  super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, HeadPortraitHatBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public HeadPortraitHatBO updateModel(HeadPortraitHatTO to) throws SerException {
        checkSeeIdentity();
        if (!StringUtils.isEmpty(to.getId())) {
            HeadPortraitHat model = super.findById(to.getId());
            if (model != null) {
                model.setUpdateUser(getCurrentUser().getUsername());
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, HeadPortraitHatBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<HeadPortraitHatBO> pageList(HeadPortraitHatDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        //
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<HeadPortraitHat> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, HeadPortraitHatBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<HeadPortraitHatBO> findHeadPortraitHats() throws SerException {
        checkSeeIdentity();
        HeadPortraitHatDTO dto = new HeadPortraitHatDTO();
        dto.getSorts().add("createTime=desc");
        //
        dto.getConditions().add(Restrict.or("createUser", getCurrentUser().getUsername()));
        List<HeadPortraitHat> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, HeadPortraitHatBO.class);
    }

    //获取当前用户
    public UserBO getCurrentUser() throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (userBO != null) {
            return userBO;
        } else {
            throw new SerException("用户未登录或已超时!");
        }
    }

}