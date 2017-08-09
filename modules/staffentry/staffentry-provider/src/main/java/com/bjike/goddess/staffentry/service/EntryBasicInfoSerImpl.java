package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.staffentry.bo.FindNameBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import com.bjike.goddess.staffentry.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 入职基本信息业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 11:57]
 * @Description: [入职基本信息业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "entryBasicInfoSerCache")
@Service
public class EntryBasicInfoSerImpl extends ServiceImpl<EntryBasicInfo, EntryBasicInfoDTO> implements EntryBasicInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private EntryRegisterSer entryRegisterSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MessageAPI messageAPI;


    /**
     * 检测部门
     *
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkDepartIdentity(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = true;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(idFlag);
//            if( !flag){
//                throw new SerException("你不是相应部门的人员，不能进行操作");
//            }
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkDepartIdentity("3");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = checkDepartIdentity("6");
        RpcTransmit.transmitUserToken(userToken);
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
                flag = checkDepartIdentity("3");
                break;
            case ADD:
                flag = checkDepartIdentity("6");
                break;
            case EDIT:
                flag = checkDepartIdentity("6");
                break;
            case DELETE:
                flag = checkDepartIdentity("6");
                break;
            case COLLECT:
                flag = checkDepartIdentity("3");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        Long count = super.count(entryBasicInfoDTO);
        return count;
    }

    @Override
    public List<EntryBasicInfoBO> listEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        List<EntryBasicInfo> entryBasicInfos = super.findByPage(entryBasicInfoDTO);
        List<EntryBasicInfoBO> boList = BeanTransform.copyProperties(entryBasicInfos, EntryBasicInfoBO.class);
        return boList;
    }


    @Override
    public EntryBasicInfoBO getEntryBasicInfo(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        EntryBasicInfo entryBasicInfo = super.findById(id);
        EntryBasicInfoBO bo = BeanTransform.copyProperties(entryBasicInfo, EntryBasicInfoBO.class);
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EntryBasicInfoBO insertEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        EntryBasicInfo entryBasicInfo = BeanTransform.copyProperties(entryBasicInfoTO, EntryBasicInfo.class, true);
        try {
            entryBasicInfo.setCreateTime(LocalDateTime.now());
            super.save(entryBasicInfo);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
        return BeanTransform.copyProperties(entryBasicInfo, EntryBasicInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EntryBasicInfoBO editEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {

        if (StringUtils.isBlank(entryBasicInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        EntryBasicInfo temp = super.findById(entryBasicInfoTO.getId());
        EntryBasicInfo entryBasicInfo = BeanTransform.copyProperties(entryBasicInfoTO, EntryBasicInfo.class, true);
        try {
            BeanUtils.copyProperties(entryBasicInfo, temp, "createTime");
            temp.setModifyTime(LocalDateTime.now());
            super.update(temp);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        return BeanTransform.copyProperties(temp, EntryBasicInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeEntryBasicInfo(String id) throws SerException {

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EntryBasicInfoBO sendEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        EntryBasicInfo entryBasicInfo = new EntryBasicInfo();
        if (entryBasicInfoTO != null) {
            if (StringUtils.isNotBlank(entryBasicInfoTO.getId()) && null != entryBasicInfoTO.getEmails() && entryBasicInfoTO.getEmails().length > 0) {
                for (String email : entryBasicInfoTO.getEmails()) {
                    if (!Validator.isEmail(email)) {
                        throw new SerException("邮箱书写不正确");
                    }
                }
                entryBasicInfo = super.findById(entryBasicInfoTO.getId());

                StringBuffer content = new StringBuffer("");
                content.append(" 员工编号：" + entryBasicInfoTO.getEmployeeID())
                        .append(" 姓名:" + entryBasicInfoTO.getName())
                        .append(" 专业:" + entryBasicInfoTO.getProfession())
                        .append(" 联系电话:" + entryBasicInfoTO.getPhone())
                        .append(" 邮箱账号:" + entryBasicInfoTO.getEmail())
                        .append(" 入职时间:" + entryBasicInfoTO.getEntryTime())
                        .append(" 入职地区:" + entryBasicInfoTO.getArea())
                        .append(" 入职部门:" + entryBasicInfoTO.getDepartment())
                        .append(" 入职项目组:" + entryBasicInfoTO.getProjectGroup())
                        .append(" 入职岗位:" + entryBasicInfoTO.getPosition())
                ;

                //发邮件
                String messageContent = "培训信息通知邮件内容: " + content.toString();
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(messageContent);
                messageTO.setTitle("入职通告");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(entryBasicInfoTO.getEmails());
                messageAPI.send(messageTO);


                /**
                 * 修改邮件发送对象
                 */
                EntryBasicInfo entryBasicInfo1 = super.findById(entryBasicInfoTO.getId());
                entryBasicInfo1.setModifyTime(LocalDateTime.now());
                entryBasicInfo1.setEmailInfo(true);
                try {
                    super.update(entryBasicInfo1);
                } catch (SerException e) {
                    throw new SerException(e.getMessage());
                }
            }
        }
        return BeanTransform.copyProperties(entryBasicInfo, EntryBasicInfoBO.class);
    }


    @Override
    public List<EntryBasicInfoBO> collectEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        //多选职位
        if (entryBasicInfoDTO == null) {
            throw new SerException("您好!查询条件为空,无法进行查询!");
        }

        if ((entryBasicInfoDTO.getPostNames() == null) || (entryBasicInfoDTO.getPostNames().length == 0)) {
            throw new SerException("您好!职位列表为空,无法进行查询!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] positions = entryBasicInfoDTO.getPostNames();              //职位列表
        StringBuffer sb = new StringBuffer("");
        for (String str : positions) {
            sb.append("'" + str + "',");
        }
        String startDateString = entryBasicInfoDTO.getStartDate();          //开始日期字符串
        String endDateString = entryBasicInfoDTO.getEndDate();              //结束日期字符串
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        if (StringUtils.isNotBlank(startDateString)) {
            startDate = LocalDate.parse(startDateString, formatter);
        }
        if (StringUtils.isNotBlank(endDateString)) {
            endDate = LocalDate.parse(endDateString, formatter);
        }

        String[] field = new String[]{"position", "entryCount"};
        String sql = "select position ,  count(employeeID) as entryCount from staffentry_entrybasicinfo where 1=1 ";
        if (positions != null && positions.length > 0) {
            sql = sql + " and position in (" + StringUtils.substringBeforeLast(sb.toString(), ",") + ")";
        }
        sql = sql + " group by position order by position desc ";
        List<EntryBasicInfoBO> list = super.findBySql(sql, EntryBasicInfoBO.class, field);
        for (EntryBasicInfoBO entryBasicInfoBO : list) {
            entryBasicInfoBO.setEntryTime(startDate + "~" + endDate);
        }


        return list;
    }

    @Override
    public List<String> listPost() throws SerException {
        List<String> post = new ArrayList<>();
        String[] fiels = new String[]{"position"};
        String sql = "select position from staffentry_entrybasicinfo group by  position ";
        List<EntryBasicInfoBO> list = super.findBySql(sql, EntryBasicInfoBO.class, fiels);
        post = list.stream().filter(str -> StringUtils.isNotBlank(str.getPosition())).map(EntryBasicInfoBO::getPosition).collect(Collectors.toList());
        return post;
    }

    @Override
    public List<EntryBasicInfoBO> getEntryBasicInfoByName(String name) throws SerException {
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        List<EntryBasicInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, EntryBasicInfoBO.class);
    }

    @Override
    public List<EntryBasicInfoVO> getByEmpNumber(String empNumber) throws SerException {
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("employeeID", empNumber));
        List<EntryBasicInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, EntryBasicInfoBO.class);
    }

    @Override
    public List<FindNameBO> findName() throws SerException {
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        List<EntryBasicInfoBO> entryBasicInfoBOs = listEntryBasicInfo(dto);
        List<FindNameBO> findNameBOs = new ArrayList<>();
        if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
            for (EntryBasicInfoBO bo : entryBasicInfoBOs) {
                FindNameBO findNameBO = new FindNameBO();
                findNameBO.setUserId(bo.getId());
                findNameBO.setName(bo.getName());
                findNameBOs.add(findNameBO);
            }
        }
        return findNameBOs;
    }

    public List<EntryOptionBO> getEntryOptionByNameAndEmpNum(String name, String empNumer) throws SerException {
        EntryOptionBO entryOptionBO = new EntryOptionBO();

        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        dto.getConditions().add(Restrict.eq("employeeID", empNumer));
        List<EntryBasicInfo> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            entryOptionBO.setEntryTime(list.get(0).getEntryTime().toString());
        } else {
            entryOptionBO.setEntryTime("");
        }
        EntryRegisterDTO registerDTO = new EntryRegisterDTO();
        registerDTO.getConditions().add(Restrict.eq("username", name));
        registerDTO.getConditions().add(Restrict.eq("empNumber", empNumer));
        List<EntryRegister> registerList = entryRegisterSer.findByCis(registerDTO);
        if (registerList != null && registerList.size() > 0) {
            entryOptionBO.setProfession(registerList.get(0).getProfession());

            entryOptionBO.setEducation(registerList.get(0).getEducation());
        } else {
            entryOptionBO.setProfession("");
            entryOptionBO.setEducation("");
        }


        entryOptionBO.setName(name);
        entryOptionBO.setEmployeeID(empNumer);
        List<EntryOptionBO> entryOptionBOList = new ArrayList<>();
        entryOptionBOList.add(entryOptionBO);
        return entryOptionBOList;
    }
}
