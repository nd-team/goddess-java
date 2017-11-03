package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.DifficultyFoactorSetBO;
import com.bjike.goddess.customer.dto.DifficultyFoactorSetDTO;
import com.bjike.goddess.customer.service.DifficultyFoactorSetSer;
import com.bjike.goddess.customer.to.DifficultyFoactorSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 难易度因素层设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:18 ]
 * @Description: [ 难易度因素层设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("difficultyFoactorSetApiImpl")
public class DifficultyFoactorSetApiImpl implements DifficultyFoactorSetAPI {
    @Autowired
    private DifficultyFoactorSetSer difficultyFoactorSetSer;
    @Override
    public Long countDifficulty(DifficultyFoactorSetDTO difficultyFoactorSetDTO) throws SerException {
        return difficultyFoactorSetSer.countDifficulty(difficultyFoactorSetDTO);
    }

    @Override
    public DifficultyFoactorSetBO getOneDifficulty(String id) throws SerException {
        return difficultyFoactorSetSer.getOneDifficulty(id);
    }

    @Override
    public List<DifficultyFoactorSetBO> listDifficulty(DifficultyFoactorSetDTO difficultyFoactorSetDTO) throws SerException {
        return difficultyFoactorSetSer.listDifficulty(difficultyFoactorSetDTO);
    }

    @Override
    public DifficultyFoactorSetBO addDifficulty(DifficultyFoactorSetTO difficultyFoactorSetTO) throws SerException {
        return difficultyFoactorSetSer.addDifficulty(difficultyFoactorSetTO);
    }

    @Override
    public DifficultyFoactorSetBO editDifficulty(DifficultyFoactorSetTO difficultyFoactorSetTO) throws SerException {
        return difficultyFoactorSetSer.editDifficulty(difficultyFoactorSetTO);
    }

    @Override
    public void deleteDifficulty(String id) throws SerException {
        difficultyFoactorSetSer.deleteDifficulty(id);
    }
}