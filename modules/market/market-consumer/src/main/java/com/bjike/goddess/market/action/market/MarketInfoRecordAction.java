package com.bjike.goddess.market.action.market;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CustomerNameNumBO;
import com.bjike.goddess.market.api.MarketInfoRecordAPI;
import com.bjike.goddess.market.bo.MarketInfoRecordBO;
import com.bjike.goddess.market.bo.OptionBO;
import com.bjike.goddess.market.bo.SummationAreaBO;
import com.bjike.goddess.market.bo.SummationBO;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoRecordTO;
import com.bjike.goddess.market.vo.*;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 市场信息记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 11:50 ]
 * @Description: [ 市场信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketinforecord")
public class MarketInfoRecordAction {
    @Autowired
    private MarketInfoRecordAPI marketInfoRecordAPI;
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
    public Result i() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = marketInfoRecordAPI.sonPermission();
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

            Boolean isHasPermission = marketInfoRecordAPI.guidePermission(guidePermissionTO);
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
     * 市场信息记录列表总条数
     *
     * @param marketInfoRecordDTO 市场信息记录dto
     * @des 获取所有市场信息记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MarketInfoRecordDTO marketInfoRecordDTO) throws ActException {
        try {
            Long count = marketInfoRecordAPI.countRecord(marketInfoRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个市场信息记录
     *
     * @param id
     * @return class MarketInfoVO
     * @des 获取一个市场信息记录
     * @version v1
     */
    @GetMapping("v1/market/{id}")
    public Result market(@PathVariable String id) throws ActException {
        try {
            MarketInfoRecordBO marketInfoRecordBO = marketInfoRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(marketInfoRecordBO, MarketInfoRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息列表
     *
     * @param marketInfoRecordDTO 市场信息dto
     * @return class MarketInfoVO
     * @des 获取所有市场信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MarketInfoRecordDTO marketInfoRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<MarketInfoRecordVO> marketInfoVOS = BeanTransform.copyProperties
                    (marketInfoRecordAPI.findListRecord(marketInfoRecordDTO), MarketInfoRecordVO.class, request);
            return ActResult.initialize(marketInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场信息记录
     *
     * @param marketInfoRecordTO 市场信息记录数据to
     * @return class MarketInfoRecordVO
     * @des 添加市场信息记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MarketInfoRecordTO marketInfoRecordTO, BindingResult bindingResult) throws ActException {
        try {
            MarketInfoRecordBO marketInfoRecordBO = marketInfoRecordAPI.insertRecord(marketInfoRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(marketInfoRecordBO,MarketInfoRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场信息记录
     *
     * @param marketInfoRecordTO 市场信息记录数据to
     * @return class MarketInfoRecordVO
     * @des 编辑市场信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MarketInfoRecordTO marketInfoRecordTO, BindingResult bindingResult) throws ActException {
        try {
            MarketInfoRecordBO marketInfoRecordBO = marketInfoRecordAPI.editRecord(marketInfoRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(marketInfoRecordBO,MarketInfoRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除市场信息记录
     *
     * @param id 用户id
     * @des 根据用户id删除市场信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketInfoRecordAPI.removeRecord(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 业务类型下拉值
     *
     * @des 业务类型下拉值
     * @version v1
     */
    @GetMapping("v1/find/bussType")
    public Result findBussType() throws ActException {
        try {
            //TODO 到市场信息进度管理-业务方向拿业务方向分类未做lijuntao
            List<String> bussTypes = new ArrayList<>();
            return ActResult.initialize(bussTypes);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 业务方向下拉值
     *
     * @des 业务方向下拉值
     * @version v1
     */
    @GetMapping("v1/find/bussDirection")
    public Result findBussDirection() throws ActException {
        try {
            //TODO 到市场信息进度管理-业务方向拿科目未做lijuntao
            List<String> bussDirection = new ArrayList<>();
            return ActResult.initialize(bussDirection);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据客户名称和竞争对手获取信息填写人
     *
     * @des 根据客户名称和竞争对手获取信息填写人
     * @version v1
     */
    @GetMapping("v1/findInfoPeopel/custmer")
    public Result findInfoPeopel(String customer,String[] competitorsNames) throws ActException {
        try {
            //TODO 客户姓名和竞争对手的收集人(创建客户信息和竞争对手的人)未做lijuntao
            String peopel = "";
            return ActResult.initialize(peopel);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 信息收集人下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<String> getPerson = new ArrayList<>();
            getPerson = marketInfoRecordAPI.findallUser();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的客户名称和客户编号
     *
     * @return class MarketNameNumVO
     * @des 获取所有的客户名称和客户编号
     * @version v1
     */
    @GetMapping("v1/customer/nameNum")
    public Result findCustomerNameNum() throws ActException {
        try {
            List<CustomerNameNumBO> customerNameNumBOS = marketInfoRecordAPI.getNameNum();
            return ActResult.initialize(BeanTransform.copyProperties(customerNameNumBOS, MarketNameNumVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的竞争对手名称
     *
     * @des 获取所有的竞争对手名称
     * @version v1
     */
    @GetMapping("v1/competitormanage/name")
    public Result findCompetitorName() throws ActException {
        try {
            List<String> name = marketInfoRecordAPI.getCompetName();
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据市场编号获取字段值
     *
     * @des 根据市场编号获取字段值
     * @version v1
     */
    @GetMapping("v1/bussContracte/marketInfoNum")
    public Result findBussContracte(@RequestParam String marketInfoNum) throws ActException {
        try {
            //todo 根据市场编号从商务合同-商务项目合同未做lijuntao
            List<String> name = marketInfoRecordAPI.getCompetName();
            return ActResult.initialize(name);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息日汇总
     *
     * @param date 日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = marketInfoRecordAPI.summaDay(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息日汇总加了个地区
     *
     * @param date 日期
     * @return class SummationAreaVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/area/day")
    public Result summarizeAreaDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationAreaBO> boList = marketInfoRecordAPI.summaDayByArea(date);
            List<SummationAreaVO> voList = BeanTransform.copyProperties(boList, SummationAreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/week")
    public Result summarizeWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = marketInfoRecordAPI.summaWeek(year, month, week);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationAreaVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/area/week")
    public Result summarizeAreaWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            List<SummationAreaBO> boList = marketInfoRecordAPI.summaWeekByArea(year, month, week);
            List<SummationAreaVO> voList = BeanTransform.copyProperties(boList, SummationAreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = marketInfoRecordAPI.summaMonth(year, month);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息月汇总加个地区
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationAreaVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/area/month")
    public Result summarizeAreaMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationAreaBO> boList = marketInfoRecordAPI.summaMonthByArea(year, month);
            List<SummationAreaVO> voList = BeanTransform.copyProperties(boList, SummationAreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/quarter")
    public Result summarizeQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = marketInfoRecordAPI.summaQuarter(year, quarter);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息季度汇总加个地区
     *
     * @param year  年份
     * @param quarter 季度
     * @return class SummationAreaVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/area/quarter")
    public Result summarizeAreaQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            List<SummationAreaBO> boList = marketInfoRecordAPI.summaMonthByArea(year, quarter);
            List<SummationAreaVO> voList = BeanTransform.copyProperties(boList, SummationAreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息年度汇总
     *
     * @param year  年份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/year")
    public Result summarizeYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = marketInfoRecordAPI.summaYear(year);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息年度汇总加个地区
     *
     * @param year  年份
     * @return class SummationAreaVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/area/year")
    public Result summarizeAreaYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            List<SummationAreaBO> boList = marketInfoRecordAPI.summaYearByArea(year);
            List<SummationAreaVO> voList = BeanTransform.copyProperties(boList, SummationAreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息累计汇总
     *
     * @param date 截止日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/total")
    public Result summarizeTotal(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = marketInfoRecordAPI.summaTotal(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息累计汇总加了个地区
     *
     * @param date 截止日期
     * @return class SummationAreaVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/area/total")
    public Result summarizeAreaTotal(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationAreaBO> boList = marketInfoRecordAPI.summaTotalByArea(date);
            List<SummationAreaVO> voList = BeanTransform.copyProperties(boList, SummationAreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息图形展示日汇总
     *
     * @param date 日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = marketInfoRecordAPI.figureShowDay(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);

            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息图形展示周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/week")
    public Result figureShowWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = marketInfoRecordAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息图形展示月汇总
     *
     * @param year  年份
     * @param month 月份
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = marketInfoRecordAPI.figureShowMonth(year, month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 市场信息图形展示季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/quarter")
    public Result figureShowQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = marketInfoRecordAPI.figureShowQuarter(year, quarter);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息图形展示年度汇总
     *
     * @param year  年份
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/year")
    public Result figureShowYear(Integer year, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = marketInfoRecordAPI.figureShowYear(year);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 市场信息图形展示累计汇总
     *
     * @param date 截止日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = marketInfoRecordAPI.figureShowTotal(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}