package com.example.win81.bab_showtext;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpectAvtivity extends AppCompatActivity implements View.OnClickListener, ManageAddressBar.ManageAddressBarEventHandler {
    private Button btnAdd, btnRemoveWithPos, btnRemoveWithText;
    private EditText tvInsertData;
    private View currentView;
    ManageAddressBar manageAddressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentView = inflater.inflate(R.layout.activity_expect_avtivity, null);
        setContentView(currentView);


        btnAdd = (Button) findViewById(R.id.btn_add_to_list);
        btnRemoveWithPos = (Button) findViewById(R.id.btn_remove_from_list_with_pos);
        btnRemoveWithText = (Button) findViewById(R.id.btn_remove_from_list_with_text);
        tvInsertData = (EditText) findViewById(R.id.et_insert_data);

        btnAdd.setOnClickListener(this);
        btnRemoveWithText.setOnClickListener(this);
        btnRemoveWithPos.setOnClickListener(this);
//--------------------------------------------------------------------------------------------------

        manageAddressBar = new ManageAddressBar(this, currentView, R.id.recyclerView, this);


    }

    //start-------------------------------------------Manage Onclick AddressBar---------------------


    @Override
    public void onItemClickAddressBar(int position, String str) {
        Toast.makeText(ExpectAvtivity.this, str+position, Toast.LENGTH_SHORT).show();
    }
    //End-------------------------------------------Manage Onclick AddressBar-----------------------

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_to_list:
                manageAddressBar.addItem(tvInsertData.getText().toString());
                break;
            case R.id.btn_remove_from_list_with_pos:
                manageAddressBar.removeItemWithPosition(Integer.parseInt(tvInsertData.getText().toString()));
                break;
            case R.id.btn_remove_from_list_with_text:
                manageAddressBar.removeItemWithText(tvInsertData.getText().toString());
                break;


        }
    }
}
