package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfaremanage.dto.HeadPortraitHatDTO;
import com.bjike.goddess.staffwelfaremanage.excel.SonPermissionObject;
import com.bjike.goddess.staffwelfaremanage.service.HeadPortraitHatSer;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.HeadPortraitHatTO;
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
}