package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.FilesDataBO;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.dto.FilesDataDTO;
import com.bjike.goddess.marketdevelopment.entity.Customer;
import com.bjike.goddess.marketdevelopment.entity.FilesData;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 阶段表头数据业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class FilesDataSerImpl extends ServiceImpl<FilesData, FilesDataDTO> implements FilesDataSer {
    @Autowired
    private CustomerSer customerSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

    private static final String planManage = "plan-manage";

    private static final String planCheck = "plan-check";

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
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
    public FilesDataBO findFiles() throws SerException {
        FilesDataDTO dto = new FilesDataDTO();
//        dto.getConditions().add(Restrict.eq("dateDataId", ""));
//        dto.getConditions().add(Restrict.ne("tableIndex", ""));
//        dto.getConditions().add(Restrict.ne("tableName", ""));
//        dto.getConditions().add(Restrict.eq("context", ""));
        dto.getConditions().add(Restrict.eq("fatherId", "1"));
        dto.getSorts().add("tableIndex=asc");
        List<FilesData> filesDataList = super.findByCis(dto);
        if (null != filesDataList && filesDataList.size() > 0) {
            FilesDataBO bo = new FilesDataBO();
            bo.setTableName("阶段");
            List<FilesDataBO> bos = new ArrayList<>(0);
            for (FilesData entity : filesDataList) {
                FilesDataBO filesDataBO = BeanTransform.copyProperties(entity, FilesDataBO.class, false);
                bos.add(filesDataBO);
//                bo.setFilesDataVO1s(bos);
            }
            bo.setFilesDataVO1s(bos);
            return bo;
        } else {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.getSorts().add("code=asc");
            List<Customer> customers = customerSer.findByCis(customerDTO);
            if (null != customers && customers.size() > 0) {
                int num = 1;
                FilesDataBO bo = new FilesDataBO();
                bo.setTableName("阶段");
                for (Customer customer : customers) {
                    FilesData entity = new FilesData();
                    entity.setTableIndex(num++);
                    entity.setTableName(customer.getStage() + customer.getCode());
                    entity = super.save(entity);
                    FilesDataBO filesDataBO = BeanTransform.copyProperties(entity, FilesDataBO.class, false);
                    bo.getFilesDataVO1s().add(filesDataBO);
                }
                return bo;
            } else {
                return null;
            }
        }
    }
}