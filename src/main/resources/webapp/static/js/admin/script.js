var tags = new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  cache: false,
  prefetch: {
    url: '/rest/tags',
    transform: function(list) {
      return $.map(list, function(tagName) {
        return {
          name: tagName
        };
      });
    }
  }
});

tags.initialize();

$('#tags').tagsinput({
  typeaheadjs: {
    name: 'tags',
    displayKey: 'name',
    valueKey: 'name',
    source: tags.ttAdapter()
  }
});

function submitForm(status) {

  var url = "/su/save";
  var form = $('#newNews')[0];

  if (!form.checkValidity()) {
    form.reportValidity();
    return;
  }

  var jsonForm = $('#newNews').serialize('json');
  jsonForm['status'] = status;
  jsonForm['title'] = encodeURIComponent($('#headline').value).replace(/\s/g, "-");
  $.post(url, jsonForm)
    .done(function(resp, statusText, c) {
      alert("Post Saved");
    })
    .fail(function(jqXHR, statusText, errorThrown) {
      alert("Error");
    });

  return false;
}