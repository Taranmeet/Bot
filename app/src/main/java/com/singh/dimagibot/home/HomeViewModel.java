package com.singh.dimagibot.home;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.singh.dimagibot.base.BaseViewModel;
import com.singh.dimagibot.botService.DimagiBotService;
import com.singh.dimagibot.data.DataSource;

/**
 * View Modal backing the home activity.
 */
public class HomeViewModel extends BaseViewModel<IHomeView> {

    /**
     * Databinding observables used to bind history and command views to view modal
     */
    public ObservableField<String> mHistory = new ObservableField<>();
    public ObservableField<String> mCommand = new ObservableField<>();

    private DimagiBotService mDimagiBot;

    public void setDimagiBot(DimagiBotService iBot) {
        mDimagiBot = iBot;
    }

    public HomeViewModel(IHomeView iView) {
        super(iView);

        mHistory.set("Hi Welcome to Dimagi bot.");
        mCommand.set("");
    }

    /**
     * Method to handle send click events
     */
    public void clickSend() {
        if (!TextUtils.isEmpty(mCommand.get())) {
            // update history window
            mHistory.set(mHistory.get() + "\n " + "[@"+ DataSource.mCurrentUser +"} "+ mCommand.get());

            // fetch current command
            String currentCommand = mCommand.get().split(" ")[0];
            // seprate arguments
            String args = null;
            if (mCommand.get().split(" ").length >1){
                args = mCommand.get().split(" ")[1];
            }
            // reset command prompt to blank
            mCommand.set("");
            // handle command
            handleCommand(currentCommand, args);
        } else {
            Toast.makeText(getView().getContext(), "Please enter a command", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method to handle commands
     * @param iCommand name of command called
     * @param args arguments. Can be null.
     */
    private void handleCommand(String iCommand, String args) {

        // based on what command it is we can call corresponding method and get response from dimagi
        String botResponse = "";
        switch (iCommand) {
            case "hi":
                botResponse = mDimagiBot.handleHi();
                break;

            case "ping":
                botResponse = mDimagiBot.handlePing();
                break;

            case "hereami":
                    botResponse = mDimagiBot.handleHereAmi(args);
                    break;

            case "inoffice":
                botResponse = mDimagiBot.handleInOffice();
                break;

            case "whereis":
                botResponse = mDimagiBot.handleWhereIs(args);
                break;

            case "help":

            default:
                botResponse = mDimagiBot.handleDefault();
                break;
        }

        mHistory.set(mHistory.get() + "\n [@DimagiBot] " +botResponse);
    }


}
