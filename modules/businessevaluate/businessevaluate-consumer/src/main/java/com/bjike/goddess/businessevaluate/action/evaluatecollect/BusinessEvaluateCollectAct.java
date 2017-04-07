package com.bjike.goddess.businessevaluate.action.evaluatecollect;

import com.bjike.goddess.businessevaluate.api.BusinessEvaluateCollectAPI;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.businessevaluate.vo.BusinessEvaluateCollectVO;
import com.bjike.goddess.businessevaluate.vo.EvaluateCollectTotalVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商务评估汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessevaluate/businessevaluatecollect")
public class BusinessEvaluateCollectAct {

    @Autowired
    private BusinessEvaluateCollectAPI businessEvaluateCollectAPI;

    /**
     * 新增商务评估汇总
     *
     * @param to 商务评估汇总
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(BusinessEvaluateCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessEvaluateCollectVO vo = BeanTransform.copyProperties(businessEvaluateCollectAPI.addModel(to), BusinessEvaluateCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商务评估汇总
     *
     * @param to 商务评估汇总
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(BusinessEvaluateCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessEvaluateCollectVO vo = BeanTransform.copyProperties(businessEvaluateCollectAPI.editModel(to), BusinessEvaluateCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结商务评估汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @GetMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            businessEvaluateCollectAPI.freeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻商务评估汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @GetMapping("v1/breakFreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            businessEvaluateCollectAPI.breakFreeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除商务评估汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            businessEvaluateCollectAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询汇总定时器
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(BusinessEvaluateCollectDTO dto) throws ActException {
        try {
            List<BusinessEvaluateCollectVO> voList = BeanTransform.copyProperties(businessEvaluateCollectAPI.pageList(dto), BusinessEvaluateCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总详情
     *
     * @param area    汇总条件
     * @param project 汇总条件
     * @version v1
     */
    @GetMapping("v1/collectionTotal")
    public Result collectionTotal(String area, String project) throws ActException {
        try {
            List<EvaluateCollectTotalVO> voList = BeanTransform.copyProperties(businessEvaluateCollectAPI.collectionTotal(area, project), EvaluateCollectTotalVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}