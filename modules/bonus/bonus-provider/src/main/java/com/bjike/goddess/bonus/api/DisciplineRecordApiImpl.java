package com.bjike.goddess.bonus.api;

import com.bjike.goddess.bonus.bo.*;
import com.bjike.goddess.bonus.service.DisciplineRecordSer;
import com.bjike.goddess.bonus.to.CollectFilterTO;
import com.bjike.goddess.bonus.to.DisciplineRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return disciplineRecordSer.projectRewardRank(to);
    }

    @Override
    public List<DisciplineRecordRankBO> projectPushRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.projectPushRank(to);
    }

    @Override
    public List<DisciplineRecordRankBO> personalRewardRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.personalRewardRank(to);
    }

    @Override
    public List<DisciplineRecordRankBO> personalPushRank(CollectFilterTO to) throws SerException {
        return disciplineRecordSer.personalPushRank(to);
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
}