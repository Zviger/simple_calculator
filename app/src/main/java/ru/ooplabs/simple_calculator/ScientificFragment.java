package ru.ooplabs.simple_calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScientificFragment extends BasicFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_scientific, container, false);

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

        return v;
    }

}
