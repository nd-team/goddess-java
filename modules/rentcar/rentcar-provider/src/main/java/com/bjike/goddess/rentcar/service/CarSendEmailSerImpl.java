package com.bjike.goddess.rentcar.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.contacts.entity.InternalContacts;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.rentcar.bo.CarSendEmailBO;
import com.bjike.goddess.rentcar.bo.DriverInfoBO;
import com.bjike.goddess.rentcar.dto.CarSendEmailDTO;
import com.bjike.goddess.rentcar.dto.DriverInfoDTO;
import com.bjike.goddess.rentcar.entity.CarSendEmail;
import com.bjike.goddess.rentcar.enums.GuideAddrStatus;
import com.bjike.goddess.rentcar.to.CarSendEmailTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送邮件业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-25 09:50 ]
 * @Description: [ 发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentcarSerCache")
@Service
public class CarSendEmailSerImpl extends ServiceImpl<CarSendEmail, CarSendEmailDTO> implements CarSendEmailSer {
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

    @Autowired
    private MessageAPI messageAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private DriverInfoSer driverInfoSer;

    private Logger logger = Logger.getLogger(CarSendEmailSerImpl.class);

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
            flag = cusPermissionSer.busCusPermission("1");
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

    // 定时器规则 "0 0 10 * * ?" 每月每日上午10:00发送邮件
    @Override
    public void sendEmailRemind() throws SerException {

        DriverInfoDTO driverInfoDTO = new DriverInfoDTO();
        List<DriverInfoBO> driverInfos = driverInfoSer.pageList(driverInfoDTO);
        for (DriverInfoBO driverInfoBO : driverInfos) {
            LocalDate startTime = DateUtil.parseDate(driverInfoBO.getEndDate());
            LocalDate endTime = LocalDate.now();
            Long interval = ChronoUnit.DAYS.between(endTime, startTime);
            List<String> receivers = new ArrayList<>();
            //合同终止日期前15天发送邮件提醒福利模块负责人
            if (interval <= 15) {
                logger.info("发送邮件开始");
                Boolean b = moduleAPI.isCheck("organize");
                if (b) {
                    logger.info("发送邮件开始1"+b);
                    PositionDetailBO positionDetailBO = positionDetailAPI.findByPosition("福利模块负责人");
                    logger.info("发送邮件开始2"+ JSON.toJSONString(positionDetailBO));
                    List<UserBO> userBOS = positionDetailUserAPI.findByPosition(positionDetailBO.getId());
                    logger.info("发送邮件开始3"+ JSON.toJSONString(userBOS));
                    if (userBOS != null && userBOS.size() > 0) {
                        InternalContactsBO internalContactsBO = internalContactsAPI.findByUser(userBOS.get(0).getId());
                        logger.info("发送邮件开始4"+ JSON.toJSONString(internalContactsBO));
                        String email = internalContactsBO.getWorkEmail();
                        receivers.add(email);
                    }
                } else {
                    throw new SerException("请去模块关联管理添加组织结构的模块关联");
                }
                logger.info("发送邮件开始5"+ JSON.toJSONString(receivers));
                String[] sendUsers = (String[]) receivers.toArray(new String[receivers.size()]);
//                String[] sendUsers = new String[]{"tangshuhua_aj@163.com"};
                MessageTO messageTO = new MessageTO("合同终止提醒", driverInfoBO.getDriver() + "司机,合同终止日期（" + startTime.getYear() + "年" + startTime.getMonth().getValue() + "月" + startTime.getDayOfMonth() + "日）,快到期了，请跟进后续工作");
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setMsgType(MsgType.SYS);//根据自己业务写
                messageTO.setSendType(SendType.EMAIL);//根据自己业务写
                messageTO.setRangeType(RangeType.SPECIFIED);//根据自己业务写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");
                messageTO.setReceivers(sendUsers);
                messageAPI.send(messageTO);
            }
        }

    }

    // 定时器规则 "0 0 10 15 * ?" 每月15日上午10:00发送邮件
    //目前需求不需要 2017-10-21日
    @Override
    public void sendEmail() throws SerException {
        List<CarSendEmail> carSendEmails = super.findAll();
        List<String> receivers = new ArrayList<>();
        if (carSendEmails.size() > 0 && carSendEmails != null) {
            for (CarSendEmail carSendEmail : carSendEmails) {
                List<UserBO> userBO = positionDetailUserAPI.findByPosition(carSendEmail.getPositionNameId());
                if (userBO.size() > 0 && userBO != null) {
                    List<User> users = BeanTransform.copyProperties(userBO, User.class, true);
                    for (User user : users) {
                        receivers.add(user.getEmail());
                    }
                }
            }
            String[] sendUsers = (String[]) receivers.toArray(new String[receivers.size()]);
            MessageTO messageTO = new MessageTO("司机新增提醒", "是否要新增招聘司机");
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setMsgType(MsgType.SYS);//根据自己业务写
            messageTO.setSendType(SendType.EMAIL);//根据自己业务写
            messageTO.setRangeType(RangeType.SPECIFIED);//根据自己业务写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");
            messageTO.setReceivers(sendUsers);
//            本地测试方法，记得要开message模块的provider和consumer方法
//            String [] sendU = new String[1];
//            sendU[0] = "jiangzaixuan_aj@163.com";
//            messageTO.setReceivers(sendU);
            messageAPI.send(messageTO);
        }

    }

    @Override
    public List<DepartmentDetailBO> findDepartMent() throws SerException {
        List<DepartmentDetailBO> departmentBOS = departmentDetailAPI.findStatus();
        return departmentBOS;
    }

    @Override
    public List<PositionDetailBO> findPosition(String id) throws SerException {
        List<PositionDetailBO> positionBOS = positionDetailAPI.findByDepartment(id);
        return positionBOS;
    }

    @Override
    public CarSendEmailBO add(CarSendEmailTO to) throws SerException {
        CarSendEmail carSendEmail = BeanTransform.copyProperties(to, CarSendEmail.class, true);
        super.save(carSendEmail);
        DepartmentDetailBO detailB = departmentDetailAPI.findBOById(to.getProjectManageId());
        PositionDetailBO positionDetailBO = positionDetailAPI.findBOById(to.getPositionNameId());
        CarSendEmailBO carSendEmailBO = BeanTransform.copyProperties(to, CarSendEmailBO.class);
        carSendEmailBO.setPositionName(detailB.getDepartment());
        carSendEmailBO.setProjectName(positionDetailBO.getPosition());
        return carSendEmailBO;
    }

    @Override
    public List<CarSendEmailBO> list() throws SerException {
        List<CarSendEmail> carSendEmailList = super.findAll();
        List<CarSendEmailBO> carSendEmailBOS = new ArrayList<>();
        if (carSendEmailList != null && !carSendEmailList.isEmpty()) {
            for (CarSendEmail carSendEmail : carSendEmailList) {
                CarSendEmailBO carSendEmailBO = new CarSendEmailBO();
                DepartmentDetailBO detailBO = departmentDetailAPI.findBOById(carSendEmail.getProjectManageId());
                PositionDetailBO positionDetailBO = positionDetailAPI.findBOById(carSendEmail.getPositionNameId());
                if (null != detailBO) {
                    carSendEmailBO.setProjectName(detailBO.getDepartment());
                }
                if (null != positionDetailBO) {
                    carSendEmailBO.setPositionName(positionDetailBO.getPosition());
                }
                carSendEmailBO.setPositionNameId(carSendEmail.getPositionNameId());
                carSendEmailBO.setProjectManageId(carSendEmail.getProjectManageId());
                carSendEmailBO.setRemark(carSendEmail.getRemark());
                carSendEmailBO.setId(carSendEmail.getId());
                carSendEmailBOS.add(carSendEmailBO);
            }
        }
        return carSendEmailBOS;
    }

    @Override
    public CarSendEmailBO edit(CarSendEmailTO to) throws SerException {
        CarSendEmail model = super.findById(to.getId());
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setPositionNameId(to.getPositionNameId());
            model.setProjectManageId(to.getProjectManageId());
            model.setRemark(to.getRemark());
            super.update(model);
            return BeanTransform.copyProperties(model, CarSendEmailBO.class);
        } else {
            throw new SerException("非法Id,发送对象不能为空！");
        }
    }

    @Override
    public CarSendEmailBO findOne(String id) throws SerException {
        CarSendEmail carSendEmail = super.findById(id);
        DepartmentDetailBO departmentDetail = departmentDetailAPI.findBOById(carSendEmail.getProjectManageId());
        PositionDetailBO positionDetailBO = positionDetailAPI.findBOById(carSendEmail.getPositionNameId());
        CarSendEmailBO bo = BeanTransform.copyProperties(carSendEmail, CarSendEmailBO.class);
        bo.setPositionName(positionDetailBO.getPosition());
        bo.setProjectName(departmentDetail.getDepartment());
        return bo;
    }

    @Override
    public void delete(String id) throws SerException {
        if (id != null) {
            CarSendEmail model = super.findById(id);
            if (model != null) {
                super.remove(model);
            } else {
                throw new SerException("传入的id有误");
            }
        } else {
            throw new SerException("id不能为空");
        }
    }
}