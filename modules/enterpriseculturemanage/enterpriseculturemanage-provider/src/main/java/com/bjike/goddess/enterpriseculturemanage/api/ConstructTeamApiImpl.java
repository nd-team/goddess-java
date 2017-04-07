package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.enterpriseculturemanage.bo.ConstructTeamBO;
import com.bjike.goddess.enterpriseculturemanage.dto.ConstructTeamDTO;
import com.bjike.goddess.enterpriseculturemanage.service.ConstructTeamSer;
import com.bjike.goddess.enterpriseculturemanage.to.ConstructTeamTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 建设小组业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-31 03:33 ]
 * @Description: [ 建设小组业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("constructTeamApiImpl")
public class ConstructTeamApiImpl implements ConstructTeamAPI {

    @Autowired
    private ConstructTeamSer constructTeamSer;

    @Override
    public ConstructTeamBO addModel(ConstructTeamTO to) throws SerException {
        return constructTeamSer.insertModel(to);
    }

    @Override
    public ConstructTeamBO editModel(ConstructTeamTO to) throws SerException {
        return constructTeamSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        constructTeamSer.remove(id);
    }

    @Override
    public List<ConstructTeamBO> pageList(ConstructTeamDTO dto) throws SerException {
        return constructTeamSer.pageList(dto);
    }
}