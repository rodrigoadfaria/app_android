package com.vinacredit.activity.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//imagpay
import com.imagpay.SwipeEvent;
import com.imagpay.SwipeHandler;
import com.imagpay.SwipeListener;

//SReader
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
	
	private Animation fadeIn = null;
	private Animation fadeOut = null;
	// Listeners to detect the end of an animation
    private LocalFadeInAnimationListener myFadeInAnimationListener = new LocalFadeInAnimationListener();
    private LocalFadeOutAnimationListener myFadeOutAnimationListener = new LocalFadeOutAnimationListener();
	
	
	
	/* *********************************** */
	private SwipeHandler _handler;
//	private MessageHandler _msgHandler;
	/* *********************************** */
	
    private ImageView	imgUsername;
    private TextView	txtPriceItem, txtSwipeCard;
    private ListView	listSale;
    private Button		btnIdentify;

    
    private String		_str_tmp = "";
    private String 		_str_number_click = "";
    private String		_str_total_price = "0";
    private MySQLiteHelper 	dbSqlite;
	private Account			account;
//    private SaleAdapter		saleAdapter;
    private Adapter			adapter;
    private List<DataItem>	ListdataItem;
    private DataItem		dataItem;
    private Bitmap			bpPhoto;
        
    private int				location;
    private int				location_listview;
    private Bundle extras;
    
    
