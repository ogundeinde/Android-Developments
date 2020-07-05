/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;

import static android.content.Intent.ACTION_VIEW;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean aBoolean, bBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        EditText editText = (EditText) findViewById(R.id.name_field);
        String name = editText.getText().toString();
        int price = calculatePrice();
        displayPrice(price, name);

//        Intent intent = new Intent(ACTION_VIEW);
//        intent.setData(Uri.parse("geo:6.5244, 3.3792"));
//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }

        Intent intent =  new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java mailto" + name);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }


    }

    public void increaseOrder(View view) {
        quantity += 1;
        display(quantity);
    }

    public void decreaseOrder(View view) {
        if(quantity > 0){
            quantity -= 1;
        }
        display(quantity);
    }

    /**
     * This method displays the given price on the screen.
     */

    private void displayPrice(int number, String name) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(String.format("Name: %s\nAdd whipped cream? %s\nAdd Chocolate? %s\nQuantity: %s\nTotal: %s\nThank you", name, bBoolean, aBoolean, quantity, NumberFormat.getCurrencyInstance().format(number)));
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(){
        int price = quantity * 2;
        if(aBoolean){
            price += 1;
        }
        if(bBoolean){
            price += 2;
        }
        return price;
    }

    public void addWhippedCream(View view){
        aBoolean = true;
    }

    public void addChocolate(View view){
        bBoolean = true;
    }

}