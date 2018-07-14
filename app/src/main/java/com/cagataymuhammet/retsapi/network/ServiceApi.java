package com.cagataymuhammet.retsapi.network;


import com.cagataymuhammet.retsapi.model.UserModel;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by OnurBarman on 8.07.2018.
 *  * UPDATED by MUHAMMET ÇAĞATAY on 8.07.2018.
 *
 */





public interface ServiceApi {

    @GET("posts")
    Observable<List<UserModel>> getUsers();
}
