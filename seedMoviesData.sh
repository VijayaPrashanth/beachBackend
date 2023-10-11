#!/bin/bash
MOVIE_IDS=('m101' 'm102' 'm103' 'm104' 'm105' 'm106' 'm107' 'm108' 'm109' 'm110' 'm111' 'm112' 'm113' 'm114' 'm115')

OS_TYPE=$(uname -s)

seed_movie_details(){

  PGPASSWORD='beach' psql -h localhost -U moviebooking -d moviebooking -p '5436' -qc\
" insert into movie(id,name,rating,genre,running_time_in_mins,image_url) values \
('m101','The Shawshank Redemption','9.3','Drama/Crime','142','https://cdn.shopify.com/s/files/1/0057/3728/3618/products/9f22e23817c4accbf052e0f91a2b7821_156f8e4f-814c-4dcb-896d-0b077053cd51_500x749.jpg'),\
('m102','The Shining','8.4','Horror/Mystery','146','https://www.movieposters.com/cdn/shop/products/f84f229657c9968473f0af72ab92cfec_7c3f9528-67d7-413c-b021-7638732546f7_480x.progressive.jpg'),\
('m103','Pulp fiction','8.9','Drama','154','https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg'),\
('m104','Interstellar','8.7','Sci-fi','169','https://www.movieposters.com/cdn/shop/products/interstellar5_f83e2c82-4b9c-4f59-86c1-895fff2b1935_480x.progressive.jpg'),\
('m105','Goodfellas','8.7','Crime/Drama','146','https://www.movieposters.com/cdn/shop/products/b8e760c3cedffdf72efb7bef4f6ffe4e_b5778591-6559-4666-855e-3f054fbab27d_480x.progressive.jpg'),\
('m106','se7en','8.6','Mystery/Drama','127','https://www.movieposters.com/cdn/shop/products/248875c05ff4754eb10c861c0f21c42d_5be29f59-c841-4941-ae55-3dff98e364dc_480x.progressive.jpg'),\
('m107','Seven samurai','8.6','Action/Drama','207','https://mir-s3-cdn-cf.behance.net/project_modules/1400/08e53493505467.5e68bf553af0e.jpg'),\
('m108','The Godfather','9.2','Crime/Drama','175','https://www.movieposters.com/cdn/shop/products/f0fa236e71deed3e86221019c88f4231_4b5f1500-ab9b-48e9-a717-9a42935be2d9_480x.progressive.jpg'),\
('m109','Apocalypse now','8.4','War/Drama','147','https://www.movieposters.com/cdn/shop/products/935e744b5a3e5b2f364af001b1ebe93b_d31f5d2b-b094-4e0a-b2be-d39c9ab22bdb_480x.progressive.jpg'),\
('m110','Schindlers list','9','War/Drama','195','https://www.movieposters.com/cdn/shop/products/9a1f9ea4a27071481cc1263e3ea11f7b_7bdb2deb-dd50-41b5-beab-8fc1cb3c895d_480x.progressive.jpg'),\
('m111','Inglourious bastards','8.3','War/Drama','153','https://www.movieposters.com/cdn/shop/products/inglourious-basterds-style1.11x17_480x.progressive.jpg'),\
('m112','The good, the bad and the ugly','8.8','Western','161','https://www.movieposters.com/cdn/shop/products/101f6a2ccb2635c88c1843a196c6b5c2_0419f536-6a82-48e6-b8f2-622aee181b85_480x.progressive.jpg'),\
('m113','The revenant','8','Western/Adventure','156','https://www.movieposters.com/cdn/shop/products/2b783ded82e94bebc6fdead47b6f57f0_5fc9baff-134d-4ef2-b90c-ee8d0643fb9c_480x.progressive.jpg'),\
('m114','Fight club','8.8','Thriller/Drama','139','https://m.media-amazon.com/images/M/MV5BODQ0OWJiMzktYjNlYi00MzcwLThlZWMtMzRkYTY4ZDgxNzgxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg'),\
('m115','Kill Bill: Volume 1','8.2','Action/Crime','111','https://www.movieposters.com/cdn/shop/products/killbill.vol1.reg.mp_480x.progressive.jpg')";

echo "movie details seeded successfully"
}

