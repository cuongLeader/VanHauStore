package com.example.shop.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class checkInternetConnect {
    public static boolean haveNetworkConnect(Context context)
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
        for(NetworkInfo ni: networkInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedMobile || haveConnectedWifi;
    }
    public static void shortToat(Context context,String thongbao)
    {
        Toast.makeText(context, thongbao, Toast.LENGTH_SHORT).show();
    }
}
