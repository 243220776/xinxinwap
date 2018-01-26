(function(){
	init();
})();

function init(){
	var params = location.search;
	if(params != ''){//暂时先这样，需要加强
		mui.ajax({
			url:"/qq/login",
			data:{"code":params},
			dataType:'json',
			success:function(data){
				window.location.href="/";
			}
		});
	}
	
	$("#myInfo").bind("tap",function(){
		window.location.href="/user/goUserInfo";
	});
	
	
}