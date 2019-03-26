<%--
  Created by IntelliJ IDEA.
  User: soro
  Date: 3/26/19
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CXF FileUpload. :)</title>
    <form method="post" enctype="multipart/form-data" action="http://localhost:8080/cxf-upload/upload ">
        <p>
            Select a file : <input type="file" name="uploadedFile" size="50" />
        </p>
        <input type="submit" value="Upload It" />
    </form>
</head>
<body>

</body>
</html>
