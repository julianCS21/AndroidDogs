package org.adaschool.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;

import org.adaschool.retrofit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadDogInfo();
    }

    private void loadDogInfo() {
        String dogImageUrl = "https://images.dog.ceo/breeds/retriever-chesapeake/n02099849_1830.jpg";
        String dogName = "Chesapeake Retriever";
        binding.textView.setText(dogName);
        Glide.with(this)
                .load(dogImageUrl)
                .into(binding.imageView);
    }


}