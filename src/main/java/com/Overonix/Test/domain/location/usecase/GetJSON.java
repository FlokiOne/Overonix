package com.Overonix.Test.domain.location.usecase;

import com.Overonix.Test.presentation.dto.RsAddress;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetJSON {

    public List<RsAddress> getJSONArray(String result) {
        JSONArray json = new JSONArray(result);
        List<RsAddress> address = new ArrayList<RsAddress>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);

            address.add(new RsAddress(
                    object.getJSONObject("address").getString("country"),
                    object.getDouble("lat"),
                    object.getDouble("lon")
            ));
        }
        return address;
    }

    public RsAddress getJSON(String result) {
            JSONObject object = new JSONObject(result);
        return new RsAddress(
                    object.getJSONObject("address").getString("country"),
                    object.getDouble("lat"),
                    object.getDouble("lon")
            );
    }

}
