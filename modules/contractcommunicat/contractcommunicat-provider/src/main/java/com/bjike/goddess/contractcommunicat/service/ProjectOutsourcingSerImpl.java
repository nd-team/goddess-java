package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectOutsourcing;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectOutsourcingExcel;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ExportExcelTO;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目外包洽谈业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.833 ]
 * @Description: [ 项目外包洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectOutsourcingSerCache")
@Service
public class ProjectOutsourcingSerImpl extends ServiceImpl<ProjectOutsourcing, ProjectOutsourcingDTO> implements ProjectOutsourcingSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectOutsourcingBO saveProjectOutsourcing(ProjectOutsourcingTO to) throws SerException {
        getCusPermission();

        isExist(to,null);
        ProjectOutsourcing model = BeanTransform.copyProperties(to, ProjectOutsourcing.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, ProjectOutsourcingBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProjectOutsourcingBO editProjectOutsourcing(ProjectOutsourcingTO to) throws SerException {

        getCusPermission();

        if (!StringUtils.isEmpty(to.getId())) {
            ProjectOutsourcing model = super.findById(to.getId());
            if (model != null) {
                isExist(to, null);
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("编辑对象不能为空");
            }

        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to, ProjectOutsourcingBO.class);
    }

    //校验字段是否存在
    public void isExist(ProjectOutsourcingTO to, Integer row) throws SerException {
        ProjectOutsourcingDTO dto = null;
        if (!StringUtils.isEmpty(to.getContractExtProject())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractExtProject", to.getContractExtProject()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "合同外部项目名称已经存在!";
                if (row == null) {
                    throw new SerException(msg);
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }

            }
        }
        if (!StringUtils.isEmpty(to.getContractExtCode())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractExtCode", to.getContractExtCode()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "合同外部编号已经存在!";
                if (row == null) {
                    throw new SerException(msg);
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractInProject", to.getContractInProject()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "内部项目名称已经存在!";
                if (row == null) {
                    throw new SerException(msg);
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getContractInCode())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("contractInCode", to.getContractInCode()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "内部项目编号已经存在!";
                if (row == null) {
                    throw new SerException(msg);
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getOutsourcingProject())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("outsourcingProject", to.getOutsourcingProject()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "外包项目名称已经存在!";
                if (row == null) {
                    throw new SerException(msg);
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }
            }
        }
        if (!StringUtils.isEmpty(to.getOutsourcingCode())) {
            dto = new ProjectOutsourcingDTO();
            dto.getConditions().add(Restrict.eq("outsourcingCode", to.getOutsourcingCode()));
            List<ProjectOutsourcing> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                String msg = "外包项目编号已经存在!";
                if (row == null) {
                    throw new SerException(msg);
                } else {
                    throw new SerException("第" + row + "行的" + msg);
                }

            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectOutsourcingBO> pageList(ProjectOutsourcingDTO dto) throws SerException {

        getCusPermission();

        dto.getSorts().add("createTime=desc");
        if (dto.getCommunicateUser() != null) {
            dto.getConditions().add(Restrict.like("communicateUser", dto.getCommunicateUser()));
        }
        if (dto.getCommunicateObj() != null) {
            dto.getConditions().add(Restrict.like("communicateObj", dto.getCommunicateObj()));
        }
        if (dto.getCommunicateResult() != null) {
            dto.getConditions().add(Restrict.like("communicateResult", dto.getCommunicateResult()));
        }

        List<ProjectOutsourcingBO> pageList = BeanTransform.copyProperties(super.findByPage(dto), ProjectOutsourcingBO.class);
        return pageList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectOutsourcingCollectBO> collect(CollectConditionTO to) throws SerException {

        getCusPermission();

        ProjectOutsourcingDTO dto = new ProjectOutsourcingDTO();
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
    public void leadExcel(List<ProjectOutsourcingTO> toList) throws SerException {

        getCusPermission();

        for (int i = 1; i <= toList.size(); i++) {
            isExist(toList.get(i - 1), i);
        }
        List<ProjectOutsourcing> list = BeanTransform.copyProperties(toList, ProjectOutsourcing.class, true);
        super.save(list);
    }

    @Override
    public byte[] exportExcel(ExportExcelTO to) throws SerException {

        getCusPermission();

        ProjectOutsourcingDTO dto = new ProjectOutsourcingDTO();
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.eq("contractInProject", to.getContractInProject()));
        }
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.gt("communicateDate", to.getStartDate()));
        }
        if (!StringUtils.isEmpty(to.getContractInProject())) {
            dto.getConditions().add(Restrict.lt("communicateDate", to.getEndDate()));
        }
        List<ProjectOutsourcing> list = super.findByCis(dto);
        List<ProjectOutsourcingExcel> toList = new ArrayList<ProjectOutsourcingExcel>();
        for (ProjectOutsourcing model : list) {
            ProjectOutsourcingExcel excel = new ProjectOutsourcingExcel();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    //设置汇总字段
    public List<ProjectOutsourcingCollectBO> setCollectField(List<ProjectOutsourcing> list) throws SerException {
        List<ProjectOutsourcingBO> boList = BeanTransform.copyProperties(list, ProjectOutsourcingBO.class);

        List<ProjectOutsourcingCollectBO> returnBoList = BeanTransform.copyProperties(list, ProjectOutsourcingCollectBO.class);
        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;

        if (returnBoList != null && !returnBoList.isEmpty()) {
            for (ProjectOutsourcingCollectBO bo : returnBoList) {
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
        ProjectOutsourcingCollectBO total = new ProjectOutsourcingCollectBO("合计", null, null, null, null, null,
                totalCostBudget, totalCooperate.toString(), totalTrail.toString(), totalAbandon.toString());
        returnBoList.add(total);

        return returnBoList;
    }

    public void getCusPermission() throws SerException {

        Boolean permission = cusPermissionSer.getCusPermission("1");

        if (!permission) {
            throw new SerException("该模块只有商务部可操作，您的帐号尚无权限");
        }
    }
}