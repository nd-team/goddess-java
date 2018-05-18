package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.recruit.api.InterviewInforAPI;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.salarymanage.bo.SalaryCalculateDetailBO;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateDetail;
import com.bjike.goddess.salarymanage.enums.GuideAddrStatus;
import com.bjike.goddess.salarymanage.enums.WorkAge;
import com.bjike.goddess.salarymanage.excel.SalaryCalculateDetailSetExcel;
import com.bjike.goddess.salarymanage.excel.SonPermissionObject;
import com.bjike.goddess.salarymanage.to.ExportSalaryCalculateTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 薪资测算明细表业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-09-16 10:45 ]
 * @Description: [ 薪资测算明细表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "salarymanageSerCache")
@Service
public class SalaryCalculateDetailSerImpl extends ServiceImpl<SalaryCalculateDetail, SalaryCalculateDetailDTO> implements SalaryCalculateDetailSer {

    @Autowired
    private InterviewInforAPI interviewInforAPI;

    @Autowired
    private UserAPI userAPI;


    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;


    @Autowired
    private SalaryCalculateResultSer salaryCalculateResultSer;

    @Autowired
    private SalaryConfirmRecordSer salaryConfirmRecordSer;

    @Autowired
    private SalaryManageCollectSer salaryManageCollectSer;

    @Autowired
    private SalaryTestCollectSer salaryTestCollectSer;

