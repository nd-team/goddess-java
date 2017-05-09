package com.bjike.goddess.businessevaluate.action.interiorevaluate;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.api.interiorevaluate.FrontlineEvaluateAPI;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.FrontlineEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.FrontlineEvaluateTO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.businessevaluate.vo.interiorevaluate.FrontlineEvaluateVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 一线体系评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("frontline")
public class FrontlineEvaluateAct {

    @Autowired
    private FrontlineEvaluateAPI frontlineEvaluateAPI;

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 查询所有项目
     *
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/porjects")
    public Result porjects(HttpServletRequest request) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAll(), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(voList);
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
    public Result count(FrontlineEvaluateDTO dto) throws ActException {
        try {
            Long count = frontlineEvaluateAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询一线体系评价
     *
     * @param id 一线体系评价id
     * @return class FrontlineEvaluateVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FrontlineEvaluateVO vo = BeanTransform.copyProperties(frontlineEvaluateAPI.findById(id), FrontlineEvaluateVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增一线体系评价
     *
     * @param to 一线体系评价
     * @return class FrontlineEvaluateVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) FrontlineEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            FrontlineEvaluateVO vo = BeanTransform.copyProperties(frontlineEvaluateAPI.addModel(to), FrontlineEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑一线体系评价
     *
     * @param to 一线体系评价
     * @return class FrontlineEvaluateVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) FrontlineEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            FrontlineEvaluateVO vo = BeanTransform.copyProperties(frontlineEvaluateAPI.editModel(to), FrontlineEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除一线体系评价
     *
     * @param id 一线体系评价ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            frontlineEvaluateAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class FrontlineEvaluateVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(FrontlineEvaluateDTO dto) throws ActException {
        try {
            List<FrontlineEvaluateVO> voList = BeanTransform.copyProperties(frontlineEvaluateAPI.pageList(dto), FrontlineEvaluateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}