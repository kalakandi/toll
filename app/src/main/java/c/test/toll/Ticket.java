package c.test.toll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Ticket extends AppCompatActivity {
    private RadioButton op1,op2,op3,op4,op5;
    private Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        op1= findViewById(R.id.option1);
        op2= findViewById(R.id.option2);
        op3= findViewById(R.id.option3);
        op4= findViewById(R.id.option4);
        op5= findViewById(R.id.option5);

        next =findViewById(R.id.next) ;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op1.isChecked()){
                    Toast.makeText(getApplicationContext(),""+op1.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Ticket.this,First.class);
                    startActivity(intent);

                }
                else if(op2.isChecked()){
                    Toast.makeText(getApplicationContext(),""+op2.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(Ticket.this,First.class);
                    startActivity(intent2);
                }
                else if(op3.isChecked()){
                    Toast.makeText(getApplicationContext(),""+op3.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(Ticket.this,First.class);
                    startActivity(intent3);
                }
                else if(op4.isChecked()){
                    Toast.makeText(getApplicationContext(),""+op4.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent4 = new Intent(Ticket.this,First.class);
                    startActivity(intent4);
                }
                else if(op5.isChecked()) {
                    Toast.makeText(getApplicationContext(), "" + op5.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent5 = new Intent(Ticket.this,First.class);
                    startActivity(intent5);
                }
            }
        });



    }


}