//    private TimeCount time = null;
	private AudioManager am = null;
	
	private boolean mHeadsetPlugged = false;
	
	/* code SReader audio
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
	end code SReader audio*/
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
					Toast.LENGTH_SHORT).show();
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
			onDetect();
		}
	};	

	private Runnable swipe = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			handler.post(begin_swipe);
			onSwipe();
		}
	};

	
	
	private Runnable timeout_ack = new Runnable() {
		public void run() {
			Toast.makeText(Sale_Activity.this, "Timeout!!!",
					Toast.LENGTH_SHORT).show();
		}
	};

	private Runnable unknown_err = new Runnable() {
		public void run() {
//			result_text.setText(R.string.unknown_error);
			Toast.makeText(Sale_Activity.this, "unknown_error",
					Toast.LENGTH_SHORT).show();
		}
	};

	private Runnable set_version = new Runnable() {
		public void run() {
			String txt = version + "\n";
//			result_text.setText(txt);
			Toast.makeText(Sale_Activity.this, txt,
					Toast.LENGTH_SHORT).show();
//			if(txt.length() > 0)
//				handler.post(swipe);
		}
	};

	private Runnable display_encryptiondata = new Runnable() {
		public void run() {
			String txt = version + "\n\nEncryption data\n";
			txt += encryption_data + "\n\n\n";
//			result_text.setText(txt);
			Toast.makeText(Sale_Activity.this, txt,
					Toast.LENGTH_SHORT).show();
		}
	};

	private Runnable display_decryptiondata = new Runnable() {
		public void run() {
//			String txt = version + "\n\nEncryption data\n";
//			txt += encryption_data + "\n\n\nDecryption data\n";
//			txt += decryption_data + "\n";
//			result_text.setText(txt);
//			Toast.makeText(Sale_Activity.this, txt,Toast.LENGTH_LONG).show();			
			//dialog.dismiss();
	    	Intent i = new Intent(getApplicationContext(),Identify_Activity.class);
			startActivity(i);
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
//			String txt = version + "\n";
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
			myToast = new MyToast(Sale_Activity.this, "Please swipe card...");
			myToast.show();
//			Toast.makeText(Sale_Activity.this, "Please swipe card...",
//					Toast.LENGTH_SHORT).show();
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
//			mToast = Toast.makeText(mContext, txt, Toast.LENGTH_SHORT);
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
    	imgUsername		= (ImageView)findViewById(R.id.imgUsername);
    	txtPriceItem	= (TextView)findViewById(R.id.txtPriceItem);
    	txtSwipeCard	= (TextView)findViewById(R.id.txtSwipeCard);
    	listSale		= (ListView)findViewById(R.id.listSale);
    	btnIdentify		= (Button)findViewById(R.id.btnIdentify);    	

    	
    	dataItem	 = new DataItem();
    	ListdataItem = new ArrayList<DataItem>();
//    	ListdataItem.add(0,dataItem);
    	dataItem.setPriceItem("0");
    	ListdataItem.add(new DataItem(dataItem.getImgItem(), dataItem.getStrItem(),  dataItem.getPriceItem()));
//    	saleAdapter  = new SaleAdapter(this, ListdataItem);
    	adapter		 = new Adapter(this, ListdataItem);
//    	listSale.setAdapter(saleAdapter);
    	listSale.setAdapter(adapter);
    	/* database instance */
    	dbSqlite = new MySQLiteHelper(this);
    	
    	/* account instance */
		account  = new Account();		
		
		extras = getIntent().getExtras();
		if(MACROS.TEST_DATABASE) {
		
		account = dbSqlite.getAccount(extras.getString("EMAIL"));
	    imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
		}
		
		if(MACROS.isReader){
			/* code SReader */
//			am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//
//			IntentFilter iFilter = new IntentFilter();
//			iFilter.addAction(Intent.ACTION_HEADSET_PLUG);
//			iFilter.addCategory(Intent.CATEGORY_DEFAULT);
//			registerReceiver(mHeadsetReceiver, iFilter);
//			setVolumeControlStream(AudioManager.STREAM_MUSIC);
//			int maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//			am.setStreamVolume(AudioManager.STREAM_MUSIC, maxVol, 0);		
			/* end code SReader */
		} else {
			/* code IMagPayManager */
			_handler = new SwipeHandler(this);
			_handler.setReadonly(true);
			_handler.setDownloadEnvironment(true);
			_handler.setUploadEnvironment(true);
			_handler.addSwipeListener(new SwipeListener() {
								
				@Override
				public void onReadData(SwipeEvent event) {
				}

				@Override
				public void onParseData(SwipeEvent event) {
					handler.post(clear_all);
					encryption_data = event.getValue();
					// hex string message
//					handler.post(display_encryptiondata);
//					sendMessage("Final(16)=>% " + result);
					String[] tmps = event.getValue().split(" ");
					StringBuffer sbf = new StringBuffer();
					for (String str : tmps) {
						sbf.append((char) Integer.parseInt(str, 16));
						sbf.append(" ");
					}
					decryption_data = sbf.toString().trim();
					if(decryption_data.length() >= 16)
						handler.post(display_decryptiondata);
					// char message
//					sendMessage("Final(10)=>% " + sbf.toString().trim());
				}

				@Override
				public void onDisconnected(SwipeEvent event) {
//					sendMessage("Device is disconnected!");
//					_testFlag = true;
//					if()
					_handler.powerOff();
//					_handler.onDestroy();
					btnIdentify.setVisibility(View.VISIBLE);
					txtSwipeCard.setVisibility(View.GONE);
					stopAnimation();
					btnIdentify.setBackgroundResource(R.drawable.card_lock);
//					Toast.makeText(getApplicationContext(), "Device is disconnected!", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onConnected(SwipeEvent event) {
//					sendMessage("Device is connected!");
//					Toast.makeText(getApplicationContext(), "Device is connected!", Toast.LENGTH_SHORT).show();
					btnIdentify.setBackgroundResource(R.drawable.card_unlock);
					if(_handler.isReadable())
						_handler.powerOn();
					
				}

				@Override
				public void onStarted(SwipeEvent event) {
//					if (!_testFlag)
//						sendMessage("Device is started");					
					btnIdentify.setVisibility(View.GONE);
					txtSwipeCard.setVisibility(View.VISIBLE);
					runAnimations();
//					Toast.makeText(getApplicationContext(), "Device is started!", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onStopped(SwipeEvent event) {
//					if (!_testFlag)
//						sendMessage("Device is stopped");
//					_handler.powerOff();
					btnIdentify.setBackgroundResource(R.drawable.card_unlock);
//					Toast.makeText(getApplicationContext(), "Device is stopped!", Toast.LENGTH_SHORT).show();
				}
				
			});
			/* end code IMagPayManager */
		}
		
		
		
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
    
    public void ConfirmClear(){
    	ListdataItem.clear();
    	bpPhoto = null;
    	dataItem.setPriceItem("0");
    	dataItem.setImgItem(bpPhoto);
    	ListdataItem.add(0,new DataItem(dataItem.getImgItem(), dataItem.getStrItem(),  dataItem.getPriceItem()));
    	_str_tmp = "";
    	_str_total_price = "0";
    	_str_number_click = "";
    	txtPriceItem.setText("0");
    	adapter.notifyDataSetChanged();
    }
    
    /**
     * clear bill
     * @param view
     */
    public void clear(View view){
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    	
//    	alertDialogBuilder.setTitle("Confirm Clear Bill");
    	
    	alertDialogBuilder.setMessage("Confirm Clear Bill")
    						.setCancelable(false)
    						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									ConfirmClear();
								}
							})
							.setNegativeButton("No", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
    	AlertDialog alertDialog = alertDialogBuilder.create();
    	
    	alertDialog.show();
    }
    
    /**
     * take a photo item
     * @param view
     */
    public void takeItem(View view){
    	location = listSale.getPositionForView(view);
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    
    public void btnIdentify(View view){
//        dialog = ProgressDialog.show(Sale_Activity.this, "", "Swiping card...", true);
//        new Thread(new Runnable() {
//               public void run() {                    
//            	   onSwipe();                   
//               }
//             }).start();
    	if(MACROS.isReader){
    		onSwipe();
    	}    	
//    	Intent i = new Intent(getApplicationContext(),Identify_Activity.class);
//		startActivity(i);
    }   
    
    /**
     * delete item
     * @param v
     */
    public void DeleteItem(View v){
    	int ix = listSale.getPositionForView(v);
    	String _str_p = ListdataItem.get(ix).getPriceItem().replaceAll(",", "");
    	
    	/*------ assign price item into total price ------*/
    	Double b = Double.parseDouble(_str_total_price) - Double.parseDouble(_str_p);
    	Integer i = b.intValue();
    	_str_total_price = String.valueOf(i);
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	ListdataItem.remove(ix);
    	if(ListdataItem.size() < 1)
    		ListdataItem.add(dataItem);
    	adapter.notifyDataSetChanged();
    }
    
    /**
     * check item
     * @param v
     */
    public void CheckItem(View v){
    	location_listview = listSale.getPositionForView(v);
//    	Toast.makeText(getApplicationContext(),edtItem.getText().toString() + ListdataItem.size(), Toast.LENGTH_SHORT).show();
    	dataItem.setImgItem(bpPhoto);
//    	dataItem.setStrItem(edtItem.getText().toString());
    	dataItem.setPriceItem(ListdataItem.get(location_listview).getPriceItem());
    	    	
    	if(_str_tmp.equals("")) return;
    	if(Library.isCheckPrice(_str_tmp)){
    		ListdataItem.add(new DataItem(dataItem.getImgItem(), dataItem.getStrItem(), dataItem.getPriceItem()));
    		adapter.notifyDataSetChanged();
        	
        	/*------ assign price item into total price ------*/
        	Double d = Double.parseDouble(_str_total_price) + Double.parseDouble(_str_tmp);
        	Integer i = d.intValue();
        	_str_total_price = String.valueOf(i);
        	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	} else {
			Toast.makeText(getApplicationContext(), "Enter price item > 100,Please!", Toast.LENGTH_SHORT).show();
		}    	
    	
    	
    	/*------ reset variable ------*/
//    	imgItem.setImageResource(R.drawable.chomsao);
    	bpPhoto = null;
    	ListdataItem.get(location_listview+1).setImgItem(bpPhoto);
    	ListdataItem.get(location_listview+1).setStrItem("");
    	ListdataItem.get(location_listview+1).setPriceItem("0");
    	_str_tmp="";
    }
    
    
    
    /**
     * button number pad
     * @param view
     */
    public void numClick(View view){
    	
    	Log.i("Debug charge","view.getId :" + view.getId());
	
    	switch (view.getId()) {
		case R.id.btn10:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "1";
			break;
    
		case R.id.btn11:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "2";
			break;
    
		case R.id.btn12:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "3";
			break;

		case R.id.btn7:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "4";
			break;
    
		case R.id.btn8:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "5";
			break;
    
		case R.id.btn9:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "6";
			break;
    
		case R.id.btn4:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "7";
			break;
    
		case R.id.btn5:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "8";
			break;
    
		case R.id.btn6:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "9";
			break;
    
		case R.id.btn2:
			if(_str_tmp.equals("") || _str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "0";
		    break;
		case R.id.btn1:
			if(ListdataItem.get(listSale.getCount()-1).getPriceItem().equals("0") || _str_tmp.length() >= 6)
				return;
			else
				_str_number_click = "000";
		    break;
		default:
			break;
	}
    	if(_str_tmp.length() > 9) // 9,000,000,000 length is 13.
    		return; 
    	else
    		_str_tmp = _str_tmp + _str_number_click;
    	
    	if(_str_tmp.length() > 3) {
    		ListdataItem.get(listSale.getCount()-1).setPriceItem(Library.addDotNumber(_str_tmp));
    		adapter.notifyDataSetChanged();
    		return;
    	} else
    	{
    		ListdataItem.get(listSale.getCount()-1).setPriceItem(_str_tmp);
    		adapter.notifyDataSetChanged();
    		Log.i("Debug Charge","_str_tmp :" + _str_tmp);
    	}

    	
    }
    
    /**
     * button clear number pad
     * @param view
     */
    public void clearClick(View view){
    	if(view.getId() == R.id.btn3){
//    	    String _str = txtItem.getText().toString();
    		if(_str_tmp.length() < 1){
    			ListdataItem.get(listSale.getCount()-1).setPriceItem("0");
    		} else {
    			_str_tmp = _str_tmp.substring(0,_str_tmp.length()-1);
    			if(_str_tmp.length() > 3)
    				ListdataItem.get(listSale.getCount()-1).setPriceItem(Library.addDotNumber(_str_tmp));
    			else
    				ListdataItem.get(listSale.getCount()-1).setPriceItem(_str_tmp);
    			if(_str_tmp.length() < 1){
    				ListdataItem.get(listSale.getCount()-1).setPriceItem("0");
        		}
    		}    		
    	}
    	adapter.notifyDataSetChanged();
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
    	_handler.powerOn();
    }
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences share = this.getSharedPreferences("EMAIL",MODE_PRIVATE );
	    SharedPreferences.Editor editor = share.edit();
	    editor.putString("EMAIL",extras.getString("EMAIL"));
	    editor.putString("SUMPRICE", txtPriceItem.getText().toString());
	    editor.putString("DECRYPTION", decryption_data);
	    editor.commit();
	    
	    _handler.powerOff();
	    
//	    sreader.Stop();
//		if (sreader != null) {
//			sreader.Stop();
//			sreader = null;
//			if (myToast != null)
//				myToast.cancel();
////			finish();
////			System.exit(0);
//		}
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_REQUEST) {
			if(resultCode == Activity.RESULT_OK){
				if(data != null){
		            bpPhoto = (Bitmap) data.getExtras().get("data");
		            if(location != listSale.getCount()-1){
		            	ListdataItem.get(location).setImgItem(bpPhoto);
			            adapter.notifyDataSetChanged();
		            } else {
		            	ListdataItem.get(listSale.getCount()-1).setImgItem(bpPhoto);
			            adapter.notifyDataSetChanged();
		            }
		            	
				} else {
					Toast.makeText(getApplicationContext(), "No image.", Toast.LENGTH_SHORT).show();
				}
			}
			
			if(resultCode == Activity.RESULT_CANCELED){
				Toast.makeText(getApplicationContext(), "Picture could not be taken.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	/* code SReader */
	
//	@Override
//	public void onDestroy() {
//		if(MACROS.isReader)
//			unregisterReceiver(mHeadsetReceiver);
//		super.onDestroy();
//	}


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
					data = sreader.ReadCard(15000);

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
						random = sreader.GetRandom(10000);
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
		handler.post(begin_detect);
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
					myToast.cancel();
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
	
	
	/* Animation textview */
	/**
     * Performs the actual fade-out
     */
    private void launchOutAnimation() {

    	txtSwipeCard.startAnimation(fadeOut);

    }    
    
    /**
     * Performs the actual fade-in
     */
    private void launchInAnimation() {
    	txtSwipeCard.startAnimation(fadeIn);
    }    

    /**
     * Starts the animation
     */
    private void runAnimations() {
    	
    	// Setup fadein/out animations
	    fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
	    fadeIn.setAnimationListener( myFadeInAnimationListener );
	    fadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
	    fadeOut.setAnimationListener( myFadeOutAnimationListener );
	    
	    // And start
    	launchInAnimation();
    	
    }
    
    /**
     * Animation listener for fade-out
     * 
     * @author VIJAYAKUMAR
     *
     */
    private class LocalFadeInAnimationListener implements AnimationListener {
    	
	    public void onAnimationEnd(Animation animation) {
	    	launchOutAnimation();
		}
		
	    public void onAnimationRepeat(Animation animation){
	    }
	
	    public void onAnimationStart(Animation animation) {
	    }
    };
    
    /**
     * Animation listener for fade-in
     * 
     * @author VIJAYAKUMAR
     *
     */
    private class LocalFadeOutAnimationListener implements AnimationListener {
    	
	    public void onAnimationEnd(Animation animation) {
	    	launchInAnimation();
		}
		
	    public void onAnimationRepeat(Animation animation) {
	    }
	
	    public void onAnimationStart(Animation animation) {
	    }
    };
    
    public void stopAnimation(){
    	txtSwipeCard.clearAnimation();
    }
}
