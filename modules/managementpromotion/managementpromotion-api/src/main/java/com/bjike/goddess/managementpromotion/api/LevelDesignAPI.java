package com.bjike.goddess.managementpromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.bo.LevelDesignBO;
import com.bjike.goddess.managementpromotion.dto.LevelDesignDTO;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.LevelDesignTO;

import java.util.List;
import java.util.Set;

/**
 * 管理分类等级设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:59 ]
 * @Description: [ 管理分类等级设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LevelDesignAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 管理分类等级设计信息
     * @return class LevelDesignBO
     * @throws SerException
     */
    default LevelDesignBO save(LevelDesignTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 管理分类等级设计id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 编辑
     *
     * @param to 管理分类等级设计信息
     * @throws SerException
     */
    default void edit(LevelDesignTO to) throws SerException {
    }

    /**
     * 管理分类等级设计列表
     *
     * @param dto 管理分类等级设计dto
     * @return class LevelDesignBO
     * @throws SerException
     */
    default List<LevelDesignBO> find(LevelDesignDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 管理分类等级设计id
     * @return class LevelDesignBO
     * @throws SerException
     */
    default LevelDesignBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找所有分类
     *
     * @return class String
     * @throws SerException
     */
    default Set<String> allClassifications() throws SerException {
        return null;
    }

    /**
     * 查找分类对应的所有管理方向
     *
     * @param classification 分类
     * @return class String
     * @throws SerException
     */
    default Set<String> allDirections(String classification) throws SerException {
        return null;
    }

    /**
     * 查找分类和管理方向对应的所有技能等级
     *
     * @param classification 分类
     * @param direction      管理方向
     * @return class String
     * @throws SerException
     */
    default Set<String> allSkillLevels(String classification, String direction) throws SerException {
        return null;
    }

    /**
     * 查找分类，管理方向和技能等级对应的档次
     *
     * @param classification 分类
     * @param direction      管理方向
     * @param skillLevel     技能等级
     * @return class String
     * @throws SerException
     */
    default String findGrade(String classification, String direction, String skillLevel) throws SerException {
        return null;
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long count(LevelDesignDTO dto) throws SerException {
        return null;
    }
}