cd
cd Documents
mkdir git_test
cd git_test
git init
touch master.txt
git add -A
git commit -m "first commit"

for n in {0..11..1}; do
    init_number1=$((2**n))
    init_number2=$((init_number1+1))
    fib="fib"
    underline="_"
    git checkout master
    branch_name="$fib$underline$init_number1$underline$init_number2"
    git checkout -b $branch_name

    # create files
    for i in {1..10..1}; do
        file_extension=".txt"
        file_name="$i$file_extension"
        touch $file_name
        space="     "

        number1=$init_number1
        number2=$init_number2

        if [ $i -eq 1 ]; then
            echo $number1 >> $file_name
        elif [ $i -eq 2 ]; then
            echo $number2 >> $file_name
        elif [ $i -eq 3 ]; then
            sum=$(( $number1 + $number2 ))
            echo $sum >> $file_name
        else
            j=2
            while [ $j -ne $i ]; do
                sum=$(( $number1 + $number2 ))
                number1=$number2
                number2=$sum
                j=$(( j + 1 ))
            done
            echo $number2 >> $file_name
        fi

        git add $file_name
        commit_message="added_the_"$i"th_number."
        git commit -m $commit_message
    done

done

git checkout master