package test3.mycoutner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/1/27.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<InformationList> mInformationLists = new ArrayList<>();

    public ListViewAdapter(Context context,List<InformationList> informationLists){
        mContext = context;
        mInformationLists = informationLists;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mInformationLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mInformationLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //返回视图
        if(convertView == null)
        {
            convertView= mLayoutInflater.inflate(R.layout.item_second_pause, null);
        }

        //获取控件
        TextView informationTextView=(TextView) convertView.findViewById(R.id.second_information);

        //数据绑定
        informationTextView.setText(mInformationLists.get(position).getmInfo());

        return convertView;
    }


}
