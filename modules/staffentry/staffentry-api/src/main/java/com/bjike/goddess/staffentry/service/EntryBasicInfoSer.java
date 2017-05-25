package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.to.EntryBasicInfoTO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;

import java.util.List;

/**
 * 入职基本信息业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 11:55]
 * @Description: [入职基本信息业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface EntryBasicInfoSer extends Ser<EntryBasicInfo, EntryBasicInfoDTO> {

    /**
     * 薪资确认列表总条数
     */
    default Long countEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        return null;
    }
    /**
     * 获取所有入职登记
     * @param entryBasicInfoDTO 入职登记dto
     * @return class EntryBasicInfoBO
     * @throws SerException
     */
    default List<EntryBasicInfoBO> listEntryBasicInfo(EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工入职基本信息
     * @param entryBasicInfoTO   员工入职基本信息数据to
     * @return class entryBasicInfoBO
     * @throws SerException
     */
    default EntryBasicInfoBO insertEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO ) throws SerException {
        return null;
    }

    ;


    /**
     * 编辑员工入职基本信息
     *
     * @param entryBasicInfoTO   员工入职基本信息数据to
     * @return class entryBasicInfoBO
     * @throws SerException
     */
    default EntryBasicInfoBO editEntryBasicInfo(EntryBasicInfoTO entryBasicInfoTO ) throws SerException {
        return null;
    }

    ;


    /**
     * 根据id删除入职基本信息
     *
     * @param id
     * @throws SerException
     */
    default void removeEntryBasicInfo(String id) throws SerException {
        return;
    }

    /**
     * 根据id查找某个员工入职基本信息
     *
     * @param id 员工入职基本信息id
     * @return class EntryBasicInfoBO
     * @throws SerException
     */
    default EntryBasicInfoBO getEntryBasicInfo(String id) throws SerException {
        return null;
    }

    /**
     * 根据id发送入职通告邮件
     * @param entryBasicInfoTO to
     * @return class EntryBasicInfoBO
     * @throws SerException
     */
    default EntryBasicInfoBO sendEntryBasicInfo( EntryBasicInfoTO entryBasicInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据岗位(position)、时间段(entryTime) 汇总入职情况统计
     * @param  entryBasicInfoDTO 员工入职基本信息bo 主要position 和 entryTime
     * @return class EntryBasicInfoBO
     * @throws SerException
     */
    default List<EntryBasicInfoBO> collectEntryBasicInfo( EntryBasicInfoDTO entryBasicInfoDTO) throws SerException {
        return null;
    }

    /**
     * 汇总获取所有岗位
     * @throws SerException
     */
    default List<String> listPost( ) throws SerException {
        return null;
    }

    /**
     * 根据名字查找信息
     * @param  name name
     * @return class EntryBasicInfoBO
     * @throws SerException
     */
    default List<EntryBasicInfoVO> getEntryBasicInfoByName(String name) throws SerException {
        return null;
    }
    /**
     * 根据员工编号获取入职时间
     * @param  empNumber empNumber
     * @return class entryBasicInfoBO
     * @throws SerException
     */
    default List<EntryBasicInfoVO> getByEmpNumber(String empNumber) throws SerException {
        return null;
    }


}
