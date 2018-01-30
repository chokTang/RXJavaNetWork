package network.rxjavanetwork.retrofit.net;


import android.content.Context;



import java.util.HashMap;
import java.util.Map;


import network.rxjavanetwork.retrofit.BaseReq;
import network.rxjavanetwork.retrofit.ZZConfig;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Administrator on 17.7.31.
 */

public class Req extends BaseReq {


    protected int page = 1; // 起始页码
    boolean update = true; // 是否为更新
    private int pageSize = ZZConfig.PageSize; // 默认分页大小
    private String keyNo = "page"; // 默认页码参数
    private String keySize = "rows"; // 默认页数


    Context mcontext;
    public static Req req;
    Map<String, String> header = new HashMap<>();
    private Api api;

    private Req(Context context) {
        api = mRetrofit.create(Api.class);
        mcontext = context;
//        header.put(ZZUserHelper.LG_SIGN, ZZUserHelper.getSign(context));
        header.put("Accept", "application/json");
        header.put("X-Requested-With", "XMLHttpRequest");
//        header.put("User-Agent", ZZDevice.getPhoneName() + ZZDevice.getSystemCOde());
    }


    public static Req req(Context context) {
        if (req == null) {
            synchronized (Req.class) {
                req = new Req(context);
            }
        }
        return req;
    }


    public Observable<String> getVerifiCode(Map<String, String> map) {//根据手机号拿到登录验证码
        return api.getVerifiCode(header, map);
    }

    public Observable<String> getCertifiedTalent() {//查询认证达人列表
        return api.getCertifiedTalent(header);
    }

    public Observable<String> setPassword(Map<String, String> map) {//设置密码
        return api.setPassword(header, map);
    }

    public Observable<String> updatePassword(Map<String, String> map) {//修改密码
        return api.updatePassword(header, map);
    }

    public Observable<String> isLogin() {//登录验证
        return api.isLogin(header);
    }

    public Observable<String> Logout() {//注销登录
        return api.Logout(header);
    }

    public Observable<String> checkUserDetail(Map<String, String> map) {//查询用户详情
        return api.checkUserDetail(header, map);
    }

    public Observable<String> queryUserData() {//查询用户个人信息
        return api.queryUserData(header);
    }

    public Observable<String> getOrderList(Map<String, String> map) {//查询订单列表
        return api.getOrderList(header, map);
    }

    public Observable<String> getTicketList(Map<String, String> map) {//查询优惠券列表
        return api.getTicketList(header, map);
    }

    public Observable<String> getTicketType() {//查询优惠券类别
        return api.getTicketType(header);
    }

    public Observable<String> getCollectionShopList(Map<String, String> map) {//查询收藏店铺列表
        return api.getCollectionShopList(header, map);
    }

    public Observable<String> getCollectionArticleList(Map<String, String> map) {//查询收藏文章列表
        return api.getCollectionArticleList(header, map);
    }

    public Observable<String> getCircleTimeList(Map<String, String> map) {//查询U圈时间列表
        return api.getCircleTimeList(header, map);
    }

    public Observable<String> getCircleFocusList(Map<String, String> map) {//查询U圈关注列表
        return api.getCircleFocusList(header, map);
    }

    public Observable<String> getCircleRQList(Map<String, String> map) {//查询U圈人气列表
        return api.getCircleRQList(header, map);
    }

    public Observable<String> getCircleNearList(Map<String, String> map) {//查询U圈附近列表
        return api.getCircleNearList(header, map);
    }

    public Observable<String> updateUserData(Map<String, String> map) {//修改用户信息
        return api.updateUserData(header, map);
    }

    public Observable<String> addReceveAddress(Map<String, String> map) {//添加用户收货地址
        return api.addReceveAddress(header, map);
    }

    public Observable<String> imgUpload(String des, Map<String, RequestBody> params) {//上传单个图片
        return api.imgUpload(header, des, params);
    }

    public Observable<String> getUserTag(Map<String, String> map) {//查询用户标签
        return api.getUserTag(header, map);
    }

    public Observable<String> addUserTag(Map<String, String> map) {//新增用户标签
        return api.addUserTag(header, map);
    }

    public Observable<String> verifyUpdatePsdCode(Map<String, String> map) {//通过手机验证码修改登录密码
        return api.verifyUpdatePsdCode(header, map);
    }

    public Observable<String> unBindPhone(Map<String, String> map) {//解除手机绑定
        return api.unBindPhone(header, map);
    }

    public Observable<String> bindPhone(Map<String, String> map) {//新手机手机绑定
        return api.bindPhone(header, map);
    }

    public Observable<String> getSysMessageList() {//查询系统消息列表
        return api.getSysMessageList(header);
    }

    public Observable<String> getSysMessageDetailList(Map<String, String> map) {//查询系统消息详情
        return api.getSysMessageDetailList(header, map);
    }

