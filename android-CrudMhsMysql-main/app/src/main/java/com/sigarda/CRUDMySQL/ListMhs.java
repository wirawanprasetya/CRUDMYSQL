package com.sigarda.CRUDMySQL;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sigarda.CRUDMySQL.Adapters.MhsAdapter;
import com.sigarda.CRUDMySQL.Models.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMhs extends AppCompatActivity {
    ProgressBar progressBar;
    BaseApiService mApiService;
    RecyclerView recyclerView;
    private MhsAdapter mAdapter;
    List<Student> studentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        mApiService = ApiClient.UtilsApi.getAPIService();
        studentList = new ArrayList<>();
        getStudents();
    }
    public void getStudents(){
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getStudents().enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        JSONObject result = jsonRESULTS.getJSONObject("result");
                        JSONArray students = result.getJSONArray("data");
                        List<Student> studentArr = new Gson().fromJson(students.toString(), new TypeToken<List<Student>>() {
                        }.getType());
                        studentList.addAll(studentArr);
                        mAdapter = new MhsAdapter(ListMhs.this, studentList);
                        recyclerView.setAdapter(mAdapter);
                        progressBar.setVisibility(View.GONE);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.d("JJJ", "Hasil "+e.getMessage());
                        Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                } else {
                    Log.d("JJJ", "Hasil "+response);

                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("JJJ", "Hasil "+t.getMessage());
            }


        });
    }
}
