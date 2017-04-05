package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.to.CommonalityTO;

import java.util.List;

/**
 * 公共邮箱管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommonalityAPI {

    /**
     * 保存
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO save(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO update(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO delete(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO congeal(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param to 公共邮箱管理传输对象
     * @return
     * @throws SerException
     */
    default CommonalityBO thaw(CommonalityTO to) throws SerException {
        return null;
    }

    /**
     * 查询未冻结的公共邮箱
     *
     * @return
     * @throws SerException
     */
    default List<CommonalityBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 列表查询
     *
     * @param dto 公共邮箱管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<CommonalityBO> maps(CommonalityDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据部门ID查询公共邮箱
     *
     * @param department 部门ID
     * @return
     * @throws SerException
     */
    default CommonalityBO findByDepartment(String department) throws SerException {
        return null;
    }

}