package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.SendEmailAPI;
import com.bjike.goddess.materialsummary.bo.PersonalBuySummBO;
import com.bjike.goddess.materialsummary.bo.SendEmailBO;
import com.bjike.goddess.materialsummary.bo.TypeBuySummBO;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.to.PersonalBuySummVO;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;
import com.bjike.goddess.materialsummary.vo.SendEmailVO;
import com.bjike.goddess.materialsummary.vo.TypeBuySummVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 物质购买发送邮件
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("sendemail")
public class SendEmailAction {
    @Autowired
    private SendEmailAPI sendEmailAPI;

    /**
     * 列表总条数
     *
     * @param sendEmailDTO 发送邮件dto
     * @des 获取邮件信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SendEmailDTO sendEmailDTO) throws ActException {
        try {
            Long count = sendEmailAPI.counts(sendEmailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个邮件
     *
     * @param id 邮件信息id
     * @return class SendEmailVO
     * @des 根据id获取邮件信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SendEmailVO sendEmailVO = BeanTransform.copyProperties(
                    sendEmailAPI.getOne(id), SendEmailVO.class);
            return ActResult.initialize(sendEmailVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 邮件列表
     *
     * @param sendEmailDTO 邮件信息dto
     * @return class SendEmailVO
     * @des 获取所有邮件信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCollectEmail(SendEmailDTO sendEmailDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SendEmailVO> sendEmailVOS = BeanTransform.copyProperties(
                    sendEmailAPI.listCollectEmail(sendEmailDTO), SendEmailVO.class, request);
            return ActResult.initialize(sendEmailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加邮件
     *
     * @param sendEmailTO 邮件基本信息数据to
     * @return class SendEmailVO
     * @des 添加商务邮件汇总
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCollectEmail(@Validated(ADD.class) SendEmailTO sendEmailTO, BindingResult bindingResult) throws ActException {
        try {
            SendEmailBO sendEmailBO = sendEmailAPI.addCollectEmail(sendEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(sendEmailBO, SendEmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑邮件
     *
     * @param sendEmailTO 邮件基本信息数据bo
     * @return class SendEmailVO
     * @des 添加邮件
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editCollectEmail(@Validated(EDIT.class) SendEmailTO sendEmailTO, BindingResult bindingResult) throws ActException {
        try {
            SendEmailBO sendEmailBO = sendEmailAPI.editCollectEmail(sendEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(sendEmailBO, SendEmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除邮件信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCollectEmail(@PathVariable String id) throws ActException {
        try {
            sendEmailAPI.deleteCollectEmail(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结邮件记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            sendEmailAPI.congealCollectEmail(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻邮件记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            sendEmailAPI.thawCollectEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据汇总模块获取汇总类型
     *
     * @version v1
     */
    @GetMapping("v1/typeByMod")
    public Result allType(@RequestParam ModuleType moduleType) throws ActException {
        try {
            List<SummaryType> types = new ArrayList<>();
            types = sendEmailAPI.summaryType(moduleType);
            return ActResult.initialize(types);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 针对各物资分类购买情况日汇总
     *
     * @param summTime 时间
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/type/day")
    public Result buySummByTypeDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.typeBuySummDay(summTime);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 针对各物资分类购买情况周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/type/week")
    public Result buySummByTypeWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.typeBuySummWeek(year,month,week);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对各物资分类购买情况月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/type/month")
    public Result buySummByTypeMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.typeBuySummMonth(year,month);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对各物资分类购买情况年汇总
     *
     * @param year 年份
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/type/year")
    public Result buySummByTypeYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.typeBuySummYear(year);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 针对各部门地区物资购买情况日汇总
     *
     * @param summTime 时间
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/dep/day")
    public Result buySummByDepDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.areaBuySummDay(summTime);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对各部门地区物资购买情况周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/dep/week")
    public Result buySummByDepWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.areaBuySummWeek(year,month,week);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对各部门地区物资购买情况月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/dep/month")
    public Result buySummByDepMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.areaBuySummMonth(year,month);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对各部门地区物资购买情况年汇总
     *
     * @param year 年份
     * @return class TypeBuySummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/dep/year")
    public Result buySummByDepYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<TypeBuySummBO> boList = sendEmailAPI.areaBuySummYear(year);
            List<TypeBuySummVO> voList = BeanTransform.copyProperties(boList, TypeBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人物资购买情况日汇总
     *
     * @param summTime 时间
     * @return class PersonalBuySummBO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/person/day")
    public Result buySummByPersonDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<PersonalBuySummBO> boList = sendEmailAPI.personBuySummDay(summTime);
            List<PersonalBuySummVO> voList = BeanTransform.copyProperties(boList, PersonalBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 个人物资购买情况周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class PersonalBuySummBO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/person/week")
    public Result buySummByPersonWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<PersonalBuySummBO> boList = sendEmailAPI.personBuySummWeek(year,month,week);
            List<PersonalBuySummVO> voList = BeanTransform.copyProperties(boList, PersonalBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 个人物资购买情况月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class PersonalBuySummBO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/person/month")
    public Result buySummByPersonMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<PersonalBuySummBO> boList = sendEmailAPI.personBuySummMonth(year,month);
            List<PersonalBuySummVO> voList = BeanTransform.copyProperties(boList, PersonalBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 个人物资购买情况年汇总
     *
     * @param year 年份
     * @return class PersonalBuySummBO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/buySummary/person/year")
    public Result buySummByPersonYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<PersonalBuySummBO> boList = sendEmailAPI.personBuySummYear(year);
            List<PersonalBuySummVO> voList = BeanTransform.copyProperties(boList, PersonalBuySummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}