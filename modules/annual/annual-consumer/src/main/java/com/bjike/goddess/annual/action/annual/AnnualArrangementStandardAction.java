package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualArrangementStandardAPI;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.to.AnnualArrangementStandardTO;
import com.bjike.goddess.annual.vo.AnnualArrangementStandardVO;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 年假层级标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annual/annualarrangementstandard")
public class AnnualArrangementStandardAction {

    @Autowired
    private AnnualArrangementStandardAPI annualArrangementStandardAPI;

    /**
     * 更新年假层级标准实体数据
     *
     * @param to 年假层级标准传输对象
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @PutMapping("v1/update/{standard_id}")
    public Result update(@Validated(EDIT.class) AnnualArrangementStandardTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.update(to), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年假标准查询年假层级标准
     *
     * @param standard_id 年假标准id
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/findByStandard/{standard_id}")
    public Result findByStandard(@PathVariable String standard_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.findByStandard(standard_id), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年假层级标准列表
     *
     * @param dto 年假层级标准数据传输对象
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualArrangementStandardDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.maps(dto), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据标准和岗位层级查询年假层级标准
     *
     * @param standard_id    年假标准ID
     * @param arrangement_id 岗位层级ID
     * @return class AnnualArrangementStandardVO
     * @version v1
     */
    @GetMapping("v1/findByArrangementStandard")
    public Result findByArrangementStandard(String standard_id, String arrangement_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualArrangementStandardAPI.findByArrangementStandard(standard_id, arrangement_id), AnnualArrangementStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}