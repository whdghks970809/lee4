package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.alhazmy13.imagefilter.ImageFilter;

import static net.alhazmy13.imagefilter.ImageFilter.Filter.SKETCH;

public class MainActivity extends AppCompatActivity {

    Context context;
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransStatus transStatus = new TransStatus();
                transStatus.execute();
            }
        });
    }
    class TransStatus extends AsyncTask<String, Void, String>{
        ProgressDialog asyncDialog = new ProgressDialog(context);
        @Override
        protected String doInBackground(String... strings) {
            for(int i = 0; i < 5; i++){
                asyncDialog.setProgress(i+30);
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    Log.e("TAG",e.toString());
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            asyncDialog.dismiss();
        }
        @Override
        protected void onPreExecute(){
            asyncDialog.setProgress(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("변환중입니다.");
            Bitmap bm = ImageFilter.applyFilter(mBitmap, ImageFilter.Filter.SKETCH);
            ImageView iv = (ImageView)findViewById(R.id.imageView);
            iv.setImageBitmap(bm);
            asyncDialog.show();
            super.onPreExecute();
        }
    }
}
