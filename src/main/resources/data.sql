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
    (1, 'Mercedes-Benz SLK', 'asdasd', 'Gasoline', '/images/car-1.jpg', 5, 10000, 500, 5, 'Automatic', 'Coupe'),
    (2, 'BMW 5 Series', 'asdasd', 'Gasoline', '/images/car-5.jpg', 4, 5000, 1000, 4, 'Automatic', 'Sedan'),
    (3, 'Range Rover', 'asdasd', 'Hybrid', '/images/car-2.jpg', 6, 20000, 2000, 6, 'Automatic', 'SUV'),
    (4, 'Audi Q2', 'asd', 'Diesel','/images/car-12.jpg', 4, 50000, 400, 5, 'Manual', 'SUV'),
    (5, 'MckLaren', 'asdasd', 'Gasoline','/images/car-3.jpg', 4, 50000, 400, 5, 'Manual', 'Coupe'),
    (6, 'Ford Mustang', 'asd', 'Gasoline','/images/car-4.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe'),
    (7, 'Alfa Romeo', 'asd', 'DIESEL','/images/car-6.jpg', 4, 50000, 400, 5, 'Manual', 'Coupe'),
    (8, 'Mercedes Benz E Class', 'asd', 'DIESEL','/images/car-7.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe'),
    (10, 'Mercedes Benz AMG GT', 'asd', 'Gasoline','/images/car-9.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe'),
    (11, 'Jeep', 'asd', 'Gasoline','/images/car-8.jpg', 4, 50000, 400, 5, 'Automatic', 'SUV'),
    (12, 'Mercedes Benz B Class', 'asd', 'DIESEL','/images/car-11.jpg', 4, 50000, 400, 5, 'Manual', 'Sedan'),
    (13, 'Mercedes Benz Amg GT Black Series', 'asd', 'Gasoline','/images/car-10.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe')
