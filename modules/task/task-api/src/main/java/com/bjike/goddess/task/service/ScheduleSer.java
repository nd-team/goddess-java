package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.enums.CollectType;
import com.bjike.goddess.task.to.CustomizeTO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-22 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ScheduleSer {

    /**
     * html
     * @param dto
     * @return
     * @throws SerException
     */
    String html(CollectDTO dto) throws SerException;

    /**
     * 汇总方案汇总
     * @param result
     * @param dto
     * @param cutomizeId
     * @param count
     * @param initas
     * @param charges
     * @param excutes
     * @throws SerException
     */
    void htmlScheme(StringBuilder result, CollectDTO dto, String cutomizeId, List<Long> count, Set<String> initas, Set<String> charges, Set<String> excutes) throws SerException;

    /**
     * 固定表头
     * @param sb
     * @throws SerException
     */
    void fixTitleScheme(StringBuilder sb) throws SerException;

    /**
     * 构建项目汇总html
     * @param collect
     * @param type
     * @return
     * @throws SerException
     */
    default String buildCollectHtml(Collect collect, CollectType type) throws SerException {
        return null;
    }

    /**
     * 自定义汇总
     *
     * @return
     * @throws SerException
     */
    default void customizeCollect(Customize customize) throws SerException {
    }

    /**
     * 字段对应的值
     * @param to
     * @return
     * @throws SerException
     */
    Set<String> values(CustomizeTO to) throws SerException;
}
