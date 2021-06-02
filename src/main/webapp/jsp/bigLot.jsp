<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>UBAY</title>
    <link rel="stylesheet" href="/ubay_war/css/mystyle.css">
    <link rel="javascript" href="/ubay_war/js/myjs.js">
</head>
<body onload="loadLot();funonload('99','')" >
<header>
    <h1 id="img"><strong>НАЗВАНИЕ ЛОТА</strong></h1>
</header>
<section>
    <h1><strong>ФОТОЧКА</strong></h1>
    <div>
        <img id="img1" src="">
    </div>
</section>
<aside>
    <div id="typ">
   <h3 id="price"><strong>ЦЕНА</strong></h3>
    <h5 id="lotId"><strong>артикул</strong></h5>
    <h5 id="lotStatus"><strong>статус лота</strong></h5>
    <p id="lotType"><strong>тип техники</strong></p>
        <p id="lotDescription"><strong>описание</strong></p>
    <p id="lotWinner"><strong>победитель</strong></p>
    <p id="lotStar"><strong>оценка победителя</strong></p>
    <p id="lotFeedback"><strong>фидбек</strong></p>
        <p ><strong>     </strong></p>
        <p ><strong>     </strong></p>
        <p ><strong>     </strong></p>
        <p ><strong>"Последние поступления"</strong></p>
        <div>
            <main>
            </main>
        </div>
</aside>
<script src="/ubay_war/js/loadlot.js"></script>
<script src="/ubay_war/js/myjs.js"></script>
</body>
</html>