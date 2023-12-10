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

insert into cars (id, brand, description, fuel, image_url, luggage, mileage, price_for_day, seats, transmission, type, price_for_fuel_surcharges, price_for_hour, price_for_month)
values
    (1, 'Mercedes-Benz SLK', 'The Mercedes-Benz SLK, a compact luxury roadster, embodies the perfect fusion of style, performance, and advanced technology. Renowned for its sleek design and iconic retractable hardtop roof, the SLK offers an exhilarating driving experience combined with the elegance expected from the Mercedes-Benz brand. With precision engineering, cutting-edge safety features, and a luxurious interior, the SLK represents the epitome of open-top driving, seamlessly blending power and sophistication for those who appreciate the finer things in automotive craftsmanship.', 'Gasoline', '/images/car-1.jpg', 5, 10000, 500, 5, 'Automatic', 'Coupe', 3, 10, 1000),
    (2, 'BMW 5 Series', 'The BMW 5 Series is a quintessential executive sedan that sets the standard for luxury, performance, and innovation. Renowned for its dynamic driving capabilities, elegant design, and cutting-edge technology, the 5 Series exemplifies the epitome of a premium, midsize luxury car. With a sophisticated and spacious interior, meticulously crafted materials, and an array of advanced features, the 5 Series provides a seamless blend of comfort and performance. Whether navigating city streets or cruising on the highway, the BMW 5 Series delivers a refined and exhilarating drivin, making it a top choice for those seeking the perfect balance between style, agility, and modernity in a luxury automobile.g experience', 'Gasoline', '/images/car-5.jpg', 4, 5000, 1000, 4, 'Automatic', 'Sedan', 3, 10, 1000),
    (3, 'Range Rover', 'The Range Rover is an icon of luxury and versatility, offering a harmonious blend of opulence, off-road capability, and cutting-edge technology. Renowned for its distinctive design, the Range Rover stands as a symbol of refined elegance with its sleek lines, floating roof, and signature grille. Beyond its captivating exterior, the interior of the Range Rover is a haven of comfort and craftsmanship, adorned with premium materials and equipped with state-of-the-art features.', 'Hybrid', '/images/car-2.jpg', 6, 20000, 2000, 6, 'Automatic', 'SUV', 3, 10, 1000),
    (4, 'Audi Q2', 'The Audi Q2 is a dynamic and compact SUV that exudes a sporty character and modern design. This compact crossover seamlessly combines urban agility with a robust and distinctive presence. Its sharp lines, prominent grille, and stylish LED headlights contribute to an eye-catching exterior that reflects Audi''s commitment to contemporary aesthetics.', 'Diesel','/images/car-12.jpg', 4, 50000, 400, 5, 'Manual', 'SUV', 3, 10, 1000),
    (5, 'MckLaren', 'The McLaren brand represents the pinnacle of automotive performance and engineering excellence. One of their notable models, the McLaren (specific model), embodies the essence of cutting-edge technology, aerodynamic prowess, and track-ready capabilities.', 'Gasoline','/images/car-3.jpg', 4, 50000, 400, 5, 'Manual', 'Coupe', 3, 10, 1000),
    (6, 'Ford Mustang', 'The Ford Mustang, an iconic symbol of American muscle and performance, continues to captivate automotive enthusiasts with its legendary design and exhilarating driving experience. From its unmistakable exterior, characterized by a bold and aggressive stance, to its distinctive front grille and iconic pony logo, the Ford Mustang is a timeless representation of raw power and classic styling. The muscular contours and aerodynamic lines pay homage to its heritage while signaling a modern, dynamic presence on the road.', 'Gasoline','/images/car-4.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe', 3, 10, 1000),
    (7, 'Alfa Romeo', 'The Alfa Romeo, an automotive masterpiece synonymous with Italian craftsmanship and passion, represents a perfect blend of style, performance, and heritage. Distinguished by its striking design, an Alfa Romeo is an artful expression on wheels. Characterized by a signature V-shaped front grille, sleek curves, and captivating lines, an Alfa Romeo exudes an aura of sophistication and elegance. Each model is a testament to the brand''s commitment to creating vehicles that are as visually stunning as they are exhilarating to drive.', 'DIESEL','/images/car-6.jpg', 4, 50000, 400, 5, 'Manual', 'Coupe', 3, 10, 1000),
    (8, 'Mercedes E Class', 'The Mercedes-Benz E-Class, a paragon of automotive excellence, seamlessly marries luxury, innovation, and performance to create a driving experience that transcends expectations. Draped in an exterior that exudes sophistication and modernity, the E-Class showcases the iconic Mercedes-Benz design language. Its sleek profile, sculpted lines, and distinctive front fascia project an aura of elegance and authority on the road. Every detail, from the iconic grille to the exquisite LED lighting, reflects the meticulous craftsmanship that defines the Mercedes-Benz brand.', 'DIESEL','/images/car-7.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe', 3, 10, 1000),
    (10, 'Mercedes AMG GT', 'The Mercedes-AMG GT, a marvel of automotive engineering, embodies the spirit of high-performance luxury in a striking and athletic package. At first glance, the AMG GT captivates with its sleek and aerodynamic design, a perfect blend of power and sophistication. The distinctive "Panamericana" grille, sculpted lines, and muscular proportions hint at the extraordinary performance capabilities that lie beneath the surface. Every curve and contour serves a purpose, contributing to both aesthetics and aerodynamics, making the AMG GT a visual masterpiece on the road.', 'Gasoline','/images/car-9.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe', 3, 10, 1000),
    (11, 'Jeep', 'The Jeep brand, synonymous with rugged capability and off-road prowess, has a rich history rooted in adventure and exploration. Known for creating iconic vehicles that can traverse challenging terrains with ease, Jeep has become a symbol of freedom and versatility.', 'Gasoline','/images/car-8.jpg', 4, 50000, 400, 5, 'Automatic', 'SUV', 3, 10, 1000),
    (12, 'Mercedes Benz B Class', 'The Mercedes-Benz B-Class is a premium compact car that seamlessly combines versatility, luxury, and advanced technology. Designed with a focus on practicality and modern aesthetics, the B-Class offers a distinctive driving experience within the luxury hatchback segment.', 'DIESEL','/images/car-11.jpg', 4, 50000, 400, 5, 'Manual', 'Sedan', 3, 10, 1000),
    (13, 'Mercedes Benz Amg GT Black Series', 'The Mercedes-AMG GT Black Series stands as the pinnacle of performance and precision within the AMG GT lineup, showcasing an extraordinary combination of power, aerodynamics, and track-focused engineering. This high-performance sports car is crafted for enthusiasts who demand the ultimate driving experience and seek the thrill of the race track in a road-legal machine.', 'Gasoline','/images/car-10.jpg', 4, 50000, 400, 5, 'Automatic', 'Coupe', 3, 10, 1000);

