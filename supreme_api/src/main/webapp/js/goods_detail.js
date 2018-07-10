
var goodsDetail = new Vue({
	el : '#goodsDetail',
	data : {
	    content:'',
		goodsSizeList:'',
		photoList:'',
		goodsName:'',
		goodsPrices:'',
		goodsPicture:'',
		goodsId:'',
		id:'',
		colourList:'',
		type:'',
		shopCarList:'',
		stu:''
	},


	mounted : function() {
		websqlOpenDBSUP(); 
		websqlCreatTable(GoodCard);
		websqlGetGoodCar(GoodCard);
		var Request = new Object();
		Request = GetRequest();
		var id = Request["id"];
		var stu = Request["stu"];
		this.getgoodsDetail( id);
	
	},
	
	methods : {
		getgoodsDetail : function(id,stu ) {
			var that = this;
			var retJson = "{\"id\":\"" + id + "\"}";
		
			$.ajax({
				type : "POST",
				async : false,
				contentType : 'application/json;charset=UTF-8',
				url : "/api/goods/details",
				data : retJson,
				success : function(data) {
					if (data.code == 1) {
						that.goodsName = data.goodsName;
						that.goodsPrices = data.goodsPrices;
						that.goodsPicture=data.goodsPicture;
						that.type = data.type;
						that.goodsSizeList = data.goodsSizeList;
						that.colourList = data.colourList;
						that.content = data.content;
						that.photoList = data.photoList;
						that.goodsId= data.goodsId;
						that.stu = stu;
						
					}
				},
			});
			

		},
		sizelist: function(sizeid){
			
			 alert(sizeid);
		 },
			
		
		shop: function(goodsPrices,goodsPicture,goodsName){
			var sizeId= $("#select").val(); 
			var sizeName=$("#select option:selected").text();
			var number=$(" input[ type='number' ] ").val()
			var UUID = uuid(8, 10);
			//先创建数据库 有就不创建
			console.log("新建数据库");
            websqlOpenDBSUP();                
            websqlCreatTable(GoodCard);   
			//插入数据 
            console.log("插入数据"); 
            //插入商品
            insertGoodsCar(GoodCard,UUID,goodsName,goodsPicture,goodsPrices,sizeId,sizeName,number,colourName);                
			//查询所有数据
            websqlGetGoodCar(GoodCard);
			//delshopCarTableTable()
            
		}
		
	}
})


function deleteCar(UUID){
	delGoodCard(GoodCard,UUID);
	websqlGetGoodCar(GoodCard);
}
var colourName;
$(function(){
	$(".pitch li").click(function(){
		$(this).attr("class","main-border");//选中的框增添class="on"属性
		colourName=$(this).children("img").attr("title");
		$(this).siblings("li").attr("class","");//未选中的框去除class="on"属性
	});
});



function GetRequest() {   
	   var url = location.search; //获取url中"?"符后的字串   
	   var theRequest = new Object();   
	   if (url.indexOf("?") != -1) {   
	      var str = url.substr(1);   
	      strs = str.split("&");   
	      for(var i = 0; i < strs.length; i ++) {   
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
	      }   
	   }   
	   return theRequest;   
	}

