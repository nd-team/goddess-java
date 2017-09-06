package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.entity.PositionDetailUser;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
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
    private UserAPI userAPI;

    private PositionDetailUserBO transformBO(PositionDetailUser entity) throws SerException {
        PositionDetailUserBO bo = BeanTransform.copyProperties(entity, PositionDetailUserBO.class);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> userBOs = userAPI.findByCis(userDTO);
        if (null != userBOs && userBOs.size() > 0) {
            bo.setUsername(userBOs.get(0).getUsername());
            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
        }
        StringBuilder positionId = new StringBuilder(0), positionName = new StringBuilder(0);
        if (null != entity.getPositionSet())
            for (PositionDetail positionDetail : entity.getPositionSet()) {
                positionId.append(positionDetail.getId()).append(",");
                positionName.append(positionDetail.getPosition()).append(",");
            }
        bo.setPosition(positionName.toString());
        bo.setPositionIds(positionId.toString());
        return bo;
    }

    private List<PositionDetailUserBO> transformBOList(Collection<PositionDetailUser> list) throws SerException {
        List<PositionDetailUserBO> bos = new ArrayList<>(0);
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
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<PositionDetailBO> findPositionByUser(String userId) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity)
            return positionDetailSer.transformationToBOList(entity.getPositionSet());
        else
            return new ArrayList<>(0);
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
        if (null != entity && null != entity.getPositionSet() && null != positionIds)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : positionIds)
                    if (detail.getId().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserArrangement(String userId, String... arrangementIds) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity && null != entity.getPositionSet() && null != arrangementIds)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : arrangementIds)
                    if (detail.getArrangement().getId().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserDepartment(String userId, String... departmentIds) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity && null != entity.getPositionSet() && null != departmentIds)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : departmentIds)
                    if (detail.getDepartment().getId().equals(id))
                        return true;
        return false;
    }

    @Override
    public Boolean checkAsUserModule(String userId, String... moduleIds) throws SerException {
        PositionDetailUser entity = this.findByUser(userId);
        if (null != entity && null != entity.getPositionSet() && null != moduleIds)
            for (PositionDetail detail : entity.getPositionSet())
                for (String id : moduleIds)
                    if (detail.getModule().getId().equals(id))
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

    @Override
    public List<UserBO> findByPosition(String position_id) throws SerException {
        String[] fields = {"id", "userId"};
        String sql = "SELECT user_id,position_id FROM  organize_position_detail_user_table WHERE position_id ='%s'";
        List<PositionDetailUser> list = super.findBySql(String.format(sql, position_id), PositionDetailUser.class, fields);
        List<UserBO> bos = new ArrayList<>(0);
        for (PositionDetailUser entity : list) {
            UserDTO dto = new UserDTO();
            dto.getConditions().add(Restrict.eq(ID, super.findById(entity.getId()).getUserId()));
            List<UserBO> userBOs = userAPI.findByCis(dto);
            if (null != userBOs && userBOs.size() > 0)
                bos.add(userBOs.get(0));
        }
        return bos;
    }

    @Override
    public List<UserBO> findUserList() throws SerException {
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        return userAPI.findByCis(dto);
    }

    @Override
    public List<UserBO> findUserListInOrgan() throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        List<PositionDetailUser> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            List<String> userIds = new ArrayList<>();
            list.stream().forEach(str -> {
                userIds.add(str.getUserId());
            });
            String[] idStrs = userIds.toArray(new String[userIds.size()]);
            UserDTO userDTO = new UserDTO();
            userDTO.getConditions().add(Restrict.in("id", idStrs));
            List<UserBO> userList = userAPI.findByCis(userDTO);

            return userList;
        }
        return null;
    }

    @Override
    public List<String> getPosition(String name) throws SerException {
        //根据名字获取用户ｉｄ
        UserBO userBO = userAPI.findByUsername(name);
        String userId = "";
        if (null != userBO) {
            userId = userBO.getId();
        }
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("userId", userId));
        List<PositionDetailUser> positionDetailUserList = super.findByCis(positionDetailUserDTO);
        List<String> positionList = new ArrayList<>(0);
        if (null != positionDetailUserList && positionDetailUserList.size() > 0) {
            PositionDetailUser positionDetailUser = positionDetailUserList.get(0);
            for (PositionDetail positionDetail : positionDetailUser.getPositionSet()) {
                positionList.add(positionDetail.getPosition());
            }
        }
        return positionList;
    }

    @Override
    public List<String> getAllPositions() throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        List<PositionDetailUserBO> positionDetailUserBOList = maps(dto);
        List<String> stringList = new ArrayList<>();
        if (null != positionDetailUserBOList && positionDetailUserBOList.size() > 0) {
            for (PositionDetailUserBO bo : positionDetailUserBOList) {
                String str = "";
                str = bo.getPosition();
                stringList.add(str);
            }
        }
        return stringList;
    }

    @Override
    public List<String[]> getAllPosition() throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        List<PositionDetailUserBO> positionDetailUserBOList = maps(dto);
        List<String[]> stringList = new ArrayList<>();
        if (null != positionDetailUserBOList && positionDetailUserBOList.size() > 0) {
            String string[] = new String[positionDetailUserBOList.size()];
            for (PositionDetailUserBO bo : positionDetailUserBOList) {
                string = bo.getPosition().split(";");
                stringList.add(string);
            }
        }
        return stringList;
    }

    @Override
    public List<String> getAllDepartment() throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        List<PositionDetailBO> positionDetailBOList = positionDetailSer.maps(dto);
        List<String> list = new ArrayList<>();
        if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
            for (PositionDetailBO bo : positionDetailBOList) {
                list.add(bo.getDepartmentName());
            }
        }
        return list;
    }

