package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.api.JoinInfoAPI;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.to.JoinInfoTO;
import com.bjike.goddess.workjoin.vo.JoinInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 交接资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("joininfo")
public class JoinInfoAction {
    @Autowired
    private JoinInfoAPI joinInfoAPI;

    /**
     * 交接资料列表总条数
     *
     * @param joinInfoDTO 交接资料dto
     * @des 获取所有交接资料总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(JoinInfoDTO joinInfoDTO) throws ActException {
        try {
            Long count = joinInfoAPI.countJoinInfo(joinInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个交接资料
     *
     * @param id
     * @return class JoinInfoVO
     * @des 获取一个交接资料
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            JoinInfoBO joinInfoBO = joinInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(joinInfoBO, JoinInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 交接资料列表
     *
     * @param joinInfoDTO 交接资料dto
     * @return class JoinInfoVO
     * @des 获取所有交接资料
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(JoinInfoDTO joinInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<JoinInfoVO> joinInfoVOS = BeanTransform.copyProperties
                    (joinInfoAPI.findListJoinInfo(joinInfoDTO), JoinInfoVO.class, request);
            return ActResult.initialize(joinInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加交接资料
     *
     * @param joinInfoTO 交接资料数据to
     * @return class JoinInfoVO
     * @des 添加交接资料
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) JoinInfoTO joinInfoTO, BindingResult bindingResult) throws ActException {
        try {
            JoinInfoBO joinInfoBO = joinInfoAPI.insertJoinInfo(joinInfoTO);
            return ActResult.initialize(joinInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑交接资料
     *
     * @param joinInfoTO 交接资料数据to
     * @return class JoinInfoVO
     * @des 编辑交接资料
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) JoinInfoTO joinInfoTO, BindingResult bindingResult) throws ActException {
        try {
            JoinInfoBO joinInfoBO = joinInfoAPI.editJoinInfo(joinInfoTO);
            return ActResult.initialize(joinInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除交接资料
     *
     * @param id 用户id
     * @des 根据用户id删除交接资料记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            joinInfoAPI.removeJoinInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            joinInfoAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 下载
     *
     * @version v1
     */
    @PostMapping("v1/download")
    public Result download() throws ActException {
        try {
            joinInfoAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}