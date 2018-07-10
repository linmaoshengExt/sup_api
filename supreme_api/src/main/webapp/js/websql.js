/**
 * Created by Datura on 2017/3/8 0008.
 */
/**
 *数据库操作辅助类,定义对象、数据操作方法都在这里定义
 */
var dbname='suprememark';/*数据库名*/
var version = '1.0'; /*数据库版本*/
var dbdesc = 'goods'; /*数据库描述*/
var dbsize = 4*1024*1024; /*数据库大小*/
var dataBase = null; /*暂存数据库对象*/
/*数据库中表的名*/
var GoodCard = "GoodCard";

/**
 * 打开数据库
 * dataBase:打开成功   null:打开失败
 */
function websqlOpenDBSUP(){
    /*数据库有就打开 没有就创建*/
    dataBase = window.openDatabase(dbname, version, dbdesc, dbsize,function() {});
    if (dataBase) {
       /* alert("数据库创建/打开成功!");*/
    } else{
        /*alert("数据库创建/打开失败！");*/
    }
    return dataBase;
}
/**
 * 新建数据库里面的表单
 * tableName:表单名
 */
function websqlCreatTable(tableName){
    var creatTableSQL = 'CREATE TABLE IF  NOT EXISTS '+ tableName + ' (UUID text, goodsName text,goodsPicture text,goodsPrices text,sizeId text,sizeName text,number text,colourName text)';
    dataBase.transaction(function (ctx,result) {
        ctx.executeSql(creatTableSQL,[],function(ctx,result){
           /* alert("表创建成功 " + tableName);*/
        },function(tx, error){
//            alert('创建表失败:' + tableName + error.message);
        });
    });
}
/**
 * 往表单里面插入数据
 * tableName:表单名
 * goodsName:商品名称
 * goodsPicture:图片URL
 * goodsPrices:价钱
 * sizeId:尺码id
 * sizeName:尺码名字
 * number:尺码
 */
function insertGoodsCar(tableName,UUID,goodsName,goodsPicture,goodsPrices,sizeId,sizeName,number,colourName){
	if (sizeId<0 && colourName==null) {
		//myAlert('Please choose size or colour');
		//alert('Please choose size or colour' );
	} else {
		 var insterTableSQL = 'INSERT INTO ' + tableName + ' (UUID,goodsName,goodsPicture,goodsPrices,sizeId,sizeName,number,colourName) VALUES (?,?,?,?,?,?,?,?)';
		    dataBase.transaction(function (ctx) {
		        ctx.executeSql(insterTableSQL,[UUID,goodsName,goodsPicture,goodsPrices,sizeId,sizeName,number,colourName],function (ctx,result){
//		                console.log("插入" + tableName  + goodsName + "成功");
		        	// alert('Add' +goodsName+'a shopping cart success' );
		        	
		            },
		            function (tx, error) {
//		                alert('插入失败: ' + error.message);
		            });
		    });
	}
   
}
/**
 * 获取数据库一个表单里面的所有数据
 * tableName:表单名
 * 返回数据集合
 */
