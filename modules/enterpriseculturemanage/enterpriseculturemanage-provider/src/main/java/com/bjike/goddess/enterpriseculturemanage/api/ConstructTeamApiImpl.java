package com.bjike.goddess.enterpriseculturemanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.enterpriseculturemanage.bo.ConstructTeamBO;
import com.bjike.goddess.enterpriseculturemanage.dto.ConstructTeamDTO;
import com.bjike.goddess.enterpriseculturemanage.excel.SonPermissionObject;
import com.bjike.goddess.enterpriseculturemanage.service.ConstructTeamSer;
import com.bjike.goddess.enterpriseculturemanage.to.ConstructTeamTO;
import com.bjike.goddess.enterpriseculturemanage.to.GuidePermissionTO;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return constructTeamSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return constructTeamSer.guidePermission(guidePermissionTO);
    }

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

    @Override
    public ConstructTeamBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(constructTeamSer.findById(id),ConstructTeamBO.class);
    }

    @Override
    public Long count(ConstructTeamDTO dto) throws SerException {
        return constructTeamSer.count(dto);
    }
}