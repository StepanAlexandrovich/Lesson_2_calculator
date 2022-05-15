package java.android.lesson_2_calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Logic logic;
    TextView textView;

    SharedPreferences pref;

    String PARCELABLE_TAG = "parcelable_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getCodeTheme());

        setContentView(R.layout.activity_main);

        logic = new Logic();
        textView = findViewById(R.id.textView);

        // numbers
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonDot).setOnClickListener(this);
        // action
        findViewById(R.id.buttonPlus).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonShare).setOnClickListener(this);
        // result
        findViewById(R.id.buttonEquals).setOnClickListener(this);

        // themes
        findViewById(R.id.buttonMyTheme0).setOnClickListener(this);
        findViewById(R.id.buttonMyTheme1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
              // numbers
            case R.id.button0: logic.inputNumber("0"); break;
            case R.id.button1: logic.inputNumber("1"); break;
            case R.id.button2: logic.inputNumber("2"); break;
            case R.id.button3: logic.inputNumber("3"); break;
            case R.id.button4: logic.inputNumber("4"); break;
            case R.id.button5: logic.inputNumber("5"); break;
            case R.id.button6: logic.inputNumber("6"); break;
            case R.id.button7: logic.inputNumber("7"); break;
            case R.id.button8: logic.inputNumber("8"); break;
            case R.id.button9: logic.inputNumber("9"); break;
            case R.id.buttonDot: logic.inputNumber("."); break;
              // action
            case R.id.buttonPlus: logic.inputAction("+"); break;
            case R.id.buttonMinus: logic.inputAction("-"); break;
            case R.id.buttonMultiply: logic.inputAction("X"); break;
            case R.id.buttonShare: logic.inputAction("/"); break;
              // result
            case R.id.buttonEquals: logic.result(); break;

              // themes
            case R.id.buttonMyTheme0: onClickSaveTheme(R.style.MyTheme0); break;
            case R.id.buttonMyTheme1: onClickSaveTheme(R.style.MyTheme1); break;
        }

        textView.setText(logic.getText());
    }

    public void onClickSaveTheme(int themeCode){
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("key",themeCode);
        edit.apply();

        recreate();
    }

    private int getCodeTheme(){
        pref = getSharedPreferences("Test",MODE_PRIVATE);
        return pref.getInt("key",R.style.MyTheme0);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(PARCELABLE_TAG,logic);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        logic = savedInstanceState.getParcelable(PARCELABLE_TAG);
        textView.setText(logic.getText());
    }
}