function websqlGetGoodCar(tableName){
    var selectALLSQL = 'SELECT * FROM ' + tableName;
    dataBase.transaction(function (ctx) {
        ctx.executeSql(selectALLSQL,[],function (ctx,result){
//                alert('查询成功: ' + tableName + result.rows.length);
                var len = result.rows.length;
                var html = ''; 
                var totalprice =0;
                console.log("-------- 数据总条数------- " +len);                                 
                for(var i = 0;i < len;i++) {
                	html +="<div class=\"ps-cart-item\"><a  class=\"ps-cart-item__close\"  onclick='deleteCar(\""+result.rows.item(i).UUID+"\")' ></a>";
                	html +="<div class=\"ps-cart-item__thumbnail\"><img src=\""+result.rows.item(i).goodsPicture+"\" ></div>";
                	html +="<div class=\"ps-cart-item__content\"><a class=\"ps-cart-item__title\" >"+result.rows.item(i).goodsName+"";
                	html +="<p><span>size:<i>"+ result.rows.item(i).sizeName+" </i><span style=\"float:right\">color:<i>"+result.rows.item(i).colourName+"</i></span><span style=\"float:right\">Total:<i>"+result.rows.item(i).goodsPrices+"</i></span></p>";
                	html +="</div>";
                	html +="</div>";
                	totalprice = totalprice*1+(result.rows.item(i).goodsPrices)*1;
                }
                
                var htmlTotal ='';
          
                htmlTotal +=" <p>Number of items:<span>"+len+"</span></p>";
                htmlTotal +=" <p>Item Total:<span>£"+totalprice+"</span></p>";
              
                var htmlNumber="<i >"+len+"</i>";
                $("#shopcar").html(html)
                $("#carTotal").html(htmlTotal)
                $("#htmlNumber").html(htmlNumber)
                /*if (len==0) {
                	 $("#cart-hide").hide();
				}*/
            },
            function (tx, error) {
//            	console('查询失败: ' + error.message);
            });
    });
}
/**
 * 获取数据库一个表单里面的部分数据
 * tableName:表单名
 * name:姓名
 */
/*function websqlGetAData(tableName,name){
    var selectSQL = 'SELECT * FROM ' + tableName + ' WHERE goodsName = ?'
    dataBase.transaction(function (ctx) {
        ctx.executeSql(selectSQL,[name],function (ctx,result){
                alert('查询成功: ' + tableName + result.rows.length);
                var len = result.rows.length;
                for(var i = 0;i < len;i++) {
                    console.log("goodsName = "  + result.rows.item(i).goodsName);
                    console.log("AGE = "  + result.rows.item(i).AGE);
                    console.log("HEIGHT = "  + result.rows.item(i).HEIGHT);
                    console.log("WEIGTH = "  + result.rows.item(i).WEIGTH);
                }
            },
            function (tx, error) {
                alert('查询失败: ' + error.message);
            });
    });
}*/
/**
 * 删除表单里的全部数据
 * tableName:表单名
 */
function websqlDeleteAllDataFromTable(tableName){
    var deleteTableSQL = 'DELETE FROM ' + tableName;
    localStorage.removeItem(tableName);
    dataBase.transaction(function (ctx,result) {
        ctx.executeSql(deleteTableSQL,[],function(ctx,result){
//            alert("删除表成功 " + tableName);
        },function(tx, error){
//            alert('删除表失败:' + tableName + error.message);
        });
    });
}
/**
 * 根据name删除数据
 * tableName:表单名
 * name:数据的姓名
 */
function delGoodCard(tableName,UUID){
    var deleteDataSQL = 'DELETE FROM ' + tableName + ' WHERE UUID = ?';
    localStorage.removeItem(tableName);
    dataBase.transaction(function (ctx,result) {
        ctx.executeSql(deleteDataSQL,[UUID],function(ctx,result){
//            alert("删除成功 " + tableName + UUID);
        },function(tx, error){
//            alert('删除失败:' + tableName  + UUID + error.message);
        });
    });
}
/**
 * 根据name修改数据
 * tableName:表单名
 * name:姓名
 * age:年龄
 */
function websqlUpdateAData(tableName,name,age){
    var updateDataSQL = 'UPDATE ' + tableName + ' SET AGE = ? WHERE NAME = ?';
    dataBase.transaction(function (ctx,result) {
        ctx.executeSql(updateDataSQL,[age,name],function(ctx,result){
//            alert("更新成功 " + tableName + name);
        },function(tx, error){
//            alert('更新失败:' + tableName  + name + error.message);
        });
    });
}

function delshopCarTableTable(){
	dataBase.transaction(function (tx) {
		tx.executeSql('drop table GoodCard');
		});
}

/**
 * 生成UUID
 */
function uuid(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length;
 
    if (len) {
      // Compact form
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
      // rfc4122, version 4 form
      var r;
 
      // rfc4122 requires these characters
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
      uuid[14] = '4';
 
      // Fill in random data.  At i==19 set the high bits of clock sequence as
      // per rfc4122, sec. 4.1.5
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random()*16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
 
    return uuid.join('');
}


