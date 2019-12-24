package ru.ooplabs.simple_calculator;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.widget.TextView;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private EditText expressionEditView;
    private TextView answerTextView;

    int leftBracketCount = 0;
    int rightBracketCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionEditView = findViewById(R.id.ExpressionEditView);
        answerTextView = findViewById(R.id.AnswerTextView);
        expressionEditView.setShowSoftInputOnFocus(false);

        expressionEditView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                answerTextView.setText(calculate());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private String calculate() {
        String txt = expressionEditView.getText().toString().replace('×', '*');
        txt = txt.replace('÷', '/');
        try {
            String result = Double.toString(new ExpressionBuilder(txt).build().evaluate());
            List<String> strs = Arrays.asList(result.split("\\s*\\.\\s*"));
            if (strs.get(1).equals("0"))
                return strs.get(0);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    public void onClearClick(View view) {
        expressionEditView.setText("");
    }

    public void onBracketClick(View view) {
        int start = (expressionEditView.isCursorVisible())? expressionEditView.getSelectionStart() : expressionEditView.getText().length();
        int end = (expressionEditView.isCursorVisible())? expressionEditView.getSelectionEnd() : start;
        String expressionEditViewStr = expressionEditView.getText().toString();
        String leftPartStr = expressionEditViewStr.substring(0, start);
        leftBracketCount = (int)leftPartStr.chars().filter(ch -> ch == '(').count();
        rightBracketCount = (int)leftPartStr.chars().filter(ch -> ch == ')').count();
        char prevChar = ' ';
        if (start != 0)
            prevChar = expressionEditViewStr.charAt(start-1);
        String resultStr;
        if (!"(+-×÷".contains(Character.toString(prevChar)) && leftBracketCount > rightBracketCount)
        {
            resultStr = ")";
            rightBracketCount++;
        }
        else
        {
            resultStr = "(";
            leftBracketCount++;
        }
        StringBuilder str = new StringBuilder(expressionEditViewStr).replace(start, end, resultStr);
        expressionEditView.setText(str);
        expressionEditView.setSelection(start + 1);
    }

    public void onBackspaceClick(View view) {
        String str = expressionEditView.getText().toString();
        if (str.length() > 0) {
             expressionEditView.setText(str.substring(0, str.length() - 1));
        }
        expressionEditView.setSelection(expressionEditView.getText().length());
    }

    public void onNumberClick(View view) {
        int start = (expressionEditView.isCursorVisible())? expressionEditView.getSelectionStart() : expressionEditView.getText().length();
        int end = (expressionEditView.isCursorVisible())? expressionEditView.getSelectionEnd() : start;
        StringBuilder str = new StringBuilder(expressionEditView.getText().toString()).replace(start, end, ((Button)view).getText().toString());
        expressionEditView.setText(str);
        expressionEditView.setSelection(start + 1);
    }

    public void onOperatorClick(View view) {
        int start = (expressionEditView.isCursorVisible())? expressionEditView.getSelectionStart() : expressionEditView.getText().length();
        int end = (expressionEditView.isCursorVisible())? expressionEditView.getSelectionEnd() : start;
        String inputStr = expressionEditView.getText().toString();
        String op = ((Button)view).getText().toString();
        if (start != 0)
        {
            String c = Character.toString(inputStr.charAt(start-1));
            if ("+-×÷".contains(c) && "+-×÷".contains(op))
            {
                start--;
            }

        }
        else
        {
            if ("+×÷".contains(op))
            {
                op = "";
            }
        }
        if (!"+-×÷".contains(op))
        {
            op = "(" + op;
        }
        StringBuilder outputStr = new StringBuilder(inputStr).replace(start, end, op);
        expressionEditView.setText(outputStr);
        expressionEditView.setSelection(start + op.length());

    }

    public void onPointClick(View view) {
        expressionEditView.setText(expressionEditView.getText() + ".");
        expressionEditView.setSelection(expressionEditView.getText().length());
    }

    public void onEqualClick(View view) {
        expressionEditView.setText(calculate());
        answerTextView.setText("");
        expressionEditView.setSelection(expressionEditView.getText().length());
    }
}
