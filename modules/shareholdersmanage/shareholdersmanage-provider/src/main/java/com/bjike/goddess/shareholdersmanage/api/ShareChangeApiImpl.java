package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareChangeBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareChangeDTO;
import com.bjike.goddess.shareholdersmanage.service.ShareChangeSer;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareChangeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股东变更业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("shareChangeApiImpl")
public class ShareChangeApiImpl implements ShareChangeAPI {
    @Autowired
    private ShareChangeSer shareChangeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return shareChangeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return shareChangeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countShareChange(ShareChangeDTO shareChangeDTO) throws SerException {
        return shareChangeSer.countShareChange(shareChangeDTO);
    }

    @Override
    public ShareChangeBO getOne(String id) throws SerException {
        return shareChangeSer.getOne(id);
    }

    @Override
    public List<ShareChangeBO> findList(ShareChangeDTO shareChangeDTO) throws SerException {
        return shareChangeSer.findList(shareChangeDTO);
    }

    @Override
    public ShareChangeBO save(ShareChangeTO shareChangeTO) throws SerException {
        return shareChangeSer.save(shareChangeTO);
    }

    @Override
    public ShareChangeBO edit(ShareChangeTO shareChangeTO) throws SerException {
        return shareChangeSer.edit(shareChangeTO);
    }

    @Override
    public void delete(String id) throws SerException {
        shareChangeSer.delete(id);
    }
}