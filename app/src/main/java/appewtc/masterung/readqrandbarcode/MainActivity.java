package appewtc.masterung.readqrandbarcode;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TextView textView;
    private static final String STRING = "com.google.zxing.client.android.SCAN";
    private String[] modeStrings = new String[]{"QR_CODE_MODE", "BAR_CODE_MODE"};
    private int[] modeInts = new int[]{0,1};
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txtResult);
        listView = (ListView) findViewById(R.id.livCode);

        stringArrayList = new ArrayList<String>();

        //Create ListView
        if (stringArrayList.size() > 0) {
            createListView();
        }


    }   // Main Method

    private void createListView() {

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(stringArrayAdapter);

    }   // createListView

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            String[] strings = new String[]{"QR code = ", "BAR code = "};
            result = data.getStringExtra("SCAN_RESULT");
            textView.setText(strings[requestCode] + result);
            stringArrayList.add(result);
            createListView();

        }   // if

    }   // onActivity

    public void clickQRcode(View view) {

        myScan(modeStrings[0], modeInts[0]);

    }   // QRcode


    public void clickBARcode(View view) {

        myScan(modeStrings[1], modeInts[1]);

    }   // BARcode

    private void myScan(String modeString, int modeInt) {

        try {

            Intent intent = new Intent(STRING);
            intent.putExtra("SCAN_MODE", modeString);
            startActivityForResult(intent, modeInt);


        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Please Install Barcode Scanner",
                    Toast.LENGTH_SHORT).show();
        }

    }   // myScan


}   // Main Class
