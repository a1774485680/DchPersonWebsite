
$(function (){
	var fal= false;
	 $("#username").blur(function(){			
		 var name=$(this).val();//��õ�ǰԪ�ص�ֵ
		$.post("/MyFirstWeb/Register/UsernameVerify.do",{username:name},function(data){
			
			 var span=$("#s_username");
			 if(data.userExsit){	 
				 span.css("color","red");
				 span.html(data.msg);
				 fal=false;
			 }else{
				 if(name==""){
					 span.css("color","red");
					 span.html("�û�������Ϊ��");
				 }
				 else{
				 span.css("color","green");
				 span.html(data.msg);
				 fal=true;
				 }
			 } 
		 },"json");
	 });
	 $("#userpassword").blur(function(){
		 var text=$(this).val();
		 var regular=/^(?![0-9]*$)[a-zA-Z0-9]{6,16}$/;
		 var span=$("#s_password")
		 if(text==""){
			 span.css("color","red");
			 span.html("���벻��Ϊ��");
		 }else if(!regular.test(text)){
			 span.css("color","red");
			 span.html("���볤����6����16֮��,�����ֺ���ĸ��ɣ������Ǵ�����");
		 }else{
			 span.html("");
		 }
	 });
	 $("#reuserpassword").blur(function(){
		 var text=$(this).val();
		 var span=$("#s_repassword");
		 
		 var password=$("#userpassword").val();
		 if(text==""){
			 span.css("color","red");
			 span.html("ȷ�����벻��Ϊ��");
		 }else if(password!=text){
			 span.css("color","red");
			 span.html("�����������벻ͬ");
		 }else{
			 span.html("");
		 }
		
	 });
	 $("#sub").click(function(){
		 var s_named=$("#s_username");
		 var s_password=$("#s_password");
		 var s_repassword=$("#s_repassword");			
		 var span= $("#s_sub");
		 if(fal && s_password.text()=="" && s_repassword.text()=="" && $("#userpassword").val()!=""){
			 $("#f_sub").submit();
		 }else{ 
			
			 span.css("color","red");
			 span.html("��������");
		 } 
	 });
		 $("#Register").on('click', function () {
				$("body").append("<div id='mask1'></div>");
				$("#mask1").addClass("mask1").fadeIn("slow");
				$("#RegisterDiv").fadeIn("slow");
				
		});
		$(".close_btn1").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#999' }) }).on('click', function () {
				
				$("#RegisterDiv").fadeOut("fast");
				$("#mask1").css({ display: 'none' });
		});
	});