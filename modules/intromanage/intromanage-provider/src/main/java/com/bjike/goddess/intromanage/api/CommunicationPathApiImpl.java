package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.CommunicationPathBO;
import com.bjike.goddess.intromanage.dto.CommunicationPathDTO;
import com.bjike.goddess.intromanage.entity.CommunicationPath;
import com.bjike.goddess.intromanage.service.CommunicationPathSer;
import com.bjike.goddess.intromanage.to.CommunicationPathTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 通讯途径业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("communicationPathApiImpl")
public class CommunicationPathApiImpl implements CommunicationPathAPI {

    @Autowired
    private CommunicationPathSer communicationPathSer;

    /**
     * 根据id查询通讯途径
     *
     * @param id 通讯途径唯一标识
     * @return class CommunicationPathBO
     * @throws SerException
     */
    @Override
    public CommunicationPathBO findById(String id) throws SerException {
        CommunicationPath model = communicationPathSer.findById(id);
        return BeanTransform.copyProperties(model, CommunicationPathBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 通讯途径dto
     * @throws SerException
     */
    @Override
    public Long count(CommunicationPathDTO dto) throws SerException {
        return communicationPathSer.count(dto);
    }

    /**
     * 分页查询通讯途径
     *
     * @return class CommunicationPathBO
     * @throws SerException
     */
    @Override
    public List<CommunicationPathBO> list(CommunicationPathDTO dto) throws SerException {
        return communicationPathSer.list(dto);
    }

    /**
     * 保存通讯途径
     *
     * @param to 通讯途径to
     * @return class CommunicationPathBO
     * @throws SerException
     */
    @Override
    public CommunicationPathBO save(CommunicationPathTO to) throws SerException {
        return communicationPathSer.save(to);
    }

    /**
     * 根据id删除通讯途径
     *
     * @param id 通讯途径唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        communicationPathSer.remove(id);
    }

    /**
     * 更新通讯途径
     *
     * @param to 通讯途径to
     * @throws SerException
     */
    @Override
    public void update(CommunicationPathTO to) throws SerException {
        communicationPathSer.update(to);
    }

    @Override
    public Set<String> address() throws SerException {
        return communicationPathSer.address();
    }
}