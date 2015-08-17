package com.application.neto.meuspedidosvagas;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.application.neto.meuspedidosvagas.fragments.MainFragment;
import com.application.neto.meuspedidosvagas.fragments.MainFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tb_main)
    Toolbar toolbar;

    @AfterViews
    void init() {
        toolBarConfig();
        mainFragmentConfig();
    }

    private void toolBarConfig() {
        toolbar.setTitle("MP Vagas");
        toolbar.setSubtitle("");
        toolbar.setTitleTextColor(0xFFFFFFFF);
    }

    private void mainFragmentConfig() {
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
        if (fragment == null) {
            fragment = MainFragment_.builder().build();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rl_fragment_container, fragment, "MainFragment");
            ft.commit();
        }
    }
}
