package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.royalty.bo.IndexLibraryBO;
import com.bjike.goddess.royalty.bo.RoyaltyCollectBO;
import com.bjike.goddess.royalty.dto.IndexLibraryDTO;
import com.bjike.goddess.royalty.entity.IndexLibrary;
import com.bjike.goddess.royalty.excel.SonPermissionObject;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.IndexLibraryTO;
import com.bjike.goddess.royalty.to.RoyaltyCollectTO;

import java.util.List;

/**
 * 指标库业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IndexLibrarySer extends Ser<IndexLibrary, IndexLibraryDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 指标库列表总条数
     */
    default Long count(IndexLibraryDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个指标库
     *
     * @return class IndexLibraryBO
     */
    default IndexLibraryBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 指标库
     *
     * @param dto 指标库dto
     * @return class IndexLibraryBO
     * @throws SerException
     */
    default List<IndexLibraryBO> list(IndexLibraryDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加指标库
     *
     * @param indexLibraryTO 指标库数据to
     * @return class IndexLibraryBO
     * @throws SerException
     */
    default IndexLibraryBO insert(IndexLibraryTO indexLibraryTO) throws SerException {
        return null;
    }

    /**
     * 编辑指标库
     *
     * @param indexLibraryTO 指标库数据to
     * @return class IndexLibraryBO
     * @throws SerException
     */
    default IndexLibraryBO edit(IndexLibraryTO indexLibraryTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除指标库
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 获取所有指标编号
     *
     * @throws SerException
     */
    default List<String> getIndexNum() throws SerException {
        return null;
    }

    /**
     * 获取所有指标名称
     *
     * @throws SerException
     */
    default List<String> getIndexName() throws SerException {
        return null;
    }

    /**
     * 根据指标编号获取指标库
     *
     * @param indexNum 指标编号
     * @throws SerException
     */
    default IndexLibraryBO getIndexLibrary(String indexNum) throws SerException {
        return null;
    }
    /**
     * 管理提成管理日汇总
     *
     * @param to to
     * @return class RoyaltyCollectBO
     * @throws SerException
     */
    default List<RoyaltyCollectBO> dayRoyalty(RoyaltyCollectTO to) throws SerException {
        return null;
    }
    /**
     * 管理提成管理周汇总
     *
     * @param to to
     * @return class RoyaltyCollectBO
     * @throws SerException
     */
    default List<RoyaltyCollectBO> weekRoyalty(RoyaltyCollectTO to) throws SerException {
        return null;
    }
    /**
     * 管理提成管理月汇总
     *
     * @param to to
     * @return class RoyaltyCollectBO
     * @throws SerException
     */
    default List<RoyaltyCollectBO> monthRoyalty(RoyaltyCollectTO to) throws SerException {
        return null;
    }
    /**
     * 管理提成管理累计汇总
     *
     * @param to to
     * @return class RoyaltyCollectBO
     * @throws SerException
     */
    default List<RoyaltyCollectBO> totalRoyalty(RoyaltyCollectTO to) throws SerException {
        return null;
    }

}