
	var Nowclassify ="classify-1";//���ڵķ���Ϊ classify-1�� classify-1���������������
	
	var Npage=1;//��õ�ǰ��ҳ��
	var allpage;//�������ҳ��
	falof1=0;
	falof2=0;//����ѯ��ʱ�򣬱���һ������ѯ��ϲ��ܲ�ѯ��һ����𣬼�С�����ݿ��ѹ��;��������õĻ���
	         //���û����ٶ�ε������һ��Ļ������ݿ�ѹ���ͻ�����������
	//����½
	$.post("/MyFirstWeb/LoginController/checklogin.do",function(data){
		if(data[0].userpassword!="δ��½"){
			var txtName = $("#txtName").val(data[0].username);
			var txtPwd = $("#txtPwd").val(data[0].userpassword);
			$("#Hlogin").hide();
			$("#Register").html("ע��");
		}
	},"json");
	//�÷���Ϊ���ݼ��ص�ͨ�÷��������ͷ�ҳ��������	
	
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
					//$("#"+i+"-span"+arrspan[2]).html("����"+Jsondata[fal].plike);
					$("#"+i+"-span"+arrspan[3]).html("�����"+Jsondata[fal].pvisit); 
					$("#"+i+"-span"+arrspan[4]).html("����"+Jsondata[fal].pclassify); 
					$("#"+i+"-a").text(Jsondata[fal].ptitle);
					$("#"+i+"-spa" +"n"+arrspan[5]).html("���"+Jsondata[fal].pbrief); 
					var authorName=Jsondata[fal].username;
					 var title=Jsondata[fal].ptitle;
					 var myherf='showPassage.html?authorName='+authorName+"&title="+title;
					 $("#"+i+"-a").attr('href',myherf);
					 $("#"+i+"-a").attr('target','_blank');
					$("#"+i).show();
					fal+=1;
			    }else{
			    	$("#"+i).hide();
			    }
				
		});
	};
	
	//��һ����ҳ���ʱ���ʼ����
	$(function (){
		//ע�ᷢ�����µ���¼�
		$("#IssuePassage").on('click', function () {
			
			window.open("Issue.html","_self");
		});
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
					
					$("<a />",{
						id : i+"-a",
						class:"mya",
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
			 //$("#"+i+"-span"+arrspan[2]).html("����"+data[fal].plike);
			 $("#"+i+"-span"+arrspan[3]).html("�����"+data[fal].pvisit); 
			 $("#"+i+"-span"+arrspan[4]).html("����"+data[fal].pclassify);
			 $("#"+i+"-a").text(data[fal].ptitle);
			 var authorName=data[fal].username;
			 var title=data[fal].ptitle;
			 var myherf='showPassage.html?authorName='+authorName+"&title="+title;
			 $("#"+i+"-a").attr('href',myherf);
			 $("#"+i+"-a").attr('target','_blank');
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
	//�����¼����������
	function classify(id)
		{		
					
				var arr=["classify-1","classify-2","classify-3","classify-4","classify-5","classify-6","classify-7","classify-8"]
				$.each(arr,function(){
					var i=this;
					$("#"+i).css("pointer-events","none" );
					
				});
				falof1=1;//�������ҳ���ı�־λ����λ1���������������
				falof2=1;//��������������Ϣ�ı�־λ����λ1���������������
		 		$("#"+Nowclassify).css("background-color","#bebebe");
		 		$("#"+id).css("background-color","#cc0000");
		 		Nowclassify=id;
		 		console.log(Nowclassify);
		 		$.post("/MyFirstWeb/Passage/allPage.do",{NowCategory:Nowclassify},function(data){
		 			allpage=data.count;
		 			$("#pageSpan").text(allpage);
		 			falof1=0;
		 			if(falof1==0&&falof2==0){			 			
			 			$.each(arr,function(){
			 				var i=this;
			 				$("#"+i).css("pointer-events","visiblepainted" );			 				
			 			});
			 		}
		 		},"json");
		 		$.post("/MyFirstWeb/Passage/classify.do",{NowCategory:Nowclassify},function(data){
		 			page(data);
		 			falof2=0;
		 			if(falof1==0&&falof2==0){			 			
			 			$.each(arr,function(){
			 				var i=this;
			 				$("#"+i).css("pointer-events","visiblepainted" );			 				
			 			});
			 		}
		 		},"text");
		 		$("#Tpage").val(1);		 		
		}
