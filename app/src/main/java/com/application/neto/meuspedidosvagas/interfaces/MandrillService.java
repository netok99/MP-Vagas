package com.application.neto.meuspedidosvagas.interfaces;

import com.application.neto.meuspedidosvagas.model.Email;
import com.application.neto.meuspedidosvagas.model.ResponseEmail;
import com.application.neto.meuspedidosvagas.model.ResponseObject;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface MandrillService {

    @POST("/messages/send.json")
    void sendEmail(@Body Email email, Callback<List<ResponseObject>> callback);

}
