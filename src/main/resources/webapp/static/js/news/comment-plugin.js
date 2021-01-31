// var disqus_config = function () {
// this.page.url = undefined;
// this.page.identifier = _page_identifier;
// };

// (function() { // DON'T EDITDIT BELOW THIS LINE
//     var d = document, s = d.createElement('script');
//     s.src = 'https://newsbyte-info.disqus.com/embed.js';
//     s.setAttribute('data-timestamp', +new Date());
//     (d.head || d.body).appendChild(s);
// })();

(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.8";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));