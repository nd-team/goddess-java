package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectContractExcel;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ExportExcelTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目承包洽谈业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.940 ]
 * @Description: [ 项目承包洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectContractSerCache")
@Service
public class ProjectContractSerImpl extends ServiceImpl<ProjectContract, ProjectContractDTO> implements ProjectContractSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException {
        ProjectContract model = BeanTransform.copyProperties(to, ProjectContract.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectContractBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            ProjectContract model = super.findById(to.getId());
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, ProjectContractBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        if (!StringUtils.isEmpty(dto.getCommunicateUser())) {
            dto.getConditions().add(Restrict.like("communicateUser", dto.getCommunicateUser()));
        }
        if (!StringUtils.isEmpty(dto.getCommunicateObj())) {
            dto.getConditions().add(Restrict.like("communicateObj", dto.getCommunicateObj()));
        }
        if (dto.getCommunicateResult() != null) {
            dto.getConditions().add(Restrict.like("communicateResult", dto.getCommunicateResult()));
        }

        List<ProjectContractBO> pageList = BeanTransform.copyProperties(super.findByPage(dto), ProjectContractBO.class);
        return pageList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectContractCollectBO> collect(CollectConditionTO to) throws SerException {
        ProjectContractDTO dto = new ProjectContractDTO();
        dto.getSorts().add("createTime=desc");
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.like("contractInProject", to.getContractInProject()));
        }
        if (!StringUtils.isEmpty(to.getStartTime())) {
            dto.getConditions().add(Restrict.gt("createTime", to.getStartTime()));
        }
        if (!StringUtils.isEmpty(to.getEndTime())) {
            dto.getConditions().add(Restrict.lt("createTime", to.getEndTime()));
        }
        return setCollectField(super.findByPage(dto));

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void setCollectSend(QuartzCycleType cycle) throws SerException {
        // TODO: 17-3-20
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<ProjectContractExcel> toList) throws SerException {
        List<ProjectContract> list = BeanTransform.copyProperties(toList, ProjectContract.class, true);
        super.save(list);
    }

    @Override
    public byte[] exportExcel(ExportExcelTO to) throws SerException {
        ProjectContractDTO dto = new ProjectContractDTO();
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.eq("contractInProject", to.getContractInProject()));
        }
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.gt("communicateDate", to.getStartDate()));
        }
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.lt("communicateDate", to.getEndDate()));
        }
        List<ProjectContract> list = super.findByCis(dto);
        List<ProjectContractExcel> toList = new ArrayList<ProjectContractExcel>();
        for (ProjectContract model : list) {
            ProjectContractExcel excel = new ProjectContractExcel();
            BeanUtils.copyProperties(model,excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    //设置汇总字段
    public List<ProjectContractCollectBO> setCollectField(List<ProjectContract> list) throws SerException {
        List<ProjectContractBO> boList = BeanTransform.copyProperties(list, ProjectContractBO.class);

        List<ProjectContractCollectBO> returnBoList = BeanTransform.copyProperties(list, ProjectContractCollectBO.class);
        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;

        if (returnBoList != null && !returnBoList.isEmpty()) {
            for (ProjectContractCollectBO bo : returnBoList) {
                if (bo.getProjectResult() == CommunicateResult.COOPERATE) {
                    bo.setCooperate("项目合作");
                    totalCooperate++;
                } else if (bo.getProjectResult() == CommunicateResult.TRAIL) {
                    bo.setTrail("项目跟进");
                    totalTrail++;
                } else if (bo.getProjectResult() == CommunicateResult.ABANDON) {
                    bo.setAbandon("项目丢弃");
                    totalAbandon++;
                }
            }
        }
        Double totalCostBudget = boList.stream().mapToDouble(p -> p.getCostBudget()).sum();
        ProjectContractCollectBO total = new ProjectContractCollectBO("合计", null, null, null, null, totalCostBudget, totalCooperate.toString(), totalTrail.toString(), totalAbandon.toString());
        returnBoList.add(total);
        return returnBoList;
    }
}