package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DesignNumberInfoAPI;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;
import com.bjike.goddess.organize.vo.DesignNumberInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 编号设计信息操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("designNumberInfo")
public class DesignNumberInfoAct {

    @Autowired
    private DesignNumberInfoAPI designNumberInfoAPI;

    /**
     * 保存编号设计信息
     *
     * @param to 编号设计信息传输对象
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DesignNumberInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DesignNumberInfoBO bo = designNumberInfoAPI.save(to);
            return new ActResult(BeanTransform.copyProperties(bo, DesignNumberInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改编号设计信息
     *
     * @param to 编号设计信息传输对象
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DesignNumberInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DesignNumberInfoBO bo = designNumberInfoAPI.update(to);
            return new ActResult(BeanTransform.copyProperties(bo, DesignNumberInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 编号设计信息数据id
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(designNumberInfoAPI.delete(id), DesignNumberInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 编号设计信息数据传输
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DesignNumberInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(designNumberInfoAPI.maps(dto), DesignNumberInfoVO.class, request));
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
            return ActResult.initialize(designNumberInfoAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询编号设计信息
     *
     * @param id 编号设计信息数据id
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(designNumberInfoAPI.findById(id), DesignNumberInfoVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
