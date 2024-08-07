/*
    프로그래머스 sql 문제
    1일차
    1.
    SELECT flavor from first_half order by total_order;
    2.
    SELECT * from animal_ins order by animal_id
    3.
    SELECT name,datetime from animal_ins order by animal_id desc
    4.
    SELECT animal_id,name from animal_ins order by animal_id
    5.
    SELECT animal_id,name,datetime from animal_ins order by name, datetime desc
    6.
    SELECT first_half.flavor from first_half inner join icecream_info on first_half.flavor=icecream_info.flavor where total_order>3000 and ingredient_type="fruit_based" order by total_order desc;
    8.
    SELECT factory_id, factory_name, address from food_factory where address like '%강원도%' order by factory_id;
    9.
    SELECT animal_id, name from animal_ins where intake_condition='sick' order by animal_id;
    10.
    SELECT animal_id, name from animal_ins where intake_condition!="aged" order by animal_id;
    11.
    SELECT name from animal_ins order by datetime limit 1;
    12.
    select id, email, first_name, last_name from developer_infos where skill_1='Python' or skill_2='Python' or skill_3='Python' order by id;

    2일차
    1.
    SELECT max(price) as MAX_PRICE from product;
    2.
    SELECT max(datetime) from animal_ins;
    3.
    select * from food_product where price=(select max(price) from food_product);
    4.
    SELECT min(datetime) from animal_ins;
    5.
    SELECT count(animal_id) from animal_ins;
    6.
    SELECT count(distinct name) from animal_ins;
    7.
    select sum(price) as TOTAL_PRICE from item_info where rarity="legend";
    8.
    SELECT count(user_id) as USERS from user_info where date_format(joined,'%Y')="2021" and age between 20 and 29;
    9.
    select count(*) as FISH_COUNT from fish_info where length is null;
    10.
    select count(id) as COUNT from ecoli_data where (conv(genotype,10,2) like '%1' or conv(genotype,10,2) like '%100' ) and conv(genotype,10,2) not like '%11';
    11.
    SELECT car_type,count(car_id) as CARS from car_rental_company_car where options like '%통풍시트%' or options like '%열선시트%' or options like '%가죽시트%' group by car_type order by car_type;
    12.
    SELECT ingredient_type, sum(total_order) as TOTAL_ORDER from first_half a inner join icecream_info b on a.flavor=b.flavor group by ingredient_type order by TOTAL_ORDER;
    13.
    SELECT mcdp_cd as 진료과코드, count(apnt_ymd) as 5월예약건수 from appointment where date_format(apnt_ymd,'%m')='05' group by 진료과코드 order by 5월예약건수,진료과코드;
    14.
    SELECT animal_type,count(animal_id) as count from animal_ins group by animal_type order by animal_type;
    15.
    SELECT NAME, count(name) as COUNT from animal_ins group by NAME having COUNT>=2 order by NAME asc;
    16.
    select sum(score) as SCORE, a.emp_no, a.emp_name, position, email from hr_employees a inner join hr_grade b on a.emp_no=b.emp_no group by emp_no order by SCORE desc limit 1;
    17.
    select count(a.fish_type) as FISH_COUNT, fish_name from fish_info a inner join fish_name_info b on a.fish_type=b.fish_type group by fish_name order by FISH_COUNT desc;
    18.
    SELECT product_code, sum(price*sales_amount) as SALES from product a inner join offline_sale b on a.product_id=b.product_id group by product_code order by SALES desc, product_code;

*/