package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.api.OilCardBasicAPI;
import com.bjike.goddess.oilcardmanage.dto.OilCardBasicDTO;
import com.bjike.goddess.oilcardmanage.to.OilCardBasicTO;
import com.bjike.goddess.oilcardmanage.vo.OilCardBasicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 油卡信息操作action
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:51]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("oilcardbasic")
public class OilCardBasicAct {

    @Autowired
    private OilCardBasicAPI oilCardBasicAPI;

    /**
     * 新增油卡基础信息
     *
     * @param to 油卡基础信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated OilCardBasicTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.saveOilCarBasic(to), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑油卡基础信息
     *
     * @param to 油卡基础信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated OilCardBasicTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.updateOilCardBasic(to), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结油卡信息记录
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @PostMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.freezeOilCardBasic(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻油卡信息
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @PostMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除油卡基本信息
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @PostMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.deleteOilCardBasic(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 油卡基本信息分页查询
     *
     * @param dto 分页查询信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(OilCardBasicDTO dto) throws ActException {

        try {
            List<OilCardBasicVO> vo = BeanTransform.copyProperties(oilCardBasicAPI.pageList(dto), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
