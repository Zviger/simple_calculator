package ru.ooplabs.simple_calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BasicFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_basic, container, false);
        Button buttonDivide = v.findViewById(R.id.buttonDivide);
        Button buttonMultiply = v.findViewById(R.id.buttonMultiply);
        Button buttonMinus = v.findViewById(R.id.buttonMinus);
        Button buttonPlus = v.findViewById(R.id.buttonPlus);
        Button buttonClear = v.findViewById(R.id.buttonClear);
        Button buttonBrackets = v.findViewById(R.id.buttonBrackets);
        Button buttonBackspace = v.findViewById(R.id.buttonBackspace);
        Button button0 = v.findViewById(R.id.button0);
        Button button1 = v.findViewById(R.id.button1);
        Button button2 = v.findViewById(R.id.button2);
        Button button3 = v.findViewById(R.id.button3);
        Button button4 = v.findViewById(R.id.button4);
        Button button5 = v.findViewById(R.id.button5);
        Button button6 = v.findViewById(R.id.button6);
        Button button7 = v.findViewById(R.id.button7);
        Button button8 = v.findViewById(R.id.button8);
        Button button9 = v.findViewById(R.id.button9);
        Button buttonPoint = v.findViewById(R.id.buttonPoint);
        Button buttonEqual = v.findViewById(R.id.buttonEqual);

        buttonClear.setOnClickListener(this::onClearClick);
        buttonBrackets.setOnClickListener(this::onBracketsClick);
        buttonDivide.setOnClickListener(this::onOperatorClick);
        buttonMultiply.setOnClickListener(this::onOperatorClick);
        buttonMinus.setOnClickListener(this::onOperatorClick);
        buttonPlus.setOnClickListener(this::onOperatorClick);
        buttonBackspace.setOnClickListener(this::onBackspaceClick);
        button0.setOnClickListener(this::onNumberClick);
        button1.setOnClickListener(this::onNumberClick);
        button2.setOnClickListener(this::onNumberClick);
        button3.setOnClickListener(this::onNumberClick);
        button4.setOnClickListener(this::onNumberClick);
        button5.setOnClickListener(this::onNumberClick);
        button6.setOnClickListener(this::onNumberClick);
        button7.setOnClickListener(this::onNumberClick);
        button8.setOnClickListener(this::onNumberClick);
        button9.setOnClickListener(this::onNumberClick);
        buttonPoint.setOnClickListener(this::onPointClick);
        buttonEqual.setOnClickListener(this::onEqualClick);

        return v;
    }

    public void onClearClick(View view) {
        mListener.getExpressionET().setText("");
    }

    public void onBracketsClick(View view) {
        int leftBracketCount;
        int rightBracketCount;

        EditText et = mListener.getExpressionET();
        int start = (et.isCursorVisible())? et.getSelectionStart() : et.getText().length();
        int end = (et.isCursorVisible())? et.getSelectionEnd() : start;
        String expressionEditViewStr = et.getText().toString();
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
        }
        else
        {
            resultStr = "(";
        }
        StringBuilder str = new StringBuilder(expressionEditViewStr).replace(start, end, resultStr);
        et.setText(str);
        et.setSelection(start + 1);
    }

    public void onOperatorClick(View view) {
        EditText et = mListener.getExpressionET();

        int start = (et.isCursorVisible())? et.getSelectionStart() : et.getText().length();
        int end = (et.isCursorVisible())? et.getSelectionEnd() : start;
        String inputStr = et.getText().toString();
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
        if (!"+-×÷πe!^2".contains(op))
        {
            op = op + "(";
        }
        StringBuilder outputStr = new StringBuilder(inputStr).replace(start, end, op);
        et.setText(outputStr);
        et.setSelection(start + op.length());
    }

    public void onBackspaceClick(View view) {
        EditText et = mListener.getExpressionET();

        String str = et.getText().toString();
        if (str.length() > 0) {
            Pattern pattern = Pattern.compile("\\w+\\($");
            Matcher matcher = pattern.matcher(str);
            String newStr = matcher.replaceFirst("");
            if(!str.equals(newStr))
            {
                et.setText(newStr);
            }
            else
            {
                et.setText(str.substring(0, str.length() - 1));
            }
        }
        et.setSelection(et.getText().length());
    }

    public void onNumberClick(View view) {
        EditText et = mListener.getExpressionET();

        int start = (et.isCursorVisible())? et.getSelectionStart() : et.getText().length();
        int end = (et.isCursorVisible())? et.getSelectionEnd() : start;
        StringBuilder str = new StringBuilder(et.getText().toString()).replace(start, end, ((Button)view).getText().toString());
        et.setText(str);
        et.setSelection(start + 1);
    }

    @SuppressLint("SetTextI18n")
    public void onPointClick(View view) {
        EditText et = mListener.getExpressionET();

        et.setText(et.getText() + ".");
        et.setSelection(et.getText().length());
    }

    public void onEqualClick(View view) {
        EditText et = mListener.getExpressionET();
        TextView tv = mListener.getAnswerTextView();
        String calculatedStr = mListener.calculate();


        if (calculatedStr == null)
            Toast.makeText(getActivity(), "Can't calculate this", Toast.LENGTH_SHORT).show();
        else
        {
            et.setText(calculatedStr);
            tv.setText("");
            et.setSelection(et.getText().length());
        }
    }

    private OnBasicFragmentButtonClickListener mListener;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBasicFragmentButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnBasicFragmentButtonClickListener");
        }
    }

    public interface OnBasicFragmentButtonClickListener {
        EditText getExpressionET();
        TextView getAnswerTextView();
        String calculate();
    }
}
