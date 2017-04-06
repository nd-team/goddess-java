package com.bjike.goddess.intromanage.action.intromanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.api.FirmIntroAPI;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import com.bjike.goddess.intromanage.vo.FirmIntroVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 公司简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("intromanage/firmintro")
public class FirmIntroAct {

    @Autowired
    private FirmIntroAPI firmIntroAPI;

    /**
     * 分页查询公司简介
     *
     * @param dto 公司简介dto
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FirmIntroDTO dto) throws ActException {
        try {
            List<FirmIntroBO> boList = firmIntroAPI.list(dto);
            List<FirmIntroVO> voList = BeanTransform.copyProperties(boList, FirmIntroVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FirmIntroTO to) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.save(to);
            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firmIntroAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑公司简介
     *
     * @param to 公司简介to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(FirmIntroTO to) throws ActException {
        try {
            firmIntroAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}