//    @Override
//    public List<PositionDetailBO> getPositionDetail(String name) throws SerException {
//        //根据名字获取用户ｉｄ
//        UserBO userBO = userAPI.findByUsername(name);
//        String userId = "";
//        if (null != userBO) {
//            userId = userBO.getId();
//        }
//        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
//        positionDetailUserDTO.getConditions().add(Restrict.eq("userId", userId));
//        List<PositionDetailUser> positionDetailUserList = super.findByCis(positionDetailUserDTO);
//        List<PositionDetail> positionList = new ArrayList<>(0);
//        List<PositionDetailBO> positionDetailBOs1 = new ArrayList<>(0);
//        if (null != positionDetailUserList && positionDetailUserList.size() > 0) {
//            PositionDetailUser positionDetailUser = positionDetailUserList.get(0);
//            Set<PositionDetail> positionDetailSet = positionDetailUser.getPositionSet();
//            Iterator it=positionDetailSet.iterator();
//            while(it.hasNext()){
//                positionList.add((PositionDetail) it.next());
//            }
//            List<PositionDetailBO> positionBOList = BeanTransform.copyProperties(positionList,PositionDetailBO.class);
//            //查询未冻结数据
//            if(null != positionBOList && positionBOList.size() > 0){
//                for(PositionDetailBO bo : positionBOList){
//                    if(Status.THAW.equals(bo.getStatus())){
//                        positionDetailBOs1.add(BeanTransform.copyProperties(bo,PositionDetailBO.class));
//                    }
//                }
//            }
//        }
//        return positionDetailBOs1;
//    }

    @Override
    public List<PositionDetailBO> getPositionDetail(String name) throws SerException {
//        根据名字获取用户ｉｄ
        UserBO userBO = userAPI.findByUsername(name);
        String userId = "";
        if (null != userBO) {
            userId = userBO.getId();
        }
        String fields[] = new String[]{"pool", "serialNumber", "staff", "arrangementId", "departmentId", "description", "position", "status", "moduleId"
//                ,"position_id","user_id"
        };
        userId = "00a94d4e-5c1f-4ed9-8a1b-e9e5811c1c0e";
        StringBuilder sql = new StringBuilder("SELECT pool, serialNumber, staff, arrangement_id, department_id, description, position, status, module_id");
        sql.append(" FROM organize_position_detail a,");
        sql.append(" organize_position_detail_user_table b ");
        sql.append(" WHERE a.id=b.position_id ");
        sql.append(" AND b.user_id= '" + userId + "'");
        List<PositionDetailBO> positionDetails = positionDetailSer.findBySql(sql.toString(), PositionDetailBO.class, fields);
        List<PositionDetailBO> list = BeanTransform.copyProperties(positionDetails, PositionDetailBO.class);
        return list;
    }
}