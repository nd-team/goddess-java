package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.ProblemBO;
import com.bjike.goddess.task.dto.ProblemDTO;
import com.bjike.goddess.task.entity.Problem;
import com.bjike.goddess.task.enums.ProblemStatus;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemEditTO;
import com.bjike.goddess.task.to.ProblemTO;
import com.bjike.goddess.task.util.SeqUtil;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ProblemSerImpl extends ServiceImpl<Problem, ProblemDTO> implements ProblemSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProblemTypeSer problemTypeSer;

    /**
     * 可用原生sql查询减少查询次数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<ProblemBO> list(ProblemDTO dto) throws SerException {
        if (null != dto.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        if (null != dto.getType()) {
            dto.getConditions().add(Restrict.eq("type", dto.getType()));
        }
        List<Problem> problems = super.findByCis(dto, true);
        List<ProblemBO> bos = BeanTransform.copyProperties(problems, ProblemBO.class, "type");
        for (int i = 0; i < bos.size(); i++) {
            bos.get(i).setType(problems.get(i).getType().getName());
        }
        return bos;
    }

    @Override
    public Long count(ProblemDTO dto) throws SerException {
        if (null != dto.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        if (null != dto.getType()) {
            dto.getConditions().add(Restrict.eq("type", dto.getType()));
        }
        return super.count(dto);
    }

    @Override
    public void edit(ProblemEditTO to) throws SerException {
        Problem problem = super.findById(to.getId());
        if (null != problem) {
            BeanTransform.copyProperties(to, problem, "id");
            problem.setType(problemTypeSer.findById(to.getTypeId()));
            super.update(problem);
        } else {
            throw new SerException("问题不存在");
        }

    }

    @Override
    public void remove(String id) throws SerException {
        //todo 删除权限
        super.remove(id);
    }

    @Override
    public void add(ProblemTO to) throws SerException {
        String recorder = userAPI.currentUser().getId(); //录入人
        String number = SeqUtil.genProblemNum(getMaxNum());
        Problem problem = BeanTransform.copyProperties(to, Problem.class, true);
        if (null == userAPI.findNameById(problem.getClaimer())) {
            throw new SerException("无效问题提出人");
        }
        problem.setRecorder(recorder);
        problem.setNumber(number);
        problem.setStatus(ProblemStatus.WAIT);
        problem.setType(problemTypeSer.findById(to.getTypeId()));
        if (null == to.getReport()) {
            problem.setReport(false);
        }
        super.save(problem);
    }

    @Override
    public void accept(AcceptTO to) throws SerException {
        String accepter = userAPI.currentUser().getId(); //审核人
        String acceptNumber = SeqUtil.genAcceptNum(getMaxAcceptNum());
        Problem problem = super.findById(to.getProblemId());
        if (null != problem) {
            BeanTransform.copyProperties(to, problem, true, "id");
            problem.setAccepter(accepter);
            problem.setAcceptNumber(acceptNumber);
            super.update(problem);
        } else {
            throw new SerException("该问题不存在");
        }
    }

    private String getMaxNum() throws SerException {
        String sql = "SELECT number FROM (SELECT MAX(number) AS number FROM task_problem) a WHERE number IS NOT NULL";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            return String.valueOf(objects.get(0));
        }
        return null;
    }

    private String getMaxAcceptNum() throws SerException {
        String sql = "SELECT acceptNumber FROM (SELECT MAX(acceptNumber) AS acceptNumber FROM task_problem) a WHERE acceptNumber  IS NOT NULL";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            return String.valueOf(objects.get(0));
        }
        return null;
    }
}
