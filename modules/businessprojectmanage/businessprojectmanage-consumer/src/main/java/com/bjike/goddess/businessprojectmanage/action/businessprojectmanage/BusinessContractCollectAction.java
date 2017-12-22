package com.bjike.goddess.businessprojectmanage.action.businessprojectmanage;

import com.bjike.goddess.businessprojectmanage.api.BusinessContractProAPI;
import com.bjike.goddess.businessprojectmanage.bo.BCCollectEchartBO;
import com.bjike.goddess.businessprojectmanage.bo.BusinessContractCollectBO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.vo.BCCollectEchartVO;
import com.bjike.goddess.businessprojectmanage.vo.BusinessContractCollectVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目实施进度汇总
 *
 * @Author: [caiwenxian]
 * @Date: [2017-12-16 10:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("businesscontractcollect")
public class BusinessContractCollectAction {

    @Autowired
    BusinessContractProAPI businessContractProAPI;

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

            Boolean isHasPermission = businessContractProAPI.guidePermission(guidePermissionTO);
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
     * 项目实施进度管理日汇总
     *
     * @param time 　日期
     * @return class BusinessContractCollectVO
     * @version v1
     */
    //    @LoginAuth
    @GetMapping("v1/daycollect")
    public Result daycollect(String time) throws ActException {
        try {
            List<BusinessContractCollectBO> bos = businessContractProAPI.dayCollect(time);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessContractCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理周汇总
     *
     * @param year  　年份
     * @param month 　月份
     * @param week  　周期
     * @return class BusinessContractCollectVO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            List<BusinessContractCollectBO> bos = businessContractProAPI.weekCollect(year, month, week);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessContractCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理月汇总
     *
     * @param year  　年份
     * @param month 　月份
     * @return class BusinessContractCollectVO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year, Integer month) throws ActException {
        try {
            List<BusinessContractCollectBO> bos = businessContractProAPI.monthCollect(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessContractCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理季度汇总
     *
     * @param year    　年份
     * @param quarter 　季度
     * @return class BusinessContractCollectVO
     * @version v1
     */
    @GetMapping("v1/quarterCollect")
    public Result quarterCollect(Integer year, Integer quarter) throws ActException {
        try {
            List<BusinessContractCollectBO> bos = businessContractProAPI.quarterCollect(year, quarter);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessContractCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理年汇总
     *
     * @param year 　年份
     * @return class BusinessContractCollectVO
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect(Integer year) throws ActException {
        try {
            List<BusinessContractCollectBO> bos = businessContractProAPI.yearCollect(year);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessContractCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理累计汇总
     *
     * @param time 　日期
     * @return class BusinessContractCollectVO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(String time) throws ActException {
        try {
            List<BusinessContractCollectBO> bos = businessContractProAPI.totalCollect(time);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessContractCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 项目实施进度完工情况管理日汇总
     *
     * @return class BCCollectEchartVO
     * @param　time 时间
     * @version v1
     */
    //    @LoginAuth
    @GetMapping("v1/dayProgressCollect")
    public Result dayProgressCollect(String time) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.dayProgressCollect(time);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度完工情况管理周汇总
     *
     * @param year  　年份
     * @param month 　月份
     * @param week  　周期
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/weekProgressCollect")
    public Result weekProgressCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.weekProgressCollect(year, month, week);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度完工情况管理月汇总
     *
     * @param year  　年份
     * @param month 　月份
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/monthProgressCollect")
    public Result monthProgressCollect(Integer year, Integer month) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.monthProgressCollect(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度完工情况管理季度汇总
     *
     * @param year    　年份
     * @param quarter 　季度
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/quarterProgressCollect")
    public Result quarterProgressCollect(Integer year, Integer quarter) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.monthProgressCollect(year, quarter);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度完工情况管理年汇总
     *
     * @param year 　年份
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/yearProgressCollect")
    public Result yearProgressCollect(Integer year) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.yearProgressCollect(year);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度完工情况管理累计汇总
     *
     * @param time 　时间
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/totalProgressCollect")
    public Result totalProgressCollect(String time) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.totalProgressCollect(time);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理金额情况日汇总
     *
     * @param time 时间
     * @return class BCCollectEchartVO
     * @version v1
     */
    //    @LoginAuth
    @GetMapping("v1/dayMoneyCollect")
    public Result dayMoneyCollect(String time) throws ActException {
        try {

            BCCollectEchartBO list = businessContractProAPI.dayMoneyCollect(time);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理金额情况周汇总
     *
     * @param year  　年份
     * @param month 　月份
     * @param week  　周期
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/weekMoneyCollect")
    public Result weekMoneyCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.weekMoneyCollect(year, month, week);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理金额情况月汇总
     *
     * @param year  　年份
     * @param month 　月份
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/monthMoneyCollect")
    public Result monthMoneyCollect(Integer year, Integer month) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.monthMoneyCollect(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理金额情况季度汇总
     *
     * @param year    　年份
     * @param quarter 　季度
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/quarterMoneyCollect")
    public Result quarterMoneyCollect(Integer year, Integer quarter) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.quarterMoneyCollect(year, quarter);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理金额情况年汇总
     *
     * @param year 　年份
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/yearMoneyCollect")
    public Result yearMoneyCollect(Integer year) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.yearMoneyCollect(year);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度管理金额情况累计汇总
     *
     * @param time 　时间
     * @return class BCCollectEchartVO
     * @version v1
     */
    @GetMapping("v1/totalMoneyCollect")
    public Result totalMoneyCollect(String time) throws ActException {
        try {
            BCCollectEchartBO list = businessContractProAPI.totalMoneyCollect(time);
            return ActResult.initialize(BeanTransform.copyProperties(list, BCCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
