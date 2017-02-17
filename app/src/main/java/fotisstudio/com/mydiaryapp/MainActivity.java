package fotisstudio.com.mydiaryapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText enterText;
    private Button saveButton;

    private final String FILE_NAME = "diary.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterText = (EditText)findViewById(R.id.enterText);

        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enterText.getText().toString().equals("")){
                    String data = enterText.getText().toString();
                    writeToFile(data);
                }else{
                    Toast.makeText(MainActivity.this, "Please Enter Text!",Toast.LENGTH_LONG).show();
                }
            }
        });// click listener

        // check if the file is present
        // if the file exists get the text and intialize the EditText
        if(readFromFile() != null){
            enterText.setText(readFromFile());
        }else{
            //TODO
        }
    }// onCreate()

    private void writeToFile(String data){
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close(); // close the stream
        }catch(IOException e){
            Log.v("MyActivity", e.toString());
        }// try-catch
    }// writeToFile()

    private String readFromFile(){
        String result = "";

        try{
            InputStream inputStream = openFileInput(FILE_NAME);

            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while((tempString = bufferedReader.readLine()) != null){
                    stringBuilder.append(tempString);
                }// while

                inputStream.close();
                result = stringBuilder.toString();
            }
        }catch(FileNotFoundException e){
            Log.v("MyActivity", e.toString());
        } catch (IOException e) {
            Log.v("MyActivity", e.toString());
        }

        return result;
    }// readFromFile()
}// class
