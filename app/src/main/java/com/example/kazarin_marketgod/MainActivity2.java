package com.example.kazarin_marketgod;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

// second screen
public class MainActivity2 extends AppCompatActivity {

    float A, B, C, D, E, F, G, H, cena_1, grami_1, cena_2, grami_2;

    EditText getCost_1;
    EditText getCost_2;
    EditText getGramms_1;
    EditText getGramms_2;

    Button begin_sravnenie_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onClickCompare(View view)
    {

        getCost_1 = (EditText) findViewById(R.id.cost_1);
        getGramms_1 = (EditText) findViewById(R.id.gramms_1);

        getCost_2 = (EditText) findViewById(R.id.cost_2);
        getGramms_2 = (EditText) findViewById(R.id.gramms_2);

        begin_sravnenie_button = (Button) findViewById(R.id.begin_sravnenie_button);
        begin_sravnenie_button.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick (View v) {
                A = cena_1 = Float.valueOf(getCost_1.getText().toString());
                B = grami_1 = Float.valueOf(getGramms_1.getText().toString());

                C = cena_2 = Float.valueOf(getCost_2.getText().toString());
                D = grami_2 = Float.valueOf(getGramms_2.getText().toString());

                String money = getResources().getString(R.string.money); // .руб (ВАЛЮТА) ( .руб, $, etc...)
                String save = getResources().getString(R.string.save); // Экономия:
                String same = getResources().getString(R.string.same); // Одинаковая стоимость продукта!
                String first_better_than_2 = getResources().getString(R.string.first_better_than_2); // 1-ый продукт дешевле 2-го!
                String second_better_than_1 = getResources().getString(R.string.second_better_than_1); // 2-ой продукт дешевле 1-го!
                String cost_1st_product = getResources().getString(R.string.cost_1st_product); // Цена первого продукта за единицу грамма =
                String cost_2nd_product = getResources().getString(R.string.cost_2nd_product); // Цена второго продукта за единицу грамма =
                String cheaper = getResources().getString(R.string.cheaper); // Выгоднее покупать продукт за

                E = A / B; // Цена 1-го продукта за единицу грамма
                DecimalFormat decimalFormat_E = new DecimalFormat( "#.##" );
                String result_E = decimalFormat_E.format(E);
                ShowToast(String.format("%s %s %s", cost_1st_product, result_E, money)); // Цена первого продукта за единицу грамма = ##.## руб. (не трогать)

                F = C / D; // Цена 2-го продукта за единицу грамма
                DecimalFormat decimalFormat_F = new DecimalFormat( "#.##" ); // не трогать
                String result_F = decimalFormat_F.format(F); // не трогать
                ShowToast(String.format("%s %s %s", cost_2nd_product, result_F, money)); // Цена второго продукта за единицу грамма = ##.## руб. (не трогать)

                int result_A = Math.round(A);
                int result_C = Math.round(C);

                if (E < F) {
                    G = F - E;
                    H = G * B;
                    DecimalFormat decimalFormat_H = new DecimalFormat( "#.##" ); // не трогать
                    String result_H = decimalFormat_H.format(H); // не трогать

                    ShowToast(String.format(first_better_than_2)); // 1-ый продукт дешевле 2-го! (не трогать)
                    ShowToast(String.format("%s %s %s", cheaper, result_A, money)); // Выгоднее покупать продукт за ##.## руб. (не трогать)
                    ShowToast(String.format("%s %s %s", save, result_H, money)); // Экономия: ##.## руб. (не трогать)
                }

                if (E > F) {
                    G = E - F;
                    H = G * D;
                    DecimalFormat decimalFormat_H = new DecimalFormat( "#.##" );
                    String result_H = decimalFormat_H.format(H);

                    ShowToast(String.format(second_better_than_1));  // 2-ой продукт дешевле 1-го! (не трогать)
                    ShowToast(String.format("%s %s %s", cheaper, result_C, money)); // Выгоднее покупать продукт за ##.## руб. (не трогать)
                    ShowToast(String.format("%s %s %s", save, result_H, money));  // Экономия: ##.## руб. (не трогать)
                }

                if (E == F) {
                    ShowToast(String.format("%s", same)); // Одинаковая стоимость продуктов! (не трогать)
                }
            }
        });
    }

    private void ShowToast(String text)
    //private static final int LONG_DELAY = 3500; // 3.5 seconds
    //private static final int SHORT_DELAY = 2000; // 2 seconds
    {
        Toast.makeText(MainActivity2.this, text, Toast.LENGTH_SHORT).show();
    }
}