package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.FilesDataBO;
import com.bjike.goddess.marketdevelopment.service.FilesDataSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 阶段表头数据业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 04:30 ]
* @Description:	[ 阶段表头数据业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("filesDataApiImpl")
public class FilesDataApiImpl implements FilesDataAPI  {
    @Autowired
    private FilesDataSer filesDataSer;

    @Override
    public FilesDataBO findFiles() throws SerException {
        return filesDataSer.findFiles();
    }
}