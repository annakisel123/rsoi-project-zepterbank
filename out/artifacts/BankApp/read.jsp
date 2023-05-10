<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="icon" href="assets/icon.png">
        <title>Клиенты</title>
        <script>
            function confirmDelete(arg1) {
                if (confirm("Вы подтверждаете удаление клиента c ID " + arg1)) {
                    location.href = 'delete?id=' + arg1;
                } else {
                    return false;
                }
            }


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
            <h1 class="heading h1">Модуль КЛИЕНТЫ</h1>
            <h2 class="heading h2">Список клиентов Абсолютбанка</h2>
            <button class="content__button" onclick="location.href = 'add'">Добавить клиента</button>
            <div class="content__table-wrapper">
                <table class="content__table-wrapper__table">
                    <% String table = (String) request.getAttribute("table");%>
                    <tr>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">ID</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Имя</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Фамилия</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Отчество</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Дата рождения</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Номер паспорта</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Серия паспорта</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Идентификационный номер</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Кем выдан</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Когда выдан</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Место рождения</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Город проживания</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Адрес проживания</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Мобильный</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Домашний</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">e-mail</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Работа</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Должность</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Родной город</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Семейное положение</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Национальность</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Инвалидность</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Пенсионер</th>
                        <th onclick="sort(this)" title="Нажмите на заголовок, чтобы отсортировать колонку">Зарплата</th>
                    </tr>
                    <%=table%>
                </table>
            </div>
        </section>
    </body>
</html>
