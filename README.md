# SQS S3 Message processing service

Назначение проекта — получение сообщений из очереди AWS SQS, обработка в зависимости от типа сущности, сохранение данных в PostgreSQL и, для отдельного типа сообщений (`site`), формирование текстового объекта в S3.

Решение полностью пригодно для локальной разработки и тестирования. Эмуляция облачной инфраструктуры AWS выполняется с помощью контейнера **LocalStack**, запускаемого в Docker.

---

## Стек технологий

- **Java 17**
- **Spring Boot 3.x** (Spring Data JPA, Spring Cloud AWS)
- **PostgreSQL** – основное хранилище
- **Docker** и **Docker Compose** 
- **LocalStack** – эмуляция AWS SQS и S3
- **AWS SDK for Java** (SQS, S3)
- **Flyway** – миграции базы данных
- **Lombok**, **Spring Boot Validation**
- **Postman** – для тестирования

---

## Быстрый старт

### Предварительные требования

- Docker (версия 20.10+) и Docker Compose (v2+)
- Свободные порты `5432` (PostgreSQL), `4566` (LocalStack), `8081` (приложение)

### Запуск окружения

1. Клонируйте репозиторий:
   `git clone <url-репозитория>`
   `cd <директория-проекта>`
  Запустите все контейнеры одной командой: docker compose up -d
  Дождитесь старта всех сервисов (обычно не больше минуты). Приложение автоматически создаст тестовую очередь SQS my-queue и тестовый бакет S3 my-bucket внутри LocalStack, если они отсутствуют.
  Проверьте логи приложения: `docker compose logs app -f`

### Проверка работоспособности
Отправьте тестовое сообщение в очередь SQS через эндпоинт LocalStack:
1. через curl
```
curl -X POST "http://localhost:4566/000000000000/my-queue?Action=SendMessage" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "MessageBody={\"type\":\"site\",\"id\":18,\"action\":\"create\"}"
```
3. через Postman (рекомендуемое):
Метод: POST
URL: `http://localhost:4566/000000000000/my-queue?Action=SendMessage`
Заголовок: Content-Type: application/x-www-form-urlencoded
Тело (тип x-www-form-urlencoded):
  Ключ: MessageBody
  Значение: `{"type":"site","id":18,"action":"create"}`
<img width="1075" height="333" alt="image" src="https://github.com/user-attachments/assets/08abf192-86ee-43b5-8acc-aa59965135ab" />

После успешной отправки в логах приложения (app) появится запись:

<img width="1793" height="179" alt="image" src="https://github.com/user-attachments/assets/7f85903f-e28e-4061-a39f-f71c4f46f644" />

Если вы отправляете сообщения для других типов (space, form, group, user), логика аналогична, но без создания объектов в S3.

### Проверка данных в базе данных

Подключитесь к работающему контейнеру PostgreSQL: `docker exec -it PG-SQSservice psql -U sqsuser SQSservice`
Выполните запрос для проверки сохранённых записей: `SELECT * FROM sites;`
Аналогично можно просмотреть таблицы spaces, forms, groups, users (чтобы выйти из psql, напишите \q).

### Проверка созданных объектов в S3
1. Просмотрите содержимое бакета: `docker exec -it project-localstack awslocal s3 ls s3://my-bucket/`
Пример вывода: `2026-05-13 08:14:41         41 site-18-create.txt`
2. Для просмотра содержимого конкретного файла: `docker exec -it project-localstack awslocal s3 cp s3://my-bucket/site-18-create.txt -`
Пример вывода: `{"type":"site","id":19,"action":"create"}`

### Поддерживаемые типы сообщений
`space`
`form`
`site `
`group`
`user`
Действия (action):
`create` – создаёт новую запись (для site также загружает файл)
`update` – обновляет временную метку updated_at
`delete` – помечает запись как удалённую (soft delete)



