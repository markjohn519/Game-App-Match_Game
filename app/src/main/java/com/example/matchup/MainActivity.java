package com.example.matchup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public TextView winner;
    ImageView curView = null;
    private int countPair = 0;
    final int[] drawable = new int[] {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};

    int[] pos = {0,1,2,3,4,5,0,1,2,3,4,5};
    int currentPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winner=findViewById(R.id.textView2);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentPos <0){
                    currentPos = position;
                    curView = (ImageView) view;
                    ((ImageView) view).setImageResource(drawable[pos[position]]);
                }

                else {
                    if (currentPos == position){
                        ((ImageView)view).setImageResource(R.drawable.hidden);
                    }
                    else if(pos[currentPos]!=pos[position]){
                        curView.setImageResource(R.drawable.hidden);
                        Toast.makeText(getApplicationContext(),"Not Match", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ((ImageView)view).setImageResource(drawable[pos[position]]);
                        countPair++;
                        Toast.makeText(getApplicationContext(),"Match", Toast.LENGTH_SHORT).show();
                        if(countPair==6){
                            winner.setText("You Win");
                        }
                    }
                    currentPos = -1;

                }
            }
        });
    }
}