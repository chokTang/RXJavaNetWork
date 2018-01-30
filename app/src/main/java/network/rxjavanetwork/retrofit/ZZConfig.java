package network.rxjavanetwork.retrofit;

/**
 * 配置文件，该文件一定会被配置，否则编译将不予通过
 * Created by Zhang on 2016/8/5.
 */
public class ZZConfig {

    //     api域名设置
    public static final String HOST = "";
//    public static final String SERVICE= "http://120.76.201.182:8090"; // 域名
    public static final String SERVICE = "http://customer.uqudmall.com"; // 域名

    //APP:http://www.uqudmall.com（优先） 或者 http://customer.uqudmall.comand


    public static final String SERVICE1 = "http://sso"; // 域名
    public static final String SERVICE2 = "http://user"; // 域名
    public static final String SERVICE3 = "http://rest"; // 域名
    public static final String SERVICE4 = "http://www"; // 域名


//    public static final String HOST = ".weicai310.com/"; // 域名
//    public static final String SERVICE1 = "http://sso-demo"; // 域名
//    public static final String SERVICE2 = "http://user-demo"; // 域名
//    public static final String SERVICE3 = "http://rest-demo"; // 域名
//    public static final String SERVICE4 = "http://www-demo"; // 域名

    //分页大小
    public static final int PageSize = 10;

    // 是否为调试模式
    public static final boolean IS_DEBUG = true;

//    // 显示的背景图片，也是页面数量的控制，最后一页点击跳转HomeActivity
//    public static final int[] SPLASH_IMAGE_RESOURCE = {R.mipmap.splash_one, R.mipmap.splash_two, R.mipmap.splash_three};

    // 是否忽略版本更新（即是否加入版本号识别）
    public static final boolean FIRST_NO_VERSION = true;

    // 是否有推送
    public static final boolean ALLOW_PUSH = true;

    // 是否有统计
    public static final boolean ALLOW_ANALYSIS = true;

    // 微信分享appId
    public static final String SHARE_WEIXIN_APP_ID = "wx4b2386f3732c7493";

    // 微信分享appKey
    public static final String SHARE_WEIXIN_APP_KEY = "2ed5b644260f7bb35be18c91bcff1c62";

    // QQ分享appId
    public static final String SHARE_QQ_APP_ID = "1106575891";

    // QQ分享appKey
    public static final String SHARE_QQ_APP_KEY = "lMNAQUFJyzZnXmg0";

    // Sina分享appId
    public static final String SHARE_SINA_APP_ID = "666630868";

    // Sina分享appKey
    public static final String SHARE_SINA_APP_KEY = "d3e33ad6b390c25d1c79c6e9f1473b68";

    public static final String BUGLY_APP_ID = "08e34ac8fa";

    public static final String PING_API_KEY = "app_jTO0S0PmzL4O4ez5";

}
