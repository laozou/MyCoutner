package test3.mycoutner;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static TestCoutnerWheel mTestCoutnerWheel;
    private Button mButtonone;
    private Button mButtontwo;
    private Button mButtonthree;
    private static final int MESSAGE_CODE=23333;
    private boolean mCheck;
    private boolean mClean;
    private ListView mListView;
    private List<InformationList> timeMessages;
    private int value;
    private ListViewAdapter mListViewAdapter;
    private int mNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView(){
        mTestCoutnerWheel = (TestCoutnerWheel)findViewById(R.id.my_wheelprogressbar);

        mButtonone = (Button)findViewById(R.id.control_button_one);
        mButtontwo = (Button)findViewById(R.id.control_button_two);
        mButtonthree = (Button)findViewById(R.id.return_button);
        mButtonone.setOnClickListener(this);
        mButtontwo.setOnClickListener(this);
        mButtonthree.setOnClickListener(this);

        mListView = (ListView)findViewById(R.id.time_list_view);
        timeMessages= new ArrayList<>();

        mListViewAdapter = new ListViewAdapter(MainActivity.this, timeMessages);


    }

    private Handler mControlHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!mClean) {
                value = mTestCoutnerWheel.getProgress();
                mTestCoutnerWheel.setProgress(++value);

                if (!mCheck) {
                    if (value >= 600) {
                        removeMessages(MESSAGE_CODE);
                    } else {
                        sendEmptyMessageDelayed(MESSAGE_CODE,100);
                    }
                }
            }else{
                mTestCoutnerWheel.setProgress(value);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.control_button_one:
                mControlHandler.sendEmptyMessage(MESSAGE_CODE);
                mButtonone.setVisibility(View.GONE);
                mButtontwo.setVisibility(View.VISIBLE);
                mCheck=false;
                mClean=false;
                break;
            case R.id.control_button_two:
                mControlHandler.removeMessages(MESSAGE_CODE);
                mButtontwo.setVisibility(View.GONE);
                mButtonone.setVisibility(View.VISIBLE);
                mCheck=true;
                break;
            case R.id.return_button:
                if(!mCheck){

                    getTimeInformation();
                }else{
                    cleardata();
                }
                break;

        }
    }

    public void getTimeInformation(){
        mNumber++;
        timeMessages.add(0,new InformationList("#"+mNumber+"\t\t\t\t\t\t"+String.valueOf(value*1.0/10)));
        mListView.setAdapter(mListViewAdapter);
    }
    public void cleardata(){
        timeMessages.clear();
        mListView.setAdapter(mListViewAdapter);
        value=0;
        mButtontwo.setVisibility(View.GONE);
        mButtonone.setVisibility(View.VISIBLE);
        mClean=true;
        mControlHandler.sendEmptyMessage(MESSAGE_CODE);
        mNumber=0;
    }
}
