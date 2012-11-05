package com.vinacredit.Resource;
import com.vinacredit.Resource.*;
public class Library {
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

	        MACROS.CHARGE_LBL               		= "Charge";
	        MACROS.CHARGE_CASH_LBL          		= "Cash Payment";
	        MACROS.CHARGE_TENDER_BTN        		= "Tender Cash";

	        MACROS.RECEIPT_EMAIL_TXT        		= "Email Address";
	        MACROS.RECEIPT_SKIP_BTN         		= "Skip";
	        MACROS.RECEIPT_SEND_BTN         		= "Send";
	        MACROS.RECEIPT_RESULT_TRUE_LBL  		= "You check email.";
	        MACROS.RECEIPT_RESULT_FALSE_LBL 		= "wrong email !";

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
	        MACROS.WRONGPASS_RESULT_LBL     		= "Nhập lại email";

	        MACROS.INFOR_LBL                		= "Thông tin cá nhân";
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

	        MACROS.CHARGE_LBL               		= "Thanh toán";
	        MACROS.CHARGE_CASH_LBL          		= "Thanh toán tiền mặt";
	        MACROS.CHARGE_TENDER_BTN        		= "Thanh toán";

	        MACROS.RECEIPT_EMAIL_TXT        		= "Địa chỉ email";
	        MACROS.RECEIPT_SKIP_BTN         		= "Bỏ qua";
	        MACROS.RECEIPT_SEND_BTN         		= "Gửi";
	        MACROS.RECEIPT_RESULT_TRUE_LBL  		= "Thư đã được gởi đến hộp thư của bạn.";
	        MACROS.RECEIPT_RESULT_FALSE_LBL 		= "Địa chỉ thư sai !";

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

}
