<%@ include file="/resources/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý phòng ban</title>

<jsp:include page="/resources/common/lib.jsp"/>

</head>
<body class="sb-nav-fixed">
	
	<jsp:include page="/resources/common/header.jsp"/>
	
	 <div id="layoutSidenav">
        
           <jsp:include page="/resources/common/menu.jsp"/>
           
            <div id="layoutSidenav_content">
                <main class="p-3">
                	<c:if test="${nhanvienlogin == null }">
                		<div class="text-center mt-5">
                			<a class="btn btn-primary" href="/login">Đăng nhập để tiếp tục</a>
                		</div>
                	</c:if> 
                	<c:if test="${nhanvienlogin != null }"> 
                	<h5 class="text-center m-3">QUẢN LÝ PHÒNG BAN</h5>
                	<div class="text-center">
	                	 <form method="post" action="/search-phongban" class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			                <div class="input-group">
			                    <input name="tenPhongBan" required="required" class="form-control" type="text" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
			                    <div class="input-group-append">
			                        <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i></button>
			                    </div>
			                </div>
			            </form>
		            </div>
                	<div class="container p-0">
                		<div class="row mb-5 mt-5">
                			<div class="col-md-12">
                				<div style="float: right;">
                					<button id="btn-xoa" class="btn btn-primary">Xóa</button>
                					<button type="button" id="btn-them1" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
										Thêm phòng ban
									</button>
                				</div>
                			</div>
                		</div>
                		<div class="row">
                			<div class="col-md-12">           				
                				<table class="table table-bordered text-center" id="table-sanpham" width="100%" cellspacing="0">
			                    	<thead>
			 							<tr>
			 								<th></th>
			 								<th>ID phòng ban</th>
			                             	<th>Tên phòng ban</th>       
			                                <th>Ngày tạo</th>
			                                <th>Sửa</th>
										</tr>
			       					 </thead>
			                    	 <tbody>
			                    	 	<c:forEach var="item" items="${listPhongBan}">                      	 		                 	 	 	
			                    	 		<tr>
			                    	 			<td>
			                    	 				<div class="checkbox">
														<label><input type="checkbox" value="${item.id }"></label>
													</div>
			                    	 			</td>
			                    	 			<td>${item.id }</td>
				                                <td class="ten-phongban">${item.tenPhongBan }</td>
				                                <td>${item.ngayTao }</td>                              
			                            		<td>
			                            			 <button type="button" class="btn btn-primary btn-sua" data-id="${item.id }" data-toggle="modal" data-target="#myModal">
													    <i class="far fa-edit"></i>
													 </button>
			                            		</td>
			                            	</tr>
			                    	 	</c:forEach>
			                    	 </tbody>
                   			 	</table>               			 
                			</div>
                		</div>
                	</div>
			                	
			        <div class="container">						
						  <div class="row">
						  <div class="col-md-12">
						  <div class="modal" id="myModal">
							    <div class="modal-dialog">
							      <div class="modal-content">
								        <!-- Modal body -->
								        <div class="modal-body">
								        <button type="button" class="close" style="float:right;" data-dismiss="modal">&times;</button>
								        <form id="form" >				
										<div class="row">
											<div class="col-md-12 mb-3">
												<label for="" class="mt-4">Tên phòng ban: </label> <br>
												<input type="text" name="tenPhongBan" id="ten-phongban" class="form-control" required="required">							
											</div>
											<p>
												<button type="button" id="btn-capnhat" class="btn btn-danger" >Cập nhật</button>
												<button type="button" id="btn-them2" class="btn btn-danger btn-them" >Thêm</button>
									          	<button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
											</p>	
										</div>
															
									</form>	
								</div>
								
							    </div>
						    </div>
						 </div>	
						 </div>	
					</div>  
					</div>
                	
                	</c:if>              
                </main>
            </div>
        </div>
        
        <script type="text/javascript">
        
        $(document).ready(function(){
        	
        	/* THEM PHONG BAN */
        	$("#btn-them2").click(function(event){
        		event.preventDefault();
        		var tenPhongBan = $("#ten-phongban").val();
        		
        		$.ajax({
    				url : "/api/them-phongban",
    				type : "POST",
    				data : {
    					tenPhongBan: tenPhongBan,
    				},
    				success : function(value) {
    					alert(value);
    					if(value === "Thêm thành công"){
    						location.reload();
    					}
    				}
    			})
        		
        	})
        	
        	/* CHON TAT CA SAN PHAM */
			$("#checkall").change(function(){
				if(this.checked){
					$("#table-sanpham input").each(function(){
						$(this).attr("checked", true);
					})
				}else{
					$("#table-sanpham input").each(function(){
						$(this).attr("checked", false);
					})
				}
			})
			
			/* XOA PHONG BAN */
			$("#btn-xoa").click(function(){
				$("#table-sanpham tbody input:checked").each(function(){
					var id = $(this).val();
					var This = $(this);
					$.ajax({
						url : "/api/xoa-phongban",
						type : "POST",
						data : {
							id: id,
						},
						success : function(value) {
							This.closest("tr").remove();
							alert("Đã xóa phòng ban.");
							location.reload();
						}
					})  
					
				})
			})
        	
        	/* LAY THONG TIN */
        	var id = 0;
        	$("body").on("click", ".btn-sua", function(event){
        		 event.preventDefault();
        		 id = $(this).attr("data-id");
        		 var tenPhongBan = $(this).closest("tr").find(".ten-phongban").text();
        		
        		 $("#ten-phongban").val(tenPhongBan);
				 
        		 /* $.ajax({
						url : "/api/laythongtinphongban",
						type : "POST",
						data : {
							id: id,
						},
						success : function(value) {
							console.log(value);
							$("#ten-phongban").val(value.tenPhongBan); 
						}
					})  */

        	})
        	
        	/* CAP NHAT PHONG BAN */
        	$("#btn-capnhat").click(function(event){
				 event.preventDefault();	
				 var formdata = $("#form").serializeArray();
				 json = {};				
				 $.each(formdata, function(i, field){				
					json[field.name] = field.value;
				 });
				 
				 json["id"] = id;
				 
				$.ajax({
						url : "/api/capnhatphongban",
						type : "POST",
						data : {
							dataJson: JSON.stringify(json)
						},
						success : function(value) {
							location.reload();
						}
					})
					
			 }) 
			
        	
        	
        	$("#btn-them1").click(function(){
				 $("#btn-them2").show();
				 $("#btn-capnhat").hide();
			 })
			 
			 $("body").on("click", ".btn-sua",  function(){
				 $("#btn-them2").hide();
				 $("#btn-capnhat").show();
			 })
        	
        })
        
        </script>

</body>
</html>