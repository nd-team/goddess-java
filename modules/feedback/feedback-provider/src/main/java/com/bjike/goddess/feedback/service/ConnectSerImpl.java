package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ConnectBO;
import com.bjike.goddess.feedback.dto.ConnectDTO;
import com.bjike.goddess.feedback.entity.Connect;
import com.bjike.goddess.feedback.to.ConnectTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisCluster;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 各类沟通模板业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 03:31 ]
 * @Description: [ 各类沟通模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ConnectSerImpl extends ServiceImpl<Connect, ConnectDTO> implements ConnectSer {
    @Override
    public Long count(ConnectDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ConnectBO getOne(String id) throws SerException {
        Connect connect = super.findById(id);
        return BeanTransform.copyProperties(connect, ConnectBO.class);
    }

    @Override
    public List<ConnectBO> list(ConnectDTO dto) throws SerException {
        List<Connect> connects = super.findByCis(dto);
        List<ConnectBO> connectBOS = BeanTransform.copyProperties(connects, ConnectBO.class);
        return connectBOS;
    }

//    @Override
//    @Transactional(rollbackFor = SerException.class)
//    public ConnectBO insert(ConnectTO to) throws SerException {
//        return null;
//    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ConnectBO edit(ConnectTO to) throws SerException {
        Connect connect = super.findById(to.getId());
        BeanTransform.copyProperties(to,connect,true);
        connect.setModifyTime(LocalDateTime.now());
        super.update(connect);
        return BeanTransform.copyProperties(connect,ConnectBO.class);
    }

//    @Override
//    @Transactional(rollbackFor = SerException.class)
//    public void remove(String id) throws SerException {
//        if (StringUtils.isNotBlank(id)) {
//            super.remove(id);
//        } else {
//            throw new SerException("id不能为空");
//        }
//
//    }

    @Override
    public List<String> getSort() throws SerException {
        String[] feilds = new String[]{"sorting"};
        List<ConnectBO> connectBOS = super.findBySql("select sorting from feedback_connect group by sorting order by sorting asc ", ConnectBO.class, feilds);
        List<String> list = connectBOS.stream().map(ConnectBO::getSorting)
                .filter(sorting -> sorting != null || !"".equals(sorting.trim())).distinct().collect(Collectors.toList());

        return list;
    }

    @Override
    public List<ConnectBO> getConnect(String sorting) throws SerException {
        Connect connect = new Connect();
        if(StringUtils.isNotBlank(sorting)){
            ConnectDTO dto = new ConnectDTO();
            dto.getConditions().add(Restrict.eq("sorting",sorting));
            connect = super.findOne(dto);
        }
        List<ConnectBO> connectBOS = BeanTransform.copyProperties(connect,ConnectBO.class);
        return connectBOS;
    }
}