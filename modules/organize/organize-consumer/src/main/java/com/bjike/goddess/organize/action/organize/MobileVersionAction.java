package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.MobileVersionAPI;
import com.bjike.goddess.organize.bo.MobileVersionBO;
import com.bjike.goddess.organize.dto.MobileVersionDTO;
import com.bjike.goddess.organize.to.MobileVersionTO;
import com.bjike.goddess.organize.vo.MobileVersionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 移动端版本
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-07 01:54 ]
 * @Description: [ 移动端版本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("mobileversion")
public class MobileVersionAction {
    @Autowired
    private MobileVersionAPI mobileVersionAPI;
    /**
     * 移动端版本列表总条数
     *
     * @param dto 移动端版本dto
     * @des 获取所有移动端版本
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MobileVersionDTO dto) throws ActException {
        try {
            Long count = mobileVersionAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个移动端版本
     *
     * @param id
     * @return class MobileVersionVO
     * @des 获取一个移动端版本
     * @version v1
     */
    @GetMapping("v1/mobile/{id}")
    public Result mobile(@PathVariable String id) throws ActException {
        try {
            MobileVersionBO mobileVersionBO = mobileVersionAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(mobileVersionBO, MobileVersionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 移动端版本列表
     *
     * @param dto 移动端版本dto
     * @return class MobileVersionVO
     * @des 获取所有移动端版本
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MobileVersionDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MobileVersionVO> mobileVersionVOS = BeanTransform.copyProperties
                    (mobileVersionAPI.list(dto), MobileVersionVO.class, request);
            return ActResult.initialize(mobileVersionVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加移动端版本
     *
     * @param to 移动端版本数据to
     * @return class MobileVersionVO
     * @des 添加移动端版本
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add( MobileVersionTO to, BindingResult bindingResult) throws ActException {
        try {
            MobileVersionBO mobileVersionBO = mobileVersionAPI.insert(to);
            return ActResult.initialize(mobileVersionBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑移动端版本
     *
     * @param to 移动端版本数据to
     * @return class MobileVersionVO
     * @des 编辑移动端版本
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(MobileVersionTO to, BindingResult bindingResult) throws ActException {
        try {
            MobileVersionBO careerPlanningCustomBO = mobileVersionAPI.edit(to);
            return ActResult.initialize(careerPlanningCustomBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除移动端版本
     *
     * @param id 用户id
     * @des 根据用户id删除移动端版本记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            mobileVersionAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}