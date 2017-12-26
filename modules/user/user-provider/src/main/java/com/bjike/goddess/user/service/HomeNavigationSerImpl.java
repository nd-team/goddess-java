package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.HomeNavigationBO;
import com.bjike.goddess.user.dto.HomeNavigationDTO;
import com.bjike.goddess.user.entity.HomeNavigation;
import com.bjike.goddess.user.to.HomeNavigationTO;
import com.bjike.goddess.user.to.NavigationTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页导航业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class HomeNavigationSerImpl extends ServiceImpl<HomeNavigation, HomeNavigationDTO> implements HomeNavigationSer {

    @Override
    public Long countHome(HomeNavigationDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<HomeNavigationBO> list(HomeNavigationDTO dto) throws SerException {
        dto.getSorts().add("orderNum=asc");
        List<HomeNavigation> homeNavigations = super.findByCis(dto);
        return BeanTransform.copyProperties(homeNavigations, HomeNavigationBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(HomeNavigationTO to) throws SerException {
        HomeNavigation homeNavigation = BeanTransform.copyProperties(to, HomeNavigation.class, true);
        String sql = "select max(orderNum) from " + getTableName(HomeNavigation.class);
        List<Object> objectList = super.findBySql(sql);
        Integer orderNum = 0;
        if (objectList != null && objectList.size() > 0) {
            orderNum = Integer.parseInt(String.valueOf(objectList.get(0) == null ? 0 : objectList.get(0)));
        }
        homeNavigation.setCreateTime(LocalDateTime.now());
        homeNavigation.setOrderNum(orderNum+1);
        super.save(homeNavigation);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(HomeNavigationTO to) throws SerException {
        HomeNavigation homeNavigation = super.findById(to.getId());
        Integer order = homeNavigation.getOrderNum();
        BeanTransform.copyProperties(to, homeNavigation, true);
        homeNavigation.setModifyTime(LocalDateTime.now());
        homeNavigation.setOrderNum(order);
        super.update(homeNavigation);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void editOrder(NavigationTO navigationTO) throws SerException {
        List<HomeNavigation> homeNavigations = new ArrayList<>();
        List<HomeNavigationTO> homeNavigationTOS = navigationTO.getTos();
        if(homeNavigationTOS!=null && homeNavigationTOS.size()>0){
            for (HomeNavigationTO homeNavigationTO : homeNavigationTOS){
                HomeNavigation homeNavigation = super.findById(homeNavigationTO.getId());
                homeNavigation.setModifyTime(LocalDateTime.now());
                homeNavigation.setOrderNum(homeNavigationTO.getOrderNum());
                homeNavigations.add(homeNavigation);
            }
            super.update(homeNavigations);
        }
    }
}