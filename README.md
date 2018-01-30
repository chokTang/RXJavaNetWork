# RXJavaNetWork

Step1.在项目gradle中添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step2.在app的gradle中添加依赖
dependencies {
	        compile 'com.github.chokTang:RXJavaNetWork:v1.0.0'
	}
 
Step3.您需要请求的Activity继承MainActivity并且实现NetworkCallback接口，fragment也是实现networkCallback接口
public class NetWorkActivity extends MainActivity implements MainActivity.NetworkCallback{
    @Override
    public void onNetworkCallback(String s, int i) {

    }
 
这里的参数s是返回的json字符串，i是你请求时候对应的请求码
 
Step4.在生命周期中oncreate调用该接口

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindCallback(this);
    }
    
Step5 数据请求

 launchRequest（context，Observable ，请求码）；
 
 这里的obervable不清楚的查看一下rxjava相关
    
    
 致此，网络请求以及数据返回都完成了！   
