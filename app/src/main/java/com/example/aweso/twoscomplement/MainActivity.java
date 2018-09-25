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

    private String addOne2(String bin)
    {
        return Integer.toBinaryString(Integer.parseInt(bin, 2) + 1);
    }

    private int binaryToDecimal(String bin)
    {
        int place = 1;
        int sum = 0;
        for(int i = bin.length()-1; i >= 0; i--)
        {

            sum += (place * (bin.charAt(i) == '0'?0:1));
            place *= 2;
        }
        return sum;
    }

    private String decimalToBinary(int n)
    {
        String answer = "";
        while(n != 0)
        {
            answer = (n%2) + answer;
            n /= 2;
        }
        return answer;
    }

    private String addOne3(String bin)
    {
        return this.decimalToBinary(this.binaryToDecimal(bin) + 1);
    }

    private String addOne(String bin)
    {
        //will return a new String that is the given String with 1 added to it
        int carry = 0;
        String temp = "";
        for(int i = 0; i < bin.length()-1; i++)
        {
            temp += "0";
        }
        temp += "1";

        String answer = "";
        //carry = 0
        //"01000
        //"10111"
        //"00001"
        //"11000"

        for(int i = bin.length() - 1; i >= 0; i--)
        {
            int n1 = bin.charAt(i) == '0'?0:1;
            int n2 = temp.charAt(i) == '0'?0:1;
            int sum = n1 + n2 + carry;
            if(sum < 2)
            {
                carry = 0;
                answer = sum + answer;
            }
            else if(sum == 2)
            {
                carry = 1;
                answer = 0 + answer;
            }
            else
            {
                carry = 1;
                answer = 1 + answer;
            }
        }
        if(carry == 1)
        {
            answer = 1 + answer;
        }
        return answer;
    }

    private String encodeAsTwosComplement(String bin)
    {
        String bitsFlipped = this.flipTheBits(bin);
        String oneAdded = this.addOne3(bitsFlipped);
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
