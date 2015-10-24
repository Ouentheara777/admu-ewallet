package app.ewallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ActionBarActivity {
    LocalShopHandler dbShop;
    Boolean atLeastOne = false;
    String item1Name = "";
    String item2Name = "";
    String item3Name = "";
    String item4Name = "";
    String item1Price = "";
    String item2Price = "";
    String item3Price = "";
    String item4Price = "";
    //EditText itemEt1, itemEt2, itemEt3, itemEt4;
    //EditText qtyEt1, qtyEt2, QtyEt3, QtyEt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //String message = intent.getStringExtra(MainActivity.Da_number);;

        //  Student student = db.getStudent(Integer.parseInt(message));
        //setContentView(textView);
        dbShop = new LocalShopHandler(this);
        updateDatabase(dbShop);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void updateDatabase(LocalShopHandler db) {
        Item item1 = new Item(1 , "Non-Colored Photocopy", 0.75);
        Item item2 = new Item(2 , "Colored Photocopy", 3.50);
        Item item3 = new Item(3 , "Printing", 4.00);
        Item item4 = new Item(4 , "Colored Printing", 7.75);
        Item item5 = new Item(5 , "Adobo Rice", 80.00);

        if (!db.checkExist(item1.getID())) {
            dbShop.addItem(item1);
        } else { }
        if (!db.checkExist(item2.getID())) {
            dbShop.addItem(item2);
        } else { }
        if (!db.checkExist(item3.getID())) {
            dbShop.addItem(item3);
        } else { }
        if (!db.checkExist(item4.getID())) {
            dbShop.addItem(item4);
        } else { }
        if (!db.checkExist(item5.getID())) {
            dbShop.addItem(item5);
        } else { }
    }

    public void onConfirmItems(View view) {
        Intent intent = new Intent(this, MainActivity2.class);


        EditText qtyEt1 = (EditText) findViewById(R.id.qty_editText1);
        EditText qtyEt2 = (EditText) findViewById(R.id.qty_editText2);
        EditText qtyEt3 = (EditText) findViewById(R.id.qty_editText3);
        EditText qtyEt4 = (EditText) findViewById(R.id.qty_editText4);
        String qty1 = qtyEt1.getText().toString();
        String qty2 = qtyEt2.getText().toString();
        String qty3 = qtyEt3.getText().toString();
        String qty4 = qtyEt4.getText().toString();


        EditText itemEt1 = (EditText) findViewById(R.id.item_editText1);
        EditText itemEt2 = (EditText) findViewById(R.id.item_editText2);
        EditText itemEt3 = (EditText) findViewById(R.id.item_editText3);
        EditText itemEt4 = (EditText) findViewById(R.id.item_editText4);
        String item1 = itemEt1.getText().toString();
        String item2 = itemEt2.getText().toString();
        String item3 = itemEt3.getText().toString();
        String item4 = itemEt4.getText().toString();


        if(!item1.equals(""))
        {
            int item1Int = Integer.parseInt(item1);
            Item actualItem1 = dbShop.getItem(item1Int);
            item1Name = actualItem1.getName();
            item1Price = String.valueOf(actualItem1.getCost());
        }
        else
        {
            item1Name = "";
            item1Price = "";
        }
        intent.putExtra("item1", item1Name);
        intent.putExtra("item1Price", item1Price);
        intent.putExtra("qty1", qty1);


        if(!item2.equals(""))
        {
            int item2Int = Integer.parseInt(item2);
            Item actualItem2 = dbShop.getItem(item2Int);
            item2Name = actualItem2.getName();
            item2Price = String.valueOf(actualItem2.getCost());
        }
        else
        {
            item2Name = "";
            item2Price = "";
        }
        intent.putExtra("item2", item2Name);
        intent.putExtra("item2Price", item2Price);
        intent.putExtra("qty2", qty2);

        if(!item3.equals(""))
        {
            int item3Int = Integer.parseInt(item3);
            Item actualItem3 = dbShop.getItem(item3Int);
            item3Name = actualItem3.getName();
            item3Price = String.valueOf(actualItem3.getCost());
        }
        else
        {
            item3Name = "";
            item3Price = "";
        }
        intent.putExtra("item3", item3Name);
        intent.putExtra("item3Price", item3Price);
        intent.putExtra("qty3", qty3);


        if(!item4.equals(""))
        {
            int item4Int = Integer.parseInt(item4);
            Item actualItem4 = dbShop.getItem(item4Int);
            item4Name = actualItem4.getName();
            item4Price = String.valueOf(actualItem4.getCost());
        }
        else
        {
            item4Name = "";
            item4Price = "";
        }
        intent.putExtra("item4", item4Name);
        intent.putExtra("item4Price", item4Price);
        intent.putExtra("qty4", qty4);

        startActivity(intent);

    }
}