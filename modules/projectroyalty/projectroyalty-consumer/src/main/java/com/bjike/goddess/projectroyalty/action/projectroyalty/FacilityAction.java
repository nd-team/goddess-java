package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.api.FacilityAPI;
import com.bjike.goddess.projectroyalty.dto.FacilityDTO;
import com.bjike.goddess.projectroyalty.to.FacilityTO;
import com.bjike.goddess.projectroyalty.vo.FacilityVO;
import com.bjike.goddess.projectroyalty.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 难易度
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("facility")
public class FacilityAction {

    @Autowired
    private FacilityAPI facilityAPI;


    /**
     * 保存
     *
     * @param to 难易度传输对象
     * @return class FacilityVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) FacilityTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityAPI.save(to), FacilityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 难易度传输对象
     * @return class FacilityVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) FacilityTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityAPI.update(to), FacilityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 难易度数据id
     * @return class FacilityVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityAPI.delete(id), FacilityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取难易度数据
     *
     * @param id 难易度数据id
     * @return class FacilityVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@Validated String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityAPI.getById(id), FacilityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findOpinion")
    public Result findOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityAPI.findOpinion(), OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 难易度数据传输对象
     * @return class FacilityVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(FacilityDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(facilityAPI.maps(dto), FacilityVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(facilityAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}