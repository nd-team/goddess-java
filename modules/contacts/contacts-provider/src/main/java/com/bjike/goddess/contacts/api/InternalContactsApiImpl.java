package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.*;
import com.bjike.goddess.contacts.dto.SearchDTO;
import com.bjike.goddess.contacts.to.CollectTO;
import com.bjike.goddess.organize.bo.InternalContactsConditionBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.service.InternalContactsSer;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内部通讯录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("internalContactsApiImpl")
public class InternalContactsApiImpl implements InternalContactsAPI {

    @Autowired
    private InternalContactsSer internalContactsSer;

    @Override
    public InternalContactsBO save(InternalContactsTO to) throws SerException {
        return internalContactsSer.save(to);
    }

    @Override
    public InternalContactsBO update(InternalContactsTO to) throws SerException {
        return internalContactsSer.update(to);
    }

    @Override
    public InternalContactsBO delete(InternalContactsTO to) throws SerException {
        return internalContactsSer.delete(to);
    }

    @Override
    public List<InternalContactsBO> findEmailNotNull() throws SerException {
        return internalContactsSer.findEmailNotNull();
    }

    @Override
    public InternalContactsBO findByUser(String user_id) throws SerException {
        return internalContactsSer.findByUser(user_id);
    }

    @Override
    public List<InternalContactsBO> maps(InternalContactsDTO dto) throws SerException {
        return internalContactsSer.maps(dto);
    }

    @Override
    public void sendEmail() throws SerException {
        internalContactsSer.sendEmail();
    }

    @Override
    public InternalContactsBO getById(String id) throws SerException {
        return internalContactsSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return internalContactsSer.getTotal();
    }


    @Override
    public Boolean sonPermission() throws SerException {
        return internalContactsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return internalContactsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public InternalContactsBO importExcel(List<InternalContactsTO> tocs) throws SerException {
        return internalContactsSer.importExcel(tocs);
    }

    @Override
    public void checkDimissionInfo() throws SerException {
        internalContactsSer.checkDimissionInfo();
    }

    @Override
    public List<NameAndIdBO> getUserName() throws SerException {
        return internalContactsSer.getUserName();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return internalContactsSer.templateExport();
    }

    @Override
    public List<String> getEmails(String[] names) throws SerException {
        return internalContactsSer.getEmails(names);
    }

    @Override
    public String getEmail(String name) throws SerException {
        return internalContactsSer.getEmail(name);
    }

    @Override
    public List<MobileInternalContactsBO> mobileList(InternalContactsDTO dto) throws SerException {
        return internalContactsSer.mobileList(dto);
    }

    @Override
    public List<InternalContactsBO> getInfoByName(String name) throws SerException {
        return internalContactsSer.getInfoByName ( name );
    }

    @Override
    public List<PhoneNumberBO> mobileGetTel() throws SerException {
        return internalContactsSer.mobileGetTel();
    }

    @Override
    public Long getMobileTotal(InternalContactsDTO dto) throws SerException {
        return internalContactsSer.getMobileTotal(dto);
    }

    @Override
    public MobileInternalContactsBO findByMobileID(String id) throws SerException {
        return internalContactsSer.findByMobileID(id);
    }

    @Override
    public void test(List<InternalContactsTO> tocs) throws SerException {
        internalContactsSer.test(tocs);
    }

    @Override
    public InternalContactsConditionBO getByName(String name) throws SerException {
        return internalContactsSer.getByName(name);
    }

    @Override
    public List<ContactsCollectBO> dayCollect(CollectTO to) throws SerException {
        return internalContactsSer.dayCollect(to);
    }

    @Override
    public List<ContactsCollectBO> weekCollect(CollectTO to) throws SerException {
        return internalContactsSer.weekCollect(to);
    }

    @Override
    public List<ContactsCollectBO> monthCollect(CollectTO to) throws SerException {
        return internalContactsSer.monthCollect(to);
    }

    @Override
    public List<ContactsCollectBO> totalCollect(CollectTO to) throws SerException {
        return internalContactsSer.totalCollect(to);
    }

    @Override
    public OptionBO dayCollectFigure(CollectTO to) throws SerException {
        return internalContactsSer.dayCollectFigure(to);
    }

    @Override
    public OptionBO weekCollectFigure(CollectTO to) throws SerException {
        return internalContactsSer.weekCollectFigure(to);
    }

    @Override
    public OptionBO monthCollectFigure(CollectTO to) throws SerException {
        return internalContactsSer.monthCollectFigure(to);
    }

    @Override
    public OptionBO totalCollectFigure(CollectTO to) throws SerException {
        return internalContactsSer.totalCollectFigure(to);
    }

    @Override
    public void checkEmail() throws SerException {
        internalContactsSer.checkEmail();
    }

    @Override
    public List<MobileContactsBO> mobileInfoByDepartment() throws SerException {
        return internalContactsSer.mobileInfoByDepartment();
    }

    @Override
    public List<MobileSearchBO> mobileSearch(SearchDTO dto) throws SerException {
        return internalContactsSer.mobileSearch(dto);
    }
}