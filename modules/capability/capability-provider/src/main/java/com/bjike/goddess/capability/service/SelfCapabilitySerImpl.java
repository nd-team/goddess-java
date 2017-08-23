package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.CapacityDTO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.entity.Capacity;
import com.bjike.goddess.capability.entity.SelfCapability;
import com.bjike.goddess.capability.entity.SelfCapabilitySocial;
import com.bjike.goddess.capability.enums.GuideAddrStatus;
import com.bjike.goddess.capability.excele.SelfCapabilityExcele;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个人能力展示业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class SelfCapabilitySerImpl extends ServiceImpl<SelfCapability, SelfCapabilityDTO> implements SelfCapabilitySer {

    @Autowired
    private SelfCapabilitySocialSer selfCapabilitySocialSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CapacitySer capacitySer;
    @Autowired
    private SelfProjectSer selfProjectSer;
    @Autowired
    private UserAPI userAPI;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public Long counts(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        if (StringUtils.isNotBlank(selfCapabilityDTO.getName())) {
            selfCapabilityDTO.getConditions().add(Restrict.like("name", selfCapabilityDTO.getName().trim()));
        }
        Long count = super.count(selfCapabilityDTO);
        return count;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SelfCapabilityBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        SelfCapability selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability, SelfCapabilityBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<SelfCapabilityBO> listSelfCapability(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }
        selfCapabilityDTO.getSorts().add("createTime=desc");
        if (StringUtils.isNotBlank(selfCapabilityDTO.getName())) {
            selfCapabilityDTO.getConditions().add(Restrict.like("name", selfCapabilityDTO.getName().trim()));
        }

        List<SelfCapability> list = super.findByCis(selfCapabilityDTO, true);
        if (null != list && list.size() > 0) {
            for (SelfCapability entity : list) {
                CapacityDTO capacityDTO = new CapacityDTO();
                capacityDTO.getConditions().add(Restrict.eq("baseId", entity.getId()));
                List<Capacity> capacities = capacitySer.findByCis(capacityDTO);
                if (null != capacities && capacities.size() > 0) {
                    for (Capacity capacity : capacities) {
                        entity.setCapacity(capacity.getName());
                    }
                }
            }
        }
        return BeanTransform.copyProperties(list, SelfCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfCapabilityBO addSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是相应人员，不可以进行添加操作");
        }

        SelfCapability selfCapability = BeanTransform.copyProperties(selfCapabilityTO, SelfCapability.class, true);
        selfCapability.setCreateTime(LocalDateTime.now());

        SelfCapabilityDTO dto = new SelfCapabilityDTO();
        dto.getConditions().add(Restrict.eq("name", selfCapabilityTO.getName().trim()));
        List<SelfCapability> selfCapabilityList = super.findByCis(dto);
        if (selfCapabilityList != null && selfCapabilityList.size() > 0) {
            SelfCapability self = selfCapabilityList.get(0);
            if (self.getName().equals(selfCapabilityTO.getName().trim())
                    && (!self.getSelfJobTitle().equals(selfCapabilityTO.getSelfJobTitle().trim())
                    || !self.getPositionTitle().equals(selfCapabilityTO.getPositionTitle().trim())
                    || !self.getWorkYear().equals(selfCapabilityTO.getWorkYear().trim())
                    || !self.getSelfProject().equals(selfCapabilityTO.getSelfProject().trim()))) {

                throw new SerException("个人姓名不能相同");
            } else {
                super.save(selfCapability);
                //添加个人资质
                String[] capacitys = selfCapabilityTO.getCapacity().split(";");
                capacitySer.addCapacity(capacitys, selfCapability.getId());

                //添加个人经手项目
                String[] selfProjects = selfCapabilityTO.getSelfProject().split(";");
                selfProjectSer.addSelfProject(selfProjects, selfCapability.getId());
            }
        } else {
            super.save(selfCapability);
        }

        return BeanTransform.copyProperties(selfCapability, SelfCapabilityBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfCapabilityBO editSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是相应人员，不可以进行编辑操作");
        }

        if (StringUtils.isBlank(selfCapabilityTO.getName())) {
            throw new SerException("失败，姓名不能为空");
        }
        SelfCapability selfCapability = super.findById(selfCapabilityTO.getId());

        SelfCapabilityDTO dto = new SelfCapabilityDTO();
        dto.getConditions().add(Restrict.eq("name", selfCapability.getName().trim()));
        List<SelfCapability> selfCapabilityList = super.findByCis(dto);
        selfCapabilityList.stream().forEach(str -> {
            str.setName(selfCapabilityTO.getName());
            str.setSelfJobTitle(selfCapabilityTO.getSelfJobTitle());
            str.setPositionTitle(selfCapabilityTO.getPositionTitle());
            str.setWorkYear(selfCapabilityTO.getWorkYear());
            str.setSelfProject(selfCapabilityTO.getSelfProject());
            str.setModifyTime(LocalDateTime.now());
        });
        super.update(selfCapabilityList);

        //编辑个人资质
        String[] capacitys = selfCapabilityTO.getCapacity().split(";");
        capacitySer.editCapacity(capacitys, selfCapability.getId());

        //编辑个人经手项目
        String[] selfProjects = selfCapabilityTO.getSelfProjects();
        selfProjectSer.editSelfProject(selfProjects, selfCapability.getId());

        return BeanTransform.copyProperties(selfCapability, SelfCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSelfCapability(String id) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除操作");
        }

        //先删社交
        SelfCapabilitySocialDTO ssDTO = new SelfCapabilitySocialDTO();
        ssDTO.getConditions().add(Restrict.eq("selfCapabilityId", id));
        List<SelfCapabilitySocial> selfCapabilitySocialList = selfCapabilitySocialSer.findByCis(ssDTO);
        selfCapabilitySocialSer.remove(selfCapabilitySocialList);

        super.remove(id);

        //删除个人资质
        capacitySer.deleteCapacity(id);

        //删除个人经手项目
        capacitySer.deleteCapacity(id);
    }


    @Override
    public List<SelfCapabilityBO> listSelfCapabilityByName(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        List<SelfCapabilityBO> selfCapabilityBOS = new ArrayList<>();
        if (StringUtils.isNotBlank(selfCapabilityDTO.getName())) {
            selfCapabilityDTO.getConditions().add(Restrict.eq("name", selfCapabilityDTO.getName().trim()));
            List<SelfCapability> selfCapabilityList = super.findByPage(selfCapabilityDTO);
            selfCapabilityBOS = BeanTransform.copyProperties(selfCapabilityList, SelfCapabilityBO.class);
        }
        return selfCapabilityBOS;
    }


    @Override
    public SelfCapabilityBO getSelf(String name) throws SerException {
        SelfCapabilityBO selfCapabilityBO = new SelfCapabilityBO();

        String[] fields = new String[]{"name", "selfJobTitle", "positionTitle", "workYear", "selfProject"};
        List<SelfCapabilityBO> selfBOS = super.findBySql("select name ,selfJobTitle,positionTitle,workYear, selfProject " +
                " from capability_selfcapability where name = '" + name.trim() + "' ", SelfCapabilityBO.class, fields);

        if (selfBOS != null && selfBOS.size() > 0) {
            selfCapabilityBO = selfBOS.get(0);
        }

        return selfCapabilityBO;
    }


    @Override
    public List<String> listAllSelfName() throws SerException {

        String[] fields = new String[]{"name"};
        List<SelfCapabilityBO> selfBOS = super.findBySql("select name  from capability_selfcapability group by name ", SelfCapabilityBO.class, fields);

        List<String> name = selfBOS.stream().map(SelfCapabilityBO::getName).collect(Collectors.toList());
        return name;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public byte[] exportExcel(String name) throws SerException {
        SelfCapabilityDTO dto = new SelfCapabilityDTO();
        if (!org.springframework.util.StringUtils.isEmpty(name)) {
            dto.getConditions().add(Restrict.eq("name", name));
        }
        List<SelfCapability> list = super.findByCis(dto);
        List<SelfCapabilityExcele> listExcele = new ArrayList<SelfCapabilityExcele>();
        for (SelfCapability module : list) {
            SelfCapabilityExcele excele = new SelfCapabilityExcele();
            BeanUtils.copyProperties(module, excele);
            listExcele.add(excele);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(listExcele, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        if (flagSee) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case AUDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case CONGEL:
                flag = guideSeeIdentity();
                break;
            case THAW:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideSeeIdentity();
                break;
            case DOWNLOAD:
                flag = guideSeeIdentity();
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


}