insert into blogs (id, description_first_title, image_url, title, second_title, description_second_title)
values
    (1, 'Embark on an exciting adventure with our first blog post, offering valuable insights and expert tips on navigating the realm of luxury car rentals. Discover the keys to unlocking a world of extraordinary experiences as we guide you through the process of choosing, renting, and savoring the unmatched thrill of driving top-tier vehicles. Join us on this journey where every page is filled with inspiration, expertise, and the promise of unforgettable road trips. Welcome to the First Blog of our Rent a Car website – where driving dreams become a reality.', '/images/image_1.jpg', 'Driving Dreams: Your First Journey into the World of Renting Exotic Cars', 'Unleashing Luxury: Your Roadmap to Premier Car Experiences', 'Embark on a journey into the world of opulence and performance with our first blog. Discover the epitome of luxury as we unveil the secrets behind an extraordinary car rental experience. From high-end models to unparalleled service, join us in exploring the road to unparalleled driving sensations.'),
    (2, 'Discover the epitome of luxury and performance as we delve into the world of high-end car rentals in our second blog. From sleek sports cars to sophisticated sedans, we explore the latest trends that redefine the experience of driving in style. Get ready to embark on a journey where elegance meets the open road, offering a unique perspective on luxury travel.', '/images/image_2.jpg', 'Driving in Style: Exploring the Latest Trends in Luxury Car Rentals', 'Unveiling the Pinnacle of Automotive Excellence: A Journey into the World of Premium Car Rentals', 'Embark on a thrilling exploration of luxury and performance as we delve into the exquisite realm of premium car rentals. Discover the epitome of automotive elegance and power, where every drive becomes a remarkable experience. From sleek sedans to powerful SUVs, our second blog unveils the diverse selection that awaits you, promising a journey beyond the ordinary in the world of elite automotive experiences.'),
    (3, 'Embark on a journey of sophistication and thrill as we explore the most enchanting destinations with our luxury car rentals. From scenic coastal drives to picturesque mountain retreats, discover the perfect blend of elegance and excitement. Join us in this blog to uncover the allure of traveling in style and experiencing the road like never before.', '/images/image_3.jpg', 'Top Driving Destinations: Unleashing Adventure with Luxury Car Rentals', 'Unveiling the Extraordinary: Luxury Car Rentals Redefining Your Travel Experience', 'Embark on a journey of sophistication and power with our meticulously curated selection of luxury cars. Our third blog dives into the realm of opulence, exploring the unique features and captivating stories behind each exceptional vehicle. Discover the epitome of automotive excellence and elevate your driving experience to unprecedented heights.'),
    (4, 'Explore the latest breakthroughs and innovations in the automotive industry. From autonomous driving to smart connectivity, discover how cutting-edge technologies are transforming the way we drive and interact with our vehicles. Stay ahead of the curve and dive into the future of modern cars with our insightful exploration.', '/images/image_4.jpg', 'Unveiling the Future: Cutting-Edge Technologies in Modern Cars', 'Navigating the Future: A Journey into the Technological Marvels Reshaping the Automotive Landscape', 'Embark on a riveting exploration through our fourth blog as we delve into the dynamic realm of automotive innovation and cutting-edge technologies. Discover the latest advancements that are reshaping the driving experience, from smart connectivity to sustainable solutions. Join us in unraveling the exciting tapestry of the automotive future.'),
    (5, 'Embark on a journey through the ever-evolving landscape of the automotive world with our fifth blog. Discover the latest trends, technological innovations, and exciting transformations shaping the future of driving. Stay informed and inspired as we explore the cutting-edge developments that are revolutionizing the way we experience automobiles.', '/images/image_5.jpg', 'Driving into the Future: Unveiling Automotive Trends and Transformations', 'Navigating the Horizon: Unveiling the Future of Automotive Excellence', 'Embark on an exhilarating journey as we delve into the world of cutting-edge automotive innovation. From the latest technology trends to the sleekest designs, discover the driving force behind the future of mobility. Get ready to explore the evolution of luxury, performance, and sustainability in the automotive industry. Join us on this captivating ride, where each blog post unveils the next chapter in the ongoing saga of automotive excellence.');

