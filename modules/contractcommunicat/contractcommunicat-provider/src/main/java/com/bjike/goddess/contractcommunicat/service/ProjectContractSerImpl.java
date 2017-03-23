package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractBO;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.entity.ProjectContract;
import com.bjike.goddess.contractcommunicat.enums.CommunicateResult;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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
    public ProjectContractBO saveProjectContract(ProjectContractTO to) throws SerException {
        ProjectContract model = BeanTransform.copyProperties(to, ProjectContract.class ,true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to,ProjectContractBO.class);
    }

    @Override
    public ProjectContractBO editProjectContract(ProjectContractTO to) throws SerException {

        if(!StringUtils.isEmpty(to.getId())){
            ProjectContract model = super.findById(to.getId());
            BeanTransform.copyProperties(to,model,true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        }else{
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(to,ProjectContractBO.class);
    }

    @Override
    public List<ProjectContractBO> pageList(ProjectContractDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        if(dto.getCommunicateUser()!=null){
            dto.getConditions().add(Restrict.like("communicateUser",dto.getCommunicateUser()));
        }
        if(dto.getCommunicateObj()!=null){
            dto.getConditions().add(Restrict.like("communicateObj",dto.getCommunicateObj()));
        }
        if(dto.getCommunicateResult()!=null){
            dto.getConditions().add(Restrict.like("communicateResult",dto.getCommunicateResult()));
        }

        List<ProjectContractBO> pageList = BeanTransform.copyProperties(super.findByPage(dto),ProjectContractBO.class);
        return pageList;
    }

    @Override
    public List<ProjectContractBO> collect(ProjectContractDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        if(dto.getContractInProject()!=null){
            dto.getConditions().add(Restrict.like("contractInProject",dto.getContractInProject()));
        }
        if(dto.getStartTime()!=null){
            dto.getConditions().add(Restrict.gt("createTime",dto.getStartTime()));
        }
        if(dto.getEndTime()!=null){
            dto.getConditions().add(Restrict.lt("createTime",dto.getEndTime()));
        }
        return setCollectField(super.findByPage(dto));

    }

    @Override
    public void setCollectSend(QuartzCycleType cycle) throws SerException {
        // TODO: 17-3-20
    }

    //设置汇总字段
    public List<ProjectContractBO> setCollectField(List<ProjectContract> list) throws SerException{
        List<ProjectContractBO>  boList = BeanTransform.copyProperties(list,ProjectContractBO.class);

        Integer totalCooperate = 0;
        Integer totalTrail = 0;
        Integer totalAbandon = 0;

        if(boList != null && !boList.isEmpty()){
            for(ProjectContractBO bo : boList){
                if(bo.getProjectResult() == CommunicateResult.COOPERATE){
                    bo.setCooperate(bo.getProjectResult());
                    totalCooperate++;
                }else if(bo.getProjectResult() == CommunicateResult.TRAIL){
                    bo.setTrail(bo.getProjectResult());
                    totalTrail++;
                }else if(bo.getProjectResult() == CommunicateResult.ABANDON){
                    bo.setAbandon(bo.getProjectResult());
                    totalAbandon++;
                }
            }
            Double totalCostBudget = boList.stream().mapToDouble(p -> p.getCostBudget()).sum();

            ProjectContractBO total = new ProjectContractBO("合计" , null , null , null , null , totalCostBudget , totalCooperate , totalTrail , totalAbandon);
            boList.add(total);
        }


        return  boList;
    }
}