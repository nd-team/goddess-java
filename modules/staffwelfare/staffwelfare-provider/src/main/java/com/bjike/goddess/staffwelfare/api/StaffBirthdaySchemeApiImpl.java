package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffwelfare.bo.HeadPortraitHatBO;
import com.bjike.goddess.staffwelfare.bo.StaffBirthdaySchemeBO;
import com.bjike.goddess.staffwelfare.bo.WishesStatementBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthdaySchemeDTO;
import com.bjike.goddess.staffwelfare.service.HeadPortraitHatSer;
import com.bjike.goddess.staffwelfare.service.StaffBirthdaySchemeSer;
import com.bjike.goddess.staffwelfare.service.WishesStatementSer;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.to.StaffBirthdaySchemeTO;
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

    @Override
    public List<EntryRegisterBO> findEntry() throws SerException {
        return staffBirthdaySchemeSer.findEntry();
    }

    @Override
    public Long count(StaffBirthdaySchemeDTO dto) throws SerException {
        return staffBirthdaySchemeSer.count(dto);
    }

    @Override
    public StaffBirthdaySchemeBO findOne(String id) throws SerException {
        return staffBirthdaySchemeSer.findOne(id);
    }

    @Override
    public List<StaffBirthdaySchemeBO> collect(StaffBirthdaySchemeDTO dto) throws SerException {
        return staffBirthdaySchemeSer.collect(dto);
    }
}