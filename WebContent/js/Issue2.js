var Npage=1;//获得当前的页码
var allpage;//获得所有页码
//把琮后台获得的数据传到前台
//设置文本域


function page(data){
	var Harr = [ "H1", "H2", "H3", "H4","H5","H6","H7","H8","H9","H10"];
	
	
	var line=data.length;//获得数组的长度
	
	var fal=0;
	
	 $.each(Harr, function(){     
		    var i=this;
		    if(fal<line){
		    	
				$("#"+i).html(data[fal]);
				fal+=1;
				$("#"+i).show();
		    }else{
		    	
		    	$("#"+i).hide();
		    }
			
	});
};
//第一步创建10个h3
$(function (){
	 var E = window.wangEditor;
	 var editor = new E('#Ipassage');
	 editor.customConfig.uploadImgShowBase64 = true;
	 editor.customConfig.showLinkImg = false;
	 editor.create();
	//定义h3数组
	var Harr = [ "H1", "H2", "H3", "H4","H5","H6","H7","H8","H9","H10"];
	$.each(Harr,function(){
		var i=this;
		
		$("<h3 />",{
			id : this,
			width : "100%",
			height : "50px",
			class:"myH3"
		}).appendTo($("#pastPtitle"));
	});
	
	//向后端发送ajax请求获得数据库中的数据
	//返回的数据为有已发布文章的页码
	$.post("/MyFirstWeb/Issue/AllpageByIssue.do",function(data){
		if(data.count==-1){
			alert("请先登陆");
			window.open("passage.html","_self");
		}else{
			
			allpage=data.count;
			$("#pageSpan").text(allpage);
			
			load();
		}
	},"json");
	//登陆成功执行该方法
	function load(){
		$.post("/MyFirstWeb/Issue/passageLoad.do",function(data){
			
			
			if(data==""){			
				allpage=0;
				$("<h3 />",{
					id : "hint",//提示
					width : "100%",
					height : "50px",
					class:"myH3"
				}).appendTo($("#pastIssue"));
				$("#hint").html("您未发布过文章");
				$("#pastPage").fadeOut("fast");
				$("#pastPtitle").fadeOut("fast");
			}else{
				
				page(data);
			}
		
		},"json");
		//设置跳转按钮
		$("#Bgo").click(function(){
			Npage=$("#Tpage").val();
			if(Npage>0&&Npage<=allpage){
				
			$.post("/MyFirstWeb/Issue/goPage.do",{goPage:Npage},function(data){
				page(data);
				
			},"json");
			}else{
				$("#Tpage").val("1");
			}
		});	
		//设置上一页按钮
		$("#BupPage").click(function(){
			Npage=$("#Tpage").val();
			var up=parseInt(Npage)-1;
			if(up>0){
				$.post("/MyFirstWeb/Issue/upPage.do",{upPage:up},function(data){
					page(data);
				},"json");
				$("#Tpage").val(up);
			}
		});
		//设置下一页按钮
		$("#BdownPage").click(function(){
			Npage=$("#Tpage").val();
			var down=parseInt(Npage)+1;
			
			if(down<=allpage){
				$.post("/MyFirstWeb/Issue/downPage.do",{downPage:down},function(data){
					page(data);
					
				},"json");
				$("#Tpage").val(down);				
			} 
		});	
	}
	$("#Apublish").on('click', function () {
		var Ptitle=$.trim($("#Iinputtitle").val());
		var pbrief=$.trim($("#Ibrief").val());
		var Pclassify=$.trim($("#Iselect option:selected").val());
		var textHtml=editor.txt.html();
		var text1=editor.txt.html();
		if(textHtml!=""&&Ptitle!=""&&Pclassify!=""&&pbrief!=""){
			
			$.post("/MyFirstWeb/Issue/publish.do",{Ptitle:Ptitle,pbrief:pbrief,Pclassify:Pclassify,textHtml:textHtml},function(data){	
				if(data=="1"){
					alert("发布成功");
				}
			},"text");
		}else{
			alert("有选项没有输入内容");
		}
		
		
	});
});