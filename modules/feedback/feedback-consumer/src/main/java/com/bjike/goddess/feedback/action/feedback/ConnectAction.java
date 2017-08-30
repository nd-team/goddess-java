package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.ConnectAPI;
import com.bjike.goddess.feedback.bo.ConnectBO;
import com.bjike.goddess.feedback.bo.RelevancyDetailBO;
import com.bjike.goddess.feedback.dto.ConnectDTO;
import com.bjike.goddess.feedback.dto.RelevancyDetailDTO;
import com.bjike.goddess.feedback.to.ConnectTO;
import com.bjike.goddess.feedback.to.RelevancyDetailTO;
import com.bjike.goddess.feedback.vo.ConnectVO;
import com.bjike.goddess.feedback.vo.RelevancyDetailVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 各类沟通模板
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("connect")
public class ConnectAction {
    @Autowired
    private ConnectAPI connectAPI;
    /**
     * 各类沟通模板列表总条数
     *
     * @param dto 各类沟通模板dto
     * @des 获取所有各类沟通模板总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ConnectDTO dto) throws ActException {
        try {
            Long count = connectAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个各类沟通模板
     *
     * @param id
     * @return class ConnectVO
     * @des 获取一个各类沟通模板
     * @version v1
     */
    @GetMapping("v1/connect/{id}")
    public Result connect(@PathVariable String id) throws ActException {
        try {
            ConnectBO bo = connectAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ConnectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 各类沟通模板列表
     *
     * @param dto 各类沟通模板dto
     * @return class ConnectVO
     * @des 获取所有各类沟通模板
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ConnectDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ConnectVO> connectVOS = BeanTransform.copyProperties
                    (connectAPI.list(dto), ConnectVO.class, request);
            return ActResult.initialize(connectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 添加各类沟通模板
//     *
//     * @param to 各类沟通模板数据to
//     * @return class ConnectVO
//     * @des 添加各类沟通模板
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(ADD.class) ConnectTO to, BindingResult bindingResult) throws ActException {
//        try {
//            ConnectBO connectBO = connectAPI.insert(to);
//            return ActResult.initialize(BeanTransform.copyProperties(connectBO,ConnectVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 编辑各类沟通模板
     *
     * @param to 各类沟通模板数据to
     * @return class ConnectVO
     * @des 编辑各类沟通模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(ConnectTO.TestEdit.class) ConnectTO to, BindingResult bindingResult) throws ActException {
        try {
            ConnectBO connectBO = connectAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(connectBO,ConnectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//    /**
//     * 根据分类获取各类沟通模板
//     *
//     * @param sorting
//     * @return class ConnectVO
//     * @des 根据分类获取各类沟通模板
//     * @version v1
//     */
//    @PostMapping("v1/sorting")
//    public Result sorting(String sorting) throws ActException {
//        try {
//            List<ConnectVO> connectVOS = BeanTransform.copyProperties(connectAPI.getConnect(sorting),ConnectVO.class);
//            return ActResult.initialize(connectVOS);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 获取所有分类
//     *
//     * @des 获取所有分类
//     * @version v1
//     */
//    @PostMapping("v1/getSorting")
//    public Result getSorting() throws ActException {
//        try {
//            List<String> sorting = connectAPI.getSort();
//            return ActResult.initialize(sorting);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

//    /**
//     * 删除各类沟通模板
//     *
//     * @param id 用户id
//     * @des 根据用户id删除各类沟通模板记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            connectAPI.remove(id);
//            return new ActResult("delete success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}