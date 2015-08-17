package com.application.neto.meuspedidosvagas.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.application.neto.meuspedidosvagas.R;
import com.application.neto.meuspedidosvagas.SkillActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.main_fragment)
public class MainFragment extends Fragment {

    @ViewById
    EditText et_nome;

    @ViewById
    EditText et_email;

    @Click(R.id.ft_button)
    void newSkils() {
        Intent intent =new Intent(getActivity(), SkillActivity_.class);
        if (!isValidEmail(et_email.getText().toString())) {
            Toast.makeText(getActivity(), "Email inválido", Toast.LENGTH_LONG).show();
            et_email.requestFocus();
        }else if(!isValidNome(et_nome.getText().toString())) {
            Toast.makeText(getActivity(), "Informe um nome com mais de três caracteres.", Toast.LENGTH_LONG).show();
            et_email.requestFocus();
        } else {
            intent.putExtra("nome", et_nome.getText().toString());
            intent.putExtra("email", et_email.getText().toString());
            startActivity(intent);
            et_nome.setText("");
            et_email.setText("");
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final static boolean isValidNome(CharSequence target) {
        if(target.length() < 4)
            return false;
        else
            return true;
    }
}
