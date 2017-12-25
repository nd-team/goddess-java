package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.entity.RentalPrecept;
import com.bjike.goddess.accommodation.enums.GuideAddrStatus;
import com.bjike.goddess.accommodation.enums.PassStatus;
import com.bjike.goddess.accommodation.excel.SonPermissionObject;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 租房方案 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房方案 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class RentalPreceptSerImpl extends ServiceImpl<RentalPrecept, RentalPreceptDTO> implements RentalPreceptSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private RentalApplySer rentalApplySer;
    @Autowired
    private RentalSer rentalSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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
     * 核对商务发展部审核权限（部门级别）
     */
    private void checkBusinessAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
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
            flag = cusPermissionSer.busCusPermission("4");
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
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是相应综合资源部的人员，不可以操作");
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
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是相应项目经理的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 核对总经办审核权限（部门级别）
     */
    private void checkGeneralAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
            if (!flag) {
                throw new SerException("您不是相应总经办的人员，不可以操作");
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
     * 核对商务发展部审核权限（部门级别）
     */
    private Boolean guideBusinessAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
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
            flag = cusPermissionSer.busCusPermission("4");
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
            flag = cusPermissionSer.busCusPermission("5");
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
            flag = cusPermissionSer.busCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * 核对总经办审核权限（部门级别）
     */
    private Boolean guideGeneralAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();
        Boolean flagBus = guideBusinessAuditIdentity();
        Boolean flagFin = guideFinanceAuditIdentity();
        Boolean flagRes = guideResourceAuditIdentity();
        Boolean flagMan = guideManagerAuditIdentity();
        Boolean flagGen = guideGeneralAuditIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("rentalPrecept");
        obj.setDescribesion("租房方案");
        if (flagSeeInfo || flagAddInfo || flagBus || flagFin || flagRes || flagMan || flagGen) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeApply = rentalApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rentalApply");
        obj.setDescribesion("租房申请");
        if (flagSeeApply) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeRental = rentalSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rental");
        obj.setDescribesion("租房信息");
        if (flagSeeRental) {
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
            case GENERALAUDIT:
                flag = guideGeneralAuditIdentity();
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
    public Long countRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        Long count = super.count(rentalPreceptDTO);
        return count;
    }

    @Override
    public RentalPreceptBO getOne(String id) throws SerException {
        RentalPrecept rentalPrecept = super.findById(id);
        return BeanTransform.copyProperties(rentalPrecept, RentalPreceptBO.class);
    }

    @Override
    public List<RentalPreceptBO> findListRentalPrecept(RentalPreceptDTO rentalPreceptDTO) throws SerException {
        checkSeeIdentity();
        rentalPreceptDTO.getSorts().add("createTime=desc");
        List<RentalPrecept> rentalPrecepts = super.findByCis(rentalPreceptDTO, true);
        List<RentalPreceptBO> rentalPreceptBOS = BeanTransform.copyProperties(rentalPrecepts, RentalPreceptBO.class);
        return rentalPreceptBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO insertPecept(RentalPreceptTO preceptTO) throws SerException {
        checkAddIdentity();
        RentalPrecept precept = BeanTransform.copyProperties(preceptTO, RentalPrecept.class, true, "projectName");
        //设置自动生成租房编号
        String rentNum = super.findByMaxField("rentNum", RentalPrecept.class);
        precept.setRentNum(generateNum(rentNum));
        precept.setCreateTime(LocalDateTime.now());
        precept.setProjectName(StringUtils.join(preceptTO.getProjectName(), ","));
        super.save(precept);
        return BeanTransform.copyProperties(precept, RentalPreceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO editPecept(RentalPreceptTO preceptTO) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(preceptTO.getId())) {
            RentalPrecept rentalPrecept = super.findById(preceptTO.getId());
            LocalDateTime createTime = rentalPrecept.getCreateTime();
            rentalPrecept = BeanTransform.copyProperties(preceptTO, RentalPrecept.class, true, "projectName");
            rentalPrecept.setCreateTime(createTime);
            rentalPrecept.setModifyTime(LocalDateTime.now());
            rentalPrecept.setProjectName(StringUtils.join(preceptTO.getProjectName(), ","));
            super.update(rentalPrecept);
            return BeanTransform.copyProperties(rentalPrecept, RentalPreceptBO.class);
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removePecept(String id) throws SerException {
        checkAddIdentity();
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO businessAudit(RentalPreceptTO preceptTO) throws SerException {
        checkBusinessAuditIdentity();
        RentalPrecept precept = super.findById(preceptTO.getId());
        BeanTransform.copyProperties(preceptTO, precept, true, "projectName");

        precept.setCommerceRemark(preceptTO.getCommerceRemark());
        super.update(precept);
        return BeanTransform.copyProperties(precept, RentalPreceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO financeAudit(RentalPreceptTO preceptTO) throws SerException {
        checkFinanceAuditIdentity();
        RentalPrecept precept = super.findById(preceptTO.getId());
        BeanTransform.copyProperties(preceptTO, precept, true, "projectName");
        precept.setMoneyOn(preceptTO.getMoneyOn());
        precept.setOperatingRemark(preceptTO.getOperatingRemark());
        super.update(precept);
        return BeanTransform.copyProperties(precept, RentalPreceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO resourceAudit(RentalPreceptTO preceptTO) throws SerException {
        checkResourceAuditIdentity();
        RentalPrecept precept = super.findById(preceptTO.getId());
        BeanTransform.copyProperties(preceptTO, precept, true, "projectName");
        precept.setComprehensiveRemark(preceptTO.getComprehensiveRemark());
        super.update(precept);
        return BeanTransform.copyProperties(precept, RentalPreceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO manageAudit(RentalPreceptTO preceptTO) throws SerException {
        checkManagerAuditIdentity();
        UserBO userBO = userAPI.currentUser();
        RentalPrecept precept = super.findById(preceptTO.getId());
        BeanTransform.copyProperties(preceptTO, precept, true, "projectName");
        precept.setManage(userBO.getUsername());
        precept.setManageOpinion(preceptTO.getManageOpinion());
        precept.setManagePass(preceptTO.getManagePass());
        if ("是".equals(preceptTO.getManagePass())) {
            precept.setManagePass(String.valueOf(PassStatus.MANAGEPASS));
        } else if ("否".equals(preceptTO.getManagePass())) {
            precept.setManagePass(String.valueOf(PassStatus.MANAGENOPASS));
        }
        super.update(precept);
        RentalPreceptBO bo = BeanTransform.copyProperties(precept, RentalPreceptBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalPreceptBO generalAudit(RentalPreceptTO preceptTO) throws SerException {
        checkGeneralAuditIdentity();
        RentalPrecept rentalPrecept = super.findById(preceptTO.getId());
        BeanTransform.copyProperties(preceptTO, rentalPrecept, true, "projectName");
        if (rentalPrecept.getPassStatus().getCode() == 0) {
            throw new SerException("总经办审核失败,项目经理还未审核");
        }
        UserBO userBO = userAPI.currentUser();
        rentalPrecept.setGeneral(userBO.getUsername());
        rentalPrecept.setGeneralOpinion(preceptTO.getGeneralOpinion());
        rentalPrecept.setGeneralPass(preceptTO.getGeneralPass());
        if ("是".equals(preceptTO.getGeneralPass())) {
            rentalPrecept.setGeneralPass(String.valueOf(PassStatus.GENERALPASS));
        } else if ("否".equals(preceptTO.getGeneralPass())) {
            rentalPrecept.setGeneralPass(String.valueOf(PassStatus.GENERALNOPASS));
        }
        super.update(rentalPrecept);
        RentalPreceptBO bo = BeanTransform.copyProperties(rentalPrecept, RentalPreceptBO.class);
        return bo;
    }

    private static final String PRO_NUMBER = "ZFFA-"; // 员工编号格式
    private static final Integer PRO_NUMBER_LENGTH = 7; // 员工编号长度
    private static final String START_NUMBER = "0"; // 编号开始
    private static final String ZERO_NUMBER = "000000"; // 员工编号0位数
//    private static String[] ss = DateUtil.dateToString(LocalDate.now()).split("-");

    private static String DATE = LocalDate.now().toString().replaceAll("-", "") + "-";

    /**
     * 生成下一个编号
     *
     * @param num 最大员工编号
     */
    public static synchronized String generateNum(String num) throws SerException {
        if (StringUtils.isNotBlank(num)) {
            Integer number = Integer.parseInt(StringUtils.substringAfterLast(num, "-")) + 1;
            Integer length = PRO_NUMBER_LENGTH - (String.valueOf(number).length());
            if (length > 0) {
                num = PRO_NUMBER + DATE + ZERO_NUMBER.substring(0, length - PRO_NUMBER.length());
            } else if (0 == length) {
                num = PRO_NUMBER + number;
            } else {
                throw new SerException("员工编号超出长度:" + length);
            }
            return num + number;
        } else {
            return generateNum(PRO_NUMBER + DATE + START_NUMBER); //假如为空,则从第一个开始ZFFA--01
        }

    }

    @Override
    public List<String> getNum() throws SerException {
        String[] fields = new String[]{"rentNum"};
        List<RentalPreceptBO> rentalPreceptBOS = super.findBySql("select rentNum from accommodation_rentalprecept group by rentNum order by rentNum asc ", RentalPreceptBO.class, fields);

        List<String> rentNumList = rentalPreceptBOS.stream().map(RentalPreceptBO::getRentNum)
                .filter(rentNum -> (rentNum != null || !"".equals(rentNum.trim()))).distinct().collect(Collectors.toList());


        return rentNumList;
    }

    @Override
    public RentalPreceptBO getRent(String rentNum) throws SerException {
        RentalPrecept rentalPrecept = new RentalPrecept();
        if (StringUtils.isNotBlank(rentNum)) {
            RentalPreceptDTO dto = new RentalPreceptDTO();
            dto.getConditions().add(Restrict.eq("rentNum", rentNum));
            rentalPrecept = super.findOne(dto);
        }
        RentalPreceptBO bo = BeanTransform.copyProperties(rentalPrecept, RentalPreceptBO.class);
        return bo;
    }

    @Override
    public List<UserBO> getUser() throws SerException {
        List<UserBO> userBOS = new ArrayList<>();
        String token=RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            userBOS = positionDetailUserAPI.findUserListInOrgan();
        }
        return userBOS;
    }


}
