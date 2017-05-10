package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.BeforeAddAPI;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.secure.vo.BeforeAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 增员前
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("beforeadd")
public class BeforeAddAct {
    @Autowired
    private BeforeAddAPI beforeAddAPI;

    /**
     * 添加
     *
     * @param to      增员前信息
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(BeforeAddTO to, HttpServletRequest request) throws ActException {
        try {
            BeforeAddBO bo = beforeAddAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补全信息和确认是否购买
     *
     * @param to      增员前信息
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/completeAndConfirm")
    public Result completeAndConfirm(@Validated({EDIT.class}) BeforeAddTO to, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            BeforeAddBO bo = beforeAddAPI.completeAndConfirm(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     增员前分页信息
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BeforeAddDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BeforeAddBO> bo = beforeAddAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      增员前id
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/beforeadd/{id}")
    public Result removeadd(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BeforeAddBO bo = beforeAddAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 增员前id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            beforeAddAPI.delete(id);
            return new ActResult("删除成功！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}