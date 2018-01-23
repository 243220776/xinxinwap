(function() {

	var __Utils = {

		format : function(data) {
			var unicode = BASE64.decoder(data);
			var str = '';
			for (var i = 0, len = unicode.length; i < len; ++i) {
				str += String.fromCharCode(unicode[i]);
			}
			return str;
		},

		replace : function(tempTxt, data) {
			return tempTxt.replace("@mainPicUrl@", data.mainPicUrl).replace(
					"@title@", data.title).replace("@discountPrice@",
					data.discountPrice).replace("@realPrice@", data.realPrice)
					.replace("@salesVolume@", data.salesVolume).replace("@id@",
							data.nid);
		},

		checkURL : function(URL) {
			var str = URL;
			// 判断URL地址的正则表达式为:http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?
			// 下面的代码中应用了转义字符"\"输出一个字符"/"
			var Expression = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
			var objExp = new RegExp(Expression);
			if (objExp.test(str) == true) {
				return true;
			} else {
				return false;
			}
		},

		getRootPath : function() {
			// 获取当前网址，如： http://localhost:8083/proj/meun.jsp
			var curWwwPath = window.document.location.href;
			// 获取主机地址之后的目录，如： proj/meun.jsp
			var pathName = window.document.location.pathname;
			var pos = curWwwPath.indexOf(pathName);
			// 获取主机地址，如： http://localhost:8083
			var localhostPath = curWwwPath.substring(0, pos);
			// 获取带"/"的项目名，如：/proj
			var projectName = pathName.substring(0, pathName.substr(1).indexOf(
					'/') + 1);
			// return (localhostPath + projectName);
			return localhostPath
		},

		request : function(strParame) {
			var args = new Object();
			var query = location.search.substring(1);

			var pairs = query.split("&"); // Break at ampersand
			for (var i = 0; i < pairs.length; i++) {
				var pos = pairs[i].indexOf('=');
				if (pos == -1)
					continue;
				var argname = pairs[i].substring(0, pos);
				var value = pairs[i].substring(pos + 1);
				value = decodeURIComponent(value);
				args[argname] = value;
			}
			return args[strParame];
		},

		isURL : function(str_url) {// 验证url
			var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
					+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
					+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL-
														// 199.194.52.184
					+ "|" // 允许IP和DOMAIN（域名）
					+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
					+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
					+ "[a-z]{2,6})" // first level domain- .com or .museum
					+ "(:[0-9]{1,4})?" // 端口- :80
					+ "((/?)|" // a slash isn't required if there is no file
								// name
					+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
			var re = new RegExp(strRegex);
			return re.test(str_url);
		}

	}

	window.Utils = __Utils;

	Date.prototype.Format = function(fmt)   
	{ // author: meizz
	  var o = {   
	    "M+" : this.getMonth()+1,                 // 月份
	    "d+" : this.getDate(),                    // 日
	    "h+" : this.getHours(),                   // 小时
	    "m+" : this.getMinutes(),                 // 分
	    "s+" : this.getSeconds(),                 // 秒
	    "q+" : Math.floor((this.getMonth()+3)/3), // 季度
	    "S"  : this.getMilliseconds()             // 毫秒
	  };   
	  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		}  
})();


function repalcePhoneByStr(string,start,length,str){

	var preStr = string.substring(0,start);
	var mStr = "";
	for (var i = 0; i < length; i++) {
		mStr += str;
	}
	var lStr = string.substring(start+length,string.length);
	return preStr+mStr+lStr;
}


function replaceNameByStr(string,start,length,str){

	var preStr = string.substring(0,start);
	var mStr = "";
	for (var i = 0; i < length; i++) {
		mStr += str;
	}
	var lStr = string.charAt(string.length-1);
	return preStr+mStr+lStr;
}


function taobaoImageUtil(arr,q){
	
	var sufix = q>0?"_350x350q"+q+'.jpg':"_350x350.jpg";
	var patt1=new RegExp(".jpg_");
	var patt2 = new  RegExp(".png_");
	var patt3 = new RegExp(".SS2_");
	
	if (patt1.test(arr)||patt2.test(arr)) {
		
		var a=arr.split(".");
		return arr.replace(a[a.length-2]+'.'+a[a.length-1],'jpg'+sufix);
	}
	if(patt3.test(arr)){
		
		var a=arr.split(".");
		return arr.replace(a[a.length-2]+'.'+a[a.length-1],'SS2'+sufix);
	}else{
		return arr.replace(".jpg", '.jpg'+sufix );
	}
}

function replaceAll(str){
	 var ret='';
	 var reg = new RegExp(".jpg" , 'g')
	 if (reg.test(str)) {
		 str = str.replace(reg , ".jpg_400x400.jpg");
	 }
	 reg = new RegExp(".png" , 'g');//g就是代表全部
	 if (reg.test(str)) {
		 str = str.replace(reg , ".png_400x400.jpg");
	 }
	 reg = new RegExp(".gif" , 'g');//g就是代表全部
	 if (reg.test(str)) {
		 str = str.replace(reg , ".gif_400x400.jpg");
	 }
	 
	 return str;
}

//使用进展100x100显示
function taobaoImageUtilTry(arr,q){
	
	var sufix = q>0?"_100x100q"+q+'.jpg':"_100x100.jpg";
	var patt1=new RegExp(".jpg_");
	var patt2 = new  RegExp(".png_");
	var patt3 = new RegExp(".SS2_");
	
	if (patt1.test(arr) || patt2.test(arr)) {
		
		var a=arr.split(".");
		return arr.replace(a[a.length-2]+'.'+a[a.length-1],'jpg'+sufix);
	}
	if(patt3.test(arr)){
		
		var a=arr.split(".");
		return arr.replace(a[a.length-2]+'.'+a[a.length-1],'SS2'+sufix);
	}else{
		var a=arr.split(".");
		if(a[a.length-1]=="png"){
			return arr.replace(".png", '.png'+sufix);
		}
		if(a[a.length-1]=="gif"){
			return arr.replace(".gif", '.gif'+sufix);
		}else{
			return arr.replace(".jpg", '.jpg'+sufix);
		}
	}
}
