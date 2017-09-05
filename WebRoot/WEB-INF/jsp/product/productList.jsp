<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">

		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	
	<%@ include file="../menu.jsp" %>
	
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<c:forEach items="${ sessionScope.categoryList}" var="c">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product/findByCid.action?cid=${c.cid }&page=1">${c.cname }</a>
							</dt>
								<c:forEach items="${c.categorySeconds }" var="cs">
									<dd>
										<a href="${ pageContext.request.contextPath }/product/findByCsid.action?csid=${cs.csid }&page=1">${cs.csname }</a>
									</dd>
								</c:forEach>
						</dl>
					</c:forEach>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="${pageContext.request.contextPath}/image" method="get">
					
				<div id="result" class="result table clearfix">
						<ul>
							<c:forEach items="${pagebean.list }" var="p">
								<li>
										<a href="${ pageContext.request.contextPath }/product/findByPid.action?pid=${p.pid}">
											<img src="${pageContext.request.contextPath}/${p.image}" width="170" height="170"  style="display: inline-block;"/>
											   
											<span style='color:green'>
											 ${p.pname }
											</span>
											 
											<span class="price">
												商城价： ￥${p.shop_price }
											</span>
											 
										</a>
								</li>
							</c:forEach>
						</ul>
				</div>
	<div class="pagination">
			<span>第 ${pagebean.page }/${pagebean.totalPage } 页</span>
		<c:if test="${cid != null }">
			<c:if test="${pagebean.page != 1 }">
				<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${cid }&page=1" class="firstPage">&nbsp;</a>
				<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${cid }&page=${ pageBean.page-1}" class="previousPage">&nbsp;</a>
			</c:if>
			
			<c:forEach varStatus="i" begin="1" end="${pagebean.totalPage }">
				<c:if test="${pagebean.page != i.count }">
					<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${cid }&page=${i.count}">${i.count }</a>
				</c:if>
				<c:if test="${pagebean.page == i.count }">
					<span class="currentPage">${i.count }</span>
				</c:if>
			</c:forEach>
			
			<c:if test="${pagebean.page != pagebean.totalPage}">	
				<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${cid }&page=${pagebean.page+1 }" class="nextPage">&nbsp;</a>
				<a href="${ pageContext.request.contextPath }/product/findByCid.action?cid=${cid }&page=${ pagebean.totalPage}" class="lastPage">&nbsp;</a>
			</c:if>
		</c:if>	
		
		
		
		<c:if test="${csid != null }">
			<c:if test="${pagebean.page != 1 }">
				<a href="${ pageContext.request.contextPath }/product/findByCsid.action?csid=${csid }&page=1" class="firstPage">&nbsp;</a>
				<a href="${ pageContext.request.contextPath }/product/findByCsid.action?csid=${csid }&page=${ pageBean.page-1}" class="previousPage">&nbsp;</a>
			</c:if>
			
			<c:forEach varStatus="i" begin="1" end="${pagebean.totalPage }">
				<c:if test="${pagebean.page != i.count }">
					<a href="${ pageContext.request.contextPath }/product/findByCsid.action?csid=${csid }&page=${i.count}">${i.count }</a>
				</c:if>
				<c:if test="${pagebean.page == i.count }">
					<span class="currentPage">${i.count }</span>
				</c:if>
			</c:forEach>
			
			<c:if test="${pagebean.page != pagebean.totalPage}">	
				<a href="${ pageContext.request.contextPath }/product/findByCsid.action?csid=${csid }&page=${pagebean.page+1 }" class="nextPage">&nbsp;</a>
				<a href="${ pageContext.request.contextPath }/product/findByCsid.action?csid=${csid }&page=${ pagebean.totalPage}" class="lastPage">&nbsp;</a>
			</c:if>
		</c:if>	
	</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52"/>
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body></html>