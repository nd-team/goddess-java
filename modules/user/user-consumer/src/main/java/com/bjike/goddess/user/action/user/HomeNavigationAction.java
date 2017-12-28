package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.HomeNavigationAPI;
import com.bjike.goddess.user.bo.HomeNavigationBO;
import com.bjike.goddess.user.dto.HomeNavigationDTO;
import com.bjike.goddess.user.entity.HomeNavigation;
import com.bjike.goddess.user.to.HomeNavigationTO;
import com.bjike.goddess.user.to.NavigationTO;
import com.bjike.goddess.user.vo.HomeNavigationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页导航
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("homenavigation")
public class HomeNavigationAction {
    @Autowired
    private HomeNavigationAPI homeNavigationAPI;
    /**
     * 列表
     *
     * @param dto 首页导航数据传输
     * @return class HomeNavigationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(HomeNavigationDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<HomeNavigationBO> list = homeNavigationAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, HomeNavigationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 首页导航传输对象
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) HomeNavigationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            homeNavigationAPI.save(to);
            return new  ActResult("save success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 首页导航传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(ADD.class) HomeNavigationTO to, BindingResult result) throws ActException {
        try {
            homeNavigationAPI.save(to);
            return new  ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 首页导航id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            homeNavigationAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 首页导航数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HomeNavigationDTO dto) throws ActException {
        try {
            return ActResult.initialize(homeNavigationAPI.countHome(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑排序
     *
     * @param navigationTO 首页导航传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit/order")
    public Result editOrder(@Validated(HomeNavigationTO.testOrder.class) NavigationTO navigationTO, BindingResult result) throws ActException {
        try {
            homeNavigationAPI.editOrder(navigationTO);
            return new  ActResult("editOrder success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}