
CREATE TABLE if not exists customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(30) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           phonenumber VARCHAR(20) UNIQUE NOT NULL CHECK (phonenumber REGEXP '^[0-9+()-]{6,20}$'),
                           address VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists rooms (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       type ENUM('STANDARD', 'LUX', 'SWEET') NOT NULL DEFAULT 'STANDARD',
                       status ENUM('RESERVED', 'AVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
                       price DECIMAL(10,2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE if not exists bookings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          customer_id BIGINT NOT NULL,
                          room_id BIGINT NOT NULL,
                          booking_date DATE NOT NULL,
                          check_in_date DATE NOT NULL,
                          check_out_date DATE NOT NULL,
                          CHECK (check_out_date > check_in_date),
                          FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
                          FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

CREATE TABLE if not exists payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          booking_id BIGINT NOT NULL UNIQUE,
                          amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
                          payment_method ENUM('CASH', 'CREDIT_CARD', 'DEBIT_CARD', 'ONLINE') NOT NULL,
                          FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE
);

CREATE TABLE if not exists additional_services (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL UNIQUE,
                                     price DECIMAL(10,2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE if not exists booking_services (
                                  booking_id BIGINT NOT NULL,
                                  service_id BIGINT NOT NULL,
                                  PRIMARY KEY (booking_id, service_id),
                                  FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE ,
                                  FOREIGN KEY (service_id) REFERENCES additional_services(id) ON DELETE CASCADE
);
