<%@ include file="/resources/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<jsp:include page="/resources/common/lib.jsp"/>

</head>
<body>
	
	<div class="frame text-center">
        <h3 class="m-2">Register Account</h3>
        <form action="" class="text-center">
            <div class="form-group mb-3">
                <input type="text" id="full-name" name="fullName" required="required" class="form-control" placeholder="Fullname"> <br>
                <input type="email" id="email" name="email" required="required" class="form-control" placeholder="Email"> <br>
                <input type="password" id="password" name="password" required="required" class="form-control" placeholder="Password"> <br>
                <input type="password" id="re-password" required="required" class="form-control" placeholder="Re-password">
            </div>
           
            <button type="button" id="btn-register" class="btn btn-primary">Submit</button>
        </form>
    </div>
    
    <script type="text/javascript">
    
    	$(document).ready(function(){
    		
    		$("#btn-register").click(function(){
    			var fullName = $("#full-name").val();
    			var email = $("#email").val();
    			var password = $("#password").val();
    			var rePassword = $("#re-password").val();
    			
    			$.ajax({
					url : "/api/register",
					type : "POST",
					data : {
						fullName: fullName,
						email: email,
						password: password,
						rePassword: rePassword
					},
					success : function(value) {
						alert(value);
						if(value === "Đăng ký thành công !"){
							window.location.href = "/login";
						}
					}
				})
    			
    		})
    		
    	})
    
    </script>
	
</body>
</html>