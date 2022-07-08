CREATE TABLE bank_account
(
    id           uuid,
    volunteer_id uuid NOT NULL,
    bank_name       VARCHAR(255),
    number       VARCHAR(255),
    CONSTRAINT PK_bank_account PRIMARY KEY (id),
    CONSTRAINT FK_bank_account_volunteer FOREIGN KEY (volunteer_id) REFERENCES volunteer
)