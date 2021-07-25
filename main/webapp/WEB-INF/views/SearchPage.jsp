<%@ include file="/resources/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>
<jsp:include page="/resources/common/lib.jsp"/>
</head>
<body>
	
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
                	<h5 class="text-center m-5">SEARCH PAGE</h5>
                	<div class="container p-0">	
                		<div class="row">
                			<div class="col-md-12">  
                				
                				<!-- SEARCH PHONG BAN  -->
                				<c:if test="${listPhongBanTheoTen != null }">        				
	                				<table class="table table-bordered text-center" id="table-sanpham" width="100%" cellspacing="0">
				                    	<thead>
				 							<tr>					
				 								<th>ID phòng ban</th>
				                             	<th>Tên phòng ban</th>       
				                                <th>Ngày tạo</th>
											</tr>
				       					 </thead>
				                    	 <tbody>
				                    	 	<c:forEach var="item" items="${listPhongBanTheoTen}">                      	 		                 	 	 	
				                    	 		<tr>
				                    	 			<td>${item.id }</td>
					                                <td class="ten-phongban">${item.tenPhongBan }</td>
					                                <td>${item.ngayTao }</td>                                 	
				                            	</tr>
				                    	 	</c:forEach>
				                    	 </tbody>
	                   			 	</table> 
                   			 	</c:if>  
                   			 	
                   			 	<!-- SEARCH TICKET -->
                   			 	<c:if test="${listTicketTheoTenPhongBan !=null }">
                   			 		<table class="table table-bordered text-center" id="table-sanpham" width="100%" cellspacing="0">
			                    	<thead>
			 							<tr>
			 								
			 								<th>ID ticket</th>
			 								<th>Phòng ban</th>
			 								<th>Người xử lý</th>
			 								<th>Đơn khiếu nại</th>
			                                <th>Nội dung xử lý</th>
			                                <th>Trạng thái</th>
			                                <th>Thời gian xử lý</th>
			                               	
										</tr>
			       					 </thead>
			                    	 <tbody>
			                    	 	<c:forEach var="item" items="${listTicketTheoTenPhongBan}">                      	 		                 	 	 	
			                    	 		<tr>
			                    	 			
			                    	 			<td class="ticket" data-sdt="${item.sdtKhachHang }" 
			                    	 					data-ykien="${item.yKienKhachHang }"
			                    	 					data-phongban="${item.phongBan.tenPhongBan }"
			                    	 					data-thoigian="${item.thoiGianTiepNhan }">${item.id }</td>
			                    	 			<td>${item.phongBan.tenPhongBan }</td>
			                    	 			<td>${item.nhanVien.fullName }</td>
			                    	 			<td>
				                                	 <button type="button" data-id="${item.id }" class="btn btn-primary xem-chi-tiet" data-toggle="modal" data-target="#myModal">
													    Chi tiết
													 </button>
				                                </td>  
				                               			                                
				                                <td>${item.noiDungXuLy }</td>
				                                <c:if test="${item.trangThaiXuLy == false }">
				                                	<td style="color: red; font-weight: bold;">Chưa xử lý</td>
				                                </c:if>
				                                <c:if test="${item.trangThaiXuLy == true }">
				                                	<td style="color: green; font-weight: bold;">Đã xử lý</td>
				                                </c:if>
				                                <td>${item.thoiGianXuLy }</td> 
				                                                          
			                            	
			                            	</tr>
			                    	 	</c:forEach>
			                    	 </tbody>
                   			 	</table>      
                   			 	
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
												<div id="div-phongban">
													<label for="" class="mt-4">Phòng ban tiếp nhận: </label> <br>
													<input type="text" name="" id="phongban" class="form-control" required="required">
												</div>										
												
												<div id="div-sdt">								
													<label for="" class="mt-4">SĐT khách hàng: </label> <br>
													<input type="text" name="sdtKhachHang" id="sdt" class="form-control" required="required">
												</div>	
												
												<div id="div-ykien">
													<label class="mt-4" for="">Ý kiến khách hàng:</label> <br>
													<textarea name="yKienKhachHang" id="ykien" class="form-control"  rows="5"></textarea>
												</div>
												
												<div id="time">
													<label class="mt-4" for="">Thời gian tiếp nhận:</label> <br>
													<input type="text" name="thoiGianTiepNhan" id="thoigian" class="form-control" required="required">
												</div>
												
												
											</div>
											
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
                			</div>
                		</div>
                	</div>
			         
                	
                	</c:if>              
                </main>
            </div>
        </div>
        
        <script type="text/javascript">
        	$(document).ready(function(){
        		
        		$("body").on("click", ".xem-chi-tiet", function(event){
        			event.preventDefault();
        			var phongBanTiepNhan = $(this).closest("tr").find(".ticket").attr("data-phongban");
        			var sdtKhachHang = $(this).closest("tr").find(".ticket").attr("data-sdt");
        			var yKienKhachHang = $(this).closest("tr").find(".ticket").attr("data-ykien");
        			var thoiGianTiepNhan = $(this).closest("tr").find(".ticket").attr("data-thoigian");
        			
        			$("#phongban").val(phongBanTiepNhan);
        			$("#sdt").val(sdtKhachHang);
        			$("#ykien").val(yKienKhachHang);
        			$("#thoigian").val(thoiGianTiepNhan);
        		})
        		
        	})
        </script>
	
</body>
</html>