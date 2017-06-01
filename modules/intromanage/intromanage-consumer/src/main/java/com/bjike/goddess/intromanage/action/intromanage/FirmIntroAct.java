package com.bjike.goddess.intromanage.action.intromanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.api.FirmIntroAPI;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.to.FirmDisplayFieldTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import com.bjike.goddess.intromanage.vo.FirmIntroVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("firmintro")
public class FirmIntroAct {

    @Autowired
    private FirmIntroAPI firmIntroAPI;

    /**
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/firmintro/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.findById(id);
            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 公司简介dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FirmIntroDTO dto, BindingResult result) throws ActException {
        try {
            Long count = firmIntroAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询公司简介
     *
     * @param dto 公司简介dto
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FirmIntroDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FirmIntroBO> boList = firmIntroAPI.list(dto);
            List<FirmIntroVO> voList = BeanTransform.copyProperties(boList, FirmIntroVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FirmIntroTO to, HttpServletRequest request) throws ActException {
        try {
            FirmIntroBO bo = firmIntroAPI.save(to);
            FirmIntroVO vo = BeanTransform.copyProperties(bo, FirmIntroVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firmIntroAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑公司简介
     *
     * @param to 公司简介to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(FirmIntroTO to) throws ActException {
        try {
            firmIntroAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置公司简介显示的字段
     *
     * @param username 用户姓名数组
     * @param to       公司简介显示字段to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/setFirmDisplayField")
    public Result setFirmDisplayField(String[] username, FirmDisplayFieldTO to) throws ActException {
        try {
            firmIntroAPI.setFirmDisplayField(username, to);
            return new ActResult("setFirmDisplayField success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}