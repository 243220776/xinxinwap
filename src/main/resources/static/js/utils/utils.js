/**
 * 
 */
var platform = navigator.platform;
var imgpath = "http://image.shike8888.com/";
// 时间格式化 new Date().Format("yy-MM-dd hh:mm:ss")
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}


//图片上传功能
function uploadImg(){
	var fileName="";
	$(".file_input").on('change', function(event) {
		event.preventDefault();
		var file = $(this)[0].files[0];
		fileName = file.name;
		if(file.size>10485760){
			mui.alert("上传图片请小于10M");
			return;
		}
		$("#uploadWait").addClass("mui-spinner");
		$('.mask_layer1').show();
		$('.mask_layer1').height(document.body.scrollHeight+30);
	    $(document.body).css({
	        "overflow-x":"hidden",
	        "overflow-y":"hidden"
	    });
		var reader = new FileReader();
		reader.readAsDataURL(file);
		var imgObj = $(this).siblings('img');
		var inputObj = $(this).siblings('input');
		reader.onload = function(e) {
			createCanvas(this.result,imgObj,inputObj);
		}
	});

	function createCanvas(src,imgObj,inputObj) {
		var before = new Date().getTime();
		var canvas = document.getElementById("uploadimp");
		var cxt = canvas.getContext('2d');
		var img = new Image();
		img.src = src;
		img.onload = function() {
			var m = img.height/img.width ;
			canvas.height =300*m;//该值影响缩放后图片的大小
			canvas.width= 300 ;
			//img放入画布中
			//设置起始坐标，结束坐标
			cxt.drawImage(img, 0, 0,300,300*m);
			//cxt.drawImage(img, 0, 0);
			imgObj.show().attr('src', canvas.toDataURL("image/jpeg", 1));  
			$.ajax({
				url: "/tryUse/uploadImg",
				type: "POST",
				dataType:'json',
				data: {
					"image": canvas.toDataURL("image/jpeg", 1).split(',')[1],
					"fileName":fileName
				},
				success: function(data) {
					if(data.code==1){
//						inputObj.val(imgpath+data.data);
						inputObj.val(data.data);
						$("#uploadWait").removeClass("mui-spinner");
						$('.mask_layer1').hide();
						$(document.body).css({
				            "overflow-x":"auto",
				            "overflow-y":"auto"
				        });
					}
				}
			});
		}
	}
}

/**
 * 
 * @param formObj  表单对象
 * @param inputObj  用于存放上传成功后图片地址的input
 * @param imgObj	用于回显的img对象
 * @param uploadUrl	上传的url
 * @returns
 */
/* function uploadImg(formObj,inputObj,imgObj,uploadUrl){
 	var filename = $("input[form="+$(formObj).attr('id')+"]").val();
 	if (filename == "") {
 		alert("请上传图片");
 	}  else{
 		$(formObj).ajaxSubmit({
 	        type: 'post',
 	        url: uploadUrl,
 	        dataType:'json',
 	        success: function(ret){
 	        	if (ret.code == 1) {
 					$(inputObj).val(ret.data);
 					$(imgObj).attr('src',imgpath+ret.data);
 				}
 	    	}
 		});
 	}
 }*/

function isHasImg(imgObj){
     if(imgObj.fileSize > 0 || (imgObj.width > 0 && imgObj.height > 0))
     {
       return true;
     } else {
       return false;
    }
}


function getC_S(color,size){
	var html="";
	if ( color &&  size) {
		html+= color+'、'+ size
	}else if ( color){
		html+= color
	}else if ( size){
		html+= size
	}else{
		html+='任意规格'
	}
	
	return html;
}

function getSplit(param){
	
	//匹配http 后面的所有非空字符
	var pattern = /http\S+/;
	
	//截取http 后面的所有非空字符---如果遇到逗号会截取逗号前面的，没有逗号不影响
	return param.match(pattern).toString().split('，')[0];
}