    public Observable<String> getUserMessageList(Map<String, String> map) {//查询用户消息列表
        return api.getUserMessageList(header, map);
    }


    public Observable<String> getUserMessageDetailList(Map<String, String> map) {//查询用户消息详情列表
        return api.getUserMessageDetailList(header, map);
    }


    public Observable<String> addMessage(Map<String, String> map) {//添加用户消息
        return api.addMessage(header, map);
    }


    public Observable<String> getUcirclePlList(Map<String, String> map) {//获取U圈评论列表
        return api.getUcirclePlList(header, map);
    }

    public Observable<String> addPlList(Map<String, String> map) {//添加评论消息
        return api.addPlList(header, map);
    }

    public Observable<String> addZan(Map<String, String> map) {//点赞
        return api.addZan(header, map);
    }

    public Observable<String> cancleZan(Map<String, String> map) {//取消点赞
        return api.cancleZan(header, map);
    }

    public Observable<String> addFocus(Map<String, String> map) {//添加关注
        return api.addFocus(header, map);
    }


    public Observable<String> cancleFocus(Map<String, String> map) {//取消关注
        return api.cancleFocus(header, map);
    }

    public Observable<String> addUcircle(Map<String, String> map) {//发布友圈
        return api.addUcircle(header, map);
    }

    public Observable<String> deleteUcircle(Map<String, String> map) {//删除友圈
        return api.deleteUcircle(header, map);
    }

    public Observable<String> addOrderComment(Map<String, String> map) {//添加订单评论
        return api.addOrderComment(header, map);
    }

    public Observable<String> queryOrderDetail(Map<String, String> map) {//获取订单详情
        return api.queryOrderDetail(header, map);
    }

    public Observable<String> footList(Map<String, String> map) {//足迹列表
        return api.footList(header, map);
    }

    public Observable<String> getBankCardList() {//获取银行卡列表
        return api.getBankCardList(header);
    }

    public Observable<String> getUserBankCardList() {//获取用户银行卡列表
        return api.getUserBankCardList(header);
    }

    public Observable<String> addBankCard(Map<String, String> map) {//添加用户银行卡列表
        return api.addBankCard(header, map);
    }

    public Observable<String> deleteBankCard(Map<String, String> map) {//删除用户银行卡列表
        return api.deleteBankCard(header, map);
    }

    public Observable<String> checkPayPsdCode(Map<String, String> map) {//验证（设置重置）支付密码验证码
        return api.checkPayPsdCode(header, map);
    }


    public Observable<String> updatePayPsd(Map<String, String> map) {//设置修改支付密码
        return api.updatePayPsd(header, map);
    }

    public Observable<String> verifyOldPassword(Map<String, String> map) {//验证原支付密码
        return api.verifyOldPassword(header, map);
    }


    public Observable<String> addRechargeByCard(Map<String, String> map) {//充值卡充值
        return api.addRechargeByCard(header, map);
    }

    public Observable<String> listOfCapitalFlow(Map<String, String> map) {//获取到资金流水
        return api.listOfCapitalFlow(header, map);
    }

    public Observable<String> getUserCash() {//用户资金接口
        return api.getUserCash(header);
    }


    public Observable<String> queryHomeCategory() {//查询首页类别
        return api.queryHomeCategory(header);
    }

    public Observable<String> getAllClass(Map<String, String> map) {//查询类目列表(显示店铺数量)
        return api.getAllClass(header, map);
    }

    public Observable<String> getAddressList(Map<String, String> map) {//获取子集地区列表
        return api.getAddressList(header, map);
    }


    public Observable<String> getAddressShopNumList(Map<String, String> map) {//获取城市店铺列表
        return api.getAddressShopNumList(header, map);
    }


    public Observable<String> getRecommendBussiness(Map<String, String> map) {//推荐商圈店铺数量列表
        return api.getRecommendBussiness(header, map);
    }

    public Observable<String> getTopAddressList() {//获取顶部集地区列表
        return api.getCityList(header);
    }

    public Observable<String> getShopList(Map<String, String> map) {//查询店铺列表
        return api.getShopList(header, map);
    }

    public Observable<String> updateDefaultBankcard(Map<String, String> map) {//修改默认银行卡
        return api.updateDefaultBankcard(header, map);
    }

    public Observable<String> uDatailed(Map<String, String> map) {//查询U圈详情
        return api.uDatailed(header, map);
    }

    public Observable<String> addUserShare(Map<String, String> map) {//添加分享成功统计
        return api.addUserShare(header, map);
    }

    public Observable<String> getAliPaySign(Map<String, String> map) {//获取支付宝签名
        return api.getAliPaySign(header, map);
    }

    @Override
    protected String Service() {
        return ZZConfig.SERVICE;
    }

    @Override
    protected String Host() {
        return ZZConfig.HOST;
    }
}
