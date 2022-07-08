create table currency_amount
(
    account_id          uuid,
    currency_amount_key varchar(255) not null,
    currency_amount     decimal(19, 2),
    CONSTRAINT PK_currency_amont PRIMARY KEY (account_id, currency_amount_key),
    CONSTRAINT FK_currency_amount FOREIGN KEY (account_id) REFERENCES bank_account
)