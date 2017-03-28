package com.bjike.goddess.competitormanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitormanage.bo.CollectionTotalBO;
import com.bjike.goddess.competitormanage.bo.CompetitorCollectBO;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.service.CompetitorCollectSer;
import com.bjike.goddess.competitormanage.to.CompetitorCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 竞争对手汇总业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("competitorCollectApiImpl")
public class CompetitorCollectApiImpl implements CompetitorCollectAPI {

    @Autowired
    private CompetitorCollectSer competitorCollectSer;

    @Override
    public CompetitorCollectBO save(CompetitorCollectTO to) throws SerException {
        return competitorCollectSer.saveModel(to);
    }

    @Override
    public CompetitorCollectBO edit(CompetitorCollectTO to) throws SerException {
        return competitorCollectSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        competitorCollectSer.remove(id);
    }

    @Override
    public void freeze(String id) throws SerException {
        competitorCollectSer.freeze(id);
    }

    @Override
    public void breakFreeze(String id) throws SerException {
        competitorCollectSer.breakFreeze(id);
    }

    @Override
    public List<CollectionTotalBO> collectionTotal() throws SerException {
        return competitorCollectSer.collectionTotal();
    }

    @Override
    public List<CompetitorCollectBO> pageList(CompetitorCollectDTO dto) throws SerException {
        return competitorCollectSer.pageList(dto);
    }
}