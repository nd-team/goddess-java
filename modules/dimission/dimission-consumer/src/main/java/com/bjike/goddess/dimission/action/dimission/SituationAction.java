package com.bjike.goddess.dimission.action.dimission;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.SituationAPI;
import com.bjike.goddess.dimission.dto.SituationDTO;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.SituationTO;
import com.bjike.goddess.dimission.vo.DimissionCollectVO;
import com.bjike.goddess.dimission.vo.SituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 离职办理节点情况
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("situation")
public class SituationAction {
    @Autowired
    private SituationAPI situationAPI;

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

            Boolean isHasPermission = situationAPI.guidePermission(guidePermissionTO);
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
     * @param to 工作交接传输对象
     * @return class SituationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SituationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(situationAPI.save(to), SituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 工作交接传输对象
     * @return class SituationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SituationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(situationAPI.update(to), SituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 工作交接数据id
     * @return class SituationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(situationAPI.delete(id), SituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 离职办理节点情况
     * @return class SituationVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SituationDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(situationAPI.list(dto), SituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取数据
     *
     * @param id id
     * @return class SituationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(situationAPI.findById(id), SituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 离职自离表里的姓名
     *
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getName() throws ActException {
        try {
            return ActResult.initialize(situationAPI.getName());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 判断是否是自离
     *
     * @version v1
     */
    @GetMapping("v1/isSelf")
    public Result isSelf(String name) throws ActException {
        try {
            return ActResult.initialize(situationAPI.isSelf(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 离职管理汇总表
     *
     * @return class DimissionCollectVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(String startTime, String endTime) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(situationAPI.collect(startTime, endTime), DimissionCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}