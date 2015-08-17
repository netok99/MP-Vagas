package com.application.neto.meuspedidosvagas;

import com.application.neto.meuspedidosvagas.interfaces.MandrillService;
import com.application.neto.meuspedidosvagas.model.Email;
import com.application.neto.meuspedidosvagas.model.ResponseObject;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

public class Mandrill {

    private final String KEY = "PDnE0geJlLRIdrpKkQtN5Q";
    private final String ENDPOINT = "https://mandrillapp.com/api/1.0";

    public void sendEmail(Email email, Callback<List<ResponseObject>> callback) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();
        MandrillService mandrillService = restAdapter.create(MandrillService.class);
        email.setKey(KEY);
        mandrillService.sendEmail(email, callback);
    }
}
