package com.ezz.bytourism1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ezz.bean.City;
import com.ezz.bean.Scenic;
import com.ezz.bean.Scenicroute;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import android.R.anim;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	private TextView textcity;
	private Spinner spinner;
	private List<String>list;

	private ArrayAdapter<String>adapter;
	private String[] citytipStrings = {"����","�Ϻ�","����","����","����"};
    private ImageView imagefirst;
	private final static int CWJ_HEAP_SIZE = 6* 1024* 1024 ;
	private String citysid;
	//����������
	private AutoCompleteTextView csearch;
	private String citytipname;
	private String cityname;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
    	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose1);
		imagefirst = (ImageView) findViewById(R.id.imagefirst);
		//ʵ����������
		csearch = (AutoCompleteTextView) findViewById(R.id.edit_Search1);
		
		Bmob.initialize(MainActivity.this, "a1a4ff643e92be99bb8649e33589c596");
		//�����б����ÿ�ʼ
		spinner=(Spinner)findViewById(R.id.spinner1);
		list=new ArrayList<String>();
		list.add("����");
		list.add("�Ϻ�");
		list.add("����");
		
		adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		citytipname=csearch.getText().toString();
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,citytipStrings);
		csearch.setAdapter(arrayAdapter);
		csearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO �Զ����ɵķ������
				if(actionId==EditorInfo.IME_ACTION_SEND||(event!=null&&event.getKeyCode()==KeyEvent.KEYCODE_ENTER)){
					Toast.makeText(MainActivity.this, "��ʼ����", Toast.LENGTH_SHORT).show();
					query();
					return true;
				}
				return false;
			}
			
		});
	}
		public void query(){
			//ͨ����������������:city���е�cityname-->city���е�id-->scenicroute���е�cityid-->����id-->scenic��name
			cityname = csearch.getText().toString();
			citysid = null;
			if(csearch.equals("")){
				return;
			}
			BmobQuery<City> query_city = new BmobQuery<City>();
			query_city.addWhereEqualTo("cityname", cityname);
			query_city.findObjects(MainActivity.this, new FindListener<City>() {
				
				@Override
				public void onSuccess(List<City> fcity) {
					// TODO �Զ����ɵķ������
					int cityid = fcity.get(0).getId();
					Toast.makeText(MainActivity.this, fcity.get(0).getId()+"", Toast.LENGTH_SHORT).show();
					BmobQuery<Scenicroute> query_sroute = new BmobQuery<Scenicroute>();
					query_sroute.addWhereEqualTo("cityid", cityid);
					query_sroute.findObjects(MainActivity.this, new FindListener<Scenicroute>() {
						@Override
						public void onError(int arg0, String arg1) {
							// TODO �Զ����ɵķ������
							Toast.makeText(MainActivity.this, "false!", Toast.LENGTH_SHORT).show();
						}
						@Override
						public void onSuccess(List<Scenicroute> fsroutes) {
							// TODO �Զ����ɵķ������
							String str = "";
							for(Scenicroute fsroute:fsroutes){
								if(fsroute.getOne()!=0){
									str = str+fsroute.getOne();
									Scenic aScenic = new Scenic();
									BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
									query_scenic.addWhereEqualTo("id", fsroute.getOne());
									query_scenic.findObjects(MainActivity.this, new FindListener<Scenic>() {
										@Override
										public void onError(int arg0,String arg1) {
											// TODO �Զ����ɵķ������
										}
										@Override
										public void onSuccess(List<Scenic> fscenics) {
											// TODO �Զ����ɵķ������
											for(Scenic fscenic:fscenics){
											Toast.makeText(MainActivity.this, fscenic.getScenicname()
													+fscenic.getDescribe(),Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
								if(fsroute.getTwo()!=0){
									str = str+fsroute.getTwo();
									Scenic aScenic = new Scenic();
									BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
									query_scenic.addWhereEqualTo("id", fsroute.getTwo());
									query_scenic.findObjects(MainActivity.this, new FindListener<Scenic>() {
										@Override
										public void onError(int arg0,String arg1) {
											// TODO �Զ����ɵķ������
										}
										@Override
										public void onSuccess(List<Scenic> fscenics) {
											// TODO �Զ����ɵķ������
											for(Scenic fscenic:fscenics){
											Toast.makeText(MainActivity.this, fscenic.getScenicname()
													+fscenic.getDescribe(),Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
								if(fsroute.getThree()!=0){
									str = str+fsroute.getThree();
									Scenic aScenic = new Scenic();
									BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
									query_scenic.addWhereEqualTo("id", fsroute.getThree());
									query_scenic.findObjects(MainActivity.this, new FindListener<Scenic>() {
										@Override
										public void onError(int arg0,String arg1) {
											// TODO �Զ����ɵķ������
										}
										@Override
										public void onSuccess(List<Scenic> fscenics) {
											// TODO �Զ����ɵķ������
											for(Scenic fscenic:fscenics){
											Toast.makeText(MainActivity.this, fscenic.getScenicname()
													+fscenic.getDescribe(),Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
								if(fsroute.getFour()!=0){
									str = str+fsroute.getFour();
									Scenic aScenic = new Scenic();
									BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
									query_scenic.addWhereEqualTo("id", fsroute.getFour());
									query_scenic.findObjects(MainActivity.this, new FindListener<Scenic>() {
										@Override
										public void onError(int arg0,String arg1) {
											// TODO �Զ����ɵķ������
										}
										@Override
										public void onSuccess(List<Scenic> fscenics) {
											// TODO �Զ����ɵķ������
											for(Scenic fscenic:fscenics){
											Toast.makeText(MainActivity.this, fscenic.getScenicname()
													+fscenic.getDescribe(),Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
								if(fsroute.getFive()!=0){
									str = str+fsroute.getFive();
									Scenic aScenic = new Scenic();
									BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
									query_scenic.addWhereEqualTo("id", fsroute.getFive());
									query_scenic.findObjects(MainActivity.this, new FindListener<Scenic>() {
										@Override
										public void onError(int arg0,String arg1) {
											// TODO �Զ����ɵķ������
										}
										@Override
										public void onSuccess(List<Scenic> fscenics) {
											// TODO �Զ����ɵķ������
											for(Scenic fscenic:fscenics){
											Toast.makeText(MainActivity.this, fscenic.getScenicname()
													+fscenic.getDescribe(),Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
							}
							Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
						}
					});
					Toast.makeText(MainActivity.this, cityid, Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent(MainActivity.this,AllSightDetail.class);
					//intent.putExtra("citys_id", value);
				}
				@Override
				public void onError(int arg0, String arg1) {
					// TODO �Զ����ɵķ������
					Toast.makeText(MainActivity.this, "false!", Toast.LENGTH_SHORT).show();
				}
			});
		
		}
		
}
