package com.vinacredit.Resource;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

public class Library {    
    
    public static String addDotNumber(String str_number){
	Log.i("Debug Library", "str_number :"+ str_number);
 	Log.i("Debug Library","str_number.length :" + str_number.length());
 	String _str_child = str_number.substring(0,1);
 	Log.i("Debug Library", "_str_child :"+ _str_child);
	str_number = str_number.replaceAll(",","");
 	Log.i("Debug Library","str_number.length :" + str_number.length());
	String str_reverse 	= reverseString(str_number);// reverse 
	boolean bl_add_dot = false;
	char[] char_tmp 	= str_reverse.toCharArray();
	
	int count_dot = 0;
	count_dot = char_tmp.length/3;
	Log.i("Debug Library","count_dot :" + count_dot);
	//if(char_tmp.length % 3 == 0)
	    //count_dot -=1;
	Log.i("Debug Library","char_tmp.length :" + char_tmp.length);
	char char_add_dot_tmp[];
	char_add_dot_tmp = new char[char_tmp.length + count_dot];
	int count_add_dot_tmp = 0;
	for(int i=0; i<char_tmp.length; i++){
	    if( i % 3 == 0 && i !=0)bl_add_dot = true;
	    if(bl_add_dot){
		char_add_dot_tmp[count_add_dot_tmp++] = ',';
		bl_add_dot = false;
	    }
	    Log.i("Debug Library","char_tmp[i] :" + char_tmp[i]);
	    
	    char_add_dot_tmp[count_add_dot_tmp++] = char_tmp[i];
	}
	String str_tmp;
	if(char_add_dot_tmp.length == 8)
	    str_tmp = String.copyValueOf(char_add_dot_tmp, 0,char_add_dot_tmp.length - 1);
	else
	    str_tmp = String.copyValueOf(char_add_dot_tmp, 0,char_add_dot_tmp.length);
	Log.i("Debug Library","char_add_dot_tmp.length :" + char_add_dot_tmp.length);
	Log.i("Debug Library","str_tmp :" + str_tmp);
	//String str_tmp = "";
	str_tmp = reverseString(str_tmp);
	return str_tmp;
    }
    
