package com.bjike.goddess.assistance.service;

import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.to.ComputerAssistTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.assistance.entity.ComputerAssist;
import com.bjike.goddess.assistance.dto.ComputerAssistDTO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;

import java.util.List;

/**
 * 电脑补助业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:20 ]
 * @Description: [ 电脑补助业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ComputerAssistSer extends Ser<ComputerAssist, ComputerAssistDTO> {

    /**
     * 电脑补助列表总条数
     *
     */
    default Long countComputerAssist(ComputerAssistDTO computerAssistDTO) throws SerException {
        return null;
    }


    /**
     * 一个电脑补助
     * @return class ComputerAssistBO
     */
    default ComputerAssistBO getOneById(String id) throws SerException {return null;}

    
    /**
     * 电脑补助列表
     * @return class ComputerAssistBO
     */
    default List<ComputerAssistBO> listComputerAssist(ComputerAssistDTO computerAssistDTO) throws SerException {return null;}
    /**
     *  添加
     * @param computerAssistTO 电脑补助信息
     * @return class ComputerAssistBO
     */
    default ComputerAssistBO addComputerAssist(ComputerAssistTO computerAssistTO) throws SerException { return null;}

    /**
     *  编辑
     * @param computerAssistTO 电脑补助信息
     * @return class ComputerAssistBO
     */
    default ComputerAssistBO editComputerAssist(ComputerAssistTO computerAssistTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteComputerAssist(String id ) throws SerException {return;};


    /**
     * 根据地区汇总
     * @return class ComputerAssistBO
     */
    default List<ComputerAssistBO> collectByArea (ComputerAssistDTO computerAssistDTO) throws SerException {return null;}
    /**
     * 根据项目组汇总
     * @return class ComputerAssistBO
     */
    default List<ComputerAssistBO> collectByProGroup (ComputerAssistDTO computerAssistDTO) throws SerException {return null;}

    /**
     * 获取所有用户
     * @throws SerException
     */
    default List<String> listAllUser() throws SerException { return null;}

    /**
     * 获取用户入职基本信息
     * @throws SerException
     */
    default EntryBasicInfoBO getUserByName(ComputerAssistDTO computerAssistDTO) throws SerException { return null;}


}