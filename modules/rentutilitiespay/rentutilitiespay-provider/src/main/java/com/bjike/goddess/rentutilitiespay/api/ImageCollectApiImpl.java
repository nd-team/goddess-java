package com.bjike.goddess.rentutilitiespay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentutilitiespay.bo.OptionBO;
import com.bjike.goddess.rentutilitiespay.service.ImageCollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-23 16:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("imageCollectApiImpl")
public class ImageCollectApiImpl implements ImageCollectAPI {

    @Autowired
    private ImageCollectSer imageCollectSer;
    @Override
    public OptionBO firgureShowMonth(Integer year, Integer month) throws SerException {
        return imageCollectSer.firgureShowMonth(year,month);
    }

    @Override
    public OptionBO figrueShowYear(Integer year) throws SerException {
        return imageCollectSer.figrueShowYear(year);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return imageCollectSer.figureShowTotal(endDate);
    }
}
