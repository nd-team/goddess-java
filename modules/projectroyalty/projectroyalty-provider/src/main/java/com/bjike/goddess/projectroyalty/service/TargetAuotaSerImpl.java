package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.TargetAuotaBO;
import com.bjike.goddess.projectroyalty.dto.TargetAuotaDTO;
import com.bjike.goddess.projectroyalty.entity.TargetAuota;
import com.bjike.goddess.projectroyalty.entity.WeightAllocation;
import com.bjike.goddess.projectroyalty.enums.GuideAddrStatus;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目提成目标定额业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class TargetAuotaSerImpl extends ServiceImpl<TargetAuota, TargetAuotaDTO> implements TargetAuotaSer {

    @Autowired
    private WeightAllocationSer weightAllocationSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    private TargetAuotaBO transform(TargetAuota entity) throws SerException {
        TargetAuotaBO bo = BeanTransform.copyProperties(entity, TargetAuotaBO.class);
        bo.setAllocationId(entity.getAllocation().getId());
        bo.setArea(entity.getAllocation().getArea());
        bo.setProject(entity.getAllocation().getProject());
        return bo;
    }

    private List<TargetAuotaBO> transformList(List<TargetAuota> list) throws SerException {
        List<TargetAuotaBO> bos = new ArrayList<>(0);
        for (TargetAuota entity : list)
            bos.add(this.transform(entity));
        return bos;
    }

    @Override
    public TargetAuotaBO targetSave(TargetAuotaTO to) throws SerException {
        TargetAuota entity = BeanTransform.copyProperties(to, TargetAuota.class);
        entity.setAllocation(weightAllocationSer.findById(to.getAllocationId()));
        if (null == entity.getAllocation())
            throw new SerException("所选的权重分配不存在");
        this.countAuota(entity);
        entity.setPlan(Boolean.TRUE);
        super.save(entity);
        return this.transform(entity);
    }

    @Override
    public TargetAuotaBO actualSave(TargetAuotaTO to) throws SerException {
        TargetAuota entity = BeanTransform.copyProperties(to, TargetAuota.class);
        entity.setAllocation(weightAllocationSer.findById(to.getAllocationId()));
        if (null == entity.getAllocation())
            throw new SerException("所选的权重分配不存在");
        this.countAuota(entity);
        entity.setPlan(Boolean.FALSE);
        super.save(entity);
        return this.transform(entity);
    }

    private void countAuota(TargetAuota entity) throws SerException {
        WeightAllocation allocation = entity.getAllocation();
        Double money = allocation.getProfit();
        entity.setBusiness(this.decimal(money * allocation.getBusiness() / 100));
        entity.setManage(this.decimal(money * allocation.getManage() / 100));
        entity.setCapital(this.decimal(money * allocation.getCapital() / 100));
        entity.setStaff(this.decimal(money * allocation.getStaff() / 100));
        entity.setRisk(this.decimal(money * allocation.getRisk() / 100));
        entity.setProfit(this.decimal(money * allocation.getProfit() / 100));
    }

    private Double decimal(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public TargetAuotaBO update(TargetAuotaTO to) throws SerException {
        TargetAuota entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setAllocation(weightAllocationSer.findById(to.getAllocationId()));
        if (null == entity.getAllocation())
            throw new SerException("所选的权重分配不存在");
        this.countAuota(entity);
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public TargetAuotaBO delete(String id) throws SerException {
        TargetAuota entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return this.transform(entity);
    }

    @Override
    public TargetAuotaBO getById(String id) throws SerException {
        TargetAuota entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transform(entity);
    }

    @Override
    public List<TargetAuotaBO> targetMaps(TargetAuotaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("plan", !Boolean.TRUE));
        dto.getSorts().add("createTime=desc");
        return this.transformList(super.findByPage(dto));
    }

    @Override
    public List<TargetAuotaBO> actualMaps(TargetAuotaDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("plan", !Boolean.FALSE));
        dto.getSorts().add("createTime=desc");
        return this.transformList(super.findByPage(dto));
    }

    @Override
    public Long getTargetTotal() throws SerException {
        TargetAuotaDTO dto = new TargetAuotaDTO();
        dto.getConditions().add(Restrict.eq("plan", !Boolean.TRUE));
        return super.count(dto);
    }

    @Override
    public Long getActualTotal() throws SerException {
        TargetAuotaDTO dto = new TargetAuotaDTO();
        dto.getConditions().add(Restrict.eq("plan", !Boolean.FALSE));
        return super.count(dto);
    }

    @Override
    public Boolean isDependent(String id) throws SerException {
        String[] files = new String[]{"allocation_id"};
        String sql = "select allocation_id from projectroyalty_targetauota where allocation_id = '" + id + "'";
        List<TargetAuota> targetAuotas = super.findBySql(sql,TargetAuota.class,files);
        Boolean tar = false;
        if(!CollectionUtils.isEmpty(targetAuotas)){
            tar = true;
        }
        return tar;
    }
}