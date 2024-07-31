CREATE DATABASE IF NOT EXISTS videogameningen;

USE videogameningen;

-- DELETE FROM carts;
-- DELETE FROM cart_items;
-- DELETE FROM sessions;

-- DROP TABLE IF EXISTS cart_items,	
-- carts,
-- sessions,
-- payments,
-- order_items,
-- orders,
-- users,
-- reviews,
-- products,
-- categories;

 CREATE TABLE IF NOT EXISTS users(
   users_id BINARY(16) NOT NULL PRIMARY KEY,
   users_username VARCHAR(255),
   users_password VARCHAR(255),
   users_email VARCHAR(255),
   users_firstname VARCHAR(255),
   users_lastname VARCHAR(255),
   users_address VARCHAR(255),
   users_phonenumber VARCHAR(255),
   users_role TINYINT
);

CREATE TABLE IF NOT EXISTS categories (
 categories_id BINARY(16) NOT NULL PRIMARY KEY,
categories_description VARCHAR(255),
categories_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders(
   orders_id BINARY(16) NOT NULL PRIMARY KEY,
   orders_date DATE,
   orders_status TINYINT,
   orders_total_amount DOUBLE,
   orders_client_address VARCHAR(255),
   orders_shipping_address VARCHAR(255),
   users_id BINARY(16) NOT NULL,
   FOREIGN KEY(users_id) REFERENCES users(users_id)
);

CREATE TABLE IF NOT EXISTS payments(
   payments_id BINARY(16) NOT NULL PRIMARY KEY,
   payments_date DATE,
   payments_method VARCHAR(255),
   payments_amount DOUBLE,
   payments_transaction_id BIGINT,
   payments_status BIT(1),
   orders_id BINARY(16) NOT NULL,
   FOREIGN KEY(orders_id) REFERENCES orders(orders_id)
);

CREATE TABLE IF NOT EXISTS sessions(
   sessions_id BINARY(16) NOT NULL PRIMARY KEY,
   sessions_creation_date DATE,
   sessions_expire_date DATE,
   users_id BINARY(16) NOT NULL,
   FOREIGN KEY(users_id) REFERENCES users(users_id)
);

CREATE TABLE IF NOT EXISTS products(
   products_id BINARY(16) NOT NULL PRIMARY KEY,
   products_title VARCHAR(255),
   products_description VARCHAR(255),
   products_price DOUBLE,
   products_stock_quantity INT,
   products_release_date DATE,
   products_publisher VARCHAR(255),
   products_platform VARCHAR(255),
   products_image VARCHAR(255),
   categories_id BINARY(16) NOT NULL,
   FOREIGN KEY(categories_id) REFERENCES categories(categories_id)
);

CREATE TABLE IF NOT EXISTS order_items(
   order_items_id BINARY(16) NOT NULL PRIMARY KEY,
   order_items_quantity BIGINT,
   order_items_price DOUBLE,
   products_id BINARY(16) NOT NULL,
   orders_id BINARY(16) NOT NULL,
   FOREIGN KEY(products_id) REFERENCES products(products_id),
   FOREIGN KEY(orders_id) REFERENCES orders(orders_id)
);

CREATE TABLE IF NOT EXISTS carts(
   carts_id BINARY(16) NOT NULL PRIMARY KEY,
   carts_total_amount DOUBLE,
   sessions_id BINARY(16) NOT NULL,
   FOREIGN KEY(sessions_id) REFERENCES Sessions(sessions_id)
);

CREATE TABLE IF NOT EXISTS cart_items(
   cart_items_id BINARY(16) NOT NULL PRIMARY KEY,
   cart_items_quantity BIGINT,
   cart_items_price DOUBLE,
   products_id BINARY(16) NOT NULL,
   carts_id BINARY(16) NOT NULL,
   FOREIGN KEY(products_id) REFERENCES products(products_id),
   FOREIGN KEY(carts_id) REFERENCES carts(carts_id)
);

CREATE TABLE IF NOT EXISTS reviews(
   reviews_id BINARY(16) NOT NULL PRIMARY KEY,
   reviews_rating DOUBLE,
   reviews_comment VARCHAR(255),
   reviews_creation_date DATE,
   products_id BINARY(16) NOT NULL,
   FOREIGN KEY(products_id) REFERENCES products(products_id)
);



INSERT INTO categories (categories_id, categories_description, categories_name) VALUES
        (UNHEX(REPLACE("0da036c8-24c2-4443-9ae3-393ebaf54b05","-","")),"Jeux mettant l\'accent sur l\'action rapide et les compétences réflexives.","Action"),
        (UNHEX(REPLACE("29e4cd4d-7f51-457f-99fa-c2827ae7d758","-","")),"Jeux nécessitant la gestion de ressources et de stratégies complexes.","Stratégie"),
        (UNHEX(REPLACE("d67ec12c-656c-4266-9d03-3a4c7fcac058","-","")),"Jeux où les joueurs simulent la vie réelle ou des scénarios fictifs.","Simulation"),
        (UNHEX(REPLACE("d878656d-c3ec-43bf-9034-77ca90971807","-","")),"Jeux basés sur la résolution de puzzles ou d'énigmes.","Puzzle"),
        (UNHEX(REPLACE("59f9c6ec-ee0e-4cc9-9e27-5f696c6a7317","-","")),"Jeux mettant l\'accent sur la narration et les histoires interactives.","Aventure"),
        (UNHEX(REPLACE("559f6382-a488-4c05-82b3-65683a54c446","-","")),"Jeux de rôle où les joueurs contrôlent un personnage et évoluent dans un monde fictif.","RPG (Role-Playing Game)"),
        (UNHEX(REPLACE("03f031b1-1ea8-43cc-bc06-16be1625bbe2","-","")),"Jeux de sport simulant des activités sportives réelles.","Sport"),
        (UNHEX(REPLACE("f2ac6d8b-6c0e-438e-b0e3-6fadd659a385","-","")),"Jeux permettant aux joueurs de construire et de gérer des structures ou des environnements.","Construction"),
        (UNHEX(REPLACE("9cdcb998-a95d-4071-9793-e82af0e084b5","-","")),"Jeux de course axés sur la vitesse et la compétition.","Course"),
        (UNHEX(REPLACE("7a2ec7b5-e264-42a7-a508-3cd669bf7e4a","-","")),"Jeux de tir à la première ou à la troisième personne.","FPS (First-Person Shooter)"),
        (UNHEX(REPLACE("18e043a6-0d4f-49b1-af61-042ea6a35672","-","")),"Jeux impliquant des combats entre personnages contrôlés par les joueurs.","Combat"),
        (UNHEX(REPLACE("92eec899-9438-477b-a9eb-2a857a18ba8e","-","")),"Jeux multijoueurs massivement en ligne où les joueurs interagissent dans un monde virtuel.","MMO (Massively Multiplayer Online)"),
        (UNHEX(REPLACE("559ce0eb-a10f-4b13-92ac-20ae10597d71","-","")),"Jeux mettant l\'accent sur la créativité et l\'imagination des joueurs.","Créatif"),
        (UNHEX(REPLACE("6a177b1e-af90-4a11-b226-f414f45a6bec","-","")),"Jeux d\'horreur conçus pour effrayer et provoquer des réactions émotionnelles intenses.","Horreur"),
        (UNHEX(REPLACE("b995ea0d-d0a5-4b50-9740-c3a3b1e78cdd","-","")),"Jeux basés sur des règles et des stratégies de jeu de société traditionnels.","Jeux de société"),
        (UNHEX(REPLACE("eac21927-9ba2-4841-bca5-77f1c9064981","-","")),"Jeux impliquant des activités de danse ou de musique.","Rythmique"),
        (UNHEX(REPLACE("93a4e160-8a94-41bb-a3a5-07e3f32afb73","-","")),"Jeux mettant l\'accent sur l\'entraînement cérébral et la réflexion.","Éducatif"),
        (UNHEX(REPLACE("7a569abf-4e35-459f-bf58-0e3ad9a79be6","-","")),"Jeux impliquant des simulations de vol ou de pilotage de véhicules.","Simulation de vol"),
        (UNHEX(REPLACE("64ea2a0f-7f77-4277-8d1a-949287b06bca","-","")),"Jeux basés sur des sports de combat réels tels que le MMA, la boxe, etc.","Sport de combat"),
        (UNHEX(REPLACE("fc743727-0ffd-4406-90ec-8295c1bffdc1","-","")),"Jeux impliquant des missions secrètes et des tactiques furtives.","Infiltration"),
        (UNHEX(REPLACE("c425cfba-6599-4396-895d-0534b1d35e1c","-","")),"Jeux où le joueur contrôle et guide des personnages virtuels dans des environnements sociaux.","Simulation sociale"),
        (UNHEX(REPLACE("fa8e6285-67c1-44a7-bb26-72b280514b24","-","")),"Jeux impliquant des aventures dans l\'espace ou des scénarios de science-fiction.","Science-fiction"),
        (UNHEX(REPLACE("e2e2c4ec-7140-4a45-9fd0-d28699966009","-","")),"Jeux qui mettent l\'accent sur la gestion de ferme ou d'autres activités agricoles.","Simulation agricole"),
        (UNHEX(REPLACE("f4d71aae-8a7e-4264-9ec0-8e16009921ea","-","")),"Jeux qui impliquent la conduite et la gestion de trains.","Simulation ferroviaire"),
        (UNHEX(REPLACE("a1789eea-bd46-476b-8b01-794f40441b33","-","")),"Jeux de cartes jouables en ligne ou hors ligne.","Jeux de cartes");


INSERT INTO products(products_id, products_description, products_image, products_platform, products_publisher, products_title, products_price, products_release_date, products_stock_quantity, categories_id) VALUES
(UNHEX(REPLACE("6efcd42d-4449-4f4c-beb5-b72f8665b423","-","")),"Un jeu de tir intense avec des missions rapides et des ennemis nombreux.","bloup","PC, PlayStation 4, Xbox One","Rockstar Games","Grand Theft Auto V",19.99,"2013-09-17",150,UNHEX(REPLACE("0da036c8-24c2-4443-9ae3-393ebaf54b05","-",""))),
(UNHEX(REPLACE("ef35a653-a81b-411e-9faf-920d8f475631","-","")),"Jeux nécessitant la gestion de ressources et de stratégies complexes.","bloup","PC","Blizzard Entertainment","StarCraft II",19.99,"2010-07-27",150,UNHEX(REPLACE("29e4cd4d-7f51-457f-99fa-c2827ae7d758","-",""))),
(UNHEX(REPLACE("2c104f14-7589-4de7-b98c-6474219d0bfd","-","")),"Jeux où les joueurs simulent la vie réelle ou des scénarios fictifs.","bloup","PC","Electronic Arts","The Sims 4",19.99,"2014-09-02",150,UNHEX(REPLACE("d67ec12c-656c-4266-9d03-3a4c7fcac058","-",""))),
(UNHEX(REPLACE("12337693-7347-43a9-a99a-1f722efde747","-","")),"Jeux basés sur la résolution de puzzles ou d'énigmes.","bloup","Mobile, PC","King","Candy Crush Saga",19.99,"2012-04-12",150,UNHEX(REPLACE("d878656d-c3ec-43bf-9034-77ca90971807","-",""))),
(UNHEX(REPLACE("4d7c66bb-d59c-4db1-9112-6679d49557b0","-","")),"Jeux mettant l'accent sur la narration et les histoires interactives.","bloup","PlayStation 4","Naughty Dog","The Last of Us Part II",19.99,"2020-06-19",150,UNHEX(REPLACE("59f9c6ec-ee0e-4cc9-9e27-5f696c6a7317","-",""))),
(UNHEX(REPLACE("ae424f8c-e3d0-4d6b-b75d-153cbe9b8aac","-","")),"Jeux de rôle où les joueurs contrôlent un personnage et évoluent dans un monde fictif.","bloup","PC, PlayStation 4, Xbox One","CD Projekt","The Witcher 3: Wild Hunt",19.99,"2015-05-19",150,UNHEX(REPLACE("559f6382-a488-4c05-82b3-65683a54c446","-",""))),
(UNHEX(REPLACE("170a3328-bc6e-4b83-8854-9df281ed35ea","-","")),"Jeux de sport simulant des activités sportives réelles.","bloup","PlayStation 4, Xbox One, PC","Electronic Arts","FIFA 21",19.99,"2020-10-09",150,UNHEX(REPLACE("03f031b1-1ea8-43cc-bc06-16be1625bbe2","-",""))),
(UNHEX(REPLACE("12684c3a-4e5f-43d7-9f92-337dc5eccc99","-","")),"Jeux permettant aux joueurs de construire et de gérer des structures ou des environnements.","bloup","PC","Mojang","Minecraft",19.99,"2011-11-18",150,UNHEX(REPLACE("f2ac6d8b-6c0e-438e-b0e3-6fadd659a385","-",""))),
(UNHEX(REPLACE("4affc55c-63e0-4bc7-8dda-489139bb1e4b","-","")),"Jeux de course axés sur la vitesse et la compétition.","bloup","PlayStation 4","Polyphony Digital","Gran Turismo Sport",19.99,"2017-10-17",150,UNHEX(REPLACE("9cdcb998-a95d-4071-9793-e82af0e084b5","-",""))),
(UNHEX(REPLACE("1a07c747-0e89-4c18-8f86-599963d37f8f","-","")),"Jeux de tir à la première ou à la troisième personne.","bloup","PC, PlayStation 4, Xbox One","Activision","Call of Duty: Modern Warfare",19.99,"2019-10-25",150,UNHEX(REPLACE("7a2ec7b5-e264-42a7-a508-3cd669bf7e4a","-",""))),
(UNHEX(REPLACE("7808f033-5a5d-4915-afd9-519a7224dacd","-","")),"Jeux impliquant des combats entre personnages contrôlés par les joueurs.","bloup","PlayStation 4, Xbox One, PC","Bandai Namco","Tekken 7",19.99,"2017-06-02",150,UNHEX(REPLACE("18e043a6-0d4f-49b1-af61-042ea6a35672","-",""))),
(UNHEX(REPLACE("1c0a7d8d-107c-41e2-95bd-8f53adf49d73","-","")),"Jeux multijoueurs massivement en ligne où les joueurs interagissent dans un monde virtuel.","bloup","PC","Blizzard Entertainment","World of Warcraft",19.99,"2004-11-23",150,UNHEX(REPLACE("92eec899-9438-477b-a9eb-2a857a18ba8e","-",""))),
(UNHEX(REPLACE("b505a8df-b422-45ff-bf88-cbff522808d4","-","")),"Jeux mettant l'accent sur la créativité et l'imagination des joueurs.","bloup","PC, PlayStation 4, Xbox One","Media Molecule","Dreams",19.99,"2020-02-14",150,UNHEX(REPLACE("559ce0eb-a10f-4b13-92ac-20ae10597d71","-",""))),
(UNHEX(REPLACE("32a8fb30-488e-42d2-ab6f-05b1292986f4","-","")),"Jeux d'horreur conçus pour effrayer et provoquer des réactions émotionnelles intenses.","bloup","PC, PlayStation 4, Xbox One","Capcom","Resident Evil 7: Biohazard",19.99,"2017-01-24",150,UNHEX(REPLACE("6a177b1e-af90-4a11-b226-f414f45a6bec","-",""))),
(UNHEX(REPLACE("b4f4a65f-8789-491f-ba9b-4b27df84b059","-","")),"Jeux basés sur des règles et des stratégies de jeu de société traditionnels.","bloup","Mobile, PC","Asmodee Digital","Ticket to Ride",19.99,"2012-05-24",150,UNHEX(REPLACE("b995ea0d-d0a5-4b50-9740-c3a3b1e78cdd","-",""))),
(UNHEX(REPLACE("ab0f7a00-d00b-4b9a-a092-d2ab559a9056","-","")),"Jeux impliquant des activités de danse ou de musique.","bloup","PlayStation 4, Xbox One, Nintendo Switch","Ubisoft","Just Dance 2021",19.99,"2020-11-12",150,UNHEX(REPLACE("eac21927-9ba2-4841-bca5-77f1c9064981","-",""))),
(UNHEX(REPLACE("0547effc-77da-4bfa-b08b-14e93bc7465f","-","")),"Jeux mettant l'accent sur l'entraînement cérébral et la réflexion.","bloup","Nintendo DS","Nintendo","Brain Age: Train Your Brain in Minutes a Day!",19.99,"2005-05-19",150,UNHEX(REPLACE("93a4e160-8a94-41bb-a3a5-07e3f32afb73","-",""))),
(UNHEX(REPLACE("cd001106-459e-46d3-bf47-06030b3876c3","-","")),"Jeux impliquant des simulations de vol ou de pilotage de véhicules.","bloup","PC","Microsoft","Microsoft Flight Simulator",19.99,"2020-08-18",150,UNHEX(REPLACE("7a569abf-4e35-459f-bf58-0e3ad9a79be6","-",""))),
(UNHEX(REPLACE("b3b30ff4-ef5c-4daf-94d2-744dbe1043a6","-","")),"Jeux basés sur des sports de combat réels tels que le MMA, la boxe, etc.","bloup","PlayStation 4, Xbox One, PC","Electronic Arts","EA Sports UFC 4",19.99,"2020-08-14",150,UNHEX(REPLACE("64ea2a0f-7f77-4277-8d1a-949287b06bca","-",""))),
(UNHEX(REPLACE("289fc633-5912-40a9-8de7-0a1be1b2934a","-","")),"Jeux impliquant des missions secrètes et des tactiques furtives.","bloup","PlayStation 4, Xbox One, PC","Ubisoft","Assassin's Creed Valhalla",19.99,"2020-11-10",150,UNHEX(REPLACE("fc743727-0ffd-4406-90ec-8295c1bffdc1","-",""))),
(UNHEX(REPLACE("4bf51315-d5db-4eda-8009-afd5c0144767","-","")),"Jeux où le joueur contrôle et guide des personnages virtuels dans des environnements sociaux.","bloup","Nintendo Switch","Nintendo","Animal Crossing: New Horizons",19.99,"2020-03-20",150,UNHEX(REPLACE("c425cfba-6599-4396-895d-0534b1d35e1c","-",""))),
(UNHEX(REPLACE("0b7af1de-3ab3-45fd-bf2b-cec244109891","-","")),"Jeux impliquant des aventures dans l'espace ou des scénarios de science-fiction.","bloup","PC, PlayStation 4, Xbox One","BioWare","Mass Effect 2",19.99,"2010-01-26",150,UNHEX(REPLACE("fa8e6285-67c1-44a7-bb26-72b280514b24","-",""))),
(UNHEX(REPLACE("50a61038-3a66-48ce-b701-fe0734dfa823","-","")),"Jeux qui mettent l'accent sur la gestion de ferme ou d'autres activités agricoles.","bloup","PC, PlayStation 4, Xbox One","Giants Software","Farming Simulator 19",19.99,"2018-11-20",150,UNHEX(REPLACE("e2e2c4ec-7140-4a45-9fd0-d28699966009","-",""))),
(UNHEX(REPLACE("c8000079-a8b0-4429-a688-4be72494719a","-","")),"Jeux qui impliquent la conduite et la gestion de trains.","bloup","PC","Dovetail Games","Train Sim World 2",19.99,"2020-08-20",150,UNHEX(REPLACE("f4d71aae-8a7e-4264-9ec0-8e16009921ea","-",""))),
(UNHEX(REPLACE("0cf70341-9de6-41c3-af61-38913191895c","-","")),"Jeux de cartes jouables en ligne ou hors ligne.","bloup","PC, Mobile","Blizzard Entertainment","Hearthstone",19.99,"2014-03-11",150,UNHEX(REPLACE("a1789eea-bd46-476b-8b01-794f40441b33","-","")));
    
INSERT INTO users(users_id, users_username, users_password, users_email,users_firstname,users_lastname,users_address, users_role,users_phonenumber) VALUES
  (UNHEX(REPLACE("00000000-0000-0000-0000-000000000000","-","")),null,
  null,null,null,null,
    null,
    0,
  null),
    (UNHEX(REPLACE("00000000-0000-0000-0000-000000000001","-","")),"24rue stephenson 59100 Roubaix",
  "sakhrin6@gmail.com","Nassim","Sakhri","$2a$10$ShKbdHqzwepAgXqGENZknuyTbXdauCgvYLbApryKGjJOLQadJYjWq",
    "0767634422",
    1,
  "ratchetandclank12")
  ;
  
  
  
 

  
  

  
