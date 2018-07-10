$("#goodsList").removeAttr("style");

var goodsList = new Vue({
	el : '#goodsList',
	data : {
		goodsList : '',
		categoryName:'all',
		categoryId:''
	},


	mounted : function() {
		websqlOpenDBSUP(); 
		websqlCreatTable(GoodCard);
		websqlGetGoodCar(GoodCard);
		this.getGoodsList( 1, 100);
	},
	
	methods : {
		getGoodsList : function( pageNo, pageSize) {
			var that = this;
			
			var retJson = "{\"categoryName\":\"" + this.categoryName + "\",";
			retJson += "\"categoryId\":\"" + this.categoryId + "\",";
			retJson += "\"pageNo\":\"" + pageNo + "\",";
			retJson += "\"pageSize\":\"" + pageSize + "\"}";
			$.ajax({
				type : "POST",
				async : false,
				contentType : 'application/json;charset=UTF-8',
				url : "/api/category/list",
				data : retJson,
				success : function(data) {
					if (data.code == 1) {
						that.goodsList = data.goodsVoList;
						
						setTimeout(function(){
							if($('.ps-masonry').length > 0){
							$('.ps-masonry').masonry('destroy').masonry();
							}
							},1000)

					}
				},
			});
		},
		
		 setType: function(name ,id){
			 this.categoryName = name;
			 this.categoryId = id;
			 this.getGoodsList(1,1000)

			}
			

	}
})
