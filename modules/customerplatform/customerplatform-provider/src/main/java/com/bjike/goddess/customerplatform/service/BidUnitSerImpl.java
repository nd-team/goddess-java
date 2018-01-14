package com.bjike.goddess.customerplatform.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.customerplatform.bo.BidUnitBO;
import com.bjike.goddess.customerplatform.dto.BidUnitDTO;
import com.bjike.goddess.customerplatform.entity.BidUnit;
import com.bjike.goddess.customerplatform.entity.SonPermissionObject;
import com.bjike.goddess.customerplatform.enums.GuideAddrStatus;
import com.bjike.goddess.customerplatform.to.BidUnitTO;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 中标单位业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:20 ]
 * @Description: [ 中标单位业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerplatformSerCache")
@Service
public class BidUnitSerImpl extends ServiceImpl<BidUnit, BidUnitDTO> implements BidUnitSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private OwnerSer ownerSer;
    @Autowired
    private SiteSer siteSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("bidunit");
        obj.setDescribesion("中标单位");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = ownerSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("owner");
        obj.setDescribesion("业主");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis1 = siteSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("site");
        obj.setDescribesion("站点");
        if (flagSeeDis1) {
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void save(BidUnitTO to) throws SerException {
        BidUnit entity = BeanTransform.copyProperties(to, BidUnit.class, true);
        super.save(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(BidUnitTO to) throws SerException {
        BidUnit entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, entity, "id", "createTime");
        if (StringUtils.isNotBlank(to.getCreateCompanyTime())) {
            entity.setCreateCompanyTime(DateUtil.parseDate(to.getCreateCompanyTime()));
        } else {
            entity.setCreateCompanyTime(null);
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        BidUnit entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<BidUnitBO> maps(BidUnitDTO dto) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<BidUnit> bidUnits = super.findByCis(dto,true);
        List<BidUnitBO> bidUnitBOs = BeanTransform.copyProperties(bidUnits, BidUnitBO.class, false);
        if (null != bidUnitBOs && bidUnitBOs.size() > 0) {
            for(BidUnitBO bo : bidUnitBOs){
                // TODO: 18-1-5 判断是否是vip
                Boolean tar = false;
                if (!tar) {
                    bo.setPhone(transPhone(bo.getPhone()));
                }
            }
        }
        return bidUnitBOs;
    }

    @Override
    public BidUnitBO getById(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        BidUnit entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BidUnitBO bo = BeanTransform.copyProperties(entity, BidUnitBO.class);
        // TODO: 18-1-5 判断是否是vip
        Boolean tar = false;
        if (!tar) {
            bo.setPhone(transPhone(bo.getPhone()));
        }
        return bo;
    }

    @Override
    public Long getTotal(BidUnitDTO dto) throws SerException {
        return super.count(dto);
    }

    private String transPhone(String phone) throws SerException {
        phone = phone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1****$2");
        return phone;
    }
}