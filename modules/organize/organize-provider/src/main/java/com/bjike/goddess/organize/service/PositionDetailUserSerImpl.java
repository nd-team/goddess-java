package com.bjike.goddess.organize.service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
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
import com.bjike.goddess.organize.vo.ActResultOrgan;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    @Autowired
    private HierarchySer hierarchySer;
    @Autowired
    private ArrangementSer arrangementSer;
    @Autowired
    private ModulesSer modulesSer;
    @Autowired
    private ModuleTypeSer moduleTypeSer;

    private static Logger logger = Logger.getLogger(PositionDetailUserSerImpl.class);

    private PositionDetailUserBO transformBO(PositionDetailUser entity) throws SerException {
        PositionDetailUserBO bo = BeanTransform.copyProperties(entity, PositionDetailUserBO.class);
//        UserDTO userDTO = new UserDTO();
//        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
//        List<UserBO> userBOs = userAPI.findByCis(userDTO);
//        if (null != userBOs && userBOs.size() > 0) {
//            bo.setUsername(userBOs.get(0).getUsername());
//            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
//        }
////        StringBuilder positionId = new StringBuilder(0), positionName = new StringBuilder(0);
////        if (null != entity.getPositionSet())
////            for (PositionDetail positionDetail : entity.getPositionSet()) {
////                positionId.append(positionDetail.getId()).append(",");
////                positionName.append(positionDetail.getPosition()).append(",");
////            }
////        bo.setPosition(positionName.toString());
////        bo.setPositionIds(positionId.toString());
//        String userId = entity.getUserId();
        List<PositionUserDetailBO> detailBOS = new ArrayList<>();
        if (entity != null) {
            if (null != entity.getPositionSet()) {
                for (PositionDetail positionDetail : entity.getPositionSet()) {
                    PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                    detailDTO.getConditions().add(Restrict.eq("userId", entity.getId()));
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
        if (this.findByUser(to.getName()) != null)
            throw new SerException("该用户已存在,无法保存");
    }

    /**
     * 检测用户职位内是否有重复的员工编号
     *
     * @param to
     * @throws SerException
     */
    private void checkUniqueNumber(PositionDetailUserTO to) throws SerException {
        if (this.findByNumber(to.getNumber()) != null)
            throw new SerException("该员工编号已存在,无法保存");
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void save(PositionDetailUserTO to) throws SerException {
        this.checkUnique(to);
        checkUniqueNumber(to);
        PositionDetailUser entity = new PositionDetailUser();
        entity.setName(to.getName());
        entity.setNumber(to.getNumber());
//        entity.setUserId(to.getUserId());
        entity.setPositionSet(new HashSet<>(0));
//        UserDTO userDTO = new UserDTO();
//        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
//        List<UserBO> userBOList = userAPI.findByCis(userDTO);
//        if (null == userBOList || userBOList.size() <= 0)
//            throw new SerException("该用户不存在");
        List<PositionUserDetailTO> detailTOS = to.getDetailTOS();
        entity.setStaffStatus(to.getStaffStatus());
        super.save(entity);
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
                positionUserDetail.setUserId(entity.getId());
                positionUserDetailSer.save(positionUserDetail);
            }
        }
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
//        return this.transformBO(entity);
    }

    @Override
    public void update(PositionDetailUserTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
//            UserDTO userDTO = new UserDTO();
//            userDTO.getConditions().add(Restrict.eq(ID, to.getUserId()));
//            List<UserBO> userBOList = userAPI.findByCis(userDTO);
//            if (null == userBOList || userBOList.size() <= 0)
//                throw new SerException("该用户不存在");
            PositionDetailUser entity = super.findById(to.getId());
//            if (!entity.getName().equals(to.getName()))
//                this.checkUnique(to);
//            if (!entity.getNumber().equals(to.getNumber()))
//                this.checkUniqueNumber(to);
            try {
                BeanTransform.copyProperties(to, entity, true, "name", "number");
                entity.setPositionSet(new HashSet<>(0));
                PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                detailDTO.getConditions().add(Restrict.eq("userId", entity.getId()));
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
                        positionUserDetail.setUserId(entity.getId());
                        positionUserDetailSer.save(positionUserDetail);
                    }
                }
                entity.setStaffStatus(to.getStaffStatus());
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
//                return this.transformBO(entity);

            } catch (SerException e) {
                throw new SerException(e.getMessage());
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
        PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
        detailDTO.getConditions().add(Restrict.eq("userId", entity.getId()));
        List<PositionUserDetail> list = positionUserDetailSer.findByCis(detailDTO);
        positionUserDetailSer.remove(list);
        super.remove(entity);
//        return this.transformBO(entity);
    }

    @Override
    public List<PositionDetailBO> findPositionByUser(String name) throws SerException {
        PositionDetailUser entity = this.findByUser(name);
        if (null != entity)
            return positionDetailSer.transformationToBOList(entity.getPositionSet());
        else
            return new ArrayList<>(0);
    }

    @Override
    public PositionDetailUserBO findOneByUser(String name) throws SerException {
        PositionDetailUser entity = this.findByUser(name);
        if (null != entity)
            return this.transformBO(entity);
        else
            return null;
    }

    private PositionDetailUser findByUser(String name) throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        dto.getConditions().add(Restrict.eq("name", name));
        return super.findOne(dto);
    }

    private PositionDetailUser findByNumber(String number) throws SerException {
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        dto.getConditions().add(Restrict.eq("number", number));
        return super.findOne(dto);
    }


