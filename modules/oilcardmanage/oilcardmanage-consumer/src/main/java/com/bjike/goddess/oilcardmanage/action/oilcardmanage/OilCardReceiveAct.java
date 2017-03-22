package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.api.OilCardReceiveAPI;
import com.bjike.goddess.oilcardmanage.dto.OilCardReceiveDTO;
import com.bjike.goddess.oilcardmanage.to.OilCardReceiveTO;
import com.bjike.goddess.oilcardmanage.vo.OilCardReceiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 油卡领用操作action
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午5:07]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("oilcardreceive")
public class OilCardReceiveAct {

    @Autowired
    private OilCardReceiveAPI oilCardReceiveAPI;

    /**
     * 新增油卡领用信息
     *
     * @param to 卡领用信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(OilCardReceiveTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardReceiveVO vo = BeanTransform.copyProperties(oilCardReceiveAPI.saveOilCardReceive(to), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑油卡领用信息
     *
     * @param to 油卡领用信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(OilCardReceiveTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardReceiveVO vo = BeanTransform.copyProperties(oilCardReceiveAPI.updateOilCardReceive(to), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑油卡领用信息
     *
     * @param to 油卡领用信息
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(OilCardReceiveTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardReceiveVO vo = BeanTransform.copyProperties(oilCardReceiveAPI.auditOilCardReceive(to), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除油卡领用
     *
     * @param id 油卡领用Id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {

        try {
            oilCardReceiveAPI.deleteOilCardReceive(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 归还油卡领用
     *
     * @param id 油卡领用Id
     * @version v1
     */
    @GetMapping("v1/returnOilCard/{id}")
    public Result returnOilCard(@PathVariable String id) throws ActException {

        try {
            oilCardReceiveAPI.returnOilCardReceive(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 油卡基本信息分页查询
     *
     * @param dto 分页查询信息
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(OilCardReceiveDTO dto) throws ActException {

        try {
            List<OilCardReceiveVO> vo = BeanTransform.copyProperties(oilCardReceiveAPI.pageList(dto), OilCardReceiveVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
