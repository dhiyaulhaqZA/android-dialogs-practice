package id.duza.dialogpractice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnShowAlert;
    private Button btnShowList;
    private Button btnShowMultiChoice;
    private Button btnCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        setupViewListener();
    }

    private void setupView() {
        btnShowAlert = (Button) findViewById(R.id.btn_alert_dialog);
        btnShowList = (Button) findViewById(R.id.btn_list_dialog);
        btnShowMultiChoice = (Button) findViewById(R.id.btn_multichoice_dialog);
        btnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);
    }

    private void setupViewListener() {
        btnShowAlert.setOnClickListener(this);
        btnShowList.setOnClickListener(this);
        btnShowMultiChoice.setOnClickListener(this);
        btnCustomDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())   {
            case R.id.btn_alert_dialog:
                showAlert();
                break;
            case R.id.btn_list_dialog:
                showList();
                break;
            case R.id.btn_multichoice_dialog:
                showMultiChoiceItem();
                break;
            case R.id.btn_custom_dialog:
                showCustomDialog();
                break;
        }
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Show toast?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Wah keren!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void showList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] str = new String[]{"Lollipop", "Marshmallow", "Nougat"};
        builder.setTitle("Android Version")
                .setItems(str, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, str[i], Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showMultiChoiceItem()  {
        final List<String> list = new ArrayList<>();
        final String[] str = new String[]{"Lollipop", "Marshmallow", "Nougat"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Android Version")
                .setMultiChoiceItems(str, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        if (isChecked)  {
                            list.add(str[i]); //store selected items in the list
                        } else if (list.contains(str[i]))   {
                            // if list is contains str[i], it means str[i] is already checked
                            // remove str[i] from list
                            list.remove(str[i]);
                        }
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String stringList = "";
                        //Show checked items
                        for (int i = 0; i < list.size(); i++)   {
                            stringList += list.get(i) + (i==list.size()-1?".":", "); //Lollipop, Nougat.
                        }
                        if (!stringList.isEmpty()) { // If stringList is not empty
                            Toast.makeText(MainActivity.this, stringList, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void showCustomDialog() {
        // show custom dialog
        CustomDialog customDialog = new CustomDialog();
        customDialog.show(getFragmentManager(), "dialog");
    }
}
