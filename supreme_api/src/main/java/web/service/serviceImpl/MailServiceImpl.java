package web.service.serviceImpl;


import java.util.Random;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import web.constants.MailInfo;
import web.service.MailService;
import web.utils.MailSendUtil;

@Service
public class MailServiceImpl implements MailService {

	/**
	 * 发送邮件
	 */
	@Override
	public String sendMail(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		String email = jsonObject.getString("email");
		Long subtotal = jsonObject.getLong("totalSum") / 100;
		Long tax = subtotal * 12 / 100;
		Long totalPrice = subtotal + tax;
		JSONObject object = null;
		String content = "";
		JSONArray jsonArray = jsonObject.getJSONArray("goodslist");
		for (int i = 0; i < jsonArray.size(); i++) {
			object = jsonArray.getJSONObject(i);
			String url = "\"" + object.getString("imgUrl") + "\"";
			String goodsName = object.getString("goodsName");
			String html = "";
			html += "<tr class=\"order-list__item order-list__item--first\" style=\"border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: 1px; width: 100%\">";
			html += "<td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0 0 15px\">";
			html += "<table style=\"border-collapse: collapse; border-spacing: 0\">";
			html += "<tbody><tr><td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
			html += "<img src=" + url
					+ "align=\"left\" width=\"60\" height=\"60\" class=\"order-list__product-image\" style=\"border: 1px solid #e5e5e5; border-radius: 8px; margin-right: 15px\">";
			html += "</td>";
			html += "<td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\">";
			html += "<span class=\"order-list__item-title\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 1.4\">"
					+ goodsName + "&nbsp;×&nbsp;1</span><br>";
			html += "<span class=\"order-list__item-variant\" style=\"color: #999; font-size: 14px\">size:   "
					+ object.getString("sizeName") + "</span>";
			html += "</td>";
			html += "<td class=\"order-list__price-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; white-space: nowrap\">";
			html += "<p class=\"order-list__item-price\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 150%; margin: 0 0 0 15px; text-align: right\" align=\"right\">$"
					+ object.getLong("goodsPrice") / 100 + "</p>";
			html += "</td>";
			html += "</tr></tbody>";
			html += "</table>";
			html += "</td>";
			html += "</tr>";
			content = content + html;
		}
		String emailhtml = maileContent(email, content, subtotal, tax, totalPrice);
		sendMail1(email, emailhtml);
		return "success";
	}

	// 发送邮件
	public void sendMail1(String mail, String content) {
		String title = "Dear user, please check your order";
		MailInfo info = new MailInfo();
		info.setToAddress(mail);// 发送对象的邮箱
		info.setSubject(title);
		info.setContent(content);
		try {

			MailSendUtil.sendHtmlMail(info);
		} catch (Exception e) {
			System.out.print("'" + title + "'的邮件发送失败！");
			e.printStackTrace();
		}
	}

