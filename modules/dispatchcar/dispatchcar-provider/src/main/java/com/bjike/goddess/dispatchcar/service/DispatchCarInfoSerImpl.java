package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.carinfo.api.DriverInfoAPI;
import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.bean.AuditResult;
import com.bjike.goddess.dispatchcar.bean.DispatchInfo;
import com.bjike.goddess.dispatchcar.bo.*;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.entity.DispatchCarInfo;
import com.bjike.goddess.dispatchcar.entity.LeaseCarCost;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.enums.GuideAddrStatus;
import com.bjike.goddess.dispatchcar.excel.SonPermissionObject;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.to.FinanceCollectTO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.oilcardmanage.api.OilCardBasicAPI;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 出车记录业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dispatchcarSerCache")
@Service
public class DispatchCarInfoSerImpl extends ServiceImpl<DispatchCarInfo, DispatchCarInfoDTO> implements DispatchCarInfoSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private LeaseCarCostSer leaseCarCostSer;
    @Autowired
    private DriverInfoAPI driverInfoAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private OilCardBasicAPI oilCardBasicAPI;
    @Autowired
    private MessageAPI messageAPI;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DispatchCarInfoBO insertModel(DispatchCarInfoTO to) throws SerException {
        UserBO userBO = userAPI.findByUsername(to.getCarUser());
        to.setUserNumber(userBO.getEmployeeNumber());
        //加油费 = 加油量 * 当天油价 ，加油量 = 总油耗 * 总里程数 ， 总油耗 = 本车耗油 + 是否开空调 + 是否市内
        DriverInfoBO driver = driverInfoAPI.findByDriver(to.getDriver());
        if (driver == null) {
            throw new SerException("司机不存在!");
        }
        Double oilWear = driver.getCarFuel();
        if (to.getAircondition()) {
            oilWear = oilWear + 0.01;
        }
        if (to.getDowntown()) {
            oilWear = oilWear + 0.01;
        }


        DispatchCarInfo model = BeanTransform.copyProperties(to, DispatchCarInfo.class, true);
        model.setMileageSubtract(model.getEndMileage() - model.getStartMileage());
        //计算餐补、加班费，满8小时，并有4小时为22点后，则给予餐费补贴30元,超过8个小时后的加班费 = 租车费 / 8 * 小时数
        //实际上班时长
        Long workHours = ChronoUnit.HOURS.between(model.getStartTime(), model.getEndTime());
        if (workHours > 8) {
            //当天22点
            LocalDateTime dateTime = LocalDateTime.of(model.getStartTime().toLocalDate(), LocalTime.of(22, 0));
            //出车开始时间+4 > 22:00 则补贴30元
            if (model.getStartTime().plusHours(4).isAfter(dateTime)) {
                model.setMealCost(30.0);
            } else {
                model.setMealCost(0.0);
            }
            if (!model.getSiesta()) {
                model.setOverWorkTime((int) (workHours - 8));
            } else {
                model.setOverWorkTime((int) (workHours - 8 - 1));
            }
        } else {
            model.setMealCost(0.0);
            model.setOverWorkTime(0);
        }
        //查找租车费用
        model.setCarRentalCost(findCost(to));
        model.setOilWear(oilWear);
        model.setMileageSubtract(to.getEndMileage() - to.getStartMileage());
        model.setAddOilAmount(model.getMileageSubtract() * oilWear);
        if (to.getOilPrice() != null) {
            model.setOilCost(model.getAddOilAmount() * to.getOilPrice());
        } else {
            model.setOilCost(0.0);
        }

        //查询油卡余额
        model.setOilCardBalance(oilCardBasicAPI.findByCode(to.getOilCardNumber()).getBalance());
        model.setOverWorkCost(model.getCarRentalCost() / 8 * model.getOverWorkTime());
        model.setCost(model.getMealCost() + model.getCarRentalCost() + model.getOverWorkCost() + model.getParkCost() + model.getRoadCost());
        model.setTotalCost(model.getMealCost() + model.getCarRentalCost() + model.getOverWorkCost() + model.getParkCost() + model.getRoadCost() + model.getOilCost());
        model.setFindType(FindType.WAITAUDIT);
        //设置出车单号----IKE20170101-1...
        setNumber(model);
        super.save(model);
        return BeanTransform.copyProperties(model, DispatchCarInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DispatchCarInfoBO updateModel(DispatchCarInfoTO to) throws SerException {
        if (to.getId() != null) {
            DispatchCarInfo model = super.findById(to.getId());
            if (model != null) {
                UserBO userBO = userAPI.findByUsername(to.getCarUser());
                to.setUserNumber(userBO.getEmployeeNumber());
                //加油费 = 加油量 * 当天油价 ，加油量 = 总油耗 * 总里程数 ， 总油耗 = 本车耗油 + 是否开空调 + 是否市内
                DriverInfoBO driver = driverInfoAPI.findByDriver(to.getDriver());
                if (driver == null) {
                    throw new SerException("司机不存在!");
                }
                Double oilWear = driver.getCarFuel();
                if (to.getAircondition()) {
                    oilWear = oilWear + 0.01;
                }
                if (to.getDowntown()) {
                    oilWear = oilWear + 0.01;
                }
                model.setOilWear(oilWear);
                model.setMileageSubtract(to.getEndMileage() - to.getStartMileage());
                model.setAddOilAmount(model.getMileageSubtract() * oilWear);
                if (to.getOilPrice() != null) {
                    model.setOilCost(model.getAddOilAmount() * to.getOilPrice());
                } else {
                    model.setOilCost(0.0);
                }

                BeanTransform.copyProperties(to, model, true);

                model.setMileageSubtract(model.getEndMileage() - model.getStartMileage());
                //计算餐补、加班费，满8小时，并有4小时为22点后，则给予餐费补贴30元,超过8个小时后的加班费 = 租车费 / 8 * 小时数
                //实际上班时长
                Long workHours = ChronoUnit.HOURS.between(model.getStartTime(), model.getEndTime());
                if (workHours > 8) {
                    //当天22点
                    LocalDateTime dateTime = LocalDateTime.of(model.getStartTime().toLocalDate(), LocalTime.of(22, 0));
                    //出车开始时间+4 > 22:00 则补贴30元
                    if (model.getStartTime().plusHours(4).isAfter(dateTime)) {
                        model.setMealCost(30.0);
                    } else {
                        model.setMealCost(0.0);
                    }
                    if (!model.getSiesta()) {
                        model.setOverWorkTime((int) (workHours - 8));
                    } else {
                        model.setOverWorkTime((int) (workHours - 8 - 1));
                    }
                } else {
                    model.setMealCost(0.0);
                    model.setOverWorkTime(0);
                }
                //查找租车费用
                model.setCarRentalCost(findCost(to));
                //查询油卡余额
                model.setOilCardBalance(oilCardBasicAPI.findByCode(to.getOilCardNumber()).getBalance());
                model.setOverWorkCost(model.getCarRentalCost() / 8 * model.getOverWorkTime());
                model.setCost(model.getMealCost() + model.getCarRentalCost() + model.getOverWorkCost() + model.getParkCost() + model.getRoadCost());
                model.setTotalCost(model.getMealCost() + model.getCarRentalCost() + model.getOverWorkCost() + model.getParkCost() + model.getRoadCost() + model.getOilCost());

                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("id不能为空");
        }
        return BeanTransform.copyProperties(to, DispatchCarInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model != null) {
            model.setModifyTime(LocalDateTime.now());
            model.setStatus(Status.CONGEAL);
            super.update(model);
        } else {
            throw new SerException("冻结对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复解冻");
            }
        } else {
            throw new SerException("非法Id,出车记录对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<DispatchCarInfoBO> pageList(DispatchCarInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime");
        List<DispatchCarInfo> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, DispatchCarInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fileUpload(DispatchCarInfoTO to) throws SerException {

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AuditDetailBO findAudit(String id) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model == null) {
            throw new SerException("审核对象不存在!");
        }

        DispatchInfo info = BeanTransform.copyProperties(model, DispatchInfo.class);

        AuditDetailBO returnBO = new AuditDetailBO();
        returnBO.setInfo(info);

        List<AuditResult> list = new ArrayList<AuditResult>();
        if (model != null) {
            DateUtil dateUtil = new DateUtil();
            //查询资金审核结果
            if (!StringUtils.isEmpty(model.getFundModuleSugg()) && model.getFundAuditTime() != null) {
                AuditResult bo = new AuditResult();
                bo.setAuditUser(model.getFundAuditUser());
                bo.setPosition("资金模块负责人");
                bo.setSuggestion(model.getFundModuleSugg());
                bo.setAuditTime(dateUtil.dateToString(model.getFundAuditTime()));
                list.add(bo);
            }
            //预算审核结果
            if (!StringUtils.isEmpty(model.getBudgetModuleSugg()) && model.getBudgetAuditTime() != null) {
                AuditResult bo = new AuditResult();
                bo.setAuditUser(model.getBudgetAuditUser());
                bo.setPosition("预算模块负责人");
                bo.setSuggestion(model.getBudgetModuleSugg());
                bo.setAuditTime(dateUtil.dateToString(model.getBudgetAuditTime()));
                list.add(bo);
            }
            //项目负责人/任务下发人审核结果
            if (!StringUtils.isEmpty(model.getPrincipalSugg()) && model.getPrincipalAuditTime() != null) {
                AuditResult bo = new AuditResult();
                bo.setAuditUser(model.getPrincipal());
                bo.setPosition("项目模块负责人");
                bo.setSuggestion(model.getPrincipal());
                bo.setAuditResult(model.getAuditResult());
                bo.setAuditTime(dateUtil.dateToString(model.getPrincipalAuditTime()));
                list.add(bo);
            }
            returnBO.setList(list);
            return returnBO;
        }
        return returnBO;
    }

    /**
     * 查询审核结果
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AuditResultBO> findAuditResults(String id) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model == null) {
            throw new SerException("审核对象不存在!");
        }

        List<AuditResultBO> list = new ArrayList<AuditResultBO>();
        if (model != null) {
            DateUtil dateUtil = new DateUtil();
            //查询资金审核结果
            if (!StringUtils.isEmpty(model.getFundModuleSugg()) && model.getFundAuditTime() != null) {
                AuditResultBO bo = new AuditResultBO();
                bo.setAuditUser(model.getFundAuditUser());
                bo.setPosition("资金模块负责人");
                bo.setSuggestion(model.getFundModuleSugg());
                bo.setAuditTime(dateUtil.dateToString(model.getFundAuditTime()));
                list.add(bo);
            }
            //预算审核结果
            if (!StringUtils.isEmpty(model.getBudgetModuleSugg()) && model.getBudgetAuditTime() != null) {
                AuditResultBO bo = new AuditResultBO();
                bo.setAuditUser(model.getBudgetAuditUser());
                bo.setPosition("预算模块负责人");
                bo.setSuggestion(model.getBudgetModuleSugg());
                bo.setAuditTime(dateUtil.dateToString(model.getBudgetAuditTime()));
                list.add(bo);
            }
            //项目负责人/任务下发人审核结果
            if (!StringUtils.isEmpty(model.getPrincipalSugg()) && model.getPrincipalAuditTime() != null) {
                AuditResultBO bo = new AuditResultBO();
                bo.setAuditUser(model.getPrincipal());
                bo.setPosition("项目模块负责人");
                bo.setSuggestion(model.getPrincipal());
                bo.setAuditResult(model.getAuditResult());
                bo.setAuditTime(dateUtil.dateToString(model.getPrincipalAuditTime()));
                list.add(bo);
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fundSugg(String id, String fundModuleSugg) throws SerException {
        UserBO userBO = userAPI.currentUser();
        UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
        if (userDetailBO != null) {
            if (userDetailBO.getPositionName().equals("资金模块负责人")) {
                DispatchCarInfo model = super.findById(id);
                if (model != null) {
                    model.setFundModuleSugg(fundModuleSugg);
                    model.setFundAuditUser(userBO.getUsername());
                    model.setFundAuditTime(LocalDateTime.now());
                    super.update(model);
                } else {
                    throw new SerException("核对对象不能为空!");
                }
            } else {
                throw new SerException("资金模块负责人方可核对记录!");
            }
        } else {
            throw new SerException("获取不到当前登录用户的职位信息!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void budgetSugg(String id, String budgetModuleSugg) throws SerException {
        UserBO userBO = userAPI.currentUser();
        UserDetailBO userDetailBO = userDetailAPI.findByUserId(userBO.getId());
        if (userDetailBO != null) {
            if (userDetailBO.getPositionName().equals("预算模块负责人")) {
                DispatchCarInfo model = super.findById(id);
                if (model != null) {
                    model.setBudgetModuleSugg(budgetModuleSugg);
                    model.setBudgetAuditUser(userBO.getUsername());
                    model.setBudgetAuditTime(LocalDateTime.now());
                    super.update(model);
                } else {
                    throw new SerException("核对对象不能为空!");
                }
            } else {
                throw new SerException("预算模块负责人方可核对记录!");
            }
        } else {
            throw new SerException("无法获取当前登录用户的职位信息!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void principalSugg(String id, String principalSugg, Boolean auditResult) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (userBO != null) {
            DispatchCarInfo model = super.findById(id);
            if (model != null) {
                if (!model.getPrincipal().equals(userBO.getUsername())) {
                    throw new SerException("项目负责人或任务下发人方可审核");
                }
                model.setPrincipalSugg(principalSugg);
                model.setAuditResult(auditResult);
                model.setPrincipalAuditTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("核对对象不能为空!");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void receiptAudit(String id, String auditReceiptSugg, String receiveReceiptDate, Boolean auditReceiptResult) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model != null) {
            model.setAuditReceiptSugg(auditReceiptSugg);
            model.setReceiveReceiptDate(DateUtil.parseDate(receiveReceiptDate));
            model.setAuditResult(auditReceiptResult);
            if (auditReceiptResult) {
                model.setFindType(FindType.WAITPAY);
            }
            super.update(model);
        } else {
            throw new SerException("核对对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pay(String id) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model != null) {
            if(FindType.PAYED==model.getFindType()){
                throw new SerException("无需重复审核!");
            }
            model.setFindType(FindType.PAYED);
            //付款后代表所有审核均通过，修改油余额
            //TODO 这里应该考虑分布式事务，联系焕来或贵钦解决该问题。 TCC
            OilCardBasicBO basicBO = oilCardBasicAPI.findByCode(model.getOilCardNumber());
            OilCardBasic oilCardBasic = oilCardBasicAPI.find(basicBO.getId());
            oilCardBasic.setBalance(oilCardBasic.getBalance() - model.getOilPrice());
            oilCardBasicAPI.updateOliCardBasic(oilCardBasic);
            if (oilCardBasic.getBalance() < 300) {
                String content = "运营商务部的同事，你们好，" + oilCardBasic.getOilCardCode() + "号油卡余额" + oilCardBasic.getBalance() + "元，低于300元，请在一天内充值，请综合资源部同事跟进充值情况";
                MessageTO to = new MessageTO("油卡余额不足300元", content);
                to.setSendType(SendType.EMAIL);
                //TODO 未明确发送对象
//                to.setReceivers(sendUsers);
                messageAPI.send(to);
            }

            super.update(model);
        } else {
            throw new SerException("付款对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<DispatchCollectBO> dispatchCollect(CollectIntervalType collectIntervalType, CollectType collectType) throws SerException {

        if (collectIntervalType == null) {
            throw new SerException("汇总类型不能为空!");
        }

        List<DispatchCarInfo> proejctList = null;
        if (collectType == CollectType.AREA) {
            //分组查询地区、项目组、项目
            proejctList = super.findBySql("select area , project_group , project ,1 from dispatchcar_basicinfo group by area , project_group , project ",
                    DispatchCarInfo.class, new String[]{"area", "group", "project"});
        } else {
            //分组查询地区、项目组、项目
            proejctList = super.findBySql("select driver , project_group , project ,1 from dispatchcar_basicinfo group by driver , project_group , project ",
                    DispatchCarInfo.class, new String[]{"area", "group", "project"});
        }

        List<DispatchCollectBO> returnList = new ArrayList<DispatchCollectBO>();

        //遍历项目组
        for (DispatchCarInfo proejct : proejctList) {
            DispatchCollectBO bo = new DispatchCollectBO();
            bo.setDriver(proejct.getDriver());
            bo.setArea(proejct.getArea());
            bo.setGroup(proejct.getGroup());
            bo.setProject(proejct.getProject());

            findByType(proejct.getArea(), proejct.getGroup(), proejct.getProject(), bo, collectIntervalType);

            returnList.add(bo);
        }

        return returnList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FinanceCollectBO> weekCollect(String startDate, String endDate) throws SerException {
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
        LocalDate start = null;
        LocalDate end = null;
        //页面初始化时(即不填写查询时间)加载本周汇总记录
        if (StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            start = DateUtil.getStartWeek();
            end = DateUtil.getEndWeek();
        } else {
            if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
                start = DateUtil.parseDate(startDate);
                end = DateUtil.parseDate(endDate);
            } else {
                throw new SerException("请选择查询时间段!");
            }
        }
        LocalDate[] condition = new LocalDate[]{start, end};
        dto.getConditions().add(Restrict.between("dispatchDate", condition));

        return financeCollect(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FinanceCollectBO findCollectDetail(String id) throws SerException {
        DispatchCarInfo model = super.findById(id);
        FinanceCollectBO bo = new FinanceCollectBO();
        if (model != null) {
            bo.setId(model.getId());
            bo.setDate(DateUtil.dateToString(model.getDispatchDate()));
            bo.setNumber(model.getNumber());
            bo.setDriver(model.getDriver());
            bo.setArea(model.getArea());
            bo.setCarUser(model.getCarUser());
            bo.setProject(model.getProject());
            bo.setProjectGroup(model.getGroup());
            bo.setAcctype(model.getAcctype());
            bo.setOilCost(model.getOilCost());
            bo.setOverWorkTime(model.getOverWorkTime());
            bo.setOverWorkCost(model.getOverWorkCost());
            bo.setMealCost(model.getMealCost());
            bo.setParkCost(model.getParkCost());
            bo.setRoadCost(model.getParkCost());
        }
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void predict(String id, String budgetPayDate, String payPlan) throws SerException {
        DispatchCarInfo model = super.findById(id);
        if (model != null) {
            model.setBudgetPayDate(DateUtil.parseDate(budgetPayDate));
            model.setPayPlan(payPlan);
        } else {
            throw new SerException("编辑对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<SonPermissionObject> financeSonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();

        Boolean flagAddSign = financeGuideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("finance");
        obj.setDescribesion("财务出车汇总");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    /**
     * 出车管理导航栏核对查看权限（部门级别）
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
     * 出车管理导航栏核对删除添加编辑..审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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
     * 财务出车管理导航栏核对查看权限（部门级别）
     */
    private Boolean financeGuideSeeIdentity() throws SerException {
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

    /**
     * 财务出车管理导航栏核对功能审核权限（部门级别）
     */
    private Boolean financeGuideAddIdentity() throws SerException {
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


    @Override
    @Transactional(rollbackFor = SerException.class)
    public Boolean financeGuidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case WEEK:
                flag = financeGuideAddIdentity();
                break;
            case MONTH:
                flag = financeGuideAddIdentity();
                break;
            case AREACOLLECT:
                flag = financeGuideAddIdentity();
                break;
            case GROUPCOLLECT:
                flag = financeGuideAddIdentity();
                break;
            case DRIVERCOLLECT:
                flag = financeGuideAddIdentity();
                break;
            case AREAANALYZE:
                flag = financeGuideAddIdentity();
                break;
            case groupAnalyze:
                flag = financeGuideAddIdentity();
                break;
            case DRIVERANALYZE:
                flag = financeGuideAddIdentity();
                break;
            case DETAIL:
                flag = financeGuideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FinanceCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
        LocalDate start = null;
        LocalDate end = null;
        //页面初始化时(即不填写查询时间)加载本月汇总记录
        if (StringUtils.isEmpty(year) && StringUtils.isEmpty(month)) {
            start = DateUtil.getStartMonth();
            end = DateUtil.getEndMonth();
        } else {
            start = DateUtil.getStartDayOfMonth(year, month);
            end = DateUtil.getEndDaYOfMonth(year, month);
        }
        LocalDate[] condition = new LocalDate[]{start, end};
        dto.getConditions().add(Restrict.between("dispatchDate", condition));

        return financeCollect(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FinanceCollectBO> selectCollect(FinanceCollectTO to) throws SerException {
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();

        //年份、月份必填
        LocalDate start = DateUtil.getStartDayOfMonth(to.getYear(), to.getMonth());
        LocalDate end = DateUtil.getEndDaYOfMonth(to.getYear(), to.getMonth());
        LocalDate[] condition = new LocalDate[]{start, end};
        dto.getConditions().add(Restrict.between("dispatchDate", condition));

        if (to.getAcctype() != null) {
            dto.getConditions().add(Restrict.eq("acctype", to.getAcctype()));
        }
        if (!StringUtils.isEmpty(to.getArea())) {
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        }
        if (!StringUtils.isEmpty(to.getGroup())) {
            dto.getConditions().add(Restrict.eq("group", to.getGroup()));
        }
        if (!StringUtils.isEmpty(to.getProject())) {
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        }
        if (!StringUtils.isEmpty(to.getDriver())) {
            dto.getConditions().add(Restrict.eq("driver", to.getDriver()));
        }
        if (!StringUtils.isEmpty(to.getCarUser())) {
            dto.getConditions().add(Restrict.eq("carUser", to.getCarUser()));
        }

        return financeCollect(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<FinanceAnalyzeBO> selectAnalyze(FinanceCollectTO to) throws SerException {
        DispatchCarInfoDTO currentDTO = new DispatchCarInfoDTO();
        DispatchCarInfoDTO lastDTO = new DispatchCarInfoDTO();
        DispatchCarInfoDTO totoalDTO = new DispatchCarInfoDTO();
        //年份、月份必填:指定月份及制定月份的上一月
        LocalDate start = DateUtil.getStartDayOfMonth(to.getYear(), to.getMonth());
        LocalDate end = DateUtil.getEndDaYOfMonth(to.getYear(), to.getMonth());
        LocalDate lastStart = DateUtil.getStartDayOfMonth(to.getYear(), to.getMonth() - 1);
        LocalDate lastEnd = DateUtil.getEndDaYOfMonth(to.getYear(), to.getMonth() - 1);

        LocalDate[] currentTime = new LocalDate[]{start, end};
        LocalDate[] lastTime = new LocalDate[]{lastStart, lastEnd};

        currentDTO.getConditions().add(Restrict.between("dispatchDate", currentTime));
        lastDTO.getConditions().add(Restrict.between("dispatchDate", lastTime));

        //总费用
        totoalDTO.getConditions().add(Restrict.between("dispatchDate", currentTime));

        if (to.getAcctype() != null) {
            currentDTO.getConditions().add(Restrict.eq("acctype", to.getAcctype()));
            lastDTO.getConditions().add(Restrict.eq("acctype", to.getAcctype()));
        }
        if (!StringUtils.isEmpty(to.getArea())) {
            currentDTO.getConditions().add(Restrict.eq("area", to.getArea()));
            lastDTO.getConditions().add(Restrict.eq("area", to.getArea()));
        }
        if (!StringUtils.isEmpty(to.getGroup())) {
            currentDTO.getConditions().add(Restrict.eq("group", to.getGroup()));
            lastDTO.getConditions().add(Restrict.eq("group", to.getGroup()));
        }
        if (!StringUtils.isEmpty(to.getProject())) {
            currentDTO.getConditions().add(Restrict.eq("project", to.getProject()));
            lastDTO.getConditions().add(Restrict.eq("project", to.getProject()));
        }
        if (!StringUtils.isEmpty(to.getDriver())) {
            currentDTO.getConditions().add(Restrict.eq("driver", to.getDriver()));
            lastDTO.getConditions().add(Restrict.eq("driver", to.getDriver()));
        }
        if (!StringUtils.isEmpty(to.getCarUser())) {
            currentDTO.getConditions().add(Restrict.eq("carUser", to.getCarUser()));
            lastDTO.getConditions().add(Restrict.eq("carUser", to.getCarUser()));
        }
        return financeAnalyze(currentDTO, lastDTO, totoalDTO, to);
    }

    public List<FinanceAnalyzeBO> financeAnalyze(DispatchCarInfoDTO currentDTO, DispatchCarInfoDTO lastDTO, DispatchCarInfoDTO totalDTO, FinanceCollectTO to) throws SerException {
        //查询月份
        List<DispatchCarInfo> currentList = super.findByCis(currentDTO);
        //查询上已月份
        List<DispatchCarInfo> lastList = super.findByCis(lastDTO);
        //查询全部记录
        List<DispatchCarInfo> totalList = super.findByCis(totalDTO);

        List<FinanceAnalyzeBO> boList = new ArrayList<FinanceAnalyzeBO>();

        FinanceAnalyzeBO bo = null;

        Double currentCost = currentList.stream().filter(p -> p.getCost() != null).mapToDouble(p -> p.getCost()).sum();
        Double lastCost = lastList.stream().filter(p -> p.getCost() != null).mapToDouble(p -> p.getCost()).sum();
        Double totalCost = totalList.stream().filter(p -> p.getCost() != null).mapToDouble(p -> p.getCost()).sum();
        Double costSubtract = currentCost - lastCost;
        Double growRate = 0.0;
        if (lastCost == 0) {
            growRate = null;
        } else {
            growRate = costSubtract / lastCost;
        }
        Double percent = 0.0;
        String percentStr = "";
        if (totalCost == 0) {
            percent = null;
            percentStr = null;
        } else {
            percent = currentCost / totalCost * 100;
            DecimalFormat format = new DecimalFormat("#.00");
            percentStr = format.format(percent) + "%";
        }

        //根据分析页面(条件)不同返回不同的分析结果
        if (!StringUtils.isEmpty(to.getArea())) {
            bo = new FinanceAnalyzeBO(to.getArea(), currentCost, lastCost, costSubtract, growRate, percentStr);
        } else if (!StringUtils.isEmpty(to.getGroup())) {
            bo = new FinanceAnalyzeBO(to.getGroup(), currentCost, lastCost, costSubtract, growRate, percentStr);
        } else if (!StringUtils.isEmpty(to.getDriver())) {
            bo = new FinanceAnalyzeBO(to.getDriver(), currentCost, lastCost, costSubtract, growRate, percentStr);
        } else {
            throw new SerException("请选择分析的地区或项目组或司机!");
        }
        boList.add(bo);
        return boList;
    }

    public List<FinanceCollectBO> financeCollect(DispatchCarInfoDTO dto) throws SerException {
        List<DispatchCarInfo> list = super.findByCis(dto);
        List<FinanceCollectBO> boList = new ArrayList<FinanceCollectBO>();

        for (DispatchCarInfo model : list) {
            FinanceCollectBO bo = new FinanceCollectBO();
            bo.setId(model.getId());
            bo.setDate(DateUtil.dateToString(model.getDispatchDate()));
            bo.setNumber(model.getNumber());
            bo.setDriver(model.getDriver());
            bo.setArea(model.getArea());
            bo.setCarUser(model.getCarUser());
            bo.setProject(model.getProject());
            bo.setProjectGroup(model.getGroup());
            bo.setAcctype(model.getAcctype());
            bo.setOilCost(model.getOilCost());
            bo.setOverWorkTime(model.getOverWorkTime());
            bo.setOverWorkCost(model.getOverWorkCost());
            bo.setMealCost(model.getMealCost());
            bo.setParkCost(model.getParkCost());
            bo.setRoadCost(model.getParkCost());
            boList.add(bo);
        }

        Double totalOilCost = 0.0;
        Integer totalOverWorkTime = 0;
        Double totalOverWorkCost = 0.0;
        Double totalMealCost = 0.0;
        Double totalParkCost = 0.0;
        Double totalRoadCost = 0.0;

        if (list != null && !list.isEmpty()) {
            totalOilCost = list.stream().filter(p -> p.getOilCost() != null).mapToDouble(p -> p.getOilCost()).sum();
            totalOverWorkTime = list.stream().filter(p -> p.getOverWorkTime() != null).mapToInt(p -> p.getOverWorkTime()).sum();
            totalOverWorkCost = list.stream().filter(p -> p.getOverWorkCost() != null).mapToDouble(p -> p.getOverWorkCost()).sum();
            totalMealCost = list.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();
            totalParkCost = list.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
            totalRoadCost = list.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();

            FinanceCollectBO totalBO = new FinanceCollectBO("合计", "", "", "", "", "", "", null,
                    totalOilCost, totalOverWorkTime, totalOverWorkCost,
                    totalMealCost, totalParkCost, totalRoadCost, "");
            boList.add(totalBO);
        } else {
            FinanceCollectBO totalBO = new FinanceCollectBO("合计", "", "", "", "", "", "", null,
                    totalOilCost, totalOverWorkTime, totalOverWorkCost,
                    totalMealCost, totalParkCost, totalRoadCost, "");
            boList.add(totalBO);
        }
        return boList;
    }

    public void findByType(String area, String group, String project, DispatchCollectBO bo, CollectIntervalType collectIntervalType) throws SerException {
        //查询指定汇总类型的出车情况(昨日/上周/上月/上季度/上年度)
        DispatchCarInfoDTO currentDTO = new DispatchCarInfoDTO();
        currentDTO.getConditions().add(Restrict.eq("area", area));
        currentDTO.getConditions().add(Restrict.eq("group", group));
        currentDTO.getConditions().add(Restrict.eq("project", project));
        //查询指定汇总类型的出车情况(本日/本周/本月/本季度/本年度)
        DispatchCarInfoDTO lastDTO = new DispatchCarInfoDTO();
        lastDTO.getConditions().add(Restrict.eq("area", area));
        lastDTO.getConditions().add(Restrict.eq("group", group));
        lastDTO.getConditions().add(Restrict.eq("project", project));

        switch (collectIntervalType) {
            case DAY:
                setDayCondition(currentDTO, lastDTO, bo, CollectIntervalType.DAY);
                findByCondition(currentDTO, lastDTO, bo);
                break;
            case WEEK:
                setCondition(currentDTO, lastDTO, bo, CollectIntervalType.WEEK);
                findByCondition(currentDTO, lastDTO, bo);
                break;
            case MONTH:
                setCondition(currentDTO, lastDTO, bo, CollectIntervalType.MONTH);
                findByCondition(currentDTO, lastDTO, bo);
                break;
            case QUARTER:
                setCondition(currentDTO, lastDTO, bo, CollectIntervalType.QUARTER);
                findByCondition(currentDTO, lastDTO, bo);
                break;
            case YEAR:
                setCondition(currentDTO, lastDTO, bo, CollectIntervalType.YEAR);
                findByCondition(currentDTO, lastDTO, bo);
                break;
        }
    }

    //查询本天、昨天出车情况
    public void setDayCondition(DispatchCarInfoDTO currentDTO, DispatchCarInfoDTO lastDTO, DispatchCollectBO bo, CollectIntervalType collectIntervalType) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        currentDTO.getConditions().add(Restrict.like("dispatchDate", today.toString()));
        lastDTO.getConditions().add(Restrict.like("dispatchDate", yesterday.toString()));
    }

    //查询周、月、季、年
    public void setCondition(DispatchCarInfoDTO currentDTO, DispatchCarInfoDTO lastDTO, DispatchCollectBO bo, CollectIntervalType collectIntervalType) throws SerException {
        LocalDate currentStarDay = null;
        LocalDate currentEndDay = null;

        LocalDate lastStarDay = null;
        LocalDate lastEndDay = null;

        switch (collectIntervalType) {
            case WEEK:
                currentStarDay = DateUtil.getStartWeek();
                currentEndDay = DateUtil.getEndWeek();
                lastStarDay = currentStarDay.minusWeeks(1);
                lastEndDay = currentEndDay.minusWeeks(1);

                break;
            case MONTH:
                currentStarDay = DateUtil.getStartMonth();
                currentEndDay = DateUtil.getEndMonth();
                lastStarDay = currentStarDay.minusMonths(1);
                lastEndDay = currentEndDay.minusMonths(1);

                break;
            case QUARTER:
                Date date = new Date();
                date.getMonth();
                currentStarDay = DateUtil.getStartDayOfMonth(getQuarterStart(date.getMonth()));
                currentEndDay = DateUtil.getEndDaYOfMonth(getQuarterEnd(date.getMonth()));
                lastStarDay = currentStarDay.minusMonths(3);
                lastEndDay = currentEndDay.minusMonths(3);

                break;
            case YEAR:
                currentStarDay = DateUtil.getStartYear();
                currentEndDay = DateUtil.getEndYear();
                lastStarDay = currentStarDay.minusYears(1);
                lastEndDay = currentEndDay.minusYears(1);

                break;
        }

        LocalDate[] currentCondition = new LocalDate[]{currentStarDay, currentEndDay};
        LocalDate[] lastCondition = new LocalDate[]{lastStarDay, lastEndDay};

        currentDTO.getConditions().add(Restrict.between("dispatchDate", currentCondition));
        lastDTO.getConditions().add(Restrict.between("dispatchDate", lastCondition));
    }

    //根据月份获取季度开始月
    public int getQuarterStart(int month) {
        int months[] = {0, 3, 6, 9};
        if (month >= 0 && month <= 2) // 1-3月;0,1,2
            return months[0];
        else if (month >= 3 && month <= 5) // 4-6月;3,4,5
            return months[1];
        else if (month >= 6 && month <= 8) // 7-9月;6,7,8
            return months[2];
        else
            // 10-12月;10,11,12
            return months[3];
    }

    //根据月份获取季度结束月
    public int getQuarterEnd(int month) {
        int months[] = {2, 5, 8, 11};
        if (month >= 0 && month <= 2) // 1-3月;0,1,2
            return months[0];
        else if (month >= 3 && month <= 5) // 4-6月;3,4,5
            return months[1];
        else if (month >= 6 && month <= 8) // 7-9月;6,7,8
            return months[2];
        else
            // 10-12月;10,11,12
            return months[3];
    }


    public void findByCondition(DispatchCarInfoDTO currentDTO, DispatchCarInfoDTO lastDTO, DispatchCollectBO bo) throws SerException {

        List<DispatchCarInfo> curretnList = super.findByCis(currentDTO);
        List<DispatchCarInfo> lastList = super.findByCis(lastDTO);

        Long companyDispatch = curretnList.stream().filter(p -> p.getCompanyDispatch() == Boolean.TRUE).count();
        Long uncompanyDispatch = curretnList.stream().filter(p -> p.getCompanyDispatch() == Boolean.FALSE).count();
        Long currentDispatch = (long) curretnList.size();
        Long lastDispatch = (long) lastList.size();
        Long dispatchSubtract = currentDispatch - lastDispatch;
        Double leaseCarCost = curretnList.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
        Double parkCost = curretnList.stream().filter(p -> p.getParkCost() != null).mapToDouble(p -> p.getParkCost()).sum();
        Double roadCost = curretnList.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
        Double mealCost = curretnList.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();

        Double lastLeaseCarCost = lastList.stream().filter(p -> p.getCarRentalCost() != null).mapToDouble(p -> p.getCarRentalCost()).sum();
        Double lastRoadCost = lastList.stream().filter(p -> p.getRoadCost() != null).mapToDouble(p -> p.getRoadCost()).sum();
        Double LastMealCost = lastList.stream().filter(p -> p.getMealCost() != null).mapToDouble(p -> p.getMealCost()).sum();

        Double totalCurrent = leaseCarCost + roadCost + mealCost;
        Double totalLast = lastLeaseCarCost + lastRoadCost + LastMealCost;
        Double costSubtract = totalCurrent - totalLast;

        bo.setCompanyDispatch(companyDispatch);
        bo.setUncompanyDispatch(uncompanyDispatch);
        bo.setCurrentDispatch(currentDispatch);
        bo.setLastDispatch(lastDispatch);
        bo.setDispatchSubtract(dispatchSubtract);
        bo.setLeaseCarCost(leaseCarCost);
        bo.setParkCarCost(parkCost);
        bo.setRoadCost(roadCost);
        bo.setMealCost(mealCost);
        bo.setCurrentCost(totalCurrent);
        bo.setLastCost(costSubtract);
        bo.setCostSubtract(costSubtract);
    }

    /**
     * 根据地区、项目组查询租车费
     */
    public Double findCost(DispatchCarInfoTO to) throws SerException {
        LeaseCarCostDTO dto = new LeaseCarCostDTO();
        dto.getConditions().add(Restrict.eq("area", to.getArea()));
        dto.getConditions().add(Restrict.eq("group", to.getGroup()));
        LeaseCarCost leaseCarCost = leaseCarCostSer.findOne(dto);
        if (leaseCarCost != null) {
            return leaseCarCost.getCost();
        } else {
            throw new SerException("该地区和项目组对应的租车费未添加,请先添加租车费用信息!");
        }
    }

    /**
     * //设置出车单号----IKE20170101-1...
     */
    public void setNumber(DispatchCarInfo model) throws SerException {
        String todayStr = LocalDate.now().toString();
        String todayNum = todayStr.replace("-", "");

        //查询当天最新的一条数据
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
        dto.getConditions().add(Restrict.like("createTime", todayStr));
        dto.getSorts().add("createTime=desc");
        dto.setLimit(1);
        List<DispatchCarInfo> list = super.findByPage(dto);
        StringBuilder numStr = new StringBuilder();
        //拼接出车单
        if (model != null && !list.isEmpty()) {
            String number = list.get(0).getNumber();
            numStr.append(number.substring(0, 12));
            int lastNum = Integer.parseInt(number.substring(12)) + 1;
            numStr.append(lastNum);
        } else {
            numStr.append("IKE");
            numStr.append(todayNum);
            numStr.append("-");
            numStr.append("1");
        }
        model.setNumber(numStr.toString());
    }

    public LocalDateTime changeEndFormat(LocalDate date) throws SerException {
        String StartDayTime = date.toString() + " 23:59:59";
        return LocalDateTime.parse(StartDayTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public List<DriverDispatchFeeBO> findDispatchFree(Integer month) throws SerException {
        if (month != null) {
            if (month != null) {
                //按月份查询已付款的出车数
                StringBuilder sql = new StringBuilder("select car.driver , sum(*) as fee from dispatchcar_basicinfo car where 0 = 0");
                sql.append(" and month(car.dispatchDate) = " + month);
                sql.append(" and car.findType = " + FindType.PAYED.getCode());
                sql.append(" GROUP BY car.driver ");
                String[] fields = new String[]{"driver", "fee"};
                return super.findBySql(sql.toString(), DriverDispatchFeeBO.class, fields);
            } else {
                throw new SerException("查询月份不能为空!");
            }
        } else {
            throw new SerException("查询月份不能为空!");
        }
    }

    @Override
    public List<DriverDispatchsBO> findDispatchs(Integer month) throws SerException {
        if (month != null) {
            //按月份查询已付款的出车数
            StringBuilder sql = new StringBuilder("select car.driver , count(*) as sum from dispatchcar_basicinfo car where 0 = 0");
            sql.append(" and month(car.dispatchDate) = " + month);
            sql.append(" and car.findType = " + FindType.PAYED.getCode());
            sql.append(" GROUP BY car.driver ");
            String[] fields = new String[]{"driver", "sum"};
            return super.findBySql(sql.toString(), DriverDispatchsBO.class, fields);
        } else {
            throw new SerException("查询月份不能为空!");
        }
    }

    @Override
    public Double findOilAmount(String oilCardCode, Integer year, Integer month) throws SerException {

        String sql = "select addOilAmount , oilPrice from dispatchcar_basicinfo where oilCardNumber = '" + oilCardCode + "'"
                + "and month(addOilTime) = " + month
                + "and year(addOilTime) = " + year;
        String[] fields = new String[]{"addOilAmount", "oilPrice"};
        List<DispatchCarInfo> list = super.findBySql(sql, DispatchCarInfo.class, fields);

        if (!CollectionUtils.isEmpty(list)) {
            Double addOilAmount = list.stream().filter(p -> p.getAddOilAmount() != null).mapToDouble(DispatchCarInfo::getAddOilAmount).sum();
            Double oilPrice = list.stream().filter(p -> p.getOilPrice() != null).mapToDouble(DispatchCarInfo::getOilPrice).sum();
            return addOilAmount * oilPrice;
        } else {
            return 0.0;
        }
    }
}