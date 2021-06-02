<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>UBAY</title>
    <link rel="stylesheet" href="/ubay_war/css/mystyle.css">
    <link rel="javascript" href="/ubay_war/js/make_bid.js">
</head>
<body onload="funonload('99','');printLogin();">
<body>
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
<section>
    <div id="zatemnit" >
        <div id="openModal">
            <p><strong>Старый пароль</strong></p>
            <p><input type="password" id="password" maxlength="25" size="40" name="password"></p>
            <p><strong>Новый пароль</strong></p>
            <p><input type="password" id="password2" maxlength="25" size="40" name="password2"></p>
            <p><strong>Подтверждение нового пароля</strong></p>
            <p><input type="password" id="password3" maxlength="25" size="40" name="password3"></p>
            <p><label id="password_change">Заполните все поля</label></p>
            <button id="upload2" class="close" onclick="change('1')">Измениить пароль</button>
            <p><a href="/ubay_war/jsp/customer.jsp" class="close">Вернуться</a></p>
        </div>
    </div>
    <p><a href="#zatemnit">изменить пароль</a></p>
    <div id="zatemnit4" >
        <div id="openModal4">
            <li class="form-row">
                <label><b>код лота</b></label>
                <input type="number" id="itemNumber1" maxlength="20" size="40" name="itemNumber1">
            </li>
            <li class="form-row">
                <label><b>сумма ставки</b></label>
                <input type="number" id="bid" maxlength="20" size="40" name="bid">
            </li>
            <p><label id="check">Заполните все поля формы</label></p>
            <p><a href="#zatemnit4" class="close" onclick="make_bid()">Сделать ставку</a></p>
            <p><a href="/ubay_war/jsp/customer.jsp" id="sig" class="close">Вернуться</a></p>
        </div>
    </div>
    <p><a href="#zatemnit4">сделать ставку</a></p>
    <div id="zatemnit2" >
        <div id="openModal2">
            <li class="form-row">
                <label><b>код лота</b></label>
                <input type="number" id="itemNumber2" maxlength="20" size="40" name="itemNumber2">
            </li>
            <li class="form-row">
            <label><b>оцените от 1 до 10</b></label>
            <input type="range"
                   id = "mark"
                   min="1"
                   max="10"
                   step="1"
                   value="1" name="mark">
            </li>
            <li class="form-row">
                <label><b>отзыв</b></label>
                <input type="text" id="feedback" maxlength="100" size="40" name="feedback">
            </li>
            <p><label id="in">Заполните все поля формы</label></p>
            <p><a href="#zatemnit2" class="close" onclick="feedback()">Oставить отзыв и оценку</a></p>
            <p><a href="/ubay_war/jsp/customer.jsp" id="ig" class="close">Вернуться</a></p>
        </div>
    </div>
    <p><a href="#zatemnit2">оставить отзыв и оценку</a></p>
    <p><a href="#" onclick="exitLog()">выйти</a></p>
<%--    <p><a onclick="exitLog()" href="/ubay_war/jsp/index.jsp">выйти</a></p>--%>
</section>
<aside>
   <main>
   </main>
</aside>
<script src="/ubay_war/js/make_bid.js"></script>
<script src="/ubay_war/js/myjs.js"></script>
</body>
</html>
