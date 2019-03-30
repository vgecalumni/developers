package org.vgecalumni;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QRCodeActivity extends AppCompatActivity {

    ImageView iv_qrcode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        iv_qrcode = findViewById(R.id.iv_qrcode);
        setQRCode("Hello");
    }

    private void setQRCode(String textToEncode) {
        iv_qrcode.setImageBitmap(encodeToQRCode(textToEncode,750, 750));
    }

    private static Bitmap encodeToQRCode(String text, int width, int height) {
        BitMatrix matrix = null;

        try {
            matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        int w = matrix.getWidth();
        int h = matrix.getHeight();
        int[] pixels = new int[w*h];
        for(int y = 0; y < h; y++) {
            int offset = y * w;
            for(int x =0; x < w; x++) {
                pixels[offset + x] = matrix.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);
        return bitmap;
    }
}
