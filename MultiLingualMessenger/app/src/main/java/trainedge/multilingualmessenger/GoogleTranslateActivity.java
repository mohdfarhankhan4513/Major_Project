package trainedge.multilingualmessenger;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GoogleTranslateActivity extends AppCompatActivity {
    GoogleTranslate translator;
    EditText translateedittext;
    TextView translatabletext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_translate);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        translateedittext = (EditText) findViewById(R.id.translateedittext);
        Button translatebutton = (Button) findViewById(R.id.translatebutton);
        translatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new EnglishToTagalog().execute();

            }
        });
    }


    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {

        }
        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate("AIzaSyC9cH5-M8Ce-3zQfIciY-7VjGUb2WoB7Ho");


                Thread.sleep(1000);


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(GoogleTranslateActivity.this, null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }


    public void translated(){

        String translatetotagalog = translateedittext.getText().toString();//get the value of text
        String text = translator.translte(translatetotagalog, "en", "tl");
        translatabletext = (TextView) findViewById(R.id.translatabletext);
        translatabletext.setText(text);

    }


}

