package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.service.PositionDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<PositionDetailBO> findByPostIds(List<String> ids) throws SerException {
        return positionDetailSer.findByPostIds(ids);

    }

    @Override
    public PositionDetailBO findByPostId(String id) throws SerException {
        return positionDetailSer.findByPostId(id);
    }

    @Override
    public List<PositionDetailBO> findChild(String parentId) throws SerException {
        return positionDetailSer.findChild(parentId);
    }

    @Override
    public PositionDetailBO findParent(String postId) throws SerException {
        return positionDetailSer.findParent(postId);
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
}
