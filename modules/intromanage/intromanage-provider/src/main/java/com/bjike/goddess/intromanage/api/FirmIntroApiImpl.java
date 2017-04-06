package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.service.FirmIntroSer;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司简介业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("firmIntroApiImpl")
public class FirmIntroApiImpl implements FirmIntroAPI {

    @Autowired
    private FirmIntroSer firmIntroSer;

    /**
     * 分页查询公司简介
     *
     * @return class FirmIntroBO
     * @throws SerException
     */
    @Override
    public List<FirmIntroBO> list(FirmIntroDTO dto) throws SerException {
        return firmIntroSer.list(dto);
    }

    /**
     * 保存公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroBO
     * @throws SerException
     */
    @Override
    public FirmIntroBO save(FirmIntroTO to) throws SerException {
        return firmIntroSer.save(to);
    }

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        firmIntroSer.remove(id);
    }

    /**
     * 更新公司简介
     *
     * @param to 公司简介to
     * @throws SerException
     */
    @Override
    public void update(FirmIntroTO to) throws SerException {
        firmIntroSer.update(to);
    }
}