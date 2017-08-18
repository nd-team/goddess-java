package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.CapitalInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.dto.CapitalInvestDTO;
import com.bjike.goddess.moneyside.entity.ApplyInvest;
import com.bjike.goddess.moneyside.entity.CallInfo;
import com.bjike.goddess.moneyside.entity.CapitalInvest;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CapitalInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资金投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CapitalInvestSerImpl extends ServiceImpl<CapitalInvest, CapitalInvestDTO> implements CapitalInvestSer {
    @Autowired
    private CallInfoSer callInfoSer;
    @Autowired
    private ApplyInvestSer applyInvestSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
        return flag;
    }


    private void checkDate(CapitalInvestTO capitalInvestTO)throws SerException {
        try {
            DateUtil.parseDate(capitalInvestTO.getArriveTime());
        }catch (Exception e){
            throw new SerException("输入的日期格式有误");
        }
    }
    @Override
    public Long countCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
        Long count = super.count(capitalInvestDTO);
        return count;
    }

    @Override
    public CapitalInvestBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CapitalInvest capitalInvest = super.findById(id);
        return BeanTransform.copyProperties(capitalInvest, CapitalInvestBO.class);
    }

    @Override
    public List<CapitalInvestBO> findListCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
//        List<CallInfoBO> callinfos = callInfoSer.findListCallInfo(null);
//        for(CallInfoBO c: callinfos){
//            BeanTransform.copyProperties(c,capitalInvestBOS);
//        }
        List<CapitalInvest> capitalInvests = super.findByPage(capitalInvestDTO);
        List<CapitalInvestBO> capitalInvestBOS = BeanTransform.copyProperties(capitalInvests, CapitalInvestBO.class);
        return capitalInvestBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CapitalInvestBO insertCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        checkDate(capitalInvestTO);
        CapitalInvest capitalInvest = BeanTransform.copyProperties(capitalInvestTO,CapitalInvest.class,true);

        CallInfoDTO dto = new CallInfoDTO();
        List<CallInfo> callInfos = callInfoSer.findByCis(dto);
        //筹资总额
        Double totalFund = 0.0;
        for(CallInfo callInfo : callInfos){
            totalFund = callInfo.getTotalFund();
        }
        ApplyInvestDTO applyInvestDTO = new ApplyInvestDTO();
        List<ApplyInvest> applyInvests = applyInvestSer.findByCis(applyInvestDTO);
        //项目风控总金额(提取风险控制保证金)
        Double extractRiskRserveRatio = 0.0;
        for(ApplyInvest applyInvest : applyInvests){
            extractRiskRserveRatio = applyInvest.getExtractRiskControlMargin();
        }
        //投资占比（本次投资额/筹资总额）
        Double investProportion = capitalInvestTO.getThisInvestMoney()/totalFund;
        capitalInvest.setInvestProportion(investProportion);
        //风险控制准备金（投资占比*项目风控总金额）
        Double riskControlReserves = investProportion*extractRiskRserveRatio;
        capitalInvest.setRiskControlReserves(riskControlReserves);
        //预估分配额（投资占比*预估到账金额）
        Double allocationForecast = investProportion*capitalInvestTO.getForecastArriveMoney();
        capitalInvest.setAllocationForecast(allocationForecast);
        capitalInvest.setCreateTime(LocalDateTime.now());
        super.save(capitalInvest);
        return BeanTransform.copyProperties(capitalInvest,CapitalInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CapitalInvestBO editCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        if(StringUtils.isBlank(capitalInvestTO.getId())){
            throw new SerException("id不能为空");
        }
        CapitalInvest capitalInvest = super.findById(capitalInvestTO.getId());
        checkDate(capitalInvestTO);
        BeanUtils.copyProperties(capitalInvestTO,capitalInvest);
        CallInfoDTO dto = new CallInfoDTO();
        List<CallInfo> callInfos = callInfoSer.findByCis(dto);
        //筹资总额
        Double totalFund = 0.0;
        for(CallInfo callInfo : callInfos){
            totalFund = callInfo.getTotalFund();
        }
        ApplyInvestDTO applyInvestDTO = new ApplyInvestDTO();
        List<ApplyInvest> applyInvests = applyInvestSer.findByCis(applyInvestDTO);
        //项目风控总金额(提取风险控制保证金)
        Double extractRiskRserveRatio = 0.0;
        for(ApplyInvest applyInvest : applyInvests){
            extractRiskRserveRatio = applyInvest.getExtractRiskControlMargin();
        }
        //投资占比（本次投资额/筹资总额）
        Double investProportion = capitalInvestTO.getThisInvestMoney()/totalFund;
        capitalInvest.setInvestProportion(investProportion);
        //风险控制准备金（投资占比*项目风控总金额）
        Double riskControlReserves = investProportion*extractRiskRserveRatio;
        capitalInvest.setRiskControlReserves(riskControlReserves);
        //预估分配额（投资占比*预估到账金额）
        Double allocationForecast = investProportion*capitalInvestTO.getForecastArriveMoney();
        capitalInvest.setAllocationForecast(allocationForecast);
        capitalInvest.setModifyTime(LocalDateTime.now());
        super.update(capitalInvest);
        return BeanTransform.copyProperties(capitalInvest,CapitalInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCapitalInvest(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Override
    public CapitalInvestBO getInnerProject(String innerProject) throws SerException {
        CallInfoDTO dto = new CallInfoDTO();
        dto.getConditions().add(Restrict.eq("innerProject", innerProject));
        List<CallInfo> callInfos = callInfoSer.findByCis(dto);
        CapitalInvest capitalInvest = new CapitalInvest();
        if (StringUtils.isNotBlank(innerProject)) {
            for(CallInfo callInfo : callInfos){
                capitalInvest.setInnerProject(callInfo.getInnerProject());//项目名称
                capitalInvest.setStartProjectTime(callInfo.getStartProjectTime());//开工时间
                capitalInvest.setEndProjectTime(callInfo.getEndProjectTime());//完工时间
                capitalInvest.setForecastArriveTime(callInfo.getForecastArriveTime());//预估到账时间
                capitalInvest.setInvestor(callInfo.getInvestor());//投资人
                capitalInvest.setInvestTotal(callInfo.getInvestTotal());
                capitalInvest.setThisInvestMoney(callInfo.getThisInvestMoney());
                capitalInvest.setAccumulativeInvestMoney(callInfo.getAccumulativeInvestMoney());
                capitalInvest.setInvestProportion(callInfo.getInvestProportion());
            }

        } else {
            throw new SerException("项目名称不能为空");
        }
        CapitalInvestBO bo = BeanTransform.copyProperties(capitalInvest,CapitalInvestBO.class);
        return bo;
    }
    @Override
    public List<String> getInnerProject() throws SerException {
        String[] fields = new String[]{"innerProject"};
        List<CapitalInvestBO> capitalInvestBOS = super.findBySql("SELECT innerProject FROM moneyside_callinfo innerProject  GROUP BY innerProject ", CapitalInvestBO.class, fields);

        List<String> capitalInvestList = capitalInvestBOS.stream().map(CapitalInvestBO::getInnerProject)
                .filter(innerProject -> (StringUtils.isNotBlank(innerProject))).distinct().collect(Collectors.toList());

        return capitalInvestList;
    }
}