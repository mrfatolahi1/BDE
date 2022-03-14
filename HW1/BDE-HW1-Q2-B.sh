n=$1
#should be <32767

declare -a array
for i in {0..100000..1}; do 
    randomGeneratedNumber=$RANDOM
    bound=32767
    value=$(( $randomGeneratedNumber * $n / $bound ))
    array[i]=$value
done

zigma=0
# 5000000 = 100000 * 50
expected_value=$(( 5000000 / $n ))

for (( range_first_number=0; range_first_number<$n; range_first_number=range_first_number+50)); do
    echo $range_first_number
    range_last_number=$(( range_first_number + 50 ))
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
done
    
echo
echo "zigma:" $zigma

myValue1=$(( n / 50 ))
myValue2=$(( myValue1 - 1 ))
myValue3=$(( $myValue2 * $expected_value ))

myValue4=$(( 100000 - $expected_value ))
myValue5=$(( $myValue4 * $myValue4 ))
myValue6=$(( $myValue5 / $expected_value ))
myValue7=$(( $myValue6 + $myValue3 ))
echo "best zigma is 0 and worst zigma is:" $myValue7