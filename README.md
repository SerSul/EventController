# Документация API

## Контроллер событий (Event Controller)

### Обновление события

Обновление существующего события.

**Endpoint:** `PUT /events/update/{eventId}`

### Создание события

Создание нового события.

**Endpoint:** `POST /events/create`

### Получение зарегистрированных пользователей

Получение списка пользователей, зарегистрированных на событие.

**Endpoint:** `GET /events/getRegisteredUsers`

### Удаление события

Удаление существующего события.

**Endpoint:** `DELETE /events/delete/{eventId}`

## Контроллер профиля пользователя (User Profile Controller)

### Обновление профиля

Обновление информации о пользователе.

**Endpoint:** `PUT /api/profiles/updateProfile`

### Создание профиля

Создание нового профиля пользователя.

**Endpoint:** `POST /api/profiles/createProfile`

## Контроллер регистрации (Registration Controller)

### Регистрация пользователя на событие

Регистрация пользователя на определенное событие.

**Endpoint:** `POST /registration/registerUserForEvent/{eventId}`

### Отмена регистрации пользователя на событие

Отмена регистрации пользователя на определенное событие.

**Endpoint:** `DELETE /registration/unregisterUserFromEvent/{eventId}`

## Контроллер аутентификации (Auth Controller)

### Регистрация

Регистрация нового пользователя.

**Endpoint:** `POST /api/auth/signup`

### Вход

Аутентификация пользователя и получение токена JWT.

**Endpoint:** `POST /api/auth/signin`

