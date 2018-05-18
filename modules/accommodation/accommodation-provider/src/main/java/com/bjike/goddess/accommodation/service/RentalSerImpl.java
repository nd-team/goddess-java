package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.CollectBO;
import com.bjike.goddess.accommodation.bo.RentalBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.dto.RentalDTO;
import com.bjike.goddess.accommodation.entity.CusPermission;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.enums.GuideAddrStatus;
import com.bjike.goddess.accommodation.excel.RentalExport;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.accommodation.to.RentalTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import javafx.scene.shape.SVGPath;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 租房信息 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房信息 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class RentalSerImpl extends ServiceImpl<Rental, RentalDTO> implements RentalSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
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
            flag = cusPermissionSer.getCusPermission("1",null);
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
            flag = cusPermissionSer.busCusPermission("2",null);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
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
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
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
@Autowired
private RentalApplySer rentalApplySer;
    @Override
    public Long count(RentalDTO rentalDTO) throws SerException {
        Long count = super.count(rentalDTO);
        return count;
    }

    @Override
    public RentalBO getOne(String id) throws SerException {
        Rental rental = super.findById(id);
        return BeanTransform.copyProperties(rental, RentalBO.class);
    }

    @Override
    public List<RentalBO> findListRental(RentalDTO rentalDTO) throws SerException {
        checkSeeIdentity();
        RentalApplyDTO applyDTO = new RentalApplyDTO();
        List<RentalApply> rentalApplies = rentalApplySer.findByCis(applyDTO);

        Rental rental = new Rental();
        for(RentalApply rentalApply:rentalApplies){
            if ("通过".equals(rentalApply.getManagePass()) && "通过".equals(rentalApply.getCommerceRemark())
                    && "通过".equals(rentalApply.getComprehensiveRemark()) && "通过".equals(rentalApply.getOperatingRemark())&&(rentalApply.getStatus())==null) {
                rental.setRentNum(rentalApply.getRentNum());//租房编号
                rental.setArea(rentalApply.getArea());//地区
                rental.setProjectGroup(rentalApply.getProjectGroup());//项目组
                rental.setProjectName(rentalApply.getProjectName());//项目名称
                rental.setLessee(rentalApply.getLessee());//租赁人
                rental.setAddress(rentalApply.getAddress());//租房地址
                rental.setLandlord(rentalApply.getLandlord());//房东姓名
                rental.setContact(rentalApply.getContact());//联系方式
                rental.setPurpose(rentalApply.getPurpose());//租房用途
                rental.setAgency(rentalApply.getAgency());//中介费
                rental.setDeposit(rentalApply.getDeposit());//押金
                rental.setRent(rentalApply.getRent());//房租
                rental.setManagementFee(rentalApply.getRentFee());//管理费
                rental.setHealthFee(rentalApply.getSanitation());//卫生费
                rental.setRentPay(rentalApply.getRentPay());//房租缴费方
                rental.setWaterMoney(rentalApply.getWater());//水费计价金额(元/吨)
                rental.setWaterPay(rentalApply.getWaterPay());//水费缴费方
                rental.setEnergyPay(rentalApply.getEnergyPay());//电费缴费方
                rental.setEnergyMoney(rentalApply.getEnergy()); //电费计价额
                rental.setNetworkMoney(rentalApply.getNetwork());// 网络套餐费用缴纳金额
                rental.setNetworkPay(rentalApply.getNetworkPay());  //网络套餐费用缴费方
                rental.setCreateTime(LocalDateTime.now());
                rental.setModifyTime(LocalDateTime.now());
                rentalApply.setStatus(true);
                rentalApplySer.update(rentalApply);
                super.save(rental);
            }
        }
        List<Rental> rentals = super.findByCis(rentalDTO, true);
        List<RentalBO> rentalBOS = BeanTransform.copyProperties(rentals, RentalBO.class);
        return rentalBOS;
    }

