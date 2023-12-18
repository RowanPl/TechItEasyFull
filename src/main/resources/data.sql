-- Televisions


INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality,
                         smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES
    (nextval('televisions_id_seq'), 'NH3216SMART', 'Nikkei', 'HD smart TV', 159, 32, 100, 'LED', 'HD ready',  true, true, false, false, false, false, 235885, 45896),
    (nextval('televisions_id_seq'), '43PUS6504/12/L', 'Philips', '4K UHD LED Smart Tv', 379, 43, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 8569452, 5685489),
    (nextval('televisions_id_seq'), '43PUS6504/12/M', 'Philips', '4K UHD LED Smart Tv', 379, 50, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 345549, 244486),
    (nextval('televisions_id_seq'), '43PUS6504/12/S', 'Philips', '4K UHD LED Smart Tv', 379, 58, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 6548945, 4485741),
    (nextval('televisions_id_seq'), 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989, 55, 100, 'OLED', 'ULTRA HD',  true, true, true, true, true, false, 50000, 20500),
    (nextval('televisions_id_seq'), 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989, 55, 100, 'OLED', 'ULTRA HD',  true, true, true, true, true, false, 50000, 20500);


-- Remote Controllers
INSERT INTO remote_controllers(id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES
    (nextval('remote_controllers_id_seq'), 'Philips', 'AAA', 'PH10103', 'Philips', 22.99, 2),
    (nextval('remote_controllers_id_seq'), 'LG', 'AAA', 'LG1130', 'LG', 28.39, 8);


-- CI-Modules
INSERT INTO ci_modules(id, name, type, price)
VALUES
    (nextval('ci_modules_id_seq'), 'universal CI-module', 'ZZXT300', 89.99);


-- Wall Brackets
INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES
    (1001, '25X32', false, 'LG bracket', 32.23),
    (1002, '25X32/32X40', true, 'LG bracket', 32.23),
    (1003, '25X25', false, 'Philips bracket', 32.23),
    (1004, '25X32/32X40', true, 'Nikkei bracket', 32.23),
    (1005, '25X32', false, 'Nikkei bracket', 32.23);

INSERT INTO television_wall_bracket (television_id, wall_bracket_id)
VALUES
    (1, 1001),
    (1, 1002),
    (2, 1001),
    (3, 1004),
    (4, 1005);
