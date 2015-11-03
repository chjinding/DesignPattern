package com.design.pattern.designpattern;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.design.pattern.designpattern.bean.MyPerson;

import java.util.ArrayList;
import java.util.List;

public class Item3Fragment extends Fragment {

	private Button add;
	private MyPerson observable;
	private int i = 1;
	private Button change;
	private ListView lv;
	private List<MyObserver> myObservers;

	private Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// 将信息加入list中显示
			MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
					myObservers);
			lv.setAdapter(myListAdapter);
			return false;
		}
	});

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_item1, null);

		add = (Button) view.findViewById(R.id.add);
		observable = new MyPerson();
		myObservers = new ArrayList<MyObserver>();
		lv = (ListView) view.findViewById(R.id.listview);
		// 添加观察者
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MyObserver myObserver = new MyObserver(i);
				i++;
				observable.addObserver(myObserver);
				myObservers.add(myObserver);
				handler.sendEmptyMessage(0);
			}
		});
		change = (Button) view.findViewById(R.id.change);
		// 通知数据改变
		change.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int d = (int) (Math.random() * 10 + 1);
				observable.setAge(d);
				observable.setName("" + d);
				observable.setSax("" + d);
				handler.sendEmptyMessage(0);
			}
		});

		return view;
	}

	private class MyListAdapter extends BaseAdapter {

		private List<MyObserver> list;
		private Context context;

		public MyListAdapter(Context context, List<MyObserver> list) {
			this.context = context;
			this.list = list;
		}

		@Override
		public int getCount() {
			int num = 0;
			if (null == list || list.size() == 0) {
				num = 0;
			} else {
				num = list.size();
			}
			return num;
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.item_observer,
						null);
				holder.txv_observer = (TextView) convertView
						.findViewById(R.id.txv_observer);
				holder.txv_observer_con = (TextView) convertView
						.findViewById(R.id.txv_observer_con);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.txv_observer.setText("观察者ID：" + list.get(position).getI());
			if (list.get(position).getMyPerson() == null
					|| list.get(position).getMyPerson().toString().equals("")) {
				holder.txv_observer_con.setText("观察者内容：未添加内容");
			} else {
				holder.txv_observer_con.setText("观察者内容："
						+ list.get(position).getMyPerson().toString());
			}

			return convertView;
		}

		class ViewHolder {
			TextView txv_observer, txv_observer_con;
		}
	}
}
