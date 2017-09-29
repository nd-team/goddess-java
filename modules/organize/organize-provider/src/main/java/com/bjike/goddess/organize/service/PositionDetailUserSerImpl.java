package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.dto.PositionUserDetailDTO;
import com.bjike.goddess.organize.entity.*;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.organize.enums.WorkStatus;
import com.bjike.goddess.organize.to.PhoneLoginUserInfoTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.organize.to.PositionUserDetailTO;
import com.bjike.goddess.organize.vo.PhoneLoginUserInfoVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.UserDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private PositionUserDetailSer positionUserDetailSer;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;

    private PositionDetailUserBO transformBO(PositionDetailUser entity) throws SerException {
        PositionDetailUserBO bo = BeanTransform.copyProperties(entity, PositionDetailUserBO.class);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> userBOs = userAPI.findByCis(userDTO);
        if (null != userBOs && userBOs.size() > 0) {
            bo.setUsername(userBOs.get(0).getUsername());
            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
        }
//        StringBuilder positionId = new StringBuilder(0), positionName = new StringBuilder(0);
//        if (null != entity.getPositionSet())
//            for (PositionDetail positionDetail : entity.getPositionSet()) {
//                positionId.append(positionDetail.getId()).append(",");
//                positionName.append(positionDetail.getPosition()).append(",");
//            }
//        bo.setPosition(positionName.toString());
//        bo.setPositionIds(positionId.toString());
        String userId = entity.getUserId();
        List<PositionUserDetailBO> detailBOS = new ArrayList<>();
        if (null != entity.getPositionSet()) {
            for (PositionDetail positionDetail : entity.getPositionSet()) {
                PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                detailDTO.getConditions().add(Restrict.eq("userId", userId));
                detailDTO.getConditions().add(Restrict.eq("positionId", positionDetail.getId()));
                PositionUserDetail detail = positionUserDetailSer.findOne(detailDTO);
                PositionUserDetailBO detailBO = new PositionUserDetailBO();
                if (null != detail) {
                    detailBO = BeanTransform.copyProperties(detail, PositionUserDetailBO.class);
                }
                detailBO.setHierarchyNumber(positionDetail.getDepartment().getHierarchy().getSerialNumber());
                detailBO.setHierarchy(positionDetail.getDepartment().getHierarchy().getHierarchy());
                detailBO.setArea(positionDetail.getDepartment().getArea());
                detailBO.setDepartNumber(positionDetail.getDepartment().getSerialNumber());
                detailBO.setDepartment(positionDetail.getDepartment().getDepartment());
                detailBO.setArrangement(positionDetail.getArrangement().getArrangement());
                if (null != positionDetail.getModule()) {
                    detailBO.setModule(positionDetail.getModule().getModule());
                }
                detailBO.setPosition(positionDetail.getPosition());
                detailBO.setPositionId(positionDetail.getId());
                detailBO.setPositionNumber(positionDetail.getSerialNumber());
                detailBOS.add(detailBO);
            }
        }
        bo.setDetailS(detailBOS);
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
    public void save(PositionDetailUserTO to) throws SerException {
        this.checkUnique(to);
        PositionDetailUser entity = new PositionDetailUser();
        entity.setUserId(to.getUserId());
        entity.setPositionSet(new HashSet<>(0));
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> userBOList = userAPI.findByCis(userDTO);
        if (null == userBOList || userBOList.size() <= 0)
            throw new SerException("该用户不存在");
        List<PositionUserDetailTO> detailTOS = to.getDetailTOS();
        if (null != detailTOS) {
            long num = detailTOS.stream().filter(positionUserDetailTO -> WorkStatus.MAIN.equals(positionUserDetailTO.getWorkStatus())).count();
            if (num < 1) {
                throw new SerException("一个员工必须要有一个主职");
            }
            if (num > 1) {
                throw new SerException("一个员工只能有一个主职");
            }
            for (PositionUserDetailTO detailTO : detailTOS) {
                String positionId = detailTO.getPositionId();
                entity.getPositionSet().add(positionDetailSer.findById(positionId));
                PositionUserDetail positionUserDetail = BeanTransform.copyProperties(detailTO, PositionUserDetail.class, true, "id");
                positionUserDetail.setUserId(to.getUserId());
                positionUserDetailSer.save(positionUserDetail);
            }
        }
        entity.setStaffStatus(to.getStaffStatus());
        super.save(entity);
//        return this.transformBO(entity);
    }

    @Override
    public void update(PositionDetailUserTO to) throws SerException {
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
                String userId = to.getUserId();
                PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                detailDTO.getConditions().add(Restrict.eq("userId", userId));
                List<PositionUserDetail> list = positionUserDetailSer.findByCis(detailDTO);
                positionUserDetailSer.remove(list);
                List<PositionUserDetailTO> detailTOS = to.getDetailTOS();
                if (null != detailTOS) {
                    long num = detailTOS.stream().filter(positionUserDetailTO -> WorkStatus.MAIN.equals(positionUserDetailTO.getWorkStatus())).count();
                    if (num < 1) {
                        throw new SerException("一个员工必须要有一个主职");
                    }
                    if (num > 1) {
                        throw new SerException("一个员工只能有一个主职");
                    }
                    for (PositionUserDetailTO detailTO : detailTOS) {
                        entity.getPositionSet().add(positionDetailSer.findById(detailTO.getPositionId()));
                        PositionUserDetail positionUserDetail = BeanTransform.copyProperties(detailTO, PositionUserDetail.class, true, "id");
                        positionUserDetail.setUserId(to.getUserId());
                        positionUserDetailSer.save(positionUserDetail);
                    }
                }
                entity.setStaffStatus(to.getStaffStatus());
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
//                return this.transformBO(entity);

            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new

                    SerException("数据ID不能为空");

    }

    @Override
    public void delete(String id) throws SerException {
        PositionDetailUser entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        String userId = entity.getUserId();
        PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
        detailDTO.getConditions().add(Restrict.eq("userId", userId));
        List<PositionUserDetail> list = positionUserDetailSer.findByCis(detailDTO);
        positionUserDetailSer.remove(list);
        super.remove(entity);
//        return this.transformBO(entity);
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
        dto.getSorts().add("createTime=desc");
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
                List<PositionUserDetailBO> detailBOS = bo.getDetailS();
                List<String> list = detailBOS.stream().map(positionUserDetailBO -> positionUserDetailBO.getPosition()).collect(Collectors.toList());
                stringList.addAll(list);
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
            for (PositionDetailUserBO bo : positionDetailUserBOList) {
                List<PositionUserDetailBO> detailBOS = bo.getDetailS();
                List<String> list = detailBOS.stream().map(positionUserDetailBO -> positionUserDetailBO.getPosition()).collect(Collectors.toList());
                String[] strings = new String[list.size()];
                strings = list.toArray(strings);
                stringList.add(strings);
            }
        }
        return stringList;
    }

    @Override
    public List<String> getAllDepartment() throws SerException {
        List<DepartmentDetail> departmentDetails = departmentDetailSer.findAll();
        if (null != departmentDetails && departmentDetails.size() > 0) {
            return departmentDetails.stream().map(DepartmentDetail::getDepartment).distinct().collect(Collectors.toList());
        }
        return null;
//        PositionDetailDTO dto = new PositionDetailDTO();
//        List<PositionDetailBO> positionDetailBOList = positionDetailSer.maps(dto);
//        List<String> list = new ArrayList<>();
//        if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
//            for (PositionDetailBO bo : positionDetailBOList) {
//                list.add(bo.getDepartmentName());
//            }
//        }
//        return list;
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
//        userId = "00a94d4e-5c1f-4ed9-8a1b-e9e5811c1c0e";
        StringBuilder sql = new StringBuilder("SELECT pool, serialNumber, staff, arrangement_id, department_id, description, position, status, module_id");
        sql.append(" FROM organize_position_detail a,");
        sql.append(" organize_position_detail_user_table b ");
        sql.append(" WHERE a.id=b.position_id ");
        sql.append(" AND b.user_id= '" + userId + "'");
        List<PositionDetailBO> positionDetails = positionDetailSer.findBySql(sql.toString(), PositionDetailBO.class, fields);
        List<PositionDetailBO> list = BeanTransform.copyProperties(positionDetails, PositionDetailBO.class);
        return list;
    }
