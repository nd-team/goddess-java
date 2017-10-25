package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.OptionBO;
import com.bjike.goddess.dispatchcar.service.ImageCollectSer;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-19 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("imageCollectApiImpl")
public class ImageCollectApiImpl implements ImageColletcAPI{
    @Autowired
    private ImageCollectSer imageCollectSer;
    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        return imageCollectSer.figureShowDay(day);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return imageCollectSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return imageCollectSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowTotal(String day) throws SerException {
        return imageCollectSer.figureShowTotal(day);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return imageCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return imageCollectSer.guidePermission(guidePermissionTO);
    }
}
