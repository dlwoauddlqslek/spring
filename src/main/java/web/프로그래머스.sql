/*
    프로그래머스 sql 문제
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
    

*/