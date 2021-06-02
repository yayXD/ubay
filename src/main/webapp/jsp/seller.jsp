<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>UBAY</title>
    <link rel="stylesheet" href="/ubay_war/css/mystyle.css">
    <link rel="javascript" href="/ubay_war/js/myjs.js">
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
            <button id="upload2" class="close" onclick="change('0')">Измениить пароль</button>
            <p><a href="/ubay_war/jsp/seller.jsp" class="close">Вернуться</a></p>
            <%--<p><input type="button" id="upload2" class="close" onclick="sign_in()"></p>--%>
        </div>
    </div>
    <p><a href="#zatemnit">изменить пароль</a></p>
    <div id="zatemnit3" >
        <div id="openModal3">
            <li class="form-row">
                <label><b>Название</b></label>
                <input type="text" id="name" maxlength="20" size="40" name="nam">
            </li>
            <li class="form-row">
                <label><b>Тип техники</b></label>
                <form action="select" method="post" >
                    <p><select size="1" id="type">
                        <option value="0">техника</option>
                        <option selected value="1">подарки</option>
                            <option value="2">аксесуары</option>
                            <option value="3">одежда</option>
                            <option value="4">обувь</option>
                            <option value="5">мебель</option>
                            <option value="6">услуги</option>
                            <option value="7">отдых</option>
                            <option value="8">спорт</option>
                    </select></p>
                </form>
            </li>
            <li class="form-row">
                <label><b>Описание</b></label>
                <input type="text" id="description" maxlength="125" size="40" name="description">
            </li>
            <li class="form-row">
                <label><b>Стартовая цена</b></label>
                <input type="number" id="price" min="0" maxlength="10" size="40" name="price">
            </li>
            <li class="form-row">
                <label><b>Прирост ставки</b></label>
                <input type="number" id="increase" min="0" maxlength="10" size="40" name="bip">
            </li>
            <li class="form-row">
                <label><b>Начало аукциона</b></label>
                <input type="date" id="startTime" min="2021-02-10" maxlength="10" size="40" name="fdate">
            </li>
            <li class="form-row">
                <label><b>Продолжительность, часы</b></label>
                <input type="time" id="duration" maxlength="10" size="40" name="durat">
            </li>
            <li class="form-row">
                <label><b>Загрузить картинку</b></label>
                <input id="inputFile" class="buttons" type="file" accept="image/*" onchange="select_image(this)">
            </li>
            <li class="form-row">
            <p class="pictures1">
                <img id="file1" src=""height="100" >
            </p>
            </li>
            <p><label id="lot_in">Заполните все поля формы</label></p>
            <p><a href="#zatemnit3" class="close" onclick="new_lot()">Разместить лот</a></p>
            <p><a href="/ubay_war/jsp/seller.jsp" id="sig" class="close">Вернуться</a></p>
        </div>
    </div>
    <p><a href="#zatemnit3">разместить лот</a></p>
    <div id="zatemnit4" >
        <div id="openModal4">
            <p><strong>Номер лота</strong></p>
            <p><input type="number" id="lot" maxlength="25" size="40" name="lot"></p>
                <p><strong>Победитель</strong></p>
                <p id="it"><strong>noname</strong></p>
                <p><label id="win">Пока все в порядке</label></p>
                <p><a href="#zatemnit4" class="close" onclick="winner()">Найти победителя</a></p>
                <p><a href="/ubay_war/jsp/seller.jsp" id="enter" class="close">Вернуться</a></p>
        </div>
    </div>
    <p><a href="#zatemnit4">определить победителя</a></p>
    <p><a href="#" onclick="exitLog()">выйти</a></p>
<%--    <p><a onclick="exitLog()" href="/ubay_war/jsp/index.jsp">выйти</a></p>--%>
</section>
<aside>
    <main>
    </main>
</aside>
<script src="/ubay_war/js/create_lote.js"></script>
<script src="/ubay_war/js/make_bid.js"></script>
<script src="/ubay_war/js/myjs.js"></script>
</body>
</html>