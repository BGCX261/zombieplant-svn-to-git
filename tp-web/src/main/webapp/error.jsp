<%@ page isELIgnored ="false" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de errorr</title>
</head>
<body>
<h5>SE HA PRODUCIDO UN ERROR : ${requestScope.error}</h5>
<a href="index.jsp">ir a pagina de inicio</a>
</body>
</html>