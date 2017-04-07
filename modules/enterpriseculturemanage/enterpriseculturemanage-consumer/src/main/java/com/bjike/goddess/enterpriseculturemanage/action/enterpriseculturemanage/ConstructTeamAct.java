package com.bjike.goddess.enterpriseculturemanage.action.enterpriseculturemanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.api.ConstructTeamAPI;
import com.bjike.goddess.enterpriseculturemanage.dto.ConstructTeamDTO;
import com.bjike.goddess.enterpriseculturemanage.to.ConstructTeamTO;
import com.bjike.goddess.enterpriseculturemanage.vo.ConstructTeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 建设小组
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("enterpriseculturemanage/constructteam")
public class ConstructTeamAct {

    @Autowired
    private ConstructTeamAPI constructTeamAPI;
    /*
    *//**
     * 查询用户列表
     *
     * @version v1
     *//*
    @PostMapping("v1/findUserInfo")
    public Result findUserInfo() throws ActException {
        try {
            ConstructTeamVO vo = BeanTransform.copyProperties(constructTeamAPI.findUserInfo(), ConstructTeamVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }*/

    /**
     * 新增建设小组
     *
     * @param to 建设小组
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ConstructTeamTO to, BindingResult bindingResult) throws ActException {
        try {
            ConstructTeamVO vo = BeanTransform.copyProperties(constructTeamAPI.addModel(to), ConstructTeamVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑建设小组
     *
     * @param to 建设小组
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ConstructTeamTO to, BindingResult bindingResult) throws ActException {
        try {
            ConstructTeamVO vo = BeanTransform.copyProperties(constructTeamAPI.editModel(to), ConstructTeamVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除建设小组
     *
     * @param id 建设小组id
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            constructTeamAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ConstructTeamDTO dto) throws ActException {
        try {
            List<ConstructTeamVO> voList = BeanTransform.copyProperties(constructTeamAPI.pageList(dto), ConstructTeamVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}