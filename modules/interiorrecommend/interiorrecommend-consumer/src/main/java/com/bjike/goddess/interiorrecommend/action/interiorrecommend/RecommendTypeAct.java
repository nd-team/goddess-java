package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendTypeAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendTypeTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = recommendTypeAPI.guidePermission(guidePermissionTO);
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
     * 新增
     *
     * @param to 推荐类型设定
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RecommendTypeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendTypeVO vo = BeanTransform.copyProperties(recommendTypeAPI.addModel(to), RecommendTypeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 推荐类型设定
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RecommendTypeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendTypeVO vo = BeanTransform.copyProperties(recommendTypeAPI.editModel(to), RecommendTypeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id 推荐类型设定id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendTypeAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(RecommendTypeDTO dto) throws ActException {
        try {
            List<RecommendTypeVO> voList = BeanTransform.copyProperties(recommendTypeAPI.pageList(dto), RecommendTypeVO.class);
            return ActResult.initialize(voList);
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
    public Result count(RecommendTypeDTO dto) throws ActException {
        try {
            Long count = recommendTypeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询竞争对手记录
     *
     * @param id 竞争对手Id
     * @return class RecommendTypeVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecommendTypeVO vo = BeanTransform.copyProperties(recommendTypeAPI.findById(id), RecommendTypeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}