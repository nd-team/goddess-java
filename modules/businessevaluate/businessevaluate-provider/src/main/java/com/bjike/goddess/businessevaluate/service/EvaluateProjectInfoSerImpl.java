package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.EvaluateProjectInfoBO;
import com.bjike.goddess.businessevaluate.bo.ProjectProfitRateBO;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.businessevaluate.to.EvaluateProjectInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 项目基本信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class EvaluateProjectInfoSerImpl extends ServiceImpl<EvaluateProjectInfo, EvaluateProjectInfoDTO> implements EvaluateProjectInfoSer {

    @Autowired
    private ProjectCostSer projectCostSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EvaluateProjectInfoBO addModel(EvaluateProjectInfoTO to) throws SerException {

        EvaluateProjectInfo model = BeanTransform.copyProperties(to, EvaluateProjectInfo.class, true);
        if (to.getYears() != null && to.getMonths() != null && to.getDays() != null) {
            StringBuilder experienceTime = new StringBuilder();
            experienceTime.append(to.getYears());
            experienceTime.append("年");
            experienceTime.append(to.getMonths());
            experienceTime.append("月");
            experienceTime.append(to.getDays());
            experienceTime.append("日");
            model.setExperienceTime(experienceTime.toString());
        } else {
            throw new SerException("工期经历时间不能为空!");
        }
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, EvaluateProjectInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EvaluateProjectInfoBO editModel(EvaluateProjectInfoTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            EvaluateProjectInfo model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, EvaluateProjectInfoBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<EvaluateProjectInfoBO> pageList(EvaluateProjectInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<EvaluateProjectInfo> list = super.findByPage(dto);

        return BeanTransform.copyProperties(list, EvaluateProjectInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectProfitRateBO> profitPageList(EvaluateProjectInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<EvaluateProjectInfo> list = super.findByPage(dto);
        List<ProjectProfitRateBO> boList = new ArrayList<ProjectProfitRateBO>();
        for (EvaluateProjectInfo model : list) {
            //查询费用
            ProjectCostDTO costDTO = new ProjectCostDTO();
            costDTO.getConditions().add(Restrict.eq("projectInfoId", model.getId()));
            ProjectCost projectCost = projectCostSer.findOne(costDTO);

            if (projectCost == null || projectCost.getServiceCost() == null || projectCost.getEntertainCost() == null || projectCost.getCommission() == null) {
                continue;
            }
            Double fee = projectCost.getServiceCost() + projectCost.getEntertainCost() + projectCost.getCommission() + projectCost.getAnother();

            ProjectProfitRateBO bo = new ProjectProfitRateBO();
            //利润 = 项目总金额 - 成本 -管理费 -税金 - 费用
            Double profit = model.getTotalAmount() - model.getCost() - model.getManageCost() - model.getTaxes() - fee;
            //利润率 = 利润 / 项目总金额

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            Double profitRate = profit / model.getTotalAmount() * 100;
            Double costRate = profit / model.getCost() * 100;
            Double feeRate = profit / fee * 100;

            //转换利率百分比
            String profitRateStr = decimalFormat.format(profitRate) + "%";
            String costRateStr = decimalFormat.format(costRate) + "%";
            String feeRateStr = decimalFormat.format(feeRate) + "%";

            bo.setArea(model.getArea());
            bo.setProject(model.getProject());
            bo.setStartTime(model.getStartTime().toString());
            bo.setEndTime(model.getEndTime().toString());
            bo.setProfit(profit);
            bo.setProjectProfitRate(profitRateStr);
            bo.setCostProfitRate(costRateStr);
            bo.setFeeProfitRate(feeRateStr);
            boList.add(bo);
        }
        return boList;
    }

    /**
     * 查询利润率最高及最低项目
     *
     * @return 利润率最高及最低项目
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectProfitRateBO> profitScope() throws SerException {

        List<EvaluateProjectInfo> list = super.findAll();
        List<ProjectProfitRateBO> boList = new ArrayList<ProjectProfitRateBO>();
        for (EvaluateProjectInfo model : list) {
            //查询费用
            ProjectCostDTO costDTO = new ProjectCostDTO();
            costDTO.getConditions().add(Restrict.eq("projectInfoId", model.getId()));
            ProjectCost projectCost = projectCostSer.findOne(costDTO);

            if (projectCost == null || projectCost.getServiceCost() == null || projectCost.getEntertainCost() == null || projectCost.getCommission() == null) {
                continue;
            }
            Double fee = projectCost.getServiceCost() + projectCost.getEntertainCost() + projectCost.getCommission();

            ProjectProfitRateBO bo = new ProjectProfitRateBO();
            //利润 = 项目总金额 - 成本 -管理费 -税金 - 费用
            Double profit = model.getTotalAmount() - model.getCost() - model.getManageCost() - model.getTaxes() - fee;
            //利润率 = 利润 / 项目总金额

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            Double profitRate = profit / model.getTotalAmount() * 100;
            String profitRateStr = decimalFormat.format(profitRate) + "%";

            bo.setArea(model.getArea());
            bo.setProject(model.getProject());
            bo.setStartTime(model.getStartTime().toString());
            bo.setEndTime(model.getEndTime().toString());
            bo.setProjectProfitRateNum(profitRate);
            bo.setProjectProfitRate(profitRateStr);
            boList.add(bo);
        }
        return swapSize(boList);
    }

    //排序利润率最高最低项目
    public List<ProjectProfitRateBO> swapSize(List<ProjectProfitRateBO> boList) throws SerException {
        //设置最快最慢月份
        Collections.sort(boList, new Comparator<ProjectProfitRateBO>() {
            @Override
            public int compare(ProjectProfitRateBO bo1, ProjectProfitRateBO bo2) {
                return bo1.getProjectProfitRateNum().compareTo(bo2.getProjectProfitRateNum());
            }
        });
        List<ProjectProfitRateBO> returnBoList = new ArrayList<ProjectProfitRateBO>();
        if (boList != null && !boList.isEmpty()) {
            ProjectProfitRateBO bo1 = boList.get(0);
            returnBoList.add(new ProjectProfitRateBO("最低", bo1.getArea(), bo1.getProject(), bo1.getStartTime(), bo1.getEndTime(), bo1.getProjectProfitRate()));
            ProjectProfitRateBO bo2 = boList.get(boList.size() - 1);
            returnBoList.add(new ProjectProfitRateBO("最高", bo2.getArea(), bo2.getProject(), bo2.getStartTime(), bo2.getEndTime(), bo2.getProjectProfitRate()));
        }
        return returnBoList;
    }
}