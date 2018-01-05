package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.IndexNameSetBO;
import com.bjike.goddess.balancecard.dto.IndexNameSetDTO;
import com.bjike.goddess.balancecard.entity.BalancecardPermission;
import com.bjike.goddess.balancecard.entity.IndexNameSet;
import com.bjike.goddess.balancecard.enums.GuideAddrStatus;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.IndexNameSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.balancecard.dto.IndexNameSetDTO;
import com.bjike.goddess.balancecard.entity.IndexNameSet;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指标名称设置业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:02 ]
 * @Description: [ 指标名称设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class IndexNameSetSerImpl extends ServiceImpl<IndexNameSet, IndexNameSetDTO> implements IndexNameSetSer {


    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BalancecardPermissionSer cusPermissionSer;

    /**
     * 核对是否为管理层权限（部门级别）
     */
    private Boolean checkManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                RpcTransmit.transmitUserToken(userToken);
                return false;
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        return true;
    }

    /**
     * 核对是否为执行层权限（部门级别）
     */
    private Boolean checkExecutiveIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                RpcTransmit.transmitUserToken(userToken);
                return false;
            }
        }
        RpcTransmit.transmitUserToken(userToken);
        return true;
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
            return true;
        }
        return flag;
    }

    /**
     * 查看是否为执行层（岗位级别）
     */
    private Boolean gudieSeeIdentity2() throws SerException {
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

    //功能导航权限
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
                flag = gudieSeeIdentity2();
                break;
            case EDIT:
                flag = gudieSeeIdentity2();
                break;
            case AUDIT:
                flag = gudieSeeIdentity2();
                break;
            case DELETE:
                flag = gudieSeeIdentity2();
                break;
            case CONGEL:
                flag = gudieSeeIdentity2();
                break;
            case THAW:
                flag = gudieSeeIdentity2();
                break;
            case COLLECT:
                flag = gudieSeeIdentity2();
                break;
            case IMPORT:
                flag = gudieSeeIdentity2();
                break;
            case EXPORT:
                flag = gudieSeeIdentity2();
                break;
            case UPLOAD:
                flag = gudieSeeIdentity2();
                break;
            case DOWNLOAD:
                flag = gudieSeeIdentity2();
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = gudieSeeIdentity2();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long countIndexNameSet(IndexNameSetDTO indexNameSetDTO) throws SerException {
        if (StringUtils.isNotBlank(indexNameSetDTO.getTypeName())) {
            indexNameSetDTO.getConditions().add(Restrict.like("typeName", indexNameSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(indexNameSetDTO.getPerson())) {
            indexNameSetDTO.getConditions().add(Restrict.like("person", indexNameSetDTO.getPerson()));
        }
        Long count = super.count(indexNameSetDTO);
        return count;
    }

    @Override
    public IndexNameSetBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        IndexNameSet indexNameSet = super.findById(id);
        return BeanTransform.copyProperties(indexNameSet,IndexNameSetBO.class);
    }

    @Override
    public List<IndexNameSetBO> listIndexNameSet(IndexNameSetDTO indexNameSetDTO) throws SerException {
        if (StringUtils.isNotBlank(indexNameSetDTO.getTypeName())) {
            indexNameSetDTO.getConditions().add(Restrict.like("typeName", indexNameSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(indexNameSetDTO.getPerson())) {
            indexNameSetDTO.getConditions().add(Restrict.like("person", indexNameSetDTO.getPerson()));
        }
        List<IndexNameSet> list = super.findByCis(indexNameSetDTO, true);
        List<IndexNameSetBO> boList = BeanTransform.copyProperties(list, IndexNameSetBO.class);
        return boList;
    }

    @Override
    public IndexNameSetBO addIndexNameSet(IndexNameSetTO indexNameSetTO) throws SerException {
        if (StringUtils.isBlank(indexNameSetTO.getTypeName())) {
            throw new SerException("指标名称不能为空");
        }
        IndexNameSet temp = BeanTransform.copyProperties( indexNameSetTO , IndexNameSet.class,true);
        temp.setPerson( userAPI.currentUser().getUsername() );
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save( temp );
        return BeanTransform.copyProperties( temp , IndexNameSetBO.class);
    }

    @Override
    public IndexNameSetBO editIndexNameSet(IndexNameSetTO indexNameSetTO) throws SerException {
        if( StringUtils.isBlank(indexNameSetTO.getId())){
            throw new SerException("失败，id不能为空");
        }if( StringUtils.isBlank(indexNameSetTO.getTypeName())){
            throw new SerException("失败，指标名称不能为空");
        }
        IndexNameSet temp = super.findById( indexNameSetTO.getId() );
        temp.setTypeName( indexNameSetTO.getTypeName());
        temp.setDescribtion( indexNameSetTO.getDescribtion() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties( temp , IndexNameSetBO.class);
    }

    @Override
    public void deleteIndexNameSet(String id) throws SerException {
        if( StringUtils.isBlank( id )){
            throw new SerException("失败，id不能为空");
        }
        super.remove( id );
    }


    @Override
    public List<String> listName() throws SerException {
        List<String> list = new ArrayList<>();
        String[] field = new String[]{"typeName"};
        String sql = "select typeName from balancecard_indexnameset group by typeName order by typeName desc ";
        List<IndexNameSetBO> listBO = super.findBySql(sql , IndexNameSetBO.class,field);
        list = listBO.stream().filter(str->StringUtils.isNotBlank(str.getTypeName())).map(IndexNameSetBO::getTypeName).collect(Collectors.toList());
        return list;
    }


}