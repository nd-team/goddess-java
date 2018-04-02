package com.bjike.goddess.mytest.action.mytest;

import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.mytest.bo.CarBO;
import com.bjike.goddess.mytest.bo.StudentBO;
import com.bjike.goddess.mytest.entity.Car;
import com.bjike.goddess.mytest.entity.Student;
import com.bjike.goddess.mytest.entity.Teacher;
import com.bjike.goddess.mytest.to.StudentTO;
import com.bjike.goddess.mytest.to.TeacherTO;
import com.bjike.goddess.mytest.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试用类 学生
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 01:02 ]
 * @Description: [ 测试用类 学生 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("student")
public class StudentAction {


    @GetMapping("test")
    public Result get() {
        Student student = new Student();
        Teacher teacher = new Teacher();
        teacher.setName("te");
        student.setTeacher(teacher);
        student.setName("st");
        Car car1 = new Car();
        car1.setColor("red");
        Car car2 = new Car();
        car2.setColor("black");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
//        List<CarBO> list = BeanTransform.copyProperties(cars, CarBO.class);
//        System.out.println(CarBO.class.getTypeName());
//        System.out.println(CarBO.class.getName());
//        System.out.println(CarBO.class.getSimpleName());
//        System.out.println(list);
        teacher.setCars(cars);
        StudentBO bo = BeanTransform.copyProperties(student, StudentBO.class);

        return new ActResult("success",BeanTransform.copyProperties(bo, StudentVO.class));
    }

    @GetMapping("get")
    public Result set() {
        StudentTO studentTO = new StudentTO();
        studentTO.setName("noon");
        TeacherTO teacherTO = new TeacherTO();
        teacherTO.setName("shell");
        Car car1 = new Car();
        car1.setColor("red");
        Car car2 = new Car();
        car2.setColor("black");
        List list = new ArrayList();
        list.add(car1);
        list.add(car2);
        teacherTO.setCarTOS(list);
        studentTO.setTeacherTO(teacherTO);
        Student student = BeanTransform.copyProperties(studentTO,Student.class);
        System.out.println(student);
        return new ActResult("success");
    }

    @GetMapping("save")
    public Result save() {

        return new ActResult();
    }
}