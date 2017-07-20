package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.rotation.bo.CoverRotationBO;
import com.bjike.goddess.rotation.bo.DetailBO;
import com.bjike.goddess.rotation.bo.RecommendRotationBO;
import com.bjike.goddess.rotation.bo.RotationStatisticsBO;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.entity.RotationStatistics;
import com.bjike.goddess.rotation.enums.AuditType;
import com.bjike.goddess.rotation.enums.GuideAddrStatus;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RotationStatisticsTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位轮换统计业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:38 ]
 * @Description: [ 岗位轮换统计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class RotationStatisticsSerImpl extends ServiceImpl<RotationStatistics, RotationStatisticsDTO> implements RotationStatisticsSer {

    @Autowired
    private SubsidyStandardSer subsidyStandardSer;
    @Autowired
    private CoverRotationSer coverRotationSer;
    @Autowired
    private RecommendRotationSer recommendRotationSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private RotationStatisticsBO transformBO(RotationStatistics entity) throws SerException {
        RotationStatisticsBO bo = BeanTransform.copyProperties(entity, RotationStatisticsBO.class);
        bo.setArrangementName(entity.getArrangement().getArrangement());
        bo.setArrangementId(entity.getArrangement().getId());
        return bo;
    }

    private List<RotationStatisticsBO> transformBOList(List<RotationStatistics> list) throws SerException {
        List<RotationStatisticsBO> bos = new ArrayList<>(0);
        for (RotationStatistics entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public RotationStatisticsBO save(RotationStatisticsTO to) throws SerException {
        RotationStatistics entity = BeanTransform.copyProperties(to, RotationStatistics.class, true);
        entity.setArrangement(subsidyStandardSer.findById(to.getArrangementId()));
        if (null == entity.getArrangement())
            throw new SerException("选择的岗位层级不存在");
        this.countStatistics(entity);
        super.save(entity);
        return this.transformBO(entity);
    }

    private void countStatistics(RotationStatistics entity) throws SerException {
        List<CoverRotationBO> coverBOs = coverRotationSer.findByUserArrangement(entity.getUsername(), entity.getArrangement().getId()).stream()
                .filter(c -> c.getAudit() == AuditType.ALLOWED && StringUtils.isNotBlank(c.getRotationDate()))
                .sorted(Comparator.comparing(CoverRotationBO::getRotationDate).reversed())
                .collect(Collectors.toList());

        List<RecommendRotationBO> recommendBOs = recommendRotationSer.findByUserArrangement(entity.getUsername(), entity.getArrangement().getId()).stream()
                .filter(r -> r.getAudit() == AuditType.ALLOWED && StringUtils.isNotBlank(r.getRotationDate()))
                .sorted(Comparator.comparing(RecommendRotationBO::getRotationDate).reversed())
                .collect(Collectors.toList());

        if (coverBOs.size() == 0 && recommendBOs.size() == 0)
            throw new SerException("该用户并没有当前选择层级数据");

        String startTime = entity.getOccupyStart().toString();
        Boolean check = false;

        for (CoverRotationBO bo : coverBOs)
            if (bo.getRotationDate().equals(startTime)) {
                check = true;
                break;
            }
        if (!check)
            for (RecommendRotationBO bo : recommendBOs)
                if (bo.getRotationDate().equals(startTime)) {
                    check = true;
                    break;
                }
        if (!check)
            throw new SerException(String.format("并没有当前用户在%s开始轮岗%s记录", startTime, entity.getArrangement().getArrangement()));

        Long cycle = entity.getSubsidyStart().until(entity.getSubsidyEnd(), ChronoUnit.MONTHS);
        entity.setCycle(cycle.intValue());
        if (entity.getSubsidy() > entity.getCycle())
            throw new SerException(String.format("周期内补贴天数不能大于%d", entity.getCycle()));
    }

    @Override
    public RotationStatisticsBO update(RotationStatisticsTO to) throws SerException {
        RotationStatistics entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setArrangement(subsidyStandardSer.findById(to.getArrangementId()));
        if (null == entity.getArrangement())
            throw new SerException("选择的岗位层级不存在");
        this.countStatistics(entity);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public RotationStatisticsBO delete(String id) throws SerException {
        RotationStatistics entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return null;
    }

    @Override
    public RotationStatisticsBO getById(String id) throws SerException {
        RotationStatistics entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<RotationStatisticsBO> maps(RotationStatisticsDTO dto) throws SerException {
        dto.getSorts().add("occupyStart=desc");
        dto.getSorts().add("username=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        RotationStatisticsDTO dto = new RotationStatisticsDTO();
        return super.count(dto);
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

    @Override
    public List<DetailBO> getDetail() throws SerException {
        //从组织结构获取
        PositionDetailDTO dto = new PositionDetailDTO();
        List<PositionDetailBO> list = positionDetailAPI.maps(dto);
        List<DetailBO> detailBOs = new ArrayList<>();

        PositionDetailUserDTO positionDetailUserDTO = new PositionDetailUserDTO();
        List<PositionDetailUserBO> maps = positionDetailUserAPI.maps(positionDetailUserDTO);

        if (null != list && list.size() > 0) {
            for (PositionDetailBO bo : list) {
                DetailBO detailBO = new DetailBO();
                String id = bo.getSerialNumber();

                if (null != maps && maps.size() > 0) {
                    for (PositionDetailUserBO positionDetailUserBO : maps) {
                        if (id.equals(positionDetailUserBO.getEmployeesNumber())) {
                            detailBO.setName(positionDetailUserBO.getUsername());
                            detailBO.setDepartment(bo.getDepartmentName());

                            detailBO.setPosition(bo.getArrangementName());
                            detailBOs.add(detailBO);
                        }
                    }
                }
            }
        }
        return detailBOs;
    }
}