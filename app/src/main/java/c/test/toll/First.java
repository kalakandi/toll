package c.test.toll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

public class First implements PaytmPaymentTransactionCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
}
