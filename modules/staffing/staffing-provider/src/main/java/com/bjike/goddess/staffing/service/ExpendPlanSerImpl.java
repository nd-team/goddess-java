package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.staffing.bo.DetailBO;
import com.bjike.goddess.staffing.bo.ExpendPlanBO;
import com.bjike.goddess.staffing.bo.ExpendPlanSonDetailBO;
import com.bjike.goddess.staffing.bo.SonBO;
import com.bjike.goddess.staffing.dto.ExpendPlanDTO;
import com.bjike.goddess.staffing.dto.ExpendPlanSonDetailDTO;
import com.bjike.goddess.staffing.dto.SonDTO;
import com.bjike.goddess.staffing.entity.ExpendPlan;
import com.bjike.goddess.staffing.entity.ExpendPlanSonDetail;
import com.bjike.goddess.staffing.entity.Son;
import com.bjike.goddess.staffing.enums.GuideAddrStatus;
import com.bjike.goddess.staffing.to.ExpendPlanSonDetailTO;
import com.bjike.goddess.staffing.to.ExpendPlanTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.to.SonTO;
import com.bjike.goddess.staffing.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 人工成本计划业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffingSerCache")
@Service
public class ExpendPlanSerImpl extends ServiceImpl<ExpendPlan, ExpendPlanDTO> implements ExpendPlanSer {
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private SonSer sonSer;
    @Autowired
    private ExpendPlanSonDetailSer expendPlanSonDetailSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ConfigurationActualSer configurationActualSer;
    @Autowired
    private ConfigurationPlanSer configurationPlanSer;
    @Autowired
    private SalarySer salarySer;

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
        obj.setName("expendplan");
        obj.setDescribesion("人工成本计划");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = configurationActualSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("configurationactual");
        obj.setDescribesion("人数配置-实际");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis1 = configurationPlanSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("configurationplan");
        obj.setDescribesion("人数配置-计划");
        if (flagSeeDis1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis2 = salarySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("salary");
        obj.setDescribesion("工资区间");
        if (flagSeeDis2) {
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ExpendPlanBO save(ExpendPlanTO to) throws SerException {
        checkAddIdentity();
        ExpendPlan expendPlan = BeanTransform.copyProperties(to, ExpendPlan.class, true);
        LocalDate time = expendPlan.getTime();
        double planExpend = expendPlan.getPlanExpend();
        ExpendPlan entity = find(time, planExpend);
        if (entity == null) {
            entity = super.save(expendPlan);
        }
        SonTO expendPlanSonTO = to.getSonTO();
        List<ExpendPlanSonDetailTO> sonDetailTOs = expendPlanSonTO.getSonDetailTOs();
        for (ExpendPlanSonDetailTO expendPlanSonDetailTO:sonDetailTOs){
            if (expendPlanSonDetailTO.getTitleIndex()%4==0){
                try {
                    Integer.valueOf(expendPlanSonDetailTO.getContent());
                }catch (Exception e){
                    throw new SerException("计划人数必须为数字");
                }
            }
        }
        Son expendPlanSon = BeanTransform.copyProperties(expendPlanSonTO, Son.class, true);
        expendPlanSon.setExpendPlan(entity);
        sonSer.save(expendPlanSon);
        for (ExpendPlanSonDetailTO expendPlanSonDetailTO : sonDetailTOs) {
            ExpendPlanSonDetail expendPlanSonDetail = BeanTransform.copyProperties(expendPlanSonDetailTO, ExpendPlanSonDetail.class, true);
            expendPlanSonDetail.setSon(expendPlanSon);
            expendPlanSonDetailSer.save(expendPlanSonDetail);
        }
        return BeanTransform.copyProperties(entity, ExpendPlanBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ExpendPlanTO to) throws SerException {
        checkAddIdentity();
        ExpendPlan entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, ExpendPlan.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        Son expendPlanSon = sonSer.findById(to.getSonTO().getId());
        LocalDateTime b = expendPlanSon.getCreateTime();
        SonTO expendPlanSonTO = to.getSonTO();
        List<ExpendPlanSonDetailTO> sonDetailTOs = expendPlanSonTO.getSonDetailTOs();
        expendPlanSon = BeanTransform.copyProperties(expendPlanSonTO, Son.class, true);
        expendPlanSon.setCreateTime(b);
        expendPlanSon.setModifyTime(LocalDateTime.now());
        expendPlanSon.setExpendPlan(entity);
        sonSer.update(expendPlanSon);
        for (ExpendPlanSonDetailTO expendPlanSonDetailTO : sonDetailTOs) {
            ExpendPlanSonDetail expendPlanSonDetail = expendPlanSonDetailSer.findById(expendPlanSonDetailTO.getId());
            expendPlanSonDetail = BeanTransform.copyProperties(expendPlanSonDetailTO, ExpendPlanSonDetail.class, true);
            expendPlanSonDetail.setSon(expendPlanSon);
            expendPlanSonDetail.setCreateTime(b);
            expendPlanSonDetail.setModifyTime(LocalDateTime.now());
            expendPlanSonDetailSer.update(expendPlanSonDetail);
        }
    }

    @Override
    public List<ExpendPlanBO> list(ExpendPlanDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<ExpendPlan> list = super.findByCis(dto, true);
        List<HierarchyBO> hierarchyBOs = hierarchyAPI.findStatus();
        List<ExpendPlanBO> boList = new ArrayList<>();
        for (ExpendPlan expendPlan : list) {
            int total = 0;
            int boss = 0;
            SonDTO sonDTO = new SonDTO();
            sonDTO.getConditions().add(Restrict.eq("expendPlan.id", expendPlan.getId()));
            List<Son> sons = sonSer.findByCis(sonDTO);
            ExpendPlanBO expendPlanBO = BeanTransform.copyProperties(expendPlan, ExpendPlanBO.class);
            List<DetailBO> detailBOs1 = new ArrayList<>();
            List<SonBO> sonBOs = new ArrayList<>();
            for (Son son : sons) {
                ExpendPlanSonDetailDTO expendPlanSonDetailDTO = new ExpendPlanSonDetailDTO();
                expendPlanSonDetailDTO.getConditions().add(Restrict.eq("son.id", son.getId()));
                List<ExpendPlanSonDetail> sonDetails = expendPlanSonDetailSer.findByCis(expendPlanSonDetailDTO);
                for (ExpendPlanSonDetail sonDetail : sonDetails) {
                    if (sonDetail.getTitleIndex() % 4 == 0) {
                        total += Integer.valueOf(sonDetail.getContent());
                    }
                    if (sonDetail.getTitleIndex() == 0) {
                        boss = Integer.valueOf(sonDetail.getContent());
                    }
                }
                List<ExpendPlanSonDetailBO> sonDetailBOs = BeanTransform.copyProperties(sonDetails, ExpendPlanSonDetailBO.class);
                SonBO sonBO = BeanTransform.copyProperties(son, SonBO.class);
                sonBO.setDetails(sonDetailBOs);
                sonBOs.add(sonBO);
            }
            for (int j = 1; j <= hierarchyBOs.size(); j++) {
                int hierarchyTotal = 0;
                for (Son son : sons) {
                    int a = 4 * j;
                    ExpendPlanSonDetailDTO expendPlanSonDetailDTO = new ExpendPlanSonDetailDTO();
                    expendPlanSonDetailDTO.getConditions().add(Restrict.eq("son.id", son.getId()));
                    List<ExpendPlanSonDetail> sonDetails = expendPlanSonDetailSer.findByCis(expendPlanSonDetailDTO);
                    List<DepartmentDetailBO> detailBOs = departmentDetailAPI.findByHierarchy(hierarchyBOs.get(j - 1).getId());
                    for (int i = a; i < (4 * detailBOs.size() + a); i++) {
                        if (i < sonDetails.size() && i % 4 == 0) {
                            hierarchyTotal += Integer.valueOf(sonDetails.get(i).getContent());
                        }
                    }
                }
                DetailBO detailBO = new DetailBO();
                detailBO.setListTitleIndex(j - 1);
                detailBO.setContent(Math.round(Double.valueOf(hierarchyTotal) / Double.valueOf(total) * 100) + "%");
                detailBOs1.add(detailBO);
            }
            expendPlanBO.setPlanSons(sonBOs);
            expendPlanBO.setBossProportion(Math.round(Double.valueOf(boss) / Double.valueOf(total) * 100) + "%");
            expendPlanBO.setTotal(total);
            expendPlanBO.setAvg(Double.valueOf(String.format("%.2f", (expendPlan.getPlanExpend() / total))));
            expendPlanBO.setDetails(detailBOs1);
            boList.add(expendPlanBO);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        Son son = sonSer.findById(id);
        if (son == null) {
            throw new SerException("该对象不存在");
        }
        ExpendPlanSonDetailDTO expendPlanSonDetailDTO = new ExpendPlanSonDetailDTO();
        expendPlanSonDetailDTO.getConditions().add(Restrict.eq("son.id", id));
        List<ExpendPlanSonDetail> sonDetails = expendPlanSonDetailSer.findByCis(expendPlanSonDetailDTO);
        expendPlanSonDetailSer.remove(sonDetails);
        String expendPlanID = son.getExpendPlan().getId();
        sonSer.delete(id);
        Set<String> ids = new HashSet<>();
        List<Son> sons = sonSer.findAll();
        for (Son s : sons) {
            ids.add(s.getExpendPlan().getId());
        }
        if ((!ids.contains(expendPlanID))) {
            super.remove(expendPlanID);
        }
    }

    @Override
    public ExpendPlanBO findByID(String id) throws SerException {
        ExpendPlan entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        SonDTO sonDTO = new SonDTO();
        sonDTO.getConditions().add(Restrict.eq("expendPlan.id", entity.getId()));
        List<Son> sons = sonSer.findByCis(sonDTO);
        List<SonBO> sonBOs = BeanTransform.copyProperties(sons, SonBO.class, true);
        ExpendPlanBO bo = BeanTransform.copyProperties(entity, ExpendPlanBO.class);
        bo.setPlanSons(sonBOs);
        return bo;
    }

    @Override
    public Long count(ExpendPlanDTO dto) throws SerException {
        return super.count(dto);
    }

    private ExpendPlan find(LocalDate time, double planExpend) throws SerException {
        ExpendPlanDTO dto = new ExpendPlanDTO();
        dto.getConditions().add(Restrict.eq("time", time));
        dto.getConditions().add(Restrict.eq("planExpend", planExpend));
        ExpendPlan expendPlan = super.findOne(dto);
        return expendPlan;
    }
}