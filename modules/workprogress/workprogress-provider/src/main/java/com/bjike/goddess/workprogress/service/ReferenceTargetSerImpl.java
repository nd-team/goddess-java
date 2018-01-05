package com.bjike.goddess.workprogress.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workprogress.bo.ReferenceTargetBO;
import com.bjike.goddess.workprogress.dto.DayTargetDTO;
import com.bjike.goddess.workprogress.dto.MonthTargetDTO;
import com.bjike.goddess.workprogress.dto.ReferenceTargetDTO;
import com.bjike.goddess.workprogress.dto.WeekTargetDTO;
import com.bjike.goddess.workprogress.entity.ReferenceTarget;
import com.bjike.goddess.workprogress.enums.GuideAddrStatus;
import com.bjike.goddess.workprogress.to.FindTO;
import com.bjike.goddess.workprogress.to.GuidePermissionTO;
import com.bjike.goddess.workprogress.to.ReferenceTargetTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考指标业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workprogressSerCache")
@Service
public class ReferenceTargetSerImpl extends ServiceImpl<ReferenceTarget, ReferenceTargetDTO> implements ReferenceTargetSer {

    @Autowired
    private DayTargetSer dayTargetSer;
    @Autowired
    private MonthTargetSer monthTargetSer;
    @Autowired
    private WeekTargetSer weekTargetSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public ReferenceTargetBO save(ReferenceTargetTO to) throws SerException {
        ReferenceTarget entity = BeanTransform.copyProperties(to, ReferenceTarget.class);
        this.countTarget(entity);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    private void countTarget(ReferenceTarget entity) throws SerException {
        LocalDate time;
        if (entity.getMonth() > 12)
            throw new SerException("月份有误");
        if (entity.getMonth() >= 10) {
            time = LocalDate.parse(String.format("%d-%d-01", entity.getYear(), entity.getMonth()));
        } else {
            time = LocalDate.parse(String.format("%d-0%d-01", entity.getYear(), entity.getMonth()));
        }
        entity.setWeekArtificial(this.decimal(entity.getMonthArtificial() / 4));
        entity.setWeekTask(this.decimal(entity.getMonthTask() / 4));
        entity.setDayArtificial(this.decimal(entity.getMonthArtificial() / time.getMonth().maxLength()));
        entity.setDayTask(this.decimal(entity.getMonthTask() / time.getMonth().maxLength()));
    }

    private double decimal(double number) throws SerException {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public ReferenceTargetBO update(ReferenceTargetTO to) throws SerException {
        ReferenceTarget entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("概数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countTarget(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    @Override
    public ReferenceTargetBO delete(String id) throws SerException {
        ReferenceTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("概数据不存在");
        DayTargetDTO dayTargetDTO = new DayTargetDTO();
        dayTargetDTO.getConditions().add(Restrict.eq("target.id", id));
        WeekTargetDTO weekTargetDTO = new WeekTargetDTO();
        weekTargetDTO.getConditions().add(Restrict.eq("target.id", id));
        MonthTargetDTO monthTargetDTO = new MonthTargetDTO();
        monthTargetDTO.getConditions().add(Restrict.eq("target.id", id));
        if (dayTargetSer.count(dayTargetDTO) != 0 || weekTargetSer.count(weekTargetDTO) != 0
                || monthTargetSer.count(monthTargetDTO) != 0)
            throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    @Override
    public ReferenceTargetBO getById(String id) throws SerException {
        ReferenceTarget entity = super.findById(id);
        if (null == entity)
            throw new SerException("概数据不存在");
        return BeanTransform.copyProperties(entity, ReferenceTargetBO.class);
    }

    @Override
    public List<ReferenceTargetBO> maps(ReferenceTargetDTO dto) throws SerException {
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        dto.getSorts().add("area=desc");
        dto.getSorts().add("type=asc");
        dto.getSorts().add("node=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ReferenceTargetBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        ReferenceTargetDTO dto = new ReferenceTargetDTO();
        return super.count(dto);
    }

    @Override
    public ReferenceTargetBO findByTO(FindTO to) throws SerException {
        List<ReferenceTargetBO> list = this.findListByTO(to);
        if (null != list && list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<ReferenceTargetBO> findListByTO(FindTO to) throws SerException {
        ReferenceTargetDTO dto = new ReferenceTargetDTO();
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getNode()))
            dto.getConditions().add(Restrict.eq("node", to.getNode()));
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        if (null != to.getMonth())
            dto.getConditions().add(Restrict.eq("month", to.getMonth()));
        if (null != to.getYear())
            dto.getConditions().add(Restrict.eq("year", to.getYear()));
        List<ReferenceTarget> list = super.findByCis(dto);
        if (null != list)
            return BeanTransform.copyProperties(list, ReferenceTargetBO.class);
        else
            return new ArrayList<>(0);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
            return true;
        } else {
            return false;
        }
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
}