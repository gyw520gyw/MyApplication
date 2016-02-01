package demo.gyw.com.myapplication.retrofit;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.Url;
import rx.Observable;


/**
 * Created by Administrator on 2015/12/22.
 */

//http://hsapi.livedevice.cn/healthstore/index.php?m=App&a=appinfo&app_id=A000000000361

public interface TeztApi {
    @POST("healthstore/index.php")//m=App&a=appinfo
    Call<AppDetailBean> getSoftwareDetail(@Query("m") String app, @Query("a") String info , @Query("app_id") String id /*@Body AppDetailBean appDetailBean*/);


    @GET//1("?m=App&a=appinfo")
    Observable<AppDetailBean> getSoftwareDetail2(@Url String url); /*1@Query("app_id") String id*/

}
