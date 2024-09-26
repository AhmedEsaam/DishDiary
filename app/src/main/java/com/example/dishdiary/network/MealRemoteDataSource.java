package com.example.dishdiary.network;

import java.util.Map;

public interface MealRemoteDataSource {
    void makeNetworkCall(NetworkCallback networkCallback, String endPoint, Map<String, String> queryParams);
}
