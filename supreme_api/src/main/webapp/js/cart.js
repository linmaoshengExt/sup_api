$(document).ready(function() {
    websqlOpenDBSUP();
    websqlCreatTable(GoodCard);
    shopTable(GoodCard);

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
                //图片
                html += "<img src=\"" + result.rows.item(i).goodsPicture + "\" align=\"left\" width=\"60\" height=\"60\" class=\"order-list__product-image\" style=\"border: 1px solid #e5e5e5; border-radius: 8px; margin-right: 15px\">";
                html += "<td>";
                html += "<td class=\"order-list__product-description-cell\" style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; width: 100%\">";
                html += "<span class=\"order-list__item-title\"";
                //商品名称
                html += "style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 1.4\">" + result.rows.item(i).goodsName + "";
                html += "</span><br> <span";
                html += "class=\"order-list__item-variant\"";
                //尺码
                html += "style=\"color: #999; font-size: 14px\">" + result.rows.item(i).sizeName + "</span>";
                html += "</td>";
                html += "<td class=\"order-list__price-cell\"";
                html += "style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', '";
                html += "Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; white-space: nowrap\">";
                html += "<p class=\"order-list__item-price\" style=\"color: #555; font-size: 16px; font-weight: 600; line-height: 150%; margin: 0 0 0 15px; text-align: right\"";
                //价钱
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
            htmlTotal += " <td class=\"subtotal-line__value\" " + "style=\"font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif; padding: 20px 0 0; " + "text-align: right\" align=\"right\"><strong  id=\"totalmoney\"style=\"color: #555; font-size: 24px\">" + totalprice + " USD</strong></td>";
            $("#shop-cart-list").html(html) $("#total-money").html(htmlTotal) paypal1(totalprice)
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
        // sandbox | production
        style: {
            label: 'buynow',
            fundingicons: true,
            // optional
            branding: true,
            // optional
            size: 'large',
            // small | medium | large | responsive
            shape: 'rect',
            // pill | rect
            color: 'black' // gold | blue | silver | black
        },
        // PayPal Client IDs - replace with your own
        // Create a PayPal app: https://developer.paypal.com/developer/applications/create
        client: {
            sandbox: 'AT8kaYOysDqVlmntBFjT3OoLMKY2WaxmpDoWfcHSES5vh28nyhdaoV7e8fQh-k-1nKmKJuHJHIggmFLA',
            production: ''
        },

        // Show the buyer a 'Pay Now' button in the checkout flow
        commit: true,

        // payment() is called when the button is clicked
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

        // onAuthorize() is called when the buyer approves the payment
        onAuthorize: function(data, actions) {

            // Make a call to the REST api to execute the payment
            return actions.payment.execute().then(function() {
                window.alert('Payment Complete!');
            });
        }

    },
    '#paypal-button-container');

}