package com.bjike.goddess.rotation.action.rotation;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.api.RotationCollectAPI;
import com.bjike.goddess.rotation.bo.RotationCollectBO;
import com.bjike.goddess.rotation.bo.RotationCollectEchartBO;
import com.bjike.goddess.rotation.bo.RotationDetailsCollectBO;
import com.bjike.goddess.rotation.dto.RotationCollectEchartDTO;
import com.bjike.goddess.rotation.dto.RotationDetailsCollectDTO;
import com.bjike.goddess.rotation.enums.CollectDetailsType;
import com.bjike.goddess.rotation.enums.CollectTimeType;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.vo.RotationCollectEchartVO;
import com.bjike.goddess.rotation.vo.RotationCollectVO;
import com.bjike.goddess.rotation.vo.RotationDetailsCollectVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 岗位轮岗汇总
 * @Author: [caiwenxian]
 * @Date: [2018-01-10 09:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("rotationcollect")
public class RotationCollectAction {

    @Autowired
    RotationCollectAPI rotationCollectAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = rotationCollectAPI.guidePermission(guidePermissionTO);
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
     * 岗位轮岗明细汇总-管理
     *
     * @param dto dto
     * @return class RotationDetailsCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/detailsManageCollect")
    public Result listDetailsManageCollect(RotationDetailsCollectDTO dto) throws ActException{

        try {
            dto.setCollectDetailsType(CollectDetailsType.MANAGER);
            List<RotationDetailsCollectBO> bos = rotationCollectAPI.listDetailsCollect(dto);
            List<RotationDetailsCollectVO> vos = BeanTransform.copyProperties(bos, RotationDetailsCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮岗明细汇总-决策
     *
     * @param dto dto
     * @return class RotationDetailsCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/detailsDecisionCollect")
    public Result listDetailsDetailsCollect(RotationDetailsCollectDTO dto) throws ActException{

        try {
            dto.setCollectDetailsType(CollectDetailsType.DECISION);
            List<RotationDetailsCollectBO> bos = rotationCollectAPI.listDetailsCollect(dto);
            List<RotationDetailsCollectVO> vos = BeanTransform.copyProperties(bos, RotationDetailsCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮换管理日汇总
     *
     * @param time 时间 '2017-12-12'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/dayCollect")
    public Result dayCollect(String time) throws ActException{
        if (StringUtils.isBlank(time)) {
            throw new ActException("缺失时间参数:time");
        }
        try {
            List<RotationCollectBO> bos = rotationCollectAPI.listCollect(CollectTimeType.DAY, time);
            List<RotationCollectVO> vos = BeanTransform.copyProperties(bos, RotationCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮换管理周汇总
     *
     * @param year 年 '2017'
     * @param month 月 '12'
     * @param week 星期 '1'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/weekCollect")
    public Result weekCollect(String year, String month, String week) throws ActException{
        if (StringUtils.isBlank(year)) {
            throw new ActException("缺失时间参数:year");
        }
        if (StringUtils.isBlank(month)) {
            throw new ActException("缺失时间参数:month");
        }
        if (StringUtils.isBlank(week)) {
            throw new ActException("缺失时间参数:week");
        }
        try {
            List<RotationCollectBO> bos = rotationCollectAPI.listCollect(CollectTimeType.WEEK, year, month, week);
            List<RotationCollectVO> vos = BeanTransform.copyProperties(bos, RotationCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮换管理月汇总
     *
     * @param year 年 '2017'
     * @param month 月 '12'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/monthCollect")
    public Result monthCollect(String year, String month) throws ActException{
        if (StringUtils.isBlank(year)) {
            throw new ActException("缺失时间参数:year");
        }
        if (StringUtils.isBlank(month)) {
            throw new ActException("缺失时间参数:month");
        }
        try {
            List<RotationCollectBO> bos = rotationCollectAPI.listCollect(CollectTimeType.MONTH, year, month);
            List<RotationCollectVO> vos = BeanTransform.copyProperties(bos, RotationCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮换管理季度汇总
     *
     * @param year 年 '2017'
     * @param quarter 季度 '1'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/quarterCollect")
    public Result quarterCollect(String year, String quarter) throws ActException{
        if (StringUtils.isBlank(year)) {
            throw new ActException("缺失时间参数:year");
        }
        if (StringUtils.isBlank(quarter)) {
            throw new ActException("缺失时间参数:quarter");
        }
        try {
            List<RotationCollectBO> bos = rotationCollectAPI.listCollect(CollectTimeType.QUART, year, quarter);
            List<RotationCollectVO> vos = BeanTransform.copyProperties(bos, RotationCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮换管理年汇总
     *
     * @param year 年 '2017'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/yearCollect")
    public Result quarterCollect(String year) throws ActException{
        if (StringUtils.isBlank(year)) {
            throw new ActException("缺失时间参数:year");
        }
        try {
            List<RotationCollectBO> bos = rotationCollectAPI.listCollect(CollectTimeType.YEAR, year);
            List<RotationCollectVO> vos = BeanTransform.copyProperties(bos, RotationCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 岗位轮换管理累计汇总
     *
     * @param time 时间 '2017-12-12'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/totalCollect")
    public Result totalCollect(String time) throws ActException{
        if (StringUtils.isBlank(time)) {
            throw new ActException("缺失时间参数:time");
        }
        try {
            List<RotationCollectBO> bos = rotationCollectAPI.listCollect(CollectTimeType.TOTAL, time);
            List<RotationCollectVO> vos = BeanTransform.copyProperties(bos, RotationCollectVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位轮换明细管理汇总图形展示
     *
     * @param area 地区 '广州'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/rotationDetailsManageEchart")
    public Result rotationDetailsManageEchart(String area) throws ActException{
        if (StringUtils.isBlank(area)) {
            throw new ActException("缺失地区参数:area");
        }
        try {
            RotationCollectEchartBO bo = rotationCollectAPI.collectDetailsEchart(new RotationCollectEchartDTO(CollectDetailsType.MANAGER, area));
            RotationCollectEchartVO vo = BeanTransform.copyProperties(bo, RotationCollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位轮换明细决策汇总图形展示
     *
     * @param area 地区 '广州'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/rotationDetailsDecisionEchart")
    public Result rotationDetailsDecisionEchart(String area) throws ActException{
        if (StringUtils.isBlank(area)) {
            throw new ActException("缺失地区参数:area");
        }
        try {
            RotationCollectEchartBO bo = rotationCollectAPI.collectDetailsEchart(new RotationCollectEchartDTO(CollectDetailsType.DECISION, area));
            RotationCollectEchartVO vo = BeanTransform.copyProperties(bo, RotationCollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位轮换管理汇总图形展示
     *
     * @param area 地区 '广州'
     * @return class RotationCollectVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/rotationEchart")
    public Result rotationEchart(String area) throws ActException{
        if (StringUtils.isBlank(area)) {
            throw new ActException("缺失地区参数:area");
        }
        try {
            RotationCollectEchartBO bo = rotationCollectAPI.collectEchart(new RotationCollectEchartDTO(null, area));
            RotationCollectEchartVO vo = BeanTransform.copyProperties(bo, RotationCollectEchartVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取全部地区
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("/v1/areas")
    public Result listArea() throws ActException{
        try {
            List<String> areas = rotationCollectAPI.listAreas();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
