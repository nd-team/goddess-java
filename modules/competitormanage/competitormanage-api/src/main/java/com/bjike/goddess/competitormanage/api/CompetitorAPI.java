package com.bjike.goddess.competitormanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.excel.SonPermissionObject;
import com.bjike.goddess.competitormanage.to.CompetitorOrganizaeTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 竞争对手信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompetitorAPI {

    /**
     * 新增竞争对手
     *
     * @param to 竞争对手信息
     */
    CompetitorBO saveCompetitor(CompetitorTO to) throws SerException;

    /**
     * 编辑竞争对手
     *
     * @param to 竞争对手信息
     */
    CompetitorBO editCompetitor(CompetitorTO to) throws SerException;

    /**
     * 删除竞争对手
     *
     * @param id 竞争对手信息Id
     */
    void delete(String id) throws SerException;

    /**
     * 编辑竞争对手组织结构信息
     *
     * @param to 竞争对手组织结构信息
     */
    CompetitorBO editOrganization(CompetitorOrganizaeTO to) throws SerException;

    /**
     * 分页查询
     *
     * @param dto 分页条件
     */
    List<CompetitorBO> pageList(CompetitorDTO dto) throws SerException;

    /**
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @return 列表总条数
     */
    Long count(CompetitorDTO dto) throws SerException;

    /**
     * 根据id查询竞争对手记录
     *
     * @param id 竞争对手记录id
     * @return 竞争对手记录
     */
    CompetitorBO findById(String id) throws SerException;

    /**
     * chenjunhao
     * 通过业务类型查找
     *
     * @param businessType 业务类型
     * @return
     * @throws SerException
     */
    List<CompetitorBO> findByBusinessType(String businessType) throws SerException;

    /**
     * chenjunhao
     * 通过组织机构名称查找
     *
     * @param organization 组织机构名称
     * @return
     * @throws SerException
     */
    List<CompetitorBO> findByOrganization(String organization) throws SerException;

    void leadExcel(List<CompetitorTO> toList) throws SerException;

    byte[] exportExcel(String startDate, String endDate) throws SerException;

    List<SonPermissionObject> sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    byte[] exportExcelModule() throws SerException;

    List<CompetitorBO> areas() throws SerException;

}