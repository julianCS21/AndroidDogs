package org.adaschool.retrofit.network.service;

import org.adaschool.retrofit.network.dto.BreedsListDto;
import org.adaschool.retrofit.network.dto.DogDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApiService {
    @GET("api/breeds/list/all")
    Call<BreedsListDto> getAllBreeds();

    @GET("api/breed/{name}/images/random")
    Call<DogDto> getBreed(@Path("name") String breedName);

}
