package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.dto.ManagementScoreDTO;
import com.bjike.goddess.regularization.service.ManagementScoreSer;
import com.bjike.goddess.regularization.to.ManagementScoreTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理层评分业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-17 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("managementScoreApiImpl")
public class ManagementScoreApiImpl implements ManagementScoreAPI {

    @Autowired
    private ManagementScoreSer managementScoreSer;

    /**
     * 分页查询管理层评分
     *
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public List<ManagementScoreBO> list(ManagementScoreDTO dto) throws SerException {
        return managementScoreSer.list(dto);
    }

    /**
     * 保存管理层评分
     *
     * @param to 管理层评分to
     * @return class ManagementScoreBO
     * @throws SerException
     */
    @Override
    public ManagementScoreBO save(ManagementScoreTO to) throws SerException {
        return managementScoreSer.save(to);
    }

    /**
     * 根据id删除管理层评分
     *
     * @param id 管理层评分唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        managementScoreSer.remove(id);
    }

    /**
     * 更新管理层评分
     *
     * @param to 管理层评分to
     * @throws SerException
     */
    @Override
    public void update(ManagementScoreTO to) throws SerException {
        managementScoreSer.update(to);
    }
}