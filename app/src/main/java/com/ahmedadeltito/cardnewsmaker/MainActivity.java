package com.ahmedadeltito.cardnewsmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;


public class MainActivity extends MediaActivity {
    private DialogGuide dialogGuide;
    private WebView webView;
    static int i;

    public void openUserGallery(View view) {
        openGallery();
    }

    public void openUserCamera(View view) {
        startCameraActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webV);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

        // 액티비티 전환
        Button button1 = (Button)findViewById(R.id.licensecbtn);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                webView.loadUrl("http://news.naver.com/main/hotissue/sectionList.nhn?mid=hot&sid1=110&cid=1010805");
            }
        });
    }


    @Override
    protected void onPhotoTaken() {
        Intent intent = new Intent(MainActivity.this, PhotoEditorActivity.class);
        intent.putExtra("selectedImagePath", selectedImagePath);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.help:
                dialogGuide = new DialogGuide(MainActivity.this);
                dialogGuide.show();
                i = 1;
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private View.OnClickListener close_Dialog = new View.OnClickListener() {
        public void onClick(View v) {
            dialogGuide.dismiss();
        }
    };

}
