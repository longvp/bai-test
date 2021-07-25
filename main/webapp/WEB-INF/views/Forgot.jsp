<%@ include file="/resources/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot password</title>

<jsp:include page="/resources/common/lib.jsp"/>

</head>
<body>
	<div class="frame frame-forgot text-center">
        <h3 class="m-5">Forgot password</h3>
        <form action="" class="text-center">
            <div class="form-group mb-3">
                <input type="email" id="email" required class="form-control" placeholder="Email"> <br>
            </div>
            <a href="/login">Login</a> <br>
            <button type="button" id="btn" class="mt-3 btn btn-primary">Submit</button>
        </form>
    </div>
    
    <script type="text/javascript">
    	
    	$(document).ready(function(){
    		
    		$("#btn").click(function(){
    			
    			var email = $("#email").val();
    			
    			$.ajax({
					url : "/api/laypass",
					type : "POST",
					data : {
						email: email,
					},
					success : function(value) {
						alert(value);
					}
				})  
    			
    		})
    		
    	})
    
    </script>
    
</body>
</html>