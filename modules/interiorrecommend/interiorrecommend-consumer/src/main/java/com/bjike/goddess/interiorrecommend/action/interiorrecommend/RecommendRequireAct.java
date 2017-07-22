package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendRequireAPI;
import com.bjike.goddess.interiorrecommend.bo.RecommendRequireBO;
import com.bjike.goddess.interiorrecommend.dto.RecommendRequireDTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendRequireTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendAssessDetailVO;
import com.bjike.goddess.interiorrecommend.vo.RecommendRequireVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = recommendRequireAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增推荐要求
     *
     * @param to 推荐要求
     * @return class RecommendRequireVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RecommendRequireTO to, BindingResult bindingResult) throws ActException {
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
     * @return class RecommendRequireVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RecommendRequireTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendRequireVO vo = BeanTransform.copyProperties(recommendRequireAPI.editModel(to), RecommendRequireVO.class);
            return ActResult.initialize(vo);
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
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
     * @return class RecommendRequireVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(RecommendRequireDTO dto) throws ActException {
        try {
            List<RecommendRequireBO> boList = recommendRequireAPI.pageList(dto);
            if (!CollectionUtils.isEmpty(boList)) {
                List<RecommendRequireVO> voList = new ArrayList<RecommendRequireVO>();
                for (RecommendRequireBO bo : boList) {
                    RecommendRequireVO vo = BeanTransform.copyProperties(bo, RecommendRequireVO.class);
                    vo.setDetailList(BeanTransform.copyProperties(bo.getDetailList(), RecommendAssessDetailVO.class));
                    voList.add(vo);
                }
                return ActResult.initialize(voList);
            } else {
                return ActResult.initialize(null);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RecommendRequireDTO dto) throws ActException {
        try {
            Long count = recommendRequireAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询推荐要求设定
     *
     * @param id Id
     * @return class RecommendRequireVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecommendRequireBO bo = recommendRequireAPI.findById(id);
            RecommendRequireVO vo = BeanTransform.copyProperties(bo, RecommendRequireVO.class, request);
            vo.setDetailList(BeanTransform.copyProperties(bo.getDetailList(), RecommendAssessDetailVO.class));
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}