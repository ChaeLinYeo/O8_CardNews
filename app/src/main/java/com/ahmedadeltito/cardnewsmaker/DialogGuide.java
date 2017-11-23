package com.ahmedadeltito.cardnewsmaker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class DialogGuide extends Dialog {
    private Button nextButton;
    private Button preButton;
    private ImageView i1;
    private ImageView i2;
    private ImageView i3;
    private ImageView i4;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow_inf = new WindowManager.LayoutParams();
        lpWindow_inf.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow_inf.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow_inf);
        setContentView(R.layout.activity_dialog_guide);
        nextButton = (Button)findViewById(R.id.go_right);
        nextButton.setBackgroundResource(R.drawable.ic_action_next_item);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.i<4) ++MainActivity.i;
                Start();
            }
        });
        preButton = (Button)findViewById(R.id.go_left);
        preButton.setBackgroundResource(R.drawable.ic_action_previous_item);
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.i>1) --MainActivity.i;
                Start();
            }
        });

        i1 = (ImageView)findViewById(R.id.img1);
        i2 = (ImageView)findViewById(R.id.img2);
        i3 = (ImageView)findViewById(R.id.img3);
        i4 = (ImageView)findViewById(R.id.img4);

//        mcloseButton = (Button)findViewById(R.id.go_right);

        // 클릭 이벤트 셋팅
//        mcloseButton.setOnClickListener(mCloseClickListener);


    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public DialogGuide(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context = context;
    }

    private void Start(){
        switch (MainActivity.i){
            case 1:
                i1.setVisibility(View.VISIBLE);
                i2.setVisibility(View.GONE);
                i3.setVisibility(View.GONE);
                i4.setVisibility(View.GONE);
                break;
            case 2:
                i1.setVisibility(View.GONE);
                i2.setVisibility(View.VISIBLE);
                i3.setVisibility(View.GONE);
                i4.setVisibility(View.GONE);
                break;
            case 3:
                i1.setVisibility(View.GONE);
                i2.setVisibility(View.GONE);
                i3.setVisibility(View.VISIBLE);
                i4.setVisibility(View.GONE);
                break;
            case 4:
                i1.setVisibility(View.GONE);
                i2.setVisibility(View.GONE);
                i3.setVisibility(View.GONE);
                i4.setVisibility(View.VISIBLE);
                break;
        }
    }

}
