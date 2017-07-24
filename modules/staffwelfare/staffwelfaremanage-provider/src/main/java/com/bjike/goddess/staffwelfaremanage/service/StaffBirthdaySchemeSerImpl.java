package com.bjike.goddess.staffwelfaremanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffwelfaremanage.bo.StaffBirthdaySchemeBO;
import com.bjike.goddess.staffwelfaremanage.dto.StaffBirthdaySchemeDTO;
import com.bjike.goddess.staffwelfaremanage.entity.StaffBirthDayWelfare;
import com.bjike.goddess.staffwelfaremanage.entity.StaffBirthdayScheme;
import com.bjike.goddess.staffwelfaremanage.enums.GuideAddrStatus;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.StaffBirthdaySchemeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工生日福利方案业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffwelfaremanageSerCache")
@Service
public class StaffBirthdaySchemeSerImpl extends ServiceImpl<StaffBirthdayScheme, StaffBirthdaySchemeDTO> implements StaffBirthdaySchemeSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private StaffBirthDayWelfareSer staffBirthDayWelfareSer;

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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public StaffBirthdaySchemeBO insertModel(StaffBirthdaySchemeTO to) throws SerException {
        checkAddIdentity();
        StaffBirthdayScheme model = BeanTransform.copyProperties(to, StaffBirthdayScheme.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, StaffBirthdaySchemeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public StaffBirthdaySchemeBO updateModel(StaffBirthdaySchemeTO to) throws SerException {
        checkAddIdentity();
        if (!StringUtils.isEmpty(to.getId())) {
            StaffBirthdayScheme model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, StaffBirthdaySchemeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<StaffBirthdaySchemeBO> pageList(StaffBirthdaySchemeDTO dto) throws SerException {
        checkAddIdentity();
        dto.getSorts().add("createTime=desc");
        List<StaffBirthdayScheme> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, StaffBirthdaySchemeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<StaffBirthdaySchemeBO> collect(StaffBirthdaySchemeDTO dto) throws SerException {
        checkAddIdentity();
        dto.getSorts().add("createTime=desc");
        if (dto.getSchemeTime() != null) {
            //字符串转换日期
            LocalDateTime dateTime = DateUtil.parseDateTime(dto.getSchemeTime());
            dto.getConditions().add(Restrict.eq("schemeTime", dateTime));
        }
        List<StaffBirthdayScheme> list = super.findByPage(dto);
        if (list != null && !list.isEmpty()) {
            Integer totalStaffAmount = list.stream().filter(p -> p.getStaffAmount() != null).mapToInt(p -> p.getStaffAmount()).sum();
            Double totalFee = list.stream().filter(p -> p.getWelfareFeeDetial() != null).mapToDouble(p -> p.getWelfareTotalFee()).sum();

            StaffBirthdayScheme total = new StaffBirthdayScheme(null, "合计", null, totalStaffAmount, null, null, null, null, null, null, totalFee, null, null, null, null,
                    null, null, null);
            list.add(total);
        }

        return BeanTransform.copyProperties(list, StaffBirthdaySchemeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pass(String id) throws SerException {
        checkAddIdentity();
        StaffBirthdayScheme model = super.findById(id);
        if (model != null) {
            UserBO userBO = userAPI.currentUser();
            if (userBO != null) {
                UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
                if (userDetailBO != null) {
                    if (userDetailBO.getPositionName().equals("总经办")) {
                        model.setGeneralManageUser(userBO.getUsername());
                        model.setGeneralManageSug("通过");
                    } else if (userDetailBO.getPositionName().equals("项目经理")) {
                        model.setGeneralManageUser(userBO.getUsername());
                        model.setGeneralManageSug("通过");
                    } else if (userDetailBO.getDepartmentName().equals("运营商务部")) {
                        model.setGeneralManageUser(userBO.getUsername());
                        model.setGeneralManageSug("通过");
                    } else {
                        throw new SerException("当前用户不具备通过权限!");
                    }
                    super.update(model);
                } else {
                    throw new SerException("当前用户异常!");
                }
            }
        } else {
            throw new SerException("更新对象不能为空!");
        }

    }

    //生日员工领取福利，并生成员工生日福利记录
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void receive(String id, String remark) throws SerException {
        StaffBirthdayScheme model = super.findById(id);
        if (model != null) {
            if (model.getReceive()) {
                throw new SerException("亲，您已经领取过了!");
            }
            UserBO userBO = userAPI.currentUser();
            if (userBO != null) {

                UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
                if (StringUtils.isEmpty(userDetailBO.getBirthday())) {
                    throw new SerException("当前用户员工生日数据未填写,请先完善用户数据");
                }
                LocalDate birthday = DateUtil.parseDate(userDetailBO.getBirthday());
                Integer birthMonth = birthday.getMonthValue();
                Integer schemeMonth = model.getSchemeTime().getMonthValue();
                if (birthMonth == schemeMonth) {
                    //设置已经领取
                    model.setReceive(Boolean.TRUE);
                    super.update(model);
                    //添加员工生日福利记录
                    StaffBirthDayWelfare staffBirthDayWelfare = new StaffBirthDayWelfare();
                    staffBirthDayWelfare.setBirthday(userDetailBO.getBirthday());
                    staffBirthDayWelfare.setName(userBO.getUsername());
                    staffBirthDayWelfare.setUserId(userBO.getId());
                    // TODO: 17-4-7 地区尚未确定
                    staffBirthDayWelfare.setArea(userDetailBO.getAddress());
                    staffBirthDayWelfare.setProjectGroup(userDetailBO.getGroupName());
                    staffBirthDayWelfare.setSendTime(model.getSendTime());
                    staffBirthDayWelfare.setSendDetail(model.getWelfareDetail());
                    staffBirthDayWelfare.setRemark(remark);
                    staffBirthDayWelfareSer.save(staffBirthDayWelfare);
                } else {
                    throw new SerException("亲，还没到您的生日哦!");
                }
            } else {
                throw new SerException("登录超时,请重新登录!");
            }
        } else {
            throw new SerException("方案对象不能为空!");
        }
    }


}