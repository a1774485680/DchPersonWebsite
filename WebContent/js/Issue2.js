var Npage=1;//获得当前的页码
var allpage;//获得所有页码




//把琮后台获得的数据传到前台
//设置文本域

//passage 点击事件
//function PassageOnclick(data,fal){
//	console.log(data);
//	var title=data[fal];
//	console.log(fal);
//	$.post("/MyFirstWeb/Issue/updataPassage.do",{title:title},function(data){
//		//editor.txt.html(data);
//	},"text");
//	
//	$("#publish").removeAttr("onclick");
//	$("#publish").attr("onclick","updatePassage()");
//}

//第一步创建10个h3
$(function (){
	//设置注销事件方法
	 $("#Loginout").on('click', function () {
		//执行注销的方法
		 $.post("/MyFirstWeb/LoginController/loginout.do",function(data){
				console.log(data);
				alert("请先登陆");
				window.open("passage.html","_self");
			},"text");
	 })
	//加载文本编辑器
	 var E = window.wangEditor;
	 var editor = new E('#Ipassage');
	 editor.customConfig.uploadImgShowBase64 = true;
	 editor.customConfig.showLinkImg = false;
	 editor.create();
	 function page(data){
			var Harr = [ "H1", "H2", "H3", "H4","H5","H6","H7","H8","H9","H10"];
			
			
			var line=data.length;//获得数组的长度
			
			var fal=0;
			
			 $.each(Harr, function(){     
				    var i=this;
				    if(fal<line){
				    	var a="";
				    	if(data[fal].publish){
				    		console.log(data[fal].publish);
				    		a="(已审核)"
				    	}else{
				    		a="(待审核)"
				    		console.log(data[fal].publish);
				    	}
						$("#"+i).html(data[fal].ptitle+a);
						
						$("#"+i).show();
						//设置点击事件
						 //$("#"+i).click({data:data},PassageOnclick);
						//$("#"+i).bind("click",{data:"12"},PassageOnclick);
						$("#"+i).click(function (){
							//var data = $(this).parents("data");
							//var fal = $(this).parents("fal");
							//console.log(data[fal]);
							//var title=data[fal];
							//console.log(title);
							var title=$(this).html();
							title = title.substr(0, title.length - 5); 
							console.log(title);
							$.post("/MyFirstWeb/Issue/updataPassage.do",{title:title},function(data){
								
								editor.txt.html(data);
								console.log("点击了该方法");
								$("#Apublish").html("修改文章");
							},"text");
							$.post("/MyFirstWeb/Issue/getinformPassage.do",{title:title},function(data){
								$("#Iinputtitle").val(data[0].ptitle);
								console.log(data[0].ptitle);
								$("#Ibrief").val(data[0].pbrief);
								$("#Iselect").attr("selected", data[0].pclassify);
								$("#Iinputtitle").attr("disabled","disabled");
							},"json");
							
						});
						
						fal+=1;
				    }else{
				    	
				    	$("#"+i).hide();
				    }
					
			});
		};
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
	$.post("/MyFirstWeb/Issue/AllpageByIssue.do",function(data){//如果没有登陆的事件处理方法
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
					page(data)
					
				},"json");
				$("#Tpage").val(down);				
			} 
		});	
	}
	$("#Apublish").on('click', function () {
		var Atext=$("#Apublish").html();
		console.log("Atext="+Atext);
		if(Atext=="发布"){
			
			var Ptitle=$.trim($("#Iinputtitle").val());
			var pbrief=$.trim($("#Ibrief").val());
			var Pclassify=$.trim($("#Iselect option:selected").val());
			var textHtml=editor.txt.html();
			var text1=editor.txt.html();
			//发送请求
			if(textHtml!=""&&Ptitle!=""&&Pclassify!=""&&pbrief!=""){
				//出现幕布
				$("body").append("<div id='mask1'></div>");
				$("#mask1").addClass("mask1").fadeIn("slow");
				$("#Div_issue_hint").fadeIn("slow");
				
				$.post("/MyFirstWeb/Issue/publish.do",{Ptitle:Ptitle,pbrief:pbrief,Pclassify:Pclassify,textHtml:textHtml},function(data){	
					if(data=="true"){
						alert("发布成功");
						$("#Div_issue_hint").fadeOut("fast");
						$("#mask1").css({ display: 'none' });
						window.open("Issue.html","_self");
					}else{
						alert("发布失败 该文章标题已经使用");
						$("#Div_issue_hint").fadeOut("fast");
						$("#mask1").css({ display: 'none' });
						window.open("Issue.html","_self");
					}
				},"text");
			}else{
				$("#Div_issue_hint").fadeOut("fast");
				$("#mask1").css({ display: 'none' });
				alert("有选项没有输入内容");
			}
		}else{
			var Ptitle=$.trim($("#Iinputtitle").val());
			var pbrief=$.trim($("#Ibrief").val());
			var Pclassify=$.trim($("#Iselect option:selected").val());
			var textHtml=editor.txt.html();
			var text1=editor.txt.html();
			//发送请求
			if(textHtml!=""&&Ptitle!=""&&Pclassify!=""&&pbrief!=""){
				//出现幕布
				$("body").append("<div id='mask1'></div>");
				$("#mask1").addClass("mask1").fadeIn("slow");
				$("#Div_issue_hint").fadeIn("slow");
				
				$.post("/MyFirstWeb/Issue/updatepublish.do",{Ptitle:Ptitle,pbrief:pbrief,Pclassify:Pclassify,textHtml:textHtml},function(data){
					console.log("csccsc");
					if(data=="true"){
						alert("修改文章成功");
						$("#Div_issue_hint").fadeOut("fast");
						$("#mask1").css({ display: 'none' });
						window.open("Issue.html","_self");
					}else{
						alert("发布失败 该文章标题已经使用");
						$("#Div_issue_hint").fadeOut("fast");
						$("#mask1").css({ display: 'none' });
						window.open("Issue.html","_self");
					}
				},"text");
			}else{
				$("#Div_issue_hint").fadeOut("fast");
				$("#mask1").css({ display: 'none' });
				alert("有选项没有输入内容");
			}
		}
		
		
		
	});
});