//    @Transactional(rollbackFor = SerException.class)
//    @Override
//    public RentalBO insertRental(RentalTO rentalTO) throws SerException {
//        Rental rental = BeanTransform.copyProperties(rentalTO, Rental.class, true,"projectName");
//        rental.setCreateTime(LocalDateTime.now());
//        rental.setProjectName(StringUtils.join(rentalTO.getProjectName(),","));
//        super.save(rental);
//        return BeanTransform.copyProperties(rental, RentalBO.class);
//    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalBO editRental(RentalTO rentalTO) throws SerException {
        checkAddIdentity();
        Rental rental = super.findById(rentalTO.getId());
        LocalDateTime createTime = rental.getCreateTime();
        rental = BeanTransform.copyProperties(rentalTO, Rental.class, true,"projectName");
        rental.setCreateTime(createTime);
        rental.setModifyTime(LocalDateTime.now());
        rental.setProjectName(StringUtils.join(rentalTO.getProjectName(),","));
        super.update(rental);
        return BeanTransform.copyProperties(rental, RentalBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRental(String id) throws SerException {
        checkAddIdentity();
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public byte[] exportExcel(RentalDTO dto) throws SerException {
        if (null != dto.getArea()) {
            dto.getConditions().add(Restrict.in("area", dto.getArea()));
        }
        List<Rental> list = super.findByCis(dto);
        List<RentalExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            RentalExport export = BeanTransform.copyProperties(str, RentalExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }
    @Override
    public List<CollectBO> collect(String [] areas) throws SerException {
        if(areas == null && areas.length <= 0){
            throw new SerException("地区不能为空");
        }
        String[] areasTemp = new String[areas.length];
        for(int i = 0 ;i<areas.length;i++){
            areasTemp[i] = "'"+areas[i]+"'";
        }
        String areasStr = StringUtils.join(areasTemp,",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT area ,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(rent) AS rent,sum(agency) AS agency,sum(deposit) AS deposit,sum(managementFee) AS managementFee, ");
        sb.append(" sum(healthFee) AS healthFee,sum(water) as water,sum(energy) as energy ,sum(network) AS network,sum(gas) AS gas, ");
        sb.append(" (sum(rent)+sum(agency)+sum(deposit)+sum(managementFee)+sum(healthFee)+sum(network)+sum(gas)) AS remark ");
        sb.append(" FROM accommodation_rental a WHERE area IN (%s) GROUP BY area,projectGroup,projectName,address ORDER BY area)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS area,NULL AS projectGroup ,NULL AS projectName,NULL AS address, ");
        sb.append(" sum(rent) AS rent ,sum(agency) AS agency,sum(deposit) AS deposit,sum(managementFee) AS managementFee, ");
        sb.append(" sum(water) as water,sum(energy) as energy ,sum(healthFee) AS healthFee,sum(network) AS network,sum(gas) AS gas, ");
        sb.append(" (sum(rent)+sum(agency)+sum(deposit)+sum(managementFee)+sum(healthFee)+sum(network)+sum(gas)) AS remark ");
        sb.append(" from ");
        sb.append(" (SELECT area ,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(rent) AS rent,sum(agency) AS agency,sum(deposit) AS deposit,sum(managementFee) AS managementFee, ");
        sb.append(" sum(healthFee) AS healthFee,sum(water) as water,sum(energy) as energy ,sum(network) AS network,sum(gas) AS gas, ");
        sb.append(" (sum(rent)+sum(agency)+sum(deposit)+sum(managementFee)+sum(healthFee)+sum(network)+sum(gas)) AS remark ");
        sb.append(" FROM accommodation_rental a WHERE area IN (%s) GROUP BY area,projectGroup,projectName,address ORDER BY area)A ");
        String sql = sb.toString();
        sql = String.format(sql, areasStr,areasStr);
        String[] fields = new String[]{"area","projectGroup","projectName","address","rent","agency","deposit","managementFee",
                "healthFee", "water","energy","network","gas","remark"};
        List<CollectBO> collectBOS = super.findBySql(sql,CollectBO.class,fields);
        return collectBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<RentalBO> rentalBOS = super.findBySql("select distinct area from accommodation_rental group by area order by area asc ", RentalBO.class, fields);

        List<String> areasList = rentalBOS.stream().map(RentalBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areasList;
    }

    @Override
    /**
     * chenjunhao
     * 获取所有租房地址
     */
    public Set<String> allAddress() throws SerException {
        List<Rental> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (Rental rental : list) {
            set.add(rental.getAddress());
        }
        return set;
    }

}
