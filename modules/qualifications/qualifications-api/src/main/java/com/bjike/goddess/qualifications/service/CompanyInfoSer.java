package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.CompanyInfoBO;
import com.bjike.goddess.qualifications.bo.PersonnelInformationBO;
import com.bjike.goddess.qualifications.dto.CompanyInfoDTO;
import com.bjike.goddess.qualifications.entity.CompanyInfo;
import com.bjike.goddess.qualifications.to.CompanyInfoTO;

import java.util.List;

/**
 * 公司基本信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:38 ]
 * @Description: [ 公司基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyInfoSer extends Ser<CompanyInfo, CompanyInfoDTO> {

    /**
     * 保存
     *
     * @param to 公司基本信息传输对象
     * @return
     * @throws SerException
     */
    default CompanyInfoBO save(CompanyInfoTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 公司基本信息传输对象
     * @return
     * @throws SerException
     */
    default CompanyInfoBO update(CompanyInfoTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 公司基本信息ID
     * @return
     * @throws SerException
     */
    default CompanyInfoBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 获取全部
     *
     * @return
     * @throws SerException
     */
    default List<CompanyInfoBO> all() throws SerException {
        return null;
    }

}