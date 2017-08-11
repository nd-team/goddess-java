package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.enums.GuideAddrStatus;
import com.bjike.goddess.accommodation.excel.RentalApplyExport;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
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
 * 租房申请 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房申请 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class RentalApplySerImpl extends ServiceImpl<RentalApply, RentalApplyDTO> implements RentalApplySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RentalSer rentalSer;
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
     * 核对项目经理审核权限（岗位级别）
     */
    private void checkManagerAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("8");
            if (!flag) {
                throw new SerException("您不是相应项目经理的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对商务发展部审核权限（部门级别）
     */
    private void checkBusinessAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("9");
            if (!flag) {
                throw new SerException("您不是相应商务发展部的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对运营财务部审核权限（部门级别）
     */
    private void checkFinanceAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("10");
            if (!flag) {
                throw new SerException("您不是相应运营财务部的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对综合资源部审核权限（部门级别）
     */
    private void checkResourceAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("11");
            if (!flag) {
                throw new SerException("您不是相应综合资源部的人员，不可以操作");
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

    /**
     * 核对项目经理审核权限（岗位级别）
     */
    private Boolean guideManagerAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("8");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对商务发展部审核权限（部门级别）
     */
    private Boolean guideBusinessAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("9");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对运营财务部审核权限（部门级别）
     */
    private Boolean guideFinanceAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("10");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对综合资源部审核权限（部门级别）
     */
    private Boolean guideResourceAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("11");
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
        Boolean flagBus = guideBusinessAuditIdentity();
        Boolean flagFin = guideFinanceAuditIdentity();
        Boolean flagRes = guideResourceAuditIdentity();
        Boolean flagMan = guideManagerAuditIdentity();
        if (flagSee || flagAdd || flagBus || flagFin || flagRes || flagMan) {
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
            case BUSINESSAUDIT:
                flag = guideBusinessAuditIdentity();
                break;
            case FINANCEAUDIT:
                flag = guideFinanceAuditIdentity();
                break;
            case RESOURCEAUDIT:
                flag = guideResourceAuditIdentity();
                break;
            case MANAGERAUDIT:
                flag = guideManagerAuditIdentity();
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
    public Long count(RentalApplyDTO rentalApplyDTO) throws SerException {
        Long count = super.count(rentalApplyDTO);
        return count;
    }

    @Override
    public RentalApplyBO getOne(String id) throws SerException {
        RentalApply rentalApply = super.findById(id);
        return BeanTransform.copyProperties(rentalApply, RentalApplyBO.class);
    }

    @Override
    public List<RentalApplyBO> findListRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {
        checkSeeIdentity();
        rentalApplyDTO.getSorts().add("createTime=desc");
        List<RentalApply> rentalApplies = super.findByCis(rentalApplyDTO, true);
        List<RentalApplyBO> rentalApplyBOS = BeanTransform.copyProperties(rentalApplies, RentalApplyBO.class);
        return rentalApplyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO insertApply(RentalApplyTO applyTO) throws SerException {
        checkAddIdentity();
        RentalApply apply = BeanTransform.copyProperties(applyTO, RentalApply.class, true, "projectName");
        apply.setCreateTime(LocalDateTime.now());
        apply.setProjectName(StringUtils.join(applyTO.getProjectName(), ","));
        super.save(apply);
        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO editApply(RentalApplyTO applyTO) throws SerException {
        checkAddIdentity();
        RentalApply rentalApply = super.findById(applyTO.getId());
        BeanTransform.copyProperties(applyTO, rentalApply, true, "projectName");
        rentalApply.setModifyTime(LocalDateTime.now());
        rentalApply.setProjectName(StringUtils.join(applyTO.getProjectName(), ","));
        super.update(rentalApply);
        RentalApplyBO bo = BeanTransform.copyProperties(rentalApply, RentalApplyBO.class);
        return bo;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeApply(String id) throws SerException {
        checkAddIdentity();
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO businessAudit(RentalApplyTO applyTO) throws SerException {
        checkBusinessAuditIdentity();
        RentalApply apply = super.findById(applyTO.getId());
        BeanTransform.copyProperties(applyTO,apply,true);
        apply.setCommerceRemark(applyTO.getCommerceRemark());
        super.update(apply);
        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO financeAudit(RentalApplyTO applyTO) throws SerException {
        checkFinanceAuditIdentity();
        RentalApply apply = super.findById(applyTO.getId());
        BeanTransform.copyProperties(applyTO,apply,true);
        apply.setOperatingRemark(applyTO.getOperatingRemark());
        super.update(apply);
        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO resourceAudit(RentalApplyTO applyTO) throws SerException {
        checkResourceAuditIdentity();
        RentalApply apply = super.findById(applyTO.getId());
        BeanTransform.copyProperties(applyTO,apply,true);
        apply.setComprehensiveRemark(applyTO.getComprehensiveRemark());
        super.update(apply);
        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO manageAudit(RentalApplyTO applyTO) throws SerException {
        checkManagerAuditIdentity();
        UserBO userBO = userAPI.currentUser();

        RentalApply apply = super.findById(applyTO.getId());
        BeanTransform.copyProperties(applyTO,apply,true);
        apply.setManage(userBO.getUsername());
        apply.setManageOpinion(applyTO.getManageOpinion());
        apply.setManagePass(applyTO.getManagePass());
        super.update(apply);
        RentalApplyBO bo = BeanTransform.copyProperties(apply, RentalApplyBO.class);
        return bo;
    }

    @Override
    public RentalBO rentInfo(RentalApplyTO to) throws SerException {

        RentalApply rentalApply = super.findById(to.getId());
                BeanTransform.copyProperties(to, rentalApply, true);
        Rental rental = new Rental();
        if ("通过".equals(to.getManagePass()) && "通过".equals(to.getCommerceRemark())
                && "通过".equals(to.getComprehensiveRemark()) && "通过".equals(to.getOperatingRemark())) {
            rental.setRentNum(rentalApply.getRentNum());//租房编号
            rental.setArea(rentalApply.getArea());//地区
            rental.setProjectGroup(rentalApply.getProjectGroup());//项目组
            rental.setProjectName(rentalApply.getProjectName());//项目名称
            rental.setLessee(rentalApply.getLessee());//租赁人
            rental.setAddress(rentalApply.getAddress());//租房地址
            rental.setLandlord(rentalApply.getLandlord());//房东姓名
            rental.setContact(rentalApply.getContact());//联系方式
            rental.setPurpose(rentalApply.getPurpose());//租房用途
            rental.setAgency(rentalApply.getAgency());//中介费
            rental.setDeposit(rentalApply.getDeposit());//押金
            rental.setRent(rentalApply.getRent());//房租
            rental.setManagementFee(rentalApply.getRentFee());//管理费
            rental.setHealthFee(rentalApply.getSanitation());//卫生费
            rental.setRentPay(rentalApply.getRentPay());//房租缴费方
            rental.setWaterMoney(rentalApply.getWater());//水费计价金额(元/吨)
            rental.setWaterPay(rentalApply.getWaterPay());//水费缴费方
            rental.setEnergyPay(rentalApply.getEnergyPay());//电费缴费方
            rental.setEnergyMoney(rentalApply.getEnergy()); //电费计价额
            rental.setNetworkMoney(rentalApply.getNetwork());// 网络套餐费用缴纳金额
            rental.setNetworkPay(rentalApply.getNetworkPay());  //网络套餐费用缴费方
            rentalSer.save(rental);
        }
        return BeanTransform.copyProperties(rental,RentalBO.class);
    }

    @Override
    public byte[] exportExcel(RentalApplyDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("createTime", condi));
        }
        List<RentalApply> list = super.findByCis(dto);
        List<RentalApplyExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            RentalApplyExport export = BeanTransform.copyProperties(str, RentalApplyExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    /**
     * 自动生成记账凭证
     */
    public String generateCredentials() throws SerException {
        //TODO: xiazhili 2017-03-10 未做自动生成记账凭证
        return null;
    }

}
