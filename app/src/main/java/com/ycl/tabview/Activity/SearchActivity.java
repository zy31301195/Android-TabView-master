package com.ycl.tabview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ycl.tabview.R;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity implements View.OnClickListener {
	public static final String EXTRA_KEY_TYPE = "extra_key_type";
	public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
	public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";
	private EditText mKeywordEt;
	private TextView mOperationTv;

	private ArrayAdapter<String> mArrAdapter;
	private SharedPreferences mPref;
	private Editor mEditor;
	private LinearLayout mSearchHistoryLl;
	private List<String> mHistoryKeywords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPref =  getSharedPreferences("test", Activity.MODE_PRIVATE);
		mEditor = mPref.edit();
		mHistoryKeywords = new ArrayList<String>();
		setContentView(R.layout.search);
		final ImageView clearKeywordIv = (ImageView) findViewById(R.id.clear_keyword_iv);
		clearKeywordIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mKeywordEt.setText("");
				v.setVisibility(View.GONE);
			}
		});
		mKeywordEt = (EditText) findViewById(R.id.tab_bar_keyword_et);

		mKeywordEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					return true;
				}
				return false;
			}
		});

		mKeywordEt.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() == 0) {
					mOperationTv.setText("搜索");
					clearKeywordIv.setVisibility(View.GONE);
					if (mHistoryKeywords.size() > 0) {
						mSearchHistoryLl.setVisibility(View.VISIBLE);
					} else {
						mSearchHistoryLl.setVisibility(View.GONE);
					}
				} else {
					mSearchHistoryLl.setVisibility(View.GONE);
					mOperationTv.setText("搜索");
					clearKeywordIv.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		mKeywordEt.requestFocus();

		mOperationTv = (TextView) findViewById(R.id.tab_bar_cancel_tv);
		mOperationTv.setText("搜索");
		mOperationTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SearchActivity.this,ChooseActivity.class);
				TextView tv1 = mKeywordEt;
				String tv1String = tv1.getText().toString();
				intent.putExtra("title",tv1String);
				intent.putExtra("key","exam_name");
				startActivity(intent);
			}
		});
		initSearchHistory();
	}

	public void initSearchHistory() {
		mSearchHistoryLl = (LinearLayout) findViewById(R.id.search_history_ll);
		ListView listView = (ListView) findViewById(R.id.search_history_lv);
		findViewById(R.id.clear_history_btn).setOnClickListener(this);
		String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD,"");
		if (!TextUtils.isEmpty(history)){
			List<String> list = new ArrayList<String>();
			for(Object o : history.split(",")) {
				list.add((String)o);
			}
			mHistoryKeywords = list;
		}
		if (mHistoryKeywords.size() > 0) {
			mSearchHistoryLl.setVisibility(View.VISIBLE);
		} else {
			mSearchHistoryLl.setVisibility(View.GONE);
		}
		mArrAdapter = new ArrayAdapter<String>(this, R.layout.item_search_history, mHistoryKeywords);
		listView.setAdapter(mArrAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				mKeywordEt.setText(mHistoryKeywords.get(i));
				mSearchHistoryLl.setVisibility(View.GONE);
			}
		});
		mArrAdapter.notifyDataSetChanged();
	}

	public void save() {
		String text = mKeywordEt.getText().toString();
		String oldText = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD,"");
		System.out.println("zlw======="+oldText);
		if (!TextUtils.isEmpty(text) && !oldText.contains(text)) {
			if(mHistoryKeywords.size()>7){
				Toast.makeText(this,"最多保存8条历史",Toast.LENGTH_SHORT).show();
				return;
			}
			mEditor.putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
			mEditor.commit();
			mHistoryKeywords.add(0,text);
		}
		mArrAdapter.notifyDataSetChanged();
	}

	public void cleanHistory() {
		mEditor.clear();
		mEditor.commit();
		mHistoryKeywords.clear();
		mArrAdapter.notifyDataSetChanged();
		mSearchHistoryLl.setVisibility(View.GONE);
		Toast.makeText(this,"清除搜索历史记录成功", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.clear_history_btn:
				cleanHistory();
				break;
			case R.id.tab_bar_cancel_tv:
				if (mKeywordEt.getText().length() > 0) {
					save();
				} else {
					finish();
				}
				break;
			default:
				break;
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		String keyword = intent.getStringExtra(EXTRA_KEY_KEYWORD);
		if (!TextUtils.isEmpty(keyword)) {
			mKeywordEt.setText(keyword);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

}
