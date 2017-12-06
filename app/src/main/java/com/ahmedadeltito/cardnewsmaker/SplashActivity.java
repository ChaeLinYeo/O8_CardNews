package com.ahmedadeltito.cardnewsmaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by kwon-younghoon on 2017. 11. 23..
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(2500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
//        style xml에서 로딩화면 이미지 수정
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }
}
