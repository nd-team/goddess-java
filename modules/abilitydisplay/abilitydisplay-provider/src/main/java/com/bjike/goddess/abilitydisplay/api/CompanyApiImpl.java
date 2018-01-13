package com.bjike.goddess.abilitydisplay.api;

import com.bjike.goddess.abilitydisplay.bo.CompanyBO;
import com.bjike.goddess.abilitydisplay.dto.CompanyDTO;
import com.bjike.goddess.abilitydisplay.entity.Company;
import com.bjike.goddess.abilitydisplay.entity.MyPage;
import com.bjike.goddess.abilitydisplay.service.CompanySer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公司业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("companyApiImpl")
public class CompanyApiImpl implements CompanyAPI {
    @Autowired
    private CompanySer companySer;

    @Override
    public MyPage getList(Integer pageNum) throws SerException {
        return companySer.getList(pageNum);
    }

    @Override
    public void add(CompanyBO companyBO) throws SerException {
        companySer.add(companyBO);
    }

    @Override
    public void del(String id) {
        companySer.del(id);
    }

    @Override
    public CompanyBO edi(String id) throws SerException {
        return companySer.edi(id);
    }

    @Override
    public void test() {
        companySer.test();
    }
}