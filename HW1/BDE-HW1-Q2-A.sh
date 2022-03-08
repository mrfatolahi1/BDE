expected_value=10
declare -a array
for i in {0..100000..1}; do 
     array[i]=$RANDOM
done

# echo ${array[*]}

zigma=0

for range_first_number in {0..32767..50}; do
    echo $range_first_number
    range_last_number=$(( range_first_number + 10 ))
    # if [ $range_last_number -eq 32770 ]; then
    #     break
    # fi
    number_of_random_numbers_in_range=0
    for random_number in "${array[@]}"; do
        if [[ $random_number -ge $range_first_number && $random_number -lt $range_last_number ]]; then
            number_of_random_numbers_in_range=$((number_of_random_numbers_in_range+1))
        fi
    done
    value1=$(( $number_of_random_numbers_in_range - $expected_value ))
    value2=$(( $value1 * $value1 ))
    value3=$(( $value2 / $expected_value ))
    zigma=$(( $zigma + $value3 ))
    # if [ $number_of_random_numbers_in_range -ne 0 ]; then
    #     echo $number_of_random_numbers_in_range
    # fi
done
    
echo
echo $zigma

