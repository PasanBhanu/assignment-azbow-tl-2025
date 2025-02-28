CREATE TABLE agent
(
    id   INT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE lead
(
    id                      INT NOT NULL,
    budget                  DECIMAL(38, 2),
    contact                 VARCHAR(255),
    follow_up_status        VARCHAR(255),
    inquiry_date            TIMESTAMP,
    name                   VARCHAR(255),
    notes                   VARCHAR(255),
    preferred_property_type VARCHAR(255),
    source                  VARCHAR(255),
    status                  TINYINT,
    agent_id                INT,
    PRIMARY KEY (id)
);

ALTER TABLE lead
    ADD CONSTRAINT FKi7o2of1rjjpu017gbnppxm6li FOREIGN KEY (agent_id) REFERENCES agent (id);

CREATE TABLE lead_seq
(
    next_val BIGINT
);

CREATE TABLE property
(
    id   INT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE property_reservation
(
    id                    INT NOT NULL,
    contract_signed       BIT,
    expected_closing_date TIMESTAMP,
    financial_status      BIT,
    legal_notes           VARCHAR(255),
    loan_amount           DECIMAL(38, 2),
    payment_plan          VARCHAR(255),
    reservation_date      TIMESTAMP,
    reservation_fee       DECIMAL(38, 2),
    lead_id               INT,
    property_id           INT,
    PRIMARY KEY (id)
);

ALTER TABLE property_reservation
    ADD CONSTRAINT FK1d1f3a568nhr20wpro4jwxlw5 FOREIGN KEY (property_id) REFERENCES property (id);

ALTER TABLE property_reservation
    ADD CONSTRAINT FK8eu73q8vxkhwtbmla5rlan3j4 FOREIGN KEY (lead_id) REFERENCES lead (id);

CREATE TABLE property_reservation_seq
(
    next_val BIGINT
);

CREATE TABLE sale
(
    id                      INT NOT NULL,
    commission_details      VARCHAR(255),
    final_sale_price        DECIMAL(38, 2),
    sale_date               TIMESTAMP,
    property_reservation_id INT,
    PRIMARY KEY (id)
);

ALTER TABLE sale
    ADD CONSTRAINT FK14hoeonm4nkkdblg49r52wi2n FOREIGN KEY (property_reservation_id) REFERENCES property_reservation (id);

CREATE TABLE sale_seq
(
    next_val BIGINT
);

CREATE TABLE "user"
(
    id       INT NOT NULL,
    name     VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255),
    username VARCHAR(255),
    PRIMARY KEY (id)
);