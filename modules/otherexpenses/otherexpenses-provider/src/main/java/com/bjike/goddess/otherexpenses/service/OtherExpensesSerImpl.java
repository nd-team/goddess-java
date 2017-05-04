package com.bjike.goddess.otherexpenses.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.otherexpenses.bo.*;
import com.bjike.goddess.otherexpenses.dto.OtherExpensesDTO;
import com.bjike.goddess.otherexpenses.entity.OtherExpenses;
import com.bjike.goddess.otherexpenses.to.CollectTO;
import com.bjike.goddess.otherexpenses.to.OtherExpensesTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 其他费用业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-03 11:49 ]
 * @Description: [ 其他费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "otherexpensesSerCache")
@Service
public class OtherExpensesSerImpl extends ServiceImpl<OtherExpenses, OtherExpensesDTO> implements OtherExpensesSer {

    @Override
    public OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        OtherExpenses entity = BeanTransform.copyProperties(to, OtherExpenses.class);
        this.countExpenses(entity);
        super.save(entity);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    private void countExpenses(OtherExpenses entity) throws SerException {
        entity.setRatio(new BigDecimal(entity.getTarget() / entity.getActual() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
        entity.setBalance(entity.getTarget() - entity.getActual());
    }

    @Override
    public OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        OtherExpenses entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该费用不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countExpenses(entity);
        super.update(entity);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public OtherExpensesBO delete(String id) throws SerException {
        OtherExpenses entity = super.findById(id);
        if (null == entity)
            throw new SerException("该费用不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public OtherExpensesBO getById(String id) throws SerException {
        OtherExpenses entity = super.findById(id);
        return BeanTransform.copyProperties(entity, OtherExpensesBO.class);
    }

    @Override
    public List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        dto.getSorts().add("area=desc");
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=asc");
        dto.getSorts().add("project=asc");
        dto.getSorts().add("type=asc");
        dto.getSorts().add("name=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), OtherExpensesBO.class);
    }

    private List<OtherExpenses> findCollect(CollectTO to) throws SerException {
        OtherExpensesDTO dto = new OtherExpensesDTO();
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getName()))
            dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (StringUtils.isNotBlank(to.getProject()))
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        List<OtherExpenses> list = super.findByCis(dto);
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd())) {
            LocalDate start = LocalDate.parse(to.getStart()), end = LocalDate.parse(to.getEnd());
            List<OtherExpenses> timeList = new ArrayList<>(0);
            for (OtherExpenses entity : list) {
                LocalDate entityTime;
                if (entity.getMonth() >= 10)
                    entityTime = LocalDate.parse(String.format("%d-%d-01", entity.getYear(), entity.getMonth()));
                else
                    entityTime = LocalDate.parse(String.format("%d-0%d-01", entity.getYear(), entity.getMonth()));
                if (!entityTime.isAfter(end) && !entityTime.isBefore(start))
                    timeList.add(entity);
            }
            return timeList;
        }
        return list;
    }

    @Override
    public List<AreaCollectBO> areaCollect(CollectTO to) throws SerException {
        List<OtherExpenses> list = this.findCollect(to).stream()
                .sorted(Comparator.comparing(OtherExpenses::getArea)
                        .thenComparing(OtherExpenses::getYear)
                        .thenComparing(OtherExpenses::getMonth))
                .collect(Collectors.toList());
        List<AreaCollectBO> collectBOs = new ArrayList<>(0);
        String area = "";
        Integer year = 0, month = 0;
        for (OtherExpenses entity : list)
            if (!area.equals(entity.getArea()) || year != entity.getYear() || month != entity.getMonth()) {
                area = entity.getArea();
                year = entity.getYear();
                month = entity.getMonth();
                List<OtherExpenses> temp = list.stream()
                        .filter(o -> o.getArea().equals(entity.getArea())
                                && o.getYear() == entity.getYear()
                                && o.getMonth() == entity.getMonth())
                        .collect(Collectors.toList());
                AreaCollectBO bo = new AreaCollectBO();
                bo.setArea(area);
                bo.setYear(year);
                bo.setMonth(month);
                bo.setTarget(temp.stream().mapToDouble(OtherExpenses::getTarget).sum());
                bo.setActual(temp.stream().mapToDouble(OtherExpenses::getActual).sum());
                bo.setRatio(new BigDecimal(bo.getTarget() / bo.getActual() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
                bo.setBalance(bo.getTarget() - bo.getActual());
                collectBOs.add(bo);
            }
        return collectBOs;
    }

    @Override
    public List<NameCollectBO> nameCollect(CollectTO to) throws SerException {
        List<OtherExpenses> list = this.findCollect(to).stream()
                .sorted(Comparator.comparing(OtherExpenses::getName)
                        .thenComparing(OtherExpenses::getYear)
                        .thenComparing(OtherExpenses::getMonth))
                .collect(Collectors.toList());
        List<NameCollectBO> collectBOs = new ArrayList<>(0);
        String name = "";
        Integer year = 0, month = 0;
        for (OtherExpenses entity : list)
            if (!name.equals(entity.getName()) || year != entity.getYear() || month != entity.getMonth()) {
                name = entity.getArea();
                year = entity.getYear();
                month = entity.getMonth();
                List<OtherExpenses> temp = list.stream()
                        .filter(o -> o.getName().equals(entity.getName())
                                && o.getYear() == entity.getYear()
                                && o.getMonth() == entity.getMonth())
                        .collect(Collectors.toList());
                NameCollectBO bo = new NameCollectBO();
                bo.setName(name);
                bo.setYear(year);
                bo.setMonth(month);
                bo.setTarget(temp.stream().mapToDouble(OtherExpenses::getTarget).sum());
                bo.setActual(temp.stream().mapToDouble(OtherExpenses::getActual).sum());
                bo.setRatio(new BigDecimal(bo.getTarget() / bo.getActual() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
                bo.setBalance(bo.getTarget() - bo.getActual());
                collectBOs.add(bo);
            }
        return collectBOs;
    }

    @Override
    public List<TypeCollectBO> typeCollect(CollectTO to) throws SerException {
        List<OtherExpenses> list = this.findCollect(to).stream()
                .sorted(Comparator.comparing(OtherExpenses::getType)
                        .thenComparing(OtherExpenses::getYear)
                        .thenComparing(OtherExpenses::getMonth))
                .collect(Collectors.toList());
        List<TypeCollectBO> collectBOs = new ArrayList<>(0);
        String type = "";
        Integer year = 0, month = 0;
        for (OtherExpenses entity : list)
            if (!type.equals(entity.getType()) || year != entity.getYear() || month != entity.getMonth()) {
                type = entity.getType();
                year = entity.getYear();
                month = entity.getMonth();
                List<OtherExpenses> temp = list.stream()
                        .filter(o -> o.getType().equals(entity.getType())
                                && o.getYear() == entity.getYear()
                                && o.getMonth() == entity.getMonth())
                        .collect(Collectors.toList());
                TypeCollectBO bo = new TypeCollectBO();
                bo.setType(type);
                bo.setYear(year);
                bo.setMonth(month);
                bo.setTarget(temp.stream().mapToDouble(OtherExpenses::getTarget).sum());
                bo.setActual(temp.stream().mapToDouble(OtherExpenses::getActual).sum());
                bo.setRatio(new BigDecimal(bo.getTarget() / bo.getActual() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
                bo.setBalance(bo.getTarget() - bo.getActual());
                collectBOs.add(bo);
            }
        return collectBOs;
    }

    @Override
    public List<ProjectCollectBO> projectCollect(CollectTO to) throws SerException {
        List<OtherExpenses> list = this.findCollect(to).stream()
                .sorted(Comparator.comparing(OtherExpenses::getProject)
                        .thenComparing(OtherExpenses::getYear)
                        .thenComparing(OtherExpenses::getMonth))
                .collect(Collectors.toList());
        List<ProjectCollectBO> collectBOs = new ArrayList<>(0);
        String project = "";
        Integer year = 0, month = 0;
        for (OtherExpenses entity : list)
            if (!project.equals(entity.getProject()) || year != entity.getYear() || month != entity.getMonth()) {
                project = entity.getProject();
                year = entity.getYear();
                month = entity.getMonth();
                List<OtherExpenses> temp = list.stream()
                        .filter(o -> o.getProject().equals(entity.getProject())
                                && o.getYear() == entity.getYear()
                                && o.getMonth() == entity.getMonth())
                        .collect(Collectors.toList());
                ProjectCollectBO bo = new ProjectCollectBO();
                bo.setProject(project);
                bo.setYear(year);
                bo.setMonth(month);
                bo.setTarget(temp.stream().mapToDouble(OtherExpenses::getTarget).sum());
                bo.setActual(temp.stream().mapToDouble(OtherExpenses::getActual).sum());
                bo.setRatio(new BigDecimal(bo.getTarget() / bo.getActual() * 100).setScale(2, RoundingMode.HALF_UP).doubleValue());
                bo.setBalance(bo.getTarget() - bo.getActual());
                collectBOs.add(bo);
            }
        return collectBOs;
    }
}