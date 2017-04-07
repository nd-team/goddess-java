package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.BusinessEvaluateCollectBO;
import com.bjike.goddess.businessevaluate.bo.EvaluateCollectTotalBO;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.dto.ProblemDisposeDTO;
import com.bjike.goddess.businessevaluate.dto.ProjectCostDTO;
import com.bjike.goddess.businessevaluate.entity.BusinessEvaluateCollect;
import com.bjike.goddess.businessevaluate.entity.EvaluateProjectInfo;
import com.bjike.goddess.businessevaluate.entity.ProblemDispose;
import com.bjike.goddess.businessevaluate.entity.ProjectCost;
import com.bjike.goddess.businessevaluate.enums.CooperateWay;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商务评估汇总业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class BusinessEvaluateCollectSerImpl extends ServiceImpl<BusinessEvaluateCollect, BusinessEvaluateCollectDTO> implements BusinessEvaluateCollectSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;
    @Autowired
    private ProblemDisposeSer problemDisposeSer;
    @Autowired
    private ProjectCostSer projectCostSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BusinessEvaluateCollectBO insertModel(BusinessEvaluateCollectTO to) throws SerException {

        BusinessEvaluateCollect model = BeanTransform.copyProperties(to, BusinessEvaluateCollect.class, true);
        model.setOperateUser(userAPI.currentUser().getUsername());
        super.save(model);
        return BeanTransform.copyProperties(to, BusinessEvaluateCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BusinessEvaluateCollectBO updateModel(BusinessEvaluateCollectTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
            BusinessEvaluateCollect model = super.findById(to.getId());
            if (model != null) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                model.setOperateUser(userAPI.currentUser().getUsername());
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, BusinessEvaluateCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {

        if (!StringUtils.isEmpty(id)) {
            BusinessEvaluateCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void breakFreeze(String id) throws SerException {
        if (!StringUtils.isEmpty(id)) {
            BusinessEvaluateCollect model = super.findById(id);
            if (model != null) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("更新对象不能为空!");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<BusinessEvaluateCollectBO> pageList(BusinessEvaluateCollectDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<BusinessEvaluateCollect> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, BusinessEvaluateCollectBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<EvaluateCollectTotalBO> collectPageList(String area, String project) throws SerException {

        EvaluateProjectInfoDTO dto = new EvaluateProjectInfoDTO();
        dto.getSorts().add("createTime=desc");
        String sql = "select distinct area ,1 from businessevaluate_evaluateprojectinfo info where 0 = 0 ";
        if (!StringUtils.isEmpty(project)) {
            dto.getConditions().add(Restrict.eq("project", project));
            sql = sql + "and info.project = '" + project + "'";
        }
        if (!StringUtils.isEmpty(area)) {
            dto.getConditions().add(Restrict.eq("area", area));
            sql = sql + "and info.area = '" + area + "'";
        }
        //查询符合条件的地区
        List<EvaluateProjectInfo> areaList = evaluateProjectInfoSer.findBySql(sql, EvaluateProjectInfo.class, new String[]{"area"});
        //查询符合条件的项目信息
        List<EvaluateProjectInfo> infoList = evaluateProjectInfoSer.findByCis(dto);
        List<EvaluateCollectTotalBO> boList = BeanTransform.copyProperties(areaList, BusinessEvaluateCollectBO.class);

        if (boList != null && !boList.isEmpty()) {

            if (infoList != null && !infoList.isEmpty()) {

                for (EvaluateCollectTotalBO bo : boList) {
                    Double areaTotalAmount = 0.0;
                    Double areaTotalCost = 0.0;
                    Double areaTotalTaxes = 0.0;
                    Double areaTotalManageCost = 0.0;
                    Double areaTotalProfit = 0.0;
                    Double areaTotalFee = 0.0;

                    Integer longtermCount = 0;
                    Integer itemCount = 0;
                    Integer agencyCount = 0;

                    Integer problemCount = 0;

                    for (EvaluateProjectInfo info : infoList) {

                        if (bo.getArea().equals(info.getArea())) {
                            //设置总金额、成本、税金、项目管理费、费用、项目利润
                            areaTotalAmount = areaTotalAmount + info.getTotalAmount();
                            areaTotalCost = areaTotalCost + info.getCost();
                            areaTotalTaxes = areaTotalTaxes + info.getTaxes();
                            areaTotalManageCost = areaTotalManageCost + info.getManageCost();

                            areaTotalFee = areaTotalFee + findProjectCost(info.getId());
                            areaTotalProfit = areaTotalProfit + (info.getTotalAmount() - info.getCost() - info.getManageCost() - info.getTaxes() - areaTotalFee);

                            if (info.getCooperateWay() == CooperateWay.LONGTERM) {
                                longtermCount++;
                            } else if (info.getCooperateWay() == CooperateWay.ITEM) {
                                itemCount++;
                            } else if (info.getCooperateWay() == CooperateWay.AGENCY) {
                                agencyCount++;
                            }

                            //设置项目问题数量
                            problemCount = problemCount + findProblemCount(info.getId());
                        }
                    }
                    bo.setProjectTotalAmount(areaTotalAmount);
                    bo.setProjectCost(areaTotalCost);
                    bo.setProjectTaxes(areaTotalTaxes);
                    bo.setProjectManageFee(areaTotalManageCost);
                    bo.setProjectProfit(areaTotalProfit);
                    bo.setProjectFee(areaTotalFee);
                    bo.setLongtermCount(longtermCount);
                    bo.setItemCount(itemCount);
                    bo.setAgencyCount(agencyCount);
                    bo.setProblemCount(problemCount);
                }
            }

            Double totalAmount = boList.stream().filter(p -> null != p.getProjectTotalAmount()).mapToDouble(p -> p.getProjectTotalAmount()).sum();
            Integer totalProblemCount = boList.stream().filter(p -> null != p.getProblemCount()).mapToInt(p -> p.getProblemCount()).sum();
            Double totalCost = boList.stream().filter(p -> null != p.getProjectCost()).mapToDouble(p -> p.getProjectCost()).sum();
            Double totalFee = boList.stream().filter(p -> null != p.getProjectFee()).mapToDouble(p -> p.getProjectFee()).sum();
            Double totalTaxes = boList.stream().filter(p -> null != p.getProjectTaxes()).mapToDouble(p -> p.getProjectTaxes()).sum();
            Double totalManageFee = boList.stream().filter(p -> null != p.getProjectManageFee()).mapToDouble(p -> p.getProjectManageFee()).sum();
            Double totalProfit = boList.stream().filter(p -> null != p.getProjectProfit()).mapToDouble(p -> p.getProjectProfit()).sum();
            Integer totalAgency = boList.stream().filter(p -> null != p.getAgencyCount()).mapToInt(p -> p.getAgencyCount()).sum();
            Integer totalItem = boList.stream().filter(p -> null != p.getItemCount()).mapToInt(p -> p.getItemCount()).sum();
            Integer totalLongtern = boList.stream().filter(p -> null != p.getLongtermCount()).mapToInt(p -> p.getLongtermCount()).sum();

            EvaluateCollectTotalBO totalBO =new EvaluateCollectTotalBO("合计",null,totalProblemCount,totalLongtern,
                    totalItem,totalAgency,totalCost,totalFee,totalTaxes,totalManageFee,totalProfit,totalAmount);

            boList.add(totalBO);
        }
        return boList;
    }

    //计算项目费用
    public Double findProjectCost(String id) throws SerException {
        ProjectCostDTO costDTO = new ProjectCostDTO();
        costDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        List<ProjectCost> list = projectCostSer.findByCis(costDTO);
        Double totalCost = 0.0;
        if (list != null && !list.isEmpty()) {
            Double cost = 0.0;
            for (ProjectCost model : list) {
                cost = cost + model.getServiceCost() + model.getEntertainCost() + model.getCommission() + model.getAnother();
            }
            totalCost = cost;
        }
        return totalCost;
    }

    //计算项目问题数量
    public Integer findProblemCount(String id) throws SerException {
        ProblemDisposeDTO problemDTO = new ProblemDisposeDTO();
        problemDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        List<ProblemDispose> list = problemDisposeSer.findByCis(problemDTO);
        Integer problemSize = 0;
        if (list != null && !list.isEmpty()) {
            problemSize = list.size();
        }
        return problemSize;
    }


}