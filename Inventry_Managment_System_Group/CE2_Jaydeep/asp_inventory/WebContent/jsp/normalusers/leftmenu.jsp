<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
/*
 * Developer: Alireza Eskandarpour Shoferi
 * Designer: Nevide (codecanyon.net/user/Nevide)
 *
 * Distributed under the terms of the MIT license
 * http://opensource.org/licenses/MIT
 */
ul {
  margin: 0;
  padding: 0;
  list-style-type: none;
}
a {
  color: #646464;
  text-decoration: none;
  cursor: pointer;
}
body {
/*   background-color: #64D7E2; */
  font-family: "Source Sans Pro", sans-serif;
}
.wrapper {
  position: absolute;
  width: 280px;
  background-color: #fff;
  box-shadow: 0 4px 10px #dac6c6;
  font-size: 0.875em;
}
.items {
  padding: 18px 0;
}
.items > li > a {
  display: block;
  margin: 0 auto;
  width: 90%;
  text-indent: 18px;
  line-height: 39px;
}
.items > li > a::after {
  position: absolute;
  right: 30px;
  margin-top: 2px;
  font-family: "FontAwesome";
}
.items > li > a::after {
  right: 30px;
  content: "\f061";
}
.itemHover {
  color: #fff;
  font-weight: 600;
  -webkit-transition: background-color 0.4s ease-in-out;
  transition: background-color 0.4s ease-in-out;
}
.items > li > a:hover {
  background-color: #c36464;
  color: #fff;
  font-weight: 600;
  -webkit-transition: background-color 0.4s ease-in-out;
  transition: background-color 0.4s ease-in-out;
}
.items > li > a.expanded {
  background-color: #c36464;
  color: #fff;
  font-weight: 600;
  -webkit-transition: background-color 0.4s ease-in-out;
  transition: background-color 0.4s ease-in-out;
}
.items > li > a.expanded::after {
  content: "\f063";
}
.sub-items > li:first-child > a {
  margin-top: 17px;
  height: 34px;
}
.sub-items > li:last-child > a {
  margin-bottom: 17px;
  height: 34px;
}
.sub-items a {
  position: relative;
  display: block;
  margin: 0 auto;
  width: 212px;
  text-indent: 24px;
  line-height: 39px;
}
.sub-items a {
  border-left: 2px solid #c36464;
}
.sub-items .current {
  position: relative;
  color: #c36464;
  border-color: white;
}
.sub-items > li:hover > a {
  color: #c36464;
  -webkit-transition: color 0.4s ease-in-out;
  transition: color 0.4s ease-in-out;
}
.sub-items {
  display: none;
}
</style>
<script>
function loadSearch(){
	document.userleftmenu.action = "loadSearch.htm";
	document.userleftmenu.submit();
}
</script>
<div class='wrapper'>
<form:form name="userleftmenu" id="userleftmenu" modelAttribute="mainForm" method="post">
  <ul class='items'>
    <li>
      <a href="#">Stock</a>
      <ul class='sub-items'>
      	<li><a href="javascript:loadSearch();">Search/Buy Stock</a></li>
      </ul>
    </li>
  </ul>
  </form:form>
</div>