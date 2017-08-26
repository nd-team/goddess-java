package com.bjike.goddess.staffwelfare.action.staffwelfare;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.api.ThankStatementAPI;
import com.bjike.goddess.staffwelfare.bo.ThankStatementBO;
import com.bjike.goddess.staffwelfare.dto.ThankStatementDTO;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.ThankStatementTO;
import com.bjike.goddess.staffwelfare.vo.ThankStatementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 感谢语
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 09:14 ]
 * @Description: [ 感谢语 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("thankstatement")
public class ThankStatementAct {

    @Autowired
    private ThankStatementAPI thankStatementAPI;

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

            Boolean isHasPermission = thankStatementAPI.guidePermission(guidePermissionTO);
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
     * 新增感谢语
     *
     * @param to 感谢语
     * @return class ThankStatementVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ThankStatementTO to, BindingResult bindingResult) throws ActException {
        try {
            ThankStatementVO vo = BeanTransform.copyProperties(thankStatementAPI.addModel(to), ThankStatementVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑感谢语
     *
     * @param to 感谢语
     * @return class ThankStatementVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ThankStatementTO to, BindingResult bindingResult) throws ActException {
        try {
            ThankStatementVO vo = BeanTransform.copyProperties(thankStatementAPI.editModel(to), ThankStatementVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结感谢语
     *
     * @param id 感谢语id
     * @version v1
     */
    @GetMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            thankStatementAPI.freeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻感谢语
     *
     * @param id 感谢语id
     * @version v1
     */
    @GetMapping("v1/breakFreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            thankStatementAPI.breakFreeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除感谢语
     *
     * @param id 感谢语id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            thankStatementAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 感谢语分页查询
     *
     * @param dto 分页条件
     * @return class ThankStatementVO
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ThankStatementDTO dto) throws ActException {
        try {
            List<ThankStatementVO> voList = BeanTransform.copyProperties(thankStatementAPI.pageList(dto), ThankStatementVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @param dto 条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ThankStatementDTO dto) throws ActException{
        try {
            Long count = thankStatementAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     * @param id 条件
     * @return class ThankStatementVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(String id) throws ActException{
        try {
            ThankStatementBO bo = thankStatementAPI.findOne(id);
            ThankStatementVO vo = BeanTransform.copyProperties(bo,ThankStatementVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
}