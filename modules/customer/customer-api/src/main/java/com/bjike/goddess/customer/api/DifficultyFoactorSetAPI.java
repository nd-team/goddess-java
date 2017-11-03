package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.DifficultyFoactorSetBO;
import com.bjike.goddess.customer.dto.DifficultyFoactorSetDTO;
import com.bjike.goddess.customer.to.DifficultyFoactorSetTO;

import java.util.List;

/**
 * 难易度因素层设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:18 ]
 * @Description: [ 难易度因素层设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DifficultyFoactorSetAPI {
    /**
     * 难易度因素层设置总条数
     */
    default Long countDifficulty(DifficultyFoactorSetDTO difficultyFoactorSetDTO) throws SerException {
        return null;
    }

    /**
     * 一个难易度因素层设置
     */
    default DifficultyFoactorSetBO getOneDifficulty(String id) throws SerException {
        return null;
    }

    /**
     * 难易度因素层设置列表
     *
     * @return class DifficultyFoactorSetBO
     */
    default List<DifficultyFoactorSetBO> listDifficulty(DifficultyFoactorSetDTO difficultyFoactorSetDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param difficultyFoactorSetTO 难易度因素层设置
     * @return class DifficultyFoactorSetBO
     */
    default DifficultyFoactorSetBO addDifficulty(DifficultyFoactorSetTO difficultyFoactorSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param difficultyFoactorSetTO 难易度因素层设置
     * @return class DifficultyFoactorSetBO
     */
    default DifficultyFoactorSetBO editDifficulty(DifficultyFoactorSetTO difficultyFoactorSetTO) throws SerException {
        return null;
    }

    /**
     * 删除难易度因素层设置
     *
     * @param id id
     */
    default void deleteDifficulty(String id) throws SerException {
        return;
    }
}