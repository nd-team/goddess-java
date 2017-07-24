package com.bjike.goddess.staffwelfaremanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfaremanage.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfaremanage.bo.StaffBirthdaySchemeBO;
import com.bjike.goddess.staffwelfaremanage.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfaremanage.dto.StaffBirthdaySchemeDTO;
import com.bjike.goddess.staffwelfaremanage.entity.HeadPortraitHat;
import com.bjike.goddess.staffwelfaremanage.service.HeadPortraitHatSer;
import com.bjike.goddess.staffwelfaremanage.service.StaffBirthdaySchemeSer;
import com.bjike.goddess.staffwelfaremanage.service.WishesStatementSer;
import com.bjike.goddess.staffwelfaremanage.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfaremanage.to.StaffBirthdaySchemeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工生日福利方案业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-05 10:17 ]
 * @Description: [ 员工生日福利方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffBirthdaySchemeApiImpl")
public class StaffBirthdaySchemeApiImpl implements StaffBirthdaySchemeAPI {

    @Autowired
    private StaffBirthdaySchemeSer staffBirthdaySchemeSer;
    @Autowired
    private WishesStatementSer wishesStatementSer;
    @Autowired
    private HeadPortraitHatSer headPortraitHatSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return staffBirthdaySchemeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffBirthdaySchemeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public StaffBirthdaySchemeBO addModel(StaffBirthdaySchemeTO to) throws SerException {
        return staffBirthdaySchemeSer.insertModel(to);
    }

    @Override
    public StaffBirthdaySchemeBO editModel(StaffBirthdaySchemeTO to) throws SerException {
        return staffBirthdaySchemeSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        staffBirthdaySchemeSer.remove(id);
    }

    @Override
    public List<StaffBirthdaySchemeBO> pageList(StaffBirthdaySchemeDTO dto) throws SerException {
        return staffBirthdaySchemeSer.pageList(dto);
    }

    @Override
    public void pass(String id) throws SerException {
        staffBirthdaySchemeSer.pass(id);
    }

    @Override
    public void receive(String id, String remark) throws SerException {
        staffBirthdaySchemeSer.receive(id, remark);
    }

    @Override
    public List<WishesStatementBO> findWishStatements() throws SerException {
        return wishesStatementSer.findAllStatement();
    }

    @Override
    public List<HeadPortraitHatBO> findHeadPortraitHats() throws SerException {
        return headPortraitHatSer.findHeadPortraitHats();
    }
}