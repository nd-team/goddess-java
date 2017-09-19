package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.BussinesBO;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.FirmIntroDTO;
import com.bjike.goddess.intromanage.to.FirmDisplayFieldTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 公司简介业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirmIntroAPI {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntroBO
     * @throws SerException
     */
    FirmIntroBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 公司简介dto
     * @throws SerException
     */
    Long count(FirmIntroDTO dto) throws SerException;

    /**
     * 分页查询公司简介
     *
     * @return class FirmIntroBO
     * @throws SerException
     */
    List<FirmIntroBO> list(FirmIntroDTO dto) throws SerException;

    /**
     * 保存公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroBO
     * @throws SerException
     */
    FirmIntroBO save(FirmIntroTO to) throws SerException;

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新公司简介
     *
     * @param to 公司简介to
     * @throws SerException
     */
    void update(FirmIntroTO to) throws SerException;

    /**
     * 设置哪些用户可以查看哪些字段
     *
     * @param username 用户名集合
     * @param to       公司简介需要显示的字段
     * @throws SerException
     */
    void setFirmDisplayField(String[] username, FirmDisplayFieldTO to) throws SerException;
    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallMonUser() throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 获取所有公司名称
     *
     * @return
     * @throws SerException
     */
    Set<String> firmNames() throws SerException;

    /**
     * chenjunhao
     * 根据公司名称获取注册资金和员工数量
     *
     * @param name
     * @return
     * @throws SerException
     */
    Set<BussinesBO> moneyByName(String name) throws SerException;
    /**
     * 冻结公司简介
     *
     * @param id id
     */
    default void congealFirmin(String id) throws SerException {
        return;
    }


    /**
     * 解冻公司简介
     *
     * @param id id
     */
    default void thawFirmin(String id) throws SerException {
        return;
    }
    /**
     * 获取最早时间
     *
     */
    default String getDate() throws SerException {
        return null;
    }
}