INSERT INTO SHOPS(NAME, OPEN, MIN_ORDER_AMOUNT, COMMISSION_RATE) VALUES('롯데리아(강남점)', true, 13000, 0.01);

INSERT INTO MENUS(SHOP_ID, FOOD_NAME, FOOD_DESCRIPTION) VALUES(1, '한우불고기 버거 세트', '버거 + 포테이토 세트 + 콜라 + 기타');

INSERT INTO OPTION_GROUP_SPECS(MENU_ID, NAME, EXCLUSIVE, BASIC) VALUES(1, '기본', true, true);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(1, '소(250g)', 12000);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(1, '중(400g)', 16000);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(1, '대(600g)', 20000);

INSERT INTO OPTION_GROUP_SPECS(MENU_ID, NAME, EXCLUSIVE, BASIC) VALUES(1, '맛선택', true, false);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(2, '일반 맛', 0);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(2, '매콤 맛', 1000);

INSERT INTO OPTION_GROUP_SPECS(MENU_ID, NAME, EXCLUSIVE, BASIC) VALUES(1, '추가선택', false, false);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(3, '치즈스틱 추가', 1000);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(3, '양념감자 변경', 2000);
INSERT INTO OPTION_SPECS(OPTION_GROUP_SPEC_ID, NAME, PRICE) VALUES(3, '핫윙 2조각 추가', 3000);