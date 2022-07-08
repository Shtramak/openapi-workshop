create table address
(
    volunteer_id uuid,
    city         varchar(255),
    zip_code     varchar(255),
    CONSTRAINT PK_address PRIMARY KEY (volunteer_id),
    CONSTRAINT FK_address_volunteer FOREIGN KEY (volunteer_id) REFERENCES volunteer
)