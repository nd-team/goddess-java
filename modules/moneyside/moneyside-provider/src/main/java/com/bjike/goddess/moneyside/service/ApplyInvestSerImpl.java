package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.ApplyInvestBO;
import com.bjike.goddess.moneyside.bo.CollectApplyInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.entity.ApplyInvest;
import com.bjike.goddess.moneyside.entity.CallInfo;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CollectApplyInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.ParameterScriptAssertValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:23 ]
 * @Description: [ 申请投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class ApplyInvestSerImpl extends ServiceImpl<ApplyInvest, ApplyInvestDTO> implements ApplyInvestSer {

    @Autowired
    private CallInfoSer callInfoSer;
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


    /**
     * 核对日期格式(年月日)
     */
    private void checkDate(ApplyInvestTO applyInvestTO) throws SerException {
        try {
            DateUtil.parseDate(applyInvestTO.getInvestDate());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }

    @Override
    public Long countApplyInvest(ApplyInvestDTO applyInvestDTO) throws SerException {
        Long count = super.count(applyInvestDTO);
        return count;
    }


    @Override
    public ApplyInvestBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ApplyInvest applyInvest = super.findById(id);
        return BeanTransform.copyProperties(applyInvest, ApplyInvestBO.class);
    }

    @Override
    public List<ApplyInvestBO> findListApplyInvest(ApplyInvestDTO applyInvestDTO) throws SerException {
        List<ApplyInvest> applyInvests = super.findByPage(applyInvestDTO);
        List<ApplyInvestBO> applyInvestBOS = BeanTransform.copyProperties(applyInvests, ApplyInvestBO.class);
        return applyInvestBOS;
    }

    public static void main(String[] args) {
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyInvestBO insertApplyInvest(ApplyInvestTO applyInvestTO) throws SerException {
        checkDate(applyInvestTO);
        ApplyInvest applyInvest = BeanTransform.copyProperties(applyInvestTO, ApplyInvest.class, true);
        applyInvest.setCreateTime(LocalDateTime.now());
        CallInfoDTO dto = new CallInfoDTO();
        List<CallInfo> callInfos = callInfoSer.findByCis(dto);
        //筹资总额
        Double totalFund = 0.0;
        //提取风险准备金率{1,2,3}
        Double extractRiskRserveRatio = 0.0;
        for (CallInfo callInfo : callInfos) {
            totalFund = callInfo.getTotalFund();
            extractRiskRserveRatio = callInfo.getExtractRiskRserveRatio();//3
            //TODO : 有问题
        }
        //投资占比（投资总额/筹资总额）
        applyInvest.setInvestProportion(applyInvestTO.getInvestTotal() / totalFund);

        //累计投资额
        ApplyInvestDTO applyInvestDTO = new ApplyInvestDTO();
        applyInvestDTO.getSorts().add("creatTime=asc");
        applyInvestDTO.getSorts().add("investor=asc");
        applyInvestDTO.getSorts().add("investObject=asc");
        List<ApplyInvest> applyInvests = super.findByCis(applyInvestDTO);

        List<ApplyInvest> final_applyInvests = new ArrayList<>();
        if (applyInvests != null && applyInvests.size() > 0) {
            ApplyInvest model = applyInvests.get(0);
            String aName = model.getInvestor();
            String bName = model.getInvestObject();
            Double accMoney = 0d;
            for (int i = 0; i < applyInvests.size(); i++) {
                ApplyInvest temp = applyInvests.get(i);
                String temp_aName = temp.getInvestor();
                String temp_bName = temp.getInvestObject();

                if (aName.equals(temp_aName) && bName.equals(temp_bName) && i == 0) {
                    accMoney = temp.getThisInvestMoney() == null ? 0d:temp.getThisInvestMoney();
                } else if (aName.equals(temp_aName) && bName.equals(temp_bName) && i != 0) {
                    accMoney = accMoney + (temp.getThisInvestMoney() == null ? 0d:temp.getThisInvestMoney());
                } else if ( !aName.equals(temp_aName) && !bName.equals(temp_bName) && i != 0) {
                    accMoney = 0d;
                    aName = temp_aName;
                    bName = temp_bName;
                }else if ( !aName.equals(temp_aName) && !bName.equals(temp_bName) && i == 0) {
                    accMoney = (temp.getThisInvestMoney() == null ? 0d:temp.getThisInvestMoney());
                    aName = temp_aName;
                    bName = temp_bName;
                }
                temp.setAccumulativeInvestMoney(accMoney);
                temp.setAccumulativeInvestProportion( accMoney/temp.getInvestTotal() );
                temp.setExtractRiskControlMargin( temp.getThisInvestMoney()*extractRiskRserveRatio );
                final_applyInvests.add( temp );
            }
        }

        //累计投资比（累计投资额/投资总额）
//        Double accumulativeInvestProportion = applyInvestTO.getAccumulativeInvestMoney() / applyInvestTO.getInvestTotal();
//        applyInvest.setAccumulativeInvestProportion(accumulativeInvestProportion);
//        提取风险准备金率
        // Double extractRiskRserveRatio = callInfo.getExtractRiskRserveRatio();
        //提取风险控制保证金（等于本次投资额*提取风险控制保证金率）
//        Double extractRiskControlMargin = applyInvestTO.getThisInvestMoney() / extractRiskRserveRatio;
//        applyInvest.setExtractRiskControlMargin(extractRiskControlMargin);
        if( final_applyInvests != null && final_applyInvests.size()>0 ){
            super.save(final_applyInvests);
        }
        return BeanTransform.copyProperties(new ApplyInvest(), ApplyInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ApplyInvestBO editApplyInvest(ApplyInvestTO applyInvestTO) throws SerException {
        if (StringUtils.isBlank(applyInvestTO.getId())) {
            throw new SerException("id不能为空");
        }
        ApplyInvest applyInvest = super.findById(applyInvestTO.getId());
        checkDate(applyInvestTO);
        BeanUtils.copyProperties(applyInvestTO, applyInvest);
        super.update(applyInvest);
        return BeanTransform.copyProperties(applyInvest, ApplyInvestBO.class);
    }

    /**
     * 计算方法
     */
    public ApplyInvest count(ApplyInvest applyInvest) throws SerException {
        //筹资总额
        CallInfo callInfo = new CallInfo();
        Double totalFund = callInfo.getTotalFund();
        //投资占比（投资总额/筹资总额）
        applyInvest.setInvestProportion(applyInvest.getInvestTotal() / totalFund);
        //累计投资额
//        String[] fields = new String[]{"creatTime"};
//        String sql = "SELECT * FROM moneyside_applyinvest WHERE createTime='asc'";
//        List<ApplyInvest> applyInvests = super.findBySql(sql, ApplyInvest.class, fields);
//        for (ApplyInvest model : applyInvests) {
//            if (applyInvests.size() == 0) {
//                applyInvest.setAccumulativeInvestMoney(applyInvest.getThisInvestMoney());
//            }
//        }
        //累计投资比（累计投资额/投资总额）
        Double accumulativeInvestProportion = applyInvest.getAccumulativeInvestMoney() / applyInvest.getInvestTotal();
        applyInvest.setAccumulativeInvestProportion(accumulativeInvestProportion);
        //提取风险准备金率
        Double extractRiskRserveRatio = callInfo.getExtractRiskRserveRatio();
        //提取风险控制保证金（本次投资额/提取风险准备金率）
        Double extractRiskControlMargin = applyInvest.getThisInvestMoney() / extractRiskRserveRatio;
        applyInvest.setExtractRiskControlMargin(extractRiskControlMargin);
        return applyInvest;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeApplyInvest(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<CollectApplyInvestBO> collectApplyInvest(CollectApplyInvestTO to) throws SerException {
        ApplyInvestDTO dto = new ApplyInvestDTO();
        String start = to.getStartDate();
        String end = to.getEndDate();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condition = new String[]{start, end};
            dto.getConditions().add(Restrict.between("investDate", condition));
        }
        if (null != to.getInvestor()) {
            dto.getConditions().add(Restrict.in("investor", to.getInvestor()));
        }
        if (null != to.getInvestObject()) {
            dto.getConditions().add(Restrict.in("investorObject", to.getInvestObject()));
        }
        return applyCollect(dto);
    }

    public List<CollectApplyInvestBO> applyCollect(ApplyInvestDTO dto) throws SerException {
        List<ApplyInvest> list = super.findByCis(dto);
        List<CollectApplyInvestBO> boList = new ArrayList<>();
        for (ApplyInvest model : list) {
            CollectApplyInvestBO bo = new CollectApplyInvestBO();
            bo.setInvestor(model.getInvestor());
            bo.setInvestObject(model.getInvestObject());
            bo.setInvestForm(model.getInvestForm());
            bo.setInvestDate(DateUtil.dateToString(model.getInvestDate()));
            bo.setExtractRiskControlMargin(model.getExtractRiskControlMargin());
            boList.add(bo);

        }
        Double thisInvestMoney = 0.0;
        Double extractRiskControlMargin = 0.0;
        if (list != null) {
            thisInvestMoney = list.stream().filter(p -> p.getThisInvestMoney() != null).mapToDouble(p -> p.getThisInvestMoney()).sum();
            extractRiskControlMargin = list.stream().filter(p -> p.getExtractRiskControlMargin() != null).mapToDouble(p -> p.getExtractRiskControlMargin()).sum();
            CollectApplyInvestBO totalBo = new CollectApplyInvestBO("合计", "", "", "", thisInvestMoney, extractRiskControlMargin);
            boList.add(totalBo);
        } else {
            CollectApplyInvestBO totalBo = new CollectApplyInvestBO("合计", "", "", "", thisInvestMoney, extractRiskControlMargin);
            boList.add(totalBo);
        }
        return boList;
    }
}