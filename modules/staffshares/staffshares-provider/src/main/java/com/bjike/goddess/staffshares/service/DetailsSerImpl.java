package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.bonus.api.DisciplineRecordAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.DetailsBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.DetailsDTO;
import com.bjike.goddess.staffshares.entity.Details;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.enums.Status1;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 交易详情业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class DetailsSerImpl extends ServiceImpl<Details, DetailsDTO> implements DetailsSer {

    @Autowired
    private SchemeAPI schemeAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private DisciplineRecordAPI disciplineRecordAPI;

    @Autowired
    private PurchaseSer purchaseSer;


    @Override
    public List<DetailsBO> listDetail(DetailsDTO dto) throws SerException {
        if (StringUtils.isBlank(dto.getId())) {
            throw new SerException("id不能为空");
        }
        List<DetailsBO> detailsBOs = new ArrayList<>(0);
        //根据ｉｄ查询交易详情
        SchemeIssueBO schemeIssueBO = schemeAPI.getOne(dto.getId());
        if (schemeIssueBO != null) {
            DetailsDTO detailsDTO = new DetailsDTO();
            List<Details> detailses = super.findByCis(detailsDTO);
            if (CollectionUtils.isEmpty(detailses)) {
                //插入第一条数据
                Details details = new Details();
                details.setCode(schemeIssueBO.getCode());
                details.setName(schemeIssueBO.getName());
                details.setPublisher(schemeIssueBO.getPublisher());
                details.setNumber(schemeIssueBO.getNumber());
                details.setPrice(schemeIssueBO.getPrice());
                details.setTime(LocalDate.parse(schemeIssueBO.getTime()));
                details.setSharesNum(schemeIssueBO.getSharesNum());
                detailsBOs.add(BeanTransform.copyProperties(details, DetailsBO.class, false));
            } else {
                //根据ｉｄ查询该条记录的详情
                detailsDTO.getConditions().add(Restrict.eq("code", schemeIssueBO.getCode()));
                List<Details> detailses1 = super.findByCis(detailsDTO);
                BeanTransform.copyProperties(detailses1, detailsBOs, false);
                return detailsBOs;
            }
        }
        return detailsBOs;
    }

    @Override
    public DetailsBO getDetailById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        Details entity = super.findById(id);
        DetailsBO detailsBO = BeanTransform.copyProperties(entity, DetailsBO.class, false);
        return detailsBO;
    }

    @Override
    public Long getTotal(DetailsDTO detailsDTO) throws SerException {
        searchCondition(detailsDTO);
        Long count = super.count(detailsDTO);
        return count;
    }

    @Override
    public void buy(PurchaseTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("交易详情id不能为空");
        }
        //根据ｉｄ查询交易详情
        Details entity = super.findById(to.getId());
        if (null != entity) {
            Purchase purchase = new Purchase();
            purchase.setStatus(Status1.SUBMIT);
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            purchase.setName(userBO.getUsername());
            purchase.setTime(LocalDateTime.now());

            purchase.setCode(entity.getCode());
            purchase.setIssueName(entity.getName());
            purchase.setFacevalue(entity.getFacevalue());
            purchase.setPrice(entity.getPrice());
            //根据用户名得到用户数据
            List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.getPositionDetail(userBO.getUsername());
            if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
                PositionDetailBO positionDetailBO = positionDetailBOList.get(0);
                purchase.setArea(positionDetailBO.getArea());
                // TODO: 17-8-5 每个持股方案有一个对应的项目
                purchase.setProject("");
                purchase.setDepartment(positionDetailBO.getDepartmentName());
                purchase.setPosition(positionDetailBO.getPosition());

                List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getByEmpNumber(positionDetailBO.getSerialNumber());
                if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
                    //获取第一条数据
                    EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
                    String time = entryBasicInfoBO.getEntryTime();
                    int months = getMonthSpace(time, LocalDate.now().toString());
                    purchase.setMonths(months);
                } else {
                    purchase.setMonths(0);
                }
                purchase.setSellName(entity.getPublisher());
                purchase.setPurchaseNum(to.getPurchaseNum());
                purchase.setMoney(to.getPurchaseNum() * entity.getPrice());
                purchase.setPenalty(disciplineRecordAPI.getPushNum(userBO.getUsername()));
                purchase.setReward(disciplineRecordAPI.getRewardNum(userBO.getUsername()));
                // TODO: 17-8-7
                //各项晋升的次数
                purchase.setPromotion(0);
                purchase.setRemark(to.getRemark());
                purchaseSer.save(purchase);
            }
        }
    }

    @Override
    public void recovery(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id 不能为空");
        }
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
//        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
//            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
////                positionDetailBO.getDepartmentId().equals()
//            }
//        }

        Details entity = super.findById(id);
        entity.setSharesNum(0);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    //计算两个日期之间的月数
    public int getMonthSpace(String date1, String date2) throws SerException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));

            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

            return result == 0 ? 1 : Math.abs(result);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }


    public void searchCondition(DetailsDTO detailsDTO) throws SerException {
        /**
         * 方案代码
         */
        if (StringUtils.isNotBlank(detailsDTO.getCode())) {
            detailsDTO.getConditions().add(Restrict.eq("code", detailsDTO.getCode()));
        }/**
         * 方案名称
         */
        if (StringUtils.isNotBlank(detailsDTO.getName())) {
            detailsDTO.getConditions().add(Restrict.eq("name", detailsDTO.getName()));
        }/**
         * 出售/发行人
         */
        if (StringUtils.isNotBlank(detailsDTO.getPublisher())) {
            detailsDTO.getConditions().add(Restrict.like("publisher", detailsDTO.getPublisher()));
        }
        /**
         * 出售/发行数量
         */
        if (0 > detailsDTO.getNumber()) {
            detailsDTO.getConditions().add(Restrict.like("number", detailsDTO.getNumber()));
        }
        /**
         * 出售/发行价格
         */
        if (null != detailsDTO.getPrice()) {
            detailsDTO.getConditions().add(Restrict.like("price", detailsDTO.getPrice()));
        }
        /**
         * 出售/发行时间
         */
        if (StringUtils.isNotBlank(detailsDTO.getTime())) {
            detailsDTO.getConditions().add(Restrict.like("time", detailsDTO.getTime()));
        }
        /**
         * 剩余出售量
         */
        if (0 > detailsDTO.getSharesNum()) {
            detailsDTO.getConditions().add(Restrict.like("sharesNum", detailsDTO.getSharesNum()));
        }
    }
}