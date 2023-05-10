<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/icon.png">
        <title>Счета</title>
        <script>
            function sort(el) {
                var col_sort = el.innerHTML;
                var tr = el.parentNode;
                var table = tr.parentNode;
                var th, arrow, col_sort_num;

                for (var i = 0; (th = tr.getElementsByTagName("th").item(i)); i++) {
                    if (th.innerHTML == col_sort) {
                        col_sort_num = i;
                        if (th.prevsort == "y") {
                            arrow = th.firstChild;
                            el.up = Number(!el.up);
                        } else {
                            th.prevsort = "y";
                            arrow = th.insertBefore(document.createElement("span"), th.firstChild);
                            el.up = 0;
                        }
                        //arrow.innerHTML = el.up ? "↑ " : "↓ ";
                    } else {
                        if (th.prevsort == "y") {
                            th.prevsort = "n";
                            if (th.firstChild)
                                th.removeChild(th.firstChild);
                        }
                    }
                }

                var a = new Array();

                for (i = 1; i < table.rows.length; i++) {
                    a[i - 1] = new Array();
                    a[i - 1][0] = table.rows[i].getElementsByTagName("td").item(col_sort_num).innerHTML;
                    a[i - 1][1] = table.rows[i];
                }

                a.sort();
                if (el.up)
                    a.reverse();

                for (i = 0; i < a.length; i++)
                    table.appendChild(a[i][1]);
            }
        </script>
    </head>
    <body>
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
            <h1 class="heading h1">Модуль CЧЕТА</h1>
            <h2 class="heading h2">Список счетов</h2>
            <div class="white-space"></div>
            <div class="content__table-wrapper">
                <table class="content__table-wrapper__table">
                    <% String table = (String) request.getAttribute("table");%>
                    <tr>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">ID счета</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Название</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Номер счета</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Тип счета</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Код счета</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Дебет</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Кредит</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Сальдо</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">ID клиента</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">ID договора</th>
                    </tr>
                    <%=table%>
            </div>
        </section>
    </body>
</html>
