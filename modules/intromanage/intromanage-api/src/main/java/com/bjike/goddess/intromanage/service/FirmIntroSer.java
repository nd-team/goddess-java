package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.entity.FirmIntro;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;

import java.util.List;

/**
 * 公司简介业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirmIntroSer extends Ser<FirmIntro, FirmIntroDTO> {

    /**
     * 分页查询公司简介
     *
     * @return class FirmIntroBO
     * @throws SerException
     */
    List<FirmIntroBO> list(FirmIntroDTO dto) throws SerException;

    /**
     * 保存公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroBO
     * @throws SerException
     */
    FirmIntroBO save(FirmIntroTO to) throws SerException;

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新公司简介
     *
     * @param to 公司简介to
     * @throws SerException
     */
    void update(FirmIntroTO to) throws SerException;

}