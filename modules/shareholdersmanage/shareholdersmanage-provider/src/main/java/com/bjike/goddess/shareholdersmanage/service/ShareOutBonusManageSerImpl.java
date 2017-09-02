package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.shareholdersmanage.bo.*;
import com.bjike.goddess.shareholdersmanage.dto.FreezeEquityDTO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOutBonusManageDTO;
import com.bjike.goddess.shareholdersmanage.entity.FreezeEquity;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusDetail;
import com.bjike.goddess.shareholdersmanage.entity.ShareOutBonusManage;
import com.bjike.goddess.shareholdersmanage.excel.ShareRosterExport;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOutBonusManageTO;
import com.bjike.goddess.shareholdersmanage.type.Gender;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.shareholdersmanage.type.TypeName;
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

/**
 * 分红管理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class ShareOutBonusManageSerImpl extends ServiceImpl<ShareOutBonusManage, ShareOutBonusManageDTO> implements ShareOutBonusManageSer {
    @Autowired
    private ShareOutBonusDetailSer shareOutBonusDetailSer;
    @Autowired
    private ShareOpenAccountSer shareOpenAccountSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    /**
     * 检查权限
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
            throw new SerException("您不是财务部门人员,没有该操作权限");
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
        if (flagSee) {
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
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case SEEFILE:
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
    public Long countOutBonus(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        Long count = super.count(shareOutBonusManageDTO);
        return count;
    }

    @Override
    public ShareOutBonusManageBO getOne(String id) throws SerException {
        ShareOutBonusManage shareOutBonusManage = super.findById(id);
        return BeanTransform.copyProperties(shareOutBonusManage, ShareOutBonusManageBO.class);
    }

    @Override
    public List<ShareOutBonusManageBO> findList(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        shareOutBonusManageDTO.getSorts().add("createTime=desc");
        List<ShareOutBonusManage> pledgeEquityList = super.findByCis(shareOutBonusManageDTO);
        return BeanTransform.copyProperties(pledgeEquityList, ShareOutBonusManageBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ShareOutBonusManageBO save(ShareOutBonusManageTO shareOutBonusManageTO) throws SerException {
        checkPermission();
        ShareOutBonusManage shareOutBonusManage = BeanTransform.copyProperties(shareOutBonusManageTO,ShareOutBonusManage.class,true);
        Double totalShareOutBonus = shareOutBonusManageTO.getPerShareDividends()*shareOutBonusManageTO.getTotalSentStocks();
        shareOutBonusManage.setCreateTime(LocalDateTime.now());
        shareOutBonusManage.setTotalShareOutBonus(totalShareOutBonus);
        shareOutBonusManage.setTotalIncomeTax(0d);
        super.save(shareOutBonusManage);
        return BeanTransform.copyProperties(shareOutBonusManage,ShareOutBonusManageBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ShareOutBonusManageBO edit(ShareOutBonusManageTO shareOutBonusManageTO) throws SerException {
        checkPermission();
        ShareOutBonusManage share = super.findById(shareOutBonusManageTO.getId());
        LocalDateTime date = share.getCreateTime();
        Double totalIncomeTax = share.getTotalIncomeTax();
        Double totalShareOutBonus = share.getTotalShareOutBonus();

        //判断每股分红和共派股是否被修改
        if (share.getTotalSentStocks()!= shareOutBonusManageTO.getTotalSentStocks() || share.getPerShareDividends() != shareOutBonusManageTO.getPerShareDividends()){
            //重新计算共分红
            totalShareOutBonus = shareOutBonusManageTO.getPerShareDividends()*shareOutBonusManageTO.getTotalSentStocks();
            //重新将所有的分红明细重算
            List<ShareOutBonusDetailBO> shareOutBonusDetailBOS = shareOutBonusDetailSer.findListBySharId(shareOutBonusManageTO.getId());

            if(shareOutBonusDetailBOS!=null && shareOutBonusDetailBOS.size()>0){
                List<ShareOutBonusDetail> shareOutBonusDetails = new ArrayList<>();
                for(ShareOutBonusDetailBO s : shareOutBonusDetailBOS){
                    ShareOutBonusDetail shareOutBonusDetail = shareOutBonusDetailSer.findById(s.getId());
                    Double shareOutBonusAmount = totalShareOutBonus*(s.getShareOutBonusPropor()/100);
                    Double incomeTax = shareOutBonusAmount*(s.getIncomeTaxPropor()/100);
                    shareOutBonusDetail.setShareOutBonusAmount(shareOutBonusAmount);//分红额
                    shareOutBonusDetail.setTotalShareOutBonus(totalShareOutBonus);//共分红
                    shareOutBonusDetail.setIncomeTax(incomeTax);//所得税
                    shareOutBonusDetail.setAfterTaxProfits(shareOutBonusAmount-incomeTax);//税后利润
                    shareOutBonusDetail.setModifyTime(LocalDateTime.now());
                    shareOutBonusDetails.add(shareOutBonusDetail);
                }
                shareOutBonusDetailSer.update(shareOutBonusDetails);
                totalIncomeTax = shareOutBonusDetailBOS.stream().mapToDouble(s->s.getIncomeTax()).sum();
            }
        }
        share = BeanTransform.copyProperties(shareOutBonusManageTO,ShareOutBonusManage.class,true);
        share.setCreateTime(date);
        share.setModifyTime(LocalDateTime.now());
        share.setTotalShareOutBonus(totalShareOutBonus);//设置共分红
        share.setTotalIncomeTax(totalIncomeTax);//设置共缴费

        super.update(share);
        return BeanTransform.copyProperties(share,ShareOutBonusManageBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        shareOutBonusDetailSer.deleteByShareId(id);//删除分红明细
        super.remove(id);
    }

    @Override
    public List<ShareOutBonusCaseBO> findShareCase(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
       checkPermission();
        String con = "";
        if (StringUtils.isNotBlank(shareOutBonusManageDTO.getStartDate()) && StringUtils.isBlank(shareOutBonusManageDTO.getEndDate())) {
            throw new SerException("请输入结束时间");
        } else if (StringUtils.isNotBlank(shareOutBonusManageDTO.getEndDate()) && StringUtils.isBlank(shareOutBonusManageDTO.getStartDate())) {
            throw new SerException("请输入开始时间");
        } else if (StringUtils.isNotBlank(shareOutBonusManageDTO.getEndDate()) && StringUtils.isNotBlank(shareOutBonusManageDTO.getStartDate())) {
            con += " and b.shareOutBonusDate between '"+shareOutBonusManageDTO.getStartDate()+" ' and ' "+shareOutBonusManageDTO.getEndDate()+"' ";
        }
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getArea())){
            con += " and b.area = '"+shareOutBonusManageDTO.getArea()+"' ";
        }
        String[] field = {"shareOutBonusDate","area","shareOutBonusName","shareholderName","equityType","holdNum","shareOutBonusAmount","incomeTax","afterTaxProfits","remark"};
        String sql =" SELECT" +
                "  b.ShareOutBonusDate   AS shareOutBonusDate," +
                "  b.area                AS area," +
                "  b.shareOutBonusName   AS shareOutBonusName," +
                "  a.shareholderName     AS shareholderName," +
                "  b.equityType          AS equityType," +
                "  c.holdNum             AS holdNum," +
                "  a.shareOutBonusAmount AS shareOutBonusAmount," +
                "  a.incomeTax           AS incomeTax," +
                "  a.afterTaxProfits     AS afterTaxProfits," +
                "  b.remark              AS remark" +
                " FROM shareholdersmanage_shareoutbonusdetail a LEFT JOIN shareholdersmanage_shareoutbonusmanage b" +
                "    ON a.ShareOutBonusManageId = b.id" +
                "  LEFT JOIN shareholdersmanage_equitytransactrecord c ON a.shareholderName = c.shareholderName" +
                " WHERE 1=1 "+con+" ";
        List<ShareOutBonusCaseBO> shareOutBonusCaseBOList = shareOutBonusDetailSer.findBySql(sql,ShareOutBonusCaseBO.class,field);
        return shareOutBonusCaseBOList;
    }

    @Override
    public List<ShareOutBonusCaseBO> summShareCase(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
       checkPermission();
        String con = "";
        if (StringUtils.isNotBlank(shareOutBonusManageDTO.getStartDate()) && StringUtils.isBlank(shareOutBonusManageDTO.getEndDate())) {
            throw new SerException("请输入结束时间");
        } else if (StringUtils.isNotBlank(shareOutBonusManageDTO.getEndDate()) && StringUtils.isBlank(shareOutBonusManageDTO.getStartDate())) {
            throw new SerException("请输入开始时间");
        } else if (StringUtils.isNotBlank(shareOutBonusManageDTO.getEndDate()) && StringUtils.isNotBlank(shareOutBonusManageDTO.getStartDate())) {
            con += " and b.shareOutBonusDate between '"+shareOutBonusManageDTO.getStartDate()+"' and '"+shareOutBonusManageDTO.getEndDate()+"'";
        }
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getArea())){
            con += " and b.area = '"+shareOutBonusManageDTO.getArea()+"' ";
        }
        String[] field = {"shareOutBonusDate","area","shareOutBonusName","shareholderName","equityType","holdNum","shareOutBonusAmount","incomeTax","afterTaxProfits","remark"};
        String sql =" SELECT" +
                "  b.ShareOutBonusDate   AS shareOutBonusDate," +
                "  b.area                AS area," +
                "  b.shareOutBonusName   AS shareOutBonusName," +
                "  a.shareholderName     AS shareholderName," +
                "  b.equityType          AS equityType," +
                "  c.holdNum             AS holdNum," +
                "  a.shareOutBonusAmount AS shareOutBonusAmount," +
                "  a.incomeTax           AS incomeTax," +
                "  a.afterTaxProfits     AS afterTaxProfits," +
                "  b.remark              AS remark" +
                " FROM shareholdersmanage_shareoutbonusdetail a LEFT JOIN shareholdersmanage_shareoutbonusmanage b" +
                "    ON a.ShareOutBonusManageId = b.id" +
                "  LEFT JOIN shareholdersmanage_equitytransactrecord c ON a.shareholderName = c.shareholderName" +
                " WHERE 1=1 "+con+" ";
        List<ShareOutBonusCaseBO> shareOutBonusCaseBOList = shareOutBonusDetailSer.findBySql(sql,ShareOutBonusCaseBO.class,field);
        if(shareOutBonusCaseBOList!=null && shareOutBonusCaseBOList.size()>0){
            ShareOutBonusCaseBO share = new ShareOutBonusCaseBO();
            Integer holdNum = shareOutBonusCaseBOList.stream().mapToInt(s->s.getHoldNum()).sum();//股份数量
            Double shareOutBonusAmount = shareOutBonusCaseBOList.stream().mapToDouble(s->s.getShareOutBonusAmount()).sum();//分红所得
            Double incomeTax = shareOutBonusCaseBOList.stream().mapToDouble(s->s.getIncomeTax()).sum();//所得税
            Double afterTaxProfits = shareOutBonusCaseBOList.stream().mapToDouble(s->s.getAfterTaxProfits()).sum();//税后利润
            share.setShareOutBonusDate("合计");
            share.setHoldNum(holdNum);
            share.setShareOutBonusAmount(shareOutBonusAmount);
            share.setIncomeTax(incomeTax);
            share.setAfterTaxProfits(afterTaxProfits);
            shareOutBonusCaseBOList.add(share);
        }
        return shareOutBonusCaseBOList;
    }

    @Override
    public List<ShareRosterBO> findShareRoster(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
       checkPermission();
        String con = "";
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getArea())){
            con += " and a.area = '"+shareOutBonusManageDTO.getArea()+"' ";
        }
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getShareholderName())){
            con += " and a.shareholderName = '"+shareOutBonusManageDTO.getShareholderName()+"' ";
        }
        String[] field = {"area","typeName","shareholderName","gender","documentType","certifiID","contactAddress","contactNum","holdNum","perSharePrice","amount","capitalWay","openDate","equityType","percentage"};
        String sql ="SELECT" +
                "  a.area            AS area," +
                "  a.typeName        AS typeName," +
                "  a.shareholderName AS shareholderName," +
                "  a.gender          AS gender," +
                "  a.documentType    AS documentType," +
                "  a.certifiID       AS certifiID," +
                "  a.contactAddress  AS contactAddress," +
                "  a.contactNum      AS contactNum," +
                "  b.holdNum         AS holdNum," +
                "  b.perSharePrice   AS perSharePrice," +
                "  b.amount          AS amount," +
                "  a.capitalWay      AS capitalWay," +
                "  a.openDate        AS openDate," +
                "  b.equityType      AS equityType," +
                "  b.percentage      AS percentage" +
                "  FROM shareholdersmanage_shareopenaccount a LEFT JOIN shareholdersmanage_equitytransactrecord b" +
                "    ON a.shareholderName = b.shareholderName" +
                "  WHERE a.shareholderStatus = 0 "+con+" ";
        List<ShareRosterBO> shareRosterBOList = shareOpenAccountSer.findBySql(sql,ShareRosterBO.class,field);
        return shareRosterBOList;
    }

    @Override
    public List<ShareRosterDetailBO> findShareRosterDetail(String shareholderName) throws SerException {
        checkPermission();
        String[] field = {"shareOutBonusDate","shareOutBonusName","shareOutBonusAmount","incomeTax","afterTaxProfits","remark"};
        String sql =" SELECT" +
                "  b.ShareOutBonusDate   AS shareOutBonusDate," +
                "  b.shareOutBonusName   AS shareOutBonusName," +
                "  a.shareOutBonusAmount AS shareOutBonusAmount," +
                "  a.incomeTax           AS incomeTax," +
                "  a.afterTaxProfits     AS afterTaxProfits," +
                "  b.remark              AS remark" +
                " FROM shareholdersmanage_shareoutbonusdetail a LEFT JOIN shareholdersmanage_shareoutbonusmanage b" +
                "    ON a.ShareOutBonusManageId = b.id" +
                " WHERE a.shareholderName = '"+shareholderName+"' ";
        List<ShareRosterDetailBO> shareRosterDetailBOList = shareOutBonusDetailSer.findBySql(sql,ShareRosterDetailBO.class,field);
        return shareRosterDetailBOList;
    }

    @Override
    public List<ShareRosterBO> summShareRoster(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
        checkPermission();
        String con = "";
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getArea())){
            con += " and a.area = '"+shareOutBonusManageDTO.getArea()+"' ";
        }
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getShareholderName())){
            con += " and a.shareholderName = '"+shareOutBonusManageDTO.getShareholderName()+"' ";
        }
        String[] field = {"area","typeName","shareholderName","gender","documentType","certifiID","contactAddress","contactNum","holdNum","perSharePrice","amount","capitalWay","openDate","equityType","percentage"};
        String sql ="SELECT" +
                "  a.area            AS area," +
                "  a.typeName        AS typeName," +
                "  a.shareholderName AS shareholderName," +
                "  a.gender          AS gender," +
                "  a.documentType    AS documentType," +
                "  a.certifiID       AS certifiID," +
                "  a.contactAddress  AS contactAddress," +
                "  a.contactNum      AS contactNum," +
                "  b.holdNum         AS holdNum," +
                "  b.perSharePrice   AS perSharePrice," +
                "  b.amount          AS amount," +
                "  a.capitalWay      AS capitalWay," +
                "  a.openDate        AS openDate," +
                "  b.equityType      AS equityType," +
                "  b.percentage      AS percentage" +
                "  FROM shareholdersmanage_shareopenaccount a LEFT JOIN shareholdersmanage_equitytransactrecord b" +
                "    ON a.shareholderName = b.shareholderName" +
                "  WHERE b.shareholderStatus = 0 "+con+" ";
        List<ShareRosterBO> shareRosterBOList = shareOpenAccountSer.findBySql(sql,ShareRosterBO.class,field);
       if (shareRosterBOList!=null && shareRosterBOList.size()>0){
           ShareRosterBO shareRosterBO = new ShareRosterBO();
           shareRosterBO.setArea("合计");
           Integer holdNum = shareRosterBOList.stream().mapToInt(s->s.getHoldNum()).sum();//股份数量
           Double amount = shareRosterBOList.stream().mapToDouble(s->s.getAmount()).sum();//金额
           shareRosterBO.setHoldNum(holdNum);
           shareRosterBO.setAmount(amount);
           shareRosterBOList.add(shareRosterBO);
       }
        return shareRosterBOList;
    }

    @Override
    public byte[] exportExcel(ShareOutBonusManageDTO shareOutBonusManageDTO) throws SerException {
       checkPermission();
        String con = "";
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getArea())){
            con += " and a.area = '"+shareOutBonusManageDTO.getArea()+"' ";
        }
        if(StringUtils.isNotBlank(shareOutBonusManageDTO.getShareholderName())){
            con += " and a.shareholderName = '"+shareOutBonusManageDTO.getShareholderName()+"' ";
        }
        String[] field = {"area","typeName","shareholderName","gender","documentType","certifiID","contactAddress","contactNum","holdNum","perSharePrice","amount","capitalWay","openDate","equityType","percentage"};
        String sql ="SELECT" +
                "  a.area            AS area," +
                "  a.typeName        AS typeName," +
                "  a.shareholderName AS shareholderName," +
                "  a.gender          AS gender," +
                "  a.documentType    AS documentType," +
                "  a.certifiID       AS certifiID," +
                "  a.contactAddress  AS contactAddress," +
                "  a.contactNum      AS contactNum," +
                "  b.holdNum         AS holdNum," +
                "  b.perSharePrice   AS perSharePrice," +
                "  b.amount          AS amount," +
                "  a.capitalWay      AS capitalWay," +
                "  a.openDate        AS openDate," +
                "  b.equityType      AS equityType," +
                "  b.percentage      AS percentage" +
                "  FROM shareholdersmanage_shareopenaccount a LEFT JOIN shareholdersmanage_equitytransactrecord b" +
                "    ON a.shareholderName = b.shareholderName" +
                "  WHERE b.shareholderStatus = 0 "+con+" ";
        List<ShareRosterBO> shareRosterBOList = shareOpenAccountSer.findBySql(sql,ShareRosterBO.class,field);
        List<ShareRosterExport> shareRosterExports = new ArrayList<>();
        shareRosterBOList.stream().forEach(str -> {
            ShareRosterExport excel = BeanTransform.copyProperties(str, ShareRosterExport.class, "typeName", "gender");
            excel.setTypeName(TypeName.exportStrConvert(str.getTypeName()));
            excel.setGender(Gender.exportStrConvert(str.getGender()));
            shareRosterExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(shareRosterExports, excel);
        return bytes;
    }
}