package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.bo.WarnBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysis;
import com.bjike.goddess.projectmarketfee.entity.Warn;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 费用效益分析业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class CostAnalysisSerImpl extends ServiceImpl<CostAnalysis, CostAnalysisDTO> implements CostAnalysisSer {
    @Autowired
    private WarnSer warnSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public CostAnalysisBO save(CostAnalysisTO to) throws SerException {
        CostAnalysis costAnalysis = BeanTransform.copyProperties(to, CostAnalysis.class, true);
        super.save(costAnalysis);
        return BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(CostAnalysisTO to) throws SerException {
        CostAnalysis costAnalysis = super.findById(to.getId());
        LocalDateTime a = costAnalysis.getCreateTime();
        LocalDateTime b = costAnalysis.getModifyTime();
        costAnalysis = BeanTransform.copyProperties(to, CostAnalysis.class, true);
        costAnalysis.setCreateTime(a);
        costAnalysis.setModifyTime(b);
        super.update(costAnalysis);
    }

    @Override
    public List<CostAnalysisBO> list(CostAnalysisDTO dto) throws SerException {
        List<CostAnalysis> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, CostAnalysisBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public CostAnalysisBO findByID(String id) throws SerException {
        CostAnalysis costAnalysis = super.findById(id);
        return BeanTransform.copyProperties(costAnalysis, CostAnalysisBO.class);
    }

    /**
     * 获取所有地区
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allArrivals() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getArrival());
        }
        return set;
    }

    /**
     * 获取所有项目组
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allProjects() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getProject());
        }
        return set;
    }

    public List<CostAnalysisBO> arrivalCount(Integer month) throws SerException {

    }

    /**
     * 获取所有项目名称
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allProjectNames() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getProjectName());
        }
        return set;
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private Set<Integer> allYears() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getYear());
        }
        return set;
    }

    /**
     * 查找所有月份
     *
     * @return class Integer
     * @throws SerException
     */
    private Set<Integer> allMonths() throws SerException {
        List<CostAnalysis> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (CostAnalysis costAnalysis : list) {
            set.add(costAnalysis.getMonth());
        }
        return set;
    }

    private List<CostAnalysisBO> all() throws SerException {
        List<CostAnalysis> list = super.findAll();
        List<Warn> warns=warnSer.findAll();
        List<CostAnalysisBO> boList = new ArrayList<CostAnalysisBO>();
        for (CostAnalysis costAnalysis : list) {
            CostAnalysisBO costAnalysisBO = new CostAnalysisBO();
            BeanUtils.copyProperties(costAnalysis, costAnalysisBO);
            costAnalysisBO.setExpectedScale(costAnalysis.getExpectedMarketCost() / costAnalysis.getExpectedIncome());
            costAnalysisBO.setDifferences(costAnalysis.getExpectedMarketCost() - costAnalysisBO.getActualMarketCost());
            costAnalysisBO.setExpectedScale(costAnalysisBO.getActualMarketCost() / costAnalysis.getExpectedIncome());
            if(warns.size()!=0) {
                Double a = costAnalysisBO.getActualScale() - costAnalysisBO.getExpectedScale();
                if(a>warns.get(0).getWarnValue()){
                    costAnalysisBO.setWarn("超出");
                }
            }
        }
    }
}