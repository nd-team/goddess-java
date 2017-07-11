package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.service.PositionDetailUserSer;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户职位业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("positionDetailUserApiImpl")
public class PositionDetailUserApiImpl implements PositionDetailUserAPI {

    @Autowired
    private PositionDetailUserSer positionDetailUserSer;

    @Override
    public PositionDetailUserBO save(PositionDetailUserTO to) throws SerException {
        return positionDetailUserSer.save(to);
    }

    @Override
    public PositionDetailUserBO update(PositionDetailUserTO to) throws SerException {
        return positionDetailUserSer.update(to);
    }

    @Override
    public PositionDetailUserBO delete(String id) throws SerException {
        return positionDetailUserSer.delete(id);
    }

    @Override
    public List<PositionDetailBO> findPositionByUser(String user_id) throws SerException {
        return positionDetailUserSer.findPositionByUser(user_id);
    }

    @Override
    public PositionDetailUserBO findOneByUser(String user_id) throws SerException {
        return positionDetailUserSer.findOneByUser(user_id);
    }

    @Override
    public Boolean checkAsUserPosition(String user_id, String[] position_ids) throws SerException {
        return positionDetailUserSer.checkAsUserPosition(user_id, position_ids);
    }

    @Override
    public Boolean checkAsUserArrangement(String user_id, String... arrangement_id) throws SerException {
        return positionDetailUserSer.checkAsUserArrangement(user_id, arrangement_id);
    }

    @Override
    public Boolean checkAsUserModule(String user_id, String... module_id) throws SerException {
        return positionDetailUserSer.checkAsUserModule(user_id, module_id);
    }

    @Override
    public Boolean checkAsUserDepartment(String userId, String... departmentIds) throws SerException {
        return positionDetailUserSer.checkAsUserDepartment(userId, departmentIds);
    }

    @Override
    public List<PositionDetailUserBO> maps(PositionDetailUserDTO dto) throws SerException {
        return positionDetailUserSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        return positionDetailUserSer.count(dto);
    }

    @Override
    public PositionDetailUserBO findById(String id) throws SerException {
        return positionDetailUserSer.getById(id);
    }

    @Override
    public List<UserBO> findByPosition(String position_id) throws SerException {
        return positionDetailUserSer.findByPosition(position_id);
    }

    @Override
    public List<UserBO> findUserList() throws SerException {
        return positionDetailUserSer.findUserList();
    }

    @Override
    public List<UserBO> findUserListInOrgan() throws SerException {
        return positionDetailUserSer.findUserListInOrgan();
    }


}