get_slot_ids_from_db () {
  slot_ids=($(PGPASSWORD='beach' psql -h localhost -U moviebooking -d moviebooking -p '5436' -qtc "select id from slot"))
}

clear_old_data () {
  echo "Truncating the following tables in database: movie, show, slot..."

  PGPASSWORD='beach' psql -h localhost -U moviebooking -d moviebooking -p '5436' -qc "truncate movie, show, slot";

  echo "Tables successfully truncated!"
}

seed_slot_data () {
  echo "Seeding slot data in database..."

  PGPASSWORD='beach' psql -h localhost -U moviebooking -d moviebooking -p '5436' -qc \
  "insert into slot (name, start_time, end_time) values \
  ('slot1', '09:00:00', '12:30:00'), \
  ('slot2', '13:30:00', '17:00:00'), \
  ('slot3', '18:00:00', '21:30:00'), \
  ('slot4', '22:30:00', '02:00:00')";

  echo "Slot data successfully seeded!"
}

get_random_movie_id(){

  lower_index=0
  upper_index=$(( ${#MOVIE_IDS[@]} - 1))

  echo ${MOVIE_IDS[$(( $RANDOM % (( $upper_index - $lower_index + 1 )) + $lower_index ))]}
}

get_price(){
  lower_price=150
  higher_price=300

  echo $(( $RANDOM % (( $higher_price - $lower_price + 1)) + $lower_price )).$(( RANDOM % 99))
}

initialise_dates () {
  if [ "$OS_TYPE" == "Darwin" ]
  then
    start_date=$(date -j -f %Y-%m-%d $1 +%Y-%m-%d)
  else
    start_date=$(date --date "$1" "+%Y-%m-%d")
  fi
  next_date=${start_date}
}

get_next_date () {
  if [ "$OS_TYPE" == "Darwin" ]
  then
    next_date_command="date -j -f %Y-%m-%d -v+1d $1 +%Y-%m-%d"
  else
    next_date_command="date --date \"$1 +1 day\" \"+%Y-%m-%d\""
  fi
  echo $(eval $next_date_command)
}

seed_data_for_first_day(){
  for ((slot_id = ${slot_ids[0]}; slot_id <= ${slot_ids[ ${#slot_ids[@]} - 1 ]}; slot_id++));
  do
    movie_id=$(get_random_movie_id)
    price=$(get_price)

    PGPASSWORD='beach' psql -h localhost -U 'moviebooking' -d 'moviebooking' -p '5436' -qc \
    "insert into show(movie_id,date,slot_id,cost) values ('$movie_id','${start_date}','$slot_id','$price')"
  done
}

seed_data_from_second_day_onwards () {
  next_date=$( get_next_date "$start_date" )
  for ((day = 2; day <= $1; day++));
  do
    for ((slot_id = ${slot_ids[0]}; slot_id <= ${slot_ids[ ${#slot_ids[@]} - 1 ]}; slot_id++));
    do
      movie_id=$( get_random_movie_id )
      price=$( get_price )
      PGPASSWORD='beach' psql -h localhost -U moviebooking -d moviebooking -p '5436' -qc \
      "insert into show (movie_id, date, slot_id, cost) values ('$movie_id', '$next_date', $slot_id, $price)"
    done
    next_date=$( get_next_date "$next_date" )
  done
}

number_of_weeks=$2

seed_shows(){

  echo "seeding shows data"

  number_of_days=$(( ${number_of_weeks} * 7 ))
  get_slot_ids_from_db

  if [ $number_of_days -ne 0 ]
  then
    seed_data_for_first_day
    seed_data_from_second_day_onwards "$number_of_days"
  fi

  echo "Show data successfully seeded!"
}

initialise_dates "$1"
clear_old_data
seed_movie_details
seed_slot_data
seed_shows