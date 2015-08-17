package com.application.neto.meuspedidosvagas;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.neto.meuspedidosvagas.model.Candidate;
import com.application.neto.meuspedidosvagas.model.Email;
import com.application.neto.meuspedidosvagas.model.Message;
import com.application.neto.meuspedidosvagas.model.ResponseObject;
import com.application.neto.meuspedidosvagas.model.To;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity(R.layout.skill_fragment)
public class SkillActivity extends AppCompatActivity {

    private Candidate candidate;

    @ViewById(R.id.tb_main)
    Toolbar toolbar;
    @ViewById
    SeekBar html;
    @ViewById
    SeekBar css;
    @ViewById
    SeekBar python;
    @ViewById
    SeekBar django;
    @ViewById
    SeekBar ios;
    @ViewById
    SeekBar android;
    @ViewById
    SeekBar javascript;
    @ViewById
    TextView valor_android;
    @ViewById
    TextView valor_ios;
    @ViewById
    TextView valor_django;
    @ViewById
    TextView valor_python;
    @ViewById
    TextView valor_javascript;
    @ViewById
    TextView valor_css;
    @ViewById
    TextView valor_html;

    @Extra
    String nome;
    @Extra
    String email;

    @AfterViews
    void init() {
        toolBarConfig();
        android.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_android.setText(String.valueOf(progress));
            }
        });

        ios.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_ios.setText(String.valueOf(progress));
            }
        });

        django.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_django.setText(String.valueOf(progress));
            }
        });
        python.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_python.setText(String.valueOf(progress));
            }
        });
        javascript.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_javascript.setText(String.valueOf(progress));
            }
        });
        css.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_css.setText(String.valueOf(progress));
            }
        });
        html.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valor_html.setText(String.valueOf(progress));
            }
        });
    }

    private void toolBarConfig() {
        toolbar.setTitle("Mudar depois");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Click(R.id.save)
    void save() {
        candidate = new Candidate();
        if (getIntent() != null) {
            candidate.setNome(getIntent().getStringExtra("nome").toString());
            candidate.setEmail(getIntent().getStringExtra("email").toString());
        }
        candidate.setAndroid(Integer.parseInt(valor_android.getText().toString()));
        candidate.setIos(Integer.parseInt(valor_ios.getText().toString()));
        candidate.setJavaScript(Integer.parseInt(valor_javascript.getText().toString()));
        candidate.setPython(Integer.parseInt(valor_python.getText().toString()));
        candidate.setDjango(Integer.parseInt(valor_django.getText().toString()));
        candidate.setHtml(Integer.parseInt(valor_html.getText().toString()));
        candidate.setCss(Integer.parseInt(valor_css.getText().toString()));

        StringBuilder body = new StringBuilder();
        body.append(" Obrigado por se candidatar, assim que tivermos uma vaga disponível para ");
        for (String str : candidate.analyzeProfile()) {
            body.append(str);
            body.append(" entraremos em contato.");

            Mandrill mandrill = new Mandrill();
            mandrill.sendEmail(mountEmail(body.toString(), "Obrigado por se candidatar", candidate)
                    , new Callback<List<ResponseObject>>() {
                @Override
                public void success(List<ResponseObject> responseObjects, Response response) {
                    Toast.makeText(SkillActivity.this, "Email enviado com sucesso, aguarde" +
                            " seu email chegar.", Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(SkillActivity.this, "Erro ao enviar email, tente mais tarde.",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        finish();
    }

    private Email mountEmail(String body, String subject, Candidate candidate) {
        To para = new To(candidate.getEmail(), candidate.getNome(), "to");
        ArrayList<To> paralist = new ArrayList<>();
        paralist.add(para);
        Message message = new Message(body, subject, "gildenio.neto@mespedidos.com",
                "Gildenio Neto", paralist);
        Email email = new Email(message, false);
        return email;
    }
}
