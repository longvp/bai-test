<%@ include file="/resources/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý khiếu nại</title>

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
                	<h5 class="text-center m-3">QUẢN LÝ KHIẾU NẠI</h5>
                	<div class="text-center">
	                	 <form method="post" action="/search-khieunai" class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
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
										Ghi nhận ý kiến khách hàng
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
			 								<th>ID ticket</th>
			 								<th>Phòng ban</th>
			 								<th>Người xử lý</th>
			 								<th>Đơn khiếu nại</th>
			                                <th>Nội dung xử lý</th>
			                                <th>Trạng thái</th>
			                                <th>Thời gian xử lý</th>
			                               	<th>Xử lý</th>
			                               <!--  <th>Sửa</th> -->
										</tr>
			       					 </thead>
			                    	 <tbody>
			                    	 	<c:forEach var="item" items="${listTicket}">                      	 		                 	 	 	
			                    	 		<tr>
			                    	 			<td>
			                    	 				<div class="checkbox">
														<label><input type="checkbox" value="${item.id }"></label>
													</div>
			                    	 			</td> 
			                    	 			<td class="ticket" data-sdt="${item.sdtKhachHang }" 
			                    	 					data-ykien="${item.yKienKhachHang }"
			                    	 					data-phongban="${item.phongBan.id }"
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
				                                <td>
			                            			 <button type="button" class="btn btn-primary btn-xuly" data-toggle="modal" data-target="#myModal">
													    <i class="far fa-edit"></i>
													 </button>
			                            		</td>                           
			                            		<%-- <td>
			                            			 <button type="button" class="btn btn-primary btn-sua" data-idbinhluan="${item.id }" data-toggle="modal" data-target="#myModal">
													    <i class="far fa-edit"></i>
													 </button>
			                            		</td> --%>
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
												<div id="div-phongban">
													<label for="" class="mt-4">Phòng ban tiếp nhận: </label> <br>
													<select name="phongBan" class="form-control" id="phongban">
														<c:forEach var="item" items="${listPhongBan }">
															<option value="${item.id }">${item.tenPhongBan }</option>
														</c:forEach>
													</select>
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
												
												<div id="div-noidungxuly">
													<label class="mt-4" for="">Nội dung xử lý:</label> <br>
													<textarea name="noiDungXuLy" id="noidung" class="form-control"  rows="5"></textarea>
												</div>
											</div>
											<p>
												<button type="button" id="btn-capnhat" class="btn btn-danger" >Xử lý</button>
												<button type="button" id="btn-them2" class="btn btn-danger btn-them" >Gửi</button>
									          	<button type="button" id="btn-huy" class="btn btn-danger" data-dismiss="modal">Hủy</button>
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
		
		/* GHI NHẬN KHIẾU NẠI */
    	$("#btn-them2").click(function(event){
    		event.preventDefault();
    		var formdata = $("#form").serializeArray();
			json = {};				
			 $.each(formdata, function(i, field){				
				json[field.name] = field.value;
			 });
			 console.log(json);
    		
    		$.ajax({
				url : "/api/ghinhankhieunai",
				type : "POST",
				data : {
					dataJson: JSON.stringify(json)
				},
				success : function(value) {
					location.reload();
				}
			})
    		
    	})		
    	
    	/* XOA */
    	$("#btn-xoa").click(function(){
				$("#table-sanpham tbody input:checked").each(function(){
					var id = $(this).val();
					var This = $(this);
					$.ajax({
						url : "/api/xoaticket",
						type : "POST",
						data : {
							id: id,
						},
						success : function(value) {
							This.closest("tr").remove();
							alert("Đã xóa.");
							location.reload();
						}
					})  
					
				})
			})
    	
    	/* XEM CHI TIET */
    	var id = 0;
    	$("body").on("click", ".xem-chi-tiet", function(event){
    		 event.preventDefault();
    		 id = $(this).attr("data-id");
    		 $("#btn-capnhat").hide();
    		 $("#btn-them2").hide();
    		 $("#btn-huy").hide();
    		 $("#time").show();
    		 $("#div-phongban").show();
	    	 $("#div-sdt").show();
	    	 $("#div-ykien").show();
	    	 $("#div-noidungxuly").hide();
    		 var phongBan = $(this).closest("tr").find(".ticket").attr("data-phongban");
    		 var sdtKhachHang = $(this).closest("tr").find(".ticket").attr("data-sdt");
    		 var yKienKhachHang = $(this).closest("tr").find(".ticket").attr("data-ykien");
    		 var thoiGianTiepNhan = $(this).closest("tr").find(".ticket").attr("data-thoigian");
    		 $("#phongban").val(phongBan);
    		 $("#sdt").val(sdtKhachHang);
    		 $("#ykien").val(yKienKhachHang);
    		 $("#thoigian").val(thoiGianTiepNhan);
    	})
    	
    	/* XU LY */   	
    	$("#btn-capnhat").click(function(event){
    		 event.preventDefault();	
    		
    		 var formdata = $("#form").serializeArray();
			 json = {};				
			 $.each(formdata, function(i, field){				
				json[field.name] = field.value;
			 });			 
			 json["id"] = id;
    		 console.log(json);
    		 $.ajax({
					url : "/api/xuly",
					type : "POST",
					data : {
						dataJson: JSON.stringify(json)
					},
					success : function(value) {
						location.reload();
					}
				})
    		 
    	})
    	
    	
    	
    	$("body").on("click", ".btn-xuly", function(event){
    		$("#time").hide();
    		$("#div-phongban").hide();
    		$("#div-sdt").hide();
    		$("#div-ykien").hide();
    		$("#div-noidungxuly").show();
    	})
    			
		$("#btn-them1").click(function(){
			 $("#time").hide();
			 $("#div-phongban").show();
	    	 $("#div-sdt").show();
	    	 $("#div-ykien").show();
	    	 $("#div-noidungxuly").hide();
			 $("#btn-them2").show();
			 $("#btn-capnhat").hide();
		 })
		 
		 $("body").on("click", ".btn-xuly",  function(){
			 $("#btn-them2").hide();
			 $("#btn-capnhat").show();
		 })
		
	})
	
	</script>
	
</body>
</html>