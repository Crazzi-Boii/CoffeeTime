package com.asitbehera.coffeetime;

/*
* @@@@@@@@@@@@@@@@@@@@@@@@@@@@
* @@                        @@
* @@ Author - Crazzi_Boii   @@
* @@                        @@
* @@@@@@@@@@@@@@@@@@@@@@@@@@@@
*
* Follow me on github - "github.com/Crazzi-Boii"
*/
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    int coffeeIndex = -1, clickStatus ; //to know which coffee type is selected
    String coffeeTypes[] = new String[]{"Cappuccino","Americano","Espresso","Macchiato","Mocha","Latte"};
    Integer picArray[] = new Integer[]{R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};

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
    // This io method work is to give a flawless use of the coffee selector
    //it's like if someone click right arrrow a number of time the it will stop at a poin n he need to navigate with the left arrow
    //but i tried to gave an user a better user experience . . so i came up implementing this method . .
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
        executeTask();
    }

    void executeTask(){
        displayCoffee(coffeeIndex);
        displayCoffeePic(coffeeIndex);
        displayCoffeePrice(coffeeIndex);

    }

    void displayCoffee(int CI ){ // CI = coffeeIndex
        TextView coffeeTypeTV = (TextView) findViewById(R.id.CoffeeTypeTextView);
        if (CI >= 0 && CI <= 5)
        coffeeTypeTV.setText(coffeeTypes[CI]);

       /* TextView testTV = (TextView) findViewById(R.id.testtv);
        testTV.setText(String.valueOf(CI)); */
    }
    void displayCoffeePic(int CI){
        ImageView coffeePictureIV = (ImageView) findViewById(R.id.coffeePicture);
        coffeePictureIV.setImageResource(picArray[CI]);
    }
    void displayCoffeePrice(int CI){
        TextView coffeePriceTV = (TextView) findViewById(R.id.coffeePriceTextView);
        coffeePriceTV.setVisibility(View.VISIBLE);
        String total = NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(CI);
        coffeePriceTV.setText(total);
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
