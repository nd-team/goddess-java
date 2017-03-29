package com.bjike.goddess.annual.api;

import com.bjike.goddess.annual.bo.AnnualStandardBO;
import com.bjike.goddess.annual.to.AnnualStandardTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 年假标准业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnnualStandardAPI {

    /**
     * 保存年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO save(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 修改年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO update(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 删除年假表尊实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO delete(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 冻结年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO congeal(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 解冻年假标准实体数据
     *
     * @param to 年假标准传输对象
     * @return
     * @throws SerException
     */
    default AnnualStandardBO thaw(AnnualStandardTO to) throws SerException {
        return null;
    }

    /**
     * 查询正常状态的年假标准
     *
     * @return
     * @throws SerException
     */
    default List<AnnualStandardBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 根据工龄获取年假标准
     *
     * @param seniority 工龄
     * @return
     * @throws SerException
     */
    default AnnualStandardBO findBySeniority(Integer seniority) throws SerException {
        return null;
    }

}