/**
 * 实体对象映射列属性配置说明
 1. 每个映射对象必须继承自 BaseEntity
 2. 每个映射对象必须有相应的生成数据库注释 comment(用columnDefinition标识必须指定数据类型(varchar),有长度的数据类型指明长度,不明确长度暂用255)
 3. columnDefinition 标识内应与数据库关键字规范一致全部大写
 4. 任何字段数值非负数 必须为 unsigned 类型
 unsigned 类型有:
      TINYINT           1 字节    (-128，127)                                    (0，255) 小整数值

        SMALLINT        2 字节    (-32 768，32 767)                           (0，65 535) 大整数值

        MEDIUMINT     3 字节    (-8 388 608，8 388 607)                  (0，16 777 215) 大整数值

        INT或INTEGER  4 字节    (-2 147 483 648，2 147 483 647)     (0，4 294 967 295) 大整数值
 */

@Entity
@Table(name = "test_entity")
public class XXX extends BaseEntity{

    /**
     * 时间类型必须设置为对应的dateTime类型
     */
    @Column(columnDefinition = "DATETIME COMMENT '连接时间'")
    private LocalDateTime accessTime;

    /**
     * 整形 (unsigned tinyint 代表0-255 ,年龄不会超过255) //否则默认用int
     */
    @Range(min = 0, max = 20) //取范围
    @Column(columnDefinition = "TINYINT COMMENT '年龄'")
    private Integer age;

    /**
     * 浮点型
     */
    @Column(columnDefinition = " DECIMAL COMMENT '身高'")
    private Float height;

    /**
     * 双精浮点(precision=5, scale=2 保留两小数)
     */
    @Column(columnDefinition = " DECIMAL(5,2) COMMENT '重量'")
    private Double weight;

    /**
     * 枚举 (设置默认值 default 0 一定要带insertable = false) insertable标签开启代表插入无效
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '用户状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 布尔类型 注意布尔类型命名
     */
    @Column(name ="is_expired", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '过期'", nullable = false, insertable = false)
    private Boolean expired;

    /**
     * 字符串类型(默认varchar,最大为5000, 超过5000字符指定text类型)
     */
    @Column(columnDefinition = "VARCHAR(12) COMMENT '姓名'")
    private String name;
    /**
     * date
     */
    @Column(columnDefinition = "DATE  COMMENT '日期'")
    private LocalDate date;


    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
