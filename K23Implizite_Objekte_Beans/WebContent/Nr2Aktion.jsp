<jsp:useBean id="attribute" class="Nr2.AttributeBean" scope="session" />
<jsp:setProperty name="attribute" property="name"
	value='<%=request.getParameter("name")%>' />
<jsp:setProperty name="attribute" property="wert"
	value='<%=request.getParameter("wert")%>' />
<jsp:setProperty name="attribute" property="scope"
	value='<%=request.getParameter("scope")%>' />

<%
	String datei = null;
	if (request.getParameter("eintragen") != null && request.getParameter("eintragen").length() != 0) {
		attribute.validiere();
		if (attribute.getFehler() != null) {
			datei = "Nr2.jsp";
		} else {
			if (request.getParameter("scope") != null) {
				if (request.getParameter("scope").equals("session")) {
					session.setAttribute(attribute.getName(), attribute.getWert());
				} else {
					System.out.println("choi");
					application.setAttribute(attribute.getName(), attribute.getWert());
				}
			}
			datei = "Erfolg.jsp";
			attribute.setName("");
			attribute.setWert("");
		}
	} else if (request.getParameter("ausgeben") != null && request.getParameter("ausgeben").length() != 0) {
		datei = "Attributliste.jsp";
	} else {
		attribute.valdel();
		if (attribute.getFehler() != null) {
			datei = "Nr2.jsp";
		} else {
			if (request.getParameter("scope") != null) {
				if (request.getParameter("scope").equals("session")) {
					session.removeAttribute(attribute.getName());
				} else {
					application.removeAttribute(attribute.getName());
				}
				datei = "Erfolg.jsp";
				attribute.setName("");
				attribute.setWert("");
			}

		}

	}
%>

<jsp:forward page="<%=datei%>" />