package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.ContractCategoryBO;
import com.bjike.goddess.businessproject.dto.ContractCategoryDTO;
import com.bjike.goddess.businessproject.entity.ContractCategory;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.excel.ContractCategoryExcel;
import com.bjike.goddess.businessproject.to.ContractCategoryTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商务项目合同类型业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 商务项目合同类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class ContractCategorySerImpl extends ServiceImpl<ContractCategory, ContractCategoryDTO> implements ContractCategorySer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = cusPermissionSer.getCusPermission("1");
        if (!flag) {
            throw new SerException("您不是相应部门的人员，不可以查看");
        }
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = cusPermissionSer.busCusPermission("2");
        if (!flag) {
            throw new SerException("您不是岗位的人员，不可以操作");
        }
    }

    /**
     * 导航蓝核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.getCusPermission("1");
        }else{
            flag = true;
        }
        return flag;
    }


    /**
     * 导航蓝核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.busCusPermission("2");
        }else{
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken( userToken );
        Boolean flagAdd = guideAddIdentity();
        if( flagSee && flagAdd ){
            return true;
        }else{
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    @Override
    public Long countContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        Long count = super.count(contractCategoryDTO);
        return count;
    }

    @Override
    public ContractCategoryBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        ContractCategory contractCategory = super.findById(id);
        return BeanTransform.copyProperties(contractCategory, ContractCategoryBO.class);
    }

    @Override
    public List<ContractCategoryBO> listContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        checkSeeIdentity();

        List<ContractCategory> list = super.findByPage(contractCategoryDTO);
        List<ContractCategoryBO> contractCategoryBOS = BeanTransform.copyProperties(list, ContractCategoryBO.class);
        return contractCategoryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractCategoryBO addContractCategory(ContractCategoryTO contractCategoryTO) throws SerException {
        checkAddIdentity();

        ContractCategory contractCategory = BeanTransform.copyProperties(contractCategoryTO, ContractCategory.class, true);
        contractCategory.setCreateTime(LocalDateTime.now());

        super.save(contractCategory);
        ContractCategoryBO bo = BeanTransform.copyProperties(contractCategory, ContractCategoryBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractCategoryBO editContractCategory(ContractCategoryTO contractCategoryTO) throws SerException {
        checkAddIdentity();

        ContractCategory temp = super.findById(contractCategoryTO.getId());

        ContractCategory contractCategory = BeanTransform.copyProperties(contractCategoryTO, ContractCategory.class, true);
        BeanUtils.copyProperties(contractCategory, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());

        super.update(temp);
        ContractCategoryBO bo = BeanTransform.copyProperties(temp, ContractCategoryBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteContractCategory(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }

    @Override
    public Set<String> allContractNames() throws SerException {
        List<ContractCategory> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ContractCategory c : list) {
            set.add(c.getContractName());
        }
        return set;
    }

    @Override
    public byte[] exportExcel(ContractCategoryDTO dto) throws SerException {
        String[] contractNames = dto.getContractNames();
        List<ContractCategoryExcel> toList = new ArrayList<ContractCategoryExcel>();
        if ((contractNames != null) && (contractNames.length>0)) {
            List<ContractCategory> list = super.findByCis(dto);
            for (String s : contractNames) {
                if (StringUtils.isNotBlank(s)) {
                    for (ContractCategory c : list) {
                        if (s.equals(c.getContractName())) {
                            ContractCategoryExcel excel = new ContractCategoryExcel();
                            BeanUtils.copyProperties(c, excel);
                            toList.add(excel);
                        }
                    }
                }
            }
        } else {
            List<ContractCategory> list = super.findByCis(dto);
            for (ContractCategory c : list) {
                ContractCategoryExcel excel = new ContractCategoryExcel();
                BeanUtils.copyProperties(c, excel);
                toList.add(excel);
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadExcel(List<ContractCategoryExcel> toList) throws SerException {
        for (ContractCategoryExcel to : toList) {
            ContractCategory contractCategory = new ContractCategory();
            BeanUtils.copyProperties(to, contractCategory);
            super.save(contractCategory);
        }
    }
}