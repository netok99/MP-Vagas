package com.application.neto.meuspedidosvagas;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.application.neto.meuspedidosvagas.model.Candidate;
import com.application.neto.meuspedidosvagas.model.Email;
import com.application.neto.meuspedidosvagas.model.Message;
import com.application.neto.meuspedidosvagas.model.To;
import com.application.neto.meuspedidosvagas.model.ResponseObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    Candidate candidate;

    public ApplicationTest() {
        super(Application.class);
    }

    public void profileFrontEnd(){
        candidate = new Candidate("Teste", "teste@teste.com", 10, 9, 7, 8, 3, 2, 10);
        assertEquals(candidate.analyzeProfile(), "Front-End");
    }

    public void profileBackEnd(){
        candidate = new Candidate("Teste", "teste@teste.com", 8, 5, 3, 8, 7, 2, 10);
        assertEquals(candidate.analyzeProfile().get(1).toString(), "Back-End");
    }

    public void profileMobile(){
        candidate = new Candidate("Teste", "teste@teste.com", 10, 6, 6, 8, 3, 7, 10);
        assertEquals(candidate.analyzeProfile().get(1).toString(), "Mobile");
    }

    public void profileGenerico(){
        candidate = new Candidate("Teste", "teste@teste.com", 6, 5, 7, 5, 2, 2, 6);
        assertEquals(candidate.analyzeProfile().get(1).toString(), "Generico");
    }

    public void profileAll(){
        candidate = new Candidate("Teste", "teste@teste.com", 10, 9, 7, 8, 7, 8, 10);
        assertEquals(candidate.analyzeProfile().size(), 3);
    }

    public void EmailSend() {
        candidate = new Candidate("Teste", "teste@teste.com", 10, 6, 6, 8, 3, 7, 10);
        Mandrill mandrill = new Mandrill();
        mandrill.sendEmail(mountEmail("Teste", "Email " + candidate.analyzeProfile(), candidate)
                , new Callback<List<ResponseObject>>() {
            @Override
            public void success(List<ResponseObject> responseObjects, Response response) {
                boolean test = false;
                if (responseObjects != null){
                    test = true;
                }
                assertEquals(test, true);
            }

            @Override
            public void failure(RetrofitError error) {}
        });
    }

    private Email mountEmail(String body, String subject, Candidate candidate) {
        To para = new To(candidate.getEmail(), candidate.getNome(), "to");
        ArrayList<To> paralist = new ArrayList<To>();
        paralist.add(para);
        Message message = new Message(body, subject, "gildenio.neto@mespedidos.com",
                "Gildenio Neto", paralist);
        Email email = new Email(message, false);
        return email;
    }
}