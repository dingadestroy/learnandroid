package com.example.ronald.ronsintenttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebView;

/**
 * Created by ronald on 11/4/15.
 */
public class MyWebView extends AppCompatActivity {
    WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        myWebView = (android.webkit.WebView) findViewById(R.id.myWebView);
        myWebView.setWebViewClient(new WebViewClient());

        //WebView mWV = (WebView) findViewById(R.id.myWebView);
        //final View webView = findViewById(R.id.myWebView);
        //WebView webView = (WebView) findViewById(R.id.myWebView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        Button go = (Button) findViewById(R.id.btnGo);
        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myWebView.canGoBack() )
                    myWebView.goBack();
            }
        });

        Button forward = (Button) findViewById(R.id.btnForward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myWebView.canGoForward() ) {
                    myWebView.goForward();
                }

            }
        });
        Button refresh = (Button) findViewById(R.id.btnRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.reload();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl(editText.getText().toString());
            }
        });






    }
}
