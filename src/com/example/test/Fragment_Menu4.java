package com.example.test;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_Menu4 extends Fragment {

	private ArrayList<String> list;
	private ListView listview;
	private ArrayAdapter<String> adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_4, container,
				false);

		list = new ArrayList<String>();
		listview = (ListView) rootView.findViewById(R.id.listview);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, list);

		listview.setAdapter(adapter);

		list.add("����");
		list.add("����");
		list.add("����");
		list.add("����");

		adapter.notifyDataSetChanged();

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String tv = (String) parent.getAdapter().getItem(position);
				//Toast.makeText(getActivity().getApplicationContext(), tv,	Toast.LENGTH_SHORT).show();

				if (tv == "����") {
					Intent intentSubActivity = new Intent(getActivity(), setup_Activity.class);
					startActivity(intentSubActivity);
				}

				if (tv == "����") {
					Intent intentSubActivity1 = new Intent(getActivity(), Info_Activity.class);
					startActivity(intentSubActivity1);
				}
				
				if (tv == "����") {
					Intent intentSubActivity2 = new Intent(getActivity(), Help_Activity.class);
					startActivity(intentSubActivity2);
				}

				if (tv == "����") {
					AlertDialog.Builder alert_confirm = new AlertDialog.Builder(
							getActivity());
					alert_confirm
							.setMessage("���α׷��� ���� �Ͻðڽ��ϱ�? (���� �����˴ϴ�.)")
							.setCancelable(false)
							.setPositiveButton("Ȯ��",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											android.os.Process
													.killProcess(android.os.Process
															.myPid()); // ��&���μ���
																		// ����
										}
									})
							.setNegativeButton("���",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// 'No'
											return;
										}
									});
					AlertDialog alert = alert_confirm.create();
					alert.show();
				}
			}

		});

		return rootView;
	}

}
