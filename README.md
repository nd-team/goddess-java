# goddess-java

# 1.打包步骤:
    1:执行 gradle build -Ppro -xtest 打包 (profile/pro的配置文件)
    2:上传前删除consumer多余包(避免上传多余的包,上传速度慢)
    3:添加命令(重启电脑生效):
        vi ~/.zshrc
        alias del='7z d  `ls | grep .jar$`  BOOT-INF/lib'
        vi /etc/profile 
        alias del='7z d `ls | grep .jar$`  BOOT-INF/lib'
    4:执行 git add 之前先执行 del 命令
    5:执行 gradle clean 清除jar,本地编译器读取的应该是resources下的配置文件,而不是读取profile/pro下的配置文件
    
# 2.拦截器使用: 添加自己的拦截器
    1:在 consumer config 下添加新建扫描拦截器类 CustomIntercept
       并实现  com.bjike.goddess.common.consumer.config.Interceptor接口 重写customerInterceptors方法
    2:添加注解  @Component 扫描注入
      例:
        @Component
        public class CustomIntercept implements Interceptor {
         @Override
            public List<HIInfo> customerInterceptors() {
                    MyInterceptor myInterceptor = new MyInterceptor(); //自定义拦截器
                    HIInfo hiInfo = new HIInfo(smoothInterceptor, "/**");
                    Arrays.asList(hiInfo)
            }
        }
    
# 3.断路器使用(避免程序异常过多导致功能崩溃):
    1: 在 consumer config包下 添加新建扫描类 CustomHystrixCommand
       并继承 com.bjike.goddess.common.consumer.aspect.HystrixCommandAdvice
    2:添加注解 @Aspect @Component 扫描注入
    
    例: @Aspect
        @Component
        public class CustomHystrixCommand extends HystrixCommandAdvice{}
    注:默认5秒内失败20个,断路器会打开
    
# 4.限流器使用(避免恶意频繁的数据访问)
    1:添加拦截器,见拦截器使用方法
   
    例:拦截器类
    @Component
    public class CustomIntercept implements Interceptor {
     @Override
        public List<HIInfo> customerInterceptors() {
                SmoothBurstyInterceptor smoothInterceptor = new SmoothBurstyInterceptor(100, SmoothBurstyInterceptor.LimitType.DROP);
                HIInfo smoothInfo = new HIInfo(smoothInterceptor, "/**");
                Arrays.asList(smoothInfo)
        }
    }
    注:实际参数说明见: com.bjike.goddess.common.consumer.interceptor.limit.SmoothBurstyInterceptor 类
    
# 5.幂等操作(避免重复提交数据,主要针对put,patch,delete,post请求)
    1:在Action类上加上@DefaultProperties即可开启
    例:
    @DefaultProperties(开启幂等)
    @RequestMapping("test")
    public class TestAct {}

# 6.文件上传
    1:引入storageAPI
    2:因上传文件使用的不是dubbo协议,而是hessian,所以要在 consumer build.gradle配置文件下 添加hessian依赖   
        compile group: 'com.caucho', name: 'hessian', version: "$hessian_version"    
    3:如果涉及文件上传,那么Action类必须继承 com.bjike.goddess.common.consumer.action.BaseFileAction 
        以便通过getInputStreams()获取到上传文件的流,如果不需要文件上传,只是获取文件列表功能,则可以不继承BaseFileAction  
    4:添加文件上传登录拦截器,见拦截器使用方法
        例:拦截器
            @Component
            public class CustomIntercept implements Interceptor {
                @Autowired
                private StorageUserAPI storageUserAPI;
                @Override
                public List<HIInfo> customerInterceptors() {
                    // */file 为需要拦截登录的action操作
                    HIInfo storageInfo = new HIInfo(new StorageIntercept(storageUserAPI), "*/file");
                    return Arrays.asList(storageInfo);
                }
            }
    注: storageAPI所有功能都必须包含:storageToken,path这两个参数 
    
 # 7.登录拦截
     1.使用@LoginAuth注解.注解在类上面,代表整个类都校验登录,在方法上注解代表该方法需要校验登录
    
     2.添加登录拦截器,见拦截器使用方法
            例:拦截器
                @Component
                public class CustomIntercept implements Interceptor {
                    @Autowired
                    private UserAPI userAPI;
                    @Override
                    public List<HIInfo> customerInterceptors() {
                        HIInfo loginInfo = new HIInfo(new StorageIntercept(userAPI), "/**");
                        return Arrays.asList(loginInfo);
                    }
                }
     例:
         @LoginAuth
         @RestController
         @RequestMapping("test")
         public class TestAct {
         }
    
    
    
