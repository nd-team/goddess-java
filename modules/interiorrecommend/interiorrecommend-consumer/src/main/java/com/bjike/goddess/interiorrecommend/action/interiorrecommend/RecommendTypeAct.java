package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendTypeAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.to.RecommendTypeTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐类型设定
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 02:10 ]
 * @Description: [ 推荐类型设定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recommendtype")
public class RecommendTypeAct {

    @Autowired
    private RecommendTypeAPI recommendTypeAPI;

    /**
     * 新增推荐类型设定
     *
     * @param to 推荐类型设定
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(RecommendTypeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendTypeVO vo = BeanTransform.copyProperties(recommendTypeAPI.addModel(to), RecommendTypeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑推荐类型设定
     *
     * @param to 推荐类型设定
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(RecommendTypeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendTypeVO vo = BeanTransform.copyProperties(recommendTypeAPI.editModel(to), RecommendTypeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除推荐类型设定
     *
     * @param id 推荐类型设定id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendTypeAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 推荐类型设定分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(RecommendTypeDTO dto) throws ActException {
        try {
            List<RecommendTypeVO> voList = BeanTransform.copyProperties(recommendTypeAPI.pageList(dto), RecommendTypeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}