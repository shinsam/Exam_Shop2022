package com.example.exam_shop2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_product_main;
    EditText edit_count;
    TextView txt_total, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price=0, val_delivery=2500, val_pay=0;
    int selected_price=1500;
    int selected_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_total = findViewById(R.id.txt_total);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay = findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);

        sumTotal(); //처음에 화면이 뜰때 계산된 값을 화면에 표시
    }

    @Override
    public void onClick(View v) {
        int count=0;
        String str_count;
        switch (v.getId()) {
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.product1);
                selected_price = 1500;
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.product2);
                selected_price = 2000;
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.product3);
                selected_price = 3000;
                sumTotal();
                break;
            case R.id.btn_minus:
                str_count= edit_count.getText().toString().trim();
                count = Integer.parseInt(str_count);
                if(count==1)
                    Toast.makeText(getApplicationContext(), "주문할 수 있는 최소수량은 1개입니다.", Toast.LENGTH_SHORT).show();
                else {
                    edit_count.setText(Integer.toString(count - 1));
                    sumTotal();
                }
                break;
            case R.id.btn_plus:
                str_count = edit_count.getText().toString().trim();
                count = Integer.parseInt(str_count);
                edit_count.setText(Integer.toString(count+1));
                sumTotal();
                break;
            case R.id.btn_pay:
                if(chk_agree.isChecked()==false){
                    Toast.makeText(getApplicationContext(),"결제 동의 버튼을 체크해주세요", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), val_pay + "원을 결제하겠습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                }

        }
    }

    //선택한 제품 단가와, 수량을 이용하여 결제 금액 계산하는 메소드
    public void sumTotal() {
        // 선택한 수량
        selected_count = Integer.parseInt(edit_count.getText().toString());
        // 단가 * 수량
        val_price = selected_price * selected_count;

        //10000원 이상이면 배송료 없음
        if(val_price>=10000)
            val_delivery=0;
        else
            val_delivery=2500;

        //배송료 포함한 총 결제 금액 계산
        val_pay = val_price + val_delivery;

        //화면에 상품 금액, 배송료, 총 결제금액 출력
        txt_total.setText(val_price + "원");
        txt_delivery.setText(val_delivery + "");
        txt_pay.setText(val_pay+"원");
    }
}