//        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
//        positionDetailUserDTO.getConditions().add(Restrict.eq("userId", userId));
//        List<PositionDetailUser> positionDetailUserList = super.findByCis(positionDetailUserDTO);
//        List<PositionDetail> positionList = new ArrayList<>(0);
//        List<PositionDetailBO> positionDetailBOs1 = new ArrayList<>(0);
//        if (null != positionDetailUserList && positionDetailUserList.size() > 0) {
//            PositionDetailUser positionDetailUser = positionDetailUserList.get(0);
//            Set<PositionDetail> positionDetailSet = positionDetailUser.getPositionSet();
//            Iterator it = positionDetailSet.iterator();
//            while (it.hasNext()) {
//                positionList.add((PositionDetail) it.next());
//            }
//            List<PositionDetailBO> positionBOList = BeanTransform.copyProperties(positionList, PositionDetailBO.class);
//            //查询未冻结数据
//            if (null != positionBOList && positionBOList.size() > 0) {
//                for (PositionDetailBO bo : positionBOList) {
//                    if (Status.THAW.equals(bo.getStatus())) {
//                        positionDetailBOs1.add(BeanTransform.copyProperties(bo, PositionDetailBO.class));
//                    }
//                }
//            }
//        }
//        return positionDetailBOs1;
//    }

    @Override
    public List<DepartPositionBO> departPositions() throws SerException {
        DepartmentDetailDTO departmentDetailDTO = new DepartmentDetailDTO();
        departmentDetailDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<DepartmentDetail> list = departmentDetailSer.findByCis(departmentDetailDTO);
        List<DepartPositionBO> bos = new ArrayList<>();
        for (DepartmentDetail depart : list) {
            PositionDetailDTO positionDetailDTO = new PositionDetailDTO();
            positionDetailDTO.getConditions().add(Restrict.eq("department.id", depart.getId()));
            positionDetailDTO.getConditions().add(Restrict.eq("status", Status.THAW));
            List<PositionDetail> positionDetails = positionDetailSer.findByCis(positionDetailDTO);
            List<PositionDetailBO> positions = BeanTransform.copyProperties(positionDetails, PositionDetailBO.class);
            DepartPositionBO bo = new DepartPositionBO();
            bo.setDepartment(depart.getDepartment());
            bo.setPositions(positions);
            bos.add(bo);
        }
        return bos;
    }

    @Override
    public StaffStatus statusByName(String name) throws SerException {
        //根据名字获取用户ｉｄ
        StaffStatus staffStatus = StaffStatus.HAVELEAVE;
        UserBO userBO = userAPI.findByUsername(name);
        if (null != userBO) {
            String userId = userBO.getId();
            PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
            positionDetailUserDTO.getConditions().add(Restrict.eq("userId", userId));
            PositionDetailUser positionDetailUser = super.findOne(positionDetailUserDTO);
            staffStatus = positionDetailUser.getStaffStatus();
        }
        return staffStatus;
    }

    @Override
    public PositionDetailUserBO bo(PositionDetailUser entity, Set<String> positions) throws SerException {
        PositionDetailUserBO bo = BeanTransform.copyProperties(entity, PositionDetailUserBO.class);
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
        List<UserBO> userBOs = userAPI.findByCis(userDTO);
        if (null != userBOs && userBOs.size() > 0) {
            bo.setUsername(userBOs.get(0).getUsername());
            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
        }
//        StringBuilder positionId = new StringBuilder(0), positionName = new StringBuilder(0);
//        if (null != entity.getPositionSet())
//            for (PositionDetail positionDetail : entity.getPositionSet()) {
//                positionId.append(positionDetail.getId()).append(",");
//                positionName.append(positionDetail.getPosition()).append(",");
//            }
//        bo.setPosition(positionName.toString());
//        bo.setPositionIds(positionId.toString());
        String userId = entity.getUserId();
        List<PositionUserDetailBO> detailBOS = new ArrayList<>();
        if (null != entity.getPositionSet()) {
            for (PositionDetail positionDetail : entity.getPositionSet()) {
                if (positions.contains(positionDetail.getId())) {
                    PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                    detailDTO.getConditions().add(Restrict.eq("userId", userId));
                    detailDTO.getConditions().add(Restrict.eq("positionId", positionDetail.getId()));
                    PositionUserDetail detail = positionUserDetailSer.findOne(detailDTO);
                    PositionUserDetailBO detailBO = new PositionUserDetailBO();
                    if (null != detail) {
                        detailBO = BeanTransform.copyProperties(detail, PositionUserDetailBO.class);
                    }
                    detailBO.setHierarchyNumber(positionDetail.getDepartment().getHierarchy().getSerialNumber());
                    detailBO.setHierarchy(positionDetail.getDepartment().getHierarchy().getHierarchy());
                    detailBO.setArea(positionDetail.getDepartment().getArea());
                    detailBO.setDepartNumber(positionDetail.getDepartment().getSerialNumber());
                    detailBO.setDepartment(positionDetail.getDepartment().getDepartment());
                    detailBO.setArrangement(positionDetail.getArrangement().getArrangement());
                    if (null != positionDetail.getModule()) {
                        detailBO.setModule(positionDetail.getModule().getModule());
                    }
                    detailBO.setPosition(positionDetail.getPosition());
                    detailBO.setPositionNumber(positionDetail.getSerialNumber());
                    detailBOS.add(detailBO);
                }
            }
        }
        bo.setDetailS(detailBOS);
        List<PositionUserDetailBO> bos = bo.getDetailS();
        if (!bos.isEmpty()) {
            return bo;
        }
        return null;
    }

    @Override
    //chenjunhao
    public DepartmentDetailBO areaAndDepart(String userId) throws SerException {
        PositionDetailUserDTO detailUserDTO = new PositionDetailUserDTO();
        detailUserDTO.getConditions().add(Restrict.eq("userId", userId));
        List<PositionDetailUser> list = super.findByCis(detailUserDTO);
        if (!list.isEmpty()) {
            String id = list.get(0).getId();
            String sql = "SELECT position_id id FROM organize_position_detail_user_table WHERE user_id = '" + id + "'";
            String[] strings = new String[]{"id"};
            List<PositionDetailBO> positionDetailBOS = super.findBySql(sql, PositionDetailBO.class, strings);
            if (null != positionDetailBOS && !positionDetailBOS.isEmpty()) {
                String pId=positionDetailBOS.get(0).getId();
                String sql1 = "SELECT area,department " +
                        "FROM organize_department_detail " +
                        "WHERE id =" +
                        "      (SELECT department_id " +
                        "       FROM organize_position_detail " +
                        "       WHERE id = " + pId + ")";
                String[] fileds = new String[]{"area", "department"};
                List<DepartmentDetailBO> areas = super.findBySql(sql1, DepartmentDetailBO.class, fileds);
                if (null != areas && !areas.isEmpty()) {
                    return areas.get(0);
                }
            }
        }
        return null;
    }
 @Override
    public Boolean isMarker(String userId) throws SerException {
        Boolean tar = false;
        String[] fildes = new String[]{"id"};
//        userId = "3348a9a1-bfcb-4799-8d43-834e13c55bf2";
        StringBuilder sql = new StringBuilder(" SELECT e.position_id as id FROM ");
        sql.append("  (SELECT id FROM organize_position_detail_user ");
        sql.append("  WHERE user_id = '" + userId + "') c, ");
        sql.append("  (SELECT f.id FROM ");
        sql.append("  (SELECT id FROM organize_moduletype ");
        sql.append("  WHERE module = '客户管理')a, ");
        sql.append("  (SELECT id FROM organize_department_detail ");
        sql.append("  WHERE department = '商务发展部')b, ");
        sql.append("  organize_position_detail f ");
        sql.append("  WHERE module_id = a.id AND department_id = b.id)d, ");
        sql.append("  organize_position_detail_user_table e ");
        sql.append("  WHERE user_id = c.id AND position_id = d.id; ");

        List<UserBO> userBO = super.findBySql(sql.toString(), UserBO.class, fildes);
        if (null != userBO && userBO.size() > 0) {
            tar = true;
        }
        return tar;
    }

    @Override
    public PhoneLoginUserInfoBO userLoginInfoByUserName(PhoneLoginUserInfoTO phoneLoginUserInfoTO) throws SerException {
        PhoneLoginUserInfoBO bo = new PhoneLoginUserInfoBO();
        //从用户那里拿员工编号
        if( StringUtils.isBlank( phoneLoginUserInfoTO.getUserName())){
            throw new SerException("用户名/工号/邮箱/手机号不能为空");
        }
        UserBO userBO = userAPI.findByAccountNumber( phoneLoginUserInfoTO.getUserName() );
        if( null != userBO ){
            //
            bo.setEmpNumer( userBO.getEmployeeNumber());
            bo.setUserName( userBO.getUsername() );
            //查询用户性别
            UserDetailBO userDetail = userDetailAPI.findByUserId( userBO.getId() );
            if( null != userDetail ){
                bo.setSex( userDetail.getSex().getCode()==0?"":( userDetail.getSex().getCode()==1?"男":"女") );
            }
            //查体系
            PositionUserDetailDTO pUserDetail = new PositionUserDetailDTO();
            pUserDetail.getConditions().add(Restrict.eq("userId", userBO.getId() ));
            pUserDetail.getConditions().add(Restrict.eq("workStatus", 0 ));
            List<PositionUserDetail> pUserDetailList = positionUserDetailSer.findByCis( pUserDetail );
            if( pUserDetailList!= null && pUserDetailList.size()>0 ){
                //拿到主职位id
                String mainJob = pUserDetailList.get(0).getPositionId();
                //去拿岗位名称
                PositionDetail pd = positionDetailSer.findById( mainJob );
                if( null != pd ){
                    //匹配获取部门里面的体系
                    PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
                    positionDetailUserDTO.getConditions().add(Restrict.eq("userId", userBO.getId()));
                    List<PositionDetailUser> positionDetailUserList = super.findByCis(positionDetailUserDTO);
                    List<String> positionList = new ArrayList<>(0);
                    if (null != positionDetailUserList && positionDetailUserList.size() > 0) {
                        PositionDetailUser positionDetailUser = positionDetailUserList.get(0);
                        for (PositionDetail positionDetail : positionDetailUser.getPositionSet()) {
                            if( positionDetail.getPosition().equals(pd.getPosition() ) ){
                                DepartmentDetail depart = positionDetail.getDepartment();
                                if( null != depart ){
                                    Hierarchy hierarchy = depart.getHierarchy();
                                    if( null != hierarchy ){
                                        //体系名
                                        String hierarchyName = hierarchy.getHierarchy();
                                        bo.setHierarchyName( hierarchyName );
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        return bo;
    }


}