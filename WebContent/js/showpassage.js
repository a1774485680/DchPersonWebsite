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
	
	var E = window.wangEditor;
	var editor = new E('#manAndinfo');
	
	editor.create();
	editor.$textElem.attr('contenteditable', false);
	$.post("/MyFirstWeb/ShowPassage/passageLoad.do",{name:theRequest.authorName,title:theRequest.title},function(data){
	
		editor.txt.html(data);
		
		
	},"text");
});