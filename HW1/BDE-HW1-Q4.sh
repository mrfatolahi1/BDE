cd
cd Documents
mkdir scrap
cd scrap

address=$1
level=$2

wget \
 --recursive \
 --wait=0.2 \
 --user-agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36" \
 --level=$level \
 -e robots=off \
 --regex-type=pcre \
 --reject-regex='^https://(?!en\.)[^.]+\.wikipedia\.org/.*$' \
 --no-directories \
 --html-extension \
 $address


mkdir my_texts

#convert html files to text file and put them in my_texts folder 
for file in *.html; do 
    filename_without_extension="${file%.*}"
    pandoc -f html -t plain -o my_texts/$filename_without_extension.txt $file 
done 

cat my_texts/*.txt > ../ALL.txt
tr '[:punct:]' "\n" <../ALL.txt | tr ' ' "\n" | sort | uniq -c > ../RESULTS.txt