package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.festival.bo.CompanyFestivalTimeBO;
import com.bjike.goddess.festival.entity.CompanyFestivalTime;
import com.bjike.goddess.festival.dto.CompanyFestivalTimeDTO;
import com.bjike.goddess.festival.to.CompanyFestivalTimeTO;

import java.util.List;

/**
 * 公司放假时间安排业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 08:10 ]
 * @Description: [ 公司放假时间安排业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyFestivalTimeSer extends Ser<CompanyFestivalTime, CompanyFestivalTimeDTO> {

    /**
     * 公司放假时间安排列表总条数
     *
     */
    default Long countCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {
        return null;
    }
    /**
     * 公司放假时间安排列表
     * @return class CompanyFestivalTimeBO
     */
    default List<CompanyFestivalTimeBO> listCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {return null;}
    /**
     *  添加
     * @param companyFestivalTimeTO 公司放假时间安排信息
     * @return class CompanyFestivalTimeBO
     */
    default CompanyFestivalTimeBO addCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException { return null;}

    /**
     *  编辑
     * @param companyFestivalTimeTO 公司放假时间安排信息
     * @return class CompanyFestivalTimeBO
     */
    default CompanyFestivalTimeBO editCompanyFestivalTime(CompanyFestivalTimeTO companyFestivalTimeTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteCompanyFestivalTime(String id ) throws SerException {return;};

    /**
     * 公司放假节日列表
     */
    default List<String> listFestivalName() throws SerException {return null;}
    /**
     * 根据节日名称查询单个时间安排
     * @return class CompanyFestivalTimeBO
     */
    default CompanyFestivalTimeBO getCompanyFestivalTime(CompanyFestivalTimeDTO companyFestivalTimeDTO) throws SerException {return null;}



}