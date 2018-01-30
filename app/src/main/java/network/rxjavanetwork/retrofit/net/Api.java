package network.rxjavanetwork.retrofit.net;


import java.util.Map;


import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by Administrator on 17.7.31.
 */

public interface Api {

    //通过电话拿到验证码
    @FormUrlEncoded
    @POST("/sms/sendIOS")
    Observable<String> getVerifiCode(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //查询认证达人列表
    @GET("/customer/circle/listOfMaster")
    Observable<String> getCertifiedTalent(@HeaderMap Map<String, String> header);

    //设置密码
    @FormUrlEncoded
    @POST("/customer/user/setPassword")
    Observable<String> setPassword(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //修改密码
    @FormUrlEncoded
    @POST("/customer/user/updatePassword")
    Observable<String> updatePassword(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //登录验证
    @GET("/customer/general/verify")
    Observable<String> isLogin(@HeaderMap Map<String, String> header);

    //注销登录
    @GET("/customer/general/out")
    Observable<String> Logout(@HeaderMap Map<String, String> header);

    //查询别的用户详情
    @FormUrlEncoded
    @POST("/customer/user/userDetail")
    Observable<String> checkUserDetail(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //查询用户个人信息
    @GET("/customer/user/userInfo")
    Observable<String> queryUserData(@HeaderMap Map<String, String> header);


    //查询订单列表
    @FormUrlEncoded
    @POST("/customer/order/list")
    Observable<String> getOrderList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //查询优惠券列表
    @FormUrlEncoded
    @POST("/customer/ticket/list")
    Observable<String> getTicketList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询优惠券类别
    @GET("/customer/ticket/listOfCategory")
    Observable<String> getTicketType(@HeaderMap Map<String, String> header);


    //查询收藏店铺列表
    @FormUrlEncoded
    @POST("/customer/collection/listOfShop")
    Observable<String> getCollectionShopList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询收藏文章列表
    @FormUrlEncoded
    @POST("/customer/collection/listOfNote")
    Observable<String> getCollectionArticleList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //查询U圈时间列表
    @FormUrlEncoded
    @POST("/customer/circle/listByTime")
    Observable<String> getCircleTimeList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询U圈关注列表
    @FormUrlEncoded
    @POST("/customer/circle/listByConcern")
    Observable<String> getCircleFocusList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询U圈人气列表
    @FormUrlEncoded
    @POST("/customer/circle/listByReview")
    Observable<String> getCircleRQList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询U圈附近列表
    @FormUrlEncoded
    @POST("/customer/circle/listByDistance")
    Observable<String> getCircleNearList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //修改用户信息
    @FormUrlEncoded
    @POST("/customer/user/updateUser")
    Observable<String> updateUserData(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //添加用户收货地址
    @FormUrlEncoded
    @POST("/customer/user/addReceivingAddress")
    Observable<String> addReceveAddress(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询用户标签
    @FormUrlEncoded
    @POST("/customer/userTag/tagList")
    Observable<String> getUserTag(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //图片上传
    @Multipart
    @POST("/management/file/img/upload")
    Observable<String> imgUpload(@HeaderMap Map<String, String> header, @Part("data") String des, @PartMap Map<String, RequestBody> params);

    //新增标签列表
    @FormUrlEncoded
    @POST("/customer/userTag/addUserTag")
    Observable<String> addUserTag(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //通过手机验证码修改登录密码
    @FormUrlEncoded
    @POST("/sms/verifyForUser")
    Observable<String> verifyUpdatePsdCode(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //解除手机绑定验证
    @FormUrlEncoded
    @POST("/customer/user/relieveValidate")
    Observable<String> unBindPhone(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //绑定新手机
    @FormUrlEncoded
    @POST("/customer/user/updatePhone")
    Observable<String> bindPhone(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询系统消息列表
    @GET("/customer/message/listOfSystem")
    Observable<String> getSysMessageList(@HeaderMap Map<String, String> header);


    //查询系统消息详情列表
    @FormUrlEncoded
    @POST("/customer/message/detailOfSystem")
    Observable<String> getSysMessageDetailList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询用户消息详情列表
    @FormUrlEncoded
    @POST("/customer/message/detail")
    Observable<String> getUserMessageDetailList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询用户消息列表
    @FormUrlEncoded
    @POST("/customer/message/list")
    Observable<String> getUserMessageList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //添加用户消息
    @FormUrlEncoded
    @POST("/customer/message/addMessage")
    Observable<String> addMessage(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //查询U圈评论列表
    @FormUrlEncoded
    @POST("/customer/circle/listOfReview")
    Observable<String> getUcirclePlList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //添加评论消息
    @FormUrlEncoded
    @POST("/customer/circle/addReview")
    Observable<String> addPlList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //添加点赞
    @FormUrlEncoded
    @POST("/customer/circle/addZan")
    Observable<String> addZan(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //取消点赞
    @FormUrlEncoded
    @POST("/customer/circle/deleteZan")
    Observable<String> cancleZan(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //添加关注
    @FormUrlEncoded
    @POST("/customer/circle/addConcern")
    Observable<String> addFocus(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //取消关注
    @FormUrlEncoded
    @POST("/customer/circle/deleteConcern")
    Observable<String> cancleFocus(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //发布友圈
    @FormUrlEncoded
    @POST("/customer/circle/add")
    Observable<String> addUcircle(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //删除友圈
    @FormUrlEncoded
    @POST("/customer/circle/delete")
    Observable<String> deleteUcircle(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //订单评价
    @FormUrlEncoded
    @POST("/customer/order/addComment")
    Observable<String> addOrderComment(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询用户订单详情
    @FormUrlEncoded
    @POST("/customer/order/detail")
    Observable<String> queryOrderDetail(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //足迹列表
    @FormUrlEncoded
    @POST("/customer/footsteps/list")
    Observable<String> footList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //获取银行卡列表
    @GET("/customer/bankcard/list")
    Observable<String> getBankCardList(@HeaderMap Map<String, String> header);


    //获取用户银行卡列表

    @GET("/customer/bankcard/listByUser")
    Observable<String> getUserBankCardList(@HeaderMap Map<String, String> header);  //获取用户银行卡列表

    //添加银行卡
    @FormUrlEncoded
    @POST("/customer/bankcard/addBankcard")
    Observable<String> addBankCard(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //删除银行卡
    @FormUrlEncoded
    @POST("/customer/bankcard/deleteBankcard")
    Observable<String> deleteBankCard(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //验证（设置重置）支付密码验证码
    @FormUrlEncoded
    @POST("/customer/pay/validateCode")
    Observable<String> checkPayPsdCode(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);

    //（设置修改）支付密码
    @FormUrlEncoded
    @POST("/customer/pay/updatePaypwd")
    Observable<String> updatePayPsd(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //验证原支付密码
    @FormUrlEncoded
    @POST("/customer/pay/validatePassword")
    Observable<String> verifyOldPassword(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //充值卡充值
    @FormUrlEncoded
    @POST("/customer/pay/addRechargeByCard")
    Observable<String> addRechargeByCard(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //获取到资金流水
    @FormUrlEncoded
    @POST("/customer/pay/listOfCapitalFlow")
    Observable<String> listOfCapitalFlow(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //用户资金
    @GET("/customer/pay/userCapital")
    Observable<String> getUserCash(@HeaderMap Map<String, String> header);



    //查询首页类别
    @GET("/customer/ticket/listOfCategory")
    Observable<String> queryHomeCategory(@HeaderMap Map<String, String> header);



    //获取到资金流水
    @FormUrlEncoded
    @POST("/customer/category/listShowShopNum")
    Observable<String> getAllClass(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


   //获取子集地区列表
    @FormUrlEncoded
    @POST("/customer/address/list")
    Observable<String> getAddressList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);



    //获取子集地区列表
    @FormUrlEncoded
    @POST("/customer/areamark/list")
    Observable<String> getAddressShopNumList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //查询推荐商圈
    @FormUrlEncoded
    @POST("/customer/areamark/listOfRecommend")
    Observable<String> getRecommendBussiness(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //获取城市地区列表
    @GET("/customer/city/list")
    Observable<String> getCityList(@HeaderMap Map<String, String> header);


    //查询店铺列表
    @FormUrlEncoded
    @POST("/customer/shop/list")
    Observable<String> getShopList(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //修改默认银行卡
    @FormUrlEncoded
    @POST("/customer/bankcard/updateDefaultBankcard")
    Observable<String> updateDefaultBankcard(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);
    //查询U圈详情
    @FormUrlEncoded
    @POST("/customer/circle/detail")
    Observable<String> uDatailed(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);


    //分享成功统计
    @FormUrlEncoded
    @POST("/customer/userShare/addUserShare")
    Observable<String> addUserShare(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);



    //获取签名信息(原生支付宝)
    @FormUrlEncoded
    @POST("/alipay/sign")
    Observable<String> getAliPaySign(@HeaderMap Map<String, String> header, @FieldMap Map<String, String> map);





}
