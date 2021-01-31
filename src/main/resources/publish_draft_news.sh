#!/bin/bash -x
query="select min(newsId) from news where status='DRAFT'"
id=`mysql -u newsbox -pnewsbox newsbox -s -e "${query}"`

query="update news set status='PUBLISHED', publish_date=current_timestamp(), visits=0 where newsId=${id};"
headlineAndLink="select newsId, title, headline from news where newsId=${id}"

row=`mysql -u newsbox -pnewsbox newsbox -s -e "${headlineAndLink}"`

id=`echo $row | cut -d " " -f 1`
title=`echo $row | cut -d " " -f 2`
message="`echo $row | cut -d " " -f 3-`"

if [[ (-z "${title// }") || (-z "${id// }") || (-z "${message// }") ]]
then
    echo "Error in posting : " ${id} ${title} ${message}
    exit 2
fi

link="http://newsbyte.info/news/$id/$title"
url="http://newsbyte.info/php/postToPage.php?qweefxa=6sd6a8s"

mysql -u newsbox -pnewsbox newsbox -e "${query}"
curl --request POST $url --data-urlencode "link=${link}"  --data-urlencode "message=${message}" --data-urlencode "qasdf12d3=dtwvfbxyt783t478btx7t7b182t1"

#User crontab job : 0 8-22 * * * PATH_TO_FILE