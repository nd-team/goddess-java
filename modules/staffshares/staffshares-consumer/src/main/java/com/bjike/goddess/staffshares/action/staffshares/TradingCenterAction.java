package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.PurchaseAPI;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.vo.SchemeIssueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 交易中心
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 09:05 ]
 * @Description: [ 交易中心 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tradingcenter")
public class TradingCenterAction {


}