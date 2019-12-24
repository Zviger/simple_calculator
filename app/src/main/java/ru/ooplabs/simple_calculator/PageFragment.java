
package ru.ooplabs.simple_calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PageFragment extends BasicFragment{

    private static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    private int pageNumber;

    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        if (pageNumber == 0)
        {
            v = inflater.inflate(R.layout.fragment_basic, null);

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

        }
        else
        {
            v = inflater.inflate(R.layout.fragment_scientific, null);

            Button buttonSin = v.findViewById(R.id.buttonSin);
            Button buttonCos = v.findViewById(R.id.buttonCos);
            Button buttonTan = v.findViewById(R.id.buttonTan);
            Button buttonPi = v.findViewById(R.id.buttonPi);
            Button buttonE = v.findViewById(R.id.buttonE);
            Button buttonFactorial = v.findViewById(R.id.buttonFactorial);
            Button buttonLog = v.findViewById(R.id.buttonLog);
            Button buttonLn = v.findViewById(R.id.buttonLn);
            Button buttonMod = v.findViewById(R.id.buttonMod);
            Button buttonPower = v.findViewById(R.id.buttonPower);
            Button buttonSquare = v.findViewById(R.id.buttonSquare);
            Button buttonSqrt = v.findViewById(R.id.buttonSqrt);

            buttonSin.setOnClickListener(this::onOperatorClick);
            buttonCos.setOnClickListener(this::onOperatorClick);
            buttonTan.setOnClickListener(this::onOperatorClick);
            buttonPi.setOnClickListener(this::onOperatorClick);
            buttonE.setOnClickListener(this::onOperatorClick);
            buttonFactorial.setOnClickListener(this::onOperatorClick);
            buttonLog.setOnClickListener(this::onOperatorClick);
            buttonLn.setOnClickListener(this::onOperatorClick);
            buttonMod.setOnClickListener(this::onOperatorClick);
            buttonPower.setOnClickListener(this::onOperatorClick);
            buttonSquare.setOnClickListener(this::onOperatorClick);
            buttonSqrt.setOnClickListener(this::onOperatorClick);
        }

        return v;
    }
}