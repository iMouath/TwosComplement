package com.example.aweso.twoscomplement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    private TextView answerTV;
    private EditText inputET;
    private Switch negativeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.answerTV = (TextView)this.findViewById(R.id.answerTV);
        this.inputET = (EditText)this.findViewById(R.id.inputET);
        this.negativeSwitch = (Switch)this.findViewById(R.id.negativeSwitch);
    }

    private String flipTheBits(String bin)
    {
        String answer = "";
        for(int i = 0; i < bin.length(); i++)
        {
            answer += bin.charAt(i) == '0' ? '1' : '0';
        }
        return answer;
    }

    private String addOne(String bin)
    {
        //will return a new String that is the given String with 1 added to it
        StringBuilder answer = new StringBuilder(bin);
        for(int i = 0; i < answer.length()-1; i++){
            if(bin.charAt(i) == '0'){
                answer.setCharAt(i, '1');
                break;
            }else{
                answer.setCharAt(i, '0');
            }
        }
        return String.valueOf(answer.reverse()); //returned it reversed cause I built it backward, and no need to rewrite the loop
    }

    private String encodeAsTwosComplement(String bin)
    {
        String bitsFlipped = this.flipTheBits(bin);
        String oneAdded = this.addOne(bitsFlipped);
        return oneAdded;
    }

    public void onConvertButtonPressed(View v)
    {
        String theBinaryNumber = this.inputET.getText().toString();

        if(this.negativeSwitch.isChecked())
        {
            this.answerTV.setText(this.encodeAsTwosComplement(theBinaryNumber));
        }
        else
        {
            this.answerTV.setText(theBinaryNumber);
        }

    }
}
