package com.asitbehera.coffeetime;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    int coffeeIndex = -1, clickStatus ; //to know which coffee type is selected
    String coffeeTypes[] = new String[]{"Cappuccino","Americano","Espresso","Macchiato","Mocha","Latte"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void  incr(View view){
        clickStatus = 1;
        io(clickStatus);
    }
    public void  decr(View view){
       clickStatus = 0;
        io(clickStatus);
    }
    private void io(int cs){
        if(cs == 1){
            coffeeIndex++;
        }
        if (cs == 0){
            coffeeIndex--;
        }
        if(coffeeIndex == 6){
            coffeeIndex = 0;
        }
        if (coffeeIndex == -1){
            coffeeIndex = 5;
        }
        displayCoffee(coffeeIndex);
    }

    void displayCoffee(int CI ){ // CI = coffeeIndex
        TextView coffeeTypeTV = (TextView) findViewById(R.id.CoffeeTypeTextView);
        if (CI >= 0 && CI <= 5);
        coffeeTypeTV.setText(coffeeTypes[CI]);
       /* TextView testTV = (TextView) findViewById(R.id.testtv);
        testTV.setText(String.valueOf(CI)); */
    }


    public void tost(View view){
        Toast toast;
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
