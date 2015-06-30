package com.pavelsikun.cardstackviewexample;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pavelsikun.cardstackview.CardStackView;

public class MainActivity extends AppCompatActivity {

    Boolean flag = false;
    int stackSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CardStackView stack = (CardStackView)findViewById(R.id.stack);
        Button add = (Button) findViewById(R.id.add);
        Button remove = (Button) findViewById(R.id.remove);

        final TextView counter = (TextView) findViewById(R.id.stackSizeLabel);
        stackSize = stack.getStackSize();
        counter.setText("Stack size = " + stackSize);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackSize++;
                if(stackSize > 5) {
                    stackSize = 5;
                    Snackbar.make(findViewById(android.R.id.content), "5 is maximal possible value!", Snackbar.LENGTH_SHORT).show();
                }
                stack.setStackSize(stackSize);
                counter.setText("Stack size = " + stackSize);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackSize--;
                if (stackSize < 0) {
                    stackSize = 0;
                    Snackbar.make(findViewById(android.R.id.content), "0 is minimal possible value!", Snackbar.LENGTH_SHORT).show();
                }
                stack.setStackSize(stackSize);
                counter.setText("Stack size = " + stackSize);
            }
        });


        Button direction = (Button) findViewById(R.id.direction);
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    stack.setStackDirection(CardStackView.DIRECTION_UP);
                    flag = !flag;
                }
                else {
                    stack.setStackDirection(CardStackView.DIRECTION_DOWN);
                    flag = !flag;
                }
            }
        });
    }
}
