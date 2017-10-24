package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.*;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.dto.SearchDTO;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.to.BiddingCollectTO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 招标信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.975 ]
 * @Description: [ 招标信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingInfoAPI {

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
     * 招标信息列表总条数
     */
    default Long countBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个招标信息
     *
     * @return class BiddingInfoBO
     */
    default BiddingInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 招标信息
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class BiddingInfoBO
     * @throws SerException
     */
    default List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class BiddingInfoBO
     * @throws SerException
     */
    default BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class BiddingInfoBO
     * @throws SerException
     */
    default BiddingInfoBO editBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除招标信息
     *
     * @param id
     * @throws SerException
     */
    default void removeBiddingInfo(String id) throws SerException {

    }


    /**
     * 汇总
     *
     * @param cities cities
     * @return class BiddingInfoCollectBO
     * @throws SerException
     */
    default List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
        return null;
    }
    /**
     * 获取地市
     *
     * @return class String
     */
    default List<String> getBiddingInfoCities() throws SerException {
        return null;
    }

    /**
     * 根据编号查找招投信息
     *
     * @return class String
     */
    default BiddingInfoBO getBidding(String biddingNumber) throws SerException {
        return null;
    }

    /**
     * 获取项目名称
     *
     * @return class String
     */
    default List<String> getProjectName() throws SerException {
        return null;
    }

    /**
     * 获取招投编号
     *
     * @return class String
     */
    default List<String> getTenderNumber() throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(BiddingInfoDTO dto) throws SerException;


    /**
     * 招投标流程进度管理日汇总
     *
     * @param to to
     * @return class BiddingCollectBO
     * @throws SerException
     */
    default List<BiddingCollectBO> dayCollect(BiddingCollectTO to) throws SerException {
        return null;
    }

    /**
     * 招投标流程进度管理周汇总
     *
     * @param to to
     * @return class BiddingCollectBO
     * @throws SerException
     */
    default List<BiddingCollectBO> weekCollect(BiddingCollectTO to) throws SerException {
        return null;
    }

    /**
     * 招投标流程进度管理月汇总
     *
     * @param to to
     * @return class BiddingCollectBO
     * @throws SerException
     */
    default List<BiddingCollectBO> monthCollect(BiddingCollectTO to) throws SerException {
        return null;
    }

    /**
     * 招投标流程进度管理累计汇总
     *
     * @param to to
     * @return class BiddingCollectBO
     * @throws SerException
     */
    default List<BiddingCollectBO> totalCollect(BiddingCollectTO to) throws SerException {
        return null;
    }
    /**
     * 招投标流程进度管理图形日汇总
     *
     * @param to to
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayFigureCollect(BiddingCollectTO to) throws SerException {
        return null;
    }

    /**
     * 招投标流程进度管理图形周汇总
     *
     * @param to to
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekFigureCollect(BiddingCollectTO to) throws SerException {
        return null;
    }

    /**
     * 招投标流程进度管理图形月汇总
     *
     * @param to to
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthFigureCollect(BiddingCollectTO to) throws SerException {
        return null;
    }

    /**
     * 招投标流程进度管理图形累计汇总
     *
     * @param to to
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalFigureCollect(BiddingCollectTO to) throws SerException {
        return null;
    }
    /**
     * 中国移动采购与招标网总条数(每页20条)
     *
     * @throws SerException
     */
    Long infoTotal() throws SerException;

    /**
     * 中国移动采购与招标网获取信息
     *
     * @param dto
     * @return class InfoBO
     * @throws SerException
     */
    List<InfoBO> info(SearchDTO dto) throws SerException;
    /**
     * 工信部招标网总条数(每页11条)
     *
     * @throws SerException
     */
    Long txzbTotal() throws SerException;

    /**
     * 工信部招标网获取信息
     *
     * @param dto
     * @return class InfoBO
     * @throws SerException
     */
    List<InfoBO> txzbInfo(SearchDTO dto) throws SerException;

    /**
     * 中央政府采购网获取信息
     *
     * @param dto
     * @return class InfoBO
     * @throws SerException
     */
    List<InfoBO> zycgInfo(SearchDTO dto) throws SerException;
    /**
     * 中央政府采购网总条数
     *
     * @throws SerException
     */
    Long zycyTotal() throws SerException;
    /**
     * 中国警务招标网总条数
     *
     * @throws SerException
     */
    Long caigouTotal() throws SerException;

    /**
     * 中国警务招标网获取信息
     *
     * @param dto
     * @return class InfoBO
     * @throws SerException
     */
    List<InfoBO> caigouInfo(SearchDTO dto) throws SerException;
    /**
     * 中国电力招标网总条数(每页23条)
     *
     * @throws SerException
     */
    Long toobiaoTotal() throws SerException;

    /**
     * 中国电力招标网获取信息
     *
     * @param dto
     * @return class InfoBO
     * @throws SerException
     */
    List<InfoBO> toobiaoInfo(SearchDTO dto) throws SerException;
    /**
     * 中国学校招标网获取信息
     *
     * @param dto
     * @return class InfoBO
     * @throws SerException
     */
//    List<InfoBO> schoolbidInfo(SearchDTO dto) throws SerException;
}