<%@ include file="/resources/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
 
  <jsp:include page="/resources/common/lib.jsp"/>
  
</head>
<body>
	 <div class="frame text-center">
        <h3 class="m-5">Login</h3>
        <form action="" class="text-center">
            <div class="form-group mb-3">
                <input type="email" id="email" required class="form-control" placeholder="Email"> <br>
                <input type="password" id="password" required class="form-control" placeholder="Password">
            </div>
            <div class="link mb-4">
                <a href="/register">Register</a>
                <a href="/forgot-password">Forgot password</a>
            </div>
            <button type="button" id="btn-login" class="btn btn-primary">Login</button>
        </form>
    </div>
    <script type="text/javascript">
    
    $(document).ready(function(){
		
		$("#btn-login").click(function(){
			var email = $("#email").val();
			var password = $("#password").val();
				
			$.ajax({
				url : "/api/login",
				type : "POST",
				data : {
					email: email,
					password: password
				},
				success : function(value) {
					if(value === "Đăng nhập thành công"){
						window.location.href = "/quanly-khieunai";
					}else{
						alert(value);
					}
				}
			})
			
		})
		
	})
    
    </script>
</body>
</html>