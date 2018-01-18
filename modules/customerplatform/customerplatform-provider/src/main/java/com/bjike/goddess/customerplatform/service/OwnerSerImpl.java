package com.bjike.goddess.customerplatform.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customerplatform.bo.OwnerBO;
import com.bjike.goddess.customerplatform.dto.OwnerDTO;
import com.bjike.goddess.customerplatform.entity.Owner;
import com.bjike.goddess.customerplatform.enums.GuideAddrStatus;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.OwnerTO;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 业主业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:27 ]
 * @Description: [ 业主业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerplatformSerCache")
@Service
public class OwnerSerImpl extends ServiceImpl<Owner, OwnerDTO> implements OwnerSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
    public void save(OwnerTO to) throws SerException {
        Owner entity = BeanTransform.copyProperties(to, Owner.class, true);
        super.save(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(OwnerTO to) throws SerException {
        Owner entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, entity, "id", "createTime");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        Owner entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        super.remove(id);
    }

    public List<OwnerBO> maps(OwnerDTO dto) throws SerException {
        search(dto);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        List<Owner> owners1 = super.findByCis(dto, true);
        List<OwnerBO> ownerBOs1 = BeanTransform.copyProperties(owners1, OwnerBO.class, false);
        if (null != ownerBOs1 && ownerBOs1.size() > 0) {
            for (OwnerBO bo : ownerBOs1) {
                // TODO: 18-1-5 判断是否是vip
                Boolean tar = false;
                if (!tar) {
                    bo.setPhone(transPhone(bo.getPhone()));
                }
            }
        }
        return ownerBOs1;
    }

    private List<OwnerBO> search(OwnerDTO dto) throws SerException {
        //业主姓名
        if (StringUtils.isNotBlank(dto.getOwnerName())) {
            dto.getConditions().add(Restrict.like("ownerName",dto.getOwnerName()));
        }
        //业主地址
        if(StringUtils.isNotBlank(dto.getOwneraddress())){
            dto.getConditions().add(Restrict.like("owneraddress",dto.getOwneraddress()));
        }
        //省份
        if(StringUtils.isNotBlank(dto.getProvinces())){
            dto.getConditions().add(Restrict.eq("provinces",dto.getProvinces()));
        }
        //市
        if(StringUtils.isNotBlank(dto.getCity())){
            dto.getConditions().add(Restrict.eq("city",dto.getCity()));
        }
        //区
        if(StringUtils.isNotBlank(dto.getArea())){
            dto.getConditions().add(Restrict.eq("area",dto.getArea()));
        }
        //需求类型
        if(StringUtils.isNotBlank(dto.getDemandType())){
            dto.getConditions().add(Restrict.like("demandType",dto.getDemandType()));
        }
        if(StringUtils.isNotBlank(dto.getStartTime()) && StringUtils.isNotBlank(dto.getEndTime())){
            dto.getConditions().add(Restrict.between("createTime",new String[]{dto.getStartTime(),dto.getEndTime()}));
        }
        List<Owner> owners = super.findByCis(dto);
        List<OwnerBO> ownerBOs= BeanTransform.copyProperties(owners,OwnerBO.class);
        return ownerBOs;
    }

    @Override
    public OwnerBO getById(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        Owner entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        OwnerBO bo = BeanTransform.copyProperties(entity, OwnerBO.class);
        // TODO: 18-1-5 判断是否是vip
        Boolean tar = false;
        if (!tar) {
            bo.setPhone(transPhone(bo.getPhone()));
        }
        return bo;
    }

    @Override
    public Long getTotal(OwnerDTO dto) throws SerException {
        search(dto);
        return super.count(dto);
    }

    private String transPhone(String phone) throws SerException {
        phone = phone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1****$2");
        return phone;
    }
    @Override
    public List<String> getProvinces() throws SerException {
        Set<String> set = new HashSet<>();
        OwnerDTO dto = new OwnerDTO();
        List<Owner> owners = super.findByCis(dto);
        if(owners != null && owners.size()>0){
            for(Owner owner:owners){
                set.add(owner.getProvinces());
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getCity(String provinces) throws SerException {
        Set<String> set = new HashSet<>();
        OwnerDTO dto = new OwnerDTO();
        dto.getConditions().add(Restrict.eq("provinces",provinces));
        List<Owner> owners = super.findByCis(dto);
        if(owners != null && owners.size()>0){
            for(Owner owner:owners){
                set.add(owner.getCity());
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getArea(String provinces,String city) throws SerException {
        Set<String> set = new HashSet<>();
        OwnerDTO dto = new OwnerDTO();
        dto.getConditions().add(Restrict.eq("provinces",provinces));
        dto.getConditions().add(Restrict.eq("city",city));
        List<Owner> owners = super.findByCis(dto);
        if(owners != null && owners.size()>0){
            for(Owner owner:owners){
                set.add(owner.getArea());
            }
        }
        return new ArrayList<>(set);
    }
}