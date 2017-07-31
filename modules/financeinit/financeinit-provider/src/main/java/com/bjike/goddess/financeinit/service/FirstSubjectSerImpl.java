package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.CategoryDTO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.entity.Category;
import com.bjike.goddess.financeinit.entity.FirstSubject;
import com.bjike.goddess.financeinit.enums.GuideAddrStatus;
import com.bjike.goddess.financeinit.excel.FirstSubjectExport;
import com.bjike.goddess.financeinit.excel.FirstSubjectTemplateExport;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一级科目业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "financeinitSerCache")
@Service
public class FirstSubjectSerImpl extends ServiceImpl<FirstSubject, FirstSubjectDTO> implements FirstSubjectSer {

    @Autowired
    private CategorySer categorySer;
    @Autowired
    private CurrencySer currencySer;
    @Autowired
    private AccountSer accountSer;
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
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应财务部门的人员，不可以查看");
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
                throw new SerException("您不是相应财务部门的人员，不可以操作");
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
            flag = cusPermissionSer.getCusPermission("1");
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("firstsubject");
        obj.setDescribesion("财务初始化一级科目");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = categorySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("category");
        obj.setDescribesion("财务初始化类别");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = accountSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("account");
        obj.setDescribesion("财务初始化账户来源");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = currencySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("currency");
        obj.setDescribesion("财务初始化币别设置");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Long countFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        Long count = super.count(firstSubjectDTO);
        return count;
    }

    @Override
    public FirstSubjectBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        FirstSubject firstSubject = super.findById(id);
        return BeanTransform.copyProperties(firstSubject, FirstSubjectBO.class);
    }

    @Override
    public List<FirstSubjectBO> listFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        checkSeeIdentity();

        List<FirstSubject> list = super.findByCis(firstSubjectDTO, true);

        return BeanTransform.copyProperties(list, FirstSubjectBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FirstSubjectBO addFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        checkAddIdentity();

        String category = firstSubjectTO.getCategory();
        if (StringUtils.isBlank(category)) {
            throw new SerException("级别所属类别不能为空哦");
        }
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("name", firstSubjectTO.getName()));
//        dto.getConditions().add(Restrict.eq("category", firstSubjectTO.getCategory()));
        Long count = super.count(dto);
        if (count > 0) {
            throw new SerException("该级别所属类别下的一级类别名已经存在，不可以再填");
        }

        String code = getCodeGenerate(category);

        FirstSubject firstSubject = BeanTransform.copyProperties(firstSubjectTO, FirstSubject.class, true);
        firstSubject.setCode(code);
        firstSubject.setCreateTime(LocalDateTime.now());
        super.save(firstSubject);
        return BeanTransform.copyProperties(firstSubject, FirstSubjectBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FirstSubjectBO editFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        checkAddIdentity();

        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("name", firstSubjectTO.getName()));
        dto.getConditions().add(Restrict.eq("category", firstSubjectTO.getCategory()));
        FirstSubject first = super.findOne(dto);
        if (first != null && firstSubjectTO.getName().equals(first.getName()) && !firstSubjectTO.getId().equals(first.getId())) {
            throw new SerException("该级别所属类别下的一级类别名已经存在，不可以修改成这个名字");
        }

        FirstSubject firstSubject = BeanTransform.copyProperties(firstSubjectTO, FirstSubject.class, true);
        FirstSubject dbFirstSubject = super.findById(firstSubjectTO.getId());

        dbFirstSubject.setName(firstSubject.getName());
        dbFirstSubject.setCategory(firstSubject.getCategory());
        dbFirstSubject.setRemark(firstSubject.getRemark());
        dbFirstSubject.setModifyTime(LocalDateTime.now());
        super.update(dbFirstSubject);
        return BeanTransform.copyProperties(firstSubject, FirstSubjectBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteFirstSubject(String id) throws SerException {
        checkAddIdentity();

        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        FirstSubject firstSubject = super.findById(id);
        if (firstSubject == null) {
            throw new SerException("删除的该对象不存在,删除失败");
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.getConditions().add(Restrict.eq("firstSubject.id", firstSubject.getId()));
        List<Category> categoryList = categorySer.findByCis(categoryDTO);
        if (categoryList != null && categoryList.size() > 0) {
            categorySer.remove(categoryList);
        }
        super.remove(id);
    }

    @Override
    public FirstSubjectBO getFirstSubject(String firstSubjectName) throws SerException {
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("name", firstSubjectName));
        FirstSubject first = super.findOne(dto);

        return BeanTransform.copyProperties(first, FirstSubjectBO.class);
    }

    @Override
    public List<String> listAllFirst() throws SerException {
        String[] field = new String[]{"name"};
        String sql = " select name  from financeinit_firstsubject group by name ";
        List<FirstSubject> list = super.findBySql(sql, FirstSubject.class, field);
        List<String> names = list.stream().map(FirstSubject::getName).collect(Collectors.toList());
        return names;
    }

    /**
     * 生成编号
     *
     * @param categoryName
     * @return
     * @throws SerException
     */
    public String getCodeGenerate(String categoryName) throws SerException {
        String code = "";
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("category", categoryName));
        dto.getSorts().add("code=desc");
        List<FirstSubject> firstSubjectList = super.findByCis(dto);
        if (firstSubjectList != null && firstSubjectList.size() > 0) {
            code = firstSubjectList.get(0).getCode();
            int num = Integer.parseInt(code.trim()) + 1;
            code = String.valueOf(num);
        } else {
            switch (categoryName) {
                case "资产类":
                    code = "1000";
                    break;
                case "负债类":
                    code = "2000";
                    break;
                case "共同类":
                    code = "3000";
                    break;
                case "权益类":
                    code = "4000";
                    break;
                case "成本类":
                    code = "5000";
                    break;
                case "损益类":
                    code = "6000";
                    break;
                default:
                    code = "1000";
                    break;
            }
        }

        return code;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public FirstSubjectBO importExcel(List<FirstSubjectTO> firstSubjectTO) throws SerException {
        List<FirstSubject> list = new ArrayList<>();
        if( firstSubjectTO!= null && firstSubjectTO.size()>0 ){
            String code = getCodeGenerate(firstSubjectTO.get(0).getCategory());
            for(FirstSubjectTO str : firstSubjectTO ){

                FirstSubjectDTO dto = new FirstSubjectDTO();
                dto.getConditions().add(Restrict.eq("name", str.getName()));
                dto.getConditions().add(Restrict.eq("category", str.getCategory()));
                Long count = super.count(dto);
                if (count > 0) {
                    throw new SerException("该级别所属类别'"+str.getCategory()+"'下的一级类别名'"+str.getName()+"'已经存在，不可以再填,请检查导入数据");
                }

//                String code = getCodeGenerate(str.getCategory());

                FirstSubject firstSubject = BeanTransform.copyProperties(str, FirstSubject.class, true);
                firstSubject.setCode(code);
                firstSubject.setCreateTime(LocalDateTime.now());
                list.add( firstSubject );

                //
                int num = Integer.parseInt(code.trim()) + 1;
                code = String.valueOf(num);
            }
        }
        if( list != null && list.size() >0 ){
//            super.save( list );
        }
        return new FirstSubjectBO();
    }

    @Override
    public byte[] exportExcel(FirstSubjectDTO dto) throws SerException {
        if (null != dto.getCategoryName() && dto.getCategoryName().length > 0) {
            dto.getConditions().add(Restrict.in("category", dto.getCategoryName()));
        }
        dto.getSorts().add("code=asc");
        List<FirstSubject> list = super.findByCis(dto);

        List<FirstSubjectExport> firstSubjectExports = BeanTransform.copyProperties(list, FirstSubjectExport.class);

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(firstSubjectExports, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<FirstSubjectTemplateExport> template = new ArrayList<>();
//        case "资产类":"负债类":"共同类": "权益类":"成本类":"损益类":
        FirstSubjectTemplateExport excel = new FirstSubjectTemplateExport();
        excel.setCategory("资产类");
        excel.setName( "test1" );
        excel.setRemark("资产类的描述");
        template.add( excel );

        FirstSubjectTemplateExport excel2 = new FirstSubjectTemplateExport();
        excel2.setCategory("负债类" );
        excel2.setName( "test2");
        excel2.setRemark("负债类的描述");
        template.add(excel2);

        FirstSubjectTemplateExport excel3 = new FirstSubjectTemplateExport();
        excel3.setCategory("共同类" );
        excel3.setName( "test3");
        excel3.setRemark("共同类的描述");
        template.add(excel3);

        FirstSubjectTemplateExport excel4 = new FirstSubjectTemplateExport();
        excel4.setCategory("权益类" );
        excel4.setName( "test4");
        excel4.setRemark("权益类的描述");
        template.add(excel4);

        FirstSubjectTemplateExport excel5 = new FirstSubjectTemplateExport();
        excel5.setCategory("成本类" );
        excel5.setName( "test5");
        excel5.setRemark("成本类的描述");
        template.add(excel5);

        FirstSubjectTemplateExport excel6 = new FirstSubjectTemplateExport();
        excel6.setCategory("损益类" );
        excel6.setName( "test6");
        excel6.setRemark("损益类的描述");
        template.add(excel6);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(template, exce);
        return bytes;
    }
}