package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectBO;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectTitleBO;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.*;
import com.bjike.goddess.supplier.entity.*;
import com.bjike.goddess.supplier.enums.GuideAddrStatus;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import com.bjike.goddess.supplier.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 供应商基本信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.062 ]
 * @Description: [ 供应商基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierInformationSerImpl extends ServiceImpl<SupplierInformation, SupplierInformationDTO> implements SupplierInformationSer {

    @Autowired
    private ContactSituationSer contactSituationSer;
    @Autowired
    private CooperationSituationSer cooperationSituationSer;
    @Autowired
    private RewardSituationSer rewardSituationSer;
    @Autowired
    private EnterpriseQualificationSer enterpriseQualificationSer;
    @Autowired
    private CollectSendSer collectSendSer;
    @Autowired
    private SupplierTypeSer supplierTypeSer;
    @Autowired
    private SupPermissionSer supPermissionSer;
    @Autowired
    private UserAPI userAPI;

    private static final String idFlag = "supplier-01";

    private static final String format = "%s供应商数量";

    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO save(SupplierInformationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = BeanTransform.copyProperties(to, SupplierInformation.class);
        entity.setExecution(this.countExecution(entity));
        entity.setSerialNumber(this.createNumber());
        super.save(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    /**
     * 创建供应商编号
     *
     * @return
     * @throws SerException
     */
    private String createNumber() throws SerException {
        StringBuilder number = new StringBuilder("QS-");
        Integer size = super.findAll().size();
        char[] chars = size.toString().toCharArray();
        for (int i = 6, lent = chars.length; i >= 0; i--) {
            if (i >= lent)
                number.append("0");
            else
                number.append(String.valueOf(chars[i]));
        }
        return number.toString();
    }

    /**
     * 计算供应商信息完成度
     */
    private Double countExecution(SupplierInformation entity) {
        entity.setExecution(0d);
        entity.setId("");
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        double sum = 0d;
        Class aClass = SupplierInformation.class;
        Method[] methods = aClass.getMethods();
        Set<Object> objects = new HashSet<>(0);
        for (Method method : methods)
            if (method.getName().substring(0, 3).equals("get")) {
                try {
                    objects.add(method.invoke(entity, null));
                    sum++;
                } catch (Exception e) {
                }
            }
        entity.setId(null);
        //供应商基本信息实体中有5个字段不纳入计算当中分别是id,创建时间,修改时间,编号和完成度;
        return new BigDecimal((objects.size() - 5) / (sum - 5)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO update(SupplierInformationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SupplierInformation entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setExecution(this.countExecution(entity));
                entity.setId(to.getId());
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SupplierInformationBO updateDetail(SupplierInformationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        return this.update(to);
    }

    @Override
    public List<SupplierInformationBO> findOrderName() throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        SupplierInformationDTO dto = new SupplierInformationDTO();
        dto.getSorts().add("supplierName");
        List<SupplierInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SupplierInformationBO.class);
    }

    @Override
    public SupplierInformationBO delete(String id) throws SerException {
        String userToken = RpcTransmit.getUserToken();
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        String message = "存在依赖关系,无法删除";

        ContactSituationDTO dto = new ContactSituationDTO();
        dto.getConditions().add(Restrict.eq("id","information.id"));
        List<ContactSituation> listContact = contactSituationSer.findByCis( dto );
        if (listContact.size() > 0) {
            contactSituationSer.remove(  listContact );
        }

        CooperationSituationDTO cooperationSituationDTO = new CooperationSituationDTO();
        cooperationSituationDTO.getConditions().add(Restrict.eq("id","information_id"));
        List<CooperationSituation> listcoop = cooperationSituationSer.findByCis(cooperationSituationDTO);
        if (listcoop.size() > 0){
            cooperationSituationSer.remove(listcoop);
        }

        EnterpriseQualificationDTO enterpriseQualificationDTO = new EnterpriseQualificationDTO();
        enterpriseQualificationDTO.getConditions().add(Restrict.eq("id","information_id"));
        List<EnterpriseQualification> listEnterp = enterpriseQualificationSer.findByCis(enterpriseQualificationDTO);
        if (listEnterp.size() > 0){
            enterpriseQualificationSer.remove(listEnterp);
        }

        RewardSituationDTO rewardSituationDTO = new RewardSituationDTO();
        rewardSituationDTO.getConditions().add(Restrict.eq("id","information_id"));
        List<RewardSituation> listrew = rewardSituationSer.findByCis(rewardSituationDTO);

        if (listrew.size() > 0){
            rewardSituationSer.remove(listrew);
        }
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    @Override
    public List<SupplierInformationBO> maps(SupplierInformationDTO dto) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("serialNumber=desc");
        List<SupplierInformation> list = super.findByPage(dto);
        if (null != list && list.size() > 0)
            return BeanTransform.copyProperties(list, SupplierInformationBO.class);
        else
            return new ArrayList<SupplierInformationBO>(0);
    }

    @Override
    public SupplierInformationBO getById(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    @Override
    public void changeEnclosure(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        SupplierInformation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.isEnclosure(Boolean.TRUE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<SupplierInfoCollectBO> collect(CollectTo to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        List<SupplierInfoCollectBO> collectBOs = new ArrayList<>(0);
        SupplierInformationDTO dto = new SupplierInformationDTO();
        if (to.getArea() != null && to.getArea().length != 0) {
            dto.getConditions().add(Restrict.in("area", to.getArea()));
        }
        if (StringUtils.isNotBlank(to.getStart()) && StringUtils.isNotBlank(to.getEnd())) {
            try {
                LocalDateTime[] times = {LocalDateTime.parse(to.getStart(), timeFormat), LocalDateTime.parse(to.getEnd(), timeFormat)};
                dto.getConditions().add(Restrict.between("createTime", times));
            } catch (Exception e) {
                throw new SerException("汇总时间格式不正确,正确格式为:yyyy-MM-dd HH:mm:ss");
            }
        }
        dto.getSorts().add("area=asc");
        dto.getSorts().add("createTime=desc");
        List<SupplierInformation> list = super.findByCis(dto);
        List<EnterpriseQualificationBO> qualificationBOs = new ArrayList<>(0);
        List<String> typeList = new ArrayList<>(0), qualificationList = new ArrayList<>(0);
        String tempArea = "", qualification = "";
        //过滤地区生成供应商汇总数据
        for (SupplierInformation entity : list) {
            qualificationBOs.addAll(enterpriseQualificationSer.findByInformation(entity.getId()));
            if (typeList.stream().filter(s -> s.equals(entity.getType())).count() == 0)
                typeList.add(entity.getType());//过滤重复供应商类型
            if (!tempArea.equals(entity.getArea())) {
                SupplierInfoCollectBO bo = new SupplierInfoCollectBO();
                bo.setArea(entity.getArea());
                bo.setTitleBOs(new ArrayList<>(0));
                collectBOs.add(bo);
            }
        }
        //过滤公司资质资质等级
        for (EnterpriseQualificationBO bo : qualificationBOs.stream()
                .sorted(Comparator.comparing(EnterpriseQualificationBO::getAptitude))
                .collect(Collectors.toList())) {
            if (!qualification.equals(bo.getAptitude()))
                qualificationList.add(bo.getAptitude());
        }
        for (SupplierInfoCollectBO bo : collectBOs) {
            this.countCollect(bo,
                    enterpriseQualificationSer.findByInformationIds(list.stream()
                            .filter(s -> s.getArea().equals(bo.getArea()))
                            .map(SupplierInformation::getId).collect(Collectors.toList()).toArray(new String[0])),
                    list.stream().filter(s -> s.getArea().equals(bo.getArea())).collect(Collectors.toList()),
                    typeList, qualificationList);
        }
        SupplierInfoCollectBO bo = new SupplierInfoCollectBO();
        bo.setArea("合计");
        bo.setTitleBOs(new ArrayList<>(0));
        this.countCollect(bo, qualificationBOs, list, typeList, qualificationList);
        collectBOs.add(bo);
        return collectBOs;
    }

    /**
     * 统计汇总数据
     *
     * @param bo                供应商汇总传输对象
     * @param count             公司资质汇总数据
     * @param list              供应商信息数据
     * @param typeList          供应商类型数据
     * @param qualificationList 资质等级数据
     * @throws SerException
     */
    private void countCollect(SupplierInfoCollectBO bo, List<EnterpriseQualificationBO> count, List<SupplierInformation> list,
                              List<String> typeList, List<String> qualificationList) throws SerException {
        for (String type : typeList) {
            Long number = list.stream().filter(s -> s.getType().equals(type)).count();
            bo.getTitleBOs().add(new SupplierInfoCollectTitleBO(number.intValue(), String.format(format, type)));
        }
        for (String q : qualificationList) {
            Long number = count.stream().filter(e -> e.getAptitude().equals(q)).count();
            bo.getTitleBOs().add(new SupplierInfoCollectTitleBO(number.intValue(), String.format(format, q)));
        }
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = supPermissionSer.getSupPermission(idFlag);
        SonPermissionObject obj;

        obj = new SonPermissionObject();
        obj.setName("supplierinformation");
        obj.setDescribesion("供应商基本信息");
        if (flagSeeSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSee = supplierTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("suppliertype");
        obj.setDescribesion("供应商类型管理");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        flagSee = contactSituationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("contactsituation");
        obj.setDescribesion("联系情况");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        flagSee = cooperationSituationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("cooperationsituation");
        obj.setDescribesion("合作情况");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        flagSee = enterpriseQualificationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("enterprisequalification");
        obj.setDescribesion("企业资质");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        flagSee = rewardSituationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("rewardsituation");
        obj.setDescribesion("获奖情况");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        flagSee = collectSendSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectsend");
        obj.setDescribesion("供应商汇总");
        if (flagSee) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    //chenjunhao
    public List<SupplierInformationBO>  findByName(String name) throws SerException {
        SupplierInformationDTO dto = new SupplierInformationDTO();
        dto.getConditions().add(Restrict.eq("supplierName", name));
        List<SupplierInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SupplierInformationBO.class);
    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = supPermissionSer.getSupPermission(idFlag);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = supPermissionSer.getSupPermission(idFlag);
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