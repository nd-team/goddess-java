package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.bo.DemandObjectBO;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businessinteraction.entity.Demand;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;

import java.util.List;

/**
 * 互动平台需求描述业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DemandSer extends Ser<Demand, DemandDTO> {

    /**
     * 互动平台需求描述列表总条数
     *
     */
    default Long countInter(DemandDTO demandDTO) throws SerException {
        return null;
    }
    /**
     * 互动平台需求描述列表id
     * @return class DemandBO
     */
    default DemandBO getOneById (String id) throws SerException {return null;}

    
    /**
     * 互动平台需求描述列表
     * @return class DemandBO
     */
    default List<DemandBO> listDemand(DemandDTO demandDTO) throws SerException {return null;}
    /**
     *  添加
     * @param demandTO 互动平台需求描述信息
     * @return class DemandBO
     */
    default DemandBO addDemand(DemandTO demandTO) throws SerException { return null;}

    /**
     *  编辑
     * @param demandTO 互动平台需求描述信息
     * @return class DemandBO
     */
    default DemandBO editDemand(DemandTO demandTO) throws SerException { return null;}

    /**
     * 删除互动平台需求描述信息
     * @param id id
     */
    default void deleteDemand(String id ) throws SerException {return;};

    /**
     * 搜索互动平台需求描述列表
     * @return class DemandBO
     */
    default List<DemandBO> searchList(DemandDTO demandDTO) throws SerException {return null;}


    /**
     * 搜索互动平台需求描述列表
     * @return class DemandBO
     */
    default List<DemandObjectBO> searchDemand(DemandDTO demandDTO) throws SerException {return null;}

}