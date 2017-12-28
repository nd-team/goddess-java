package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.bo.DetailBO;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.entity.Asset;
import com.bjike.goddess.reportmanagement.to.AssetTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.SonPermissionObject;

import java.util.List;

/**
 * 资产表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AssetSer extends Ser<Asset, AssetDTO> {
//    /**
//     * 通过id和日期查找
//     *
//     * @param id
//     * @param startTime 起始时间
//     * @param endTime   结束时间
//     * @return
//     * @throws SerException
//     */
//    AssetBO find(String id, String startTime, String endTime) throws SerException;

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AssetBO> list(AssetDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    AssetBO save(AssetTO to) throws SerException;

    /**
     * 查看资产结构表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<StructureBO> assetStructure(AssetDTO dto) throws SerException;

    /**
     * 偿还能力分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RepayAnalyzeBO> repayAnalyze(AssetDTO dto) throws SerException;

    /**
     * 查看金额明细
     *
     * @param id
     * @param dto
     * @return
     * @throws SerException
     */
    List<DetailBO> findDetails(String id, AssetDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    AssetBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(AssetDTO dto) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(AssetTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 列表1
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<AssetBO> list1(AssetDTO dto) throws SerException;

    /**
     * 导出资产负债表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(AssetDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取所有科目
     *
     * @return
     * @throws SerException
     */
    default List<String> allFirstSubjects() throws SerException {
        return null;
    }

    /**
     * 获取所有项目名称
     *
     * @return
     * @throws SerException
     */
    default List<String> allProjectNames() throws SerException {
        return null;
    }
}