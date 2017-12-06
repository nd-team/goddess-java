package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.projectroyalty.api.CompletionTimeAPI;
import com.bjike.goddess.projectroyalty.dto.CompletionTimeDTO;
import com.bjike.goddess.projectroyalty.to.CompletionTimeTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.vo.CompletionTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 完工时间
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:44 ]
 * @Description: [ 完工时间 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("completiontime")
public class CompletionTimeAction {

    @Autowired
    private CompletionTimeAPI completionTimeAPI;

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

            Boolean isHasPermission = completionTimeAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 完工时间传输对象
     * @return class CompletionTimeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CompletionTimeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(completionTimeAPI.save(to), CompletionTimeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 完工时间传输对象
     * @return class CompletionTimeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CompletionTimeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(completionTimeAPI.update(to), CompletionTimeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 完工时间数据id
     * @return class CompletionTimeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(completionTimeAPI.delete(id), CompletionTimeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取完工时间数据
     *
     * @param id 完工时间数据id
     * @return class CompletionTimeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(completionTimeAPI.getById(id), CompletionTimeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取选项
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/findOpinion")
    public Result findOpinion() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(completionTimeAPI.findOpinion(), OpinionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 完工时间数据传输对象
     * @return class CompletionTimeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CompletionTimeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(completionTimeAPI.maps(dto), CompletionTimeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(completionTimeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据完工时间获取重要性
     *
     * @version v1
     */
    @GetMapping("v1/findImportain")
    public Result findImportain(@RequestParam Integer month) throws ActException {
        try {
            return ActResult.initialize(completionTimeAPI.findImportain(month));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}