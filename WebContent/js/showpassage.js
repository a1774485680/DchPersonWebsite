//该方法获得前端来的参数
var theRequest = new Object();
function GetRequest() {
            var url = decodeURI(location.search); //获取url中"?"符后的字串
            
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                
                strs = str.split("&");
                for(var i = 0; i < strs.length; i ++) {
                    theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                }
            }
 };
$(function (){
	GetRequest();
	$("#IssuePassage").on('click', function () {
		
		window.open("Issue.html","_self");
	});
	//检查登陆
	$.post("/MyFirstWeb/LoginController/checklogin.do",function(data){
		if(data[0].userpassword!="未登陆"){
			var txtName = $("#txtName").val(data[0].username);
			var txtPwd = $("#txtPwd").val(data[0].userpassword);
			$("#Hlogin").hide();
			$("#Register").html("注销");
		}
	},"json");
	var E = window.wangEditor;
	var editor = new E('#manAndinfo');
	
	editor.create();
	editor.$textElem.attr('contenteditable', false);
	$.post("/MyFirstWeb/ShowPassage/passageLoad.do",{name:theRequest.authorName,title:theRequest.title},function(data){
	
		editor.txt.html(data);
		
		
	},"text");
	$.post("/MyFirstWeb/ShowPassage/passageinform.do",{name:theRequest.authorName,title:theRequest.title},function(data){
		
		//editor.txt.html(data);
		$("#h2_title").html(data[0].ptitle);
		$("#span_data").html("时间 "+data[0].pdate+"&nbsp;&nbsp;&nbsp");
		$("#span_visit").html("浏览量 "+data[0].pvisit+"&nbsp;&nbsp;&nbsp");
		$("#span_author").html("作者 "+data[0].username+"&nbsp;&nbsp;&nbsp");
		//$("#h2-title").html(data[0].ptitle);
		
	},"json");
});