package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.marketactivitymanage.api.MarketServeApplyAPI;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeSummary;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 市场招待汇总业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketactivitymanageSerCache")
@Service
public class MarketServeSummarySerImpl extends ServiceImpl<MarketServeSummary, MarketServeSummaryDTO> implements MarketServeSummarySer {

    @Autowired
    private MarketServeApplySer marketServeApplySer;

    @Autowired
    private CustomerInfoSer customerInfoSer;

    /**
     * 分页查询市场招待汇总
     *
     * @param dto 市场招待汇总dto
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    public List<MarketServeSummaryBO> list(MarketServeSummaryDTO dto) throws SerException {
        List<MarketServeSummary> list = super.findByPage(dto);
        List<MarketServeSummaryBO> listBO = BeanTransform.copyProperties(list, MarketServeSummaryBO.class);
        return listBO;
    }

    /**
     * 保存市场招待汇总
     *
     * @param to 市场招待汇总to
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    public MarketServeSummaryBO save(MarketServeSummaryTO to) throws SerException {
        MarketServeSummary marketServeSummary = BeanTransform.copyProperties(to, MarketServeSummary.class, true);
        marketServeSummary = super.save(marketServeSummary);
        MarketServeSummaryBO bo = BeanTransform.copyProperties(marketServeSummary, MarketServeSummaryBO.class);
        return bo;
    }

    /**
     * 编辑市场招待汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    public void update(MarketServeSummaryTO to) throws SerException {
        MarketServeSummary entity = BeanTransform.copyProperties(to, MarketServeSummary.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 冻结市场招待申请汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    public void thaw(MarketServeSummaryTO to) throws SerException {
        this.update(to);
    }

    /**
     * 解冻市场招待申请汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    public void congeal(MarketServeSummaryTO to) throws SerException {
        this.update(to);
    }

    /**
     * 市场招待申请汇总
     *
     * @param to 市场招待汇总to
     * @return class ServeSummaryBO
     * @throws SerException
     */
    @Override
    public List<ServeSummaryBO> summarize(MarketServeSummaryTO to) throws SerException {
        boolean isApply = to.getType();                 //判断是市场招待申请汇总还是实际汇总
        if (isApply) {
            return summarizePlan(to);
        } else {
            return summarizeActual(to);
        }
    }

    /**
     * 市场招待记录汇总
     *
     * @param to
     * @return
     */
    private List<ServeSummaryBO> summarizeActual(MarketServeSummaryTO to) {
        String[] projectGroups = to.getProjectGroups();//获取项目组
        String startTime = to.getStartTime();          //开始活动时间点
        String endTime = to.getEndTime();              //结束活动时间点
        List<ServeSummaryBO> boList = new ArrayList<>(0);

        for (String projectGroup : projectGroups) {

        }

        return null;
    }

    /**
     * 市场招待记录申请汇总
     *
     * @param to
     * @return
     */
    private List<ServeSummaryBO> summarizePlan(MarketServeSummaryTO to) throws SerException {
        String[] projectGroups = to.getProjectGroups();//获取项目组
        String startTimeString = to.getStartTime();          //开始活动时间点
        String endTimeString = to.getEndTime();              //结束活动时间点
        LocalDateTime startTime = DateUtil.parseDateTime(startTimeString);//起始时间
        LocalDateTime endTime = DateUtil.parseDateTime(endTimeString);//结束时间
        LocalDateTime[] planActivityTiming = new LocalDateTime[]{startTime, endTime};

        MarketServeApplyDTO dto = new MarketServeApplyDTO();
        dto.getConditions().add(Restrict.in("projectName", projectGroups));
        dto.getConditions().add(Restrict.between("planActivityTiming", planActivityTiming)); //时间范围查询
        dto.getSorts().add("projectName=asc");
        dto.getSorts().add("planActivityTiming=asc");
        List<MarketServeApply> marketServeApplyList = marketServeApplySer.findByCis(dto, true);
        List<ServeSummaryBO> serveSummaryBOList = new ArrayList<>(0);

        //遍历查询集合
        for (MarketServeApply obj : marketServeApplyList) {
            String clientName = getClientName(obj);
            String planActivityTimingStr = obj.getPlanActivityTiming().toString().replace("T", " ").substring(0, 19);
            ServeSummaryBO serveSummaryBO = new ServeSummaryBO();
            serveSummaryBO.setProjectName(obj.getProjectName());//项目名称
            serveSummaryBO.setArea(obj.getArea());//设置地区
            serveSummaryBO.setServePrincipal(obj.getServePrincipal());//设置招待负责人
            serveSummaryBO.setClientName(clientName);//设置客户姓名
            serveSummaryBO.setActivityType(obj.getPlanActivityType());//设置计划活动类型
            serveSummaryBO.setActivityTiming(planActivityTimingStr);//设置计划活动时间点
            serveSummaryBO.setWhetherTemporaryServe(obj.getWhetherTemporaryServe());//设置是否临时招待
        }

        return null;
    }

    /**
     * 获取对应记录的客户姓名
     *
     * @param obj
     * @return
     */
    private String getClientName(MarketServeApply obj) throws SerException {
        StringBuffer clientNames = new StringBuffer();
        String marketServeId = obj.getId();
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", marketServeId));
        List<CustomerInfo> boList = customerInfoSer.findByCis(dto);
        for (CustomerInfo info : boList) {
            clientNames.append(info.getClientName()).append(",");
        }
        String clientNameString = clientNames.toString();

        return clientNameString.substring(0, clientNameString.length() - 1);
    }
}