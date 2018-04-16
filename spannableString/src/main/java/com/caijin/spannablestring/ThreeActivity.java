package com.caijin.spannablestring;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ThreeActivity extends Activity {
    private AutoLinkStyleTextView autoLinkStyleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        autoLinkStyleTextView = (AutoLinkStyleTextView) findViewById(R.id.tv);
        autoLinkStyleTextView.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
                    @Override
                    public void onClick(int position) {
                        if (position == 0) {
                            Toast.makeText(ThreeActivity.this, "购买须知",
                                    Toast.LENGTH_SHORT).show();
                        } else if (position == 1) {
                            Toast.makeText(ThreeActivity.this, "用户条款",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
