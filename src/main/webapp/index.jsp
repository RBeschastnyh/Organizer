<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="stylesheets/style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Jura:wght@700&display=swap" rel="stylesheet">
        <title>Органайзер</title>
    </head>
    <body>
        <nav>
            <div class="navigation-placeholder">
                <input type="checkbox" id="btnControl"/>
                <label class="btn" for="btnControl">
                    <div class="menu-invoker-placeholder">
                        <div class="menu-invoker">
                            <div class="menu-straight-line-top"></div>
                            <div class="menu-straight-line-bottom"></div>
                        </div>
                    </div>
                </label>
                <div class="main-menu-placeholder">
                    <div class="menu-item">Посмотреть электричку</div>
                    <div class="menu-item">Погода в твоём районе</div>
                    <div class="menu-item"></div>
                </div>
                </label>
            </div>
        </nav>
        <div class="functionality-demonstrator">
            <div class="function-demonstration-flow">
                <div class="functionality-palette">
                    <div class="image-placeholder">
                        <img src="images/trains-icons/subway-train.png">
                    </div>
                    <div class="description-placeholder">
                        <label class="function-description">Смотри ближайшие элки на Белорусском направлении</label>
                    </div>
                </div>
                <div class="functionality-palette">
                    <div class="image-placeholder">
                        <img src="images/sun-icons/sun-icon.png">
                    </div>
                    <div class="description-placeholder">
                        <label class="function-description">Оцени погоду перед выходом на улицу</label>
                    </div>
                </div>
            </div>
            <div class="function-demonstration-flow">
                <div class="functionality-palette">
                    <div class="image-placeholder">
                        <img src="images/hse-icons/book.png">
                    </div>
                    <div class="description-placeholder">
                        <label class="function-description">Посмотри пары на сегодня</label>
                    </div>
                </div>
                <div class="functionality-palette">
                    <div class="image-placeholder">
                        <img src="images/task-list-icons/tick-without-box.png">
                    </div>
                    <div class="description-placeholder">
                        <label class="function-description">Составь список дел на день</label>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            Данные предоставлены сервисом <a href="https://rasp.yandex.ru/" target="_blank">Яндекс.Расписания</a>
        </footer>
    </body>
</html>
