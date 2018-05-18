package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.abilitydisplay.bo.CompanyBO;
import com.bjike.goddess.abilitydisplay.dto.CompanyDTO;
import com.bjike.goddess.abilitydisplay.entity.Company;
import com.bjike.goddess.common.jpa.dao.JpaRep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公司持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-06 03:38 ]
 * @Description: [ 公司持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

public interface CompanyRep extends JpaRep<Company, CompanyDTO>,PagingAndSortingRepository<Company,String>{

    @Modifying
    void deleteById(String id);

    @Transactional
    @Override
    Page<Company> findAll(Pageable pageable);

}