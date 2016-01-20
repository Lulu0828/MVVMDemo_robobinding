package com.lulu.mvvmdemo_robobinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		MainModel mainModel = new MainModel(this, getResources());
		ViewBinder viewBinder = createViewBinder();
		View rootView = viewBinder.inflateAndBind(R.layout.activity_main, mainModel);
        setContentView(rootView);

        mainModel.loadAsync();
    }

	private ViewBinder createViewBinder() {
		BinderFactory reusableBinderFactory = new BinderFactoryBuilder().build();
		return reusableBinderFactory.createViewBinder(this);
	}

    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