    @Autowired
    private SalaryInformationSer salaryInformationSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.getCusPermission ( "1" );
            if (!flag) {
                throw new SerException ( "您不是相应部门的人员，不可以查看" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "2" );
            if (!flag) {
                throw new SerException ( "您不是相应部门的人员，不可以操作" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.getCusPermission ( "1" );
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "2" );
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<> ();
        String userToken = RpcTransmit.getUserToken ();
        Boolean flagCalculate = guideSeeIdentity ();
        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagAddSign = guideAddIdentity ();

        SonPermissionObject obj = new SonPermissionObject ();

        obj = new SonPermissionObject ();
        obj.setName ( "salaryCalculateDetail" );
        obj.setDescribesion ( "薪资测算明细表" );
        if (flagCalculate || flagAddSign) {
            obj.setFlag ( true );
        } else {
            obj.setFlag ( false );
        }
        list.add ( obj );


        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagInformation = salaryInformationSer.sonPermission ();
        RpcTransmit.transmitUserToken ( userToken );
        obj = new SonPermissionObject ();
        obj.setName ( "salaryInformation" );
        obj.setDescribesion ( "员工薪资明细" );
        if (flagInformation) {
            obj.setFlag ( true );
        } else {
            obj.setFlag ( false );
        }
        list.add ( obj );

        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagResult = salaryCalculateResultSer.sonPermission ();
        RpcTransmit.transmitUserToken ( userToken );
        obj = new SonPermissionObject ();
        obj.setName ( "salaryCalculateResult" );
        obj.setDescribesion ( "薪资测算结果" );
        if (flagResult) {
            obj.setFlag ( true );
        } else {
            obj.setFlag ( false );
        }
        list.add ( obj );

        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagConfirm = salaryConfirmRecordSer.sonPermission ();
        RpcTransmit.transmitUserToken ( userToken );
        obj = new SonPermissionObject ();
        obj.setName ( "salaryConfirmRecord" );
        obj.setDescribesion ( "招聘面谈薪资确认记录" );
        if (flagConfirm) {
            obj.setFlag ( true );
        } else {
            obj.setFlag ( false );
        }
        list.add ( obj );

        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagManageCollect = salaryManageCollectSer.sonPermission ();
        RpcTransmit.transmitUserToken ( userToken );
        obj = new SonPermissionObject ();
        obj.setName ( "salaryManageCollect" );
        obj.setDescribesion ( "薪资管理汇总" );
        if (flagManageCollect) {
            obj.setFlag ( true );
        } else {
            obj.setFlag ( false );
        }
        list.add ( obj );

        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagTestCollect = salaryTestCollectSer.sonPermission ();
        RpcTransmit.transmitUserToken ( userToken );
        obj = new SonPermissionObject ();
        obj.setName ( "salaryTestCollect" );
        obj.setDescribesion ( "薪资测算汇总" );
        if (flagTestCollect) {
            obj.setFlag ( true );
        } else {
            obj.setFlag ( false );
        }
        list.add ( obj );


        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken ();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus ();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity ();
                break;
            case ADD:
                flag = guideAddIdentity ();
                break;
            case EDIT:
                flag = guideAddIdentity ();
                break;
            case DELETE:
                flag = guideAddIdentity ();
                break;
            case CONGEL:
                flag = guideAddIdentity ();
                break;
            case THAW:
                flag = guideAddIdentity ();
                break;
            case COLLECT:
                flag = guideAddIdentity ();
                break;
            case IMPORT:
                flag = guideAddIdentity ();
                break;
            case EXPORT:
                flag = guideAddIdentity ();
                break;
            case UPLOAD:
                flag = guideAddIdentity ();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity ();
                break;
            case SEE:
                flag = guideSeeIdentity ();
                break;
            case SEEFILE:
                flag = guideSeeIdentity ();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public void add(SalaryCalculateDetailTO to) throws SerException {
        SalaryCalculateDetail model = BeanTransform.copyProperties ( to, SalaryCalculateDetail.class );
        super.save ( model );
    }

    @Override
    public void delete(String id) throws SerException {
        if (!"".equals ( id ) && id != null) {
            SalaryCalculateDetail model = super.findById ( id );
            super.remove ( id );
        } else {
            throw new SerException ( "id不能为空" );
        }
    }

    @Override
    public void modify(SalaryCalculateDetailTO to) throws SerException {
        SalaryCalculateDetail model = super.findById ( to.getId () );
        BeanTransform.copyProperties ( to, model, "createTime", "modifyTime" );
        model.setModifyTime ( LocalDateTime.now () );
        super.update ( model );
    }

    @Override
    public List<SalaryCalculateDetailBO> findList(SalaryCalculateDetailDTO dto) throws SerException {
        checkAddIdentity ();
        List<SalaryCalculateDetail> list = super.findByCis ( dto );
        List<SalaryCalculateDetailBO> boList = BeanTransform.copyProperties ( list, SalaryCalculateDetailBO.class );
        return boList;
    }

    @Override
    public List<InterviewInforBO> findInteview() throws SerException {
        List<InterviewInforBO> boList = new ArrayList<> ();
        if (moduleAPI.isCheck ( "recruit" )) {
            boList = interviewInforAPI.findInterview ();
        }
        return boList;
    }

    @Override
    public SalaryCalculateDetailBO findOne(String id) throws SerException {
        SalaryCalculateDetail model = super.findById ( id );
        SalaryCalculateDetailBO bo = BeanTransform.copyProperties ( model, SalaryCalculateDetailBO.class );
        return bo;
    }

    @Override
    public Long count(SalaryCalculateDetailDTO dto) throws SerException {
        return super.count ( dto );
    }

    @Override
    public void leadExcel(List<SalaryCalculateDetailTO> toList) throws SerException {
        UserBO userBO = userAPI.currentUser ();
        List<SalaryCalculateDetail> list = BeanTransform.copyProperties ( toList, SalaryCalculateDetail.class, true );
        if (list != null && list.size () > 0) {
            list.stream ().forEach ( str -> {
                str.setModifyTime ( LocalDateTime.now () );
                str.setCreateTime ( LocalDateTime.now () );
            } );
        }
        super.save ( list );
    }

    @Override
    public byte[] exportExcel(ExportSalaryCalculateTO to) throws SerException {
        SalaryCalculateDetailDTO detailDTO = new SalaryCalculateDetailDTO ();
        List<SalaryCalculateDetail> list = super.findByCis ( detailDTO );
        List<SalaryCalculateDetailSetExcel> toList = new ArrayList<SalaryCalculateDetailSetExcel> ();
        for (SalaryCalculateDetail model : list) {
            SalaryCalculateDetailSetExcel excel = BeanTransform.copyProperties ( model, SalaryCalculateDetailSetExcel.class );
            toList.add ( excel );
        }
        Excel excel = new Excel ( 0, 2 );
        byte[] bytes = ExcelUtil.clazzToExcel ( toList, excel );
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<SalaryCalculateDetailSetExcel> salaryCalculateDetailSetExcels = new ArrayList<> ();

        SalaryCalculateDetailSetExcel excel = new SalaryCalculateDetailSetExcel ();

        excel.setUserName ( "姓名" );
        excel.setPosition ( "岗位" );
        excel.setArea ( "地区" );
        excel.setDepartment ( "项目组/部门" );
        excel.setBusinessDirection ( "业务方向" );
        excel.setSkill ( "技能" );
        excel.setWorkAge ( WorkAge.ADVANCED );
        excel.setExpectation ( 5000 );
        excel.setZhilian ( 0 );
        excel.setZhonghua ( 0 );
        excel.setLieping ( 0 );
        excel.setWuyou ( 0 );
        excel.setBoss ( 0 );
        salaryCalculateDetailSetExcels.add ( excel );

        Excel exce = new Excel ( 0, 2 );
        byte[] bytes = ExcelUtil.clazzToExcel ( salaryCalculateDetailSetExcels, exce );
        return bytes;
    }
}