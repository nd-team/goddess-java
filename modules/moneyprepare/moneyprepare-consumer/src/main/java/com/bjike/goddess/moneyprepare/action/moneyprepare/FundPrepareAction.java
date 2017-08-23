package com.bjike.goddess.moneyprepare.action.moneyprepare;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyprepare.api.FundPrepareAPI;
import com.bjike.goddess.moneyprepare.bo.*;
import com.bjike.goddess.moneyprepare.dto.FundPrepareDTO;
import com.bjike.goddess.moneyprepare.to.*;
import com.bjike.goddess.moneyprepare.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 资金准备
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundprepare")
public class FundPrepareAction {

    @Autowired
    private FundPrepareAPI fundPrepareAPI;

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

            Boolean isHasPermission = fundPrepareAPI.guidePermission(guidePermissionTO);
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
     * @param fundPrepareDTO 资金准备dto
     * @des 获取所有资金准备总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundPrepareDTO fundPrepareDTO) throws ActException {
        try {
            Long count = fundPrepareAPI.countFundPrepare(fundPrepareDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一条资金准备
     *
     * @param id 项目资金准备id
     * @return class FundPrepareVO
     * @des 根据id获取项目资金准备
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            FundPrepareVO fundPrepareVO = BeanTransform.copyProperties(
                    fundPrepareAPI.getOneById(id), FundPrepareVO.class);
            return ActResult.initialize(fundPrepareVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目资金准备列表
     *
     * @param fundPrepareDTO 项目资金准备信息dto
     * @return class FundPrepareVO
     * @des 获取所有项目资金准备信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListFundPrepare(FundPrepareDTO fundPrepareDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<FundPrepareBO> list = fundPrepareAPI.listFundPrepare(fundPrepareDTO);
            List<FundPrepareVO> fundPrepareVOList = new ArrayList<>();
            list.stream().forEach(str -> {
                FundPrepareVO vo = BeanTransform.copyProperties(str, FundPrepareVO.class);
                fundPrepareVOList.add(vo);
            });

            return ActResult.initialize(fundPrepareVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目资金准备
     *
     * @param fundPrepareObjectTO 项目资金准备基本信息数据to
     * @return class FundPrepareVO
     * @des 添加项目资金准备
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addFundPrepare(@Validated(FundPrepareTO.TestAdd.class) FundPrepareObjectTO fundPrepareObjectTO, BindingResult bindingResult) throws ActException {
        try {
            List<FundPrepareBO> fundPrepareBO1List = fundPrepareAPI.addFundPrepare(fundPrepareObjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(fundPrepareBO1List, FundPrepareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目资金准备
     *
     * @param fundPrepareTO 项目资金准备基本信息数据bo
     * @return class FundPrepareVO
     * @des 添加项目资金准备
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editFundPrepare(@Validated(FundPrepareTO.TestAdd.class) FundPrepareTO fundPrepareTO, BindingResult bindingResult) throws ActException {
        try {
            FundPrepareBO fundPrepareBO1 = fundPrepareAPI.editFundPrepare(fundPrepareTO);
            return ActResult.initialize(BeanTransform.copyProperties(fundPrepareBO1, FundPrepareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目资金准备信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteFundPrepare(@PathVariable String id) throws ActException {
        try {
            fundPrepareAPI.deleteFundPrepare(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得一级科目
     *
     * @des 获得一级科目
     * @version v1
     */
    @GetMapping("v1/findFirstSubject")
    public Result findFirstSubject() throws ActException {
        try {
            List<String> list = fundPrepareAPI.findFirstSubject();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获得二级科目
     *
     * @des 获得二级科目
     * @version v1
     */
    @GetMapping("v1/findSecondSubject")
    public Result findSecondSubject() throws ActException {
        try {
            List<String> list = fundPrepareAPI.findSecondSubject();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分配详情
     *
     * @param id 项目资金准备id
     * @return class ProportionVO
     * @des 根据资金准备ID获得项目分配详情
     * @version v1
     */
    @GetMapping("v1/listDetail/{id}")
    public Result listDetail(@PathVariable String id) throws ActException {
        try {
            List<ProportionBO> list = BeanTransform.copyProperties(
                    fundPrepareAPI.listDetail(id), ProportionVO.class, "time");
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目分配详情进行修改
     *
     * @param proportionObjectTO 项目分配详情
     * @return class FundPrepareVO
     * @des 项目分配详情进行修改(修改时除了加上fundId,还要加上比例分配的id)
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/editProportion")
    public Result editProportion(ProportionObjectTO proportionObjectTO) throws ActException {
        try{
        List<ProportionBO> proportionBOs = fundPrepareAPI.editProportion(proportionObjectTO);
            return ActResult.initialize(BeanTransform.copyProperties(proportionBOs,ProportionTO.class));
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 周汇总
     *
     * @return class FundPrepareWeekVO
     * @des 查询当月周汇总
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect() throws ActException {
        try {
            List<FundPrepareWeekBO> fundPrepareWeekBOList = fundPrepareAPI.weekCollect();
            return ActResult.initialize(BeanTransform.copyProperties(fundPrepareWeekBOList, FundPrepareWeekVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @return class FundPrepareMonthVO
     * @des 查询月汇总
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect() throws ActException {
        try {
            List<FundPrepareMonthBO> bos = fundPrepareAPI.monthCollect();
            return ActResult.initialize(BeanTransform.copyProperties(bos, FundPrepareMonthVO.class, "time", "project"));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年汇总
     *
     * @return class FundPrepareYearVO
     * @des 查询年汇总
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect() throws ActException {
        try {
            List<FundPrepareYearBO> bos = fundPrepareAPI.yearCollect();
            return ActResult.initialize(BeanTransform.copyProperties(bos, FundPrepareYearVO.class, "time", "project"));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金准备对比分析
     *
     * @return class FundPrepareanAlysisVO
     * @des 资金准备对比分析
     * @version v1
     */
    @GetMapping("v1/analysis")
    public Result analysis() throws ActException {
        try {
            List<FundPrepareanAlysisBO> list = fundPrepareAPI.analysis();
            return ActResult.initialize(BeanTransform.copyProperties(list, FundPrepareanAlysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目分配汇总
     *
     * @return class ProjectCollectVO
     * @des 项目分配汇总
     * @version v1
     */
    @GetMapping("v1/projectCollect")
    public Result projectCollect() throws ActException {
        try {
            List<ProjectCollectBO> list = fundPrepareAPI.projectCollect();
            return ActResult.initialize(BeanTransform.copyProperties(list,ProjectCollectVO.class,"time"));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
