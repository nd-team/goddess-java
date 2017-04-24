package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 用户职位业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "organizeSerCache")
@Service
public class PositionDetailUserSerImpl extends ServiceImpl<PositionDetailUser, PositionDetailUserDTO> implements PositionDetailUserSer {

    @Autowired
    private PositionDetailSer positionDetailSer;
    @Autowired
    private PositionAPI positionAPI;
    @Autowired
    private UserAPI userAPI;

    private PositionDetailUserBO transformBO(PositionDetailUser entity) throws SerException {
        PositionDetailUserBO bo = BeanTransform.copyProperties(entity, PositionDetailUserBO.class);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUser_id()));
        List<UserBO> userBOs = userAPI.findByCis(userDTO);
        if (userBOs.size() > 0) {
            bo.setUsername(userBOs.get(0).getUsername());
            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
        }
        StringBuilder position_id = new StringBuilder(0), positionName = new StringBuilder(0);
        for (PositionDetail positionDetail : entity.getPositionSet()) {
            position_id.append(positionDetail.getId()).append(",");
            positionName.append(positionAPI.findById(positionDetail.getPosition_id()).getName()).append(",");
        }
        bo.setPosition(positionName.toString());
        bo.setPosition_ids(position_id.toString());
        return bo;
    }

    private List<PositionDetailUserBO> transformBOList(Collection<PositionDetailUser> list) throws SerException {
        List<PositionDetailUserBO> bos = new ArrayList<>(list.size());
        for (PositionDetailUser entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public PositionDetailUserBO save(PositionDetailUserTO to) throws SerException {
        PositionDetailUser entity = new PositionDetailUser();
        entity.setUser_id(to.getUser_id());
        entity.setPositionSet(new HashSet<>(0));
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUser_id()));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);
        if (userAPI.findByCis(userDTO).size() <= 0)
            throw new SerException("该用户不存在");
        if (null != to.getPosition_ids())
            for (String id : to.getPosition_ids())
                entity.getPositionSet().add(positionDetailSer.findById(id));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public PositionDetailUserBO update(PositionDetailUserTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq(ID, to.getUser_id()));
            if (userAPI.findByCis(userDTO).size() <= 0)
                throw new SerException("该用户不存在");
            try {
                PositionDetailUser entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setPositionSet(new HashSet<>(0));
                if (null != to.getPosition_ids())
                    for (String id : to.getPosition_ids())
                        entity.getPositionSet().add(positionDetailSer.findById(id));
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return this.transformBO(entity);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public PositionDetailUserBO delete(String id) throws SerException {
        PositionDetailUser entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<PositionDetailBO> findPositionByUser(String user_id) throws SerException {
        PositionDetailUser entity = this.findByUser(user_id);
        if (null != entity)
            return positionDetailSer.transformationToBOList(entity.getPositionSet());
        else
            return null;
    }

    @Override
    public PositionDetailUserBO findOneByUser(String user_id) throws SerException {
        PositionDetailUser entity = this.findByUser(user_id);
        if (null != entity)
            return this.transformBO(entity);
        else
            return null;
    }

    private PositionDetailUser findByUser(String user_id) throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        dto.getConditions().add(Restrict.eq("user_id", user_id));
        return super.findOne(dto);
    }

    @Override
    public Boolean checkAsUserPosition(String user_id, String[] position_ids) throws SerException {
        PositionDetailUser entity = this.findByUser(user_id);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : position_ids)
                    if (detail.getPosition_id().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserPositionDetail(String user_id, String[] position_ids) throws SerException {
        PositionDetailUser entity = this.findByUser(user_id);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : position_ids)
                    if (detail.getId().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserArrangement(String user_id, String arrangement_id) throws SerException {
        PositionDetailUser entity = this.findByUser(user_id);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                if (detail.getArrangement().getId().equals(arrangement_id))
                    return true;
        return false;
    }

    @Override
    public Boolean checkAsUserModule(String user_id, String module_id) throws SerException {
        PositionDetailUser entity = this.findByUser(user_id);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                if (detail.getModule().getId().equals(module_id))
                    return true;
        return false;
    }

    @Override
    public List<PositionDetailUserBO> maps(PositionDetailUserDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public PositionDetailUserBO getById(String id) throws SerException {
        return this.transformBO(super.findById(id));
    }
}