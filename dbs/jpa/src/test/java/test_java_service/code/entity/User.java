package test_java_service.code.entity;

import com.bjike.goddess.dbs.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by huanghuanlai on 16/4/28.
 */
@Entity
@Table(name="test_user")
public class User extends BaseEntity {

    @Column(unique = true, length = 12)
    private String username;
    private String password;
    @OrderBy(value = "age desc ")
    private Integer age;
    @Column(name = "money", nullable = true, precision = 12, scale = 2)//12位数字可保留两位小数，可以为空
    private Double money;
    private Float height;
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "dateTime")//指定数据库类型
    private LocalDateTime accessTime = LocalDateTime.now();
    @Column(columnDefinition = "TINYINT(1)")//指定数据库类型
    private Boolean superMan;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "group_id")
    private UserGroup group;

     @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @OrderBy(value = "seq ASC")
    private Set<UserInterest> interestSet;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public Boolean getSuperMan() {
        return superMan;
    }

    public void setSuperMan(Boolean superMan) {
        this.superMan = superMan;
    }

//    public UserInfo getInfo() {
//        return info;
//    }
//
//    public void setInfo(UserInfo info) {
//        this.info = info;
//    }

    public Set<UserInterest> getInterestSet() {
        return interestSet;
    }

    public void setInterestSet(Set<UserInterest> interestSet) {
        this.interestSet = interestSet;
    }
}
