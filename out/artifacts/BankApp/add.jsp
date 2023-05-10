
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/icon.png">
        <title>Добавление клиента</title>
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
            <form class="client-form" action="addClient" method="get">
                <section>
                    <h2 class="client-form__heading">Информация о клиенте</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Фамилия*</p>
                        <input class="client-form__info__text-input" type="text" name="surname" placeholder="Фамилия" required >
                        <p class="client-form__info__text">Имя*</p>
                        <input class="client-form__info__text-input" type="text" name="name" placeholder="Имя" required>
                        <p class="client-form__info__text">Отчество*</p>
                        <input class="client-form__info__text-input" type="text" name="fname" placeholder="Отчество" required>
                        <p class="client-form__info__text">Дата рождения*</p>
                        <input class="client-form__info__date-input" type="date" name="birthdate" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Паспортные данные</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Серия*</p>
                        <input class="client-form__info__text-input" type="text" name="spp" placeholder="XY" maxlength="2" required>
                        <p class="client-form__info__text">Номер*</p>
                        <input class="client-form__info__text-input" type="text" name="npp" placeholder="1234567" maxlength="7" required>
                        <p class="client-form__info__text">Ид. номер*</p>
                        <input class="client-form__info__text-input" type="text" name="ipp" placeholder="12345678901234" maxlength="14" required>
                        <p class="client-form__info__text">Кем выдан*</p>
                        <input class="client-form__info__text-input" type="text" name="whogive" placeholder="Кем выдан" required>
                        <p class="client-form__info__text">Когда выдан*</p>
                        <input class="client-form__info__date-input" type="date" name="whengive" placeholder="Когда выдан" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Место проживания</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Место рождения*</p>
                        <input class="client-form__info__text-input" type="text" name="cityborn" placeholder="Место рождения" required>
                        <p class="client-form__info__text">Город прописки*</p>
                        <select class="client-form__info__select" name="city">
                            <c:forEach var="row" items="${result1.rows}">
                                <option value="${row.city}">${row.city}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Город проживания*</p>
                        <select class="client-form__info__select" name="citydoc">
                            <c:forEach var="row" items="${result1.rows}">
                                <option value="${row.city}">${row.city}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Адрес проживания*</p>
                        <input class="client-form__info__text-input" type="text" name="address" placeholder="Адрес проживания" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Контактные данные</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Мобильный*</p>
                        <input class="client-form__info__text-input" type="text" name="mobilenum" placeholder="Мобильный телефон" maxlength="12" required>
                        <p class="client-form__info__text">Домашний*</p>
                        <input class="client-form__info__text-input" type="text" name="homenum" placeholder="Домашний телефон" maxlength="7" required>
                        <p class="client-form__info__text">e-mail*</p>
                        <input class="client-form__info__text-input" type="text" name="email" placeholder="e-mail" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Работа</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Место работы*</p>
                        <input class="client-form__info__text-input" type="text" name="job" placeholder="Место работы" required>
                        <p class="client-form__info__text">Должность*</p>
                        <input class="client-form__info__text-input" type="text" name="post" placeholder="Должность" required>
                        <p class="client-form__info__text">Доход*</p>
                        <input class="client-form__info__text-input" type="text" name="money" placeholder="Доход" required>
                    </div>
                </section>

                <section>
                    <h2 class="client-form__heading">Допольнительно</h2>
                    <div class="client-form__info">
                        <p class="client-form__info__text">Семейное положение*</p>
                        <select class="client-form__info__select" name="marriage" required="true">
                            <c:forEach var="row" items="${result2.rows}">
                                <option value="${row.family}">${row.family}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Национальность*</p>
                        <select class="client-form__info__select" name="nat" required="true">
                            <c:forEach var="row" items="${result4.rows}">
                                <option value="${row.national}">${row.national}</option>
                            </c:forEach>
                        </select>
                        <p class="client-form__info__text">Инвалидность*</p>
                        <select class="client-form__info__select" name="invalid" required="true">
                            <c:forEach var="row" items="${result3.rows}">
                                <option value="${row.invalidgroup}">${row.invalidgroup}</option>
                            </c:forEach>
                        </select>

                        <p class="client-form__info__text">Пенсионер*</p>
                        <input class="client-form__info__checkbox" type="checkbox" name="old" value="Да">
                        <input type="hidden" name="old" value="Нет">

                    </div>
                </section>

                <button class="client-form__button content__button">Добавить клиента</button>
            </form>
        </section>
    </body>
</html>
