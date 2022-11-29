package com.erenyamic.KingBank;

import com.google.gson.annotations.SerializedName;

public class investclass {
    @SerializedName("currency")
    public String currency;
    @SerializedName("price")
    public String price;
    @SerializedName("max_supply")
    public String max_supply;
    @SerializedName("circulating_supply")
    public String circulating_supply;
    @SerializedName("market_cap")
    public String market_cap;
    @SerializedName("high")
    public String high;
}
