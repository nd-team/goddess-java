package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.organize.service.PositionDetailUserSer;
import com.bjike.goddess.organize.to.PhoneLoginUserInfoTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public List<PositionDetailBO> findPositionByUser(String user_id) throws SerException {
        return positionDetailUserSer.findPositionByUser(user_id);
    }

    @Override
    public PositionDetailUserBO findOneByUser(String user_id) throws SerException {
        return positionDetailUserSer.findOneByUser(user_id);
    }

    @Override
    public Boolean checkAsUserPosition(String user_id, String[] position_ids) throws SerException {
        return positionDetailUserSer.checkAsUserPosit2(user_id, position_ids);
    }

    @Override
    public Boolean checkAsUserPosit2(String name, String[] position_ids) throws SerException {
        return positionDetailUserSer.checkAsUserPosit2(name,position_ids);
    }

//    @Override
//    public Boolean checkAsUserPosit2(String name, String[] position_ids) throws SerException {
//        return positionDetailUserSer.checkAsUserPosit2(name,position_ids);
//    }

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

    @Override
    public List<String> getPosition(String name) throws SerException {
        return positionDetailUserSer.getPosition(name);
    }

    @Override
    public List<String> getAllPositions() throws SerException {
        return positionDetailUserSer.getAllPositions();
    }

    @Override
    public List<String[]> getAllPosition() throws SerException {
        return positionDetailUserSer.getAllPosition();
    }

    @Override
    public List<String> getAllDepartment() throws SerException {
        return positionDetailUserSer.getAllDepartment();
    }

    @Override
    public List<PositionDetailBO> getPositionDetail(String name) throws SerException {
        return positionDetailUserSer.getPositionDetail(name);
    }

    @Override
    public StaffStatus statusByName(String name) throws SerException {
        return positionDetailUserSer.statusByName(name);
    }

    @Override
    public void save(PositionDetailUserTO to) throws SerException {
        positionDetailUserSer.save(to);
    }

    @Override
    public void update(PositionDetailUserTO to) throws SerException {
        positionDetailUserSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        positionDetailUserSer.delete(id);
    }

    @Override
    public PositionDetailUserBO getById(String id) throws SerException {
        return positionDetailUserSer.getById(id);
    }

    @Override
    public List<DepartPositionBO> departPositions() throws SerException {
        return positionDetailUserSer.departPositions();
    }

    @Override
    public PositionDetailUserBO bo(PositionDetailUser entity, Set<String> positions) throws SerException {
        return positionDetailUserSer.bo(entity,positions);
    }

    @Override
    public DepartmentDetailBO areaAndDepart(String userId) throws SerException {
        return positionDetailUserSer.areaAndDepart(userId);
    }
  @Override
    public Boolean isMarker(String userId) throws SerException {
        return positionDetailUserSer.isMarker(userId);
    }

    @Override
    public PhoneLoginUserInfoBO userLoginInfoByUserName(PhoneLoginUserInfoTO phoneLoginUserInfoTO) throws SerException {
        return positionDetailUserSer.userLoginInfoByUserName( phoneLoginUserInfoTO );
    }

    @Override
    public List<String> names() throws SerException {
        return positionDetailUserSer.names();
    }

    @Override
    public String customRepPerson() throws SerException {
        return positionDetailUserSer.customRepPerson();
    }

    @Override
    public String[] budgetPerson() throws SerException {
        return positionDetailUserSer.budgetPerson();
    }

    @Override
    public String[] planPerson() throws SerException {
        return positionDetailUserSer.planPerson();
    }
    @Override
    public String[] managerPerson() throws SerException{
        return positionDetailUserSer.managerPerson();
    }
    @Override
    public String[] generPerson() throws SerException{
        return positionDetailUserSer.generPerson();
    }
}