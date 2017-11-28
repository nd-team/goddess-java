package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.workhandover.bo.workTimeSpecificationBO;
import com.bjike.goddess.workhandover.dto.workTimeSpecificationDTO;
import com.bjike.goddess.workhandover.entity.workTimeSpecification;
import com.bjike.goddess.workhandover.excel.workTimeSpecificationExport;
import com.bjike.goddess.workhandover.to.workTimeSpecificationTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 工作交接时间规范业务实现
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 工作交接时间规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workhandoverSerCache")
@Service
public class workTimeSpecificationSerImpl extends ServiceImpl<workTimeSpecification, workTimeSpecificationDTO> implements workTimeSpecificationSer {

    @Override
    public List<workTimeSpecificationBO> findworkTimeSpecification(workTimeSpecificationDTO dto) throws SerException {
        dto.getSorts ().add ( "createTime=desc" );
        List<workTimeSpecification> work = super.findByPage ( dto );
        List<workTimeSpecificationBO> bo = BeanTransform.copyProperties ( work, workTimeSpecificationBO.class );
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public workTimeSpecificationBO insertworkTimeSpecification(workTimeSpecificationTO to) throws SerException {
        workTimeSpecification work = BeanTransform.copyProperties ( to, workTimeSpecification.class, true );
        work.setCreateTime ( LocalDateTime.now () );
        super.save ( work );
        return BeanTransform.copyProperties ( work, workTimeSpecificationBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public workTimeSpecificationBO editworkTimeSpecification(workTimeSpecificationTO to) throws SerException {
        workTimeSpecification work = super.findById ( to.getId () );
        LocalDateTime creatTime = work.getCreateTime ();
        work.setModifyTime ( LocalDateTime.now () );
        work.setCreateTime ( creatTime );
        super.update ( work );
        return BeanTransform.copyProperties ( work, workTimeSpecificationBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeworkTimeSpecification(String id) throws SerException {
        super.remove ( id );
    }

    @Override
    public List<String> findWorkHandoverReason() throws SerException {
        Set<String> set = new HashSet<> (  );
        List<workTimeSpecification> workTimeSpecifications = super.findAll ();
        for(workTimeSpecification workTimeSpecification:workTimeSpecifications){
            set.add ( workTimeSpecification.getWorkHandoverReason () );
        }
        return new ArrayList<> ( set );
    }

    @Override
    public workTimeSpecificationBO importExcel(List<workTimeSpecificationTO> to) throws SerException {
        List<workTimeSpecification> list = BeanTransform.copyProperties ( to, workTimeSpecification.class, true );
        list.stream ().forEach ( str -> {
            str.setCreateTime ( LocalDateTime.now () );
            str.setModifyTime ( LocalDateTime.now () );
        } );
        super.save ( list );

        workTimeSpecificationBO bo = BeanTransform.copyProperties ( new workTimeSpecification (), workTimeSpecificationBO.class );
        return bo;
    }

    @Override
    public byte[] exportExcel(workTimeSpecificationDTO dto) throws SerException {
        List<workTimeSpecification> list = super.findByCis ( dto );

        List<workTimeSpecificationExport> exports = new ArrayList<> ();
        list.stream ().forEach ( str -> {
            workTimeSpecificationExport excel = BeanTransform.copyProperties ( str, workTimeSpecificationExport.class,"createTime","modifyTime");
            exports.add ( excel );
        } );
        Excel excel = new Excel ( 0, 2 );
        byte[] bytes = ExcelUtil.clazzToExcel ( exports, excel );
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        return new byte[0];
    }

}