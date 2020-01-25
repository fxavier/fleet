CREATE TABLE work_order(
id BIGSERIAL NOT NULL,
number VARCHAR(50),
date_scheduled DATE,
date_due DATE,
date_started DATE,
date_completed DATE,
equipment_id BIGINT NOT NULL,
customer_id BIGINT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_wo_equipment
  FOREIGN KEY(equipment_id) REFERENCES equipment(id),
CONSTRAINT fk_wo_customer
  FOREIGN KEY(customer_id) REFERENCES customer(id)
);

CREATE TABLE purchase_order(
id BIGSERIAL NOT NULL,
number VARCHAR(50) NOT NULL,
date_issued DATE NOT NULL,
work_order_id BIGINT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_po_wo
  FOREIGN KEY(work_order_id) REFERENCES work_order(id)
);

CREATE TABLE purchase_order_item(
purchase_order_id BIGINT NOT NULL,
part_id BIGINT NOT NULL,
quantity INTEGER NOT NULL,
price NUMERIC(10, 2) NOT NULL,
PRIMARY KEY(purchase_order_id, part_id),
CONSTRAINT fk_po_item
  FOREIGN KEY(purchase_order_id) REFERENCES purchase_order(id),
CONSTRAINT fk_po_part
  FOREIGN KEY(part_id) REFERENCES part(id)
);
