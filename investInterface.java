package com.erenyamic.KingBank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface investInterface {
    @GET("ticker?key=m_c478db76e8660ecd3601a24ceed1836b2afc9055")
    Call<List<investclass>> getData();
}
