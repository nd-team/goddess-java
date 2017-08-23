package com.bjike.goddess.bonus.api;

import com.bjike.goddess.bonus.bo.*;
import com.bjike.goddess.bonus.dto.DisciplineRecordDTO;
import com.bjike.goddess.bonus.service.DisciplineRecordSer;
import com.bjike.goddess.bonus.to.CollectFilterTO;
import com.bjike.goddess.bonus.to.DisciplineRecordTO;
import com.bjike.goddess.bonus.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 奖罚记录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("disciplineRecordApiImpl")
public class DisciplineRecordApiImpl implements DisciplineRecordAPI {

    @Autowired
    private DisciplineRecordSer disciplineRecordSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Override
    public Boolean sonPermission() throws SerException {
        return disciplineRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return disciplineRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public DisciplineRecordBO save(DisciplineRecordTO to) throws SerException {
        return disciplineRecordSer.save(to);
    }

    @Override
    public DisciplineRecordBO update(DisciplineRecordTO to) throws SerException {
        return disciplineRecordSer.update(to);
    }

    @Override
    public DisciplineRecordBO delete(String id) throws SerException {
        return disciplineRecordSer.delete(id);
    }

    @Override
    public List<DisciplineRecordRankBO> projectRewardRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.projectRank(to, true);
    }

    @Override
    public List<DisciplineRecordRankBO> projectPushRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.projectRank(to, false);
    }

    @Override
    public List<DisciplineRecordRankBO> personalRewardRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.personalRank(to, true);
    }

    @Override
    public List<DisciplineRecordRankBO> personalPushRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.personalRank(to, false);
    }

    @Override
    public List<DisciplineRecordDetailBO> disciplineDetailCollect(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.disciplineDetailCollect(to);
    }

    @Override
    public List<DisciplineRecordQuantityBO> disciplineQuantityCollect(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.disciplineQuantityCollect(to);
    }

    @Override
    public List<DisciplineRecordScoreBO> disciplineScoreCollect(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.disciplineScoreCollect(to);
    }

    @Override
    public List<DisciplineRecordBO> findByFilter(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.findByFilter(to);
    }

    @Override
    public List<DisciplineRecordBO> rewardMaps(DisciplineRecordDTO dto) throws SerException {
        return disciplineRecordSer.rewardMaps(dto);
    }

    @Override
    public List<DisciplineRecordBO> pushMaps(DisciplineRecordDTO dto) throws SerException {
        return disciplineRecordSer.pushMaps(dto);
    }

    @Override
    public DisciplineRecordBO getById(String id) throws SerException {
        return disciplineRecordSer.getById(id);
    }

    @Override
    public Long getRewardTotal() throws SerException {
        return disciplineRecordSer.getRewardTotal();
    }

    @Override
    public Long getPushTotal() throws SerException {
        return disciplineRecordSer.getPushTotal();
    }

    @Override
    public List<String> getName() throws SerException {
        List<String> list = new ArrayList<>(0);
        List<UserBO> userBOList = positionDetailUserAPI.findUserListInOrgan();
        if(null != userBOList && userBOList.size() > 0){
            for(UserBO userBO : userBOList){
                list.add(userBO.getUsername());
            }
        }
        return list;
    }

    @Override
    public List<String> getarea() throws SerException {
        return disciplineRecordSer.getarea();
    }

    @Override
    public List<String> getGroup() throws SerException {
        return disciplineRecordSer.getGroup();
    }

    @Override
    public List<String> getTarget() throws SerException {
        return disciplineRecordSer.getTarget();
    }

    @Override
    public Integer getPushNum(String userName) throws SerException {
        return disciplineRecordSer.getPushNum(userName);
    }

    @Override
    public Integer getRewardNum(String userName) throws SerException {
        return disciplineRecordSer.getRewardNum(userName);
    }

    @Override
    public ScoreBO getRePuTotal(String userName) throws SerException {
        return disciplineRecordSer.getRePuTotal(userName);
    }
    public String getRewardBallot(String name) throws SerException {
        return disciplineRecordSer.getRewardBallot(name);
    }

    @Override
    public String getPushBallot(String name) throws SerException {
        return disciplineRecordSer.getPushBallot(name);
    }
}