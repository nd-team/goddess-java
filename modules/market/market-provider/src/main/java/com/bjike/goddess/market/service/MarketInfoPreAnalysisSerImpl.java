package com.bjike.goddess.market.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.market.bo.MarketInfoPreAnalysisBO;
import com.bjike.goddess.market.dto.MarketInfoPreAnalysisDTO;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.market.entity.MarketInfoPreAnalysis;
import com.bjike.goddess.market.entity.MarketInfoRecord;
import com.bjike.goddess.market.enums.GuideAddrStatus;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 市场信息记录业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 01:36 ]
 * @Description: [ 市场信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketSerCache")
@Service
public class MarketInfoPreAnalysisSerImpl extends ServiceImpl<MarketInfoPreAnalysis, MarketInfoPreAnalysisDTO> implements MarketInfoPreAnalysisSer {
    @Autowired
    private MarketInfoRecordSer marketInfoRecordSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看添加修改删除权限（部门级别）
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
                throw new SerException("您不是相应部门的人员，不可以进行此操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对预算模块分析权限（模块级别）
     */
    private void checkButgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是预算模块人员，不可以进行分析");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对规划模块分析权限（模块级别）
     */
    private void checkPlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是规划模块的人员，不可以进行分析");
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
     * 导航栏核对预算模块分析权限（模块级别)
     */
    private Boolean guideButgetIdentity() throws SerException {
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

    /**
     * 导航栏核对规划模块分析权限（模块级别)
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
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
        Boolean flagButget = guideButgetIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPlan = guidePlanIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagButget || flagPlan) {
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case BUDGETAUDIT:
                flag = guideButgetIdentity();
                break;
            case PLANAUDIT:
                flag = guidePlanIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countAnalysis(MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO) throws SerException {
        Long count = super.count(marketInfoPreAnalysisDTO);
        return count;
    }

    @Override
    public MarketInfoPreAnalysisBO getOne(String id) throws SerException {
        MarketInfoPreAnalysis marketInfoPreAnalysis = super.findById(id);
        return BeanTransform.copyProperties(marketInfoPreAnalysis, MarketInfoPreAnalysisBO.class);
    }

    @Override
    public List<MarketInfoPreAnalysisBO> findListAnalysis(MarketInfoPreAnalysisDTO marketInfoRecordDTO) throws SerException {
        checkSeeIdentity();
        List<MarketInfoPreAnalysis> marketInfoPreAnalyses = super.findByCis(marketInfoRecordDTO, true);
        return BeanTransform.copyProperties(marketInfoPreAnalyses, MarketInfoPreAnalysisBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void analysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        checkSeeIdentity();
        MarketInfoPreAnalysis marketInfoPreAnalysis = super.findById(marketInfoPreAnalysisTO.getId());
        marketInfoPreAnalysis.setModifyTime(LocalDateTime.now());
        marketInfoPreAnalysis.setEffctiveInfo(marketInfoPreAnalysisTO.getEffctiveInfo());
        marketInfoPreAnalysis.setPreliminaryAnalyDate(DateUtil.parseDate(marketInfoPreAnalysisTO.getPreliminaryAnalyDate()));
        marketInfoPreAnalysis.setPartnerRisk(marketInfoPreAnalysisTO.getPartnerRisk());
        marketInfoPreAnalysis.setRiskDescribe(marketInfoPreAnalysisTO.getRiskDescribe());
        marketInfoPreAnalysis.setPreliminaryAnaly(marketInfoPreAnalysisTO.getPreliminaryAnaly());
        marketInfoPreAnalysis.setConversionBuissOpp(marketInfoPreAnalysisTO.getConversionBuissOpp());
        marketInfoPreAnalysis.setConversionMarketFor(marketInfoPreAnalysisTO.getConversionMarketFor());
        marketInfoPreAnalysis.setConversionBussNegotia(marketInfoPreAnalysisTO.getConversionBussNegotia());
        marketInfoPreAnalysis.setRemark(marketInfoPreAnalysisTO.getRemark());
        super.update(marketInfoPreAnalysis);
        MarketInfoRecordDTO marketInfoRecordDTO = new MarketInfoRecordDTO();
        marketInfoRecordDTO.getConditions().add(Restrict.eq("marketInfoNum", marketInfoPreAnalysis.getMarketInfoNum()));
        MarketInfoRecord marketInfoRecord = marketInfoRecordSer.findOne(marketInfoRecordDTO);
        marketInfoRecord.setEffctiveInfo(marketInfoPreAnalysisTO.getEffctiveInfo());
        marketInfoRecord.setPreliminaryAnaly(marketInfoPreAnalysisTO.getPreliminaryAnaly());
        marketInfoRecord.setConversionBuissOpp(marketInfoPreAnalysisTO.getConversionBuissOpp());
        marketInfoRecord.setConversionMarketFor(marketInfoPreAnalysisTO.getConversionMarketFor());
        marketInfoRecord.setConversionBussNegotia(marketInfoPreAnalysisTO.getConversionBussNegotia());
        marketInfoRecordSer.update(marketInfoRecord);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void budgetAnalysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        checkButgetIdentity();
        MarketInfoPreAnalysis marketInfoPreAnalysis = super.findById(marketInfoPreAnalysisTO.getId());
        marketInfoPreAnalysis.setFundOperation(marketInfoPreAnalysisTO.getFundOperation());
        super.update(marketInfoPreAnalysis);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void planAnalysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        checkPlanIdentity();
        MarketInfoPreAnalysis marketInfoPreAnalysis = super.findById(marketInfoPreAnalysisTO.getId());
        marketInfoPreAnalysis.setAbleProvide(marketInfoPreAnalysisTO.getAbleProvide());
        super.update(marketInfoPreAnalysis);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAnalysis(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }


}