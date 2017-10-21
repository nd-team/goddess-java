package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.ReHierarchyBO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.service.PositionDetailSer;
import com.bjike.goddess.organize.to.PositionDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 岗位详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("positionDetailApiImpl")
public class PositionDetailApiImpl implements PositionDetailAPI {

    @Autowired
    private PositionDetailSer positionDetailSer;

    @Override
    public List<PositionDetailBO> findStatus() throws SerException {
        return positionDetailSer.findStatus();
    }

    @Override
    public List<PositionDetailBO> findByPostIds(String[] ids) throws SerException {
        return positionDetailSer.findByPostIds(ids);

    }

    @Override
    public List<PositionDetailBO> findChildByArrangement(String postId) throws SerException {
        return positionDetailSer.findChildByArrangement(postId);
    }

    @Override
    public List<PositionDetailBO> findParentByArrangement(String postId) throws SerException {
        return positionDetailSer.findParentByArrangement(postId);
    }

    @Override
    public PositionDetailBO findBOById(String id) throws SerException {
        return positionDetailSer.findBOById(id);
    }

    @Override
    public PositionDetailBO save(PositionDetailTO to) throws SerException {
        return positionDetailSer.save(to);
    }

    @Override
    public PositionDetailBO update(PositionDetailTO to) throws SerException {
        return positionDetailSer.update(to);
    }

    @Override
    public PositionDetailBO congeal(String id) throws SerException {
        return positionDetailSer.congeal(id);
    }

    @Override
    public PositionDetailBO thaw(String id) throws SerException {
        return positionDetailSer.thaw(id);
    }

    @Override
    public List<PositionDetailBO> transformationToBOList(Collection<PositionDetail> list) throws SerException {
        return positionDetailSer.transformationToBOList(list);
    }

    @Override
    public PositionDetailBO delete(String id) throws SerException {
        return positionDetailSer.delete(id);
    }

    @Override
    public List<PositionDetailBO> maps(PositionDetailDTO dto) throws SerException {
        return positionDetailSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        return positionDetailSer.count(dto);
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        return positionDetailSer.findThawOpinion();
    }

    @Override
    public List<PositionDetailBO> findByDepartment(String... departmentIds) throws SerException {
        return positionDetailSer.findByDepartment(departmentIds);
    }

    @Override
    public List<OpinionBO> findByIds(String... ids) throws SerException {
        return positionDetailSer.findByIds(ids);
    }

    @Override
    public List<OpinionBO> findAllOpinion() throws SerException {
        return positionDetailSer.findAllOpinion();
    }

    @Override
    public List<String> getPositions(String id) throws SerException {
        return positionDetailSer.getPositions(id);
    }

    @Override
    public List<ReHierarchyBO> list() throws SerException {
        return positionDetailSer.list();
    }

    @Override
    public PositionDetailBO findByNumber(String serialNumber) throws SerException {
        return positionDetailSer.findByNumber(serialNumber);
    }

    @Override
    public PositionDetailBO findByPosition(String position) throws SerException {
        return positionDetailSer.findByPosition(position);
    }

    @Override
    public String number(PositionDetailTO to) throws SerException {
        return positionDetailSer.number(to);
    }

    @Override
    public List<String> positionNames() throws SerException {
        return positionDetailSer.positionNames();
    }
}
