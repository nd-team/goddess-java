package com.bjike.goddess.marketactivitymanage.service;

import com.alibaba.dubbo.common.json.Yylex;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketactivitymanage.api.MarketServeRecordAPI;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyDetailBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.excel.MarketServeApplyExcel;
import com.bjike.goddess.marketactivitymanage.excel.MarketServeApplyTemplateExprot;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyImprotTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import com.bjike.goddess.marketactivitymanage.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 市场招待申请业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.058 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketServeApplySerCache")
@Service
public class MarketServeApplySerImpl extends ServiceImpl<MarketServeApply, MarketServeApplyDTO> implements MarketServeApplySer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private CustomerInfoSer customerInfoSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarketServeRecordSer marketServeRecordSer;


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
        //商务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
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
     * 核对审核权限(层次)
     *
     * @throws SerException
     */
    private void checkAuditAPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();

        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是决策层人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
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
     * 核对审核权限（层次级别）
     */
    private Boolean guideAuditAIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.arrCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAuditA = guideAuditAIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAuditA) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case MONEYAUDIT:
                flag = guideIdentity();
                break;
            case DECISIONAUDIT:
                flag = guideAuditAIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询市场招待申请
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public List<MarketServeApplyBO> list(MarketServeApplyDTO dto) throws SerException {
        checkPermission();
        dto.getConditions().add(Restrict.ne("executiveAuditOpinion",1));
        List<MarketServeApply> list = super.findByPage(dto);
        List<MarketServeApplyBO> listBO = BeanTransform.copyProperties(list, MarketServeApplyBO.class);
        return listBO;
    }

    /**
     * 保存市场招待申请
     *
     * @param to 市场招待申请to
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MarketServeApplyBO save(MarketServeApplyTO to) throws SerException {
        checkPermission();//商务模块权限
        MarketServeApply marketServeApply = BeanTransform.copyProperties(to, MarketServeApply.class, true);
        marketServeApply = super.save(marketServeApply);
        MarketServeApplyBO bo = BeanTransform.copyProperties(marketServeApply, MarketServeApplyBO.class);
        return bo;
    }


    /**
     * 更新市场招待申请
     *
     * @param to 市场招待申请to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MarketServeApplyTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MarketServeApply model = super.findById(to.getId());
            if (model != null) {
                updateMarketServeApply(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新市场招待申请
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMarketServeApply(MarketServeApplyTO to, MarketServeApply model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 添加用户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addClientInfo(CustomerInfoTO to) throws SerException {
        String marketServeId = to.getMarketServeId();
        List<String> clientInfoNos = to.getClientInfoNos();//客户信息编号
        List<String> clientNames = to.getClientNames();//客户姓名
        List<String> importanceLevels = to.getImportanceLevels();//重要性级别

        if ((clientInfoNos != null) && (clientInfoNos.size() > 0)) {
            int clientSize = clientInfoNos.size();
            for (int i = 0; i < clientSize; i++) {
                CustomerInfoTO customerInfoTO = new CustomerInfoTO();
                customerInfoTO.setClientInfoNo(clientInfoNos.get(i));
                customerInfoTO.setClientName(clientNames.get(i));
                customerInfoTO.setImportanceLevel(importanceLevels.get(i));
                customerInfoTO.setMarketServeId(marketServeId);

                customerInfoSer.save(customerInfoTO);
            }
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param id                市场招待申请唯一标识
     * @param fundModuleOpinion 运营商务部资金模块意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException {
        checkPermission();
        MarketServeApply model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setFundModuleOpinion(fundModuleOpinion);
        super.update(model);
    }

    /**
     * 决策层意见
     *
     * @param id                    市场招待申请唯一标识
     * @param executiveAuditOpinion 决策层意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException {
        checkAuditAPermission();

        MarketServeApply model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setExecutiveAuditOpinion(executiveAuditOpinion);
        super.update(model);
        if(model.getExecutiveAuditOpinion().equals(AuditType.ALLOWED)){
            MarketServeRecord marketServeRecord = new MarketServeRecord();
            BeanUtils.copyProperties(model,marketServeRecord);
            marketServeRecord = marketServeRecordSer.save(marketServeRecord);
            List<CustomerInfoBO> list = customerInfoSer.findByMarketServeId(model.getId());
            if(null != list && list.size()!=0){
                for (CustomerInfoBO customerInfoBO : list){
                    CustomerInfo customerInfo = BeanTransform.copyProperties(customerInfoBO,CustomerInfo.class,true,"marketServeId","id");
                    customerInfo.setCreateTime(LocalDateTime.now());
                    customerInfo.setModifyTime(LocalDateTime.now());
                    customerInfo.setMarketServeId(marketServeRecord.getId());
                    customerInfoSer.save(customerInfo);
                }
            }
            //TODO : lijuntao插数据到借款记录表未明白
        }
    }

    @Override
    public MarketServeApplyBO getOne(String id) throws SerException {
        checkPermission();//商务模块权限
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        MarketServeApply marketServeApply = super.findById(id);
        return BeanTransform.copyProperties(marketServeApply, MarketServeApplyBO.class);
    }

    /**
     * 查看详情
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    @Override
    public MarketServeApplyDetailBO checkDetails(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        MarketServeApplyBO marketServeApplyBO = getOne(id);
        MarketServeApplyDetailBO marketServeApplyDetailBO = new MarketServeApplyDetailBO();
        BeanTransform.copyProperties(marketServeApplyBO, marketServeApplyDetailBO);
        List<CustomerInfoBO> customerInfoBOList = customerInfoSer.findByMarketServeId(id);
        marketServeApplyDetailBO.setCustomerInfos(customerInfoBOList);
        return marketServeApplyDetailBO;
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    public void editClientInfo(CustomerInfoTO to) throws SerException {
        checkPermission();
        String marketServeId = to.getMarketServeId();
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", marketServeId));
        List<CustomerInfo> list = customerInfoSer.findByCis(dto);
        customerInfoSer.remove(list);
        addClientInfo(to);
    }

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 判断是否临时招待
     *
     * @param obj
     * @return
     */
    private String getWhetherTemporaryServe(MarketServeApply obj) {
        Boolean whetherTemporaryServe = obj.getWhetherTemporaryServe();
        if (whetherTemporaryServe != null) {
            if (whetherTemporaryServe == Boolean.TRUE) {
                return "是";
            } else {
                return "否";
            }
        }

        return "否";
    }

    /**
     * 查看所有的项目名
     *
     * @return list
     * @throws SerException
     */
    @Override
    public List<String> findAllProjectName() throws SerException {
        List<MarketServeApply> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MarketServeApply model : list) {
            String projectName = model.getProjectName();
            if (StringUtils.isNotBlank(model.getProjectName())) {
                set.add(projectName);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 查看所有的地区
     *
     * @return list
     * @throws SerException
     */
    @Override
    public List<String> findAllAreas() throws SerException {
        List<MarketServeApply> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MarketServeApply model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 导出
     *
     * @param areas
     * @param startTime
     * @param endTime
     * @return
     * @throws SerException
     */
    @Override
    public byte[] exportExcel(String[] areas, String startTime, String endTime) throws SerException {
        checkPermission();
        List<MarketServeApplyExcel> toList = new ArrayList<MarketServeApplyExcel>();
        if (StringUtils.isNotBlank(startTime) && startTime.length() > 0) {
            LocalDateTime startTimeV = DateUtil.parseDateTime(startTime);//起始时间
            LocalDateTime endTimeV = DateUtil.parseDateTime(endTime);//结束时间
            LocalDateTime[] planActivityTiming = new LocalDateTime[]{startTimeV, endTimeV};

            MarketServeApplyDTO dto = new MarketServeApplyDTO();
            dto.getConditions().add(Restrict.between("planActivityTiming", planActivityTiming)); //时间范围查询

            if ((areas != null) && (areas.length > 0)) {
                dto.getConditions().add(Restrict.in("area", areas));
            }
            List<MarketServeApply> list = super.findByCis(dto);
            for (MarketServeApply marketServeApply : list) {
                String applyId = marketServeApply.getId();
                List<CustomerInfoBO> infos = customerInfoSer.findByMarketServeId(applyId);
                StringBuffer clientinfo = new StringBuffer();
                if (!CollectionUtils.isEmpty(infos)) {
                    for (CustomerInfoBO customerInfoBO : infos) {
                        if (StringUtils.isNoneBlank(customerInfoBO.getClientName())) {
                            clientinfo.append(customerInfoBO.getClientInfoNo());
                            clientinfo.append(",");
                            clientinfo.append(customerInfoBO.getClientName());
                            clientinfo.append(",");
                            clientinfo.append(customerInfoBO.getImportanceLevel());
                            clientinfo.append(";");
                        }
                    }
                }

                MarketServeApplyExcel excel = BeanTransform.copyProperties(marketServeApply, MarketServeApplyExcel.class, "whetherTemporaryServe", "executiveAuditOpinion");
                String whetherTemporaryServe = getWhetherTemporaryServe(marketServeApply);
                excel.setWhetherTemporaryServe(whetherTemporaryServe);

                if (marketServeApply.getExecutiveAuditOpinion() != null) {
                    excel.setExecutiveAuditOpinion(AuditType.exportStrConvert(marketServeApply.getExecutiveAuditOpinion()));
                } else {
                    excel.setExecutiveAuditOpinion("");
                }
                excel.setClientinfo(clientinfo.toString());
                toList.add(excel);
            }

        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    /**
     * 导入
     *
     * @param marketServeApplyImprotTOS 市场活动申请记录
     * @throws SerException
     */
    @Override
    public void importExcel(List<MarketServeApplyImprotTO> marketServeApplyImprotTOS) throws SerException {
        checkPermission();
        for (MarketServeApplyImprotTO marketServeApplyImprotTO : marketServeApplyImprotTOS) {
            MarketServeApply marketServeApply = new MarketServeApply();
            BeanTransform.copyProperties(marketServeApplyImprotTO, marketServeApply, true);
            marketServeApply.setCreateTime(LocalDateTime.now());
            marketServeApply.setModifyTime(LocalDateTime.now());
            marketServeApply = super.save(marketServeApply);
            if (StringUtils.isNotBlank(marketServeApplyImprotTO.getClientinfo())) {
                String clients = marketServeApplyImprotTO.getClientinfo().substring(0, marketServeApplyImprotTO.getClientinfo().length() - 1);
                String[] clientinfos = clients.split(";");
                for (String clientinfo : clientinfos) {
                    String[] clientinfoV = clientinfo.split(",");
                    CustomerInfo customerInfo = new CustomerInfo();
                    String clientInfoNo = clientinfoV[0];
                    String clientName = clientinfoV[1];
                    String importanceLevel = clientinfoV[2];

                    customerInfo.setClientInfoNo(clientInfoNo);
                    customerInfo.setClientName(clientName);
                    customerInfo.setImportanceLevel(importanceLevel);
                    customerInfo.setMarketServeId(marketServeApply.getId());
                    customerInfo.setCreateTime(LocalDateTime.now());
                    customerInfo.setModifyTime(LocalDateTime.now());
                    customerInfoSer.save(customerInfo);
                }
            }
        }
    }

    @Override
    public byte[] templateExport() throws SerException {
        // checkPermission();
        List<MarketServeApplyTemplateExprot> marketServeApplyTemplateExprots = new ArrayList<>();

        MarketServeApplyTemplateExprot excel = new MarketServeApplyTemplateExprot();
        excel.setPlanActivityTiming(LocalDateTime.now());
        excel.setArea("广州");
        excel.setPurpose("调查");
        excel.setMarketInfoNo("SSSSSSSS");
        excel.setProjectCode("001001");
        excel.setProjectName("issp项目");
        excel.setProjectNature("活动");
        excel.setPredictAttendNo(1000);
        excel.setPlanActivityType("活动");
        excel.setClassify("类型");
        excel.setPredictCharge(10000d);
        excel.setServePrincipal("李玉刚");
        excel.setWhetherTemporaryServe("是");
        excel.setYyFundModule("资金运营");
        excel.setFundModuleOpinion("意见");
        excel.setDecisionLevel("test");
        excel.setExecutiveAuditOpinion("通过");
        excel.setClientinfo("客户编号,客户姓名,客户等级;");
        marketServeApplyTemplateExprots.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(marketServeApplyTemplateExprots, exce);
        return bytes;
    }
}