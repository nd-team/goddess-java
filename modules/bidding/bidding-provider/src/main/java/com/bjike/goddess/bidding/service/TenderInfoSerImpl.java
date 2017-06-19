package com.bjike.goddess.bidding.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bjike.goddess.bidding.bo.TenderInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.enums.GuideAddrStatus;
import com.bjike.goddess.bidding.excel.TenderInfoExport;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.to.TenderInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bidding.dto.TenderInfoDTO;
import com.bjike.goddess.bidding.entity.TenderInfo;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
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
 * 标书资料业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.338 ]
 * @Description: [ 标书资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "biddingSerCache")
@Service
public class TenderInfoSerImpl extends ServiceImpl<TenderInfo, TenderInfoDTO> implements TenderInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.getCusPermission("1");
        }else{
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException{
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser( );
        RpcTransmit.transmitUserToken( userToken );
        String userName = userBO.getUsername();
        if( !"admin".equals( userName.toLowerCase())){
            flag = cusPermissionSer.busCusPermission("2");
        }else{
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken( userToken );
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
            return true;
        }else{
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        tenderInfoDTO.getSorts().add("createTime=desc");
        Long count = super.count(tenderInfoDTO);
        return count;
    }
    @Override
    public TenderInfoBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        TenderInfo tenderInfo = super.findById(id);
        return BeanTransform.copyProperties(tenderInfo,TenderInfoBO.class);
    }
    @Override
    public List<TenderInfoBO> findListTenderInfo(TenderInfoDTO tenderInfoDTO) throws SerException {
        checkSeeIdentity();
        List<TenderInfo> tenderInfo = super.findByCis(tenderInfoDTO,true);
        List<TenderInfoBO> tenderInfoBOS = BeanTransform.copyProperties(tenderInfo,TenderInfoBO.class);
        return tenderInfoBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public TenderInfoBO insertTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        checkAddIdentity();
        TenderInfo tenderInfo = BeanTransform.copyProperties(tenderInfoTO, TenderInfo.class, true);
        tenderInfo.setModifyTime(LocalDateTime.now());
        super.save(tenderInfo);
        return BeanTransform.copyProperties(tenderInfo, TenderInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public TenderInfoBO editTenderInfo(TenderInfoTO tenderInfoTO) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(tenderInfoTO.getId())){
            throw new SerException("id不能为空");
        }
        TenderInfo tenderInfo = super.findById(tenderInfoTO.getId());
        BeanTransform.copyProperties(tenderInfoTO, tenderInfo, true);
        tenderInfo.setModifyTime(LocalDateTime.now());
        super.update(tenderInfo);
        return BeanTransform.copyProperties(tenderInfoTO, TenderInfoBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeTenderInfo(String id) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Override
    public byte[] exportExcel(TenderInfoDTO dto) throws SerException{
        if(StringUtils.isNotBlank(dto.getProjectName())){
            dto.getConditions().add(Restrict.eq("projectName",dto.getProjectName()));
        }
        List<TenderInfo> list = super.findByCis(dto);
        List<TenderInfoExport> tenderInfoExports = new ArrayList<>();
        list.stream().forEach(str->{
            TenderInfoExport export = BeanTransform.copyProperties(str,TenderInfoExport.class);
            tenderInfoExports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte [] bytes = ExcelUtil.clazzToExcel(tenderInfoExports,excel);
        return bytes;
    }
}