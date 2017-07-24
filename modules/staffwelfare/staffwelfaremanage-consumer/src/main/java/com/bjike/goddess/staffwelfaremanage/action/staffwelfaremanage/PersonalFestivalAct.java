package com.bjike.goddess.staffwelfaremanage.action.staffwelfaremanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.api.PersonalFestivalAPI;
import com.bjike.goddess.staffwelfaremanage.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.PersonalFestivalTO;
import com.bjike.goddess.staffwelfaremanage.vo.PersonalFestivalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 个人节日
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("personalfestival")
public class PersonalFestivalAct {

    @Autowired
    private PersonalFestivalAPI personalFestivalAPI;

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

            Boolean isHasPermission = personalFestivalAPI.guidePermission(guidePermissionTO);
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
     * 新增个人节日
     *
     * @param to 个人节日
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(PersonalFestivalTO to, BindingResult bindingResult) throws ActException {
        try {
            PersonalFestivalVO vo = BeanTransform.copyProperties(personalFestivalAPI.addModel(to), PersonalFestivalVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑个人节日
     *
     * @param to 个人节日
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(PersonalFestivalTO to, BindingResult bindingResult) throws ActException {
        try {
            PersonalFestivalVO vo = BeanTransform.copyProperties(personalFestivalAPI.editModel(to), PersonalFestivalVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除个人节日
     *
     * @param id 个人节日id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            personalFestivalAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人节日分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(PersonalFestivalDTO dto) throws ActException {
        try {
            List<PersonalFestivalVO> voList = BeanTransform.copyProperties(personalFestivalAPI.pageList(dto), PersonalFestivalVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一声祝福
     *
     * @param id 个人节日id
     * @param wishStatement 祝福语
     * @version v1
     */
    @GetMapping("v1/wish")
    public Result wish(String id,String wishStatement) throws ActException {
        try {
            personalFestivalAPI.wish(id,wishStatement);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}