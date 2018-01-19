package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.materialbuy.bo.*;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.dto.TempMatterDemandDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.entity.TempMatterDemand;
import com.bjike.goddess.materialbuy.enums.AuditState;
import com.bjike.goddess.materialbuy.enums.DateType;
import com.bjike.goddess.materialbuy.enums.GuideAddrStatus;
import com.bjike.goddess.materialbuy.to.GuidePermissionTO;
import com.bjike.goddess.materialbuy.to.MaterialBuyTO;
import com.bjike.goddess.materialbuy.vo.SonPermissionObject;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 物资购买业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class MaterialBuySerImpl extends ServiceImpl<MaterialBuy, MaterialBuyDTO> implements MaterialBuySer {

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DeviceTypeSer deviceTypeSer;
    @Autowired
    private TempMatterDemandSer tempMatterDemandSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

    private static String title = "有物资购买需要您审核";

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
        obj.setName("materialbuy");
        obj.setDescribesion("物资购买");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = deviceTypeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("devicetype");
        obj.setDescribesion("设备类型");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis1 = tempMatterDemandSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("tempmatterdemand");
        obj.setDescribesion("临时物资需求");
        if (flagSeeDis1) {
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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
     * 分页查询物资购买
     *
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public List<MaterialBuyBO> list(MaterialBuyDTO dto) throws SerException {
        checkSeeIdentity();
        List<MaterialBuy> list = super.findByPage(dto);
        List<MaterialBuyBO> listBO = BeanTransform.copyProperties(list, MaterialBuyBO.class);
        if (listBO != null) {

        }

        return listBO;
    }

    /**
     * 保存物资购买
     *
     * @param to 物资购买to
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialBuyBO save(MaterialBuyTO to) throws SerException {
        checkAddIdentity();
        MaterialBuy entity = BeanTransform.copyProperties(to, MaterialBuy.class, true);
        entity.setIfPayment(Boolean.FALSE);
        entity = super.save(entity);
        String requisitioner = entity.getRequisitioner();
        String content = "1." + requisitioner + "在系统申请物资需" +
                "求成功;\n2.请" +
                "确认是否需要" +
                "购买并在第二天" +
                "17:00前填写审核情" +
                "况";
        String t = title;
        send1(t, content);
        MaterialBuyBO bo = BeanTransform.copyProperties(entity, MaterialBuyBO.class);
        return bo;
    }

    private void send1(String title, String content) throws SerException {
        List<String> emails = new ArrayList<>();
        List<DepartmentDetailBO> departs = departmentDetailAPI.findStatus();
        for (DepartmentDetailBO depart : departs) {
            if ("运营商务部".equals(depart.getDepartment()) || "综合资源部".equals(depart.getDepartment())) {
                CommonalityBO commonalityBO = commonalityAPI.findByDepartment(depart.getId());
                if (commonalityBO != null) {
                    emails.add(commonalityBO.getEmail());
                }
            }
        }
        if (!emails.isEmpty()) {
            String[] mails = new String[emails.size()];
            mails = emails.toArray(mails);
            send(title, content, mails);
        }
        List<PositionDetailBO> list = positionDetailAPI.findStatus();
        List<UserBO> users = null;
        for (PositionDetailBO positionDetailBO : list) {
            if ("福利模块".equals(positionDetailBO.getModuleName()) && ("管理层").equals(positionDetailBO.getArrangementName())) {
                users = positionDetailUserAPI.findByPosition(positionDetailBO.getId());
            }
        }
        if (users != null) {
            List<String> list1 = new ArrayList<>();
            for (UserBO user : users) {
                list1.add(user.getUsername());
            }
            if (!list1.isEmpty()) {
                String[] names = new String[list1.size()];
                names = list1.toArray(names);
                List<String> mails = internalContactsAPI.getEmails(names);
                if (!mails.isEmpty()) {
                    String[] strings = new String[mails.size()];
                    strings = mails.toArray(strings);
                    send(title, content, strings);
                }
            }
        }
    }

    private void send(String title, String content, String[] receivers) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle(title);
        messageTO.setContent(content);
        messageTO.setReceivers(receivers);
        messageAPI.send(messageTO);
    }

    /**
     * 根据id删除物资购买
     *
     * @param id 物资购买唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    /**
     * 更新物资购买
     *
     * @param to 物资购买to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialBuyTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialBuy model = super.findById(to.getId());
            if (model != null) {
                updateMaterialBuy(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资购买
     *
     * @param to    物资购买to
     * @param model 物资购买
     * @throws SerException
     */
    private void updateMaterialBuy(MaterialBuyTO to, MaterialBuy model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
        if (AuditState.AUDITED.equals(model.getAuditState())) {
            String t = "有物资购买审核通过";
            String content = model.getRequisitioner() + "的物资购买申请已通过";
            send1(t, content);
            String content1 = "网页链接如下：" + model.getBuyAddress() + "，请确认";
            String title1 = "请确认网页链接";
            UserBO user = userAPI.findByUsername(model.getRequisitioner());
            if (user != null) {
                String email = internalContactsAPI.getEmail(user.getUsername());
                if (null != email) {
                    send(title1, content1, new String[]{email});
                }
            }
        }
    }

    /**
     * 文件上传
     *
     * @param maps 文件名，byte 文件字节
     * @param path 上传路径
     */
    @Override
    public void upload(Map<String, byte[]> maps, String path) throws SerException {
        // TODO: 17-4-20
    }

    /**
     * 查看详情
     *
     * @param id 物资购买唯一标识
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public MaterialBuyBO checkDetail(String id) throws SerException {
        MaterialBuy model = super.findById(id);
        return BeanTransform.copyProperties(model, MaterialBuyBO.class);
    }

    /**
     * 地区负责人审核
     *
     * @param to 物资购买to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void areaPrincipalAudit(MaterialBuyTO to) throws SerException {
        update(to);
    }

    /**
     * 查询等待付款
     *
     * @param dto 物资购买dto
     * @return class MaterialBuyBO
     * @throws SerException
     */
    @Override
    public List<MaterialBuyBO> findWaitPay(MaterialBuyDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("ifPayment", Boolean.TRUE));
        List<MaterialBuy> list = super.findByCis(dto);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        List<MaterialBuyBO> listBO = BeanTransform.copyProperties(list, MaterialBuyBO.class);
        return listBO;
    }

    /**
     * 地区购买情况汇总
     *
     * @return class AreaBuyStatusDayCollectBO
     * @throws SerException
     */
    @Override
    public List<AreaBuyStatusDayCollectBO> areaBuyStatusDaySum() throws SerException {
        checkSeeIdentity();
        //查询所有部门
        List<OpinionBO> listBO = departmentDetailAPI.findAllOpinion();
        if (CollectionUtils.isEmpty(listBO)) {
            return null;
        }
        List<String> listDepartments = new ArrayList<>(0);
        for (OpinionBO opinionBO : listBO) {
            if (StringUtils.isNotBlank(opinionBO.getValue())) {
                listDepartments.add(opinionBO.getValue());
            }
        }
        List<String> listDeviceTypes = deviceTypeSer.findAllDeviceNames();
        List<AreaBuyStatusDayCollectBO> areaCollectBO = new ArrayList<>(0);
        for (String department : listDepartments) {
            for (String deviceType : listDeviceTypes) {
                MaterialBuyDTO dto = new MaterialBuyDTO();
                dto.getConditions().add(Restrict.eq("subscribeDate", LocalDate.now()));
                dto.getConditions().add(Restrict.eq("projectTeam", department));
                dto.getConditions().add(Restrict.eq("deviceType", deviceType));
                dto.getSorts().add("subscribeDate=desc");
                List<MaterialBuy> list = super.findByCis(dto);
                String area = null;
                Integer totalQty = 0;//总数量
                Double totalAmount = 0d;//总金额
                for (MaterialBuy materialBuy : list) {
                    Integer quantity = materialBuy.getQuantity() == null ? 0 : (materialBuy.getQuantity());
                    Double unitPrice = materialBuy.getUnitPrice() == null ? 0 : (materialBuy.getUnitPrice());
                    Double amount = quantity * unitPrice;
                    totalQty += quantity;
                    totalAmount += amount;
                }

            }

        }

        return null;
    }

    @Override
    public Long count(MaterialBuyDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<String> findDevType(String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String devType = model.getDeviceType();
            if (StringUtils.isNotBlank(model.getDeviceType())) {
                set.add(devType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findAreaByType(String devType, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("deviceType", devType));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String areas = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(areas);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDeparByTyAre(String devType, String area, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("deviceType", devType));
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String projectTeam = model.getProjectTeam();
            if (StringUtils.isNotBlank(model.getProjectTeam())) {
                set.add(projectTeam);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<MaterialBuyBO> findByTyAndAr(String devType, String area, String department, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("deviceType", devType));
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("projectTeam", department));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        return BeanTransform.copyProperties(materialBuys, MaterialBuyBO.class);
    }


    @Override
    public List<String> findArea(String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDepByArea(String area, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String projectTeam = model.getProjectTeam();
            if (StringUtils.isNotBlank(model.getProjectTeam())) {
                set.add(projectTeam);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDevByAreaDev(String area, String projectTeam, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("projectTeam", projectTeam));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String devType = model.getDeviceType();
            if (StringUtils.isNotBlank(model.getDeviceType())) {
                set.add(devType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<MaterialBuyBO> findByTeamAnArea(String area, String projectTeam, String devType, String[] intervalTime) throws SerException {
//        MaterialBuyDTO dto = new MaterialBuyDTO();
//        dto.getConditions().add(Restrict.eq("projectTeam", projectTeam));
//        dto.getConditions().add(Restrict.eq("area", area));
//        dto.getConditions().add(Restrict.eq("deviceType", devType));
//        dto.getConditions().add(Restrict.between("subscribeDate",intervalTime));
//        List<MaterialBuy> materialBuys = super.findByCis(dto);
//        return BeanTransform.copyProperties(materialBuys,MaterialBuyBO.class);
        return null;
    }

    @Override
    public List<String> findRequis(String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String requisitioner = model.getRequisitioner();
            if (StringUtils.isNotBlank(model.getRequisitioner())) {
                set.add(requisitioner);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findByRequis(String requisitioner, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("requisitioner", requisitioner));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        if (CollectionUtils.isEmpty(materialBuys)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (MaterialBuy model : materialBuys) {
            String devType = model.getDeviceType();
            if (StringUtils.isNotBlank(model.getDeviceType())) {
                set.add(devType);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<MaterialBuyBO> findByRequisType(String requisitioner, String devType, String[] intervalTime) throws SerException {
        MaterialBuyDTO dto = new MaterialBuyDTO();
        dto.getConditions().add(Restrict.eq("requisitioner", requisitioner));
        dto.getConditions().add(Restrict.eq("deviceType", devType));
        dto.getConditions().add(Restrict.between("subscribeDate", intervalTime));
        List<MaterialBuy> materialBuys = super.findByCis(dto);
        return BeanTransform.copyProperties(materialBuys, MaterialBuyBO.class);
    }

    @Override
    public List<String> findSubscribeDate() throws SerException {
        List<String> stringList = new ArrayList<>(0);
        List<MaterialBuy> materialBuys = super.findAll();
        if (!CollectionUtils.isEmpty(materialBuys)) {
            List<LocalDate> list = materialBuys.stream().map(MaterialBuy::getSubscribeDate).distinct().collect(Collectors.toList());
            for (LocalDate localDate : list) {
                stringList.add(localDate.toString());
            }
        }
        return stringList;
    }

    @Override
    public List<String> findRequisitioner() throws SerException {
        List<String> list = new ArrayList<>(0);
        List<MaterialBuy> materialBuys = super.findAll();
        if (!CollectionUtils.isEmpty(materialBuys)) {
            list = materialBuys.stream().map(MaterialBuy::getRequisitioner).distinct().collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public byte[] exportExcel(MaterialBuyDTO dto) throws SerException {
        return new byte[0];
    }

    /**
     * 物资购买汇总说明
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<MaterialBuySummaryBO> materialBuySum(MaterialBuyDTO dto) throws SerException {
        List<MaterialBuySummaryBO> materialBuySummaryBOS = this.findByBuySum(dto);
        for (int i = 0; i < materialBuySummaryBOS.size(); i++) {
            MaterialBuySummaryBO buySummaryBO = materialBuySummaryBOS.get(i);
            List<MaterialBuySummaryBO> list = this.findByTempBuySum(dto, buySummaryBO);
            if (list.size() > 0) {
                buySummaryBO.setIfStockSatisfy(list.get(0).getIfStockSatisfy());
                buySummaryBO.setIfFinanceAudit(list.get(0).getIfFinanceAudit());
                buySummaryBO.setApplyQuantity(list.get(0).getApplyQuantity());
            } else {
                buySummaryBO.setIfStockSatisfy("0");
                buySummaryBO.setIfFinanceAudit("0");
                buySummaryBO.setApplyQuantity("0");
            }
        }
        return materialBuySummaryBOS;
    }

    /**
     * 物资购买汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    public List<MaterialBuySummaryBO> findByBuySum(MaterialBuyDTO dto) throws SerException {
        List<MaterialBuySummaryBO> materialBuySummaryBOS = new ArrayList<>();
        /**
         * 是否要根据日期进行汇总
         */
        if (dto.getDateType() != null) {
            if (DateType.YEAR.name().equals(dto.getDateType().name())) {
            } else if (DateType.MONTH.name().equals(dto.getDateType().name())) {
            } else if (DateType.WEEK.name().equals(dto.getDateType().name())) {
            } else if (DateType.DAY.name().equals(dto.getDateType().name())) {
            } else if (DateType.QUARTER.name().equals(dto.getDateType().name())) {
            }
        } else {
            String sql = "SELECT area,projectTeam,deviceType,deviceName,model " +
                    "    ,ifnull((case any_value(ifReplaceBorrow) WHEN '1' THEN  any_value(count(ifReplaceBorrow)) end),0) ifReplaceBorrow " +
                    "    ,ifnull((case any_value(ifPayment) WHEN '1' THEN  any_value(count(ifPayment)) end),0) ifPayment " +
                    "    ,ifnull((case any_value(ifArrival) WHEN '1' THEN  any_value(count(ifArrival)) end),0) ifArrival " +
                    "    ,ifnull((case any_value(ifCommerceAudit) WHEN '1' THEN  any_value(count(ifCommerceAudit)) end),0) ifCommerceAudit " +
                    "    ,any_value(sum(quantity)) buyQuantity ,any_value(sum(totalSum)) totalSum " +
                    " FROM materialbuy_materialbuy " +
                    "GROUP BY area,projectTeam,deviceType,deviceName,model; ";
            String[] fields = {"area", "projectTeam", "deviceType", "deviceName", "model", "ifReplaceBorrow", "ifPayment", "ifArrival", "ifCommerceAudit", "buyQuantity", "totalSum"};
            materialBuySummaryBOS = super.findBySql(sql, MaterialBuySummaryBO.class, fields);
        }

        return materialBuySummaryBOS;
    }

    /**
     * 临时物资需求汇总
     *
     * @param dto
     * @param bo
     * @return
     * @throws SerException
     */
    public List<MaterialBuySummaryBO> findByTempBuySum(MaterialBuyDTO dto, MaterialBuySummaryBO bo) throws SerException {
        List<MaterialBuySummaryBO> materialBuySummaryBOS = new ArrayList<>();
        /**
         * 是否要根据日期进行汇总
         */
        if (dto.getDateType() != null) {
            if (DateType.YEAR.name().equals(dto.getDateType().name())) {
            } else if (DateType.MONTH.name().equals(dto.getDateType().name())) {
            } else if (DateType.WEEK.name().equals(dto.getDateType().name())) {
            } else if (DateType.DAY.name().equals(dto.getDateType().name())) {
            } else if (DateType.QUARTER.name().equals(dto.getDateType().name())) {
            }
        } else {
            String sql = "SELECT  " +
                    "   ifnull((case any_value(ifStockSatisfy) WHEN '1' THEN  any_value(count(ifStockSatisfy)) end),0) ifStockSatisfy " +
                    "  ,ifnull((case any_value(ifFinanceAudit) WHEN '1' THEN  any_value(count(ifFinanceAudit)) end),0) ifFinanceAudit " +
                    "  ,any_value(sum(quantity))  applyQuantity " +
                    "FROM materialbuy_tempmatterdemand " +
                    "WHERE area = '" + bo.getArea() + "' AND projectTeam = '" + bo.getProjectTeam() + "' AND deviceType = '" + bo.getDeviceType() + "' AND deviceName = '" + bo.getDeviceName() + "' AND model = '" + bo.getModel() + "'" +
                    "GROUP BY area,projectTeam,deviceType,deviceName,model;";
            String[] fields = {"ifStockSatisfy", "ifFinanceAudit", "applyQuantity"};
            materialBuySummaryBOS = super.findBySql(sql, MaterialBuySummaryBO.class, fields);
        }
        return materialBuySummaryBOS;
    }

    @Override
    public OptionBO GUI(String year, String month) throws SerException {
        OptionBO bo = new OptionBO();
        Integer y = Integer.parseInt(year);
        Integer m = Integer.parseInt(month);

        List<Integer> list = new ArrayList<>();
        List<LocalDate[]> localDates = new ArrayList<>();
        Integer num = DateUtil.getWeekNum(y, m);//根据年月 获取有几周
        for (int i = 0; i < num; i++) {
            LocalDate[] localDate = DateUtil.getWeekTimes(y, m, i + 1);
            localDates.add(localDate);
        }
        for (int i = 0; i < localDates.size(); i++) {
            LocalDate[] localDate = localDates.get(i);
            list.add(todayWeek(localDate[0]));
        }
        //根据年月获取全部的项目组
        List<String> projects = getProjects(year, month);
        //seriser 数据列
        List<SeriesBO> seriesBOS = new ArrayList<>();

        //开始获取全部项目组 全部周数的总额
        for (int i = 0; i < projects.size(); i++) {
            Double[] doubles = new Double[num];
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(projects.get(i));
            seriesBO.setType("line");
            for (int j = 0; j < list.size(); j++) {
                String sql = "SELECT sum(totalSum) AS totalSum FROM " +
                        "  materialbuy_materialbuy " +
                        " WHERE WEEK(subscribeDate) = '" + list.get(j) + "' AND projectTeam = '" + projects.get(i) + "' " +
                        " GROUP BY projectTeam";
                List<Object> l = super.findBySql(sql);
                if (l.size() > 0) {
                    doubles[j] = Double.parseDouble(l.get(0) + "");
                } else {
                    doubles[j] = Double.parseDouble("0");
                }
            }
            seriesBO.setData(doubles);
            seriesBOS.add(seriesBO);
        }
        SeriesBO[] series = new SeriesBO[seriesBOS.size()];
        for (int i = 0; i < seriesBOS.size(); i++) {
            SeriesBO seriesBO = seriesBOS.get(i);
            series[i] = seriesBO;
        }

        TitleBO title = new TitleBO();
        title.setText(String.format("物资购买月汇总（%s年%s月）", year, month));
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("item");
        LegendBO legendBO = new LegendBO();
        String[] d = (String[]) projects.toArray(new String[projects.size()]);
        legendBO.setData(d);
        XAxisBO xAxisBO = new XAxisBO();
        String[] data = new String[]{"第一周", "第二周", "第三周", "第四周", "第五周"};
        xAxisBO.setData(data);
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        bo.setTitle(title);
        bo.setTooltip(tooltipBO);
        bo.setLegend(legendBO);
        bo.setxAxis(xAxisBO);
        bo.setyAxis(yAxisBO);
        bo.setSeries(series);
        return bo;
    }

    /**
     * 获取当前时间是当前年份第几周
     *
     * @param localDate 传入的日期
     * @return
     * @throws SerException
     */
    public static int todayWeek(LocalDate localDate) throws SerException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(DateUtil.dateToString(localDate));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
            return weekOfMonth;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    /**
     * 根据年月获取全部的项目组
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    public List<String> getProjects(String year, String month) throws SerException {
        List<String> projects = new ArrayList<>();
        Integer y = Integer.parseInt(year);
        Integer m = Integer.parseInt(month);
        LocalDate startDate = DateUtil.getStartDayOfMonth(y, m);
        LocalDate endDate = DateUtil.getEndDaYOfMonth(y, m);

        String sql = "SELECT projectTeam  FROM  materialbuy_materialbuy " +
                "WHERE subscribeDate BETWEEN '" + startDate + "' AND '" + endDate + "' GROUP BY projectTeam ";
        List<Object> list = super.findBySql(sql);

        for (int i = 0; i < list.size(); i++) {
            projects.add(list.get(i) + "");
        }
        return projects;
    }

    /**
     * 根据年月周汇总各个部门情况
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    @Override
    public OptionBO GuiByWeek(String year, String month, String week) throws SerException {
        OptionBO bo = new OptionBO();
        Integer y = Integer.parseInt(year);
        Integer m = Integer.parseInt(month);
        Integer w = Integer.parseInt(week);
        //获取当前你年月周 日期
        LocalDate[] localDate = DateUtil.getWeekTimes(y, m, w);
        //获取开始日期 ，结束日期
        LocalDate startDate = localDate[0];
        LocalDate endDate = localDate[1];

        List<String> projects = getProjects(startDate, endDate);
        SeriesBO[] series = new SeriesBO[projects.size()];

        for (int i = 0; i < projects.size(); i++) {
            Double applyNum = 0D;
            Double buyNum = 0D;
            Double arrivalNum = 0D;
            Double[] doubles = new Double[3];
            MaterialBuyDTO dto = new MaterialBuyDTO();
            dto.getConditions().add(Restrict.between("subscribeDate", new LocalDate[]{startDate, endDate}));
            dto.getConditions().add(Restrict.eq("projectTeam", projects.get(i)));
            List<MaterialBuy> materialBuys = super.findByCis(dto);

            TempMatterDemandDTO demandDTO = new TempMatterDemandDTO();
            demandDTO.getConditions().add(Restrict.between("requiredDate", new LocalDate[]{startDate, endDate}));
            demandDTO.getConditions().add(Restrict.eq("projectTeam", projects.get(i)));
            List<TempMatterDemand> tempMatterDemands = tempMatterDemandSer.findByCis(demandDTO);

            for (MaterialBuy buy : materialBuys) {
                buyNum += buy.getQuantity();
                if (buy.getIfArrival()) {
                    arrivalNum += buy.getQuantity();
                }
            }

            for (TempMatterDemand tempMatterDemand : tempMatterDemands) {
                applyNum += tempMatterDemand.getQuantity();
            }
            doubles[0] = applyNum;
            doubles[1] = buyNum;
            doubles[2] = arrivalNum;
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(projects.get(i));
            seriesBO.setType("line");
            seriesBO.setData(doubles);
            series[i] = seriesBO;
        }

        TitleBO title = new TitleBO();
        title.setText(String.format("物资购买周汇总（%s年%s月%s～%s)", year, month, startDate, endDate));
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("item");
        LegendBO legendBO = new LegendBO();
        String[] d = (String[]) projects.toArray(new String[projects.size()]);
        legendBO.setData(d);
        XAxisBO xAxisBO = new XAxisBO();
        String[] data = new String[]{"物资需求申请数", "物资购买数量", "到货数"};
        xAxisBO.setData(data);
        YAxisBO yAxisBO = new YAxisBO();
        yAxisBO.setType("value");

        bo.setTitle(title);
        bo.setTooltip(tooltipBO);
        bo.setLegend(legendBO);
        bo.setxAxis(xAxisBO);
        bo.setyAxis(yAxisBO);
        bo.setSeries(series);
        return bo;
    }

    /**
     * 根据开始日期 结束日期获取全部的项目组
     *
     * @return
     * @throws SerException
     */
    public List<String> getProjects(LocalDate startDate, LocalDate endDate) throws SerException {
        List<String> projects = new ArrayList<>();

        String sql = "SELECT projectTeam  FROM  materialbuy_materialbuy " +
                "WHERE subscribeDate BETWEEN '" + startDate + "' AND '" + endDate + "' GROUP BY projectTeam ";
        List<Object> list = super.findBySql(sql);

        for (int i = 0; i < list.size(); i++) {
            projects.add(list.get(i) + "");
        }
        return projects;
    }
}