package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ProblemCodeRuleBO;
import com.bjike.goddess.feedback.dto.ProblemCodeRuleDTO;
import com.bjike.goddess.feedback.entity.ProblemCodeRule;
import com.bjike.goddess.feedback.to.ProblemCodeRuleTO;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 问题编码规则业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:17 ]
 * @Description: [ 问题编码规则业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ProblemCodeRuleSerImpl extends ServiceImpl<ProblemCodeRule, ProblemCodeRuleDTO> implements ProblemCodeRuleSer {

    @Autowired
    private PositionDetailAPI positionDetailAPI;

    @Override
    public Long count(ProblemCodeRuleDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ProblemCodeRuleBO getOne(String id) throws SerException {
        ProblemCodeRule problemCodeRule = super.findById(id);
        return BeanTransform.copyProperties(problemCodeRule, ProblemCodeRuleBO.class);
    }

    @Override
    public List<ProblemCodeRuleBO> list(ProblemCodeRuleDTO dto) throws SerException {
        List<ProblemCodeRule> problemCodeRules = super.findAll();
        if (problemCodeRules.isEmpty()) {
            List<PositionDetailBO> positionDetailBOS = positionDetailAPI.findStatus();
//            for (ProblemCodeRule problemCodeRule : problemCodeRules) {
                for (PositionDetailBO positionDetailBO : positionDetailBOS) {
                    ProblemCodeRule problemCodeRule=new ProblemCodeRule();
                    problemCodeRule.setArea(positionDetailBO.getArea());
                    problemCodeRule.setProjectGroup(positionDetailBO.getDepartmentName());
                    problemCodeRule.setModule(positionDetailBO.getModuleName());
                    String depart = positionDetailBO.getDepartmentName();
                    String module = positionDetailBO.getModuleName();
                    String problemRule = null;
                    String acceptRule = null;
                    switch (depart) {
                        case "湛江项目组":
                            problemRule = "ZJ-zte";
                            acceptRule = "ZJ-zte";
                            break;
                        case "江门项目组":
                            problemRule = "JM-it";
                            acceptRule = "JM-it";
                            break;
                        case "佛山有线督导项目组":
                            problemRule = "FS-yx";
                            acceptRule = "FS-yx";
                            break;
                        case "佛山无线督导项目组":
                            problemRule = "FS-wu";
                            acceptRule = "FS-wu";
                            break;
                        case "广州研发项目组":
                            problemRule = "GZ-yf";
                            acceptRule = "GZ-yf";
                            break;

                    }
                    switch (module) {
                        case "预算模块":
                            problemRule = "YS-zn";
                            acceptRule = "YS-zn";
                            break;
                        case "资金模块":
                            problemRule = "ZJ-zn";
                            acceptRule = "ZJ-zn";
                            break;
                        case "账务模块":
                            problemRule = "ZW-zn";
                            acceptRule = "ZW-zn";
                            break;
                        case "客户管理模块":
                            problemRule = "KH-zn";
                            acceptRule = "FKUS-zn";
                            break;
                        case "业务管理模块":
                            problemRule = "YW-zn";
                            acceptRule = "YW-zn";
                            break;
                        case "进度管理模块":
                            problemRule = "JD-zn";
                            acceptRule = "JD-zn";
                            break;
                        case "规划模块":
                            problemRule = "GH-zn";
                            acceptRule = "GH-zn";
                            break;
                        case "素养模块":
                            problemRule = "SY-zn";
                            acceptRule = "SY-zn";
                            break;
                        case "福利模块":
                            problemRule = "FL-zn";
                            acceptRule = "FL-zn";
                            break;
                    }
                    if (problemRule == null && acceptRule == null) {
                        problemRule = "不符合规则";
                        acceptRule = "不符合规则";
                    }
                    problemCodeRule.setProblemCodeRule(problemRule);
                    problemCodeRule.setAcceptCodeRule(acceptRule);
                    problemCodeRule.setCodeFixedRule("Q" + LocalDateTime.now());
                    super.save(problemCodeRule);

//                }
            }
        }
        List<ProblemCodeRule> list = super.findByCis(dto);
        List<ProblemCodeRuleBO> problemCodeRuleBOS = BeanTransform.copyProperties(list, ProblemCodeRuleBO.class);
        return problemCodeRuleBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemCodeRuleBO edit(ProblemCodeRuleTO to) throws SerException {
        ProblemCodeRule entity = super.findById(to.getId());
        BeanTransform.copyProperties(to, entity, true);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ProblemCodeRuleBO.class);
    }
}