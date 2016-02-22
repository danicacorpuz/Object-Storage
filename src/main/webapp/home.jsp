
<%@page import="java.util.List"%>
<%@page import="org.openstack4j.model.storage.object.SwiftObject"%>
<%@page import="connector.ObjectStorageConnector" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Object Storage</title>
    </head>
    <body>
		
        <form action="Upload" method="POST" enctype="multipart/form-data">
            Select file to upload:<input type="file" name="uploadfile" />
            <input type="submit" value="Upload File" />
        </form><br><br><br>
		
        <%
            ObjectStorageConnector connect = new ObjectStorageConnector();
			
			if(connect.listAllObjects("sample").isEmpty()) {
				connect.createContainer("sample");
			} else {
				List<? extends SwiftObject> objectlist = connect.listAllObjects("sample");
				out.println("<table>");
				for (int i = 0; i < objectlist.size(); i++) {
					out.println("<tr>");
					out.println("<td><label for=\"filename\">"+ objectlist.get(i).getName() +"</label></td>");
					out.println("<td><form action=\"Download\" method=\"GET\">");
					out.println("<input type=\"hidden\" readonly name=\"filename\" value=\"" + objectlist.get(i).getName() + "\"/>");
					out.println("<input type=\"submit\" value=\"Download\" name=\"" + i + "\">");
					out.println("</form></td>");
					out.println("</tr>");
				}
				out.println("</table>");
			}
        %>
    </body>
</html>
