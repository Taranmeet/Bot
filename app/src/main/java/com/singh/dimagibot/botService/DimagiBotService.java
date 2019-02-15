package com.singh.dimagibot.botService;

import android.app.Service;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Binder;
import android.os.IBinder;

import com.singh.dimagibot.data.DataSource;

/**
 * Bounded service used to replicate the Dimagi bot.
 */
public class DimagiBotService extends Service {

    private DimagiBinder mBinder = new DimagiBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public class DimagiBinder extends Binder{

        public DimagiBotService getDimagiBot(){
            return DimagiBotService.this;
        }

    }

    public String handleHi(){
        return "Hi " + DataSource.mCurrentUser+ " Dimagi bot welcomes you";
    }

    public String handleDefault(){
        return "We only provide following options - hi, hereami, help, inoffice, ping, whereis";
    }

    public String handlePing(){
        return "Dimagi Bot is up";
    }

    public String handleHereAmi(String iLocation){
        if (iLocation == null){
            return "Argument cannot be empty should include location";
        }
        DataSource.mLocation.put(DataSource.mCurrentUser, iLocation);
        return DataSource.mCurrentUser + " you current location updated to " + iLocation;
    }

    public String handleInOffice(){
        String response = "";
        for (String user :
                DataSource.mLocation.keySet()) {
            response += "User " + user + " is at " + DataSource.mLocation.get(user) + " location";
            response += "\n";
        }
        return response;
    }

    public String handleWhereIs(String iUser){
        if (iUser == null){
            return "Argument cannot be empty should include user name";
        }
        DataSource.mLocation.get(iUser);
        return  iUser +" is currently at " + DataSource.mLocation.get(iUser);
    }
}
