package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.ManagerBO;
import com.bjike.goddess.organize.bo.OptionBO;
import com.bjike.goddess.organize.bo.PositionWorkDetailsBO;
import com.bjike.goddess.organize.dto.PositionWorkDetailsDTO;
import com.bjike.goddess.organize.excel.PositionWorkDetailsImport2;
import com.bjike.goddess.organize.service.PositionWorkDetailsSer;
import com.bjike.goddess.organize.to.PositionWorkDetailsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位工作明细表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("positionWorkDetailsApiImpl")
public class PositionWorkDetailsApiImpl implements PositionWorkDetailsAPI {

    @Autowired
    private PositionWorkDetailsSer positionWorkDetailsSer;

    @Override
    public void save(PositionWorkDetailsTO to) throws SerException {
        positionWorkDetailsSer.save(to);
    }

    @Override
    public void update(PositionWorkDetailsTO to) throws SerException {
        positionWorkDetailsSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        positionWorkDetailsSer.delete(id);
    }

    @Override
    public List<PositionWorkDetailsBO> maps(PositionWorkDetailsDTO dto) throws SerException {
        return positionWorkDetailsSer.maps(dto);
    }

    @Override
    public Long getTotal(PositionWorkDetailsDTO dto) throws SerException {
        return positionWorkDetailsSer.getTotal(dto);
    }

    @Override
    public PositionWorkDetailsBO findById(String id) throws SerException {
        return positionWorkDetailsSer.getById(id);
    }

    @Override
    public List<ManagerBO> weekCollect(String startTime, String endTime) throws SerException {
        return positionWorkDetailsSer.weekCollect(startTime, endTime);
    }

    @Override
    public List<ManagerBO> monthCollect(String month) throws SerException {
        return positionWorkDetailsSer.monthCollect(month);
    }

    @Override
    public List<ManagerBO> totalCollect() throws SerException {
        return positionWorkDetailsSer.totalCollect();
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return positionWorkDetailsSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(String month) throws SerException {
        return positionWorkDetailsSer.figureShowMonth(month);
    }

    @Override
    public OptionBO figureShowAll() throws SerException {
        return positionWorkDetailsSer.figureShowAll();
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        return positionWorkDetailsSer.figureShowDay(day);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return positionWorkDetailsSer.exportExcel();
    }

    @Override
    public void importExcel(List<PositionWorkDetailsImport2> tos) throws SerException {
        positionWorkDetailsSer.importExcel(tos);
    }
}