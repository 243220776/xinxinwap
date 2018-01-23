(function(){
	init();
})();

function init(){
	var params = location.search;
	if(params != ''){//暂时先这样，需要加强
		mui.ajax({
			url:"/qq/authQQ",
			data:{"code":params},
			dataType:'text',
			success:function(data){
				debugger
				console.log(data);
			}
		});
	}
	
	$("#myInfo").bind("tap",function(){
		window.location.href="/user/goUserInfo";
	});
	
	
}