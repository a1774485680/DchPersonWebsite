
	var Nowclassify ="classify-1";//���ڵķ���Ϊ classify-1�� classify-1���������������
	
	var Npage=1;//��õ�ǰ��ҳ��
	var allpage;//�������ҳ��
	function page(data){
		var arr = [ "div1", "div2", "div3", "div4","div5","div6","div7","div8","div9","div10"];
		var arrspan=["span1","span2","span3","span4","span5","span6"];
		var css=data;
		
		var Jsondata=eval(data);//���ַ���ת����json
		//��json����ת��������
		var jsonArr = [];
	     for(var i =0 ;i < Jsondata.length;i++){
	            jsonArr[i] = Jsondata[i];
	     }
		var line=jsonArr.length;//�������ĳ���
		
		var fal=0;
		
		 $.each(arr, function(){     
			    var i=this;
			    if(fal<line){
			    	
					$("#"+i+"-span"+arrspan[0]).html("����  "+Jsondata[fal].username);
					$("#"+i+"-span"+arrspan[1]).html("���� ��"+Jsondata[fal].pdate);
					$("#"+i+"-span"+arrspan[2]).html("����"+Jsondata[fal].plike);
					$("#"+i+"-span"+arrspan[3]).html("�����"+Jsondata[fal].pvisit); 
					$("#"+i+"-span"+arrspan[4]).html("����"+Jsondata[fal].pclassify); 
					$("#"+i+"-h2").html(data[fal].ptitle);
					$("#"+i+"-spa" +
							"n"+arrspan[5]).html("���"+Jsondata[fal].pbrief); 
					$("#"+i).show();
					fal+=1;
			    }else{
			    	$("#"+i).hide();
			    }
				
		});
	};
	$(function (){
		$("#"+Nowclassify).css("background-color","#cc0000");//���õ�һ�μ���ҳ���ʱ�� Nowclassify�������ɫ
		$.post("/MyFirstWeb/Passage/passageLoad.do",function(data){
			var fal=0;//��¼�ǵڼ���div
			
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
			 $("#"+i+"-span"+arrspan[0]).html("����  "+data[fal].username);
			 $("#"+i+"-span"+arrspan[1]).html("���� ��"+data[fal].pdate);
			 $("#"+i+"-span"+arrspan[2]).html("����"+data[fal].plike);
			 $("#"+i+"-span"+arrspan[3]).html("�����"+data[fal].pvisit); 
			 $("#"+i+"-span"+arrspan[4]).html("����"+data[fal].pclassify);
			 $("#"+i+"-h2").html(data[fal].ptitle);
			 $("#"+i+"-span"+arrspan[5]).html("���"+data[fal].pbrief);
			 
			 fal+=1;
		 });
		 
		},"json");
		//���÷�ҳ��ϵͳ ��õ�ǰ������
		$.post("/MyFirstWeb/Passage/allPage.do",{NowCategory:Nowclassify},function(data){
			allpage=data.count;
			$("#pageSpan").text(allpage);
		},"json");
		//������ת��ť
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
		//������һҳ��ť
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
		//������һҳ��ť
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
		  //id.innerHTML="лл!";
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
