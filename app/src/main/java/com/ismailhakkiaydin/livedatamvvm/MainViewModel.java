package com.ismailhakkiaydin.livedatamvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private BlogRepository blogRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        blogRepository = new BlogRepository(application);
    }

    public LiveData<List<Blog>> getAllBlog(){
        return blogRepository.getMutableLiveData();
    }

}
