<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<style>
.top_bar {
	background-color: #343131;
}
body {
	margin: 0px;
	background-color: #2ecc71;
}
.top_center {
	margin: 0 auto;
	width: 950px;
}
.logo {
	padding: 0.5em;
	color: #fff;
	font-size: 1.2em;
}
.left_col {
	width: 670px;
	padding: 15px;
	float: left;
	background-color: #eee;
}
.right_col {
	width: 250px;
	float: left;
	background-color: #474747;
	text-align: center;
	padding: 20px 0;
}
.clearer {
	clear: both;
}
.body_content {
	margin: 30px auto 0 auto;
	width: 950px;
}
.posted_on {
	color: #aaa;
	font-style: italic;
}
h2 {
	font-size: 1.3em;
	margin: 0.5em 0;
}
.author_name {
	font-size: 1.1em;
	color: #fff;
}
#author_image {
	margin: 0 auto;
}
.author_info {
	color: #aaa;
	font-size: 1em;
}
</style>

<div class="body_content">
	<div class="left_col">
		<h2>${article.title}</h2>
		<div class="posted_on">
			posted on <fmt:formatDate pattern="d MMM yyyy" value="${article.date}" />
		</div>

			<p>${article.intro}</p>
			${article.body}
	</div>
	<div class="right_col">
		<img id="author_image" src="https://s3.amazonaws.com/jculb_blog_assets/justin_author.png"/>
		<div class="author_name">
			Justin Culbertson
		</div>
		<div class="author_info">
			Web Developer
		</div>
	</div>
	<div class="clearer"></div>
</div>

<jsp:include page="footer.jsp"/>
