<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>购物车</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
		function del(pid)
		{
			var isDel = confirm("您确定要删除吗？");
			if(isDel)
			{
				location.href="${pageContext.request.contextPath}/deletecart?pid="+pid;
			}
		}
		function delAll(cartList)
		{
			var isDel = confirm("您确定要清空购物车吗？");
			if(isDel)
			{
				location.href="${pageContext.request.contextPath}/deleteAll";
			}

			
		}
</script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
</head>

<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="container">
		<div class="row">

			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong style="font-size: 16px; margin: 5px 0;">订单详情</strong>
				<c:forEach items="${cartList}" var="product">

					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<tr class="active">
								<td width="60" width="40%"><input type="hidden" name="id"
									value="22"> <img src="${pageContext.request.contextPath }/${product.pimage }" width="70"
									height="60"></td>
								<td width="30%"><a target="_blank"> ${product.pname} </a></td>
								<td width="20%">￥${product.shop_price }</td>
								<td width="10%"><input type="text" name="quantity"
									value="${product.quantity}" maxlength="4" size="10"></td>
								<td width="15%"><span class="subtotal">￥${product.subtotal} </span></td>
								<td><a href="javascript:void(0)" onClick="del('${product.pid}')" class="delete">删除</a></td>
							</tr>
						</tbody>
					</table>

				</c:forEach>
			</div>
		</div>

		<div style="margin-right: 130px;">
			<div style="text-align: right;">
				<em style="color: #ff6600;"> 登录后确认是否享有优惠&nbsp;&nbsp; </em> &nbsp; 商品金额: <strong
					style="color: #ff6600;">￥ ${total} 元</strong>
			</div>
			<div
				style="text-align: right; margin-top: 10px; margin-bottom: 10px;">
				<a href="javascript:void(0)" onClick="delAll('${cartList}')" id="clear" class="clear">清空购物车</a> <a
					href="/Shop_Sys/orderInfo"> <input type="submit" width="100"
					value="提交订单" name="submit" border="0"
					style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
				</a>
			</div>
		</div>

	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>