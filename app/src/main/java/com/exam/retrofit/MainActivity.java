package com.exam.retrofit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.exam.retrofit.api.GitHubService;
import com.exam.retrofit.api.model.GithubOwner;
import com.exam.retrofit.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private GitHubService gitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // https://api.github.com/users/peterkimlab/repos
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        gitHubService = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);

        mainBinding.getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOwnerData();
            }
        });
    }

    public void getOwnerData() {
        Call<GithubOwner> call = gitHubService.getRepo("peterkimlab");

        call.enqueue(new Callback<GithubOwner>() {
            @Override
            public void onResponse(Call<GithubOwner> call, Response<GithubOwner> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mainBinding.resultTV.setText("Response Code : " + response.body().avatarUrl);
                }
            }

            @Override
            public void onFailure(Call<GithubOwner> call, Throwable t) {

            }
        });
    }
}
