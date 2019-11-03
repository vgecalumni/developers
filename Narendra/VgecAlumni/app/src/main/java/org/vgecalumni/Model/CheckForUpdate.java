package org.vgecalumni.Model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;

import org.jsoup.Jsoup;

public class CheckForUpdate extends AsyncTask<Void, String, CheckForUpdate.Result> {

    private static final String REFERRER = "http://www.google.com";
    private static final String DIV = "div.hAyfc:nth-child(4)>span:nth-child(2)>div:nth-child(1)>span:nth-child(1)";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private static final String PLAY_STORE_LINK = "http://play.google.com/store/apps/details?id=%s&hl=en";

    private Result result;
    private isTaskComplete listener;
    private Context context;

    public CheckForUpdate(Context context) {
        this.context = context;
        this.result = new Result(context);
    }

    @Override
    protected Result doInBackground(Void... voids) {
        result.setOldVersionCode(getCurrentVersion());
        try {
            String newVersionCode = Jsoup.connect(getPlayURL())
                    .timeout(30000)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRER)
                    .get()
                    .select(DIV)
                    .first()
                    .ownText();
            result.setNewVersionCode(newVersionCode);
        } catch (Exception e) {
            //
        }
        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (listener != null) {
            listener.onTaskComplete(result);
        }
    }


    private String getCurrentVersion() {
        String oldVersionCode = null;
        try {
            oldVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return oldVersionCode;
    }

    private String getPlayURL() {
        return String.format(PLAY_STORE_LINK, context.getPackageName());
    }

    public CheckForUpdate setOnTaskCompleteListener(isTaskComplete listener) {
        this.listener = listener;
        return this;
    }

    public interface isTaskComplete {
        void onTaskComplete(Result result);
    }

    public class Result {
        private Context context;
        private String newVersionCode;
        private String oldVersionCode;

        Result(Context context, String newVersionCode, String oldVersionCode) {
            this.context = context;
            this.newVersionCode = newVersionCode;
            this.oldVersionCode = oldVersionCode;
        }

        Result(Context context) {
            this(context, "", "");
        }

        public boolean hasUpdates() {
            return Float.valueOf(oldVersionCode) < Float.valueOf(newVersionCode);
        }

        public String getNewVersionCode() {
            return newVersionCode;
        }

        void setNewVersionCode(String newVersionCode) {
            this.newVersionCode = newVersionCode;
        }

        public String getOldVersionCode() {
            return oldVersionCode;
        }

        void setOldVersionCode(String oldVersionCode) {
            this.oldVersionCode = oldVersionCode;
        }

        public void openUpdateLink() {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getPlayURL())));
        }
    }
}
