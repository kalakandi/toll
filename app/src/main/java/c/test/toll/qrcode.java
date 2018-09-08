package c.test.toll;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

import java.io.File;

public class qrcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        
        VCard abhay = new VCard("Anonymous")
                .setAddress("India")
                .setTitle("Receipt")
                .setCompany("Vehicle Type= Car/Jeep/Van")
                .setPhoneNumber("Amount=120")
                .setWebsite("Vehicle No=abc1234");
        Bitmap myBitmap = QRCode.from(abhay).withSize(250, 250).bitmap();
        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setImageBitmap(myBitmap);
    }
}
