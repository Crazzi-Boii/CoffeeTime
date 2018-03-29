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
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity{
    int coffeeIndex = -1, clickStatus, quantity = 0; //to know which coffee type is selected
    Toast toast;
    String coffeeTypes[] = new String[]{"Cappuccino","Americano","Espresso","Macchiato","Mocha","Latte"};
    Integer picArray[] = new Integer[]{R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    int basePrice[] = new int[]{ 120 , 110 , 90 , 110 , 125 , 130 };
    int coffeeSize[] = new int[]{ 14 , 21};
    int addOn[] = new int[]{ 23 , 12};
    int[][] cart = new int[10][6];
    int totalPrice = 0;
    static int cupCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout layout1TV = (LinearLayout) findViewById(R.id.layout1);
        layout1TV.setVisibility(View.VISIBLE);
        LinearLayout layout2TV = (LinearLayout) findViewById(R.id.layout2);
        layout2TV.setVisibility(View.INVISIBLE);
        LinearLayout linerButton2 = (LinearLayout) findViewById(R.id.linerbttm2);
        linerButton2.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("want to exit Coffee Time");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
        AlertDialog a = builder.create();
        a.show();
    }
    public void  incr(View view){
        LinearLayout totalMenu = (LinearLayout) findViewById(R.id.totalmenu);
        totalMenu.setVisibility(View.VISIBLE);
        if (quantity != 0)
            addToCart();
        clickStatus = 1;
        io(clickStatus);

    }
    public void  decr(View view){
        LinearLayout totalMenu = (LinearLayout) findViewById(R.id.totalmenu);
        totalMenu.setVisibility(View.VISIBLE);
        if (quantity != 0)
            addToCart();
        clickStatus = 0;
        io(clickStatus);

    }



    public void  qincr(View view){
        if (quantity < 5){
            quantity++;
            displayQuantity();
        }
        if(quantity >= 5){
            makeToast("Max 5 cups!");
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
        displayQuantityTV.setText(quantity + "\nCup(s)");
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
        if(coffeeIndex >= 6){
            coffeeIndex = 0;
        }
        if (coffeeIndex <= -1){
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
    }
    void displayCoffeePic(int CI){
        ImageView coffeePictureIV = (ImageView) findViewById(R.id.coffeePicture);
        if (CI >= 0 && CI <= 5) {
            coffeePictureIV.setImageResource(picArray[CI]);
        }
    }
    void displayCoffeePrice(int CI){
        TextView coffeePriceTV = (TextView) findViewById(R.id.coffeePriceTextView);
        coffeePriceTV.setVisibility(View.VISIBLE);
        LinearLayout linerbttmTV = (LinearLayout) findViewById(R.id.linerbttm);
        linerbttmTV.setVisibility(View.VISIBLE);
        String total = NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(basePrice[CI]);
        coffeePriceTV.setText(total);
    }

    /***
     *  Extra addons
     *
     */
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
    int getAddOnPrice(){
        if (XtrachinaStatus == 1 && XtrachocoStatus == 1)
            return (addOn[0] + addOn[1]);
        else if (XtrachinaStatus == 1)
            return addOn[0];
        else if (XtrachocoStatus == 1)
            return addOn[1];
        else
            return 0;
    }
    /***
     *  Extra addons ends
     */




    /***************************************************
     * *************************************************
     *
     * Size Methods Starts
     *
     * ***************************************************
     * ***************************************************/
    int coffeeSizeIndex ;
    public void sizeS(View view){
        coffeeSizeIndex = 0;
        sizeExecute();
    }
    public void sizeL(View view){
        coffeeSizeIndex = 1;
        sizeExecute();
    }

    void sizeExecute(){
        TextView smallTV = (TextView) findViewById(R.id.small);

        TextView largeTV = (TextView) findViewById(R.id.large);
        sizeReset();
        switch (coffeeSizeIndex){
            case 0:{
                smallTV.setTextColor(Color.RED);
                break;
            }
            case 1:{
                smallTV.setTextColor(Color.parseColor("#211f21"));
                largeTV.setTextColor(Color.RED);
                break;
            }
        }
    }
    void sizeReset(){
        TextView smallTV = (TextView) findViewById(R.id.small);
        TextView largeTV = (TextView) findViewById(R.id.large);
        smallTV.setTextColor(Color.RED);
        largeTV.setTextColor(Color.parseColor("#211f21"));

    }
    /***************************************************
     * *************************************************
     *
     * Size Methods Emds
     *
     * ***************************************************
     * ****************************************************/

    void priceAlgo(){
            try {
                    cart[cupCounter][0] = coffeeIndex;
                    cart[cupCounter][1] = coffeeSizeIndex;
                    cart[cupCounter][2] = XtrachinaStatus;
                    cart[cupCounter][3] = XtrachocoStatus;
                    cart[cupCounter][4] = quantity;
                    cart[cupCounter][5] = ( (basePrice[coffeeIndex] + coffeeSize[coffeeSizeIndex] + getAddOnPrice()) * quantity);
                    cupCounter++;
            }catch (Exception e){
                makeToast("" + e);
            }

    }

    void addToCart(){
        if (cupCounter < 5) {
            if (quantity > 0) {
                //makeToast("added");
                priceAlgo();
                makeToast(coffeeTypes[cart[(cupCounter - 1)][0]] + " added");
                resetAll();
            } else {
                makeToast("Increase Quantity");
            }
        }
            else {
            LinearLayout layout1TV = (LinearLayout) findViewById(R.id.layout1);
            layout1TV.setVisibility(View.INVISIBLE);
            LinearLayout linerButton = (LinearLayout) findViewById(R.id.linerbttm);
            linerButton.setVisibility(View.INVISIBLE);
            LinearLayout linerButton2 = (LinearLayout) findViewById(R.id.linerbttm2);
            linerButton2.setVisibility(View.VISIBLE);
            LinearLayout layout2TV = (LinearLayout) findViewById(R.id.layout2);
            layout2TV.setVisibility(View.VISIBLE);
            setOrderList();
            }
        resetAll();
    }
    public void checkout(View view){
        if (quantity != 0 || cupCounter != 0){
            int i = displayAll();
            if(i == 1){
                LinearLayout layout1TV = (LinearLayout) findViewById(R.id.layout1);
                layout1TV.setVisibility(View.INVISIBLE);
                LinearLayout linerButton = (LinearLayout) findViewById(R.id.linerbttm);
                linerButton.setVisibility(View.INVISIBLE);
                LinearLayout linerButton2 = (LinearLayout) findViewById(R.id.linerbttm2);
                linerButton2.setVisibility(View.VISIBLE);
                LinearLayout layout2TV = (LinearLayout) findViewById(R.id.layout2);
                layout2TV.setVisibility(View.VISIBLE);
                setOrderList();
            }
        }
    }
    void setOrderList(){
        for (int i=0;i<6;i++){
            if (i < cupCounter)
            {
                switch (i){


                    case 0:{
                        TextView OrderDetail1_1TV = (TextView) findViewById(R.id.OrderDetail1_1);
                        TextView OrderDetail1_2TV = (TextView) findViewById(R.id.OrderDetail1_2);
                        TextView OrderDetail1_3TV = (TextView) findViewById(R.id.OrderDetail1_3);
                        TextView OrderDetail1_4TV = (TextView) findViewById(R.id.OrderDetail1_4);
                        TextView OrderDetail1_5TV = (TextView) findViewById(R.id.OrderDetail1_5);

                        OrderDetail1_1TV.setText(coffeeTypes[cart[0][0]] + " -");

                        if (cart[0][1] == 0){
                            OrderDetail1_2TV.setText("Regular");
                        }else if (cart[0][1] == 1){
                            OrderDetail1_2TV.setText("King");
                        }

                        if (cart[0][2] == 1 && cart[0][3] == 1){
                            OrderDetail1_3TV.setText("with extra chinnamon and \n" +
                                    " coffee syryp");
                        }
                        if (cart[0][2] == 1 && cart[0][3] == 0){
                            OrderDetail1_3TV.setText("with extra chinnamon.");
                        }
                        if (cart[0][2] == 0 && cart[0][3] == 1){
                            OrderDetail1_3TV.setText("with extra coffee syryp.");
                        }
                        if (cart[0][2] == 0 && cart[0][3] == 0){
                            OrderDetail1_3TV.setText("Nothing extra added.");
                        }
                        OrderDetail1_4TV.setText("Quantity " + cart[0][4] + " cup(s)");
                        OrderDetail1_5TV.setText("Total - " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(cart[0][5]));
                        break;
                    }




                    case 1:{
                        TextView OrderDetail2_1TV = (TextView) findViewById(R.id.OrderDetail2_1);
                        TextView OrderDetail2_2TV = (TextView) findViewById(R.id.OrderDetail2_2);
                        TextView OrderDetail2_3TV = (TextView) findViewById(R.id.OrderDetail2_3);
                        TextView OrderDetail2_4TV = (TextView) findViewById(R.id.OrderDetail2_4);
                        TextView OrderDetail2_5TV = (TextView) findViewById(R.id.OrderDetail2_5);

                        OrderDetail2_1TV.setText(coffeeTypes[cart[1][0]] + " -");

                        if (cart[1][1] == 0){
                            OrderDetail2_2TV.setText("Regular");
                        }else if (cart[1][1] == 1){
                            OrderDetail2_2TV.setText("King");
                        }

                        if (cart[1][2] == 1 && cart[1][3] == 1){
                            OrderDetail2_3TV.setText("with extra chinnamon and \n" +
                                    " coffee syryp");
                        }
                        if (cart[1][2] == 1 && cart[1][3] == 0){
                            OrderDetail2_3TV.setText("with extra chinnamon.");
                        }
                        if (cart[1][2] == 0 && cart[1][3] == 1){
                            OrderDetail2_3TV.setText("with extra coffee syryp.");
                        }
                        if (cart[1][2] == 0 && cart[1][3] == 0){
                            OrderDetail2_3TV.setText("Nothing extra added.");
                        }
                        OrderDetail2_4TV.setText("" + cart[1][4]);
                        OrderDetail2_5TV.setText("Total - " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(cart[1][5]));
                        break;
                    }




                    case 2:{

                        TextView OrderDetail3_1TV = (TextView) findViewById(R.id.OrderDetail3_1);
                        TextView OrderDetail3_2TV = (TextView) findViewById(R.id.OrderDetail3_2);
                        TextView OrderDetail3_3TV = (TextView) findViewById(R.id.OrderDetail3_3);
                        TextView OrderDetail3_4TV = (TextView) findViewById(R.id.OrderDetail3_4);
                        TextView OrderDetail3_5TV = (TextView) findViewById(R.id.OrderDetail3_5);

                        OrderDetail3_1TV.setText(coffeeTypes[cart[2][0]] + " -");
                        if (cart[2][1] == 0){
                            OrderDetail3_2TV.setText("Regular");
                        }else if (cart[2][1] == 1){
                            OrderDetail3_2TV.setText("King");
                        }

                        if (cart[2][2] == 1 && cart[2][3] == 1){
                            OrderDetail3_3TV.setText("with extra chinnamon and \n" +
                                    " coffee syryp");
                        }
                        if (cart[2][2] == 1 && cart[2][3] == 0){
                            OrderDetail3_3TV.setText("with extra chinnamon.");
                        }
                        if (cart[2][2] == 0 && cart[2][3] == 1) {
                            OrderDetail3_3TV.setText("with extra coffee syryp.");
                        }
                        if (cart[2][2] == 0 && cart[2][3] == 0){
                            OrderDetail3_3TV.setText("Nothing extra added.");
                        }
                        OrderDetail3_4TV.setText("" + cart[2][4]);
                        OrderDetail3_5TV.setText("Total - " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(cart[2][5]));
                        break;

                    }





                    case 3:{
                        TextView OrderDetail4_1TV = (TextView) findViewById(R.id.OrderDetail4_1);
                        TextView OrderDetail4_2TV = (TextView) findViewById(R.id.OrderDetail4_2);
                        TextView OrderDetail4_3TV = (TextView) findViewById(R.id.OrderDetail4_3);
                        TextView OrderDetail4_4TV = (TextView) findViewById(R.id.OrderDetail4_4);
                        TextView OrderDetail4_5TV = (TextView) findViewById(R.id.OrderDetail4_5);

                        OrderDetail4_1TV.setText(coffeeTypes[cart[3][0]] + " -");
                        if (cart[3][1] == 0){
                            OrderDetail4_2TV.setText("Regular");
                        }else if (cart[3][1] == 1){
                            OrderDetail4_2TV.setText("King");
                        }

                        if (cart[3][2] == 1 && cart[3][3] == 1){
                            OrderDetail4_3TV.setText("with extra chinnamon and \n" +
                                    " coffee syryp");
                        }
                        if (cart[3][2] == 1 && cart[3][3] == 0){
                            OrderDetail4_3TV.setText("with extra chinnamon.");
                        }
                        if (cart[3][2] == 0 && cart[3][3] == 1) {
                            OrderDetail4_3TV.setText("with extra coffee syryp.");
                        }
                        if (cart[3][2] == 0 && cart[3][3] == 0){
                            OrderDetail4_3TV.setText("Nothing extra added.");
                        }
                        OrderDetail4_4TV.setText("" + cart[3][4]);
                        OrderDetail4_5TV.setText("Total - " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(cart[3][5]));
                        break;

                    }




                    case 4:{
                        TextView OrderDetail5_1TV = (TextView) findViewById(R.id.OrderDetail5_1);
                        TextView OrderDetail5_2TV = (TextView) findViewById(R.id.OrderDetail5_2);
                        TextView OrderDetail5_3TV = (TextView) findViewById(R.id.OrderDetail5_3);
                        TextView OrderDetail5_4TV = (TextView) findViewById(R.id.OrderDetail5_4);
                        TextView OrderDetail5_5TV = (TextView) findViewById(R.id.OrderDetail5_5);

                        OrderDetail5_1TV.setText(coffeeTypes[cart[4][0]] + " -");
                        if (cart[4][1] == 0){
                            OrderDetail5_2TV.setText("Regular");
                        }else if (cart[4][1] == 1){
                            OrderDetail5_2TV.setText("King");
                        }

                        if (cart[4][2] == 1 && cart[4][3] == 1){
                            OrderDetail5_3TV.setText("with extra chinnamon and \n" +
                                    " coffee syryp");
                        }
                        if (cart[4][2] == 1 && cart[4][3] == 0){
                            OrderDetail5_3TV.setText("with extra chinnamon.");
                        }
                        if (cart[4][2] == 0 && cart[4][3] == 1) {
                            OrderDetail5_3TV.setText("with extra coffee syryp.");
                        }
                        if (cart[4][2] == 0 && cart[4][3] == 0){
                            OrderDetail5_3TV.setText("Nothing extra added.");
                        }
                        OrderDetail5_4TV.setText("" + cart[4][4]);
                        OrderDetail5_5TV.setText("Total - " + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(cart[4][5]));
                        break;
                    }

                }
            }else{
                switch (i){
                    case 0:{
                        RelativeLayout OrderDetail1 = (RelativeLayout) findViewById(R.id.orderDetail1);
                        OrderDetail1.setVisibility(View.GONE);
                        break;
                    }
                    case 1:{
                        RelativeLayout OrderDetail2 = (RelativeLayout) findViewById(R.id.orderDetail2);
                        OrderDetail2.setVisibility(View.GONE);
                        break;
                    }
                    case 2:{
                        RelativeLayout OrderDetail3 = (RelativeLayout) findViewById(R.id.orderDetail3);
                        OrderDetail3.setVisibility(View.GONE);
                        break;
                    }
                    case 3:{
                        RelativeLayout OrderDetail4 = (RelativeLayout) findViewById(R.id.orderDetail4);
                        OrderDetail4.setVisibility(View.GONE);
                        break;
                    }
                    case 4:{
                        RelativeLayout OrderDetail5 = (RelativeLayout) findViewById(R.id.orderDetail5);
                        OrderDetail5.setVisibility(View.GONE);
                        break;
                    }
                }
            }
        }

    }
    void resetAll(){
        quantity = 0;
        sizeReset();
        XtrachinaStatus = 0;
        XtrachocoStatus = 0;
        totalPrice = 0;
        displayQuantity();
        TextView xtraChinaTV = (TextView) findViewById(R.id.xtraChinaTextView);
        TextView xtraChocoTV = (TextView) findViewById(R.id.xtraChoco);
        xtraChinaTV.setTextColor(Color.parseColor("#211f21"));
        xtraChocoTV.setTextColor(Color.parseColor("#211f21"));
    }
    int displayAll(){
        addToCart();
        return 1;
    }
    /**3
     *   ADD COFFEE
     */
    public void addCoffee(View view){
        addToCart();
    }
    /***
     *   ADD COFFEE END
     */
    /***
     * CANCEL ORDER methods
     **/
    int cancelOrderIndex;
    public void canOrd1(View view){
        cancelOrderIndex = 0;
        removeFromCart();
        RelativeLayout OrderDetail1 = (RelativeLayout) findViewById(R.id.orderDetail1);
        OrderDetail1.setVisibility(View.GONE);
    }
    public void canOrd2(View view) {
        cancelOrderIndex = 1;
        removeFromCart();
        RelativeLayout OrderDetail2 = (RelativeLayout) findViewById(R.id.orderDetail2);
        OrderDetail2.setVisibility(View.GONE);
    }
    public  void canOrd3(View view){
        cancelOrderIndex = 2;
        removeFromCart();
        RelativeLayout OrderDetail3 = (RelativeLayout) findViewById(R.id.orderDetail3);
        OrderDetail3.setVisibility(View.GONE);
    }
    public void canOrd4(View view){
        cancelOrderIndex = 3;
        removeFromCart();
        RelativeLayout OrderDetail4 = (RelativeLayout) findViewById(R.id.orderDetail4);
        OrderDetail4.setVisibility(View.GONE);
    }
    public void canOrd5(View view){
        cancelOrderIndex = 4;
        removeFromCart();
        RelativeLayout OrderDetail5 = (RelativeLayout) findViewById(R.id.orderDetail5);
        OrderDetail5.setVisibility(View.GONE);
    }
    void removeFromCart(){
        for (int i=cancelOrderIndex; i < 5 ; i++){
            for (int j=0; j <= 4; j++){
                cart[i][j] = cart[i + 1][j];
            }
        }
        cupCounter--;
    }
    /***
     *
     * CANCEL ORDER methods end here
     */
    public void confirmOrder(View view){
        for (int i=0; i<cupCounter;i++)
        {
            totalPrice = totalPrice + cart[i][5];
        }
        LinearLayout layout2TV = (LinearLayout) findViewById(R.id.layout2);
        layout2TV.setVisibility(View.INVISIBLE);
        LinearLayout linerButton2 = (LinearLayout) findViewById(R.id.linerbttm2);
        linerButton2.setVisibility(View.INVISIBLE);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout3.setVisibility(View.VISIBLE);
        TextView tpTV = (TextView) findViewById(R.id.tp);
        tpTV.setText("" + NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(totalPrice));
    }


    public void makeToast(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
        toast.show();
    }




}
