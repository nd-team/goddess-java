package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOpenAccountDTO;
import com.bjike.goddess.shareholdersmanage.service.ShareOpenAccountSer;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountBTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股东开户业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("shareOpenAccountApiImpl")
public class ShareOpenAccountApiImpl implements ShareOpenAccountAPI {
    @Autowired
    private ShareOpenAccountSer shareOpenAccountSer;
    @Override
    public Long countShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return shareOpenAccountSer.countShareOpen(shareOpenAccountDTO);
    }

    @Override
    public ShareOpenAccountBO getOne(String id) throws SerException {
        return shareOpenAccountSer.getOne(id);
    }

    @Override
    public List<ShareOpenAccountBO> findList(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return shareOpenAccountSer.findList(shareOpenAccountDTO);
    }

    @Override
    public ShareOpenAccountBO save(ShareOpenAccountTO shareOpenAccountTO) throws SerException {
        return shareOpenAccountSer.save(shareOpenAccountTO);
    }

    @Override
    public ShareOpenAccountBO edit(ShareOpenAccountBTO shareOpenAccountBTO) throws SerException {
        return shareOpenAccountSer.edit(shareOpenAccountBTO);
    }

    @Override
    public List<String> findEquityType() throws SerException {
        return shareOpenAccountSer.findEquityType();
    }

    @Override
    public void delete(String id) throws SerException {
        shareOpenAccountSer.delete(id);
    }

    @Override
    public List<ShareOpenAccountBO> summationShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return shareOpenAccountSer.summationShareOpen(shareOpenAccountDTO);
    }

    @Override
    public ShareOpenAccountBO importExcel(List<ShareOpenAccountTO> shareOpenAccountTOS) throws SerException {
        return shareOpenAccountSer.importExcel(shareOpenAccountTOS);
    }

    @Override
    public byte[] exportExcel(ShareOpenAccountDTO shareOpenAccountDTO) throws SerException {
        return shareOpenAccountSer.exportExcel(shareOpenAccountDTO);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return shareOpenAccountSer.templateExport();
    }

    @Override
    public ShareOpenAccountBO findByName(String shareholderName) throws SerException {
        return shareOpenAccountSer.findByName(shareholderName);
    }

    @Override
    public List<String> findShareholderName() throws SerException {
        return shareOpenAccountSer.findShareholderName();
    }
}