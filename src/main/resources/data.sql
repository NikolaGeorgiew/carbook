insert into users (id,username,email,password)
values
    (1,'admin','admin@admin.com', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151'),
    (2,'user','user@user.com', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');

insert into roles (id,role)
values (1,'ADMIN'),
       (2,'USER');

insert into users_roles (user_id, role_id)
values (1,1),
       (1,2),
       (2,2);

insert into cars (id, brand, description, fuel, image_url, luggage, mileage, price_for_day, seats, transmission, type)
values
    (1, 'Mercedes-Benz', 'asdasd', 'GASOLINE', '/images/car-1.jpg', 5, 10000, 500, 5, 'AUTOMATIC', 'COUPE'),
    (2, 'BMW', 'asdasd', 'GASOLINE', '/images/car-5.jpg', 4, 5000, 1000, 4, 'AUTOMATIC', 'SEDAN'),
    (3, 'Range Rover', 'asdasd', 'HYBRID', '/images/car-2.jpg', 6, 20000, 2000, 6, 'AUTOMATIC', 'SUV')
