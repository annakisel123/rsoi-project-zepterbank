
<%@page import="model.Clients"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.OC"%>
<% OC oc = (OC) request.getAttribute("oc");%>
<% Clients client = (Clients) request.getAttribute("client");%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Оформление кредита</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/icon.png">
    </head>
    <body>
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/vbankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                           user="root"  password="markovskaya_02"/>

        <sql:query dataSource="${snapshot}" var="result1">SELECT * from credits
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result2">SELECT * from families;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result3">SELECT * from invalids;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result4">SELECT * from nationals;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result5">SELECT * from currency;
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
            <form class="client-form" action="cconfD" method="get">
                <section>
                    <h2 class="client-form__heading">Информация о клиенте</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">ClientID</p>
                        <input class="client-form__info__text-input" type="text" readonly name="clientID" value="<%= client.getId()%>" required>
                        <p class="client-form__info__text">Имя*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="name" value="<%= client.getName()%>" required>
                        <p class="client-form__info__text">Фамилия*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="surname" value="<%= client.getSurname()%>" required>
                        <p class="client-form__info__text">Отчество*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="fname" value="<%= client.getFname()%>" required>
                        <p class="client-form__info__text">Дата рождения*</p>
                        <input class="client-form__info__text-input" type="date" readonly name="birthdate" value="<%= client.getBirthdate()%>" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Паспортные данные</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Серия*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="spp" value="<%= client.getSpp()%>" required pattern="[А-Я]{2,2}" placeholder="XY">
                        <p class="client-form__info__text">Номер*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="npp" value="<%= client.getNpp()%>" required pattern="[0-9]{7,7}" placeholder="xxxxxxx">
                        <p class="client-form__info__text">Кем выдан*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="whogive" value="<%= client.getWhogive()%>" required>
                        <p class="client-form__info__text">Когда выдан*</p>
                        <input class="client-form__info__text-input" type="date" readonly name="whengive" value="<%= client.getWhengive()%>" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Информация о кредите</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Кредит*</p>
                        <c:forEach var="row" items="${result1.rows}">
                            <c:set var="a" scope="session" value="${row.creditID}"/>
                            <c:set var="b" scope="session" value="<%= oc.getCrId()%>"/>

                            <c:if test="${a == b}">
                                <input class="client-form__info__text-input" type="text" readonly name="crName" value="<c:out value="${row.creditName}"/>" required>
                            </c:if>

                        </c:forEach>
                        <p class="client-form__info__text">Валюта*</p>
                        <c:forEach var="row" items="${result5.rows}">
                            <c:set var="a" scope="session" value="${row.curID}"/>
                            <c:set var="b" scope="session" value="<%= oc.getCurId()%>"/>

                            <c:if test="${a == b}">
                                <input class="client-form__info__text-input" type="text" readonly name="curName" value="<c:out value="${row.curName}"/>" required>
                            </c:if>

                        </c:forEach>
                        <p class="client-form__info__text">Процентная ставка*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="percent" value="<%= oc.getPercent()%>" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Информация о сроках</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Дата выдачи*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="dateOfStart" value="<%= oc.getDateOfStart()%>" required>
                        <p class="client-form__info__text">Дата закрытия*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="dateOfFinish" value="<%= oc.getDateOfFinish()%>" required>
                        <p class="client-form__info__text">Срок вклада*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="period" value="<%= oc.getPeriod()%>">
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Размер кредита</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Сумма кредита*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="summa" value="<%= oc.getSumma()%>" required>
                        <p class="client-form__info__text">Проценты по окончанию срока*</p>
                        <input class="client-form__info__text-input" type="text" readonly name="accumulation" value="<%= oc.getAccumulation()%>" required>
                    </div>

                </section>

                <button class="client-form__button content__button">Выдать кредит</button>
            </form>
        </section>
    </body>
</html>

