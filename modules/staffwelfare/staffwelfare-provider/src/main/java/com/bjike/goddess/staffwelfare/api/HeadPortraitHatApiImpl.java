package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfare.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfare.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfare.service.HeadPortraitHatSer;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.HeadPortraitHatTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 头像帽业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-06 04:00 ]
 * @Description: [ 头像帽业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("headPortraitHatApiImpl")
public class HeadPortraitHatApiImpl implements HeadPortraitHatAPI {


    @Autowired
    private HeadPortraitHatSer headPortraitHatSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return headPortraitHatSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return headPortraitHatSer.guidePermission(guidePermissionTO);
    }

    @Override
    public HeadPortraitHatBO addModel(HeadPortraitHatTO to) throws SerException {
        return headPortraitHatSer.insertModel(to);
    }

    @Override
    public HeadPortraitHatBO editModel(HeadPortraitHatTO to) throws SerException {
        return headPortraitHatSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        headPortraitHatSer.remove(id);
    }

    @Override
    public List<HeadPortraitHatBO> pageList(HeadPortraitHatDTO dto) throws SerException {
        return headPortraitHatSer.pageList(dto);
    }

    @Override
    public HeadPortraitHatBO findOne(String id) throws SerException {
        return headPortraitHatSer.findOne(id);
    }

    @Override
    public Long count(HeadPortraitHatDTO dto) throws SerException {
        return headPortraitHatSer.count(dto);
    }
}