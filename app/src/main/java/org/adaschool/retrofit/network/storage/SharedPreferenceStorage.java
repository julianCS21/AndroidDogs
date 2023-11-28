package org.adaschool.retrofit.network.storage;

import static org.adaschool.retrofit.network.storage.Constants.TOKEN_KEY;

import android.content.SharedPreferences;

public class SharedPreferenceStorage implements Storage {
    private SharedPreferences sharedPreferences;

    public SharedPreferenceStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveToken(String token) {
        sharedPreferences.edit()
                .putString(TOKEN_KEY, token)
                .apply();
    }

    @Override
    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, "");
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
