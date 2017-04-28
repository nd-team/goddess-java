package com.bjike.goddess.materialbuy.action.materialbuy;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.api.TempMatterDemandAPI;
import com.bjike.goddess.materialbuy.bo.TempMatterDemandBO;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.to.TempMatterDemandTO;
import com.bjike.goddess.materialbuy.vo.TempMatterDemandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 临时物资需求
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:08 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tempmatterdemand")
public class TempMatterDemandAct {

    @Autowired
    private TempMatterDemandAPI tempMatterDemandAPI;

    /**
     * 根据id查询临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/tempmatterdemand/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            TempMatterDemandBO bo = tempMatterDemandAPI.findById(id);
            TempMatterDemandVO vo = BeanTransform.copyProperties(bo, TempMatterDemandVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询临时物资需求
     *
     * @param dto           临时物资需求dto
     * @param bindingResult
     * @return class TempMatterDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TempMatterDemandDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<TempMatterDemandBO> boList = tempMatterDemandAPI.list(dto);
            List<TempMatterDemandVO> voList = BeanTransform.copyProperties(boList, TempMatterDemandVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加临时物资需求
     *
     * @param to     临时物资需求to
     * @param result
     * @return class TempMatterDemandVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({TempMatterDemandTO.TempMatterDemandAdd.class}) TempMatterDemandTO to, BindingResult result) throws ActException {
        try {
            TempMatterDemandBO bo = tempMatterDemandAPI.save(to);
            TempMatterDemandVO vo = BeanTransform.copyProperties(bo, TempMatterDemandVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除临时物资需求
     *
     * @param id 临时物资需求唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            tempMatterDemandAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑临时物资需求
     *
     * @param to     临时物资需求to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(TempMatterDemandTO.TempMatterDemandEdit.class) TempMatterDemandTO to, BindingResult result) throws ActException {
        try {
            tempMatterDemandAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to     临时物资需求to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/audit")
    public Result audit(@Validated({TempMatterDemandTO.Audit.class}) TempMatterDemandTO to, BindingResult result) throws ActException {
        try {
            tempMatterDemandAPI.audit(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看详情
     *
     * @param id 临时物资需求唯一标识
     * @return class TempMatterDemandVO
     * @throws ActException
     */
    @GetMapping("v1/checkdetail/{id}")
    public Result checkDetail(@PathVariable String id) throws ActException {
        try {
            TempMatterDemandBO bo = tempMatterDemandAPI.checkDetail(id);
            TempMatterDemandVO vo = BeanTransform.copyProperties(bo, TempMatterDemandVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}