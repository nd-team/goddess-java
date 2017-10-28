package com.bjike.goddess.interiorrecommend.action.interiorrecommend;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.interiorrecommend.api.RecommendSchemeAPI;
import com.bjike.goddess.interiorrecommend.dto.RecommendSchemeDTO;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;
import com.bjike.goddess.interiorrecommend.to.RecommendSchemeTO;
import com.bjike.goddess.interiorrecommend.vo.RecommendSchemeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

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

//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//
//            Boolean isHasPermission = recommendSchemeAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 新增
     *
     * @param to 推荐方案
     * @return class RecommendSchemeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendSchemeVO vo = BeanTransform.copyProperties(recommendSchemeAPI.addModel(to), RecommendSchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 推荐方案
     * @return class RecommendSchemeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RecommendSchemeTO to, BindingResult bindingResult) throws ActException {
        try {
            RecommendSchemeVO vo = BeanTransform.copyProperties(recommendSchemeAPI.editModel(to), RecommendSchemeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 综合资源部意见
//     *
//     * @param id               id
//     * @param resourcesSuggest 意见
//     * @param resourcesAudit   结果
//     * @version v1
//     */
//    @LoginAuth
//    @PutMapping("v1/resourcesAudit/{id}")
//    public Result resourcesAudit(@PathVariable String id, @RequestParam String resourcesSuggest, @RequestParam Boolean resourcesAudit) throws ActException {
//        try {
//            recommendSchemeAPI.resourcesAudit(id, resourcesSuggest, resourcesAudit);
//            return new ActResult("审核成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 运营商务部意见
//     *
//     * @param id             推荐方案
//     * @param operateSuggest 意见
//     * @param operateAudit   结果
//     * @version v1
//     */
//    @LoginAuth
//    @PutMapping("v1/operateAudit/{id}")
//    public Result operateAudit(@PathVariable String id, @RequestParam String operateSuggest, @RequestParam Boolean operateAudit) throws ActException {
//        try {
//            recommendSchemeAPI.operateAudit(id, operateSuggest, operateAudit);
//            return new ActResult("审核成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 总经办意见
//     *
//     * @param id             推荐方案
//     * @param generalSuggest 意见
//     * @param generalAudit   结果
//     * @version v1
//     */
//    @LoginAuth
//    @PutMapping("v1/generalAudit/{id}")
//    public Result generalAudit(@PathVariable String id, @RequestParam String generalSuggest, @RequestParam Boolean generalAudit) throws ActException {
//        try {
//            recommendSchemeAPI.generalAudit(id, generalSuggest, generalAudit);
//            return new ActResult("审核成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 删除
     *
     * @param id 推荐方案id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recommendSchemeAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class RecommendSchemeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(RecommendSchemeDTO dto) throws ActException {
        try {
            List<RecommendSchemeVO> voList = BeanTransform.copyProperties(recommendSchemeAPI.pageList(dto), RecommendSchemeVO.class,true);
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
    public Result count(RecommendSchemeDTO dto) throws ActException {
        try {
            Long count = recommendSchemeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询推荐方案
     *
     * @param id Id
     * @return class RecommendSchemeVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecommendSchemeVO vo = BeanTransform.copyProperties(recommendSchemeAPI.findById(id), RecommendSchemeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有推荐岗位
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/position")
    public Result findPosition() throws ActException{
        try {
            Set<String> position = recommendSchemeAPI.findPosition();
            return ActResult.initialize(position);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



}