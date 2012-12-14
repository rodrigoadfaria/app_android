package com.vinacredit.activity.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.singular.hijack.SReaderApi;
import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;
import com.vinacredit.activity.Sale.Identify.Identify_Activity;

import con.vinacredit.DTO.Account;



public class Sale_Activity extends Activity{
	
	private static final int CAMERA_REQUEST = 1888;
	
    private Button 		btn_gotoAccount, btn_gotoCharge;
    private TextView	txtTitleBar;
    private Button		btnTakeItem, btnClear;
    private ImageView	imgUsername, imgItem;
    private TextView	txtPriceItem, txtItem;
    private ListView	listSale;
    private EditText	edtItem;
    
    private String		_str_tmp = "";
    private String 		_str_number_click = "";
    private String		_str_total_price = "0";
    private MySQLiteHelper 	dbSqlite;
	private Account			account;
    private SaleAdapter		saleAdapter;
    private List<DataItem>	ListdataItem;
    private DataItem		dataItem;
    private Bitmap			bpPhoto;
    
    private Bundle extras;
    
    
//    private TimeCount time = null;
	private AudioManager am = null;
	
	private boolean mHeadsetPlugged = false;
	private BroadcastReceiver mHeadsetReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_HEADSET_PLUG)) {
				boolean hasHeadset = (intent.getIntExtra("state", 0) == 1);
				boolean hasMicroPhone = (intent.getIntExtra("microphone", 0) == 1);
				if (hasHeadset && hasMicroPhone) {
					mHeadsetPlugged = true;
//					handler.post(enable_detect);
					handler.post(mHeadsetPluginHandler);
					handler.post(detect);
				} else {
					mHeadsetPlugged = false;
					handler.post(mHeadsetPluginHandler);
					if (sreader != null)
						sreader.Stop();
//					handler.post(disable_button);
				}
				
			}
		}
	};
	
	private Handler handler = new Handler();
	SReaderApi sreader = null;

	private String version = null;
	private String ksn = null;
	private String random = null;
	private String workingkey = null;

	private String encryption_data = null;
	private String decryption_data = null;

	private Runnable mHeadsetPluginHandler = new Runnable() {
		public void run() {
			String plug_str = mHeadsetPlugged ? "plugin" : "unplugin";
			Toast.makeText(Sale_Activity.this, "Headset " + plug_str,
					Toast.LENGTH_LONG).show();
			if (sreader != null) {
				CloseSinWave();
//				finish();
			}
		}
	};

	private Runnable detect = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.post(begin_detect);
			onDetect();
		}
	};	

	private Runnable swipe = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.post(begin_swipe);
			onSwipe();
		}
	};

	
	
	private Runnable timeout_ack = new Runnable() {
		public void run() {
			Toast.makeText(Sale_Activity.this, "Timeout!!!",
					Toast.LENGTH_LONG).show();
		}
	};

	private Runnable unknown_err = new Runnable() {
		public void run() {
//			result_text.setText(R.string.unknown_error);
		}
	};

	private Runnable set_version = new Runnable() {
		public void run() {
			String txt = version + "\n";
//			result_text.setText(txt);
			Toast.makeText(Sale_Activity.this, txt,
					Toast.LENGTH_LONG).show();
			if(txt.length() > 0)
				handler.post(swipe);
		}
	};

	private Runnable display_encryptiondata = new Runnable() {
		public void run() {
			String txt = version + "\n\nEncryption data\n";
			txt += encryption_data + "\n\n\n";
//			result_text.setText(txt);
			Toast.makeText(Sale_Activity.this, txt,
					Toast.LENGTH_LONG).show();
		}
	};

	private Runnable display_decryptiondata = new Runnable() {
		public void run() {
			String txt = version + "\n\nEncryption data\n";
			txt += encryption_data + "\n\n\nDecryption data\n";
			txt += decryption_data + "\n";
//			result_text.setText(txt);
			Toast.makeText(Sale_Activity.this, txt,
					Toast.LENGTH_LONG).show();
		}
	};

	private Runnable clear_all = new Runnable() {
		public void run() {
			version = "";
			encryption_data = "";
			decryption_data = "";
//			result_text.setText("");
		}
	};

	private Runnable clear_encryption = new Runnable() {
		public void run() {
			encryption_data = "";
			decryption_data = "";
			String txt = version + "\n";
//			result_text.setText(txt);
		}
	};

	private Runnable begin_detect = new Runnable() {
		public void run() {
			myToast = new MyToast(Sale_Activity.this,
					"Card Reader Detecting...");
			myToast.show();
		}
	};

	private Runnable begin_swipe = new Runnable() {
		public void run() {
//			myToast = new MyToast(Sale_Activity.this, "Please swipe card...");
//			myToast.show();
			Toast.makeText(Sale_Activity.this, "Please swipe card...",
					Toast.LENGTH_SHORT).show();
		}
	};

	public class MyToast {
		private Context mContext = null;
		private Toast mToast = null;
		private Handler mHandler = null;
		private Runnable mToastThread = new Runnable() {

	
			public void run() {
				mToast.show();
				mHandler.postDelayed(mToastThread, 1000);
			}
		};

		public MyToast(Context context, String txt) {
			mContext = context;
			mHandler = new Handler(mContext.getMainLooper());
			mToast = Toast.makeText(mContext, txt, Toast.LENGTH_SHORT);
		}

		public void setText(String text) {
			mToast.setText(text);
		}

		public void show() {
			mHandler.post(mToastThread);
		}

		public void cancel() {
			mHandler.removeCallbacks(mToastThread);
			mToast.cancel();
		}
	}

	private MyToast myToast = null;  
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_sale);
    	initialize();
    	
    }
    private void initialize() {
    	// TODO Auto-generated method stub
    	btn_gotoAccount = (Button)findViewById(R.id._btnAccount);
    	btn_gotoCharge  = (Button)findViewById(R.id._btnSaleCharge);
    	txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
    	btnTakeItem		= (Button)findViewById(R.id.btnTakeItem);
    	btnClear		= (Button)findViewById(R.id.btnClear);
    	imgUsername		= (ImageView)findViewById(R.id.imgUsername);
    	txtPriceItem	= (TextView)findViewById(R.id.txtPriceItem);
    	listSale		= (ListView)findViewById(R.id.listSale);
    	txtItem			= (TextView)findViewById(R.id.txtItem);
    	edtItem			= (EditText)findViewById(R.id.edtItem);
    	imgItem			= (ImageView)findViewById(R.id.imgItem);
    	/* tranlate language */
    	translate();
    	
    	dataItem	 = new DataItem();
    	ListdataItem = new ArrayList<DataItem>();
    	saleAdapter  = new SaleAdapter(this, ListdataItem);
    	listSale.setAdapter(saleAdapter);
    	
    	/* database instance */
    	dbSqlite = new MySQLiteHelper(this);
    	
    	/* account instance */
		account  = new Account();		
		
		extras = getIntent().getExtras();
		if(MACROS.TEST_DATABASE) {
		
		account = dbSqlite.getAccount(extras.getString("EMAIL"));
	    imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
		}
		
		
		/* code SReader */
		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(Intent.ACTION_HEADSET_PLUG);
		iFilter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(mHeadsetReceiver, iFilter);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		int maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		am.setStreamVolume(AudioManager.STREAM_MUSIC, maxVol, 0);
    }
    
    private void translate() {
	// TODO Auto-generated method stub
    	txtTitleBar.setText(MACROS.SALE_LBL);
    	btn_gotoAccount.setText(MACROS.SALE_ACCOUNT);
    	btn_gotoCharge.setText(MACROS.SALE_CHARGE);
    	
    }
    
    /**
     * button account
     * @param view
     */
    public void gotoAccount(View view){
    	Intent i = new Intent(getApplicationContext(),Account_Activity.class);
    	i.putExtra("EMAIL", account.getEmail());
		startActivity(i);
    }
    
    /**
     * button charge
     * @param view
     */
    public void gotoCharge(View view){
    	
    	Intent i = new Intent(getApplicationContext(),Charge_Activity.class);
    	Bundle myBundle = new Bundle();
    	myBundle.putString("EMAIL", account.getEmail());
    	myBundle.putString("PRICEITEM", txtPriceItem.getText().toString());
    	i.putExtras(myBundle);
		startActivity(i);
    }
    
    /**
     * clear bill
     * @param view
     */
    public void clear(View view){
    	ListdataItem.clear();
    	_str_tmp = "";
    	_str_total_price = "0";
    	_str_number_click = "";
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * take a photo item
     * @param view
     */
    public void takeItem(View view){
    	
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    
    public void btnIdentify(View view){
    	Intent i = new Intent(getApplicationContext(),Identify_Activity.class);
		startActivity(i);
    }
    
    /**
     * add item
     * @param view
     */
    public void AddItem(View view){
//    	Toast.makeText(getApplicationContext(), "demo add item", Toast.LENGTH_LONG).show();
    	dataItem.setImgItem(bpPhoto);
    	dataItem.setStrItem(edtItem.getText().toString());
    	dataItem.setQuantityItem("1x");
    	dataItem.setPriceItem(txtItem.getText().toString());
    	if(_str_tmp.length() > 0){
    		ListdataItem.add(0,new DataItem(dataItem.getImgItem(), dataItem.getStrItem(), dataItem.getQuantityItem(), dataItem.getPriceItem()));
        	saleAdapter.notifyDataSetChanged();
        	
        	/*------ assign price item into total price ------*/
        	_str_total_price = String.valueOf(Integer.parseInt(_str_total_price) + Integer.parseInt(_str_tmp));
        	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	} else {
			Toast.makeText(getApplicationContext(), "Enter price item,Please!", Toast.LENGTH_SHORT).show();
		}    	
	    
    	/*------ reset variable ------*/
    	imgItem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.chomsao));
    	bpPhoto = null;
    	_str_tmp="";
    	txtItem.setText("0");
    	edtItem.setText("");
    }
    
    /**
     * Plus price item
     * @param v
     */
    public void PlusItem(View v){
    	int i = listSale.getPositionForView(v);
    	Toast.makeText(getApplicationContext(), ListdataItem.get(i).getStrItem(), Toast.LENGTH_SHORT).show();
    	int count = ListdataItem.get(i).getQtyItem();
    	count++;
    	ListdataItem.get(i).setQtyItem(count);
    	ListdataItem.get(i).setQuantityItem(count + "x");
    	String _str_p = ListdataItem.get(i).getPriceItem().replaceAll(",", "");
    	/*------ assign price item into total price ------*/
    	_str_total_price = String.valueOf(Integer.parseInt(_str_total_price) + Integer.parseInt(_str_p));
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * minus price item
     * @param v
     */
    public void MinusItem(View v){
    	int ix = listSale.getPositionForView(v);
    	Toast.makeText(getApplicationContext(), ListdataItem.get(ix).getStrItem(), Toast.LENGTH_SHORT).show();
    	int count = ListdataItem.get(ix).getQtyItem();
    	String _str_p = ListdataItem.get(ix).getPriceItem().replaceAll(",", "");
    	count--;
    	/*------ assign price item into total price ------*/
    	_str_total_price = String.valueOf(Integer.parseInt(_str_total_price) - Integer.parseInt(_str_p));
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	
    	if(count < 1) 
    		{
    			ListdataItem.remove(ix);
    			saleAdapter.notifyDataSetChanged();
    			return;
    		}
    	ListdataItem.get(ix).setQtyItem(count);
    	ListdataItem.get(ix).setQuantityItem(count + "x");    	
    	
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * button number pad
     * @param view
     */
    public void numClick(View view){
    	
    	Log.i("Debug charge","view.getId :" + view.getId());
	
    	switch (view.getId()) {
		case R.id.btn10:
		    _str_number_click = "1";
			break;
    
		case R.id.btn11:
		    _str_number_click = "2";
			break;
    
		case R.id.btn12:
		    _str_number_click = "3";
			break;

		case R.id.btn7:
		    _str_number_click = "4";
			break;
    
		case R.id.btn8:
		    _str_number_click = "5";
			break;
    
		case R.id.btn9:
		    _str_number_click = "6";
			break;
    
		case R.id.btn4:
		    _str_number_click = "7";
			break;
    
		case R.id.btn5:
		    _str_number_click = "8";
			break;
    
		case R.id.btn6:
		    _str_number_click = "9";
			break;
    
		case R.id.btn2:
		    _str_number_click = "0";
		    break;
		case R.id.btn1:
		    _str_number_click = "000";
		    break;
		default:
			break;
	}
    	if(_str_tmp.length() > 13) return; // 9,000,000,000 length is 13.
	_str_tmp = _str_tmp + _str_number_click;
	if(_str_tmp.length() > 3) {
		txtItem.setText(Library.addDotNumber(_str_tmp));
		return;
	}		
	Log.i("Debug Charge","_str_tmp :" + _str_tmp);
	txtItem.setText(_str_tmp);
    }
    
    /**
     * button clear number pad
     * @param view
     */
    public void clearClick(View view){
    	if(view.getId() == R.id.btn3){
//    	    String _str = txtItem.getText().toString();
    		if(_str_tmp.length() < 1){
    			txtItem.setText("0");
    		} else {
    			_str_tmp = _str_tmp.substring(0,_str_tmp.length()-1);
    			if(_str_tmp.length() > 3)
    				txtItem.setText(Library.addDotNumber(_str_tmp));
    			else
    				txtItem.setText(_str_tmp);
    			if(_str_tmp.length() < 1){
        			txtItem.setText("0");
        		}
    		}    		
    	}
    }
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
//    	super.onBackPressed();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	translate();
    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences share = this.getSharedPreferences("EMAIL",MODE_WORLD_READABLE );
	    SharedPreferences.Editor editor = share.edit();
	    editor.putString("EMAIL",extras.getString("EMAIL"));
	    editor.putString("SUMPRICE", txtPriceItem.getText().toString());
	    editor.commit();
	    
//	    sreader.Stop();
		if (sreader != null) {
			sreader.Stop();
			sreader = null;
			if (myToast != null)
				myToast.cancel();
//			finish();
//			System.exit(0);
		}
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_REQUEST) {
            bpPhoto = (Bitmap) data.getExtras().get("data");
            imgItem.setImageBitmap(bpPhoto);
		}
	}
	
	
	/* code SReader */
	
	@Override
	public void onDestroy() {
		unregisterReceiver(mHeadsetReceiver);
		super.onDestroy();
	}


	private void onSwipe() {
		if (sreader == null)
			return;
		new Thread() {
			public void run() {
				String data = null;
				decryption_data = null;
				encryption_data = null;
				handler.post(clear_encryption);
				handler.post(begin_swipe);
				try {
					data = sreader.ReadCard(1500);

				} catch (Exception ex) {
					if (ex instanceof TimeoutException) {
//						time.cancel();
						myToast.cancel();
						sreader.Cancel();
//						handler.post(enable_button);
						handler.post(timeout_ack);
						return;
					} else
						handler.post(unknown_err);
					CloseSinWave();
				}


				myToast.cancel();
//				time.cancel();

				if (data == null) {
					encryption_data = sreader.GetErrorString();
					if (encryption_data.equalsIgnoreCase("cancel all"))
						return;
					handler.post(display_encryptiondata);
				} else {
					encryption_data = "\n" + data;
					handler.post(display_encryptiondata);
					String d_str = sreader.TriDesDecryption(workingkey, data);

					if (d_str != null) {
						if (false == d_str.startsWith("A1")) {
							return;
						}
						int index2 = FindSplitCharIndex(d_str, "A2", 2);
					
						int index3 = FindSplitCharIndex(d_str, "A3", index2 + 2);
					
						if (index2 < 0 || index3 < 0) {
							return;
						}
						String t1 = d_str.substring(2, index2);
						String t2 = d_str.substring(index2 + 2, index3);
						String t3 = d_str.substring(index3 + 2);
						String ex_msg = "";

						if (t1.equals(""))
							decryption_data = "\nT1=" + "T1 Empty";
						else
							decryption_data = "\nT1="
									+ changeHexString2CharString(t1);
						if (t2.equals(""))
							decryption_data += "\nT2=" + "T2 Empty";
						else {
							String e2 = changeHexString2CharString(t2);

							if (e2.length() < 24 || e2.length() > 40)
								ex_msg = "\nTrack2 "
										+ getResources().getText(
												R.string.de_len) + e2.length()
										+ "byte";
							decryption_data += "\nT2=" + e2;
						}
						if (t3.equals(""))
							decryption_data += "\nT3=" + "T3 Empty";
						else
							decryption_data += "\nT3="
									+ changeHexString2CharString(t3) + ex_msg;
						handler.post(display_decryptiondata);
					}

					try {
						random = sreader.GetRandom(1000);
						if (random == null) {
							String err = sreader.GetErrorString();
							if (err.equalsIgnoreCase("cancel all"))
								return;
						}
						workingkey = sreader.GenerateWorkingKey(random, ksn);
					} catch (Exception ex) {
						if (ex instanceof TimeoutException) {
							handler.post(timeout_ack);
							sreader.Cancel();
//							handler.post(enable_button);
//							handler.post(settext_swpie);
							return;
						} else
							handler.post(unknown_err);
						CloseSinWave();
					}
				}
//				handler.post(enable_button);
//				handler.post(settext_swpie);
			}
		}.start();
	}

	private int FindSplitCharIndex(String str, String split, int start) {
		int i = start;
		while (i < str.length() && i + 1 < str.length()) {
			String e = str.substring(i, i + 2);
			if (e.equals(split)) {
				return i;
			}
			i += 2;
		}
		return -1;
	}


	private String changeHexString2CharString(String e) {
		String char_txt = "";
		for (int i = 0; i < e.length(); i = i + 2) {
			String c = e.substring(i, i + 2);
			char j = (char) Integer.parseInt(c, 16);
			char_txt += j;
		}
		return char_txt;
	}

	private boolean Detect_sReader() {
		mHeadsetPlugged = HeadSetUtils.checkHeadset();
		if (!mHeadsetPlugged) {
//			result_text.setText(R.string.nodevice);
		}
		return mHeadsetPlugged;
	}

	private boolean GenerateSinWave() {
		sreader = SReaderApi.getSreaderInstance();
		if (sreader.Init() == true) {
			sreader.Start();
			am.setMode(AudioManager.MODE_NORMAL);
			return true;
		}
		return false;
	}

	private void CloseSinWave() {
		if (sreader != null)
			sreader.Stop();
	}

	private void Initialization() {
		new Thread() {
			public void run() {
				int i = 0;
				try {
					int j = 1;
					boolean s_init = false;
					while (j < 5) {
						try {
							s_init = sreader.Initial(2500);
							if (s_init)
								break;
						} catch (Exception ex) {
							if (ex instanceof TimeoutException) {
								if (j == 4) {
									handler.post(timeout_ack);
								} else
									sleep(1000);
							} else {
								handler.post(unknown_err);
								break;
							}
						}
						j++;
					}
					if (!s_init) {
//						time.cancel();
						myToast.cancel();
//						handler.post(enable_detect);
						CloseSinWave();
						return;
					}

					version = sreader.GetVersion(10000);

					i++;
					
					ksn = sreader.GetKSN(10000);

					if (ksn == null) {
						String err = sreader.GetErrorString();
						if (err.equalsIgnoreCase("cancel all"))
							return;
						throw new Exception("ksn is null");
					}

					i++;

					random = sreader.GetRandom(10000);

					if (random == null) {
						String err = sreader.GetErrorString();
						if (err.equalsIgnoreCase("cancel all"))
							return;
						throw new Exception("random is null");
					}

					workingkey = sreader.GenerateWorkingKey(random, ksn);


					if (workingkey == null) {
						String err = sreader.GetErrorString();
						if (err.equalsIgnoreCase("cancel all"))
							return;
						throw new Exception("workingkey is null");
					}

//					time.cancel();
					myToast.cancel();
//					handler.post(enable_button);
					handler.post(set_version);
				} catch (Exception ex) {
//					time.cancel();
//					myToast.cancel();
					if (ex instanceof TimeoutException) {
						handler.post(timeout_ack);
					} else
						handler.post(unknown_err);
//					handler.post(enable_detect);
					CloseSinWave();
				}
			}
		}.start();
	}

	private void onDetect() {
		if (Detect_sReader() == true) {
			handler.post(clear_all);
			if (GenerateSinWave() == true) {
				Initialization();
			}
		}
	}
    
}
