package com.bjike.goddess.oilcardmanage.action.oilcardmanage;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
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
 * 油卡信息
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:51]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("basic")
public class OilCardBasicAct {

    @Autowired
    private OilCardBasicAPI oilCardBasicAPI;

    /**
     * 新增
     *
     * @param to 油卡基础信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OilCardBasicTO to, BindingResult bindingResult) throws ActException {

        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.saveOilCarBasic(to), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 油卡基础信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OilCardBasicTO to, BindingResult bindingResult) throws ActException {
        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.updateOilCardBasic(to), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.freezeOilCardBasic(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @version v1
     */
    @PutMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 油卡信息记录ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {

        try {
            oilCardBasicAPI.deleteOilCardBasic(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页查询信息
     * @return class OilCardBasicVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(OilCardBasicDTO dto) throws ActException {
        try {
            List<OilCardBasicVO> voList = BeanTransform.copyProperties(oilCardBasicAPI.pageList(dto), OilCardBasicVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据Id查询油卡基础信息
     *
     * @param id id
     * @return class OilCardBasicVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result pageList(String id) throws ActException {
        try {
            OilCardBasicVO vo = BeanTransform.copyProperties(oilCardBasicAPI.findById(id), OilCardBasicVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OilCardBasicDTO dto) throws ActException {
        try {
            if (dto.getStatus() != null) {
                dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
            }
            Long count = oilCardBasicAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
