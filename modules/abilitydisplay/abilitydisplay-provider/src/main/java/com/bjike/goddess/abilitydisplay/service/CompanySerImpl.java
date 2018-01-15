package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.bo.CompanyBO;
import com.bjike.goddess.abilitydisplay.dao.CompanyRep;
import com.bjike.goddess.abilitydisplay.dto.CompanyDTO;
import com.bjike.goddess.abilitydisplay.entity.Company;
import com.bjike.goddess.abilitydisplay.entity.MyPage;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * 公司业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class CompanySerImpl extends ServiceImpl<Company, CompanyDTO> implements CompanySer {

    @Autowired
    private CompanyRep companyRep;

    @Transactional
    @Override
    public MyPage getList(Integer pageNum) throws SerException {
        Pageable pageable = new PageRequest(0, 10);
        Page<Company> page = companyRep.findAll(pageable);
        MyPage myPage = new MyPage();

        myPage.setContent(BeanTransform.wanycopyProperties(page.getContent(), CompanyBO.class));
        myPage.setNumber(page.getNumber());
        myPage.setTotalElements(page.getTotalElements());
        return myPage;
    }

    @Override
    public void add(CompanyBO companyBO) throws SerException {
        Company company = BeanTransform.wanycopyProperties(companyBO, Company.class);
        super.save(company);
    }

    @Override
    public void del(String id) {
        companyRep.deleteById(id);
    }

    @Override
    public CompanyBO edi(String id) throws SerException {
        return BeanTransform.wanycopyProperties(super.findById(id), Company.class);
    }

    @Override
    public void test() {

    }

}