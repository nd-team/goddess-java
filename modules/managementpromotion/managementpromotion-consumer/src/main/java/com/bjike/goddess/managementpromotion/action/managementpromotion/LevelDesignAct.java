package com.bjike.goddess.managementpromotion.action.managementpromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.api.GradeLevelAPI;
import com.bjike.goddess.managementpromotion.api.LevelDesignAPI;
import com.bjike.goddess.managementpromotion.bo.LevelDesignBO;
import com.bjike.goddess.managementpromotion.dto.LevelDesignDTO;
import com.bjike.goddess.managementpromotion.to.LevelDesignTO;
import com.bjike.goddess.managementpromotion.vo.LevelDesignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 管理分类等级设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:59 ]
 * @Description: [ 管理分类等级设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("leveldesign")
public class LevelDesignAct {
    @Autowired
    private LevelDesignAPI levelDesignAPI;
    @Autowired
    private GradeLevelAPI gradeLevelAPI;

    /**
     * 查找所有体系部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allDepartments")
    public Result allDepartments() throws ActException {
        try {
            Set<String> set = gradeLevelAPI.allDepartments();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理分类等级设计列表总条数
     *
     * @param dto 管理分类等级设计dto
     * @des 获取所有管理分类等级设计总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LevelDesignDTO dto) throws ActException {
        try {
            Long count = levelDesignAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个管理分类等级设计
     *
     * @param id id
     * @return class LevelDesignVO
     * @des 获取一个管理分类等级设计
     * @version v1
     */
    @GetMapping("v1/leveldesign/{id}")
    public Result leveldesign(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            LevelDesignBO bo = levelDesignAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, LevelDesignVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理分类等级设计列表
     *
     * @param dto 管理分类等级设计信息dto
     * @return class LevelDesignVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(LevelDesignDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<LevelDesignVO> VOS = BeanTransform.copyProperties
                    (levelDesignAPI.find(dto), LevelDesignVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加管理分类等级设计信息
     *
     * @param to 管理分类等级设计信息数据to
     * @return class LevelDesignVO
     * @version v1
     */
 //   @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) LevelDesignTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            LevelDesignBO bo = levelDesignAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, LevelDesignVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑管理分类等级设计信息
     *
     * @param to 管理分类等级设计信息数据to
     * @return class LevelDesignVO
     * @des 编辑管理分类等级设计信息
     * @version v1
     */
  //  @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) LevelDesignTO to, BindingResult bindingResult) throws ActException {
        try {
            levelDesignAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除管理分类等级设计信息
     *
     * @param id 用户id
     * @des 根据用户id删除管理分类等级设计信息记录
     * @version v1
     */
  //  @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            levelDesignAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有分类
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allClassifications")
    public Result allClassifications() throws ActException {
        try {
            Set<String> set = levelDesignAPI.allClassifications();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找分类对应的所有管理方向
     *
     * @param classification 分类
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allDirections/{classification}")
    public Result allDirections(@PathVariable String classification) throws ActException {
        try {
            Set<String> set = levelDesignAPI.allDirections(classification);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找分类和管理方向对应的所有技能等级
     *
     * @param classification 分类
     * @param direction      管理方向
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allSkillLevels/{classification}/{direction}")
    public Result allSkillLevels(@PathVariable String classification, @PathVariable String direction) throws ActException {
        try {
            Set<String> set = levelDesignAPI.allSkillLevels(classification, direction);
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找分类，管理方向和技能等级对应的档次
     *
     * @param classification 分类
     * @param direction      管理方向
     * @param skillLevel     技能等级
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findGrade/{classification}/{direction}/{skillLevel}")
    public Result findGrade(@PathVariable String classification, @PathVariable String direction, @PathVariable String skillLevel) throws ActException {
        try {
            String s = levelDesignAPI.findGrade(classification, direction, skillLevel);
            return ActResult.initialize(s);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}