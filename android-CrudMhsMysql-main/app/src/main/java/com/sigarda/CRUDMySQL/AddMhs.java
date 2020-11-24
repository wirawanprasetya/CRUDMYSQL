package com.sigarda.CRUDMySQL;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sigarda.CRUDMySQL.Models.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMhs extends AppCompatActivity {
    BaseApiService mApiService;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_mhs);
        mApiService = ApiClient.UtilsApi.getAPIService();
        save = findViewById(R.id.save);
        save.setOnClickListener(save->{
            Toast.makeText(this,"Menyimpan...",Toast.LENGTH_SHORT).show();
        });
    }

    public void saveMhs(){
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

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.d("JJJ", "Hasil "+e.getMessage());
                        Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("JJJ", "Hasil "+response);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("JJJ", "Hasil "+t.getMessage());
            }


        });
    }
}