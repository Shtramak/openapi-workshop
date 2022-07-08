create table volunteer
(
    id               uuid,
    date_of_creation date,
    name             varchar(255),
    CONSTRAINT PK_volunteer primary key (id),
    CONSTRAINT UQ_volunteer_name UNIQUE (name)
)