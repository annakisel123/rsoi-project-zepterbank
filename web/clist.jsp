
<%@page import="model.Credits"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый кредит</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/icon.png">
    </head>
    <% String table = (String) request.getAttribute("table");%>
    <body>
        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy");%>
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/vbankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC"
                           user="root"  password="markovskaya_02"/>

        <sql:query dataSource="${snapshot}" var="result1">SELECT * from client;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result2">SELECT * from credits;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result3">SELECT * from currency;
        </sql:query>
        <nav class="header">
            <img class="header__logo" src="assets/bank-logo.png" alt="bank-logo" onclick="location.href = '../Zepterbank'">
            <ul class="header__list">
                <li class="header__list__item"><a class="invisible-href" href="clients">Клиенты</a></li>
                <li class="header__list__item"><a class="invisible-href" href="deposits">Депозиты</a></li>
                <li class="header__list__item"><a class="invisible-href" href="credits">Кредиты</a></li>
                <li class="header__list__item"><a class="invisible-href" href="invoices">Счета</a></li>
            </ul>
        </nav>
        <section class="content">
            <section class="content__form-wrapper">
                <div class="content__table-wrapper-short">
                    <table class="content__table-wrapper__table">
                        <tr>
                            <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">ID</th>
                            <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Название</th>
                            <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Кредит BYN</th>
                            <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Кредит EUR</th>
                            <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Кредит RUB</th>
                            <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Кредит USD</th>
                        </tr>
                        <%=table%>
                </div>
                <form action="cnew" method="get">
                    <section class="form-thin">
                        <h2 class="client-form__heading">Оформление кредита</h2>
                    </section>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Клиент*</p>
                        <select class="client-form__info__select" name="ClientID" required>
                            <c:forEach var="row" items="${result1.rows}">
                                <option value="${row.id}">${row.name} ${row.surname}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Вид кредита*</p>
                        <select class="client-form__info__select" name="crID" required>
                            <c:forEach var="row" items="${result2.rows}">
                                <option value="${row.creditID}">${row.creditName}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Валюта*</p>
                        <select class="client-form__info__select" name="curID" required>
                            <c:forEach var="row" items="${result3.rows}">
                                <option value="${row.curID}">${row.curName}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Сумма кредита*</p>
                        <input class="client-form__info__text-input" type="text" name="summa" value="0" required>
                        <p class="client-form__info__text">Дата оформления*</p>
                        <input class="client-form__info__text-input" type="text" name="dateOfStart" value="<%= df.format(new java.util.Date())%>" required>
                        <p class="client-form__info__text">На срок, мес*</p>
                        <input class="client-form__info__text-input" type="text" name="period" value="1" required="true" pattern="^\d+$">
                        <button class="form-thin__button content__button" onclick="check()" name="submit">Продолжить</button>
                    </div>
                </form>
            </section>
        </section>
    </body>
</html>
