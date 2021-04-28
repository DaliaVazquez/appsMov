package com.example.actividad2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final int EXAMPLE_CODE = 0;
    private static final String TAG_FRAGMENTO="fragmento";
    private Handler handler;
    JSONArray datos;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info="[ {'name':'burger', 'price':15, 'description':'a juicy burger!'}, {'name':'hotdog', 'price':20, 'description':'an ok hot dog'}, {'name':'tacos', 'price':12, 'description':'some pretty good tacos'},{'name':'torta', 'price':22, 'description':'nice torta'},{'name':'carne asada', 'price':50, 'description':'a great carne asada'}]";
        handleMessage();
    }

    public void cambiarFragmento(Fragment nuevo){
        FragmentManager manager=getSupportFragmentManager();
        Fragment f=manager.findFragmentByTag(TAG_FRAGMENTO);
        FragmentTransaction transaction=manager.beginTransaction();
        if(nuevo==f){
            return;
        }
        if(f!=null){
            transaction.remove(f);
        }
        transaction.add(R.id.contenedor,nuevo,TAG_FRAGMENTO);
        transaction.commit();

    }


    public boolean handleMessage() {
        try{
            datos=new JSONArray(info);
            for(int i=0;i<datos.length();i++){
                JSONObject temp=datos.getJSONObject(i);
                Log.wtf("JSON","--------------");
                Log.wtf("JSON",temp.getString("name"));
                Log.wtf("JSON",temp.getString("price"));

                ListFragment recyclerViewFragment= ListFragment.newInstance(datos);
                cambiarFragmento(recyclerViewFragment);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void toInfo(View v, int pos) {
        try{
            JSONObject temp=datos.getJSONObject(pos);
            Toast.makeText(this, temp.getString("description"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}