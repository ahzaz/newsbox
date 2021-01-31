function confirmDelete(headline, id) {
  var confirmation = confirm("Delete : " + headline);

  if (confirmation) {
    var url = "/su/delete/" + id;
    $.post(url)
      .done(function(resp, statusText, c) {
        alert("Post Deleted");
        window.location = "/";
      })
      .fail(function(jqXHR, statusText, errorThrown) {
        // handleError(jqXHR.status, jqXHR.statusText);
        alert("Error");
      });
  }

}