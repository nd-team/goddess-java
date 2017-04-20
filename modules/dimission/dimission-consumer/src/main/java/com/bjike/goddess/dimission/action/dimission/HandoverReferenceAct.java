package com.bjike.goddess.dimission.action.dimission;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.HandoverReferenceAPI;
import com.bjike.goddess.dimission.dto.HandoverReferenceDTO;
import com.bjike.goddess.dimission.to.HandoverReferenceTO;
import com.bjike.goddess.dimission.vo.HandoverReferenceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 交接信息参考
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:36 ]
 * @Description: [ 交接信息参考 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("handoverreference")
public class HandoverReferenceAct {

    @Autowired
    private HandoverReferenceAPI handoverReferenceAPI;

    /**
     * 保存
     *
     * @param to 交接信息传输对象
     * @return class HandoverReferenceVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) HandoverReferenceTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handoverReferenceAPI.save(to), HandoverReferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 交接信息传输对象
     * @return class HandoverReferenceVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) HandoverReferenceTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handoverReferenceAPI.update(to), HandoverReferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 交接信息数据id
     * @return class HandoverReferenceVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handoverReferenceAPI.delete(id), HandoverReferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 交接信息参考数据传输对象
     * @return class HandoverReferenceVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(HandoverReferenceDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(handoverReferenceAPI.maps(dto), HandoverReferenceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}