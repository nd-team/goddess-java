package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.bo.FirstSubjectBO;
import com.bjike.goddess.financeinit.dto.FirstSubjectDTO;
import com.bjike.goddess.financeinit.entity.FirstSubject;
import com.bjike.goddess.financeinit.to.FirstSubjectTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<FirstSubjectBO> listFirstSubject(FirstSubjectDTO firstSubjectDTO) throws SerException {
        List<FirstSubject> list = super.findByCis(firstSubjectDTO, true);

        return BeanTransform.copyProperties(list, FirstSubjectBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FirstSubjectBO addFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        String category = firstSubjectTO.getCategory();
        if(StringUtils.isBlank( category )){
            throw  new SerException("级别所属类别不能为空哦");
        }
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("name",firstSubjectTO.getName() ) );
        dto.getConditions().add(Restrict.eq("category",firstSubjectTO.getCategory() ) );
        Long count = super.count( dto );
        if (count>0){
            throw new SerException("该级别所属类别下的一级类别名已经存在，不可以再填");
        }

        String code = getCodeGenerate( category );

        FirstSubject firstSubject = BeanTransform.copyProperties(firstSubjectTO,FirstSubject.class,true);
        firstSubject.setCode(code);
        firstSubject.setCreateTime(LocalDateTime.now());
        super.save( firstSubject );
        return BeanTransform.copyProperties(firstSubject, FirstSubjectBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FirstSubjectBO editFirstSubject(FirstSubjectTO firstSubjectTO) throws SerException {
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("name",firstSubjectTO.getName() ) );
        dto.getConditions().add(Restrict.eq("category",firstSubjectTO.getCategory() ) );
        FirstSubject first = super.findOne( dto );
        if (first != null && firstSubjectTO.getCode().equals(first.getCode()) ){
            throw new SerException("该级别所属类别下的一级类别名已经存在，不可以修改成这个名字");
        }

        FirstSubject firstSubject = BeanTransform.copyProperties(firstSubjectTO,FirstSubject.class,true);
        FirstSubject dbFirstSubject = super.findById( firstSubjectTO.getId() );

        dbFirstSubject.setName( firstSubject.getName() );
        dbFirstSubject.setCategory( firstSubject.getCategory() );
        dbFirstSubject.setRemark( firstSubject.getRemark() );
        dbFirstSubject.setModifyTime(LocalDateTime.now());
        super.update( dbFirstSubject );
        return BeanTransform.copyProperties(firstSubject, FirstSubjectBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteFirstSubject(String id) throws SerException {
        super.remove( id );
    }

    @Override
    public FirstSubjectBO getFirstSubject(String firstSubjectName) throws SerException {
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("name",firstSubjectName ) );
        FirstSubject first = super.findOne( dto );

        return BeanTransform.copyProperties(first, FirstSubjectBO.class);
    }

    /**
     * 生成编号
     * @param categoryName
     * @return
     * @throws SerException
     */
    public String getCodeGenerate( String categoryName ) throws SerException {
        String code = "";
        FirstSubjectDTO dto = new FirstSubjectDTO();
        dto.getConditions().add(Restrict.eq("category", categoryName));
        dto.getSorts().add(" code=desc");
        List<FirstSubject> firstSubjectList = super.findByCis( dto );
        if( firstSubjectList != null && firstSubjectList.size()>0 ){
            code = firstSubjectList.get(0).getCode();
            int num  = Integer.parseInt(code.trim())+1;
            code = String.valueOf(num) ;
        }else{
            switch (categoryName){
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
}