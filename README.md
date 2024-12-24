### Gmod lua_error_url web hook

Данный инструмент позволяет захостить веб панель для сбора ошибок со всех ваших серверов Garry's Mod

### Запуск
1. Соберите back (это обычный java Spring Boot проблем с гуглом возникнуть не должно)
2. Соберите фронт (также обычный NuxtJS сборка через npm run build)
3. Перейдите на http://localhost:3000/ и воуля, у вас есть панелька!

### Чтобы gmod слал еррор логи
добавьте в server.cfg: lua_error_url "http://localhost:8080/log?server=rg_dev_srv"
где rg_dev_srv любой уникальный тег для сервера.

### В планах

Добавить сборку в Docker
Расширить функционал анализа ошибок.


![GitHubViewsCounter](https://openlabx.com/githubviewscounter/api/gitvcr.php?username=physgun-box&repository=gmod-errors-hook-web&theme=dark)
