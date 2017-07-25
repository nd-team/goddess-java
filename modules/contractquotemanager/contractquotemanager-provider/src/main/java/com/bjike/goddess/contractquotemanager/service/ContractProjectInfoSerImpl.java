package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractProjectInfo;
import com.bjike.goddess.contractquotemanager.excel.ContractProjectInfoExcel;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;
import com.bjike.goddess.contractquotemanager.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: []
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractquotemanagerSerCache")
@Service
public class ContractProjectInfoSerImpl extends ServiceImpl<ContractProjectInfo, ContractProjectInfoDTO> implements ContractProjectInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;


    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //财务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询合同项目基本信息
     *
     * @param dto 合同项目基本信息dto
     * @return class ContractProjectInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ContractProjectInfoBO> list(ContractProjectInfoDTO dto) throws SerException {
        checkPermission();
        List<ContractProjectInfo> list = super.findByPage(dto);
        List<ContractProjectInfoBO> boList = BeanTransform.copyProperties(list, ContractProjectInfoBO.class);
        return boList;
    }

    /**
     * 保存合同项目基本信息
     *
     * @param to 合同项目基本信息to
     * @return class ContractProjectInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ContractProjectInfoBO save(ContractProjectInfoTO to) throws SerException {
        checkPermission();
        ContractProjectInfo entity = BeanTransform.copyProperties(to, ContractProjectInfo.class, true);
        super.save(entity);
        ContractProjectInfoBO bo = BeanTransform.copyProperties(entity, ContractProjectInfoBO.class);
        return bo;
    }

    /**
     * 根据id删除合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新合同项目基本信息
     *
     * @param to 合同项目基本信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ContractProjectInfoTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            ContractProjectInfo model = super.findById(to.getId());
            if (model != null) {
                updateContractProjectInfo(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新合同项目基本信息
     *
     * @param to    合同项目基本信息to
     * @param model 合同项目基本信息
     * @throws SerException
     */
    private void updateContractProjectInfo(ContractProjectInfoTO to, ContractProjectInfo model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 查看所有的地区
     *
     * @return list
     * @throws SerException
     */
    @Override
    public List<String> findAllAreas() throws SerException {
        List<ContractProjectInfo> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (ContractProjectInfo model : list) {
            String area = model.getArea();
            if (StringUtils.isNotBlank(model.getArea())) {
                set.add(area);
            }
        }
        return new ArrayList<>(set);
    }


    @Override
    public byte[] exportExcel(ContractProjectInfoDTO contractProjectInfoDTO) throws SerException {
        checkPermission();
        if (null != contractProjectInfoDTO.getAreas() && contractProjectInfoDTO.getAreas().length != 0) {
            contractProjectInfoDTO.getConditions().add(Restrict.in("area", contractProjectInfoDTO.getAreas()));
        }

        List<ContractProjectInfo> list = super.findByCis(contractProjectInfoDTO);

        List<ContractProjectInfoExcel> contractProjectInfoExcels = new ArrayList<>();
        list.stream().forEach(str -> {
            ContractProjectInfoExcel excel = BeanTransform.copyProperties(str, ContractProjectInfoExcel.class);
            contractProjectInfoExcels.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(contractProjectInfoExcels, excel);
        return bytes;
    }

    @Override
    public void importExcel(List<ContractProjectInfoTO> contractProjectInfoTOS) throws SerException {
        {
            List<ContractProjectInfo> contractProjectInfo = BeanTransform.copyProperties(contractProjectInfoTOS, ContractProjectInfo.class, true);
            contractProjectInfo.stream().forEach(str -> {
                str.setCreateTime(LocalDateTime.now());
                str.setModifyTime(LocalDateTime.now());
            });
            super.save(contractProjectInfo);
        }
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ContractProjectInfoExcel> contractProjectInfoExcels = new ArrayList<>();

        ContractProjectInfoExcel excel = new ContractProjectInfoExcel();
        excel.setArea("广州");
        excel.setProjectGroup("广州研发组");
        excel.setProjectInner("扁平化");
        excel.setDispatchProject("test");
        excel.setDispatchNum("A2321232");
        excel.setOutProjectNum("test");
        excel.setStartProjectTime("test");
        excel.setEndProjectTime("test");
        excel.setCompleteTime(5);
        excel.setMoney(6000d);
        excel.setMajorCompany("华为");
        excel.setContractScale("3000M");
        excel.setReceivableNum(38d);
        excel.setEmphasis("重要");
        excel.setDifficulty("难");
        contractProjectInfoExcels.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(contractProjectInfoExcels, exce);
        return bytes;
    }
}