package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.materialbuy.bo.AreaBuyStatusDayCollectBO;
import com.bjike.goddess.materialbuy.bo.MaterialBuyBO;
import com.bjike.goddess.materialbuy.dto.MaterialBuyDTO;
import com.bjike.goddess.materialbuy.entity.MaterialBuy;
import com.bjike.goddess.materialbuy.enums.AuditState;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
                CommonalityBO commonalityBO=commonalityAPI.findByDepartment(depart.getId());
                if(commonalityBO!=null){
                    emails.add(commonalityBO.getEmail());
                }
            }
        }
        String[] mails = new String[emails.size()];
        mails = emails.toArray(mails);
        send(title, content, mails);
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
                list1.add(user.getId());
            }
            String[] ids = new String[list1.size()];
            ids = list1.toArray(ids);
            send(title, content, ids);
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
            UserBO user=userAPI.findByUsername(model.getRequisitioner());
            if (user!=null) {
                String[] reciers = new String[]{user.getId()};
                send(title1, content1, reciers);
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
}