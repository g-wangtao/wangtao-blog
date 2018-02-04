<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="pages/views/common/com_head.jsp"/>
    </head>
    <body>
    	<script type="text/javascript">
    		alert(blogMain.contentPath);
    	</script>
    	<h1>${contentPath}</h1>
        <h1>Hello World</h1>
 	</body>
</html>