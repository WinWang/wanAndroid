package com.winwang.wanandroid.http;

import com.winwang.wanandroid.model.BannerBean;
import com.winwang.wanandroid.model.BannerData;
import com.winwang.wanandroid.model.BaseModel;
import com.winwang.wanandroid.model.FeedArticleListData;
import com.winwang.wanandroid.model.KnowledgeHierarchyData;
import com.winwang.wanandroid.model.TokenBean;
import com.winwang.wanandroid.model.UpdateBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 2018/4/11.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("api")
    Flowable<BannerBean> queryHomeData(@Field("accessToken") String token,
                                       @Field("actionName") String actionName,
                                       @Field("payload") String payload,
                                       @Field("timestamp") long timaStamp,
                                       @Field("sign") String sign,
                                       @Field("from") String from);

    @FormUrlEncoded
    @POST("get-access-token")
    Flowable<TokenBean> getToken(@Field("mobileType") String type,
                                 @Field("mobileId") String mobileID);


    @FormUrlEncoded
    @POST("api")
    Flowable<ResponseBody> queryData(@Field("accessToken") String token,
                                     @Field("actionName") String actionName,
                                     @Field("payload") String payload,
                                     @Field("timestamp") long timaStamp,
                                     @Field("sign") String sign,
                                     @Field("from") String from);


    @GET("mobile/android/last-version")
    Flowable<UpdateBean> getUpdate(@Query("packageName") String name);


    /**********玩AndroidAPI*********************/
    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Flowable<BaseModel<List<BannerData>>> getBannerData();


    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    Flowable<BaseModel<FeedArticleListData>> getFeedArticleList(@Path("num") int num);


    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    Flowable<BaseModel<List<KnowledgeHierarchyData>>> getKnowledge();


}
