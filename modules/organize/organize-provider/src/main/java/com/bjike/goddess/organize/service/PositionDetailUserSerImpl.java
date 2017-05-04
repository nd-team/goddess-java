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
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> userBOs = userAPI.findByCis(userDTO);
        if (userBOs.size() > 0) {
            bo.setUsername(userBOs.get(0).getUsername());
            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
        }
        StringBuilder positionId = new StringBuilder(0), positionName = new StringBuilder(0);
        for (PositionDetail positionDetail : entity.getPositionSet()) {
            positionId.append(positionDetail.getId()).append(",");
            positionName.append(positionDetail.getPosition()).append(",");
        }
        bo.setPosition(positionName.toString());
        bo.setPositionIds(positionId.toString());
        return bo;
    }

    private List<PositionDetailUserBO> transformBOList(Collection<PositionDetailUser> list) throws SerException {
        List<PositionDetailUserBO> bos = new ArrayList<>(list.size());
        for (PositionDetailUser entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    /**
     * 检测用户职位内是否有重复用户
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(PositionDetailUserTO to) throws SerException {
        if (this.findByUser(to.getUserId()) != null)
            throw new SerException("该用户已存在,无法保存");
    }

    @Override
    public PositionDetailUserBO save(PositionDetailUserTO to) throws SerException {
        this.checkUnique(to);
        PositionDetailUser entity = new PositionDetailUser();
        entity.setUserId(to.getUserId());
        entity.setPositionSet(new HashSet<>(0));
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);
        if (null == userBOList || userBOList.size() <= 0)
            throw new SerException("该用户不存在");
        if (null != to.getPositionIds())
            for (String id : to.getPositionIds())
                entity.getPositionSet().add(positionDetailSer.findById(id));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public PositionDetailUserBO update(PositionDetailUserTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.eq(ID, to.getUserId()));
            List<UserBO> userBOList = userAPI.findByCis(userDTO);
            if (null == userBOList || userBOList.size() <= 0)
                throw new SerException("该用户不存在");
            PositionDetailUser entity = super.findById(to.getId());
            if (!entity.getUserId().equals(to.getUserId()))
                this.checkUnique(to);
            try {
                BeanTransform.copyProperties(to, entity, true);
                entity.setPositionSet(new HashSet<>(0));
                if (null != to.getPositionIds())
                    for (String id : to.getPositionIds())
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
    public List<PositionDetailBO> findPositionByUser(String userId) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            return positionDetailSer.transformationToBOList(entity.getPositionSet());
        else
            return null;
    }

    @Override
    public PositionDetailUserBO findOneByUser(String userId) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            return this.transformBO(entity);
        else
            return null;
    }

    private PositionDetailUser findByUser(String userId) throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        return super.findOne(dto);
    }

    @Override
    public Boolean checkAsUserPosition(String userId, String[] positionIds) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : positionIds)
                    if (detail.getPosition().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserPositionDetail(String userId, String[] positionIds) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : positionIds)
                    if (detail.getId().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserArrangement(String userId, String arrangementId) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                if (detail.getArrangement().getId().equals(arrangementId))
                    return true;
        return false;
    }

    @Override
    public Boolean checkAsUserModule(String userId, String moduleId) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            for (PositionDetail detail : entity.getPositionSet())
                if (detail.getModule().getId().equals(moduleId))
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