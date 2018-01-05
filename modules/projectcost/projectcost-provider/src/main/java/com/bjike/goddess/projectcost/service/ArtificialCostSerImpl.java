package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.bo.ArtificialCostBO;
import com.bjike.goddess.projectcost.dto.ArtificialCostDTO;
import com.bjike.goddess.projectcost.entity.ArtificialCost;
import com.bjike.goddess.projectcost.enums.GuideAddrStatus;
import com.bjike.goddess.projectcost.excel.SonPermissionObject;
import com.bjike.goddess.projectcost.to.ArtificialCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 人工费用业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcostSerCache")
@Service
public class ArtificialCostSerImpl extends ServiceImpl<ArtificialCost, ArtificialCostDTO> implements ArtificialCostSer {


    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CarCostSer carCostSer;
    @Autowired
    private CostControlSer costControlSer;
    @Autowired
    private OtherExpensesSer otherExpensesSer;

    private ArtificialCostBO transformBO(ArtificialCost entity) throws SerException {
        ArtificialCostBO bo = BeanTransform.copyProperties(entity, ArtificialCostBO.class);
        FindTO to = BeanTransform.copyProperties(entity, FindTO.class);
        List<ArtificialCost> list = this.findAsTO(to);
        if (entity.getActualCost() == 0)
            bo.setArtificial(0d);
        else
            bo.setArtificial(this.decimal(entity.getActualCost() / list.stream().mapToDouble(ArtificialCost::getActualCost).sum()));
        return bo;
    }

    private List<ArtificialCostBO> transformBOList(List<ArtificialCost> list) throws SerException {
        List<ArtificialCostBO> bos = new ArrayList<>(list.size());
        for (ArtificialCost entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public ArtificialCostBO save(ArtificialCostTO to) throws SerException {
        ArtificialCost entity = BeanTransform.copyProperties(to, ArtificialCost.class);
        entity.setActualHour(0d);//@TODO 统计任务分配对应工时
        this.countCost(entity);
        super.save(entity);
        return this.transformBO(entity);
    }

    private void countCost(ArtificialCost entity) throws SerException {
        entity.setTargetCost(this.decimal(entity.getUnivalent() * entity.getTargetHour()));
        entity.setActualCost(this.decimal(entity.getUnivalent() * entity.getActualHour()));
        entity.setProportion(this.decimal(entity.getActualCost() / entity.getTargetCost()));
    }

    private Double decimal(Double number) throws SerException {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public ArtificialCostBO update(ArtificialCostTO to) throws SerException {
        ArtificialCost entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setActualHour(0d);//@TODO 统计任务分配对应工时
        this.countCost(entity);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public ArtificialCostBO delete(String id) throws SerException {
        ArtificialCost entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ArtificialCostBO.class);
    }

    @Override
    public List<ArtificialCostBO> maps(ArtificialCostDTO dto) throws SerException {
        dto.getSorts().add("year=desc");
        dto.getSorts().add("month=desc");
        dto.getSorts().add("area=desc");
        dto.getSorts().add("project=desc");
        dto.getSorts().add("name=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ArtificialCostBO updateActual(ArtificialCostTO to) throws SerException {
        ArtificialCost entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不存在");
        BeanTransform.copyProperties(to, entity, true);
        this.countCost(entity);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public ArtificialCostBO getById(String id) throws SerException {
        ArtificialCost entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        ArtificialCostDTO dto = new ArtificialCostDTO();
        return super.count(dto);
    }

    private List<ArtificialCost> findAsTO(FindTO to) throws SerException {
        ArtificialCostDTO dto = new ArtificialCostDTO();
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getProject()))
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        if (StringUtils.isNotBlank(to.getName()))
            dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (null != to.getMonth())
            dto.getConditions().add(Restrict.eq("month", to.getMonth()));
        if (null != to.getYear())
            dto.getConditions().add(Restrict.eq("year", to.getYear()));
        return super.findByCis(dto);

    }

    @Override
    public List<ArtificialCostBO> findByTO(FindTO to) throws SerException {
        return this.transformBOList(this.findAsTO(to));
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
        obj.setName("artificialcost");
        obj.setDescribesion("人工费用");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = carCostSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("carcost");
        obj.setDescribesion("车辆费用");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = costControlSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("costcontrol");
        obj.setDescribesion("项目成本控制");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = otherExpensesSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("otherexpenses");
        obj.setDescribesion("其他费用");
        if (flagSeeBase) {
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
    public List<ArtificialCostBO> find(Integer year, Integer month, String project) throws SerException {
        ArtificialCostDTO dto = new ArtificialCostDTO();
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        dto.getConditions().add(Restrict.eq("project", project));
        List<ArtificialCost> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ArtificialCostBO.class);
    }
}