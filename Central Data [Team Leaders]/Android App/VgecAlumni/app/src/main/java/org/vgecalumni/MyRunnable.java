package org.vgecalumni;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MyRunnable implements Runnable {
    private Handler h2;
    public MyRunnable(Handler h) {
        this.h2 = h;
    }

    @Override
    public void run() {

        //everything inside rum method executes in new thread

            Message m = Message.obtain(); //get null message
            Bundle b = new Bundle();
            b.putString("30", "Hii");
            m.setData(b);
            //use the handler to send message
            h2.sendMessage(m);


    }
}