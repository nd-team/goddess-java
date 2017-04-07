package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommerceMemberBO;
import com.bjike.goddess.contacts.dto.CommerceMemberDTO;
import com.bjike.goddess.contacts.to.CommerceMemberTO;

import java.util.List;

/**
 * 商务会员卡业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommerceMemberAPI {

    /**
     * 保存
     *
     * @param to 商务会员卡传输对象
     * @return
     * @throws SerException
     */
    default CommerceMemberBO save(CommerceMemberTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 商务会员卡传输对象
     * @return
     * @throws SerException
     */
    default CommerceMemberBO update(CommerceMemberTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 商务会员卡传输对象
     * @return
     * @throws SerException
     */
    default CommerceMemberBO delete(CommerceMemberTO to) throws SerException {
        return null;
    }

    /**
     * 列表查询
     *
     * @param dto 商务会员卡数据传输对象
     * @return
     * @throws SerException
     */
    default List<CommerceMemberBO> maps(CommerceMemberDTO dto) throws SerException {
        return null;
    }

}