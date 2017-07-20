package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.bo.FindNameBO;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.entity.CoverRotation;
import com.bjike.goddess.rotation.entity.CoverRotationOpinion;
import com.bjike.goddess.rotation.enums.AuditType;
import com.bjike.goddess.rotation.enums.GuideAddrStatus;
import com.bjike.goddess.rotation.excel.SonPermissionObject;
import com.bjike.goddess.rotation.to.CoverRotationOpinionTO;
import com.bjike.goddess.rotation.to.CoverRotationTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位轮换自荐业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class CoverRotationSerImpl extends ServiceImpl<CoverRotation, CoverRotationDTO> implements CoverRotationSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private RegularizationAPI regularizationAPI;

    @Autowired
    private RecommendRotationSer recommendRotationSer;

    @Autowired
    private RotationConditionSer rotationConditionSer;
    @Autowired
    private RotationStatisticsSer rotationStatisticsSer;
    @Autowired
    private SubsidyStandardSer subsidyStandardSer;

    @Autowired
    private CoverRotationOpinionSer coverRotationOpinionSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    private CoverRotationBO transformBO(CoverRotation entity) throws SerException {
        CoverRotationBO bo = BeanTransform.copyProperties(entity, CoverRotationBO.class);
        UserBO user = userAPI.findByUsername(entity.getUsername());
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("name", entity.getUsername()));
        List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.listEntryBasicInfo(dto);
        RegularizationDTO regularizationDTO = new RegularizationDTO();
        regularizationDTO.getConditions().add(Restrict.eq("name", entity.getUsername()));
        List<RegularizationBO> regularizationBOs = regularizationAPI.list(regularizationDTO);
        if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
            EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
            bo.setEntryTime(entryBasicInfoBO.getEntryTime());
        }
        if (null != regularizationBOs && regularizationBOs.size() > 0) {
            RegularizationBO regularizationBO = regularizationBOs.get(0);
            bo.setEntryTime(regularizationBO.getHiredate());
            bo.setRegularTime(regularizationBO.getPositiveDate());
        }
        if (null != entity.getApplyLevel()) {
            bo.setApplyLevelId(entity.getApplyLevel().getId());
            bo.setApplyLevelArrangement(entity.getApplyLevel().getArrangement());
        }
        if (null != entity.getRotationLevel()) {
            bo.setRotationLevelId(entity.getRotationLevel().getId());
            bo.setRotationLevelArrangement(entity.getRotationLevel().getArrangement());
        }
        return bo;
    }

    private List<CoverRotationBO> transformBOList(List<CoverRotation> list) throws SerException {
        List<CoverRotationBO> bos = new ArrayList<>(0);
        for (CoverRotation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public CoverRotationBO save(CoverRotationTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        CoverRotation entity = BeanTransform.copyProperties(to, CoverRotation.class, true);
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(user.getId()).stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArea)
                        .thenComparing(PositionDetailBO::getDepartmentId))
                .collect(Collectors.toList());
        StringBuilder area = new StringBuilder(), department = new StringBuilder(), position = new StringBuilder(), arrangement = new StringBuilder();
        String tempArea = "", tempDepartment = "", tempArrangement = "";
        for (PositionDetailBO positionDetailBO : positionDetailBOs) {
            if (!tempArea.equals(positionDetailBO.getArea())) {
                tempArea = positionDetailBO.getArea();
                area.append(tempArea + ",");
            }
            if (!tempDepartment.equals(positionDetailBO.getDepartmentName())) {
                tempDepartment = positionDetailBO.getDepartmentName();
                department.append(tempDepartment + ",");
            }
            position.append(positionDetailBO.getPosition());
        }
        for (String s : positionDetailBOs.stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArrangementName))
                .map(PositionDetailBO::getArrangementName).collect(Collectors.toList()))
            if (!tempArrangement.equals(s)) {
                tempArrangement = s;
                arrangement.append(s);
            }

        entity.setUsername(user.getUsername());
        entity.setArea(area.toString());
        entity.setPosition(position.toString());
        entity.setArrangement(arrangement.toString());
        entity.setDepartment(department.toString());
        entity.setAudit(AuditType.NONE);
        entity.setApplyLevel(subsidyStandardSer.findById(to.getApplyLevelId()));
        if (null == entity.getApplyLevel())
            throw new SerException("申请的层级不存在");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public CoverRotationBO update(CoverRotationTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        CoverRotation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (!user.getUsername().equals(entity.getUsername()))
            throw new SerException("不能修改他人的轮换申请");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setApplyLevel(subsidyStandardSer.findById(to.getApplyLevelId()));
        if (null == entity.getApplyLevel())
            throw new SerException("申请的层级不存在");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public CoverRotationBO delete(String id) throws SerException {
        UserBO user = userAPI.currentUser();
        CoverRotation entity = super.findById(id);
        if (!user.getUsername().equals(entity.getUsername()))
            throw new SerException("不能删除他人的轮换申请");
        if (null == entity)
            throw new SerException("该数据不存在");
        if (coverRotationOpinionSer.getTotal(id) != 0)
            throw new SerException("存在依赖关系,无法删除,请确保相关联的岗位轮换自荐数据已删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public CoverRotationBO getById(String id) throws SerException {
        CoverRotation entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public CoverRotationOpinionBO opinion(CoverRotationOpinionTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        CoverRotationOpinion entity = BeanTransform.copyProperties(to, CoverRotationOpinion.class);
        entity.setCover(super.findById(to.getCoverId()));
        if (null == entity.getCover())
            throw new SerException("岗位轮换自荐数据不存在");
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(user.getId()).stream()
                .sorted(Comparator.comparing(PositionDetailBO::getArea)
                        .thenComparing(PositionDetailBO::getDepartmentId))
                .collect(Collectors.toList());
        StringBuilder area = new StringBuilder(), department = new StringBuilder(), position = new StringBuilder();
        String tempArea = "", tempDepartment = "";
        for (PositionDetailBO positionDetailBO : positionDetailBOs) {
            if (!tempArea.equals(positionDetailBO.getArea())) {
                tempArea = positionDetailBO.getArea();
                area.append(tempArea + ",");
            }
            if (!tempDepartment.equals(positionDetailBO.getDepartmentName())) {
                tempDepartment = positionDetailBO.getDepartmentName();
                department.append(tempDepartment + ",");
            }
            position.append(positionDetailBO.getPosition());
        }

        entity.setUsername(user.getUsername());
        entity.setArea(area.toString());
        entity.setPosition(position.toString());
        entity.setDepartment(department.toString());
        coverRotationOpinionSer.save(entity);
        return coverRotationOpinionSer.transformBO(entity);
    }

    @Override
    public CoverRotationBO generalOpinion(CoverRotationTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        CoverRotation entity = super.findById(to.getId());
        //@TODO 职位判断
        if (null == entity)
            throw new SerException("该数据不存在");
        if (entity.getAudit() != AuditType.NONE)
            throw new SerException("该数据已被评价");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setGeneral(user.getUsername());
        entity.setAudit(to.getPass() ? AuditType.ALLOWED : AuditType.DENIED);
        entity.setRotationLevel(subsidyStandardSer.findById(to.getRotationLevelId()));
        if (to.getPass() && null == entity.getRotationLevel())
            throw new SerException("选择的层级不存在");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<CoverRotationBO> maps(CoverRotationDTO dto) throws SerException {
        dto.getSorts().add("audit=asc");
        dto.getSorts().add("rotationDate=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        CoverRotationDTO dto = new CoverRotationDTO();
        return super.count(dto);
    }

    @Override
    public List<CoverRotationBO> findByUserArrangement(String username, String arrangementId) throws SerException {
        CoverRotationDTO dto = new CoverRotationDTO();
        dto.getConditions().add(Restrict.eq(USERNAME, username));
        dto.getConditions().add(Restrict.eq("audit", AuditType.ALLOWED.getValue()));
        dto.getConditions().add(Restrict.eq("rotationLevel.id", arrangementId));
        dto.getSorts().add("rotationDate=desc");
        return this.transformBOList(super.findByCis(dto));
    }

    @Override
    public List<FindNameBO> getName() throws SerException {
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        List<EntryBasicInfoBO> entryBasicInfoBOList = entryBasicInfoAPI.listEntryBasicInfo(dto);
        List<FindNameBO> list = new ArrayList<>();
        if (null != entryBasicInfoBOList && entryBasicInfoBOList.size() > 0){
            for(EntryBasicInfoBO bo : entryBasicInfoBOList){
                FindNameBO findNameBO = new FindNameBO();
                findNameBO.setName(bo.getName());
                list.add(findNameBO);
            }
        }
        return list;
    }

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("coverrotationopinion");
        obj.setDescribesion("岗位轮换自荐");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = coverRotationOpinionSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("coverrotationopinion");
        obj.setDescribesion("岗位轮换自荐意见");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = recommendRotationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("recommendrotation");
        obj.setDescribesion("岗位轮换推荐");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate1 = rotationConditionSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rotationcondition");
        obj.setDescribesion("岗位轮换条件");
        if (flagSeeCate1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate2 = rotationStatisticsSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rotationstatistics");
        obj.setDescribesion("岗位轮换统计");
        if (flagSeeCate2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate3 = subsidyStandardSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("subsidystandard");
        obj.setDescribesion("岗位补贴标准");
        if (flagSeeCate3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }
}