package com.exam.retrofit.api;

import com.exam.retrofit.api.model.GithubOwner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("/users/{anything}") //https://api.github.com/users/peterkimlab
    Call<GithubOwner> getRepo(@Path("anything") String id);

}
