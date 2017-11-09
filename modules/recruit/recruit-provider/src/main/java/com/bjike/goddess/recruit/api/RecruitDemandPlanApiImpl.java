package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.*;
import com.bjike.goddess.recruit.dto.RecruitDemandPlanDTO;
import com.bjike.goddess.recruit.service.RecruitDemandPlanSer;
import com.bjike.goddess.recruit.to.ChannelTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘需求与计划业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("recruitDemandPlanApiImpl")
public class RecruitDemandPlanApiImpl implements RecruitDemandPlanAPI {
    @Autowired
    private RecruitDemandPlanSer recruitDemandPlanSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return recruitDemandPlanSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return recruitDemandPlanSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(RecruitDemandPlanDTO dto) throws SerException {
        return recruitDemandPlanSer.count(dto);
    }

    @Override
    public RecruitDemandPlanBO getId(String id) throws SerException {
        return recruitDemandPlanSer.getId(id);
    }

    @Override
    public List<RecruitDemandPlanBO> list(RecruitDemandPlanDTO dto) throws SerException {
        return recruitDemandPlanSer.list(dto);
    }

    @Override
    public RecruitDemandPlanBO save(RecruitDemandPlanTO to) throws SerException {
        return recruitDemandPlanSer.save(to);
    }

    @Override
    public void remove(String id) throws SerException {
        recruitDemandPlanSer.remove(id);
    }

    @Override
    public RecruitDemandPlanBO update(RecruitDemandPlanTO to) throws SerException {
        return recruitDemandPlanSer.update(to);
    }

    @Override
    public RecruitDemandPlanBO makePlan(RecruitDemandPlanTO to) throws SerException {
        return recruitDemandPlanSer.makePlan(to);
    }

    @Override
    public List<RecruitProgressBO> dayCollect(String time) throws SerException {
        return recruitDemandPlanSer.dayCollect(time);
    }

    @Override
    public List<RecruitProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return recruitDemandPlanSer.weekCollect(year, month, week);
    }

    @Override
    public List<RecruitProgressBO> monthCollect(Integer year, Integer month) throws SerException {
        return recruitDemandPlanSer.monthCollect(year, month);
    }

    @Override
    public List<RecruitProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        return recruitDemandPlanSer.quarterCollect(year, quarter);
    }

    @Override
    public List<RecruitProgressBO> yearCollect(Integer year) throws SerException {
        return recruitDemandPlanSer.yearCollect(year);
    }

    @Override
    public List<RecruitProgressBO> totalCollect(String time) throws SerException {
        return recruitDemandPlanSer.totalCollect(time);
    }

    @Override
    public List<RecruitDetailsBO> dayRecruit(String time) throws SerException {
        return recruitDemandPlanSer.dayRecruit(time);
    }

    @Override
    public List<RecruitDetailsBO> weekRecruit(Integer year, Integer month, Integer week) throws SerException {
        return recruitDemandPlanSer.weekRecruit(year, month, week);
    }

    @Override
    public List<RecruitDetailsBO> monthRecruit(Integer year, Integer month) throws SerException {
        return recruitDemandPlanSer.monthRecruit(year, month);
    }
    @Override
    public OptionBO channelCollect(ChannelTO to) throws SerException {
        return recruitDemandPlanSer.channelCollect(to);
    } @Override
    public OptionPieBO faceFigure(ChannelTO to) throws SerException {
        return recruitDemandPlanSer.faceFigure(to);
    }

    @Override
    public OptionPieBO entryFigure(ChannelTO to) throws SerException {
        return recruitDemandPlanSer.entryFigure(to);
    }
    @Override
    public List<String> getPosition() throws SerException{
        return recruitDemandPlanSer.getPosition();
    }

}