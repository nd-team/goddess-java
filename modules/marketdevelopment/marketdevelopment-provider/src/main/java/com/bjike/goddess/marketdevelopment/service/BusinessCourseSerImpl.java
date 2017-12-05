package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessSubjectBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessCourseDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessCourse;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.enums.Status;
import com.bjike.goddess.marketdevelopment.excel.BusinessCourseExportExcel;
import com.bjike.goddess.marketdevelopment.excel.BusinessCourseImportExcel;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务方向科目业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class BusinessCourseSerImpl extends ServiceImpl<BusinessCourse, BusinessCourseDTO> implements BusinessCourseSer {

    @Autowired
    private BusinessTypeSer typeSer;
    @Autowired
    private MarPermissionSer marPermissionSer;
    @Autowired
    private UserAPI userAPI;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = marPermissionSer.getMarPermission(marketCheck);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = marPermissionSer.getMarPermission(marketManage);
        } else {
            flag = true;
        }
        return flag;
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
    public List<String> getProjectName() throws SerException {
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        dto.getSorts().add("course=asc");
        List<BusinessCourse> list = super.findByCis(dto);
        if (null != list && list.size() > 0) {
            return list.stream().map(BusinessCourse::getCourse).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        BusinessCourseImportExcel businessCourseImportExcel = new BusinessCourseImportExcel();
        List<BusinessCourseImportExcel> list = new ArrayList<>(0);
        list.add(businessCourseImportExcel);
        Excel excel = new Excel(0, 2);
        byte bytes[] = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Override
    public void upload(List<BusinessCourseImportExcel> tos) throws SerException {
        List<BusinessCourseTO> businessCourseTOs = BeanTransform.copyProperties(tos, BusinessCourseTO.class);
        for (BusinessCourseTO to : businessCourseTOs) {
            this.save(to);
        }
    }

    @Override
    public byte[] exportExcel(BusinessCourseDTO dto) throws SerException {
        dto.getSorts().add("businessNum=asc");
        dto.getSorts().add("subjectNum=asc");
        List<BusinessCourse> list = super.findByCis(dto);
        List<BusinessCourseExportExcel> businessCourseExportExcels = BeanTransform.copyProperties(list, BusinessCourseExportExcel.class);

        List<Integer> mainTableLen = new ArrayList<>(0);
        List<String> stringList = list.stream().map(BusinessCourse::getBusinessType).distinct().collect(Collectors.toList());
        if (null != stringList && stringList.size() > 0) {
            for (String str : stringList) {
                BusinessCourseDTO dto1 = new BusinessCourseDTO();
                dto1.getConditions().add(Restrict.eq("businessType", str));
                List<BusinessCourse> businessCourseList = super.findByCis(dto1);
                mainTableLen.add(businessCourseList.size());
            }
        }

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(businessCourseExportExcels, excel);

        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            //主表行数
            int rowSize = list.size();


            int index = 0;
            int lastRow = 0;
            for (int j = 0; j < rowSize; j++) {

                //List<int> maxList所有子表的长度
                if (null != mainTableLen && mainTableLen.size() > 0) {
                    int mergeRowCount = mainTableLen.get(j);
                    //5
                    if (mergeRowCount != 1) {

                        int firstRow = index;
                        lastRow = firstRow + mergeRowCount - 1;
                        if (firstRow == 0) {
                            firstRow += 1;
                            lastRow += 1;
                        }
                        //合并主表共33列
                        for (int i = 0; i < 2; i++) {
                            //1,5
                            sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                        }

                        os = new ByteArrayOutputStream();
                        wb.write(os);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    /**
     * 转换业务方向科目传输对象
     *
     * @param entity 业务方向科目实体
     * @return
     */
    private BusinessCourseBO transformBO(BusinessCourse entity) {
        BusinessCourseBO bo = BeanTransform.copyProperties(entity, BusinessCourseBO.class);
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getSorts().add("businessNum=asc");
        dto.getSorts().add("subjectNum=asc");

        return bo;
    }

    /**
     * 转换业务方向科目传输对象集合
     *
     * @param list 业务方向科目实体集合
     * @return
     */
    private List<BusinessCourseBO> transformBOList(List<BusinessCourse> list) throws SerException {
        List<BusinessCourseBO> bos = new ArrayList<>(0);
        if (null == list || list.size() < 1) {
            return null;
        }

        List<String> stringList = list.stream().map(BusinessCourse::getBusinessType).distinct().collect(Collectors.toList());
        for (String string : stringList) {
            BusinessCourseDTO dto = new BusinessCourseDTO();
            dto.getConditions().add(Restrict.eq("businessType", string));
            List<BusinessCourse> businessCourses = super.findByCis(dto);
            BusinessCourseBO bo = new BusinessCourseBO();
            bo.setBusinessNum(businessCourses.get(0).getBusinessNum());
            bo.setBusinessType(businessCourses.get(0).getBusinessType());
            List<BusinessSubjectBO> businessSubjectBOs = BeanTransform.copyProperties(businessCourses, BusinessSubjectBO.class);
            bo.setBusinessSubjectVOs(businessSubjectBOs);
            bos.add(bo);
        }

        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO save(BusinessCourseTO to) throws SerException {
        BusinessCourse entity = BeanTransform.copyProperties(to, BusinessCourse.class);

        List<BusinessCourse> businessCourses = super.findAll();
        int businessNum = 0;
        int subjectNum = 0;
        if (null != businessCourses && businessCourses.size() > 0) {
            for (BusinessCourse businessCourse : businessCourses) {
                if (entity.getBusinessType().equals(businessCourse.getBusinessType())) {
                    businessNum = Integer.valueOf(businessCourse.getBusinessNum());
                }
            }
            if (businessNum == 0) {
                List<String> stringList = businessCourses.stream().map(BusinessCourse::getBusinessType).distinct().collect(Collectors.toList());
                businessNum = stringList.size() + 1;
                subjectNum = 1;
            } else {
                BusinessCourseDTO businessCourseDTO = new BusinessCourseDTO();
                businessCourseDTO.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
                List<BusinessCourse> businessCourses1 = super.findByCis(businessCourseDTO);
                List<String> stringList = businessCourses1.stream().map(BusinessCourse::getCourse).distinct().collect(Collectors.toList());
                subjectNum = stringList.size() + 1;
            }

            entity.setBusinessNum(String.valueOf(businessNum));
            entity.setSubjectNum(String.valueOf(businessNum) + "." + String.valueOf(subjectNum));

        } else {
            entity.setBusinessNum("1");
            entity.setSubjectNum("1.1");
        }
        entity.setStatus(Status.THAW);

        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO update(BusinessCourseTO to) throws SerException {
        int businessNum = 0;
        int subjectNum = 0;
        BusinessCourse entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }

        BeanTransform.copyProperties(to, entity);
//        List<BusinessCourse> businessCourses3 = super.findAll();
//        if (businessCourses3.size() == 1) {
//            entity.setModifyTime(LocalDateTime.now());
//            super.update(entity);
//        } else {
//            BusinessCourseDTO dto = new BusinessCourseDTO();
//            dto.getConditions().add(Restrict.eq("businessType", entity.getBusinessType()));
//            List<BusinessCourse> businessCourses = super.findByCis(dto);
//            if (null != businessCourses && businessCourses.size() > 0) {
//                businessNum = Integer.valueOf(businessCourses.get(0).getBusinessNum());
//                List<BusinessCourse> businessCourses2 = businessCourses.stream().filter(obj -> entity.getCourse().equals(obj.getCourse())).distinct().collect(Collectors.toList());
//                if (null != businessCourses2 && businessCourses2.size() > 0) {
//                    subjectNum = Integer.valueOf(businessCourses2.get(0).getSubjectNum());
//                } else {
//                    subjectNum = businessCourses.size() + 1;
//                }
//            } else {
//                List<BusinessCourse> businessCourses1 = super.findAll();
//                List<String> stringList = businessCourses1.stream().map(BusinessCourse::getBusinessType).distinct().collect(Collectors.toList());
//                businessNum = stringList.size() + 1;
//                subjectNum = 1;
//            }
//            entity.setBusinessNum(String.valueOf(businessNum));
//            entity.setSubjectNum(String.valueOf(businessNum) + "." + String.valueOf(subjectNum));
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
//        }
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO congeal(BusinessCourseTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("数据对象不能为空");
        }
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO thaw(BusinessCourseTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessCourseBO delete(BusinessCourseTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessCourse entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return this.transformBO(entity);
    }

    @Override
    public List<BusinessCourseBO> findByType(String typeId) throws SerException {
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getConditions().add(Restrict.eq("type.id", typeId));
        List<BusinessCourse> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<BusinessCourseBO> findThaw() throws SerException {
        BusinessCourseDTO dto = new BusinessCourseDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        dto.getSorts().add("course=asc");
        List<BusinessCourse> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public BusinessCourseBO getById(String id) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        try {
            return this.transformBO(super.findById(id));
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public List<BusinessCourseBO> maps(BusinessCourseDTO dto) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketCheck))
//            throw new SerException("您的帐号没有权限");
        dto.getSorts().add("businessNum=asc");
        dto.getSorts().add("subjectNum=asc");
        return this.transformBOList(super.findByPage(dto));
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }
}