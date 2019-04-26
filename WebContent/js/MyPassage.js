
	var Nowclassify ="classify-1";//现在的分类为 classify-1。 classify-1代表所有这个分类
	
	var Npage=1;//获得当前的页码
	var allpage;//获得所有页码
	function page(data){
		var arr = [ "div1", "div2", "div3", "div4","div5","div6","div7","div8","div9","div10"];
		var arrspan=["span1","span2","span3","span4","span5","span6"];
		var css=data;
		
		var Jsondata=eval(data);//讲字符串转换成json
		//将json对象转换成数组
		var jsonArr = [];
	     for(var i =0 ;i < Jsondata.length;i++){
	            jsonArr[i] = Jsondata[i];
	     }
		var line=jsonArr.length;//获得数组的长度
		
		var fal=0;
		
		 $.each(arr, function(){     
			    var i=this;
			    if(fal<line){
			    	
					$("#"+i+"-span"+arrspan[0]).html("作者  "+Jsondata[fal].username);
					$("#"+i+"-span"+arrspan[1]).html("日期 ："+Jsondata[fal].pdate);
					$("#"+i+"-span"+arrspan[2]).html("点赞"+Jsondata[fal].plike);
					$("#"+i+"-span"+arrspan[3]).html("浏览量"+Jsondata[fal].pvisit); 
					$("#"+i+"-span"+arrspan[4]).html("分类"+Jsondata[fal].pclassify); 
					$("#"+i+"-h2").html(data[fal].ptitle);
					$("#"+i+"-spa" +
							"n"+arrspan[5]).html("简介"+Jsondata[fal].pbrief); 
					$("#"+i).show();
					fal+=1;
			    }else{
			    	$("#"+i).hide();
			    }
				
		});
	};
	$(function (){
		$("#"+Nowclassify).css("background-color","#cc0000");//设置第一次加载页面的时候 Nowclassify分类的颜色
		$.post("/MyFirstWeb/Passage/passageLoad.do",function(data){
			var fal=0;//记录是第几个div
			
		 var arr = [ "div1", "div2", "div3", "div4","div5","div6","div7","div8","div9","div10"];
		 var arrspan=["span1","span2","span3","span4","span5","span6"];
		 $.each(arr, function(){     
		    var i=this; 
		   	$("<div />",{
				id : this,
				name : "newName",
				width : "100%",
				height : "150px",
				class:"mydiv",
				click : function() {
					console.log("div");
				}
			}).appendTo($("#manAndinfo"));
			
			$.each(arrspan,function(){
				var j=this;
				if(j=="span6"){
					
					$("<h2 />",{
						id : i+"-h2",
						class:"myh2",
					}).appendTo($("#"+i));
					$("<span />",{
						id : i+"-span"+j,
						name : "newName",
						class:"myspan",
						
						click : function() {
							console.log("div");
						}
					}).appendTo($("#"+i));
				}else{
					
					$("<span />",{
						id : i+"-span"+j,
						name : "newName",
						class:"myspan",
						
						click : function() {
							console.log("div");
						}
					}).appendTo($("#"+i));
				}
			});
			
			
		 });
		 $.each(arr,function(){
			 var i=this; 
			 //alert("#"+i+"-span"+arrspan[0]);
			 $("#"+i+"-span"+arrspan[0]).html("作者  "+data[fal].username);
			 $("#"+i+"-span"+arrspan[1]).html("日期 ："+data[fal].pdate);
			 $("#"+i+"-span"+arrspan[2]).html("点赞"+data[fal].plike);
			 $("#"+i+"-span"+arrspan[3]).html("浏览量"+data[fal].pvisit); 
			 $("#"+i+"-span"+arrspan[4]).html("分类"+data[fal].pclassify);
			 $("#"+i+"-h2").html(data[fal].ptitle);
			 $("#"+i+"-span"+arrspan[5]).html("简介"+data[fal].pbrief);
			 
			 fal+=1;
		 });
		 
		},"json");
		//设置分页的系统 获得当前的行数
		$.post("/MyFirstWeb/Passage/allPage.do",{NowCategory:Nowclassify},function(data){
			allpage=data.count;
			$("#pageSpan").text(allpage);
		},"json");
		//设置跳转按钮
		$("#Bgo").click(function(){
			Npage=$("#Tpage").val();
			if(Npage>0&&Npage<=allpage){
				
			$.post("/MyFirstWeb/Passage/goPage.do",{goPage:Npage,NowCategory:Nowclassify},function(data){
				page(data);
			},"text");
			}else{
				$("#Tpage").val("1");
			}
		});	
		//设置上一页按钮
		$("#BupPage").click(function(){
			Npage=$("#Tpage").val();
			var up=parseInt(Npage)-1;
			if(up>0){
				$.post("/MyFirstWeb/Passage/upPage.do",{upPage:up,NowCategory:Nowclassify},function(data){
					page(data);
				},"text");
				$("#Tpage").val(up);
			}
		});
		//设置下一页按钮
		$("#BdownPage").click(function(){
			Npage=$("#Tpage").val();
			var down=parseInt(Npage)+1;
			
			if(down<=allpage){
				$.post("/MyFirstWeb/Passage/downPage.do",{downPage:down,NowCategory:Nowclassify},function(data){
					page(data);
				},"text");
				$("#Tpage").val(down);				
			} 
		});	
	}); 
	function classify(id)
		{
		  //id.innerHTML="谢谢!";
			$("#"+Nowclassify).css("background-color","#bebebe");
			$("#"+id).css("background-color","#cc0000");
			Nowclassify=id;
			$.post("/MyFirstWeb/Passage/allPage.do",{NowCategory:Nowclassify},function(data){
				allpage=data.count;
				$("#pageSpan").text(allpage);
			},"json");
			$.post("/MyFirstWeb/Passage/classify.do",{NowCategory:Nowclassify},function(data){
				page(data);
			},"text");
			$("#Tpage").val(1)
		}
