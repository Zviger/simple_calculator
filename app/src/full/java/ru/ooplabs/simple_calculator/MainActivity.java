package ru.ooplabs.simple_calculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;
import android.text.Editable;
import android.widget.TextView;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.function.Function;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends FragmentActivity implements BasicFragment.OnBasicFragmentButtonClickListener {

    private EditText expressionEditView;
    private TextView answerTextView;


    static final int PAGE_COUNT = 2;

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            pager = findViewById(R.id.pager);
            pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            pager.setAdapter(pagerAdapter);
        }

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

    @Override
    public EditText getExpressionET()
    {
        return expressionEditView;
    }

    @Override
    public TextView getAnswerTextView()
    {
        return answerTextView;
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        MyFragmentPagerAdapter(FragmentManager fm) {super(fm);}

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

    @Override
    public String calculate() {
        Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

            @Override
            public double apply(double... args) {
                final int arg = (int) args[0];
                if ((double) arg != args[0]) {
                    throw new IllegalArgumentException("Operand for factorial has to be an integer");
                }
                if (arg < 0) {
                    throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
                }
                double result = 1;
                for (int i = 1; i <= arg; i++) {
                    result *= i;
                }
                return result;
            }
        };

        Function sqrt = new Function("sqrt", 1) {

            @Override
            public double apply(double... args) {
                return Math.sqrt(args[0]);
            }
        };

        String txt = expressionEditView.getText().toString().replace('×', '*');
        txt = txt.replace('÷', '/');
        try {
            String result = Double.toString(new ExpressionBuilder(txt).operator(factorial).function(sqrt).build().evaluate());
            result = result.replace('E', 'e');
            List<String> strs = Arrays.asList(result.split("\\s*\\.\\s*"));
            if (strs.get(1).equals("0"))
                return strs.get(0);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
    }
