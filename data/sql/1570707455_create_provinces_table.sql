
-- +migrate Up
-- SQL in section 'Up' is executed when this migration is applied
CREATE TABLE IF NOT EXISTS provinces (
  "id" BIGINT NOT NULL PRIMARY KEY,
  "name" VARCHAR NOT NULL,
  "alt_name" VARCHAR NOT NULL DEFAULT '',
  "latitude" DOUBLE PRECISION NOT NULL DEFAULT 0,
  "longitude" DOUBLE PRECISION NOT NULL DEFAULT 0
);

INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (11, 'ACEH', 'ACEH', 4.368550, 97.025300);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (12, 'SUMATERA UTARA', 'SUMATERA UTARA', 2.192350, 99.381220);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (13, 'SUMATERA BARAT', 'SUMATERA BARAT', -1.342250, 100.076100);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (14, 'RIAU', 'RIAU', 0.500410, 101.547580);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (15, 'JAMBI', 'JAMBI', -1.611570, 102.779700);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (16, 'SUMATERA SELATAN', 'SUMATERA SELATAN', -3.126680, 104.093060);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (17, 'BENGKULU', 'BENGKULU', -3.518680, 102.535980);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (18, 'LAMPUNG', 'LAMPUNG', -4.855500, 105.027300);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (19, 'KEPULAUAN BANGKA BELITUNG', 'KEPULAUAN BANGKA BELITUNG', -2.757750, 107.583940);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (21, 'KEPULAUAN RIAU', 'KEPULAUAN RIAU', -0.154780, 104.580370);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (31, 'DKI JAKARTA', 'DKI JAKARTA', 6.174500, 106.822700);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (32, 'JAWA BARAT', 'JAWA BARAT', -6.889170, 107.640470);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (33, 'JAWA TENGAH', 'JAWA TENGAH', -7.303240, 110.004410);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (34, 'DI YOGYAKARTA', 'DI YOGYAKARTA', 7.795600, 110.369500);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (35, 'JAWA TIMUR', 'JAWA TIMUR', -6.968510, 113.980050);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (36, 'BANTEN', 'BANTEN', -6.445380, 106.137560);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (51, 'BALI', 'BALI', -8.235660, 115.122390);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (52, 'NUSA TENGGARA BARAT', 'NUSA TENGGARA BARAT', -8.121790, 117.636960);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (53, 'NUSA TENGGARA TIMUR', 'NUSA TENGGARA TIMUR', -8.565680, 120.697860);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (61, 'KALIMANTAN BARAT', 'KALIMANTAN BARAT', -0.132240, 111.096890);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (62, 'KALIMANTAN TENGAH', 'KALIMANTAN TENGAH', -1.499580, 113.290330);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (63, 'KALIMANTAN SELATAN', 'KALIMANTAN SELATAN', -2.943480, 115.375650);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (64, 'KALIMANTAN TIMUR', 'KALIMANTAN TIMUR', 0.788440, 116.242000);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (65, 'KALIMANTAN UTARA', 'KALIMANTAN UTARA', 2.725940, 116.911000);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (71, 'SULAWESI UTARA', 'SULAWESI UTARA', 0.655570, 124.090150);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (72, 'SULAWESI TENGAH', 'SULAWESI TENGAH', -1.693780, 120.808860);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (73, 'SULAWESI SELATAN', 'SULAWESI SELATAN', -3.644670, 119.947190);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (74, 'SULAWESI TENGGARA', 'SULAWESI TENGGARA', -3.549120, 121.727960);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (75, 'GORONTALO', 'GORONTALO', 0.718620, 122.455590);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (76, 'SULAWESI BARAT', 'SULAWESI BARAT', -2.497450, 119.391900);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (81, 'MALUKU', 'MALUKU', -3.118840, 129.420780);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (82, 'MALUKU UTARA', 'MALUKU UTARA', 0.630120, 127.972020);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (91, 'PAPUA BARAT', 'PAPUA BARAT', -1.384240, 132.902530);
INSERT INTO provinces (id, name, alt_name, latitude, longitude) VALUES (94, 'PAPUA', 'PAPUA', -3.988570, 138.348530);

-- +migrate Down
-- SQL section 'Down' is executed when this migration is rolled back
DROP TABLE IF EXISTS provinces;

