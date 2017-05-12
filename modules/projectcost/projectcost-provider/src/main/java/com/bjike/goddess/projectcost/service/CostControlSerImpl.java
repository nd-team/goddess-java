package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.bo.*;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.entity.CostControl;
import com.bjike.goddess.projectcost.to.CostControlTO;
import com.bjike.goddess.projectcost.to.FindTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目成本控制业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目成本控制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcostSerCache")
@Service
public class CostControlSerImpl extends ServiceImpl<CostControl, CostControlDTO> implements CostControlSer {

    @Autowired
    private ArtificialCostSer artificialCostSer;

    @Autowired
    private CarCostSer carCostSer;

    @Autowired
    private OtherExpensesSer otherExpensesSer;

    @Override
    public CostControlBO save(CostControlTO to) throws SerException {
        CostControl entity = BeanTransform.copyProperties(to, CostControl.class);
        //@TODO 获取资金管理数据
        entity.setActualIncome(0d);
        this.countControl(entity);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CostControlBO.class);
    }

    private void countControl(CostControl entity) throws SerException {
        FindTO to = BeanTransform.copyProperties(entity, FindTO.class);

        List<ArtificialCostBO> artificialCostList = artificialCostSer.findByTO(to);
        if (artificialCostList == null)
            artificialCostList = new ArrayList<>(0);
        List<CarCostBO> carCostList = carCostSer.findByTO(to);
        if (carCostList == null)
            carCostList = new ArrayList<>(0);
        List<OtherExpensesBO> otherExpensesList = otherExpensesSer.findByTO(to);
        if (otherExpensesList == null)
            otherExpensesList = new ArrayList<>(0);

        entity.setTargetCost(artificialCostList.stream().mapToDouble(ArtificialCostBO::getTargetCost).sum());
        entity.setActualCost(artificialCostList.stream().mapToDouble(ArtificialCostBO::getActualCost).sum());
        entity.setTargetCar(carCostList.stream().mapToDouble(CarCostBO::getTargetCost).sum());
        entity.setActualCar(carCostList.stream().mapToDouble(CarCostBO::getActualCost).sum());
        entity.setTargetOther(otherExpensesList.stream().mapToDouble(OtherExpensesBO::getTarget).sum());
        entity.setActualOther(otherExpensesList.stream().mapToDouble(OtherExpensesBO::getActual).sum());
        entity.setTargetTotal(entity.getTargetCost() + entity.getTargetCar() + entity.getTargetOther());
        entity.setActualTotal(entity.getActualCost() + entity.getActualCar() + entity.getActualOther());

        //计算利润
        entity.setTargetProfit(entity.getTargetIncome() - entity.getTargetTotal());
        entity.setActualProfit(entity.getActualIncome() - entity.getActualTotal());

        //计算收入比
        if (entity.getTargetTotal() == 0 || entity.getTargetIncome() == 0)
            entity.setTargetContrast(0d);
        else
            entity.setTargetContrast(this.decimal(entity.getTargetIncome() / entity.getTargetTotal()));
        if (entity.getActualIncome() == 0 || entity.getActualTotal() == 0)
            entity.setActualContrast(0d);
        else
            entity.setActualContrast(this.decimal(entity.getActualIncome() / entity.getActualTotal()));

        //计算利润率
        if (entity.getTargetProfit() == 0 || entity.getTargetIncome() == 0)
            entity.setTargetRate(0d);
        else
            entity.setTargetRate(this.decimal(entity.getTargetProfit() / entity.getTargetIncome()));
        if (entity.getActualIncome() == 0 || entity.getActualProfit() == 0)
            entity.setActualRate(0d);
        else
            entity.setActualRate(this.decimal(entity.getActualProfit() / entity.getActualIncome()));
    }

    private Double decimal(Double number) throws SerException {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public CostControlBO update(CostControlTO to) throws SerException {
        CostControl entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countControl(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostControlBO.class);
    }

    @Override
    public CostControlBO delete(String id) throws SerException {
        CostControl entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CostControlBO.class);
    }

    @Override
    public CostControlBO updateActual(CostControlTO to) throws SerException {
        return this.update(to);
    }

    @Override
    public List<CostControlBO> maps(CostControlDTO dto) throws SerException {
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        dto.getSorts().add("area=desc");
        dto.getSorts().add("project=desc");
        dto.getSorts().add("name=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), CostControlBO.class);
    }

    @Override
    public CostControlBO getById(String id) throws SerException {
        CostControl entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, CostControlBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CostControlDTO dto = new CostControlDTO();
        return super.count(dto);
    }

    @Override
    public List<HistogramBO> histogramCollect(FindTO to) throws SerException {
        List<CostControl> list = this.selectByTo(to);
        List<HistogramBO> collectList = new ArrayList<>(0);
        String area = "";
        for (CostControl entity : list) {
            if (!area.equals(entity.getArea())) {
                area = entity.getArea();
                List<CostControl> temp = list.stream()
                        .filter(c -> c.getArea().equals(entity.getArea()))
                        .collect(Collectors.toList());
                HistogramBO bo = new HistogramBO();
                bo.setArea(area);
                bo.setTargetIncome(temp.stream().mapToDouble(CostControl::getTargetIncome).sum());
                bo.setActualIncome(temp.stream().mapToDouble(CostControl::getActualIncome).sum());
                bo.setTargetCost(temp.stream().mapToDouble(CostControl::getTargetCost).sum());
                bo.setActualCost(temp.stream().mapToDouble(CostControl::getActualCost).sum());
                bo.setTargetCar(temp.stream().mapToDouble(CostControl::getTargetCar).sum());
                bo.setActualCar(temp.stream().mapToDouble(CostControl::getActualCar).sum());
                bo.setTargetOther(temp.stream().mapToDouble(CostControl::getTargetOther).sum());
                bo.setActualOther(temp.stream().mapToDouble(CostControl::getActualOther).sum());
                bo.setTargetTotal(temp.stream().mapToDouble(CostControl::getTargetTotal).sum());
                bo.setActualTotal(temp.stream().mapToDouble(CostControl::getActualTotal).sum());
                bo.setTargetProfit(temp.stream().mapToDouble(CostControl::getTargetProfit).sum());
                bo.setActualProfit(temp.stream().mapToDouble(CostControl::getActualProfit).sum());
                collectList.add(bo);
            }
        }
        return collectList;
    }

    private List<CostControl> selectByTo(FindTO to) throws SerException {
        CostControlDTO dto = new CostControlDTO();
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getProject()))
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        if (StringUtils.isNotBlank(to.getName()))
            dto.getConditions().add(Restrict.eq("name", to.getName()));
        dto.getSorts().add("area=desc");
        dto.getSorts().add("project=desc");
        dto.getSorts().add("name=desc");
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        List<CostControl> list = super.findByCis(dto);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd())) {
            LocalDate start = LocalDate.parse(to.getStart()), end = LocalDate.parse(to.getEnd());
            List<CostControl> temp = new ArrayList<>(0);
            String format = "";
            for (CostControl entity : list) {
                if (entity.getMonth() >= 10)
                    format = "%d-%d-01";
                else
                    format = "%d-0%d-01";
                LocalDate time = LocalDate.parse(String.format(format, end.getYear(), end.getMonth()));
                if (!start.isAfter(time) && !end.isBefore(time))
                    temp.add(entity);
            }
            return temp;
        }
        return list;
    }

    @Override
    public List<CostControlBO> findByTo(FindTO to) throws SerException {
        return BeanTransform.copyProperties(this.selectByTo(to), CostControlBO.class);
    }
}