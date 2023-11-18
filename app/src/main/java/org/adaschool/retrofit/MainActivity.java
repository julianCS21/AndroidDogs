package org.adaschool.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.bumptech.glide.Glide;
import org.adaschool.retrofit.databinding.ActivityMainBinding;
import org.adaschool.retrofit.network.RetrofitInstance;
import org.adaschool.retrofit.network.dto.BreedsListDto;
import org.adaschool.retrofit.network.dto.DogDto;
import org.adaschool.retrofit.network.service.DogApiService;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DogApiService dogApiService = RetrofitInstance.getRetrofitInstance().create(DogApiService.class);

        Call<DogDto> call = dogApiService.getBreed("pitbull");
        call.enqueue(new Callback<DogDto>() {
            @Override
            public void onResponse(Call<DogDto> call, Response<DogDto> response) {
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    String url= response.body().getMessage();
                    Log.e(url,"url");

                    loadDogInfo(url,"pitbull");

                } else {
                    Log.e(TAG, "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<DogDto> call, Throwable t) {
                Log.e(TAG, "Error al llamar a la API", t);
            }
        });
    }

        private void loadDogInfo(String url, String dog) {
            binding.textView.setText(dog);
            Glide.with(this)
                    .load(url)
                    .into(binding.imageView);
        }


}
