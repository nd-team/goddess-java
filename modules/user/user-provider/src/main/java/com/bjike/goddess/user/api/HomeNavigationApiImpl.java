package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.HomeNavigationBO;
import com.bjike.goddess.user.dto.HomeNavigationDTO;
import com.bjike.goddess.user.service.HomeNavigationSer;
import com.bjike.goddess.user.to.HomeNavigationTO;
import com.bjike.goddess.user.to.NavigationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页导航业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("homeNavigationApiImpl")
public class HomeNavigationApiImpl implements HomeNavigationAPI {
    @Autowired
    private HomeNavigationSer homeNavigationSer;

    @Override
    public Long countHome(HomeNavigationDTO dto) throws SerException {
        return homeNavigationSer.countHome(dto);
    }

    @Override
    public List<HomeNavigationBO> list(HomeNavigationDTO dto) throws SerException {
        return homeNavigationSer.list(dto);
    }

    @Override
    public void save(HomeNavigationTO to) throws SerException {
        homeNavigationSer.save(to);
    }

    @Override
    public void edit(HomeNavigationTO to) throws SerException {
        homeNavigationSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        homeNavigationSer.delete(id);
    }

    @Override
    public void editOrder(NavigationTO navigationTO) throws SerException {
        homeNavigationSer.editOrder(navigationTO);
    }
}