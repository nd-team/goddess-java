package com.bjike.goddess.voucher.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.financeinit.api.FirstSubjectAPI;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.entity.Category;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import com.bjike.goddess.voucher.entity.VoucherTotal;
import com.bjike.goddess.voucher.enums.AuditStatus;
import com.bjike.goddess.voucher.enums.CheckStatus;
import com.bjike.goddess.voucher.enums.TransferStatus;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 记账凭证生成业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "voucherSerCache")
@Service
public class VoucherGenerateSerImpl extends ServiceImpl<VoucherGenerate, VoucherGenerateDTO> implements VoucherGenerateSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private VoucherTotalSer voucherTotalSer;
    @Autowired
    private FirstSubjectAPI firstSubjectAPI;
    @Autowired
    private CategoryAPI categoryAPI;

    @Override
    public VoucherGenerateBO getById(String id) throws SerException {
        VoucherGenerate vg = super.findById(id);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);

    }

    @Override
    public Long countVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));

        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);

        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    //获取凭证字号
    public Double generateVoucherNum(VoucherGenerate voucherGenerate) throws SerException {
        Double num = 1d;
        //凭证日期
        LocalDate voucherDate = voucherGenerate.getVoucherDate();
        //凭证字
        String voucherWord = voucherGenerate.getVoucherWord();

        //这个日期这个月的这个凭证字的第几号
        LocalDate start = voucherDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = voucherDate.with(TemporalAdjusters.lastDayOfMonth());

        String[] field = new String[]{"voucherNum"};
        String sql = "select max(voucherNum) as voucherNum ,1 from voucher_vouchergenerate " +
                " where voucherWord = '" + voucherWord + "' and voucherDate between '" + start + "' and '" + end + "' ";
        List<VoucherGenerate> list = super.findBySql(sql, VoucherGenerate.class, field);
        if (list != null && list.size() > 0) {
            num = list.get(0).getVoucherNum() + 1;
        }
        return num;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<VoucherGenerateBO> addVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getFirstSubjects() == null || voucherGenerateTO.getFirstSubjects().size() <= 0) {
            throw new SerException("一级科目不能为空");
        }
        List<String> first = voucherGenerateTO.getFirstSubjects();
        List<String> second = voucherGenerateTO.getSecondSubjects();
        List<String> third = voucherGenerateTO.getThirdSubjects();
        List<Double> borrow = voucherGenerateTO.getBorrowMoneys();
        List<Double> loan = voucherGenerateTO.getLoanMoneys();
        List<VoucherGenerate> list = new ArrayList<>();
        VoucherGenerate voucherGenerate = BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerate.class, true);
        //生成字号
        Double num = generateVoucherNum(voucherGenerate);

        Double borrowSum = borrow.stream().mapToDouble(Double::shortValue).sum();
        Double loanSum = loan.stream().mapToDouble(Double::shortValue).sum();
        if (borrowSum != loanSum) {
            throw new SerException("借贷方金额不相等，不能添加");
        }
        Double totalMoney = borrowSum;
        VoucherTotal vt = new VoucherTotal();
        vt.setMoney(totalMoney);
        vt.setCreateTime(LocalDateTime.now());
        vt.setModifyTime(LocalDateTime.now());
        voucherTotalSer.save(vt);
        //处理多个一级科目
        for (int i = 0; i < voucherGenerateTO.getFirstSubjects().size(); i++) {

            VoucherGenerate temp = new VoucherGenerate();
            BeanUtils.copyProperties(voucherGenerate, temp);
            temp.setCreateTime(LocalDateTime.now());
            temp.setFirstSubject(first.get(i));
            temp.setSecondSubject(second.get(i));
            temp.setThirdSubject(third.get(i));
            temp.setBorrowMoney(borrow.get(i));
            temp.setLoanMoney(loan.get(i));
            temp.setTicketer(userAPI.currentUser().getUsername());
            temp.setCheckStatus(CheckStatus.NONE);
            temp.setTransferStatus(TransferStatus.NONE);
            temp.setAuditStatus(AuditStatus.NONE);
            temp.setTotalId(vt.getId());
            temp.setVoucherNum(num);

            list.add(temp);
        }
        super.save(list);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO editVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getFirstSubjects() == null || voucherGenerateTO.getFirstSubjects().size() <= 0) {
            throw new SerException("一级科目不能为空");
        }
        Double borrowSum = voucherGenerateTO.getBorrowMoneys().stream().mapToDouble(Double::shortValue).sum();
        Double loanSum = voucherGenerateTO.getLoanMoneys().stream().mapToDouble(Double::shortValue).sum();
        if (borrowSum != loanSum) {
            throw new SerException("借贷方金额不相等，不能添加");
        }
        Double totalMoney = borrowSum;

        VoucherGenerate voucherGenerate = BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerate.class, true);
        VoucherGenerate temp = super.findById(voucherGenerateTO.getId());

        //修改合计金额
        VoucherTotal vt = voucherTotalSer.findById(temp.getTotalId());
        vt.setMoney(totalMoney);
        vt.setModifyTime(LocalDateTime.now());
        voucherTotalSer.update(vt);


        BeanTransform.copyProperties(voucherGenerate, temp);
        temp.setFirstSubject(voucherGenerateTO.getFirstSubjects().get(0));
        temp.setSecondSubject(voucherGenerateTO.getSecondSubjects().get(0));
        temp.setThirdSubject(voucherGenerateTO.getThirdSubjects().get(0));
        temp.setBorrowMoney(voucherGenerateTO.getBorrowMoneys().get(0));
        temp.setLoanMoney(voucherGenerateTO.getLoanMoneys().get(0));
        temp.setModifyTime(LocalDateTime.now());
        temp.setTicketer(userAPI.currentUser().getUsername());
        temp.setCheckStatus(CheckStatus.NONE);
        temp.setTransferStatus(TransferStatus.NONE);
        temp.setAuditStatus(AuditStatus.NONE);

        super.update(temp);
        return BeanTransform.copyProperties(temp, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteVoucherGenerate(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate voucherGenerate = super.findById(id);
        Double borrow = voucherGenerate.getBorrowMoney();
        Double loan = voucherGenerate.getLoanMoney();

        String totalId = voucherGenerate.getTotalId();
        VoucherTotal voucherTotal =  voucherTotalSer.findById( totalId );
        voucherTotal.setMoney( voucherTotal.getMoney()-borrow-loan );
        voucherTotal.setCreateTime( LocalDateTime.now() );
        voucherTotalSer.update( voucherTotal );
        super.remove(id);

        //删掉合计
        VoucherGenerateDTO vgDTO = new VoucherGenerateDTO();
        vgDTO.getConditions().add(Restrict.eq("totalId",totalId));
        List<VoucherGenerate> list = super.findByCis( vgDTO );
        if( list==null ){
            voucherTotalSer.remove( totalId );
        }
    }


    @Override
    public Long countAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.NONE));

        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);

        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO split(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getFirstSubjects() == null || voucherGenerateTO.getFirstSubjects().size() <= 0) {
            throw new SerException("一级科目不能为空");
        }
        Double borrowSum = voucherGenerateTO.getBorrowMoneys().stream().mapToDouble(Double::shortValue).sum();
        Double loanSum = voucherGenerateTO.getLoanMoneys().stream().mapToDouble(Double::shortValue).sum();

        VoucherGenerate voucherGenerate = BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerate.class, true);
        VoucherGenerate temp = super.findById(voucherGenerateTO.getId());


        //拿到相同合计id的那条数据
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.eq("totalId", temp.getTotalId()));
        dto.getConditions().add(Restrict.ne("id", temp.getId()));
        List<VoucherGenerate> lists = super.findByCis(dto);
        borrowSum = lists.stream().mapToDouble(VoucherGenerate::getBorrowMoney).sum() + borrowSum;
        loanSum = lists.stream().mapToDouble(VoucherGenerate::getLoanMoney).sum() + loanSum;

        if (borrowSum != loanSum) {
            throw new SerException("借贷方金额不相等，不能添加");
        }

        List<VoucherGenerate> addlist = new ArrayList<>();
        for (int i = 0; i < voucherGenerateTO.getFirstSubjects().size(); i++) {
            VoucherGenerate temp1 = new VoucherGenerate();
            if (i == 0) {
                BeanTransform.copyProperties(voucherGenerate, temp);
                temp.setFirstSubject(voucherGenerateTO.getFirstSubjects().get(i));
                temp.setSecondSubject(voucherGenerateTO.getSecondSubjects().get(i));
                temp.setThirdSubject(voucherGenerateTO.getThirdSubjects().get(i));
                temp.setBorrowMoney(voucherGenerateTO.getBorrowMoneys().get(i));
                temp.setLoanMoney(voucherGenerateTO.getLoanMoneys().get(i));
                temp.setModifyTime(LocalDateTime.now());
                temp.setTicketer(userAPI.currentUser().getUsername());
                temp.setCheckStatus(CheckStatus.NONE);
                temp.setTransferStatus(TransferStatus.NONE);
                temp.setAuditStatus(AuditStatus.NONE);
                super.update(temp);
            } else {
                temp1 = temp;
                temp1.setFirstSubject(voucherGenerateTO.getFirstSubjects().get(i));
                temp1.setSecondSubject(voucherGenerateTO.getSecondSubjects().get(i));
                temp1.setThirdSubject(voucherGenerateTO.getThirdSubjects().get(i));
                temp1.setBorrowMoney(voucherGenerateTO.getBorrowMoneys().get(i));
                temp1.setLoanMoney(voucherGenerateTO.getLoanMoneys().get(i));
                temp1.setCreateTime(LocalDateTime.now());
                temp1.setModifyTime(LocalDateTime.now());
                temp1.setTicketer(userAPI.currentUser().getUsername());
                temp1.setCheckStatus(CheckStatus.NONE);
                temp1.setTransferStatus(TransferStatus.NONE);
                temp1.setAuditStatus(AuditStatus.NONE);
                addlist.add(temp1);
            }
        }

        if (addlist != null && addlist.size() > 0) {
            super.save(addlist);
        }
        return BeanTransform.copyProperties(temp, VoucherGenerateBO.class);
    }

    @Override
    public VoucherGenerateBO audit(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate vg = super.findById(id);
        vg.setAuditStatus(AuditStatus.CHECK);
        vg.setModifyTime(LocalDateTime.now());
        super.update(vg);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
    }

    @Override
    public Long countAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("auditStatus", AuditStatus.CHECK));
        voucherGenerateDTO.getSorts().add("createTime=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO posting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
            throw new SerException("id数组不能为空,至少要有一个");
        }
        StringBuffer sb = new StringBuffer("");
        for (String str : voucherGenerateTO.getIds()) {
            sb.append("'" + str.trim() + "',");
        }
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.in("id", StringUtils.substringBeforeLast(sb.toString(), ",")));
        List<VoucherGenerate> vgList = super.findByCis(dto);
        List<VoucherGenerate> list = new ArrayList<>();
        vgList.stream().forEach(obj -> {
            obj.setTransferStatus(TransferStatus.CHECK);
            obj.setModifyTime(LocalDateTime.now());
            list.add(obj);
        });
        super.update(list);
        return BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO antiAudit(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate vg = super.findById(id);
        vg.setAuditStatus(AuditStatus.CHECK);
        vg.setTransferStatus(TransferStatus.NONE);
        vg.setModifyTime(LocalDateTime.now());
        super.update(vg);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
    }


    @Override
    public List<VoucherGenerateBO> collectSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                     && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by firstSubject " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'" )
                    .append(" and secondSubject = '" + second + "' and auditStatus = 1 " );
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'" )
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "' and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> collectArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area) ) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by area " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> collectGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(group) ) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by projectGroup " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "' and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> collectPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(projectName) ) {
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by projectName " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "' and auditStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public Long countChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("transferStatus", TransferStatus.CHECK));
        voucherGenerateDTO.getSorts().add("createTime=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO antiPosting(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        VoucherGenerate vg = super.findById(id);
        vg.setTransferStatus(TransferStatus.NONE);
        vg.setModifyTime(LocalDateTime.now());
        super.update(vg);
        return BeanTransform.copyProperties(vg, VoucherGenerateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public VoucherGenerateBO checkAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        if (voucherGenerateTO.getIds() == null || voucherGenerateTO.getIds().length <= 0) {
            throw new SerException("id数组不能为空,至少要有一个");
        }
        StringBuffer sb = new StringBuffer("");
        for (String str : voucherGenerateTO.getIds()) {
            sb.append("'" + str.trim() + "',");
        }
        VoucherGenerateDTO dto = new VoucherGenerateDTO();
        dto.getConditions().add(Restrict.in("id", StringUtils.substringBeforeLast(sb.toString(), ",")));
        List<VoucherGenerate> vgList = super.findByCis(dto);
        List<VoucherGenerate> list = new ArrayList<>();
        vgList.stream().forEach(obj -> {
            obj.setCheckStatus(CheckStatus.CHECK);
            obj.setModifyTime(LocalDateTime.now());
            list.add(obj);
        });
        super.update(list);
        return BeanTransform.copyProperties(voucherGenerateTO, VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctTransSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by firstSubject " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'" )
                    .append(" and secondSubject = '" + second + "' and transferStatus = 1 " );
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'" )
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "' and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctTransArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area) ) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by area " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }


    @Override
    public List<VoucherGenerateBO> ctTransGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(group) ) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by projectGroup " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "' and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctTransPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(projectName) ) {
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by projectName " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "' and transferStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public Long countCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getConditions().add(Restrict.eq("checkStatus", CheckStatus.CHECK));
        voucherGenerateDTO.getSorts().add("voucherDate=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctCkSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String first = voucherGenerateDTO.getFirstSubject();
        String second = voucherGenerateDTO.getSecondSubject();
        String third = voucherGenerateDTO.getThirdSubject();

        String[] field = new String[]{"firstSubject", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(一级科目/借方金额/贷方金额)
        if (StringUtils.isBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            sql.append(" select firstSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by firstSubject " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isBlank(second) && StringUtils.isBlank(third)) {
            //若有选一级，没选二级、三级科目，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "' and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isBlank(third)) {
            //若有选二级，则一级必选，三级科目可选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'" )
                    .append(" and secondSubject = '" + second + "' and checkStatus = 1 " );
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(first) && StringUtils.isNotBlank(second) && StringUtils.isNotBlank(third)) {
            //若有选三级，则一级必选，二级科目必选，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where firstSubject = '" + first + "'" )
                    .append(" and secondSubject = '" + second + "'")
                    .append(" and thirdSubject = '" + third + "' and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctCkArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String area = voucherGenerateDTO.getArea();

        String[] field = new String[]{"area", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(area) ) {
            sql.append(" select area , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by area " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(area)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where area = '" + area + "' and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }
    @Override
    public List<VoucherGenerateBO> ctCkGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String group = voucherGenerateDTO.getProjectGroup();

        String[] field = new String[]{"projectGroup", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(group) ) {
            sql.append(" select projectGroup , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by projectGroup " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(group)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where projectGroup = '" + group + "' and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public List<VoucherGenerateBO> ctCkPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        String projectName = voucherGenerateDTO.getProjectName();

        String[] field = new String[]{"projectName", "borrowMoney", "loanMoney"};
        StringBuffer sql = new StringBuffer("");
        List<VoucherGenerate> list = new ArrayList<>();
        //若没有选一级、二级、三级科目，表头是：(地区/借方金额/贷方金额)
        if (StringUtils.isBlank(projectName) ) {
            sql.append(" select projectName , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney " )
                    .append( " from voucher_vouchergenerate where 1=1 and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            sql.append( " group by projectName " );
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        } else if (StringUtils.isNotBlank(projectName)) {
            //若有选地区，表头是：(一级科目/二级科目/三级科目/借方金额/贷方金额/凭证日期/地区/项目组/项目名称)
            field = new String[]{"firstSubject", "secondSubject", "thirdSubject", "borrowMoney",
                    "loanMoney", "voucherDate", "area", "projectGroup", "projectName"};
            sql.append(" select firstSubject,secondSubject, thirdSubject , sum(borrowMoney) as borrowMoney , sum(loanMoney) as loanMoney ")
                    .append(" , voucherDate , area , projectGroup , projectName ")
                    .append(" from voucher_vouchergenerate where projectName = '" + projectName + "' and checkStatus = 1 ");
            if( StringUtils.isNotBlank(voucherGenerateDTO.getStartTime())
                    && StringUtils.isNotBlank( voucherGenerateDTO.getEndTime() )){
                sql.append( " and voucherDate between '"+voucherGenerateDTO.getStartTime()+"' and '"+voucherGenerateDTO.getEndTime()+"' ");
            }
            list = super.findBySql(sql.toString(), VoucherGenerate.class, field);
        }else{
            throw  new SerException("请正确填写数据");
        }
        return BeanTransform.copyProperties(list,VoucherGenerateBO.class);
    }

    @Override
    public Long countRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        Long count = super.count(voucherGenerateDTO);
        return count;
    }

    @Override
    public List<VoucherGenerateBO> listRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        voucherGenerateDTO.getSorts().add("createTime=desc");
        List<VoucherGenerate> list = super.findByCis(voucherGenerateDTO, true);
        return BeanTransform.copyProperties(list, VoucherGenerateBO.class);
    }


    @Override
    public List<String> listFirstSubject() throws SerException {
        List<String> list = firstSubjectAPI.listAllFirst();
        return list;
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        CategoryDTO cdto = new CategoryDTO();
        cdto.setFirstSubjectName( firstSub );
        List<String> list = categoryAPI.getSecondSubject(cdto);
        return list;
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        CategoryDTO cdto = new CategoryDTO();
        cdto.setFirstSubjectName( firstSub );
        cdto.setSecondSubject( secondSub );
        List<String> list = categoryAPI.getSecondSubject(cdto);
        return list;
    }

    @Override
    public List<String> listArea() throws SerException {
        String [] field = new String[]{"area"};
        String sql = " select area ,1 from voucher_vouchergenerate group by area ";
        List<VoucherGenerate> list = super.findBySql( sql , VoucherGenerate.class , field );
        List<String> area = list.stream().map(VoucherGenerate::getArea).collect(Collectors.toList());
        return area;
    }

    @Override
    public List<String> listProject() throws SerException {
        String [] field = new String[]{"projectName"};
        String sql = " select projectName ,1 from voucher_vouchergenerate group by projectName ";
        List<VoucherGenerate> list = super.findBySql( sql , VoucherGenerate.class , field );
        List<String> area = list.stream().map(VoucherGenerate::getProjectName).collect(Collectors.toList());
        return area;
    }

    @Override
    public List<String> listGroup() throws SerException {
        String [] field = new String[]{"projectGroup"};
        String sql = " select projectGroup ,1 from voucher_vouchergenerate group by projectGroup ";
        List<VoucherGenerate> list = super.findBySql( sql , VoucherGenerate.class , field );
        List<String> area = list.stream().map(VoucherGenerate::getProjectGroup).collect(Collectors.toList());
        return area;
    }
}