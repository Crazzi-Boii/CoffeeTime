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
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    int coffeeIndex = -1, clickStatus, quantity = 0; //to know which coffee type is selected
    Toast toast;
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
    public void  qincr(View view){
        if (quantity < 10){
            quantity++;
            displayQuantity();
        }
        if(quantity >= 10){
            makeToast("Max 10 cups!");
        }
    }
    public void  qdecr(View view){
        if (quantity > 0){
            quantity--;
            displayQuantity();
        }
    }
    void displayQuantity(){
        TextView displayQuantityTV = (TextView) findViewById(R.id.showQuantity);
        displayQuantityTV.setText("" + quantity);
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
    int XtrachinaStatus = 0;
    public void xtraChina(View view) {
        TextView xtraChinaTV = (TextView) findViewById(R.id.xtraChinaTextView);
        if (XtrachinaStatus == 0){
            xtraChinaTV.setTextColor(Color.RED);
            makeToast("Extra chinnamon added.");
            XtrachinaStatus=1;
         }else if (XtrachinaStatus == 1){
            xtraChinaTV.setTextColor(Color.parseColor("#211f21"));
            makeToast("Extra chinnamon removed.");
            XtrachinaStatus=0;
        }
    }
    int XtrachocoStatus = 0;
    public void XtraChoco(View view){
        TextView xtraChocoTV = (TextView) findViewById(R.id.xtraChoco);
        if (XtrachocoStatus == 0){
            xtraChocoTV.setTextColor(Color.RED);
            makeToast("Extra chocolate syrup added.");
            XtrachocoStatus=1;
        }else if (XtrachocoStatus == 1){
            xtraChocoTV.setTextColor(Color.parseColor("#211f21"));
            makeToast("Extra chocolate syrup removed.");
            XtrachocoStatus=0;
        }
    }
    /***************************************************
     * *************************************************
     *
     * Size Methods Starts
     *
     * ***************************************************
     * ***************************************************/
    int coffeeSize ;
    public void sizeS(View view){
        coffeeSize = 1;
        sizeExecute();
    }
    public void sizeM(View view){
        coffeeSize = 2;
        sizeExecute();
    }
    public void sizeL(View view){
        coffeeSize = 3;
        sizeExecute();
    }
    public void sizeXL(View view){
        coffeeSize = 4;
        sizeExecute();
    }

    void sizeExecute(){
        TextView smallTV = (TextView) findViewById(R.id.small);
        TextView mediumTV = (TextView) findViewById(R.id.medium);
        TextView largeTV = (TextView) findViewById(R.id.large);
        TextView xlargeTV = (TextView) findViewById(R.id.Xlarge);
        sizeReset();
        switch (coffeeSize){
            case 1:{
                smallTV.setTextColor(Color.RED);
                break;
            }
            case 2:{
                mediumTV.setTextColor(Color.RED);
                break;
            }
            case 3:{
                largeTV.setTextColor(Color.RED);
                break;
            }
            case 4:{
                xlargeTV.setTextColor(Color.RED);
                break;
            }
        }
    }
    void sizeReset(){
        TextView smallTV = (TextView) findViewById(R.id.small);
        TextView mediumTV = (TextView) findViewById(R.id.medium);
        TextView largeTV = (TextView) findViewById(R.id.large);
        TextView xlargeTV = (TextView) findViewById(R.id.Xlarge);
        smallTV.setTextColor(Color.parseColor("#211f21"));
        mediumTV.setTextColor(Color.parseColor("#211f21"));
        largeTV.setTextColor(Color.parseColor("#211f21"));
        xlargeTV.setTextColor(Color.parseColor("#211f21"));
    }
    /***************************************************
     * *************************************************
     *
     * Size Methods Emds
     *
     * ***************************************************
     * ****************************************************/


    public void makeToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
