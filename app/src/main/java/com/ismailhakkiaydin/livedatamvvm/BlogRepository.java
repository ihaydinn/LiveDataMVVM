package com.ismailhakkiaydin.livedatamvvm;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogRepository {

    private ArrayList<Blog> movies = new ArrayList<>();
    private MutableLiveData<List<Blog>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public BlogRepository(Application application){
        this.application = application;
    }

    public MutableLiveData<List<Blog>> getMutableLiveData(){
        RestApiService restApiService = RetrofitInstance.getApiService();
        Call<BlogWrapper> call = restApiService.getPopularBlog();
        call.enqueue(new Callback<BlogWrapper>() {
            @Override
            public void onResponse(Call<BlogWrapper> call, Response<BlogWrapper> response) {
                BlogWrapper blogWrapper = response.body();
                if (blogWrapper != null && blogWrapper.getBlog() != null){
                    movies= (ArrayList<Blog>) blogWrapper.getBlog();
                }
            }

            @Override
            public void onFailure(Call<BlogWrapper> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

}
