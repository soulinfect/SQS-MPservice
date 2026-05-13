INSERT INTO spaces (external_id, name, description, status, action, payload_json, deleted)
VALUES
    ('space-001', 'Space Alpha',   'created by migration', 'created', 'create', '', FALSE),
    ('space-002', 'Space Beta',    'created by migration', 'created', 'create', '', FALSE),
    ('space-003', 'Space Gamma',   'created by migration', 'created', 'create', '', FALSE),
    ('space-004', 'Space Delta',   'created by migration', 'created', 'create', '', FALSE),
    ('space-005', 'Space Epsilon', 'created by migration', 'created', 'create', '', FALSE);

INSERT INTO groups (external_id, name, description, status, action, payload_json, deleted)
VALUES
    ('group-001', 'Group Admins',    'created by migration', 'created', 'create', '', FALSE),
    ('group-002', 'Group Editors',   'created by migration', 'created', 'create', '', FALSE),
    ('group-003', 'Group Viewers',   'created by migration', 'created', 'create', '', FALSE),
    ('group-004', 'Group Managers',  'created by migration', 'created', 'create', '', FALSE),
    ('group-005', 'Group Developers','created by migration', 'created', 'create', '', FALSE);

INSERT INTO users (external_id, name, description, status, action, payload_json, deleted, email, first_name, last_name, group_id)
VALUES
    ('user-001', 'User Ivan',       'created by migration', 'created', 'create', '', FALSE, 'ivan@mail.ru',       'Иван',     'Иванов',       1),
    ('user-002', 'User Petr',       'created by migration', 'created', 'create', '', FALSE, 'petr@mail.ru',       'Пётр',     'Петров',       1),
    ('user-003', 'User Sergei',     'created by migration', 'created', 'create', '', FALSE, 'sergei@mail.ru',     'Сергей',   'Сергеев',      1),
    ('user-004', 'User Alex',       'created by migration', 'created', 'create', '', FALSE, 'alex@mail.ru',       'Алексей',  'Алексеев',     2),
    ('user-005', 'User Dima',       'created by migration', 'created', 'create', '', FALSE, 'dima@mail.ru',       'Дмитрий',  'Дмитриев',     2),
    ('user-006', 'User Kolya',      'created by migration', 'created', 'create', '', FALSE, 'kolya@mail.ru',      'Николай',  'Николаев',     2),
    ('user-007', 'User Vasya',      'created by migration', 'created', 'create', '', FALSE, 'vasya@mail.ru',      'Василий',  'Васильев',     2),
    ('user-008', 'User Artem',      'created by migration', 'created', 'create', '', FALSE, 'artem@mail.ru',      'Артём',    'Артёмов',      3),
    ('user-009', 'User Roman',      'created by migration', 'created', 'create', '', FALSE, 'roman@mail.ru',      'Роман',    'Романов',      3),
    ('user-010', 'User Denis',      'created by migration', 'created', 'create', '', FALSE, 'denis@mail.ru',      'Денис',    'Денисов',      3),
    ('user-011', 'User Andrey',     'created by migration', 'created', 'create', '', FALSE, 'andrey@mail.ru',     'Андрей',   'Андреев',      3),
    ('user-012', 'User Oleg',       'created by migration', 'created', 'create', '', FALSE, 'oleg@mail.ru',       'Олег',     'Олегов',       4),
    ('user-013', 'User Pavel',      'created by migration', 'created', 'create', '', FALSE, 'pavel@mail.ru',      'Павел',    'Павлов',       4),
    ('user-014', 'User Vlad',       'created by migration', 'created', 'create', '', FALSE, 'vlad@mail.ru',       'Владислав','Владов',       4),
    ('user-015', 'User Egor',       'created by migration', 'created', 'create', '', FALSE, 'egor@mail.ru',       'Егор',     'Егоров',       4),
    ('user-016', 'User Maxim',      'created by migration', 'created', 'create', '', FALSE, 'maxim@mail.ru',      'Максим',   'Максимов',     5),
    ('user-017', 'User Ilya',       'created by migration', 'created', 'create', '', FALSE, 'ilya@mail.ru',       'Илья',     'Ильин',        5),
    ('user-018', 'User Anton',      'created by migration', 'created', 'create', '', FALSE, 'anton@mail.ru',      'Антон',    'Антонов',      5),
    ('user-019', 'User Kirill',     'created by migration', 'created', 'create', '', FALSE, 'kirill@mail.ru',     'Кирилл',   'Кириллов',     5),
    ('user-020', 'User Fedor',      'created by migration', 'created', 'create', '', FALSE, 'fedor@mail.ru',      'Фёдор',    'Фёдоров',      5);

INSERT INTO forms (external_id, name, description, status, action, payload_json, deleted, space_id)
VALUES
    ('form-001', 'Form Contact',   'created by migration', 'created', 'create', '', FALSE, 1),
    ('form-002', 'Form Feedback',  'created by migration', 'created', 'create', '', FALSE, 1),
    ('form-003', 'Form Order',     'created by migration', 'created', 'create', '', FALSE, 2),
    ('form-004', 'Form Support',   'created by migration', 'created', 'create', '', FALSE, 2),
    ('form-005', 'Form Survey',    'created by migration', 'created', 'create', '', FALSE, 3),
    ('form-006', 'Form Register',  'created by migration', 'created', 'create', '', FALSE, 3),
    ('form-007', 'Form Apply',     'created by migration', 'created', 'create', '', FALSE, 4),
    ('form-008', 'Form Review',    'created by migration', 'created', 'create', '', FALSE, 4),
    ('form-009', 'Form Request',   'created by migration', 'created', 'create', '', FALSE, 5),
    ('form-010', 'Form Question',  'created by migration', 'created', 'create', '', FALSE, 5);

INSERT INTO sites (external_id, name, description, status, action, payload_json, deleted, s3_object_key)
VALUES
    ('site-001', 'Site One',   'created by migration', 'created', 'create', '', FALSE, ''),
    ('site-002', 'Site Two',   'created by migration', 'created', 'create', '', FALSE, ''),
    ('site-003', 'Site Three', 'created by migration', 'created', 'create', '', FALSE, ''),
    ('site-004', 'Site Four',  'created by migration', 'created', 'create', '', FALSE, ''),
    ('site-005', 'Site Five',  'created by migration', 'created', 'create', '', FALSE, '');