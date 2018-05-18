package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.FairChangeAPI;
import com.bjike.goddess.shareholdersmanage.bo.FairChangeBO;
import com.bjike.goddess.shareholdersmanage.dto.FairChangeDTO;
import com.bjike.goddess.shareholdersmanage.entity.FairChange;
import com.bjike.goddess.shareholdersmanage.to.FairChangeTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.vo.FairChangeVO;
import com.bjike.goddess.shareholdersmanage.vo.FairTypeAndPerVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公允值变动
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-29 05:36 ]
 * @Description: [ 公允值变动 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fairchange")
public class FairChangeAction {
    @Autowired
    private FairChangeAPI fairChangeAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = fairChangeAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param fairChangeDTO 公允值变动dto
     * @des 获取所有公允值变动总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FairChangeDTO fairChangeDTO) throws ActException {
        try {
            Long count = fairChangeAPI.countFair(fairChangeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个公允值变动
     *
     * @param id 公允值变动id
     * @return class FairChangeVO
     * @des 根据id获公允值变动
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            FairChangeVO fairChangeVO = BeanTransform.copyProperties(
                    fairChangeAPI.getOne(id), FairChangeVO.class);
            return ActResult.initialize(fairChangeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公允值变动列表
     *
     * @param fairChangeDTO 公允值变动dto
     * @return class FairChangeVO
     * @des 获取所有公允值变动
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findFair(FairChangeDTO fairChangeDTO, HttpServletRequest request) throws ActException {
        try {
            List<FairChangeVO> fairChangeVOS = BeanTransform.copyProperties(
                    fairChangeAPI.findList(fairChangeDTO), FairChangeVO.class, request);
            return ActResult.initialize(fairChangeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加公允值变动
     *
     * @param fairChangeTO 公允值变动to
     * @return class FairChangeVO
     * @des 添加公允值变动
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) FairChangeTO fairChangeTO, BindingResult result) throws ActException {
        try {
            FairChangeBO fairChangeBO = fairChangeAPI.save(fairChangeTO);
            return ActResult.initialize(BeanTransform.copyProperties(fairChangeBO, FairChangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑公允值变动
     *
     * @param fairChangeTO 公允值变动数据bo
     * @return class FairChangeVO
     * @des 编辑公允值变动
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) FairChangeTO fairChangeTO, BindingResult result) throws ActException {
        try {
            FairChangeBO fairChangeBO = fairChangeAPI.edit(fairChangeTO);
            return ActResult.initialize(BeanTransform.copyProperties(fairChangeBO, FairChangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个类型对应一个每股价格
     *
     * @return class FairChangeVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/fair/typeAndPer")
    public Result getFairTypeAndPer() throws ActException {
        try {
            List<FairTypeAndPerVO> fairTypeAndPerVOS = BeanTransform.copyProperties(
                    fairChangeAPI.typeAndPer(), FairTypeAndPerVO.class);
            return ActResult.initialize(fairTypeAndPerVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}