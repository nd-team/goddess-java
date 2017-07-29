package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.entity.FirmIntro;
import com.bjike.goddess.intromanage.service.FirmIntroSer;
import com.bjike.goddess.intromanage.to.FirmDisplayFieldTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;
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

    @Override
    public Boolean sonPermission() throws SerException {
        return firmIntroSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return firmIntroSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntroBO
     * @throws SerException
     */
    @Override
    public FirmIntroBO findById(String id) throws SerException {
        return firmIntroSer.findByFirmId(id);
    }

    /**
     * 计算总条数
     *
     * @param dto 公司简介dto
     * @throws SerException
     */
    @Override
    public Long count(FirmIntroDTO dto) throws SerException {
        return firmIntroSer.count(dto);
    }

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

    /**
     * 设置哪些用户可以查看哪些字段
     *
     * @param username 用户名集合
     * @param to       公司简介需要显示的字段
     * @throws SerException
     */
    @Override
    public void setFirmDisplayField(String[] username, FirmDisplayFieldTO to) throws SerException {
        firmIntroSer.setFirmDisplayField(username, to);
    }
}