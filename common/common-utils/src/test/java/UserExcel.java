
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-10 09:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserExcel {
    @ExcelHeader(name = "姓名",notNull = true)
    private String name  ;
    @ExcelHeader(name = "手机")
    private String phone  ;
    @ExcelHeader(name = "年龄")
    private Integer age ;
    @ExcelHeader(name = "创建时间")
    private LocalDateTime createTime;
    @ExcelHeader(name = "日期")
    private LocalDate date ;
    @ExcelHeader(name = "时间")
    private LocalTime time ;
    @ExcelHeader(name = "资产")
    private Double money;
    @ExcelHeader(name = "性别")
    private SexType sexType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }
}
