package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectEvaluateResultBO;
import com.bjike.goddess.projectmeasure.bo.ProjectOtherDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectOtherDemandDTO;
import com.bjike.goddess.projectmeasure.entity.ProjectOtherDemand;
import com.bjike.goddess.projectmeasure.excel.SonPermissionObject;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectOtherDemandTO;
import com.bjike.goddess.projectmeasure.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 其他需求界面业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectOtherDemandSerImpl extends ServiceImpl<ProjectOtherDemand, ProjectOtherDemandDTO> implements ProjectOtherDemandSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private MultipleProjectMultipleUISer multipleProjectMultipleUISer;
    @Autowired
    private MultipleProjectSingleUISer multipleProjectSingleUISer;
    @Autowired
    private ProjectBasicInfoSer projectBasicInfoSer;
    @Autowired
    private ProjectCostStatusSer projectCostStatusSer;
    @Autowired
    private ProjectMeasureSummarySer projectMeasureSummarySer;
    @Autowired
    private SingleProjectMultipleUISer singleProjectMultipleUISer;
    @Autowired
    private SingleProjectSingleUISer singleProjectSingleUISer;
    @Autowired
    private MessageAPI messageAPI;


    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            //商务模块权限
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航检查权限
     *
     * @throws SerException
     */
    private Boolean guildPermission() throws SerException {
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectOtherDemandBO add(ProjectOtherDemandTO projectOtherDemandTO) throws SerException {
        {
            ProjectOtherDemand projectOtherDemand = BeanTransform.copyProperties(projectOtherDemandTO, ProjectOtherDemand.class, true);
            projectOtherDemand = super.save(projectOtherDemand);
            return BeanTransform.copyProperties(projectOtherDemand, ProjectOtherDemandBO.class);
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectOtherDemandBO edit(ProjectOtherDemandTO projectOtherDemandTO) throws SerException {
        checkPermission();
        if (!StringUtils.isEmpty(projectOtherDemandTO.getId())) {
            ProjectOtherDemand projectOtherDemand = super.findById(projectOtherDemandTO.getId());
            BeanTransform.copyProperties(projectOtherDemandTO, projectOtherDemand, true);
            projectOtherDemand.setModifyTime(LocalDateTime.now());
            super.update(projectOtherDemand);
            return null;
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<ProjectOtherDemandBO> findProjectOtherDemand(ProjectOtherDemandDTO dto) throws SerException {
        checkPermission();
        List<ProjectOtherDemand> projectOtherDemands = super.findByPage(dto);
        List<ProjectOtherDemandBO> projectOtherDemandBOS = BeanTransform.copyProperties(projectOtherDemands, ProjectOtherDemandBO.class);
        return projectOtherDemandBOS;

    }

    @Override
    public List<ProjectEvaluateResultBO> findEvaluateResult(ProjectOtherDemandDTO demandDTO) throws SerException {
        checkPermission();
        String con = "";
        if (StringUtils.isNotBlank(demandDTO.getArea())) {
            con = " and a.area = '" + demandDTO.getArea() + "'";
        }
        String[] field = {"projectName", "area", "amount", "timeLimit", "labour", "serviceCharge", "royalties", "serveCharge", "deviceCharge", "vehicleCharge", "configCharge", "otherCharge", "taxes", "backDate", "workInterface", "totalCost"};
        String sql = "select a.projectName as projectName ,a.area as area, IFNULL(a.amount,0) as amount, a.timeLimit as timeLimit, IFNULL(a.labour,0) as labour," +
                " IFNULL(a.serviceCharge,0) as serviceCharge, IFNULL(a.royalties,0) as royalties, IFNULL(a.serveCharge,0) as serveCharge, IFNULL(a.deviceCharge,0) as deviceCharge, " +
                "IFNULL(a.vehicleCharge,0) as vehicleCharge, IFNULL(a.configCharge,0) as configCharge, IFNULL(a.otherCharge,0) as otherCharge, IFNULL(b.taxes,0) as taxes, b.backDate as backDate," +
                " c.interfaceSelect as workInterface, IFNULL(d.totalCost,0) as totalCost from projectmeasure_projectbasicinfo" +
                " a left join projectmeasure_projectcoststatus b on a.projectName " +
                "= b.projectName left join projectmeasure_singleprojectsingleui c " +
                " on b.projectName = c.projectName left join" +
                " projectmeasure_projectpersonneldemand d on c.projectName = d.projectName where 1=1 " + con + " limit " + demandDTO.getPage() + "," + demandDTO.getLimit();

        List<ProjectEvaluateResultBO> projectEvaluateResultBOS = new ArrayList<>();
        projectEvaluateResultBOS = projectBasicInfoSer.findBySql(sql, ProjectEvaluateResultBO.class, field);
        if (projectEvaluateResultBOS != null && projectEvaluateResultBOS.size() > 0) {
            for (ProjectEvaluateResultBO str : projectEvaluateResultBOS) {
                ProjectEvaluateResultBO projectEvaluateResultBO = str;
                double labour = projectEvaluateResultBO.getLabour();//人工成本
                double serviceCharge = projectEvaluateResultBO.getServiceCharge();//服务费用
                double royalties = projectEvaluateResultBO.getRoyalties();//提成
                double serveCharge = projectEvaluateResultBO.getServeCharge();//招待费
                double deviceCharge = projectEvaluateResultBO.getDeviceCharge();//设备费用
                double vehicleCharge = projectEvaluateResultBO.getVehicleCharge();//车辆费用
                double configCharge = projectEvaluateResultBO.getConfigCharge();//配置费用
                double otherCharge = projectEvaluateResultBO.getOtherCharge();//其他费用
                double consumptionCosts = serviceCharge + royalties + serveCharge + deviceCharge + vehicleCharge + configCharge + otherCharge;//消耗费用
                double amount = projectEvaluateResultBO.getAmount();//金额
                double totalCost = labour+consumptionCosts;//总成本
                double taxes = projectEvaluateResultBO.getTaxes();//税金
                double profit = amount - consumptionCosts - totalCost - taxes;

                projectEvaluateResultBO.setConsumptionCosts(consumptionCosts);
                projectEvaluateResultBO.setTotalCost(totalCost);
                projectEvaluateResultBO.setProfit(profit);

            }
        }

        if (projectEvaluateResultBOS != null && projectEvaluateResultBOS.size() > 0) {

            Collections.sort(projectEvaluateResultBOS, new Comparator<ProjectEvaluateResultBO>() {
                @Override
                public int compare(ProjectEvaluateResultBO o1, ProjectEvaluateResultBO o2) {
                    return o2.getProfit().compareTo(o1.getProfit());
                }
            });
        }
        return projectEvaluateResultBOS;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagOtherSign = guildPermission();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("projectotherdemand");
        obj.setDescribesion("其他项目需求界面");
        if (flagOtherSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeMulM = multipleProjectMultipleUISer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("multipleprojectmultipleui");
        obj.setDescribesion("多个项目多个界面");
        if (flagSeeMulM) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeMulSing = multipleProjectSingleUISer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("multipleprojectpingleui");
        obj.setDescribesion("多个项目单个界面");
        if (flagSeeMulSing) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeBaInfo = projectBasicInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectbasicinfo");
        obj.setDescribesion("项目基本情况");
        if (flagSeeBaInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeCost = projectCostStatusSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectcoststatus");
        obj.setDescribesion("项目费用情况");
        if (flagSeeCost) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeSumma = projectMeasureSummarySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectmeasuresummary");
        obj.setDescribesion("项目测算汇总");
        if (flagSeeSumma) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeSingSing = singleProjectSingleUISer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("singleprojectsingleui");
        obj.setDescribesion("单个项目单个界面");
        if (flagSeeSingSing) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeSingMul = singleProjectMultipleUISer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("singleprojectmultipleui");
        obj.setDescribesion("单个项目多个界面");
        if (flagSeeSingMul) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeMeasure = projectMeasureSummarySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectmeasuresummary");
        obj.setDescribesion("项目人员需求");
        if (flagSeeMeasure) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outevaluateresult");
        obj.setDescribesion("输出评估结果界面");
        if (flagOtherSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        {
            String userToken = RpcTransmit.getUserToken();
            GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
            Boolean flag = true;
            switch (guideAddrStatus) {
                case LIST:
                    flag = guildPermission();
                    break;
                case ADD:
                    flag = guildPermission();
                    break;
                case EDIT:
                    flag = guildPermission();
                    break;
                case DELETE:
                    flag = guildPermission();
                    break;
                case CONGEL:
                    flag = guildPermission();
                    break;
                case THAW:
                    flag = guildPermission();
                    break;
                case COLLECT:
                    flag = guildPermission();
                    break;
                case SEE:
                    flag = guildPermission();
                    break;
                default:
                    flag = true;
                    break;
            }
            return flag;
        }
    }

    @Override
    public ProjectOtherDemandBO getOne(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        ProjectOtherDemand projectBasicInfo = super.findById(id);
        return BeanTransform.copyProperties(projectBasicInfo, ProjectOtherDemandBO.class);
    }
}