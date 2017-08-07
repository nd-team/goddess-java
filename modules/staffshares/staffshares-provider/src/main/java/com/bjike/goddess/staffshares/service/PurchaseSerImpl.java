package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.enums.Status1;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 干股申购表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class PurchaseSerImpl extends ServiceImpl<Purchase, PurchaseDTO> implements PurchaseSer {
    @Autowired
    private SchemeAPI schemeAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;

    @Override
    public void buy(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        SchemeIssueBO schemeIssueBO = schemeAPI.getOne(to.getId());

        Purchase entity = new Purchase();
        entity.setStatus(Status1.SUBMIT);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        entity.setName(userBO.getUsername());
        entity.setTime(LocalDateTime.now());
        //根据用户名得到用户数据
        List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.getPositionDetail(userBO.getUsername());
        if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
            PositionDetailBO positionDetailBO = positionDetailBOList.get(0);
            entity.setArea(positionDetailBO.getArea());
            // TODO: 17-8-5 每个持股方案有一个对应的项目，目前该项目从哪里拿不清楚，以后有的话得加上去
            entity.setProject("");

            entity.setDepartment(positionDetailBO.getDepartmentName());
            entity.setPosition(positionDetailBO.getPosition());
            List<EntryBasicInfoBO>  entryBasicInfoBOs = entryBasicInfoAPI.getByEmpNumber(positionDetailBO.getSerialNumber());
            if(null != entryBasicInfoBOs && entryBasicInfoBOs.size() >0){
                //获取第一条数据
                EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
                String time = entryBasicInfoBO.getEntryTime();



            }

            for(EntryBasicInfoBO entryBasicInfoBO : entryBasicInfoBOs){}

        }


        List<EntryBasicInfoBO>  entryBasicInfoVOs = entryBasicInfoAPI.getByEmpNumber();




        if ()

            Scheme scheme = BeanTransform.copyProperties(to, Scheme.class, true);
        BeanUtils.copyProperties(scheme, temp, "id", "createTime");
        String userToken = RpcTransmit.getUserToken();
        String name = userApi.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        temp.setSetters(name);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }
}