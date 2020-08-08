package com.example.rxoperatorexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rxoperatorexercise.impoperator.ConcateOperator;
import com.example.rxoperatorexercise.impoperator.FlatMapOperator;
import com.example.rxoperatorexercise.impoperator.MapOperator;
import com.example.rxoperatorexercise.impoperator.MergeOperator;
import com.example.rxoperatorexercise.operators.CountOperator;
import com.example.rxoperatorexercise.operators.DistinctOperator;
import com.example.rxoperatorexercise.operators.TakeOperator;

public class MainActivity extends AppCompatActivity {
    public static String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button click=(Button)findViewById(R.id.clickMe);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new FromArrayOperator().startRx();
//                new JustOperator().startRx();
//                new FilterOperator().startRx();
//                new RangeOperator().startRx();
//                new CompositDisposal().startRx();
//                new BufferOperator().startRx();
//                new DebounceOperator().startRx();
//                new RepeatOperator().startRx();
//                new SkipOperator().startRX();
//                new TakeOperator().startRX();
//                new DistinctOperator().startRX();
//                new CountOperator().startRX();
//                new ConcateOperator().startRX();
//                new MergeOperator().startRX();
//                new MapOperator().startRX();
                new FlatMapOperator().startRX();

            }
        });


}


}

//output

//System.out: Debounce Item:9