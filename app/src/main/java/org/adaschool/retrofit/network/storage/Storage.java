package org.adaschool.retrofit.network.storage;

public interface Storage {

    void saveToken(String token);

    String getToken();

    void clear();
}