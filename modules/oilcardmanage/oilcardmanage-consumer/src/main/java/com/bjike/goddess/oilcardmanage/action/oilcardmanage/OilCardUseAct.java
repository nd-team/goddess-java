package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.vo.InfoForOilCardVO;
import com.bjike.goddess.oilcardmanage.api.OilCardRechargeAPI;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.vo.AnalyzeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 油卡使用记录
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午11:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recharge")
public class OilCardUseAct {

    @Autowired
    private OilCardRechargeAPI oilCardRechargeAPI;
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;




    /**
     * 列表
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class InfoForOilCardVO
     * @version v1
     */
    @GetMapping("v1/list/{oilCardCode}")
    public Result pageList(@PathVariable String oilCardCode, @RequestParam String startTime, @RequestParam String endTime) throws ActException {

        try {
            DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
            dto.getConditions().add(Restrict.ne("findType", FindType.WAITAUDIT));
            dto.getConditions().add(Restrict.gt("addOilTime", startTime));
            dto.getConditions().add(Restrict.lt("addOilTime", endTime));
            dto.getConditions().add(Restrict.eq("oilCardNumber", oilCardCode));
            List<InfoForOilCardVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), InfoForOilCardVO.class);
            if (!CollectionUtils.isEmpty(voList)) {
                for (InfoForOilCardVO vo : voList) {
                    vo.setAddOilAmount(vo.getOilPrice() * vo.getAddOilAmount());
                }
            }
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分析
     *
     * @param year  年份
     * @param month 月份
     * @return class InfoForOilCardVO
     * @version v1
     */
    @GetMapping("v1/analyze/{oilCardCode}")
    public Result analyze(@PathVariable String oilCardCode, @RequestParam Integer year, @RequestParam Integer month) throws ActException {

        try {
            AnalyzeVO vo = BeanTransform.copyProperties(oilCardRechargeAPI.analyze(oilCardCode, year, month), InfoForOilCardVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
