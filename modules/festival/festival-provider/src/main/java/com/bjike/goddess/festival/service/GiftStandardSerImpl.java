package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.GiftStandardBO;
import com.bjike.goddess.festival.dto.GiftStandardDTO;
import com.bjike.goddess.festival.entity.GiftStandard;
import com.bjike.goddess.festival.to.GiftStandardTO;
import com.bjike.goddess.festival.to.GuidePermissionTO;
import com.bjike.goddess.festival.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 节假日礼品标准业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class GiftStandardSerImpl extends ServiceImpl<GiftStandard, GiftStandardDTO> implements GiftStandardSer {


    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
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
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
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
        if (flagSee ) {
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
            case SEE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {
        return super.count( giftStandardDTO );
    }

    @Override
    public GiftStandardBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        GiftStandard giftStandard = super.findById(id);
        return BeanTransform.copyProperties(giftStandard, GiftStandardBO.class );

    }

    @Override
    public List<GiftStandardBO> listGiftStandard(GiftStandardDTO giftStandardDTO) throws SerException {
        checkPermission();
        giftStandardDTO.getSorts().add("createTime=desc");
        List<GiftStandard> list = super.findByCis(giftStandardDTO);

        return BeanTransform.copyProperties(list, GiftStandardBO.class );
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public GiftStandardBO addGiftStandard(GiftStandardTO giftStandardTO) throws SerException {
        checkPermission();
        GiftStandard giftStandard = BeanTransform.copyProperties(giftStandardTO,GiftStandard.class,true);
        giftStandard.setCreateTime(LocalDateTime.now());
        super.save( giftStandard );
        return BeanTransform.copyProperties(giftStandard, GiftStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public GiftStandardBO editGiftStandard(GiftStandardTO giftStandardTO) throws SerException {
        checkPermission();
        GiftStandard giftStandard = BeanTransform.copyProperties(giftStandardTO,GiftStandard.class,true);
        GiftStandard temp = super.findById( giftStandardTO.getId() );

        temp.setName( giftStandard.getName() );
        temp.setDescribeDetail( giftStandard.getDescribeDetail() );
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties(giftStandard, GiftStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteGiftStandard(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<String> getGiftByFestivalName() throws SerException {
        String[] fields = new String[]{"name"};
        List<GiftStandardBO> giftStandardBOList = super.findBySql(
                "select name  from festival_giftstandard where name  order by createTime desc ", GiftStandardBO.class, fields);

        List<String> list = giftStandardBOList.stream().map(GiftStandardBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return list;
    }
}