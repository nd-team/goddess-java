package com.bjike.goddess.accommodation.service;

import com.bjike.goddess.accommodation.bo.RentalApplyBO;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalApplyDTO;
import com.bjike.goddess.accommodation.entity.Rental;
import com.bjike.goddess.accommodation.entity.RentalApply;
import com.bjike.goddess.accommodation.entity.RentalPrecept;
import com.bjike.goddess.accommodation.enums.PassStatus;
import com.bjike.goddess.accommodation.excel.RentalApplyExport;
import com.bjike.goddess.accommodation.to.RentalApplyTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 租房申请 业务实现
 *
 * @Author: [xiazhili]
 * @Date: [2017-3-10 10:16]
 * @Description: [租房申请 业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "accommodationSerCache")
@Service
public class RentalApplySerImpl extends ServiceImpl<RentalApply,RentalApplyDTO> implements RentalApplySer{
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RentalSer rentalSer;
    @Override
    public Long count(RentalApplyDTO rentalApplyDTO) throws SerException {
        Long count = super.count(rentalApplyDTO);
        return count;
    }

    @Override
    public RentalApplyBO getOne(String id) throws SerException {
        RentalApply rentalApply = super.findById(id);
        return BeanTransform.copyProperties(rentalApply,RentalPreceptBO.class);
    }
    @Override
    public List<RentalApplyBO> findListRentalApply(RentalApplyDTO rentalApplyDTO) throws SerException {

        rentalApplyDTO.getSorts().add("createTime=desc");
        List<RentalApply> rentalApplies = super.findByCis(rentalApplyDTO,true);
        List<RentalApplyBO> rentalApplyBOS = BeanTransform.copyProperties(rentalApplies,RentalApplyBO.class);
        return rentalApplyBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO insertApply(RentalApplyTO applyTO) throws SerException {
        RentalApply apply = BeanTransform.copyProperties(applyTO,RentalApply.class,true,"projectName");
        apply.setCreateTime(LocalDateTime.now());
        apply.setProjectName(StringUtils.join(applyTO.getProjectName(),","));
        super.save(apply);
        return BeanTransform.copyProperties(apply, RentalApplyBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentalApplyBO editApply(RentalApplyTO applyTO) throws SerException {

        RentalApply rentalApply = super.findById(applyTO.getId());
        BeanTransform.copyProperties(applyTO,rentalApply,true,"projectName");
        rentalApply.setModifyTime(LocalDateTime.now());
        rentalApply.setProjectName(StringUtils.join(applyTO.getProjectName(),","));
        super.update(rentalApply);
        RentalApplyBO bo = BeanTransform.copyProperties(rentalApply,RentalApplyBO.class);
        return bo;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeApply(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public RentalApplyBO manageAudit(RentalApplyTO applyTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        RentalApply apply = super.findById(applyTO.getId());

        apply.setManage(userBO.getUsername());
        apply.setManageOpinion(applyTO.getManageOpinion());
        apply.setManagePass(applyTO.getManagePass());
        if ("是".equals(applyTO.getManagePass())) {
            apply.setManagePass(String.valueOf(PassStatus.MANAGEPASS));
        } else if ("否".equals(applyTO.getManagePass())) {
            apply.setManagePass(String.valueOf(PassStatus.MANAGENOPASS));
        }
        super.update(apply);
        RentalApplyBO bo = BeanTransform.copyProperties(apply, RentalApplyBO.class);
        return bo;
    }
    //@Override
    public void summary() throws SerException {
        List<RentalApply> rentalApplies = super.findAll();
        for(RentalApply r:rentalApplies){
            //for(Rental rental:rentals){
               Rental rental =  new Rental();
                BeanUtils.copyProperties(r,rental);
               rentalSer.save(rental);
//                rental.setArea(r.getArea());//地区
//                rental.setProjectGroup(r.getProjectGroup());//项目组
//                rental.setProjectName(r.getProjectName());//项目名称
//                rental.setLessee(r.getLessee());//租赁人
//                rental.setAddress(r.getAddress());//租房地址
//                rental.setLandlord(r.getLandlord());//房东姓名
//                rental.setContact(r.getContact());//联系方式
//                rental.setPurpose(r.getPurpose());//租房用途
//                rental.setAgency(r.getAgency());//中介费
//                rental.setDeposit(r.getDeposit());//押金
//                rental.setRent(r.getRent());//房租
//                rental.setWater(r.getWater());//水费计价
//                rental.setEnergy(r.getEnergy());//电费计价
//                rental.setNetwork(r.getNetwork());//网络套餐费用
//            }


        }
    }

    @Override
    public byte[] exportExcel(RentalApplyDTO dto) throws SerException{
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("createTime", condi));
        }
        List<RentalApply> list = super.findByCis(dto);
        List<RentalApplyExport> exports = new ArrayList<>();
        list.stream().forEach(str->{
            RentalApplyExport export = BeanTransform.copyProperties(str,RentalApplyExport.class);
            exports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte [] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
    }
    /**
     *自动生成记账凭证
     */
    public String generateCredentials ()throws SerException{
        //TODO: xiazhili 2017-03-10 未做自动生成记账凭证
        return null;
    }

}
