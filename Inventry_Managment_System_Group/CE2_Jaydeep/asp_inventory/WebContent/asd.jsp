<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>asd</title>

<meta charset="utf-8">
<title>jQuery UI Autocomplete - Default functionality</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/autofill/jquery-ui.css">
<script
	src="<%=request.getContextPath()%>/css/autofill/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath()%>/css/autofill/jquery-ui.js"></script>
<script>
	$(function() {
		var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC",
				"C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang",
				"Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp",
				"Perl", "PHP", "Python", "Ruby", "Scala", "Scheme" ];

		$("#tags").autocomplete({
			source : availableTags
		});
	});
</script>
</head>
<body>

	<div class="ui-widget">
		<label for="tags">Tags: </label> <input id="tags">
	</div>


</body>
</html>
