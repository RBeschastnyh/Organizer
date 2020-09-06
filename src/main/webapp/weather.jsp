<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="stylesheets/style.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Jura:wght@700&display=swap" rel="stylesheet">
        <title>Погода</title>
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
                    <div class="menu-item"><a class="without-highlighting" href="/strawberry-organizer/">Главная</a></div>
                    <div class="menu-item"><a class="without-highlighting" href="/strawberry-organizer/schedule">Посмотреть электричку</a></div>
                    <div class="menu-item"><a class="without-highlighting" href="/strawberry-organizer/weather">Погода в твоём районе</a></div>
                    <div class="menu-item">Список дел</div>
                    <div class="menu-item">Расписание занятий</div>
                    <div class="menu-item">Об авторе</div>
                </div>
                </label>
            </div>
        </nav>
        <div class="main-text">
            Погода
        </div>
        <div class="main-content-placeholder">
            ${currentTemperature}
        </div>
        <div class="other-content-placeholder">
            Ощущается как ${currentTemperatureAsFeels}
        </div>
    </body>
</html>
