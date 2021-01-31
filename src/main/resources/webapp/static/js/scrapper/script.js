var tags = new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  cache: false,
  prefetch: {
    url: '/rest/tags',
    transform: function(list) {
      return $.map(list, function(tagName) {
        return { 'name' : tagName };
      });
    }
  }
});


tags.initialize(true);

$('.tags').tagsinput({
  typeaheadjs: {
    name: 'tags',
    displayKey: 'name',
    valueKey: 'name',
    source: tags.ttAdapter()
  }
});


function scrapNews(status, website) {
    var url = "/su/scrapper/" + website;

    var headlinesTags = $(".headline");
    var tagTags = $(".tags");

    var headlines = [];
    var tags = [];

    for (var i = 0; i < headlinesTags.length; i++) {
        if (headlinesTags[i].checked) {
            headlines.push(headlinesTags[i].value);
            tags.push(tagTags[i].value);
        }
    }

    jsonForm = {
        'headlines': headlines,
        'tags': tags,
        'status': status
    };
    console.log(jsonForm);

    $.post(url, jsonForm)
        .done(function(resp, statusText, c) {
            alert("Posts added :)");
        })
        .fail(function(jqXHR, statusText, errorThrown) {
            // console.log("error");
            // console.log(JSON.stringify(jqXHR));
            // console.log(JSON.stringify(statusText));
            // console.log(JSON.stringify(errorThrown));
            alert("Error :(");
        });

    return false;
}