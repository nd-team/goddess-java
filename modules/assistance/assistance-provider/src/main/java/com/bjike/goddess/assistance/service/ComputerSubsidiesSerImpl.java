package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.ComputerSubsidiesBO;
import com.bjike.goddess.assistance.dto.ComputerSubsidiesDTO;
import com.bjike.goddess.assistance.entity.ComputerSubsidies;
import com.bjike.goddess.assistance.enums.GuideAddrStatus;
import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.enums.Usage;
import com.bjike.goddess.assistance.excel.ComputerSubsidiesImport;
import com.bjike.goddess.assistance.to.ComputerSubsidiesAddTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 电脑补助业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "assistanceSerCache")
@Service
public class ComputerSubsidiesSerImpl extends ServiceImpl<ComputerSubsidies, ComputerSubsidiesDTO> implements ComputerSubsidiesSer {
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对审核权限（岗位级别）
     */
    private void checkAduitIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加删除修改查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
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

    /**
     * 核对审核权限（岗位级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    private Boolean allTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAuditIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean allTrue = allTrueIdentity();
        if (flagSee || flagAdd || allTrue) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case MANAGEAUDIT:
                flag = guideAuditIdentity();
                break;
            case CONFIRM:
                flag = allTrueIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countComputer(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        searchCondition(computerSubsidiesDTO);
        Long count = super.count(computerSubsidiesDTO);
        return null;
    }

    @Override
    public ComputerSubsidiesBO getOneById(String id) throws SerException {
        ComputerSubsidies computerSubsidies = super.findById(id);
        return BeanTransform.copyProperties(computerSubsidies, ComputerSubsidiesBO.class);
    }

    @Override
    public List<ComputerSubsidiesBO> listComputer(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        checkSeeIdentity();
        searchCondition(computerSubsidiesDTO);
        computerSubsidiesDTO.getSorts().add("modifyTime=desc");
        List<ComputerSubsidies> computerSubsidies = super.findByPage(computerSubsidiesDTO);
        return BeanTransform.copyProperties(computerSubsidies, ComputerSubsidiesBO.class);
    }

    public void searchCondition(ComputerSubsidiesDTO computerSubsidiesDTO) throws SerException {
        if (StringUtils.isNotBlank(computerSubsidiesDTO.getName())) {
            computerSubsidiesDTO.getConditions().add(Restrict.eq("name", computerSubsidiesDTO.getName()));
        }
    }

    @Override
    public void saveComputer(ComputerSubsidiesAddTO computerSubsidiesAddTO) throws SerException {
        ComputerSubsidies computerSubsidies = BeanTransform.copyProperties(computerSubsidiesAddTO, ComputerSubsidies.class, true);
        computerSubsidies.setCreateTime(LocalDateTime.now());
        super.save(computerSubsidies);
    }

    @Override
    public void editComputer(ComputerSubsidiesTO computerSubsidiesTO) throws SerException {
        checkSeeIdentity();
        ComputerSubsidies computerSubsidies = super.findById(computerSubsidiesTO.getId());
        computerSubsidies.setNecklineComputer(computerSubsidiesTO.getNecklineComputer());
        computerSubsidies.setUsage(computerSubsidiesTO.getUsage());
        if (computerSubsidiesTO.getUsage() == Usage.USEMYSELFCOMPUTER) {
            computerSubsidies.setComputerAmount(100d);
            computerSubsidies.setSubsidiesStatus(SubsidiesStatus.INSUBSIDIES);
        } else {
            computerSubsidies.setComputerAmount(0d);
            computerSubsidies.setSubsidiesStatus(SubsidiesStatus.NOSUBSIDIES);
        }
        super.update(computerSubsidies);
    }


    @Override
    public void deleteTemp(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
//        checkSeeIdentity();
        List<ComputerSubsidies> list = super.findAll();
        List<ComputerSubsidiesImport> computerSubsidiesImports = new ArrayList<>();
        list.stream().forEach(str -> {
            ComputerSubsidiesImport excel = BeanTransform.copyProperties(str, ComputerSubsidiesImport.class);
            computerSubsidiesImports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(computerSubsidiesImports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ComputerSubsidiesImport> computerSubsidiesImports = new ArrayList<>();
        ComputerSubsidiesImport excel = new ComputerSubsidiesImport();
        excel.setArea("广州");
        excel.setDepartment("研发部");
        excel.setName("张三");
        excel.setEntryDate("2017-09-12");
        excel.setNecklineComputer("否");
        excel.setUsage("使用自己的电脑");
        excel.setComputerAmount(100d);
        excel.setConfirm("是");
        excel.setConfirmDate("2017-12-12");
        excel.setSubsidiesStatus("在补助");
        excel.setStaffStatus("在职");
        computerSubsidiesImports.add(excel);
        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(computerSubsidiesImports, exce);
        return bytes;
    }

    @Override
    public void importExcel(List<ComputerSubsidiesExcelTO> computerSubsidiesExcelTOS) throws SerException {
//        checkSeeIdentity();
        List<ComputerSubsidies> computerSubsidiesList = BeanTransform.copyProperties(computerSubsidiesExcelTOS, ComputerSubsidies.class, true);
        computerSubsidiesList.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(computerSubsidiesList);
    }

    @Override
    public void remindingConfirm(String id) throws SerException {
        ComputerSubsidies computerSubsidies = super.findById(id);
        String email = internalContactsAPI.getEmail(computerSubsidies.getName());
        //如果该员工邮箱存在发送邮件,否则就不发
        if (StringUtils.isNotBlank(email)) {
            StringBuffer content = new StringBuffer();
            content.append(computerSubsidies.getName() + "你好! 请确认: 你本月的电脑补助.");
            MessageTO messageTO = new MessageTO();
            messageTO.setContent(content.toString());
            messageTO.setTitle("确认提醒");
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);

            messageTO.setReceivers(new String[]{email});
            messageAPI.send(messageTO);
        }
    }

    @Override
    public void confirm(String id, Boolean confirm) throws SerException {
        ComputerSubsidies computerSubsidies = super.findById(id);
        computerSubsidies.setConfirm(confirm);
        computerSubsidies.setConfirmDate(LocalDate.now());
        computerSubsidies.setCreateTime(LocalDateTime.now());
        super.update(computerSubsidies);
    }
}