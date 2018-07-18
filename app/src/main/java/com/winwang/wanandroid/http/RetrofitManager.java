package com.winwang.wanandroid.http;

import org.reactivestreams.Publisher;

import cn.droidlover.xdroidmvp.net.IModel;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RetrofitManager {
    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWNHOSTEXCEPTION = "网络异常，请检查您的网络状态";

    private ApiService apiService;

    public ApiService getApiService(String BASE_URL) {
        apiService = XApi.getInstance().getRetrofit(BASE_URL + "/", true).create(ApiService.class);
        return apiService;
    }


    public ApiService getApiService() {
        apiService = XApi.getInstance().getRetrofit(URLConfig.WANURLHOST, true).create(ApiService.class);
        return apiService;
    }

    /**
     * HttpUtil实例
     */
    private static RetrofitManager INSTANCE;

    /**
     * 获取HttpUtil实例 ,单例模式
     */
    public static RetrofitManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitManager();
                }
            }
        }
        return INSTANCE;
    }

    public <T extends IModel> FlowableTransformer<T, T> refreshToken() {

        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.flatMap(new Function<T, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(T model) throws Exception {

                        return null;
                    }
                });
            }
        };
    }


    /**
     * 异常处理变换
     *
     * @return
     */
    public static <T extends IModel> FlowableTransformer<T, T> rxCacheBeanHelper(final String key) {

        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<T>() {
                            @Override
                            public void accept(T t) throws Exception {

                            }
                        });

                //                return upstream.flatMap(new Function<T, Publisher<T>>() {
                //                    @Override
                //                    public Publisher<T> apply(T model) throws Exception {
                //                        if (model == null || model.isNull()) {
                //                            return Flowable.error(new NetError(model.getErrorMsg(), NetError.NoDataError));
                //                        } else if (model.isAuthError()) {
                //                            return Flowable.error(new NetError(model.getErrorMsg(), NetError.AuthError));
                //                        } else if (model.isBizError()) {
                //                            return Flowable.error(new NetError(model.getErrorMsg(), NetError.BusinessError));
                //                        } else {
                //                            return Flowable.just(model);
                //                        }
                //                    }
                //                });
            }
        };
    }


}
