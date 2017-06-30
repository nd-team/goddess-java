package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.entity.Formula;
import com.bjike.goddess.reportmanagement.enums.Form;
import com.bjike.goddess.reportmanagement.to.FormulaTO;
import com.bjike.goddess.reportmanagement.utils.Utils;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 对应的公式业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class FormulaSerImpl extends ServiceImpl<Formula, FormulaDTO> implements FormulaSer {
    @Autowired
    private SubjectCollectAPI subjectCollectAPI;

    @Override
    public List<FormulaBO> findByFid(String foreignId, String startTime, String endTime, String projectGroup) throws SerException {
        String[] strings = new String[]{foreignId};
        List<Formula> list = null;
        for (String s : strings) {
            String sql = "select project,type from reportmanagement_formula" +
                    " where foreign_id='" + s + "' ORDER BY type ASC";
            String[] fileds = new String[]{"project", "type"};
            list = super.findBySql(sql, Formula.class, fileds);
        }
        Integer startMonth = 0;
        Integer endMonth = 0;
        LocalDate s = Utils.tranTime(startTime);
        startMonth = s.getMonthValue();
        LocalDate e = Utils.tranTime(endTime);
        endMonth = e.getMonthValue();
        List<FormulaBO> boList = new ArrayList<FormulaBO>();
        if ((list != null) && (!list.isEmpty())) {
            double beginSum = 0;
            double endSum = 0;
            double currentSum = 0;
            double yearSum = 0;
            Form form = null;
            for (Formula f : list) {
                SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
                subjectCollectDTO.getConditions().add(Restrict.eq("firstSubject", f.getProject()));
                subjectCollectDTO.getConditions().add(Restrict.eq("projectGroup", projectGroup));
                SubjectCollectDTO beginDTO = new SubjectCollectDTO();
                beginDTO.getConditions().add(Restrict.eq("firstSubject", f.getProject()));
                beginDTO.getConditions().add(Restrict.eq("projectGroup", projectGroup));
                if (LocalDate.now().getYear() == e.getYear()) {
                    double year = 0;
                    Integer[] years = new Integer[]{1, endMonth};
                    subjectCollectDTO.getConditions().add(Restrict.between("months", years));
                    SubjectCollectBO yearBO = subjectCollectAPI.getSum(subjectCollectDTO);
                    FormulaBO bo1 = BeanTransform.copyProperties(f, FormulaBO.class);
                    if (Form.DEBIT.equals(bo1.getForm())) {
                        year = yearBO.getIssueDebitAmount();
                    } else if (Form.CREDIT.equals(bo1.getForm())) {
                        year = yearBO.getIssueCreditAmount();
                    }
                    if (LocalDate.now().getYear() == s.getYear()) {
                        Integer[] months = new Integer[]{startMonth, endMonth};
                        subjectCollectDTO.getConditions().add(Restrict.between("months", months));
                        SubjectCollectBO subjectCollectBO = subjectCollectAPI.getSum(subjectCollectDTO);
                        FormulaBO bo = BeanTransform.copyProperties(f, FormulaBO.class);
                        bo.setYear(year);
                        form = bo.getForm();
                        if ("1".equals(f.getType())) {
                            bo.setOperation("+");
                        } else if ("2".equals(f.getType())) {
                            bo.setOperation("-");
                        }
                        if (Form.DEBIT.equals(bo.getForm())) {
                            bo.setEnd(subjectCollectBO.getEndDebitAmount());
                            bo.setCurrent(subjectCollectBO.getIssueDebitAmount());
                        } else if (Form.CREDIT.equals(bo.getForm())) {
                            bo.setEnd(subjectCollectBO.getEndCreditAmount());
                            bo.setCurrent(subjectCollectBO.getIssueCreditAmount());
                        }
                        if (startMonth != 1) {
                            beginDTO.getConditions().add(Restrict.eq("months", startMonth - 1));
                            SubjectCollectBO beginBO = subjectCollectAPI.getSum(beginDTO);
                            if (Form.DEBIT.equals(bo.getForm())) {
                                bo.setBegin(beginBO.getEndDebitAmount());
                            } else if (Form.CREDIT.equals(bo.getForm())) {
                                bo.setBegin(beginBO.getEndCreditAmount());
                            }
                            if ("1".equals(f.getType())) {     //1代表加
                                beginSum += bo.getBegin();
                                endSum += bo.getEnd();
                                currentSum += bo.getCurrent();
                                yearSum += bo.getYear();
                            } else if ("2".equals(f.getType())) {  //2代表减
                                beginSum = beginSum - bo.getBegin();
                                endSum = endSum - bo.getEnd();
                                currentSum = currentSum - bo.getCurrent();
                                yearSum -= bo.getYear();
                            }
                        } else {
                            beginDTO.getConditions().add(Restrict.eq("months", 1));
                            SubjectCollectBO beginBO = subjectCollectAPI.getSum(beginDTO);
                            if (Form.DEBIT.equals(bo.getForm())) {
                                bo.setBegin(beginBO.getBeginningDebitAmount());
                            } else if (Form.CREDIT.equals(bo.getForm())) {
                                bo.setBegin(beginBO.getBeginningCreditAmount());
                            }
                            if ("1".equals(f.getType())) {     //1代表加
                                beginSum += bo.getBegin();
                                endSum += bo.getEnd();
                                currentSum += bo.getCurrent();
                                yearSum += bo.getYear();
                            } else if ("2".equals(f.getType())) {  //2代表减
                                beginSum = beginSum - bo.getBegin();
                                endSum = endSum - bo.getEnd();
                                currentSum = currentSum - bo.getCurrent();
                                yearSum -= bo.getYear();
                            }
                        }
                        boList.add(bo);
                    } else {
                        Integer[] months = new Integer[]{1, endMonth};
                        subjectCollectDTO.getConditions().add(Restrict.between("months", months));
                        SubjectCollectBO subjectCollectBO = subjectCollectAPI.getSum(subjectCollectDTO);
                        FormulaBO bo = BeanTransform.copyProperties(f, FormulaBO.class);
                        form = bo.getForm();
                        bo.setYear(year);
                        if ("1".equals(f.getType())) {
                            bo.setOperation("+");
                        } else if ("2".equals(f.getType())) {
                            bo.setOperation("-");
                        }
                        if (Form.DEBIT.equals(bo.getForm())) {
                            bo.setEnd(subjectCollectBO.getEndDebitAmount());
                            bo.setCurrent(subjectCollectBO.getIssueDebitAmount());
                        } else if (Form.CREDIT.equals(bo.getForm())) {
                            bo.setEnd(subjectCollectBO.getEndCreditAmount());
                            bo.setCurrent(subjectCollectBO.getIssueCreditAmount());
                        }
                        beginDTO.getConditions().add(Restrict.eq("months", 1));
                        SubjectCollectBO beginBO = subjectCollectAPI.getSum(beginDTO);
                        if (Form.DEBIT.equals(bo.getForm())) {
                            bo.setBegin(beginBO.getBeginningDebitAmount());
                        } else if (Form.CREDIT.equals(bo.getForm())) {
                            bo.setBegin(beginBO.getBeginningCreditAmount());
                        }
                        if ("1".equals(f.getType())) {     //1代表加
                            currentSum += bo.getCurrent();
                            beginSum += bo.getBegin();
                            endSum += bo.getEnd();
                            yearSum += bo.getYear();
                        } else if ("2".equals(f.getType())) {  //2代表减
                            currentSum = currentSum - bo.getCurrent();
                            beginSum = beginSum - bo.getBegin();
                            endSum = endSum - bo.getEnd();
                            yearSum -= bo.getYear();
                        }
                        boList.add(bo);
                    }
                }
            }
            FormulaBO bo = new FormulaBO();
            bo.setProject("合计：");
            bo.setForm(form);
            bo.setBegin(beginSum);
            bo.setEnd(endSum);
            bo.setCurrent(currentSum);
            bo.setYear(yearSum);
            boList.add(bo);
            return boList;
        }
        return null;
    }

    @Override
    public List<FormulaBO> profitAnalyze(String foreignId, String time, String projectGroup) throws SerException {
        String[] strings = new String[]{foreignId};
        List<Formula> list = null;
        for (String s : strings) {
            String sql = "select project,type from reportmanagement_formula" +
                    " where foreign_id='" + s + "' ORDER BY type ASC";
            String[] fileds = new String[]{"project", "type"};
            list = super.findBySql(sql, Formula.class, fileds);
        }
        Integer month = 0;
        LocalDate t = Utils.tranTime(time);
        month = t.getMonthValue();
        List<FormulaBO> boList = new ArrayList<FormulaBO>();
        if ((list != null) && (!list.isEmpty())) {
            double currentSum = 0;
            FormulaBO addBO = new FormulaBO();
            addBO.setProject("加：");
            boList.add(addBO);
            boolean b = true;
            for (Formula f : list) {
                if ("2".equals(f.getType()) && b) {    //2代表减
                    FormulaBO bo = new FormulaBO();
                    bo.setProject("减：");
                    boList.add(bo);
                    b = false;
                }
                SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
                subjectCollectDTO.getConditions().add(Restrict.eq("firstSubject", f.getProject()));
                subjectCollectDTO.getConditions().add(Restrict.eq("projectGroup", projectGroup));
                if (LocalDate.now().getYear() == t.getYear()) {
                    subjectCollectDTO.getConditions().add(Restrict.eq("months", month));
                    SubjectCollectBO subjectCollectBO = subjectCollectAPI.getSum(subjectCollectDTO);
                    FormulaBO bo = BeanTransform.copyProperties(f, FormulaBO.class);
                    if (Form.DEBIT.equals(bo.getForm())) {
                        bo.setCurrent(subjectCollectBO.getIssueDebitAmount());
                    } else if (Form.CREDIT.equals(bo.getForm())) {
                        bo.setCurrent(subjectCollectBO.getIssueCreditAmount());
                    }
                    if ("1".equals(f.getType())) {     //1代表加
                        currentSum += bo.getCurrent();
                    } else if ("2".equals(f.getType())) {  //2代表减
                        currentSum = currentSum - bo.getCurrent();
                    }
                    boList.add(bo);
                }
            }
            FormulaBO bo = new FormulaBO();
            bo.setProject("合计：");
            bo.setCurrent(currentSum);
            boList.add(bo);
            return boList;
        }
        return null;
    }

    @Override
    public FormulaBO add(FormulaTO to) throws SerException {
        Formula entity = BeanTransform.copyProperties(to, Formula.class, true);
        entity.setType("1");
        super.save(entity);
        return BeanTransform.copyProperties(entity, FormulaBO.class);
    }

    @Override
    public FormulaBO remove(FormulaTO to) throws SerException {
        Formula entity = BeanTransform.copyProperties(to, Formula.class, true);
        entity.setType("2");
        super.save(entity);
        return BeanTransform.copyProperties(entity, FormulaBO.class);
    }

    @Override
    public void delete(String id) throws SerException {
        Formula entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }
}