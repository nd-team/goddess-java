package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.OtherSpendBO;
import com.bjike.goddess.fundcheck.dto.OtherSpendDTO;
import com.bjike.goddess.fundcheck.service.OtherSpendSer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherSpendCollectTO;
import com.bjike.goddess.fundcheck.to.OtherSpendTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其他支出业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:57 ]
 * @Description: [ 其他支出业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("otherSpendApiImpl")
public class OtherSpendApiImpl implements OtherSpendAPI {
    @Autowired
    private OtherSpendSer otherSpendSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return otherSpendSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return otherSpendSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(OtherSpendDTO otherSpendDTO) throws SerException {
        return otherSpendSer.count(otherSpendDTO);
    }

    @Override
    public OtherSpendBO getOne(String id) throws SerException {
        return otherSpendSer.getOne(id);
    }

    @Override
    public List<OtherSpendBO> findListBack(OtherSpendDTO otherSpendDTO) throws SerException {
        return otherSpendSer.findListBack(otherSpendDTO);
    }

    @Override
    public OtherSpendBO insert(OtherSpendTO otherSpendTO) throws SerException {
        return otherSpendSer.insert(otherSpendTO);
    }

    @Override
    public OtherSpendBO edit(OtherSpendTO otherSpendTO) throws SerException {
        return otherSpendSer.edit(otherSpendTO);
    }

    @Override
    public void remove(String id) throws SerException {
        otherSpendSer.remove(id);
    }
    @Override
    public List<OtherSpendBO> collect(OtherSpendCollectTO to) throws SerException {
        return otherSpendSer.collect(to);
    }
    @Override
    public OtherSpendBO importExcel(List<OtherSpendTO> otherSpendTOS) throws SerException {
        return otherSpendSer.importExcel(otherSpendTOS);
    }
    @Override
    public byte[] templateExport() throws SerException {
        return otherSpendSer.templateExport();
    }
    @Override
    public List<String> listFirstSubject() throws SerException {
        return otherSpendSer.listFirstSubject();
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        return otherSpendSer.listSubByFirst(firstSub);
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return otherSpendSer.listTubByFirst(firstSub, secondSub);
    }
}