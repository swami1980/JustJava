package com.example.android.justjava; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static com.example.android.justjava.R.id.CustName;
import static com.example.android.justjava.R.id.chocolate_cream;
import static com.example.android.justjava.R.id.quantity_text_view;
import static com.example.android.justjava.R.id.whipped_cream;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int cost = 5;
    int price;
    int totPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        String s = quantityTextView.getText().toString();
        price = Integer.parseInt(s);
        totPrice = calculatePrice();
        createOrderSummary(totPrice);

    }


    public int calculatePrice() {

        totPrice = totPrice + (price * cost);

        return totPrice;

    }

    public void createOrderSummary(int total) {


        CheckBox cb = (CheckBox) findViewById(whipped_cream);
        CheckBox cb1 = (CheckBox) findViewById(chocolate_cream);
        EditText et = (EditText) findViewById(CustName);

        String name = et.getText().toString();
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        if (cb.isChecked() && cb1.isChecked()) {
            total = total + (price*4);
        }
        else if
                (cb.isChecked() || cb1.isChecked()) {
            total = total + (price*2);


        }
        else
        {
            total=total+0;
        }
       // priceTextView.setText(" Name : " + name + " \n Quantity : " + NumberFormat.getCurrencyInstance().format(total) + "\n Addons : " + "\n WhippedCream " + cb.isChecked() + "\n Chocolate " + cb1.isChecked() + "\n Total :" + price + " \n thank you");

          {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
              String addresses ="swami.srinivasan80@gmail.com";
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
              String subject ="Coffee order";
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
              String text = " Name : " + name + " \n Quantity : " + NumberFormat.getCurrencyInstance().format(total) + "\n Addons : " + "\n WhippedCream " + cb.isChecked() + "\n Chocolate " + cb1.isChecked() + "\n Total :" + price + "\n " + getString(R.string.thank_you);
              intent.putExtra(Intent.EXTRA_TEXT, text);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number, int price) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Amount due " + NumberFormat.getCurrencyInstance().format(number) + " for ordering " + price + " \n thank you");
    }

    public void increment(View view) {

        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        String s = quantityTextView.getText().toString();
        int increment = Integer.parseInt(s);
        if (increment > 100) {
            Context context = getApplicationContext();
            CharSequence text = "You have exceeded the max limit";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {

        increment = Integer.parseInt(s) + 1;
    }
        quantityTextView.setText("" + increment);


    }

    public void decrement(View view) {

        TextView quantityTextView = (TextView) findViewById(quantity_text_view);
        String s = quantityTextView.getText().toString();
        int decrement = Integer.parseInt(s);
        if (decrement <= 1) {
            Context context = getApplicationContext();
            CharSequence text = "You have exceeded the minimum limit";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else {

            decrement = Integer.parseInt(s) - 1;
        }
        quantityTextView.setText("" + decrement);
    }

}
