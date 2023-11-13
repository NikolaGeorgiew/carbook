insert into users (id,username,email,password)
values
    (1,'admin','admin@admin.com','$2a$10$7LQTn4O1c8U/QujZWPn6weuHi2TgWpVHcrK1XHEhPzyNxEfOwd.J2'),
    (2,'user','user@user.com', '$2a$10$7LQTn4O1c8U/QujZWPn6weuHi2TgWpVHcrK1XHEhPzyNxEfOwd.J2');

insert into roles (id,role)
values (1,'ADMIN'),
       (2,'USER');

insert into users_roles (user_id, role_id)
values (1,1),
       (1,2),
       (2,2);