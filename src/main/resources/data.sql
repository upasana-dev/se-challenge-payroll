INSERT INTO employee (id, business_id) VALUES (1000, 1);
INSERT INTO employee (id, business_id) VALUES (1001, 2);

INSERT INTO employee_effort (id, effort_date, hours_worked, wage_group, employee_id) VALUES (2000, '2020-01-04', 10,'A', 1000);
INSERT INTO employee_effort (id, effort_date, hours_worked, wage_group, employee_id) VALUES (2001, '2020-01-15', 15,'A', 1000);

INSERT INTO employee_effort (id, effort_date, hours_worked, wage_group, employee_id) VALUES (2002, '2020-02-18', 15,'B', 1000);

INSERT INTO employee_effort (id, effort_date, hours_worked, wage_group, employee_id) VALUES (2003, '2019-07-18', 34,'B', 1001);