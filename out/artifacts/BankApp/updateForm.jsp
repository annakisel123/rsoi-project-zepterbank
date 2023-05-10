
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Clients"%>
<% Clients client = (Clients) request.getAttribute("client");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактировать информацию о клиенте</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/icon.png">
    </head>
    <body>
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/vbankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                           user="root"  password="markovskaya_02"/>

        <sql:query dataSource="${snapshot}" var="result1">SELECT * from cities;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result2">SELECT * from families;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result3">SELECT * from invalids;
        </sql:query>
        <sql:query dataSource="${snapshot}" var="result4">SELECT * from nationals;
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
            <form class="client-form" action="updateClient" method="get">
                <section>
                    <h2 class="client-form__heading">Информация о клиенте</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">ClientID</p>
                        <input class="client-form__info__text-input" type="text" name="clientID" value="<%= client.getId()%>" required>
                        <p class="client-form__info__text">Имя*</p>
                        <input class="client-form__info__text-input" type="text" name="name" value="<%= client.getName()%>" required>
                        <p class="client-form__info__text">Фамилия*</p>
                        <input class="client-form__info__text-input" type="text" name="surname" value="<%= client.getSurname()%>" required>
                        <p class="client-form__info__text">Отчество*</p>
                        <input class="client-form__info__text-input" type="text" name="fname" value="<%= client.getFname()%>" required>
                        <p class="client-form__info__text">Дата рождения*</p>
                        <input class="client-form__info__text-input" type="date" name="birthdate" value="<%= client.getBirthdate()%>" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Паспортные данные</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Серия*</p>
                        <input class="client-form__info__text-input" type="text" name="spp" value="<%= client.getSpp()%>" required maxlength="2" pattern="[А-Я]{2,2}" placeholder="XY">
                        <p class="client-form__info__text">Номер*</p>
                        <input class="client-form__info__text-input" type="text" name="npp" value="<%= client.getNpp()%>" required maxlength="7" pattern="[0-9]{7,7}" placeholder="xxxxxxx">
                        <p class="client-form__info__text">Ид. номер*</p>
                        <input class="client-form__info__text-input" type="text" name="ipp" value="<%= client.getIpp()%>" required maxlength="14" pattern="[А-Яа-я0-9]{14}">
                        <p class="client-form__info__text">Кем выдан*</p>
                        <input class="client-form__info__text-input" type="text" name="whogive" value="<%= client.getWhogive()%>" required>
                        <p class="client-form__info__text">Когда выдан*</p>
                        <input class="client-form__info__text-input" type="date" name="whengive" value="<%= client.getWhengive()%>" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Место проживания</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Место рождения*</p>
                        <input class="client-form__info__text-input" type="text" name="cityborn" value="<%= client.getCityborn()%>" required>
                        <p class="client-form__info__text">Город прописки*</p>
                        <select class="client-form__info__select" name="city">
                            <c:forEach var="row" items="${result1.rows}">
                                <option value="${row.city}">${row.city}</option>
                            </c:forEach>
                            <option selected class="defn"><%= client.getCity()%></option>
                        </select>
                        <p class="client-form__info__text">Город проживания*</p>
                        <select class="client-form__info__select" name="citydoc">
                            <c:forEach var="row" items="${result1.rows}">
                                <option value="${row.city}">${row.city}</option>
                            </c:forEach>
                            <option selected class="defn"><%= client.getCitydoc()%></option>
                        </select>
                        <p class="client-form__info__text">Адрес проживания*</p>
                        <input class="client-form__info__text-input" type="text" name="address" value="<%= client.getAddress()%>">
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Контактные данные</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Мобильный*</p>
                        <input class="client-form__info__text-input" type="text" id="mh" name="mobilenum" value="<%= client.getMobilenum()%>">
                        <p class="client-form__info__text">Домашний*</p>
                        <input class="client-form__info__text-input" type="text" id="hh" name="homenum" value="<%= client.getHomenum()%>">
                        <p class="client-form__info__text">e-mail*</p>
                        <input class="client-form__info__text-input" type="email" name="email" value="<%= client.getEmail()%>">
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Работа</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Место работы*</p>
                        <input class="client-form__info__text-input" type="text" name="job" value="<%= client.getJob()%>">
                        <p class="client-form__info__text">Должность*</p>
                        <input class="client-form__info__text-input" type="text" name="post" value="<%= client.getPost()%>">
                        <p class="client-form__info__text">Доход*</p>
                        <input class="client-form__info__text-input" type="text" name="money" value="<%= client.getMoney()%>">
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Допольнительно</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Семейное положение*</p>
                        <select class="client-form__info__select" name="marriage" required>
                            <c:forEach var="row" items="${result2.rows}">
                                <option value="${row.family}">${row.family}</option>
                            </c:forEach>
                            <option selected class="defn"><%= client.getMarriage()%></option>
                        </select>
                        <p class="client-form__info__text">Национальность*</p>
                        <select class="client-form__info__select" name="nat" required>
                            <c:forEach var="row" items="${result4.rows}">
                                <option value="${row.national}">${row.national}</option>
                            </c:forEach>
                            <option selected class="defn"><%= client.getNat()%></option>
                        </select>
                        <p class="client-form__info__text">Инвалидность*</p>
                        <select class="client-form__info__select" name="invalid" required>
                            <c:forEach var="row" items="${result3.rows}">
                                <option value="${row.invalidgroup}">${row.invalidgroup}</option>
                            </c:forEach>
                            <option selected class="defn"><%= client.getInvalid()%></option>
                        </select>
                        <p class="client-form__info__text">Пенсионер*</p>
                        <% if(client.getOld().equals("Да")) { %>
                            <input class="client-form__info__checkbox" type="checkbox" name="old" value="Да" checked>
                        <% } else { %>
                            <input class="client-form__info__checkbox" type="checkbox" name="old" value="Да">
                        <% } %>
                        <input type="hidden" name="old" value="Нет">
                    </div>
                </section>

                <button class="client-form__button content__button">Обновить</button>
            </form>
        </section>
    </body>
</html>
