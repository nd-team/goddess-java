package com.bjike.goddess.commerce.service;

import com.bjike.goddess.commerce.bo.CommerceConferenceBO;
import com.bjike.goddess.commerce.dto.CommerceConferenceDTO;
import com.bjike.goddess.commerce.entity.CommerceConference;
import com.bjike.goddess.commerce.enums.GuideAddrStatus;
import com.bjike.goddess.commerce.excel.CommerceConferenceExcel;
import com.bjike.goddess.commerce.to.CollectTO;
import com.bjike.goddess.commerce.to.CommerceConferenceExcelTO;
import com.bjike.goddess.commerce.to.CommerceConferenceTO;
import com.bjike.goddess.commerce.to.GuidePermissionTO;
import com.bjike.goddess.commerce.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务会议业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-02 10:36 ]
 * @Description: [ 商务会议业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "commerceSerCache")
@Service
public class CommerceConferenceSerImpl extends ServiceImpl<CommerceConference, CommerceConferenceDTO> implements CommerceConferenceSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    private static final String manage = "conference-manage";

    private static final String check = "conference-check";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public CommerceConferenceBO save(CommerceConferenceTO to) throws SerException {
        if (!cusPermissionSer.getCusPermission(manage))
            throw new SerException("您的帐号没有权限");
        CommerceConference entity = BeanTransform.copyProperties(to, CommerceConference.class, true);
        entity.setType("商务会议");
        LocalDateTime[] times = {entity.getConferenceTime().withHour(0).withMinute(0).withSecond(0),
                entity.getConferenceTime().withSecond(59).withMinute(59).withHour(23)};
        List<CommerceConference> list = this.findTime(times);
        if (entity.getConferenceTime().getMonthValue() >= 10 && entity.getConferenceTime().getDayOfMonth() >= 10)
            entity.setSerialNumber(String.format("SW-DW-%d%d%d-%d", entity.getConferenceTime().getYear(),
                    entity.getConferenceTime().getMonthValue(), entity.getConferenceTime().getDayOfMonth(), list.size() + 1));
        else if (entity.getConferenceTime().getMonthValue() < 10 && entity.getConferenceTime().getDayOfMonth() >= 10)
            entity.setSerialNumber(String.format("SW-DW-%d0%d%d-%d", entity.getConferenceTime().getYear(),
                    entity.getConferenceTime().getMonthValue(), entity.getConferenceTime().getDayOfMonth(), list.size() + 1));
        else
            entity.setSerialNumber(String.format("SW-DW-%d0%d0%d-%d", entity.getConferenceTime().getYear(),
                    entity.getConferenceTime().getMonthValue(), entity.getConferenceTime().getDayOfMonth(), list.size() + 1));
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CommerceConferenceBO.class);
    }

    @Override
    public CommerceConferenceBO update(CommerceConferenceTO to) throws SerException {
        if (!cusPermissionSer.getCusPermission(manage))
            throw new SerException("您的帐号没有权限");
        CommerceConference entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommerceConferenceBO.class);
    }

    @Override
    public CommerceConferenceBO congeal(String id) throws SerException {
        if (!cusPermissionSer.getCusPermission(manage))
            throw new SerException("您的帐号没有权限");
        CommerceConference entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CommerceConferenceBO.class);
    }

    @Override
    public CommerceConferenceBO getById(String id) throws SerException {
        if (!cusPermissionSer.getCusPermission(check))
            throw new SerException("您的帐号没有权限");
        CommerceConference entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, CommerceConferenceBO.class);
    }

    @Override
    public List<CommerceConferenceBO> maps(CommerceConferenceDTO dto) throws SerException {
        if (!cusPermissionSer.getCusPermission(check))
            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("status=asc");
        dto.getSorts().add("conferenceTime=desc");
        List<CommerceConference> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, CommerceConferenceBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        if (!cusPermissionSer.getCusPermission(check))
            throw new SerException("您的帐号没有权限");
        CommerceConferenceDTO dto = new CommerceConferenceDTO();
        return super.count(dto);
    }

    @Override
    public void upload(List<CommerceConferenceExcelTO> list) throws SerException {
        if (!cusPermissionSer.getCusPermission(manage))
            throw new SerException("您的帐号没有权限");
        for (int i = 1; i <= list.size(); i++) {
            CommerceConferenceExcelTO to = list.get(i - 1);
            if (null != this.findByNumber(to.getSerialNumber()))
                throw new SerException("第" + i + "行的编号已存在");
        }
        List<CommerceConference> commerceConferenceList = BeanTransform.copyProperties(list, CommerceConference.class, true);
        super.save(commerceConferenceList);
    }

    @Override
    public byte[] exportExcel(CollectTO to) throws SerException {
        if (!cusPermissionSer.getCusPermission(manage))
            throw new SerException("您的帐号没有权限");
        List<CommerceConferenceBO> list = this.findByTime(to);
        if (null == list)
            throw new SerException("没有查询到数据");
        List<CommerceConferenceExcelTO> toList = new ArrayList<>(0);
        for (CommerceConferenceBO bo : list) {
            CommerceConferenceExcelTO excelTO = new CommerceConferenceExcelTO();
            BeanUtils.copyProperties(bo, excelTO);
            toList.add(excelTO);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    private List<CommerceConference> findTime(LocalDateTime[] times) throws SerException {
        CommerceConferenceDTO dto = new CommerceConferenceDTO();
        dto.getConditions().add(Restrict.between("conferenceTime", times));
        dto.getSorts().add("conferenceTime=desc");
        return super.findByCis(dto);
    }

    @Override
    public List<CommerceConferenceBO> findByTime(CollectTO to) throws SerException {
        if (StringUtils.isBlank(to.getEnd()) || StringUtils.isBlank(to.getStart()))
            return null;
        LocalDateTime[] times = {LocalDateTime.parse(to.getStart(), FORMATTER), LocalDateTime.parse(to.getEnd(), FORMATTER)};
        return BeanTransform.copyProperties(this.findTime(times), CommerceConferenceBO.class);
    }

    @Override
    public CommerceConferenceBO findByNumber(String serialNumber) throws SerException {
        CommerceConferenceDTO dto = new CommerceConferenceDTO();
        dto.getConditions().add(Restrict.eq("serialNumber", serialNumber));
        CommerceConference entity = super.findOne(dto);
        if (null == entity)
            return null;
        else
            return BeanTransform.copyProperties(entity, CommerceConferenceBO.class);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = cusPermissionSer.getCusPermission(check);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = cusPermissionSer.getCusPermission(manage);

        SonPermissionObject obj = new SonPermissionObject();

        obj.setName("commerceconference");
        obj.setDescribesion("商务会议");
        if (flagSeeSign || flagAddSign) {
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CommerceConferenceBO importExcel(List<CommerceConferenceTO> tocs) throws SerException {

        List<CommerceConference> commerceConference = BeanTransform.copyProperties(tocs, CommerceConference.class, true);
        commerceConference.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(commerceConference);

        CommerceConferenceBO commerceConferenceBO = BeanTransform.copyProperties(new CommerceConference(), CommerceConferenceBO.class);
        return commerceConferenceBO;
    }

    @Override
    public byte[] templateExport() throws SerException {
//        getCusPermission();

            List<CommerceConferenceExcel> siginManageExports = new ArrayList<>();

            CommerceConferenceExcel excel = new CommerceConferenceExcel();
            excel.setType("移动通信类");
            excel.setSerialNumber( "test" );
            excel.setConferenceTime(LocalDateTime.now());
            excel.setWay("test");
            excel.setArea("test");
            excel.setPersonnel( "test");
            excel.setQuantity(10);
            excel.setOrganization("fdsf");
            excel.setEmcee("fds");
            excel.setRecorder( "fdsf" );
            excel.setContent("已签fds订" );
            excel.setBulletin( "框架fds合同");
            excel.setConsult( "已aa立项");
            excel.setNegotiation("test");
            excel.setCooperation( "tesdft");
            excel.setRemark("tfest");
            siginManageExports.add( excel );

            Excel exce = new Excel(0, 2);
            byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, exce);
            return bytes;
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
//            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
}