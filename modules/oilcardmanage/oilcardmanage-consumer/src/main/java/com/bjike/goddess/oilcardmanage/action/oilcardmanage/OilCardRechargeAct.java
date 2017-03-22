package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.api.OilCardRechargeAPI;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;
import com.bjike.goddess.oilcardmanage.vo.OilCardRechargeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 油卡充值操作action
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午11:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("oilCardRecharge")
public class OilCardRechargeAct {

    @Autowired
    private OilCardRechargeAPI oilCardRechargeAPI;


    /**
     * 新增油卡充值信息
     *
     * @param to 充卡充值信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(OilCardRechargeTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardRechargeVO vo = BeanTransform.copyProperties(oilCardRechargeAPI.saveOilCardRecharge(to), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑油卡充值信息
     *
     * @param to 油卡充值信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(OilCardRechargeTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardRechargeVO vo = BeanTransform.copyProperties(oilCardRechargeAPI.updateOilCardRecharge(to), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 油卡充值分页查询
     *
     * @param dto 分页查询信息
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(OilCardRechargeDTO dto) throws ActException {

        try {
            List<OilCardRechargeVO> vo = BeanTransform.copyProperties(oilCardRechargeAPI.pageList(dto), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 油卡充值汇总查询
     *
     * @param id        油卡id
     * @param startTime 分页查询信息
     * @param endTime   分页查询信息
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(String id, String startTime, String endTime) throws ActException {

        try {
            List<OilCardRechargeVO> vo = BeanTransform.copyProperties(oilCardRechargeAPI.collect(id, startTime, endTime), OilCardRechargeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
