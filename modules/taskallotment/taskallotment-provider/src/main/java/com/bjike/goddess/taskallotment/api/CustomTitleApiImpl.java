package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.dto.CustomTitleDTO;
import com.bjike.goddess.taskallotment.entity.CustomTitle;
import com.bjike.goddess.taskallotment.service.CustomTitleSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义字段业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:35 ]
 * @Description: [ 自定义字段业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("customTitleApiImpl")
public class CustomTitleApiImpl implements CustomTitleAPI {
    @Autowired
    private CustomTitleSer customTitleSer;

    @Override
    public List<String> nodeId(CustomTitleDTO customTitleDTO) throws SerException {
        List<CustomTitle> list = customTitleSer.findByCis(customTitleDTO);
        return list.stream().map(CustomTitle::getTaskNodeId).distinct().collect(Collectors.toList());
    }
}