    public static void Translate(boolean language) {

	   if(language){
	       
	        MACROS.WELCOME_TO_VINACREDIT_LBL   		= "Welcome to Vinacredit";// lbl : label
	        MACROS.WELCOME_TEXT_LBL            		= "Free credit card reader Take your first payment today Nightly deposits to your bank";
	        MACROS.WELCOME_SIGNIN_BTN          		= "Sign In";// btn : butto
	        
	        MACROS.SIGNIN_LBL                  		= "Sign In";
	        MACROS.SIGNIN_CANCEL_BTN           		= "Cancel";
	        MACROS.SIGNIN_BTN                  		= "SignIn";
	        MACROS.SIGNIN_FORGOT_BTN           		= "Forgot Password?";
	        MACROS.SIGNIN_STATUS_LBL           		= "Login Status";
	        MACROS.SIGNIN_EMAIL_TXT            		= "Email Address";
	        MACROS.SIGNIN_PASS_TXT             		= "Password";
	        MACROS.SIGNIN_RESULT_LOGIN_TRUE_LBL             = "Login Successful!!";
	        MACROS.SIGNIN_RESULT_LOGIN_FALSE_LBL            = "Login failed !";

	        MACROS.WRONGPASS_LBL            		= "Wrong Pass";
	        MACROS.WRONGPASS_EMAIL_TXT      		= "Email Address";
	        MACROS.WRONGPASS_SEND_BTN       		= "Send";
	        MACROS.WRONGPASS_CANCEL_BTN       		= "Cancel";
	        MACROS.WRONGPASS_RESULT_LBL     		= "Enter email again";

	        MACROS.INFOR_LBL                		= "Information";
	        MACROS.INFOR_ENTER_LBL          		= "Enter Your Information";
	        MACROS.INFOR_CONTINUE_BTN       		= "Continue";
	        MACROS.INFOR_FIRSTNAME_TXT      		= "firstname";
	        MACROS.INFOR_LASTNAME_TXT       		= "lastname";
	        MACROS.INFOR_COMPANY_TXT        		= "company name";
	        MACROS.INFOR_ADDRESS_TXT       		 	= "address";
	        MACROS.INFOR_OLDPASS_TXT        		= "old password";
	        MACROS.INFOR_NEWPASS_TXT        		= "at least 8 characters";
	        MACROS.INFOR_CONFIRMPASS_TXT    		= "enter again ";
	        MACROS.INFOR_EMAIL_LBL          		= "Email:";
	        MACROS.INFOR_OLDPASS_LBL        		= "Old Pass:";
	        MACROS.INFOR_NEWPASS_LBL        		= "Password:";
	        MACROS.INFOR_CONFIRMPASS_LBL    		= "Confirm :";

	        MACROS.SALE_LBL                		 	= "Sale";
	        MACROS.SALE_TAKEPHOTO_LBL       		= "Take Photo";
	        MACROS.SALE_CHOOSEPHOTO_LBL     		= "Choose Photo";
	        MACROS.SALE_DELETEPHOTO_LBL     		= "Delete Photo";
	        MACROS.SALE_CANCEL_LBL          		= "Cancel";
	        MACROS.SALE_SWIPE_CARD          		= "Swipe Card";
	        MACROS.SALE_ACCOUNT						= "Account";
	        MACROS.SALE_CHARGE						= "Charge";	
	        
	        MACROS.CHARGE_LBL               		= "Charge";
	        MACROS.CHARGE_CASH_LBL          		= "Cash Payment";
	        MACROS.CHARGE_TENDER_BTN        		= "Tender Cash";

	        MACROS.RECEIPT_EMAIL_TXT        		= "Email Address";
	        MACROS.RECEIPT_SKIP_BTN         		= "Skip";
	        MACROS.RECEIPT_SEND_BTN         		= "Send";
	        MACROS.RECEIPT_RESULT_TRUE_LBL  		= "You check email.";
	        MACROS.RECEIPT_RESULT_FALSE_LBL 		= "wrong email !";

	        MACROS.SIGNATURE_LBL					= "Signature";
	        MACROS.SIGNATURE_RIGHT_BTN      		= "Next";
	        MACROS.SIGNTURE_CLEAR_BTN       		= "Clear";
	        MACROS.SIGNATURE_TEXT_LBL       		= "I agree to pay the amount above according to my card issues agreement";

	        MACROS.IDENTIFY_LBL             		= "Identify";
	        MACROS.IDENTIFY_RIGHT_BTN       		= "Signature";
	        MACROS.IDENTIFY_TEXT_LBL        		= "Image used to identify with Bank";
	        MACROS.IDENTIFY_PIC_LBL         		= "picture";
	        MACROS.IDENTIFY_TAKEPHOTO_BTN   		= "shooting";

	        MACROS.SEND_LBL                 		= "Sending";

	        MACROS.ACCOUNT_LBL              		= "Account";
	        MACROS.ACCOUNT_RIGHT_BTN        		= "Sign Out";
	        MACROS.ACCOUNT_SALEHISTORY_LBL  		= "Sales History";
	        MACROS.ACCOUNT_TAX_LBL          		= "Tax";
	        MACROS.ACCOUNT_SUPPORT_LBL      		= "Support";
	        MACROS.ACCOUNT_CANCEL_LBL       		= "Cancel";

	        MACROS.SALEHISTORY_LBL          		= "Sale History";

	        MACROS.DETAILSALE_LBL           		= "Detail Sale";
	        MACROS.DETAILSALE_TOTAL_LBL     		= "Total:";
	        MACROS.DETAILSALE_BILL_LBL      		= "Bill";

	        MACROS.TAX_LBL                  		= "Tax";
	        MACROS.TAX_SALETAX_LBL          		= "Add Sale Tax";
	        MACROS.TAX_TAXRATE_LBL          		= "Tax Rate";
	        MACROS.TAX_TEXT_LBL             		= "Add a percentage to you payments to account for state taxes.";

	        MACROS.SUPPORT_LBL              		= "Support";
	        MACROS.SUPPORT_VINA_LBL         		= "Vinacredit";
	        MACROS.SUPPORT_HOTLINE_LBL      		= "Hotline";
	        MACROS.SUPPORT_INSTRU_LBL       		= "Instructions";

	        MACROS.DONE_PAID_LBL            		= "paid, Thanks.";
	        MACROS.DONE_BTN                 		= "Done";        
	    }
	    else{
	        MACROS.WELCOME_TO_VINACREDIT_LBL   		= "Chào mừng đến Vinacredit";// lbl : label
	        MACROS.WELCOME_TEXT_LBL            		= "Thanh toán thẻ tín dụng trực tiếp";
	        MACROS.WELCOME_SIGNIN_BTN          		= "Đăng Nhập";// btn : button

	        MACROS.SIGNIN_LBL                  		= "Đăng Nhập";
	        MACROS.SIGNIN_CANCEL_BTN           		= "Thoát";
	        MACROS.SIGNIN_BTN                  		= "Đăng Nhập";
	        MACROS.SIGNIN_FORGOT_BTN           		= "Quên mật khẩu?";
	        MACROS.SIGNIN_STATUS_LBL           		= "Trạng thái đăng nhập";
	        MACROS.SIGNIN_EMAIL_TXT            		= "Địa chỉ email";
	        MACROS.SIGNIN_PASS_TXT             		= "Mật khẩu";
	        MACROS.SIGNIN_RESULT_LOGIN_TRUE_LBL        	= "Đăng Nhập Thành Công!";
	        MACROS.SIGNIN_RESULT_LOGIN_FALSE_LBL       	= "Đăng Nhập Thất Bại !";

	        MACROS.WRONGPASS_LBL            		= "Lấy lại mật khẩu";
	        MACROS.WRONGPASS_EMAIL_TXT      		= "Địa chỉ email";
	        MACROS.WRONGPASS_SEND_BTN       		= "Gửi";
	        MACROS.WRONGPASS_CANCEL_BTN       		= "Thoát";
	        MACROS.WRONGPASS_RESULT_LBL     		= "Nhập lại email";

	        MACROS.INFOR_LBL                		= "Trang cá nhân";
	        MACROS.INFOR_ENTER_LBL          		= "Thay đổi thông tin cá nhân";
	        MACROS.INFOR_CONTINUE_BTN       		= "Tiếp tục";
	        MACROS.INFOR_FIRSTNAME_TXT      		= "tên";
	        MACROS.INFOR_LASTNAME_TXT       		= "họ";
	        MACROS.INFOR_COMPANY_TXT        		= "tên công ty";
	        MACROS.INFOR_ADDRESS_TXT        		= "địa chỉ";
	        MACROS.INFOR_OLDPASS_TXT        		= "mật khẩu cũ";
	        MACROS.INFOR_NEWPASS_TXT        		= "mật khẩu mới";
	        MACROS.INFOR_CONFIRMPASS_TXT    		= "nhập lại";
	        MACROS.INFOR_EMAIL_LBL          		= "Email:";
	        MACROS.INFOR_OLDPASS_LBL        		= "Mật khẩu cũ:";
	        MACROS.INFOR_NEWPASS_LBL        		= "Mật khẩu mới:";
	        MACROS.INFOR_CONFIRMPASS_LBL    		= "Xác nhận :";

	        MACROS.SALE_LBL                 		= "Bán hàng";
	        MACROS.SALE_TAKEPHOTO_LBL       		= "Chụp hình";
	        MACROS.SALE_CHOOSEPHOTO_LBL     		= "Chọn hình";
	        MACROS.SALE_DELETEPHOTO_LBL     		= "Xoá hình";
	        MACROS.SALE_CANCEL_LBL          		= "Thoát";
	        MACROS.SALE_SWIPE_CARD          		= "Quét Thẻ Visa";
	        MACROS.SALE_ACCOUNT						= "Tài khoản";
	        MACROS.SALE_CHARGE						= "Thanh Toán";	

	        MACROS.CHARGE_LBL               		= "Thanh toán";
	        MACROS.CHARGE_CASH_LBL          		= "Thanh toán tiền mặt";
	        MACROS.CHARGE_TENDER_BTN        		= "Thanh toán";

	        MACROS.RECEIPT_EMAIL_TXT        		= "Địa chỉ email";
	        MACROS.RECEIPT_SKIP_BTN         		= "Bỏ qua";
	        MACROS.RECEIPT_SEND_BTN         		= "Gửi";
	        MACROS.RECEIPT_RESULT_TRUE_LBL  		= "Thư đã được gởi đến hộp thư của bạn.";
	        MACROS.RECEIPT_RESULT_FALSE_LBL 		= "Địa chỉ thư sai !";

	        MACROS.SIGNATURE_LBL					= "Chữ ký";
	        MACROS.SIGNATURE_RIGHT_BTN      		= "Tiếp tục";
	        MACROS.SIGNTURE_CLEAR_BTN       		= "Xoá";
	        MACROS.SIGNATURE_TEXT_LBL       		= "Tôi đồng ý trả số tiền trên theo thỏa thuận các vấn đề thẻ của tôi";

	        MACROS.IDENTIFY_LBL             		= "Xác nhận";
	        MACROS.IDENTIFY_RIGHT_BTN       		= "Ký tên";
	        MACROS.IDENTIFY_TEXT_LBL        		= "Hình ảnh được sử dụng để xác nhận với Ngân hàng";
	        MACROS.IDENTIFY_PIC_LBL         		= "Hình ảnh";
	        MACROS.IDENTIFY_TAKEPHOTO_BTN   		= "Chụp hình";

	        MACROS.SEND_LBL                 		= "Đang gửi";

	        MACROS.ACCOUNT_LBL              		= "Trang cá nhân";
	        MACROS.ACCOUNT_RIGHT_BTN        		= "Đăng xuất";
	        MACROS.ACCOUNT_SALEHISTORY_LBL  		= "Lịch sử bán hàng";
	        MACROS.ACCOUNT_TAX_LBL          		= "Thuế";
	        MACROS.ACCOUNT_SUPPORT_LBL      		= "Hỗ trợ";
	        MACROS.ACCOUNT_CANCEL_LBL       		= "Thoát";

	        MACROS.SALEHISTORY_LBL          		= "Lịch sử bán hàng";

	        MACROS.DETAILSALE_LBL           		= "Chi tiết bán hàng";
	        MACROS.DETAILSALE_TOTAL_LBL     		= "Tổng:";
	        MACROS.DETAILSALE_BILL_LBL      		= "Hoá đơn";

	        MACROS.TAX_LBL                  		= "Thuế";
	        MACROS.TAX_SALETAX_LBL          		= "Thuế bán hàng";
	        MACROS.TAX_TAXRATE_LBL          		= "Tỉ giá thuế";
	        MACROS.TAX_TEXT_LBL             		= "Thêm tỷ lệ phần trăm để bạn thanh toán vào tài khoản cho các khoản thuế nhà nước.";

	        MACROS.SUPPORT_LBL              		= "Hỗ trợ";
	        MACROS.SUPPORT_VINA_LBL         		= "Vinacredit";
	        MACROS.SUPPORT_HOTLINE_LBL      		= "Đường dây nóng";
	        MACROS.SUPPORT_INSTRU_LBL       		= "Hướng dẫn";

	        MACROS.DONE_PAID_LBL            		= "đã trả, cảm ơn.";
	        MACROS.DONE_BTN                 		= "Hoàn thành.";
	        
	    }
}
    static String reverseString(String str){
	String tmp 	= "";
        for(int i = str.length() - 1; i >=0; i--){
            tmp += str.charAt(i);
        }
        return tmp;
    }
    
    /**
     * convert byte[] to bitmap
     * @param bitmap
     * @return byte[]
     */
    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }
    
    /**
     * convert bitmap to byte[]
     * @return
     */
    public static Bitmap getBitmapFromByte(byte[] bitmap){
    	return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
    }
    /**
     * convert view to bitmap
     * @param view
     * @return
     */
	public static Bitmap getBitmapFromView(View view) {
	    Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.RGB_565);
	    Canvas canvas = new Canvas(returnedBitmap);
	    view.draw(canvas);
	    return returnedBitmap;
	}
    
    /**
     * get current date
     * @return String
     */
    public static String getDate(){
    	Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EE,dd-MM-yyyy");
        return df.format(c.getTime());
    }
    
    /**
     * get current time
     * @return String
     */
    public static String getTime(){
    	Calendar c = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        return dt.format(c.getTime());
    }
    
    public static boolean isCheckPrice(String price){
    	
    	double iPrice = Double.parseDouble(price.replaceAll(",",""));
    	if(iPrice % 100 == 0)
    		return true;
    	return false;
    }

}
