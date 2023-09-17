package com.example.flash;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;

import com.example.flash.databinding.ActivityMainBinding;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.SourcesRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.kwabenaberko.newsapilib.models.response.SourcesResponse;

public class MainActivity extends AppCompatActivity {
    NewsApiClient newsApiClient;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        news("cricket");
}

    @Override
    protected void onStart() {
        super.onStart();
 newsApiClient = new NewsApiClient("2c536c3d9dbf45a3a6e5c9a396bf46da");
    }

    void news(String s)
{

// /v2/everything
    newsApiClient.getEverything(
            new EverythingRequest.Builder()
                    .q(s)
                    .build(),
            new NewsApiClient.ArticlesResponseCallback() {
                @Override
                public void onSuccess(ArticleResponse response) {

                    System.out.println(response.getArticles().get(0).getTitle());
binding.textView.setText(response.getArticles().get(0).getTitle());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }
            }
    );

// /v2/top-headlines

// /v2/top-headlines/sources
    newsApiClient.getSources(
            new SourcesRequest.Builder()
                    .language("en")
                    .country("us")
                    .build(),
            new NewsApiClient.SourcesCallback() {
                @Override
                public void onSuccess(SourcesResponse response) {
                    System.out.println(response.getSources().get(0).getName());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }
            }
    );
}
void headlines(String headline)
{
    newsApiClient.getTopHeadlines(
            new TopHeadlinesRequest.Builder()
                    .q(headline)
                    .language("en")
                    .build(),
            new NewsApiClient.ArticlesResponseCallback() {
                @Override
                public void onSuccess(ArticleResponse response) {
                    System.out.println(response.getArticles().get(0).getTitle());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }
            }
    );

}
}