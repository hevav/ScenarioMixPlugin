# ScenarioMix

[![Build](https://github.com/Elytrium/ElytraMix/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/Elytrium/ElytraMix/actions/workflows/maven.yml)
![Issuses](https://img.shields.io/github/issues/Elytrium/ElytraMix)
![Stars](https://img.shields.io/github/stars/Elytrium/ElytraMix)

Версия: 1.2.1<br>
Spigot-плагин на сценарии для ивентов (fork плагина от 7isenko)<br>
Нативная версия: 1.16.4<br>
Для работы ElytraMix требуется наличие плагинов **WorldGuard** и **WorldEdit**!<br>
**Версия 1.12.2 ныне не поддерживается!**<br>
[Скачать](https://github.com/Elytrium/ElytraMix/releases/latest)

## Использование

Просто напишите команду ``/mix``, и выберите необходимый сценарий.<br>
Для настройки сценария нажмите ПКМ по сценарию

## Разрешения

- **scenariomix.menu** - открывать меню со сценариями
- **scenariomix.tools** - использовать инструменты с плагина включая команды(например палку с Выброса TNT)
- **scenariomix.debug** - Показывать сообщения отладки

## Доступные сценарии
### Игровые сценарии

- **Толкатель** - Каждую секунду толкает всех игроков в случайную сторону
- **Низшему не жить** - Каждую минуту самый нижний игрок умирает
- **Снежки** - Теперь снежки могут наносить урон
- **Снегопад** - Запускает сильный снегопад
- **Последний взгляд** - Взгляд на человека с тегом ``last_sight`` вас мгновенно убьёт. Чтобы добавить тег введите ``/scoreboard players tag <nick> add last_sight``
- **Заполнение** - Постепенно заполняет мир выбранным блоком
- **Паукопакалипсис** - Дождь из пауков, аллилуйя
- **Апокалипсис** - Запускает метеоритный дождь
- **Погнали** - Теперь можно ездить на игроках
- **Тактильная смерть** - Обнимашки запрещены
- **Не прыгать** - Прыгать нельзя. Я серьёзно, не прыгай
- **Security** - Игрок с тегом ``weak`` не может бить, с тегом ``strong`` не может ломать
- **Рандомная выдача предметов** - Выдает определенное количество рандомных блоков за определенное время и из определенного списка (по умолчанию все блоки и предметы)
- **Выброс TNT** - Возможность выбрасывать TNT определенным предметом. Задача игроков: не попасть в воду.

### Инструменты

- **Авто-гм 3** - Когда игрок возрождается, он автоматически получает гм 3
- **Авто-возрождение** - При смерти игрок будет автоматически возрождён
- **Все на меня!** - Все игроки могут бить только тех, у кого есть тег fight_me.  Чтобы добавить тег введите ``/scoreboard players tag <nick> add fight_me``
- **Ограничение высоты** - Устанавливает максимальную и минимальную высоту установки блоков 
- **Рандомные команды** - Рандомно делит игроков на команды в scoreboard 

### Быстрые команды
> *Меню, в котором можно быстро выполнить нужную команду, например включить PvP в вашем регионе или скрыть никнеймы игроков.*

- **Переключение белого списка** - /whitelist
- **Очистка белого списка** - /whitelist
- **Видимость никнеймов** - /team modify
- **Правило коллизии** - /team modify
- **Флаг Build** - /region flag
- **Флаг PvP** - /region flag
- **Флаг Use**  - /region flag

### Команды Essentials
> *Набор популярных команд из плагина Essentials*
- **Powertool** - Делает из предмета волшебную палочку
- **Gamemode** - Переключает игровой режим
- **Fly** - Включает режим полёта для игрока
- **Heal** - Исцеляет игрока
- **Broadcast** - Отправляет сообщение на весь сервер
- **Kickall** - Кикает всех игроков
- **Mobkill** - Убивает всех мобов в заданом радиусе
- **Day** - Устанавливает дневное время суток
- **Night** - Устанавливает ночное время суток
- **Sun** - Устанавливает солнечную погоду
- **Rain** - Устанавливает дождливую погоду
- **TpAll** - Телепортирует всех игроков к вам
- **TpHere** - Телепортирует игрока к вам

#### Лицензия
[AGPL-3.0](https://github.com/Elytrium/ElytraMix/blob/master/LICENSE)
