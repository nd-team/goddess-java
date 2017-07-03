package com.bjike.goddess.bonus.api;

import com.bjike.goddess.bonus.bo.*;
import com.bjike.goddess.bonus.dto.DisciplineRecordDTO;
import com.bjike.goddess.bonus.to.CollectFilterTO;
import com.bjike.goddess.bonus.to.DisciplineRecordTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 奖罚记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-10 11:54 ]
 * @Description: [ 奖罚记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DisciplineRecordAPI {


    /**
     * 保存
     *
     * @param to 奖罚记录传输对象
     * @return
     * @throws SerException
     */
    default DisciplineRecordBO save(DisciplineRecordTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 奖罚记录传输对象
     * @return
     * @throws SerException
     */
    default DisciplineRecordBO update(DisciplineRecordTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 奖罚记录传输对象
     * @return
     * @throws SerException
     */
    default DisciplineRecordBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 项目组奖励排名
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordRankBO> projectRewardRank(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 项目组处罚排名
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordRankBO> projectPushRank(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 个人奖励排名
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordRankBO> personalRewardRank(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 个人处罚排名
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordRankBO> personalPushRank(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 奖罚明细汇总
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordDetailBO> disciplineDetailCollect(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 奖罚人数汇总
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordQuantityBO> disciplineQuantityCollect(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 奖罚分数汇总
     *
     * @param to 汇总过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordScoreBO> disciplineScoreCollect(CollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 根据过滤条件查询奖罚记录
     *
     * @param to 过滤条件传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordBO> findByFilter(CollectFilterTO to) throws SerException {
        return null;
    }


    /**
     * 奖励列表
     *
     * @param dto 奖罚记录数据传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordBO> rewardMaps(DisciplineRecordDTO dto) throws SerException {
        return null;
    }

    /**
     * 处罚列表
     *
     * @param dto 奖罚记录数据传输对象
     * @return
     * @throws SerException
     */
    default List<DisciplineRecordBO> pushMaps(DisciplineRecordDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取奖罚记录
     *
     * @param id 奖罚记录数据id
     * @return
     * @throws SerException
     */
    default DisciplineRecordBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取奖励总条数
     *
     * @return
     * @throws SerException
     */
    default Long getRewardTotal() throws SerException {
        return null;
    }

    /**
     * 获取处罚总条数
     *
     * @return
     * @throws SerException
     */
    default Long getPushTotal() throws SerException {
        return null;
    }

}