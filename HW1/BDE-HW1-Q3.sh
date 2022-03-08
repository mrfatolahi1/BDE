filename='/proc/cpuinfo'
file_text=""
n=1
while read line; do
    file_text="$file_text$line"
    n=$((n+1))
done < $filename

word_to_find1="processor"
word_to_find2="management:processor"
file_text_array=($file_text)
output=0

for word in "${file_text_array[@]}"; do
    if [[ "$word_to_find1" == "$word" || "$word_to_find2" == "$word" ]]; then
        echo $word
        output=$((output+1))
    fi
done

echo
echo $word_to_find $output