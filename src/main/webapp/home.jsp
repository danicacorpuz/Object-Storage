
<%@page import="java.util.List"%>
<%@page import="org.openstack4j.model.storage.object.SwiftAccount"%>
<%@page import="org.openstack4j.model.storage.object.SwiftObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Object Storage</title>
    </head>
    <body>
		
		//Upload
        <form action="Upload" method="POST" enctype="multipart/form-data">
            Select file to upload:<input type="file" name="fileName" /><br>
            <input type="submit" value="Upload" />
        </form>
		<br><br>

        <%
		/*
			//Download
            ObjectStorageClient connect = new ObjectStorageClient();
            List<? extends SwiftObject> objectlist = connect.listAllObjects("sample");
            for(int i=0; i<objectlist.size(); i++) {
                out.println("<form action=\"Download\" method=\"POST\">");
                out.println("<input type=\"hidden\" readonly name=\"filename\" value=\"" + objectlist.get(i).getName() + "\"/>");
                out.println("<input type=\"submit\" value=\"Download File\" name=\""+ i +"\">");
                out.println("</form>");
            }
		*/
        %>
    </body>
</html>
