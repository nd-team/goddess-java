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
import com.bjike.goddess.materialsummary.bo.*;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.excel.SonPermissionObject;
import com.bjike.goddess.materialsummary.to.GuidePermissionTO;
import com.bjike.goddess.materialsummary.to.PersonalBuySummVO;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;
import com.bjike.goddess.materialsummary.vo.*;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
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

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = sendEmailAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = sendEmailAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
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
     * @return class PersonalBuySummVO
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
     * @return class PersonalBuySummVO
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
     * @return class PersonalBuySummVO
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
     * @return class PersonalBuySummVO
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

    /**
     * 整体针对入库来源的物资日汇总
     *
     * @param summTime 时间
     * @return class ResouceStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/source/day")
    public Result stockSummBySourceDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<ResouceStockSummBO> boList = sendEmailAPI.sourStockSummDay(summTime);
            List<ResouceStockSummVO> voList = BeanTransform.copyProperties(boList, ResouceStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 整体针对入库来源的物资周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class ResouceStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/source/week")
    public Result stockSummBySourceWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<ResouceStockSummBO> boList = sendEmailAPI.sourStockSummWeek(year,month,week);
            List<ResouceStockSummVO> voList = BeanTransform.copyProperties(boList, ResouceStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 整体针对入库来源的物资月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class ResouceStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/source/month")
    public Result stockSummBySourceMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<ResouceStockSummBO> boList = sendEmailAPI.sourStockSummMonth(year,month);
            List<ResouceStockSummVO> voList = BeanTransform.copyProperties(boList, ResouceStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 整体针对入库来源的物资年汇总
     *
     * @param year 年份
     * @return class ResouceStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/source/year")
    public Result stockSummBySourceYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<ResouceStockSummBO> boList = sendEmailAPI.sourStockSummYear(year);
            List<ResouceStockSummVO> voList = BeanTransform.copyProperties(boList, ResouceStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 各地区入库情况日汇总
     *
     * @param summTime 时间
     * @return class AreaStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/area/day")
    public Result stockSummByAreaDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<AreaStockSummBO> boList = sendEmailAPI.areaStockSummDay(summTime);
            List<AreaStockSummVO> voList = BeanTransform.copyProperties(boList, AreaStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 各地区入库情况周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class AreaStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/area/week")
    public Result stockSummByAreaWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<AreaStockSummBO> boList = sendEmailAPI.areaStockSummWeek(year,month,week);
            List<AreaStockSummVO> voList = BeanTransform.copyProperties(boList, AreaStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 各地区入库情况月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class AreaStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/area/month")
    public Result stockSummByAreaMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<AreaStockSummBO> boList = sendEmailAPI.areaStockSummMonth(year,month);
            List<AreaStockSummVO> voList = BeanTransform.copyProperties(boList, AreaStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 各地区入库情况年汇总
     *
     * @param year 年份
     * @return class AreaStockSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/stockSummary/area/year")
    public Result stockSummByAreaYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<AreaStockSummBO> boList = sendEmailAPI.areaStockSummYear(year);
            List<AreaStockSummVO> voList = BeanTransform.copyProperties(boList, AreaStockSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 针对维修状态分类情况日汇总
     *
     * @param summTime 时间
     * @return class StatusDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/status/day")
    public Result deviceSummByStatusDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<StatusDeviceSummBO> boList = sendEmailAPI.statusDeviceSummDay(summTime);
            List<StatusDeviceSummVO> voList = BeanTransform.copyProperties(boList, StatusDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对维修状态分类情况周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class StatusDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/status/week")
    public Result deviceSummByAreaWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<StatusDeviceSummBO> boList = sendEmailAPI.statusDeviceSummWeek(year,month,week);
            List<StatusDeviceSummVO> voList = BeanTransform.copyProperties(boList, StatusDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对维修状态分类情况月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class StatusDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/status/month")
    public Result deviceSummByAreaMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<StatusDeviceSummBO> boList = sendEmailAPI.statusDeviceSummMonth(year,month);
            List<StatusDeviceSummVO> voList = BeanTransform.copyProperties(boList, StatusDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对维修状态分类情况年汇总
     *
     * @param year 年份
     * @return class StatusDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/status/year")
    public Result deviceSummByAreaYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<StatusDeviceSummBO> boList = sendEmailAPI.statusDeviceSummYear(year);
            List<StatusDeviceSummVO> voList = BeanTransform.copyProperties(boList, StatusDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 针对保修状态分类情况日汇总
     *
     * @param summTime 时间
     * @return class WarrantyDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/warranty/day")
    public Result deviceSummByWarrDay(String summTime, HttpServletRequest request) throws ActException {
        try {
            List<WarrantyDeviceSummBO> boList = sendEmailAPI.warranDeviceSummDay(summTime);
            List<WarrantyDeviceSummVO> voList = BeanTransform.copyProperties(boList, WarrantyDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对保修状态分类情况周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期数
     * @return class WarrantyDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/warranty/week")
    public Result deviceSummByWarrWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<WarrantyDeviceSummBO> boList = sendEmailAPI.warranDeviceSummWeek(year,month,week);
            List<WarrantyDeviceSummVO> voList = BeanTransform.copyProperties(boList, WarrantyDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对保修状态分类情况月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class WarrantyDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/warranty/month")
    public Result deviceSummByWarrMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<WarrantyDeviceSummBO> boList = sendEmailAPI.warranDeviceSummMonth(year,month);
            List<WarrantyDeviceSummVO> voList = BeanTransform.copyProperties(boList, WarrantyDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 针对保修状态分类情况年汇总
     *
     * @param year 年份
     * @return class WarrantyDeviceSummVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deviceSummary/warranty/year")
    public Result deviceSummByWarrYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<WarrantyDeviceSummBO> boList = sendEmailAPI.warranDeviceSummYear(year);
            List<WarrantyDeviceSummVO> voList = BeanTransform.copyProperties(boList, WarrantyDeviceSummVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 商务邮件汇总汇总派工合同
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail(   ) throws ActException {
        try {
            sendEmailAPI.checkSendEmail( );
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}