	public String maileContent(String mail, String content, long subtotal, long taxPrice, long totalPrice) {
		String html = "";
		// 第一段开头 begin
		html += "<body bgcolor=\"#ffffff\">";
		html += "<table class=\"header row\" style=\"border-collapse: collapse; border-spacing: 0; margin: 40px 0 20px; width: 100%\">";
		html += "<tbody><tr>";
		html += "<td class=\"header__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
		html += "<center>";
		html += "<table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">";
		html += "<tbody><tr>";
		html += "<td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
		html += "<table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">";
		html += "<tbody><tr>";
		html += "<td class=\"shop-name__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
		html += "<img src=\"http://p73qv84ep.bkt.gdipper.com/whitelogo769702092889111677.png\" align=\"left\" width=\"350\" height=\"80\" >";

		html += "</td>";
		html += "<td class=\"order-number__cell\" style=\"color: #999; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; font-size: 14px; text-align: right; text-transform: uppercase\" align=\"right\">";
		html += "<span class=\"order-number__text\" style=\"font-size: 16px\">";
		html += "Order";
		html += "</span></td></tr> </tbody></table>";
		html += "</td>";
		html += " </tr>";
		html += "</tbody></table>";
		html += " </center>";
		html += "</td>";
		html += "</tr>";
		html += "</tbody></table>";
		// 第一段开头结束 end

		// 第二段开始,订单列表 begin
		html += "<table class=\"row section\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; width: 100%\">";
		html += "<tbody><tr>";
		html += "<td class=\"section__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 40px 0\">";
		html += "<center>";
		html += "<table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">";
		html += " <tbody><tr>";
		html += "<td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
		html += "<h3 style=\"font-size: 20px; font-weight: normal; margin: 0 0 25px\">Order summary</h3>";
		html += "</td>";
		html += "</tr>";
		html += "</tbody></table>";
		html += "<table class=\"container\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0 auto; text-align: left; width: 560px\">";
		html += "<tbody><tr>";
		html += "<td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
		html += "<table class=\"row\" style=\"border-collapse: collapse; border-spacing: 0; width: 100%\">";
		html += "<tbody>";
		html += content;
		html += "</tbody>";
		html += "</table>";
		html += "<table class=\"row subtotal-lines\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 1px; margin-top: 15px; width: 100%\">";
		html += "<tbody><tr>";
		html += "<td class=\"subtotal-spacer\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 40%\"></td>";
		html += "<td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
		html += "<table class=\"row subtotal-table\" style=\"border-collapse: collapse; border-spacing: 0; margin-top: 20px; width: 100%\">";

		html += "<tbody><tr class=\"subtotal-line\">";
		html += "<td class=\"subtotal-line__title\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 5px 0\">";
		html += "<p style=\"color: #777; font-size: 16px; line-height: 1.2em; margin: 0\">";
		html += "<span style=\"font-size: 16px\">Subtotal</span>";
		html += "</p>";
		html += "</td>";
		html += "<td class=\"subtotal-line__value\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 5px 0; text-align: right\" align=\"right\">";
		html += "<strong style=\"color: #555; font-size: 16px\">$" + subtotal + "</strong>";
		html += "</td> ";
		html += "</tr> ";
		html += "<tr class=\"subtotal-line\">";
		html += "<td class=\"subtotal-line__title\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 5px 0\">";
		html += "<p style=\"color: #777; font-size: 16px; line-height: 1.2em; margin: 0\"> ";
		html += "<span style=\"font-size: 16px\">tax</span>";
		html += "</p>";
		html += "</td> ";
		html += "<td class=\"subtotal-line__value\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 5px 0; text-align: right\" align=\"right\">";
		html += "<strong style=\"color: #555; font-size: 16px\">" + taxPrice + "</strong>";
		html += "</td>";
		html += "</tr>";
		html += "</tbody></table> ";
		html += "<table class=\"row subtotal-table subtotal-table--total\" style=\"border-collapse: collapse; border-spacing: 0; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: 2px; margin-top: 20px; width: 100%\">";
		html += "<tbody><tr class=\"subtotal-line\"> ";
		html += "<td class=\"subtotal-line__title\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 20px 0 0\"> ";
		html += "<p style=\"color: #777; font-size: 16px; line-height: 1.2em; margin: 0\"> ";
		html += "<span style=\"font-size: 16px\">Total</span>";
		html += "</p> ";
		html += "</td>";
		html += "<td class=\"subtotal-line__value\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 20px 0 0; text-align: right\" align=\"right\"> ";
		html += "<strong style=\"color: #555; font-size: 24px\">$" + totalPrice + "USD</strong> ";
		html += "</td>";
		html += "</tr>";
		html += "</tbody></table>";
		html += "</td>";
		html += "</tr>";
		html += "</tbody></table>";
		html += "</center>";
		html += "</td>";
		html += "</tr>";
		html += "</tbody></table>";
		return html;
	}

	public static void main(String[] args) {
		
		  
		    String sources = "0123456789abcdefghijklmnopqrstuvwxyz"; // 加上一些字母，就可以生成pc站的验证码了  
		    Random rand = new Random();  
		    StringBuffer flag = new StringBuffer();  
		    for (int j = 0; j < 6; j++)   
		    {  
		        flag.append(sources.charAt(rand.nextInt(36)) + "");  
		    }  
		    System.out.println(flag.toString());  
	 
	}
}
