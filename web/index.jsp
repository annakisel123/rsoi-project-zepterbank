<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Цептер Банк</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/favicon.ico">
    </head>
    <body>
        <section class="index-page">
            <img class="index-page__bank-logo" src="assets/bank-logo.png" alt="bank-logo" onclick="location.href = '../Zepterbank'">
            <ul class="index-page__list">
                <li class="index-page__list__item">
                    <div class="index-page__list__item__img-wrapper" onclick="location.href = 'clients'">
                        <img class="index-page__list__item__img-wrapper__img" src="assets/clients.png" alt="clients">
                    </div>
                    <p class="index-page__list__item__button"><a class="invisible-href" href="clients">Клиенты</a></p>
                </li>
                <li class="index-page__list__item">
                    <div class="index-page__list__item__img-wrapper" onclick="location.href = 'deposits'">
                        <img class="index-page__list__item__img-wrapper__img" src="assets/deposit.png" alt="deposits">
                    </div>
                    <p class="index-page__list__item__button"><a class="invisible-href" href="deposits">Депозиты</a></p>
                </li>
                <li class="index-page__list__item">
                    <div class="index-page__list__item__img-wrapper" onclick="location.href = 'credits'">
                        <img class="index-page__list__item__img-wrapper__img" src="assets/credit.png" alt="credits">
                    </div>
                    <p class="index-page__list__item__button"><a class="invisible-href" href="credits">Кредиты</a></p>
                </li>
                <li class="index-page__list__item">
                    <div class="index-page__list__item__img-wrapper" onclick="location.href = 'invoices'">
                        <img class="index-page__list__item__img-wrapper__img" src="assets/accounts.png" alt="accounts">
                    </div>
                    <p class="index-page__list__item__button"><a class="invisible-href" href="invoices">Счета</a></p>
                </li>
            </ul>
        </section>
    </body>
</html>
