<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>UBAY</title>
    <link rel="stylesheet" href="/ubay_war/css/mystylesigin.css">
    <link rel="javascript" href="/ubay_war/js/myjs.js">
</head>
<body>
<header>
</header>
<section>
    <div id="/ubay_war/jsp/registr.jsp/zatemnit" >
        <div id="openModal">
            <p><strong>Логин</strong></p>
            <p><input type="text" id="login" maxlength="25" size="40" name="login"></p>
            <p><strong>Пароль</strong></p>
            <p><input type="password" id="password" maxlength="25" size="40" name="password"></p>
            <p><strong>Подтвердите пароль</strong></p>
            <p><input type="password" id="password2" maxlength="25" size="40" name="password2"></p>
            <p><strong>Введите емейл</strong></p>
            <p><input type="email" id="email" maxlength="250" size="30" name="email"></p>
            <p><strong>Роль</strong></p>
            <form action="select" method="post" >
            <p><select size="1" id="role">
                <option value="0">Продавец</option>
                <option selected value="1">Покупатель</option>
            </select></p>
            </form>
            <p><a href="#" id="upload1" class="close" onclick="registr()">Зарегистрироваться</a></p>
            <p><a href="/ubay_war/jsp/index.jsp" id="sig" class="close">Вернуться</a></p>
            <p><label id="label_registr"><b>Введите логин и пароль</b></label></p>
        </div>
    </div>
    <p><a href="registr./ubay_war/js/zatemnit"></a></p>
</section>
<aside>
</aside>
<script src="/ubay_war/js/myjs.js"></script>
</body>
</html>
