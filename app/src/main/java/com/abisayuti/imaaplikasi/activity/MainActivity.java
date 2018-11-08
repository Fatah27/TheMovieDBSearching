package com.abisayuti.imaaplikasi.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.abisayuti.imaaplikasi.BuildConfig;
import com.abisayuti.imaaplikasi.R;
import com.abisayuti.imaaplikasi.adapter.CustomAdapter;
import com.abisayuti.imaaplikasi.model.ResponseSearch;
import com.abisayuti.imaaplikasi.model.ResultsItem;
import com.abisayuti.imaaplikasi.network.ApiService;
import com.abisayuti.imaaplikasi.network.InstanceRetrodit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pdFragmentSearch)
    ProgressBar pdFragmentSearch;
    @BindView(R.id.edtSearch)
    TextInputEditText edtSearch;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.recycler_movie_search)
    RecyclerView recyclerMovieSearch;
    @BindView(R.id.linerLayout)
    ConstraintLayout linerLayout;


    List<ResultsItem> dataAja;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    private void getDakar() {
        String query = edtSearch.getText().toString();
        if (query.isEmpty()){

            edtSearch.setError(getString(R.string.tdkbisa));
        }else {

            ApiService service = InstanceRetrodit.getInstance();
            Call<ResponseSearch> call = service.searchFilm(BuildConfig.API_KEY, BuildConfig.LANGUAGE, query);
            call.enqueue(new Callback<ResponseSearch>() {
                @Override
                public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {

                    List<ResultsItem> dataMovie = response.body().getResults();
                    String data1 = response.body().getResults().toString();
                    ResponseSearch responseSearch = response.body();
                    dataAja = response.body().getResults();
                    adapter = new CustomAdapter(MainActivity.this, dataAja);
                    recyclerMovieSearch.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, dataMovie);
                    recyclerMovieSearch.setAdapter(customAdapter);
                }

                @Override
                public void onFailure(Call<ResponseSearch> call, Throwable t) {

                }
            });

        }

    }

    @OnClick(R.id.btnSearch)
    public void onViewClicked() {
        getDakar();
    }
}
