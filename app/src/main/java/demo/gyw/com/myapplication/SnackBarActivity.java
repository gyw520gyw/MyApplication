package demo.gyw.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mrengineer13.snackbar.SnackBar;


public class SnackBarActivity extends Activity implements SnackBar.OnMessageClickListener {


    public static final String SAVED_SNACKBAR = "SAVED_SNACKBAR";

    static final int SHORT_MSG = 0, LONG_MSG = 1;

    static final int SHORT_SNACK = 0, MED_SNACK = 1, LONG_SNACK = 2;

    static final int DEFAULT = 0, ALERT = 1, CONFIRM = 2, INFO = 3;

    static final int ACTION_BTN = 0, NO_ACTION_BTN = 1;

    static final int STRING_TYPE_STRING = 0, STRING_TYPE_RESOURCE = 1;

    private Spinner mMsgLengthOptions, mDurationOptions, mActionBtnOptions,
            mActionBtnColorOptions, mBGColorOptions, mStringTypeOptions;

    private SnackBar mSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        mMsgLengthOptions = (Spinner) findViewById(R.id.message_length_selector);
        mDurationOptions = (Spinner) findViewById(R.id.snack_duration_selector);
        mActionBtnOptions = (Spinner) findViewById(R.id.action_btn_presence_selector);
        mActionBtnColorOptions = (Spinner) findViewById(R.id.action_btn_color);
        mBGColorOptions = (Spinner) findViewById(R.id.bg_color);
        mStringTypeOptions = (Spinner) findViewById(R.id.action_btn_string_type);
    }


    public void onCreateClicked(View view) {
        String message = "";
        int messageRes = -1;
        short duration = 0;
        SnackBar.Style style;
        SnackBar.Style bgStyle;
        int bgColor;

        int selectedStringType = mStringTypeOptions.getSelectedItemPosition();
        int selectedMessageLength = mMsgLengthOptions.getSelectedItemPosition();
        switch (selectedMessageLength) {
            case SHORT_MSG:
                if (selectedStringType == STRING_TYPE_STRING) {
                    message = "This is a one-line message.";
                } else {
                    messageRes = R.string.short_message;
                }
                break;
            case LONG_MSG:
                if (selectedStringType == STRING_TYPE_STRING) {
                    message = "This message is a lot longer, it should stretch at least two lines. ";
                } else {
                    messageRes = R.string.long_message;
                }
                break;
        }

        int selectedDuration = mDurationOptions.getSelectedItemPosition();
        switch (selectedDuration) {
            case SHORT_SNACK:
                duration = SnackBar.SHORT_SNACK;
                break;
            case MED_SNACK:
                duration = SnackBar.MED_SNACK;
                break;
            case LONG_SNACK:
                duration = SnackBar.LONG_SNACK;
                break;


        }

        int selectedActionBtnColor = mActionBtnColorOptions.getSelectedItemPosition();
        switch (selectedActionBtnColor) {
            default:
            case DEFAULT:
                style = SnackBar.Style.DEFAULT;
                break;
            case ALERT:
                style = SnackBar.Style.ALERT;
                break;
            case CONFIRM:
                style = SnackBar.Style.CONFIRM;
                break;
            case INFO:
                style = SnackBar.Style.INFO;
                break;
        }

        int selectedBGColor = mBGColorOptions.getSelectedItemPosition();
        switch (selectedBGColor) {
            default:
            case DEFAULT:
                bgColor = R.color.sb__snack_bkgnd;
                break;
            case ALERT:
                bgColor = R.color.sb__snack_alert_bkgnd;
                break;
        }

        int selectedActionBtnExistance = mActionBtnOptions.getSelectedItemPosition();
        switch (selectedActionBtnExistance) {
            case ACTION_BTN:
                if (messageRes <= 0) {
                    mSnackBar = new SnackBar.Builder(this)
                            .withOnClickListener(this)
                            .withMessage(message)
                            .withActionMessage("Action")
                            .withStyle(style)
                            .withBackgroundColorId(bgColor)
                            .withDuration(duration)
                            .show();
                } else {
                    mSnackBar = new SnackBar.Builder(this)
                            .withOnClickListener(this)
                            .withMessageId(messageRes)
                            .withActionMessageId(R.string.action)
                            .withStyle(style)
                            .withBackgroundColorId(bgColor)
                            .withDuration(duration)
                            .show();
                }
                break;
            case NO_ACTION_BTN:
                if (messageRes <= 0) {
                    mSnackBar = new SnackBar.Builder(this)
                            .withMessage(message)
                            .withBackgroundColorId(bgColor)
                            .withDuration(duration)
                            .show();
                } else {
                    mSnackBar = new SnackBar.Builder(this)
                            .withMessageId(messageRes)
                            .withBackgroundColorId(bgColor)
                            .withDuration(duration)
                            .show();
                }

                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
        // use this to save your snacks for later
        //saveState.putBundle(SAVED_SNACKBAR, mSnackBar.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle loadState) {
        super.onRestoreInstanceState(loadState);
        // use this to load your snacks for later
        //mSnackBar.onRestoreInstanceState(loadState.getBundle(SAVED_SNACKBAR));
    }

    @Override
    public void onMessageClick(Parcelable token) {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_LONG).show();
    }

}
