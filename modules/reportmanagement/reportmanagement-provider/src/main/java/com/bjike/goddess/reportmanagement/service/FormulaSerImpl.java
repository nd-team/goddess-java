package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.dto.FormulaDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.entity.Formula;
import com.bjike.goddess.reportmanagement.enums.Form;
import com.bjike.goddess.reportmanagement.enums.GuideAddrStatus;
import com.bjike.goddess.reportmanagement.to.FormulaTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.utils.Utils;
import com.bjike.goddess.subjectcollect.api.SubjectCollectAPI;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<FormulaBO> findByFid(String foreignId, FormulaDTO dto) throws SerException {
        String[] strings = new String[]{foreignId};
        List<Formula> list = null;
        for (String s : strings) {
            String sql = "select id,project,type1,form from reportmanagement_formula" +
                    " where foreign_id='" + s + "' ORDER BY type1 ASC";
            String[] fileds = new String[]{"id","project", "type1", "form"};
            list = super.findBySql(sql, Formula.class, fileds);
        }
        Integer startMonth = 0;
        Integer endMonth = 0;
        LocalDate s = Utils.tranTime(dto.getStartTime());
        startMonth = s.getMonthValue();
        LocalDate e = Utils.tranTime(dto.getEndTime());
        endMonth = e.getMonthValue();
        String[] projectNames = dto.getProjectNames();
        String[] departs = dto.getDeparts();
        String[] areas = dto.getAreas();
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
                if (projectNames != null) {
                    subjectCollectDTO.getConditions().add(Restrict.in("projectName", projectNames));
                }if (departs != null) {
                    subjectCollectDTO.getConditions().add(Restrict.in("projectGroup", departs));
                }if (areas != null) {
                    subjectCollectDTO.getConditions().add(Restrict.in("area", areas));
                }
                SubjectCollectDTO beginDTO = new SubjectCollectDTO();
                beginDTO.getConditions().add(Restrict.eq("firstSubject", f.getProject()));
                if (projectNames != null) {
                    beginDTO.getConditions().add(Restrict.in("projectName", projectNames));
                }if (departs != null) {
                    beginDTO.getConditions().add(Restrict.in("projectGroup", departs));
                }if (areas != null) {
                    beginDTO.getConditions().add(Restrict.in("area", areas));
                }
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
                        if ("1".equals(f.getType1())) {
                            bo.setOperation("+");
                        } else if ("2".equals(f.getType1())) {
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
                            if ("1".equals(f.getType1())) {     //1代表加
                                beginSum += bo.getBegin();
                                endSum += bo.getEnd();
                                currentSum += bo.getCurrent();
                                yearSum += bo.getYear();
                            } else if ("2".equals(f.getType1())) {  //2代表减
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
                            if ("1".equals(f.getType1())) {     //1代表加
                                beginSum += bo.getBegin();
                                endSum += bo.getEnd();
                                currentSum += bo.getCurrent();
                                yearSum += bo.getYear();
                            } else if ("2".equals(f.getType1())) {  //2代表减
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
                        if ("1".equals(f.getType1())) {
                            bo.setOperation("+");
                        } else if ("2".equals(f.getType1())) {
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
                        if ("1".equals(f.getType1())) {     //1代表加
                            currentSum += bo.getCurrent();
                            beginSum += bo.getBegin();
                            endSum += bo.getEnd();
                            yearSum += bo.getYear();
                        } else if ("2".equals(f.getType1())) {  //2代表减
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
//            bo.setForm(form);
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
    public List<FormulaBO> profitAnalyze(String foreignId, String time, ProfitDTO dto) throws SerException {
        String[] strings = new String[]{foreignId};
        List<Formula> list = null;
        for (String s : strings) {
            String sql = "select project,type1,form from reportmanagement_formula" +
                    " where foreign_id='" + s + "' ORDER BY type1 ASC";
            String[] fileds = new String[]{"project", "type1", "form"};
            list = super.findBySql(sql, Formula.class, fileds);
        }
        Integer month = 0;
        LocalDate t = Utils.tranTime(time);
        month = t.getMonthValue();
        List<FormulaBO> boList = new ArrayList<FormulaBO>();
        String[] projectNames=dto.getProjectNames();
        String[] departs=dto.getDeparts();
        String[] areas=dto.getAreas();
        if ((list != null) && (!list.isEmpty())) {
            double currentSum = 0;
            FormulaBO addBO = new FormulaBO();
            addBO.setProject("加：");
            boList.add(addBO);
            boolean b = true;
            for (Formula f : list) {
                if ("2".equals(f.getType1()) && b) {    //2代表减
                    FormulaBO bo = new FormulaBO();
                    bo.setProject("减：");
                    boList.add(bo);
                    b = false;
                }
                SubjectCollectDTO subjectCollectDTO = new SubjectCollectDTO();
                subjectCollectDTO.getConditions().add(Restrict.eq("firstSubject", f.getProject()));
                if (projectNames != null) {
                    subjectCollectDTO.getConditions().add(Restrict.in("projectName", projectNames));
                }if (departs != null) {
                    subjectCollectDTO.getConditions().add(Restrict.in("projectGroup", departs));
                }if (areas != null) {
                    subjectCollectDTO.getConditions().add(Restrict.in("area", areas));
                }
                if (LocalDate.now().getYear() == t.getYear()) {
                    subjectCollectDTO.getConditions().add(Restrict.eq("months", month));
                    SubjectCollectBO subjectCollectBO = subjectCollectAPI.getSum(subjectCollectDTO);
                    FormulaBO bo = BeanTransform.copyProperties(f, FormulaBO.class);
                    if (Form.DEBIT.equals(bo.getForm())) {
                        bo.setCurrent(subjectCollectBO.getIssueDebitAmount());
                    } else if (Form.CREDIT.equals(bo.getForm())) {
                        bo.setCurrent(subjectCollectBO.getIssueCreditAmount());
                    }
                    if ("1".equals(f.getType1())) {     //1代表加
                        currentSum += bo.getCurrent();
                    } else if ("2".equals(f.getType1())) {  //2代表减
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
    @Transactional(rollbackFor = SerException.class)
    public FormulaBO add(FormulaTO to) throws SerException {
        checkAddIdentity();
        Formula entity = BeanTransform.copyProperties(to, Formula.class, true);
        entity.setType1("1");
        super.save(entity);
        return BeanTransform.copyProperties(entity, FormulaBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FormulaBO remove(FormulaTO to) throws SerException {
        checkAddIdentity();
        Formula entity = BeanTransform.copyProperties(to, Formula.class, true);
        entity.setType1("2");
        super.save(entity);
        return BeanTransform.copyProperties(entity, FormulaBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        checkAddIdentity();
        Formula entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FormulaBO save(FormulaTO to) throws SerException {
        checkAddIdentity();
        Formula entity = BeanTransform.copyProperties(to, Formula.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, FormulaBO.class);
    }
}