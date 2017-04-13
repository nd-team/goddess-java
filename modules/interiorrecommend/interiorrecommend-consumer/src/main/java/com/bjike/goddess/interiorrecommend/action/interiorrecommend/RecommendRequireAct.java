package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendRequireAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendRequireDTO;
import com.bjike.goddess.interiorrecommend.to.RecommendRequireTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendAssessDetailVO;
import com.bjike.goddess.interiorrecommend.vo.RecommendRequireVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐要求
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 03:29 ]
 * @Description: [ 推荐要求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recommendrequire")
public class RecommendRequireAct {

    @Autowired
    private RecommendRequireAPI recommendRequireAPI;

    /**
     * 新增推荐要求
     *
     * @param to 推荐要求
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(RecommendRequireTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendRequireVO vo = BeanTransform.copyProperties(recommendRequireAPI.addModel(to), RecommendRequireVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑推荐要求
     *
     * @param to 推荐要求
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(RecommendRequireTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendRequireVO vo = BeanTransform.copyProperties(recommendRequireAPI.editModel(to), RecommendRequireVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据推荐要求id查询推荐考核内容
     *
     * @param id 推荐要求id
     * @version v1
     */
    @GetMapping("v1/findAssessDetail/{id}")
    public Result findAssessDetail(@PathVariable String id) throws ActException {
        try {
            List<RecommendAssessDetailVO> voList = BeanTransform.copyProperties(recommendRequireAPI.findAssessDetail(id), RecommendAssessDetailVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除推荐要求
     *
     * @param id 推荐要求id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendRequireAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 推荐要求分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(RecommendRequireDTO dto) throws ActException {
        try {
            List<RecommendRequireVO> voList = BeanTransform.copyProperties(recommendRequireAPI.pageList(dto), RecommendRequireVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}