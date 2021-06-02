<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>UBAY</title>
    <link rel="stylesheet" href="/ubay_war/css/mystyle.css">
    <link rel="javascript" href="/ubay_war/js/myjs.js">
</head>
<body onload="funonload('99','');">
<header>
    <h1><strong>АУКЦИОН</strong></h1>
    <p><label id="label_img_type"class="label_img_type">Искать по</label></p>
    <div class="img_type">
        <input id="img_type0" type="radio" name="radio3" value="0" checked>
        <label for="img_type0">имени лота</label>
        <%--        <input id="img_type1" type="radio" name="radio3" value="1" checked>--%>
        <%--        <label for="img_type1">номер лота</label>--%>
        <input id="img_type1" type="radio" name="radio3" value="2" checked>
        <label for="img_type1">имени продавца</label>
        <input id="img_type2" type="radio" name="radio3" value="3" checked>
        <label for="img_type2">статусу</label>
        <%--        <input id="img_type4" type="radio" name="radio3" value="4" checked>--%>
        <%--        <label for="img_type4">стартовой цене</label>--%>
        <%--        <input id="img_type1" type="radio" name="radio3" value="1" >--%>
        <%--        <label for="img_type1">типу техники</label>--%>
        <input id="img_type3" type="radio" name="radio3" value="5" >
        <label for="img_type3">дате начала в виде 2000-01-01</label>
        <p id="logIn"><strong>Логин:</strong></p>
    </div>
    <div class="search">
        <input type="search" id="search">
        <input type="submit" class="but" value="Найти" onclick="findUbay()">
    </div>
</header>
<section class="ref">
    <p><a href="/ubay_war/jsp/registr.jsp">зарегистрироваться</a></p>
    <p><a href="/ubay_war/jsp/log_in.jsp">войти</a></p>
    <p><a href="/ubay_war/jsp/forgot.jsp">забыл пароль</a></p>
<%--    <p><a href="/ubay_war/jsp/seller.jsp">продавец</a></p>--%>
<%--    <p><a href="/ubay_war/jsp/customer.jsp">покупатель</a></p>--%>
<%--    <p><a href="/ubay_war/jsp/bigLot.jsp">лот</a></p>--%>
</section>
<aside>
    <main>
    </main>
</aside>
<script src="/ubay_war/js/myjs.js"></script>

</body>
</html>