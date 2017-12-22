package com.bjike.goddess.competitorsmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.competitorsmanagement.bo.CompetitorBO;
import com.bjike.goddess.competitorsmanagement.dto.CompetitorDTO;
import com.bjike.goddess.competitorsmanagement.entity.Competitor;
import com.bjike.goddess.competitorsmanagement.entity.OrganizationSD;
import com.bjike.goddess.competitorsmanagement.to.CompetitorTO;

import java.util.List;

/**
 * 竞争对手管理业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-13 09:45 ]
 * @Description: [ 竞争对手管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompetitorSer extends Ser<Competitor, CompetitorDTO> {

    List<CompetitorBO> getList() throws SerException;

    void add(CompetitorTO to) throws SerException;

    CompetitorBO editor(String id) throws SerException;

    void upDate(CompetitorTO to) throws SerException;

    byte[] exportExcel(CompetitorDTO dto) throws SerException;

    void importExcel(List<CompetitorTO> tos) throws SerException;

    List<CompetitorBO> search(String condition) throws SerException;

    OrganizationSD organizationSD(String id) throws SerException;
}