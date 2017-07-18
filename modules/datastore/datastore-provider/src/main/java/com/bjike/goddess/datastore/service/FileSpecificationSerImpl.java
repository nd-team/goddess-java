package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.FileSpecificationBO;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.entity.FileSpecification;
import com.bjike.goddess.datastore.enums.GuideAddrStatus;
import com.bjike.goddess.datastore.excel.SonPermissionObject;
import com.bjike.goddess.datastore.to.FileSpecificationTO;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据存储文件规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "datastoreSerCache")
@Service
public class FileSpecificationSerImpl extends ServiceImpl<FileSpecification, FileSpecificationDTO> implements FileSpecificationSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private AccountPwdSpecificationSer accountPwdSpecificationSer;
    @Autowired
    private NumSpecificationSer numSpecificationSer;
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
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("filespecification");
        obj.setDescribesion("数据存储文件规范");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = numSpecificationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("numspecification");
        obj.setDescribesion("数据存储编号规范");
        if (flagSeeAnswer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeWeb = accountPwdSpecificationSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accountpwdspecification");
        obj.setDescribesion("数据存储账号密码规范");
        if (flagSeeWeb) {
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



    @Override
    public Long countFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        fileSpecificationDTO.getSorts().add("createTime=desc");
        Long counts = super.count(fileSpecificationDTO);
        return counts;
    }

    @Override
    public FileSpecificationBO getOne(String id) throws SerException {
        FileSpecification fileSpecification = super.findById(id);
        return BeanTransform.copyProperties(fileSpecification, FileSpecificationBO.class);
    }

    @Override
    public List<FileSpecificationBO> findListFileSpecification(FileSpecificationDTO fileSpecificationDTO) throws SerException {
        checkSeeIdentity();
        fileSpecificationDTO.getSorts().add("createTime=desc");
        List<FileSpecification> fileSpecifications = super.findByCis(fileSpecificationDTO, true);
        List<FileSpecificationBO> fileSpecificationBOS = BeanTransform.copyProperties(fileSpecifications, FileSpecificationBO.class);
        return fileSpecificationBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FileSpecificationBO insertFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        checkAddIdentity();
        FileSpecification fileSpecification = BeanTransform.copyProperties(fileSpecificationTO, FileSpecification.class, true);
        fileSpecification.setCreateTime(LocalDateTime.now());
        super.save(fileSpecification);
        return BeanTransform.copyProperties(fileSpecification, FileSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FileSpecificationBO editFileSpecification(FileSpecificationTO fileSpecificationTO) throws SerException {
        checkAddIdentity();
        FileSpecification fileSpecification = super.findById(fileSpecificationTO.getId());
        BeanTransform.copyProperties(fileSpecificationTO, fileSpecification, true);
        fileSpecification.setModifyTime(LocalDateTime.now());
        super.update(fileSpecification);
        return BeanTransform.copyProperties(fileSpecification, FileSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeFileSpecification(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
}