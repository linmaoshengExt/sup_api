$(document).ready(function() {
    websqlOpenDBSUP();
    websqlCreatTable(GoodCard);
    shopTable(GoodCard);
/*    $("#paypal-button-container").click(function(){
     
       if ( check()){
    	   alert(chenggong)
       }else {
		   alert(fail)
	}
           
    });*/

});

function shopTable(tableName) {
    var selectALLSQL = 'SELECT * FROM ' + tableName;
    dataBase.transaction(function(ctx) {
        ctx.executeSql(selectALLSQL, [],
        function(ctx, result) {
            
            var len = result.rows.length;
            var html = '';
            var totalprice = 0;
           
            for (var i = 0; i < len; i++) {
                html += "<tr class=\"order-list__item \" style=\"border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: 1px; width: 100%\">";
                html += "<td class=\"order-list__item__cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 0 0 15px\">";
                html += "<table style=\"border-collapse: collapse; border-spacing: 0\">";
                html += "<tbody>";
                html += "<tr>";
                html += "<td style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif\">";
             
                html += "<img src=\"" + result.rows.item(i).goodsPicture + "\" align=\"left\" width=\"60\" height=\"60\" class=\"order-list__product-image\" style=\"border: 1px solid #e5e5e5; border-radius: 8px; margin-right: 15px\">";
                html += "<td>";
                html += "<td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\">";
                html += "<span class=\"order-list__item-title\"";
             
                html += "style=\"color: #E5E5E5	; font-size: 16px; font-weight: 600; line-height: 1.4\">" + result.rows.item(i).goodsName + "";
                html += "</span><br> <span";
                html += "class=\"order-list__item-variant\"";
           
                html += "style=\"color: #E5E5E5	; font-size: 14px\">" + result.rows.item(i).sizeName + "</span>";
                html += "</td>";
                html += "<td class=\"order-list__price-cell\"";
                html += "style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', '";
                html += "Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; white-space: nowrap\">";
                html += "<p class=\"order-list__item-price\" style=\"color: #E5E5E5; font-size: 16px; font-weight: 600; line-height: 150%; margin: 0 0 0 15px; text-align: right\"";
              
                html += "align=\"right\">" + result.rows.item(i).goodsPrices + "</p>";
                html += "</td>";
                html += "</tr>";
                html += "</tbody>";
                html += "</table>";
                html += "</td>";
                html += "</tr>";
                totalprice = totalprice * 1 + (result.rows.item(i).goodsPrices) * 1;
            }

            var htmlTotal = '';
            htmlTotal += " <td class=\"subtotal-line__value\" " + "style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 20px 0 0; " + "text-align: right\" align=\"right\"><strong  id=\"totalmoney\"style=\"color: #E5E5E5; font-size: 24px\">" + totalprice + " USD</strong></td>";
            $("#shop-cart-list").html(html) 
            $("#total-money").html(htmlTotal) 
            paypal1(totalprice)
        },
        function(tx, error) {
            
        });
    });
}
var totalcart;
function paypal1(totalMoney) {
    totalcart = totalMoney;
  
    paypal.Button.render({
    	 
        env: 'sandbox',
       
        style: {
            label: 'buynow',
            fundingicons: true,
            
            branding: true,
           
            size: 'large',
            
            shape: 'rect',
          
            color: 'silver' 
        },
       
        client: {
            sandbox: 'AT8kaYOysDqVlmntBFjT3OoLMKY2WaxmpDoWfcHSES5vh28nyhdaoV7e8fQh-k-1nKmKJuHJHIggmFLA',
            production: ''
        },

       
        commit: true,

       
        payment: function(data, actions) {
            return actions.payment.create({
                payment: {
                    transactions: [{
                        amount: {
                            total: totalcart,
                            currency: 'USD'
                        }
                    }]
                }
            });
        },

        onAuthorize: function(data, actions) {

            return actions.payment.execute().then(function() {
                window.alert('Payment Complete!');
            });
        }

    },'#paypal-button-container');

}
function check() {
	var result = true;
	if ($("#Fname").val() == "") {
		alert("请输入first name");
		$("#Fname").focus(function(){
		    $("input").css("background-color","#FF0000");
		  });
		return false;
	}
	if (isNaN($("#appId").val())) { 
		alert("应用ID请输入数字"); 
		$("#appId").focus();
		return false;
	}
	if ($("#appId").val() == "") {
		alert("请输入应用ID");
		$("#appId").focus();
		return false;
	}
	if ($("#appName").val() == "") {
		alert("请输入应用名称");
		$("#appName").focus();
		return false;
	}
	if ($("#version").val() == "") {
		alert("请输入版本");
		$("#version").focus();
		return false;
	}
	if ($("#versionInt").val() == "") {
		alert("请填写版本INT（整数）");
		$("#versionInt").focus();
		return false;
	}
	if ($("#updatePeriod").val() == "") {
		alert("请填写缓存更新周期");
		$("#updatePeriod").focus();
		return false;
	}
	if ($("#recommendGroupId").val() == "0") {
		alert("请填选择推荐分组");
		$("#recommendGroupId").focus();
		return false;
	}
	
	return result;
}