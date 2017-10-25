package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.*;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.entity.BusinessContract;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.excel.BusinessContractExport;
import com.bjike.goddess.businessproject.excel.BusinessContractTemplateExcel;
import com.bjike.goddess.businessproject.to.BusinessContractTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商务项目合同业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class BusinessContractSerImpl extends ServiceImpl<BusinessContract, BusinessContractDTO> implements BusinessContractSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MessageAPI messageAPI;

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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
    public Long count(BusinessContractDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public BusinessContractsBO getOneById(String id) throws SerException {
        BusinessContract contract = super.findById(id);
        BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
        return bo;
    }

    @Override
    public List<BusinessContractsBO> list(BusinessContractDTO dto) throws SerException {
        checkSeeIdentity();
        search(dto);
        List<BusinessContract> contracts = super.findByCis(dto);
        List<BusinessContractsBO> contractBOS = BeanTransform.copyProperties(contracts, BusinessContractsBO.class);
        return contractBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO add(BusinessContractTO to) throws SerException {
        checkAddIdentity();
        BusinessContract contract = BeanTransform.copyProperties(to, BusinessContract.class, true);
        contract.setCreateTime(LocalDateTime.now());
        MessageTO messageTO = new MessageTO();
        messageTO.setContent(to.getContent());
        messageTO.setTitle("提供意见");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);

//        messageTO.setReceivers(emails);
        messageAPI.send(messageTO);
        super.save(contract);

        BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO edit(BusinessContractTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            LocalDateTime createTime = contract.getCreateTime();
            contract = BeanTransform.copyProperties(to, BusinessContract.class, true);
            contract.setCreateTime(createTime);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    private void search(BusinessContractDTO dto) throws SerException {
        //签订时间
        if (StringUtils.isNotBlank(dto.getSignedTime())) {
            dto.getConditions().add(Restrict.eq("signedTime", dto.getSignedTime()));
        }
        //地区
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.like("area", dto.getArea()));
        }
        //业务类型
        if (StringUtils.isNotBlank(dto.getBusinessType())) {
            dto.getConditions().add(Restrict.like("businessType", dto.getBusinessType()));
        }
        //总包单位名称
        if (StringUtils.isNotBlank(dto.getMajorCompany())) {
            dto.getConditions().add(Restrict.like("majorCompany", dto.getMajorCompany()));
        }
        //所属项目组
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            dto.getConditions().add(Restrict.like("projectGroup", dto.getProjectGroup()));
        }
        //内部项目名称
        if (StringUtils.isNotBlank(dto.getInnerProject())) {
            dto.getConditions().add(Restrict.like("innerProject", dto.getInnerProject()));
        }
        //项目负责人
        if (StringUtils.isNotBlank(dto.getProjectCharge())) {
            dto.getConditions().add(Restrict.like("projectCharge", dto.getProjectCharge()));
        }
        //是否闭单
        if (null != dto.getCloseSingle()) {
            dto.getConditions().add(Restrict.eq("closeSingle", dto.getCloseSingle()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO managerIdea(BusinessContractTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO planIdea(BusinessContractTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO budgetIdea(BusinessContractTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO hadContract(BusinessContractTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            if(to.getMakeContract().equals(MakeContract.HADMAKE)){
                MessageTO messageTO = new MessageTO();
                messageTO.setContent(to.getContent());
                messageTO.setTitle("协助函");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);

//        messageTO.setReceivers(emails);
                messageAPI.send(messageTO);
            }
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO advance(BusinessContractTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            contract.setModifyTime(LocalDateTime.now());
            contract.setGuarantor(userBO.getUsername());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO changes(BusinessContractTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessContractsBO notification(BusinessContractTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BusinessContract contract = super.findById(to.getId());
            BeanTransform.copyProperties(to, contract, true);
            contract.setModifyTime(LocalDateTime.now());
            contract.setNotificationTime(LocalDate.now());
            super.update(contract);
            BusinessContractsBO bo = BeanTransform.copyProperties(contract, BusinessContractsBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<BusinessContractADetailBO> collect(String[] areas) throws SerException {
        List<BusinessContractADetailBO> aDetailBOS = new ArrayList<>();
        List<BusinessContractDetailBO> boList = new ArrayList<>();
        List<BusinessContractDetailBO> contractDetailBOS = new ArrayList<>();
        if (areas == null && areas.length <= 0) {
            throw new SerException("汇总失败,请选择地区");
        }

        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areasStr = StringUtils.join(areasTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ");
        sb.append(" a.area AS area,a.major AS major,a.internalProjectNum AS internalProjectNum, ");
        sb.append(" a.innerProject AS innerProject,sum(a.makeMoney) AS makeMoney,sum(a.forecastMoney) AS forecastMoney, ");
        sb.append(" sum(a.scale) AS scale,sum(a.forecastRoundMoney) AS forecastRoundMoney, ");
        sb.append(" sum(a.forecastMarchMoney) AS forecastMarchMoney,sum(a.estimatedMarketLosses) AS estimatedMarketLosses, ");
        sb.append(" MAX( CASE WHEN makeContract=1 THEN count END ) AS hadMakeNum, ");
        sb.append(" MAX( CASE WHEN makeContract=0 THEN count END ) AS noMakeNum, ");
        sb.append(" MAX( CASE WHEN makeContract=2 THEN count END ) AS notMakeNum, ");
        sb.append(" sum(a.scaleContract) AS scaleContract,a.projectCharge AS projectCharge, ");
        sb.append(" a.majorCompany AS majorCompany,a.subCompany AS subCompany ");
        sb.append(" FROM ");
        sb.append(" (SELECT count(*) AS count,makeContract as makeContract, ");
        sb.append(" area,major,internalProjectNum,innerProject,projectCharge,majorCompany,subCompany, ");
        sb.append(" sum(makeMoney) AS makeMoney,sum(forecastMoney) AS forecastMoney, ");
        sb.append(" sum(scale) AS scale,sum(forecastFinishMoney) AS forecastRoundMoney, ");
        sb.append(" sum(forecastMarchMoney) AS forecastMarchMoney,sum(estimatedMarketLosses) AS estimatedMarketLosses, ");
        sb.append(" sum(scaleContract) AS scaleContract ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE area IN (%s) ");
        sb.append(" GROUP BY makeContract,area,major,internalProjectNum,innerProject, projectCharge,majorCompany,subCompany)a ");
        sb.append(" GROUP BY a.area,a.major,a.internalProjectNum,a.innerProject, a.projectCharge,a.majorCompany,a.subCompany ");
        String[] fields = new String[]{"area", "major", "internalProjectNum", "innerProject", "makeMoney", "forecastMoney",
                "scale", "forecastRoundMoney", "forecastMarchMoney", "estimatedMarketLosses", "hadMakeNum", "noMakeNum", "notMakeNum",
                "scaleContract", "projectCharge", "majorCompany", "subCompany"};
        String sql = sb.toString();
        sql = String.format(sql, areasStr);
        List<BusinessContractDetailBO> detailBOS = super.findBySql(sql, BusinessContractDetailBO.class, fields);
        Double makeUnit = 0.0;
        Double forecastUnit = 0.0;
        //todo 结算完成金额，预估确认时间，预估转正时间，实际完成规模数量需求方也不知道从哪拿
        for (BusinessContractDetailBO bo : detailBOS) {
            //立项总单价(立项总金额/合同规模数量)
            makeUnit = bo.getMakeMoney() / bo.getScaleContract();
            //预估单价(预估总金额/预估规模)
            forecastUnit = bo.getForecastMoney() / bo.getScale();
            bo.setMakeUnit(makeUnit);
            bo.setForecastUnit(forecastUnit);
            //未进场
            String[] notApproachField = new String[]{"notApproach", "area"};
            String notApproachSql = " SELECT count(notApproach) AS notApproach ,area AS area FROM businessproject_businesscontract " +
                    " WHERE notApproach='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(notApproachSql, BusinessContractDetailBO.class, notApproachField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setNotApproach(bo1.getNotApproach());
            }
            //进场
            String[] approachField = new String[]{"approach", "area"};
            String approachSql = " SELECT count(approach) AS approach ,area AS area FROM businessproject_businesscontract " +
                    " WHERE approach='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(approachSql, BusinessContractDetailBO.class, approachField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setApproach(bo1.getApproach());
            }
            //停工
            String[] shutdownField = new String[]{"shutdown", "area"};
            String shutdownSql = " SELECT count(shutdown) AS shutdown ,area AS area FROM businessproject_businesscontract " +
                    " WHERE shutdown='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(shutdownSql, BusinessContractDetailBO.class, shutdownField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setShutdown(bo1.getShutdown());
            }
            //进行
            String[] marchField = new String[]{"march", "area"};
            String marchSql = " SELECT count(march) AS march ,area AS area FROM businessproject_businesscontract " +
                    " WHERE march='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(marchSql, BusinessContractDetailBO.class, marchField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setMarch(bo1.getMarch());
            }
            //完工
            String[] completeField = new String[]{"complete", "area"};
            String completeSql = " SELECT count(complete) AS complete ,area AS area FROM businessproject_businesscontract " +
                    " WHERE complete='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(completeSql, BusinessContractDetailBO.class, completeField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setComplete(bo1.getComplete());
            }
            //到货
            String[] goodsField = new String[]{"goods", "area"};
            String goodsSql = " SELECT count(goods) AS goods ,area AS area FROM businessproject_businesscontract " +
                    " WHERE goods='到货' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(goodsSql, BusinessContractDetailBO.class, goodsField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setGoods(bo1.getGoods());
            }
            //初验
            String[] initialField = new String[]{"initialTest", "area"};
            String initialSql = " SELECT count(initialTest) AS initialTest ,area AS area FROM businessproject_businesscontract " +
                    " WHERE initialTest='完成' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(initialSql, BusinessContractDetailBO.class, initialField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setInitialTest(bo1.getInitialTest());
            }
            //终验
            String[] finalField = new String[]{"finalTest", "area"};
            String finalSql = " SELECT count(finalTest) AS finalTest ,area AS area FROM businessproject_businesscontract " +
                    " WHERE finalTest='完成' AND area = '" + bo.getArea() + "'GROUP BY area ";
            contractDetailBOS = super.findBySql(finalSql, BusinessContractDetailBO.class, finalField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setFinalTest(bo1.getFinalTest());
            }
            //2014立项金额
            String[] aField = new String[]{"makeMoneyA", "area"};
            String aSql = " SELECT sum(makeMoney) AS makeMoneyA,area AS  area FROM businessproject_businesscontract " +
                    " WHERE year(signedTime)='2014' AND area = '" + bo.getArea() + "' GROUP BY area ";
            contractDetailBOS = super.findBySql(aSql, BusinessContractDetailBO.class, aField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setMakeMoneyA(bo1.getMakeMoneyA());
            }
            //2015立项金额
            String[] bField = new String[]{"makeMoneyB", "area"};
            String bSql = " SELECT sum(makeMoney) AS makeMoneyB,area AS  area FROM businessproject_businesscontract " +
                    " WHERE year(signedTime)='2015' AND area = '" + bo.getArea() + "' GROUP BY area ";
            contractDetailBOS = super.findBySql(bSql, BusinessContractDetailBO.class, bField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setMakeMoneyB(bo1.getMakeMoneyB());
            }
            //2016立项金额
            String[] cField = new String[]{"makeMoneyC", "area"};
            String cSql = " SELECT sum(makeMoney) AS makeMoneyC,area AS  area FROM businessproject_businesscontract " +
                    " WHERE year(signedTime)='2016' AND area = '" + bo.getArea() + "' GROUP BY area ";
            contractDetailBOS = super.findBySql(cSql, BusinessContractDetailBO.class, cField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setMakeMoneyC(bo1.getMakeMoneyC());
            }
            //2017立项金额
            String[] dField = new String[]{"makeMoneyD", "area"};
            String dSql = " SELECT sum(makeMoney) AS makeMoneyD,area AS  area FROM businessproject_businesscontract " +
                    " WHERE year(signedTime)='2017' AND area = '" + bo.getArea() + "' GROUP BY area ";
            contractDetailBOS = super.findBySql(dSql, BusinessContractDetailBO.class, dField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setMakeMoneyD(bo1.getMakeMoneyD());
            }
            //2018立项金额
            String[] eField = new String[]{"makeMoneyE", "area"};
            String eSql = " SELECT sum(makeMoney) AS makeMoneyE,area AS  area FROM businessproject_businesscontract " +
                    " WHERE year(signedTime)='2018' AND area = '" + bo.getArea() + "' GROUP BY area ";
            contractDetailBOS = super.findBySql(eSql, BusinessContractDetailBO.class, eField);
            for (BusinessContractDetailBO bo1 : contractDetailBOS) {
                bo.setMakeMoneyE(bo1.getMakeMoneyE());
            }
            boList.add(bo);

        }
        List<BusinessContractBDetailBO> bDetailBOS = new ArrayList<>();
        Set<String> majors = boList.stream().map(BusinessContractDetailBO::getMajor).collect(Collectors.toSet());
        Double totalMoney = 0.0;
        for (String major : majors) {
            List<BusinessContractDetailBO> bos = boList.stream().filter(businessContractDetailBO -> major.equals(businessContractDetailBO.getMajor())).collect(Collectors.toList());
            bDetailBOS = BeanTransform.copyProperties(bos, BusinessContractBDetailBO.class);
            totalMoney = bDetailBOS.stream().filter(p -> p.getMakeMoney() != null).mapToDouble(p -> p.getMakeMoney()).sum();
            BusinessContractADetailBO aDetailBO = new BusinessContractADetailBO();
            aDetailBO.setTotalMoney(totalMoney);
            aDetailBO.setBusinessContractBDetailBOS(bDetailBOS);
            aDetailBOS.add(aDetailBO);
        }
        return aDetailBOS;
    }

    @Override
    public List<BusinessContractProgressBO> dayCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startTime = time;
        String endTime = time;
        return progressCollect(startTime, endTime);
    }

    @Override
    public List<BusinessContractProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractProgressBO> monthCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractProgressBO> yearCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractProgressBO> totalCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        return progressCollect(startDate, endDate);
    }

    @Override
    public OptionBO dayAreaScaleFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;

        String text_1 = "各地区合同规模数日汇总" + "(" + startDate + "-" + endDate + ")";
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekAreaScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各地区合同规模数周汇总" + startDate + "-" + endDate;
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthAreaScaleFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各地区合同规模数月汇总" + startDate + "-" + endDate;
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO quarterAreaScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各地区合同规模数周汇总" + startDate + "-" + endDate;
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO yearAreaScaleFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各地区合同规模数年汇总" + startDate + "-" + endDate;
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalAreaScaleFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各地区合同规模数累计汇总" + "(累计)";
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO dayProjectGroupScaleFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各项目组合同规模数日汇总" + startDate + "-" + endDate;
        return projectGroupScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekProjectGroupScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各所属项目组合同规模数周汇总" + startDate + "-" + endDate;
        return projectGroupScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthProjectGroupScaleFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各所属项目组合同规模数月汇总" + startDate + "-" + endDate;
        return projectGroupScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO quarterProjectGroupScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各所属项目组合同规模数周汇总" + startDate + "-" + endDate;
        return projectGroupScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO yearProjectGroupScaleFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各所属项目组合同规模数年汇总" + startDate + "-" + endDate;
        return projectGroupScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalProjectGroupScaleFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各所属项目组合同规模数累计汇总" + "(累计)";
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO dayMajorScaleFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各专业/工期合同规模数日汇总" + startDate + '-' + endDate;
        return majorScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekMajorScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各专业/工期合同规模数周汇总" + startDate + "-" + endDate;
        return areaScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthMajorScaleFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各专业/工期合同规模数月汇总" + startDate + "-" + endDate;
        return majorScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO quarterMajorScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各专业/工期合同规模数周汇总" + startDate + "-" + endDate;
        return majorScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO yearMajorScaleFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各专业/工期合同规模数年汇总" + startDate + "-" + endDate;
        return majorScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalMajorScaleFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各专业/工期合同规模数累计汇总" + "(累计)";
        return majorScaleFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO dayAreaMakeFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各地区合同立项情况金额图表日汇总" + startDate + "-" + endDate;
        return areaMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO weekAreaMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各地区合同立项情况金额周汇总" + startDate + "-" + endDate;
        return areaMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO monthAreaMakeFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各地区合同立项情况金额月汇总" + startDate + "-" + endDate;
        return areaMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO quarterAreaMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各地区合同立项情况金额周汇总" + startDate + "-" + endDate;
        return areaMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO yearAreaMakeFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各地区合同立项情况金额年汇总" + startDate + "-" + endDate;
        return areaMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO totalAreaMakeFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各地区合同立项情况金额累计汇总" + "(累计)";
        return areaMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO dayProjectGroupMakeFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各所属项目组合同立项情况金额图表日汇总" + startDate + "-" + endDate;
        return projectGroupMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO weekProjectGroupMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各所属项目组合同立项情况金额图表周汇总" + startDate + "-" + endDate;
        return projectGroupMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO monthProjectGroupMakeFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各所属项目组合同立项情况金额图表月汇总" + startDate + "-" + endDate;
        return projectGroupMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO quarterProjectGroupMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各所属项目组合同立项情况金额周汇总" + startDate + "-" + endDate;
        return projectGroupMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO yearProjectGroupMakeFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各所属项目组合同立项情况金额图表年汇总" + startDate + "-" + endDate;
        return projectGroupMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO totalProjectGroupMakeFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各所属项目组合同立项情况金额图表累计汇总" + "(累计)";
        return projectGroupMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO dayMajorMakeFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各专业/工期合同立项情况金额图表日汇总" + startDate + "-" + endDate;
        return majorMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO weekMajorMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各专业/工期合同立项情况金额图表周汇总" + startDate + "-" + endDate;
        return majorMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO monthMajorMakeFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各专业/工期合同立项情况金额图表月汇总" + startDate + "-" + endDate;
        return majorMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO quarterMajorMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各专业/工期合同立项情况金额周汇总" + startDate + "-" + endDate;
        return majorMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO yearMajorMakeFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各专业/工期合同立项情况金额图表年汇总" + startDate + "-" + endDate;
        return majorMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionMakeBO totalMajorMakeFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各专业/工期合同立项情况金额图表累计汇总" + "(累计)";
        return majorMakeFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO dayAreaMakeCaseFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各地区合同立项情况图表日汇总" + startDate + "-" + endDate;
        return areaMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekAreaMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各地区合同立项情况图表周汇总" + startDate + "-" + endDate;
        return areaMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthAreaMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各地区合同立项情况图表月汇总" + startDate + "-" + endDate;
        return areaMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO quarterAreaMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各地区合同立项情况图表周汇总" + startDate + "-" + endDate;
        return areaMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO yearAreaMakeCaseFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各地区合同立项情况图表年汇总" + startDate + "-" + endDate;
        return areaMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalAreaMakeCaseFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各地区合同立项情况图表累计汇总" + "(累计)";
        return areaMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO dayProjectGroupMakeCaseFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各所属项目组合同立项情况图表日汇总" + startDate + "-" + endDate;
        return projectGroupMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekProjectGroupMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各所属项目组合同立项情况图表周汇总" + startDate + "-" + endDate;
        return projectGroupMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthProjectGroupMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各所属项目组合同立项情况图表月汇总" + startDate + "-" + endDate;
        return projectGroupMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO quarterProjectGroupMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各所属项目组合同立项情况图表周汇总" + startDate + "-" + endDate;
        return projectGroupMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO yearProjectGroupMakeCaseFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各所属项目组合同立项情况图表年汇总" + startDate + "-" + endDate;
        return projectGroupMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalProjectGroupMakeCaseFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各所属项目组合同立项情况图表累计汇总" + "(累计)";
        return projectGroupMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO dayMajorMakeCaseFigureCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        String text_1 = "各专业/工期合同立项情况图表日汇总" + startDate + "-" + endDate;
        return majorMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO weekMajorMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "各专业/工期合同立项情况图表周汇总" + startDate + "-" + endDate;
        return majorMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthMajorMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "各专业/工期合同立项情况图表月汇总" + startDate + "-" + endDate;
        return majorMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO quarterMajorMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        String text_1 = "各专业/工期合同立项情况图表周汇总" + startDate + "-" + endDate;
        return majorMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO yearMajorMakeCaseFigureCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        String text_1 = "各专业/工期合同立项情况图表年汇总" + startDate + "-" + endDate;
        return majorMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalMajorMakeCaseFigureCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  " + getTableName(BusinessContract.class) + " where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "各专业/工期合同立项情况图表累计汇总" + "(累计)";
        return majorMakeCaseFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public BrokenOptionBO scaleNum(Integer year) throws SerException {
        year = year == null ? (LocalDate.now().getYear()) : year;
        List<ScaleContractFigureBO> figureBOS = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String startDate = DateUtil.dateToString(LocalDate.of(year, i, 1));
            String endDate = DateUtil.dateToString(LocalDate.of(year, i, DateUtil.getDayByDate(year, i)));
            String[] date = new String[]{startDate, endDate};
            BusinessContractDTO dto = new BusinessContractDTO();
            dto.getConditions().add(Restrict.between("signedTime", date));
            List<BusinessContract> businessContracts = super.findByCis(dto);
            //规模数量(总规模数量)
            Integer scaleContract = 0;
            //实际完成规模数量
            Integer finishScale = 0;
            if (businessContracts != null && businessContracts.size() > 0) {
                scaleContract = businessContracts.stream().filter(p -> p.getScaleContract() != null).mapToInt(p -> p.getScaleContract()).sum();
                for (BusinessContract bo : businessContracts) {
                    scaleContract = bo.getScaleContract();
                }
            }
            ScaleContractFigureBO bo = new ScaleContractFigureBO();
            bo.setYearMonth(i + "月");
            bo.setScaleContract(scaleContract);
            bo.setFinishScale(0);
            figureBOS.add(bo);

        }
        Integer scaleContractSum = 0;
        scaleContractSum = figureBOS.stream().filter(p -> p.getScaleContract() != null).mapToInt(p -> p.getScaleContract()).sum();
        ScaleContractFigureBO bo1 = new ScaleContractFigureBO();
        bo1.setYearMonth("合计");
        bo1.setScaleContract(scaleContractSum);
        bo1.setFinishScale(0);
        figureBOS.add(bo1);
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(year + "年合同规模数汇总");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"规模数量", "实际完成规模数量"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(false);
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);


        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> saleContract = new ArrayList<>();
            List<Integer> finishScale = new ArrayList<>();
            for (ScaleContractFigureBO scaleContractFigureBO : figureBOS) {
                text_list_3.add(scaleContractFigureBO.getYearMonth());

                //柱状图数据
                saleContract.add(scaleContractFigureBO.getScaleContract());
                finishScale.add(scaleContractFigureBO.getFinishScale());
            }
            Integer[] saleContract_str = new Integer[saleContract.size()];
            saleContract_str = saleContract.toArray(saleContract_str);
            Integer[] finishScale_str = new Integer[finishScale.size()];
            finishScale_str = finishScale.toArray(finishScale_str);
            SeriesBO seriesBO1 = new SeriesBO();
            seriesBO1.setName("规模数量");
            seriesBO1.setType("line");
            seriesBO1.setData(saleContract_str);
            seriesBOList.add(seriesBO1);
            SeriesBO seriesBO2 = new SeriesBO();
            //todo 实际完成规模数量需求方也不知道从哪拿
            seriesBO2.setName("实际完成规模数量");
            seriesBO2.setType("line");
            seriesBO2.setData(finishScale_str);
            seriesBOList.add(seriesBO2);
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        BrokenOptionBO brokenOptionBO = new BrokenOptionBO();
        brokenOptionBO.setTitle(titleBO);
        brokenOptionBO.setTooltipBO(tooltipBO);
        brokenOptionBO.setLegend(legendBO);
        brokenOptionBO.setxAxis(xAxisBO);
        brokenOptionBO.setyAxis(yAxisBO);

        brokenOptionBO.setSeries(text_4);
        return brokenOptionBO;

    }

    @Override
    public BrokenOptionMakeBO makeMoney(Integer year) throws SerException {
        year = year == null ? (LocalDate.now().getYear()) : year;
        List<MakeCaseFigureBO> figureBOS = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String startDate = DateUtil.dateToString(LocalDate.of(year, i, 1));
            String endDate = DateUtil.dateToString(LocalDate.of(year, i, DateUtil.getDayByDate(year, i)));
            String[] date = new String[]{startDate, endDate};
            BusinessContractDTO dto = new BusinessContractDTO();
            dto.getConditions().add(Restrict.between("signedTime", date));
            List<BusinessContract> businessContracts = super.findByCis(dto);
            //已派工金额
            Double taskMoney = 0.0;
            //立项总金额
            Double makeMoney = 0.0;
            //预估总金额
            Double forecastMoney = 0.0;
            taskMoney = businessContracts.stream().filter(p -> p.getTaskMoney() != null).mapToDouble(p -> p.getTaskMoney()).sum();
            makeMoney = businessContracts.stream().filter(p -> p.getMakeMoney() != null).mapToDouble(p -> p.getMakeMoney()).sum();
            forecastMoney = businessContracts.stream().filter(p -> p.getForecastMoney() != null).mapToDouble(p -> p.getForecastMoney()).sum();
            if (businessContracts != null && businessContracts.size() > 0) {
                for (BusinessContract bo : businessContracts) {
                    taskMoney = bo.getTaskMoney();
                    makeMoney = bo.getMakeMoney();
                    forecastMoney = bo.getForecastMoney();
                }
            }
            MakeCaseFigureBO bo = new MakeCaseFigureBO();
            bo.setYearMonth(i + "月");
            bo.setTaskMoney(taskMoney);
            bo.setMakeMoney(makeMoney);
            bo.setForecastMoney(forecastMoney);
            figureBOS.add(bo);
        }
        Double taskMoneySum = figureBOS.stream().filter(p -> p.getTaskMoney() != null).mapToDouble(p -> p.getTaskMoney()).sum();
        Double makeMoneySum = figureBOS.stream().filter(p -> p.getMakeMoney() != null).mapToDouble(p -> p.getMakeMoney()).sum();
        Double forecastMoneySum = figureBOS.stream().filter(p -> p.getForecastMoney() != null).mapToDouble(p -> p.getForecastMoney()).sum();
        MakeCaseFigureBO bo1 = new MakeCaseFigureBO();
        bo1.setYearMonth("合计");
        bo1.setTaskMoney(taskMoneySum);
        bo1.setMakeMoney(makeMoneySum);
        bo1.setForecastMoney(forecastMoneySum);
        figureBOS.add(bo1);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(year + "年合同立项情况金额汇总");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"已派工金额", "立项总金额", "预估总金额"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(false);
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);


        List<SeriesBBO> seriesBOList = new ArrayList<>();

        if (figureBOS != null && figureBOS.size() > 0) {
            List<Double> taskMoney = new ArrayList<>();
            List<Double> makeMoney = new ArrayList<>();
            List<Double> forecastMoney = new ArrayList<>();
            for (MakeCaseFigureBO makeCaseFigureBO : figureBOS) {
                text_list_3.add(makeCaseFigureBO.getYearMonth());

                //柱状图数据
                taskMoney.add(makeCaseFigureBO.getTaskMoney());
                makeMoney.add(makeCaseFigureBO.getMakeMoney());
                forecastMoney.add(makeCaseFigureBO.getForecastMoney());
            }
            Double[] taskMoney_str = new Double[taskMoney.size()];
            taskMoney_str = taskMoney.toArray(taskMoney_str);
            Double[] makeMoney_str = new Double[makeMoney.size()];
            makeMoney_str = makeMoney.toArray(makeMoney_str);
            Double[] forecastMoney_str = new Double[forecastMoney.size()];
            forecastMoney_str = makeMoney.toArray(forecastMoney_str);
            SeriesBBO seriesBO1 = new SeriesBBO();
            seriesBO1.setName("已派工金额");
            seriesBO1.setType("line");
            seriesBO1.setData(taskMoney_str);
            seriesBOList.add(seriesBO1);
            SeriesBBO seriesBO2 = new SeriesBBO();
            seriesBO2.setName("立项总金额");
            seriesBO2.setType("line");
            seriesBO2.setData(makeMoney_str);
            seriesBOList.add(seriesBO2);
            SeriesBBO seriesBO3 = new SeriesBBO();
            seriesBO3.setName("预估总金额");
            seriesBO3.setType("line");
            seriesBO3.setData(forecastMoney_str);
            seriesBOList.add(seriesBO3);
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        BrokenOptionMakeBO brokenOptionBO = new BrokenOptionMakeBO();
        brokenOptionBO.setTitle(titleBO);
        brokenOptionBO.setTooltipBO(tooltipBO);
        brokenOptionBO.setLegend(legendBO);
        brokenOptionBO.setxAxis(xAxisBO);
        brokenOptionBO.setyAxis(yAxisBO);

        brokenOptionBO.setSeries(text_4);
        return brokenOptionBO;
    }

    @Override
    public BrokenOptionBO makeCase(Integer year) throws SerException {
        year = year == null ? (LocalDate.now().getYear()) : year;
        List<MakeCaseCollectFigureBO> figureBOS = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String startDate = DateUtil.dateToString(LocalDate.of(year, i, 1));
            String endDate = DateUtil.dateToString(LocalDate.of(year, i, DateUtil.getDayByDate(year, i)));
            String[] date = new String[]{startDate, endDate};
            BusinessContractDTO dto = new BusinessContractDTO();
            dto.getConditions().add(Restrict.between("signedTime", date));
            List<BusinessContract> businessContracts = super.findByCis(dto);
            //内部项目名称数量
            Long innerNameNum = businessContracts.stream().filter(p -> p.getInnerProject() != null).count();
            //已立项合同单数数量
            Long hadNum = businessContracts.stream().filter(p -> (p.getMakeContract() != null) && p.getMakeContract().equals(MakeContract.HADMAKE)).count();
            //未立项合同单数数量
            Long noNum = businessContracts.stream().filter(p -> (p.getMakeContract() != null) && p.getMakeContract().equals(MakeContract.NOMAKE)).count();
            //不立项合同单数数量
            Long notNum = businessContracts.stream().filter(p -> (p.getMakeContract() != null) && p.getMakeContract().equals(MakeContract.NOTMAKE)).count();
            //已完工单数数量
            Long completeNum = businessContracts.stream().filter(p -> (p.getComplete() != null) && p.getComplete().equals("是")).count();
            //进行
            Long march = businessContracts.stream().filter(p -> (p.getMarch() != null) && p.getMarch().equals("是")).count();
            MakeCaseCollectFigureBO bo = new MakeCaseCollectFigureBO();
            bo.setYearMonth(i + "月");
            bo.setInnerNameNum(Integer.valueOf(String.valueOf(innerNameNum)));
            bo.setHadNum(Integer.valueOf(String.valueOf(hadNum)));
            bo.setNoNum(Integer.valueOf(String.valueOf(noNum)));
            bo.setNotNum(Integer.valueOf(String.valueOf(notNum)));
            bo.setCompleteNum(Integer.valueOf(String.valueOf(completeNum)));
            bo.setMarch(Integer.valueOf(String.valueOf(march)));
            figureBOS.add(bo);
        }
        //内部项目名称数量
        Integer innerNameNumSum = figureBOS.stream().filter(p -> p.getInnerNameNum() != null).mapToInt(p -> p.getInnerNameNum()).sum();
        //已立项合同单数数量
        Integer hadNumSum = figureBOS.stream().filter(p -> p.getHadNum() != null).mapToInt(p -> p.getHadNum()).sum();
        //未立项合同单数数量
        Integer noNumSum = figureBOS.stream().filter(p -> p.getNoNum() != null).mapToInt(p -> p.getNoNum()).sum();
        //不立项合同单数数量
        Integer notNumSum = figureBOS.stream().filter(p -> p.getNotNum() != null).mapToInt(p -> p.getNotNum()).sum();
        //已完工单数数量
        Integer completeNumSum = figureBOS.stream().filter(p -> p.getCompleteNum() != null).mapToInt(p -> p.getCompleteNum()).sum();
        //进行
        Integer marchSum = figureBOS.stream().filter(p -> p.getMarch() != null).mapToInt(p -> p.getMarch()).sum();
        MakeCaseCollectFigureBO bo1 = new MakeCaseCollectFigureBO();
        bo1.setInnerNameNum(innerNameNumSum);
        bo1.setHadNum(hadNumSum);
        bo1.setNoNum(noNumSum);
        bo1.setNotNum(notNumSum);
        bo1.setCompleteNum(completeNumSum);
        bo1.setMarch(marchSum);
        figureBOS.add(bo1);
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(year + "年合同立项情况汇总");

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("axis");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        xAxisBO.setType("category");
        xAxisBO.setBoundaryGap(false);
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);


        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> innerNameNum = new ArrayList<>();
            List<Integer> hadNum = new ArrayList<>();
            List<Integer> noNum = new ArrayList<>();
            List<Integer> notNum = new ArrayList<>();
            List<Integer> completeNum = new ArrayList<>();
            List<Integer> march = new ArrayList<>();
            for (MakeCaseCollectFigureBO scaleContractFigureBO : figureBOS) {
                text_list_3.add(scaleContractFigureBO.getYearMonth());

                //柱状图数据
                hadNum.add(scaleContractFigureBO.getInnerNameNum());
                noNum.add(scaleContractFigureBO.getHadNum());
                notNum.add(scaleContractFigureBO.getNoNum());
                notNum.add(scaleContractFigureBO.getNotNum());
                completeNum.add(scaleContractFigureBO.getCompleteNum());
                march.add(scaleContractFigureBO.getMarch());
            }
            Integer[] innerNameNum_str = new Integer[innerNameNum.size()];
            innerNameNum_str = innerNameNum.toArray(innerNameNum_str);
            Integer[] hadNum_str = new Integer[hadNum.size()];
            hadNum_str = hadNum.toArray(hadNum_str);
            Integer[] noNum_str = new Integer[noNum.size()];
            noNum_str = noNum.toArray(noNum_str);
            Integer[] notNum_str = new Integer[notNum.size()];
            notNum_str = notNum.toArray(notNum_str);
            Integer[] completeNum_str = new Integer[completeNum.size()];
            completeNum_str = completeNum.toArray(completeNum_str);
            Integer[] march_str = new Integer[march.size()];
            march_str = march.toArray(march_str);
            SeriesBO seriesBO1 = new SeriesBO();
            seriesBO1.setName("内部项目名称数量");
            seriesBO1.setType("line");
            seriesBO1.setData(innerNameNum_str);
            seriesBOList.add(seriesBO1);
            SeriesBO seriesBO2 = new SeriesBO();
            seriesBO2.setName("已立项合同单数数量");
            seriesBO2.setType("line");
            seriesBO2.setData(hadNum_str);
            seriesBOList.add(seriesBO2);
            SeriesBO seriesBO3 = new SeriesBO();
            seriesBO3.setName("未立项合同单数数量");
            seriesBO3.setType("line");
            seriesBO3.setData(noNum_str);
            seriesBOList.add(seriesBO3);
            SeriesBO seriesBO4 = new SeriesBO();
            seriesBO4.setName("不立项合同单数数量");
            seriesBO4.setType("line");
            seriesBO4.setData(notNum_str);
            seriesBOList.add(seriesBO4);
            SeriesBO seriesBO5 = new SeriesBO();
            seriesBO5.setName("已完工单数数量");
            seriesBO5.setType("line");
            seriesBO5.setData(completeNum_str);
            seriesBOList.add(seriesBO5);
            SeriesBO seriesBO6 = new SeriesBO();
            seriesBO6.setName("进行");
            seriesBO6.setType("line");
            seriesBO6.setData(march_str);
            seriesBOList.add(seriesBO6);
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        BrokenOptionBO brokenOptionBO = new BrokenOptionBO();
        brokenOptionBO.setTitle(titleBO);
        brokenOptionBO.setTooltipBO(tooltipBO);
        brokenOptionBO.setLegend(legendBO);
        brokenOptionBO.setxAxis(xAxisBO);
        brokenOptionBO.setyAxis(yAxisBO);

        brokenOptionBO.setSeries(text_4);
        return brokenOptionBO;
    }

    @Override
    public Set<String> areas() throws SerException {
        Set<String> set = new HashSet<>();
        List<BusinessContract> list = super.findAll();
        for (BusinessContract businessContract : list) {
            set.add(businessContract.getArea());
        }
        return set;
    }

    @Override
    public BusinessContractsBO importExcel(List<BusinessContractTO> contractTOS) throws SerException {
        List<BusinessContract> businessContracts = new ArrayList<>(contractTOS.size());
        for (BusinessContractTO to : contractTOS) {
            BusinessContract contract = BeanTransform.copyProperties(to, BusinessContract.class, true);
            businessContracts.add(contract);
        }
        super.save(businessContracts);
        BusinessContractsBO bo = BeanTransform.copyProperties(new BusinessContract(), BusinessContractsBO.class);
        return bo;
    }

    @Override
    public byte[] exportExcel(BusinessContractDTO dto) throws SerException {
        if (null != dto.getAreas()) {
            dto.getConditions().add(Restrict.eq("area", dto.getAreas()));
        }
        List<BusinessContract> list = super.findByCis(dto);
        List<BusinessContractExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            BusinessContractExport export = BeanTransform.copyProperties(str, BusinessContractExport.class,
                    "measurePass", "notification", "commonSubcontractor", "taskFinish", "taskContract",
                    "makeContract", "scaleBalance", "solutionBalance", "implement", "partial",
                    "persist", "settlementProcess", "account", "closeSingle", "archive");
            //测算是否通过
            if (str.getMeasurePass().equals(true)) {
                export.setMeasurePass("是");
            } else {
                export.setMeasurePass("否");
            }
            //是否通报
            if (str.getNotification().equals(true)) {
                export.setNotification("是");
            } else {
                export.setNotification("否");
            }
            //是否有共同分包单位
            if (str.getCommonSubcontractor().equals(true)) {
                export.setCommonSubcontractor("是");
            } else {
                export.setCommonSubcontractor("否");
            }
            //派工归属清理是否完成
            if (str.getTaskFinish().equals(true)) {
                export.setTaskFinish("是");
            } else {
                export.setTaskFinish("否");
            }
            //是否有合同派工合同
            if (str.getTaskContract().equals(true)) {
                export.setTaskContract("是");
            } else {
                export.setTaskContract("否");
            }

            //合同规模数是否有差异
            if (str.getScaleBalance().equals(true)) {
                export.setScaleBalance("是");
            } else {
                export.setScaleBalance("否");
            }
            //是否解决差异问题
            if (str.getSolutionBalance().equals(true)) {
                export.setSolutionBalance("是");
            } else {
                export.setSolutionBalance("否");
            }
            //预估项目是否确认实施
            if (str.getImplement().equals(true)) {
                export.setImplement("是");
            } else {
                export.setImplement("否");
            }
            //是否分批结算
            if (str.getPartial().equals(true)) {
                export.setPartial("是");
            } else {
                export.setPartial("否");
            }
            //是否为持续
            if (str.getPersist().equals(true)) {
                export.setPersist("是");
            } else {
                export.setPersist("否");
            }
            //是否正在走结算流程
            if (str.getSettlementProcess().equals(true)) {
                export.setSettlementProcess("是");
            } else {
                export.setSettlementProcess("否");
            }
            //是否到账
            if (str.getAccount().equals(true)) {
                export.setAccount("是");
            } else {
                export.setAccount("否");
            }
            //是否闭单
            if (str.getCloseSingle().equals(true)) {
                export.setCloseSingle("是");
            } else {
                export.setCloseSingle("否");
            }
            //合同是否已归档
            if (str.getArchive().equals(true)) {
                export.setArchive("是");
            } else {
                export.setArchive("否");
            }
            //是否有合同立项
            export.setMakeContract(MakeContract.exportStrConvert(str.getMakeContract()));
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<BusinessContractTemplateExcel> templateExcels = new ArrayList<>();
        BusinessContractTemplateExcel templateExcel = new BusinessContractTemplateExcel();
        templateExcel.setMeasureClassify("内包");
        templateExcel.setMeasurePass("是");
        templateExcel.setSignedTime(LocalDate.now());
        templateExcel.setNotificationTime(LocalDate.now());
        templateExcel.setNotification("是");
        templateExcel.setArea("test");
        templateExcel.setBusinessType("test");
        templateExcel.setBusinessSubject("test");
        templateExcel.setMajorCompany("test");
        templateExcel.setSubCompany("test");
        templateExcel.setCommonSubcontractor("是");
        templateExcel.setCommonSubcontractorName("test");
        templateExcel.setTaskFinish("是");
        templateExcel.setCustomerName("test");
        templateExcel.setProjectGroup("test");
        templateExcel.setOperator("test");
        templateExcel.setType("test");
        templateExcel.setMajor("test");
        templateExcel.setTaskContract("是");
        templateExcel.setMarketNum("test");
        templateExcel.setInternalProjectNum("test");
        templateExcel.setInternalContractNum("test");
        templateExcel.setMakeContract("预立项");
        templateExcel.setSalesContractNum("test");
        templateExcel.setSingleContractNum("test");
        templateExcel.setSingleContractName("test");
        templateExcel.setDispatchInterfaceA("test");
        templateExcel.setDispatchInterfaceB("test");
        templateExcel.setDispatchInterfaceC("test");
        templateExcel.setScale(0d);
        templateExcel.setScaleContract(0);
        templateExcel.setScaleBalance("是");
        templateExcel.setSolutionBalance("是");
        templateExcel.setTaskMoney(0d);
        templateExcel.setMakeMoney(0d);
        templateExcel.setForecastMoney(0d);
        templateExcel.setForecastFinishMoney(0d);
        templateExcel.setForecastMarchMoney(0d);
        templateExcel.setEstimatedMarketLosses(0d);
        templateExcel.setGuarantor("test");
        templateExcel.setGuarantorIdea("test");
        templateExcel.setImplement("是");
        templateExcel.setManagerIdea("test");
        templateExcel.setPlanIdea("test");
        templateExcel.setBudgetIdea("test");
        templateExcel.setPartial("是");
        templateExcel.setPartialMoney(0d);
        templateExcel.setContractPersist(0d);
        templateExcel.setPersist("是");
        templateExcel.setExpectStartDate(LocalDate.now());
        templateExcel.setRealityStartDate(LocalDate.now());
        templateExcel.setRealityCompleteTime(LocalDate.now());
        templateExcel.setExpectCompleteTime(LocalDate.now());
        templateExcel.setNotApproach("test");
        templateExcel.setApproach("test");
        templateExcel.setShutdown("test");
        templateExcel.setMarch("test");
        templateExcel.setComplete("test");
        templateExcel.setGoods("test");
        templateExcel.setInitialTest("test");
        templateExcel.setFinalTest("test");
        templateExcel.setSettlementProcess("是");
        templateExcel.setAccount("是");
        templateExcel.setCloseSingle("是");
        templateExcel.setBusinessCooperate("test");
        templateExcel.setOuterProject("test");
        templateExcel.setInnerProject("test");
        templateExcel.setBusinessAssessProject("test");
        templateExcel.setBusinessAssessCase("test");
        templateExcel.setBusinessReplyFeedback("test");
        templateExcel.setProjectCharge("test");
        templateExcel.setHandlers("test");
        templateExcel.setArchive("是");
        templateExcel.setArchiveNum(0d);
        templateExcel.setStorageLocation("test");

        templateExcels.add(templateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, excel);
        return bytes;
    }

    //季度
    private String[] quarter(Integer year, Integer quarter) throws SerException {
        String startDate = null;
        String endDate = null;
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = DateUtil.dateToString(LocalDate.now());
                endDate = DateUtil.dateToString(LocalDate.now());
                break;
        }

        return new String[]{startDate, endDate};
    }

    private OptionBO majorMakeCaseFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<MakeCaseCollectFigureBO> figureBOS = new ArrayList<>();
        List<MakeCaseCollectFigureBO> caseCollectFigureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"major", "innerNameNum", "hadNum", "noNum", "notNum"};
        sb.append(" SELECT a.major AS major,a.innerNameNum AS innerNameNum, ");
        sb.append(" MAX( CASE WHEN makeContract=1 THEN count END ) AS hadNum, ");
        sb.append(" MAX( CASE WHEN makeContract=0 THEN count END ) AS noNum, ");
        sb.append(" MAX( CASE WHEN makeContract=2 THEN count END ) AS notNum ");
        sb.append(" FROM ");
        sb.append(" (SELECT count(*) AS count,makeContract as makeContract,major AS major,count(innerProject) AS innerNameNum ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY makeContract,major)a GROUP BY a.major,a.innerNameNum ");
        List<MakeCaseCollectFigureBO> collectFigureBOS = super.findBySql(sb.toString(), MakeCaseCollectFigureBO.class, fields);
        for (MakeCaseCollectFigureBO bo : collectFigureBOS) {
            //已完工单数数量
            String[] completeField = new String[]{"major", "complete"};
            String sql = "SELECT major AS major,count(complete) AS complete FROM " +
                    " businessproject_businesscontract WHERE complete = '是' AND major = '" + bo.getMajor() + "' " +
                    " GROUP BY major";
            caseCollectFigureBOS = super.findBySql(sql, MakeCaseCollectFigureBO.class, completeField);
            for (MakeCaseCollectFigureBO bo1 : caseCollectFigureBOS) {
                bo.setCompleteNum(bo1.getCompleteNum());
            }
            //进行
            String[] marchField = new String[]{"major", "march"};
            String marchSql = "SELECT major AS major,count(march) AS march FROM " +
                    " businessproject_businesscontract WHERE march = '是' AND major = '" + bo.getMajor() + "' " +
                    " GROUP BY major";
            caseCollectFigureBOS = super.findBySql(marchSql, MakeCaseCollectFigureBO.class, marchField);
            for (MakeCaseCollectFigureBO bo1 : caseCollectFigureBOS) {
                bo.setMarch(bo1.getMarch());
            }
            figureBOS.add(bo);

        }
        Integer innerNameNums = figureBOS.stream().filter(p -> p.getInnerNameNum() != null).mapToInt(p -> p.getInnerNameNum()).sum();
        Integer hadNums = figureBOS.stream().filter(p -> p.getHadNum() != null).mapToInt(p -> p.getHadNum()).sum();
        Integer noNums = figureBOS.stream().filter(p -> p.getNoNum() != null).mapToInt(p -> p.getNoNum()).sum();
        Integer notNums = figureBOS.stream().filter(p -> p.getNotNum() != null).mapToInt(p -> p.getNotNum()).sum();
        Integer completeNums = figureBOS.stream().filter(p -> p.getCompleteNum() != null).mapToInt(p -> p.getCompleteNum()).sum();
        Integer marchs = figureBOS.stream().filter(p -> p.getMarch() != null).mapToInt(p -> p.getMarch()).sum();
        MakeCaseCollectFigureBO makeCaseCollectFigureBO = new MakeCaseCollectFigureBO();
        makeCaseCollectFigureBO.setMajor("合计");
        makeCaseCollectFigureBO.setInnerNameNum(innerNameNums);
        makeCaseCollectFigureBO.setHadNum(hadNums);
        makeCaseCollectFigureBO.setNoNum(noNums);
        makeCaseCollectFigureBO.setNotNum(notNums);
        makeCaseCollectFigureBO.setCompleteNum(completeNums);
        makeCaseCollectFigureBO.setMarch(marchs);
        figureBOS.add(makeCaseCollectFigureBO);
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> innerNameNum = new ArrayList<>();
            List<Integer> hadNum = new ArrayList<>();
            List<Integer> noNum = new ArrayList<>();
            List<Integer> notNum = new ArrayList<>();
            List<Integer> completeNum = new ArrayList<>();
            List<Integer> march = new ArrayList<>();
            for (MakeCaseCollectFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getMajor());

                //柱状图数据
                innerNameNum.add(figureBO.getInnerNameNum());
                hadNum.add(figureBO.getHadNum());
                noNum.add(figureBO.getNoNum());
                notNum.add(figureBO.getNotNum());
                completeNum.add(figureBO.getCompleteNum());
                march.add(figureBO.getMarch());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(innerNameNum);
            nums.add(hadNum);
            nums.add(noNum);
            nums.add(notNum);
            nums.add(completeNum);
            nums.add(march);
            String[] ziduan = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
            for (int i = 0; i < 6; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionBO projectGroupMakeCaseFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<MakeCaseCollectFigureBO> figureBOS = new ArrayList<>();
        List<MakeCaseCollectFigureBO> caseCollectFigureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"projectGroup", "innerNameNum", "hadNum", "noNum", "notNum"};
        sb.append(" SELECT a.projectGroup AS projectGroup,a.innerNameNum AS innerNameNum, ");
        sb.append(" MAX( CASE WHEN makeContract=1 THEN count END ) AS hadNum, ");
        sb.append(" MAX( CASE WHEN makeContract=0 THEN count END ) AS noNum, ");
        sb.append(" MAX( CASE WHEN makeContract=2 THEN count END ) AS notNum ");
        sb.append(" FROM ");
        sb.append(" (SELECT count(*) AS count,makeContract as makeContract,projectGroup AS projectGroup,count(innerProject) AS innerNameNum ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY makeContract,projectGroup)a GROUP BY a.projectGroup,a.innerNameNum ");
        List<MakeCaseCollectFigureBO> collectFigureBOS = super.findBySql(sb.toString(), MakeCaseCollectFigureBO.class, fields);
        for (MakeCaseCollectFigureBO bo : collectFigureBOS) {
            //已完工单数数量
            String[] completeField = new String[]{"projectGroup", "complete"};
            String sql = "SELECT projectGroup AS projectGroup,count(complete) AS complete FROM " +
                    " businessproject_businesscontract WHERE complete = '是' AND projectGroup = '" + bo.getProjectGroup() + "' " +
                    " GROUP BY projectGroup";
            caseCollectFigureBOS = super.findBySql(sql, MakeCaseCollectFigureBO.class, completeField);
            for (MakeCaseCollectFigureBO bo1 : caseCollectFigureBOS) {
                bo.setCompleteNum(bo1.getCompleteNum());
            }
            //进行
            String[] marchField = new String[]{"projectGroup", "march"};
            String marchSql = "SELECT projectGroup AS projectGroup,count(march) AS march FROM " +
                    " businessproject_businesscontract WHERE march = '是' AND projectGroup = '" + bo.getProjectGroup() + "' " +
                    " GROUP BY projectGroup";
            caseCollectFigureBOS = super.findBySql(marchSql, MakeCaseCollectFigureBO.class, marchField);
            for (MakeCaseCollectFigureBO bo1 : caseCollectFigureBOS) {
                bo.setMarch(bo1.getMarch());
            }
            figureBOS.add(bo);

        }
        Integer innerNameNums = figureBOS.stream().filter(p -> p.getInnerNameNum() != null).mapToInt(p -> p.getInnerNameNum()).sum();
        Integer hadNums = figureBOS.stream().filter(p -> p.getHadNum() != null).mapToInt(p -> p.getHadNum()).sum();
        Integer noNums = figureBOS.stream().filter(p -> p.getNoNum() != null).mapToInt(p -> p.getNoNum()).sum();
        Integer notNums = figureBOS.stream().filter(p -> p.getNotNum() != null).mapToInt(p -> p.getNotNum()).sum();
        Integer completeNums = figureBOS.stream().filter(p -> p.getCompleteNum() != null).mapToInt(p -> p.getCompleteNum()).sum();
        Integer marchs = figureBOS.stream().filter(p -> p.getMarch() != null).mapToInt(p -> p.getMarch()).sum();
        MakeCaseCollectFigureBO makeCaseCollectFigureBO = new MakeCaseCollectFigureBO();
        makeCaseCollectFigureBO.setProjectGroup("合计");
        makeCaseCollectFigureBO.setInnerNameNum(innerNameNums);
        makeCaseCollectFigureBO.setHadNum(hadNums);
        makeCaseCollectFigureBO.setNoNum(noNums);
        makeCaseCollectFigureBO.setNotNum(notNums);
        makeCaseCollectFigureBO.setCompleteNum(completeNums);
        makeCaseCollectFigureBO.setMarch(marchs);
        figureBOS.add(makeCaseCollectFigureBO);
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> innerNameNum = new ArrayList<>();
            List<Integer> hadNum = new ArrayList<>();
            List<Integer> noNum = new ArrayList<>();
            List<Integer> notNum = new ArrayList<>();
            List<Integer> completeNum = new ArrayList<>();
            List<Integer> march = new ArrayList<>();
            for (MakeCaseCollectFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getProjectGroup());

                //柱状图数据
                innerNameNum.add(figureBO.getInnerNameNum());
                hadNum.add(figureBO.getHadNum());
                noNum.add(figureBO.getNoNum());
                notNum.add(figureBO.getNotNum());
                completeNum.add(figureBO.getCompleteNum());
                march.add(figureBO.getMarch());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(innerNameNum);
            nums.add(hadNum);
            nums.add(noNum);
            nums.add(notNum);
            nums.add(completeNum);
            nums.add(march);
            String[] ziduan = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
            for (int i = 0; i < 6; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionBO areaMakeCaseFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<MakeCaseCollectFigureBO> figureBOS = new ArrayList<>();
        List<MakeCaseCollectFigureBO> caseCollectFigureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"area", "innerNameNum", "hadNum", "noNum", "notNum"};
        sb.append(" SELECT a.area AS area,a.innerNameNum AS innerNameNum, ");
        sb.append(" MAX( CASE WHEN makeContract=1 THEN count END ) AS hadNum, ");
        sb.append(" MAX( CASE WHEN makeContract=0 THEN count END ) AS noNum, ");
        sb.append(" MAX( CASE WHEN makeContract=2 THEN count END ) AS notNum ");
        sb.append(" FROM ");
        sb.append(" (SELECT count(*) AS count,makeContract as makeContract,area AS area,count(innerProject) AS innerNameNum ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY makeContract,area)a GROUP BY a.area,a.innerNameNum ");
        List<MakeCaseCollectFigureBO> collectFigureBOS = super.findBySql(sb.toString(), MakeCaseCollectFigureBO.class, fields);
        for (MakeCaseCollectFigureBO bo : collectFigureBOS) {
            //已完工单数数量
            String[] completeField = new String[]{"area", "complete"};
            String sql = "SELECT area AS area,count(complete) AS complete FROM " +
                    " businessproject_businesscontract WHERE complete = '是' AND area = '" + bo.getArea() + "' " +
                    " GROUP BY area";
            caseCollectFigureBOS = super.findBySql(sql, MakeCaseCollectFigureBO.class, completeField);
            for (MakeCaseCollectFigureBO bo1 : caseCollectFigureBOS) {
                bo.setCompleteNum(bo1.getCompleteNum());
            }
            //进行
            String[] marchField = new String[]{"area", "march"};
            String marchSql = "SELECT area AS area,count(march) AS march FROM " +
                    " businessproject_businesscontract WHERE march = '是' AND area = '" + bo.getArea() + "' " +
                    " GROUP BY area";
            caseCollectFigureBOS = super.findBySql(marchSql, MakeCaseCollectFigureBO.class, marchField);
            for (MakeCaseCollectFigureBO bo1 : caseCollectFigureBOS) {
                bo.setMarch(bo1.getMarch());
            }
            figureBOS.add(bo);

        }
        Integer innerNameNums = figureBOS.stream().filter(p -> p.getInnerNameNum() != null).mapToInt(p -> p.getInnerNameNum()).sum();
        Integer hadNums = figureBOS.stream().filter(p -> p.getHadNum() != null).mapToInt(p -> p.getHadNum()).sum();
        Integer noNums = figureBOS.stream().filter(p -> p.getNoNum() != null).mapToInt(p -> p.getNoNum()).sum();
        Integer notNums = figureBOS.stream().filter(p -> p.getNotNum() != null).mapToInt(p -> p.getNotNum()).sum();
        Integer completeNums = figureBOS.stream().filter(p -> p.getCompleteNum() != null).mapToInt(p -> p.getCompleteNum()).sum();
        Integer marchs = figureBOS.stream().filter(p -> p.getMarch() != null).mapToInt(p -> p.getMarch()).sum();
        MakeCaseCollectFigureBO makeCaseCollectFigureBO = new MakeCaseCollectFigureBO();
        makeCaseCollectFigureBO.setArea("合计");
        makeCaseCollectFigureBO.setInnerNameNum(innerNameNums);
        makeCaseCollectFigureBO.setHadNum(hadNums);
        makeCaseCollectFigureBO.setNoNum(noNums);
        makeCaseCollectFigureBO.setNotNum(notNums);
        makeCaseCollectFigureBO.setCompleteNum(completeNums);
        makeCaseCollectFigureBO.setMarch(marchs);
        figureBOS.add(makeCaseCollectFigureBO);
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> innerNameNum = new ArrayList<>();
            List<Integer> hadNum = new ArrayList<>();
            List<Integer> noNum = new ArrayList<>();
            List<Integer> notNum = new ArrayList<>();
            List<Integer> completeNum = new ArrayList<>();
            List<Integer> march = new ArrayList<>();
            for (MakeCaseCollectFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getArea());

                //柱状图数据
                innerNameNum.add(figureBO.getInnerNameNum());
                hadNum.add(figureBO.getHadNum());
                noNum.add(figureBO.getNoNum());
                notNum.add(figureBO.getNotNum());
                completeNum.add(figureBO.getCompleteNum());
                march.add(figureBO.getMarch());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(innerNameNum);
            nums.add(hadNum);
            nums.add(noNum);
            nums.add(notNum);
            nums.add(completeNum);
            nums.add(march);
            String[] ziduan = new String[]{"内部项目名称数量", "已立项合同单数数量", "未立项合同单数数量", "不立项合同单数数量", "已完工单数数量", "进行"};
            for (int i = 0; i < 6; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }


    private OptionMakeBO majorMakeFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<MakeCaseFigureBO> figureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT major AS major,sum(taskMoney) AS taskMoney, ");
        sb.append(" sum(makeMoney) AS makeMoney,sum(forecastMoney) AS forecastMoney ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY major ");
        String[] fields = new String[]{"major", "taskMoney", "makeMoney", "forecastMoney"};
        figureBOS = super.findBySql(sb.toString(), MakeCaseFigureBO.class, fields);
        Double taskMoneys = figureBOS.stream().filter(p -> p.getTaskMoney() != null).mapToDouble(p -> p.getTaskMoney()).sum();
        Double makeMoneys = figureBOS.stream().filter(p -> p.getMakeMoney() != null).mapToDouble(p -> p.getMakeMoney()).sum();
        Double forecastMoneys = figureBOS.stream().filter(p -> p.getForecastMoney() != null).mapToDouble(p -> p.getForecastMoney()).sum();
        MakeCaseFigureBO bo = new MakeCaseFigureBO();
        bo.setMajor("合计");
        bo.setTaskMoney(taskMoneys);
        bo.setMakeMoney(makeMoneys);
        bo.setForecastMoney(forecastMoneys);
        figureBOS.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"已派工金额", "立项总金额", "预估总金额"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Double> taskMoney = new ArrayList<>();
            List<Double> makeMoney = new ArrayList<>();
            List<Double> forecastMoney = new ArrayList<>();
            for (MakeCaseFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getMajor());

                //柱状图数据
                taskMoney.add(figureBO.getTaskMoney());
                makeMoney.add(figureBO.getMakeMoney());
                forecastMoney.add(figureBO.getForecastMoney());
            }
            List<List<Double>> nums = new ArrayList<>();
            nums.add(taskMoney);
            nums.add(makeMoney);
            nums.add(forecastMoney);
            String[] ziduan = new String[]{"已派工金额", "立项总金额", "预估总金额"};
            for (int i = 0; i < 3; i++) {
                SeriesBBO seriesBO = new SeriesBBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Double[] text_int_4 = new Double[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionMakeBO optionBO = new OptionMakeBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionMakeBO projectGroupMakeFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<MakeCaseFigureBO> figureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT projectGroup AS projectGroup,sum(taskMoney) AS taskMoney, ");
        sb.append(" sum(makeMoney) AS makeMoney,sum(forecastMoney) AS forecastMoney ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY projectGroup ");
        String[] fields = new String[]{"projectGroup", "taskMoney", "makeMoney", "forecastMoney"};
        figureBOS = super.findBySql(sb.toString(), MakeCaseFigureBO.class, fields);
        Double taskMoneys = figureBOS.stream().filter(p -> p.getTaskMoney() != null).mapToDouble(p -> p.getTaskMoney()).sum();
        Double makeMoneys = figureBOS.stream().filter(p -> p.getMakeMoney() != null).mapToDouble(p -> p.getMakeMoney()).sum();
        Double forecastMoneys = figureBOS.stream().filter(p -> p.getForecastMoney() != null).mapToDouble(p -> p.getForecastMoney()).sum();
        MakeCaseFigureBO bo = new MakeCaseFigureBO();
        bo.setProjectGroup("合计");
        bo.setTaskMoney(taskMoneys);
        bo.setMakeMoney(makeMoneys);
        bo.setForecastMoney(forecastMoneys);
        figureBOS.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"已派工金额", "立项总金额", "预估总金额"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Double> taskMoney = new ArrayList<>();
            List<Double> makeMoney = new ArrayList<>();
            List<Double> forecastMoney = new ArrayList<>();
            for (MakeCaseFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getProjectGroup());

                //柱状图数据
                taskMoney.add(figureBO.getTaskMoney());
                makeMoney.add(figureBO.getMakeMoney());
                forecastMoney.add(figureBO.getForecastMoney());
            }
            List<List<Double>> nums = new ArrayList<>();
            nums.add(taskMoney);
            nums.add(makeMoney);
            nums.add(forecastMoney);
            String[] ziduan = new String[]{"已派工金额", "立项总金额", "预估总金额"};
            for (int i = 0; i < 3; i++) {
                SeriesBBO seriesBO = new SeriesBBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Double[] text_int_4 = new Double[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionMakeBO optionBO = new OptionMakeBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionMakeBO areaMakeFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<MakeCaseFigureBO> figureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT area AS area,sum(taskMoney) AS taskMoney, ");
        sb.append(" sum(makeMoney) AS makeMoney,sum(forecastMoney) AS forecastMoney ");
        sb.append(" FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY area ");
        String[] fields = new String[]{"area", "taskMoney", "makeMoney", "forecastMoney"};
        figureBOS = super.findBySql(sb.toString(), MakeCaseFigureBO.class, fields);
        Double taskMoneys = figureBOS.stream().filter(p -> p.getTaskMoney() != null).mapToDouble(p -> p.getTaskMoney()).sum();
        Double makeMoneys = figureBOS.stream().filter(p -> p.getMakeMoney() != null).mapToDouble(p -> p.getMakeMoney()).sum();
        Double forecastMoneys = figureBOS.stream().filter(p -> p.getForecastMoney() != null).mapToDouble(p -> p.getForecastMoney()).sum();
        MakeCaseFigureBO bo = new MakeCaseFigureBO();
        bo.setArea("合计");
        bo.setTaskMoney(taskMoneys);
        bo.setMakeMoney(makeMoneys);
        bo.setForecastMoney(forecastMoneys);
        figureBOS.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"已派工金额", "立项总金额", "预估总金额"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBBO> seriesBBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Double> taskMoney = new ArrayList<>();
            List<Double> makeMoney = new ArrayList<>();
            List<Double> forecastMoney = new ArrayList<>();
            for (MakeCaseFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getArea());

                //柱状图数据
                taskMoney.add(figureBO.getTaskMoney());
                makeMoney.add(figureBO.getMakeMoney());
                forecastMoney.add(figureBO.getForecastMoney());
            }
            List<List<Double>> nums = new ArrayList<>();
            nums.add(taskMoney);
            nums.add(makeMoney);
            nums.add(forecastMoney);
            String[] ziduan = new String[]{"已派工金额", "立项总金额", "预估总金额"};
            for (int i = 0; i < 3; i++) {
                SeriesBBO seriesBBO = new SeriesBBO();
                seriesBBO.setName(ziduan[i]);
                seriesBBO.setType("bar");
                Double[] text_int_4 = new Double[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBBO.setData(text_int_4);
                seriesBBOS.add(seriesBBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBBOS.size()];
        text_4 = seriesBBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionMakeBO optionBO = new OptionMakeBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionBO majorScaleFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<ScaleContractFigureBO> figureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"major", "scaleContract"};
        sb.append(" SELECT major AS major, sum(scaleContract) AS scaleContract FROM businessproject_businesscontract");
        sb.append(" WHERE signedTime between '" + startDate + "' and '" + endDate + "' GROUP BY major ");
        figureBOS = super.findBySql(sb.toString(), ScaleContractFigureBO.class, fields);
        ScaleContractFigureBO bo = new ScaleContractFigureBO();
        Integer scaleContracts = figureBOS.stream().filter(p -> p.getScaleContract() != null).mapToInt(p -> p.getScaleContract()).sum();
        bo.setMajor("合计");
        bo.setScaleContract(scaleContracts);
        figureBOS.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"规模数量(总规模数量)", "实际规模数量"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> scaleContract = new ArrayList<>();
            //todo 实际规模数量需求方也不知道从哪拿
//            List<Integer> finishScale = new ArrayList<>();
            for (ScaleContractFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getMajor());

                //柱状图数据
                scaleContract.add(figureBO.getScaleContract());
//                finishScale.add(figureBO.getFinishScale());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(scaleContract);
//            nums.add(finishScale);
            String[] ziduan = new String[]{"规模数量(总规模数量)", "实际规模数量"};
//            List<SeriesBO> seriesBOList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionBO projectGroupScaleFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<ScaleContractFigureBO> figureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"projectGroup", "scaleContract"};
        sb.append(" SELECT projectGroup AS projectGroup, sum(scaleContract) AS scaleContract FROM businessproject_businesscontract");
        sb.append(" WHERE signedTime between '" + startDate + "' and '" + endDate + "' GROUP BY projectGroup ");
        figureBOS = super.findBySql(sb.toString(), ScaleContractFigureBO.class, fields);
        ScaleContractFigureBO bo = new ScaleContractFigureBO();
        Integer scaleContracts = figureBOS.stream().filter(p -> p.getScaleContract() != null).mapToInt(p -> p.getScaleContract()).sum();
        bo.setProjectGroup("合计");
        bo.setScaleContract(scaleContracts);
        figureBOS.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"规模数量(总规模数量)", "实际规模数量"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> scaleContract = new ArrayList<>();
//            List<Integer> finishScale = new ArrayList<>();
            for (ScaleContractFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getProjectGroup());

                //柱状图数据
                scaleContract.add(figureBO.getScaleContract());
//                finishScale.add(figureBO.getFinishScale());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(scaleContract);
            //todo 实际完成规模数量需求方也不知道从哪拿
//            nums.add(finishScale);
            String[] ziduan = new String[]{"规模数量(总规模数量)", "实际规模数量"};
//            List<SeriesBO> seriesBOList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionBO areaScaleFigureCollect(String startDate, String endDate, String text_1) throws SerException {
        List<ScaleContractFigureBO> figureBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"area", "scaleContract"};
        sb.append(" SELECT area AS area, sum(scaleContract) AS scaleContract FROM businessproject_businesscontract");
        sb.append(" WHERE signedTime between '" + startDate + "' and '" + endDate + "' GROUP BY area ");
        figureBOS = super.findBySql(sb.toString(), ScaleContractFigureBO.class, fields);
        ScaleContractFigureBO bo = new ScaleContractFigureBO();
        Integer scaleContracts = figureBOS.stream().filter(p -> p.getScaleContract() != null).mapToInt(p -> p.getScaleContract()).sum();
        bo.setArea("合计");
        bo.setScaleContract(scaleContracts);
        figureBOS.add(bo);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        //todo 实际完成规模数量需求方也不知道从哪拿
        String[] text_2 = new String[]{"规模数量(总规模数量)", "实际规模数量"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> scaleContract = new ArrayList<>();
//            List<Integer> finishScale = new ArrayList<>();
            for (ScaleContractFigureBO figureBO : figureBOS) {
                text_list_3.add(figureBO.getArea());

                //柱状图数据
                scaleContract.add(figureBO.getScaleContract());
//                finishScale.add(figureBO.getFinishScale());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(scaleContract);
//            nums.add(finishScale);
            String[] ziduan = new String[]{"规模数量(总规模数量)", "实际规模数量"};
//            List<SeriesBO> seriesBOList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(0).size()];
                text_int_4 = nums.get(0).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private List<BusinessContractProgressBO> progressCollect(String startTime, String endTime) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT a.area AS area,a.projectGroup AS projectGroup,sum(notTaskMoney) AS notTaskMoney, ");
        sb.append(" sum(a.hadTaskMoney) AS hadTaskMoney,sum(a.makeMoney) AS makeMoney,sum(forecastMoney) AS forecastMoney, ");
        sb.append(" sum(a.scale) AS scale,sum(a.forecastRoundMoney) AS forecastRoundMoney,sum(a.estimatedMarketLosses) AS estimatedMarketLosses, ");
        sb.append(" MAX( CASE WHEN makeContract=1 THEN count END ) AS hadMakeNum, ");
        sb.append(" MAX( CASE WHEN makeContract=0 THEN count END ) AS noMakeNum, ");
        sb.append(" MAX( CASE WHEN makeContract=2 THEN count END ) AS notMakeNum, ");
        sb.append(" sum(a.scaleContract) AS scaleContract FROM ");
        sb.append(" (SELECT count(*) AS count,makeContract as makeContract, ");
        sb.append(" area AS area,projectGroup AS  projectGroup,sum(forecastMoney) AS notTaskMoney, ");
        sb.append(" sum(taskMoney) AS hadTaskMoney,sum(makeMoney) AS makeMoney,sum(forecastMoney) AS forecastMoney, ");
        sb.append(" sum(scale) AS scale,sum(forecastFinishMoney) AS forecastRoundMoney,sum(estimatedMarketLosses) AS estimatedMarketLosses, ");
        sb.append(" sum(scaleContract) AS scaleContract FROM businessproject_businesscontract ");
        sb.append(" WHERE signedTime between '" + startTime + "' and '" + endTime + "' GROUP BY area,projectGroup,makeContract)a ");
        sb.append(" GROUP BY a.area,a.projectGroup ");
        String[] fields = new String[]{"area", "projectGroup", "notTaskMoney", "hadTaskMoney", "makeMoney", "forecastMoney",
                "scale", "forecastRoundMoney", "estimatedMarketLosses", "hadMakeNum", "noMakeNum", "notMakeNum", "scaleContract"};
        List<BusinessContractProgressBO> progressBOS = super.findBySql(sb.toString(), BusinessContractProgressBO.class, fields);
        List<BusinessContractProgressBO> boList = new ArrayList<>();
        List<BusinessContractProgressBO> contractProgressBOS = new ArrayList<>();
        Double forecastUnit = 0.0;
        Double makeUnit = 0.0;
        Double totalMoney = 0.0;
        //todo 结算完成金额，清理派工归属数量,不需派工单但要跟进回款单数,不需派工单但要跟进回款金额,实际完成规模数量需求方也不知道从哪拿
        if (progressBOS != null) {
            for (BusinessContractProgressBO bo : progressBOS) {
                //预估单价(预估总金额/预估规模)
                forecastUnit = bo.getForecastMoney() / bo.getScale();
                //立项总单价(立项总金额/合同规模数量)
                makeUnit = bo.getMakeMoney() / bo.getScaleContract();
                //总金额(立项总金额+预估总金额)
                totalMoney = bo.getMakeMoney() + bo.getForecastMoney();
                bo.setTotalMoney(totalMoney);
                bo.setForecastUnit(forecastUnit);
                bo.setMakeUnit(makeUnit);
                //通报新合同单数
                String[] passNumField = new String[]{"area", "passNum"};
                String passNumSql = " SELECT area AS area,count(*) AS passNum FROM businessproject_businesscontract " +
                        " WHERE is_notification=1 AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(passNumSql, BusinessContractProgressBO.class, passNumField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setPassNum(bo1.getPassNum());
                }
                //是否有合同立项
                String[] contractNumField = new String[]{"contractNum", "area"};
                String contractNumSql = " SELECT sum(makeContract) AS contractNum,area AS  area FROM businessproject_businesscontract " +
                        " WHERE makeContract=1 AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(contractNumSql, BusinessContractProgressBO.class, contractNumField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setContractNum(bo1.getContractNum());
                }
                //内包项目数
                String[] insideNumField = new String[]{"insideNum", "area"};
                String insideNumSql = " SELECT count(measureClassify) AS insideNum,area AS  area FROM businessproject_businesscontract " +
                        " WHERE measureClassify='内包' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(insideNumSql, BusinessContractProgressBO.class, insideNumField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setInsideNum(bo1.getInsideNum());
                }
                //外包项目数
                String[] outsourcingNumField = new String[]{"outsourcingNum", "area"};
                String outsourcingNumSql = " SELECT count(measureClassify) AS outsourcingNum,area AS  area FROM businessproject_businesscontract " +
                        " WHERE measureClassify='外包' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(outsourcingNumSql, BusinessContractProgressBO.class, outsourcingNumField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setOutsourcingNum(bo1.getOutsourcingNum());
                }
                //半外包项目数
                String[] halfOutsourcingNumField = new String[]{"halfOutsourcingNum", "area"};
                String halfOutsourcingNumSql = " SELECT count(measureClassify) AS halfOutsourcingNum,area AS  area FROM businessproject_businesscontract " +
                        " WHERE measureClassify='半外包' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(halfOutsourcingNumSql, BusinessContractProgressBO.class, halfOutsourcingNumField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setHalfOutsourcingNum(bo1.getHalfOutsourcingNum());
                }
                //2014立项金额
                String[] aField = new String[]{"makeMoneyA", "area"};
                String aSql = " SELECT sum(makeMoney) AS makeMoneyA,area AS  area FROM businessproject_businesscontract " +
                        " WHERE year(signedTime)='2014' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(aSql, BusinessContractProgressBO.class, aField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setMakeMoneyA(bo1.getMakeMoneyA());
                }
                //2015立项金额
                String[] bField = new String[]{"makeMoneyB", "area"};
                String bSql = " SELECT sum(makeMoney) AS makeMoneyB,area AS  area FROM businessproject_businesscontract " +
                        " WHERE year(signedTime)='2015' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(bSql, BusinessContractProgressBO.class, bField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setMakeMoneyB(bo1.getMakeMoneyB());
                }
                //2016立项金额
                String[] cField = new String[]{"makeMoneyC", "area"};
                String cSql = " SELECT sum(makeMoney) AS makeMoneyC,area AS  area FROM businessproject_businesscontract " +
                        " WHERE year(signedTime)='2016' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(cSql, BusinessContractProgressBO.class, cField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setMakeMoneyC(bo1.getMakeMoneyC());
                }
                //2017立项金额
                String[] dField = new String[]{"makeMoneyD", "area"};
                String dSql = " SELECT sum(makeMoney) AS makeMoneyD,area AS  area FROM businessproject_businesscontract " +
                        " WHERE year(signedTime)='2017' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(dSql, BusinessContractProgressBO.class, dField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setMakeMoneyD(bo1.getMakeMoneyD());
                }
                //2018立项金额
                String[] eField = new String[]{"makeMoneyE", "area"};
                String eSql = " SELECT sum(makeMoney) AS makeMoneyE,area AS  area FROM businessproject_businesscontract " +
                        " WHERE year(signedTime)='2018' AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(eSql, BusinessContractProgressBO.class, eField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setMakeMoneyE(bo1.getMakeMoneyE());
                }
                //解决派工和现场数量不一致数量
                String[] solutionBalanceField = new String[]{"solutionBalance", "area"};
                String solutionBalanceSql = " SELECT count(is_solutionBalance) AS solutionBalance,area AS  area FROM businessproject_businesscontract " +
                        " WHERE is_solutionBalance=1 AND area = '" + bo.getArea() + "' GROUP BY area ";
                contractProgressBOS = super.findBySql(solutionBalanceSql, BusinessContractProgressBO.class, solutionBalanceField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setSolutionBalance(bo1.getSolutionBalance());
                }
                //未进场
                String[] notApproachField = new String[]{"notApproach", "area"};
                String notApproachSql = " SELECT count(notApproach) AS notApproach ,area AS area FROM businessproject_businesscontract " +
                        " WHERE notApproach='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(notApproachSql, BusinessContractProgressBO.class, notApproachField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setNotApproach(bo1.getNotApproach());
                }
                //进场
                String[] approachField = new String[]{"approach", "area"};
                String approachSql = " SELECT count(approach) AS approach ,area AS area FROM businessproject_businesscontract " +
                        " WHERE approach='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(approachSql, BusinessContractProgressBO.class, approachField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setApproach(bo1.getApproach());
                }
                //停工
                String[] shutdownField = new String[]{"shutdown", "area"};
                String shutdownSql = " SELECT count(shutdown) AS shutdown ,area AS area FROM businessproject_businesscontract " +
                        " WHERE shutdown='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(shutdownSql, BusinessContractProgressBO.class, shutdownField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setShutdown(bo1.getShutdown());
                }
                //进行
                String[] marchField = new String[]{"march", "area"};
                String marchSql = " SELECT count(march) AS march ,area AS area FROM businessproject_businesscontract " +
                        " WHERE march='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(marchSql, BusinessContractProgressBO.class, marchField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setMarch(bo1.getMarch());
                }
                //已完工单数
                String[] completeField = new String[]{"complete", "area"};
                String completeSql = " SELECT count(complete) AS complete ,area AS area FROM businessproject_businesscontract " +
                        " WHERE complete='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(completeSql, BusinessContractProgressBO.class, completeField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setComplete(bo1.getComplete());
                }
                //完工金额
                String[] completeMoneyField = new String[]{"completeMoney", "area"};
                String completeMoneySql = " SELECT sum(makeMoney+forecastFinishMoney) AS completeMoney ,area AS area FROM businessproject_businesscontract " +
                        " WHERE complete='是' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(completeMoneySql, BusinessContractProgressBO.class, completeMoneyField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setCompleteMoney(bo1.getCompleteMoney());
                }
                //初验
                String[] initialField = new String[]{"initialTest", "area"};
                String initialSql = " SELECT count(initialTest) AS initialTest ,area AS area FROM businessproject_businesscontract " +
                        " WHERE initialTest='完成' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(initialSql, BusinessContractProgressBO.class, initialField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setInitialTest(bo1.getInitialTest());
                }
                //初验金额
                String[] initialTestMoneyField = new String[]{"initialTestMoney", "area"};
                String initialTestMoneySql = " SELECT sum(makeMoney+forecastFinishMoney) AS initialTestMoney ,area AS area FROM businessproject_businesscontract " +
                        " WHERE initialTest='完成' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(initialTestMoneySql, BusinessContractProgressBO.class, initialTestMoneyField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setInitialTestMoney(bo1.getInitialTestMoney());
                }
                //终验
                String[] finalField = new String[]{"finalTest", "area"};
                String finalSql = " SELECT count(finalTest) AS finalTest ,area AS area FROM businessproject_businesscontract " +
                        " WHERE finalTest='完成' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(finalSql, BusinessContractProgressBO.class, finalField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setFinalTest(bo1.getFinalTest());
                }
                //终验金额
                String[] finalTestMoneyField = new String[]{"finalTestMoney", "area"};
                String finalTestMoneySql = " SELECT sum(makeMoney+forecastFinishMoney) AS finalTestMoney ,area AS area FROM businessproject_businesscontract " +
                        " WHERE finalTest='完成' AND area = '" + bo.getArea() + "'GROUP BY area ";
                contractProgressBOS = super.findBySql(finalTestMoneySql, BusinessContractProgressBO.class, finalTestMoneyField);
                for (BusinessContractProgressBO bo1 : contractProgressBOS) {
                    bo.setFinalTestMoney(bo1.getFinalTestMoney());
                }
                boList.add(bo);
            }
        }
        return boList;
    }
}