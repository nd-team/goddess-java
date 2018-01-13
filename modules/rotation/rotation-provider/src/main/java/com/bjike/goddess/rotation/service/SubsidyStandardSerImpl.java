package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.bo.SubsidyStandardBO;
import com.bjike.goddess.rotation.dto.CoverRotationDTO;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.dto.SubsidyStandardDTO;
import com.bjike.goddess.rotation.entity.SubsidyStandard;
import com.bjike.goddess.rotation.enums.GuideAddrStatus;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.SubsidyStandardTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位补贴标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class SubsidyStandardSerImpl extends ServiceImpl<SubsidyStandard, SubsidyStandardDTO> implements SubsidyStandardSer {

    @Autowired
    private CoverRotationSer coverRotationSer;
    @Autowired
    private RecommendRotationSer recommendRotationSer;
    @Autowired
    private RotationStatisticsSer rotationStatisticsSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidyStandardBO save(SubsidyStandardTO to) throws SerException {
        if (this.findByArrangement(to.getArrangement()) != null)
            throw new SerException("该岗位层级已存在");
        SubsidyStandard entity = BeanTransform.copyProperties(to, SubsidyStandard.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidyStandardBO update(SubsidyStandardTO to) throws SerException {
        SubsidyStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        if (!entity.getArrangement().equals(to.getArrangement()) && this.findByArrangement(to.getArrangement()) != null)
            throw new SerException("该岗位层级已存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidyStandardBO delete(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        CoverRotationDTO coverRotationApplyDTO = new CoverRotationDTO();
        coverRotationApplyDTO.getConditions().add(Restrict.eq("applyLevel.id", id));
        CoverRotationDTO coverRotationDTO = new CoverRotationDTO();
        coverRotationDTO.getConditions().add(Restrict.eq("rotationLevel.id", id));

        RecommendRotationDTO recommendRotationApplyDTO = new RecommendRotationDTO();
        recommendRotationApplyDTO.getConditions().add(Restrict.eq("applyLevel.id", id));
        RecommendRotationDTO recommendRotationDTO = new RecommendRotationDTO();
        recommendRotationDTO.getConditions().add(Restrict.eq("rotationLevel.id", id));

        RotationStatisticsDTO rotationStatisticsDTO = new RotationStatisticsDTO();
        rotationStatisticsDTO.getConditions().add(Restrict.eq("arrangement.id", id));

        if (coverRotationSer.count(coverRotationDTO) != 0 ||
                coverRotationSer.count(coverRotationApplyDTO) != 0 ||
                recommendRotationSer.count(recommendRotationDTO) != 0 ||
                recommendRotationSer.count(recommendRotationApplyDTO) != 0 ||
                rotationStatisticsSer.count(rotationStatisticsDTO) != 0)
            throw new SerException("存在依赖关系,无法删除,请确保该层级没有相关人员,方可删除");
        super.remove(entity);

        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidyStandardBO congeal(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubsidyStandardBO thaw(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public SubsidyStandardBO getById(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public List<SubsidyStandardBO> maps(SubsidyStandardDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), SubsidyStandardBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        SubsidyStandardDTO dto = new SubsidyStandardDTO();
        return super.count(dto);
    }

    @Override
    public SubsidyStandardBO findByArrangement(String arrangement) throws SerException {
        if (StringUtils.isBlank(arrangement))
            return new SubsidyStandardBO();
        SubsidyStandardDTO dto = new SubsidyStandardDTO();
        dto.getConditions().add(Restrict.eq("arrangement", arrangement));
        return BeanTransform.copyProperties(super.findOne(dto), SubsidyStandardBO.class);
    }

    @Override
    public List<SubsidyStandardBO> findThaw() throws SerException {
        SubsidyStandardDTO dto = new SubsidyStandardDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        return BeanTransform.copyProperties(super.findByCis(dto), SubsidyStandardBO.class);
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
}