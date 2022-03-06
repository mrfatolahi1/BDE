echo "Enter directory path: "
read directory_path

if [ ! -d "$directory_path" ]
then
    echo "Directory does not exists."
    exit 1
fi

echo

biggest_value=0
for filename in "$directory_path"/*
do
	filename_without_path=$(basename -- "$filename")
	filename_without_extension="${filename_without_path%.*}"
	length=${#filename_without_extension}
	echo $length "	-->	"  $filename_without_extension
	if [ $length -gt $biggest_value ]; then
		biggest_value=$length
	fi
done
echo -e "\nbiggest value is: " $biggest_value
