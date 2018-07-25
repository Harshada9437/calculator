package com.example.carufus.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private GridLayout gridLayout;
    private Button button;
    Double reesult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        textView = (TextView) findViewById(R.id.input);
        int child_count = gridLayout.getChildCount();
        for (int i = 0; i < child_count; i++) {
            gridLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button =(Button)view;
                    String button_txt = button.getText().toString();
                    if(button_txt.equals("c")){
                        String data = textView.getText().toString();
                        int length = data.length();
                        if(length==1){
                            textView.setText("0");
                        }else {
                            textView.setText(data.substring(0,length-1));
                        }
                    }else if(button_txt.equals("DEL")){
                        textView.setText("0");
                    }else if(!button_txt.equals("=")){
                        if(textView.getText().toString().length()==1  && textView.getText().equals("0") && isNumeric(button_txt)){
                            textView.setText(button_txt);
                        }else {
                            textView.setText(textView.getText().toString() + button_txt);
                        }
                    }else{
                        String expression = textView.getText().toString();
                        try {
                            Expression exp = new ExpressionBuilder(expression).build();
                            reesult = exp.evaluate();

                            textView.setText(expression + " \n= " + String.valueOf(reesult));
                        }catch (ArithmeticException e){
                            textView.setText(expression + " \n= " + "ERROR");
                        }
                    }

                }
            });
        }

    }
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
