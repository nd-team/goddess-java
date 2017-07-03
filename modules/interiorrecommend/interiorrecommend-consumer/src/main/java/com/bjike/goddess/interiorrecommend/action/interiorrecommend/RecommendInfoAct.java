package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendInfoAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendInfoDTO;
import com.bjike.goddess.interiorrecommend.to.RecommendInfoTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendContentVO;
import com.bjike.goddess.interiorrecommend.vo.RecommendInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("recommendinfo")
public class RecommendInfoAct {

    @Autowired
    private RecommendInfoAPI recommendInfoAPI;

    /**
     * 新增推荐信息
     *
     * @param to 推荐信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(RecommendInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendInfoVO vo = BeanTransform.copyProperties(recommendInfoAPI.addModel(to), RecommendInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑推荐信息
     *
     * @param to 推荐信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(RecommendInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendInfoVO vo = BeanTransform.copyProperties(recommendInfoAPI.editModel(to), RecommendInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 采纳审核
     *
     * @param id     推荐信息id
     * @param reason 原因
     * @param accept 是否采纳
     * @version v1
     */
    @GetMapping("v1/acceptAudit")
    public Result acceptAudit(String id, String reason, Boolean accept) throws ActException {
        try {
            recommendInfoAPI.acceptAudit(id, reason, accept);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 奖励审核
     *
     * @param id 推荐信息id
     * @param id 推荐信息id
     * @version v1
     */
    @GetMapping("v1/conformAudit")
    public Result conformAudit(String id, Boolean conform) throws ActException {
        try {
            recommendInfoAPI.conformAudit(id, conform);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据推荐信息查询推荐内容
     *
     * @param id 推荐信息id
     * @version v1
     */
    @GetMapping("v1/findContent/{id}")
    public Result findContent(@PathVariable String id) throws ActException {
        try {
            List<RecommendContentVO> voList = BeanTransform.copyProperties(recommendInfoAPI.findContent(id), RecommendContentVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除推荐信息
     *
     * @param id 推荐信息id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 推荐信息分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(RecommendInfoDTO dto) throws ActException {
        try {
            List<RecommendInfoVO> voList = BeanTransform.copyProperties(recommendInfoAPI.pageList(dto), RecommendInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}