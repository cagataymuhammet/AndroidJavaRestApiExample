# AndroidJavaRestApiExample

Retrofit2, RxJava ve Observer kullanılarak geliştirilmiş bir android örneğidir.

Android Stuido 3.1.3 ve Google Play Services 28.0.0 kullanılmıştır.

Api olarak  [JSONPlaceholder](https://jsonplaceholder.typicode.com/) kullanılmıştır.


Detaylı anlatıma  [Android Türkiye Medium](https://medium.com/android-t%C3%BCrkiye/android-uygulamada-retrofit2-ve-rxjava2-kullanarak-web-servisten-veri-%C3%A7ekme-91568a31a5fb) sayfasından ulaşabilirsiniz.

<img src="https://github.com/cagataymuhammet/AndroidJavaRestApiExample/blob/master/sh.png"/>


# dependencies

```gradle

dependencies {

    implementation fileTree(dir: 'libs', include: ['*.jar'])

    def supportLibraryVersion="28+"
    def retrofitLibraryVersion = "2.3.0"

    //SUPPORT LİBRARY
    implementation "com.android.support:appcompat-v7:$supportLibraryVersion"
    implementation "com.android.support:design:$supportLibraryVersion"
    implementation "com.android.support:recyclerview-v7:$supportLibraryVersion"
    implementation "com.android.support:cardview-v7:$supportLibraryVersion"
    implementation 'com.android.support.constraint:constraint-layout:1.1.2'

    //Retrofit Library
    implementation "com.squareup.retrofit2:retrofit:$retrofitLibraryVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitLibraryVersion"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofitLibraryVersion"
    implementation 'com.squareup.okhttp:okhttp:2.7.2'

    //RxJava Library
    implementation 'io.reactivex.rxjava2:rxjava:2.1.7'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'

}

```

# Service.java
```java

public class Service {

    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private ServiceApi serviceApi;
    private static Retrofit retrofit;

    private static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClientFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    private static OkHttpClient getOkHttpClientFactory() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return builder.build();
    }

    public ServiceApi getServiceApi() {
        serviceApi = getInstance().create(ServiceApi.class);
        return serviceApi;
    }
}
```

# ServiceApi.class
```java

public interface ServiceApi {
    @GET("posts")
    Observable<List<UserModel>> getUsers();
}

```


# UserModel.class
```java
public class UserModel {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

```


# Servise bağlanıp veri çekmek
```java
new Service().getServiceApi().getUsers().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserModel>>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserModel> userModels) {
                        usersList=userModels;
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(getApplicationContext(), "Error :" +e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete()
                    {
                        if(usersList.size()>0)
                        {
                            generateUserList(usersList);
                        }

                    }
                });

```



License
--------


    Copyright 2018 Muhammet ÇAĞATAY.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
