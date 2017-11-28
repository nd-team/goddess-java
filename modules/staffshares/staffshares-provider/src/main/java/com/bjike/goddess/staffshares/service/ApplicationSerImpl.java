package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assistance.api.SenioritySubsidiesAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffshares.bo.ApplicationBO;
import com.bjike.goddess.staffshares.dto.ApplicationDTO;
import com.bjike.goddess.staffshares.entity.Application;
import com.bjike.goddess.staffshares.enums.GuideAddrStatus;
import com.bjike.goddess.staffshares.excel.SonPermissionObject;
import com.bjike.goddess.staffshares.to.ApplicationTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 干股代表申请业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:27 ]
 * @Description: [ 干股代表申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class ApplicationSerImpl extends ServiceImpl<Application, ApplicationDTO> implements ApplicationSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
//    @Autowired
//    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private BuyscheduleSer buyscheduleSer;
    @Autowired
    private DetailsSer detailsSer;
    @Autowired
    private DividendsSer dividendsSer;
    @Autowired
    private PurchaseSer purchaseSer;
    @Autowired
    private SchemeSer schemeSer;
    @Autowired
    private SellscheduleSer sellscheduleSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private SenioritySubsidiesAPI senioritySubsidiesAPI;

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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("application");
        obj.setDescribesion("干股代表申请");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = buyscheduleSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("buyschedule");
        obj.setDescribesion("买入记录表");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = detailsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("details");
        obj.setDescribesion("交易详情");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = dividendsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("dividends");
        obj.setDescribesion("干股分红表");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = purchaseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("purchase");
        obj.setDescribesion("干股申购表");
        if (flagSeeBase) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase1 = schemeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("scheme");
        obj.setDescribesion("员工持股管理");
        if (flagSeeBase1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase2 = sellscheduleSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("sellschedule");
        obj.setDescribesion("出售记录表");
        if (flagSeeBase2) {
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

    @Override
    public void save(ApplicationTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        Application entity = new Application();
        entity.setShareholder(userBO.getUsername());
        entity.setTime(LocalDateTime.now());

        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId()).stream().filter(str -> str.getStatus().equals(Status.THAW)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(positionDetailBOs)) {
            throw new SerException("当前用户无数据");
        }
        entity.setArea(positionDetailBOs.get(0).getArea());
        entity.setDepartment(positionDetailBOs.get(0).getDepartmentName());
        entity.setPosition(positionDetailBOs.get(0).getPosition());
        int months = 0;
//        List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getEntryBasicInfoByName(userBO.getUsername());
//        if (!CollectionUtils.isEmpty(entryBasicInfoBOs)) {
//            months = getMonthSpace(entryBasicInfoBOs.get(0).getEntryTime(), LocalDate.now().toString());
//        }
        if (moduleAPI.isCheck("assistance")) {
            months = senioritySubsidiesAPI.findAge(userBO.getUsername()).getCompanyLength();
        }
        entity.setMonths(months);
        entity.setReason(to.getReason());
        entity.setRemark(to.getRemark());
        super.save(entity);
    }

    @Override
    public List<ApplicationBO> maps(ApplicationDTO dto) throws SerException {
//        checkSeeIdentity();

        searchCondition(dto);
        List<Application> list = super.findByPage(dto);
        List<ApplicationBO> applicationBOList = BeanTransform.copyProperties(list, ApplicationBO.class, false);
        return applicationBOList;
    }

    @Override
    public ApplicationBO getById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Application application = super.findById(id);
        return BeanTransform.copyProperties(application, ApplicationBO.class);
    }

    @Override
    public Long getTotal(ApplicationDTO applicationDTO) throws SerException {
        searchCondition(applicationDTO);
        Long count = super.count(applicationDTO);
        return count;
    }

    @Override
    public void examine(ApplicationTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);

        Application entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("数据为空");
        }

        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId()).stream().filter(str -> str.getStatus().equals(Status.THAW)).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if (!"财务运营部".equals(positionDetailBO.getDepartmentName()) && !"规划模块".equals(positionDetailBO.getDepartmentName()) && !"协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName()) && !"admin".equals(userBO.getUsername())) {
                    throw new SerException("当前用户没有权限");
                }

                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(entity.getFinancial())) {
                    entity.setFinancial(userBO.getUsername());
                    entity.setOpinion(to.getOpinion());
                    if (to.getSituation()) {
                        entity.setTar(true);
                    } else {
                        entity.setTar(false);
                    }
                } else if ("规划模块".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(entity.getPlanModule())) {
                    entity.setPlanModule(userBO.getUsername());
                    entity.setOpinion1(to.getOpinion());
                    if (to.getSituation() && entity.getTar()) {
                        entity.setTar(true);
                    } else {
                        entity.setTar(false);
                    }
                } else if ("协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(entity.getManager())) {
                    entity.setManager(userBO.getUsername());
                    entity.setOpinion2(to.getOpinion2());
                    if (to.getSituation() && entity.getTar()) {
                        entity.setSituation(true);
                    }
                }
            }
        } else if ("admin".equals(userBO.getUsername())) {
            entity.setFinancial(userBO.getUsername());
            entity.setOpinion(to.getOpinion());
            entity.setPlanModule(userBO.getUsername());
            entity.setOpinion1(to.getOpinion());
            entity.setManager(userBO.getUsername());
            entity.setOpinion2(to.getOpinion());
            entity.setSituation(to.getSituation());
        }
        super.update(entity);
    }

    //计算两个日期之间的月数
    public int getMonthSpace(String date1, String date2) throws SerException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));

            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

            return result == 0 ? 1 : Math.abs(result);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    public void searchCondition(ApplicationDTO dto) throws SerException {
        /**
         * 持股人
         */
        if (StringUtils.isNotBlank(dto.getShareholder())) {
            dto.getConditions().add(Restrict.eq("getShareholder", dto.getShareholder()));
        }
        /**
         * 申请时间
         */
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto.getConditions().add(Restrict.eq("time", dto.getTime()));
        }
        /**
         * 地区
         */
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.like("area", dto.getArea()));
        }
        /**
         * 项目组/部门
         */
        if (StringUtils.isNotBlank(dto.getDepartment())) {
            dto.getConditions().add(Restrict.like("department", dto.getDepartment()));
        }
        /**
         * 岗位
         */
        if (StringUtils.isNotBlank(dto.getPosition())) {
            dto.getConditions().add(Restrict.eq("position", dto.getPosition()));
        }
        /**
         * 在职时间（月数）
         */
        if (0 < dto.getMonths()) {
            dto.getConditions().add(Restrict.eq("months", dto.getMonths()));
        }
        /**
         * 申请原因
         */
        if (StringUtils.isNotBlank(dto.getReason())) {
            dto.getConditions().add(Restrict.like("reason", dto.getReason()));
        }
    }
}