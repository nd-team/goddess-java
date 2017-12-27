package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.HomeNavigationBO;
import com.bjike.goddess.user.dto.HomeNavigationDTO;
import com.bjike.goddess.user.to.HomeNavigationTO;
import com.bjike.goddess.user.to.NavigationTO;

import java.util.List;

/**
 * 首页导航业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HomeNavigationAPI {
    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long countHome(HomeNavigationDTO dto) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<HomeNavigationBO> list(HomeNavigationDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    void save(HomeNavigationTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(HomeNavigationTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 修改排序
     *
     * @param navigationTO
     * @throws SerException
     */
    void editOrder(NavigationTO navigationTO) throws SerException;
}