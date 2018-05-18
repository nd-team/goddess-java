package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.bo.CompanyBO;
import com.bjike.goddess.abilitydisplay.dto.CompanyDTO;
import com.bjike.goddess.abilitydisplay.entity.Company;
import com.bjike.goddess.abilitydisplay.entity.MyPage;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 公司业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanySer extends Ser<Company, CompanyDTO> {

    default Company test() {
        return null;
    }

    MyPage getList(Integer pageNum) throws SerException;

    void add(CompanyBO companyBO) throws SerException;

    void del(String id);

    CompanyBO edi(String id) throws SerException;

    void update(CompanyBO companyBO) throws SerException;

}