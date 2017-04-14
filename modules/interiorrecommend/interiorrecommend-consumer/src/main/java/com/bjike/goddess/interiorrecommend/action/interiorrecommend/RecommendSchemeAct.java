package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendSchemeAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendSchemeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recommendscheme")
public class RecommendSchemeAct {

    @Autowired
    private RecommendSchemeAPI recommendSchemeAPI;

    /**
     * 新增推荐方案
     *
     * @param to 推荐方案
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendSchemeVO vo = BeanTransform.copyProperties(recommendSchemeAPI.addModel(to), RecommendSchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑推荐方案
     *
     * @param to 推荐方案
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendSchemeVO vo = BeanTransform.copyProperties(recommendSchemeAPI.editModel(to), RecommendSchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合资源部意见
     *
     * @param to 推荐方案
     * @version v1
     */
    @PostMapping("v1/resourcesAudit")
    public Result resourcesAudit(RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            recommendSchemeAPI.resourcesAudit(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部意见
     *
     * @param to 推荐方案
     * @version v1
     */
    @PostMapping("v1/operateAudit")
    public Result operateAudit(RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            recommendSchemeAPI.operateAudit(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办意见
     *
     * @param to 推荐方案
     * @version v1
     */
    @PostMapping("v1/generalAudit")
    public Result generalAudit(RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            recommendSchemeAPI.generalAudit(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除推荐方案
     *
     * @param id 推荐方案id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendSchemeAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 推荐方案分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(RecommendSchemeDTO dto) throws ActException {
        try {
            List<RecommendSchemeVO> voList = BeanTransform.copyProperties(recommendSchemeAPI.pageList(dto), RecommendSchemeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}