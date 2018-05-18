package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.IndexTypeSetBO;
import com.bjike.goddess.balancecard.dto.IndexTypeSetDTO;
import com.bjike.goddess.balancecard.entity.IndexTypeSet;
import com.bjike.goddess.balancecard.enums.CusPermissionType;
import com.bjike.goddess.balancecard.enums.GuideAddrStatus;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.IndexTypeSetTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指标类型业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 指标类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "balancecardSerCache")
@Service
public class IndexTypeSetSerImpl extends ServiceImpl<IndexTypeSet, IndexTypeSetDTO> implements IndexTypeSetSer {


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
    public Long countIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        if (StringUtils.isNotBlank(indexTypeSetDTO.getTypeName())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("typeName", indexTypeSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(indexTypeSetDTO.getPerson())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("person", indexTypeSetDTO.getPerson()));
        }
        Long count = super.count(indexTypeSetDTO);
        return count;
    }

    @Override
    public IndexTypeSetBO getOneById(String id) throws SerException {
        IndexTypeSet indexTypeSet = super.findById(id);
        return BeanTransform.copyProperties(indexTypeSet,IndexTypeSetBO.class);
    }

    @Override
    public List<IndexTypeSetBO> listIndexTypeSet(IndexTypeSetDTO indexTypeSetDTO) throws SerException {
        if (StringUtils.isNotBlank(indexTypeSetDTO.getTypeName())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("typeName", indexTypeSetDTO.getTypeName()));
        }
        if (StringUtils.isNotBlank(indexTypeSetDTO.getPerson())) {
            indexTypeSetDTO.getConditions().add(Restrict.like("person", indexTypeSetDTO.getPerson()));
        }
        List<IndexTypeSet> list = super.findByCis(indexTypeSetDTO, true);
        List<IndexTypeSetBO> boList = BeanTransform.copyProperties(list, IndexTypeSetBO.class);
        return boList;
    }

    @Override
    public IndexTypeSetBO addIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        if (StringUtils.isBlank(indexTypeSetTO.getTypeName())) {
            throw new SerException("指标类型不能为空");
        }
        IndexTypeSet temp = BeanTransform.copyProperties( indexTypeSetTO , IndexTypeSet.class,true);
        temp.setPerson( userAPI.currentUser().getUsername() );
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save( temp );
        return BeanTransform.copyProperties( temp , IndexTypeSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public IndexTypeSetBO editIndexTypeSet(IndexTypeSetTO indexTypeSetTO) throws SerException {
        if( StringUtils.isBlank(indexTypeSetTO.getId())){
            throw new SerException("失败，id不能为空");
        }
        if( StringUtils.isBlank(indexTypeSetTO.getTypeName())){
            throw new SerException("失败，指标类型不能为空");
        }
        IndexTypeSet temp = super.findById( indexTypeSetTO.getId() );
        BeanTransform.copyProperties(indexTypeSetTO,temp,true);
        temp.setTypeName( indexTypeSetTO.getTypeName());
        temp.setDescribtion( indexTypeSetTO.getDescribtion() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties( temp , IndexTypeSetBO.class);
    }

    @Override
    public void deleteIndexTypeSet(String id) throws SerException {
        if( StringUtils.isBlank( id )){
            throw new SerException("失败，id不能为空");
        }
        super.remove( id );
    }


    @Override
    public List<String> listName() throws SerException {
        List<String> list = new ArrayList<>();
        String[] field = new String[]{"typeName"};
        String sql = "select typeName from balancecard_indextypeset group by typeName order by typeName desc ";
        List<IndexTypeSetBO> listBO = super.findBySql(sql , IndexTypeSetBO.class,field);
        list = listBO.stream().filter(str->StringUtils.isNotBlank(str.getTypeName())).map(IndexTypeSetBO::getTypeName).collect(Collectors.toList());
        return list;
    }


}