//    @Override
//    public Boolean checkAsUserPosit2(String name, String[] position_ids) throws SerException {
//        logger.info("开始给");
//    }

    public Boolean checkAsUserPosition(String name, String[] position_ids) throws SerException {
//        UserDTO userDTO = new UserDTO();
//        userDTO.getConditions().add(Restrict.eq(ID, name));
//        UserBO userBO = userAPI.findOne(userDTO);
//        if (userBO != null) {
//            PositionDetailUser entity = this.findByUser(userBO.getUsername());
//            if (null != entity && null != entity.getPositionSet() && null != position_ids)
//                for (PositionDetail detail : entity.getPositionSet())
//                    for (String id : position_ids)
//                        if (detail.getId().equals(id))
//                            return true;
//        }
        return false;
    }

    @Override
    public Boolean checkAsUserPosit2(String name, String[] position_ids) throws SerException {
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, name));
        UserBO userBO = userAPI.findOne(userDTO);
        logger.info("开始给user");
        if (userBO != null) {
            PositionDetailUser entity = this.findByUser(userBO.getUsername());
            logger.info("开始给uposit");
            PositionDetailUserDTO dto = new PositionDetailUserDTO();
            dto.getConditions().add(Restrict.eq("name", name));
            PositionDetailUser entity1 = super.findOne(dto);
            if (null != entity1 && null != entity1.getPositionSet() && null != position_ids)
                for (PositionDetail detail : entity1.getPositionSet())
                    for (String id : position_ids)
                        if (detail.getId().equals(id))
                            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAsUserArrangement(String userId, String... arrangementIds) throws SerException {
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, userId));
        UserBO userBO = userAPI.findOne(userDTO);
        if (userBO != null) {
            PositionDetailUser entity = this.findByUser(userBO.getUsername());
            if (null != entity && null != entity.getPositionSet() && null != arrangementIds)
                for (PositionDetail detail : entity.getPositionSet())
                    for (String id : arrangementIds)
                        if (detail.getArrangement().getId().equals(id))
                            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAsUserDepartment(String userId, String... departmentIds) throws SerException {
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, userId));
        UserBO userBO = userAPI.findOne(userDTO);
        if (userBO != null) {
            PositionDetailUser entity = this.findByUser(userBO.getUsername());
            if (null != entity && null != entity.getPositionSet() && null != departmentIds)
                for (PositionDetail detail : entity.getPositionSet())
                    for (String id : departmentIds)
                        if (detail.getDepartment().getId().equals(id))
                            return true;
        }
        return false;
    }

    @Override
    public Boolean checkAsUserModule(String userId, String... moduleIds) throws SerException {
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq(ID, userId));
        UserBO userBO = userAPI.findOne(userDTO);
        if (userBO != null) {
            PositionDetailUser entity = this.findByUser(userBO.getUsername());
            if (null != entity && null != entity.getPositionSet() && null != moduleIds)
                for (PositionDetail detail : entity.getPositionSet())
                    for (String id : moduleIds)
                        if (detail.getModule().getId().equals(id))
                            return true;
        }
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
        String[] fields = {"id"};
        String sql = "SELECT user_id as id FROM  organize_position_detail_user_table WHERE position_id ='%s'";
        List<PositionDetailUser> list = super.findBySql(String.format(sql, position_id), PositionDetailUser.class, fields);
        List<UserBO> bos = new ArrayList<>(0);
        if (null != list) {
            for (PositionDetailUser entity : list) {
                UserBO userBO = userAPI.findByUsername(super.findById(entity.getId()).getName());
                if (null != userBO) {
                    bos.add(userBO);
                }
            }
        }
        return bos;
    }

    @Override
    public Integer findNumber(String position_id) throws SerException {
        String[] fields = {"id"};
        String sql = "SELECT user_id as id  FROM organize_position_detail_user_table WHERE position_id ='%s'";
        List<PositionDetailUser> list = super.findBySql(String.format(sql, position_id), PositionDetailUser.class, fields);
        if (null != list) {
            return list.size();
        }
        return 0;
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
        List<UserBO> bos = new ArrayList<>();
        for (PositionDetailUser p : list) {
            UserBO userBO = new UserBO();
            userBO.setUsername(p.getName());
            userBO.setEmployeeNumber(p.getNumber());
            bos.add(userBO);
        }
        return bos;
    }

    @Override
    public List<String> getPosition(String name) throws SerException {
//        //根据名字获取用户ｉｄ
//        UserBO userBO = userAPI.findByUsername(name);
//        String userId = "";
//        if (null != userBO) {
//            userId = userBO.getId();
//        }
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("name", name));
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
    }


    @Override
    public List<PositionDetailBO> getPositionDetail(String name) throws SerException {
//        根据名字获取用户ｉｄ
//        UserBO userBO = userAPI.findByUsername(name);
//        String userId = "";
//        if (null != userBO) {
//            userId = userBO.getId();
//        }
        String fields[] = new String[]{"pool", "serialNumber", "staff", "arrangementId", "departmentId", "description", "position", "status", "moduleId"
//                ,"position_id","user_id"
        };
//        userId = "00a94d4e-5c1f-4ed9-8a1b-e9e5811c1c0e";
        StringBuilder sql = new StringBuilder("SELECT d.pool, d.serialNumber, d.staff, d.arrangement_id as arrangementId, d.department_id as departmentId, d.description, d.position, d.status, d.module_id as moduleId");
        sql.append(" FROM organize_position_detail_user b,");
        sql.append(" organize_position_detail_user_table c,organize_position_detail d ");
        sql.append(" WHERE b.id=c.user_id AND c.position_id=d.id ");
        sql.append(" AND b.name= '" + name + "'");
        List<PositionDetailBO> positionDetails = positionDetailSer.findBySql(sql.toString(), PositionDetailBO.class, fields);
        List<PositionDetailBO> list = BeanTransform.copyProperties(positionDetails, PositionDetailBO.class);
        if (null != list && list.size() > 0) {
            for (PositionDetailBO positionDetailBO : list) {
                DepartmentDetail department = departmentDetailSer.findById(positionDetailBO.getDepartmentId());
                positionDetailBO.setDepartmentName(department.getDepartment());
                positionDetailBO.setArea(department.getArea());
                Hierarchy hierarchy = hierarchySer.findById(department.getHierarchy().getId());
                positionDetailBO.setHierarchyName(hierarchy.getHierarchy());
                Arrangement arrangement = arrangementSer.findById(positionDetailBO.getArrangementId());
                positionDetailBO.setArrangementName(arrangement.getArrangement());
                ModuleType modules = moduleTypeSer.findById(positionDetailBO.getModuleId());
                positionDetailBO.setModuleName(modules.getModule());
            }
        }
        return list;
    }


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
        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        positionDetailUserDTO.getConditions().add(Restrict.eq("name", name));
        PositionDetailUser positionDetailUser = super.findOne(positionDetailUserDTO);
        if (null != positionDetailUser) {
            StaffStatus staffStatus = positionDetailUser.getStaffStatus();
            return staffStatus;
        }
        return null;
    }

    @Override
    public PositionDetailUserBO bo(PositionDetailUser entity, Set<String> positions) throws SerException {
        PositionDetailUserBO bo = BeanTransform.copyProperties(entity, PositionDetailUserBO.class);
//        UserDTO userDTO = new UserDTO();
//        userDTO.getConditions().add(Restrict.eq(ID, entity.getUserId()));
//        List<UserBO> userBOs = userAPI.findByCis(userDTO);
//        if (null != userBOs && userBOs.size() > 0) {
//            bo.setUsername(userBOs.get(0).getUsername());
//            bo.setEmployeesNumber(userBOs.get(0).getEmployeeNumber());
//        }
//        StringBuilder positionId = new StringBuilder(0), positionName = new StringBuilder(0);
//        if (null != entity.getPositionSet())
//            for (PositionDetail positionDetail : entity.getPositionSet()) {
//                positionId.append(positionDetail.getId()).append(",");
//                positionName.append(positionDetail.getPosition()).append(",");
//            }
//        bo.setPosition(positionName.toString());
//        bo.setPositionIds(positionId.toString());
//        String userId = entity.getUserId();
        List<PositionUserDetailBO> detailBOS = new ArrayList<>();
        if (null != entity.getPositionSet()) {
            for (PositionDetail positionDetail : entity.getPositionSet()) {
                if (positions.contains(positionDetail.getId())) {
                    PositionUserDetailDTO detailDTO = new PositionUserDetailDTO();
                    detailDTO.getConditions().add(Restrict.eq("userId", entity.getId()));
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
    public DepartmentDetailBO areaAndDepart(String name) throws SerException {
        PositionDetailUserDTO detailUserDTO = new PositionDetailUserDTO();
        detailUserDTO.getConditions().add(Restrict.eq("name", name));
        List<PositionDetailUser> list = super.findByCis(detailUserDTO);
        if (!list.isEmpty()) {
            String id = list.get(0).getId();
            String sql = "SELECT position_id id FROM organize_position_detail_user_table WHERE user_id = '" + id + "'";
            String[] strings = new String[]{"id"};
            List<PositionDetailBO> positionDetailBOS = super.findBySql(sql, PositionDetailBO.class, strings);
            if (null != positionDetailBOS && !positionDetailBOS.isEmpty()) {
                String pId = positionDetailBOS.get(0).getId();
                String sql1 = "SELECT area,department " +
                        "FROM organize_department_detail " +
                        "WHERE id =" +
                        "      (SELECT department_id " +
                        "       FROM organize_position_detail " +
                        "       WHERE id = '" + pId + "')";
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
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq(ID, userId));
        UserBO user = userAPI.findOne(dto);
        if (null != user) {
            StringBuilder sql = new StringBuilder(" SELECT e.position_id as id FROM ");
            sql.append("  (SELECT id FROM organize_position_detail_user ");
            sql.append("  WHERE name = '" + user.getUsername() + "') c, ");
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
        }
        return tar;
    }

    @Override
    public PhoneLoginUserInfoBO userLoginInfoByUserName(PhoneLoginUserInfoTO phoneLoginUserInfoTO) throws SerException {
        PhoneLoginUserInfoBO bo = new PhoneLoginUserInfoBO();
        //从用户那里拿员工编号
        if (StringUtils.isBlank(phoneLoginUserInfoTO.getUserName())) {
            throw new SerException("用户名/工号/邮箱/手机号不能为空");
        }
        UserBO userBO = userAPI.findByAccountNumber(phoneLoginUserInfoTO.getUserName());
        //查询用户性别
        if (null != userBO) {
            UserDetailBO userDetail = userDetailAPI.findByUserId(userBO.getId());
            if (null != userDetail) {
                bo.setSex(userDetail.getSex().getCode() == 0 ? "" : (userDetail.getSex().getCode() == 1 ? "男" : "女"));
            }
        }
        PositionDetailUserDTO dto = new PositionDetailUserDTO();
        dto.getConditions().add(Restrict.eq("name", phoneLoginUserInfoTO.getUserName()));
        PositionDetailUser positionDetailUser = super.findOne(dto);
        if (null != positionDetailUser) {
            //
            bo.setEmpNumer(positionDetailUser.getNumber());
            bo.setUserName(positionDetailUser.getName());
            //查体系
            PositionUserDetailDTO pUserDetail = new PositionUserDetailDTO();
            pUserDetail.getConditions().add(Restrict.eq("userId", positionDetailUser.getId()));
            pUserDetail.getConditions().add(Restrict.eq("workStatus", 0));
            List<PositionUserDetail> pUserDetailList = positionUserDetailSer.findByCis(pUserDetail);
            if (pUserDetailList != null && pUserDetailList.size() > 0) {
                //拿到主职位id
                String mainJob = pUserDetailList.get(0).getPositionId();
                //去拿岗位名称
                PositionDetail pd = positionDetailSer.findById(mainJob);
                if (null != pd) {
                    //匹配获取部门里面的体系
//                    PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
//                    positionDetailUserDTO.getConditions().add(Restrict.eq("userId", userBO.getId()));
//                    List<PositionDetailUser> positionDetailUserList = super.findByCis(positionDetailUserDTO);
//                    List<String> positionList = new ArrayList<>(0);
//                    if (null != positionDetailUserList && positionDetailUserList.size() > 0) {
//                        PositionDetailUser positionDetailUser = positionDetailUserList.get(0);
                    for (PositionDetail positionDetail : positionDetailUser.getPositionSet()) {
                        if (positionDetail.getPosition().equals(pd.getPosition())) {
                            DepartmentDetail depart = positionDetail.getDepartment();
                            if (null != depart) {
                                Hierarchy hierarchy = depart.getHierarchy();
                                if (null != hierarchy) {
                                    //体系名
                                    String hierarchyName = hierarchy.getHierarchy();
                                    bo.setHierarchyName(hierarchyName);
                                }
                            }
                            break;
                        }
                    }
//                    }
                }
            }
        }
        return bo;
    }


    @Override
    public List<String> names() throws SerException {
        List<String> list = new ArrayList<>(0);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("https://staffentry.issp.bjike.com:8080/entryregister/v1/names");//线上
        HttpGet httpGet = new HttpGet("http://localhost:51218/entryregister/v1/names");//线下测试
        httpGet.setHeader("userToken", RpcContext.getContext().getAttachment("userToken"));

        ActResultOrgan resultOrgan = new ActResultOrgan();
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            resultOrgan = JSON.parseObject(EntityUtils.toString(response.getEntity()), ActResultOrgan.class);
            list = (List<String>) (resultOrgan.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> names = super.findAll().stream().map(positionDetailUser -> positionDetailUser.getName()).collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (!names.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public InternalContactsConditionBO getByName(String name) throws SerException {
        StringBuilder sql = new StringBuilder("select area, number, department, position ");
        sql.append(" from organize_department_detail a, organize_position_detail b, organize_position_detail_user c, organize_position_detail_user_table d");
        sql.append(" where c.name = '" + name + "' ");
        sql.append(" and c.id = d.user_id ");
        sql.append(" and d.position_id = b.id ");
        sql.append(" and b.department_id = a.id ");
        String[] fildes = new String[]{"area", "number", "department", "position"};
        List<InternalContactsConditionBO> internalContactsConditionBOs = super.findBySql(sql.toString(), InternalContactsConditionBO.class, fildes);
        if (null != internalContactsConditionBOs && internalContactsConditionBOs.size() > 0) {
            return internalContactsConditionBOs.get(0);
        }
        return null;
    }
}